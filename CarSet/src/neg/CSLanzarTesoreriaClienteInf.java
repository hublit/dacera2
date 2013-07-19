package neg;

import data.Cliente;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author raulin
 */
public class CSLanzarTesoreriaClienteInf extends javax.swing.JPanel
{
    private int clienteID;

    public CSLanzarTesoreriaClienteInf() throws SQLException
    {
        CSDesktop.menuBuscarCliente.setEnabled(false);
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
        jSeparator4 = new javax.swing.JSeparator();
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lFechaFin1 = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        lFechaIni1 = new javax.swing.JLabel();
        jDateFechaIni = new com.toedter.calendar.JDateChooser();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        lCliente1 = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("jLabel3");
        jLabel3.setName("jLabel3"); // NOI18N

        setMaximumSize(new java.awt.Dimension(0, 0));

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText(" Tesorería Cliente");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        lFechaFin1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin1.setText("Fecha Hasta");
        lFechaFin1.setName("lFechaFin1"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        lFechaIni1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni1.setText("Fecha Desde");
        lFechaIni1.setName("lFechaIni1"); // NOI18N

        jDateFechaIni.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaIni.setName("jDateFechaIni"); // NOI18N

        jToggleButtonCliente.setText("Buscar Cliente");
        jToggleButtonCliente.setName("jToggleButtonCliente");
        jToggleButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonClienteActionPerformed(evt);
            }
        });

        lCliente1.setForeground(new java.awt.Color(0, 0, 100));
        lCliente1.setText("Cliente");
        lCliente1.setName("lCliente1");

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFechaIni1)
                                    .addComponent(lCliente1))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(95, 95, 95)
                                        .addComponent(lFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lFechaIni1)
                    .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCliente1)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.BuscarTesoreriaClienteInf .dispose();
       CSDesktop.menuTesoreriaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        int numFl = 0;
        String fechaI="";
        String fechaF="";
        String fechaIFc="";
        String fechaFFc="";
        System.out.println("Inicio");
        
        
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
        
        /*String query = "SELECT distinct(fl.fl_id), fl.fl_fecha, fl.fl_num, cl.cl_nombre, " +
                       "fl.fl_importe, fl.fl_iva, fl.fl_importe_total, cl.cl_plazo, fp.fp_tipo, " +
                       "fl.fl_estado, fl.fl_fecha_pago, cl.cl_num_cuenta, fl.fl_observaciones, cl.cl_dias_plazo " +
                       "FROM fl_factura_cliente fl, pe_pedidos pe, cl_clientes cl, fp_forma_pago fp " +
                       "WHERE  fl.cl_id = cl.cl_id AND cl.fp_id = fp.fp_id AND fl.fl_num = pe.pe_num_fa_cl";*/
        
        String query="";

        System.out.println("Entrando");
        if (numFl == 0 && cliente.equals("") && 
           (fechaI.equals("") && fechaF.equals("")) &&
           (fechaIFc.equals("") && fechaFFc.equals("")))
        {
            System.out.println("Entra");
            jButtonBuscar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar número de factura, un Cliente o período de tiempo</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonBuscar.setEnabled(true);
        }
        else
        {
           /* if (numFl != 0)
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
                query = query + " AND cl.fp_id='" + fPagoId + "'";
            }*/

            //query = query + " ORDER BY fl.fl_fecha ASC";
            System.out.println(query);
            /**try {
                CSResultBuscarTesoreriaClienteInf resultBuscarTesoreriaClInf = new CSResultBuscarTesoreriaClienteInf(listaResul,(ArrayList)listaResultados);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CSLanzarInformeTesoreriaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente.");
        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true);
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(jTextCliente, "", true);
        CSDesktop.BuscaCliente.getContentPane().add(panel, BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add(CSDesktop.BuscaCliente);
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible(true);
    }//GEN-LAST:event_jToggleButtonClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private com.toedter.calendar.JDateChooser jDateFechaIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente1;
    private javax.swing.JLabel lFechaFin1;
    private javax.swing.JLabel lFechaIni1;
    // End of variables declaration//GEN-END:variables

   

    /**
     *
     * @throws SQLException
     */
   


    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }
}