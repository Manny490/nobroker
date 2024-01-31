package com.nobroker.controller;

import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private UserRepository userRepository; // Assuming UserRepository is your Spring Data JPA repository for User entity

    //http://localhost:8080/api/pdf/generate

    @GetMapping("/generate")
    public void generatePdfReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=users_report.pdf");

        List<User> userList = userRepository.findAll(); // Fetch all users from the database

        pdfGeneratorService.generatePdf(userList, response.getOutputStream());
    }
}
