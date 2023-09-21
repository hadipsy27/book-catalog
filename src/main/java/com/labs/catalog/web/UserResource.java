package com.labs.catalog.web;

import com.labs.catalog.dto.UserDetailResponseDTO;
import com.labs.catalog.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserResource {

    private AppUserService appUserService;

    @GetMapping("/v1/user")
    public ResponseEntity<UserDetailResponseDTO> findUserDetail(){
        return ResponseEntity.ok(appUserService.findUserDetail());
    }
}
