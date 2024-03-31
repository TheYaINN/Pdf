package de.joachimsohn.api;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfDemoRestClient {


    @NotNull PdfDto print(final @NotNull PdfDataWrapper wrapper);

    byte[] get(final @NotNull UUID id);

}
