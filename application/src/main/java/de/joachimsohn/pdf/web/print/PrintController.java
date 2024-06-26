package de.joachimsohn.pdf.web.print;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/print/")
public final class PrintController {

    private final PdfWebAdapter adapter;

    @PostMapping
    public @NotNull ResponseEntity<PdfDto> print(@RequestBody final @NotNull PdfDataWrapper wrapper) {
        return ResponseEntity.ok(adapter.create(wrapper));
    }

    @GetMapping("{id}")
    public @NotNull ResponseEntity<InputStreamResource> get(@PathVariable("id") final @NotNull UUID id) {
        return createResponse(adapter.get(id));
    }

    private ResponseEntity<InputStreamResource> createResponse(final @NotNull PdfDto pdf) {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaTypeFactory
                .getMediaType(pdf.getName())
                .orElse(MediaType.APPLICATION_OCTET_STREAM));
        headers.setContentDisposition(ContentDisposition
                .inline()
                .filename(pdf.getName())
                .build());
        return new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(pdf.getContent())), headers, HttpStatus.OK);
    }
}
