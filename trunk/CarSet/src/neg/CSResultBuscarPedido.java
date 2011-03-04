/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSResultBuscarPedido extends javax.swing.JPanel
{
    private  String consulta="";
    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarPedido(String query) throws UnknownHostException, FileNotFoundException, IOException
    {

        

        consulta=query;
        TablaModelo modelo = new TablaModelo();
        ResultSet rs = CSDesktop.datos.select(query);

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    jButtonCerrar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        for (int k = 0; k < this.getComponents().length; k ++)
        {
                this.getComponents()[k].addKeyListener(l);
        }
        addKeyListener(l);

        modelo.setColumnIdentifiers(new String[] {"NUM", "FECHA", "CLIENTE" , "SERVICIO" , "ORIGEN", "DESTINO", "F.CORRECCION", "MATRICULA","MARCA","MODELO","PROVEEDOR","TAR.CL","TAR.PR", "SE" ,"SUPLE","MG","F.RECOGIDA","F.ENTREGA","F.REAL","ESTADO","OBSERVACIONES"});

        int numeroFila = 0;
        double totalCliente = 0;
        double totalProveedor = 0;
        double totalSEspecial = 0;
        double totalSuplemento = 0;
        double totalMargen = 0;

        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                 double ta_es_cl=0;
                 double ta_es_pr=0;
                 double s_especial=0;
                 double importeServicioD = 0;
                 double suple=0;
                 double ganancia=0;
                 String cl_id = rs.getString("cl_id");
                 System.out.println("Cliente: "+cl_id);
                 String fechaPe = rs.getString("pe_fecha");
                 System.out.println("Fecha: "+fechaPe);
                for (int k = 0; k < 21; k++) {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k==6 || k == 7 || k == 8 || k==9 || k==10 || k==11 || k==12 || k==13 || k==14 || k==15 || k==16 || k==17 || k==18 || k==19 || k==20) {
                        if((k==1) || (k==16)|| (k==17) || (k==18))
                        {
                            String fecha=(rs.getObject(k+1)).toString();
                             String [] temp = null;
                             temp = fecha.split("\\-");
                             String anyo=temp[0];
                             String mes=temp[1];
                             String dia=temp[2];
                             String nueva=dia+"-"+mes+"-"+anyo;

                             datosFila[j] = nueva;
                        }
                        else if(k==11)
                        {
                             ta_es_cl=rs.getDouble(k+1);
                             datosFila[j] = rs.getDouble(k + 1);
                             totalCliente = totalCliente + ta_es_cl;
                             totalCliente = Utilidades.redondear(totalCliente, 2);
                        }
                        else if(k==12)
                        {
                            ta_es_pr=rs.getDouble(k+1);
                            datosFila[j] = rs.getDouble(k + 1);
                            System.out.println("Clase: " + datosFila[j].getClass().getName());
                            totalProveedor = totalProveedor + ta_es_pr;
                           totalProveedor = Utilidades.redondear(totalProveedor, 2);

                        }
                        else if (k==13)
                        {
//                            s_especial = rs.getDouble(k+1);
//                            datosFila[j] = rs.getDouble(k + 1);
//                            totalSEspecial = totalSEspecial + s_especial;

                            if(!rs.getObject(k+1).equals(""))
                            {
                                if(!rs.getObject(k+1).equals("Otros"))
                                {
                                    String servicio = rs.getObject(k+1).toString();
                                    String sEspecial = Utilidades.CalcularImporteServicioEspecial(servicio,cl_id, fechaPe);
                                    if(!servicio.equals(""))
                                    {
                                        importeServicioD = Double.parseDouble(sEspecial);
                                        importeServicioD = Utilidades.redondear(importeServicioD, 2);
                                    }
                                }
                            }
                            datosFila[j] = importeServicioD;
                            totalSEspecial = totalSEspecial + importeServicioD;
                            totalSEspecial = Utilidades.redondear(totalSEspecial, 2);

                        }
                        else if (k==14)
                        {
                            suple = rs.getDouble(k+1);
                            datosFila[j] = suple;
                            totalSuplemento = totalSuplemento + suple;
                            totalSuplemento = Utilidades.redondear(totalSuplemento, 2);
                        }
                        else if (k==15)
                        {
                            ganancia = ((ta_es_cl + s_especial) + suple) - ta_es_pr;
                            double gananciaF=Utilidades.redondear(ganancia, 2);
                            datosFila[j] = gananciaF;
                           
                            totalMargen = totalMargen + gananciaF;
                            totalMargen = Utilidades.redondear((totalMargen), 2);
                        }
                        else
                        {
                            datosFila[j] = rs.getObject(k + 1);
                            System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                        }
                        j++;
                    }
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
            for (int k = 0; k < 18; k++)
            {
                if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k==6 || k == 7 || k == 8 || k==9 || k==10 || k==11 || k==12 || k==13 || k==14 || k==15 || k==16 || k==17|| k==18) {
                    if(k==10)
                    {
                        datosFilaTotal[i] = "TOTALES";
                    }
                    if(k==11)
                    {
                        datosFilaTotal[i] = totalCliente;
                    }
                    if(k==12)
                    {
                        datosFilaTotal[i] = totalProveedor;
                    }
                    if(k==13)
                    {
                        datosFilaTotal[i] = totalSEspecial;
                    }
                    if(k==14)
                    {
                        datosFilaTotal[i] = totalSuplemento;
                    }
                    if(k==15)
                    {
                        datosFilaTotal[i] = totalMargen;
                    }
                }
                i++;
           }
       
           modelo.addRow(datosFilaTotal);

        } catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultPedido = new JInternalFrame("Resultado Búsqueda Pedidos", true, false, false, true );
            CSDesktop.ResultPedido.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultPedido.pack();
            CSDesktop.BuscarPedido.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultPedido );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultPedido.getSize();
            CSDesktop.ResultPedido.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultPedido.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.Object.class, new MiRender());
        //jTable1.setDefaultRenderer (java.util.Date.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.String.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.Double.class, new MiRender());

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(80);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(200);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(80);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(120);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(120);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(80);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(100);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(100);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(120);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(200);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(60);
        TableColumn columna12 = jTable1.getColumnModel().getColumn(12);
        columna12.setPreferredWidth(60);
        TableColumn columna13 = jTable1.getColumnModel().getColumn(13);
        columna13.setPreferredWidth(60);
        TableColumn columna14 = jTable1.getColumnModel().getColumn(14);
        columna14.setPreferredWidth(60);
        TableColumn columna15 = jTable1.getColumnModel().getColumn(15);
        columna15.setPreferredWidth(60);
        TableColumn columna16 = jTable1.getColumnModel().getColumn(16);
        columna16.setPreferredWidth(80);
        TableColumn columna17 = jTable1.getColumnModel().getColumn(17);
        columna17.setPreferredWidth(80);
        TableColumn columna18 = jTable1.getColumnModel().getColumn(18);
        columna18.setPreferredWidth(80);
         TableColumn columna19 = jTable1.getColumnModel().getColumn(19);
        columna19.setPreferredWidth(100);
        TableColumn columna20 = jTable1.getColumnModel().getColumn(20);
        columna20.setPreferredWidth(500);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        //jTable1.getTableHeader().setPreferredSize(new Dimension(jTable1.getTableHeader().getWidth(),26));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        


        /*jTable1.getColumnModel().getColumn(0).setCellRenderer(tcrCenter);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcrCenter);
        jTable1.getColumnModel().getColumn(11).setCellRenderer(tcrRight);
        jTable1.getColumnModel().getColumn(12).setCellRenderer(tcrRight);
        jTable1.getColumnModel().getColumn(13).setCellRenderer(tcrRight);
        jTable1.getColumnModel().getColumn(14).setCellRenderer(tcrRight);
        jTable1.getColumnModel().getColumn(15).setCellRenderer(tcrCenter);*/
        //jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);

        jTable1.setAutoCreateRowSorter(true);

        jTable1.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = jTable1.rowAtPoint(e.getPoint());
            int columna = jTable1.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {
               int proveedor = Integer.parseInt((String)jTable1.getValueAt(fila,0).toString());
               CSDesktop.EditarPedido = new JInternalFrame("Editar Pedido", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarPedido editarC = null;
                    try {
                        editarC = new CSEditarPedido(proveedor,consulta);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
               CSDesktop.EditarPedido.getContentPane().add( editarC,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarPedido.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarPedido );

               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarPedido.getSize();
               CSDesktop.EditarPedido.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarPedido.setVisible( true );
               CSDesktop.ResultPedido.setVisible(false);

            System.out.println(jTable1.getValueAt(fila,columna));
         }
        }
        }    );
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
    }

     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                    boolean hasFocus, int row, int col)
    {
        int componente = table.getSelectedRow();
     Component comp = getTableCellRendererComponent(table,  value, isSelected, hasFocus, row, col);

     String s =  table.getModel().getValueAt(row, col ).toString();

     if(s.equalsIgnoreCase("Fail"))
     {
         comp.setForeground(Color.red);
     }
     else 
     {
         comp.setForeground(null);
     }

     return( comp );
 }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonCerrar = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();

        setAutoscrolls(true);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 255));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jButtonCerrar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonCerrar.setText("Cancelar");
        jButtonCerrar.setName("jButtonCerrar"); // NOI18N
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jButtonExportar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonExportar.setText("Exportar EXCEL");
        jButtonExportar.setName("jButtonExportar"); // NOI18N
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonExportar)
                        .addGap(109, 109, 109)
                        .addComponent(jButtonCerrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCerrar)
                    .addComponent(jButtonExportar))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultPedido.dispose();
        CSDesktop.menuBuscarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        try {
            // Se crea el libro excel
            HSSFWorkbook libro = new HSSFWorkbook();
            //Se crea la hoja
            HSSFSheet hoja = libro.createSheet("Pedidos");
            //Numero de fila de la hoja Excel
            int num_fila = 1;
            crearCabeceraHojaExcel(libro, hoja);

            HSSFCellStyle cs2 = libro.createCellStyle();
            cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cs2.setBottomBorderColor(HSSFColor.BLACK.index);
            cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cs2.setLeftBorderColor(HSSFColor.BLACK.index);
            cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cs2.setRightBorderColor(HSSFColor.BLACK.index);
            cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cs2.setTopBorderColor(HSSFColor.BLACK.index);

            HSSFCellStyle cs3 = libro.createCellStyle();
            cs3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cs3.setBottomBorderColor(HSSFColor.BLACK.index);
            cs3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cs3.setLeftBorderColor(HSSFColor.BLACK.index);
            cs3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cs3.setRightBorderColor(HSSFColor.BLACK.index);
            cs3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cs3.setTopBorderColor(HSSFColor.BLACK.index);
            ResultSet rs = CSDesktop.datos.select(consulta);

            crearFilaHojaExcel(libro, hoja, num_fila, rs, cs2,cs3);
            FileOutputStream elFichero = null;
            elFichero = new FileOutputStream("c:\\Pedidos.xls");
            libro.write(elFichero);
            elFichero.close();
            elFichero.flush();
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);
            System.out.println("OS current temporary directory is " + tempDir);
            String file = new String("C:\\Pedidos.xls");
            Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);

    }//GEN-LAST:event_jButtonExportarActionPerformed
        catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSResultBuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }          catch (IOException ex) {
            Logger.getLogger(CSResultBuscarPedido.class.getName()).log(Level.SEVERE, null, ex);
       
    }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores

            jTable1.setRowHeight(20);

            if (column == 0 ||column == 1 || column == 16 || column == 17 || column == 18 )
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 11 ||column == 12 || column == 13 || column == 14 || column == 15 )
            {
                this. setHorizontalAlignment(SwingConstants.RIGHT);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }
            //se toman algunos valores especificos para mi programa
            //double cantidad = Double. parseDouble(table. getValueAt(row, 11). toString());
            //double stockMin = Double. parseDouble(table. getValueAt(row, 12). toString());
            //double stockMax = Double. parseDouble(table. getValueAt(row, 13). toString());
            //si cumplen x condicion se pintan
            if (row % 2 ==1)
            {
                Color fondo = new  Color(206, 227, 242);
                cell. setBackground(fondo);
                cell. setForeground(Color.DARK_GRAY);
            }
            else
            {
                cell. setBackground(Color.white);
                cell. setForeground(Color. BLACK);
            }

            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table. getValueAt(row, 10). toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
            }

            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            return cell;
        }
    }

    private static void crearCabeceraHojaExcel(HSSFWorkbook libro, HSSFSheet hoja)
	{
		HSSFRow fila = null;
		HSSFCell celda = null;

		// Modificamos la fuente por defecto para que salga en negrita
		HSSFCellStyle cs = libro.createCellStyle();
		HSSFFont f = libro.createFont();
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                f.setColor(HSSFColor.WHITE.index);
		cs.setFont(f);
                //cs.setFillBackgroundColor(HSSFColor.GREEN.index);
                cs.setFillForegroundColor(HSSFColor.GREEN.index);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cs.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
                cs.setBottomBorderColor(HSSFColor.BLACK.index);
                cs.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
                cs.setLeftBorderColor(HSSFColor.BLACK.index);
                cs.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
                cs.setRightBorderColor(HSSFColor.BLACK.index);
                cs.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
                cs.setTopBorderColor(HSSFColor.BLACK.index);

                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                
                

		// Creamos la cabecera de las columnas
                fila = hoja.createRow(0);

                celda = fila.createCell( (short) 0);
                celda.setCellStyle(cs);
                HSSFRichTextString texto = new HSSFRichTextString("NUM");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 0, (short) ((50 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 1);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FECHA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 1, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 2);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("CLIENTE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 2, (short) ((350 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 3);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("SERVICIO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 3, (short) ((130 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 4);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("ORIGEN");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 4, (short) ((175 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 5);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("DESTINO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 5, (short) ((175 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 6);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.CORRECCION");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 6, (short) ((150 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 7);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("MATRICULA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 7, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("MARCA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 8, (short) ((150 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 9);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("MODELO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 9, (short) ((150 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 10);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("PROVEEDOR");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 10, (short) ((350 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)11);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("TAR.CL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 11, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)12);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("TAR.PR");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 12, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)13);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("SERV.ES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 13, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)14);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("SUPLE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 14, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)15);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("MG");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 15, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 16);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.ENTREGA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 16, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 17);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.RECOGIDA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 17, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 18);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FECHA REAL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 18, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 19);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("OBSERVACIONES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 19, (short) ((600 * 2) / ((double) 1 / 20)) );

        
	}

        private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, ResultSet rs, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
	{
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;
                int num_fila_aux=2;

                while (rs.next())
                {
                    String cl_id = rs.getString("cl_id");
                    String fechaPe = rs.getString("pe_fecha");
                    double importeServicioD=0;

                    // Se crea una fila dentro de la hoja
                    fila = hoja.createRow(num_fila);

                    //Celda del numero de pedido
                    celda = fila.createCell( (short) 0);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(rs.getInt("pe_num"));

                    //Celda de la fecha del pedido
                    celda = fila.createCell( (short) 1);
                    String fecha=(rs.getObject("pe_fecha")).toString();
                             String [] temp = null;
                             temp = fecha.split("\\-");
                             String anyo=temp[0];
                             String mes=temp[1];
                             String dia=temp[2];
                             String nueva=dia+"-"+mes+"-"+anyo;
                    texto = new HSSFRichTextString(nueva);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    //Celda del Cliente
                    celda = fila.createCell( (short) 2);                  
                    String cliente=rs.getString("cl_nombre");
                    texto = new HSSFRichTextString(cliente);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Servicio
                    celda = fila.createCell( (short) 3);
                    String servicio=rs.getString("pe_servicio");
                    texto = new HSSFRichTextString(servicio);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Origen
                    celda = fila.createCell( (short) 4);
                    String origen=rs.getString("pe_servicio_origen");
                    texto = new HSSFRichTextString(origen);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Origen
                    celda = fila.createCell( (short) 5);
                    String destino=rs.getString("pe_servicio_destino");
                    texto = new HSSFRichTextString(destino);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Factor de Correccion
                    celda = fila.createCell( (short) 6);
                    String factor=rs.getString("fc_nombre");
                    texto = new HSSFRichTextString(factor);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Matricula
                    celda = fila.createCell( (short) 7);
                    String matricula=rs.getString("pe_ve_matricula");
                    texto = new HSSFRichTextString(matricula);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Marca
                    celda = fila.createCell( (short) 8);
                    String marca=rs.getString("pe_ve_marca");
                    texto = new HSSFRichTextString(marca);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Modelo
                    celda = fila.createCell( (short) 9);
                    String modelo=rs.getString("pe_ve_modelo");
                    texto = new HSSFRichTextString(modelo);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Proveedor
                    celda = fila.createCell( (short) 10);
                    String proveedor=rs.getString("pr_nombre_fiscal");
                    texto = new HSSFRichTextString(proveedor);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Tarifa Cliente
                    celda = fila.createCell( (short) 11);
                    HSSFDataFormat format = libro.createDataFormat();
                    HSSFCellStyle style = libro.createCellStyle();
                    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    style.setBottomBorderColor(HSSFColor.BLACK.index);
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    style.setLeftBorderColor(HSSFColor.BLACK.index);
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    style.setRightBorderColor(HSSFColor.BLACK.index);
                    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                    style.setTopBorderColor(HSSFColor.BLACK.index);

                    style.setDataFormat(format.getFormat("00.00"));

                    celda.setCellStyle(style);                                       
                    celda.setCellValue(rs.getDouble("pe_ta_es_cliente"));


                    celda = fila.createCell( (short) 12);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("pe_ta_es_proveedor"));

                    celda = fila.createCell( (short) 13);
                    style.setDataFormat(format.getFormat("##.00"));
                    //style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);

                     if(!rs.getObject("pe_servicio_especial").equals(""))
                            {
                                if(!rs.getObject("pe_servicio_especial").equals("Otros"))
                                {
                                    String servicioEs = rs.getObject("pe_servicio_especial").toString();
                                    String sEspecial = Utilidades.CalcularImporteServicioEspecial(servicioEs,cl_id, fechaPe);
                                    if(!servicioEs.equals(""))
                                    {
                                        importeServicioD = Double.parseDouble(sEspecial);
                                        importeServicioD = Utilidades.redondear(importeServicioD, 2);
                                    }
                                }
                            }
                    celda.setCellValue(importeServicioD);


                    celda = fila.createCell( (short) 14);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("pe_suplemento"));

                    //Columna de MG
                    celda = fila.createCell( (short) 15);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellFormula("L"+num_fila_aux+"-M"+num_fila_aux+"+N"+num_fila_aux+"+O"+num_fila_aux+"");
                    celda.setCellStyle(style);

                    //Celda de la fecha del pedido
                    celda = fila.createCell( (short) 16);
                    String fechaRecogida=(rs.getObject("pe_fecha_destino")).toString();
                             String [] tempR = null;
                             tempR = fechaRecogida.split("\\-");
                             String anyoR=tempR[0];
                             String mesR=tempR[1];
                             String diaR=tempR[2];
                             String nuevaR=diaR+"-"+mesR+"-"+anyoR;
                    texto = new HSSFRichTextString(nuevaR);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    celda = fila.createCell( (short) 17);
                    String fechaEntrega=(rs.getObject("pe_fecha_destino")).toString();
                             tempR = null;
                             tempR = fechaEntrega.split("\\-");
                             anyoR=tempR[0];
                             mesR=tempR[1];
                             diaR=tempR[2];
                             nuevaR=diaR+"-"+mesR+"-"+anyoR;
                    texto = new HSSFRichTextString(nuevaR);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    celda = fila.createCell( (short) 18);
                    String fechaReal=(rs.getObject("pe_fecha_real_destino")).toString();
                             tempR = null;
                             tempR = fechaReal.split("\\-");
                             anyoR=tempR[0];
                             mesR=tempR[1];
                             diaR=tempR[2];
                             nuevaR=diaR+"-"+mesR+"-"+anyoR;
                    texto = new HSSFRichTextString(nuevaR);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    celda = fila.createCell( (short) 19);
                    String descripcion=rs.getString("pe_descripcion");
                    texto = new HSSFRichTextString(descripcion);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);


                    //Se incrementa el numero de fila
                    num_fila++;
                    num_fila_aux++;

                }
        }
}