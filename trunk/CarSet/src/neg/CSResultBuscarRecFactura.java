/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import data.BeanCliente;
import data.BeanFactura;
import data.BeanRecFactura;
import data.Cliente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import net.sf.jasperreports.engine.JRException;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


/**
 *
 * @author depr102
 */
public class CSResultBuscarRecFactura extends javax.swing.JPanel
{
    ArrayList facturas = new ArrayList();
    ArrayList lista = new ArrayList();
    ArrayList pedidos = new ArrayList();
    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarRecFactura(String query) throws UnknownHostException, FileNotFoundException, IOException
    {

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

        modelo.setColumnIdentifiers(new String[] {"ID", "NUMERO", "FECHA" , "CLIENTE" , "F.DESDE", "F.HASTA", "IMPORTE"});

        int numeroFila = 0;
        try {
            while (rs.next()) {

                BeanRecFactura recFactura = new BeanRecFactura();

                recFactura.setNumFactura(rs.getString("fl_num"));
                recFactura.setFechaFactura(rs.getString("fl_fecha"));
                recFactura.setCliente(rs.getString("cl_nombre"));
                recFactura.setFechaDesde(rs.getString("fl_fecha_desde"));
                recFactura.setFechaHasta(rs.getString("fl_fecha_hasta"));
                recFactura.setImporte(rs.getString("fl_importe_total"));

                facturas.add(recFactura);

                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 7; k++) {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k == 6 ) {
                        if((k==2) || (k==4)|| (k==5))
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
            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultRecFactura = new JInternalFrame("Resultado Búsqueda Facturas Cliente", true, false, false, true );
            CSDesktop.ResultRecFactura.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultRecFactura.pack();
            CSDesktop.RecuperarFacturaCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultRecFactura );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultRecFactura.getSize();
            CSDesktop.ResultRecFactura.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultRecFactura.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.Object.class, new MiRender());
        //jTable1.setDefaultRenderer (java.util.Date.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.String.class, new MiRender());
        //jTable1.setDefaultRenderer (java.lang.Double.class, new MiRender());

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(170);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(450);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(100);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(100);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(103);
        
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

        
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
    }

     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        int componente = table.getSelectedRow();
        Component comp = getTableCellRendererComponent(table,  value, true, hasFocus, row, col);


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
        jButtonRecuperar = new javax.swing.JButton();
        jButtonAbono = new javax.swing.JButton();

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

