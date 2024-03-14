package de.joachimsohn.pdf.web.print.model.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfADto;
import de.joachimsohn.pdf.web.print.model.data.impl.PdfBDto;
import org.jetbrains.annotations.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CUSTOM;

@JsonTypeInfo(use = CUSTOM, include = EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes(value = {
        @Type(value = PdfADto.class, name = "TYPE_A"),
        @Type(value = PdfBDto.class, name = "TYPE_B")
})
public interface PdfDataWrapper {

    @NotNull PdfType getType();

    @NotNull PdfDataDto getData();

}
