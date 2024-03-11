package de.joachimsohn.pdfdemo.domain;

import de.joachimsohn.pdfdemo.domain.filesave.PdfHandler;
import de.joachimsohn.pdfdemo.domain.repository.PdfRepository;
import de.joachimsohn.pdfdemo.domain.repository.model.Pdf;
import de.joachimsohn.pdfdemo.web.model.PdfDto;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
final class PdfServiceImpl implements PdfService {

    private PdfCreationService pdfCreationService;
    private PdfRepository repository;
    private PdfHandler saver;

    @Override
    public @NotNull PdfDto fetchById(final @NotNull UUID id) {
        return repository.findById(id).map(db -> PdfDto.builder()
                .id(db.getId())
                .name("%s.pdf".formatted(id))
                .content(saver.load(db.getPath()))
                .build()).orElseThrow();
    }

    @Override public @NotNull PdfDto create(final @NotNull Object body) {
        final var pdf = pdfCreationService.generatePdfFromHtml();
        final var id = UUID.randomUUID();
        CompletableFuture.runAsync(() -> {
            final var path = saver.save(pdf, id.toString());
            repository.save(Pdf.builder().path(path).id(id).build());
        });
        return PdfDto.builder()
                .content(pdf)
                .id(id)
                .build();
    }
}
