/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import data.BeanProveedor;
import data.BeanTesoreriaProveedor;
import data.Proveedor;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSValidarPedidosProveedor extends javax.swing.JPanel
{
    private  String consulta = "";
    ArrayList pedidos = new ArrayList();
    String pr_id = "";
    double totalProveedor = 0;
    /** Creates new form ABResultBuscarPedido */
    public CSValidarPedidosProveedor(String query) throws UnknownHostException, FileNotFoundException, IOException
    {
        consulta = query;
System.out.println("En el result: "+query);
        TablaModelo modelo = new TablaModelo();
        ArrayList lista=new ArrayList();
        String fechaSI="";
        String fechaSF="";
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
        double totalSuplemento = 0;
        double totalMargen = 0;

        try
        {
            while (rs.next()) 
            {
                pedidos.add(rs.getLong("pe_num"));
                Object[] datosFila = new Object[modelo.getColumnCount()];

                int j = 0;
                 double ta_es_cl=0;
                 double ta_es_pr=0;
                 double s_especial=0;
                 double importeServicioD = 0;
                 double suple=0;
                 double ganancia=0;
                 pr_id = rs.getString("pr_id");
                 String fechaPe = rs.getString("pe_fecha");
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
//            CSDesktop.BuscarPedido.dispose();
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

        jTable1.setAutoCreateRowSorter(true);

    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1150,650 );
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
        jButtonValidar = new javax.swing.JButton();
        jLabelNumFa = new javax.swing.JLabel();
        jTextFieldNFa = new javax.swing.JTextField();
        jDateChooserFechaFa = new com.toedter.calendar.JDateChooser();
        jLabelFechaFa = new javax.swing.JLabel();
        jLabelAnyoIva = new javax.swing.JLabel();
        jComboBoxAnyoIva = new javax.swing.JComboBox();
        jLabelObservaciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservaciones = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelIvaTrimestre = new javax.swing.JLabel();
        jComboBoxIvaTrimestre = new javax.swing.JComboBox();
        jLabelIvaTipo = new javax.swing.JLabel();
        jComboBoxIvaTipo = new javax.swing.JComboBox();
        jLabelFechaCont = new javax.swing.JLabel();
        jDateChooserFechaCont = new com.toedter.calendar.JDateChooser();

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

        jButtonValidar.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButtonValidar.setForeground(new java.awt.Color(0, 0, 100));
        jButtonValidar.setText("Validar Pedidos");
        jButtonValidar.setName("jButtonValidar"); // NOI18N
        jButtonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarActionPerformed(evt);
            }
        });

        jLabelNumFa.setForeground(new java.awt.Color(0, 0, 100));
        jLabelNumFa.setText("Núm. Factura");
        jLabelNumFa.setName("jLabelNumFa"); // NOI18N

        jTextFieldNFa.setName("jTextFieldNFa"); // NOI18N
        jTextFieldNFa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNFaActionPerformed(evt);
            }
        });

        jDateChooserFechaFa.setName("jDateChooserFechaFa"); // NOI18N

        jLabelFechaFa.setForeground(new java.awt.Color(0, 0, 100));
        jLabelFechaFa.setText("Fecha factura");
        jLabelFechaFa.setName("jLabelFechaFa"); // NOI18N

        jLabelAnyoIva.setForeground(new java.awt.Color(0, 0, 100));
        jLabelAnyoIva.setText("IVA año");
        jLabelAnyoIva.setName("jLabelAnyoIva"); // NOI18N

        jComboBoxAnyoIva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2011", "2010" }));
        jComboBoxAnyoIva.setName("jComboBoxAnyoIva"); // NOI18N

        jLabelObservaciones.setForeground(new java.awt.Color(0, 0, 100));
        jLabelObservaciones.setText("Observaciones");
        jLabelObservaciones.setName("jLabelObservaciones"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextAreaObservaciones.setColumns(20);
        jTextAreaObservaciones.setRows(5);
        jTextAreaObservaciones.setName("jTextAreaObservaciones"); // NOI18N
        jScrollPane2.setViewportView(jTextAreaObservaciones);

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabelIvaTrimestre.setForeground(new java.awt.Color(0, 0, 100));
        jLabelIvaTrimestre.setText("IVA Trimestre");
        jLabelIvaTrimestre.setName("jLabelIvaTrimestre"); // NOI18N

        jComboBoxIvaTrimestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 T", "2 T", "3 T", "4 T", " " }));
        jComboBoxIvaTrimestre.setName("jComboBoxIvaTrimestre"); // NOI18N
        jComboBoxIvaTrimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIvaTrimestreActionPerformed(evt);
            }
        });

        jLabelIvaTipo.setForeground(new java.awt.Color(0, 0, 100));
        jLabelIvaTipo.setText("IVA Tipo");
        jLabelIvaTipo.setName("jLabelIvaTipo"); // NOI18N

        jComboBoxIvaTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18%", "8%", "4%", "5%", "2%", " " }));
        jComboBoxIvaTipo.setName("jComboBoxIvaTipo"); // NOI18N

        jLabelFechaCont.setForeground(new java.awt.Color(0, 0, 100));
        jLabelFechaCont.setText("Fecha contabilización");
        jLabelFechaCont.setName("jLabelFechaCont"); // NOI18N

        jDateChooserFechaCont.setName("jDateChooserFechaCont"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jButtonValidar, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addGap(269, 269, 269)
                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelFechaCont, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFechaCont, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIvaTrimestre, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIvaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabelAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabelIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabelFechaFa, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserFechaFa, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jLabelNumFa, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNFa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserFechaCont, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelObservaciones)
                                .addComponent(jLabelNumFa)
                                .addComponent(jTextFieldNFa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelFechaCont)
                            .addComponent(jDateChooserFechaFa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaFa))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIvaTrimestre)
                    .addComponent(jComboBoxIvaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIvaTipo)
                    .addComponent(jComboBoxIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAnyoIva))
                .addGap(51, 51, 51)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValidar)
                    .addComponent(jButtonCerrar))
                .addGap(34, 34, 34))
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultPedido.dispose();
        CSDesktop.menuBuscarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarActionPerformed

        int celdas = jTable1.getSelectedRowCount();
        if(celdas == 0)
        {
            jButtonValidar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonValidar.setEnabled(true);
        }
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres validar los pedidos seleccionados?");


            if (JOptionPane.OK_OPTION == confirmado)
            {
                int numero = 0;
                String num_carset = "";
                String fechaFac = "";
                String fechaCont = "";
                String query = "SELECT max(tr_id), tr_num_carset FROM tr_tesoreria_proveedor";
                ResultSet rs = CSDesktop.datos.select(query);
                try
                {
                    while (rs.next())
                    {
                        numero = Integer.valueOf(rs.getInt("max(tr_id)"));
                        num_carset = rs.getString("tr_num_carset");
                    }
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("Voy por aqui");
                    int longitud = jTable1.getSelectedRow();

                    Calendar fechaCalendar = jDateChooserFechaFa.getCalendar();
//                    if (fechaCalendar!=null)
//                    {
//                        Date fecha = fechaCalendar.getTime();
//                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//                        fechaFac=formatoDeFecha.format(fecha);
//                    }
                    Calendar fechaConta = jDateChooserFechaCont.getCalendar();
//                    if (fechaConta!=null)
//                    {
//                        Date fecha = fechaConta.getTime();
//                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//                        fechaCont=formatoDeFecha.format(fecha);
//                    }
                    String numFa = jTextFieldNFa.getText();

                    String[] arrayNumCarset = num_carset.split("/");
                    //Comprobar
                    int numCarset = (Integer.parseInt(arrayNumCarset[2]) + 1);

                    String observaciones = jTextAreaObservaciones.getText();
                    BeanTesoreriaProveedor tsProveedor = new BeanTesoreriaProveedor();
                    tsProveedor.setTr_id(numero);
                    tsProveedor.setTr_fecha(fechaCalendar);
                    tsProveedor.setTr_fh_vencimiento(fechaConta);
                    tsProveedor.setTr_num(numFa);
                    tsProveedor.setTr_num_carset("");
                    tsProveedor.setPr_num(pr_id);
                    tsProveedor.setTr_importe("");
                    tsProveedor.setTr_iva("");
                    tsProveedor.setTr_irpf("");
                    tsProveedor.setTr_importe_neto("");
                    tsProveedor.setTr_observaciones(observaciones);

                    for(int i = 0; i < pedidos.size(); i++)
                    {
                        String queryUpdate = "UPDATE pe_pedidos SET pe_estado = 'Facturado y Validado, pe_num_fa_pr = '"+numFa+"' WHERE pe_num = '"+pedidos.get(i)+"'";
                        boolean rsUp = CSDesktop.datos.manipuladorDatos(queryUpdate);
                    }
                    
                    //guardamos los datos de la tesoreria
                    guardarTesoreria(tsProveedor);
             }
        
    }//GEN-LAST:event_jButtonValidarActionPerformed

        private void jTextFieldNFaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNFaActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jTextFieldNFaActionPerformed

        private void jComboBoxIvaTrimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIvaTrimestreActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jComboBoxIvaTrimestreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JComboBox jComboBoxAnyoIva;
    private javax.swing.JComboBox jComboBoxIvaTipo;
    private javax.swing.JComboBox jComboBoxIvaTrimestre;
    private com.toedter.calendar.JDateChooser jDateChooserFechaCont;
    private com.toedter.calendar.JDateChooser jDateChooserFechaFa;
    private javax.swing.JLabel jLabelAnyoIva;
    private javax.swing.JLabel jLabelFechaCont;
    private javax.swing.JLabel jLabelFechaFa;
    private javax.swing.JLabel jLabelIvaTipo;
    private javax.swing.JLabel jLabelIvaTrimestre;
    private javax.swing.JLabel jLabelNumFa;
    private javax.swing.JLabel jLabelObservaciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaObservaciones;
    private javax.swing.JTextField jTextFieldNFa;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public class MiRender extends DefaultTableCellRenderer
    {
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
                cell.setBackground(fondo);
                cell.setForeground(Color.DARK_GRAY);
            }
            else
            {
                cell.setBackground(Color.white);
                cell.setForeground(Color. BLACK);
            }

            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }
            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table. getValueAt(row, 10).toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
            }

            return cell;
        }
    }


    /**
     * Guardamos en la base de datos los datos seleccionados de tesorería
     * @param ts
     * @param fechaFac
     * @param fechaCont
     * @param numFa
     * @param numCarset
     * @param observaciones
     */
    public void guardarTesoreria(BeanTesoreriaProveedor ts)
    {
        Proveedor pr = new Proveedor();
        BeanProveedor beanPr = new BeanProveedor();
        int pr_num = Integer.parseInt(ts.getPr_num());
        beanPr = pr.getDatosProveedor(pr_num);
        String proveedor = beanPr.getNombre();
        String diasPlazo = beanPr.getPlazoPago();
        String formaPago = beanPr.getFormaPago();
        String numCuenta = beanPr.getNumCuenta();
        String email = beanPr.getEmail();
        String prTipo = beanPr.getTipo();
        Double iva = Double.valueOf(ts.getTr_iva()).doubleValue();
        Double irpf = 0.0;
        if (prTipo.equals("Gruero"))
        {
            irpf = (totalProveedor * 1) /100;
            irpf = Utilidades.redondear(irpf, 2);
        }
        Double totalImporte = totalProveedor + iva + irpf;

        Calendar fechaPago = ts.getTr_fecha();//.add(Calendar.DAY_OF_MONTH, 6);


        String query = "INSERT INTO tr_tesoreria_proveedor (tr_fecha, tr_num, tr_num_carset, pr_num, tr_importe_neto, " +
                                                            "tr_iva, tr_irpf, tr_importe, tr_estado, tr_fecha_pago, " +
                                                            "tr_banco, tr_observaciones) " +
                                                            "VALUES ('"+ts.getTr_fecha()+"', '"+ts.getTr_num()+"', " +
                                                            "'"+ts.getTr_num_carset()+"', '"+pr_num+"', '"+totalProveedor+"', " +
                                                            "'"+iva+"', '"+irpf+"', '"+totalImporte+"', 'PTE', '"+fechaPago+"', " +
                                                            "'"+numCuenta+"', '"+ts.getTr_observaciones()+"')";
        System.out.println(query);
        boolean rsIn = CSDesktop.datos.manipuladorDatos(query);

    }
}