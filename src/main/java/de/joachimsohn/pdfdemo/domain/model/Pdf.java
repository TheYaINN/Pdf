package de.joachimsohn.pdfdemo.domain.model;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Builder
public record Pdf(@NotNull UUID id, @NotNull byte[] content) {
}
