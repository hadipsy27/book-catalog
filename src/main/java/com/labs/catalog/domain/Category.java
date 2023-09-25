package com.labs.catalog.domain;

import javax.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = -7370220842171704593L;

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    // Many to many relationships yang bersifat unidirectional
    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}
