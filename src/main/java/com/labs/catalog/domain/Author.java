package com.labs.catalog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
@DynamicUpdate // Hanya mengupdate kolom yg di request saja
public class Author {

    // Jika membuat batch insert gunakan generationType nya SEQUENCE untuk meng enable batch insert
    // maka spring akan membuat batch insert = true
    // dan apabila Generation Type nya IDENTITY
    // maka spring akan mengeksekusi query nya secara terpisah (satu per satu) [batch insert disable]
    // Hal ini akan menjadi issue apabila data yg di masukkan secara batch jumlahnya banyak dalam satu request
    // apabila tetap akan menggunakan IDENTITY maka cara terbaik yaitu dengan menggunakan Strore Prosedure

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "author_name", nullable = false, columnDefinition = "varchar(300)")
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;
}
