package com.cob.salesforce.controllers.patient;

import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.pdf.PatientData;
import com.cob.salesforce.services.patient.PatientFinderService;
import com.cob.salesforce.services.patient.export.ExcelReportGenerator;
import com.cob.salesforce.services.patient.export.PatientPDFGenerator;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reports/generator")
public class ReportGeneratorController {

    @Autowired
    PatientFinderService patientFinderService;

    @PostMapping("/excel")
    public void generateExcel(@RequestBody List<PatientContainerDTO> patients, HttpServletResponse response) throws IOException {
        ExcelReportGenerator excelReportGenerator = new ExcelReportGenerator();
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        excelReportGenerator.setPatients(patients);
        excelReportGenerator.export(response);
    }

    @PostMapping(value = "/pdf")
    public void generatePDF(@RequestParam(name = "insuranceWorkerType") String insuranceWorkerType,
                            @RequestParam(name = "patientSourceType") String patientSourceType,
                            @RequestParam(name = "hasPhysicalTherapy") Boolean hasPhysicalTherapy,
                            @RequestParam(name = "patientId") Long patientId,
                            HttpServletResponse response) throws DocumentException, IOException {
        PatientData patientData = patientFinderService.getPDFPatientData(
                InsuranceWorkerType.valueOf(insuranceWorkerType),
                PatientSourceType.valueOf(patientSourceType),
                hasPhysicalTherapy,
                patientId);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","inline");
        PatientPDFGenerator pdfGenerator = new PatientPDFGenerator();
        pdfGenerator.setData(patientData);
        pdfGenerator.generate(response);
    }
}
