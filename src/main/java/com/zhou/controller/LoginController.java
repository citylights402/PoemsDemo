package com.zhou.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhou.common.LoginDto;
import com.zhou.common.Result;
import com.zhou.entity.User;
import com.zhou.service.UserService;
import com.zhou.service.impl.UserServiceImpl;
import com.zhou.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 */
@RestController
public class LoginController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
//        if(!user.getPassword().equals(loginDto.getPassword())){
            System.out.println(user.getPassword());
            System.out.println(SecureUtil.md5(loginDto.getPassword()));
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generate(user.getUsername());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .map()
        );
    }

//    @RequiresAuthentication
//    @GetMapping("/logout")
//    public Result logout() {
//        SecurityUtils.getSubject().logout();
//        return Result.succ(null);
//    }

    //接口测试
    @GetMapping("api")
    public String  api(){//拿到请求头里的Token
//        String jwt=httpServletRequest.getHeader("Authorization");
//        if (jwt==null){
//            return "请先登录";
//
//        }
        return "api请求数据成功";
    }
}
