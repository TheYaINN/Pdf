package de.joachimsohn.pdfdemo.api;

import de.joachimsohn.pdfdemo.web.print.model.PdfDto;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfDemoRestClient {


    @NotNull PdfDto print(final @NotNull Object object);

    byte[] get(final @NotNull UUID id);

}
