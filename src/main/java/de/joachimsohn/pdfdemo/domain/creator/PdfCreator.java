package de.joachimsohn.pdfdemo.domain.creator;

import de.joachimsohn.pdfdemo.domain.model.PdfData.PdfType;
import org.jetbrains.annotations.NotNull;

public interface PdfCreator {

    boolean applies(final @NotNull PdfType type);

    @NotNull String create(final @NotNull Object input);
}
