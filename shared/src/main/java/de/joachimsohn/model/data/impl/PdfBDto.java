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
@AllArgsConstructor
@NoArgsConstructor
public final class PdfBDto implements PdfDataWrapper {

    private PdfBDataDto data;

    @Override public @NotNull String getType() {
        return PdfType.TYPE_B;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class PdfBDataDto implements PdfDataDto {
        private String important;
    }

}
