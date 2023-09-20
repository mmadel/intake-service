package com.cob.salesforce.controllers.admin.reports;

import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.container.report.PatientReportRecord;
import com.cob.salesforce.services.export.excel.ExcelReportGenerator;
import com.cob.salesforce.services.export.pdf.PatientPDFGenerator;
import com.cob.salesforce.services.intake.PatientFinderServiceNew;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
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
    PatientFinderServiceNew patientFinderServiceNew;

    @PostMapping("/excel")
    public void generateExcel(@RequestBody List<PatientReportRecord> patients, HttpServletResponse response) throws IOException {
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

    @PostMapping(value = "/pdf/patientId/{patientId}")
    public void generatePDF(@PathVariable("patientId") Long patientId,
                            HttpServletResponse response) throws DocumentException, IOException {
        Patient model = patientFinderServiceNew.getPatient(patientId);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","inline");
        PatientPDFGenerator pdfGenerator = new PatientPDFGenerator();
        pdfGenerator.setData(model);
        pdfGenerator.generate(response);
    }
}
