/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ruoyi.common.core.alg;

import java.math.BigInteger;
import static java.math.BigInteger.ZERO;
import java.util.Random;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 使用该工具类，需调用init方法进行参数初始化
 * */
/**
 * 密钥生成： 1、随机选择两个大质数p和q满足gcd（pq,(p-1)(q-1)）=1。 这个属性是保证两个质数长度相等。 2、计算 n = p*q和λ=
 * lcm (p - 1,q-1)。 3、选择 随机整数 g使得gcd(L(g^lambda % n^2) , n) = 1,满足g属于n^2;
 * 4、公钥为（N，g） 5、私钥为lambda。 :加密 选择随机数r满足 计算密文 其中m为加密信息
 *
 * 解密： m = D(c,lambda) = ( L(c^lambda%n^2)/L(g^lambda%n^2) )%n; 其中L(u) =
 * (u-1)/n;
 */
@Component
public class Paillier {

    // 选取两个较大的质数p与q，lambda是p-1与q-1的最小公倍数
    @Value("${paillier.p}")
    private BigInteger p;

    @Value("${paillier.q}")
    private BigInteger q;

    private BigInteger lambda;

    // n 是p，q的乘积
    private BigInteger n;
    // n_square = n*n
    private BigInteger n_square;
    /* g 是随机选择的整数，属于小于n的平方n_square中的整数集。且满足
     * g的lambda次方对n的平方求模后减一后再除与n，最后再将其与n求最大公约数，且最大公约数等于一。
     * 即 gcd (L(g^lambda mod nsquare), n) = 1
     */
    private BigInteger g;
    // 模数的位数
    private int bitLength = 512;
    //新的大整数表示质数的概率将超过（1-2^（-certainty））。p, q生成的执行时间与此参数的值成正比。
    private int certainty = 64;

    public void init() {
        // 初始化n的值
        n = p.multiply(q);
        // 初始化n_square的值
        n_square = n.multiply(n);
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
                .divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

        // 选取g
        g = new BigInteger("2");
        /* 检查 g 是否符合取值规则 */
        if (g.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("g is not good. Choose g again.");
            System.exit(1);
        }
    }

    public void init(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        // 初始化n的值
        this.init();
    }

    /**
     * create by: BEATREE description: 用来生成大质数 p / q create time: 2020/3/25
     * 16:25
     *
     * @return String
     */
    public String generateKeyPQ() {
        // 构造随机生成的可能是素数的正大整数，具有指定的位长和确定性
        BigInteger key = new BigInteger(bitLength / 2, certainty, new Random());
        return key.toString();
    }

    /**
     * 加密明文 m. ciphertext c = g^m * r^n mod n^2. 方法内部随机生成 r 进行辅助加密吗，
     *
     * @param m BigInteger类型的明文值
     * @return BigInteger类型的密文
     */
    public BigInteger encryption(BigInteger m) {
        // 随机r用来辅助加密
        BigInteger r = new BigInteger(bitLength, new Random());
        return g.modPow(m, n_square).multiply(r.modPow(n, n_square)).mod(n_square);
    }

    /**
     * 解密密文 c. plaintext m = L(c^lambda mod n^2) * u mod n, 其中 u = (L(g^lambda
     * mod n^2))^(-1) mod n.
     *
     * @param c 密文
     * @return BigInteger类型的明文
     */
    public BigInteger decryption(BigInteger c) {
        BigInteger u = g.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

    /**
     * 密文 em1 和 em2的和
     *
     * @param em1
     * @param em2
     * @return
     */
    public BigInteger cipherAdd(BigInteger em1, BigInteger em2) {
        return em1.multiply(em2).mod(n_square);
    }

    /**
     * 密文数组ems的和
     *
     * @param ems
     * @return
     */
    public BigInteger cipherAdd(BigInteger[] ems) {
        BigInteger ret = ZERO;
        if (isNotEmpty(ems)) {
            ret = ems[0];
            if (ems.length > 1) {
                for (int idx = 1; idx < ems.length; idx++) {
                    ret = cipherAdd(ret, ems[idx]);
                }
            }
        }
        return ret;
    }
}
