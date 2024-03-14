package de.joachimsohn.api;

import de.joachimsohn.pdf.web.print.model.PdfDto;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfDemoRestClient {


    @NotNull PdfDto print(final @NotNull Object object);

    byte[] get(final @NotNull UUID id);

}
