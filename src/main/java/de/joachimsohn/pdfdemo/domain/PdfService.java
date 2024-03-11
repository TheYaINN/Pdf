package de.joachimsohn.pdfdemo.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface PdfService {

    @NotNull Pdf save(final @NotNull Pdf pdf);

    @NotNull List<Pdf> fetch();

    @NotNull Pdf update(final @NotNull Pdf pdf, final @NotNull UUID id);

    void deleteById(final @NotNull UUID id);
}
