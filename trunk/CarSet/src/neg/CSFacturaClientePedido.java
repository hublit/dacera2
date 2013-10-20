/*
 * ABBuscarFactura.java
 *
 * Created on 07-oct-2009, 17:50:47
 */

package neg;

import data.BeanCliente;
import data.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.Utilidades;

/**
 *
 * @author raul
 */
public class CSFacturaClientePedido extends JPanel
{
    
    public CSFacturaClientePedido()
    {
        initComponents();
        CSDesktop.menuFacturaClientePedido.setEnabled(false);

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    jButtonCancelar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        jTextCliente.addKeyListener(l);
        addKeyListener(l);
    }

    public CSFacturaClientePedido(String cliente,String fechaI, String fechaF,boolean libre)
    {
        initComponents();
        CSDesktop.menuFacturaClientePedido.setEnabled(false);

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    jButtonCancelar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        try {            
            LanzarFactura(0,cliente,fechaI,fechaF,libre);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaClientePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancelar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        lCliente = new javax.swing.JLabel();
        lFechaIni = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jTextCliente = new javax.swing.JTextField();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        lFechaFin = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jButtonBuscar = new javax.swing.JButton();
        jButtonBuscarLibres = new javax.swing.JButton();

        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        jButtonCancelar.setForeground(new java.awt.Color(204, 0, 0));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setName("jButtonCancelar"); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jSeparator4.setName("jSeparator4"); // NOI18N

        jToggleButtonCliente.setText("Buscar Cliente");
        jToggleButtonCliente.setName("jToggleButtonCliente"); // NOI18N
        jToggleButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonClienteActionPerformed(evt);
            }
        });

        lCliente.setForeground(new java.awt.Color(0, 0, 100));
        lCliente.setText("Cliente");
        lCliente.setName("lCliente"); // NOI18N

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha Desde");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Fecha Hasta");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("FACTURA CLIENTE POR PEDIDO");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setName("jButtonBuscar"); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonBuscarLibres.setText("Buscar Pedidos Libres");
        jButtonBuscarLibres.setName("jButtonBuscarLibres"); // NOI18N
        jButtonBuscarLibres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarLibresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFechaIni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(lFechaFin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(191, 191, 191))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jButtonBuscarLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente)
                    .addComponent(jToggleButtonCliente))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonBuscarLibres))
                .addGap(82, 82, 82))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.FacturaClientePedido.dispose();
       CSDesktop.menuFacturaClientePedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            LanzarFactura(0,"","","",false);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaClientePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");
        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(jTextCliente,"",true);
        CSDesktop.BuscaCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaCliente );
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible( true );
}//GEN-LAST:event_jToggleButtonClienteActionPerformed

    private void jButtonBuscarLibresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarLibresActionPerformed
         try {
            LanzarFactura(0,"","","",true);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaClientePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBuscarLibresActionPerformed

 public Dimension getPreferredSize()
   {
      return new Dimension( 826,500 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarLibres;
    private javax.swing.JButton jButtonCancelar;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaIni;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
    }

    public void LanzarFactura(int numero,String clienteS,String fechaIni, String fechaFin,boolean libre) throws UnknownHostException
    {
        ArrayList pedidos=new ArrayList();
        int clienteID = 0;
        String fechaI="";
        String fechaF="";
        ArrayList lista=new ArrayList();
      
        String cliente = new String(jTextCliente.getText());
        if (cliente.equals(""))
        {
            cliente=clienteS;
        }
        Cliente oCliente = new Cliente();
        BeanCliente beanCliente = new BeanCliente();

        clienteID = oCliente.getClienteID(cliente);
        beanCliente = oCliente.getDatosFacturaCliente(clienteID);
        beanCliente.setCl_id(String.valueOf(clienteID));

        Calendar fechaCalendar = jDateFecha.getCalendar();       
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaI=formatoDeFecha.format(fecha);
        }
        else
        {
            fechaI=fechaIni;
        }

        fechaCalendar = jDateFechaFin.getCalendar();        
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaF=formatoDeFecha.format(fecha);
        }
        else
        {
            fechaF=fechaFin;
        }

        if (!Utilidades.campoObligatorio(cliente, "Cliente").equals("OK"))
        {
                ValidarFormatos(Utilidades.campoObligatorio(cliente, "Cliente"));
        }
        else
        {  
             String query="";
             if(libre==false)
             {
             /*query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " +
                           "pe.pe_servicio, pe.pe_servicio_especial, " +
                           "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " +
                           "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " +
                           "pe.pe_descripcion, tc.tc_tarifa, sc_entrada_campa, sc_campa " +
                           "FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc, sc_servicios_clientes sc " +
                           "WHERE pe.pe_num = pc.pe_num " +
                           "AND sc.cl_id = pc.cl_id " +
                           "AND tc.tc_fecha_hasta > pe.pe_fecha " +
                           "AND sc.sc_fecha_hasta > pe.pe_fecha " +
                           "AND tc.tc_servicio = pe.pe_servicio " +
                           "AND tc.cl_id = pc.cl_id " +
                           "AND (tc.tc_servicio_origen = pe.pe_servicio_origen " + 
                           "OR tc.tc_servicio_origen = pe.pe_servicio_destino) " +
                           "AND (tc.tc_servicio_destino = pe.pe_servicio_destino " +
                           "OR tc.tc_servicio_destino = pe.pe_servicio_origen) " +
                           "AND tc.tc_soporte = pe.pe_soporte " +
                           "AND (pe.pe_estado = 'Entregado' OR pe.pe_estado = 'Fallido')"  +
                           "AND pe_fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' " +
                           "AND pc.cl_id = "+clienteID+"  GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";*/

                  query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " +
                           "pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " +
                           "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " +
                           "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " +
                           "pe.pe_descripcion, sc_entrada_campa, sc_campa " +
                           "FROM pe_pedidos pe, pc_pedidos_clientes pc, sc_servicios_clientes sc " +
                           "WHERE pe.pe_num = pc.pe_num " +
                           "AND sc.cl_id = pc.cl_id " +
                           "AND sc.sc_fecha_hasta > pe.pe_fecha " +
                           "AND (pe.pe_estado = 'Entregado' OR pe.pe_estado = 'Fallido')"  +
                           "AND pe_fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' " +
                           "AND pc.cl_id = "+clienteID+"  GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

                           CSResultBuscarFactura resultBuscarCliente = new CSResultBuscarFactura(query,beanCliente,fechaI,fechaF);
             }
             else
             {
                           query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, cl.cl_nombre, " +
                           " pe.pe_descripcion, pe.pe_ta_es_cliente " +
                           " FROM pe_pedidos pe, cl_clientes cl, pc_pedidos_clientes pc " +
                           " WHERE pe.pe_estado = 'Pedido Libre' AND pc.cl_id = cl.cl_id"  +
                           " AND pe_fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' " +
                           " AND pc.cl_id = "+clienteID+"  GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

                           CSResultBuscarPedidosLibres resultBuscarCliente = new CSResultBuscarPedidosLibres(query,beanCliente,fechaI,fechaF);
             }

            System.out.println(query);
                    
                      
        }
    }
}