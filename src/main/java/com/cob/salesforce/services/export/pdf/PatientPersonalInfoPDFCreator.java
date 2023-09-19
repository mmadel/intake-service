package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.BasicInfoDTO;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.essentials.PatientEssentialInformation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PatientPersonalInfoPDFCreator {


    static final String[] firstRow = new String[]
            {"First Name", "Middle Name", "Last Name", "Gender", "Marital Status"};
    static final String[] secondRow = new String[]
            {"Employment Status", "Email", "Phone Type", "Phone Number"};

    static final String[] idRow = new String[]
            {"Id Type", "Patient Id", "Effective-From", "Effective-To"};
    static final String[] emergencyRow = new String[]
            {"Emergency Name", "Emergency Phone"};

    public static void create(Document document, Patient source) throws DocumentException {
        PDFPageCreator.createHeader(document, "Personal Information");
        PDFPageCreator.createTable(document, firstRow, new String[]{
                source.getPatientEssentialInformation().getPatientName().getFirstName(),
                source.getPatientEssentialInformation().getPatientName().getMiddleName(),
                source.getPatientEssentialInformation().getPatientName().getLastName(),
                source.getPatientEssentialInformation().getGender().label,
                source.getPatientEssentialInformation().getMaritalStatus().label,
        }, 5);
        PDFPageCreator.createHeader(document, "");
        PDFPageCreator.createTable(document, secondRow, new String[]{
                source.getPatientEssentialInformation().getPatientEmployment().getEmploymentStatus().label,
                source.getPatientEssentialInformation().getEmail(),
                source.getPatientEssentialInformation().getPatientPhone().getPhoneType().label,
                source.getPatientEssentialInformation().getPatientPhone().getPhone(),
        }, 4);
        PDFPageCreator.createHeader(document, "");
        PDFPageCreator.createTable(document, emergencyRow, new String[]{
                source.getPatientEssentialInformation().getPatientEmergencyContact().getEmergencyName(),
                source.getPatientEssentialInformation().getPatientEmergencyContact().getEmergencyName(),
        }, 2);
    }
}
