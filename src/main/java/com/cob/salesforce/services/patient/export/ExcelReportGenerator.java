package com.cob.salesforce.services.patient.export;

import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.utils.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelReportGenerator {
    List<PatientContainerDTO> patients;

    private XSSFSheet sheet;

    public void setPatients(List<PatientContainerDTO> patients) {
        this.patients = patients;
    }

    private void writeHeaderLine(XSSFWorkbook workbook) {
        if (sheet == null)
            sheet = workbook.createSheet("Patients");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "First Name", style);
        createCell(row, 1, "Middle Name", style);
        createCell(row, 2, "Last Name", style);
        createCell(row, 3, "Gender", style);
        createCell(row, 4, "Patient Id-Type", style);
        createCell(row, 5, "Patient Id", style);
        createCell(row, 6, "Phone", style);
        createCell(row, 7, "Created", style);
        createCell(row, 8, "Source", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(XSSFWorkbook workbook) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (PatientContainerDTO patient : patients) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, patient.getFirstName(), style);
            createCell(row, columnCount++, patient.getMiddleName(), style);
            createCell(row, columnCount++, patient.getLastName(), style);
            createCell(row, columnCount++, patient.getGender(), style);
            createCell(row, columnCount++, patient.getIdType(), style);
            createCell(row, columnCount++, patient.getPatientId(), style);
            createCell(row, columnCount++, patient.getPhoneNumber(), style);
            DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
            Date createdDate = new Date(patient.getCreatedAt());
            createCell(row, columnCount++, formatter.format(createdDate), style);
            switch (patient.getPatientSourceType()) {
                case Doctor:
                    createCell(row, columnCount++, "Doctor:" + patient.getDoctorSourceData(), style);
                    break;
                case Entity:
                    createCell(row, columnCount++, patient.getEntitySourceData(), style);
                    break;
            }

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        writeHeaderLine(workbook);
        writeDataLines(workbook);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
