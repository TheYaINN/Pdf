package de.joachimsohn.pdfdemo.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public final class PdfServiceImpl implements PdfService {

    private PdfRepository repository;

    @Override public @NotNull Pdf save(final @NotNull Pdf pdf) {
        return repository.save(pdf);
    }

    @Override public @NotNull List<Pdf> fetch() {
        return (List<Pdf>) repository.findAll();
    }

    @Override public @NotNull Pdf update(final @NotNull Pdf pdf, final @NotNull UUID id) {
        final var dbpdf = repository.findById(id);
        //TODO: update values of PDF here
        return dbpdf.map(this::save).orElseThrow();
    }

    @Override public void deleteById(final @NotNull UUID id) {
        repository.deleteById(id);
    }
}
