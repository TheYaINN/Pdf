package de.joachimsohn.pdfdemo.web.print.model.data;

import org.jetbrains.annotations.NotNull;

public interface PdfDataWrapper {

    @NotNull PdfType getType();

    @NotNull PdfDataDto getData();

}
