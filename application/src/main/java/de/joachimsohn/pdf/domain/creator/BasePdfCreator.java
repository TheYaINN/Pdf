package de.joachimsohn.pdf.domain.creator;

import de.joachimsohn.pdf.domain.model.PdfContextData;
import de.joachimsohn.pdf.domain.model.PdfData;
import de.joachimsohn.pdf.domain.model.PdfData.PdfType;
import de.joachimsohn.pdf.domain.model.impl.PdfA;
import de.joachimsohn.pdf.domain.model.impl.PdfA.PdfAContextData;
import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public abstract class BasePdfCreator implements PdfCreator {

    @Override
    public @NotNull String create(final @NotNull PdfData input) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(getTemplate(input.getType()), getContext(input.getContextData()));
    }

    protected abstract @NotNull String getTemplate(final @NotNull PdfType type);
    protected abstract <T extends PdfContextData>@NotNull Context getContext(final @NotNull T contextData);
}
