package de.joachimsohn.pdf.domain.model.impl;

import de.joachimsohn.pdf.domain.model.PdfContextData;
import de.joachimsohn.pdf.domain.model.PdfData;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public final class PdfB implements PdfData {
    @Override public @NotNull PdfType getType() {
        return PdfType.TYPE_B;
    }

    private PdfBContextData contextData;

    @Data
    @Builder
    public static class PdfBContextData implements PdfContextData {

        private String someBData;

    }

}
