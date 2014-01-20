package neg;

import com.toedter.calendar.JDateChooser;
import data.BeanTesoreriaProveedor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import utils.TablaModeloTesoreria;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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
public class CSResultBuscarTesoreriaProveedor extends javax.swing.JPanel
{
    private String consulta = "";
    ArrayList lista=new ArrayList();
    ArrayList pedidos=new ArrayList();
    static String fVencimiento = "";

    public CSResultBuscarTesoreriaProveedor(String query) throws UnknownHostException, FileNotFoundException, IOException, ParseException
    {
        consulta = query;
        TablaModeloTesoreria modelo = new TablaModeloTesoreria();
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

        modelo.setColumnIdentifiers(new String[] {"F. FACTURA", "VENCIMIENTO", "N.º FACTURA" , "N.º F. CARSET" , "PROVEEDOR", "NETO", "IVA", "IRPF", "TOTAL", "DIAS F.F.", "F. PAGO", "N.º CUENTA", "ESTADO", "FECHA PAGO" , "BANCO", "EMAIL", "OBSERVACIONES"});
        int numeroFila = 0;
        double total = 0;
        double totalIva = 0;
        double totalIrpf = 0;
        double totalImporte = 0;

        try
        {
            while (rs.next())
            {
                BeanTesoreriaProveedor campos = new BeanTesoreriaProveedor();

                Calendar cal = Calendar.getInstance();
                Date fechaTs = null;
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");

                campos.setTr_id(rs.getInt("tr_id"));
                campos.setTr_estado(rs.getString("tr_estado"));

                if (rs.getDate("tr_fecha_pago") != null)
                {
                    fechaTs = (Date)formatoDeFecha.parse(rs.getString("tr_fecha_pago"));
                    cal.setTime(fechaTs);
                }
                campos.setTr_fecha_pago(cal);

                campos.setTr_banco(rs.getString("tr_banco"));

                lista.add(campos);

                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                double total_pr = 0;
                double iva = 0;
                double irpf = 0;
                double importe = 0;

                for (int k = 0; k < 17; k++)
                {
                    if((k==0))
                    {
                        String fecha=rs.getString("tr_fecha");
                         String [] temp = null;
                         if (!fecha.equals(" "))
                         {
                            temp = fecha.split("\\-");
                            String anyo=temp[0];
                            String mes=temp[1];
                            String dia=temp[2];
                            String nueva=dia+"-"+mes+"-"+anyo;

                            datosFila[j] = nueva;
                         }
//                    System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==1)
                    {
                        String plazo = rs.getString("pr_plazo");
                        int diasPlazo = 0;
                        if(!plazo.equals("Especial"))
                        {
                            String[] tempVe = plazo.split("\\ ");
                            diasPlazo = Integer.parseInt(tempVe[0]);
                        }
                        else
                        {
                            diasPlazo = Integer.parseInt(rs.getString("pr_dias_plazo"));
                        }
                        //sumamos a la fecha el plazo en días
                        fVencimiento = Utilidades.sumarFecha(rs.getString("tr_fecha"), diasPlazo);
                        datosFila[j] = fVencimiento;
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==5)
                    {
                        total_pr = rs.getDouble(k);
                        datosFila[j] = Utilidades.separadorMiles(Double.toString(rs.getDouble(k)));
                        total = total + total_pr;
                        total = Utilidades.redondear(total, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);

                    }
                    else if(k==6)
                    {
                        iva = rs.getDouble(k);
                        datosFila[j] = Utilidades.separadorMiles(Double.toString(rs.getDouble(k)));
                        totalIva = totalIva + iva;
                        totalIva = Utilidades.redondear(totalIva, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if (k==7)
                    {
                        irpf = rs.getDouble(k);
                        datosFila[j] = Utilidades.separadorMiles(Double.toString(rs.getDouble(k)));
                        totalIrpf = totalIrpf + irpf;
                        totalIrpf = Utilidades.redondear(totalIrpf, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if (k==8)
                    {
                        importe = rs.getDouble(k);
                        datosFila[j] = Utilidades.separadorMiles(Double.toString(rs.getDouble(k)));
                        totalImporte = totalImporte + importe;
                        totalImporte = Utilidades.redondear(totalImporte, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if((k==13))
                    {
                        String fechaPago = rs.getString("tr_fecha_pago");
                        String [] temp = null;
                        if (fechaPago != null && !fechaPago.equals(" "))
                        {
                            temp = fechaPago.split("\\-");
                            String anyo=temp[0];
                            String mes=temp[1];
                            String dia=temp[2];
                            String nuevaPago = dia+"-"+mes+"-"+anyo;

                            datosFila[j] = nuevaPago;
                        }
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else
                    {
                        datosFila[j] = rs.getObject(k);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    j++;

               // System.out.println("Dato" + k + " " + datosFila[j]);
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
            for (int k = 0; k < 17; k++)
            {
                    if(k==4)
                    {
                        datosFilaTotal[i] = "TOTALES";
                    }
                    if(k==5)
                    {
                        datosFilaTotal[i] = Utilidades.redondear(total, 2);
                    }
                    if(k==6)
                    {
                        datosFilaTotal[i] = Utilidades.redondear(totalIva, 2);
                    }
                    if(k==7)
                    {
                        datosFilaTotal[i] = Utilidades.redondear(totalIrpf, 2);
                    }
                    if(k==8)
                    {
                        datosFilaTotal[i] = Utilidades.redondear(totalImporte, 2);
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
            CSDesktop.ResultTesoreriaProveedor = new JInternalFrame("Resultado Búsqueda Tesorería Proveedor", true, false, true, true );
            CSDesktop.ResultTesoreriaProveedor.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultTesoreriaProveedor.pack();
            CSDesktop.ResultTesoreriaProveedor.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultTesoreriaProveedor );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultTesoreriaProveedor.getSize();
            CSDesktop.ResultTesoreriaProveedor.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultTesoreriaProveedor.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());

        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(80);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(80);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(140);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(100);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(160);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(60);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(60);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(60);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(60);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(70);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(80);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(120);
        TableColumn columna12 = jTable1.getColumnModel().getColumn(12);
        columna12.setPreferredWidth(90);
        TableColumn columna13 = jTable1.getColumnModel().getColumn(13);
        columna13.setPreferredWidth(90);
        TableColumn columna14 = jTable1.getColumnModel().getColumn(14);
        columna14.setPreferredWidth(60);
        TableColumn columna15 = jTable1.getColumnModel().getColumn(15);
        columna15.setPreferredWidth(150);
        TableColumn columna16 = jTable1.getColumnModel().getColumn(16);
        columna16.setPreferredWidth(200);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        
        jTable1.setAutoCreateRowSorter(true);
    }

    /**
     *
     * @return
     */
    public Dimension getPreferredSize()
    {
      return new Dimension( 1100,650 );
    }

   /**
    *
    * @param table
    * @param value
    * @param isSelected
    * @param hasFocus
    * @param row
    * @param col
    * @return
    */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                    boolean hasFocus, int row, int col)
    {
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
        jButtonModificar = new javax.swing.JButton();

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

        jButtonModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonModificar.setText("Modificar");
        jButtonModificar.setName("jButtonModificar"); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonExportar)
                        .addGap(239, 239, 239)
                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201)
                        .addComponent(jButtonCerrar)))
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
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonExportar))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultTesoreriaProveedor.dispose();
        CSDesktop.menuTesoreriaProveedor.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed


    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        try {
            // Se crea el libro excel
            HSSFWorkbook libro = new HSSFWorkbook();
            //Se crea la hoja
            HSSFSheet hoja = libro.createSheet("Tesorería Proveedor");
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
            elFichero = new FileOutputStream("c:\\TesoreriaProveedor.xls");
            libro.write(elFichero);
            elFichero.close();
            elFichero.flush();
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);
            System.out.println("OS current temporary directory is " + tempDir);
            String file = new String("C:\\TesoreriaProveedor.xls");
            Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);

    }//GEN-LAST:event_jButtonExportarActionPerformed
        catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }          catch (IOException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
        private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

            int fila = 0;
            String estado = "PTE";
            String nueva = "";
            boolean tesoreria = false;
            BeanTesoreriaProveedor campos = new BeanTesoreriaProveedor();

            for(int i = 0; i < lista.size(); i++)
            {
                campos =(BeanTesoreriaProveedor)lista.get(fila);
                int tr_id = campos.getTr_id();

                if(jTable1.getValueAt(fila, 12) != null){
                 estado =  jTable1.getValueAt(fila, 12).toString();
                }
                String fechaPago = jTable1.getValueAt(fila, 13).toString();
                if (fechaPago != null && !fechaPago.equals(""))
                {
                     String [] temp = null;
                     temp = fechaPago.split("\\-");
                     String anyo = temp[2];
                     String mes = temp[1];
                     String dia = temp[0];
                     nueva = anyo+"-"+mes+"-"+dia;
                }

                String banco = (jTable1.getValueAt(fila, 14) != null) ? jTable1.getValueAt(fila, 14).toString() : "";

                fila ++;

//                System.out.println("Elemento id: "+tr_id);
//                System.out.println("Elemento estado: "+estado);
//                System.out.println("Elemento fecha pago: "+fechaPago);
//                System.out.println("Elemento banco: "+banco);

                try {
                    //guardamos las modificaciones en la bd
                   tesoreria =  modificarTesoreria(tr_id, estado, nueva, banco);
                } catch (SQLException ex) {
                    Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(tesoreria)
            {
                jButtonModificar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonModificar.setEnabled(true);
            }
            else
            {
                jButtonModificar.setEnabled(false);
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, mensaje);
                jButtonModificar.setEnabled(true);
                CSDesktop.ResultTesoreriaProveedor.dispose();
                CSDesktop.BuscarTesoreriaProveedor.dispose();
                CSDesktop.menuTesoreriaProveedor.setEnabled(true);
            }
        }//GEN-LAST:event_jButtonModificarActionPerformed


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
                HSSFRichTextString texto = new HSSFRichTextString("F. FACTURA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 0, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 1);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("VENCIMIENTO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 1, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 2);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("N.º FACTURA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 2, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 3);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("N.º F. CARSET");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 3, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 4);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("PROVEEDOR");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 4, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 5);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("NETO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 5, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 6);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("IVA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 6, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 7);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("IRPF");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 7, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("TOTAL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 8, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 9);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("DIAS F.F.");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 9, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 10);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F. COBRO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 10, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)11);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("N.º CUENTA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 11, (short) ((350 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)12);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("ESTADO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 12, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)13);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FECHA COBRO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 13, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)14);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("BANCO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 14, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)15);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("EMAIL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 15, (short) ((350 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 16);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("OBSERVACIONES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 16, (short) ((600 * 2) / ((double) 1 / 20)) );
	}

        private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, ResultSet rs, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
	{
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;
                int num_fila_aux=2;

                while (rs.next())
                {
//                    String tr_id = rs.getString("tr_id");
//                    String fechaTr = rs.getString("tr_fecha");

                    // Se crea una fila dentro de la hoja
                    fila = hoja.createRow(num_fila);

                    //Celda de la fecha
                    celda = fila.createCell( (short) 0);
                    String fecha=(rs.getObject("tr_fecha")).toString();
                             String [] temp = null;
                             temp = fecha.split("\\-");
                             String anyo=temp[0];
                             String mes=temp[1];
                             String dia=temp[2];
                             String nueva=dia+"-"+mes+"-"+anyo;
                    texto = new HSSFRichTextString(nueva);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    //Plazo de pago
                    String plazo = rs.getString("pr_plazo");
                    int diasPlazo = 0;
                    if(!plazo.equals("Especial"))
                    {
                        String[] tempVe = plazo.split("\\ ");
                        diasPlazo = Integer.parseInt(tempVe[0]);
                    }
                    else
                    {
                        diasPlazo = Integer.parseInt(rs.getString("pr_dias_plazo"));
                    }
                    //Celda de la fecha vencimiento
                    celda = fila.createCell( (short) 1);
                    try {
                        fVencimiento = Utilidades.sumarFecha(fecha, diasPlazo);
                    } catch (ParseException ex) {
                        Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    texto = new HSSFRichTextString(fVencimiento);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda numero
                    celda = fila.createCell( (short) 2);
                    String numero=rs.getString("tr_num");
                    texto = new HSSFRichTextString(numero);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda numero Carset
                    celda = fila.createCell( (short) 3);
                    String numeroCarset=rs.getString("tr_num_carset");
                    texto = new HSSFRichTextString(numeroCarset);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Proveedor
                    celda = fila.createCell( (short) 4);
                    String proveedor=rs.getString("pr_nombre_fiscal");
                    texto = new HSSFRichTextString(proveedor);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Importes
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

                    //Celda Importe Neto
                    celda = fila.createCell( (short) 5);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("tr_importe_neto"));

                    //Celda IVA
                    celda = fila.createCell( (short) 6);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("tr_iva"));
                    
                    //Celda IRPF
                    celda = fila.createCell( (short) 7);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("tr_irpf"));
  
                    //Celda Total
                    celda = fila.createCell( (short) 8);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("tr_importe"));
                    
                    //Celda del Plazo
                    celda = fila.createCell( (short) 9);
                    texto = new HSSFRichTextString(plazo);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Tipo
                    celda = fila.createCell( (short) 10);
                    String tipo=rs.getString("fp_tipo");
                    texto = new HSSFRichTextString(tipo);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de número de cuenta
                    celda = fila.createCell( (short) 11);
                    String numCuenta=rs.getString("pr_num_cuenta");
                    texto = new HSSFRichTextString(numCuenta);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Estado
                    celda = fila.createCell( (short) 12);
                    String estado=rs.getString("tr_estado");
                    texto = new HSSFRichTextString(estado);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de la fecha de pago
                    celda = fila.createCell( (short) 13);
                    String fecha_pago = rs.getString("tr_fecha_pago");
                    if (fecha_pago != null && !fecha_pago.equals(" "))
                    {
                         temp = null;
                         temp = fecha_pago.split("\\-");
                         anyo=temp[0];
                         mes=temp[1];
                         dia=temp[2];
                         nueva=dia+"-"+mes+"-"+anyo;
                    }
                    texto = new HSSFRichTextString(nueva);
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    //Celda de Banco
                    celda = fila.createCell( (short) 14);
                    String banco=rs.getString("tr_banco");
                    texto = new HSSFRichTextString(banco);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Email
                    celda = fila.createCell( (short) 15);
                    String email=rs.getString("pr_email");
                    texto = new HSSFRichTextString(email);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de las observaciones
                    celda = fila.createCell( (short) 16);
                    String observaciones=rs.getString("tr_observaciones");
                    texto = new HSSFRichTextString(observaciones);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Se incrementa el numero de fila
                    num_fila++;
                    num_fila_aux++;

                }
        }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores
            jTable1.setRowHeight(20);

            if (column == 0 || column == 1 || column == 13)
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 5 || column == 6 || column == 7 || column == 8)
            {
                this. setHorizontalAlignment(SwingConstants.RIGHT);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }

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

            if (column == 11 || column == 12 || column == 13 || column == 14 || column == 15 )
            {
                Color fondo = new  Color(255, 255, 157);
                cell.setBackground(fondo);
                cell. setForeground(Color.DARK_GRAY);
            }

            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            // These are the combobox values
            String[] values = new String[]{"","PTE", "PAGADO"};
            String[] valuesBanco = new String[]{"","LC", "OP", "OB"};

            //System.out.println(table.getRowCount()+" / "+fila);
            TableColumn col = table.getColumnModel().getColumn(column);
            TableColumn colB = table.getColumnModel().getColumn(column);

            if (column == 12)
            {
                col.setCellEditor(new MyComboBoxEditor(values));
                col.setCellRenderer(new MyComboBoxRenderer(values));
                jTable1.setValueAt(values, row, column);
            }
         
            else if(column == 14)
            {
                colB.setCellEditor(new MyComboBoxEditor(valuesBanco));
                colB.setCellRenderer(new MyComboBoxRenderer(valuesBanco));
                jTable1.setValueAt(valuesBanco, row, column);
            }
            
            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table. getValueAt(row, 4). toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
                col.setCellEditor(null);
                col.setCellRenderer(null);
            }

            jTable1.setRowSelectionAllowed(true);
            jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

            return cell;
        }
    }

    /**
     *
     */
    public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer
    {
        public MyComboBoxRenderer(String[] items)
        {
            super(items);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            }
            else
            {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }

    /**
     *
     */
    public class MyComboBoxEditor extends DefaultCellEditor
    {
        public MyComboBoxEditor(String[] items)
        {
            super(new JComboBox(items));
        }
    }

    /**
     * 
     */
    public class DateEditer extends AbstractCellEditor implements TableCellEditor
    {

        private JDateChooser theSpinner;
        protected Object value;

        public DateEditer()
        {
            theSpinner = new JDateChooser();
            theSpinner.setDateFormatString( "dd-MMM-yyyy" );
            //theSpinner.setOpaque(true);
        }

        public Object getCellEditorValue()
        {
            return theSpinner.getDate();
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
        {
        // If the date is blank then default to today
            if (value == null)
            {
                value = new Date();
            }

        theSpinner.setDate((Date)value);
        if (isSelected)
        {
            theSpinner.setBackground(table.getSelectionBackground());
        }
        else
        {
            theSpinner.setBackground(table.getBackground());
        }

        return theSpinner;
        }
    }


    /**
     * Modifica los campos de la tesorería del proveedor
     * @param ts
     * @throws SQLException
     */
    public boolean modificarTesoreria(int tr_id, String estado, String fechaPago, String banco) throws SQLException
    {
        String query = "UPDATE tr_tesoreria_proveedor SET tr_estado = '"+estado+"'";
        if(!fechaPago.equals(""))
        {
            query = query + ", tr_fecha_pago = '"+fechaPago+"'";
        }
        query = query + ", tr_banco = '"+banco+"' WHERE tr_id = "+tr_id;
        System.out.println(query);
        boolean rsUpdate = CSDesktop.datos.manipuladorDatos(query);

        return rsUpdate;
        
    }

}