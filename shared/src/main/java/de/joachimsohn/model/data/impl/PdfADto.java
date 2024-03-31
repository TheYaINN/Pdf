package de.joachimsohn.model.data.impl;

import de.joachimsohn.model.data.PdfDataDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import de.joachimsohn.model.data.PdfType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PdfADto implements PdfDataWrapper {

    private PdfADataDto data;

    @Override public @NotNull String getType() {
        return PdfType.TYPE_A;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class PdfADataDto implements PdfDataDto {
        private String test;
    }

}
