package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.models.PatientSignatureDTO;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.services.PatientSignatureService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientPDFGenerator {
    Patient source;

    public void setData(Patient source) {
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
        PatientPersonalInfoPDFCreator.create(document, source);
        PatientMedicalPDFCreator.create(document, source);
        PatientInsurancePDFCreator.create(document, source);
        PatientAgreementPDFGenerator.create(document, source.getPatientAgreements(),source.getCreatedAt(),getPatientSignature(source.getId()));
        document.close();
    }

    private void createRightCornerParagraph(Document document) throws DocumentException {
        Date dobDate=new Date(source.getPatientEssentialInformation().getDateOfBirth());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        String dobDateStr = formatter.format(dobDate);
        String value = "";
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
        value += source.getPatientEssentialInformation().getPatientName().getFirstName() + "," + source.getPatientEssentialInformation().getPatientName().getLastName() + "\n";
        value += "DOB: " + dobDateStr + "\n";
        Paragraph paragraph = new Paragraph(value, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph);
    }

    private void createLeftCornerParagraph() {

    }

    private PatientSignatureDTO getPatientSignature(Long patientId){
        PatientSignatureService patientSignatureService = BeanFactory.getBean(PatientSignatureService.class);
        return patientSignatureService.get(patientId);
    }
}

