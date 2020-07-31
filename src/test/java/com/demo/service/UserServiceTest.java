package com.demo.service;

import com.demo.Application;
import com.demo.BaseTest;
import com.demo.controller.request.UserRequest;
import com.demo.domain.model.User;
import com.demo.domain.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* @ClassName: UserServiceTest 
* @Description: 
* @author jinhj 
* @date 2020/7/31 
* @version V1.0 
*/

public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Before
    public void Before(){
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .phoneNo("18088989212")
                .build();
        try {
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void should_user_info_not_all() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .idType("1")
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
                .password("123456789")
                .realName("张三")
                .phoneNo("18088669212")
                .build();

        Exception exception = assertThrows(
                Exception.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("用户已注册"));
    }

    @Test
    void should_user_info_real_name_verification() throws Exception {
        User user=User.builder()
                .idCode("220106199001108214")
                .idType("1")
                .realName("张三")
                .password("123456789")
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
                .password("123456789")
                .phoneNo("18088669212")
                .build();
        String res=userService.register(user);
        assertTrue(res.contains("成为该平台的注册用户"));
    }
}