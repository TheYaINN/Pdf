package de.joachimsohn.pdf.web.print;

import de.joachimsohn.model.PdfDto;
import de.joachimsohn.model.data.impl.PdfADto;
import de.joachimsohn.pdf.domain.PdfService;
import de.joachimsohn.pdf.web.print.mapper.PdfMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        final var givenWrapper = PdfADto.builder().build();
        when(mapperMock.toDto(any()))
                .thenReturn(PdfDto.builder()
                        .name("Test")
                        .build());

        // when
        classUnderTest.create(givenWrapper);

        // then
        verify(mapperMock).toDomain(givenWrapper);
        verify(serviceMock).create(any());
        verify(mapperMock).toDto(any());

    }

    @Test
    void get() {
        // given
        final var givenId = UUID.randomUUID();
        final var givenPdf = PdfDto.builder().id(givenId)
                .name("Test")
                .build();
        when(mapperMock.toDto(any()))
                .thenReturn(givenPdf);

        // when
        final var result = classUnderTest.get(givenId);

        // then
        verify(serviceMock).fetchById(givenId);
        Assertions.assertEquals(result, givenPdf);
    }

}