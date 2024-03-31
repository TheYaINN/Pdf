package de.joachimsohn.api;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.PdfDataWrapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class PdfDemoRestClientImpl implements PdfDemoRestClient {

    private final PdfDemoRestClientAuthConfiguration config;
    @Value("{client.url}")
    private String baseUrl;
    private final RestClient client = RestClient.create(baseUrl);

    @Override
    public @NotNull PdfDto print(final @NotNull PdfDataWrapper wrapper) {
        return client.post().uri("%s/print/".formatted("http://localhost:8080"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(wrapper)
                .headers(h -> h.setBasicAuth(config.getUsername(), config.getPassword()))
                .retrieve()
                .toEntity(PdfDto.class)
                .getBody();
    }

    @Override
    public byte[] get(final @NotNull UUID id) {
        return client.get().uri("%s/print/{id}".formatted("http://localhost:8080"), id)
                .exchange((clientRequest, clientResponse) -> clientResponse.getBody().readAllBytes());
    }
}


