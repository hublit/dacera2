package neg; 

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import utils.Utilidades;
import data.*;
import javax.mail.PasswordAuthentication;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;

/** 
 * 
 * @author Administrador 
 */ 
public class CSLanzarFactura
{

    JasperPrint jasperPrint;
    String nombreFichero="";
    /** 
     * @param args the command line arguments 
     */ 
    //public static void lanzar(String query,String fechaFactura,BeanCliente beanCliente,int flag) throws ClassNotFoundException, SQLException {
    public void lanzar(ArrayList lista,BeanCliente beanCliente,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin) throws ClassNotFoundException, SQLException, JRException
    {
     //Lo primero que hacemos es borrar la tabla para generar la factura que queremos
     String queryDel = "DELETE FROM fa_facturas_aux";
     boolean resDel = CSDesktop.datos.manipuladorDatos(queryDel);

     double total=0;
     double iva = 0;
     double totalIva = 0;
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
        String labelOtros="";
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
        String importeServicioEsOtros = "";
        String importeCampa="";
        String importeCampa2="";
        String importeCampaAux="";
        String importeCampa2Aux="";
        String labelIda="";
        String textoIda="";
        String importeIda="";
        String IdaVuelta="";
        String factorTexto2="";
        String importeTotalAux="";
        double importeTarifa = 0;
        double importeServicio = 0;
         double importeServicioOtros = 0;
        double importeFc = 0;
        double importeSup = 0;
        double totalAux = 0;       
        double IdaVueltaP=0;
        double IdaVuelta2=0;
        double importeCampa4=0;
        double importeCampa5=0;


        BeanFactura otro = (BeanFactura)lista.get(i);

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
        String numCamion=otro.getNumCamion();

        if(soporte.equals("Camion Completo"))
        {
            
        }

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
            if(otro.getTarifaEsCliente().equals("-1"))
            {
                importeTraslado = otro.getTarifa();
                if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                    importeTraslado="0";
               
            }
            else
            {
                if (!otro.getServicioEspecial().equals("Otros"))
                    importeTraslado = otro.getTarifaEsCliente();
                else
                    importeTraslado=otro.getTarifa();
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";
            }
            importeTarifa = Double.parseDouble(importeTraslado);

            //SI TIENE IDA Y VUELTA
            if(otro.getIdaVuelta().equals("1"))
            {
               String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

               ResultSet rsIv = CSDesktop.datos.select(queryIv);
               while (rsIv.next())
               {
                  IdaVuelta = rsIv.getString("sc_ida_vuelta");
               }

                labelIda="DESCUENTO";
                textoIda="IDA-VUELTA ("+IdaVuelta+"%)";

                IdaVueltaP=Double.parseDouble(IdaVuelta);

                IdaVuelta2=(importeTarifa*IdaVueltaP)/100;

                importeIda="- " + String.valueOf(IdaVuelta2);
            }

             //LINEA DE FACTOR DE CORRECCION

           
                ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                factorTexto = factorTarifa.get(0).toString();

             if(soporte.equals("Grúa"))
             {
                if((!factorTexto.equals("Sin factor") && (!factorTexto.equals("TURISMO")) )&& !importeTraslado.equals(""))
                {
                    labelFactor = "FACTOR DE CORRECCION";
                    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                    double ft = Double.parseDouble(factorTarifa.get(1).toString());
                    importeFc = ((importeTarifa * ft) - importeTarifa);
                    double nuevoImporteFactor=Utilidades.redondear(importeFc, 2);
                    importeFactor = Double.toString(nuevoImporteFactor);
                    factorTexto2=factorTarifa.get(0).toString();
                    //double dd2dec = new Double(df2.format(valor F)).doubleValue();
                    //System.out.println("Valor factor de corrección"+dd2dec);

                }
             }
            importeServicioEs=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
            if(!importeServicioEs.equals(""))
             importeServicio = Double.parseDouble(importeServicioEs);
           
            //SUPLEMENTO
            if(!otro.getSuplemento().equals("0"))
            {
                labelSuplemento="SUPLEMENTO";
                ServicioSuplemento = otro.getDescripcion();
                importeSuplemento=otro.getSuplemento();
                importeSup = Integer.parseInt(importeSuplemento);
            }
        }
        else
        {
             String queryCampa = "SELECT sc_entrada_campa,sc_campa FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

               ResultSet rsCampa = CSDesktop.datos.select(queryCampa);
               while (rsCampa.next())
               {
                  importeCampaAux = rsCampa.getString("sc_entrada_campa");
                  importeCampa2Aux = rsCampa.getString("sc_campa");
               }

            soporte = "CAMPA";
            labelCampa = "CAMPA";
            labelCampa2 = "CAMPA";
            finalCampa = "ENTRADA";
            finalCampa2 = otro.getDiasCampa()+ " DIAS * " + importeCampa2Aux;
            importeCampa=importeCampaAux;

            importeCampa4=Double.parseDouble(importeCampa);
            importeCampa5=(Double.parseDouble(otro.getDiasCampa()))*(Double.parseDouble(importeCampa2Aux));

            importeCampa2=String.valueOf(importeCampa5);

            ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
            factorTexto = factorTarifa.get(0).toString();
        }

