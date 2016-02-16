package neg;

import data.BeanPedido;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JPanel;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import jxl.read.biff.BiffException;


public class CSAnyadirPedidoArchivoNew extends JPanel
{
    @SuppressWarnings("static-access")
    public CSAnyadirPedidoArchivoNew() throws SQLException, ParseException, IOException, BiffException
    {
        // PRIMERO SE BORRA LA TABLA AUXILIAR PARA GENERAR LAS PEDIDOS.
        String queryDelPa = "DELETE FROM pa_pedidos_aux";
        boolean resDel = CSDesktop.datos.manipuladorDatos(queryDelPa);

        String file = new String("C:/AplicacionCarSet/pedidos_archivo.xls");
        FileInputStream fis = null;
        Integer clientID = null;
        Integer proveedorID = null;
        File inputWorkbook = new File(file);
        Workbook w;
        
        try
        {
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Cargar pedidos desde el Archivo?");
            String pe_num = "0";
            int numUnido = 0;
            int finUnido = 0;

            ArrayList<String> errores = new ArrayList<String>();
            
            if (JOptionPane.OK_OPTION == confirmado)
            {
              WorkbookSettings opciones= new WorkbookSettings();
              opciones.setEncoding("iso-8859-1");
              w = Workbook.getWorkbook(inputWorkbook, opciones);
              // Get the first sheet
              Sheet sheet = w.getSheet(0);
              // Loop over first 10 column and lines

              //System.out.println("columnas: "+sheet.getColumns());
              //System.out.println("filas: "+sheet.getRows());

              for (int i = 0; i < (sheet.getRows()-1); i++)
              {
                String query = "INSERT INTO pa_pedidos_aux (pa_fecha, pa_direccion_origen, pa_poblacion_origen, pa_provincia_origen, " +
                               "pa_cp_origen, pa_nombre_origen, pa_telefono_origen, pa_direccion_destino, pa_poblacion_destino, " +
                               "pa_provincia_destino, pa_cp_destino, pa_nombre_destino, pa_telefono_destino, fc_id, pa_ve_estado, " +
                               "pa_ve_matricula, pa_ve_marca, pa_ve_modelo, pa_soporte, pa_servicio, pa_kms, pa_num_en_camion, pa_dias_campa, " +
                               "pa_descripcion, pa_estado, pa_fecha_origen, pa_fecha_destino, pa_fecha_real_destino, pa_ta_es_cliente, pa_ta_es_proveedor, " +
                               "cl_id, pr_id, pa_observaciones_carset, pa_ob_general, pa_ob_cl_mail, pa_ob_pr_mail, pa_num_unido, pa_fin_unido) " +
                               "VALUES (";

                for (int j = 0; j < sheet.getColumns(); j++)
                {
                    jxl.Cell cell =  sheet.getCell(j, i);
                    CellType type = cell.getType();
                    Integer column = 0;

                   if(cell.getColumn() == 4 || cell.getColumn() == 10){
                        String cp = (cell.getContents().length()<5) ? "0"+cell.getContents() : cell.getContents();
                        //System.out.println("cp: "+cell.getContents() +" / "+cell.getContents().length());
                        query += ""+ cp + ", ";
                   }else if(cell.getColumn() == 3 || cell.getColumn() == 9){
                        query += "'"+ cell.getContents().toUpperCase() + "',";
                   } else if (cell.getColumn() == 13){
                          if(cell.getContents().equals("Turismo")){column = 1;}
                          else if(cell.getContents().equals("Furgoneta Ligera o Monovolumen")){column = 2;}
                          else if(cell.getContents().equals("Todoterreno")){column = 3;}
                          else if(cell.getContents().equals("Furgonetas")){column = 4;}
                          else if(cell.getContents().equals("Furgones")){column = 5;}
                          else if(cell.getContents().equals("SUV")){column = 6;}
                          else if(cell.getContents().equals("Carrozados y Sobredimensionados")){column = 7;}
                          else if(cell.getContents().equals("Moto")){column = 8;}
                          else if(cell.getContents().equals("Especiales")){column = 9;}
                          else{column = 0;}
                          query += ""+column + ", ";

                    }else if(cell.getColumn() == 0 || cell.getColumn() == 26 || cell.getColumn() == 27|| cell.getColumn() == 28){
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                           String sFecha = "";
                           Date hoy = new Date();
                            //System.out.println("fecha: "+cell.getContents());
                           if(!cell.toString().equals("") && !cell.getContents().isEmpty())
                           {
                                String[] fecha = cell.getContents().split("/");
                                sFecha = fecha[2]+"-"+fecha[1]+"-"+fecha[0];
                                //System.out.println("fecha hoy: "+sFecha);
                                query += "'"+sFecha + "', ";
                            }else{
                                sFecha = sdf.format(hoy);
                                query += "NULL, ";
                            }
//                     } else if (cell.getColumn() == 25){
                    
                     }else if(cell.getColumn() == 29 || cell.getColumn() == 30){
                        String importe = cell.getContents().replace(",", ".");
                         query += "'"+ importe + "',";
                     }else if(cell.getColumn() == 19){
                         String servivio = (!cell.getContents().equals("")) ? cell.getContents() : "0";
                         query += "'"+ servivio + "',";
                     }else if(cell.getColumn() == 20){
                         String kms = (!cell.getContents().equals("")) ? cell.getContents() : "0";
                         query += "'"+ kms + "',";
                     }else if(cell.getColumn() == 21){
                         String nCamion = (!cell.getContents().equals("")) ? cell.getContents() : "0";
                         query += "'"+ nCamion + "',";
                     }else if(cell.getColumn() == 22){
                         String diasCampa = (!cell.getContents().equals("")) ? cell.getContents() : "0";
                         query += "'"+ diasCampa + "',";
                     }else if(cell.getColumn() == 23 || cell.getColumn() == 33 || cell.getColumn() == 34){
                         String observaciones = (!cell.getContents().equals("") || cell.getContents() != null) ? cell.getContents().toUpperCase() : "";
                         //System.out.println("Observaciones: "+ j +observaciones);
                         query += "'"+ observaciones + "',";
                     }else if(cell.getColumn() == 31){
                        clientID = (!cell.getContents().equals("")) ? Integer.parseInt(cell.getContents()) : 0;
                         query += "'"+ clientID + "',";
                     }else if(cell.getColumn() == 32){
                        proveedorID = (!cell.getContents().equals("")) ? Integer.parseInt(cell.getContents()) : 0;
                         query += "'"+ proveedorID + "',";
                     }else if (cell.getColumn() == 35){
                         int obClMail = (!cell.getContents().equals("SI")) ? 0 : 1;
                         query += "'"+ obClMail + "',";
                     }else if (cell.getColumn() == 36){
                         int obPrMail = (!cell.getContents().equals("SI")) ? 0 : 1;
                         query += "'"+ obPrMail + "',";
                     }else if (cell.getColumn() == 37){
                         int numeroUnido = (cell.getContents().equals("UNIR PEDIDO") || cell.getContents().equals("FINAL") ) ? 1 : 0;
                         finUnido = (cell.getContents().equals("FINAL")) ? 1 : 0;
                         query += "'"+ numeroUnido + "', '"+ finUnido + "')";
//                       numUnido = (cell.getContents().equals("FINAL")) ? "0" : numUnido;
                     } else {
                        if (type == CellType.LABEL)
                        {
                            //System.out.println("I got a label "+ cell.getContents());
                            query += "'"+cell.getContents() + "', ";
                        }
                        if (type == CellType.NUMBER)
                        {
                            //System.out.println("I got a number "+ Integer.parseInt(cell.getContents()));
                            query += "'"+Integer.parseInt(cell.getContents()) + "', ";
                        }
                     }
                   }
                    //query += "'En Proceso')";
                    System.out.println(query);

                    String sentencia = CSDesktop.datos.manipuladorDatosArchivo(query);
                    
                    if (!sentencia.equals("")){
                        errores.add("Error en el campo " + sentencia + " - Línea: " + (i+1));
                        
                    }
              }
              
              if (errores.size() > 0) {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue><center>"+errores.get(0)+"</center></FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                CSDesktop.NuevoPedidoArchivo.dispose();
                CSDesktop.NuevoPedidoArchivo.pack();
                CSDesktop.NuevoPedidoArchivo.setVisible(false);
              } else {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, mensaje);
                CSDesktop.NuevoPedidoArchivo.dispose();
                CSDesktop.NuevoPedidoArchivo.pack();
               CSDesktop.NuevoPedidoArchivo.setVisible(false);                                 
              }

            }
          } catch (IOException e){
            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue><center>Se ha producido un error al guardar los pedidos en la base de datos.<br> Compruebe el archivo con los pedidos.</center></FONT></HTML>");
            JOptionPane.showMessageDialog(null,mensaje);
            CSDesktop.NuevoPedidoArchivo.dispose();
            CSDesktop.NuevoPedidoArchivo.pack();
            CSDesktop.NuevoPedidoArchivo.setVisible(false);
            e.printStackTrace();
          } catch (NumberFormatException en){
            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue><center>Se ha producido un error al guardar los pedidos en la base de datos.<br> Compruebe el archivo con los pedidos.</center></FONT></HTML>");
            JOptionPane.showMessageDialog(null,mensaje);
            CSDesktop.NuevoPedidoArchivo.dispose();
            CSDesktop.NuevoPedidoArchivo.pack();
            CSDesktop.NuevoPedidoArchivo.setVisible(false);
            en.printStackTrace();
          }finally{
                if (fis != null) {
                 fis.close();
                }
           }
    }
    
    
    private void enviarCorreo(BeanPedido pedido){
                //Se manda el mail de confirmacion
      /*  if (estado.equals("En Proceso") || (estado.equals("Entregado")))
        {
            if(!bPeUnido || finUnido)
            {
            String mails = "\n";
            if (CSDesktop.mailCliente.size() > 0) {
                for (int i = 0; i < CSDesktop.mailCliente.size(); i++) {
                    mails = mails + CSDesktop.mailCliente.get(i);
                    if (i != CSDesktop.mailCliente.size() - 1) {
                        mails = mails + "\n";
                    }
                }
                BeanCorreoCliente mail = new BeanCorreoCliente();
                //Para calcular la fecha
                Date fechaHoy = new Date(System.currentTimeMillis());
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                String fechaHoy2 = formatoDeFecha.format(fechaHoy);
                //Para el numero de pedido
                String numPedido = Utilidades.rellenarCeros(numero, 5);
                String pedido = numPedido + "/" + fecha2.substring(2, 4);
                mail.setCliente(clName);
                mail.setFecha(fechaHoy2);
                mail.setNumPedido(pedido);
                mail.setSoporte(soporte);
                mail.setFechaEntrega(fechaEntrega);
                mail.setFechaRecogida(fechaRecogida);
                mail.setFechaRealEntrega(fechaRealDestino);
                mail.setMarca(marca);
                mail.setModelo(modelo);
                mail.setMatricula(matricula);
                if(finUnidoN == 1){
                    try {
                    BeanPedido pedidoOrigen = getPedidoUnido(jTextPeUnido.getText());
                    numPedido = Utilidades.rellenarCeros(pedidoOrigen.getNum(), 5);
                    pedido = numPedido + "/" + fecha2.substring(2, 4);
                    mail.setNumPedido(pedido);
                    String fechaOrigenUnido = "";
                    if (pedidoOrigen!=null)
                    {
                        SimpleDateFormat formatoOrigenFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaODestino=formatoOrigenFecha.parse(pedidoOrigen.getFechaOrigen());
                        SimpleDateFormat formatoOrigenFecha2 = new SimpleDateFormat("dd-MM-yyyy");
                        fechaOrigenUnido = formatoOrigenFecha2.format(fechaODestino);
                    }
                    mail.setFechaRecogida(fechaOrigenUnido);
                    mail.setDireccionOrigen(pedidoOrigen.getDireccionOrigen());
                    mail.setPoblacionOrigen(pedidoOrigen.getPoblacionOrigen());
                    mail.setProvinciaOrigen(pedidoOrigen.getProvinciaOrigen());
                    mail.setNombreOrigen(pedidoOrigen.getNombreOrigen());
                    mail.setTelefonoOrigen(pedidoOrigen.getTelefonoOrigen());
                    mail.setDescripcion(pedidoOrigen.getObservacionesCl());
                    mail.setTarifaEspecialCliente(pedidoOrigen.getTarifa());
                    mail.setKms(pedidoOrigen.getKms());
                    mail.setSoporte(pedidoOrigen.getSoporte());
                    mail.setPeUnido(true);
                } catch (ParseException ex) {
                    Logger.getLogger(CSEditarPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                        Logger.getLogger(CSAnyadirPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    mail.setDireccionOrigen(direccionOrigen);
                    mail.setPoblacionOrigen(poblacionOrigen);
                    mail.setProvinciaOrigen(provinciaOrigen);
                    mail.setNombreOrigen(nombreOrigen);
                    mail.setTelefonoOrigen(telefonoOrigen);
                    mail.setDescripcion(descripcion);
                    mail.setTarifaEspecialCliente(tarifaCliente);
                    mail.setKms(kilometros);
                    mail.setSoporte(soporte);
                    mail.setPeUnido(false);
                }
                mail.setDireccionDestino(direccionDestino);
                mail.setPoblacionDestino(poblacionDestino);
                mail.setProvinciaDestino(provinciaDestino);
                mail.setNombreDestino(nombreDestino);
                mail.setTelefonoDestino(telefonoDestino);
                mail.setNumero(numero);
                mail.setObsClInmail(ob_cl_mail);
                mail.setTarifakmCliente(tarifaKmCl);
                Cliente client = new Cliente();
                mail.setClienteID(String.valueOf(client.getClienteID(clName)));
                if (estado.equals("En Proceso")) {
                    for (int i = 0; i < CSDesktop.mailCliente.size(); i++) {
                        CSEnviarMailProcesoNew.main(mail, CSDesktop.mailCliente.get(i).toString(), CSDesktop.nombreCliente.get(i).toString());
                    }
                } else if (estado.equals("Entregado")) {
                    for (int i = 0; i < CSDesktop.mailCliente.size(); i++) {
                        CSEnviarMailEntregadoNew.main(mail, CSDesktop.mailCliente.get(i).toString(), CSDesktop.nombreCliente.get(i).toString());
                    }
                }
                
              }
            }
            if (estado.equals("En Proceso")) {
                String mailsP = "\n";
                if (CSDesktop.mailProveedor.size() > 0) {
                    for (int i = 0; i < CSDesktop.mailProveedor.size(); i++) {
                        mailsP = mailsP + CSDesktop.mailProveedor.get(i);
                        if (i != CSDesktop.mailProveedor.size() - 1) {
                            mailsP = mailsP + "\n";
                        }
                    }
                    int seleccion2 = JOptionPane.showOptionDialog(CSEditarPedidoNew.this, "¿Quieres mandar un mail al proveedor " + mailsP + "?", "Atención", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (seleccion2 == 0) {
                        BeanCorreoCliente mail = new BeanCorreoCliente();
                        //Para calcular la fecha
                        Date fechaHoy = new Date(System.currentTimeMillis());
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                        String fechaHoy2 = formatoDeFecha.format(fechaHoy);
                        //Para el numero de pedido
                        String numPedido = Utilidades.rellenarCeros(numero, 5);
                        String pedido = numPedido + "/" + fecha2.substring(2, 4);
                        mail.setCliente(proveedor);
                        mail.setFecha(fechaHoy2);
                        mail.setNumPedido(pedido);
                        mail.setSoporte(soporte);
                        mail.setFechaEntrega(fechaEntrega);
                        mail.setFechaRecogida(fechaRecogida);
                        mail.setMarca(marca);
                        mail.setModelo(modelo);
                        mail.setMatricula(matricula);
                        mail.setDireccionOrigen(direccionOrigen);
                        mail.setPoblacionOrigen(poblacionOrigen);
                        mail.setProvinciaOrigen(provinciaOrigen);
                        mail.setNombreOrigen(nombreOrigen);
                        mail.setTelefonoOrigen(telefonoOrigen);
                        mail.setDireccionDestino(direccionDestino);
                        mail.setPoblacionDestino(poblacionDestino);
                        mail.setProvinciaDestino(provinciaDestino);
                        mail.setNombreDestino(nombreDestino);
                        mail.setTelefonoDestino(telefonoDestino);
                        mail.setTarifaEspecialProveedor(tarifaProveedor);
                        mail.setNumero(numero);
                        mail.setObservaciones(observaciones);
                        mail.setObsPrInmail(ob_pr_mail);
                        Proveedor proveed = new Proveedor();
                        mail.setClienteID(String.valueOf(proveed.getProveedorID(proveedor)));
                        for (int i = 0; i < CSDesktop.mailProveedor.size(); i++) {
                            CSEnviarMailProveedorNew.main(mail, CSDesktop.mailProveedor.get(i).toString(), CSDesktop.nombreProveedor.get(i).toString());
                        }
                    }
                }
            }
        }*/
    }
}