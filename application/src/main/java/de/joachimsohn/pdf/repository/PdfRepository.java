package de.joachimsohn.pdf.repository;

import de.joachimsohn.pdf.repository.model.Pdf;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@EnableJpaAuditing
public interface PdfRepository extends CrudRepository<Pdf, UUID> {
}
