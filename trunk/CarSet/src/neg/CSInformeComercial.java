/*
 * CSInformeComercial.java
 *
 * Created on 10-dic-2009, 8:48:09
 */

package neg;


import com.mysql.jdbc.Connection;
import data.DbConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author depr102
 */
public class CSInformeComercial extends javax.swing.JPanel
{
    public String query = "";
    /** Creates new form CSInformeDet1 */
    public CSInformeComercial() throws SQLException
    {
        initComponents();
        CSDesktop.menuInformeComercial.setEnabled(false);
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
        jSeparator6 = new javax.swing.JSeparator();
        jButtonGenerar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        lFechaFin = new javax.swing.JLabel();
        jComboBoxAnyo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabelOrder = new javax.swing.JLabel();
        jComboOrden = new javax.swing.JComboBox();
        jComboBoxTipo = new javax.swing.JComboBox();
        lTipo = new javax.swing.JLabel();
        lFechaMes = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("Informe Comercial");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

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

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Año");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jComboBoxAnyo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" }));
        jComboBoxAnyo.setSelectedIndex(5);
        jComboBoxAnyo.setName("jComboBoxAnyo"); // NOI18N
        jComboBoxAnyo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnyoActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Informe Comercial anual de pedidos por Clientes");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabelOrder.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelOrder.setForeground(new java.awt.Color(170, 16, 4));
        jLabelOrder.setText("Orden");
        jLabelOrder.setName("jLabelOrder"); // NOI18N

        jComboOrden.setBackground(new java.awt.Color(255, 255, 102));
        jComboOrden.setForeground(new java.awt.Color(0, 0, 100));
        jComboOrden.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tarifa Cliente", "Nombre Cliente", "Margen Pedido", "Número de Pedidos" }));
        jComboOrden.setName("jComboOrden"); // NOI18N
        jComboOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboOrdenActionPerformed(evt);
            }
        });

        jComboBoxTipo.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxTipo.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Empresa", "Concesionario", "Renting", "Compraventa", "Particular", "Proveedor", "Fabricante", "Asistencia", "Intermediario Renting", "Carrocero", "Otros" }));
        jComboBoxTipo.setName("jComboBoxTipo"); // NOI18N

        lTipo.setForeground(new java.awt.Color(0, 0, 100));
        lTipo.setText("    Tipo");
        lTipo.setName("lTipo"); // NOI18N

        lFechaMes.setForeground(new java.awt.Color(0, 0, 100));
        lFechaMes.setText("Mes");
        lFechaMes.setName("lFechaMes"); // NOI18N

        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMes.setSelectedIndex(0);
        jComboBoxMes.setName("jComboBoxMes"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.CENTER)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jButtonCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabelOrder)
                                .addGap(33, 33, 33)
                                .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(lFechaFin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lFechaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTipo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lFechaMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrder)
                    .addComponent(jComboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonGenerar))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        GenerarInforme();
    }

    public void GenerarInforme()
    {
        //Se comprueba que haya seleccionado un cliente
        int anyo = Integer.parseInt(jComboBoxAnyo.getSelectedItem().toString());
        int anyo_post = anyo + 1;
        int mes = jComboBoxMes.getSelectedIndex();
        double total = 0;
        String tipoCliente = new String(jComboBoxTipo.getSelectedItem().toString());
        
        //SE RECOGEN LAS FECHAS DE GENERACION DEL INFORME
        //FECHA INICIO
        String fechaIni=anyo+"-01-01";

        //FECHA FIN
        String fechaFin=anyo+"-12-31";

        // SE EJECUTA LA QUERY NECEARIA PARA RECOGER LOS DATOS NECESARIOS PARA REALIZAR EL INFORME
        // POR LO QUE PARECE, EL CLIENTE SIEMPRE TIENE QUE APARECER PORQUE EN LA QUERY ESTA.
        query = "SELECT cl.cl_id, cl.cl_nombre, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 1, pe.pe_ta_es_cliente, 0) ) AS enero," +
                "SUM(IF(MONTH(pe.pe_fecha) = 2, pe.pe_ta_es_cliente, 0) ) AS febrero, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 3, pe.pe_ta_es_cliente, 0) ) AS marzo, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 4, pe.pe_ta_es_cliente, 0) ) AS abril, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 5, pe.pe_ta_es_cliente, 0) ) AS mayo, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 6, pe.pe_ta_es_cliente, 0) ) AS junio, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 7, pe.pe_ta_es_cliente, 0) ) AS julio, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 8, pe.pe_ta_es_cliente, 0) ) AS agosto, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 9, pe.pe_ta_es_cliente, 0) ) AS septiembre, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 10, pe.pe_ta_es_cliente, 0) ) AS octubre," +
                "SUM(IF(MONTH(pe.pe_fecha) = 11, pe.pe_ta_es_cliente, 0) ) AS noviembre, " +
                "SUM(IF(MONTH(pe.pe_fecha) = 12, pe.pe_ta_es_cliente, 0) ) AS diciembre, " +
                "SUM(pe.pe_ta_es_cliente - pe.pe_ta_es_proveedor) AS mg_pedido, " +
                "SUM(1) AS num_pedido, " +
                "SUM(pe.pe_ta_es_cliente) AS ta_cliente, " +
                "SUM(pe.pe_ta_es_proveedor) AS ta_proveedor " +
                "FROM carset.pe_pedidos pe INNER JOIN carset.pc_pedidos_clientes pc " +
                "INNER JOIN carset.cl_clientes cl " +
//                "WHERE cl.cl_estado = 'Activo' AND pe.pe_incidencia != 'ADMINISTRATIVA'" ;
                "WHERE pe.pe_num = pc.pe_num AND pc.cl_id = cl.cl_id ";
        if ((!fechaIni.equals("")) && (!fechaFin.equals(""))) {
            query = query + " AND pe.pe_fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";
        }
        if (!tipoCliente.equals("Selecciona"))
        {
           query = query + " AND cl.cl_tipo= '"+tipoCliente+"'";
        }
        if (mes != 0)
        {
            query = query + " AND MONTH(pe.pe_fecha) = "+mes+"";
        }
        query = query + " GROUP BY cl.cl_nombre";
        
        //Orden de la query
        int orden = new Integer(jComboOrden.getSelectedIndex());

        String order = "";
        switch (orden) {
            case 0:  order = "ta_cliente";
                     break;
            case 1:  order = "cl.cl_nombre";
                     break;
            case 2:  order = "mg_pedido";
                     break;
            case 3:  order = "num_pedido";
                     break;
            default: order = "cl.cl_nombre";
                     break;
        }

        query = query + " ORDER BY " + order + " DESC";

       System.out.println(query);

        ResultSet rs = CSDesktop.datos.select(query);
        
        //Sacamos el importe toal de los pedidos
        try {
            total = this.getImporteTotalPedidos(fechaIni, fechaFin, tipoCliente, mes);
        } catch (SQLException ex) {
            Logger.getLogger(CSInformeComercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Map pars = new HashMap();
        //pars.put("Mes",Utilidades.LiteralMes(mes)+" "+anyo);
        pars.put("Query", query);
        pars.put("FechaInicio", fechaIni);
        pars.put("FechaFin", fechaFin);
        pars.put("total", total);

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

                CSDesktop.InformeComercial.dispose();

                //1-Compilamos el archivo XML y lo cargamos en memoria
                jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/data/reportes/InformeComercial.jrxml"));

                //2-Llenamos el reporte con la informaci�n y par�metros necesarios
                jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("/data/reportes/InformeComercial.jasper"), pars, con);
                jasperPrint.setProperty("query", query);
                JRViewerComercial jrViewer = new JRViewerComercial(jasperPrint);
                CSDesktop.InformeComercial = new JInternalFrame("Informe Comercial", true, false, false, true );
                CSDesktop.InformeComercial.getContentPane().add( jrViewer, BorderLayout.CENTER );
                CSDesktop.InformeComercial.pack();

                CSDesktop.elEscritorio.add( CSDesktop.InformeComercial );
                Dimension pantalla = CSDesktop.elEscritorio.getSize();
                CSDesktop.InformeComercial.setSize(pantalla);
                CSDesktop.InformeComercial.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.InformeComercial.dispose();
        CSDesktop.menuInformeComercial.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxAnyoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnyoActionPerformed
        ArrayList<String> years_tmp = new ArrayList<String>();
        for(int years = 2010 ; years<=Calendar.getInstance().get(Calendar.YEAR);years++){
        years_tmp.add(years+"");
       }
        //jComboBoxAnyo = new jComboBoxAnyo(years_tmp.toArray());
}//GEN-LAST:event_jComboBoxAnyoActionPerformed

    private void jComboOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboOrdenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JComboBox jComboBoxAnyo;
    private javax.swing.JComboBox jComboBoxMes;
    private javax.swing.JComboBox jComboBoxTipo;
    private javax.swing.JComboBox jComboOrden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelOrder;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaMes;
    private javax.swing.JLabel lTipo;
    // End of variables declaration//GEN-END:variables

     public void ValidarFormatos(String accion)
    {
         jButtonGenerar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGenerar.setEnabled(true);
    }

    /**
     *
     * @param accion
     */
    public double getImporteTotalPedidos(String fechaIni, String fechaFin, String tipoCliente, int mes) throws SQLException
    {
        double total = 0;
        String queryPe ="SELECT SUM(pe.pe_ta_es_cliente) AS total FROM carset.pe_pedidos pe " +
                        "INNER JOIN carset.pc_pedidos_clientes pc ON pe.pe_num = pc.pe_num " +
                        "RIGHT JOIN carset.cl_clientes cl ON pc.cl_id = cl.cl_id " +
                        "WHERE pe.pe_fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";

        if (!tipoCliente.equals("Selecciona"))
        {
           queryPe = queryPe + " AND cl.cl_tipo= '"+tipoCliente+"'";
        }
        if (mes != 0)
        {
            queryPe = queryPe + " AND MONTH(pe.pe_fecha) = "+mes+"";
        }

        System.out.println("Total: "+ queryPe);
        ResultSet rs = CSDesktop.datos.select(queryPe);
        while(rs.next())
        {
            total = (rs.getDouble("total"));
        }

        return total;
    }

}
