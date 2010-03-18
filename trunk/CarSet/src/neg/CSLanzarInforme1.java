package neg;

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class CSLanzarInforme1
{
    /**
     * @param args the command line arguments
     */
    //public static void lanzar(String query,String fechaFactura,BeanCliente beanCliente,int flag) throws ClassNotFoundException, SQLException {
    public static void lanzar(ArrayList lista, int clienteID,String cliente,int mes, int anyo) throws ClassNotFoundException, SQLException, JRException, ParseException
    {
     //Lo primero que hacemos es borrar la tabla para generar la factura que queremos
     String queryDel = "DELETE FROM fi_informe_aux";
     boolean resDel = CSDesktop.datos.manipuladorDatos(queryDel);

     double traslado=0;
     double suplemento = 0;
     double descuento = 0;
     double neto=0;
     double iva = 0;

     for(int i = 0; i < lista.size(); i++)
     {
        String finalServicio="";
        String importeTraslado="";
        String IdaVuelta="";
        String Vehiculo="";
        String finalNum="";
        double IdaVuelta2=0;
        double importeTarifa=0;
        double IdaVueltaP=0;
        double dSuplemento=0;
        BeanFactura otro = (BeanFactura)lista.get(i);

        String numPedido=Long.valueOf(otro.getNumPedido()).toString();
        finalNum=Utilidades.rellenarCeros(numPedido, 5);
        String fecha=otro.getFecha();                    
        finalNum=finalNum +"/"+ fecha.substring(2, 4);
        //String marca=otro.getMarca();
        //String modelo=otro.getModelo();
        String matricula=otro.getMatricula();
        String soporte=otro.getSoporte();        
        String servicioEspecial = otro.getServicioEspecial();
        String sSuplemento=otro.getSuplemento();
        dSuplemento = Double.parseDouble(sSuplemento);

       
            //LINEA DE TRASLADO
            String servicio = otro.getServicio();
            String origen = otro.getProvinciaOrigen();
            String destino = otro.getProvinciaDestino();

            if(origen.equals(destino))
            {
                finalServicio=origen+" "+ servicio.toUpperCase();
            }
            else
            {
                finalServicio=origen+" - "+ destino;
            }

            if(otro.getTarifaEsCliente().equals("0"))
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
               String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+clienteID;

               ResultSet rsIv = CSDesktop.datos.select(queryIv);
               while (rsIv.next())
               {
                  IdaVuelta = rsIv.getString("sc_ida_vuelta");
               }              
               
               IdaVueltaP=Double.parseDouble(IdaVuelta);

               IdaVuelta2=(importeTarifa*IdaVueltaP)/100;
            }

            if(otro.getVehiculo().equals("Ninguno"))
            {
                Vehiculo="Turismo";
            }
            else
            {
                Vehiculo=otro.getVehiculo();
            }
         
        //TOTAL
        double importeTotalAux = importeTarifa + dSuplemento - IdaVuelta2;
        //importeTotalAux = Double.toString(totalAux);
        


        String query = "INSERT INTO fi_informe_aux (fi_num, fi_fecha, fi_soporte, fi_traslado, " +
                                                    "fi_servicio,fi_vehiculo, fi_matricula, fi_tarifa, fi_suplemento, " +
                                                    "fi_descuento, fi_neto" +
                                                    " ) VALUES (";
        query = query + "'"+finalNum+"','"+fecha+"','"+soporte+"','"+finalServicio+"', '"+servicioEspecial+"','"+Vehiculo+"','"+matricula+"'," +
                        "'"+importeTarifa+"','"+dSuplemento+"','"+IdaVuelta2+"','"+importeTotalAux+"')";

        System.out.println(query);
        boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

        traslado=traslado + importeTarifa;
        suplemento=suplemento + dSuplemento;
        descuento= descuento + IdaVuelta2;
        neto = neto + importeTotalAux;
        //total=total + totalAux;
     }

            Map pars = new HashMap();
            pars.put("Cliente", cliente);
            pars.put("Mes",Utilidades.LiteralMes(mes)+" "+anyo);
            pars.put("SumaTarifa", traslado);
            pars.put("SumaSuplemento", suplemento);
            pars.put("SumaDescuento", descuento);
            pars.put("SumaNeto", neto);

             JasperReport jasperReport = null;
             JasperPrint jasperPrint;
             Connection con = null;

             try
             {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CSLanzarInforme1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //1-Compilamos el archivo XML y lo cargamos en memoria
                jasperReport = JasperCompileManager.compileReport("c:\\AplicacionCarSet\\reportes\\InformeDet1.jrxml");

                /* JasperCompileManager.compileReportToFile("c:\\prueba.jrxml");*/
                //JasperFillManager.fillReportToFile("c:\\report1.jasper", pars, new JREmptyDataSource());

                //JasperExportManager.exportReportToPdfFile("c:\\report1.jrprint");
                //2-Llenamos el reporte con la informaci�n y par�metros necesarios
                jasperPrint = JasperFillManager.fillReport("c:\\AplicacionCarSet\\reportes\\InformeDet1.jasper", pars, con);

               //3-Exportamos el reporte a pdf y lo guardamos en disco
               //JasperExportManager.exportReportToPdfFile(
               //jasperPrint, "c:/holaMundo.pdf");

                JRViewerDet1 jrViewer = new JRViewerDet1(jasperPrint);
                CSDesktop.NuevoInformeDetallado1 = new JInternalFrame("Informe Detallado 1", true, false, false, true );
                CSDesktop.NuevoInformeDetallado1.getContentPane().add( jrViewer, BorderLayout.CENTER );
                //CSDesktop.NuevaFactura.add(jrViewer);
                CSDesktop.NuevoInformeDetallado1.pack();

                CSDesktop.elEscritorio.add( CSDesktop.NuevoInformeDetallado1 );
                Dimension pantalla = CSDesktop.elEscritorio.getSize();
                CSDesktop.NuevoInformeDetallado1.setSize(pantalla);
                CSDesktop.NuevoInformeDetallado1.setVisible(true);
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
              descripcion = "TURISMO";
          break;
          case 1 :
              campo = "sp_industrial";
              descripcion = "INDUSTRIAL";
          break;
          case 2 :
              campo = "sp_todoterreno";
              descripcion = "TODOTERRENO";
          break;
          case 3 :
              campo = "sp_furgonetas";
              descripcion = "FURGONETAS";
          break;
          case 4 :
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