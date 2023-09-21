package com.ruoyi.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.framework.shiro.service.SysRegisterService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@Controller
public class SysRegisterController extends BaseController {

    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysRoleService roleService;
    
    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        if (isEmpty(msg)) {
            SysUser newUser = sysUserService.selectUserByLoginName(user.getLoginName());
            SysRole role = roleService.selectRoleByKey("employee");
            if (nonNull(role) && nonNull(newUser)) {
                sysUserService.insertUserAuth(newUser.getUserId(), new Long[]{role.getRoleId()});
            }
        }
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
