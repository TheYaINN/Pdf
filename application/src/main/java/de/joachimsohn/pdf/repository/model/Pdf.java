package de.joachimsohn.pdf.repository.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public final class Pdf {

    @Id
    @Column(nullable = false, updatable = false)
    private @NotNull UUID id;

    @Default
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private @NotNull LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private @NotNull String path;


    public String name() {
        return "%s.pdf".formatted(id);
    }
}
