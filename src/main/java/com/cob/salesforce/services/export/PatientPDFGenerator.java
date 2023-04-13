package com.cob.salesforce.services.export;

import com.cob.salesforce.models.pdf.PatientData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientPDFGenerator {
    PatientData source;

    public void setData(PatientData source) {
        this.source = source;
    }

    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
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
        String value = "";
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        value += source.getFirstName() + "," + source.getLastName() + "\n";
        value += "ID: " + source.getPatientId() + "\n";
        value += "DOB: " + source.getDateOfBirth() + "\n";
        Paragraph paragraph = new Paragraph(value, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph);
    }

    private void createLeftCornerParagraph() {

    }

    private void createPatientPersonalInfo(Document document) throws DocumentException {
        createHeader(document, "Personal Info");

        createTable(document, new String[]{"User Type", "First Name", "Last Name"},
                new String[]{"userType", source.getFirstName(), source.getLastName()});
        createTable(document, new String[]{"Social Security ID", "Gender", "Home Phone Number"},
                new String[]{source.getPatientId(), source.getGender().label, source.getPhone()});
        createTable(document, new String[]{"Mobile Phone Number", "Email", "Address"},
                new String[]{source.getPhone(), source.getEmail(), source.getAddress()});
        createTable(document, new String[]{"Marital Status", "Injury Cause", "Emergency Contact First Name"},
                new String[]{source.getMaritalStatus().label, "Injury Cause", source.getEmergencyFirstName()});
        createTable(document, new String[]{"Emergency Contact Last Name", "Emergency Contact Phone Number", "Relationship"},
                new String[]{source.getEmergencyLastName(), source.getEmergencyPhone(), "Relationship"});
        createTable(document, new String[]{"Referral Source"},
                new String[]{source.getPatientSourceData().getEntityName()});

    }

    private void createPatientInsurance(Document document) throws DocumentException {
        createHeader(document, "Insurance ");
        createTable(document, new String[]{"Medicare Patient", "Secondary Insurance Policy", "Insurance Plan Name"},
                new String[]{source.getInsuranceData().getMedicare() ? "yes" : "No", source.getInsuranceData().getSecondaryInsurancePolicy() ? "Yes" : "No", source.getInsuranceData().getInsurancePlanName()});
        createTable(document, new String[]{"Policy ID", "Phone Number", "Secondary Policy Holder"},
                new String[]{source.getInsuranceData().getPolicyId(), "Phone Number", source.getInsuranceData().getSecondaryPolicyHolder() ? "Yes" : "No"});
    }

    private void createPatientMedical(Document document) throws DocumentException {
        createHeader(document, "Medical");
        createTable(document, new String[]{"Height", "Recent Falls", "Feel unsteady"},
                new String[]{source.getMedicalData().getHeight().toString(), "Recent Falls", "Feel unsteady"});
        createTable(document, new String[]{"Worry about falling", "No Referring Doctor", "Medical Conditions"},
                new String[]{"Worry about falling", source.getPatientSourceData().getDoctorName() == null ? "Yes" : "No", source.getMedicalData().getPatientCondition().toString()});
        createTable(document, new String[]{"Current Medications", "Therapy Goal", "Medical History"},
                new String[]{"Current Medications", "Therapy Goal", "Medical History"});
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

    private PdfPTable createTable(Document document, String[] columns, String[] data) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
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
            tableHeader.addElement(new Phrase(column, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
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

