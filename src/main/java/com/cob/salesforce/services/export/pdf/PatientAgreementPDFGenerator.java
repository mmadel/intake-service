package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.PatientDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PatientAgreementPDFGenerator {
    public static void create(Document document, PatientDTO dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Agreement Information");
        dto.getPatientAgreement().forEach((agreementName, agreementValue) -> {
            try {
                createAgreement(document, agreementName, agreementValue, dto);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static PdfPTable createAgreement(Document document, String column, String data, PatientDTO dto) throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(527);
        table.setLockedWidth(true);
        table.getDefaultCell().setFixedHeight(40);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        PdfPCell tableHeader = new PdfPCell();
        tableHeader.setPaddingBottom(15);
        tableHeader.setPaddingLeft(10);
        tableHeader.setBorder(Rectangle.NO_BORDER);
        tableHeader.addElement(new Phrase(column, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
        table.addCell(tableHeader);

        PdfPCell tableData = new PdfPCell();
        tableData.setPaddingBottom(15);
        tableData.setPaddingLeft(10);
        tableData.setBorder(Rectangle.NO_BORDER);

        tableData.addElement(new Phrase(data, new Font(Font.FontFamily.HELVETICA, 12)));
        table.addCell(tableData);

        PdfPCell patientNameCell = new PdfPCell();
        patientNameCell.setPaddingBottom(15);

        patientNameCell.setBorder(Rectangle.NO_BORDER);
        String patientName = dto.getBasicInfo().getFirstName().substring(0, 1).toUpperCase() + dto.getBasicInfo().getFirstName().substring(1) + ","
                + dto.getBasicInfo().getMiddleName().substring(0, 1).toUpperCase() + ".,"
                + dto.getBasicInfo().getLastName().substring(0, 1).toUpperCase() + dto.getBasicInfo().getLastName().substring(1);


        patientNameCell.addElement(new Phrase("Patient Signature:"));
        PdfPCell patientSignatureCell = new PdfPCell();
        patientSignatureCell.setBorder(Rectangle.NO_BORDER);
        Image img = Image.getInstance((dto.getPatientSignature().getSignatureAsBytes()));
        img.scaleToFit(100f, 100f);
        patientSignatureCell.addElement(img);
        table.addCell(patientNameCell);
        table.addCell(patientSignatureCell);

        PdfPCell patientDateOfSign = new PdfPCell();
        patientDateOfSign.setPaddingBottom(15);
        patientDateOfSign.setPaddingLeft(10);
        patientDateOfSign.setBorder(Rectangle.NO_BORDER);
        Date dateOfSign = new Date(dto.getCreatedAt() == null ? new Date().getTime() : dto.getCreatedAt());
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        String dateOfSignStr = dateFormatter.format(dateOfSign);
        patientDateOfSign.addElement(new Phrase("Date: " + dateOfSignStr, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC)));
        table.addCell(patientDateOfSign);
        document.add(table);
        return table;
    }
}
