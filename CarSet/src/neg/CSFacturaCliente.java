/*
 * ABBuscarFactura.java
 *
 * Created on 07-oct-2009, 17:50:47
 */

package neg;

import data.BeanCliente;
import data.Cliente;
import data.BeanFactura;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
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
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import utils.Utilidades;

/**
 *
 * @author raul
 */
public class CSFacturaCliente extends JPanel
{
    
    public CSFacturaCliente()
    {
        Date hoy = new Date();
        initComponents();
        jDateFechaFactura.setDate(hoy);
        CSDesktop.menuFacturaCliente.setEnabled(false);

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonGenerar = new javax.swing.JButton();
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
        jButtonPrev = new javax.swing.JButton();
        lFechaIni1 = new javax.swing.JLabel();
        jDateFechaFactura = new com.toedter.calendar.JDateChooser();
        jSeparator8 = new javax.swing.JSeparator();

        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        jButtonGenerar.setText("Generar");
        jButtonGenerar.setName("jButtonGenerar"); // NOI18N
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

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
        jLabel1.setText("FACTURA CLIENTE");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

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

        jSeparator8.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator8.setName("jSeparator8"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(349, 349, 349))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
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
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(177, 177, 177))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(lFechaIni1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lFechaIni1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente)
                    .addComponent(jToggleButtonCliente))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPrev))
                .addGap(31, 31, 31)
                .addComponent(jButtonCancelar)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres generar la Factura y un nuevo numero?");

        if (JOptionPane.OK_OPTION == confirmado)
        {
            int numero = 0;
            String query = "Select max(fl_id) from fl_factura_cliente";
            ResultSet rs = CSDesktop.datos.select(query);
            try
            {
                while (rs.next())
                {
                    numero =Integer.valueOf(rs.getInt("max(fl_id)"));
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                LanzarFactura(numero + 1);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }//GEN-LAST:event_jButtonGenerarActionPerformed

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

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.FacturaCliente.dispose();
       CSDesktop.menuFacturaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        try {
            LanzarFactura(0);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPrevActionPerformed

 public Dimension getPreferredSize()
   {
      return new Dimension( 826,500 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JButton jButtonPrev;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaFactura;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaIni;
    private javax.swing.JLabel lFechaIni1;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonGenerar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGenerar.setEnabled(true);
    }

    private void LanzarFactura(int numero) throws UnknownHostException
    {
        ArrayList pedidos=new ArrayList();
        int clienteID = 0;
        String fechaI="";
        String fechaF="";
        String fechaFac="";
        ArrayList lista=new ArrayList();
      
        String cliente = new String(jTextCliente.getText());
        Cliente oCliente = new Cliente();
        BeanCliente beanCliente = new BeanCliente();

        clienteID = oCliente.getClienteID(cliente);
        beanCliente = oCliente.getDatosFacturaCliente(clienteID);
        beanCliente.setCl_id(String.valueOf(clienteID));

        Calendar fechaCalendar = jDateFechaFactura.getCalendar();
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFac=formatoDeFecha.format(fecha);
        }

        fechaCalendar = jDateFecha.getCalendar();       
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaI=formatoDeFecha.format(fecha);
        }

        fechaCalendar = jDateFechaFin.getCalendar();        
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaF=formatoDeFecha.format(fecha);
        }

        if (!Utilidades.campoObligatorio(cliente, "Cliente").equals("OK"))
        {
                ValidarFormatos(Utilidades.campoObligatorio(cliente, "Cliente"));
        }
        else
        {
           String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " +
               "pe.pe_servicio, pe.pe_poblacion_origen, pe.pe_poblacion_destino, pe.pe_servicio_especial, pe.pe_estado, " +
               "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_estado, " +
               "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento, pe.pe_num_en_camion, pe.pe_ob_cl_mail, " +
               "pe.pe_descripcion, pe.pe_num_unido, pe_unido.destino_unido, pe_unido.estado_unido, pe_unido.pob_unido, pe.pe_kms, cl.cl_email  " +
               "FROM (pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl) " +
               "LEFT JOIN (SELECT pe_num_unido AS num_unido, pe_provincia_destino, pe_provincia_destino AS destino_unido, pe_poblacion_destino AS pob_unido, pe_estado AS estado_unido " +
               "FROM pe_pedidos WHERE pe_fin_unido = 1 ORDER BY pe_num DESC) " +
               "pe_unido ON pe.pe_num = pe_unido.num_unido " +
               "WHERE pe.pe_num = pc.pe_num " +
               "AND pc.cl_id = cl.cl_id " +
               "AND (pe.pe_estado = 'Entregado' OR pe.pe_estado = 'Fallido' OR pe.pe_estado = 'Validado') "  +
               "AND pe_fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' " +
               "AND pc.cl_id = "+clienteID+"  GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

            System.out.println(query);
            ResultSet rs = CSDesktop.datos.select(query);

            try {
                while (rs.next()) {
                    Boolean unidoEstado = true;
                    if (rs.getString("estado_unido") != null){
                        unidoEstado = (rs.getString("estado_unido").equals("Entregado") || rs.getString("estado_unido").equals("Validado")) ? true : false;
                    }
                    if (rs.getLong("pe_num_unido") == 0 &&
                       (rs.getString("pe_estado").equals("") || rs.getString("pe_estado").equals("Entregado")
                        || rs.getString("pe_estado").equals("Validado") || rs.getString("pe_estado").equals("Fallido")) &&
                       unidoEstado)
                    {
                        BeanFactura nueva = new BeanFactura();
                        nueva.setNumPedido(rs.getLong("pe_num"));

                        nueva.setFecha(rs.getString("pe_fecha"));
                        nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                        nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                        if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                            nueva.setProvinciaDestino(rs.getString("destino_unido"));
                            nueva.setServicioDestino(rs.getString("destino_unido"));
                            nueva.setPoblacionDestino(rs.getString("pob_unido"));
                        }else{
                            nueva.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                            nueva.setServicioDestino(rs.getString("pe_provincia_destino"));
                            nueva.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                        }
                        nueva.setServicio(rs.getString("pe_servicio"));
                        nueva.setServicioOrigen(rs.getString("pe_provincia_origen"));
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
                        nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                        nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                        nueva.setObsInFactura(rs.getBoolean("pe_ob_cl_mail"));
                        nueva.setKms(rs.getString("pe_kms"));
                        nueva.setAtt(rs.getString("cl_email"));
                        nueva.setVe_estado(rs.getString("pe_ve_estado"));
                        nueva.setEstado(rs.getString("pe_estado"));
                        
                        lista.add(nueva);
                    }
                    pedidos.add(rs.getLong("pe_num"));
                    
                } 

            } catch (SQLException ex) {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                try {
                    CSLanzarFactura factura = new CSLanzarFactura();
                    factura.lanzar(lista,beanCliente,fechaFac,numero,clienteID,fechaI,fechaF,pedidos,0);
                } catch (JRException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }               
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}