package de.joachimsohn.pdfdemo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PdfRepository extends CrudRepository<Pdf, UUID> {
}
