package de.joachimsohn.pdfdemo.domain;

import de.joachimsohn.pdfdemo.domain.model.Pdf;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfService {

    @NotNull Pdf fetchById(final @NotNull UUID id);

    @NotNull Pdf create(final @NotNull Object body);
}
