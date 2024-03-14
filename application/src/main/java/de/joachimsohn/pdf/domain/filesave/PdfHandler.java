package de.joachimsohn.pdf.domain.filesave;

import de.joachimsohn.pdf.domain.model.Pdf;
import org.jetbrains.annotations.NotNull;

public interface PdfHandler {

    String save(final @NotNull Pdf pdf);

    byte[] load(final @NotNull String path);
}
