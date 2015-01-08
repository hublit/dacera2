/*
 * CSValidarPedidoArchivo.java
 *
 * Created on 06-12-2014.
 */

package neg;

import data.BeanPedidoAux;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import utils.TablaValidarArchivo;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSValidarPedidoArchivo extends javax.swing.JPanel
{
    ArrayList pedidos=new ArrayList();
    private String query = "";
    int numeroFila = 0;

    /** Creates new form ResultBuscarPedido */
    public CSValidarPedidoArchivo() throws UnknownHostException, FileNotFoundException, IOException
    {
        query = "SELECT pa.pa_fecha, pa.pa_direccion_origen, pa.pa_poblacion_origen, pa.pa_provincia_origen, pa.pa_cp_origen, " +
                "pa.pa_nombre_origen, pa.pa_telefono_origen, pa.pa_direccion_destino, pa.pa_poblacion_destino, pa.pa_provincia_destino, " +
                "pa.pa_cp_destino, pa.pa_nombre_destino, pa.pa_telefono_destino, fc.fc_nombre, pa.pa_ve_estado, pa.pa_ve_matricula, pa.pa_ve_marca, " +
                "pa.pa_ve_modelo, pa.pa_soporte, pa.pa_servicio, pa.pa_kms, pa.pa_num_en_camion, pa.pa_dias_campa, pa.pa_descripcion, pa.pa_fecha_origen, " +
                "pa.pa_fecha_destino, pa.pa_ta_es_cliente, pa.pa_ta_es_proveedor, cl.cl_nombre, pr.pr_nombre_fiscal, pa.pa_observaciones_carset, " +
                "pa.pa_ob_general, pa.pa_ob_cl_mail, pa.pa_ob_pr_mail, pa.pa_num_unido, pa.pa_fin_unido, pa.pa_estado, pa.cl_id, pa.pr_id, pa.fc_id " + 
                "FROM pa_pedidos_aux pa INNER JOIN cl_clientes cl ON pa.cl_id = cl.cl_id INNER JOIN pr_proveedores pr ON pa.pr_id = pr.pr_id " + 
                "INNER JOIN fc_factores_correccion fc ON pa.fc_id = fc.fc_id";

        TablaValidarArchivo modelo = new TablaValidarArchivo();
        modelo.fireTableDataChanged();
        ResultSet rs = CSDesktop.datos.select(query);
System.out.println(query);
        boolean acceso = (CSDesktop.user.equals("9") || CSDesktop.user.equals("10") || CSDesktop.user.equals("11")) ? false : true;
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
        modelo.setColumnIdentifiers(new String[] {"FECHA", "DIR ORIGEN", "POB ORIGEN", "PROV ORIGEN", "CP ORIGEN", "NOMBRE ORIGEN", "TELEF ORIGEN", 
                                                  "DIR DESTINO", "POB DESTINO", "PROV DESTINO", "CP DESTINO", "NOMBRE DESTINO", "TELEF DESTINO", "FACTOR", 
                                                  "ESTADO VEHICULO", "MATRICULA", "MARCA", "MODELO", "SOPORTE", "SERVICIO", "KMS", "NUM CAMION", "D.C.",
                                                  "OBSERVACIONES", "F.RECOGIDA", "F.ENTREGA", "TAR.CL", "TAR.PR", "CLIENTE", "PROVEEDOR", "OBS CARSET", 
                                                  "OBS GENERALES", "OB CL MAIL", "OB PR MAIL", "NUM UNIDO", "FIN UNIDO"});
        int totalKms = 0;
        double totalCliente = 0;
        double totalProveedor = 0;
        int totalDiasCampa = 0;

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat dfTotal = new DecimalFormat("#,###.### ¤");

        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                double ta_es_cl=0;
                double ta_es_pr=0;
                int kms = 0;
                int diasCampa = 0;

                for (int k = 0; k < 36; k++)
                {
                    if((k==0) || (k==24)|| (k==25))
                    {
                        if(rs.getObject(k+1)!=null && rs.getObject(k+1)!="")
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
                    }
                    else if(k==20)
                    {
                        kms = rs.getInt(k + 1);
                        datosFila[j] = rs.getInt(k + 1);
                        totalKms = totalKms + kms;
                    }
                    else if(k==22)
                    {
                        diasCampa = rs.getInt(k + 1);
                        datosFila[j] = rs.getInt(k + 1);
                        totalDiasCampa = totalDiasCampa + diasCampa;
                    }
                    else if(k==13)//FACTOR
                    {
                        datosFila[j] = rs.getString(k + 1);
                    }
                    else if(k==26)
                    {
                         ta_es_cl=rs.getDouble(k+1);
                         datosFila[j] = df.format(rs.getDouble(k + 1));
 //                        System.out.println("Dato" + k + " " + datosFila[j]);
                         totalCliente = totalCliente + ta_es_cl;
                         totalCliente = Utilidades.redondear(totalCliente, 2);
                    }
                    else if(k==27)
                    {
                        ta_es_pr=rs.getDouble(k+1);
                        datosFila[j] = df.format(rs.getDouble(k + 1));
//                        System.out.println("Dato" + k + " " + datosFila[j]);
                        totalProveedor = totalProveedor + ta_es_pr;
                        totalProveedor = Utilidades.redondear(totalProveedor, 2);
                    }
                    else if(k==32 || (k==33) || (k==34) || (k==35))
                    {
                        String valor = (rs.getInt(k + 1) == 1) ? "SI" : "";
                        datosFila[j] = valor;
                    }
                    else
                    {
                        datosFila[j] = rs.getObject(k + 1);
                        //System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                    }

                    j++;
                }
                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
            for (int k = 0; k < 36; k++)
            {
                if(k==0)
                {
                    datosFilaTotal[i] = numeroFila;
                }
                if(k==19)
                {
                    datosFilaTotal[i] = "TOTALES";
                }
                if(k==20)
                {
                    datosFilaTotal[i] = totalKms;
                }
                if(k==22)
                {
                    datosFilaTotal[i] = totalDiasCampa;
                }
                if(k==26)
                {
                    if(acceso){
                        datosFilaTotal[i] = dfTotal.format(Utilidades.redondear(totalCliente, 2));
                    }
                }
                if(k==27)
                {
                    if(acceso){
                        datosFilaTotal[i] = dfTotal.format(totalProveedor);
                    }
                }
                i++;
           }
           modelo.addRow(datosFilaTotal);

        } catch (SQLException ex) {
            Logger.getLogger(CSResultBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado datos.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
         else
        {
            CSDesktop.ValidarPedidoArchivo = new JInternalFrame("Validar Pedidos desde Archivo", true, false, true, true );
            CSDesktop.ValidarPedidoArchivo.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ValidarPedidoArchivo.pack();
            CSDesktop.elEscritorio.add( CSDesktop.ValidarPedidoArchivo );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ValidarPedidoArchivo.getSize();
            CSDesktop.ValidarPedidoArchivo.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ValidarPedidoArchivo.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());

        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(80);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(120);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(300);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(120);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(30);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(120);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(30);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(80);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(70);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(80);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(60);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(200);
        TableColumn columna12 = jTable1.getColumnModel().getColumn(12);
        columna12.setPreferredWidth(60);
        TableColumn columna13 = jTable1.getColumnModel().getColumn(13);
        columna13.setPreferredWidth(60);
        TableColumn columna14 = jTable1.getColumnModel().getColumn(14);
        columna14.setPreferredWidth(60);
        TableColumn columna15 = jTable1.getColumnModel().getColumn(15);
        columna15.setPreferredWidth(120);
        TableColumn columna16 = jTable1.getColumnModel().getColumn(16);
        columna16.setPreferredWidth(80);
        TableColumn columna17 = jTable1.getColumnModel().getColumn(17);
        columna17.setPreferredWidth(80);
        TableColumn columna18 = jTable1.getColumnModel().getColumn(18);
        columna18.setPreferredWidth(80);
        TableColumn columna19 = jTable1.getColumnModel().getColumn(19);
        columna19.setPreferredWidth(100);
        TableColumn columna20 = jTable1.getColumnModel().getColumn(20);
        columna20.setPreferredWidth(100);
        TableColumn columna21 = jTable1.getColumnModel().getColumn(21);
        columna21.setPreferredWidth(100);
        TableColumn columna22 = jTable1.getColumnModel().getColumn(22);
        columna22.setPreferredWidth(100);
        TableColumn columna23 = jTable1.getColumnModel().getColumn(23);
        columna23.setPreferredWidth(50);
        TableColumn columna24 = jTable1.getColumnModel().getColumn(24);
        columna24.setPreferredWidth(50);
        TableColumn columna25 = jTable1.getColumnModel().getColumn(25);
        columna25.setPreferredWidth(50);
        TableColumn columna26 = jTable1.getColumnModel().getColumn(26);
        columna26.setPreferredWidth(50);
        TableColumn columna27 = jTable1.getColumnModel().getColumn(27);
        columna27.setPreferredWidth(100);
        TableColumn columna28 = jTable1.getColumnModel().getColumn(28);
        columna28.setPreferredWidth(100);
        TableColumn columna29 = jTable1.getColumnModel().getColumn(29);
        columna29.setPreferredWidth(100);
        TableColumn columna30 = jTable1.getColumnModel().getColumn(30);
        columna30.setPreferredWidth(40);
        TableColumn columna31 = jTable1.getColumnModel().getColumn(31);
        columna31.setPreferredWidth(40);
        TableColumn columna32 = jTable1.getColumnModel().getColumn(32);
        columna32.setPreferredWidth(50);
        TableColumn columna33 = jTable1.getColumnModel().getColumn(33);
        columna33.setPreferredWidth(50);
        TableColumn columna34 = jTable1.getColumnModel().getColumn(34);
        columna34.setPreferredWidth(100);
        TableColumn columna35 = jTable1.getColumnModel().getColumn(35);
        columna35.setPreferredWidth(100);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        

        jTable1.setAutoCreateRowSorter(true);

        modelo.fireTableDataChanged();//REVISAR
    }

    public Dimension getPreferredSize()
    {
        return new Dimension( 1100,650 );
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                    boolean hasFocus, int row, int col)
    {
        int componente = table.getSelectedRow();
        Component comp = getTableCellRendererComponent(table,  value, isSelected, hasFocus, row, col);

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
        jButtonModificar = new javax.swing.JButton();
        jButtonInsertarPedidos = new javax.swing.JButton();

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

        jButtonModificar.setForeground(new java.awt.Color(255, 0, 0));
        jButtonModificar.setText("Modificar Pedidos");
        jButtonModificar.setName("jButtonModificar"); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonInsertarPedidos.setForeground(new java.awt.Color(255, 0, 0));
        jButtonInsertarPedidos.setText("Insertar Pedidos");
        jButtonInsertarPedidos.setName("jButtonInsertarPedidos"); // NOI18N
        jButtonInsertarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarPedidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonModificar)
                        .addGap(358, 358, 358)
                        .addComponent(jButtonInsertarPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                        .addComponent(jButtonCerrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonCerrar)
                    .addComponent(jButtonInsertarPedidos))
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ValidarPedidoArchivo.dispose();
        CSDesktop.menuValidarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    /**
     * Modificar los datos de la pantalla
     * @param evt
     */
    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        boolean modificarPedidos = false;
        String fecha = "";
        ArrayList<BeanPedidoAux> listaArchivo = new ArrayList<BeanPedidoAux>();
        //Recorremos todos los pedidos de la pantalla
        for(int i = 0; i < numeroFila; i++)
        {
            BeanPedidoAux campos = new BeanPedidoAux();

            String fechaPedido = jTable1.getValueAt(i, 0).toString();
            if (fechaPedido != null && !fechaPedido.equals("")){
                 String [] temp = null;
                 temp = fechaPedido.split("\\-");
                 String anyo = temp[2];
                 String mes = temp[1];
                 String dia = temp[0];
                 fecha = anyo+"-"+mes+"-"+dia;
            }
            campos.setFecha(fecha);
            campos.setDireccionOrigen(jTable1.getValueAt(i, 1).toString());
            campos.setPoblacionOrigen(jTable1.getValueAt(i, 2).toString());
            campos.setProvinciaOrigen(jTable1.getValueAt(i, 3).toString());
            campos.setCpOrigen(jTable1.getValueAt(i, 4).toString());
            campos.setNombreOrigen(jTable1.getValueAt(i, 5).toString());
            campos.setTelefonoOrigen(jTable1.getValueAt(i, 6).toString());
            campos.setDireccionDestino(jTable1.getValueAt(i, 7).toString());
            campos.setPoblacionDestino(jTable1.getValueAt(i, 8).toString());
            campos.setProvinciaDestino(jTable1.getValueAt(i, 9).toString());
            campos.setCpDestino(jTable1.getValueAt(i, 10).toString());
            campos.setNombreDestino(jTable1.getValueAt(i, 11).toString());
            campos.setTelefonoDestino(jTable1.getValueAt(i, 12).toString());
            campos.setFactor(Integer.parseInt(jTable1.getValueAt(i, 13).toString()));
            campos.setEstado_vehiculo(jTable1.getValueAt(i, 14).toString());
            campos.setMatricula(jTable1.getValueAt(i, 15).toString());
            campos.setMarca(jTable1.getValueAt(i, 16).toString());
            campos.setModelo(jTable1.getValueAt(i, 17).toString());
            campos.setSoporte(jTable1.getValueAt(i, 18).toString());
            campos.setServicio(jTable1.getValueAt(i, 19).toString());
            campos.setKms(jTable1.getValueAt(i, 20).toString());
            campos.setNumEnCamion(Integer.parseInt(jTable1.getValueAt(i, 21).toString()));
            campos.setDiasCampa(Integer.parseInt(jTable1.getValueAt(i, 22).toString()));
            campos.setDescripcion(jTable1.getValueAt(i, 23).toString());
            String fechaRecogida = jTable1.getValueAt(i, 24).toString();
            if (fechaRecogida != null && !fechaRecogida.equals("")){
                 String [] temp = null;
                 temp = fechaRecogida.split("\\-");
                 String anyo = temp[2];
                 String mes = temp[1];
                 String dia = temp[0];
                 fecha = anyo+"-"+mes+"-"+dia;
            }
            campos.setFechaOrigen(fecha);
            String fechaDestino = jTable1.getValueAt(i, 25).toString();
            if (fechaDestino != null && !fechaDestino.equals("")){
                 String [] temp = null;
                 temp = fechaDestino.split("\\-");
                 String anyo = temp[2];
                 String mes = temp[1];
                 String dia = temp[0];
                 fecha = anyo+"-"+mes+"-"+dia;
            }
            campos.setFechaDestino(fecha);
            campos.setTarifaCl(Double.valueOf(Utilidades.validarDecimal(jTable1.getValueAt(i, 26).toString())));
            campos.setTarifaPr(Double.valueOf(Utilidades.validarDecimal(jTable1.getValueAt(i, 27).toString())));
            campos.setCliente(Integer.parseInt(jTable1.getValueAt(i, 28).toString()));
            campos.setProveedor(Integer.parseInt(jTable1.getValueAt(i, 29).toString()));
            campos.setObsCarset(jTable1.getValueAt(i, 30).toString());
            campos.setObsGeneral(jTable1.getValueAt(i, 31).toString());
            boolean obClMail = (jTable1.getValueAt(i, 32).toString().equals("TRUE") ? true : false);
            campos.setObClMail(obClMail);
            boolean obPrMail = (jTable1.getValueAt(i, 33).toString().equals("TRUE") ? true : false);
            campos.setObPrMail(obPrMail);
            boolean numUnido = (jTable1.getValueAt(i, 34).toString().equals("TRUE") ? true : false);
            campos.setPeNumUnido(numUnido);
            boolean finUnido = (jTable1.getValueAt(i, 35).toString().equals("TRUE") ? true : false);
            campos.setPeFinUnido(finUnido);
            
            listaArchivo.add(campos);
        }

        try {
            //guardamos las modificaciones en la bd
           modificarPedidos =  modificarPedidosArchivo(listaArchivo);
        } catch (SQLException ex){
            Logger.getLogger(CSValidarPedidoArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(modificarPedidos)
        {
            jButtonModificar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al modificar los datos</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonModificar.setEnabled(true);
        }
        else
        {
            jButtonModificar.setEnabled(false);
            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han modificado correctamente.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, mensaje);
            jButtonModificar.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    /**
     * Insertamos los pedidos de la tabla Aux en pedidos 
     * @param evt
     */
    private void jButtonInsertarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarPedidosActionPerformed
        ArrayList<BeanPedidoAux> listaArchivo = new ArrayList<BeanPedidoAux>();
        String queryAux = "SELECT * FROM pa_pedidos_aux";

        ResultSet rsPAux = CSDesktop.datos.select(queryAux);
        try
        {
            while(rsPAux.next())
            {
                BeanPedidoAux pedidoAux = new BeanPedidoAux();

                pedidoAux.setFecha(rsPAux.getString("pa_fecha"));
                pedidoAux.setDireccionOrigen(rsPAux.getString("pa_direccion_origen"));
                pedidoAux.setPoblacionOrigen(rsPAux.getString("pa_poblacion_origen"));
                pedidoAux.setProvinciaOrigen(rsPAux.getString("pa_provincia_origen"));
                pedidoAux.setCpOrigen(rsPAux.getString("pa_cp_origen"));
                pedidoAux.setFechaOrigen(rsPAux.getString("pa_fecha_origen"));
                pedidoAux.setNombreOrigen(rsPAux.getString("pa_nombre_origen"));
                pedidoAux.setTelefonoOrigen(rsPAux.getString("pa_telefono_origen"));
                pedidoAux.setDireccionDestino(rsPAux.getString("pa_direccion_destino"));
                pedidoAux.setPoblacionDestino(rsPAux.getString("pa_poblacion_destino"));
                pedidoAux.setProvinciaDestino(rsPAux.getString("pa_provincia_destino"));
                pedidoAux.setCpDestino(rsPAux.getString("pa_cp_destino"));
                pedidoAux.setFechaDestino(rsPAux.getString("pa_fecha_destino"));
                pedidoAux.setNombreDestino(rsPAux.getString("pa_nombre_destino"));
                pedidoAux.setTelefonoDestino(rsPAux.getString("pa_telefono_destino"));
                pedidoAux.setFactor(rsPAux.getInt("fc_id"));
                pedidoAux.setEstado_vehiculo(rsPAux.getString("pa_ve_estado"));
                pedidoAux.setMatricula(rsPAux.getString("pa_ve_matricula"));
                pedidoAux.setMarca(rsPAux.getString("pa_ve_marca"));
                pedidoAux.setModelo(rsPAux.getString("pa_ve_modelo"));
                pedidoAux.setSoporte(rsPAux.getString("pa_soporte"));
                pedidoAux.setServicio(rsPAux.getString("pa_servicio"));
                pedidoAux.setKms(rsPAux.getString("pa_kms"));
                pedidoAux.setNumEnCamion(rsPAux.getInt("pa_num_en_camion"));
                pedidoAux.setDiasCampa(rsPAux.getInt("pa_dias_campa"));
                pedidoAux.setDescripcion(rsPAux.getString("pa_descripcion"));
                pedidoAux.setTarifaCl(rsPAux.getDouble("pa_ta_es_cliente"));
                pedidoAux.setTarifaPr(rsPAux.getDouble("pa_ta_es_proveedor"));
                pedidoAux.setCliente(rsPAux.getInt("cl_id"));
                pedidoAux.setProveedor(rsPAux.getInt("pr_id"));
                pedidoAux.setObsCarset(rsPAux.getString("pa_observaciones_carset"));
                pedidoAux.setObsGeneral(rsPAux.getString("pa_ob_general"));
                pedidoAux.setObClMail(rsPAux.getBoolean("pa_ob_cl_mail"));
                pedidoAux.setObPrMail(rsPAux.getBoolean("pa_ob_pr_mail"));
                pedidoAux.setPeNumUnido(rsPAux.getBoolean("pa_num_unido"));
                pedidoAux.setPeFinUnido(rsPAux.getBoolean("pa_fin_unido"));
                pedidoAux.setEstado(rsPAux.getString("pa_estado"));

                listaArchivo.add(pedidoAux);
            }
            rsPAux.close();
            //Insertar en la tabla de pedidos
            boolean insPedidos = insertarPedidosAux(listaArchivo);
            //System.out.println("Borrado de pedidos: "+ insPedidos);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Pedidos, con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }

    }//GEN-LAST:event_jButtonInsertarPedidosActionPerformed
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonInsertarPedidos;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores

            jTable1.setRowHeight(20);

            if (column == 0 || column == 8 || column == 14 || column == 16 || column == 17 ||
                column == 18 || column == 23 || column == 24 || column == 25 || column == 26)
            {
                this. setHorizontalAlignment(SwingConstants.RIGHT);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }

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

            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table.getValueAt(row, 19).toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
            }

            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            return cell;
        }
    }
    

    /**
     * Modificación de pedidos del listado
     */
    public boolean modificarPedidosArchivo(ArrayList<BeanPedidoAux> listaArchivo)throws SQLException
    {
       // PRIMERO SE BORRA LA TABLA AUXILIAR PARA GENERAR LAS PEDIDOS.
        String queryDelPa = "DELETE FROM pa_pedidos_aux";
        boolean resDel = CSDesktop.datos.manipuladorDatos(queryDelPa);

        Iterator iterator = listaArchivo.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
        boolean rsInsert = false;
        //Mientras que el iterador tenga un proximo elemento
        while( iterator.hasNext() ) {
            BeanPedidoAux bpa = (BeanPedidoAux)iterator.next();
            int obClmail = (bpa.isObClMail() ? 1 : 0);
            int obPrmail = (bpa.isObPrMail() ? 1 : 0);
            int numUnido = (bpa.isPeNumUnido() ? 1 : 0);
            int finUnido = (bpa.isPeFinUnido() ? 1 : 0);
            
            String query = "INSERT INTO pa_pedidos_aux (pa_fecha, pa_direccion_origen, pa_poblacion_origen, pa_provincia_origen, pa_cp_origen, " +
                           "pa_fecha_origen, pa_nombre_origen, pa_telefono_origen, pa_direccion_destino, pa_poblacion_destino, pa_provincia_destino, " +
                           "pa_cp_destino, pa_fecha_destino, pa_nombre_destino, pa_telefono_destino, fc_id, pa_ve_estado, pa_ve_matricula, " +
                           "pa_ve_marca, pa_ve_modelo, pa_soporte, pa_servicio, pa_kms, pa_num_en_camion, pa_dias_campa, pa_descripcion, " +
                           "pa_ta_es_cliente, pa_ta_es_proveedor, cl_id, pr_id, pa_observaciones_carset, pa_ob_general, pa_ob_cl_mail, pa_ob_pr_mail, " +
                           "pa_num_unido, pa_fin_unido, pa_estado) VALUES ('"+bpa.getFecha()+"','"+bpa.getDireccionOrigen()+"','"+bpa.getPoblacionOrigen()+"', " +
                           "'"+bpa.getProvinciaOrigen()+"', '"+bpa.getCpOrigen()+"', '"+bpa.getFechaOrigen()+"', '"+bpa.getNombreOrigen()+"', '"+bpa.getTelefonoOrigen()+"'," +
                           "'"+bpa.getDireccionDestino()+"', '"+bpa.getPoblacionDestino()+"', '"+bpa.getProvinciaDestino()+"', '"+bpa.getCpDestino()+"', " +
                           "'"+bpa.getFechaDestino()+"', '"+bpa.getNombreDestino()+"', '"+bpa.getTelefonoDestino()+"', '"+bpa.getFactor()+"', '"+bpa.getEstado_vehiculo()+"', " +
                           "'"+bpa.getMatricula()+"', '"+bpa.getMarca()+"', '"+bpa.getModelo()+"', '"+bpa.getSoporte()+"', '"+bpa.getServicio()+"', '"+bpa.getKms()+"', " +
                           "'"+bpa.getNumEnCamion()+"', '"+bpa.getDiasCampa()+"', '"+bpa.getDescripcion()+"', '"+bpa.getTarifaCl()+"', '"+bpa.getTarifaPr()+"'," +
                           "'"+bpa.getCliente()+"', '"+bpa.getProveedor()+"', '"+bpa.getObsCarset()+"', '"+bpa.getObsGeneral()+"', '"+obClmail+"', " +
                           "'"+obPrmail+"','"+numUnido+"', '"+finUnido+"', 'En Proceso')";
            //System.out.println(query);
            rsInsert = CSDesktop.datos.manipuladorDatos(query);

        }

       return rsInsert;
    }

    /**
     * Modifica los campos de la tesorería del proveedor
     * @param BeanPedidoAux
     * @throws SQLException
     */
    public boolean insertarPedidosAux(ArrayList<BeanPedidoAux> listaArchivo) throws SQLException
    {
        boolean resDel = false;        
        int confirmado = JOptionPane.showConfirmDialog(this,"¿Estás seguro de INSERTAR los pedidos?");
        if (JOptionPane.OK_OPTION == confirmado)
        {
            int peNum = 0;
            int pe_num = 0;
            Iterator iterator = listaArchivo.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
            boolean rsPedido = false;
            //Mientras que el iterador tenga un proximo elemento
            while( iterator.hasNext() ) {
                BeanPedidoAux bpa = (BeanPedidoAux)iterator.next();
                int obClmail = (bpa.isObClMail() ? 1 : 0);
                int obPrmail = (bpa.isObPrMail() ? 1 : 0);

    //            int numUnido = (bpa.isPeNumUnido()) ? 1 : 0;
                peNum = (bpa.isPeNumUnido()) ? peNum : 0;
                int numUnido = (peNum == 0 && bpa.isPeNumUnido()) ? pe_num : peNum;
                peNum = (numUnido != 0) ? numUnido : peNum;
                int finUnido = (bpa.isPeFinUnido() ? 1 : 0);
                String queryInPe =  "INSERT INTO pe_pedidos (pe_fecha, pe_direccion_origen, pe_poblacion_origen, pe_provincia_origen, " +
                                "pe_servicio_origen, pe_cp_origen, pe_nombre_origen, pe_fecha_origen, pe_telefono_origen, pe_direccion_destino, " +
                                "pe_poblacion_destino, pe_provincia_destino, pe_servicio_destino, pe_cp_destino, pe_nombre_destino, pe_fecha_destino, " +
                                "pe_telefono_destino, fc_id, pe_ve_estado, pe_ve_matricula, pe_ve_marca, pe_ve_modelo, pe_soporte, pe_servicio, " +
                                "pe_kms, pe_num_en_camion, pe_dias_campa, pe_descripcion, pe_ta_es_cliente, pe_ta_es_proveedor, pe_observaciones_carset, " +
                                "pe_ob_general, pe_ob_cl_mail, pe_ob_pr_mail, pe_num_unido, pe_fin_unido, pe_estado) VALUES (" +
                                "'"+bpa.getFecha()+"','"+bpa.getDireccionOrigen()+"','"+bpa.getPoblacionOrigen()+"', '"+bpa.getProvinciaOrigen()+"', " +
                                "'"+bpa.getProvinciaOrigen()+"','"+bpa.getCpOrigen()+"','"+bpa.getNombreOrigen()+"','"+bpa.getFechaOrigen()+"', " +
                                "'"+bpa.getTelefonoOrigen()+"', '"+bpa.getDireccionDestino()+"', '"+bpa.getPoblacionDestino()+"', '"+bpa.getProvinciaDestino()+"', " +
                                "'"+bpa.getProvinciaDestino()+"', '"+bpa.getCpDestino()+"', '"+bpa.getNombreDestino()+"', '"+bpa.getFechaDestino()+"', " +
                                "'"+bpa.getTelefonoDestino()+"', '"+bpa.getFactor()+"', '"+bpa.getEstado_vehiculo()+"','"+bpa.getMatricula()+"', '"+bpa.getMarca()+"', " +
                                "'"+bpa.getModelo()+"', '"+bpa.getSoporte()+"', '"+bpa.getServicio()+"', '"+bpa.getKms()+"','"+bpa.getNumEnCamion()+"', '"+bpa.getDiasCampa()+"', " +
                                "'"+bpa.getDescripcion()+"', '"+bpa.getTarifaCl()+"', '"+bpa.getTarifaPr()+"', '"+bpa.getObsCarset()+"', '"+bpa.getObsGeneral()+"', " +
                                "'"+obClmail+"', '"+obPrmail+"','"+numUnido+"', '"+finUnido+"', '"+bpa.getEstado()+"')";
                System.out.println(queryInPe);
                rsPedido = CSDesktop.datos.manipuladorDatos(queryInPe);
                peNum = (bpa.isPeFinUnido() ? 0 : peNum);
                if(!rsPedido)
                {
                    query = "select distinct last_insert_id() from pe_pedidos";
                    pe_num = 0;
                    ResultSet rs2 = CSDesktop.datos.select(query);
                    try
                    {
                       if (rs2.first())
                        {
                            pe_num = rs2.getInt("last_insert_id()");
                            //System.out.println(rs2.getInt("last_insert_id()"));
                            String queryCon = "INSERT INTO pc_pedidos_clientes (pe_num,cl_id) VALUES ("+pe_num+", '"+bpa.getCliente()+"')";
                            //System.out.println(queryCon);
                            boolean rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                            if(rsCon)
                            {
                                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                JOptionPane.showMessageDialog(null,errorFields);
                            }
                            queryCon = "INSERT INTO pp_pedidos_proveedores (pe_num,pr_id) VALUES ("+pe_num+",'"+bpa.getProveedor()+"')";
                            //System.out.println(queryCon);
                            rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                            if(rsCon)
                            {
                                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                JOptionPane.showMessageDialog(null,errorFields);
                            }
                        }
                    } catch (SQLException ex) {
                        JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar los pedidos en la base de datos</FONT></HTML>");
                        JOptionPane.showMessageDialog(null,errorFields);
                        ex.printStackTrace();
                    }
                }// TODO add your handling code here:
              }

            if (!rsPedido){
                // POR ULTIMO SE BORRA LA TABLA AUXILIAR PARA NO VOLVER A GENERAR LOS MISMOS PEDIDOS.
                String queryDelPa = "DELETE FROM pa_pedidos_aux";
                resDel = CSDesktop.datos.manipuladorDatos(queryDelPa);

                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los pedidos se han insertado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, mensaje);
                jButtonModificar.setEnabled(true);
                CSDesktop.ValidarPedidoArchivo.dispose();
                CSDesktop.menuValidarPedido.setEnabled(true);
            }
        }
        return resDel;
    }
}