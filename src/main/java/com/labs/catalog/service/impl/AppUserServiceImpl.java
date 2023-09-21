package com.labs.catalog.service.impl;

import com.labs.catalog.dto.UserDetailResponseDTO;
import com.labs.catalog.exception.ResourceNotFoundException;
import com.labs.catalog.repository.AppUserRepository;
import com.labs.catalog.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username"));
    }

    @Override
    public UserDetailResponseDTO findUserDetail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetailResponseDTO dto = new UserDetailResponseDTO();
        String username = securityContext.getAuthentication().getName();
        dto.setName(username);
        return dto;
    }
}
