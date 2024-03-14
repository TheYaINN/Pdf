package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.impl.PdfB;
import de.joachimsohn.pdf.domain.model.impl.PdfB.PdfBContextData;
import de.joachimsohn.pdf.web.print.model.data.PdfDataDto;
import de.joachimsohn.pdf.web.print.model.data.PdfType;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfBDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class PdfBWebMapperImpl implements PdfWebMapper {

    @Override
    public boolean applies(final @NotNull String type) {
        return PdfType.TYPE_B.equals(type);
    }

    @Override
    public @NotNull PdfData convert(final @NotNull PdfDataDto data) {
        final var casted = (PdfBDto.PdfBDataDto) data;
        return PdfB.builder()
                .contextData(PdfBContextData.builder()
                        .someBData(casted.getImportant())
                        .build())
                .build();
    }
}
