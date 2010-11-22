package neg; 

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.UnknownHostException;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import utils.Utilidades;
import data.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** 
 * 
 * @author Administrador 
 */ 
public class CSLanzarFacturaProveedor
{ 
    /** 
     * @param args the command line arguments 
     */ 
    //public static void lanzar(String query,String fechaFactura,BeanCliente beanCliente,int flag) throws ClassNotFoundException, SQLException {
    public void lanzar(ArrayList lista,BeanProveedor beanProveedor,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin) throws ClassNotFoundException, SQLException, JRException, UnknownHostException
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
        String pr_id = beanProveedor.getPr_id();

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
            String labelCampa="";
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
                if(!beanFactura.getTarifaEsProveedor().equals("-1"))
                {
                    importeTraslado = beanFactura.getTarifaEsProveedor();

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
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,pr_id,fecha);
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
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, pr_id);
                    factorTexto = factorTarifa.get(0).toString();
                }
                // SI NO TIENE TARIFA ESPECIAL
                else
                {
                    importeTraslado = beanFactura.getTarifa();
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
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,pr_id,fecha);
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
                        String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+pr_id;

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
                    ArrayList factorTarifa = Utilidades.obtenerFactor(factor, pr_id);
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
                 String queryCampa = "SELECT sc_entrada_campa,sc_campa FROM sc_servicios_clientes WHERE cl_id = "+pr_id;

                 ResultSet rsCampa = CSDesktop.datos.select(queryCampa);
                    while (rsCampa.next())
                    {
                        importeEntradaCampaAux = rsCampa.getString("sc_entrada_campa");
                        importeDiasCampaAux = rsCampa.getString("sc_campa");
                    }

                soporte = "CAMPA";
                labelCampa = "CAMPA";
                finalCampaEntrada = "ENTRADA";
                finalCampaDias = beanFactura.getDiasCampa()+ " DIAS * " + importeDiasCampaAux;

                importeCampaEntradaD=Double.parseDouble(importeEntradaCampaAux);
                importeCampaDiasD=(Double.parseDouble(beanFactura.getDiasCampa()))*(Double.parseDouble(importeDiasCampaAux));

                importeCampaDias=String.valueOf(importeCampaDiasD);

                //LINEA DE FACTOR DE CORRECCION
                ArrayList factorTarifa = Utilidades.obtenerFactor(factor, pr_id);
                factorTexto = factorTarifa.get(0).toString();

                 if(!beanFactura.getServicioEspecial().equals(""))
                    {
                        if(!beanFactura.getServicioEspecial().equals("Otros"))
                        {
                            importeServicio=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,pr_id,fecha);
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
                        /*else
                        {
                            labelOtros=otro.getDescripcion().toUpperCase();
                        }*/

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
                                                    "fa_importe_servicio_adicional,fa_servicio_otro,fa_importe_servicio_otro, fa_campa,fa_texto_campa, fa_importe_campa, " +
                                                    "fa_campa2, fa_texto_campa2, fa_importe_campa2,fa_label_ida,fa_texto_ida,fa_importe_ida, fa_importe_total, fa_num_en_camion ) " +
                                                    "VALUES (";
        query = query + "'"+finalNum+"','"+fecha+"','"+marca+"','"+modelo+"','"+matricula+"','"+factorTexto+"'," +
                            "'"+soporte+"','"+labelTraslado+"','"+finalServicio+"','"+importeTrasladoD+"','"+labelFactor+"'," +
                            "'"+factorTexto2+"','"+importeFactor+"','"+labelSuplemento+"','"+ServicioSuplemento+"','"+importeSupD+"'," +
                            "'"+labelServicioEspecial+"','"+servicioEspecial+"','"+importeServicioD+"','"+labelOtros+"','"+importeServicioOtros+"','"+labelCampa+"','"+finalCampaEntrada+"'," +
                            "'"+importeEntradaCampaAux+"','"+labelCampa+"','"+finalCampaDias+"','"+importeCampaDiasD+"','"+labelIda+"','"+textoIda+"','"+IdaVueltaDF+"','"+importeTotalAuxS+"','"+numCamion+"')";

        System.out.println(query);
        boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

        total=total + totalAux;
     }

    //IVA
        iva = ((total * 18) / 100.0);
        importeIva = Double.toString(iva);

        //TOTAL IVA
        totalIva = total + iva;
        importeTotalIva = Double.toString(totalIva);

    JasperReport jasperReport = null;
    JasperPrint jasperPrint; 
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
       jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/Albaran.jrxml"));

       /* JasperCompileManager.compileReportToFile("c:\\prueba.jrxml");*/

      if(beanProveedor.getDireccion_fiscal().equals(""))
      {
          direccionFiscal=beanProveedor.getDireccion();
          poblacionFiscal=beanProveedor.getPoblacion();
          provinciaFiscal=beanProveedor.getProvincia();
          codPostalFiscal=beanProveedor.getCod_postal();
      }
      else
      {
          direccionFiscal=beanProveedor.getDireccion_fiscal();
          poblacionFiscal=beanProveedor.getPoblacion_fiscal();
          provinciaFiscal=beanProveedor.getProvinciaFiscal();
          codPostalFiscal=beanProveedor.getCod_postal_fiscal();
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
            String plazoPago=beanProveedor.getPlazoPago();
            int diasPlazo=0;
            if (plazoPago.equals("Especial"))
            {
                diasPlazo=Integer.parseInt(beanProveedor.getDiasPlazo());
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

      Map pars = new HashMap();
        pars.put("FechaFactura", nuevaFechaFactura);
        pars.put("NombreCliente",beanProveedor.getNombre());
        pars.put("DireccionFiscal", direccionFiscal);
        pars.put("Direccion", beanProveedor.getDireccion());
        pars.put("PoblacionFiscal", poblacionFiscal);
        pars.put("Poblacion", beanProveedor.getPoblacion());
        pars.put("ProvinciaFiscal", provinciaFiscal);
        pars.put("Provincia", beanProveedor.getProvincia());
        pars.put("CodPostalFiscal", codPostalFiscal);
        pars.put("CodPostal", beanProveedor.getCod_postal());
        pars.put("CIF", beanProveedor.getDNI_CIF());
        pars.put("Query","SELECT * FROM pe_pedidos;");
        pars.put("Blanco","");
        pars.put("Factor","Turismo");
        pars.put("ImporteTotal",total);
        pars.put("IVA","18%");
        pars.put("ImporteIVA", iva);
        pars.put("ImporteTotalIVA", totalIva);
        pars.put("EURO","€");
         pars.put("FechaVFactura",fecha2);

        if(numero==0)
        {
            pars.put("NumFactura","PREV");
        }
        else
        {
            String numFactura=Integer.valueOf(numero).toString();
            finalNumFactura=Utilidades.rellenarCeros(numFactura,5);
            String finalNumCliente=Utilidades.rellenarCeros(beanProveedor.getPr_id(), 5);
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
        jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/Albaran.jasper"), pars, con);
       
       //3-Exportamos el reporte a pdf y lo guardamos en disco 
      //JasperExportManager.exportReportToPdfFile(
      //    jasperPrint, "c:/holaMundo.pdf");

        String finalNumFactura2=finalNumFactura.replace("/","_");
        String nombreFichero=finalNumFactura2+"_"+beanProveedor.getNombre()+".pdf";

        if(numero==0)
        {
             JRViewerSin jrViewer = new JRViewerSin(jasperPrint);
             CSDesktop.NuevaFactura = new JInternalFrame("Previsualización Factura Proveedor", true, false, false, true );
             CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
             CSDesktop.NuevaFactura.pack();
             CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
             Dimension pantalla = CSDesktop.elEscritorio.getSize();
             CSDesktop.NuevaFactura.setSize(pantalla);
             CSDesktop.NuevaFactura.setVisible(true);
        }
        else
        {
            String query="INSERT INTO fr_factura_proveedor (fr_num,fr_fecha_desde,fr_fecha_hasta,pr_id, " +
                    "fr_fecha_pago, fr_pagado) VALUES (";
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
                 BeanCorreoCliente mail=new BeanCorreoCliente();
                 JRViewerFactura jrViewer = new JRViewerFactura(jasperPrint,nombreFichero,mail,1);
                 CSDesktop.NuevaFactura = new JInternalFrame("Previsualización Albaran Proveedor", true, false, false, true );
                 CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
                 CSDesktop.NuevaFactura.pack();
                 CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
                 Dimension pantalla = CSDesktop.elEscritorio.getSize();
                 CSDesktop.NuevaFactura.setSize(pantalla);
                 CSDesktop.NuevaFactura.setVisible(true);
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
  private static ArrayList obtenerFactor(String factor, String proveedor) throws SQLException
  {
      ArrayList factorSel = new ArrayList();
      int factorInt = Integer.parseInt(factor);
      String campo = "";
      String descripcion = "";

      switch(factorInt)
      {
          case 0 :
              descripcion = "Sin factor";
          break;
         case 1 :
              descripcion = "TURISMO";
          break;
          case 2 :
              campo = "sp_industrial";
              descripcion = "INDUSTRIAL";
          break;
          case 3 :
              campo = "sp_todoterreno";
              descripcion = "TODOTERRENO";
          break;
          case 4 :
              campo = "sp_furgonetas";
              descripcion = "FURGONETAS";
          break;
          case 5 :
              campo = "sp_furgones";
              descripcion = "FURGONES";
          break;
      }
       factorSel.add(descripcion);
      if (!campo.equals(""))
      {
          String queryFactor = "SELECT "+campo+" FROM sp_servicios_proveedores WHERE pr_id="+proveedor;
          ResultSet rs3 = CSDesktop.datos.select(queryFactor);

          while(rs3.next())
          {
              factorSel.add(rs3.getDouble(campo));
             
          }
      }
     
      return factorSel;
  }
  public static double redondear( double numero, int decimales ) {
    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
  }

}