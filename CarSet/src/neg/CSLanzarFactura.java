package neg; 

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.UnknownHostException;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;

/** 
 * 
 * @author Administrador 
 */ 
public class CSLanzarFactura extends javax.swing.JPanel
{

    JasperPrint jasperPrint;
    String nombreFichero="";
    /** 
     * @param args the command line arguments 
     */ 

    public void lanzar(ArrayList lista,BeanCliente beanCliente,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin,ArrayList pedidos) throws ClassNotFoundException, SQLException, JRException, UnknownHostException
    {
        // PRIMERO SE BORRA LA TABLA AUXILIAR PARA GENERAR LAS FACTURAS.
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

            /*if(soporte.equals("Camion Completo"))
            {

            }*/

            //PRIMERO SE COMPRUEBA SI TIENE LOS DIAS DE CAMPA O NO, PORQUE LA CAMPA ES UN PEDIDO

            //SI NO TIENE DIAS CAMPA
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
                // SI NO HAY TARIFA ESPECIAL CLIENTE, LA TARIFA SE RECOGE DE LA TABLA DE TARIFAS
                if(otro.getTarifaEsCliente().equals("-1"))
                {
                    importeTraslado = otro.getTarifa();

                    // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                    // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";

                }
                // SI HAY TARIFA ESPECIAL CLIENTE PUEDEN OCURRIR DOS CASOS
                else
                {
                    //SI EL SERVICIO ESPECIAL NO ES "OTROS", LA TAFIFA ES LA TARIFA ESPECIAL
                    if (!otro.getServicioEspecial().equals("Otros"))
                        importeTraslado = otro.getTarifaEsCliente();
                    //SI EL SERVICIO ESPECIAL ES "OTROS", LA TARIFA SE COGE DE LA TABLA DE LAS TARIFAS
                    else
                        importeTraslado=otro.getTarifa();
                        // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                        // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
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

                 // SI HAY TARIFA ESPECIAL CLIENTE NO SE APLICA EL FACTOR DE CORRECCION
                 if(otro.getTarifaEsCliente().equals("-1"))
                 {
                    // SI EL SOPORTE ES GRUA, ES EL UNICO CASO EN EL QUE HAY FACTOR DE CORRECCION.
                    if(soporte.equals("Grúa"))
                    {
                        //SI ES SIN FACTOR O TURISMO, Y EL IMPORTE NO ES VACIO, SE CALCULA EL FACTOR DE CORRECCION.
                        if((!factorTexto.equals("Sin factor") && (!factorTexto.equals("TURISMO")) )&& !importeTraslado.equals(""))
                        {
                            labelFactor = "FACTOR DE CORRECCION";
                            DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                            double ft = Double.parseDouble(factorTarifa.get(1).toString());
                            importeFc = ((importeTarifa * ft) - importeTarifa);
                            double nuevoImporteFactor=Utilidades.redondear(importeFc, 2);
                            importeFactor = Double.toString(nuevoImporteFactor);
                            factorTexto2=factorTarifa.get(0).toString();
                        }
                    }
                 }
                //SUPLEMENTO
                if(!otro.getSuplemento().equals("0"))
                {
                    labelSuplemento="SUPLEMENTO";
                    ServicioSuplemento = otro.getDescripcion();
                    importeSuplemento=otro.getSuplemento();
                    importeSup = Integer.parseInt(importeSuplemento);
                }
            }
            //SI EL CAMPO DIAS CAMPA VIENE CON EL VALOR DISTINTO DE 0
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

             //SERVICIO ESPECIAL
             importeServicioEs=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
             if(!importeServicioEs.equals(""))
                importeServicio = Double.parseDouble(importeServicioEs);

            // SI SE HA SELECCIONADO UN SERVICIO ESPECIAL
            if(!otro.getServicioEspecial().equals(""))
            {
                // SI EL SERVICIO ESPECIAL ES OTROS
                if(otro.getServicioEspecial().equals("Otros"))
                {
                   //SE RECOGE EL MENSAJE DEL CAMPO DESCRIPCION Y SU VALOR ES LA TARIFA ESPECIAL CLIENTE
                   labelOtros=otro.getDescripcion().toUpperCase();
                   importeServicioOtros=Integer.parseInt(otro.getTarifaEsCliente());
                   importeServicioEsOtros=otro.getTarifaEsCliente();
                   servicioEspecial="";
                }
                // SI EL SERVICIO ESPECIAL NO ES OTROS
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
        } // FIN DEL FOR QUE COMPRUEBA SI EXISTEN MAS PEDIDOS


         //SE CALCULA EL IVA CORRESPONDIENTE
         iva = ((total * 18) / 100.0);
         importeIva = Double.toString(iva);

         //SE SUMA EL RESULTADO Y EL IVA
         totalIva = total + iva;
         importeTotalIva = Double.toString(totalIva);

         //SE GENERA LA FACTURA EN PDF
         JasperReport jasperReport = null;
         Connection con = null;
         String direccionFiscal="";
         String poblacionFiscal="";
         String provinciaFiscal="";
         String codPostalFiscal="";
         String finalNumFactura="";


         try
         {
            // SE REALIZA LA CONEXION
            DbConnection conexion=new DbConnection();
            con=conexion.getConexion();

            // COMPILAMOS EL ARCHIVO XML Y LO CARGAMOS EN MEMORIA
            jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/Factura.jrxml"));

            //SI VIENE INFORMADA LA DIRECCION FISCAL
            if(beanCliente.getDireccion_fiscal().equals(""))
            {
                direccionFiscal=beanCliente.getDireccion();
                poblacionFiscal=beanCliente.getPoblacion();
                provinciaFiscal=beanCliente.getProvincia();
                codPostalFiscal=beanCliente.getCod_postal();
            }
            // SINO SE PONE LA DIRECCION COMO DIRECCION FISCAL
            else
            {
                direccionFiscal=beanCliente.getDireccion_fiscal();
                poblacionFiscal=beanCliente.getPoblacion_fiscal();
                provinciaFiscal=beanCliente.getProvinciaFiscal();
                codPostalFiscal=beanCliente.getCod_postal_fiscal();
            }

            // SE PONE LA FECHA DE LA FACTURA EN EL FORMATO ELEGIDO
            String [] tempOrigen = null;
            tempOrigen = fechaFactura.split("\\-");
            String nuevaFechaFactura=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];

            // SE INTRODUCEN LOS VALORES DE LOS PARAMETROS DE LA FACTURA
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
                pars.put("IVA","18%");
                pars.put("ImporteIVA", iva);
                pars.put("ImporteTotalIVA", totalIva);
                pars.put("EURO","€");

            // SI EL NUMERO ES 0, SIGNIFICA QUE SE HA PULSADO EL BOTON PREVISUALIZAR
            if(numero==0)
            {
                pars.put("NumFactura","PREV");
            }
            // SI NO, HAY QUE GENERAR UN NUEVO NUMERO DE FACTURA
            else
            {
                // EL NUMERO DE FACTURA SERIA DEL TIPO 00001/00001/10 (NUMERO FACTURA/NUMERO CLIENTE/AÑO)
                String numFactura=Integer.valueOf(numero).toString();
                finalNumFactura=Utilidades.rellenarCeros(numFactura,5);
                String finalNumCliente=Utilidades.rellenarCeros(beanCliente.getCl_id(), 5);
                finalNumFactura=nuevaFechaFactura.substring(8, 10)+"/"+ finalNumCliente +"/"+ finalNumFactura;
                // SE LE PASA COMO PARAMETRO A LA FACUTA
                pars.put("NumFactura",finalNumFactura);
            }

            //ESTOS CAMPOS DE MOMENTO LOS VOY A COMENTAR PORQUE NO SE SI SIRVEN O NO
            /*pars.put("TipoServicio","Probanso");
            pars.put("Servicio","Prueba");
            pars.put("Importe","Dinero");*/

            // LLENAMOS EL REPORTE CON LA INFORMACION Y PARAMETROS NECESARIOS
            jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/Factura.jasper"), pars, con);

            // GUARDAR LA FACTURA EN EL DIRECTORIO TEMPORAL DE WINDOWS
            String finalNumFactura2=finalNumFactura.replace("/","_");
            nombreFichero=finalNumFactura2+"_"+beanCliente.getNombre()+".pdf";
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);
            System.out.println("OS current temporary directory is " + tempDir);

