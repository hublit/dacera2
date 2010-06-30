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
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.JasperReport; 
import utils.Utilidades;
import data.*;
import java.text.DecimalFormat;
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
    public void lanzar(ArrayList lista,BeanProveedor beanProveedor,String fechaFactura,int numero, int clienteID,String fechaIni, String fechaFin) throws ClassNotFoundException, SQLException, JRException
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
     String pr_id = beanProveedor.getPr_id();

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
        String importeServicioEsOtros = "";
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
        String importeCampaAux="";
        String importeCampa2Aux="";
        String labelIda="";
        String textoIda="";
        String importeIda="";
        String IdaVuelta="";
        String factorTexto2="";
        String importeTotalAux="";
        String labelOtros="";
        double importeTarifa = 0;
        double importeServicio = 0;
        double importeFc = 0;
        double importeSup = 0;
        double totalAux = 0;       
        double IdaVueltaP=0;
        double IdaVuelta2=0;
        double importeCampa4=0;
        double importeCampa5=0;
        double importeServicioOtros = 0;

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
            }
            else
            {
                if (!otro.getServicioEspecial().equals("Otros"))
                    importeTraslado = otro.getTarifaEsCliente();
                else
                    importeTraslado=otro.getTarifa();
            }
            importeTarifa = Double.parseDouble(importeTraslado);

            //SI TIENE IDA Y VUELTA
            if(otro.getIdaVuelta().equals("1"))
            {
               String queryIv = "SELECT sp_ida_vuelta FROM sp_servicios_proveedores WHERE pr_id = "+pr_id;

               ResultSet rsIv = CSDesktop.datos.select(queryIv);
               while (rsIv.next())
               {
                  IdaVuelta = rsIv.getString("sp_ida_vuelta");
               }

                labelIda="DESCUENTO";
                textoIda="IDA-VUELTA ("+IdaVuelta+"%)";

                IdaVueltaP=Double.parseDouble(IdaVuelta);

                IdaVuelta2=(importeTarifa*IdaVueltaP)/100;

                importeIda="- " + String.valueOf(IdaVuelta2);
            }

             //LINEA DE FACTOR DE CORRECCION
           
                ArrayList factorTarifa = obtenerFactor(factor, pr_id);
                factorTexto = factorTarifa.get(0).toString();

             if(soporte.equals("Grúa"))
             {
                if((!factorTexto.equals("Sin factor") && !factorTexto.equals("TURISMO")) && importeTraslado != null)
                {
                    labelFactor = "FACTOR DE CORRECCION";
                    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                    double ft = Double.parseDouble(factorTarifa.get(1).toString());
                    importeFc = ((importeTarifa * ft) - importeTarifa);
                    double nuevoImporteFactor=redondear(importeFc, 2);
                    importeFactor = Double.toString(nuevoImporteFactor);
                    factorTexto2=factorTarifa.get(0).toString();
                   
                }
             }
            importeServicioEs=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,pr_id,fecha);
            if(!importeServicioEs.equals(""))
             importeServicio = Double.parseDouble(importeServicioEs);
            
            //SUPLEMENTO
            if(!otro.getSuplemento().equals("0"))
            {
                labelSuplemento="SUPLEMENTO";
                ServicioSuplemento = otro.getDescripcion();
                importeSuplemento=otro.getServicioSuplemento();
                importeSup = Integer.parseInt(importeSuplemento);
            }
        }
        else
        {
             String queryCampa = "SELECT sp_entrada_campa, sp_campa FROM sp_servicios_proveedores WHERE pr_id = "+pr_id;

               ResultSet rsCampa =CSDesktop.datos.select(queryCampa);
               while (rsCampa.next())
               {
                  importeCampaAux = rsCampa.getString("sp_entrada_campa");
                  importeCampa2Aux = rsCampa.getString("sp_campa");
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

            ArrayList factorTarifa = obtenerFactor(factor, pr_id);
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
                                                    "fa_importe_servicio_adicional,fa_servicio_otro,fa_importe_servicio_otro, fa_campa,fa_texto_campa, fa_importe_campa, " +
                                                    "fa_campa2, fa_texto_campa2, fa_importe_campa2,fa_label_ida,fa_texto_ida,fa_importe_ida, fa_importe_total, fa_num_en_camion ) " +
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

      String [] tempOrigen = null;
      tempOrigen = fechaFactura.split("\\-");
      String nuevaFechaFactura=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];     

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
            String query="INSERT INTO fr_factura_proveedor (fr_num,fr_fecha_desde,fr_fecha_hasta,cl_id, " +
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
                 JRViewerFactura jrViewer = new JRViewerFactura(jasperPrint,nombreFichero);
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