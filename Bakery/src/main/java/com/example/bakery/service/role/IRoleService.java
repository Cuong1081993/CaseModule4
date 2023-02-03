package com.example.bakery.service.role;

import com.example.bakery.model.Role;
import com.example.bakery.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
