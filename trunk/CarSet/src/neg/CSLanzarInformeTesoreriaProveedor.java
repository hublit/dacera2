package neg;

import data.Proveedor;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author raulin
 */
public class CSLanzarInformeTesoreriaProveedor extends javax.swing.JPanel
{
    private int proveedorID;

    public CSLanzarInformeTesoreriaProveedor()
    {
        CSDesktop.menuBuscarProveedor.setEnabled(false);
        initComponents();

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    jButtonBuscar.doClick();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    jButtonCancelar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        for (int k = 0; k < this.getComponents().length; k ++)
        {

                this.getComponents()[k].addKeyListener(l);
        }
        jTextNumTr.addKeyListener(l);
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

        jTextProveedor = new javax.swing.JTextField();
        lCliente = new javax.swing.JLabel();
        lFechaIni = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        jDateFechaIni = new com.toedter.calendar.JDateChooser();
        jToggleButtonProveedor = new javax.swing.JToggleButton();
        jTextNumTr = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lFechaFin = new javax.swing.JLabel();
        jLabelnumFc = new javax.swing.JLabel();
        lEstado5 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jDateFechaCFin = new com.toedter.calendar.JDateChooser();
        lFechaFinC = new javax.swing.JLabel();
        lServicio3 = new javax.swing.JLabel();
        lFechaIniC = new javax.swing.JLabel();
        jDateFechaIniC = new com.toedter.calendar.JDateChooser();

        setMaximumSize(new java.awt.Dimension(0, 0));

        jTextProveedor.setEditable(false);
        jTextProveedor.setName("jTextProveedor"); // NOI18N

        lCliente.setForeground(new java.awt.Color(0, 0, 100));
        lCliente.setText("Proveedor");
        lCliente.setName("lCliente"); // NOI18N

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha Desde");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        jDateFechaIni.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaIni.setName("jDateFechaIni"); // NOI18N

        jToggleButtonProveedor.setText("Buscar Proveedor");
        jToggleButtonProveedor.setName("jToggleButtonProveedor"); // NOI18N
        jToggleButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonProveedorActionPerformed(evt);
            }
        });

        jTextNumTr.setName("jTextNumTr"); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator3"); // NOI18N

        jButtonBuscar.setText("Generar Informe");
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("Informe Tesorería Proveedor");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Fecha Hasta");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jLabelnumFc.setForeground(new java.awt.Color(0, 0, 100));
        jLabelnumFc.setText("N.º Factura ");
        jLabelnumFc.setName("jLabelnumFc"); // NOI18N

        lEstado5.setForeground(new java.awt.Color(0, 0, 100));
        lEstado5.setText("Estado");
        lEstado5.setName("lEstado5"); // NOI18N

        jComboBoxEstado.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxEstado.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "PTE", "PAGADO" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N

        jDateFechaCFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaCFin.setName("jDateFechaCFin"); // NOI18N

        lFechaFinC.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFinC.setText("Fecha Hasta");
        lFechaFinC.setName("lFechaFinC"); // NOI18N

        lServicio3.setFont(new java.awt.Font("Tahoma", 1, 11));
        lServicio3.setForeground(new java.awt.Color(170, 16, 4));
        lServicio3.setText("FECHA CONTABILIZACIÓN");
        lServicio3.setName("lServicio3"); // NOI18N

        lFechaIniC.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIniC.setText("Fecha Desde");
        lFechaIniC.setName("lFechaIniC"); // NOI18N

        jDateFechaIniC.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaIniC.setName("jDateFechaIniC"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addComponent(jButtonCancelar)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lCliente)
                            .addComponent(lFechaIni))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelnumFc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(lFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButtonProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextNumTr, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(lEstado5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lServicio3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFechaIniC)
                        .addGap(18, 18, 18)
                        .addComponent(jDateFechaIniC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(lFechaFinC, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateFechaCFin, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addGap(148, 148, 148))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNumTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelnumFc)
                    .addComponent(lEstado5)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonProveedor)
                    .addComponent(lCliente))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(lServicio3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateFechaCFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFechaFinC)
                    .addComponent(jDateFechaIniC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFechaIniC))
                .addGap(25, 25, 25)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.BuscarTesoreriaProveedor.dispose();
       CSDesktop.menuTesoreriaProveedor.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        int numTr = 0;
        String fechaI="";
        String fechaF="";
        String fechaIniCont = "";
        String fechaFinCont = "";

        String numero = new String(jTextNumTr.getText());
        if(!numero.equals(""))
        {
            numTr = Integer.valueOf(numero);
        }

        String proveedor = new String(jTextProveedor.getText());
        Proveedor oProveedor = new Proveedor();
        proveedorID = oProveedor.getProveedorID(proveedor);

        Calendar fechaCalendar = jDateFechaIni.getCalendar();

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

        String estado = new String(jComboBoxEstado.getSelectedItem().toString());
