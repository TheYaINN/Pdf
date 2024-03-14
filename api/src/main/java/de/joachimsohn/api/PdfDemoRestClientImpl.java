package de.joachimsohn.api;

import de.joachimsohn.pdf.web.print.model.PdfDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PdfDemoRestClientImpl implements PdfDemoRestClient {

    private final PdfDemoRestClientAuthConfiguration config;
    @Value("{client.url}")
    private String baseUrl;
    private final RestClient client = RestClient.create(baseUrl);

    @Override
    public @NotNull PdfDto print(final @NotNull Object object) {
        return client.post().uri("/print/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(object)
                .headers(h -> h.setBasicAuth(config.getUsername(), config.getPassword()))
                .retrieve()
                .toEntity(PdfDto.class)
                .getBody();
    }

    @Override
    public byte[] get(final @NotNull UUID id) {
        try {
            return client.get().uri("/print/{id}", id)
                    .retrieve()
                    .body(InputStreamResource.class)
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


