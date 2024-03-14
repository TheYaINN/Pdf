package de.joachimsohn.pdf.web.print.model.data.impl;

import de.joachimsohn.pdf.web.print.model.data.PdfDataDto;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.PdfType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public final class PdfBDto implements PdfDataWrapper {

    private PdfBData bdata;

    @Override
    public @NotNull PdfType getType() {
        return PdfType.TYPE_B;
    }

    @Override public @NotNull PdfDataDto getData() {
        return bdata;
    }

    public static final class PdfBData implements PdfDataDto {
        private String important;
    }

}
