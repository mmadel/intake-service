package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.BasicInfoDTO;
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

    public static void create(Document document, BasicInfoDTO patientPersonalInfo) throws DocumentException {
        PDFPageCreator.createHeader(document, "Personal Information");
        PDFPageCreator.createTable(document, firstRow, new String[]{
                patientPersonalInfo.getEmploymentStatus(),
                patientPersonalInfo.getMiddleName(),
                patientPersonalInfo.getLastName(),
                patientPersonalInfo.getGender(),
                patientPersonalInfo.getMaritalStatus(),
        }, 5);
        PDFPageCreator.createHeader(document, "");
        PDFPageCreator.createTable(document, secondRow, new String[]{
                patientPersonalInfo.getEmploymentStatus(),
                patientPersonalInfo.getEmail(),
                patientPersonalInfo.getPhoneType(),
                patientPersonalInfo.getPhoneNumber(),
        }, 4);
//        PDFPageCreator.createHeader(document, "");
//        PDFPageCreator.createTable(document, idRow, new String[]{
//                patientPersonalInfo.getIdType(),
//                patientPersonalInfo.getPatientId(),
//                patientPersonalInfo.getIdEffectiveFrom().toString(),
//                patientPersonalInfo.getIdEffectiveTo().toString(),
//        }, 4);
        PDFPageCreator.createHeader(document, "");
        PDFPageCreator.createTable(document, emergencyRow, new String[]{
                patientPersonalInfo.getEmergencyName(),
                patientPersonalInfo.getEmergencyName(),
        }, 2);
    }
}
