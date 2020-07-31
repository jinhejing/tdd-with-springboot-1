package com.demo.domain.service;

import com.demo.domain.model.User;
import com.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String register(User user) throws Exception {
        checkUserInfo(user);
        if(userRepository.selectByIdCode(user.getIdCode())){
            throw new Exception("用户已注册");
        }
        realNameVerification(user);
        User user1=userRepository.save(user);
        sendSMS(user1);
        return user1.getRealName()+"成为该平台的注册用户";
    }

    private void checkUserInfo(User user) throws Exception {
        if(user.getPhoneNo().length()>20){
            throw new Exception("手机号码长度不能超过20位");
        }
        if (user.getIdCode().length()>18){
            throw new Exception("身份证号长度不能超过18位");
        }
        if (user.getRealName().length()>20){
            throw new Exception("姓名长度不能超过20位");
        }
        if (user.getPassword().length()<8||user.getPassword().length()>16){
            throw new Exception("密码长度不能小于8，大于16位");
        }
    }

    private void realNameVerification(User user) throws Exception{
        //调实名验证接口
        if(false){
            throw new Exception("实名验证失败");
        }
    }

    private void sendSMS(User user) throws Exception{
        //调实名验证接口

    }
}
