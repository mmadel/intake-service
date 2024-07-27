package com.cob.salesforce.services.export.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFPageCreator {
    public static void createTitle(Document document) throws DocumentException {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 21, Font.BOLD);

        Paragraph paragraph = new Paragraph("Patient Information Form", fontTitle);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
    }
    public static void createHeader(Document document, String title) throws DocumentException {
        //https://memorynotfound.com/adding-header-footer-pdf-using-itext-java/
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(527);
        header.setLockedWidth(true);
        header.getDefaultCell().setFixedHeight(40);
        header.getDefaultCell().setBorder(Rectangle.BOTTOM);
        header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        PdfPCell text = new PdfPCell();
        text.setPaddingBottom(15);
        text.setPaddingLeft(10);
        text.setBorder(Rectangle.BOTTOM);
        text.setBorderColor(BaseColor.LIGHT_GRAY);
        text.addElement(new Phrase(title, new Font(Font.FontFamily.HELVETICA, 16)));
        header.addCell(text);
        document.add(header);
    }
    public static void createHeaderWithColor(Document document, String title , BaseColor color) throws DocumentException {
        //https://memorynotfound.com/adding-header-footer-pdf-using-itext-java/
        PdfPTable header = new PdfPTable(1);
        header.setTotalWidth(527);
        header.setLockedWidth(true);
        header.getDefaultCell().setFixedHeight(40);
        header.getDefaultCell().setBorder(Rectangle.BOTTOM);
        header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        PdfPCell text = new PdfPCell();
        text.setPaddingBottom(15);
        text.setPaddingLeft(10);
        text.setBorder(Rectangle.BOTTOM);
        text.setBorderColor(BaseColor.LIGHT_GRAY);
        text.addElement(new Phrase(title, new Font(Font.FontFamily.HELVETICA, 16,1,color)));
        header.addCell(text);
        document.add(header);
    }

    public static PdfPTable createTable(Document document, String[] columns, String[] data ,int numberOfColumns) throws DocumentException {
        PdfPTable table = new PdfPTable(numberOfColumns);
        table.setTotalWidth(527);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(40);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
        for (String column : columns) {
            PdfPCell tableHeader = new PdfPCell();
            tableHeader.setPaddingBottom(15);
            tableHeader.setPaddingLeft(10);
            tableHeader.setBorder(Rectangle.NO_BORDER);
            tableHeader.addElement(new Phrase(column, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLDITALIC)));
            table.addCell(tableHeader);
        }
        if (data != null)
            for (String value : data) {
                PdfPCell tableData = new PdfPCell();
                tableData.setPaddingBottom(15);
                tableData.setPaddingLeft(10);
                tableData.setBorder(Rectangle.NO_BORDER);
                tableData.addElement(new Phrase(value, new Font(Font.FontFamily.HELVETICA, 10)));
                table.addCell(tableData);
            }
        document.add(table);
        return table;
    }
}
