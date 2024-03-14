package de.joachimsohn.pdf.domain.creator;

import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.PdfData.PdfType;
import org.jetbrains.annotations.NotNull;

public interface PdfCreator {

    boolean applies(final @NotNull PdfType type);

    @NotNull String create(final @NotNull PdfData data);
}
