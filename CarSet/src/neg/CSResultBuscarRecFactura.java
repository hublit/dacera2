/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

import data.BeanCliente;
import data.BeanFactura;
import data.BeanRecFactura;
import data.Cliente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import net.sf.jasperreports.engine.JRException;
import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


/**
 *
 * @author depr102
 */
public class CSResultBuscarRecFactura extends javax.swing.JPanel
{
    ArrayList facturas = new ArrayList();
    ArrayList lista = new ArrayList();
    ArrayList pedidos = new ArrayList();
    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarRecFactura(String query) throws UnknownHostException, FileNotFoundException, IOException
    {
        
        TablaModelo modelo = new TablaModelo();
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

        modelo.setColumnIdentifiers(new String[] {"ID", "NUMERO", "FECHA" , "CLIENTE" , "F.DESDE", "F.HASTA", "IMPORTE","TIPO"});

        int numeroFila = 0;
        try {
            while (rs.next()) {

                BeanRecFactura recFactura = new BeanRecFactura();

                recFactura.setNumFactura(rs.getString("fl_num"));
                recFactura.setFechaFactura(rs.getString("fl_fecha"));
                recFactura.setCliente(rs.getString("cl_nombre"));
                recFactura.setFechaDesde(rs.getString("fl_fecha_desde"));
                recFactura.setFechaHasta(rs.getString("fl_fecha_hasta"));
                recFactura.setImporte(rs.getString("fl_importe_total"));
                recFactura.setTipo(rs.getString("fl_tipo"));

                facturas.add(recFactura);

                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 8; k++) {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k == 6 || k == 7 ) {
                        if((k==2) || (k==4)|| (k==5))
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
                        else
                        {
                            datosFila[j] = rs.getObject(k + 1);
//                            System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                        }
                        j++;
                    }
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ResultRecFactura = new JInternalFrame("Resultado Búsqueda Facturas Cliente", true, false, false, true );
            CSDesktop.ResultRecFactura.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultRecFactura.pack();
            CSDesktop.RecuperarFacturaCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultRecFactura );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultRecFactura.getSize();
            CSDesktop.ResultRecFactura.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultRecFactura.setVisible( true );
        }
        initComponents();
        Date hoy = new Date();
        jDateFechaFactura.setDate(hoy);
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(170);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(350);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(100);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(100);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(103);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(100);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        

        jTable1.setAutoCreateRowSorter(true);
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
    }

     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        int componente = table.getSelectedRow();
        Component comp = getTableCellRendererComponent(table,  value, true, hasFocus, row, col);


     String s =  table.getModel().getValueAt(row, col ).toString();

     if(s.equalsIgnoreCase("Fail"))
     {
         comp.setForeground(Color.red);
     }
     else 
     {
         comp.setForeground(null);
     }

     return( comp );
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
        jButtonAbono = new javax.swing.JButton();
        jButtonAbonoPrev = new javax.swing.JButton();
        lObservaciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextObservaciones = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jButtonRecuperarFactura = new javax.swing.JButton();
        jDateFechaFactura = new com.toedter.calendar.JDateChooser();
        lFechaIni1 = new javax.swing.JLabel();

        setAutoscrolls(true);

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

