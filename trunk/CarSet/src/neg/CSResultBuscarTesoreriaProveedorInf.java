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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
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
import data.BeanAuxInformeTesoreria;
import java.util.HashMap;

/**
 *
 * @author depr102
 */
public class CSResultBuscarTesoreriaProveedorInf extends javax.swing.JPanel
{
    private String consulta = "";
    ArrayList lista=new ArrayList();
    ArrayList pedidos=new ArrayList();
    static String fVencimiento = "";
    static HashMap<Integer,BeanAuxInformeTesoreria> listaResultadosDefinitivos = new HashMap<Integer,BeanAuxInformeTesoreria>();
    static ArrayList listaPedidosDefinitivos = new ArrayList();

    public CSResultBuscarTesoreriaProveedorInf(HashMap <Integer, BeanAuxInformeTesoreria> listaResul,ArrayList listaResultados) throws UnknownHostException, FileNotFoundException, IOException, ParseException
    {
        //consulta = query;
        TablaModeloTesoreria modelo = new TablaModeloTesoreria();
        //ResultSet rs = CSDesktop.datos.select(query);

        //Paso la lista de resultados a una variable global para poder hacer la exportacion a Excell
        listaResultadosDefinitivos = (HashMap <Integer, BeanAuxInformeTesoreria>)listaResul.clone();
        listaPedidosDefinitivos = (ArrayList) listaResultados.clone();
        
        
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

        modelo.setColumnIdentifiers(new String[] {"NOMBRE PROVEEDOR", "P.VALIDADOS", "P.PENDIENTES" , "P.NO FACTURADOS" , "TOTAL", "IMP FACTURAS", "FACT PAGADAS", "FACT PENDIENTES", "FACT CLIENTE", "COBROS CL", "DIFERENCIA"});
        int numeroFila = 0;
        double total = 0;
        double totalIva = 0;
        double totalIrpf = 0;
        double totalImporte = 0;

       
            for(int i=0; i<listaResultados.size();i++)
            //while (rs.next())
            {
                BeanAuxInformeTesoreria beanAuxInfTesoreria = (BeanAuxInformeTesoreria) listaResul.get(listaResultados.get(i));
            
               
                
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                double pedidosValidados = 0;
                double pedidosPendientes = 0;
                double pedidosNoFacturados = 0;
                double pedidosTotales = 0;
                double pedidosValidadosR = 0;
                double pedidosPendientesR = 0;
                double pedidosNoFacturadosR = 0;
                double pedidosTotalesR = 0;
                double importeFacturas = 0;
                double importeFacturasPagadas = 0;
                double importeFacturasPendientes = 0;
                double importeFacturasPendientesCliente = 0;
                double importeFacturasPendientesClienteR = 0;
                double importeFacturasCobradasCliente = 0;
                double importeFacturasCobradasClienteR = 0;
                double importeFacturasCliente = 0;
                double importeFacturasClienteR = 0;
                double importeFacturasR = 0;
                double importeFacturasPagadasR = 0;
                double importeFacturasPendientesR = 0;
                String resPedidosValidados="";
                String resPedidosValidadosF="";
                String resPedidosPendientes="";
                String resPedidosPendientesF="";
                String resPedidosNoFacturados="";
                String resPedidosNoFacturadosF="";
                String resPedidosTotales="";
                String resPedidosTotalesF="";
                String resImporteFacturas="";
                String resImporteFacturasF="";
                String resImporteFacturasPagadas="";
                String resImporteFacturasPagadasF="";
                String resImporteFacturasPendientes="";
                String resImporteFacturasPendientesF="";
                String resImporteFacturasPendientesCliente="";
                String resImporteFacturasPendientesClienteF="";
                String resImporteFacturasCobradasCliente="";
                String resImporteFacturasCobradasClienteF="";
                String resImporteFacturasCliente="";
                String resImporteFacturasClienteF="";
                
                for (int k = 0; k < 11; k++)
                {
                    if((k==0))
                    {
                        datosFila[j] = beanAuxInfTesoreria.getNombreProveedor();
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==1))
                    {
                        pedidosValidados = beanAuxInfTesoreria.getPedidosValidados();
                        pedidosValidadosR=Utilidades.redondear(pedidosValidados, 2);
                        resPedidosValidados=Utilidades.separadorMiles(String.valueOf(pedidosValidadosR));
                        resPedidosValidadosF=Utilidades.dosDecimales(resPedidosValidados);
                        datosFila[j] = resPedidosValidadosF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==2))
                    {
                        pedidosPendientes = beanAuxInfTesoreria.getPedidosPendientes();
                        pedidosPendientesR=Utilidades.redondear(pedidosPendientes, 2);
                        resPedidosPendientes=Utilidades.separadorMiles(String.valueOf(pedidosPendientesR));
                        resPedidosPendientesF=Utilidades.dosDecimales(resPedidosPendientes);
                        datosFila[j] = resPedidosPendientesF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==3))
                    {
                        pedidosNoFacturados = beanAuxInfTesoreria.getPedidosNoFacturados();
                        pedidosNoFacturadosR=Utilidades.redondear(pedidosNoFacturados, 2);
                        resPedidosNoFacturados=Utilidades.separadorMiles(String.valueOf(pedidosNoFacturadosR));
                        resPedidosNoFacturadosF=Utilidades.dosDecimales(resPedidosNoFacturados);
                        datosFila[j] = resPedidosNoFacturadosF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==4))
                    {
                        pedidosTotales = pedidosValidados + pedidosPendientes + pedidosNoFacturados;
                        pedidosTotalesR=Utilidades.redondear(pedidosTotales, 2);
                        resPedidosTotales=Utilidades.separadorMiles(String.valueOf(pedidosTotalesR));
                        resPedidosTotalesF=Utilidades.dosDecimales(resPedidosTotales);
                        datosFila[j] = resPedidosTotalesF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==5))
                    {
                        importeFacturas = beanAuxInfTesoreria.getImporteFacturas();
                        importeFacturasR = Utilidades.redondear(importeFacturas,2 );
                        resImporteFacturas=Utilidades.separadorMiles(String.valueOf(importeFacturasR));
                        resImporteFacturasF=Utilidades.dosDecimales(resImporteFacturas);
                        datosFila[j] = resImporteFacturasF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==6))
                    {
                        importeFacturasPagadas = beanAuxInfTesoreria.getImporteFacturasPagadas();
                        importeFacturasPagadasR = Utilidades.redondear(importeFacturasPagadas,2 );
                        resImporteFacturasPagadas=Utilidades.separadorMiles(String.valueOf(importeFacturasPagadasR));
                        resImporteFacturasPagadasF=Utilidades.dosDecimales(resImporteFacturasPagadas);
                        datosFila[j] = resImporteFacturasPagadasF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==7))
                    {
                        importeFacturasPendientes = importeFacturas - importeFacturasPagadas;
                        importeFacturasPendientesR = Utilidades.redondear(importeFacturasPendientes,2 );
                        resImporteFacturasPendientes=Utilidades.separadorMiles(String.valueOf(importeFacturasPendientesR));
                        resImporteFacturasPendientesF=Utilidades.dosDecimales(resImporteFacturasPendientes);
                        datosFila[j] = resImporteFacturasPendientesF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }                               
                    else if((k==8))
                    {
                        importeFacturasPendientesCliente = beanAuxInfTesoreria.getImporteFacturasPendientesCliente();
                        importeFacturasPendientesClienteR = Utilidades.redondear(importeFacturasPendientesCliente,2 );
                        resImporteFacturasPendientesCliente=Utilidades.separadorMiles(String.valueOf(importeFacturasPendientesClienteR));
                        resImporteFacturasPendientesClienteF=Utilidades.dosDecimales(resImporteFacturasPendientesCliente);
                        datosFila[j] = resImporteFacturasPendientesClienteF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }    
                    else if((k==9))
                    {
                        importeFacturasCobradasCliente = beanAuxInfTesoreria.getImporteFacturasCobradasCliente();
                        importeFacturasCobradasClienteR = Utilidades.redondear(importeFacturasCobradasCliente,2 );
                        resImporteFacturasCobradasCliente=Utilidades.separadorMiles(String.valueOf(importeFacturasCobradasClienteR));
                        resImporteFacturasCobradasClienteF=Utilidades.dosDecimales(resImporteFacturasCobradasCliente);
                        datosFila[j] = resImporteFacturasCobradasClienteF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }
                    else if((k==10))
                    {
                        importeFacturasCliente = importeFacturasPendientesCliente - importeFacturasCobradasCliente;
                        importeFacturasClienteR = Utilidades.redondear(importeFacturasCliente,2 );
                        resImporteFacturasCliente=Utilidades.separadorMiles(String.valueOf(importeFacturasCliente));
                        resImporteFacturasClienteF=Utilidades.dosDecimales(resImporteFacturasCliente);
                        datosFila[j] = resImporteFacturasClienteF;
                        System.out.println("Dato" + k + " " + datosFila[j]);
                         
                    }                    
                    j++;
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
           // rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
           
       
           modelo.addRow(datosFilaTotal);

       
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultTesoreriaProveedor = new JInternalFrame("Resultado Búsqueda Tesorería Proveedor", true, false, false, true );
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

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(350);
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
        columna10.setPreferredWidth(80);
        

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        //jTable1.getTableHeader().setPreferredSize(new Dimension(jTable1.getTableHeader().getWidth(),26));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonExportar)
                        .addGap(631, 631, 631)
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
            HSSFSheet hoja = libro.createSheet("Informe Tesoreria Proveedor");
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
            
            
           // ResultSet rs = CSDesktop.datos.select(consulta);

            crearFilaHojaExcel(libro, hoja, num_fila, cs2,cs3);
            FileOutputStream elFichero = null;
            elFichero = new FileOutputStream("c:\\TesoreriaProveedorInforme.xls");
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
                HSSFRichTextString texto = new HSSFRichTextString("NOMBRE PROVEEDOR");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 0, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 1);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("P.VALIDADOS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 1, (short) ((80 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 2);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("P.PENDIENTES");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 2, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 3);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("P.NO FACTURADOS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 3, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 4);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("TOTAL");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 4, (short) ((250 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 5);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("IMPORTE FACTURAS");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 5, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 6);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("FACTURAS CLIENTE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 6, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 7);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("COBROS CLIENTE");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 7, (short) ((100 * 2) / ((double) 1 / 20)) );

                celda = fila.createCell( (short) 8);
                celda.setCellStyle(cs);
                texto = new HSSFRichTextString("DIFERENCIA");
                celda.setCellValue(texto);
                hoja.setColumnWidth((short) 8, (short) ((100 * 2) / ((double) 1 / 20)) );              
	}

        private static void crearFilaHojaExcel(HSSFWorkbook libro,HSSFSheet hoja, int num_fila, HSSFCellStyle cs2,HSSFCellStyle cs3) throws SQLException, UnknownHostException
	{
		HSSFRow fila = null;
		HSSFCell celda = null;
		HSSFRichTextString texto = null;
                int num_fila_aux=2;

                for (int j=0;j<listaResultadosDefinitivos.size();j++)
                {                    
                    
                    BeanAuxInformeTesoreria beanInforme = (BeanAuxInformeTesoreria) listaResultadosDefinitivos.get(listaPedidosDefinitivos.get(j));
                    
                    // Se crea una fila dentro de la hoja
                    fila = hoja.createRow(num_fila);

                    //Celda de nombre del Proveedor
                    celda = fila.createCell( (short) 0);                    
                    texto = new HSSFRichTextString(beanInforme.getNombreProveedor());
                    celda.setCellStyle(cs2);
                    celda.setCellValue(texto);

                    //Celda de pedidos validados                                        
                    celda = fila.createCell( (short) 1);                  
                    String pedidosValidados = String.valueOf(beanInforme.getPedidosValidados());
                    texto = new HSSFRichTextString(pedidosValidados);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos pendientes
                    celda = fila.createCell( (short) 2);
                    String pedidosPendientes = String.valueOf(beanInforme.getPedidosPendientes());
                    texto = new HSSFRichTextString(pedidosPendientes);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos no facturados
                    celda = fila.createCell( (short) 3);
                    String pedidosNoFacturados = String.valueOf(beanInforme.getPedidosNoFacturados());
                    texto = new HSSFRichTextString(pedidosNoFacturados);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda de pedidos totales
                    celda = fila.createCell( (short) 4);
                    String pedidosTotal = String.valueOf(beanInforme.getPedidosTotales());
                    texto = new HSSFRichTextString(pedidosTotal);
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
                    String importeFacturas = String.valueOf(beanInforme.getImporteFacturas());
                    texto = new HSSFRichTextString(importeFacturas);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);

                    //Celda IVA
                    celda = fila.createCell( (short) 6);
                    String importeFacturasCliente = String.valueOf(beanInforme.getImporteFacturasPendientesCliente());
                    texto = new HSSFRichTextString(importeFacturasCliente);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);
                    //Celda IRPF
                    celda = fila.createCell( (short) 7);
                   String importeFacturasClienteCobradas = String.valueOf(beanInforme.getImporteFacturasCobradasCliente());
                    texto = new HSSFRichTextString(importeFacturasClienteCobradas);
                    celda.setCellStyle(cs3);
                    celda.setCellValue(texto);
  
                    //Celda Total
                    celda = fila.createCell( (short) 8);
                     String importeFacturasDiferencia = String.valueOf(beanInforme.getImporteFacturasPendientes());
                    texto = new HSSFRichTextString(importeFacturasDiferencia);
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

            if (column == 12 || column == 13 || column == 14 )
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
            String[] valuesBanco = new String[]{"","LC", "OP"};

            //System.out.println(table.getRowCount()+" / "+fila);
            TableColumn col = table.getColumnModel().getColumn(column);
            TableColumn colB = table.getColumnModel().getColumn(column);

            if (column == 12)
            {
                col.setCellEditor(new MyComboBoxEditor(values));
                col.setCellRenderer(new MyComboBoxRenderer(values));
                jTable1.setValueAt(value, row, column);
            }
            
//            if (column == 13)
//            {
//               col.setCellEditor(new DateEditer());
//            }
            else if(column == 14)
            {
                colB.setCellEditor(new MyComboBoxEditor(valuesBanco));
                colB.setCellRenderer(new MyComboBoxRenderer(valuesBanco));
                jTable1.setValueAt(value, row, column);
            }
            
            //si no cumplen esa condicion pongo las celdas en color blanco
/*            if (table. getValueAt(row, 4). toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
                col.setCellEditor(null);
                col.setCellRenderer(null);
            }
*/
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
}