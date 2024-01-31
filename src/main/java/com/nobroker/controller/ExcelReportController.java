package com.nobroker.controller;

import com.nobroker.service.ExcelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelReportController {

    //http://localhost:8080/api/excel/generate
    @Autowired
    private ExcelReportService excelReportService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateExcelReport() {
        try {
            byte[] excelFile = excelReportService.generateExcelReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "user_report.xlsx");
            headers.setContentLength(excelFile.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelFile);
        } catch (IOException e) {
            // Handle the exception, log it, or return an error response
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error generating Excel report".getBytes());
        }
    }
}
