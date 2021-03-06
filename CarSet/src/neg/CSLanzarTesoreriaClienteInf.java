package neg;

import data.BeanAuxInformeTesoreriaCliente;
import data.Cliente;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanAccessLanguageException;

/**
 *
 * @author raulin
 */
public class CSLanzarTesoreriaClienteInf extends javax.swing.JPanel
{
    private int clienteID;

    public CSLanzarTesoreriaClienteInf() throws SQLException
    {
        initComponents();

        getFPagoClientes();

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
        lFPago = new javax.swing.JLabel();
        jComboBoxFPago = new javax.swing.JComboBox();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
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
        jToggleButtonCliente.setName("jToggleButtonCliente"); // NOI18N
        jToggleButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonClienteActionPerformed(evt);
            }
        });

        lCliente1.setForeground(new java.awt.Color(0, 0, 100));
        lCliente1.setText("Cliente");
        lCliente1.setName("lCliente1"); // NOI18N

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        lFPago.setForeground(new java.awt.Color(0, 0, 100));
        lFPago.setText("Forma de Pago");
        lFPago.setName("lFPago"); // NOI18N

        jComboBoxFPago.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxFPago.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxFPago.setName("jComboBoxFPago"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFechaIni1)
                                    .addComponent(lCliente1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addComponent(jToggleButtonCliente)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lFPago)
                                        .addGap(15, 15, 15)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxFPago, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lFechaIni1)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCliente1)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFPago)
                    .addComponent(jToggleButtonCliente))
                .addGap(104, 104, 104)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.BuscarTesoreriaClienteInf.dispose();
       CSDesktop.menuTesoreriaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        String fechaI="";
        String fechaF="";
        
        String cliente = new String(jTextCliente.getText());
        Cliente cl = new Cliente();
        clienteID = cl.getClienteID(cliente);

        int fPago = jComboBoxFPago.getSelectedIndex();

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

        if (cliente.equals("") && 
           (fechaI.equals("") && fechaF.equals("")))
        {
            jButtonBuscar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar un Cliente o período de tiempo</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonBuscar.setEnabled(true);
        }
        else
        {
            List listaResultados = new ArrayList();
            HashMap<Integer, BeanAuxInformeTesoreriaCliente> listaResul = new HashMap<Integer, BeanAuxInformeTesoreriaCliente>();
            String query_cl = "SELECT distinct(cl.cl_id) FROM fl_factura_cliente fl,cl_clientes cl WHERE  fl.cl_id = cl.cl_id";
            if (!cliente.equals("")) {
                query_cl = query_cl + " AND cl.cl_id = " + clienteID;
            }
            if ((!fechaI.equals("")) && (!fechaF.equals("")))
            {
                query_cl = query_cl + " AND fl.fl_fecha >= '" + fechaI + "' AND fl.fl_fecha <= '" + fechaF + "'";
            }
            System.out.println(query_cl);
            
            ResultSet rs_cl = CSDesktop.datos.select(query_cl);
            try {

                List aClientes = new ArrayList();
                while (rs_cl.next()) {
                    aClientes.add(rs_cl.getInt("cl_id"));
                }
                rs_cl.close();

 
                for(int i = 0; i < aClientes.size(); i++)
                {
                    String query = "SELECT distinct(fl.fl_id), fl.fl_fecha, fl.fl_num, cl.cl_id, cl.cl_nombre, " +
                                    "fl.fl_importe, fl.fl_iva, fl.fl_importe_total, cl.cl_plazo, cl.cl_dias_plazo, fp.fp_tipo, " +
                                    "fl.fl_estado, fl.fl_fecha_pago, cl.cl_num_cuenta, cl.cl_dias_plazo " +
                                    "FROM fl_factura_cliente fl,cl_clientes cl, fp_forma_pago fp " +
                                    "WHERE  fl.cl_id = cl.cl_id AND cl.fp_id = fp.fp_id AND cl.cl_id = " + aClientes.get(i);

                    if ((!fechaI.equals("")) && (!fechaF.equals(""))) {
                        query = query + " AND fl.fl_fecha >= '" + fechaI + "' AND fl.fl_fecha <= '" + fechaF + "'";
                    }
                    if (fPago != 0) {
                        query = query + " AND cl.fp_id='" + fPago + "'";
                    }
                    query = query + " ORDER BY fl.fl_fecha ASC";

                    System.out.println(query);
                    
                    ResultSet rs = CSDesktop.datos.select(query);

                    int size = 0;
                    try {
                        double sumaFechaVencida = 0;
                        double sumaPendienteVencer = 0;
                        double sumaTotalPendientesCobro = 0;
                        double sumaFacturasCobradas = 0;
                        double sumaFacturasIncobrables = 0;
                        double sumaFacturasAplazadas = 0;

                        //Count del resulset
                        rs.last();
                        size = rs.getRow();
                        rs.beforeFirst();
                        int cont = 0;

                        System.out.println("size: " + size);
                        
                        while (rs.next())
                        {
                            // SE PONE LA FECHA DE LA FACTURA EN EL FORMATO ELEGIDO
                            String[] tempOrigen = null;
                            tempOrigen = (rs.getString("fl_fecha")).split("\\-");
                            String nuevaFechaFactura = tempOrigen[2] + "/" + tempOrigen[1] + "/" + tempOrigen[0];
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date datehora = null;
                            try {
                                datehora = sdf1.parse(nuevaFechaFactura);
                            } catch (ParseException ex) {
                                Logger.getLogger(CSLanzarFactura.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // PARA PONER UNA FECHA ENTREGA, DEPENDIENDO DEL PERIODO DE FACTURACION DEL CLIENTE.
                            String plazoPago = rs.getString("cl_plazo");
                            int diasPlazo = 0;
                            if (plazoPago.equals("Especial")) {
                                diasPlazo = rs.getInt("cl_dias_plazo");
                            } else {
                                diasPlazo = Integer.parseInt(plazoPago.substring(0, 2));
                            }
                            Calendar myGDate = new GregorianCalendar();
                            myGDate.setTime(datehora);
                            myGDate.add(Calendar.DAY_OF_MONTH, diasPlazo);
                            Date fechaActual = myGDate.getTime();
                            String estado = rs.getString("fl_estado");
                            Date hoy = new Date();
                            if (estado.equals("PTE") && hoy.after(fechaActual)) {
                                sumaFechaVencida += rs.getDouble("fl_importe_total");
                            }
                            if (estado.equals("PTE") && hoy.before(fechaActual)) {
                                sumaPendienteVencer += rs.getDouble("fl_importe_total");
                            }
                            if (estado.equals("COBRADO")) {
                                sumaFacturasCobradas += rs.getDouble("fl_importe_total");
                            }
                            if (estado.equals("INCOBRABLE")) {
                                sumaFacturasIncobrables += rs.getDouble("fl_importe_total");
                            }
                            if (estado.equals("DEVOLUCIÓN") || estado.equals("APLAZADO")) {
                                sumaFacturasAplazadas += rs.getDouble("fl_importe_total");
                            }

                            cont++;
                            if(size == cont){
                                BeanAuxInformeTesoreriaCliente dato = new BeanAuxInformeTesoreriaCliente();
                                dato.setNombreCliente(rs.getString("cl.cl_nombre"));
                                dato.setImporteFechaVencida(sumaFechaVencida);
           System.out.println("sumaFechaVencida: "+sumaFechaVencida);
                                dato.setImportePendienteVencer(sumaPendienteVencer);
           System.out.println("sumaPendienteVencer: "+sumaPendienteVencer);
                                sumaTotalPendientesCobro += sumaFechaVencida + sumaPendienteVencer;
                                dato.setImporteTotalPendientesCobro(sumaTotalPendientesCobro);
           System.out.println("sumaTotalPendientesCobro: "+sumaTotalPendientesCobro);
                                dato.setImporteFacturasCobradas(sumaFacturasCobradas);
           System.out.println("sumaFacturasCobradas: "+sumaFacturasCobradas);
                                dato.setImporteFacturasIncobrables(sumaFacturasIncobrables);
           System.out.println("sumaFacturasIncobrables: "+sumaFacturasIncobrables);
                                dato.setImporteFacturasAplazadas(sumaFacturasAplazadas);
           System.out.println("sumaFacturasAplazadas: "+sumaFacturasAplazadas);

                                listaResultados.add(rs.getInt("cl_id"));
                               /* ArrayList aTsCl = datosInformeTesoreria(rs.getInt("cl_id"));
                                if (aTsCl.size() > 0){
                                    dato.setFecha((Date) aTsCl.get(0));
                                    dato.setEmail(aTsCl.get(1).toString());
                                    dato.setContacto(aTsCl.get(2).toString());
                                    dato.setObservaciones(aTsCl.get(3).toString());
                                    dato.setFormaPago(aTsCl.get(4).toString());
                                }*/

                                listaResul.put(rs.getInt("cl_id"), dato);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CSLanzarTesoreriaProveedorInf.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CSLanzarTesoreriaClienteInf.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                CSResultBuscarTesoreriaClienteInf resultBuscarTesoreriaClInf = new CSResultBuscarTesoreriaClienteInf(listaResul,(ArrayList)listaResultados);
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
    private javax.swing.JComboBox jComboBoxFPago;
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
    private javax.swing.JLabel lFPago;
    private javax.swing.JLabel lFechaFin1;
    private javax.swing.JLabel lFechaIni1;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @throws SQLException
     */
     private void getFPagoClientes() throws SQLException
    {
        ResultSet rs = CSDesktop.datos.select("SELECT fp_id, fp_tipo FROM fp_forma_pago");
        int j = 0;
        String valor = "";
        jComboBoxFPago.addItem("Selecciona");

        while(rs.next())
        {
            valor = rs.getString("fp_tipo");
            jComboBoxFPago.addItem(valor);
            jComboBoxFPago.setSelectedIndex(0);

            //jCombPago.addItem(rs.getString("fp_tipo"))
            j++;
        }
     }

     /**
      * Método para recuperar los datos del la table Tesorería cliente
      * @param cl_id
      * @param fechaPago
      * @param email
      * @param contacto
      * @param observaciones
      * @param formaPago
      * @return
      * @throws SQLException
      */
     public ArrayList datosInformeTesoreria(int cl_id) throws SQLException
     {
         ArrayList aTsCl = new ArrayList();
        System.out.println("Entra: "+ cl_id);
        ResultSet rsTc = CSDesktop.datos.select("SELECT * FROM ts_tesoreria_informe WHERE cl_id = " + cl_id);

        while(rsTc.next())
        {
            aTsCl.add(0, rsTc.getDate("ts_fecha"));
            //dato.setFecha(rs.getDate("ts_fecha"));
            aTsCl.add(1, rsTc.getString("ts_email"));
            //dato.setEmail(rs.getString("ts_email"));
            aTsCl.add(2, rsTc.getString("ts_contacto"));
            //dato.setContacto(rs.getString("ts_contacto"));
            aTsCl.add(3, rsTc.getString("ts_observaciones"));
            //dato.setObservaciones(rs.getString("ts_observaciones"));
            aTsCl.add(4, rsTc.getString("ts_forma_pago"));
//            dato.setFormaPago(rs.getString("ts_forma_pago"));
        }
        return aTsCl;
    }

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