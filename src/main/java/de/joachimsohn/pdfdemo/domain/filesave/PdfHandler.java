package de.joachimsohn.pdfdemo.domain.filesave;

import org.jetbrains.annotations.NotNull;

public interface PdfHandler {

    String save(final byte[] content, final @NotNull String name);

    byte[] load(final @NotNull String path);
}
