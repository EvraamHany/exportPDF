package org.websparrow.report.service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import org.springframework.stereotype.Service;
import org.websparrow.report.dto.Employee;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeReportService {



	public void generateReport() {
		try {

			Connection conn = PostgresConnectionUtils.getConnection();
			String reportPath = "/home/evraam/Work/springboot-jasper-report/src/main/resources";

			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "/employee-rpt.jrxml");

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("local", "ar");
//			parameters.put(JRXlsAbstractExporter.PROPERTY_SHEET_DIRECTION, "RTL");

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			jasperPrint.addStyle(prepareStyle());

			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");
//			JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "\\Emp-Rpt.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JRDesignStyle prepareStyle(){
		JRDesignStyle jrDesignStyle = new JRDesignStyle();
		jrDesignStyle.setDefault(true);
		jrDesignStyle.setPdfFontName("/arial.ttf");
		jrDesignStyle.setPdfEncoding("Identity-H");
		jrDesignStyle.setPdfEmbedded(true);
		return jrDesignStyle;
	}
}
