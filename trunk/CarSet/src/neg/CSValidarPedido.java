/*
 * ABBuscarFactura.java
 *
 * Created on 07-oct-2009, 17:50:47
 */

package neg;


import data.BeanFactura;
import data.BeanProveedor;
import data.Proveedor;
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
public class CSValidarPedido extends JPanel
{
    
    public CSValidarPedido()
    {
        initComponents();
        CSDesktop.menuTesoreriaValidacion.setEnabled(false);

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
        jTextProveedor.addKeyListener(l);
        addKeyListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jToggleButtonProveedor = new javax.swing.JToggleButton();
        lCliente = new javax.swing.JLabel();
        lFechaIni = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jTextProveedor = new javax.swing.JTextField();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        lFechaFin = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();

        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setName("jButtonBuscar"); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
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

        jToggleButtonProveedor.setText("Buscar Proveedor");
        jToggleButtonProveedor.setName("jToggleButtonProveedor"); // NOI18N
        jToggleButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonProveedorActionPerformed(evt);
            }
        });

        lCliente.setForeground(new java.awt.Color(0, 0, 100));
        lCliente.setText("Proveedor");
        lCliente.setName("lCliente"); // NOI18N

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha Desde");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jTextProveedor.setEditable(false);
        jTextProveedor.setName("jTextProveedor"); // NOI18N

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Fecha Hasta");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("VALIDAR PEDIDOS");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFechaIni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(lFechaFin))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(191, 191, 191))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
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
                    .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente)
                    .addComponent(jToggleButtonProveedor))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscar)
                .addGap(31, 31, 31)
                .addComponent(jButtonCancelar)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        System.out.println("\nBotón Buscar Pedidos en Validar Pedido.");

        String fechaI="";
        String fechaF="";

        Calendar fechaCalendar = jDateFecha.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaI=formatoDeFecha.format(fecha);
        }

        fechaCalendar = jDateFechaFin.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaF=formatoDeFecha.format(fecha);
        }

        String proveedor=new String(jTextProveedor.getText());

        if (proveedor.equals("") && fechaI.equals("") && fechaF.equals(""))
        {
            jButtonBuscar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar un Proveedor y un período de fecha </FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonBuscar.setEnabled(true);
            //query = query + " WHERE ";
        }
         else
        {
             Proveedor proveedor2 = new Proveedor();
             int proveedorID = proveedor2.getProveedorID(proveedor);

             String query = "SELECT * FROM pe_pedidos pe ";
             query = query + " , pp_pedidos_proveedores pp WHERE "+
                        " pe.pe_num = pp.pe_num AND pp.pr_id = "+proveedorID;

              CSResultValidarPedido resultBuscarCliente = new CSResultValidarPedido(query);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jToggleButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonProveedorActionPerformed

        System.out.println("\nBotón Buscar Proveedor en Validar Pedido.");
        CSDesktop.BuscaProveedor = new JInternalFrame("Seleccionar Proveedor", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectProveedor panel = new CSSelectProveedor(jTextProveedor);
        CSDesktop.BuscaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaProveedor.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaProveedor );
        CSDesktop.BuscaProveedor.setLocation(150, 50);
        CSDesktop.BuscaProveedor.setVisible( true );
}//GEN-LAST:event_jToggleButtonProveedorActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.ValidacionPedidos.dispose();
       CSDesktop.menuTesoreriaValidacion.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

 public Dimension getPreferredSize()
   {
      return new Dimension( 826,500 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JTextField jTextProveedor;
    private javax.swing.JToggleButton jToggleButtonProveedor;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaIni;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }

    private void LanzarFactura(int numero) throws UnknownHostException
    {
        int proveedorID = 0;
        String fechaI="";
        String fechaF="";
        String fechaFac="";
        ArrayList lista=new ArrayList();
      
        String proveedor = new String(jTextProveedor.getText());
        Proveedor oProveedor = new Proveedor();
        BeanProveedor beanProveedor = new BeanProveedor();

        proveedorID = oProveedor.getProveedorID(proveedor);
        beanProveedor = oProveedor.getDatosFacturaProveedor(proveedorID);
        beanProveedor.setPr_id(String.valueOf(proveedorID));

        Calendar fechaCalendar = jDateFecha.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaI=formatoDeFecha.format(fecha);
        }

        fechaCalendar = jDateFechaFin.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaF=formatoDeFecha.format(fecha);
        }

        if (!Utilidades.campoObligatorio(proveedor, "Cliente").equals("OK"))
        {
                ValidarFormatos(Utilidades.campoObligatorio(proveedor, "Cliente"));
        }
        else
        {
           String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " +
                           "pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " +
                           "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " +
                           "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento, pe.pe_num_en_camion, " +
                           "pe.pe_descripcion, tp.tp_tarifa, sp_entrada_campa, sp_campa, sp_suplemento " +
                           "FROM pe_pedidos pe, pp_pedidos_proveedores pp, tp_tarifas_proveedores tp, sp_servicios_proveedores sp " +
                           "WHERE pe.pe_num = pp.pe_num " +
                           "AND sp.pr_id = pp.pr_id " +
                           "AND tp.tp_fecha_hasta > pe.pe_fecha " +
                           "AND tp.tp_servicio = pe.pe_servicio " +
                           "AND tp.pr_id = pp.pr_id " +
                           "AND (tp.tp_servicio_origen = pe.pe_servicio_origen " +
                           "OR tp.tp_servicio_origen = pe.pe_servicio_destino) " +
                           "AND (tp.tp_servicio_destino = pe.pe_servicio_destino " +
                           "OR tp.tp_servicio_destino = pe.pe_servicio_origen) " +
                           "AND tp.tp_soporte = pe.pe_soporte " +
                           "AND pe_fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' " +
                           "AND pp.pr_id = "+proveedorID+"  GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

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
                    nueva.setTarifa(rs.getString("tp_tarifa"));
                    nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                    nueva.setServicioSuplemento(rs.getString("sp_suplemento"));
                    nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                    lista.add(nueva);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(CSValidarPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(query);
            try {
                try {
                    CSLanzarFacturaProveedor factura=new CSLanzarFacturaProveedor();
                    factura.lanzar(lista,beanProveedor,fechaF,numero,proveedorID,fechaI,fechaF);
                } catch (JRException ex) {
                    Logger.getLogger(CSValidarPedido.class.getName()).log(Level.SEVERE, null, ex);
                }               
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSValidarPedido.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSValidarPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            //CSResultBuscarPedido resultBuscarCliente = new CSResultBuscarPedido(query);
    }
    }
   
}