/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
        jMonthChooser = new com.toedter.calendar.JMonthChooser();
        lCliente1 = new javax.swing.JLabel();
        jYearChooser = new com.toedter.calendar.JYearChooser();
        jButtonGenerar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("GENERAR  INFORME DETALLADO 2");
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

        jMonthChooser.setName("jMonthChooser"); // NOI18N

        lCliente1.setForeground(new java.awt.Color(0, 0, 100));
        lCliente1.setText("Mes");
        lCliente1.setName("lCliente1"); // NOI18N

        jYearChooser.setName("jYearChooser"); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCliente1)
                                .addGap(22, 22, 22)
                                .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(181, 181, 181)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jButtonGenerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(194, 194, 194))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lCliente1)
                    .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonCliente)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerar)
                    .addComponent(jButtonCancelar))
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");

        String query = "SELECT cl_id, cl_nombre , cl_DNI_CIF FROM cl_clientes order by cl_id";

        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(query,jTextCliente);
        CSDesktop.BuscaCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaCliente );
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible( true );
}//GEN-LAST:event_jToggleButtonClienteActionPerformed

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

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

            int mes=jMonthChooser.getMonth()+ 1 ;
            int anyo=jYearChooser.getYear();

            Cliente oCliente = new Cliente();
            clienteID = oCliente.getClienteID(cliente);
            
            int mesIni=0;
            int anyoIni=0;
            if(mes==1)
            {
                mesIni=12;
                anyoIni=anyo-1;
            }
            else
            {
                mesIni=mes-1;
                anyoIni=anyo;
            }
            
            fechaIni=anyoIni+"-"+mesIni+"-26";
            fechaFin=anyo+"-"+mes+"-24";
            System.out.println(fechaIni);
            System.out.println(fechaFin);

            String query="select pe.pe_num,pe.pe_fecha,pe.pe_ve_matricula,pe.pe_direccion_origen,pe.pe_cp_origen, " +
                    "pe.pe_fecha_origen,pe.pe_hora_origen,pe.pe_nombre_origen,pe.pe_direccion_destino, pe.pe_cp_destino, " +
                    "pe.pe_fecha_destino,pe.pe_hora_destino,pe.pe_nombre_destino FROM pe_pedidos pe, pc_pedidos_clientes pc " +
                    "WHERE pe.pe_num = pc.pe_num AND pe.pe_fecha BETWEEN '"+fechaIni+"' " +
                    "AND '"+fechaFin+"' AND pc.cl_id = "+clienteID+" ORDER BY pe.pe_num ASC";

             System.out.println(query);           
            Map pars = new HashMap();
            pars.put("Cliente", cliente);
            pars.put("Mes",Utilidades.LiteralMes(mes)+" "+anyo);
            pars.put("Query", query);

             JasperReport jasperReport = null;
             JasperPrint jasperPrint;
             Connection con = null;

             try
             {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CSInformeDet1.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                   // con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/carset","root","sc09V1");
                    //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/carset","root","rcortes");
                    DbConnection conexion=new DbConnection();
                    con=(Connection) conexion.getConexion();
               
                //1-Compilamos el archivo XML y lo cargamos en memoria
                 jasperReport = JasperCompileManager.compileReport("c:\\AplicacionCarSet\\reportes\\InformeDet2.jrxml");

                /* JasperCompileManager.compileReportToFile("c:\\prueba.jrxml");*/
                //JasperFillManager.fillReportToFile("c:\\report1.jasper", pars, new JREmptyDataSource());

                //JasperExportManager.exportReportToPdfFile("c:\\report1.jrprint");
                //2-Llenamos el reporte con la informaci�n y par�metros necesarios
                jasperPrint = JasperFillManager.fillReport("c:\\AplicacionCarSet\\reportes\\InformeDet2.jasper", pars, con);

               //3-Exportamos el reporte a pdf y lo guardamos en disco
               //JasperExportManager.exportReportToPdfFile(
               //jasperPrint, "c:/holaMundo.pdf");

                JRViewer jrViewer = new JRViewer(jasperPrint);
                CSDesktop.NuevaFactura = new JInternalFrame("Informe Detallado 2", true, false, false, true );
                CSDesktop.NuevaFactura.getContentPane().add( jrViewer, BorderLayout.CENTER );
                //CSDesktop.NuevaFactura.add(jrViewer);
                CSDesktop.NuevaFactura.pack();

                CSDesktop.elEscritorio.add( CSDesktop.NuevaFactura );
                Dimension pantalla = CSDesktop.elEscritorio.getSize();
                CSDesktop.NuevaFactura.setSize(pantalla);
                CSDesktop.NuevaFactura.setVisible(true);
             }
             catch (JRException e)
             {
                e.printStackTrace();
             }
        }
    }//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.InformeDetallado2.dispose();
        CSDesktop.menuInformeDetallado2.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed


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
