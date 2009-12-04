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
import data.*;
import java.text.DecimalFormat;

/** 
 * 
 * @author Administrador 
 */ 
public class PruebaCesar
{
    static DbConnection datos = new DbConnection();     
    /** 
     * @param args the command line arguments 
     */ 
    //public static void lanzar(String query,String fechaFactura,BeanCliente beanCliente,int flag) throws ClassNotFoundException, SQLException {
    public static void lanzar(ArrayList lista,BeanCliente beanCliente,String fechaFactura) throws ClassNotFoundException, SQLException, JRException
    {
     //Lo primero que hacemos es borrar la tabla para generar la factura que queremos
     String queryDel = "DELETE FROM fa_facturas_aux";
     boolean resDel = datos.manipuladorDatos(queryDel);

     String importeTotal = "";
     String importeTotalIva = "";
     String importeIva = "";
     String cl_id = beanCliente.getCl_id();

     for(int i = 0; i < lista.size(); i++)
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
        String importeServicioEs = "";
        String importeCampa="";
        String importeCampa2="";
        double importeTarifa = 0;
        double importeServicio = 0;
        double importeFc = 0;
        double importeSup = 0;
        double total = 0;
        double iva = 0;
        double totalIva = 0;

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
        servicioEspecial = otro.getServicioEspecial();
        
        if(numCampa.equals("0"))
        {
            //LINEA DE TRASLADO
            String servicio = otro.getServicio();
            String origen = otro.getProvinciaOrigen();
            String destino = otro.getProvinciaDestino();

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

            //TARIFA
            if(otro.getTarifaEsCliente().equals("0"))
            {
                importeTraslado = otro.getTarifa();
            }
            else
            {
                importeTraslado = otro.getTarifaEsCliente();
            }
            importeTarifa = Double.parseDouble(importeTraslado);

             //LINEA DE FACTOR DE CORRECCION
            ArrayList factorTarifa = obtenerFactor(factor, cl_id);

            if(!factorTexto.equals("TURISMO") && importeTraslado != null)
            {
                 labelFactor = "FACTOR DE CORRECCION";
                 DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                 double ft = Double.parseDouble(factorTarifa.get(0).toString());
                 importeFc = ((importeTarifa * ft) - importeTarifa);
                 importeFactor = Double.toString(importeFc);
                 factorTexto = factorTarifa.get(1).toString();
                 //double dd2dec = new Double(df2.format(valor F)).doubleValue();
                 //System.out.println("Valor factor de corrección"+dd2dec);
            }
            
            //SERVICIOS ESPECIALES
            String campoServicio = "";
            
            if (servicioEspecial.equals("Urgente"))
            {
                campoServicio = "sc_urgente"; 
            }
            else if(servicioEspecial.equals("ITV"))
            {
                campoServicio = "sc_itv";
            }
            else if(servicioEspecial.equals("Pre_ITV"))
            {
                campoServicio = "sc_pre_itv";
            }
            else if(servicioEspecial.equals("Chequeo"))
            {
                campoServicio = "sc_chequeo";
            }
            else if(servicioEspecial.equals("Reacondicionamiento"))
            {
                campoServicio = "sc_reacondicionamiento";
            }
            else if(servicioEspecial.equals("Lavado Exterior"))
            {
                campoServicio = "sc_lavado";
            }
            else if(servicioEspecial.equals("L. Interior y Exterior"))
            {
                campoServicio = "sc_lavado_exin";
            }
            else if(servicioEspecial.equals("Lavado Extra"))
            {
                campoServicio = "sc_lavado_extra";
            }
            else if(servicioEspecial.equals("Lavado Integral"))
            {
                campoServicio = "sc_completo";
            }
            else if(servicioEspecial.equals("Lavado Higienizado"))
            {
                campoServicio = "sc_higienizado";
            }
            else if(servicioEspecial.equals("L. Interior y Exterior4x4"))
            {
                campoServicio = "sc_int_ext_cuatro";
            }
            else if(servicioEspecial.equals("Lavado Integral 4x4"))
            {
                campoServicio = "sc_integral_cuatro";
            }
            else if(servicioEspecial.equals("L. Interior y Ext. Indust."))
            {
                campoServicio = "sc_int_ext_industrial";
            }
            else if(servicioEspecial.equals("Lavado Integral Indust."))
            {
                campoServicio = "sc_integral_industrial";
            }
            else if(servicioEspecial.equals("Limpieza + Pegatinas"))
            {
                campoServicio = "sc_limpieza_pegatinas";
            }
            else if(servicioEspecial.equals("Limpieza Integral+Peg."))
            {
                campoServicio = "sc_interior_pegatinas";
            }
            else if(servicioEspecial.equals("Repostaje"))
            {
                campoServicio = "sc_repostaje";
            }
            else if(servicioEspecial.equals("M. obra Mecánic/Chapa"))
            {
                campoServicio = "sc_mo_mecanica_chapa";
            }

           String querySe = "SELECT "+campoServicio+" FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

           ResultSet rsSe = datos.select(querySe);
           while (rsSe.next())
           {
            importeServicioEs = rsSe.getString(campoServicio);
           }
           importeServicio = Integer.parseInt(importeServicioEs);

            //SUPLEMENTO
            if(!otro.getSuplemento().equals("0"))
            {
                labelSuplemento="SUPLEMENTO";
                ServicioSuplemento = otro.getDescripcion();
                importeSup = Integer.parseInt(importeSuplemento);
            }
        }
        else
        {
            soporte = "CAMPA";
            labelCampa = "CAMPA";
            labelCampa2 = "CAMPA";
            finalCampa = "ENTRADA";
            finalCampa2 = otro.getDiasCampa()+ " DIAS";
        }

        //LINEA DE SERVICIO ESPECIAL OTROS
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

        //TOTAL
        total = importeTarifa + importeFc + importeServicio + importeSup;
        importeTotal = Double.toString(total);

        //IVA
        iva = ((total * 16) / 100.0);
        importeIva = Double.toString(iva);

        //TOTAL IVA
        totalIva = total + iva;
        importeTotalIva = Double.toString(totalIva);

        String query = "INSERT INTO fa_facturas_aux (fa_num, fa_fecha, fa_marca, fa_modelo, " +
                                                    "fa_matricula, fa_factor, fa_soporte, fa_traslado, " +
                                                    "fa_texto_traslado, fa_importe_traslado, fa_factor_correccion, " +
                                                    "fa_texto_factor_correccion, fa_importe_factor_correccion, " +
                                                    "fa_suplemento, fa_texto_suplemento, fa_importe_suplemento, " +
                                                    "fa_servicio_adicional, fa_texto_servicio_adicional, " +
                                                    "fa_importe_servicio_adicional, fa_campa,fa_texto_campa, fa_importe_campa, " +
                                                    "fa_campa2, fa_texto_campa2, fa_importe_campa2, fa_importe_total) " +
                                                    "VALUES (";
        query = query + "'"+finalNum+"','"+fecha+"','"+marca+"','"+modelo+"','"+matricula+"','"+factorTexto+"'," +
                        "'"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTraslado+"','"+labelFactor+"'," +
                        "'"+factorTexto+"','"+importeFactor+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+""+"'," +
                        "'"+labelServicioEspecial+"','"+servicioEspecial+"','"+importeServicioEs+"','"+labelCampa+"','"+finalCampa+"'," +
                        "'"+""+"','"+labelCampa2+"','"+finalCampa2+"','"+""+"','"+importeTotal+"')";

        System.out.println(query);
        boolean rs3 = datos.manipuladorDatos(query);
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
        con = DriverManager.getConnection("jdbc:mysql://localhost/carset","root","sc09V1");
        //1-Compilamos el archivo XML y lo cargamos en memoria 
        jasperReport = JasperCompileManager.compileReport("src\\data\\report1.jrxml");

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
        pars.put("IVA","16%");
        pars.put("ImporteIVA", importeIva);
        pars.put("ImporteTotalIVA", importeTotalIva);
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
        jasperPrint = JasperFillManager.fillReport("src\\data\\report1.jasper", pars, con);

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

  /**
   * Función para recuperar el valor y texto del factor de corrección del pedido seleccionado
   * @param factor
   * @param cliente
   * @return
   * @throws SQLException
   */
  private static ArrayList obtenerFactor(String factor, String cliente) throws SQLException
  {
      ArrayList factorSel = new ArrayList();
      int factorInt = Integer.parseInt(factor);
      String campo = "";
      String descripcion = "";

      switch(factorInt)
      {
          case 0 :
              descripcion = "TURISMO";
          break;
          case 1 : 
              campo = "sc_industrial";
              descripcion = "INDUSTRIAL";
          break;
          case 2 : 
              campo = "sc_todoterreno";
              descripcion = "TODOTERRENO";
          break;
          case 3 : 
              campo = "sc_furgonetas";
              descripcion = "FURGONETAS";
          break;
          case 4 : 
              campo = "sc_furgones";
              descripcion = "FURGONES";
          break;
      }

      String queryFactor = "SELECT "+campo+" FROM sc_servicios_clientes WHERE cl_id="+cliente;
      ResultSet rs3 = datos.select(queryFactor);

      while(rs3.next())
      {
          factorSel.add(rs3.getDouble(campo));
          factorSel.add(descripcion);
      }
      return factorSel;
  }

}