package de.joachimsohn.pdf.web.print.model.data.impl;

import de.joachimsohn.pdf.web.print.model.data.PdfDataDto;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.PdfType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public final class PdfADto implements PdfDataWrapper {

    private PdfAData aData;

    @Override
    public @NotNull PdfType getType() {
        return PdfType.TYPE_A;
    }

    @Override public @NotNull PdfDataDto getData() {
        return aData;
    }

    public static final class PdfAData implements PdfDataDto {
        private String test;
    }

}
