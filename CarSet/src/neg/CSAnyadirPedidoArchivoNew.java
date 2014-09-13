package neg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String file = new String("C:/AplicacionCarSet/pedidosInterpartner NUEVO.xls");
        FileInputStream fis = null;
        Integer clientID = null;
        Integer proveedorID = null;
        File inputWorkbook = new File(file);
        Workbook w;

        try
        {
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Cargar pedidos desde el Archivo?");
            String pe_num = "";
            String numUnido = "0";
            if (JOptionPane.OK_OPTION == confirmado)
            {
              WorkbookSettings opciones= new WorkbookSettings();
              opciones.setEncoding("iso-8859-1");
              w = Workbook.getWorkbook(inputWorkbook, opciones);
              // Get the first sheet
              Sheet sheet = w.getSheet(0);
              // Loop over first 10 column and lines

              System.out.println("columnas: "+sheet.getColumns());
              System.out.println("filas: "+sheet.getRows());

              for (int i = 0; i < sheet.getRows(); i++)
              {
                String query = "INSERT INTO pe_pedidos (pe_fecha, pe_direccion_origen, pe_poblacion_origen, pe_provincia_origen, " +
                               "pe_cp_origen, pe_nombre_origen, pe_telefono_origen, pe_direccion_destino, pe_poblacion_destino, " +
                               "pe_provincia_destino, pe_cp_destino, pe_nombre_destino, pe_telefono_destino, fc_id, pe_ve_estado, " +
                               "pe_ve_matricula, pe_ve_marca, pe_ve_modelo, pe_soporte, pe_servicio, pe_kms, pe_num_en_camion, " +
                               "pe_dias_campa, pe_descripcion, pe_fecha_origen, pe_fecha_destino, pe_ta_es_cliente, pe_ta_es_proveedor, " +
                               "pe_observaciones_carset, pe_ob_general, pe_ob_cl_mail, pe_ob_pr_mail, pe_num_unido, pe_fin_unido, pe_estado) " +
                               "VALUES (";

                for (int j = 0; j < sheet.getColumns(); j++){
                    jxl.Cell cell =  sheet.getCell(j, i);
                    CellType type = cell.getType();
                    Integer column = 0;

                   if(cell.getColumn() == 4 || cell.getColumn() == 10){
                        String cp = (cell.getContents().length()<5) ? "0"+cell.getContents() : cell.getContents();
                        System.out.println("cp: "+cell.getContents() +" / "+cell.getContents().length());
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

                    }else if(cell.getColumn() == 0 || cell.getColumn() == 25 || cell.getColumn() == 26){
                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                           String sFecha = "";
                           Date hoy = new Date();
                            System.out.println("fecha: "+cell.getContents());
                           if(!cell.toString().equals(""))
                           {
                                String[] fecha = cell.getContents().split("/");
                                sFecha = "20"+fecha[2]+"-"+fecha[1]+"-"+fecha[0];
                               
                                System.out.println("fecha hoy: "+sFecha);
                                query += "'"+sFecha + "', ";
                            }else{
                                sFecha = sdf.format(hoy);
                                query += "'', ";
                            }
                    /* }else if(cell.getColumn() == 17){
                        String especial = (cell.getContents().length() >0) ? "Otros" : "";
                        query += "'"+ cell.getContents().toUpperCase() + "', '"+ especial + "',";*/
                     }else if(cell.getColumn() == 27 || cell.getColumn() == 28){
                        String importe = cell.getContents().replace(",", ".");
                         query += "'"+ importe + "',";
                     }else if(cell.getColumn() == 29){
                        clientID = Integer.parseInt(cell.getContents());
                     }else if(cell.getColumn() == 30){
                        proveedorID = Integer.parseInt(cell.getContents());
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
                     }else if(cell.getColumn() == 23 || cell.getColumn() == 31 || cell.getColumn() == 32){
                         String observaciones = (!cell.getContents().equals("")) ? cell.getContents().toUpperCase() : "";
                         query += "'"+ observaciones + "',";
                     }else if (cell.getColumn() == 33){
                         int obClMail = (!cell.getContents().equals("SI")) ? 0 : 1;
                         query += "'"+ obClMail + "',";
                     }else if (cell.getColumn() == 34){
                         int obPrMail = (!cell.getContents().equals("SI")) ? 0 : 1;
                         query += "'"+ obPrMail + "',";
                     }else if (cell.getColumn() == 35){
                         numUnido = (cell.getContents().equals("UNIR PEDIDO") && numUnido.equals("0")) ? pe_num : numUnido;
                         int finUnido = (cell.getContents().equals("FINAL")) ? 1 : 0;
                         query += "'"+ numUnido + "', '"+ finUnido + "', ";
                         numUnido = (cell.getContents().equals("FINAL")) ? "0" : numUnido;
                     } else {
                        if (type == CellType.LABEL)
                        {
                            System.out.println("I got a label "+ cell.getContents());
                            query += "'"+cell.getContents() + "', ";
                        }
                        if (type == CellType.NUMBER)
                        {
                            System.out.println("I got a number "+ Integer.parseInt(cell.getContents()));
                            query += "'"+Integer.parseInt(cell.getContents()) + "', ";
                        }
                     }
                   }
                    query += "'En Proceso')";
                    System.out.println(query);

                    boolean rs = CSDesktop.datos.manipuladorDatos(query);

                    if(!rs)
                    {
                        query = "select distinct last_insert_id() from pe_pedidos";
                        pe_num="";
                        ResultSet rs2 = CSDesktop.datos.select(query);
                        try
                        {
                           if (rs2.first())
                            {
                                pe_num=Integer.valueOf(rs2.getInt("last_insert_id()")).toString();
                                //System.out.println(rs2.getInt("last_insert_id()"));
                                String queryCon = "INSERT INTO pc_pedidos_clientes (pe_num,cl_id) "+
                                                  "VALUES ('"+pe_num+"', '"+clientID+"')";
                                System.out.println(queryCon);
                                boolean rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                                if(rsCon)
                                {
                                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                    JOptionPane.showMessageDialog(null,errorFields);
                                }
                                queryCon = "INSERT INTO pp_pedidos_proveedores (pe_num,pr_id) "+
                                                  "VALUES ('"+pe_num+"','"+proveedorID+"')";
                                System.out.println(queryCon);
                                rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                                if(rsCon)
                                {
                                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                    JOptionPane.showMessageDialog(null,errorFields);
                                }
                            }
                        } catch (SQLException ex) {
                            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                            JOptionPane.showMessageDialog(null,errorFields);
                            ex.printStackTrace();
                        }
                    }
              }
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                CSDesktop.NuevoPedidoArchivo.dispose();
                CSDesktop.NuevoPedidoArchivo.pack();
                CSDesktop.NuevoPedidoArchivo.setVisible(false);
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

}