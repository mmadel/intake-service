package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.medical.PatientMedicalHistory;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PatientMedicalPDFCreator {

    public static void create(Document document, Patient model) throws DocumentException {
        PDFPageCreator.createHeader(document, "Medical Information");
        createMedicalHistoryInfo(document, model.getPatientMedical().getPatientMedicalHistory());
        createMedicalInfo(document, model.getPatientMedical());
        createPatientSource(document, model.getPatientSource());
    }

    private static void createMedicalInfo(Document document, PatientMedical dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Physical Therapy Receiving");
        if ((dto.getPatientPhysicalTherapy() != null) &&
                (dto.getPatientPhysicalTherapy().getLocation() != null || dto.getPatientPhysicalTherapy().getNumberOfVisit() != null)
        ) {
            String[] primaryDoctorRow = new String[]{"Location", "Number Of Visit"};
            PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{
                    dto.getPatientPhysicalTherapy().getLocation(),
                    dto.getPatientPhysicalTherapy().getNumberOfVisit().toString(),
            }, 2);
        }
        PDFPageCreator.createHeader(document, "");
        String[] primaryDoctorRow = new String[]
                {"primaryDoctor"};
        PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{
                dto.getPrimaryDoctor()
        }, 1);
    }

    private static void createPatientSource(Document document, PatientSource dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Patient Source");
        if (dto.getDoctorSource() != null) {
            String[] recommendationDoctorRow = new String[]{"Doctor Name", "Doctor NPI"};
            PDFPageCreator.createTable(document, recommendationDoctorRow, new String[]{
                    dto.getDoctorSource().getDoctorName(),
                    dto.getDoctorSource().getDoctorNPI(),
            }, 2);
        }
        PDFPageCreator.createHeader(document, "");
        if (dto.getEntitySource() != null) {
            String[] recommendationEntityRow = new String[]{"Entity Name"};
            PDFPageCreator.createTable(document, recommendationEntityRow, new String[]{
                    dto.getEntitySource().getOrganizationName()
            }, 1);
        }
    }

    private static void createMedicalHistoryInfo(Document document, PatientMedicalHistory dto) throws DocumentException {
        String[] firstHistoryRow = new String[]{"Height", "Weight", "Scanning test", "Metal Implantation", "Pacemaker"};
        PDFPageCreator.createTable(document, firstHistoryRow, new String[]{
                dto.getHeight() != null ? dto.getHeight().toString() : "",
                dto.getWeight() != null ? dto.getWeight().toString() : "",
                ((dto.getScanningTestValue() == null)
                        || dto.getScanningTestValue().isEmpty()) ? "No" : dto.getScanningTestValue(),
                dto.getMetalImplantation() ? "Yes" : "No",
                dto.getPacemaker() ? "Yes" : "No",
        }, 5);
    }
}
