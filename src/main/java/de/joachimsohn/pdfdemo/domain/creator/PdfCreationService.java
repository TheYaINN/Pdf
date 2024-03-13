package de.joachimsohn.pdfdemo.domain.creator;

import com.lowagie.text.DocumentException;
import de.joachimsohn.pdfdemo.domain.model.PdfData;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class PdfCreationService {

    private final List<PdfCreator> creators;

    public byte[] generatePdfFromHtml(final @NotNull PdfData pdf) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(creators.stream()
                    .filter(c -> c.applies(pdf.getType()))
                    .findFirst()
                    .map(c -> c.create(pdf.getContextData()))
                    .orElseThrow());
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
