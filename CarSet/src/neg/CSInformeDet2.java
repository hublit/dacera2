/*
 * CSInformeDet1.java
 *
 * Created on 10-dic-2009, 8:48:09
 */

package neg;

import com.mysql.jdbc.Connection;
import data.Cliente;
import data.DbConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSInformeDet2 extends javax.swing.JPanel
{
    /** Creates new form CSInformeDet1 */
    public CSInformeDet2()
    {
        initComponents();
        CSDesktop.menuInformeDetallado2.setEnabled(false);

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
        jButtonCancelar = new javax.swing.JButton();
        jButtonGenerar = new javax.swing.JButton();
        jMonthChooser = new com.toedter.calendar.JMonthChooser();
        jSeparator6 = new javax.swing.JSeparator();
        jYearChooser = new com.toedter.calendar.JYearChooser();
        lCliente1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("INFORME CLIENTE 2");
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

        jButtonCancelar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setName("jButtonCancelar"); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonGenerar.setText("Generar");
        jButtonGenerar.setName("jButtonGenerar"); // NOI18N
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

        jMonthChooser.setName("jMonthChooser"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jYearChooser.setName("jYearChooser"); // NOI18N

        lCliente1.setForeground(new java.awt.Color(0, 0, 100));
        lCliente1.setText("Mes");
        lCliente1.setName("lCliente1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCliente1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addGap(149, 149, 149)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonCliente)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lCliente1)
                    .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerar)
                    .addComponent(jButtonCancelar))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");
        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(jTextCliente);
        CSDesktop.BuscaCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaCliente );
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible( true );
}//GEN-LAST:event_jToggleButtonClienteActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.InformeDetallado2.dispose();
        CSDesktop.menuInformeDetallado2.setEnabled(true);
}//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        //Se comprueba que haya seleccionado un cliente
        String cliente = new String(jTextCliente.getText());

        /*if (!Utilidades.campoObligatorio(cliente, "Cliente").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorio(cliente, "Cliente"));
        } else {*/
            int clienteID = 0;
            String fechaIni="";
            String fechaFin="";
            String queryAux="";

            int mes=jMonthChooser.getMonth()+ 1 ;
            int anyo=jYearChooser.getYear();

            if(!cliente.equals(""))
            {
                Cliente oCliente = new Cliente();
                clienteID = oCliente.getClienteID(cliente);
                queryAux=" AND pc.cl_id = "+clienteID+" ";
            }

            int mesIni=0;
            int anyoIni=0;
            if(mes==1) {
                mesIni=12;
                anyoIni=anyo-1;
            } else {
                mesIni=mes-1;
                anyoIni=anyo;
            }

            fechaIni=anyoIni+"-"+mesIni+"-26";
            fechaFin=anyo+"-"+mes+"-25";
            System.out.println(fechaIni);
            System.out.println(fechaFin);

            String query="SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_ve_matricula, pe.pe_direccion_origen," +
                " pe.pe_cp_origen, pe.pe_fecha_origen, pe.pe_hora_origen, pe.pe_nombre_origen, pe.pe_direccion_destino,"+
                " pe.pe_cp_destino, pe.pe_fecha_destino, pe.pe_hora_destino, pe.pe_nombre_destino"+
                " FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc"+
                " WHERE pe.pe_num = pc.pe_num"+
                " AND tc.tc_servicio = pe.pe_servicio"+
                " AND tc.cl_id = pc.cl_id"+
                " AND tc.tc_fecha_hasta = '2050-01-01'"+
                " AND (tc.tc_servicio_origen = pe.pe_servicio_origen  OR tc.tc_servicio_origen = pe.pe_servicio_destino)"+
                " AND (tc.tc_servicio_destino = pe.pe_servicio_destino  OR tc.tc_servicio_destino = pe.pe_servicio_origen)"+
                " AND tc.tc_soporte = pe.pe_soporte"+
                " AND pe_fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";
                   
            if(!cliente.equals(""))
            {
                query=query + queryAux;
            }
            query = query + " ORDER BY pe.pe_num ASC";

            System.out.println(query);

            Map pars = new HashMap();
            pars.put("Cliente", cliente);
            pars.put("Mes",Utilidades.LiteralMes(mes)+" "+anyo);
            pars.put("Query", query);

            JasperReport jasperReport = null;
            JasperPrint jasperPrint;
            Connection con = null;

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
                }
                 DbConnection conexion = null;
            try {
                conexion = new DbConnection();
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSInformeDet2.class.getName()).log(Level.SEVERE, null, ex);
            }
                con=(Connection) conexion.getConexion();
                //1-Compilamos el archivo XML y lo cargamos en memoria
                jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/InformeDet2.jrxml"));
                             
                //2-Llenamos el reporte con la informaci�n y par�metros necesarios
                jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/InformeDet2.jasper"), pars, con);
              
                JRViewerDet2 jrViewer = new JRViewerDet2(jasperPrint);
                CSDesktop.NuevoInformeDetallado2 = new JInternalFrame("Informe Detallado 2", true, false, false, true );
                CSDesktop.NuevoInformeDetallado2.getContentPane().add( jrViewer, BorderLayout.CENTER );
                //CSDesktop.NuevaFactura.add(jrViewer);
                CSDesktop.NuevoInformeDetallado2.pack();

                CSDesktop.elEscritorio.add( CSDesktop.NuevoInformeDetallado2 );
                Dimension pantalla = CSDesktop.elEscritorio.getSize();
                CSDesktop.NuevoInformeDetallado2.setSize(pantalla);
                CSDesktop.NuevoInformeDetallado2.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }
        //}
}//GEN-LAST:event_jButtonGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JMonthChooser jMonthChooser;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private com.toedter.calendar.JYearChooser jYearChooser;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lCliente1;
    // End of variables declaration//GEN-END:variables

     public void ValidarFormatos(String accion)
    {
         jButtonGenerar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGenerar.setEnabled(true);
    }

}