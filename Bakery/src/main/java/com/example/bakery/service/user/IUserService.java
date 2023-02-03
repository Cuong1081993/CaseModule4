package com.example.bakery.service.user;

import com.example.bakery.model.User;
import com.example.bakery.model.dto.UserDTO;
import com.example.bakery.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String username);
}

