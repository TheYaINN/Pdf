package de.joachimsohn.pdfdemo.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("pdfclient")
public class PdfDemoRestClientAuthConfiguration {
    private String username;
    private String password;
}
