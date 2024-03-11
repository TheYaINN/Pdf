package de.joachimsohn.pdfdemo.domain.filesave;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PdfHandler {

    String save(final byte[] content, final @NotNull UUID id);

    byte[] load(final @NotNull String path);
}
