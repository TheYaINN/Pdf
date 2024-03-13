package de.joachimsohn.pdfdemo.web.print;

import de.joachimsohn.pdfdemo.domain.PdfService;
import de.joachimsohn.pdfdemo.domain.model.Pdf;
import de.joachimsohn.pdfdemo.web.print.mapper.PdfMapper;
import de.joachimsohn.pdfdemo.web.print.model.PdfDto;
import de.joachimsohn.pdfdemo.web.print.model.data.PdfDataWrapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class PdfWebAdapter {

    private final PdfService service;
    private final PdfMapper mapper;

    public @NotNull PdfDto create(final @NotNull PdfDataWrapper wrapper) {
        return toDto(service.create(mapper.toDomain(wrapper)));
    }

    public @NotNull PdfDto get(final @NotNull UUID id) {
        return toDto(service.fetchById(id));
    }


    public @NotNull PdfDto toDto(final @NotNull Pdf pdf) {
        return PdfDto.builder()
                .id(pdf.id())
                .name("%s.pdf".formatted(pdf.id()))
                .content(pdf.content())
                .build();
    }
}
