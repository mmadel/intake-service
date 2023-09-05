package com.cob.salesforce.services.export.pdf;

import com.cob.salesforce.models.PatientDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import java.io.IOException;

public class PatientSignaturePDFGenerator {
    public static void create(Document document, PatientDTO dto) throws DocumentException, IOException {
        Image img = Image.getInstance((dto.getPatientSignature().getSignatureAsBytes()));
        document.add(img);
    }
}
