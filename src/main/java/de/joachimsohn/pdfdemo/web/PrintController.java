package de.joachimsohn.pdfdemo.web;

import de.joachimsohn.pdfdemo.domain.PdfService;
import de.joachimsohn.pdfdemo.web.model.PdfDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/print/")
public final class PrintController {

    private final PdfService pdfService;

    /**
     * Extra info: <a href="https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers">...</a>
     */
    @GetMapping
    public @NotNull ResponseEntity<InputStreamResource> print() {
        return createResponse(pdfService.create(new Object()));
    }

    @GetMapping("{id}")
    public @NotNull ResponseEntity<InputStreamResource> get(@PathVariable final @NotNull UUID id) {
        return createResponse(pdfService.fetchById(id));
    }

    private ResponseEntity<InputStreamResource> createResponse(final @NotNull PdfDto pdf) {
        final var mediaType = MediaTypeFactory
                .getMediaType("test.pdf")
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        final var headers = new HttpHeaders();
        headers.setContentType(mediaType);
        final var disposition = ContentDisposition
                .inline()
                .filename(pdf.getName())
                .build();
        headers.setContentDisposition(disposition);
        return new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(pdf.getContent())), headers, HttpStatus.OK);
    }
}
