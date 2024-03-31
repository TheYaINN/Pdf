package de.joachimsohn.pdf.web.print;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.domain.PdfService;
import de.joachimsohn.pdf.web.print.mapper.PdfMapper;
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
        return mapper.toDto(service.create(mapper.toDomain(wrapper)));
    }

    public @NotNull PdfDto get(final @NotNull UUID id) {
        return mapper.toDto(service.fetchById(id));
    }

}
