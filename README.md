# A Microservice designed to handle PDFs

This is a Microservice for handling the creating and saving of PDFs in a fast and resilient way.
The main purpose is to avoid sending PDF across your (internal) network and only ever sending and storing the UUID of
the PDF in your applications.
This ensures all PDFS are in one location in your system to allow for modification, filtering etc. when necessary in
bulk.

The concept is that a Thymeleaf engine create a PDF which is then processed internally and simultaneously send as a
response to the requester.
Internally the PDF is saved as a PDF file on the drive and an entry is saved in a DB where the UUID of the PDF and its
corresponding (relative) path is stored.
This ensures fast retrieval of documents in the future when needed.

Using the API example code:

### maven

```xml

<dependencies>
    <dependency>
        <groupId>de.joachimsohn</groupId>
        <artifactId>api</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>de.joachimsohn</groupId>
        <artifactId>shared</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### Java

```java

@EnableConfigurationProperties
@SpringBootApplication
public class TestApp implements CommandLineRunner {

    @Autowired
    private PdfClientAuth auth;

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @Override
    public void run(final String... args) {
        final var test = new PdfDemoRestClientImpl(auth);
        System.out.println(test.creds());
        final var result = test.print(new PdfDataWrapper() {
            @NotNull @Override public String getType() {
                return PdfType.TYPE_A;
            }

            @NotNull @Override public PdfDataDto getData() {
                return PdfADto.PdfADataDto.builder().build();
            }
        });
        System.out.println(Arrays.toString(result.getContent()));
    }
}
```

and the implementation of the interface:

```java

@Getter
@Setter
@Configuration
@ConfigurationProperties("pdfclient.auth")
public class PdfClientAuth implements AuthConfig {

    private String username;
    private String password;
}
```

### application.yml

```yaml
 client:
   url: localhost:8080
 spring:
   application:
     name: testapp
   config.import: vault://
   cloud:
     vault:
       kv:
         enabled: true
       authentication: token
       token: dev-only-token
       uri: http://localhost:8200
       connection-timeout: 5000
       read-timeout: 15000
 ```