            // SI ES LA PREVISUALIZACION, SE LLAMA A UN VISOR EN EL QUE NO EXISTEN ALGUNOS BOTONES
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
            // SI ES GENERACION, HAY QUE INTRODUCIR UN REGISTRO EN LA TABLA DE FACTURAS CLIENTES
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
                     BeanCorreoCliente mail = new BeanCorreoCliente();
                     mail.setCliente(beanCliente.getNombre());
                     mail.setClienteID(beanCliente.getCl_id());
                     mail.setFecha(nuevaFechaFactura);
                     mail.setNumPedido(finalNumFactura);
                     mail.setTarifa(importeTotalIva);
                     mail.setMarca(beanCliente.getPlazoPago());
                     mail.setModelo(beanCliente.getDiasPlazo());
                     mail.setMatricula(beanCliente.getFormaPago());

                     SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                     Date datehora=null;
                        try {
                            datehora = sdf1.parse(nuevaFechaFactura);
                        } catch (ParseException ex) {
                            Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
                        }

                     // PARA PONER UNA FECHA ENTREGA, DEPENDIENDO DEL PERIODO DE FACTURACION DEL CLIENTE.
                     Calendar myGDate=new GregorianCalendar();
                     myGDate.setTime(datehora);
                     myGDate.add(Calendar.DAY_OF_MONTH, 15);
                     Date fechaActual = myGDate.getTime();
                     SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                     String fecha2=formatoDeFecha.format(fechaActual);
                     // SE INTRODUCE EL VALOR EN EL BEAN
                     mail.setFechaEntrega(fecha2);

