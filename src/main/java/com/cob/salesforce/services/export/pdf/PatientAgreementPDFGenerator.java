package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.Agreement;
import com.cob.salesforce.models.PatientSignatureDTO;
import com.cob.salesforce.models.intake.agreement.PatientAgreement;
import com.cob.salesforce.repositories.AgreementRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PatientAgreementPDFGenerator {
    public static void create(Document document, PatientAgreement patientAgreements, Long createdDate, PatientSignatureDTO signature) throws DocumentException {
        PDFPageCreator.createHeader(document, "Agreement Information");
        try {
            createMandatoryAgreements(document, createdDate, signature);
            createOptionalAgreements(document, patientAgreements, createdDate, signature);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createMandatoryAgreements(Document document, Long createdDate, PatientSignatureDTO signature) throws DocumentException, IOException {

        String[] mandatoryAgreements = new String[]{"ReleaseInformation", "FinancialResponsibility", "FinancialAgreement", "Insurance"};
        AgreementRepository agreementRepository = BeanFactory.getBean(AgreementRepository.class);
        for (int i = 0; i < mandatoryAgreements.length; i++) {
            Agreement agreement = agreementRepository.findByAgreementName(mandatoryAgreements[i]).get();
            createAgreement(document, agreement.getAgreementName(), agreement.getAgreementText(), createdDate, signature);
        }
    }

    private static void createOptionalAgreements(Document document, PatientAgreement patientAgreements, Long createdDate, PatientSignatureDTO signature) {
        List<String> optionalAgreements = new ArrayList<>();
        if (patientAgreements.getAcceptHIPAAAgreements() != null && patientAgreements.getAcceptHIPAAAgreements())
            optionalAgreements.add("HIPAAAcknowledgement");
        if (patientAgreements.getCancellationPolicyAgreements() != null && patientAgreements.getCancellationPolicyAgreements())
            optionalAgreements.add("CancellationPolicy");
        if (patientAgreements.getCommunicationAttestationAgreements() != null && patientAgreements.getCommunicationAttestationAgreements())
            optionalAgreements.add("CommunicationAttestation");
        if (patientAgreements.getAuthorizationToReleaseObtainInformationAgreements() != null && patientAgreements.getAuthorizationToReleaseObtainInformationAgreements())
            optionalAgreements.add("Authorization-To-Release-Obtain-Information");
        if (patientAgreements.getConsentToTreatmentAgreements() != null && patientAgreements.getConsentToTreatmentAgreements())
            optionalAgreements.add("Consent-To-Treatment");
        if (patientAgreements.getNoticeOfPrivacyPracticesAgreements() != null && patientAgreements.getNoticeOfPrivacyPracticesAgreements())
            optionalAgreements.add("Notice-Of-Privacy-Practices");
        if (patientAgreements.getAssignmentReleaseOfBenefitsAgreements() != null && patientAgreements.getAssignmentReleaseOfBenefitsAgreements())
            optionalAgreements.add("Assignment-Release-Of-Benefits");
        if (patientAgreements.getAcceptCuppingAgreements() != null && patientAgreements.getAcceptCuppingAgreements())
            optionalAgreements.add("Cupping");
        if (patientAgreements.getAcceptPelvicAgreements() != null && patientAgreements.getAcceptPelvicAgreements())
            optionalAgreements.add("Pelvic");
        if (patientAgreements.getAcceptPhotoVideoAgreements() != null && patientAgreements.getAcceptPhotoVideoAgreements())
            optionalAgreements.add("PhotoVideo");
        AgreementRepository agreementRepository = BeanFactory.getBean(AgreementRepository.class);
        optionalAgreements.forEach(name -> {
            Agreement agreement = agreementRepository.findByAgreementName(name).get();
            try {
                createAgreement(document, agreement.getAgreementName(), agreement.getAgreementText(), createdDate, signature);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static PdfPTable createAgreement(Document document, String column, String data, Long createdDate, PatientSignatureDTO signature) throws DocumentException, IOException {
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

        tableData.addElement(new Phrase( Jsoup.parse(data).wholeText(), new Font(Font.FontFamily.HELVETICA, 12)));
        table.addCell(tableData);

        PdfPCell patientNameCell = new PdfPCell();
        patientNameCell.setPaddingBottom(15);

        patientNameCell.setBorder(Rectangle.NO_BORDER);


        patientNameCell.addElement(new Phrase("patient Signature:"));
        PdfPCell patientSignatureCell = new PdfPCell();
        patientSignatureCell.setBorder(Rectangle.NO_BORDER);
        Image img = Image.getInstance((signature.getSignatureAsBytes()));
        img.scaleToFit(100f, 100f);
        patientSignatureCell.addElement(img);
        table.addCell(patientNameCell);
        table.addCell(patientSignatureCell);

        PdfPCell patientDateOfSign = new PdfPCell();
        patientDateOfSign.setPaddingBottom(15);
        patientDateOfSign.setPaddingLeft(10);
        patientDateOfSign.setBorder(Rectangle.NO_BORDER);
        Date dateOfSign = new Date(createdDate == null ? new Date().getTime() : createdDate);
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        String dateOfSignStr = dateFormatter.format(dateOfSign);
        patientDateOfSign.addElement(new Phrase("Date: " + dateOfSignStr, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC)));
        table.addCell(patientDateOfSign);
        document.add(table);
        return table;
    }
}
