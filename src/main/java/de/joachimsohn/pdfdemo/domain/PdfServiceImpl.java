package de.joachimsohn.pdfdemo.domain;

import de.joachimsohn.pdfdemo.domain.filesave.PdfHandler;
import de.joachimsohn.pdfdemo.domain.model.Pdf;
import de.joachimsohn.pdfdemo.repository.PdfRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
final class PdfServiceImpl implements PdfService {

    private PdfCreationService pdfCreationService;
    private PdfRepository repository;
    private PdfHandler saver;

    @Override
    public @NotNull Pdf fetchById(final @NotNull UUID id) {
        return repository.findById(id).map(db -> Pdf.builder()
                .id(db.getId())
                .content(saver.load(db.getPath()))
                .build()).orElseThrow();
    }

    @Override public @NotNull Pdf create(final @NotNull Object body) {
        final var pdfContent = pdfCreationService.generatePdfFromHtml();
        final var id = UUID.randomUUID();
        final var pdf = Pdf.builder().id(id).content(pdfContent).build();
        Thread.startVirtualThread(() -> {
            //TODO: the repository should have another Abstraction layer to decouple it from the domain itself
            final var path = saver.save(pdf);
            repository.save(de.joachimsohn.pdfdemo.repository.model.Pdf.builder()
                    .id(id)
                    .path(path)
                    .build());
        });
        return pdf;
    }
}
