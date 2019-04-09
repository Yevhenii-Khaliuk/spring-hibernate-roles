package com.khaliuk.dao;

import com.khaliuk.model.Role;

public interface RoleDao  {
    Role getByRolename(String rolename);
}
