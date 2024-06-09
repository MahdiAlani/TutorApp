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

    @JoinColumn(name="tutorId")
    private Long tutorId;

    private String description;

    private String price_per_hour;

    private LocalDate created_date;

    public Listing() {}

    public Listing(Long id, Long tutorId, String description, String price_per_hour, LocalDate created_date) {
        this.id = id;
        this.tutorId = tutorId;
        this.description = description;
        this.price_per_hour = price_per_hour;
        this.created_date = created_date;
    }
}