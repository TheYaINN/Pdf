package de.joachimsohn.pdf.domain.model.impl;

import de.joachimsohn.pdf.domain.model.PdfContextData;
import de.joachimsohn.pdf.domain.model.PdfData;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public final class PdfA implements PdfData {
    @Override public @NotNull PdfType getType() {
        return PdfType.TYPE_A;
    }

    private PdfAContextData contextData;

    @Data
    @Builder
    public static class PdfAContextData implements PdfContextData {

        private String hiddenData;

    }

}