                     // SE LLAMA AL VISOR DE PDF´S
                     JRViewerFactura jrViewer = new JRViewerFactura(jasperPrint,nombreFichero,mail,0);
                     CSDesktop.NuevaFactura = new JInternalFrame("Generación Factura Cliente", true, false, false, true );
                     CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
                     CSDesktop.NuevaFactura.pack();
                     CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
                     Dimension pantalla = CSDesktop.elEscritorio.getSize();
                     CSDesktop.NuevaFactura.setSize(pantalla);
                     CSDesktop.NuevaFactura.setVisible(true);

                     // EXPORTAMOS EL REPORTE A PDF Y LO GUARDAMOS EN DISCO
                    JasperExportManager.exportReportToPdfFile(jasperPrint, tempDir+"/"+nombreFichero);


                    //CAMBIAMOS LOS PEDIDOS DE ESTADO, SE PIDE CONFIRMACION
                    if(pedidos.size()>0)
                    {
                        int seleccion = JOptionPane.showOptionDialog(
                            CSLanzarFactura.this,
                            "¿Quieres cambiar el estado de los envíos a 'Facturado'?",
                            "Atención",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "SI", "NO"},
                            "SI");
                        // SI LA RESPUESTA ES POSITIVA, SE CAMBIA EL ESTADO DE LOS PEDIDOS
                        if(seleccion == 0)
                        {
                            for (int i=0;i<pedidos.size();i++)
                            {
                                if(CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado' WHERE pe_num="+ pedidos.get(i)));
                            }
                        }
                    }
                 }
            } // FIN DE GENERACION DE FACTURA
        }
        catch (JRException e)
        {
          e.printStackTrace();
        }
    }// FIN DE LA CLASE LANZAR

  public void enviarMail(BeanCorreoCliente mail,String nombre)
  {
    try {
        try {
            try {
                    //SE RECOGE A QUIEN SE VA A ENVIAR EL MAIL
                    String queryContacto="SELECT * FROM CC_CONTACTOS_CLIENTE WHERE CL_ID="+mail.getClienteID()+" LIMIT 1";
                    ResultSet rsContacto = CSDesktop.datos.select(queryContacto);
                    String nombreContacto="";
                    String email="";

                    double importe=Utilidades.redondear(Double.parseDouble(mail.getTarifa()),2);
                    String importeS=String.valueOf(importe);

                    while (rsContacto.next())
                    {
                        nombreContacto=rsContacto.getString("cc_nombre");
                        email=rsContacto.getString("cc_email");
                    }

                    Properties props = new Properties();
                    props.put("mail.transport.protocol","smtp");
                    props.put("mail.smtp.host", "smtp.e.telefonica.net");
                    //props.put("mail.smtp.host", "localhost");
                    //props.put("mail.smtp.starttls.enable", "false");
                    props.put("mail.smtp.port", "25");
                    props.put("mail.smtp.auth", "true");
            

                    SMTPAuthenticator auth = new SMTPAuthenticator();
                    Session mailSession = Session.getDefaultInstance(props, auth);
                    Transport transport = mailSession.getTransport();

                    MimeMessage message = new MimeMessage(mailSession);
                    message.setFrom(new InternetAddress("Administración CarSet <carset@carset.es>"));
                    /*message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(email));*/
                    message.addRecipient(
                        Message.RecipientType.CC,
                        new InternetAddress("carset@carset.es"));
                    message.setSubject("CarSet - Factura: " + mail.getNumPedido());
                    String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";
           
                    // Create the message part
                    BodyPart messageBodyPart = new MimeBodyPart();

                    String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
                    "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
                    "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
                    "<br><br><table width='700'>" +
                    "<tr><td width='100'><img src=\""+imagen+"\" width='100'></td>" +
                    "<td align='center'><p><font face='Helvetica' size='+1'> ENVIO DE FACTURA </p></font></td></tr>" +
                    "<tr><td colspan='2'><br><br><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Factura:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
                    "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: "+nombreContacto+"</font>" +
                    "<tr><td colspan='2'><br><br><font face='Helvetica'> A continuaci&oacute;n, le adjuntamos la factura correspondiente a los servicios contratados hasta la fecha con nuestra empresa. </font></td></tr>" +
                    "<tr><td colspan='2'><br><br><font face='Helvetica'> En caso de necesitar una copia de esta factura en papel, háganoslo saber y se la remitiremos por correo a la mayor brevedad. </font></td></tr>" +
                    "<tr><td colspan='2'><br><table border='1' width='400'>" +
                    "<tr><td width='200' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;</b></font></td><td width='200'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;</b></font></td>" +
                    "<tr><td width='200'><font face='Helvetica'>&nbsp;Importe (Con IVA)</font></td><td width='200'><font face='Helvetica'>&nbsp;"+importeS+"</font></td>" +
                    "<tr><td width='200'><font face='Helvetica'>&nbsp;Fecha Factura</font></td><td width='200'><font face='Helvetica'>&nbsp;"+mail.getFecha()+"</font></td>" ;
            if(mail.getMarca().equals("Especial"))
            {
                 htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Plazo Pago</font></td><td width='200'><font face='Helvetica'>&nbsp;"+mail.getModelo()+ " días</font></td>" ;
                 SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                 Date datehora=null;
                    try {
                        datehora = sdf1.parse(mail.getFecha());
                    } catch (ParseException ex) {
                        Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }

		 Calendar myGDate=new GregorianCalendar();
                 myGDate.setTime(datehora);
                 myGDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(mail.getModelo()));
		 Date fechaActual = myGDate.getTime();
                 SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                 String fecha2=formatoDeFecha.format(fechaActual);
                
                 htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Fecha Vencimiento</font></td><td width='200'><font face='Helvetica'>&nbsp;"+fecha2+"</font></td>" ;
            }
            else
            {
                htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Plazo Pago</font></td><td width='200'><font face='Helvetica'>&nbsp;"+mail.getMarca()+ "</font></td>" ;
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                 Date datehora=null;
                    try {
                        datehora = sdf1.parse(mail.getFecha());
                    } catch (ParseException ex) {
                        Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }

		 Calendar myGDate=new GregorianCalendar();
                 myGDate.setTime(datehora);
                 myGDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(mail.getMarca().substring(0, 2)));
		 Date fechaActual = myGDate.getTime();
                 SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                 String fecha2=formatoDeFecha.format(fechaActual);

                 htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Fecha Vencimiento</font></td><td width='200'><font face='Helvetica'>&nbsp;"+fecha2+"</font></td>" ;
            }
            
            htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Forma de Pago</font></td><td width='200'><font face='Helvetica'>&nbsp;"+mail.getMatricula()+"</font></td>" ;
            if(mail.getMatricula().equals("Transferencia"))
            {
                htmlText= htmlText + "<tr><td width='200'><font face='Helvetica'>&nbsp;Nº Cuenta CarSet</font></td><td width='200'><font face='Helvetica'>2100 4024 61 2200077238 (La Caixa)</font></td>" ;
            }
            htmlText = htmlText + "<tr><td width='200' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;</b></font></td><td width='200'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;</b></font></td></table>";
            htmlText = htmlText + "<tr><td colspan='2'><br><font face='Helvetica'>No dude en ponerse en contacto con nuestro departamento de administración para cualquier aclaración al respecto. </font></td></tr>" ;
            htmlText = htmlText + "<tr><td colspan='2'><br><font face='Helvetica'> Atentamente, </font></td></tr>" ;
            htmlText = htmlText +"<br><br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'><b> Departamento de Administracion<b></font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' size='+1' color='#088A08'>CarSet</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Tlf: 91 268 69 60</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Movil: 606 33 96 56</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Avda. Puente Cultural, 5 Bl.A - Pl .3 - Of. 2 </font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>www.carset.es</font></td></tr>";
            htmlText = htmlText +"</table></body>";

            // Fill the message
            messageBodyPart.setContent(htmlText, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

             String property = "java.io.tmpdir";

             // Get the temporary directory and print it.
            String tempDir = System.getProperty(property);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(tempDir+"/"+nombre);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(nombre);
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);

            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());

            // Cierre.
            transport.close();

            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail ha sido enviado correctamente.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,mensaje);


        }catch (NoSuchProviderException e)
            {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail no ha podido ser enviado.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                e.printStackTrace();
            }
        }catch (MessagingException e1)
            {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail no ha podido ser enviado.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                e1.printStackTrace();
            }
        }catch (SQLException e2)
            {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail no ha podido ser enviado.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                e2.printStackTrace();
            }

    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "carset@carset.e.telefonica.net";
            String password = "912686953";
           return new PasswordAuthentication(username, password);
        }
    }
}