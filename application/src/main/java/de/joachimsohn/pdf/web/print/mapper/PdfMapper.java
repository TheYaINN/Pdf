package de.joachimsohn.pdf.web.print.mapper;

import de.joachimsohn.pdf.domain.model.PdfContextData;
import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class PdfMapper {

    public @NotNull PdfData toDomain(final @NotNull PdfDataWrapper wrapper) {
        return new PdfData() {
            @Override public @NotNull PdfType getType() {
                return switch (wrapper.getType()) {
                    case TYPE_A -> PdfType.TYPE_A;
                    case TYPE_B -> PdfType.TYPE_B;
                };
            }

            @Override public @NotNull PdfContextData getContextData() {
                return new PdfContextData() {
                };
            }
        };
    }
}
