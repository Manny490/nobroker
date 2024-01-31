package com.nobroker.service;

import com.nobroker.entity.User;
import jakarta.servlet.ServletOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfGeneratorService {

    public void generatePdf(List<User> userList, ServletOutputStream outputPath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                float marginX = 50;
                float yStart = page.getMediaBox().getHeight() - 50;

                // Headers
                float tableWidth = page.getMediaBox().getWidth() - 2 * marginX;
                float cellWidth = tableWidth / 3f;
                float yPosition = yStart;
                contentStream.beginText();
                contentStream.newLineAtOffset(marginX, yPosition);
                contentStream.showText("ID");
                contentStream.newLineAtOffset(cellWidth, 0);
                contentStream.showText("Name");
                contentStream.newLineAtOffset(cellWidth, 0);
                contentStream.showText("Email");
                contentStream.endText();

                // Data
                for (User user : userList) {
                    yPosition -= 20; // Move to the next row
                    contentStream.beginText();
                    contentStream.newLineAtOffset(marginX, yPosition);
                    contentStream.showText(String.valueOf(user.getId()));
                    contentStream.newLineAtOffset(cellWidth, 0);
                    contentStream.showText(user.getName());
                    contentStream.newLineAtOffset(cellWidth, 0);
                    contentStream.showText(user.getEmail());
                    contentStream.endText();
                }
            }

            document.save(outputPath);
        }
    }
}
