package com.demo.service;

import com.demo.controller.request.UserRequest;
import com.demo.domain.model.User;
import com.demo.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* @ClassName: UserServiceTest 
* @Description: 
* @author jinhj 
* @date 2020/7/31 
* @version V1.0 
*/
public class UserServiceTest{
    @Autowired
    private UserService userService;

    @Test
    void should_user_info_not_all() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .idType("220106199001108214")
                .phoneNo("")
                .build();

        Exception exception = assertThrows(
                Exception.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("必输"));
    }

    @Test
    void should_user_info_phoneno_super_long() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .phoneNo("1111111111111111111111")
                .build();
        Exception exception = assertThrows(
                Exception.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("长度不能"));
    }

    @Test
    void should_user_info_register() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .phoneNo("18088669212")
                .build();

        Exception exception = assertThrows(
                Exception.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("长度不能"));
    }

    @Test
    void should_user_info_real_name_verification() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .phoneNo("18088669212")
                .build();
        Exception exception = assertThrows(
                Exception.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("实名验证失败"));
    }

    @Test
    void should_user_info_send_sms() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .phoneNo("18088669212")
                .build();
        String res=userService.register(user);
        assertTrue(res.contains("成为该平台的注册用户"));
    }
}