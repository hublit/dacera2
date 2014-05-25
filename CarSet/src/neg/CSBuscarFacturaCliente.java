/*
 * ABBuscarFactura.java
 *
 * Created on 07-oct-2009, 17:50:47
 */

package neg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.LimitadorDeDocumento;
import data.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JInternalFrame;

/**
 *
 * @author depr73
 */
public class CSBuscarFacturaCliente extends javax.swing.JPanel
{
    int clienteID=0;
    int proveedorID=0;
    private String fecha2;

    /** Creates new form ABBuscarPedido */
    public CSBuscarFacturaCliente()
    {
        CSDesktop.menuBuscarPedido.setEnabled(false);
        initComponents();
        limitacionesCampos();

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
            if (this.getComponents()[k] != jComboBoxServicio &&
                this.getComponents()[k] != jComboBoxServicioFMad &&
                this.getComponents()[k] != jComboBoxSoporte &&
                this.getComponents()[k] != jComboBoxEstado)
            {
                this.getComponents()[k].addKeyListener(l);
            }
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

        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jComboVehiculo = new javax.swing.JComboBox();
        lServicio = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lSoporte = new javax.swing.JLabel();
        jTextMatricula = new javax.swing.JTextField();
        lFuMadrid = new javax.swing.JLabel();
        lMatricula = new javax.swing.JLabel();
        lVehiculo = new javax.swing.JLabel();
        jCheckBoxFMadrid = new javax.swing.JCheckBox();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        lCliente = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox();
        lEstado3 = new javax.swing.JLabel();
        lFechaIni = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jSeparator5 = new javax.swing.JSeparator();
        jTextCliente = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        jComboBoxSoporte = new javax.swing.JComboBox();
        lFechaFin = new javax.swing.JLabel();
        jDateFechaFin = new com.toedter.calendar.JDateChooser();
        lServicioFMad = new javax.swing.JLabel();
        jComboBoxServicioFMad = new javax.swing.JComboBox();
        lServicioFMad1 = new javax.swing.JLabel();
        jComboBoxServicioFMadDestino = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();

        jButtonBuscar.setText("Buscar");
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

        jComboVehiculo.setBackground(new java.awt.Color(228, 229, 255));
        jComboVehiculo.setForeground(new java.awt.Color(0, 0, 100));
        jComboVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Turismo", "Industrial", "4x4", "Monovolumen" }));
        jComboVehiculo.setName("jComboVehiculo"); // NOI18N

        lServicio.setForeground(new java.awt.Color(0, 0, 100));
        lServicio.setText("Servicio");
        lServicio.setName("lServicio"); // NOI18N

        jSeparator4.setName("jSeparator4"); // NOI18N

        lSoporte.setForeground(new java.awt.Color(0, 0, 100));
        lSoporte.setText("Soporte");
        lSoporte.setName("lSoporte"); // NOI18N

        jTextMatricula.setName("jTextMatricula"); // NOI18N
        jTextMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextMatriculaFocusLost(evt);
            }
        });

        lFuMadrid.setForeground(new java.awt.Color(0, 0, 100));
        lFuMadrid.setText("Fuera de Madrid");
        lFuMadrid.setName("lFuMadrid"); // NOI18N

        lMatricula.setForeground(new java.awt.Color(0, 0, 100));
        lMatricula.setText("Matrícula");
        lMatricula.setName("lMatricula"); // NOI18N

        lVehiculo.setForeground(new java.awt.Color(0, 0, 100));
        lVehiculo.setText("Tipo de vehículo");
        lVehiculo.setName("lVehiculo"); // NOI18N

        jCheckBoxFMadrid.setAlignmentX(0.5F);
        jCheckBoxFMadrid.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jCheckBoxFMadrid.setMaximumSize(new java.awt.Dimension(45, 45));
        jCheckBoxFMadrid.setMinimumSize(new java.awt.Dimension(45, 45));
        jCheckBoxFMadrid.setName("jCheckBoxFMadrid"); // NOI18N
        jCheckBoxFMadrid.setPreferredSize(new java.awt.Dimension(60, 60));
        jCheckBoxFMadrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFMadridActionPerformed(evt);
            }
        });

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

        jComboBoxServicio.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicio.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urbano", "Interurbano", "Provincial", "Urbano ITV", "Especial" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N

        lEstado3.setForeground(new java.awt.Color(0, 0, 100));
        lEstado3.setText("Estado");
        lEstado3.setName("lEstado3"); // NOI18N

        lFechaIni.setForeground(new java.awt.Color(0, 0, 100));
        lFechaIni.setText("Fecha ini");
        lFechaIni.setName("lFechaIni"); // NOI18N

        jComboBoxEstado.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxEstado.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo", "Pendiente" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N

        jSeparator5.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator5.setName("jSeparator5"); // NOI18N

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        jComboBoxSoporte.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxSoporte.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxSoporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grúa", "Camión completo", "Conductor", "Tren", "Custodia" }));
        jComboBoxSoporte.setSelectedIndex(2);
        jComboBoxSoporte.setName("jComboBoxSoporte"); // NOI18N

        lFechaFin.setForeground(new java.awt.Color(0, 0, 100));
        lFechaFin.setText("Fecha fin");
        lFechaFin.setName("lFechaFin"); // NOI18N

        jDateFechaFin.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFechaFin.setName("jDateFechaFin"); // NOI18N

        lServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad.setText("F.M. Origen");
        lServicioFMad.setName("lServicioFMad"); // NOI18N

        jComboBoxServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERíA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CORDOBA", "CORUÑA, A", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPUZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MALAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PALMAS, LAS", "PONTEVEDRA", "RIOJA, LA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxServicioFMad.setEnabled(false);
        jComboBoxServicioFMad.setName("jComboBoxServicioFMad"); // NOI18N

        lServicioFMad1.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad1.setText("F.M. Destino");
        lServicioFMad1.setName("lServicioFMad1"); // NOI18N

        jComboBoxServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMadDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERíA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CORDOBA", "CORUÑA, A", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPUZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MALAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PALMAS, LAS", "PONTEVEDRA", "RIOJA, LA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxServicioFMadDestino.setEnabled(false);
        jComboBoxServicioFMadDestino.setName("jComboBoxServicioFMadDestino"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(new java.awt.Color(170, 16, 4));
        jLabel1.setText("GENERAR  FACTURA CLIENTE");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator8.setBackground(new java.awt.Color(0, 102, 51));
        jSeparator8.setForeground(new java.awt.Color(0, 102, 51));
        jSeparator8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 53), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        jSeparator8.setOpaque(true);
        jSeparator8.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFuMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lServicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lServicioFMad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lServicioFMad1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxServicioFMadDestino, 0, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lSoporte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lVehiculo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lMatricula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lEstado3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFechaIni)
                                .addGap(3, 3, 3)
                                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(lFechaFin)
                                .addGap(5, 5, 5)
                                .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(lCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(jLabel1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(735, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lCliente)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButtonCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lFechaIni))
                    .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jDateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lSoporte)
                            .addComponent(lServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMad1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lFuMadrid)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVehiculo)
                    .addComponent(jComboVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMatricula)
                    .addComponent(jTextMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEstado3)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButtonBuscar)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelar)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        try {
            System.out.println("\njButtonGuardar_actionPerformed(ActionEvent e) called.");
            String fechaI = "";
            String fechaF = "";
            Calendar fechaCalendar = jDateFecha.getCalendar();
            //String fecha = ConvertirFechaString(fechaCalendar);
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaI = formatoDeFecha.format(fecha);
            }
            fechaCalendar = jDateFechaFin.getCalendar();
            //String fecha = ConvertirFechaString(fechaCalendar);
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaF = formatoDeFecha.format(fecha);
            }
            String cliente = new String(jTextCliente.getText());
            boolean fueraMad = new Boolean(jCheckBoxFMadrid.isSelected());
            String servicio = new String(jComboBoxServicio.getSelectedItem().toString());
            String servicioFMad = new String(jComboBoxServicioFMad.getSelectedItem().toString());
            String servicioFMadDestino = new String(jComboBoxServicioFMadDestino.getSelectedItem().toString());
            String soporte = new String(jComboBoxSoporte.getSelectedItem().toString());
            String vehiculo = new String(jComboVehiculo.getSelectedItem().toString());
            String matricula = new String(jTextMatricula.getText());
            String estado = new String(jComboBoxEstado.getSelectedItem().toString());
            boolean and = false;
            String queryPe = "SELECT pe.pe_num, pe.pe_fecha, pe.pe_provincia_origen, pe.pe_provincia_destino, " + "pe.pe_servicio, pe.pe_servicio_destino, pe.pe_servicio_especial, pe.pe_dias_campa, " + "pe.pe_ida_vuelta, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_modelo, " + "pe.pe_ta_es_cliente, pe.pe_suplemento, pe.fc_id, tc.tc_tarifa " + "FROM pe_pedidos pe, tc_tarifas_clientes tc " + "WHERE pe.pe_soporte = tc.tc_soporte AND pe.pe_servicio = tc.tc_servicio " + "AND pe.pe_servicio_destino = tc.tc_servicio_destino;";
            String query = "SELECT * FROM pe_pedidos pe ";
            if (!cliente.equals("")) {
                Cliente cliente2 = new Cliente();
                clienteID = cliente2.getClienteID(cliente);
                query = query + ", pc_pedidos_clientes pc WHERE pe.pe_num = pc.pe_num AND pc.cl_id = " + clienteID;
                and = true;
            } else {
                query = query + " WHERE ";
            }
            if ((!fechaI.equals("")) && (!fechaF.equals(""))) {
                if (and) {
                    query = query + " AND pe_fecha >='" + fechaI + "' and pe_fecha<='" + fechaF + "'";
                } else {
                    query = query + " pe_fecha >='" + fechaI + "' and pe_fecha<='" + fechaF + "'";
                    and = true;
                }
            }
            if (!fueraMad) {
                if (and) {
                    query = query + " AND pe_fuera_mad=0 AND pe_servicio='" + servicio + "'";
                } else {
                    query = query + " pe_fuera_mad=0 AND pe_servicio='" + servicio + "'";
                    and = true;
                }
            } else {
                if (and) {
                    query = query + " AND pe_fuera_mad=1 ";
                } else {
                    query = query + " pe_fuera_mad=1 ";
                    and = true;
                }
                if (!servicioFMad.equals("Selecciona")) {
                    query = query + " AND pe_servicio='" + servicioFMad + "'";
                }
                if (!servicioFMadDestino.equals("Selecciona")) {
                    query = query + " AND pe_servicio_destino='" + servicioFMadDestino + "'";
                }
            }
            if (soporte.equals("")) {
                if (and) {
                    query = query + " AND pe_soporte='" + soporte + "'";
                } else {
                    query = query + " pe_soporte='" + soporte + "'";
                    and = true;
                }
            }
            if (!vehiculo.equals("Selecciona")) {
                if (and) {
                    query = query + " AND pe_vehiculo='" + vehiculo + "'";
                } else {
                    query = query + " pe_vehiculo='" + vehiculo + "'";
                    and = true;
                }
            }
            if (!matricula.equals("")) {
                if (and) {
                    query = query + " AND pe_matricula='" + matricula + "'";
                } else {
                    query = query + " pe_matricula='" + matricula + "'";
                    and = true;
                }
            }
            if (estado.equals("")) {
                if (and) {
                    query = query + " AND pe_estado='" + estado + "'";
                } else {
                    query = query + " pe_estado='" + estado + "'";
                    and = true;
                }
            }
            System.out.println(query + " ORDER BY pe.pe_num DESC");
            query = query + " ORDER BY pe.pe_num ASC";
            CSResultBuscarPedido resultBuscarCliente = new CSResultBuscarPedido(query);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CSBuscarFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSBuscarFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSBuscarFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jCheckBoxFMadridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFMadridActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxFMadrid.isSelected()) {
            jComboBoxServicio.setEnabled(false);
            jComboBoxServicioFMad.setEnabled(true);
            jComboBoxServicioFMadDestino.setEnabled(true);
        } else {
            jComboBoxServicio.setEnabled(true);
            jComboBoxServicioFMad.setEnabled(false);
            jComboBoxServicioFMadDestino.setEnabled(false);
        }
}//GEN-LAST:event_jCheckBoxFMadridActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");
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

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
       CSDesktop.BuscarPedido.dispose();
       CSDesktop.menuBuscarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextMatriculaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMatriculaFocusLost
        String MatriculaM = jTextMatricula.getText().toUpperCase();
       jTextMatricula.setText(MatriculaM);
    }//GEN-LAST:event_jTextMatriculaFocusLost

 public Dimension getPreferredSize()
   {
      return new Dimension( 1020,500 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JCheckBox jCheckBoxFMadrid;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxServicio;
    private javax.swing.JComboBox jComboBoxServicioFMad;
    private javax.swing.JComboBox jComboBoxServicioFMadDestino;
    private javax.swing.JComboBox jComboBoxSoporte;
    private javax.swing.JComboBox jComboVehiculo;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextMatricula;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lEstado3;
    private javax.swing.JLabel lFechaFin;
    private javax.swing.JLabel lFechaIni;
    private javax.swing.JLabel lFuMadrid;
    private javax.swing.JLabel lMatricula;
    private javax.swing.JLabel lServicio;
    private javax.swing.JLabel lServicioFMad;
    private javax.swing.JLabel lServicioFMad1;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lVehiculo;
    // End of variables declaration//GEN-END:variables


    private void limitacionesCampos()
    {
       LimitadorDeDocumento limitadorMatricula= new LimitadorDeDocumento(10);
       jTextMatricula.setDocument(limitadorMatricula);
    }
   
}