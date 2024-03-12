package de.joachimsohn.pdfdemo.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("pdfclient.auth")
public class PdfDemoRestClientAuthConfiguration {
    private String username;
    private String password;
}
