package com.labs.catalog.repository;

import com.labs.catalog.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    public Optional<Publisher> findBySecureId(String secureId);
}
