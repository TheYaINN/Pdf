package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.model.data.PdfDataDto;
import de.joachimsohn.model.data.PdfType;
import de.joachimsohn.model.data.impl.PdfADto;
import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.impl.PdfA;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class PdfAWebMapperImpl implements PdfWebMapper {

    @Override
    public boolean applies(final @NotNull String type) {
        return PdfType.TYPE_A.equals(type);
    }

    @Override
    public @NotNull PdfData convert(final @NotNull PdfDataDto data) {
        final var casted = (PdfADto.PdfADataDto) data;
        return PdfA.builder()
                .contextData(PdfA.PdfAContextData.builder()
                        .hiddenData(casted.getTest())
                        .build())
                .build();
    }
}
