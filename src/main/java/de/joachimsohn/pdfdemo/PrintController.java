package de.joachimsohn.pdfdemo;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/print/")
public final class PrintController {

    private final PdfGenerator generator;

    @GetMapping
    public @NotNull ResponseEntity<InputStreamResource> print() {
        // Extra info: https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers
        final var resource = generator.generatePdfFromHtml();
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
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
