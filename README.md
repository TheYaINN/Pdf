# A Microservice designed to handle PDFs

To start just start as a Service and open <code>localhost:8080/print/</code> in your browser. You should immediately see
a PDF and in your [specified folder](src/main/resources/application.properties) in the corresponding PDF locally.
Additionally you can open the [h2 console ](http://localhost:8080/h2-console) and check for the file location there and
its corresponding ID

```plantuml
start
:Import Client into Project;
->Configure application.properties;
:Read values from Vault; 
:Send Request to PdfService;
->Read Headers from Request;
:Authenticate access via OAuth;
:process Request;
fork
    :save metadata in DB;
    :Save file locally;
fork again
    :Send Response;
end fork
end
```