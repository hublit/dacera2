package neg;

import com.toedter.calendar.JDateChooser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import data.BeanAuxInformeTesoreriaCliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import utils.TablaModeloTsClienteInf;

/**
 *
 * @author raul
 */
public class CSResultBuscarTesoreriaClienteInf extends javax.swing.JPanel
{
    ArrayList lista = new ArrayList();
    static String fVencimiento = "";
    static HashMap<Integer,BeanAuxInformeTesoreriaCliente> listaResultadosDefinitivos = new HashMap<Integer,BeanAuxInformeTesoreriaCliente>();
    static ArrayList listaPedidosDefinitivos = new ArrayList();

    public CSResultBuscarTesoreriaClienteInf(HashMap <Integer, BeanAuxInformeTesoreriaCliente> listaResul, ArrayList listaResultados) throws UnknownHostException, FileNotFoundException, IOException, ParseException
    {
        TablaModeloTsClienteInf modelo = new TablaModeloTsClienteInf();

        //Paso la lista de resultados a una variable global para poder hacer la exportacion a Excell
        listaResultadosDefinitivos = (HashMap <Integer, BeanAuxInformeTesoreriaCliente>)listaResul.clone();
        listaPedidosDefinitivos = (ArrayList) listaResultados.clone();
        lista.add(listaPedidosDefinitivos);
        
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

        modelo.setColumnIdentifiers(new String[] {"FACTURACIÓN CLIENTES", "F. VENCIDA", "F. PTE VENCER" , "TOTAL (F. PDTES. COBRO)" , "F. COBRADAS", "F. INCOBRABLES", "F. APLAZADAS/DEVOLUCIÓN", "FORMA DE PAGO", "EMAIL", "FECHA", "CONTACTO", "OBSERVACIONES"});
        int numeroFila = 0;
        double total = 0;
        ArrayList aTsCl = null;
       
        for(int i = 0; i < listaResultados.size(); i++)
        {
            System.out.println("Lista resultados: "+listaResultados.get(i));
            BeanAuxInformeTesoreriaCliente beanAuxInfTesoreria = (BeanAuxInformeTesoreriaCliente) listaResul.get(listaResultados.get(i));
            int iCl = ((Integer) listaResultados.get(i)).intValue();
            try {
                aTsCl = datosInformeTesoreria(iCl);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarTesoreriaClienteInf.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] datosFila = new Object[modelo.getColumnCount()];
            int j = 0;
            double facturasValidadas = 0;
            double facturasPteValidar = 0;
            double facturasNoCobradas = 0;
            double facturasCobradas = 0;
            double facturasIncobrables = 0;
            double facturasAplazadas = 0;
            double facturasValidadasR = 0;
            double facturasPteValidarR = 0;
            double pfacturasNoCobradasR = 0;
            double facturasCobradasR = 0;
            double facturasIncobrablesR = 0;
            double facturasAplazadasR = 0;
            String resFacturasValidadas="";
            String resFacturasPteValidar="";
            String resFacturasNoCobradas="";
            String resFacturasCobradas="";
            String resFacturasIncobrables="";
            String resFacturasAplazadas="";

            for (int k = 0; k < 12; k++)
            {
                if((k==0))
                {
                    datosFila[j] = beanAuxInfTesoreria.getNombreCliente();
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==1))
                {
                    facturasValidadas = beanAuxInfTesoreria.getImporteFechaVencida();
                    facturasValidadasR = Utilidades.redondear(facturasValidadas, 2);
                    resFacturasValidadas = Utilidades.separadorMiles(String.valueOf(facturasValidadasR));
                    datosFila[j] = resFacturasValidadas;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==2))
                {
                    facturasPteValidar = beanAuxInfTesoreria.getImportePendienteVencer();
                    facturasPteValidarR=Utilidades.redondear(facturasPteValidar, 2);
                    resFacturasPteValidar=Utilidades.separadorMiles(String.valueOf(facturasPteValidarR));
                    datosFila[j] = resFacturasPteValidar;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==3))
                {
                    facturasNoCobradas = beanAuxInfTesoreria.getImporteTotalPendientesCobro();
                    pfacturasNoCobradasR=Utilidades.redondear(facturasNoCobradas, 2);
                    resFacturasNoCobradas=Utilidades.separadorMiles(String.valueOf(pfacturasNoCobradasR));
                    datosFila[j] = resFacturasNoCobradas;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==4))
                {
                    facturasCobradas = beanAuxInfTesoreria.getImporteFacturasCobradas();
                    facturasCobradasR=Utilidades.redondear(facturasCobradas, 2);
                    resFacturasCobradas=Utilidades.separadorMiles(String.valueOf(facturasCobradasR));
                    datosFila[j] = resFacturasCobradas;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==5))
                {
                    facturasIncobrables = beanAuxInfTesoreria.getImporteFacturasIncobrables();
                    facturasIncobrablesR = Utilidades.redondear(facturasIncobrables,2 );
                    resFacturasIncobrables=Utilidades.separadorMiles(String.valueOf(facturasIncobrablesR));
                    datosFila[j] = resFacturasIncobrables;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==6))
                {
                    facturasAplazadas = beanAuxInfTesoreria.getImporteFacturasAplazadas();
                    facturasAplazadasR = Utilidades.redondear(facturasAplazadas,2 );
                    resFacturasAplazadas=Utilidades.separadorMiles(String.valueOf(facturasAplazadasR));
                    datosFila[j] = resFacturasAplazadas;
                    System.out.println("Dato" + k + " " + datosFila[j]);
                }
                else if((k==7) && aTsCl.size() > 0)
                {
                    System.out.println("Size: "+aTsCl.size());
                    datosFila[j] = aTsCl.get(0);
                    System.out.println("Dato" + k + " " +aTsCl.get(0));
                }
                else if((k==8) && aTsCl.size() > 1)
                {
                    System.out.println("Size: "+aTsCl.size());
                    datosFila[j] = aTsCl.get(1);
                    System.out.println("Dato" + k + " " +aTsCl.get(1));
                }
                else if((k==9) && aTsCl.size() > 2)
                {
                    System.out.println("Size: "+aTsCl.size());
                    String fecha = aTsCl.get(2).toString();
                    String [] temp = null;
                    if (!fecha.equals(""))
                    {
                        temp = fecha.split("\\-");
                        String anyo=temp[0];
                        String mes=temp[1];
                        String dia=temp[2];
                        String nueva = dia+"-"+mes+"-"+anyo;

                        datosFila[j] = nueva;
                    }
                    System.out.println("Dato" + k + " " +aTsCl.get(2));
                }
                else if((k==10) && aTsCl.size() > 3)
                {
                    System.out.println("Size: "+aTsCl.size());
                    datosFila[j] = aTsCl.get(3);
                    System.out.println("Dato" + k + " " +aTsCl.get(3));
                }
                else if((k==11) && aTsCl.size() > 4)
                {
                    System.out.println("Size: "+aTsCl.size());
                    datosFila[j] = aTsCl.get(4);
                    System.out.println("Dato" + k + " " +aTsCl.get(4));
                }

                j++;
            }
            modelo.addRow(datosFila);
            numeroFila++;
        }
        Object[] datosFilaTotal = new Object[modelo.getColumnCount()];

        modelo.addRow(datosFilaTotal);
       
        if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultTesoreriaCliente = new JInternalFrame("Resultado Búsqueda Tesorería Cliente", true, false, false, true );
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

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(300);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(80);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(80);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(80);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(80);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(80);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(80);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(80);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(80);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(80);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(130);

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
      return new Dimension( 1200,650 );
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
                        .addGap(225, 225, 225)
                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(213, 213, 213)
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
                    .addComponent(jButtonExportar)
                    .addComponent(jButtonModificar))
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
            HSSFSheet hoja = libro.createSheet("Informe Tesoreria Cliente");
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

            crearFilaHojaExcel(libro, hoja, num_fila, cs2,cs3);
            FileOutputStream elFichero = null;
            elFichero = new FileOutputStream("c:\\AplicacionCarSet\\TesoreriaClienteInforme.xls");
            libro.write(elFichero);
            elFichero.close();
            elFichero.flush();
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);
            System.out.println("OS current temporary directory is " + tempDir);
            String file = new String("C:\\AplicacionCarSet\\TesoreriaClienteInforme.xls");
            Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);

        }catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }          catch (IOException ex) {
            Logger.getLogger(CSResultBuscarTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExportarActionPerformed

        private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

            String nueva = "";
            boolean tesoreria = false;
            String formaPago = "";
            String email = "";
            String fechaPago = "";
            String contacto = "";
            String observaciones = "";
            int longitud = jTable1.getSelectedRowCount();
            int[] celdas = jTable1.getSelectedRows();

            for(int i = 0; i < lista.size(); i++) {
                System.out.println("celda: "+lista.get(i));
                //ArrayList indices = (ArrayList) listaPedidosDefinitivos.get(celdas[i]);
                ArrayList indices = (ArrayList) lista.get(i);
                int cl_id = Integer.parseInt(indices.get(0).toString());
                formaPago = (String) jTable1.getValueAt(i, 7);
                email = (String) jTable1.getValueAt(i, 8);
                fechaPago = (String) jTable1.getValueAt(i, 9);
        //System.out.println("fecha: "+fechaPago+email);
                if (fechaPago != null && !fechaPago.equals("")) {
                    String [] temp = null;
                    temp = fechaPago.split("\\-");
                    String anyo = temp[2];
                    String mes = temp[1];
                    String dia = temp[0];
                    nueva = anyo+"-"+mes+"-"+dia;
                }else{
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date hoy = new Date();
                    nueva = formatter.format(hoy);
                }
                contacto = (String) jTable1.getValueAt(i, 10);
                observaciones = (String) jTable1.getValueAt(i, 11);
       //System.out.println("Observaciones: "+observaciones);

                try {
                    //guardamos las modificaciones en la bd
                    tesoreria = modificarTesoreria(cl_id, nueva, email, contacto, observaciones, formaPago);
                } catch (SQLException ex) {
                    Logger.getLogger(CSResultBuscarTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(tesoreria) {
                jButtonModificar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonModificar.setEnabled(true);
            } else {
                jButtonModificar.setEnabled(false);
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, mensaje);
                jButtonModificar.setEnabled(true);
                CSDesktop.ResultTesoreriaCliente.dispose();
                CSDesktop.BuscarTesoreriaClienteInf.dispose();
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
                HSSFRichTextString texto = new HSSFRichTextString("NOMBRE CLIENTE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 0, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 1);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.VENCIDAS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 1, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 2);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.PENDIENTES VENCER");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 2, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 3);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.NO COBRADAS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 3, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 4);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.COBRADAS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 4, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 5);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.INCOBRABLES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 5, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 6);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("F.APLAZADAS/DEVOLUCIÓN");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 6, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 7);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FORMA PAGO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 7, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("EMAIL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 8, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 9);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FECHA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 9, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 10);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("CONTACTO");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 10, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 11);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("OBSERVACIONES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 11, (short) ((100 * 2) / ((double) 1 / 20)) );
	}

        private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
	{
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;
                int num_fila_aux=2;

                for (int j=0;j<listaResultadosDefinitivos.size();j++)
                {
                    BeanAuxInformeTesoreriaCliente beanInforme = (BeanAuxInformeTesoreriaCliente) listaResultadosDefinitivos.get(listaPedidosDefinitivos.get(j));
                    
                    // Se crea una fila dentro de la hoja
                    fila = hoja.createRow(num_fila);

                    //Celda de nombre del Proveedor
                    celda = fila.createCell( (short) 0);                    
                    texto = new HSSFRichTextString(beanInforme.getNombreCliente());
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    //Celda de pedidos validados                                        
                    celda = fila.createCell( (short) 1);                  
                    String facturasVencidas = String.valueOf(beanInforme.getImporteFechaVencida());
                    texto = new HSSFRichTextString(facturasVencidas);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos pendientes
                    celda = fila.createCell( (short) 2);
                    String facturasPteVencer = String.valueOf(beanInforme.getImportePendienteVencer());
                    texto = new HSSFRichTextString(facturasPteVencer);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos no facturados
                    celda = fila.createCell( (short) 3);
                    String facturasPteCobro = String.valueOf(beanInforme.getImporteTotalPendientesCobro());
                    texto = new HSSFRichTextString(facturasPteCobro);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos totales
                    celda = fila.createCell( (short) 4);
                    String facturasCobradas = String.valueOf(beanInforme.getImporteFacturasCobradas());
                    texto = new HSSFRichTextString(facturasCobradas);
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

                    celda = fila.createCell( (short) 5);
                    String facturasIncobrables = String.valueOf(beanInforme.getImporteFacturasIncobrables());
                    texto = new HSSFRichTextString(facturasIncobrables);
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
            
            
            if (column == 1 || column == 2 ||column == 3 || column == 4 ||column == 5 || column == 6 || column == 7 || column == 8|| column == 9|| column == 10)
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

            if (column == 7 || column == 8 || column == 9 || column == 10 || column == 11)
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
            String[] values = new String[]{"","RECIBO DOMICILIADO", "GRUPO", "PROVEEDOR", "NO ENVIAR"};

            TableColumn col = table.getColumnModel().getColumn(column);

            if (column == 7)
            {
                col.setCellEditor(new MyComboBoxEditor(values));
                col.setCellRenderer(new MyComboBoxRenderer(values));
                jTable1.setValueAt(value, row, column);
            }
            
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
     * Busca los campos de la tesorería del cliente
     * @param ts
     * @throws SQLException
     */
    public boolean  buscarTesoreria(int cl_id) throws SQLException
    {
        boolean result = false;
        ResultSet rs = CSDesktop.datos.select("SELECT * FROM ts_tesoreria_informe WHERE cl_id = " + cl_id);
        //Count del resulset
        rs.last();
        //size = rs.getRow();
        rs.beforeFirst();

        return result;
    }

         /**
      * Método para recuperar los datos del la table Tesorería cliente
      * @param cl_id
      * @param fechaPago
      * @param email
      * @param contacto
      * @param observaciones
      * @param formaPago
      * @return
      * @throws SQLException
      */
     public ArrayList datosInformeTesoreria(int cl_id) throws SQLException
     {
         ArrayList aTsCl = new ArrayList();
        System.out.println("Entra: "+ cl_id);
        ResultSet rsTc = CSDesktop.datos.select("SELECT * FROM ts_tesoreria_informe WHERE cl_id = " + cl_id);

        while(rsTc.next())
        {
            aTsCl.add(0, rsTc.getString("ts_forma_pago"));
            aTsCl.add(1, rsTc.getString("ts_email"));
            aTsCl.add(2, rsTc.getDate("ts_fecha"));
            aTsCl.add(3, rsTc.getString("ts_contacto"));
            aTsCl.add(4, rsTc.getString("ts_observaciones"));

        }
        return aTsCl;
    }


        /**
     * Modifica los campos de la tesorería del cliente o inserta si es la primera vez
     * @param ts
     * @throws SQLException
     */
    public boolean  modificarTesoreria(int cl_id,
                                       String fechaPago,
                                       String email,
                                       String contacto,
                                       String observaciones,
                                       String formaPago) throws SQLException
    {
        int size = 0;
        ResultSet rs = CSDesktop.datos.select("SELECT ts_observaciones FROM ts_tesoreria_informe WHERE cl_id = " + cl_id);
        //Count del resulset
        rs.last();
        size = rs.getRow();
        rs.beforeFirst();
        if(size > 0){
            boolean rsUpdate = false;
            String query = "UPDATE ts_tesoreria_informe SET ts_fecha ='"+fechaPago+"', ts_email = '"+email+"', " +
                           "ts_contacto = '"+contacto+"', ts_observaciones = '"+observaciones+"', " +
                           "ts_forma_pago = '"+formaPago+"' WHERE cl_id = "+cl_id;
            System.out.println(query);
            rsUpdate = CSDesktop.datos.manipuladorDatos(query);

            return rsUpdate;
        }else{
            String queryInsert =  "INSERT INTO ts_tesoreria_informe (cl_id, ts_fecha, ts_email, ts_contacto, ts_observaciones, ts_forma_pago) " +
                            "VALUES ("+cl_id+", '"+fechaPago+"', '"+email+"', " +
                            "'"+contacto+"', '"+observaciones+"', '"+formaPago+"')";
            System.out.println(queryInsert);
            boolean rsInsert = CSDesktop.datos.manipuladorDatos(queryInsert);
            return rsInsert;
        }
    }
}