        jButtonRecuperar.setText("Recuperar");
        jButtonRecuperar.setName("jButtonRecuperar"); // NOI18N
        jButtonRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecuperarActionPerformed(evt);
            }
        });

        jButtonAbono.setText("Factura Rectificativa");
        jButtonAbono.setName("jButtonAbono"); // NOI18N
        jButtonAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jButtonRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbono)
                    .addComponent(jButtonCerrar)
                    .addComponent(jButtonRecuperar))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultRecFactura.dispose();
        CSDesktop.menuRecuperarFacturaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecuperarActionPerformed
      int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonRecuperar.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperar.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonRecuperar.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperar.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas==1)
        {
            try {
                int seleccion = jTable1.getSelectedRow();
                BeanRecFactura recFacturaAux = new BeanRecFactura();
                recFacturaAux = (BeanRecFactura) facturas.get(seleccion);
                Cliente cliente = new Cliente();
                int cl_id = cliente.getClienteID(recFacturaAux.getCliente());
                BeanCliente beanCliente = new BeanCliente();
                beanCliente = cliente.getDatosFacturaCliente(cl_id);
                beanCliente.setCl_id(String.valueOf(cl_id));
                String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " + "pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " + "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " + "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " + "pe.pe_descripcion, tc.tc_tarifa, sc_entrada_campa, sc_campa " + "FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc, sc_servicios_clientes sc " + "WHERE pe.pe_num = pc.pe_num " + "AND sc.cl_id = pc.cl_id " + "AND tc.tc_fecha_hasta > pe.pe_fecha " + "AND sc.sc_fecha_hasta > pe.pe_fecha " + "AND tc.tc_servicio = pe.pe_servicio " + "AND tc.cl_id = pc.cl_id " + "AND (tc.tc_servicio_origen = pe.pe_servicio_origen " + "OR tc.tc_servicio_origen = pe.pe_servicio_destino) " + "AND (tc.tc_servicio_destino = pe.pe_servicio_destino " + "OR tc.tc_servicio_destino = pe.pe_servicio_origen) " + "AND tc.tc_soporte = pe.pe_soporte " + "AND pe.pe_estado = 'Facturado' " + "AND pe_fecha BETWEEN '" + recFacturaAux.getFechaDesde() + "' AND '" + recFacturaAux.getFechaHasta() + "' " + "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";
                System.out.println(query);
                ResultSet rs = CSDesktop.datos.select(query);
                try {
                    while (rs.next()) {
                        BeanFactura nueva = new BeanFactura();
                        nueva.setNumPedido(rs.getLong("pe_num"));
                        nueva.setFecha(rs.getString("pe_fecha"));
                        nueva.setProvinciaOrigen(rs.getString("pe_servicio_origen"));
                        nueva.setProvinciaDestino(rs.getString("pe_servicio_destino"));
                        nueva.setServicio(rs.getString("pe_servicio"));
                        nueva.setServicioOrigen(rs.getString("pe_servicio_origen"));
                        nueva.setServicioDestino(rs.getString("pe_servicio_destino"));
                        nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                        nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                        nueva.setFactor(rs.getString("fc_id"));
                        nueva.setSoporte(rs.getString("pe_soporte"));
                        nueva.setMatricula(rs.getString("pe_ve_matricula"));
                        nueva.setMarca(rs.getString("pe_ve_marca"));
                        nueva.setModelo(rs.getString("pe_ve_modelo"));
                        nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                        nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                        nueva.setSuplemento(rs.getString("pe_suplemento"));
                        nueva.setDescripcion(rs.getString("pe_descripcion"));
                        nueva.setTarifa(rs.getString("tc_tarifa"));
                        nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                        nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                        nueva.setAux(recFacturaAux.getNumFactura());
                        lista.add(nueva);
                        pedidos.add(rs.getLong("pe_num"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                CSLanzarFactura factura = new CSLanzarFactura();
                factura.lanzar(lista, beanCliente, recFacturaAux.getFechaFactura(), 1, cl_id, recFacturaAux.getFechaDesde(), recFacturaAux.getFechaHasta(), pedidos, 0);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonRecuperarActionPerformed

    private void jButtonAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbonoActionPerformed
      int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonRecuperar.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperar.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonRecuperar.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperar.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas==1)
        {
            try {
                int seleccion = jTable1.getSelectedRow();
                BeanRecFactura recFacturaAux = new BeanRecFactura();
                recFacturaAux = (BeanRecFactura) facturas.get(seleccion);
                Cliente cliente = new Cliente();
                int cl_id = cliente.getClienteID(recFacturaAux.getCliente());
                BeanCliente beanCliente = new BeanCliente();
                beanCliente = cliente.getDatosFacturaCliente(cl_id);
                beanCliente.setCl_id(String.valueOf(cl_id));
                String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " + "pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " + "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " + "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " + "pe.pe_descripcion, tc.tc_tarifa, sc_entrada_campa, sc_campa " + "FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc, sc_servicios_clientes sc " + "WHERE pe.pe_num = pc.pe_num " + "AND sc.cl_id = pc.cl_id " + "AND tc.tc_fecha_hasta > pe.pe_fecha " + "AND sc.sc_fecha_hasta > pe.pe_fecha " + "AND tc.tc_servicio = pe.pe_servicio " + "AND tc.cl_id = pc.cl_id " + "AND (tc.tc_servicio_origen = pe.pe_servicio_origen " + "OR tc.tc_servicio_origen = pe.pe_servicio_destino) " + "AND (tc.tc_servicio_destino = pe.pe_servicio_destino " + "OR tc.tc_servicio_destino = pe.pe_servicio_origen) " + "AND tc.tc_soporte = pe.pe_soporte " + "AND pe.pe_estado = 'Facturado' " + "AND pe_fecha BETWEEN '" + recFacturaAux.getFechaDesde() + "' AND '" + recFacturaAux.getFechaHasta() + "' " + "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";
                System.out.println(query);
                ResultSet rs = CSDesktop.datos.select(query);
                try {
                    while (rs.next()) {
                        BeanFactura nueva = new BeanFactura();
                        nueva.setNumPedido(rs.getLong("pe_num"));
                        nueva.setFecha(rs.getString("pe_fecha"));
                        nueva.setProvinciaOrigen(rs.getString("pe_servicio_origen"));
                        nueva.setProvinciaDestino(rs.getString("pe_servicio_destino"));
                        nueva.setServicio(rs.getString("pe_servicio"));
                        nueva.setServicioOrigen(rs.getString("pe_servicio_origen"));
                        nueva.setServicioDestino(rs.getString("pe_servicio_destino"));
                        nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                        nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                        nueva.setFactor(rs.getString("fc_id"));
                        nueva.setSoporte(rs.getString("pe_soporte"));
                        nueva.setMatricula(rs.getString("pe_ve_matricula"));
                        nueva.setMarca(rs.getString("pe_ve_marca"));
                        nueva.setModelo(rs.getString("pe_ve_modelo"));
                        nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                        nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                        nueva.setSuplemento(rs.getString("pe_suplemento"));
                        nueva.setDescripcion(rs.getString("pe_descripcion"));
                        nueva.setTarifa(rs.getString("tc_tarifa"));
                        nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                        nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                        nueva.setAux(recFacturaAux.getNumFactura());
                        lista.add(nueva);
                        pedidos.add(rs.getLong("pe_num"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                CSLanzarFactura factura = new CSLanzarFactura();
                factura.lanzar(lista, beanCliente, recFacturaAux.getFechaFactura(), 1, cl_id, recFacturaAux.getFechaDesde(), recFacturaAux.getFechaHasta(), pedidos, 0);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAbonoActionPerformed
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbono;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonRecuperar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {

            Component cell = super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores



            jTable1.setRowHeight(20);

            if (column == 0 ||column == 1 || column == 2 || column == 4 || column == 5 )
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 6 )
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
            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            //si no cumplen esa condicion pongo las celdas en color blanco
          
            return cell;
        }
    }
}



        
        
