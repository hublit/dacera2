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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

//import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class CSAnyadirPedidoArchivo extends JPanel
{

    @SuppressWarnings("static-access")
    public CSAnyadirPedidoArchivo() throws SQLException, ParseException, IOException, BiffException
    {
        String file = new String("C:/AplicacionCarSet/pedidosInterpartner.xls");

        List sheetData = new ArrayList();
        FileInputStream fis = null;
        Integer clientID = null;
        Integer proveedorID = null;
        File inputWorkbook = new File(file);
        Workbook w;

        try
        {
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Cargar pedidos desde el Archivo?");

            if (JOptionPane.OK_OPTION == confirmado)
            {
              w = Workbook.getWorkbook(inputWorkbook);
              // Get the first sheet
              Sheet sheet = w.getSheet(0);
              // Loop over first 10 column and lines

              System.out.println("columnas: "+sheet.getColumns());
              System.out.println("filas: "+sheet.getRows());


              for (int i = 0; i < sheet.getRows(); i++)
              {
                String query = "INSERT INTO pe_pedidos (pe_fecha, " +
                "pe_direccion_origen, pe_poblacion_origen, pe_provincia_origen, pe_servicio_origen, " +
                "pe_cp_origen, pe_nombre_origen, pe_telefono_origen, pe_direccion_destino, pe_poblacion_destino, " +
                "pe_provincia_destino, pe_servicio_destino, pe_cp_destino, pe_nombre_destino, pe_telefono_destino, " +
                "fc_id, pe_ve_matricula, pe_ve_marca, pe_ve_modelo, pe_descripcion, pe_servicio_especial, " +
                "pe_fecha_origen, pe_fecha_destino, pe_ta_es_cliente, pe_ta_es_proveedor, " +
                "pe_tipo_origen, pe_tipo_destino, pe_servicio, pe_soporte, " +
                "pe_fecha_real_destino, pe_estado) VALUES (";

                for (int j = 0; j < sheet.getColumns(); j++)
                {
                    jxl.Cell cell =  sheet.getCell(j, i);
                    CellType type = cell.getType();

                    Integer column = 0;
                   if(cell.getColumn() == 4 || cell.getColumn() == 10){
                        System.out.println("cp: "+cell.getContents() +" / "+cell.getContents().length());
                        String cp = (cell.getContents().length() <5) ? "0"+cell.getContents() : cell.getContents();
                        query += ""+ cp + ", ";
                   }else if(cell.getColumn() == 3 || cell.getColumn() == 9){
                        query += "'"+ cell.getContents().toUpperCase() + "', '"+ cell.getContents().toUpperCase() + "',";
                    } else if (cell.getColumn() == 13){
                          if(cell.getContents().equals("Turismo")){column = 1;}
                          else if(cell.getContents().equals("Industriales y Monovolumen")){column = 2;}
                          else if(cell.getContents().equals("Todoterreno")){column = 3;}
                          else if(cell.getContents().equals("Furgonetas")){column = 4;}
                          else if(cell.getContents().equals("Furgones")){column = 5;}
                          else{column = 0;}
                          query += ""+column + ", ";

                    }else if(cell.getColumn() == 0 || cell.getColumn() == 19 || cell.getColumn() == 20){
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
                     }else if(cell.getColumn() == 17){
                        String especial = (cell.getContents().length() >0) ? "Otros" : "";
                        query += "'"+ cell.getContents().toUpperCase() + "', '"+ especial + "',";
                     }else if(cell.getColumn() == 23){
                        clientID = Integer.parseInt(cell.getContents());
                     }else if(cell.getColumn() == 24){
                        proveedorID = Integer.parseInt(cell.getContents());
                     } else {
                        if (type == CellType.LABEL)
                        {
                            System.out.println("I got a label "
                            + cell.getContents());
                            query += "'"+cell.getContents().toUpperCase() + "', ";
                        }

                        if (type == CellType.NUMBER)
                        {
                            System.out.println("I got a number "
                            + Integer.parseInt(cell.getContents()));
                            query += "'"+Integer.parseInt(cell.getContents()) + "', ";
                        }
                     }
                   }
                    query += "'','', '', 'Grúa', '2050-01-01', 'En Proceso')";
                    System.out.println(query);

                    boolean rs = CSDesktop.datos.manipuladorDatos(query);

                    if(!rs)
                    {
                        query = "select distinct last_insert_id() from pe_pedidos";
                        String pe_num="";
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
          } finally{
                if (fis != null) {
                 fis.close();
                }
           }

    }

}
