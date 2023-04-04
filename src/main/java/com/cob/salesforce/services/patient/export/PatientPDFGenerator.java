package com.cob.salesforce.services.patient.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientPDFGenerator {
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        createRightCornerParagraph(document);
        createTitle(document);
        Chunk linebreak = new Chunk(new LineSeparator(10, 100, BaseColor.BLACK, 0, 0));
        document.add(linebreak);
        createPatientPersonalInfo(document);
        createPatientInsurance(document);
        createPatientMedical(document);
        document.close();
    }

    private void createTitle(Document document) throws DocumentException {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 21, Font.BOLD);

        Paragraph paragraph = new Paragraph("Patient Information Form", fontTitle);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

    }

    private void createRightCornerParagraph(Document document) throws DocumentException {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        String value = "Ahmed Farghal\n" +
                "ID: 3939393 \n" +
                "DOB: 05/08/1981";
        Paragraph paragraph = new Paragraph(value, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph);
    }

    private void createLeftCornerParagraph() {

    }

    private void createPatientPersonalInfo(Document document) throws DocumentException {
        createHeader(document, "Personal Info");

        createTable(document, new String[]{"User Type", "First Name", "Last Name"});
        createTable(document, new String[]{"Social Security ID", "Gender", "Home Phone Number"});
        createTable(document, new String[]{"Mobile Phone Number", "Email", "Address"});
        createTable(document, new String[]{"Marital Status", "Injury Cause", "Emergency Contact First Name"});
        createTable(document, new String[]{"Emergency Contact Last Name", "Emergency Contact Phone Number", "Relationship"});

    }

    private void createPatientInsurance(Document document) throws DocumentException {
        createHeader(document, "Insurance ");
        createTable(document, new String[]{"Medicare Patient", "Secondary Insurance Policy", "Insurance Plan Name"});
        createTable(document, new String[]{"Policy ID", "Phone Number", "Secondary Policy Holder"});
    }

    private void createPatientMedical(Document document) throws DocumentException {
        createHeader(document, "Medical");
        createTable(document, new String[]{"Height", "Recent Falls", "Feel unsteady"});
        createTable(document, new String[]{"Worry about falling", "No Referring Doctor", "Medical Conditions"});
        createTable(document, new String[]{"Current Medications", "Therapy Goal", "Medical History"});
    }

    private void createHeader(Document document, String title) throws DocumentException {
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

    private void createTable(Document document, String[] columns) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(527);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(40);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
        for (String column : columns) {
            PdfPCell userType = new PdfPCell();
            userType.setPaddingBottom(15);
            userType.setPaddingLeft(10);
            userType.setBorder(Rectangle.NO_BORDER);
            userType.addElement(new Phrase(column, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
            table.addCell(userType);
        }
        document.add(table);
    }
}

