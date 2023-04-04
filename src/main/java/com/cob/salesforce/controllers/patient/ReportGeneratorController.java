package com.cob.salesforce.controllers.patient;

import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.services.patient.export.ExcelReportGenerator;
import com.cob.salesforce.services.patient.export.PatientPDFGenerator;
import com.itextpdf.text.DocumentException;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePDF(HttpServletResponse response) throws DocumentException, IOException {
        PatientPDFGenerator  pdfGenerator = new PatientPDFGenerator();
        pdfGenerator.generate(response);
    }
}
