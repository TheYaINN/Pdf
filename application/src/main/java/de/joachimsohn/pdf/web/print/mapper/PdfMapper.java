package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.impl.PdfA;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfADto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class PdfMapper {

    public @NotNull PdfData toDomain(final @NotNull PdfDataWrapper wrapper) {
        return PdfA.builder()
                .contextData(PdfA.PdfAContextData.builder()
                        .hiddenData(((PdfADto.PdfADataDto)wrapper.getData()).getTest())
                        .build())
                .build();
    }
}
