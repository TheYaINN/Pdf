package de.joachimsohn.pdfdemo.web;

import de.joachimsohn.pdfdemo.domain.PdfService;
import de.joachimsohn.pdfdemo.domain.model.Pdf;
import de.joachimsohn.pdfdemo.web.model.PdfDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class PdfWebAdapter {

    private final PdfService service;

    public @NotNull PdfDto create(final @NotNull Object someModel) {
        return toDto(service.create(someModel));
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
