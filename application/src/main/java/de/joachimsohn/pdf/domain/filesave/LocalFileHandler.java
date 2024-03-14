package de.joachimsohn.pdf.domain.filesave;

import de.joachimsohn.pdf.domain.model.Pdf;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Component
public final class LocalFileHandler implements PdfHandler {

    @Value("${localfilesaver.basePath}")
    private String basePath;

    @Override
    public String save(final @NotNull Pdf pdf) {
        final var name = pdf.id().toString();
        final var folderPath = "%s/%s".formatted(basePath, Arrays.stream(name.split("-")).findFirst().orElseThrow());
        final var filePath = "%s/%s.pdf".formatted(folderPath, name);
        final var folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (final var stream = new FileOutputStream(filePath)) {
            stream.write(pdf.content());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override public byte[] load(final @NotNull String path) {
        try (final var stream = new FileInputStream(path)) {
            return stream.readAllBytes();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
