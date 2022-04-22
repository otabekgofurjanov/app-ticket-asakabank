package com.example.appticketasakabank.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.example.appticketasakabank.domain.Shopping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PDFGenerateService {

    private List<Shopping> shoppingList;

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Shopping ticket List", fontTitle);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(6);

        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.YELLOW);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(CMYKColor.BLACK);
        fontTitle.setSize(14);

        cell.setPhrase(new Phrase("Seat", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Movie Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Seance", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cinema name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Hall name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);


        for (Shopping shopping : shoppingList) {
            table.addCell(String.valueOf(shopping.getSeats().getColumnNumber()));
            table.addCell(String.valueOf(shopping.getShowSeances().getMovieName()));
            table.addCell(String.valueOf(shopping.getShowSeances().getSeanceType()));
            table.addCell(String.valueOf(shopping.getHall().getCinema().getName()));
            table.addCell(String.valueOf(shopping.getHall().getName()));
            table.addCell(String.valueOf(shopping.getPaySum()));

        }
        document.add(table);
        document.close();
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }
}

