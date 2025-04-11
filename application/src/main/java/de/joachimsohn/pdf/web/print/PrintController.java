package de.joachimsohn.pdf.web.print;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create and save a new PDF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successfully created and saved PDF",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PdfDto.class))}
            )})
    @PostMapping
    public @NotNull ResponseEntity<PdfDto> print(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The Data to be used for Printing the PDF", required = true) @RequestBody final @NotNull PdfDataWrapper wrapper) {
        return ResponseEntity.ok(adapter.create(wrapper));
    }

    @Operation(summary = "Get a PDF as InputStreamResource by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successfully retrieved PDF",
                    content = {@Content(mediaType = "application/octet-stream",
                            schema = @Schema(implementation = InputStreamResource.class))})
    })
    @GetMapping("{id}")
    public @NotNull ResponseEntity<InputStreamResource> get(@Parameter(description = "The ID of the PDF to be retrieved") @PathVariable("id") final @NotNull UUID id) {
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
