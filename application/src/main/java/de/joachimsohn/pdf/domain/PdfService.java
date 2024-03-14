package de.joachimsohn.pdf.domain;

import de.joachimsohn.pdf.domain.model.Pdf;
import de.joachimsohn.pdf.domain.model.PdfData;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfService {

    @NotNull Pdf fetchById(final @NotNull UUID id);

    @NotNull Pdf create(final @NotNull PdfData pdfData);
}
