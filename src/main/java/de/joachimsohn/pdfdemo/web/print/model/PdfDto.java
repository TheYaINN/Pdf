package de.joachimsohn.pdfdemo.web.print.model;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Builder
public record PdfDto(@NotNull UUID id, @NotNull String name, byte[] content) {
}
