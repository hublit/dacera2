package neg; 

//import con_reportes.presentacion;
import data.BeanCliente;
import data.DbConnection;
import data.FacturasCesar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperExportManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import net.sf.jasperreports.view.JasperViewer;
import utils.Utilidades;


/** 
 * 
 * @author Administrador 
 */ 
public class PruebaCesar {

    DbConnection datos = new DbConnection();
   
   
    /** 
     * @param args the command line arguments 
     */ 
    //public static void lanzar(String query,String fechaFactura,BeanCliente beanCliente,int flag) throws ClassNotFoundException, SQLException {
    public static void lanzar(ArrayList lista,BeanCliente beanCliente,String fechaFactura) throws ClassNotFoundException, SQLException, JRException {
        // TODO code application logic here 


     //Lo primero que hacemos es borrar la tabla para generar la factura que queremos

     DbConnection datosDel = new DbConnection();
     String queryDel="DELETE FROM fa_facturas_aux";
     boolean resDel=datosDel.manipuladorDatos(queryDel);

     double importeTotal=0;

     for(int i=0;i<lista.size();i++)
     {
        String factorTexto="";
        String finalServicio="";
        String finalFactor="";
        String labelFactor="";
        String labelTraslado="";
        String labelSuplemento="";
        String ServicioSuplemento="";
        String labelServicioEspecial="";
        String servicioEspecial="";
        String finalNum="";
        String labelCampa="";
        String labelCampa2="";
        String finalCampa="";
        String finalCampa2="";
        String importeTraslado="";
        String importeFactor="";
        String importeSuplemento="";
        String importeCampa="";
        String importeCampa2="";

        FacturasCesar otro = (FacturasCesar)lista.get(i);

        String numPedido=Long.valueOf(otro.getNumPedido()).toString();
        finalNum=Utilidades.rellenarCeros(numPedido, 5);
        String fecha=otro.getFecha();
        finalNum=finalNum +"/"+ fecha.substring(2, 4);
        String marca=otro.getMarca();
        String modelo=otro.getModelo();
        String matricula=otro.getMatricula();
        String soporte=otro.getSoporte();
        String factor=otro.getFactor();
        String numCampa=otro.getDiasCampa();
        
        if(numCampa.equals("0"))
        {


            //LINEA DE TRASLADO
            String servicio=otro.getServicio();
            String origen=otro.getProvinciaOrigen();
            String destino=otro.getProvinciaDestino();

            if((!origen.equals("Selecciona")) && (!destino.equals("Selecciona")))
            {
                if(origen.equals(destino))
                {
                    finalServicio=origen+" "+ servicio.toUpperCase();
                }
                else
                {
                    finalServicio=origen+" - "+ destino;
                }
                labelTraslado="TRASLADO";
            }
            else
            {
                finalServicio="MADRID" + servicio;
            }
            if(otro.getTarifaEsCliente().equals("0"))
            {
                importeTraslado=otro.getTarifa();
            }
            else
            {
                importeTraslado=otro.getTarifaEsCliente();
            }


            if(!otro.getSuplemento().equals("0"))
            {
                labelSuplemento="SUPLEMENTO";
                ServicioSuplemento=otro.getDescripcion();
            }

             //LINEA DE FACTOR DE CORRECCION
            factorTexto=obtenerFactor(factor);

            if(!factorTexto.equals("TURISMO"))
            {

                 labelFactor="FACTOR DE CORRECCION";
                 //double valor= Double.parseDouble(importeTraslado) -(Double.parseDouble(importeTraslado) * importeFactorNum);
            }
        }
        else
        {
            soporte="CAMPA";
            labelCampa="CAMPA";
            labelCampa2="CAMPA";
            finalCampa="ENTRADA";
            finalCampa2=otro.getDiasCampa()+ " DIAS";
        }

        //LINEA DE SERVICIO ESPECIAL
        if(!otro.getServicioEspecial().equals(""))
        {
            if(otro.getServicioEspecial().equals("Otros"))
            {
               servicioEspecial=otro.getDescripcion().toUpperCase();
            }
            else
            {
               servicioEspecial=otro.getServicioEspecial().toUpperCase();
            }
            labelServicioEspecial="SERVICIO ESPECIAL";
        }

        String query = "INSERT INTO fa_facturas_aux (fa_num, fa_fecha, fa_marca, fa_modelo, " +
                                                         "fa_matricula,fa_factor,fa_soporte,fa_traslado,fa_texto_traslado,fa_importe_traslado,fa_factor_correccion,fa_texto_factor_correccion,fa_importe_factor_correccion,fa_suplemento,fa_texto_suplemento,fa_importe_suplemento,fa_servicio_adicional,fa_texto_servicio_adicional,fa_importe_servicio_adicional,fa_campa,fa_texto_campa,fa_importe_campa,fa_campa2,fa_texto_campa2,fa_importe_campa2) VALUES (";
        query = query + "'"+finalNum+"','"+fecha+"','"+marca+"','"+modelo+"','"+matricula+"','"+factorTexto+"','"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTraslado+"','"+labelFactor+"','"+factorTexto+"','"+""+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+""+"','"+labelServicioEspecial+"','"+servicioEspecial+"','"+""+"','"+labelCampa+"','"+finalCampa+"','"+""+"','"+labelCampa2+"','"+finalCampa2+"','"+""+"')";

        System.out.println(query);
        DbConnection datos2 = new DbConnection();
        boolean rs3 = datos2.manipuladorDatos(query);

        importeTotal=0;
     }

     
    JasperReport jasperReport = null;
    JasperPrint jasperPrint; 
    Connection con = null;
    String direccionFiscal="";
    String poblacionFiscal="";
    String provinciaFiscal="";
    String codPostalFiscal="";


     //FacturaXML nueva=new FacturaXML(query);
    try 
    { 
        Class.forName("com.mysql.jdbc.Driver"); 
        con = DriverManager.getConnection("jdbc:mysql://localhost/carset","root","rcortes");
            //1-Compilamos el archivo XML y lo cargamos en memoria 
      jasperReport = JasperCompileManager.compileReport(
          "c:\\report1.jrxml");

     /* JasperCompileManager.compileReportToFile("c:\\prueba.jrxml");*/


      if(beanCliente.getDireccion_fiscal().equals(""))
      {
          direccionFiscal=beanCliente.getDireccion();
          poblacionFiscal=beanCliente.getPoblacion();
          provinciaFiscal=beanCliente.getProvincia();
          codPostalFiscal=beanCliente.getCod_postal();
      }
      else
      {
          direccionFiscal=beanCliente.getDireccion_fiscal();
          poblacionFiscal=beanCliente.getPoblacion_fiscal();
          provinciaFiscal=beanCliente.getProvinciaFiscal();
          codPostalFiscal=beanCliente.getCod_postal_fiscal();
      }


      String [] tempOrigen = null;
      tempOrigen = fechaFactura.split("\\/");                    
                    String diaO=tempOrigen[2];
                    String nuevaO=diaO.substring(2,4);
                

      Map pars = new HashMap();
        pars.put("FechaFactura", fechaFactura);
        pars.put("NombreCliente",beanCliente.getNombre());
        pars.put("DireccionFiscal", direccionFiscal);
        pars.put("Direccion", beanCliente.getDireccion().concat(beanCliente.getPoblacion()));
        pars.put("PoblacionFiscal", poblacionFiscal);
        pars.put("Poblacion", beanCliente.getPoblacion());
        pars.put("ProvinciaFiscal", provinciaFiscal);
        pars.put("Provincia", beanCliente.getProvincia());
        pars.put("CodPostalFiscal", codPostalFiscal);
        pars.put("CodPostal", beanCliente.getCod_postal());
        pars.put("CIF", beanCliente.getDNI_CIF());
        pars.put("Query","SELECT * FROM pe_pedidos;");
        pars.put("Blanco","");
        pars.put("Factor","Turismo");
        /*if(flag==1)
            pars.put("NumFactura","");
        else
            pars.put("NumFactura","09/"+1212);*/
        pars.put("Anyo","/"+nuevaO);

        pars.put("TipoServicio","Probanso");
        pars.put("Servicio","Prueba");
        pars.put("Importe","Dinero");


        //JasperFillManager.fillReportToFile("c:\\report1.jasper", pars, new JREmptyDataSource());

        //JasperExportManager.exportReportToPdfFile("c:\\report1.jrprint");
        //2-Llenamos el reporte con la informaci�n y par�metros necesarios
        jasperPrint = JasperFillManager.fillReport(
          "c:\\report1.jasper", pars, con);

               //3-Exportamos el reporte a pdf y lo guardamos en disco 
      //JasperExportManager.exportReportToPdfFile(
      //    jasperPrint, "c:/holaMundo.pdf");


        JRViewer jrViewer = new JRViewer(jasperPrint);
        CSDesktop.NuevaFactura = new JInternalFrame("Previsualización Factura Cliente", true, false, false, true );
        CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
        //CSDesktop.NuevaFactura.add(jrViewer);
        CSDesktop.NuevaFactura.pack();
       
        CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
        Dimension pantalla = CSDesktop.elEscritorio.getSize();
        CSDesktop.NuevaFactura.setSize(pantalla);
        CSDesktop.NuevaFactura.setVisible(true);

        


      /*JasperViewer hola=new JasperViewer(jasperPrint, false);
      CSDesktop.NuevaFactura = new JInternalFrame("Resultado Búsqueda Pedidos", true, false, false, true );
     //CSDesktop.NuevaFactura.getContentPane().add( hola, BorderLayout.CENTER );
      CSDesktop.NuevaFactura.pack();
      CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
      CSDesktop.NuevaFactura.setVisible( true );*/



      //JasperViewer.viewReport(jasperPrint, false);*/
    }
    catch (JRException e)
    {
      e.printStackTrace();
    }
  }

  private static String obtenerFactor(String factor) throws SQLException
  {
      DbConnection datos3 = new DbConnection();
      String factorAux="";

      int factorInt=Integer.parseInt(factor);

      String queryFactor="select fc_descripcion from fc_factores_correccion where fc_id="+factorInt;
      ResultSet rs3=datos3.select(queryFactor);

      while(rs3.next())
      {
          factorAux=rs3.getString("fc_descripcion");
      }
      return factorAux;
  }
}
