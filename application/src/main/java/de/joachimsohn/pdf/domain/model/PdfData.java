package de.joachimsohn.pdf.domain.model;

import org.jetbrains.annotations.NotNull;

public interface PdfData {

    @NotNull PdfType getType();

    @NotNull PdfContextData getContextData();

    enum PdfType {
        TYPE_A,
        TYPE_B
    }
}
