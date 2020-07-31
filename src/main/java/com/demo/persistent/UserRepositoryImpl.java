package com.demo.persistent;

import com.demo.domain.model.User;
import com.demo.domain.repository.UserRepository;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = new ArrayList<>();

    @Override
    public User save(User user) {
        user.setId(UUID.randomUUID().toString());
        userList.add(user);
        return user;
    }

    @Override
    public Boolean selectByIdCode(User user) {
        //userList.add(user);
        boolean flag = false;
        for (User user1:userList){
            if (user.getIdCode().equals(user1.getIdCode())){
                flag =true;
            }
        }
        return flag;
    }
}
