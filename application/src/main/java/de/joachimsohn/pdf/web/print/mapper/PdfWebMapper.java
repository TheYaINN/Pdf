package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.web.print.model.data.PdfDataDto;
import org.jetbrains.annotations.NotNull;

public interface PdfWebMapper {

    boolean applies(final @NotNull String type);

    @NotNull PdfData convert(final @NotNull PdfDataDto data);
}
