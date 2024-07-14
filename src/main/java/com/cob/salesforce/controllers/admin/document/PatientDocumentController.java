package com.cob.salesforce.controllers.admin.document;

import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.services.admin.document.PatientDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
@RequestMapping(value = "/document")
public class PatientDocumentController {

    @Autowired
    PatientDocumentService documentService;

    @GetMapping(value = "/id/patientId/{patientId}/hasGuarantor/{hasGuarantor}", produces = "application/zip")
    public void exportPatientIdDocument(@PathVariable("patientId") Long patientId, @PathVariable("hasGuarantor") Boolean hasGuarantor, HttpServletResponse response) throws IOException {
        if (!hasGuarantor) {
            List<PatientPhotoImage> idDocuments = documentService.findIdDocumentByType(patientId);
            exportAsZipFiles(idDocuments, response);
        } else {
            PatientGrantorEntity patientGrantor = documentService.findGuarantorIdDocument(patientId);
            exportGuarantorAsZipFiles(patientGrantor, response);
        }
    }

    @GetMapping(value = "/insurance/patientId/{patientId}")
    public void exportPatientInsuranceDocument(@PathVariable("patientId") Long patientId, HttpServletResponse response) throws IOException {
        List<PatientPhotoImage> idDocuments = documentService.findInsuranceDocumentByType(patientId);
        exportAsZipFiles(idDocuments, response);
    }

    private void exportAsZipFiles(List<PatientPhotoImage> documents, HttpServletResponse response) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (int i = 0; i < documents.size(); i++) {
            String documentName = i + "_" + documents.get(i).getName().split(":")[0] +".jpg";
            ZipEntry zipEntry = new ZipEntry(documentName);
            zipEntry.setSize(documents.get(i).getImage().length);
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(documents.get(i).getImage(), zipOut);
            zipOut.closeEntry();
        }
        zipOut.finish();
        zipOut.close();
    }

    private void exportGuarantorAsZipFiles(PatientGrantorEntity patientGrantor, HttpServletResponse response) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                String documentName = "Patient Grantor ID Front.jpg";
                ZipEntry zipEntry = new ZipEntry(documentName);
                zipEntry.setSize(patientGrantor.getIdFront().length);
                zipOut.putNextEntry(zipEntry);
                StreamUtils.copy(patientGrantor.getIdFront(), zipOut);
                zipOut.closeEntry();
            }
            if (i == 1) {
                String documentName = "Patient Grantor ID Back.jpg";
                ZipEntry zipEntry = new ZipEntry(documentName);
                zipEntry.setSize(patientGrantor.getIdBack().length);
                zipOut.putNextEntry(zipEntry);
                StreamUtils.copy(patientGrantor.getIdBack(), zipOut);
                zipOut.closeEntry();
            }
        }
        zipOut.finish();
        zipOut.close();
    }
}
