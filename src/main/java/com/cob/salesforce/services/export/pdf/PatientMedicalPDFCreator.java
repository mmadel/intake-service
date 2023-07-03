package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.MedicalHistoryInformationDTO;
import com.cob.salesforce.models.MedicalQuestionnaireInfoDTO;
import com.cob.salesforce.models.PatientDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PatientMedicalPDFCreator {

    public static void create(Document document, PatientDTO dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Medical Information");
        createMedicalHistoryInfo(document, dto.getMedicalHistoryInformation());
        createMedicalInfo(document, dto.getMedicalQuestionnaireInfo());
    }

    private static void createMedicalInfo(Document document, MedicalQuestionnaireInfoDTO dto) throws DocumentException {
        PDFPageCreator.createSubHeader(document, "Physical Therapy Receiving");
        if (dto.getPhysicalTherapyReceiving()) {
            String[] primaryDoctorRow = new String[]{"Location", "Number Of Visit"};
            PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{
                    dto.getPhysicalTherapy().getLocation(),
                    dto.getPhysicalTherapy().getNumberOfVisit().toString(),
            }, 2);
        }
        PDFPageCreator.createSubHeader(document, "Recommendation");
        if (dto.getRecommendationDoctor() != null) {
            String[] recommendationDoctorRow = new String[]{"Doctor Name", "Doctor Address", "Doctor NPI", "Doctor Fax"};
            PDFPageCreator.createTable(document, recommendationDoctorRow, new String[]{
                    dto.getRecommendationDoctor().getName(),
                    dto.getRecommendationDoctor().getDoctorAddress().getFullAddress(),
                    dto.getRecommendationDoctor().getNpi(),
                    dto.getRecommendationDoctor().getFax(),
            }, 4);
        }
        PDFPageCreator.createHeader(document, "");
        if (dto.getRecommendationEntity() != null) {
            String[] recommendationEntityRow = new String[]{"Entity Name"};
            PDFPageCreator.createTable(document, recommendationEntityRow, new String[]{
                    dto.getRecommendationEntity().getName()
            }, 1);
        }
        PDFPageCreator.createHeader(document, "");
        String[] primaryDoctorRow = new String[]
                {"primaryDoctor"};
        PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{
                dto.getPrimaryDoctor()
        }, 1);
    }

    private static void createMedicalHistoryInfo(Document document, MedicalHistoryInformationDTO dto) throws DocumentException {
        String[] firstHistoryRow = new String[]{"Height", "Weight", "Scanning test", "Metal Implantation", "Pacemaker"};
        PDFPageCreator.createTable(document, firstHistoryRow, new String[]{
                dto.getHeight() != null ? dto.getHeight().toString() : "",
                dto.getWeight() != null ? dto.getWeight().toString() : "",
                dto.getScanningTestValue() == null ? "NO" : dto.getScanningTestValue(),
                dto.getMetalImplantation() ? "Yes" : "No",
                dto.getPacemaker() ? "Yes" : "No",
        }, 5);
    }
}
