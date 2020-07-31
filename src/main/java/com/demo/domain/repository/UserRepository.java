package com.demo.domain.repository;

import com.demo.domain.model.User;

public interface UserRepository {

    User save(User user);
    Boolean selectByIdCode(User user);
}
