# A Microservice designed to handle PDFs

This is a Microservice to handle PDF in a fast and resilient way.
The main purpose is to avoid sending PDF across your internal network and only ever sending and storing the UUID of the PDF in your applications.
This ensures all PDFS are in one location in your system to allow for modification, filtering etc. when necessary in bulk.

The concept is that a Thymeleaf engine create a PDF which is then processed internally and simultaneously send as a response to the requester.
Internally the PDF is saved as a PDF file on the drive and an entry is saved in a DB where the UUID of the PDF and its corresponding (relative) path is stored.
This ensures fast retrieval of documents in the future when needed.