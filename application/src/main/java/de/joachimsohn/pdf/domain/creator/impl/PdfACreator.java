package de.joachimsohn.pdf.domain.creator.impl;

import de.joachimsohn.pdf.domain.creator.BasePdfCreator;
import de.joachimsohn.pdf.domain.model.PdfContextData;
import de.joachimsohn.pdf.domain.model.PdfData.PdfType;
import de.joachimsohn.pdf.domain.model.impl.PdfA.PdfAContextData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public final class PdfACreator extends BasePdfCreator {

    @Override public boolean applies(final @NotNull PdfType type) {
        return PdfType.TYPE_A.equals(type);
    }

    @Override protected @NotNull String getTemplate(final @NotNull PdfType type) {
        return "thymeleaf_template";
    }

    @Override protected @NotNull <T extends PdfContextData> Context getContext(@NotNull final T contextData) {
        final var casted = (PdfAContextData) contextData;
        Context context = new Context();
        context.setVariable("title", casted.getHiddenData());
        return context;
    }
}
