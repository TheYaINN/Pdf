package de.joachimsohn.api;

import de.joachimsohn.pdf.web.print.model.PdfDto;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfDemoRestClient {


    @NotNull PdfDto print(final @NotNull PdfDataWrapper wrapper);

    byte[] get(final @NotNull UUID id);

}
