package com.labs.catalog.service;

import com.labs.catalog.dto.UserDetailResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    public UserDetailResponseDTO findUserDetail();
}
