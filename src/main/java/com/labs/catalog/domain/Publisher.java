package com.labs.catalog.domain;

import javax.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "publisher")
public class Publisher extends AbstractBaseEntity {
    @Serial
    private static final long serialVersionUID = 2938165267791997824L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
    @SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "address")
    private String address;

    // Optional -> Jika hanya menginginkan relasi yg bersifat 2 arah
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
