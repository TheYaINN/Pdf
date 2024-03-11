package de.joachimsohn.pdfdemo.domain;

import de.joachimsohn.pdfdemo.web.model.PdfDto;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfService {

    @NotNull PdfDto fetchById(final @NotNull UUID id);

    @NotNull PdfDto create(final @NotNull Object body);
}
