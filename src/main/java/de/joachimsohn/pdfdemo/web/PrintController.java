package de.joachimsohn.pdfdemo.web;

import de.joachimsohn.pdfdemo.domain.PdfCreationService;
import de.joachimsohn.pdfdemo.domain.Pdf;
import de.joachimsohn.pdfdemo.domain.PdfService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/print/")
public final class PrintController {

    private final PdfCreationService pdfCreationService;
    private final PdfService pdfService;

    @GetMapping
    public @NotNull ResponseEntity<InputStreamResource> print() throws IOException {
        // Extra info: https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
        final var resource = pdfCreationService.generatePdfFromHtml();
        final var bytearray = resource.getContentAsByteArray();
        final var id = UUID.randomUUID();
        CompletableFuture.runAsync(() -> savePdfToDisk(bytearray, id));
        MediaType mediaType = MediaTypeFactory
                .getMediaType("test.pdf")
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        ContentDisposition disposition = ContentDisposition
                .inline()
                .filename(resource.getFilename())
                .build();
        headers.setContentDisposition(disposition);
        return new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(bytearray)), headers, HttpStatus.OK);
    }

    private void savePdfToDisk(final byte[] resource, final @NotNull UUID id) {
        final var path = "C:/Users/Bengt/.m2/repository/de/joachimsohn/contentmanager/pdfdemo/src/main/java/de/joachimsohn/pdfdemo/web/%s.pdf".formatted(Arrays.stream(id.toString().split("-")).findFirst().orElseThrow());
        pdfService.save(Pdf.builder()
                .id(id)
                .path(path)
                .build());
        try (final var stream = new FileOutputStream(path)) {
            stream.write(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
