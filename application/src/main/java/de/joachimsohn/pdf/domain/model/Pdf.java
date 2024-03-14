package de.joachimsohn.pdf.domain.model;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Builder
public record Pdf(@NotNull UUID id, byte[] content) {


}
