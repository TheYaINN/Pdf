package de.joachimsohn.model.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.joachimsohn.model.data.impl.PdfADto;
import de.joachimsohn.model.data.impl.PdfBDto;
import org.jetbrains.annotations.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXISTING_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(
        use = NAME,
        include = EXISTING_PROPERTY,
        property = PdfDataWrapper.DISCRIMINATOR
)
@JsonSubTypes({
        @Type(value = PdfADto.class, name = PdfType.TYPE_A),
        @Type(value = PdfBDto.class, name = PdfType.TYPE_B)
})
public interface PdfDataWrapper {

    String DISCRIMINATOR = "type";

    @NotNull String getType();

    @NotNull PdfDataDto getData();

}