        jButtonAbono.setText("Generar Abono");
        jButtonAbono.setName("jButtonAbono"); // NOI18N
        jButtonAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbonoActionPerformed(evt);
            }
        });

        jButtonAbonoPrev.setText("Prev. Abono");
        jButtonAbonoPrev.setMaximumSize(new java.awt.Dimension(105, 23));
        jButtonAbonoPrev.setMinimumSize(new java.awt.Dimension(105, 23));
        jButtonAbonoPrev.setName("jButtonAbonoPrev"); // NOI18N
        jButtonAbonoPrev.setPreferredSize(new java.awt.Dimension(105, 23));
        jButtonAbonoPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbonoPrevActionPerformed(evt);
            }
        });

        lObservaciones.setForeground(new java.awt.Color(0, 0, 100));
        lObservaciones.setText("Observaciones");
        lObservaciones.setName("lObservaciones"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextObservaciones.setName("jTextObservaciones"); // NOI18N
        jTextObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextObservacionesFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTextObservaciones);

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Solo para Abono");
        jLabel1.setName("jLabel1"); // NOI18N

        jButtonRecuperarFactura.setText("Recuperar Factura");
        jButtonRecuperarFactura.setName("jButtonRecuperarFactura"); // NOI18N
        jButtonRecuperarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecuperarFacturaActionPerformed(evt);
            }
        });

        jDateFechaFactura.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFactura.setName("jDateFechaFactura"); // NOI18N

        lFechaIni1.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni1.setText("Fecha Abono ");
        lFechaIni1.setName("lFechaIni1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonAbonoPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButtonAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(lFechaIni1)
                                .addGap(18, 18, 18)
                                .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButtonRecuperarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                                .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAbono, jButtonAbonoPrev, jButtonRecuperarFactura});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonAbonoPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAbono)
                    .addComponent(jButtonRecuperarFactura)
                    .addComponent(lFechaIni1)
                    .addComponent(jDateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(616, 616, 616)
                .addComponent(jButtonCerrar)
                .addGap(11, 11, 11))
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultRecFactura.dispose();
        CSDesktop.menuRecuperarFacturaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbonoActionPerformed
      int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonAbono.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonAbono.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonAbono.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonAbono.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas==1)
        {
                int seleccion = jTable1.getSelectedRow();
                BeanRecFactura recFacturaAux = new BeanRecFactura();
                recFacturaAux = (BeanRecFactura) facturas.get(seleccion);
                String tipo=recFacturaAux.getTipo();
                if((tipo.equals("Rectificada"))||(tipo.equals("Abono")))
                {
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Opción sólo disponible para Facturas.</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);
                }
                else
                {
                    lista.clear();
                    pedidos.clear();
                    String observaciones=jTextObservaciones.getText();
                    Date fechaAbono=jDateFechaFactura.getDate();
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaFacturaAbono=formatoDeFecha.format(fechaAbono);
                    Cliente cliente = new Cliente();
                    try
                    {
                        int numero = 0;
                        String queryNum = "Select max(fl_id) from fl_factura_cliente";
                        ResultSet rsNum = CSDesktop.datos.select(queryNum);
                        try
                        {
                            while (rsNum.next())
                            {
                                numero =Integer.valueOf(rsNum.getInt("max(fl_id)"));
                            }
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        int cl_id = cliente.getClienteID(recFacturaAux.getCliente());
                        BeanCliente beanCliente = new BeanCliente();
                        beanCliente = cliente.getDatosFacturaCliente(cl_id);
                        beanCliente.setCl_id(String.valueOf(cl_id));
/*                        String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " +
                                    "pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " +
                                    "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " +
                                    "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " +
                                    "pe.pe_descripcion, pe.pe_kms, cl.cl_email, pe.pe_ve_estado " +
                                    "FROM pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl " +
                                    "WHERE pe.pe_num = pc.pe_num " +
                                    "AND pc.cl_id = cl.cl_id " +
                                    "AND (pe.pe_estado = 'Facturado' OR pe.pe_estado='Facturado y Validado') " +
                                    "AND '" + recFacturaAux.getFechaHasta() + "' " +
                                    "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";
 */
                        String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " +
                                   "pe.pe_servicio, pe.pe_poblacion_origen, pe.pe_poblacion_destino, pe.pe_servicio_especial, pe.pe_estado, " +
                                   "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_estado, " +
                                   "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento, pe.pe_num_en_camion, pe.pe_ob_cl_mail, " +
                                   "pe.pe_descripcion, pe.pe_num_unido, pe_unido.destino_unido, pe_unido.estado_unido, pe_unido.pob_unido, pe.pe_kms, cl.cl_email  " +
                                   "FROM (pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl) " +
                                   "LEFT JOIN (SELECT pe_num_unido AS num_unido, pe_provincia_destino, pe_provincia_destino AS destino_unido, pe_poblacion_destino AS pob_unido, pe_estado AS estado_unido " +
                                   "FROM pe_pedidos WHERE pe_fin_unido = 1 ORDER BY pe_num DESC) " +
                                   "pe_unido ON pe.pe_num = pe_unido.num_unido " +
                                   "WHERE pe.pe_num = pc.pe_num " +
                                   "AND pc.cl_id = cl.cl_id " +
                                    "AND (pe.pe_estado = 'Facturado' OR pe.pe_estado='Facturado y Validado') " +
//                                    "AND pe.pe_fecha = '" + recFacturaAux.getFechaHasta() + "' " +
                                    "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";
                        System.out.println(query);
                        ResultSet rs = CSDesktop.datos.select(query);
                        try
                        {
                           /* while (rs.next())
                            {
                                BeanFactura nueva = new BeanFactura();
                                nueva.setNumPedido(rs.getLong("pe_num"));
                                nueva.setFecha(rs.getString("pe_fecha"));
                                nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                nueva.setProvinciaDestino(rs.getString("pe_servicio_destino"));
                                nueva.setServicio(rs.getString("pe_servicio"));
                                nueva.setServicioOrigen(rs.getString("pe_servicio_origen"));
                                nueva.setServicioDestino(rs.getString("pe_servicio_destino"));
                                nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                                nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                                nueva.setFactor(rs.getString("fc_id"));
                                nueva.setSoporte(rs.getString("pe_soporte"));
                                nueva.setMatricula(rs.getString("pe_ve_matricula"));
                                nueva.setMarca(rs.getString("pe_ve_marca"));
                                nueva.setModelo(rs.getString("pe_ve_modelo"));
                                nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                                nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                                nueva.setSuplemento(rs.getString("pe_suplemento"));
                                nueva.setDescripcion(rs.getString("pe_descripcion"));
                                //nueva.setTarifa(rs.getString("tc_tarifa"));
                                nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                                nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                                nueva.setKms(rs.getString("pe_kms"));
                                nueva.setAtt(rs.getString("cl_email"));
                                nueva.setVe_estado(rs.getString("pe_ve_estado"));
                                nueva.setAux(recFacturaAux.getNumFactura());
                                lista.add(nueva);
                                pedidos.add(rs.getLong("pe_num"));
                            }*/
                            while (rs.next()) {
                                Boolean unidoEstado = true;
                                if (rs.getString("estado_unido") != null){
                                    unidoEstado = (rs.getString("estado_unido").equals("Facturado") || rs.getString("pe_estado").equals("Facturado y Validado")) ? true : false;
                                }
                                if (rs.getLong("pe_num_unido") == 0 &&
                                   (rs.getString("pe_estado").equals("") || rs.getString("pe_estado").equals("Facturado") || rs.getString("pe_estado").equals("Facturado y Validado")) &&
                                   unidoEstado)
                                {
                                    BeanFactura nueva = new BeanFactura();
                                    nueva.setNumPedido(rs.getLong("pe_num"));

                                    nueva.setFecha(rs.getString("pe_fecha"));
                                    nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                                    if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                                        nueva.setProvinciaDestino(rs.getString("destino_unido"));
                                        nueva.setServicioDestino(rs.getString("destino_unido"));
                                        nueva.setPoblacionDestino(rs.getString("pob_unido"));
                                    }else{
                                        nueva.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setServicioDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                                    }
                                    nueva.setServicio(rs.getString("pe_servicio"));
                                    nueva.setServicioOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                                    nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                                    nueva.setFactor(rs.getString("fc_id"));
                                    nueva.setSoporte(rs.getString("pe_soporte"));
                                    nueva.setMatricula(rs.getString("pe_ve_matricula"));
                                    nueva.setMarca(rs.getString("pe_ve_marca"));
                                    nueva.setModelo(rs.getString("pe_ve_modelo"));
                                    nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                                    nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                                    nueva.setSuplemento(rs.getString("pe_suplemento"));
                                    nueva.setDescripcion(rs.getString("pe_descripcion"));
                                    nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                                    nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                                    nueva.setObsInFactura(rs.getBoolean("pe_ob_cl_mail"));
                                    nueva.setKms(rs.getString("pe_kms"));
                                    nueva.setAtt(rs.getString("cl_email"));
                                    nueva.setVe_estado(rs.getString("pe_ve_estado"));
                                    nueva.setAux(recFacturaAux.getNumFactura());
                                    lista.add(nueva);
                                }
                                pedidos.add(rs.getLong("pe_num"));
                            }


                        } catch (SQLException ex) {
                            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        CSLanzarFactura factura = new CSLanzarFactura();
                        factura.lanzarAbono(lista, beanCliente, fechaFacturaAbono, numero+1, cl_id, recFacturaAux.getFechaDesde(), recFacturaAux.getFechaHasta(), pedidos, 2,observaciones);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnknownHostException ex) {
                        Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
        }
    }//GEN-LAST:event_jButtonAbonoActionPerformed

    private void jButtonAbonoPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbonoPrevActionPerformed
             int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonAbonoPrev.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonAbonoPrev.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonAbonoPrev.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonAbonoPrev.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas==1)
        {

                    int seleccion = jTable1.getSelectedRow();
                    BeanRecFactura recFacturaAux = new BeanRecFactura();
                    recFacturaAux = (BeanRecFactura) facturas.get(seleccion);
                    String tipo=recFacturaAux.getTipo();
                    if((tipo.equals("Rectificada"))||(tipo.equals("Abono")))
                    {
                        JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Opción sólo disponible para Facturas.</FONT></HTML>");
                        JOptionPane.showMessageDialog(null,errorFields);
                    }
                    else
                    {
                        try
                        {

                            lista.clear();
                            pedidos.clear();
                            String observaciones=jTextObservaciones.getText();
                            Date fechaAbono=jDateFechaFactura.getDate();
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaFacturaAbono=formatoDeFecha.format(fechaAbono);
                            Cliente cliente = new Cliente();
                            int cl_id = cliente.getClienteID(recFacturaAux.getCliente());
                            BeanCliente beanCliente = new BeanCliente();
                            beanCliente = cliente.getDatosFacturaCliente(cl_id);
                            beanCliente.setCl_id(String.valueOf(cl_id));
                            /*String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, pe.pe_poblacion_origen, " +
                                    "pe.pe_poblacion_destino, pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " +
                                    "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, " +
                                    "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, " +
                                    "pe.pe_descripcion, pe.pe_ve_estado, pe.pe_kms, cl.cl_email, pe.pe_poblacion_origen, pe.pe_poblacion_destino " +
                                    "FROM pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl " +
                                    "WHERE pe.pe_num = pc.pe_num " +
                                    "AND pc.cl_id = cl.cl_id " +
                                    "AND (pe.pe_estado = 'Facturado' OR pe.pe_estado='Facturado y Validado') " +
                                    "AND '" + recFacturaAux.getFechaHasta() + "' " +
                                    "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";*/

                            String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " +
                                   "pe.pe_servicio, pe.pe_poblacion_origen, pe.pe_poblacion_destino, pe.pe_servicio_especial, pe.pe_estado, " +
                                   "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_estado, " +
                                   "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento, pe.pe_num_en_camion, pe.pe_ob_cl_mail, " +
                                   "pe.pe_descripcion, pe.pe_num_unido, pe_unido.destino_unido, pe_unido.estado_unido, pe_unido.pob_unido, pe.pe_kms, cl.cl_email  " +
                                   "FROM (pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl) " +
                                   "LEFT JOIN (SELECT pe_num_unido AS num_unido, pe_provincia_destino, pe_provincia_destino AS destino_unido, pe_poblacion_destino AS pob_unido, pe_estado AS estado_unido " +
                                   "FROM pe_pedidos WHERE pe_fin_unido = 1 ORDER BY pe_num DESC) " +
                                   "pe_unido ON pe.pe_num = pe_unido.num_unido " +
                                   "WHERE pe.pe_num = pc.pe_num " +
                                   "AND pc.cl_id = cl.cl_id " +
                                    "AND (pe.pe_estado = 'Facturado' OR pe.pe_estado='Facturado y Validado') " +
//                                    "AND '" + recFacturaAux.getFechaHasta() + "' " +
                                    "AND pc.cl_id = " + cl_id + " AND pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

                            System.out.println(query);
                            ResultSet rs = CSDesktop.datos.select(query);
                            try
                            {
                             /*   while (rs.next())
                                {
                                    BeanFactura nueva = new BeanFactura();
                                    nueva.setNumPedido(rs.getLong("pe_num"));
                                    nueva.setFecha(rs.getString("pe_fecha"));
                                    nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                                    nueva.setServicioOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                                    nueva.setServicioDestino(rs.getString("pe_provincia_destino"));
                                    nueva.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                                    nueva.setServicio(rs.getString("pe_servicio"));
                                    nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                                    nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                                    nueva.setFactor(rs.getString("fc_id"));
                                    nueva.setSoporte(rs.getString("pe_soporte"));
                                    nueva.setMatricula(rs.getString("pe_ve_matricula"));
                                    nueva.setMarca(rs.getString("pe_ve_marca"));
                                    nueva.setModelo(rs.getString("pe_ve_modelo"));
                                    nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                                    nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                                    nueva.setSuplemento(rs.getString("pe_suplemento"));
                                    nueva.setDescripcion(rs.getString("pe_descripcion"));
                                    nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                                    nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                                    nueva.setKms(rs.getString("pe_kms"));
                                    nueva.setAtt(rs.getString("cl_email"));
                                    nueva.setVe_estado(rs.getString("pe_ve_estado"));
                                    nueva.setAux(recFacturaAux.getNumFactura());
                                    lista.add(nueva);
                                    pedidos.add(rs.getLong("pe_num"));
                                }*/

                            while (rs.next()) {
                                Boolean unidoEstado = true;
                                if (rs.getString("estado_unido") != null){
                                    unidoEstado = (rs.getString("estado_unido").equals("Facturado") || rs.getString("pe_estado").equals("Facturado y Validado")) ? true : false;
                                }
                                if (rs.getLong("pe_num_unido") == 0 &&
                                   (rs.getString("pe_estado").equals("") || rs.getString("pe_estado").equals("Facturado") || rs.getString("pe_estado").equals("Facturado y Validado")) &&
                                   unidoEstado)
                                {
                                    BeanFactura nueva = new BeanFactura();
                                    nueva.setNumPedido(rs.getLong("pe_num"));

                                    nueva.setFecha(rs.getString("pe_fecha"));
                                    nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                                    if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                                        nueva.setProvinciaDestino(rs.getString("destino_unido"));
                                        nueva.setServicioDestino(rs.getString("destino_unido"));
                                        nueva.setPoblacionDestino(rs.getString("pob_unido"));
                                    }else{
                                        nueva.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setServicioDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                                    }
                                    nueva.setServicio(rs.getString("pe_servicio"));
                                    nueva.setServicioOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                                    nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                                    nueva.setFactor(rs.getString("fc_id"));
                                    nueva.setSoporte(rs.getString("pe_soporte"));
                                    nueva.setMatricula(rs.getString("pe_ve_matricula"));
                                    nueva.setMarca(rs.getString("pe_ve_marca"));
                                    nueva.setModelo(rs.getString("pe_ve_modelo"));
                                    nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                                    nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                                    nueva.setSuplemento(rs.getString("pe_suplemento"));
                                    nueva.setDescripcion(rs.getString("pe_descripcion"));
                                    nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                                    nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                                    nueva.setObsInFactura(rs.getBoolean("pe_ob_cl_mail"));
                                    nueva.setKms(rs.getString("pe_kms"));
                                    nueva.setAtt(rs.getString("cl_email"));
                                    nueva.setVe_estado(rs.getString("pe_ve_estado"));
                                    nueva.setAux(recFacturaAux.getNumFactura());
                                    lista.add(nueva);
                                }
                                pedidos.add(rs.getLong("pe_num"));
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        CSLanzarFactura factura = new CSLanzarFactura();
                        factura.lanzarAbono(lista, beanCliente, fechaFacturaAbono, 0, cl_id, recFacturaAux.getFechaDesde(), recFacturaAux.getFechaHasta(), pedidos, 2,observaciones);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        }
    }//GEN-LAST:event_jButtonAbonoPrevActionPerformed

    private void jTextObservacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextObservacionesFocusLost
        String ObservacionesM = jTextObservaciones.getText().toUpperCase();
        jTextObservaciones.setText(ObservacionesM);
}//GEN-LAST:event_jTextObservacionesFocusLost

    private void jButtonRecuperarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecuperarFacturaActionPerformed
       int celdas = jTable1.getSelectedRowCount();
        if(celdas==0)
        {
            jButtonRecuperarFactura.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperarFactura.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas>1)
        {
            jButtonRecuperarFactura.setEnabled(false);
            jButtonCerrar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes seleccionar un pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonRecuperarFactura.setEnabled(true);
            jButtonCerrar.setEnabled(true);
        }
         else if (celdas==1)
        {

                    int seleccion = jTable1.getSelectedRow();
                    BeanRecFactura recFacturaAux = new BeanRecFactura();
                    recFacturaAux = (BeanRecFactura) facturas.get(seleccion);
                    String tipo=recFacturaAux.getTipo();
                    if(!tipo.equals("Factura"))
                    {
                        JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Sólo puedes recuperar Facturas.</FONT></HTML>");
                        JOptionPane.showMessageDialog(null,errorFields);
                    }
                    else
                    {
                        try {
                        lista.clear();
                        pedidos.clear();
                        Cliente cliente = new Cliente();
                        int cl_id = cliente.getClienteID(recFacturaAux.getCliente());
                        BeanCliente beanCliente = new BeanCliente();
                        beanCliente = cliente.getDatosFacturaCliente(cl_id);
                        beanCliente.setCl_id(String.valueOf(cl_id));

                        /*String query="SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, pe.pe_servicio, " +
                        "pe.pe_poblacion_origen, pe.pe_poblacion_destino, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, " +
                        "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_modelo, pe.pe_ta_es_cliente, " +
                        "pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion, pe.pe_descripcion, pe.pe_ve_estado, pe_ob_cl_mail, pe.pe_kms, cl.cl_email" +
                        " FROM pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl " +
                        " WHERE pe.pe_num = pc.pe_num " +
                        "AND pc.cl_id = cl.cl_id " +
                         */
                       String query = "SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " +
                       "pe.pe_servicio, pe.pe_poblacion_origen, pe.pe_poblacion_destino, pe.pe_servicio_especial, pe.pe_estado, " +
                       "pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_estado, " +
                       "pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_suplemento, pe.pe_num_en_camion, pe.pe_ob_cl_mail, " +
                       "pe.pe_descripcion, pe.pe_num_unido, pe_unido.destino_unido, pe_unido.estado_unido, pe_unido.pob_unido, pe.pe_kms, cl.cl_email  " +
                       "FROM (pe_pedidos pe, pc_pedidos_clientes pc, cl_clientes cl) " +
                       "LEFT JOIN (SELECT pe_num_unido AS num_unido, pe_provincia_destino, pe_provincia_destino AS destino_unido, pe_poblacion_destino AS pob_unido, pe_estado AS estado_unido " +
                       "FROM pe_pedidos WHERE pe_fin_unido = 1 ORDER BY pe_num DESC) " +
                       "pe_unido ON pe.pe_num = pe_unido.num_unido " +
                       "WHERE pe.pe_num = pc.pe_num " +
                       "AND pc.cl_id = cl.cl_id " +
                        " AND (pe.pe_estado = 'Facturado y Validado' OR pe.pe_estado='Facturado') AND pc.cl_id = " + cl_id + " AND pe.pe_num_fa_cl='" + recFacturaAux.getNumFactura() + "' GROUP BY pe.pe_num ORDER BY pe.pe_num ASC";

                        //System.out.println(query);
                        ResultSet rs = CSDesktop.datos.select(query);
                        try
                        {
                            while (rs.next())
                            {
                                Boolean unidoEstado = true;
                                if (rs.getString("estado_unido") != null){
                                    unidoEstado = (rs.getString("estado_unido").equals("Facturado y Validado") || rs.getString("estado_unido").equals("Facturado")) ? true : false;
                                }
                                if (rs.getLong("pe_num_unido") == 0 &&
                                   (rs.getString("pe_estado").equals("") || rs.getString("pe_estado").equals("Facturado y Validado") || rs.getString("pe_estado").equals("Facturado")) &&
                                   unidoEstado)
                                {
                                    BeanFactura nueva = new BeanFactura();
                                    nueva.setNumPedido(rs.getLong("pe_num"));

                                    nueva.setFecha(rs.getString("pe_fecha"));
                                    nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                                    if (rs.getString("destino_unido") != null && !rs.getString("destino_unido").equals("")){
                                        nueva.setProvinciaDestino(rs.getString("destino_unido"));
                                        nueva.setServicioDestino(rs.getString("destino_unido"));
                                        nueva.setPoblacionDestino(rs.getString("pob_unido"));
                                    }else{
                                        nueva.setProvinciaDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setServicioDestino(rs.getString("pe_provincia_destino"));
                                        nueva.setPoblacionDestino(rs.getString("pe_poblacion_destino"));
                                    }
                                    nueva.setNumPedido(rs.getLong("pe_num"));
                                    nueva.setFecha(rs.getString("pe_fecha"));
                                    nueva.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
                                    nueva.setServicioOrigen(rs.getString("pe_provincia_origen"));
                                    nueva.setServicio(rs.getString("pe_servicio"));
                                    nueva.setServicioEspecial(rs.getString("pe_servicio_especial"));
                                    nueva.setDiasCampa(rs.getString("pe_dias_campa"));
                                    nueva.setFactor(rs.getString("fc_id"));
                                    nueva.setSoporte(rs.getString("pe_soporte"));
                                    nueva.setMatricula(rs.getString("pe_ve_matricula"));
                                    nueva.setMarca(rs.getString("pe_ve_marca"));
                                    nueva.setModelo(rs.getString("pe_ve_modelo"));
                                    nueva.setTarifaEsCliente(rs.getString("pe_ta_es_cliente"));
                                    nueva.setTarifaEsProveedor(rs.getString("pe_ta_es_proveedor"));
                                    nueva.setSuplemento(rs.getString("pe_suplemento"));
                                    nueva.setDescripcion(rs.getString("pe_descripcion"));
                                    nueva.setIdaVuelta(rs.getString("pe_ida_vuelta"));
                                    nueva.setNumCamion(rs.getString("pe_num_en_camion"));
                                    nueva.setObsInFactura(rs.getBoolean("pe_ob_cl_mail"));
                                    nueva.setKms(rs.getString("pe_kms"));
                                    nueva.setAtt(rs.getString("cl_email"));
                                    nueva.setVe_estado(rs.getString("pe_ve_estado"));
                                    nueva.setAux(recFacturaAux.getNumFactura());
                                    lista.add(nueva);
                                }
                                pedidos.add(rs.getLong("pe_num"));
                            }
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        CSLanzarFactura factura = new CSLanzarFactura();
                        factura.lanzar(lista, beanCliente, recFacturaAux.getFechaFactura(), 1, cl_id, recFacturaAux.getFechaDesde(), recFacturaAux.getFechaHasta(), pedidos, 0);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(CSResultBuscarRecFactura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
        }
    }//GEN-LAST:event_jButtonRecuperarFacturaActionPerformed
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbono;
    private javax.swing.JButton jButtonAbonoPrev;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonRecuperarFactura;
    private com.toedter.calendar.JDateChooser jDateFechaFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextObservaciones;
    private javax.swing.JLabel lFechaIni1;
    private javax.swing.JLabel lObservaciones;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {

            Component cell = super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores

            jTable1.setRowHeight(20);

            if (column == 0 ||column == 1 || column == 2 || column == 4 || column == 5 )
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 6 )
            {
                this. setHorizontalAlignment(SwingConstants.RIGHT);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }
            //se toman algunos valores especificos para mi programa
            //double cantidad = Double. parseDouble(table. getValueAt(row, 11). toString());
            //double stockMin = Double. parseDouble(table. getValueAt(row, 12). toString());
            //double stockMax = Double. parseDouble(table. getValueAt(row, 13). toString());
            //si cumplen x condicion se pintan
            if (row % 2 ==1)
            {
                Color fondo = new  Color(206, 227, 242);
                cell. setBackground(fondo);
                cell. setForeground(Color.DARK_GRAY);
            }
            else
            {
                cell. setBackground(Color.white);
                cell. setForeground(Color. BLACK);
            }
            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            //si no cumplen esa condicion pongo las celdas en color blanco
          
            return cell;
        }
    }
}



        
