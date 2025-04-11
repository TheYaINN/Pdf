package de.joachimsohn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PdfDto {

    @Default
    private final @NotNull UUID id = UUID.randomUUID();

    private @NotNull String name;

    @Default
    private byte[] content = new byte[]{};
}