//        String query="SELECT tr_fecha, tr_num, tr_num_carset, pr_num, tr_importe_neto, tr_iva, tr_irpf, " +
//                     "tr_importe, tr_estado, tr_fecha_pago, tr_banco, tr_observaciones " +
//                     "FROM tr_tesoreria_proveedor WHERE ";

        Calendar fechaCalendarIniC = jDateFechaIniC.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendarIniC != null) {
            Date fecha = fechaCalendarIniC.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaIniCont = formatoDeFecha.format(fecha);
        }
        Calendar fechaCalendarFinC = jDateFechaCFin.getCalendar();
        //String fecha = ConvertirFechaString(fechaCalendar);
        if (fechaCalendarFinC != null) {
            Date fecha = fechaCalendarFinC.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFinCont = formatoDeFecha.format(fecha);
        }

        String query = "SELECT tr.tr_fecha, tr.tr_num, tr.tr_num_carset, pr.pr_nombre_fiscal, tr.tr_importe_neto, tr.tr_iva, " +
                       "tr.tr_irpf, tr.tr_importe, pr.pr_plazo, fp.fp_tipo, pr.pr_num_cuenta, tr.tr_estado, tr.tr_fecha_pago, " +
                       "tr.tr_banco, pr.pr_email, tr.tr_observaciones, tr_id " +
                       "FROM tr_tesoreria_proveedor tr, pr_proveedores pr, fp_forma_pago fp " +
                       "WHERE  tr.pr_num = pr.pr_id AND fp.fp_id = pr.fp_id";

        if (numTr == 0 && proveedor.equals("") && (fechaI.equals("") && fechaF.equals("")))
        {
            jButtonBuscar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar número de informe, un Proveedor o período de tiempo</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonBuscar.setEnabled(true);
        }
        else
        {
            if (numTr != 0)
            {
                query = query + " AND tr.tr_id = " + numTr;
            }
            if (!proveedor.equals("")) {
                query = query + " AND tr.pr_num = " + proveedorID;
            }
            if ((!fechaI.equals("")) && (!fechaF.equals(""))) 
            {
                query = query + " AND tr.tr_fecha >='" + fechaI + "' AND tr.tr_fecha<='" + fechaF + "'";
            }
            if (!estado.equals("Selecciona"))
            {
                query = query + " AND tr.tr_estado='" + estado + "'";
            }
            if ((!fechaIniCont.equals("")) && (!fechaFinCont.equals("")))
            {
                    query = query + " AND tr.tr_fecha_cont >='" + fechaIniCont + "' AND tr.tr_fecha_cont<='" + fechaFinCont + "'";
            }

            query = query + " ORDER BY tr.tr_id ASC";
            System.out.println(query);
            try {
                CSResultBuscarTesoreriaProveedor resultBuscarValidarPedido = new CSResultBuscarTesoreriaProveedor(query);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jToggleButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonProveedorActionPerformed

        System.out.println("\nBotón Buscar Proveedor en Validar Pedido.");
        CSDesktop.BuscaProveedor = new JInternalFrame("Seleccionar Proveedor", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectProveedor panel = new CSSelectProveedor(jTextProveedor,"",true);
        CSDesktop.BuscaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaProveedor.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaProveedor );
        CSDesktop.BuscaProveedor.setLocation(150, 50);
        CSDesktop.BuscaProveedor.setVisible( true );
}//GEN-LAST:event_jToggleButtonProveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox jComboBoxEstado;
    private com.toedter.calendar.JDateChooser jDateFechaCFin;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private com.toedter.calendar.JDateChooser jDateFechaIni;
    private com.toedter.calendar.JDateChooser jDateFechaIniC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelnumFc;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextNumTr;
    public javax.swing.JTextField jTextProveedor;
    private javax.swing.JToggleButton jToggleButtonProveedor;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lEstado5;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaFinC;
    private javax.swing.JLabel lFechaIni;
    private javax.swing.JLabel lFechaIniC;
    private javax.swing.JLabel lServicio3;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }

}