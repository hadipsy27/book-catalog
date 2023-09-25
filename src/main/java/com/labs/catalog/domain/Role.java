package com.labs.catalog.domain;

import javax.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = -2740735005522546373L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_"+name;
    }
}
