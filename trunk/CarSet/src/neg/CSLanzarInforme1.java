package neg;

//import con_reportes.presentacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.UnknownHostException;
import java.sql.Connection;
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
    public void lanzar(ArrayList lista, int clienteID,String cliente,String fechaIni, String fechaFin) throws ClassNotFoundException, SQLException, JRException, ParseException, UnknownHostException
    {
         // BORRAR LA TABLA AUXILIAR DE INFORMES
         String queryDel = "DELETE FROM fi_informe_aux";
         boolean resDel = CSDesktop.datos.manipuladorDatos(queryDel);

         double traslado=0;
         double suplemento = 0;
         double descuento = 0;
         double neto=0;
         double iva = 0;

         // PARA CADA ELEMENTO DE LA LISTA, HAY QUE HACER UNA INSERCCION EN LA TABLA AUXILIAR DE INFORMES.
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
            String importeServicioEs="";
            double importeServicio=0;

            // SE GUARDA EN ELEMENTO DE LA LISTA EN EL BEAN FACTURA (AUNQUE EN ESTE CASO SERVIRA PARA EL INFORME)
            BeanFactura informe = (BeanFactura)lista.get(i);

            // NUMERO DE PEDIDO. CONVERSION A 5 DIGITOS E INCLUSION DEL AÑO
            String numPedido=Long.valueOf(informe.getNumPedido()).toString();
            finalNum=Utilidades.rellenarCeros(numPedido, 5);
            String fecha=informe.getFecha();
            finalNum=finalNum +"/"+ fecha.substring(2, 4);

            //OTROS CAMPOS NECESARIOS
            String matricula = informe.getMatricula();
            String soporte = informe.getSoporte();
            String servicioEspecial = informe.getServicioEspecial();

            String numCamion = informe.getNumCamion();
            String observaciones = informe.getDescripcion();
            String estado = (informe.getEstado() != null) ? informe.getEstado() : "";
            String fechaPrevistaRecogida = informe.getFecha_prevista_recogida();
            String fechaPrevistaEntrega = informe.getFecha_prevista_entrega();
            String fechaRealEntrega = informe.getFecha_real_entrega();

            String [] temp = null;
            String nuevaFechaRealEntrega = "";
            if(fechaRealEntrega != null){
            temp = fechaRealEntrega.split("\\-");
                String anyo=temp[0];
                String mes=temp[1];
                String dia=temp[2];
                nuevaFechaRealEntrega = dia+"/"+mes+"/"+anyo;
            }
            // SE CALCULA EL VALOR DEL SERVICIO ESPECIAL
            String cl_id=String.valueOf(clienteID);
            importeServicioEs=Utilidades.CalcularImporteServicioEspecial(servicioEspecial,cl_id,fecha);
            if(!importeServicioEs.equals(""))
               importeServicio = Double.parseDouble(importeServicioEs);
            if(servicioEspecial.equals("Otros"))
                servicioEspecial="";

            //LINEA DE TRASLADO
            String servicio = informe.getServicio();
            String origen = informe.getProvinciaOrigen();
            String destino = informe.getProvinciaDestino();

            // SI EL ORIGEN Y EL DESTINO SON IGUALES
            if(origen.equals(destino) && !servicio.equals("Selecciona"))
            {
                finalServicio = origen+" - "+ servicio.toUpperCase();
            }
            // SI SON DISTINTOS
            else
            {
                finalServicio = origen+" - "+ destino;
            }

            // SI NO HAY TARIFA ESPECIAL CLIENTE
            /*if(informe.getTarifaEsCliente().equals("-1"))
            {
                importeTraslado = informe.getTarifa();
            }
            // SI HAY TARIFA ESPECIAL CLIENTE
            else
            {*/
                // SI EL CAMPO SERVICIO ESPECIAL NO ES OTROS
                // EL IMPORTE DEL TRASLADO ES LA TARIFA ESPECIAL
                //if (!informe.getServicioEspecial().equals("Otros"))
                        importeTraslado = informe.getTarifaEsCliente();

                // SI EL CAMPO SERVICIO ESPECIAL ES OTROS
              /*  else
                        importeTraslado=informe.getTarifa();
                }*/
                // SE PASA EL IMPORTE DEL TRASLADO A DOUBLE PARA PODER SUMARLO DESPUES
                importeTarifa = Double.parseDouble(importeTraslado);

            //SI TIENE IDA Y VUELTA
/*            if(informe.getIdaVuelta().equals("1"))
            {
                String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+clienteID;

                ResultSet rsIv = CSDesktop.datos.select(queryIv);
                while (rsIv.next())
                {
                    IdaVuelta = rsIv.getString("sc_ida_vuelta");
                }

                IdaVueltaP=Double.parseDouble(IdaVuelta);

                IdaVuelta2=(importeTarifa*IdaVueltaP)/100;
                IdaVuelta2=redondear(IdaVuelta2, 2);

            }*/

            // OBTENER EL FACTOR DE CORRECCION
            Vehiculo = obtenerFactor(informe.getFactor());
            if(Vehiculo.equals("Industriales y Monovolumenes"))
            {
                Vehiculo="Ind. y Monov.";
            }

            // SI EL SOPORTE ES CAMION COMPLETO Y NO ES EL PRIMERO DEL CAMION, SU IMPORTE ES 0.
            if(soporte.equals("Camión completo") && !numCamion.equals("1"))
            {
                importeTarifa=0;
            }

            //TOTAL
            double importeTotalAux = importeTarifa + dSuplemento - IdaVuelta2 + importeServicio;
            importeTotalAux=redondear(importeTotalAux, 2);

            // SE INTRODUCEN LOS VALORES EN LA TABLA AUXILIAR DE INFORMES
            String query = "INSERT INTO fi_informe_aux (fi_num, fi_fecha, fi_soporte, fi_traslado, " +
                                                        "fi_servicio, fi_importe_servicio, fi_vehiculo, fi_matricula, fi_tarifa, fi_suplemento, " +
                                                        "fi_descuento, fi_neto, fi_fecha_prevista_recogida, fi_fecha_prevista_entrega, fi_estado, " +
                                                        "fi_fecha_real_entrega, fi_observaciones" +
                                                        " ) VALUES (";
            query = query + "'"+finalNum+"','"+fecha+"','"+soporte+"','"+finalServicio+"', '"+servicioEspecial+"','"+importeServicioEs+"','"+Vehiculo+"','"+matricula+"'," +
                            "'"+importeTarifa+"','"+dSuplemento+"','"+IdaVuelta2+"','"+importeTotalAux+"','"+fechaPrevistaRecogida+"','"+fechaPrevistaEntrega+"', " +
                            "'"+estado+"','"+nuevaFechaRealEntrega+"','"+observaciones+"')";

            System.out.println(query);
            boolean rs3 = CSDesktop.datos.manipuladorDatos(query);

            // SE VAN REALIZANDO LAS SUMAS INTERMEDIAS PARA CALCULAR LA SUMA TOTAL
            traslado=traslado + importeTarifa;
            suplemento=suplemento + dSuplemento;
            descuento= descuento + IdaVuelta2;
            neto = neto + importeTotalAux;
         } // FIN DEL FOR QUE RECORRE LA LISTA

         // SE REDONDEAN LOS VALORES FINALES
         Double importeTrasladoAux=redondear(traslado, 2);
         Double importeSuplementoAux=redondear(suplemento, 2);
         Double importeDescuentoAux=redondear(descuento, 2);
         Double importeNetoAux=redondear(neto, 2);

         // SE PREPARAN LOS PARAMETROS QUE VA A UTILIZAR EL INFORME
         Map pars = new HashMap();
         pars.put("Cliente", cliente);
         //pars.put("Mes",Utilidades.LiteralMes(mes)+" "+anyo);
         pars.put("FechaInicio", fechaIni);
         pars.put("FechaFin", fechaFin);
         pars.put("SumaTarifa", importeTrasladoAux);
         pars.put("SumaSuplemento", importeSuplementoAux);
         pars.put("SumaDescuento", importeDescuentoAux);
         pars.put("SumaNeto", importeNetoAux);
         

         // CREACION DEL INFORME
         JasperReport jasperReport = null;
         JasperPrint jasperPrint;
         Connection con = null;

         try
         {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(CSLanzarInforme1.class.getName()).log(Level.SEVERE, null, ex);
            }

            DbConnection conexion=new DbConnection();
            con=(Connection) conexion.getConexion();

            // COMPILAMOS EL ARCHIVO XML Y LO CARGAMOS EN MEMORIA
            jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/InformeClienteCompleto1.jrxml"));

            // LLENAMOS EL REPORTE CON LA INFORMACION Y LOS PARAMETROS NECESARIOS
            jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/InformeClienteCompleto1.jasper"), pars, con);

            JRViewerDet1 jrViewer = new JRViewerDet1(jasperPrint);
            CSDesktop.NuevoInformeDetallado1 = new JInternalFrame("Informe Detallado 1", true, false, false, true );
            CSDesktop.NuevoInformeDetallado1.getContentPane().add( jrViewer, BorderLayout.CENTER );
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
    }// FIN DEL METODO LANZAR

    /**
    * Función para recuperar el valor y texto del factor de corrección del pedido seleccionado
    * @param factor
    * @param cliente
    * @return
    * @throws SQLException
    */
    private static String obtenerFactor(String factor)
    {

          int factorInt = Integer.parseInt(factor);
          String descripcion = "";

          switch(factorInt)
          {
              case 0 :
                  descripcion = "";
              break;
             case 1 :
                  descripcion = "TURISMO";
              break;
              case 2 :
                  descripcion = "INDUS/MONOV";
              break;
              case 3 :
                  descripcion = "TODOTERRENO";
              break;
              case 4 :
                  descripcion = "FURGONETAS";
              break;
              case 5 :
                  descripcion = "FURGONES";
              break;
          }


          return descripcion;
    }
    
    // METODO PARA REDONDEAR UN NUMERO CON DECIMALES
    public static double redondear( double numero, int decimales )
    {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }
}