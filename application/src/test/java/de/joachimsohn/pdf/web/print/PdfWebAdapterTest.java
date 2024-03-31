package de.joachimsohn.pdf.web.print;

import de.joachimsohn.pdf.domain.PdfService;
import de.joachimsohn.pdf.web.print.mapper.PdfMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

class PdfWebAdapterTest {

    @Mock
    private PdfService serviceMock;
    @Mock
    private PdfMapper mapperMock;

    @InjectMocks
    private PdfWebAdapter classUnderTest;

    @Test
    void create() {
        // given

        // when

        // then

    }

    @Test
    void get() {
        // given
        final var givenId = UUID.randomUUID();

        // when
        final var result = classUnderTest.get(givenId);

        // then
        
    }

}