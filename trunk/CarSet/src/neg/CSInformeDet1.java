/*
 * CSInformeDet1.java
 *
 * Created on 10-dic-2009, 8:48:09
 */

package neg;

import data.BeanFactura;
import data.Cliente;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSInformeDet1 extends javax.swing.JPanel
{
    /** Creates new form CSInformeDet1 */
    public CSInformeDet1() throws SQLException
    {
        initComponents();
        CSDesktop.menuInformeDetallado1.setEnabled(false);
        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    jButtonGenerar.doClick();
                }
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        lCliente = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jButtonGenerar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        lFechaIni = new javax.swing.JLabel();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        lFechaFin = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("INFORME CLIENTE 1");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

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

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jButtonGenerar.setText("Generar");
        jButtonGenerar.setName("jButtonGenerar"); // NOI18N
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

        jButtonCancelar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setName("jButtonCancelar"); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha Desde");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Fecha Hasta");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFechaIni)
                                .addGap(18, 18, 18)
                                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(lFechaFin)
                                .addGap(18, 18, 18)
                                .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente)
                    .addComponent(jToggleButtonCliente))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jDateFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerar)
                    .addComponent(jButtonCancelar))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        GenerarInforme();
    }

    public void GenerarInforme(){

        //Se comprueba que haya seleccionado un cliente
        String cliente = new String(jTextCliente.getText());

        if (!Utilidades.campoObligatorio(cliente, "Cliente").equals("OK"))
        {
                ValidarFormatos(Utilidades.campoObligatorio(cliente, "Cliente"));
        }
        else
        {
            int clienteID = 0;
            String fechaIni="";
            String fechaFin="";
            String fechaSIni="";
            String fechaSFin="";
            String queryAux="";
            ArrayList lista=new ArrayList();

            //SE RECOGEN LAS FECHAS DE GENERACION DEL INFORME
            
            //FECHA INICIO
            Calendar fechaCalendar = jDateFecha.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaIni=formatoDeFecha.format(fecha);
                formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                fechaSIni = formatoDeFecha.format(fecha);
            }

            //FECHA FIN
            fechaCalendar = jDateFechaFin.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaFin=formatoDeFecha.format(fecha);
                formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                fechaSFin = formatoDeFecha.format(fecha);
            }

            // SI HAY CLIENTE, SE PUEDE HACER INFORME SIN CLIENTE, SOLO POR RANGO DE FECHAS
            if (!cliente.equals(""))
            {
                Cliente oCliente = new Cliente();
                clienteID = oCliente.getClienteID(cliente);
                queryAux=" AND pc.cl_id = "+clienteID+" ";
            }

            // SE EJECUTA LA QUERY NECEARIA PARA RECOGER LOS DATOS NECESARIOS PARA REALIZAR EL INFORME
            // POR LO QUE PARECE, EL CLIENTE SIEMPRE TIENE QUE APARECER PORQUE EN LA QUERY ESTA.
            String query="SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, pe.pe_servicio,"+
                " pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, pe.pe_dias_campa, pe.pe_num_en_camion, "+
                " pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte,pe.pe_ve_matricula, pe.pe_ta_es_cliente, pe.pe_suplemento, " +
                "pe.pe_descripcion, pe.pe_estado, pe.pe_fecha_origen, pe.pe_fecha_destino, pe.pe_fecha_real_destino, " +
                "pe.pe_num_unido, destino_unido, real_destino, fecha_destino "+
                "FROM (carset.pe_pedidos pe, carset.pc_pedidos_clientes pc) "+
                "LEFT JOIN (SELECT pe_num_unido AS num_unido, pe_provincia_destino AS destino_unido , pe_fecha_destino AS fecha_destino, " +
                "pe_fecha_real_destino as real_destino FROM pe_pedidos WHERE pe_fin_unido = 1 ORDER BY pe_num DESC) " +
                "pe_unido ON pe.pe_num = pe_unido.num_unido " +
                " WHERE pc.pe_num = pe.pe_num ";
                if ((!fechaIni.equals("")) && (!fechaFin.equals(""))) {
                    query = query + " AND pe.pe_fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";
                }
                query = query + " AND pc.cl_id = "+clienteID+" " +
                        " GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

            System.out.println(query);
            ResultSet rs = CSDesktop.datos.select(query);
            try {
                while (rs.next()) {
                    // SE METEN LOS DATOS EN EL BEAN DE FACTURA PARA DESPUES RELLENAR LA TABLA AUXILIAR DE INFORMES
                    if (rs.getLong("pe_num_unido") == 0 )
                    {
                        BeanFactura nueva = new BeanFactura();

                        nueva.setNumPedido(rs.getLong("pe_num"));
                        nueva.setFecha(rs.getString("pe_fecha"));
                        nueva.setProvinciaOrigen(rs.getString("pe_servicio_origen"));
                        if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                            nueva.setFecha_prevista_entrega(rs.getString("fecha_destino"));
                            nueva.setProvinciaDestino(rs.getString("destino_unido"));
                            nueva.setServicioDestino(rs.getString("destino_unido"));
                            nueva.setFecha_real_entrega(rs.getString("real_destino"));
                        }else{
                            nueva.setFecha_prevista_entrega(rs.getString("pe_fecha_destino"));
                            nueva.setProvinciaDestino(rs.getString("pe_servicio_destino"));
                            nueva.setServicioDestino(rs.getString("pe_servicio_destino"));
                            nueva.setFecha_real_entrega(rs.getString("pe_fecha_real_destino"));
                        }
                        nueva.setServicio(rs.getString("pe_servicio"));
                        nueva.setServicioOrigen(rs.getString("pe_servicio_origen"));
                        nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                        nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                        nueva.setFactor(rs.getString("fc_id"));
                        nueva.setSoporte(rs.getString("pe_soporte"));
                        nueva.setSuplemento(rs.getString("pe_suplemento"));
                        nueva.setMatricula(rs.getString("pe_ve_matricula"));
                        nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                        nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                        nueva.setDescripcion(rs.getString("pe_descripcion"));
                        nueva.setEstado(rs.getString("pe_estado"));
                        nueva.setFecha_prevista_recogida(rs.getString("pe_fecha_origen"));
                        lista.add(nueva);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                CSLanzarInforme1 informe1=new CSLanzarInforme1();
                try {
                    informe1.lanzar(lista, clienteID, cliente,fechaSIni, fechaSFin);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
            }                      
        }
    }//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.InformeDetallado1.dispose();
        CSDesktop.menuInformeDetallado1.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGenerar;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private javax.swing.JLabel jLabel1;
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
         jButtonGenerar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGenerar.setEnabled(true);
    }

}
