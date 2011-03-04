/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import data.BeanFactura;
import data.BeanCliente;
import data.Cliente;
import java.net.UnknownHostException;
import net.sf.jasperreports.engine.JRException;
import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author depr102
 */
public class CSResultBuscarPedidosLibres extends javax.swing.JPanel
{
    Object[] datosFila = null;
    TablaModelo modelo = new TablaModelo();
    ArrayList lista=new ArrayList();
    ArrayList pedidos=new ArrayList();
    BeanCliente bCliente=new BeanCliente();
    String fechaSI="";
    String fechaSF="";
    int clienteID=0;
    Date hoy = new Date();


    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarPedidosLibres(String query,BeanCliente beanCliente, String fechaI,String fechaF)
    {
        modelo = new TablaModelo();
       
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

        modelo.setColumnIdentifiers(new String[] {"NUM", "FECHA", "CLIENTE", "DESCRIPCION", "IMPORTE"});

        int numeroFila = 0;
        try {
            while (rs.next()) {

                BeanFactura campos = new BeanFactura();

                    campos.setNumPedido(rs.getLong("pe_num"));
                    campos.setFecha(rs.getString("pe_fecha"));                                                                                                                                                                                                                                                
                    campos.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));                                        
                    campos.setDescripcion(rs.getString("pe_descripcion"));                                                            
                    lista.add(campos);
                    pedidos.add(rs.getLong("pe_num"));

                datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 5; k++) {
                    if (k==0 ||k==1 || k == 2 || k==3 || k == 4) {
                        if(k==1)
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
        } catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Pedidos con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultFacturaPedido = new JInternalFrame("Resultado Factura Pedidos", true, false, false, true );
            CSDesktop.ResultFacturaPedido.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultFacturaPedido.pack();
            //CSDesktop.BuscarPedido.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultFacturaPedido );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultFacturaPedido.getSize();
            CSDesktop.ResultFacturaPedido.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultFacturaPedido.setVisible( true );
        }
        initComponents();
        jDateFechaFactura.setDate(hoy);
        jTable1.setModel(modelo);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(10);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(30);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(200);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(400);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(30);
        
       

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        
        

        bCliente=beanCliente;
        fechaSI=fechaI;
        fechaSF=fechaF;
        String clienteSID=beanCliente.getCl_id();
        clienteID=Integer.parseInt(clienteSID);

        
       
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
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
        jButtonGenerar = new javax.swing.JButton();
        jButtonPrev = new javax.swing.JButton();
        lFechaIni1 = new javax.swing.JLabel();
        jDateFechaFactura = new com.toedter.calendar.JDateChooser();
        lIVA = new javax.swing.JLabel();
        jTextIVA = new javax.swing.JTextField();
        jTextIRPF = new javax.swing.JTextField();
        lIRPF = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

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

        jButtonGenerar.setText("Generar");
        jButtonGenerar.setName("jButtonGenerar"); // NOI18N
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

        jButtonPrev.setText("Previsualizar");
        jButtonPrev.setName("jButtonPrev"); // NOI18N
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        lFechaIni1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni1.setText("Fecha Factura ");
        lFechaIni1.setName("lFechaIni1"); // NOI18N

        jDateFechaFactura.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFactura.setName("jDateFechaFactura"); // NOI18N

        lIVA.setForeground(new java.awt.Color(0, 0, 100));
        lIVA.setText("I.V.A.");
        lIVA.setName("lIVA"); // NOI18N

        jTextIVA.setText("18");
        jTextIVA.setName("jTextIVA"); // NOI18N
        jTextIVA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextIVAFocusLost(evt);
            }
        });

        jTextIRPF.setText("7");
        jTextIRPF.setName("jTextIRPF"); // NOI18N
        jTextIRPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextIRPFFocusLost(evt);
            }
        });

        lIRPF.setForeground(new java.awt.Color(0, 0, 100));
        lIRPF.setText("IRPF");
        lIRPF.setName("lIRPF"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jButtonCerrar)
                        .addGap(209, 209, 209))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lFechaIni1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(lIVA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(lIRPF)
                                .addGap(18, 18, 18)
                                .addComponent(jTextIRPF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lFechaIni1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lIVA)
                        .addComponent(jTextIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextIRPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lIRPF)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrev)
                    .addComponent(jButtonGenerar)
                    .addComponent(jButtonCerrar))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultFacturaPedido.dispose();
        CSDesktop.menuFacturaClientePedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
         else if (celdas==1)
        {

            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres generar la Factura y un nuevo numero?");

            if (JOptionPane.OK_OPTION == confirmado) {
                int numero = 0;
                String fechaFac="";
                String query = "Select max(fl_id) from fl_factura_cliente";
                ResultSet rs = CSDesktop.datos.select(query);
                try {
                    while (rs.next()) {
                        numero =Integer.valueOf(rs.getInt("max(fl_id)"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println("Voy por aqui");
                    int longitud=jTable1.getSelectedRow();

                    Calendar fechaCalendar = jDateFechaFactura.getCalendar();
                    if (fechaCalendar!=null)
                    {
                        Date fecha = fechaCalendar.getTime();
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                        fechaFac=formatoDeFecha.format(fecha);
                    }
                     String IVA = jTextIVA.getText();
                     String IRPF = jTextIRPF.getText();
                    BeanFactura factura=new BeanFactura();
                    factura=(BeanFactura)lista.get(longitud);
                    long pedido=(Long)pedidos.get(longitud);
                    ArrayList listaPedidos=new ArrayList();
                    listaPedidos.add(pedido);
                    ArrayList listaFacturas=new ArrayList();
                    listaFacturas.add(factura);
                    CSLanzarFactura facturaFinal = new CSLanzarFactura();
                    try {

                        facturaFinal.lanzarLibre(listaFacturas, bCliente, fechaFac, numero+1, clienteID, fechaSI, fechaSF, listaPedidos,1,IVA,IRPF);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                        Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
         }
}//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        /*try {

            //LanzarFactura(0);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
        else if (celdas>1)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
        else if (celdas==1)
        {   
            String fechaFac="";
            Calendar fechaCalendar = jDateFechaFactura.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaFac=formatoDeFecha.format(fecha);
            }
            String IVA = jTextIVA.getText();
            String IRPF = jTextIRPF.getText();
            int longitud=jTable1.getSelectedRow();
            BeanFactura factura=new BeanFactura();
            factura=(BeanFactura)lista.get(longitud);
            long pedido=(Long)pedidos.get(longitud);
            ArrayList listaPedidos=new ArrayList();
            listaPedidos.add(pedido);
            ArrayList listaFacturas=new ArrayList();
            listaFacturas.add(factura);
            CSLanzarFactura facturaFinal = new CSLanzarFactura();
            try {                
                facturaFinal.lanzarLibre(listaFacturas, bCliente, fechaFac, 0, clienteID, fechaSI, fechaSF, listaPedidos,1,IVA,IRPF);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarPedidosLibres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_jButtonPrevActionPerformed

    private void jTextIVAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIVAFocusLost
        String MatriculaM = jTextIVA.getText().toUpperCase();
        jTextIVA.setText(MatriculaM);
}//GEN-LAST:event_jTextIVAFocusLost

    private void jTextIRPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextIRPFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIRPFFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JButton jButtonPrev;
    private com.toedter.calendar.JDateChooser jDateFechaFactura;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextIRPF;
    private javax.swing.JTextField jTextIVA;
    private javax.swing.JLabel lFechaIni1;
    private javax.swing.JLabel lIRPF;
    private javax.swing.JLabel lIVA;
    // End of variables declaration//GEN-END:variables

}