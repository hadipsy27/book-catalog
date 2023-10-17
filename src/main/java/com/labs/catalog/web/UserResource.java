package com.labs.catalog.web;

import com.labs.catalog.dto.UserDetailResponseDTO;
import com.labs.catalog.service.AppUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserResource {

    private AppUserService appUserService;

    @GetMapping("/v1/user")
    public ResponseEntity<UserDetailResponseDTO> findUserDetail(){
        return ResponseEntity.ok(appUserService.findUserDetail());
    }
}
