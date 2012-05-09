package neg;

import data.Cliente;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CSLanzarInformeTesoreriaCliente extends javax.swing.JPanel
{
    private int clienteID;

    public CSLanzarInformeTesoreriaCliente() throws SQLException
    {
        CSDesktop.menuBuscarProveedor.setEnabled(false);
        initComponents();
        getFPago();

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
            if (this.getComponents()[k] != jComboBoxEstado &&
                this.getComponents()[k] != jComboFPago)
            {
                this.addKeyListener(l);
            }
                this.getComponents()[k].addKeyListener(l);
        }
        jTextNumFa.addKeyListener(l);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        lCliente = new javax.swing.JLabel();
        lFechaIni = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        jDateFechaIni = new com.toedter.calendar.JDateChooser();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        jTextNumFa = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lFechaFin = new javax.swing.JLabel();
        jLabelnumFc = new javax.swing.JLabel();
        lEstado5 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jComboFPago = new javax.swing.JComboBox();
        lFPago = new javax.swing.JLabel();
        lServicio = new javax.swing.JLabel();
        lFechaFin1 = new javax.swing.JLabel();
        jDateFechaFinFc = new com.toedter.calendar.JDateChooser();
        lFechaIni1 = new javax.swing.JLabel();
        jDateFechaIniFc = new com.toedter.calendar.JDateChooser();
        lServicio1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("jLabel3");
        jLabel3.setName("jLabel3"); // NOI18N

        setMaximumSize(new java.awt.Dimension(0, 0));

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        lCliente.setForeground(new java.awt.Color(0, 0, 100));
        lCliente.setText("Cliente");
        lCliente.setName("lCliente"); // NOI18N

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha Desde");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        jDateFechaIni.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaIni.setName("jDateFechaIni"); // NOI18N

        jToggleButtonCliente.setText("Buscar Cliente");
        jToggleButtonCliente.setName("jToggleButtonCliente"); // NOI18N
        jToggleButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonClienteActionPerformed(evt);
            }
        });

        jTextNumFa.setName("jTextNumFa"); // NOI18N

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
        jLabel1.setText("Informe Tesorería Cliente");
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
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "PTE", "COBRADO", "DEVOLUCIÓN", "APLAZADO", "APLAZADO IVA" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N

        jComboFPago.setBackground(new java.awt.Color(228, 229, 255));
        jComboFPago.setForeground(new java.awt.Color(0, 0, 100));
        jComboFPago.setName("jComboFPago"); // NOI18N
        jComboFPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboFPagoActionPerformed(evt);
            }
        });

        lFPago.setForeground(new java.awt.Color(0, 0, 100));
        lFPago.setText("Forma de Pago");
        lFPago.setName("lFPago"); // NOI18N

        lServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        lServicio.setForeground(new java.awt.Color(170, 16, 4));
        lServicio.setText("PEDIDOS");
        lServicio.setName("lServicio"); // NOI18N

        lFechaFin1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin1.setText("Fecha Hasta");
        lFechaFin1.setName("lFechaFin1"); // NOI18N

        jDateFechaFinFc.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFinFc.setName("jDateFechaFinFc"); // NOI18N

        lFechaIni1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni1.setText("Fecha Desde");
        lFechaIni1.setName("lFechaIni1"); // NOI18N

        jDateFechaIniFc.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaIniFc.setName("jDateFechaIniFc"); // NOI18N

        lServicio1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lServicio1.setForeground(new java.awt.Color(170, 16, 4));
        lServicio1.setText("FACTURA");
        lServicio1.setName("lServicio1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jSeparator4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(jButtonCancelar)))
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelnumFc)
                    .addComponent(lServicio)
                    .addComponent(lServicio1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextNumFa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(lCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lFechaIni1)
                                .addGap(15, 15, 15)
                                .addComponent(jDateFechaIniFc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lFechaIni)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lEstado5)
                                        .addGap(47, 47, 47)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaFinFc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFPago)
                                    .addComponent(lFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboFPago, 0, 118, Short.MAX_VALUE)
                                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))))))
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNumFa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelnumFc)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonCliente)
                    .addComponent(lCliente))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lFechaIni1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lServicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateFechaIniFc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaFin1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaFinFc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                .addComponent(lServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFPago)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEstado5))
                .addGap(48, 48, 48)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.BuscarTesoreriaCliente.dispose();
       CSDesktop.menuTesoreriaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        int numFl = 0;
        String fechaI="";
        String fechaF="";
        String fechaIFc="";
        String fechaFFc="";

        String numero = new String(jTextNumFa.getText());
        if(!numero.equals(""))
        {
            numFl = Integer.valueOf(numero);
        }
        
        String cliente = new String(jTextCliente.getText());
        Cliente cl = new Cliente();
        clienteID = cl.getClienteID(cliente);

        Calendar fechaCalendar = jDateFechaIni.getCalendar();
        if (fechaCalendar!=null)
        {
            Date fecha = fechaCalendar.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaI=formatoDeFecha.format(fecha);
        }

        Calendar fechaCalendarFin = jDateFechaFin.getCalendar();
        if (fechaCalendarFin!=null)
        {
            Date fecha = fechaCalendarFin.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaF=formatoDeFecha.format(fecha);
        }

       Calendar fechaCalendarIniFc = jDateFechaIniFc.getCalendar();
        if (fechaCalendarIniFc!=null)
        {
            Date fecha = fechaCalendarIniFc.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaIFc=formatoDeFecha.format(fecha);
        }

        Calendar fechaCalendarFinFc = jDateFechaFinFc.getCalendar();
        if (fechaCalendarFinFc!=null)
        {
            Date fecha = fechaCalendarFinFc.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFFc=formatoDeFecha.format(fecha);
        }

        String estado = new String(jComboBoxEstado.getSelectedItem().toString());
        String fPago = new String(jComboFPago.getSelectedItem().toString());

        String query = "SELECT fl.fl_id, fl.fl_fecha, fl.fl_num, cl.cl_nombre, " +
                       "fl.fl_importe, fl.fl_iva, fl.fl_importe_total, cl.cl_plazo, fp.fp_tipo, " +
                       "fl.fl_estado, fl.fl_fecha_pago, cl.cl_num_cuenta, fl.fl_observaciones " +
                       "FROM fl_factura_cliente fl, pe_pedidos pe, cl_clientes cl, fp_forma_pago fp " +
                       "WHERE  fl.cl_id = cl.cl_id AND cl.fp_id = fp.fp_id AND fl.fl_num = pe.pe_num_fa_cl";

        if (numFl == 0 && cliente.equals("") && (fechaI.equals("") && fechaF.equals("")))
        {
            jButtonBuscar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar número de factura, un Cliente o período de tiempo</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonBuscar.setEnabled(true);
        }
        else
        {
            if (numFl != 0)
            {
                query = query + " AND fl.fl_id = " + numFl;
            }
            if (!cliente.equals("")) {
                query = query + " AND cl.cl_id = " + clienteID;
            }
            if ((!fechaI.equals("")) && (!fechaF.equals(""))) 
            {
                query = query + " AND pe.pe_fecha >= '" + fechaI + "' AND pe.pe_fecha <= '" + fechaF + "'";
            }
            if ((!fechaIFc.equals("")) && (!fechaFFc.equals(""))) 
            {
                query = query + " AND fl.fl_fecha >= '" + fechaIFc + "' AND fl.fl_fecha <= '" + fechaFFc + "'";
            }
            if (!estado.equals("Selecciona"))
            {
                query = query + " AND fl.fl_estado='" + estado + "'";
            }
            if (!fPago.equals("Selecciona"))
            {
                query = query + " AND cl.fp_id='" + fPago + "'";
            }

            query = query + " ORDER BY fl.fl_id ASC";
            System.out.println(query);
            try {
                CSResultBuscarTesoreriaCliente resultBuscarTesoreriaCl = new CSResultBuscarTesoreriaCliente(query);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente.");
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

    private void jComboFPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboFPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboFPagoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboFPago;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private com.toedter.calendar.JDateChooser jDateFechaFinFc;
    private com.toedter.calendar.JDateChooser jDateFechaIni;
    private com.toedter.calendar.JDateChooser jDateFechaIniFc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelnumFc;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextNumFa;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lEstado5;
    private javax.swing.JLabel lFPago;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaFin1;
    private javax.swing.JLabel lFechaIni;
    private javax.swing.JLabel lFechaIni1;
    private javax.swing.JLabel lServicio;
    private javax.swing.JLabel lServicio1;
    // End of variables declaration//GEN-END:variables

    private void getFPago() throws SQLException
    {
        ResultSet rs = CSDesktop.datos.select("SELECT fp_id, fp_tipo FROM fp_forma_pago");
        int j = 0;
        String valor = "Selecciona";

        jComboFPago.addItem(valor);
        while(rs.next())
        {
            valor = rs.getString("fp_tipo");
            jComboFPago.addItem(valor);
            //jComboBoxFPago.setSelectedIndex(index);
            j++;
        }
     }

    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }
}