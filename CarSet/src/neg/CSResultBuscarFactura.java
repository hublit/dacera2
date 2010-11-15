/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import data.BeanFactura;
import data.BeanCliente;
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
import java.util.ArrayList;
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
public class CSResultBuscarFactura extends javax.swing.JPanel
{
    Object[] datosFila = null;
    TablaModelo modelo = new TablaModelo();
    ArrayList lista=new ArrayList();
    ArrayList pedidos=new ArrayList();
    BeanCliente bCliente=new BeanCliente();
    String fechaSI="";
    String fechaSF="";
    int clienteID=0;

    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarFactura(String query,BeanCliente beanCliente, String fechaI,String fechaF)
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

        modelo.setColumnIdentifiers(new String[] {"NUM", "FECHA", "ORIGEN", "DESTINO", "SOPORTE", "MATRICULA", "OBSERVACIONES"});

        int numeroFila = 0;
        try {
            while (rs.next()) {

                BeanFactura campos = new BeanFactura();

                    campos.setNumPedido(rs.getLong("pe_num"));
                    campos.setFecha(rs.getString("pe_fecha"));
                    campos.setProvinciaOrigen(rs.getString("pe_servicio_origen"));
                    campos.setProvinciaDestino(rs.getString("pe_servicio_destino"));
                    campos.setServicio(rs.getString("pe_servicio"));
                    campos.setServicioOrigen(rs.getString("pe_servicio_origen"));
                    campos.setServicioDestino(rs.getString("pe_servicio_destino"));
                    campos.setServicioEspecial(rs.getString("pe_servicio_especial"));
                    campos.setDiasCampa(rs.getString("pe_dias_campa"));
                    campos.setFactor(rs.getString("fc_id"));
                    campos.setSoporte(rs.getString("pe_soporte"));
                    campos.setMatricula(rs.getString("pe_ve_matricula"));
                    campos.setMarca(rs.getString("pe_ve_marca"));
                    campos.setModelo(rs.getString("pe_ve_modelo"));
                    campos.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                    campos.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                    campos.setSuplemento(rs.getString("pe_suplemento"));
                    campos.setDescripcion(rs.getString("pe_descripcion"));
                    campos.setTarifa(rs.getString("tc_tarifa"));
                    campos.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                    campos.setNumCamion(rs.getString("pe_num_en_camion"));
                    lista.add(campos);
                    pedidos.add(rs.getLong("pe_num"));

                datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 20; k++) {
                    if (k==0 ||k==1 || k == 2 || k==3 || k == 9 || k==10 || k==17) {
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
        jTable1.setModel(modelo);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(25);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(40);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(100);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(50);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(50);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(410);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);

        bCliente=beanCliente;
        fechaSI=fechaI;
        fechaSF=fechaF;
        String clienteSID=beanCliente.getCl_id();
        clienteID=Integer.parseInt(clienteSID);

        
        /*jTable1.addMouseListener(new MouseAdapter()
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
                        editarC = new CSEditarPedido(proveedor);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
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
        }    );*/
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jButtonCerrar)
                        .addGap(260, 260, 260))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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

        int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres generar la Factura y un nuevo numero?");

        if (JOptionPane.OK_OPTION == confirmado) {
            int numero = 0;
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

                BeanFactura factura=new BeanFactura();
                factura=(BeanFactura)lista.get(longitud);
                long pedido=(Long)pedidos.get(longitud);
                ArrayList listaPedidos=new ArrayList();
                listaPedidos.add(pedido);
                ArrayList listaFacturas=new ArrayList();
                listaFacturas.add(factura);
                CSLanzarFactura facturaFinal = new CSLanzarFactura();
                try {

                    facturaFinal.lanzar(listaFacturas, bCliente, fechaSF, numero+1 , clienteID, fechaSI, fechaSF, listaPedidos,1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JRException ex) {
                    Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
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
                facturaFinal.lanzar(listaFacturas, bCliente, fechaSF, 0, clienteID, fechaSI, fechaSF, listaPedidos,1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_jButtonPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}