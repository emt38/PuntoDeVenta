package reportes;
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
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;
	private static Map<String, Object> parameters;
	
	public static void createReport(Connection conn, String path){
		
		try{
			report = (JasperReport) JRLoader.loadObjectFromFile(path);
			reportFilled = JasperFillManager.fillReport(report, parameters, conn);
					
		}
		catch(JRException ex){
			ex.printStackTrace();
		}
	}
	
	public static void showViewer(){
		viewer = new JasperViewer(reportFilled);
		viewer.setVisible(true);
	}
	
	public static void exportToPDF(String destination){
		try {
			JasperExportManager.exportReportToPdfFile(reportFilled, destination);
		}
		catch(JRException ex){
			ex.printStackTrace();
		}
	}

}
