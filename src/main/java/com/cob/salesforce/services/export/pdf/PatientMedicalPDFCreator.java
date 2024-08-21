package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.medical.PatientMedicalHistory;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.text.DecimalFormat;

public class PatientMedicalPDFCreator {

    public static void create(Document document, Patient model) throws DocumentException {
        PDFPageCreator.createHeader(document, "Medical Information");
        createMedicalHistoryInfo(document, model.getPatientMedical().getPatientMedicalHistory());
        createMedicalInfo(document, model.getPatientMedical());
        createPatientSource(document, model.getPatientSource());
    }

    private static void createMedicalInfo(Document document, PatientMedical dto) throws DocumentException {
        if ((dto.getPatientPhysicalTherapy() != null) &&
                (dto.getPatientPhysicalTherapy().getLocation() != null || dto.getPatientPhysicalTherapy().getNumberOfVisit() != null)
        ) {
            PDFPageCreator.createHeaderWithColor(document, "Physical Therapy Receiving", BaseColor.RED);
            String[] primaryDoctorRow = new String[]{"Location", "Number Of Visit"};
            PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{
                    dto.getPatientPhysicalTherapy().getLocation(),
                    dto.getPatientPhysicalTherapy().getNumberOfVisit().toString(),
            }, 2);
        } else {
            PDFPageCreator.createHeader(document, "Physical Therapy Receiving");
            String[] primaryDoctorRow = new String[]
                    {"No Receiving Physical Therapy"};
            PDFPageCreator.createTable(document, primaryDoctorRow, new String[]{}, 1);
        }
        PDFPageCreator.createHeader(document, "Primary Doctor");
        String[] primaryDoctorRow = new String[]
                {""};
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
            String[] recommendationEntityRow = new String[]{""};
            PDFPageCreator.createTable(document, recommendationEntityRow, new String[]{
                    dto.getEntitySource().getOrganizationName()
            }, 1);
        }
    }

    private static void createMedicalHistoryInfo(Document document, PatientMedicalHistory dto) throws DocumentException {
        String[] firstHistoryRow = new String[]{"Height", "Weight", "Scanning test", "Metal Implantation", "Pacemaker"};
        String[] height =calculateHeight(dto.getHeightUnit() , dto.getHeight());
        String[] weight = calculateWeight(dto.getWeightUnit(),dto.getWeight().toString());
        PDFPageCreator.createTable(document, firstHistoryRow, new String[]{
                height[0] + " CM , " + height[1] + " FT",
                weight[0] +" KG , " + weight[1] + " LB",
                ((dto.getScanningTestValue() == null)
                        || dto.getScanningTestValue().isEmpty()) ? "No" : String.join(",", dto.getScanningTestValue()),
                dto.getMetalImplantation() ? "Yes" : "No",
                dto.getPacemaker() ? "Yes" : "No",
        }, 5);
    }

    private static String[] calculateHeight(String unit, String value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        String[] heightValue = new String[2];
        switch (unit) {
            case "cm":
                heightValue[0] = value;
                heightValue[1] = String.valueOf(decimalFormat.format(Integer.parseInt(value) * 0.032808));
                break;
            case "Inch":
                heightValue[0] = String.valueOf(Math.round(Integer.parseInt(value) / 0.032808));
                heightValue[1] = value;
                break;
        }
        return heightValue;
    }

    private static String[] calculateWeight(String unit, String value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        String[] weightValue = new String[2];
        switch (unit){
            case "kg":
                weightValue[0]= value;
                weightValue[1]= String.valueOf(decimalFormat.format(Integer.parseInt(value) * 2.20462));;
            break;
            case "pound":
                weightValue[0]= String.valueOf(Math.round(Double.parseDouble(value) / 2.20462));
                weightValue[1]= value;
                break;
        }
        return weightValue;
    }
}
