package de.joachimsohn.pdfdemo.domain.repository.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Pdf {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String path;
}
