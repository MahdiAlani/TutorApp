package com.example.ututor.Listing;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "listings")
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int tutor_id;

    @Column
    private String description;

    @Column
    private String price_per_hour;

    @Column
    private LocalDate created_date;

    public Listing() {}

    public Listing(Long id, int tutor_id, String description, String price_per_hour, LocalDate created_date) {
        this.id = id;
        this.tutor_id = tutor_id;
        this.description = description;
        this.price_per_hour = price_per_hour;
        this.created_date = created_date;
    }
}