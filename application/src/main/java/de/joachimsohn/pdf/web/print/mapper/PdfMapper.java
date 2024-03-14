package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.impl.PdfA;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfADto;
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
}