        //LINEA DE SERVICIO ESPECIAL OTROS
        if(!otro.getServicioEspecial().equals(""))
        {
            if(otro.getServicioEspecial().equals("Otros"))
            {
               labelOtros=otro.getDescripcion().toUpperCase();
               importeServicioOtros=Integer.parseInt(otro.getTarifaEsCliente());
               importeServicioEsOtros=otro.getTarifaEsCliente();
               servicioEspecial="";
            }
            else
            {
               servicioEspecial=otro.getServicioEspecial().toUpperCase();
               labelServicioEspecial="SERVICIO ESPECIAL";
            }
           
        }

        //TOTAL
        totalAux = importeTarifa - IdaVuelta2 + importeFc + importeServicio + importeSup + importeCampa4 + importeCampa5 + importeServicioOtros;
        importeTotalAux = Double.toString(totalAux);

        

        String query = "INSERT INTO fa_facturas_aux (fa_num, fa_fecha, fa_marca, fa_modelo, " +
                                                    "fa_matricula, fa_factor, fa_soporte, fa_traslado, " +
                                                    "fa_texto_traslado, fa_importe_traslado, fa_factor_correccion, " +
                                                    "fa_texto_factor_correccion, fa_importe_factor_correccion, " +
                                                    "fa_suplemento, fa_texto_suplemento, fa_importe_suplemento, " +
                                                    "fa_servicio_adicional, fa_texto_servicio_adicional, " +
                                                    "fa_importe_servicio_adicional, fa_servicio_otro,fa_importe_servicio_otro,fa_campa,fa_texto_campa, fa_importe_campa, " +
                                                    "fa_campa2, fa_texto_campa2, fa_importe_campa2,fa_label_ida,fa_texto_ida,fa_importe_ida, fa_importe_total, fa_num_en_camion) " +
                                                    "VALUES (";
        query = query + "'"+finalNum+"','"+fecha+"','"+marca+"','"+modelo+"','"+matricula+"','"+factorTexto+"'," +
                        "'"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTraslado+"','"+labelFactor+"'," +
                        "'"+factorTexto2+"','"+importeFactor+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+importeSuplemento+"'," +
                        "'"+labelServicioEspecial+"','"+servicioEspecial+"','"+importeServicioEs+"','"+labelOtros+"','"+importeServicioEsOtros+"','"+labelCampa+"','"+finalCampa+"'," +
                        "'"+importeCampa+"','"+labelCampa2+"','"+finalCampa2+"','"+importeCampa2+"','"+labelIda+"','"+textoIda+"','"+importeIda+"','"+importeTotalAux+"','"+numCamion+"')";

        System.out.println(query);
        boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

