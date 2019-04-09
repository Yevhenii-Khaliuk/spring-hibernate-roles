package com.khaliuk.service;

import com.khaliuk.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);

    User register(User user);

    User emailVerification(String token);
}
