package neg; 

//import con_reportes.presentacion;
import java.awt.Frame; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.HashMap; 
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperExportManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import net.sf.jasperreports.view.JasperViewer;


/** 
 * 
 * @author Administrador 
 */ 
public class PruebaCesar {

    /** 
     * @param args the command line arguments 
     */ 
    public static void lanzar() throws ClassNotFoundException, SQLException {
        // TODO code application logic here 
      
         
    JasperReport jasperReport; 
    JasperPrint jasperPrint; 
    Connection con = null; 
    try 
    { 
        Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/carset","root","rcortes");
            //1-Compilamos el archivo XML y lo cargamos en memoria 
      jasperReport = JasperCompileManager.compileReport( 
          "c:/report1.jrxml");
           
               //2-Llenamos el reporte con la informaci�n y par�metros necesarios  
      jasperPrint = JasperFillManager.fillReport( 
          jasperReport, new HashMap(), con); 

               //3-Exportamos el reporte a pdf y lo guardamos en disco 
      //JasperExportManager.exportReportToPdfFile(
      //    jasperPrint, "c:/holaMundo.pdf");

      JasperViewer hola=new JasperViewer(jasperPrint, false);
      CSDesktop.NuevaFactura = new JInternalFrame("Resultado Búsqueda Pedidos", true, false, false, true );
      CSDesktop.NuevaFactura.add(hola);
      CSDesktop.NuevaFactura.pack();
     
      CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );



      //JasperViewer.viewReport(jasperPrint, false);
    } 
    catch (JRException e) 
    { 
      e.printStackTrace(); 
    } 
  }
}
