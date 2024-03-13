package de.joachimsohn.pdfdemo.domain.creator;

import de.joachimsohn.pdfdemo.domain.model.PdfData.PdfType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public final class SimplePDF extends BasePdfCreator {

    @Override
    @NotNull Context getContext() {
        Context context = new Context();
        context.setVariable("title", "My very thoughtful title");
        return context;
    }

    @Override @NotNull String getTemplate() {
        return "thymeleaf_template";
    }

    @Override public boolean applies(final @NotNull PdfType type) {
        return true;
    }
}
