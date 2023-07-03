package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.InsuranceWorkerCommercialDTO;
import com.cob.salesforce.models.InsuranceWorkerCompNoFaultDTO;
import com.cob.salesforce.models.PatientDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class PatientInsurancePDFCreator {
    public static void create(Document document, PatientDTO dto) throws DocumentException {
        PDFPageCreator.createHeader(document, "Insurance Information");
        if (!dto.getInsuranceQuestionnaireInfo().getIsCompNoFault())
            createInsuranceWorkerCommercial(document, dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial());
        else
            createInsuranceWorkerCompNoFault(document, dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault());

    }

    private static void createInsuranceWorkerCommercial(Document document, InsuranceWorkerCommercialDTO dto) throws DocumentException {
        String[] typeRow = new String[]{"Insurance Worker Type"};
        PDFPageCreator.createTable(document, typeRow, new String[]{
                "Insurance Worker Commercial"
        }, 1);
        PDFPageCreator.createHeader(document, "");
        String[] firstRow = new String[]{"Insurance Company", "MemberId", "PolicyId", "Relationship"};
        PDFPageCreator.createTable(document, firstRow, new String[]{
                dto.getInsuranceCompanyId().toString(),
                dto.getMemberId().toString(),
                dto.getPolicyId().toString(),
                dto.getRelationship()
        }, 4);
        PDFPageCreator.createHeader(document, "");
        String[] secondRow = new String[]{"Secondary Insurance", "Medicare Coverage"};
        PDFPageCreator.createTable(document, secondRow, new String[]{
                dto.getIsSecondaryInsurance() ? "Yes" : "No",
                dto.getIsMedicareCoverage() ? "Yes" : "No"
        }, 2);
    }

    private static void createInsuranceWorkerCompNoFault(Document document, InsuranceWorkerCompNoFaultDTO dto) throws DocumentException {
        String[] typeRow = new String[]{"Insurance Worker Type"};
        PDFPageCreator.createTable(document, typeRow, new String[]{
                "Insurance Worker Compensation"
        }, 1);
        PDFPageCreator.createHeader(document, "");
        String[] firstRow = new String[]{"InjuryType", "AccidentDate", "WorkerStatus", "WorkerAddress"};
        PDFPageCreator.createTable(document, firstRow, new String[]{
                dto.getInjuryType(),
                dto.getAccidentDate().toString(),
                dto.getWorkerStatus(),
                dto.getWorkerCompAddress().getFullAddress()
        }, 4);
        PDFPageCreator.createHeader(document, "");
        String[] secondRow = new String[]{"insuranceName", "claimNumber"};
        PDFPageCreator.createTable(document, secondRow, new String[]{
                dto.getInsuranceName(),
                dto.getClaimNumber().toString()
        }, 2);
        PDFPageCreator.createHeader(document, "");
        String[] adjusterRow = new String[]{"AdjusterName", "AdjusterPhone"};
        PDFPageCreator.createTable(document, adjusterRow, new String[]{
                dto.getAdjusterInfoName(),
                dto.getAdjusterInfoPhone()
        }, 2);
        PDFPageCreator.createHeader(document, "");
        String[] attorneyRow = new String[]{"AttorneyName", "AttorneyPhone"};
        PDFPageCreator.createTable(document, attorneyRow, new String[]{
                dto.getAttorneyInfoName(),
                dto.getAttorneyInfoPhone()
        }, 2);
    }
}
