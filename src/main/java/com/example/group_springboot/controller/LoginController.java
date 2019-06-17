package com.example.group_springboot.controller;

import com.example.group_springboot.component.EncryptorComponent;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String TEACHER_ROLE = "bb63e5f7e0f2ffae845c";
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    public void login(@RequestBody Map<String,String> user, HttpServletResponse response) {
        Optional.ofNullable(userService.getUser(user.get("number")))
                .ifPresentOrElse(u -> {
                    if (!passwordEncoder.matches(user.get("password"), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    // 生成加密token
                    String token = encryptorComponent.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("token", token);
                    String role = null;
                    if (u.getAuthority() == User.USER_AUTHORITY) {
                        role = TEACHER_ROLE;
                    } if (u.getAuthority() == User.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }
                    response.setHeader("role", role);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名错误");
                });
    }
}