        total=total + totalAux;
     }

    //IVA
        iva = ((total * 16) / 100.0);
        importeIva = Double.toString(iva);

        //TOTAL IVA
        totalIva = total + iva;
        importeTotalIva = Double.toString(totalIva);

    JasperReport jasperReport = null;
    Connection con = null;
    String direccionFiscal="";
    String poblacionFiscal="";
    String provinciaFiscal="";
    String codPostalFiscal="";
    String finalNumFactura="";

     //FacturaXML nueva=new FacturaXML(query);
    try 
    {
        DbConnection conexion=new DbConnection();
        con=conexion.getConexion();
        //1-Compilamos el archivo XML y lo cargamos en memoria 
       jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/Factura.jrxml"));

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
      tempOrigen = fechaFactura.split("\\-");
      String nuevaFechaFactura=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];     

      Map pars = new HashMap();
        pars.put("FechaFactura", nuevaFechaFactura);
        pars.put("NombreCliente",beanCliente.getNombre());
        pars.put("DireccionFiscal", direccionFiscal);
        pars.put("Direccion", beanCliente.getDireccion());
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
        pars.put("ImporteTotal",total);
        pars.put("IVA","16%");
        pars.put("ImporteIVA", iva);
        pars.put("ImporteTotalIVA", totalIva);
        pars.put("EURO","€");

        if(numero==0)
        {
            pars.put("NumFactura","PREV");
        }
        else
        {
            String numFactura=Integer.valueOf(numero).toString();
            finalNumFactura=Utilidades.rellenarCeros(numFactura,5);
            String finalNumCliente=Utilidades.rellenarCeros(beanCliente.getCl_id(), 5);
            finalNumFactura=nuevaFechaFactura.substring(8, 10)+"/"+ finalNumCliente +"/"+ finalNumFactura;
            //finalNumFactura=finalNumFactura +"/"+ fechaFactura.substring(8, 10);

            pars.put("NumFactura",finalNumFactura);
        }
               
        pars.put("TipoServicio","Probanso");
        pars.put("Servicio","Prueba");
        pars.put("Importe","Dinero");


        //JasperFillManager.fillReportToFile("src\\data\\report1.jasper", pars, con);
       
        //JasperExportManager.exportReportToPdfFile("src\\data\\report1.jrprint");
        //2-Llenamos el reporte con la informaci�n y par�metros necesarios
        jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/Factura.jasper"), pars, con);

       

        String finalNumFactura2=finalNumFactura.replace("/","_");
        nombreFichero=finalNumFactura2+"_"+beanCliente.getNombre()+".pdf";

         String property = "java.io.tmpdir";

        // Get the temporary directory and print it.
        String tempDir = System.getProperty(property);
        System.out.println("OS current temporary directory is " + tempDir);

        if(numero==0)
        {
             JRViewerSin jrViewer = new JRViewerSin(jasperPrint);
             CSDesktop.NuevaFactura = new JInternalFrame("Previsualización Factura Cliente", true, false, false, true );
             CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
             CSDesktop.NuevaFactura.pack();
             CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
             Dimension pantalla = CSDesktop.elEscritorio.getSize();
             CSDesktop.NuevaFactura.setSize(pantalla);
             CSDesktop.NuevaFactura.setVisible(true);
        }
        else
        {
            String query="INSERT INTO fl_factura_cliente (fl_num,fl_fecha_desde,fl_fecha_hasta,cl_id, " +
                    "fl_fecha_pago, fl_pagado) VALUES (";
            query = query + "'"+finalNumFactura+"','"+fechaIni+"','"+fechaFin+"','"+clienteID+"','0000-00-00','0')";

            System.out.println(query);

             boolean rs = CSDesktop.datos.manipuladorDatos(query);
             if(rs)
             {
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);

             }
             else
             {
                 JRViewerFactura jrViewer = new JRViewerFactura(jasperPrint,nombreFichero,beanCliente);
                 CSDesktop.NuevaFactura = new JInternalFrame("Generación Factura Cliente", true, false, false, true );
                 CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
                 CSDesktop.NuevaFactura.pack();
                 CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
                 Dimension pantalla = CSDesktop.elEscritorio.getSize();
                 CSDesktop.NuevaFactura.setSize(pantalla);
                 CSDesktop.NuevaFactura.setVisible(true);

                 //3-Exportamos el reporte a pdf y lo guardamos en disco
                JasperExportManager.exportReportToPdfFile(jasperPrint, tempDir+"/"+nombreFichero);
             }
        }                    
         //3-Exportamos el reporte a pdf y lo guardamos en disco
         //     JasperExportManager.exportReportToPdfFile(
         //      jasperPrint,nombreFichero);

        //try {
        //        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + nombreFichero);
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }

     
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
  

    public void enviarMail(BeanCliente beanCliente) throws JRException, NoSuchProviderException, MessagingException, SQLException
    {

            String queryContacto="SELECT * FROM CC_CONTACTOS_CLIENTE WHERE CL_ID="+beanCliente.getCl_id()+" LIMIT 1";
            ResultSet rsContacto = CSDesktop.datos.select(queryContacto);

             while (rsContacto.next())  {
                 String nombre=rsContacto.getString("cc_nombre");
                 String email=rsContacto.getString("cc_email");
             }

            Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            //props.put("mail.smtp.host", "smtp.e.telefonica.net");
            props.put("mail.smtp.host", "10.25.11.32");
            //props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.port", "2525");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.user", "raul.cortes@grupoaldebaran.com");
            //props.put("mail.smtp.password","aldebaran");

            SMTPAuthenticator auth = new SMTPAuthenticator();
            Session mailSession = Session.getDefaultInstance(props, auth);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("Operaciones CarSet <operaciones@carset.es>"));
             /*message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(email));*/
             message.addRecipient(
                Message.RecipientType.CC,
                new InternetAddress("cesardecruz@gmail.com"));
            /*message.setSubject("Resumen Estado Pedido " + mail.getNumPedido());*/
            String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "operaciones@carset.e.telefonica.net";
            String password = "912686953";
           return new PasswordAuthentication(username, password);
        }
    }
}