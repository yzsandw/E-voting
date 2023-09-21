/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ruoyi.vote.enums;

import java.math.BigInteger;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.TWO;//2权值
import static java.math.BigInteger.TEN;//10权值

/**
 * @Description:
 * @author wmao
 * @date: 2021年2月15日 下午12:17:25
 */
public enum Vote {
    YES(ONE, "是"), NO(ZERO, "否");

    private BigInteger value;

    private String desc;

    Vote(BigInteger value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return the value
     */
    public BigInteger getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigInteger value) {
        this.value = value;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
