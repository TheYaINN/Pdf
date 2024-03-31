package de.joachimsohn.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@Builder
public final class PdfDto {
    @Default
    private final @NotNull UUID id = UUID.randomUUID();

    private @NotNull String name;

    @Default
    private byte[] content = new byte[]{};
}
