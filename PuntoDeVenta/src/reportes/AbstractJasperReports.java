package reportes;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;

public abstract class AbstractJasperReports {
	
	private static JasperReport report;
	private static JasperPrint reporteLleno;
	private static JasperViewer viewer;
	private static Map<String, Object> parameters;
	
	public static void createReport(Connection conn, String path){
		
		try{
			report = (JasperReport) JRLoader.loadObjectFromFile(path);
			reporteLleno = JasperFillManager.fillReport(report, parameters, conn);
		}
		catch(JRException ex){
			ex.printStackTrace();
		}
	}
	
	public static boolean reporteEstaLleno(Connection conn, String path){
		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(path);
			reporteLleno = JasperFillManager.fillReport(report, parameters, conn);
		} catch (JRException e) {
			e.printStackTrace();
		}		
		return (reporteLleno != null);
	}
	
	public static void showViewer() {
		viewer = new JasperViewer(reporteLleno);
		viewer.setVisible(true);
	}
	public static void hideViewer(){
		viewer.setVisible(false);
	}
	public static Point getLocation(){
		return viewer.getLocation();
	}
	
	public static void exportToPDF(Connection conn, String destination){
		try {
			reporteLleno = JasperFillManager.fillReport(report, parameters, conn);
			JasperExportManager.exportReportToPdfFile(reporteLleno, destination);
		}
		catch(JRException ex){
			ex.printStackTrace();
		}
	}

}
