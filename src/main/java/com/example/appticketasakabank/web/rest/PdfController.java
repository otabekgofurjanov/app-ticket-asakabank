package com.example.appticketasakabank.web.rest;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appticketasakabank.domain.Shopping;
import com.example.appticketasakabank.service.PDFGenerateService;
import com.example.appticketasakabank.service.ShoppingService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PdfController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/pdf/ticket")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<Shopping> shoppingList = shoppingService.shoppingList();
        PDFGenerateService pdfGenerateService = new PDFGenerateService();
        pdfGenerateService.setShoppingList(shoppingList);
        pdfGenerateService.generate(response);
    }
}
