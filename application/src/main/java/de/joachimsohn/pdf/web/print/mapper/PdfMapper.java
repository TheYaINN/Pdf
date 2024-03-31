package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.domain.model.Pdf;
import de.joachimsohn.pdf.domain.model.PdfData;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public final class PdfMapper {

    private final List<PdfWebMapper> mappers;

    public @NotNull PdfData toDomain(final @NotNull PdfDataWrapper wrapper) {
        return mappers.stream()
                .filter(m -> m.applies(wrapper.getType()))
                .findFirst()
                .map(m -> m.convert(wrapper.getData()))
                .orElseThrow(IllegalArgumentException::new);
    }

    public @NotNull PdfDto toDto(final @NotNull Pdf pdf) {
        return PdfDto.builder()
                .id(pdf.id())
                .name("%s.pdf".formatted(pdf.id()))
                .content(pdf.content())
                .build();
    }
}
