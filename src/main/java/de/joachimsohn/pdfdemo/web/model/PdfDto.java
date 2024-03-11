package de.joachimsohn.pdfdemo.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class PdfDto {
    
    private UUID id;
    private String name;
    private byte[] content;
}
