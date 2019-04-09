package com.khaliuk.dao;

import com.khaliuk.model.User;

public interface UserDao {
    User getByUsername(String username);

    User addUser(User user);

    User getByToken(String token);

    User updateUser(User user);
}
