package de.joachimsohn.pdfdemo.repository;

import de.joachimsohn.pdfdemo.repository.model.Pdf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PdfRepository extends CrudRepository<Pdf, UUID> {
}
