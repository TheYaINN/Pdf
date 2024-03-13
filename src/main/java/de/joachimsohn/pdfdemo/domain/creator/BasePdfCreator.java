package de.joachimsohn.pdfdemo.domain.creator;

import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public abstract class BasePdfCreator implements PdfCreator {

    @Override public @NotNull String create(final @NotNull Object input) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(getTemplate(), getContext());
    }

    abstract @NotNull Context getContext();

    abstract @NotNull String getTemplate();
}
