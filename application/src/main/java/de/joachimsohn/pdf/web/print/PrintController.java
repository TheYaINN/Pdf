package de.joachimsohn.pdf.web.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.joachimsohn.pdf.web.print.model.PdfDto;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfADto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/print/")
public final class PrintController {

    private final PdfWebAdapter adapter;
    private final ObjectMapper mapper;

    /**
     * Extra info: <a href="https://stackoverflow.com/questions/5673260/downloading-a-file-from-spring-controllers">...</a>
     */
    @PostMapping
    public @NotNull ResponseEntity<PdfDto> print() {
        return ResponseEntity.ok(adapter.create(new PdfADto()));
    }

    @GetMapping("{id}")
    public @NotNull ResponseEntity<InputStreamResource> get(@PathVariable final @NotNull UUID id) {
        return createResponse(adapter.get(id));
    }

    private ResponseEntity<InputStreamResource> createResponse(final @NotNull PdfDto pdf) {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaTypeFactory
                .getMediaType(pdf.name())
                .orElse(MediaType.APPLICATION_OCTET_STREAM));
        headers.setContentDisposition(ContentDisposition
                .inline()
                .filename(pdf.name())
                .build());
        return new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(pdf.content())), headers, HttpStatus.OK);
    }
}
