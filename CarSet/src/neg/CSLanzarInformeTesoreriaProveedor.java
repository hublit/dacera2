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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(lFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jDateFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButtonProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTextNumTr, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152))
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNumTr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelnumFc))
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
                .addGap(87, 87, 87)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addGap(42, 42, 42))
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


//        String query="SELECT tr_fecha, tr_num, tr_num_carset, pr_num, tr_importe_neto, tr_iva, tr_irpf, " +
//                     "tr_importe, tr_estado, tr_fecha_pago, tr_banco, tr_observaciones " +
//                     "FROM tr_tesoreria_proveedor WHERE ";

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
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private com.toedter.calendar.JDateChooser jDateFechaIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelnumFc;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextNumTr;
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

}