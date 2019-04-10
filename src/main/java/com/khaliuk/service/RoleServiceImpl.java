package com.khaliuk.service;

import com.khaliuk.dao.RoleDao;
import com.khaliuk.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getByRolename(String rolename) {
        return roleDao.getByRolename(rolename);
    }
}
