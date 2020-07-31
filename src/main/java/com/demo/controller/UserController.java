package com.demo.controller;

import com.demo.controller.request.UserRequest;
import com.demo.domain.model.User;
import com.demo.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@Validated @RequestBody UserRequest request) throws Exception {
        User user = transfer(request);
        return userService.register(user);
    }

    private User transfer(UserRequest request) {
        return User.builder()
                .phoneNo(request.getPhoneNo())
                .password(request.getPassword())
                .realName(request.getRealName())
                .idType(request.getIdType())
                .idCode(request.getIdCode())
                .build();
    }
}
