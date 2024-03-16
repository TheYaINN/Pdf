package de.joachimsohn.pdf.web.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.joachimsohn.pdf.web.print.model.PdfDto;
import de.joachimsohn.pdf.web.print.model.data.PdfDataWrapper;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfADto;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfBDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PrintController.class)
@AutoConfigureMockMvc(addFilters = false)
class PrintControllerTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PdfWebAdapter adapter;

    private static Stream<Arguments> printTestData() {
        return Stream.of(
                Arguments.of(PdfADto.builder().build()),
                Arguments.of(PdfBDto.builder().build())
        );
    }


    @ParameterizedTest
    @MethodSource("printTestData")
    void printTest(final PdfDataWrapper givenContent) throws Exception {
        mockMvc.perform(post("/print/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(givenContent))
                ).andDo(print())
                .andExpect(status().isOk());
        verify(adapter).create(givenContent);
    }

    @Test
    void get() throws Exception {
        final var givenId = UUID.randomUUID();
        final var givenPdf = PdfDto.builder().name("Test").build();
        when(adapter.get(givenId))
                .thenReturn(givenPdf);
        mockMvc.perform(MockMvcRequestBuilders.get("/print/{id}", givenId.toString()))
                .andDo(print())
                .andExpect(content().bytes(givenPdf.getContent()))
                .andExpect(status().isOk());
        verify(adapter).get(givenId);
    }

}