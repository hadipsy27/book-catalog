package com.labs.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Table(indexes = {
        @Index(name = "uk_secure_id", columnList = "secure_id")
})
@Data
public abstract class AbstractBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1200123580561118253L;

    @Column(name = "secure_id", nullable = false, unique = true)
    private String secureId = UUID.randomUUID().toString();

    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;
}
