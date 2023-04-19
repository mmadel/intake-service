package com.cob.salesforce.services.export;

import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.models.pdf.PatientData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientPDFGenerator {
    PatientDTO source;

    public void setData(PatientDTO source) {
        this.source = source;
    }

    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        createRightCornerParagraph(document);
        PDFPageCreator.createTitle(document);
        Chunk linebreak = new Chunk(new LineSeparator(10, 100, BaseColor.BLACK, 0, 0));
        document.add(linebreak);
        document.close();
    }

    private void createRightCornerParagraph(Document document) throws DocumentException {
        String value = "";
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        value += source.getBasicInfo().getFirstName() + "," + source.getBasicInfo().getLastName() + "\n";
        value += "ID: " + source.getBasicInfo().getPatientId() + "\n";
        value += "DOB: " + source.getBasicInfo().getBirthDate() + "\n";
        Paragraph paragraph = new Paragraph(value, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph);
    }

    private void createLeftCornerParagraph() {

    }
}

