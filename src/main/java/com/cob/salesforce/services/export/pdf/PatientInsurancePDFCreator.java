package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.insurance.commercial.PatientCommercialInsurance;
import com.cob.salesforce.models.intake.insurance.compensation.PatientInsuranceCompensationNoFault;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PatientInsurancePDFCreator {
    public static void create(Document document, Patient dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Insurance Information");
        if (dto.getPatientInsurance().getPatientInsuranceCompensationNoFault() == null)
            createInsuranceWorkerCommercial(document, dto.getPatientInsurance().getPatientCommercialInsurance());
        else
            createInsuranceWorkerCompNoFault(document, dto.getPatientInsurance().getPatientInsuranceCompensationNoFault());

    }

    private static void createInsuranceWorkerCommercial(Document document, PatientCommercialInsurance patientCommercialInsurance) throws DocumentException {
        String[] typeRow = new String[]{"Insurance Type"};
        PDFPageCreator.createTable(document, typeRow, new String[]{
                "Commercial"
        }, 1);
        PDFPageCreator.createHeader(document, "");
        String[] firstRow = new String[]{"Insurance Company", "MemberId", "PolicyId", "Relationship"};
        PDFPageCreator.createTable(document, firstRow, new String[]{
                patientCommercialInsurance.getInsuranceCompanyId().toString(),
                patientCommercialInsurance.getMemberId().toString(),
                patientCommercialInsurance.getPolicyId().toString(),
                patientCommercialInsurance.getRelationship()
        }, 4);
        PDFPageCreator.createHeader(document, "");
        String[] secondRow = new String[]{"Secondary Insurance", "Medicare Coverage"};
        PDFPageCreator.createTable(document, secondRow, new String[]{
                patientCommercialInsurance.getSecondaryInsurance() != null ? "Yes" : "No",
                patientCommercialInsurance.getMedicareCoverage() != null ? "Yes" : "No"
        }, 2);
    }

    private static void createInsuranceWorkerCompNoFault(Document document, PatientInsuranceCompensationNoFault patientInsuranceCompensationNoFault) throws DocumentException {
        String[] typeRow = new String[]{"Insurance Type"};
        PDFPageCreator.createTable(document, typeRow, new String[]{
                "Compensation No Fault"
        }, 1);
        PDFPageCreator.createHeader(document, "");
        String[] firstRow = new String[]{"InjuryType", "AccidentDate", "WorkerStatus", "WorkerAddress"};
        PDFPageCreator.createTable(document, firstRow, new String[]{
                patientInsuranceCompensationNoFault.getInjuryType(),
                patientInsuranceCompensationNoFault.getAccidentDate().toString(),
                patientInsuranceCompensationNoFault.getWorkerStatus(),
                patientInsuranceCompensationNoFault.getAddress().getFirst() + "," + patientInsuranceCompensationNoFault.getAddress().getCity()
        }, 4);
        PDFPageCreator.createHeader(document, "");
        String[] secondRow = new String[]{"insuranceName", "claimNumber"};
        PDFPageCreator.createTable(document, secondRow, new String[]{
                patientInsuranceCompensationNoFault.getInsuranceName(),
                patientInsuranceCompensationNoFault.getClaimNumber().toString()
        }, 2);
        PDFPageCreator.createHeader(document, "");
        String[] adjusterRow = new String[]{"AdjusterName", "AdjusterPhone"};
        PDFPageCreator.createTable(document, adjusterRow, new String[]{
                patientInsuranceCompensationNoFault.getAdjusterInfoName(),
                patientInsuranceCompensationNoFault.getAdjusterInfoPhone()
        }, 2);
        PDFPageCreator.createHeader(document, "");
        String[] attorneyRow = new String[]{"AttorneyName", "AttorneyPhone"};
        PDFPageCreator.createTable(document, attorneyRow, new String[]{
                patientInsuranceCompensationNoFault.getAttorneyInfoName(),
                patientInsuranceCompensationNoFault.getAttorneyInfoPhone()
        }, 2);
    }
}
