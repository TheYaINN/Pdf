package de.joachimsohn.pdf.web.print.model.data.impl;

import de.joachimsohn.pdf.web.print.model.data.PdfDataDto;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.PdfType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public final class PdfADto implements PdfDataWrapper {

    private PdfADataDto data;

    @Override public @NotNull String getType() {
        return PdfType.TYPE_A;
    }

    @Data
    public static final class PdfADataDto implements PdfDataDto {
        private String test;
    }

}
