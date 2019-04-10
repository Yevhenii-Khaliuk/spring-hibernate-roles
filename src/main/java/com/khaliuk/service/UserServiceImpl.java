package com.khaliuk.service;

import com.khaliuk.dao.UserDao;
import com.khaliuk.model.Role;
import com.khaliuk.model.User;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MailService mailService;

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public User register(User user) {
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setToken(getToken());
        user.setStatus(User.UserStatus.PENDING_VERIFICATION);
        User savedUser = userDao.addUser(user);
        mailService.send(savedUser);
        return savedUser;
    }

    @Override
    public User emailVerification(String token) {
        User user = userDao.getByToken(token);
        user.setStatus(User.UserStatus.ACTIVE);
        Role role = roleService.getByRolename("USER");
        user.setRoles(Arrays.asList(role));
        userDao.updateUser(user);
        return user;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);
        List<SimpleGrantedAuthority> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), roles);
    }
}
