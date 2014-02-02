package neg; 

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
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
import data.BeanFactura;
import javax.mail.PasswordAuthentication;
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
    
    public void lanzar(ArrayList lista,BeanCliente beanCliente,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin,ArrayList pedidos, int codigo) throws ClassNotFoundException, SQLException, JRException, UnknownHostException
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
            //VARIABLES TRASLADO
            String finalServicio="";
            String labelTraslado="";
            String importeTraslado="";
            double importeTrasladoD = 0;
            //VARIABLES SERVICIO ESPECIAL
            String importeServicio = "";
            double importeServicioD = 0;
            String labelServicioEspecial="";
            //VARIABLES OTROS
            String labelOtros="";
            String importeServicioOtros="";
            //VARIABLES FACTOR DE CORRECCION
            String factorTexto="";
            //VARIABLES IDA VUELTA
            String IdaVuelta="";
            String labelIda="";
            String textoIda="";
            String importeIda="";
            double IdaVueltaD=0;
            double IdaVueltaDF=0;
            //VARIBLES FACTOR
            String labelFactor="";
            double importeFc = 0;
            String importeFactor="";
            String factorTexto2="";
            //VARIABLES SUPLEMENTO
            String labelSuplemento="";
            String ServicioSuplemento="";
            String importeSuplemento="";
            double importeSupD = 0;
            //VARIABLES CAMPA
            String importeEntradaCampaAux="";
            String importeDiasCampaAux="";
            String labelCampa1="";
            String labelCampa2="";
            String finalCampaEntrada = "";
            String finalCampaDias = "";
            double importeCampaEntradaD=0;
            double importeCampaDiasD=0;
            String importeCampaDias="";
            //VARIABLES TOTALES
            double totalAux = 0;
            String importeTotalAuxS="";
            
            BeanFactura beanFactura = (BeanFactura)lista.get(i);

            String numPedido=Long.valueOf(beanFactura.getNumPedido()).toString();
            String finalNum=Utilidades.rellenarCeros(numPedido, 5);
            String fecha=beanFactura.getFecha();
            finalNum=finalNum +"/"+ fecha.substring(2, 4);
            String marca=beanFactura.getMarca();
            String modelo=beanFactura.getModelo();
            String matricula=beanFactura.getMatricula();
            String soporte=beanFactura.getSoporte();
            String factor=beanFactura.getFactor();
            String numCampa=beanFactura.getDiasCampa();
            String servicioEspecial = beanFactura.getServicioEspecial();
            String numCamion=beanFactura.getNumCamion();

            //NUEVA LOGICA DE FACTURACION

            if (numCampa.equals("0"))
            {
                //LINEA DE TRASLADO
                String servicio = beanFactura.getServicio();
                String origen = beanFactura.getProvinciaOrigen();
                String destino = beanFactura.getProvinciaDestino();

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
                // SI TIENE TARIFA ESPECIAL CLIENTE
                if(!beanFactura.getTarifaEsCliente().equals("-1"))
                {
                    importeTraslado = beanFactura.getTarifaEsCliente();
                   
                    // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                    // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";
                    importeTrasladoD = Double.parseDouble(importeTraslado);
                    importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                    //SERVICIO ESPECIAL
                    if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        else
                        {
                            labelOtros=beanFactura.getDescripcion().toUpperCase();
                        }
                    }
                    // FACTOR DE CORRECCION
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                    factorTexto = factorTarifa.get(0).toString();
                }
                // SI NO TIENE TARIFA ESPECIAL
                else
                {
                    //Se comenta de momento para que no busque las tarifas del cliente.
                    //importeTraslado = beanFactura.getTarifa();
                    importeTraslado = "0";

                    // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                    // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";
                    importeTrasladoD = Double.parseDouble(importeTraslado);
                    importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                    if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        // NO PUEDE TENER VALOR OTROS
                        else
                        {
                             if(soporte.equals("Camión completo"))
                            {
                                labelOtros=beanFactura.getDescripcion().toUpperCase();
                            }
                        }                        
                    }
                    //SI TIENE IDA Y VUELTA
                    if(beanFactura.getIdaVuelta().equals("1"))
                    {
                        String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

                        ResultSet rsIv = CSDesktop.datos.select(queryIv);
                        while (rsIv.next())
                        {
                            IdaVuelta = rsIv.getString("sc_ida_vuelta");
                        }

                        labelIda="DESCUENTO";
                        textoIda="IDA-VUELTA ("+IdaVuelta+"%)";

                        IdaVueltaD=Double.parseDouble(IdaVuelta);

                        IdaVueltaDF=(importeTrasladoD*IdaVueltaD)/100;
                        IdaVueltaDF = Utilidades.redondear(IdaVueltaDF, 2);

                        importeIda="- " + String.valueOf(IdaVueltaDF);
                    }
                    //LINEA DE FACTOR DE CORRECCION
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                    factorTexto = factorTarifa.get(0).toString();

                    // SI EL SOPORTE ES GRUA, ES EL UNICO CASO EN EL QUE HAY FACTOR DE CORRECCION.
                    if(soporte.equals("Grúa"))
                    {
                        //SI ES SIN FACTOR O TURISMO, Y EL IMPORTE NO ES VACIO, SE CALCULA EL FACTOR DE CORRECCION.
                        if((!factorTexto.equals("Sin factor") && (!factorTexto.equals("TURISMO")) )&& !importeTraslado.equals(""))
                        {
                            labelFactor = "FACTOR DE CORRECCION";
                            //DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                            double ft = Double.parseDouble(factorTarifa.get(1).toString());
                            importeFc = ((importeTrasladoD * ft) - importeTrasladoD);
                            importeFc=Utilidades.redondear(importeFc, 2);
                            importeFactor = Double.toString(importeFc);
                            factorTexto2=factorTarifa.get(0).toString();
                        }
                    }
                    //SUPLEMENTO
                    if(!beanFactura.getSuplemento().equals("0"))
                    {
                        labelSuplemento="SUPLEMENTO";
                        ServicioSuplemento = beanFactura.getDescripcion();
                        importeSuplemento=beanFactura.getSuplemento();
                        importeSupD = Double.parseDouble(importeSuplemento);
                        importeSupD = Utilidades.redondear(importeSupD, 2);
                    }
                }
            }
            //SI EL CAMPO DIAS CAMPA VIENE CON EL VALOR DISTINTO DE 0
            else
            {
                if(!beanFactura.getTarifaEsCliente().equals("-1"))
                {
                     soporte = "Custodia";
                     labelCampa1 = "Custodia";
                     finalCampaEntrada = "ENTRADA / DÍAS";
                     importeCampaEntradaD=Double.parseDouble(beanFactura.getTarifaEsCliente());
                }
                else
                {
                    String queryCampa = "SELECT sc_entrada_campa,sc_campa FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

                    ResultSet rsCampa = CSDesktop.datos.select(queryCampa);
                        while (rsCampa.next())
                        {
                            importeEntradaCampaAux = rsCampa.getString("sc_entrada_campa");
                            importeDiasCampaAux = rsCampa.getString("sc_campa");
                        }

                    soporte = "Custodia";
                    labelCampa1 = "Custodia";
                    labelCampa2 = "Custodia";
                    finalCampaEntrada = "ENTRADA";
                    finalCampaDias = beanFactura.getDiasCampa()+ " DIAS * " + importeDiasCampaAux;
                
                    importeCampaEntradaD=Double.parseDouble(importeEntradaCampaAux);
                    importeCampaDiasD=(Double.parseDouble(beanFactura.getDiasCampa()))*(Double.parseDouble(importeDiasCampaAux));

                    importeCampaDias=String.valueOf(importeCampaDiasD);
                   
                }
                 //LINEA DE FACTOR DE CORRECCION
                     ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                     factorTexto = factorTarifa.get(0).toString();

                 if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        else
                        {
                            // NO PUEDE TENER VALOR OTROS EXCEPTO QUE SEA CAMION COMPLETO
                            if(soporte.equals("Camión completo"))
                            {
                                labelOtros=beanFactura.getDescripcion().toUpperCase();
                            }
                        }                      
                    }
           }          
            //TOTAL
            totalAux = importeTrasladoD - IdaVueltaDF + importeFc + importeServicioD + importeSupD + importeCampaEntradaD + importeCampaDiasD;
            importeTotalAuxS = Double.toString(totalAux);


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
                            "'"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTrasladoD+"','"+labelFactor+"'," +
                            "'"+factorTexto2+"','"+importeFactor+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+importeSupD+"'," +
                            "'"+labelServicioEspecial+"','"+servicioEspecial+"','"+importeServicioD+"','"+labelOtros+"','"+importeServicioOtros+"','"+labelCampa1+"','"+finalCampaEntrada+"'," +
                            "'"+importeCampaEntradaD+"','"+labelCampa2+"','"+finalCampaDias+"','"+importeCampaDiasD+"','"+labelIda+"','"+textoIda+"','"+IdaVueltaDF+"','"+importeTotalAuxS+"','"+numCamion+"')";

            System.out.println(query);
            boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

            total=total + totalAux;
        } // FIN DEL FOR QUE COMPRUEBA SI EXISTEN MAS PEDIDOS

        // SE PONE LA FECHA DE LA FACTURA EN EL FORMATO ELEGIDO
            String [] tempOrigen = null;
            tempOrigen = fechaFactura.split("\\-");
            String nuevaFechaFactura=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];

             SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
             Date datehora=null;
             Date datehora2=null;
             try
             {
                datehora = sdf1.parse(nuevaFechaFactura);
             } catch (ParseException ex) {
                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
             }
             try
             {
                datehora2 = sdf1.parse("01/09/2012");
             } catch (ParseException ex) {
                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
             }
         //SE CALCULA EL IVA CORRESPONDIENTE
       
         if (datehora.before(datehora2))
         {
            iva = ((total * 18) / 100.0);
            importeIva = Double.toString(iva);
         }
         else
         {
             iva = ((total * 21) / 100.0);
            importeIva = Double.toString(iva);
         }
         //SE SUMA EL RESULTADO Y EL IVA
         totalIva = total + iva;
         double totalIva2=Utilidades.redondear(totalIva, 2);
         importeTotalIva = Double.toString(totalIva2);

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

            // PARA PONER UNA FECHA ENTREGA, DEPENDIENDO DEL PERIODO DE FACTURACION DEL CLIENTE.
            String plazoPago=beanCliente.getPlazoPago();
            int diasPlazo=0;
            if (plazoPago.equals("Especial"))
            {
                diasPlazo=Integer.parseInt(beanCliente.getDiasPlazo());
            }
            else
            {
                diasPlazo=Integer.parseInt(plazoPago.substring(0,2));
            }

            Calendar myGDate=new GregorianCalendar();
            myGDate.setTime(datehora);
            myGDate.add(Calendar.DAY_OF_MONTH, diasPlazo );
            Date fechaActual = myGDate.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fecha2=formatoDeFecha.format(fechaActual);
            // SE INTRODUCE EL VALOR EN EL BEAN
            //mail.setFechaEntrega(fecha2);

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
                if (datehora.before(datehora2))
                    pars.put("IVA","18%");
                else
                   pars.put("IVA","21%");
                pars.put("ImporteIVA", iva);
                pars.put("ImporteTotalIVA", totalIva);
                pars.put("EURO","€");
                pars.put("FechaVFactura",fecha2);

            // SI EL NUMERO ES 0, SIGNIFICA QUE SE HA PULSADO EL BOTON PREVISUALIZAR
            if(numero==0)
            {
                pars.put("NumFactura","PREV");
            }
            else if (numero==1)
            {
                BeanFactura bean = (BeanFactura) lista.get(0);
                pars.put("NumFactura",bean.getAux());
            }
            // SI NO, HAY QUE GENERAR UN NUEVO NUMERO DE FACTURA
            else
            {
                // EL NUMERO DE FACTURA SERIA DEL TIPO 00001/00001/10 (NUMERO FACTURA/NUMERO CLIENTE/AÑO)
                String numFactura=Integer.valueOf(numero).toString();
                finalNumFactura=Utilidades.rellenarCeros(numFactura,5);
                String finalNumCliente=Utilidades.rellenarCeros(beanCliente.getCl_id(), 5);
                finalNumFactura=nuevaFechaFactura.substring(8, 10)+"/"+ finalNumCliente +"/"+ finalNumFactura;
                // SE LE PASA COMO PARAMETRO A LA FACTURA
                pars.put("NumFactura",finalNumFactura);
            }
          
            // LLENAMOS EL REPORTE CON LA INFORMACION Y PARAMETROS NECESARIOS
            jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/Factura.jasper"), pars, con);

            // GUARDAR LA FACTURA EN EL DIRECTORIO TEMPORAL DE WINDOWS
            String finalNumFactura2=finalNumFactura.replace("/","_");
            nombreFichero=beanCliente.getNombre()+"_"+finalNumFactura2+".pdf";
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
                if(numero!=1)
                {
                    String query="INSERT INTO fl_factura_cliente (fl_num,fl_fecha_desde,fl_fecha_hasta,cl_id, " +
                            "fl_fecha_pago, fl_estado, fl_fecha, fl_importe_total,fl_importe,fl_iva,fl_tipo) VALUES (";
                    query = query + "'"+finalNumFactura+"','"+fechaIni+"','"+fechaFin+"','"+clienteID+"','2050-01-01','PTE','"+fechaFactura+"', "+totalIva2+","+total+","+iva+",'Factura')";
                   
                    System.out.println(query);

                     boolean rs = CSDesktop.datos.manipuladorDatos(query);
                     if(rs)
                     {
                            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                            JOptionPane.showMessageDialog(null,errorFields);
                     }
                }
                     BeanCorreoCliente mail = new BeanCorreoCliente();
                     mail.setCliente(beanCliente.getNombre());
                     mail.setClienteID(beanCliente.getCl_id());
                     mail.setFecha(nuevaFechaFactura);
                     mail.setNumPedido(finalNumFactura);
                     mail.setTarifa(importeTotalIva);
                     mail.setMarca(beanCliente.getPlazoPago());
                     mail.setModelo(beanCliente.getDiasPlazo());
                     mail.setMatricula(beanCliente.getFormaPago());
                     
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
                    if(numero!=1)
                    {
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
                                    if(CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num="+ pedidos.get(i)))
                                    {
                                        CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num_unido="+ pedidos.get(i));
                                    }
                                }
                                if(codigo==1)
                                {
                                    CSDesktop.ResultFacturaPedido.dispose();
                                    CSFacturaClientePedido facturaCliente = new CSFacturaClientePedido(beanCliente.getNombre(),fechaIni,fechaFin,false);
                                    CSDesktop.ResultFacturaPedido.toBack();
                                    CSDesktop.FacturaClientePedido.toBack();
                                    CSDesktop.NuevaFactura.toFront();
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

  public void enviarMail(BeanCorreoCliente mail,String nombre,String email,String remitente) throws IOException, MessagingException
  {
 
        try {
            
                    String importeS="";
                    if(mail.getTarifa().contains("-"))
                    {
                        importeS=mail.getTarifa();
                    }
                    else
                    {
                        double importe=Utilidades.redondear(Double.parseDouble(mail.getTarifa()),2);
                        importeS=String.valueOf(importe);
                    }
                   
                    Properties props = new Properties();
                    props.put("mail.transport.protocol","smtp");
                    //props.put("mail.smtp.host", "smtp.e.telefonica.net");
                    props.put("mail.smtp.host", "smtp.office365.com");
                    //props.put("mail.smtp.host", "localhost");
                    //props.put("mail.smtp.starttls.enable", "false");
                    props.put("mail.smtp.starttls.enable", "true");
                    //props.put("mail.smtp.port", "25");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
            

                    SMTPAuthenticator auth = new SMTPAuthenticator();
                    Session mailSession = Session.getDefaultInstance(props, auth);
                    Transport transport = mailSession.getTransport();

                    MimeMessage message = new MimeMessage(mailSession);
                    message.setFrom(new InternetAddress("Operaciones CarSet <operaciones@carset.es>"));
                    message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(email));
                    message.addRecipient(
                        Message.RecipientType.CC,
                        new InternetAddress("operaciones@carset.es"));
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
                    "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: "+remitente+"</font>" +
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

            /* BufferedWriter bw2 = null;
                bw2 = new BufferedWriter(new FileWriter("c://mailFactura.html", false));
                try {
                    bw2.write(htmlText);
                } catch (IOException ex) {
                    Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
                bw2.close();*/

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
    

    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "operaciones@carset.e.telefonica.net";
           //String password = "912686953";
           String password = "CAR11set";
           return new PasswordAuthentication(username, password);
        }
    }

    public void lanzarLibre(ArrayList lista,BeanCliente beanCliente,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin,ArrayList pedidos, int codigo,String IVA, String labelIRPF) throws ClassNotFoundException, SQLException, JRException, UnknownHostException
    {
         double importeIva=0;
         double importeIRPF=0;
         double importeTotal =0;
         double Iniciald=0;
         String importeIvaS="";
         String importeIRPFS="";
         String importeTotalS ="";

        // PRIMERO SE BORRA LA TABLA AUXILIAR PARA GENERAR LAS FACTURAS.
        String queryDel = "DELETE FROM fa_facturas_aux";
        boolean resDel = CSDesktop.datos.manipuladorDatos(queryDel);     
        String cl_id = beanCliente.getCl_id();

        for(int i = 0; i < lista.size(); i++)
        {           
            BeanFactura beanFactura = (BeanFactura)lista.get(i);

            String numPedido=Long.valueOf(beanFactura.getNumPedido()).toString();
            String finalNum=Utilidades.rellenarCeros(numPedido, 5);
            String fecha=beanFactura.getFecha();
            finalNum=finalNum +"/"+ fecha.substring(2, 4);            
            String descripcion=beanFactura.getDescripcion();
            String importeInicial=beanFactura.getTarifaEsCliente();

            double IVAd=Double.parseDouble(IVA);
            double IRPFd=Double.parseDouble(labelIRPF);
            Iniciald=Double.parseDouble(importeInicial);
            importeIva=Utilidades.redondear((Iniciald * IVAd)/100,2);
            importeIRPF=Utilidades.redondear((Iniciald * IRPFd)/100,2);

            importeTotal = Utilidades.redondear((Iniciald + importeIva - importeIRPF), 2);

            importeIvaS=String.valueOf(importeIva);
            importeIRPFS=String.valueOf(importeIRPF);
            importeTotalS=String.valueOf(importeTotal);

            String query = "INSERT INTO fa_facturas_aux (fa_num, fa_fecha, " +                                                                                                                                                                                                                                                                                        
                                                        "fa_servicio_otro,fa_importe_servicio_otro) " +
                                                        "VALUES (";
            query = query + "'"+finalNum+"','"+fecha+"','"+descripcion+"','"+importeInicial+"')";

            System.out.println(query);
            boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

            
        } // FIN DEL FOR QUE COMPRUEBA SI EXISTEN MAS PEDIDOS
                                  
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
            jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/FacturaLibre.jrxml"));

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

             SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
             Date datehora=null;
             try
             {
                datehora = sdf1.parse(nuevaFechaFactura);
             } catch (ParseException ex) {
                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
             }

            // PARA PONER UNA FECHA ENTREGA, DEPENDIENDO DEL PERIODO DE FACTURACION DEL CLIENTE.
            String plazoPago=beanCliente.getPlazoPago();
            int diasPlazo=0;
            if (plazoPago.equals("Especial"))
            {
                diasPlazo=Integer.parseInt(beanCliente.getDiasPlazo());
            }
            else
            {
                diasPlazo=Integer.parseInt(plazoPago.substring(0,2));
            }

            Calendar myGDate=new GregorianCalendar();
            myGDate.setTime(datehora);
            myGDate.add(Calendar.DAY_OF_MONTH, diasPlazo );
            Date fechaActual = myGDate.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fecha2=formatoDeFecha.format(fechaActual);
            // SE INTRODUCE EL VALOR EN EL BEAN
            //mail.setFechaEntrega(fecha2);

            IVA=IVA+" %";
            labelIRPF=labelIRPF+" %";
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
                pars.put("IVA",IVA);
                pars.put("labelIRPF",labelIRPF);
                pars.put("ImporteIVA",importeIva);
                pars.put("ImporteIRPF", importeIRPFS);
                pars.put("ImporteTotal", importeTotal);
                pars.put("ImporteSinIVA",Iniciald);
                pars.put("EURO","€");
                pars.put("FechaVFactura",fecha2);

            // SI EL NUMERO ES 0, SIGNIFICA QUE SE HA PULSADO EL BOTON PREVISUALIZAR
            if(numero==0)
            {
                pars.put("NumFactura","PREV");
            }
            else if (numero==1)
            {
                BeanFactura bean = (BeanFactura) lista.get(0);
                pars.put("NumFactura",bean.getAux());
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
            jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/FacturaLibre.jasper"), pars, con);

            // GUARDAR LA FACTURA EN EL DIRECTORIO TEMPORAL DE WINDOWS
            String finalNumFactura2=finalNumFactura.replace("/","_");
            nombreFichero=beanCliente.getNombre()+"_"+finalNumFactura2+".pdf";
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
                if(numero!=1)
                {
                    String query="INSERT INTO fl_factura_cliente (fl_num,fl_fecha_desde,fl_fecha_hasta,cl_id, " +
                            "fl_fecha_pago, fl_estado, fl_fecha, fl_importe_total,fl_importe,fl_iva,fl_tipo) VALUES (";
                    query = query + "'"+finalNumFactura+"','"+fechaIni+"','"+fechaFin+"','"+clienteID+"','2050-01-01','PTE','"+fechaFactura+"', "+importeTotal+","+Iniciald+","+importeIva+",'Factura')";
                    //String query="INSERT INTO fl_factura_cliente (fl_num,fl_fecha_desde,fl_fecha_hasta,cl_id, " +
                    //        "fl_fecha_pago, fl_pagado) VALUES (";
                    //query = query + "'"+finalNumFactura+"','"+fechaIni+"','"+fechaFin+"','"+clienteID+"','0000-00-00','0')";
                    System.out.println(query);

                     boolean rs = CSDesktop.datos.manipuladorDatos(query);
                     if(rs)
                     {
                            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                            JOptionPane.showMessageDialog(null,errorFields);
                     }
                }
                     BeanCorreoCliente mail = new BeanCorreoCliente();
                     mail.setCliente(beanCliente.getNombre());
                     mail.setClienteID(beanCliente.getCl_id());
                     mail.setFecha(nuevaFechaFactura);
                     mail.setNumPedido(finalNumFactura);
                     mail.setTarifa(String.valueOf(importeTotal));
                     mail.setMarca(beanCliente.getPlazoPago());
                     mail.setModelo(beanCliente.getDiasPlazo());
                     mail.setMatricula(beanCliente.getFormaPago());

                     /*SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
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
                     mail.setFechaEntrega(fecha2);*/

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
                    if(numero!=1)
                    {
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
                                    if(CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num="+ pedidos.get(i)))
                                    {
                                        CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num_unido="+ pedidos.get(i));
                                    }
                                }
                                if(codigo==1)
                                {
                                    CSDesktop.ResultFacturaPedido.dispose();
                                    CSFacturaClientePedido facturaCliente = new CSFacturaClientePedido(beanCliente.getNombre(),fechaIni,fechaFin,true);
                                    CSDesktop.ResultFacturaPedido.toBack();
                                    CSDesktop.FacturaClientePedido.toBack();
                                    CSDesktop.NuevaFactura.toFront();
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

    public void lanzarAbono(ArrayList lista,BeanCliente beanCliente,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin,ArrayList pedidos, int codigo,String observaciones) throws ClassNotFoundException, SQLException, JRException, UnknownHostException
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
        String numFacturaRec="";
        

        for(int i = 0; i < lista.size(); i++)
        {
            //VARIABLES TRASLADO
            String finalServicio="";
            String labelTraslado="";
            String importeTraslado="";
            double importeTrasladoD = 0;
            //VARIABLES SERVICIO ESPECIAL
            String importeServicio = "";
            double importeServicioD = 0;
            String labelServicioEspecial="";
            //VARIABLES OTROS
            String labelOtros="";
            String importeServicioOtros="";
            //VARIABLES FACTOR DE CORRECCION
            String factorTexto="";
            //VARIABLES IDA VUELTA
            String IdaVuelta="";
            String labelIda="";
            String textoIda="";
            String importeIda="";
            double IdaVueltaD=0;
            double IdaVueltaDF=0;
            //VARIBLES FACTOR
            String labelFactor="";
            double importeFc = 0;
            String importeFactor="";
            String factorTexto2="";
            //VARIABLES SUPLEMENTO
            String labelSuplemento="";
            String ServicioSuplemento="";
            String importeSuplemento="";
            double importeSupD = 0;
            //VARIABLES CAMPA
            String importeEntradaCampaAux="";
            String importeDiasCampaAux="";
            String labelCampa1="";
            String labelCampa2="";
            String finalCampaEntrada = "";
            String finalCampaDias = "";
            double importeCampaEntradaD=0;
            double importeCampaDiasD=0;
            String importeCampaDias="";
            //VARIABLES TOTALES
            double totalAux = 0;
            String importeTotalAuxS="";

            BeanFactura beanFactura = (BeanFactura)lista.get(i);

            String numPedido=Long.valueOf(beanFactura.getNumPedido()).toString();
            String finalNum=Utilidades.rellenarCeros(numPedido, 5);
            String fecha=beanFactura.getFecha();
            finalNum=finalNum +"/"+ fecha.substring(2, 4);
            String marca=beanFactura.getMarca();
            String modelo=beanFactura.getModelo();
            String matricula=beanFactura.getMatricula();
            String soporte=beanFactura.getSoporte();
            String factor=beanFactura.getFactor();
            String numCampa=beanFactura.getDiasCampa();
            String servicioEspecial = beanFactura.getServicioEspecial();
            String numCamion=beanFactura.getNumCamion();
            numFacturaRec=beanFactura.getAux();


            //NUEVA LOGICA DE FACTURACION

            if (numCampa.equals("0"))
            {
                //LINEA DE TRASLADO
                String servicio = beanFactura.getServicio();
                String origen = beanFactura.getProvinciaOrigen();
                String destino = beanFactura.getProvinciaDestino();

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
                // SI TIENE TARIFA ESPECIAL CLIENTE
                if(!beanFactura.getTarifaEsCliente().equals("-1"))
                {
                    importeTraslado = beanFactura.getTarifaEsCliente();

                    // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                    // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";
                    importeTrasladoD = Double.parseDouble(importeTraslado);
                    importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                    //SERVICIO ESPECIAL
                    if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        else
                        {
                            labelOtros=beanFactura.getDescripcion().toUpperCase();
                        }
                    }
                    // FACTOR DE CORRECCION
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                    factorTexto = factorTarifa.get(0).toString();
                }
                // SI NO TIENE TARIFA ESPECIAL
                else
                {
                    //Se comenta de momento para que no busque las tarifas del cliente.
                    //importeTraslado = beanFactura.getTarifa();
                    importeTraslado = "0";

                    // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                    // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                    if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";
                    importeTrasladoD = Double.parseDouble(importeTraslado);
                    importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                    if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        // NO PUEDE TENER VALOR OTROS
                        else
                        {
                             if(soporte.equals("Camión completo"))
                            {
                                labelOtros=beanFactura.getDescripcion().toUpperCase();
                            }
                        }
                    }
                    //SI TIENE IDA Y VUELTA
                    if(beanFactura.getIdaVuelta().equals("1"))
                    {
                        String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

                        ResultSet rsIv = CSDesktop.datos.select(queryIv);
                        while (rsIv.next())
                        {
                            IdaVuelta = rsIv.getString("sc_ida_vuelta");
                        }

                        labelIda="DESCUENTO";
                        textoIda="IDA-VUELTA ("+IdaVuelta+"%)";

                        IdaVueltaD=Double.parseDouble(IdaVuelta);

                        IdaVueltaDF=(importeTrasladoD*IdaVueltaD)/100;
                        IdaVueltaDF = Utilidades.redondear(IdaVueltaDF, 2);

                        importeIda="- " + String.valueOf(IdaVueltaDF);
                    }
                    //LINEA DE FACTOR DE CORRECCION
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                    factorTexto = factorTarifa.get(0).toString();

                    // SI EL SOPORTE ES GRUA, ES EL UNICO CASO EN EL QUE HAY FACTOR DE CORRECCION.
                    if(soporte.equals("Grúa"))
                    {
                        //SI ES SIN FACTOR O TURISMO, Y EL IMPORTE NO ES VACIO, SE CALCULA EL FACTOR DE CORRECCION.
                        if((!factorTexto.equals("Sin factor") && (!factorTexto.equals("TURISMO")) )&& !importeTraslado.equals(""))
                        {
                            labelFactor = "FACTOR DE CORRECCION";
                            //DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                            double ft = Double.parseDouble(factorTarifa.get(1).toString());
                            importeFc = ((importeTrasladoD * ft) - importeTrasladoD);
                            importeFc=Utilidades.redondear(importeFc, 2);
                            importeFactor = Double.toString(importeFc);
                            factorTexto2=factorTarifa.get(0).toString();
                        }
                    }
                    //SUPLEMENTO
                    if(!beanFactura.getSuplemento().equals("0"))
                    {
                        labelSuplemento="SUPLEMENTO";
                        ServicioSuplemento = beanFactura.getDescripcion();
                        importeSuplemento=beanFactura.getSuplemento();
                        importeSupD = Double.parseDouble(importeSuplemento);
                        importeSupD = Utilidades.redondear(importeSupD, 2);
                    }
                }

            }
            //SI EL CAMPO DIAS CAMPA VIENE CON EL VALOR DISTINTO DE 0
            else
            {
                if(!beanFactura.getTarifaEsCliente().equals("-1"))
                {
                     soporte = "Custodia";
                     labelCampa1 = "Custodia";
                     finalCampaEntrada = "ENTRADA / DÍAS";
                     importeCampaEntradaD=Double.parseDouble(beanFactura.getTarifaEsCliente());
                }
                else
                {
                    String queryCampa = "SELECT sc_entrada_campa,sc_campa FROM sc_servicios_clientes WHERE cl_id = "+cl_id;

                    ResultSet rsCampa = CSDesktop.datos.select(queryCampa);
                        while (rsCampa.next())
                        {
                            importeEntradaCampaAux = rsCampa.getString("sc_entrada_campa");
                            importeDiasCampaAux = rsCampa.getString("sc_campa");
                        }

                    soporte = "Custodia";
                    labelCampa1 = "Custodia";
                    labelCampa2 = "Custodia";
                    finalCampaEntrada = "ENTRADA";
                    finalCampaDias = beanFactura.getDiasCampa()+ " DIAS * " + importeDiasCampaAux;

                    importeCampaEntradaD=Double.parseDouble(importeEntradaCampaAux);
                    importeCampaDiasD=(Double.parseDouble(beanFactura.getDiasCampa()))*(Double.parseDouble(importeDiasCampaAux));

                    importeCampaDias=String.valueOf(importeCampaDiasD);

                }
                 //LINEA DE FACTOR DE CORRECCION
                     ArrayList factorTarifa = Utilidades.obtenerFactor(factor, cl_id);
                     factorTexto = factorTarifa.get(0).toString();

                 if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
                            if(!importeServicio.equals(""))
                            {
                                importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                            }
                            servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                            labelServicioEspecial="SERVICIO ESPECIAL";
                        }
                        else
                        {
                            // NO PUEDE TENER VALOR OTROS EXCEPTO QUE SEA CAMION COMPLETO
                            if(soporte.equals("Camión completo"))
                            {
                                labelOtros=beanFactura.getDescripcion().toUpperCase();
                            }
                        }
                    }
           }
            //TOTAL
            totalAux = importeTrasladoD - IdaVueltaDF + importeFc + importeServicioD + importeSupD + importeCampaEntradaD + importeCampaDiasD;
            importeTotalAuxS = Double.toString(totalAux);



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
                            "'"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTrasladoD+"','"+labelFactor+"'," +
                            "'"+factorTexto2+"','"+importeFactor+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+importeSupD+"'," +
                            "'"+labelServicioEspecial+"','"+servicioEspecial+"','"+importeServicioD+"','"+labelOtros+"','"+importeServicioOtros+"','"+labelCampa1+"','"+finalCampaEntrada+"'," +
                            "'"+importeCampaEntradaD+"','"+labelCampa2+"','"+finalCampaDias+"','"+importeCampaDiasD+"','"+labelIda+"','"+textoIda+"','"+IdaVueltaDF+"','"+importeTotalAuxS+"','"+numCamion+"')";

            System.out.println(query);
            boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

            total=total + totalAux;
        } // FIN DEL FOR QUE COMPRUEBA SI EXISTEN MAS PEDIDOS

          // SE PONE LA FECHA DE LA FACTURA EN EL FORMATO ELEGIDO
            String [] tempOrigen = null;
            tempOrigen = fechaFactura.split("\\-");
            String nuevaFechaFactura=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];

             SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
             Date datehora=null;
             Date datehora2=null;
             try
             {
                datehora = sdf1.parse(nuevaFechaFactura);
             } catch (ParseException ex) {
                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
             }
             try
             {
                datehora2 = sdf1.parse("01/09/2012");
             } catch (ParseException ex) {
                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
             }
         //SE CALCULA EL IVA CORRESPONDIENTE
         if(datehora.before(datehora2))
         {
            iva = ((total * 18) / 100.0);
            importeIva = Double.toString(iva);
         }
         else
         {
             iva = ((total * 21) / 100.0);
            importeIva = Double.toString(iva);
         }

         //SE SUMA EL RESULTADO Y EL IVA
         totalIva = total + iva;
         double totalIva2=Utilidades.redondear(totalIva, 2);
         importeTotalIva = Double.toString(totalIva2);

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
            jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/Abono.jrxml"));

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

            // PARA PONER UNA FECHA ENTREGA, DEPENDIENDO DEL PERIODO DE FACTURACION DEL CLIENTE.
            String plazoPago=beanCliente.getPlazoPago();
            int diasPlazo=0;
            if (plazoPago.equals("Especial"))
            {
                diasPlazo=Integer.parseInt(beanCliente.getDiasPlazo());
            }
            else
            {
                diasPlazo=Integer.parseInt(plazoPago.substring(0,2));
            }

            Calendar myGDate=new GregorianCalendar();
            myGDate.setTime(datehora);
            myGDate.add(Calendar.DAY_OF_MONTH, diasPlazo );
            Date fechaActual = myGDate.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fecha2=formatoDeFecha.format(fechaActual);
            // SE INTRODUCE EL VALOR EN EL BEAN
            //mail.setFechaEntrega(fecha2);

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
                total=Utilidades.redondear(total, 2);
                pars.put("ImporteTotal",total);
                if(datehora.before(datehora2))
                    pars.put("IVA","18%");
                else
                    pars.put("IVA","21%");
                iva=Utilidades.redondear(iva, 2);
                pars.put("ImporteIVA", iva);
                totalIva=Utilidades.redondear(totalIva, 2);
                pars.put("ImporteTotalIVA", totalIva);
                pars.put("EURO","€");
                pars.put("FechaVFactura",fecha2);
                pars.put("Observaciones",observaciones);
                pars.put("FacturaRec",numFacturaRec);

            // SI EL NUMERO ES 0, SIGNIFICA QUE SE HA PULSADO EL BOTON PREVISUALIZAR
            /*if(numero==0)
            {
                pars.put("NumFactura","PREV");
            }
            else if (numero==1)
            {
                BeanFactura bean = (BeanFactura) lista.get(0);
                pars.put("NumFactura",bean.getAux());
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
            }*/

            // COMO NO HAY PREVISUALIZACION SIEMPRE GENERAMOS UN NUMERO DE FACTURA
                String numFactura=Integer.valueOf(numero).toString();
                finalNumFactura=Utilidades.rellenarCeros(numFactura,5);
                String finalNumCliente=Utilidades.rellenarCeros(beanCliente.getCl_id(), 5);
                finalNumFactura=nuevaFechaFactura.substring(8, 10)+"/"+ finalNumCliente +"/"+ finalNumFactura;
                pars.put("NumFactura","R"+ finalNumFactura);


            // LLENAMOS EL REPORTE CON LA INFORMACION Y PARAMETROS NECESARIOS
            jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/Abono.jasper"), pars, con);

            // GUARDAR LA FACTURA EN EL DIRECTORIO TEMPORAL DE WINDOWS
            String finalNumFactura2=finalNumFactura.replace("/","_");
            nombreFichero=beanCliente.getNombre()+"_R"+finalNumFactura2+".pdf";
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
                if(numero!=1)
                {
                    double totalNeg=totalIva2-2*(totalIva2);
                    double totalN=total-2*(total);
                    double ivaN=iva-2*(iva);
                    String finalNF="R"+finalNumFactura;
                    String query="INSERT INTO fl_factura_cliente (fl_num,fl_fecha_desde,fl_fecha_hasta,cl_id, " +
                            "fl_fecha_pago, fl_estado, fl_fecha, fl_importe_total,fl_importe, fl_iva,fl_tipo) VALUES (";
                    query = query + "'"+finalNF+"','"+fechaIni+"','"+fechaFin+"','"+clienteID+"','2050-01-01','PTE','"+fechaFactura+"', "+totalNeg+",'"+totalN+"', "+ivaN+",'Abono')";

                    System.out.println(query);

                     boolean rs = CSDesktop.datos.manipuladorDatos(query);
                     if(rs)
                     {
                            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                            JOptionPane.showMessageDialog(null,errorFields);
                     }

                     if(CSDesktop.datos.manipuladorDatos("UPDATE fl_factura_cliente SET fl_tipo='Rectificada' WHERE fl_num='"+ numFacturaRec+"'"));
                }
                     String nuevofinal="- "+importeTotalIva;
                     BeanCorreoCliente mail = new BeanCorreoCliente();
                     mail.setCliente(beanCliente.getNombre());
                     mail.setClienteID(beanCliente.getCl_id());
                     mail.setFecha(nuevaFechaFactura);
                     mail.setNumPedido(finalNumFactura);
                     mail.setTarifa(nuevofinal);
                     mail.setMarca(beanCliente.getPlazoPago());
                     mail.setModelo(beanCliente.getDiasPlazo());
                     mail.setMatricula(beanCliente.getFormaPago());

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
                    if(numero!=1)
                    {
                        if(pedidos.size()>0)
                        {
                            int seleccion = JOptionPane.showOptionDialog(
                                CSLanzarFactura.this,
                                "¿Quieres cambiar el estado de los envíos a 'Entregado'?",
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
                                    if(CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Entregado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num="+ pedidos.get(i)))
                                    {
                                        CSDesktop.datos.manipuladorDatos("UPDATE pe_pedidos SET pe_estado='Facturado', pe_num_fa_cl='"+ finalNumFactura +"' WHERE pe_num_unido="+ pedidos.get(i));
                                    }
                                }
                                if(codigo==1)
                                {
                                    CSDesktop.ResultFacturaPedido.dispose();
                                    CSFacturaClientePedido facturaCliente = new CSFacturaClientePedido(beanCliente.getNombre(),fechaIni,fechaFin,false);
                                    CSDesktop.ResultFacturaPedido.toBack();
                                    CSDesktop.FacturaClientePedido.toBack();
                                    CSDesktop.NuevaFactura.toFront();
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


}