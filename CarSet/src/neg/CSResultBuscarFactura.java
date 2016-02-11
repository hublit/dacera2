/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

import data.BeanFactura;
import data.BeanCliente;
import java.net.UnknownHostException;
import net.sf.jasperreports.engine.JRException;
import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author depr102
 */
public class CSResultBuscarFactura extends javax.swing.JPanel
{
    Object[] datosFila = null;
    TablaModelo modelo = new TablaModelo();
    ArrayList lista=new ArrayList();
    ArrayList pedidos=new ArrayList();
    BeanCliente bCliente=new BeanCliente();
    String fechaSI="";
    String fechaSF="";
    int clienteID=0;
    Date hoy = new Date();


    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarFactura(String query,BeanCliente beanCliente, String fechaI,String fechaF)
    {
        modelo = new TablaModelo();
       
        ResultSet rs = CSDesktop.datos.select(query);

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    jButtonCerrar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        for (int k = 0; k < this.getComponents().length; k ++)
        {
                this.getComponents()[k].addKeyListener(l);
        }
        addKeyListener(l);

        modelo.setColumnIdentifiers(new String[] {"NUM", "FECHA", "ORIGEN", "DESTINO", "SOPORTE", "MATRICULA", "OBSERVACIONES"});

        int numeroFila = 0;
        try {
            while (rs.next()) {

                BeanFactura campos = new BeanFactura();

                Boolean unidoEstado = true;
                if (rs.getString("estado") != null){
                    unidoEstado = (rs.getString("estado").equals("Entregado") || rs.getString("estado").equals("Validado") || rs.getString("estado").equals("Fallido")) ? true : false;
                }
                if (rs.getLong("pe_num_unido") == 0 &&
                   (rs.getString("pe_estado").equals("") || rs.getString("pe_estado").equals("Entregado") || 
                    rs.getString("pe_estado").equals("Validado") || rs.getString("pe_estado").equals("Fallido")) &&
                   unidoEstado)
                {

                    campos.setNumPedido(rs.getLong("pe_num"));
                    campos.setFecha(rs.getString("pe_fecha"));
                    campos.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                    campos.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                    if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                        campos.setProvinciaDestino(rs.getString("destino_unido"));
                        campos.setServicioDestino(rs.getString("destino_unido"));
                        campos.setPoblacionDestino(rs.getString("pob_unido"));
                    }else{
                        campos.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                        campos.setServicioDestino(rs.getString("pe_provincia_destino"));
                        campos.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                    }
                    campos.setServicio(rs.getString("pe_servicio"));
                    campos.setServicioOrigen(rs.getString("pe_servicio_origen"));
                    campos.setServicioEspecial(rs.getString("pe_servicio_especial"));
                    campos.setDiasCampa(rs.getString("pe_dias_campa"));
                    campos.setFactor(rs.getString("fc_id"));
                    campos.setSoporte(rs.getString("pe_soporte"));
                    campos.setMatricula(rs.getString("pe_ve_matricula"));
                    campos.setMarca(rs.getString("pe_ve_marca"));
                    campos.setModelo(rs.getString("pe_ve_modelo"));
                    campos.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                    campos.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                    campos.setSuplemento(rs.getString("pe_suplemento"));
                    campos.setDescripcion(rs.getString("pe_descripcion"));
                    //campos.setTarifa(rs.getString("tc_tarifa"));
                    campos.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                    campos.setNumCamion(rs.getString("pe_num_en_camion"));
                    campos.setObsInFactura(rs.getBoolean("pe_ob_cl_mail"));
                    campos.setKms(rs.getString("pe_kms"));
                    campos.setAtt(rs.getString("cl_email"));
                    campos.setVe_estado(rs.getString("pe_ve_estado"));
                    campos.setEstado(rs.getString("pe_estado"));

                    lista.add(campos);
                    pedidos.add(rs.getLong("pe_num"));


                    datosFila = new Object[modelo.getColumnCount()];

                    int j = 0;
                    for (int k = 0; k < 20; k++) {

                        if (k==0 ||k==1 || k == 2 || k == 3 || k == 14 || k==15 || k==24) {
                            if(k==1)
                            {
                                 String fecha=(rs.getObject(k+1)).toString();
                                 String [] temp = null;
                                 temp = fecha.split("\\-");
                                 String anyo=temp[0];
                                 String mes=temp[1];
                                 String dia=temp[2];
                                 String nueva=dia+"-"+mes+"-"+anyo;

                                 datosFila[j] = nueva;
                            }
                            else if(k==3){

                                if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                                    datosFila[j] = rs.getString("destino_unido");
                                }
                                else{
                                    datosFila[j] = rs.getObject(k + 1);
                                }
                            }
                            else
                            {
                                datosFila[j] = rs.getObject(k + 1);
                                System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                            }
                            j++;
                        }
                    }

                    modelo.addRow(datosFila);
                    numeroFila++;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Pedidos con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultFacturaPedido = new JInternalFrame("Resultado Factura Pedidos", true, false, false, true );
            CSDesktop.ResultFacturaPedido.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultFacturaPedido.pack();
            //CSDesktop.BuscarPedido.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultFacturaPedido );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultFacturaPedido.getSize();
            CSDesktop.ResultFacturaPedido.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultFacturaPedido.setVisible( true );
        }
        initComponents();
        jDateFechaFactura.setDate(hoy);
        jTable1.setModel(modelo);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(25);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(40);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(100);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(50);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(50);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(410);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        

        bCliente=beanCliente;
        fechaSI=fechaI;
        fechaSF=fechaF;
        String clienteSID=beanCliente.getCl_id();
        clienteID=Integer.parseInt(clienteSID);

        
       
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonCerrar = new javax.swing.JButton();
        jButtonGenerar = new javax.swing.JButton();
        jButtonPrev = new javax.swing.JButton();
        lFechaIni1 = new javax.swing.JLabel();
        jDateFechaFactura = new com.toedter.calendar.JDateChooser();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 255));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jButtonCerrar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonCerrar.setText("Cancelar");
        jButtonCerrar.setName("jButtonCerrar"); // NOI18N
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jButtonGenerar.setText("Generar");
        jButtonGenerar.setName("jButtonGenerar"); // NOI18N
        jButtonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarActionPerformed(evt);
            }
        });

        jButtonPrev.setText("Previsualizar");
        jButtonPrev.setName("jButtonPrev"); // NOI18N
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        lFechaIni1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni1.setText("Fecha Factura ");
        lFechaIni1.setName("lFechaIni1"); // NOI18N

        jDateFechaFactura.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFactura.setName("jDateFechaFactura"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jButtonGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jButtonCerrar)
                        .addGap(207, 207, 207))))
            .addGroup(layout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addComponent(lFechaIni1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(478, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lFechaIni1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrev)
                    .addComponent(jButtonGenerar)
                    .addComponent(jButtonCerrar))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultFacturaPedido.dispose();
        CSDesktop.menuFacturaClientePedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarActionPerformed

        int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
         else if (celdas==1)
        {

            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres generar la Factura y un nuevo numero?");

            if (JOptionPane.OK_OPTION == confirmado) {
                int numero = 0;
                String fechaFac="";
                String query = "Select max(fl_id) from fl_factura_cliente";
                ResultSet rs = CSDesktop.datos.select(query);
                try {
                    while (rs.next()) {
                        numero =Integer.valueOf(rs.getInt("max(fl_id)"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    int longitud=jTable1.getSelectedRow();

                    Calendar fechaCalendar = jDateFechaFactura.getCalendar();
                    if (fechaCalendar!=null)
                    {
                        Date fecha = fechaCalendar.getTime();
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                        fechaFac=formatoDeFecha.format(fecha);
                    }

                    BeanFactura factura=new BeanFactura();
                    factura=(BeanFactura)lista.get(longitud);
                    long pedido=(Long)pedidos.get(longitud);                    
                    
                    ArrayList listaPedidos=new ArrayList();
                    listaPedidos.add(pedido);
            
                    //Buscamos los pedidos unidos para poder cambiarles el estado
                    try {
                        ArrayList<Integer> peUnidosList = getPedidosUnidos(pedido);
                        for (int i = 0; i < peUnidosList.size(); i++){
                            listaPedidos.add(peUnidosList.get(i));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ArrayList listaFacturas=new ArrayList();
                    listaFacturas.add(factura);
                    CSLanzarFactura facturaFinal = new CSLanzarFactura();
                    try {

                        facturaFinal.lanzar(listaFacturas, bCliente, fechaFac, numero+1 , clienteID, fechaSI, fechaSF, listaPedidos,1);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                        Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
         }
}//GEN-LAST:event_jButtonGenerarActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        /*try {

            //LanzarFactura(0);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
        else if (celdas>1)
        {
            jButtonPrev.setEnabled(false);
            jButtonGenerar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
             jButtonPrev.setEnabled(true);
            jButtonGenerar.setEnabled(true);
        }
        else if (celdas==1)
        {   
            String fechaFac="";
            Calendar fechaCalendar = jDateFechaFactura.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaFac=formatoDeFecha.format(fecha);
            }
            int longitud=jTable1.getSelectedRow();
            BeanFactura factura=new BeanFactura();
            factura=(BeanFactura)lista.get(longitud);
            long pedido=(Long)pedidos.get(longitud);
            
            ArrayList listaPedidos=new ArrayList();
            listaPedidos.add(pedido);
            
            ArrayList listaFacturas=new ArrayList();
            listaFacturas.add(factura);
            CSLanzarFactura facturaFinal = new CSLanzarFactura();
            try {
                facturaFinal.lanzar(listaFacturas, bCliente, fechaFac, 0, clienteID, fechaSI, fechaSF, listaPedidos,1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(CSResultBuscarFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_jButtonPrevActionPerformed


    /**
     * Buscamos los pedidos unidos
     * @throws SQLException
     */
    private ArrayList<Integer> getPedidosUnidos(long pe_num) throws SQLException
    {
        ArrayList<Integer> peUnidos = new ArrayList<Integer>();
        
        ResultSet rsPeUnidos = CSDesktop.datos.select("SELECT pe_num FROM pe_pedidos WHERE pe_num_unido = '"+pe_num+"'");
        
        int num_unido = 0;
                
        while(rsPeUnidos.next())
        {
            num_unido = rsPeUnidos.getInt("pe_num");
            peUnidos.add(num_unido);
        }
        rsPeUnidos.close();

        return peUnidos;
     }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonGenerar;
    private javax.swing.JButton jButtonPrev;
    private com.toedter.calendar.JDateChooser jDateFechaFactura;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lFechaIni1;
    // End of variables declaration//GEN-END:variables

}