package neg;

import com.toedter.calendar.JDateChooser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
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
import utils.TablaModeloTsCliente;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSResultBuscarTesoreriaCliente extends javax.swing.JPanel
{
    private String consulta = "";
    ArrayList lista = new ArrayList();
    static String fVencimiento = "";

    public CSResultBuscarTesoreriaCliente(String query) throws UnknownHostException, FileNotFoundException, IOException, ParseException
    {
        consulta = query;
        TablaModeloTsCliente modelo = new TablaModeloTsCliente();
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

        modelo.setColumnIdentifiers(new String[] {"F. FACTURA", "VENCIMIENTO", "N.º FACTURA" , "CLIENTE", "NETO", "IVA","TOTAL","DIAS F.F.","F. COBRO", "BANCO", "ESTADO", "FECHA COBRO" , "N.º CUENTA", "OBSERVACIONES", "CIF", "C.P.", "ACTUALIZADO"});
        int numeroFila = 0;
        double total = 0;
        double totalIva = 0;
        double totalImporte = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        try
        {
            while (rs.next())
            {
                Calendar cal = Calendar.getInstance();
                Date fechaTs = null;
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                ArrayList facturas = new ArrayList();
                facturas.add(rs.getInt("fl_id"));
                facturas.add(rs.getString("fl_estado"));
                facturas.add(rs.getString("cl_num_cuenta"));

                if (rs.getDate("fl_fecha_pago") != null)
                {
                    fechaTs = (Date)formatoDeFecha.parse(rs.getString("fl_fecha_pago"));
                    cal.setTime(fechaTs);
                }
                facturas.add(cal);
                lista.add(facturas);

                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                double total_cl = 0;
                double iva = 0;
                double importe = 0;

                for (int k = 0; k < 17; k++)
                {
                    if(k==0)
                    {
                         String fecha=rs.getString("fl_fecha");
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
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==1)
                    {
                        String plazo = rs.getString("cl_plazo");
                        int diasPlazo = 0;
                        if(!plazo.equals("Especial"))
                        {
                            String[] tempVe = plazo.split("\\ ");
                            diasPlazo = Integer.parseInt(tempVe[0]);
                        }
                        else
                        {
                            diasPlazo = Integer.parseInt(rs.getString("cl_dias_plazo"));
                        }
                        //sumamos a la fecha el plazo en días
                        fVencimiento = Utilidades.sumarFecha(rs.getString("fl_fecha"), diasPlazo);
                        datosFila[j] = fVencimiento;
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==4)
                    {
                        total_cl = rs.getDouble(k+1);
                        datosFila[j] = df.format(total_cl);
                        total = total + total_cl;
                        total = Utilidades.redondear(total, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==5)
                    {
                        iva = rs.getDouble(k+1);
                        datosFila[j] = df.format(iva);
                        totalIva = totalIva + iva;
                        totalIva = Utilidades.redondear(totalIva, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if (k==6)
                    {
                        importe = rs.getDouble(k+1);
                        NumberFormat NF = NumberFormat.getInstance();
                        NF.setMaximumFractionDigits(2); //3 decimales
                        datosFila[j] = df.format(importe);

                        totalImporte = totalImporte + importe;
                        totalImporte = Utilidades.redondear(totalImporte, 2);
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==9)
                    {
                        String banco = rs.getString("fl_banco");
                        datosFila[j] = banco;
                    }
                    else if(k==10)
                    {
                        String estado = rs.getString("fl_estado");
                        datosFila[j] = estado;
                    }
                    else if(k==11)
                    {
                        String fecha=rs.getString("fl_fecha_pago");
                        String [] temp = null;
                        if (fecha !=null && !fecha.equals(""))
                        {
                            temp = fecha.split("\\-");
                            String anyo=temp[0];
                            String mes=temp[1];
                            String dia=temp[2];
                            String nueva=dia+"-"+mes+"-"+anyo;

                            datosFila[j] = nueva;
                        }
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    else if(k==12)
                    {
                        String numCuenta = rs.getString("cl_num_cuenta");
                        datosFila[j] = numCuenta;
                    }
                    else if(k==13)
                    {
                        String observaciones= rs.getString("fl_observaciones");
                        datosFila[j] = observaciones;
                    }                     
                    else if(k==14)
                    {
                        String cif = rs.getString("cl_DNI_CIF");
                        datosFila[j] = cif;
                    }
                    else if(k==15)
                    {
                        String cif = rs.getString("cl_cod_postal");
                        datosFila[j] = cif;
                    }
                    else if(k==16)
                    {
                        String actualizado = rs.getString("date_modified");
                        String [] temp = null;
                        String [] tempHora = null;
                        if (actualizado !=null && !actualizado.equals(""))
                        {
                            temp = actualizado.split("\\-");
                            String anyo=temp[0];
                            String mes=temp[1];
                            String dia=temp[2].substring(0,2);

                            tempHora = actualizado.split("\\ ");
                            String hora = tempHora[1].substring(0,5);

                            String nueva=anyo+"-"+mes+"-"+dia+" "+hora;

                            datosFila[j] = nueva;
                        }
                    }
                    else
                    {
                        datosFila[j] = rs.getObject(k+1);
//                       System.out.println("Dato" + k + " " + datosFila[j]);
                    }
                    j++;
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
            for (int k = 0; k < 13; k++)
            {
                if(k==3)
                {
                    datosFilaTotal[i] = "TOTALES";
                }
                if(k==4)
                {
                    datosFilaTotal[i] = df.format(total);
                }
                if(k==5)
                {
                    datosFilaTotal[i] = df.format(totalIva);
                }
                if(k==6)
                {
                    datosFilaTotal[i] = df.format(totalImporte);
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
            CSDesktop.ResultTesoreriaCliente = new JInternalFrame("Resultado Búsqueda Tesorería Cliente", true, false, true, true );
            CSDesktop.ResultTesoreriaCliente.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultTesoreriaCliente.pack();
            CSDesktop.ResultTesoreriaCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultTesoreriaCliente );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultTesoreriaCliente.getSize();
            CSDesktop.ResultTesoreriaCliente.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultTesoreriaCliente.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(80);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(80);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(140);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(200);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(60);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(60);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(60);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(100);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(100);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(120);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(80);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(100);
        TableColumn columna12 = jTable1.getColumnModel().getColumn(12);
        columna12.setPreferredWidth(130);
        TableColumn columna13 = jTable1.getColumnModel().getColumn(13);
        columna13.setPreferredWidth(250);
        TableColumn columna14 = jTable1.getColumnModel().getColumn(14);
        columna14.setPreferredWidth(80);
        TableColumn columna15 = jTable1.getColumnModel().getColumn(15);
        columna15.setPreferredWidth(80);
        TableColumn columna16 = jTable1.getColumnModel().getColumn(16);
        columna16.setPreferredWidth(110);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        
        jTable1.setAutoCreateRowSorter(true);
        RowFilter.dateFilter(ComparisonType.BEFORE, new Date(),0);

        ListSelectionModel selectionModel = jTable1.getSelectionModel();
        selectionModel.setSelectionInterval(0, 12);
        selectionModel.addSelectionInterval(0, 12);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.setSelectionModel(selectionModel);
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

        jButtonModificar.setFont(new java.awt.Font("Tahoma", 1, 11));
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
        CSDesktop.ResultTesoreriaCliente.dispose();
        CSDesktop.menuTesoreriaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed


    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        try {
            // Se crea el libro excel
            HSSFWorkbook libro = new HSSFWorkbook();
            //Se crea la hoja
            HSSFSheet hoja = libro.createSheet("Tesorería Cliente");
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
            elFichero = new FileOutputStream("c:\\TesoreriaCliente.xls");
            libro.write(elFichero);
            elFichero.close();
            elFichero.flush();
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);
            System.out.println("OS current temporary directory is " + tempDir);
            String file = new String("C:\\TesoreriaCliente.xls");
            Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);

    }//GEN-LAST:event_jButtonExportarActionPerformed
        catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }          catch (IOException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
        private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

            String nueva = "";
            String estado = "";
            boolean tesoreria = false;

            for(int i = 0; i < lista.size(); i++)
            {
//               System.out.println("celda: "+lista.get(i));
                //ArrayList indices = (ArrayList) lista.get(celdas[i]);
                ArrayList indices = (ArrayList) lista.get(i);
                int fl_id = Integer.parseInt(indices.get(0).toString());
                estado = jTable1.getValueAt(i, 10).toString();
                String fechaPago = jTable1.getValueAt(i, 11).toString();

                if (fechaPago != null && !fechaPago.equals(""))
                {
                    String [] temp = null;
                    temp = fechaPago.split("\\-");
                    String anyo = temp[2];
                    String mes = temp[1];
                    String dia = temp[0];
                    nueva = anyo+"-"+mes+"-"+dia;
                }


                String observaciones = (String) jTable1.getValueAt(i, 13);

                try {
                    //guardamos las modificaciones en la bd
                  tesoreria = modificarTesoreria(fl_id, estado, nueva, jTable1.getValueAt(i, 9), observaciones);
                } catch (SQLException ex) {
                    Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
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
                CSDesktop.ResultTesoreriaCliente.dispose();
                //CSDesktop.BuscarTesoreriaCliente.dispose();
                CSDesktop.menuTesoreriaCliente.setEnabled(true);
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
                texto = new HSSFRichTextString("CLIENTE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 3, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 4);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("NETO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 4, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 5);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("IVA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 5, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 6);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("TOTAL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 6, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 7);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("DIAS F.F.");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 7, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F. COBRO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 8, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short)9);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("BANCO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 11, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 10);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("ESTADO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 9, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 11);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FECHA COBRO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 10, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 12);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("N.º CUENTA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 12, (short) ((130 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 13);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("OBSERVACIONES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 13, (short) ((600 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 14);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("CIF");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 14, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 15);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("C.P.");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 15, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 16);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("ACTUALIZADO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 16, (short) ((90 * 2) / ((double) 1 / 20)) );

	}

        private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, ResultSet rs, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
	{
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;
                int num_fila_aux=2;

                while (rs.next())
                {
//                    String tr_id = rs.getString("fl_id");
//                    String fechaTr = rs.getString("fl_fecha");

                    // Se crea una fila dentro de la hoja
                    fila = hoja.createRow(num_fila);

                    //Celda de la fecha
                    celda = fila.createCell( (short) 0);
                    String fecha=(rs.getObject("fl_fecha")).toString();
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
                    String plazo = rs.getString("cl_plazo");
                    int diasPlazo = 0;
                    if(!plazo.equals("Especial"))
                    {
                        String[] tempVe = plazo.split("\\ ");
                        diasPlazo = Integer.parseInt(tempVe[0]);
                    }
                    else
                    {
                        diasPlazo = Integer.parseInt(rs.getString("cl_dias_plazo"));
                    }
                    //Celda de la fecha vencimiento
                    celda = fila.createCell( (short) 1);
                    try {
                        fVencimiento = Utilidades.sumarFecha(fecha, diasPlazo);
                    } catch (ParseException ex) {
                        Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    texto = new HSSFRichTextString(fVencimiento);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda numero
                    celda = fila.createCell( (short) 2);
                    String numero=rs.getString("fl_num");
                    texto = new HSSFRichTextString(numero);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Cliente
                    celda = fila.createCell( (short) 3);
                    String proveedor=rs.getString("cl_nombre");
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
                    celda = fila.createCell( (short) 4);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("fl_importe"));

                    //Celda IVA
                    celda = fila.createCell( (short) 5);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("fl_iva"));
  
                    //Celda Total
                    celda = fila.createCell( (short) 6);
                    style.setDataFormat(format.getFormat("00.00"));
                    celda.setCellStyle(style);
                    celda.setCellValue(rs.getDouble("fl_importe_total"));
                    
                    //Celda del Plazo
                    celda = fila.createCell( (short) 7);
                    texto = new HSSFRichTextString(plazo);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda del Tipo
                    celda = fila.createCell( (short) 8);
                    String tipo=rs.getString("fp_tipo");
                    texto = new HSSFRichTextString(tipo);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                 //Celda de Banco
                    celda = fila.createCell( (short) 9);
                    String banco=rs.getString("fl_banco");
                    texto = new HSSFRichTextString(banco);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de Estado
                    celda = fila.createCell( (short) 10);
                    String estado=rs.getString("fl_estado");
                    texto = new HSSFRichTextString(estado);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de la fecha de pago
                    celda = fila.createCell( (short) 11);
                    String fecha_pago=rs.getString("fl_fecha_pago");
                    if (fecha_pago !=null && !fecha_pago.equals("")){
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

                    //Celda del nuemro de cuenta
                    celda = fila.createCell( (short) 12);
                    String num_cuenta=rs.getString("cl_num_cuenta");
                    texto = new HSSFRichTextString(num_cuenta);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de las observaciones
                    celda = fila.createCell( (short) 13);
                    String observaciones=rs.getString("fl_observaciones");
                    texto = new HSSFRichTextString(observaciones);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda CIF
                    celda = fila.createCell( (short) 14);
                    String cif=rs.getString("cl_DNI_CIF");
                    texto = new HSSFRichTextString(cif);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda CP
                    celda = fila.createCell( (short) 15);
                    String cp=rs.getString("cl_cod_postal");
                    texto = new HSSFRichTextString(cp);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de la fecha de MODIFICACIÓN
                    celda = fila.createCell( (short) 16);
                    String actualizado = rs.getString("date_modified");
                    temp = null;
                    String [] tempHora = null;
                    if (actualizado !=null && !actualizado.equals(""))
                    {
                        temp = actualizado.split("\\-");
                        anyo=temp[0];
                        mes=temp[1];
                        dia=temp[2].substring(0,2);

                        tempHora = actualizado.split("\\ ");
                        String hora = tempHora[1].substring(0,5);

                        nueva=anyo+"-"+mes+"-"+dia+" "+hora;
                        
                        texto = new HSSFRichTextString(nueva);
                        celda.setCellStyle(cs2);
                        celda.setCellValue(texto);
                    }

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

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores
            jTable1.setRowHeight(20);

            if (column == 0 ||column == 1 ||column == 2 ||column == 10)
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 4 ||column == 5 ||column == 6)
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

            if (column == 11 || column == 12 || column == 13 )
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
/*            String[] values = new String[]{"","PTE", "COBRADO", "DEVOLUCIÓN", "APLAZADO", "INCOBRABLE"};

            TableColumn col = table.getColumnModel().getColumn(column);

            if (column == 9)
            {
                col.setCellEditor(new MyComboBoxEditor(values));
                col.setCellRenderer(new MyComboBoxRenderer(values));
                jTable1.setValueAt(values, row, column);
            }*/

            TableColumn col = table.getColumnModel().getColumn(column);

            if (column == 10)
            {
                JComboBox comboBox = new JComboBox();
                comboBox.addItem("");
                comboBox.addItem("PTE");
                comboBox.addItem("COBRADO");
                comboBox.addItem("DEVOLUCION");
                comboBox.addItem("DUDOSO");
                comboBox.addItem("APLAZADO");
                comboBox.addItem("INCOBRABLE");
                col.setCellEditor(new DefaultCellEditor(comboBox));

                //Set up tool tips for the sport cells.
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setToolTipText("Click for combo box");
                col.setCellRenderer(renderer);
            }

            if(column == 9){
                JComboBox comboBoxBc = new JComboBox();
                comboBoxBc.addItem("");
                comboBoxBc.addItem("LC");
                comboBoxBc.addItem("OB");
                comboBoxBc.addItem("PP");
                comboBoxBc.addItem("4");
                comboBoxBc.addItem("5");
                comboBoxBc.addItem("6");
                comboBoxBc.addItem("7");
                comboBoxBc.addItem("8");
                comboBoxBc.addItem("9");
                comboBoxBc.addItem("10");
                col.setCellEditor(new DefaultCellEditor(comboBoxBc));

                //Set up tool tips for the sport cells.
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setToolTipText("Click for combo box");
                col.setCellRenderer(renderer);
             }

            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table. getValueAt(row, 3). toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
                col.setCellEditor(null);
                col.setCellRenderer(null);
                //table.getCellEditor(table.getRowCount(), 9).cancelCellEditing();
            }

//            jTable1.setRowSelectionAllowed(true);
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
     * Modifica los campos de la tesorería del cliente
     * @param fl_id
     * @param estado
     * @param fechaPago
     * @param banco
     * @param observaciones
     * @return @throws SQLException
     */
    public boolean  modificarTesoreria(int fl_id, String estado, String fechaPago, Object banco, String observaciones) throws SQLException
    {
        boolean rsUpdate = false;
        String query = "UPDATE fl_factura_cliente SET fl_estado = '"+estado+"'";
        if(!fechaPago.equals(""))
        {
            query = query + ", fl_fecha_pago = '"+fechaPago+"'";
        }
        query = query + ", fl_banco = '"+banco+"' , fl_observaciones = '"+observaciones+"' WHERE fl_id = "+fl_id;
        System.out.println(query);
        rsUpdate = CSDesktop.datos.manipuladorDatos(query);

        return rsUpdate;
    }

}