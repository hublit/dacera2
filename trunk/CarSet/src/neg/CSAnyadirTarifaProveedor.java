/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABAnyadirProveedores.java
 *
 * Created on 29-sep-2009, 21:04:18
 */

package neg;


import java.text.ParseException;
import utils.Utilidades;
import data.DbConnection;
import data.Proveedor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lito
 */
public class CSAnyadirTarifaProveedor extends JPanel
{
    DbConnection datos;
    /** Creates new form ABAnyadirProveedores */
    public CSAnyadirTarifaProveedor() throws ParseException
    {
        CSDesktop.menuTarifaProveedor.setEnabled(false);
        this.setLayout(new GridBagLayout());
        Date hoy = new Date();
        initComponents();
        jDateDesde.setDate(hoy);
        String nueva="01-01-2050";
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        Date d=sdf.parse(nueva);
        jDateHasta.setDate(d);
        //Para la fecha
        
        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    jButtonGuardar.doClick();
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
                this.getComponents()[k] != jComboBoxServicioFMadDestino &&
                this.getComponents()[k] != jComboBoxSoporte)
            {
                this.getComponents()[k].addKeyListener(l);
            }
        }
        addKeyListener(l);

    }

   public Dimension getPreferredSize()
   {
      return new Dimension( 850,600 );
   }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jToggleButtonProveedor = new javax.swing.JToggleButton();
        jTextProveedor = new javax.swing.JTextField();
        lProveedor = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lFechaHasta = new javax.swing.JLabel();
        lServicioFMadDestino = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox();
        jComboBoxServicioFMadDestino = new javax.swing.JComboBox();
        jLabelO4 = new javax.swing.JLabel();
        jLabelO = new javax.swing.JLabel();
        jLabelO3 = new javax.swing.JLabel();
        jLabelO7 = new javax.swing.JLabel();
        jComboBoxServicioFMad = new javax.swing.JComboBox();
        jComboBoxSoporte = new javax.swing.JComboBox();
        lPContacto2 = new javax.swing.JLabel();
        lFechaDesde = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jDateHasta = new com.toedter.calendar.JDateChooser();
        lSoporte = new javax.swing.JLabel();
        lServicioFMadOrigen = new javax.swing.JLabel();
        jCheckBoxFMadrid = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        lFuMadrid = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jDateDesde = new com.toedter.calendar.JDateChooser();
        jLabelO6 = new javax.swing.JLabel();
        lTarifa = new javax.swing.JLabel();
        jTextTarifa = new javax.swing.JTextField();

        setForeground(new java.awt.Color(0, 0, 100));

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setName("jButtonGuardar"); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
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

        jSeparator1.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator1.setName("jSeparator1"); // NOI18N

        jToggleButtonProveedor.setText("Buscar Proveedor");
        jToggleButtonProveedor.setName("jToggleButtonProveedor"); // NOI18N
        jToggleButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonProveedorActionPerformed(evt);
            }
        });

        jTextProveedor.setEditable(false);
        jTextProveedor.setName("jTextProveedor"); // NOI18N
        jTextProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextProveedorActionPerformed(evt);
            }
        });

        lProveedor.setForeground(new java.awt.Color(0, 0, 100));
        lProveedor.setText("Proveedor");
        lProveedor.setName("lProveedor"); // NOI18N

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

        lFechaHasta.setForeground(new java.awt.Color(0, 0, 100));
        lFechaHasta.setText("Fecha Vigencia Hasta");
        lFechaHasta.setName("lFechaHasta"); // NOI18N

        lServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMadDestino.setText("FM Destino");
        lServicioFMadDestino.setName("lServicioFMadDestino"); // NOI18N

        jComboBoxServicio.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicio.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urbano", "Interurbano", "Provincial", "Urbano ITV" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N

        jComboBoxServicioFMadDestino.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMadDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERíA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CORDOBA", "CORUÑA, A", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPUZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MALAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PALMAS, LAS", "PONTEVEDRA", "RIOJA, LA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxServicioFMadDestino.setEnabled(false);
        jComboBoxServicioFMadDestino.setName("jComboBoxServicioFMadDestino"); // NOI18N

        jLabelO4.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO4.setText("*");
        jLabelO4.setName("jLabelO4"); // NOI18N

        jLabelO.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO.setText("*");
        jLabelO.setName("jLabelO"); // NOI18N

        jLabelO3.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO3.setText("*");
        jLabelO3.setName("jLabelO3"); // NOI18N

        jLabelO7.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO7.setText("*");
        jLabelO7.setName("jLabelO7"); // NOI18N

        jComboBoxServicioFMad.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERíA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CORDOBA", "CORUÑA, A", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPUZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MALAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PALMAS, LAS", "PONTEVEDRA", "RIOJA, LA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxServicioFMad.setEnabled(false);
        jComboBoxServicioFMad.setName("jComboBoxServicioFMad"); // NOI18N

        jComboBoxSoporte.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxSoporte.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxSoporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grúa", "Camión completo", "Conductor", "Tren" }));
        jComboBoxSoporte.setName("jComboBoxSoporte"); // NOI18N

        lPContacto2.setFont(new java.awt.Font("Tahoma", 1, 11));
        lPContacto2.setForeground(new java.awt.Color(170, 16, 4));
        lPContacto2.setText("SERVICIOS");
        lPContacto2.setName("lPContacto2"); // NOI18N

        lFechaDesde.setForeground(new java.awt.Color(0, 0, 100));
        lFechaDesde.setText("Fecha Vigencia Desde");
        lFechaDesde.setName("lFechaDesde"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        jDateHasta.setDateFormatString("dd-MM-yyyy");
        jDateHasta.setName("jDateHasta"); // NOI18N

        lSoporte.setForeground(new java.awt.Color(0, 0, 100));
        lSoporte.setText("Soporte");
        lSoporte.setName("lSoporte"); // NOI18N

        lServicioFMadOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMadOrigen.setText("FM Origen");
        lServicioFMadOrigen.setName("lServicioFMadOrigen"); // NOI18N

        jCheckBoxFMadrid.setAlignmentX(0.5F);
        jCheckBoxFMadrid.setMaximumSize(new java.awt.Dimension(45, 45));
        jCheckBoxFMadrid.setMinimumSize(new java.awt.Dimension(45, 45));
        jCheckBoxFMadrid.setName("jCheckBoxFMadrid"); // NOI18N
        jCheckBoxFMadrid.setPreferredSize(new java.awt.Dimension(45, 45));
        jCheckBoxFMadrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFMadridActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        lFuMadrid.setForeground(new java.awt.Color(0, 0, 100));
        lFuMadrid.setText("Fuera de Madrid");
        lFuMadrid.setName("lFuMadrid"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        jDateDesde.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateDesde.setName("jDateDesde"); // NOI18N

        jLabelO6.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO6.setText("*");
        jLabelO6.setName("jLabelO6"); // NOI18N

        lTarifa.setFont(new java.awt.Font("Tahoma", 1, 11));
        lTarifa.setForeground(new java.awt.Color(0, 0, 100));
        lTarifa.setText("TARIFA");
        lTarifa.setName("lTarifa"); // NOI18N

        jTextTarifa.setName("jTextTarifa"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelO, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lFuMadrid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(lServicioFMadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(lServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lPContacto2)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelO6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTarifa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel17)
                                    .addGap(4, 4, 4)
                                    .addComponent(lProveedor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jToggleButtonProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
                                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(14, 14, 14))
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)))))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabelO3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSoporte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(jLabelO4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaDesde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabelO7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))
                        .addGap(3, 3, 3)))
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(394, Short.MAX_VALUE)
                .addComponent(jButtonGuardar)
                .addGap(385, 385, 385))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lServicioFMadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lFuMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelO))))))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelO4)
                                    .addComponent(jComboBoxSoporte)
                                    .addComponent(jLabelO3)
                                    .addComponent(lSoporte)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelO7)
                                    .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButtonProveedor)
                            .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lProveedor)
                            .addComponent(jLabel17))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelO6)
                            .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lTarifa))
                        .addGap(154, 154, 154)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGuardar)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonCancelar)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lPContacto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        try {
            System.out.println("\njButtonGuardar_actionPerformed(ActionEvent e) called.");
            int fueraM = 0;
            String fechaDesde = "";
            String fechaHasta = "";
            String servicio = new String(jComboBoxServicio.getSelectedItem().toString());
            String servicioFMad = new String(jComboBoxServicioFMad.getSelectedItem().toString());
            String servicioFMadDestino = new String(jComboBoxServicioFMadDestino.getSelectedItem().toString());
            String soporte = new String(jComboBoxSoporte.getSelectedItem().toString());
            boolean fueraMad = new Boolean(jCheckBoxFMadrid.isSelected());
            String incremento = "";
            String tarifa = new String(jTextTarifa.getText());
            String proveedor = new String(jTextProveedor.getText());

            Calendar fechaCalendar = jDateDesde.getCalendar();
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaDesde = formatoDeFecha.format(fecha);
            }

            fechaCalendar = jDateDesde.getCalendar();
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaHasta = formatoDeFecha.format(fecha);
            }

            int idProveedor = 0;
            Proveedor pr = new Proveedor();
            idProveedor = pr.getProveedorID(proveedor);
            try {
                idProveedor = this.getIdProveedores(proveedor);
            } catch (SQLException ex) {
                Logger.getLogger(CSAnyadirTarifaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }

            String servicioAux = "";
            if (!fueraMad) {
                fueraM = 0;
                servicioAux = servicio;
            } else {
                fueraM = 1;
                servicioAux = servicioFMad;
            }

            String servicioFMadDestinoAux="";
            if(!servicioFMadDestino.equals("Selecciona"))
            {
                servicioFMadDestinoAux=servicioFMadDestino;
            }
            //Comprobamos si la tarifa existe para ese proveedor
            int ta = 0;
            ta = getTarifaProveedor(idProveedor, soporte, servicioAux, servicioFMadDestino);

            if (ta != 0) {
                jButtonGuardar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Ya existe una tarifa con el Soporte y Servicio seleccionado para ese proveedor.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);
                jButtonGuardar.setEnabled(true);
            } else if (!Utilidades.campoObligatorio(soporte, "Soporte").equals("OK")) {
                ValidarFormatos(Utilidades.campoObligatorio(soporte, "Soporte"));
            } else if (!Utilidades.campoObligatorio(fechaDesde, "Fecha Desde").equals("OK")) {
                ValidarFormatos(Utilidades.campoObligatorio(fechaDesde, "Fecha Desde"));
            } else if (!Utilidades.campoObligatorio(proveedor, "Proveedor").equals("OK")) {
                ValidarFormatos(Utilidades.campoObligatorio(proveedor, "Proveedor"));
            } else if (!Utilidades.campoObligatorio(tarifa, "Tarifa").equals("OK")) {
                ValidarFormatos(Utilidades.campoObligatorio(tarifa, "Tarifa"));
            } else {

                String query = "INSERT INTO tp_tarifas_proveedores (tp_servicio, tp_servicio_destino, tp_soporte, tp_fecha_desde, tp_fecha_hasta, " +
                               "tp_fuera_mad, tp_incremento, tp_tarifa, pr_id) " + "VALUES (";
                if (!servicio.equals("")) {
                    query = query + "'" + servicioAux + "'";
                } else {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe asignar valor al campo Servicio.</FONT></HTML>");
                }

                query = query + " ,'" + servicioFMadDestinoAux + "'";

                if (!soporte.equals("")) {
                    query = query + " ,'" + soporte + "'";
                } else {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe asignar valor al campo Soporte.</FONT></HTML>");
                }
                query = query + " ,'" + fechaDesde + "','" + fechaHasta + "','" + fueraM + "'";
                if (!incremento.equals("") && Utilidades.validarNumericoDecimal(incremento).equals("OK")) {
                    query = query + " , " + incremento + "";
                } else {
                    query = query + " , 0";
                }
                if (!tarifa.equals("") && Utilidades.validarNumericoDecimal(tarifa).equals("OK")) {
                    double tarifaN = Double.valueOf(tarifa).doubleValue();
                    query = query + " , " + tarifaN + "";
                } else {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe rellenar el campo tarifa con un valor en €.</FONT></HTML>");
                }
                if (!proveedor.equals("")) {
                    query = query + " , " + idProveedor + ")";
                } else {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe seleccionar algún Proveedor.</FONT></HTML>");
                }
                System.out.print(query);
                datos = new DbConnection();
                boolean rs = datos.manipuladorDatos(query);
                System.out.println(rs);
                if (rs) {
                    jButtonGuardar.setEnabled(false);
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, errorFields);
                    jButtonGuardar.setEnabled(true);
                    //this.setVisible(true);
                } else {
                    jButtonGuardar.setEnabled(false);
                    JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, mensaje);
                    jButtonGuardar.setEnabled(true);
                    CSDesktop.TarifaProveedor.dispose();
                     CSDesktop.menuTarifaProveedor.setEnabled(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CSAnyadirTarifaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButtonGuardarActionPerformed
    public void ValidarFormatos(String accion)
    {
         jButtonGuardar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGuardar.setEnabled(true);
    }
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.TarifaProveedor.dispose();
         CSDesktop.menuTarifaProveedor.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jToggleButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonProveedorActionPerformed
        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");

        String query="select pr_id,pr_nombre_fiscal,pr_DNI_CIF from pr_proveedores order by pr_id";

        CSDesktop.BuscaProveedor = new JInternalFrame("Seleccionar Proveedor", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectProveedor panel = new CSSelectProveedor(query,jTextProveedor);
        CSDesktop.BuscaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaProveedor.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaProveedor );
        CSDesktop.BuscaProveedor.setLocation(150, 50);
        CSDesktop.BuscaProveedor.setVisible( true );
    }//GEN-LAST:event_jToggleButtonProveedorActionPerformed

    private void jTextProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextProveedorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextProveedorActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JCheckBox jCheckBoxFMadrid;
    private javax.swing.JComboBox jComboBoxServicio;
    private javax.swing.JComboBox jComboBoxServicioFMad;
    private javax.swing.JComboBox jComboBoxServicioFMadDestino;
    private javax.swing.JComboBox jComboBoxSoporte;
    private com.toedter.calendar.JDateChooser jDateDesde;
    private com.toedter.calendar.JDateChooser jDateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabelO;
    private javax.swing.JLabel jLabelO3;
    private javax.swing.JLabel jLabelO4;
    private javax.swing.JLabel jLabelO6;
    private javax.swing.JLabel jLabelO7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextProveedor;
    private javax.swing.JTextField jTextTarifa;
    private javax.swing.JToggleButton jToggleButtonProveedor;
    private javax.swing.JLabel lFechaDesde;
    private javax.swing.JLabel lFechaHasta;
    private javax.swing.JLabel lFuMadrid;
    private javax.swing.JLabel lPContacto2;
    private javax.swing.JLabel lProveedor;
    private javax.swing.JLabel lServicioFMadDestino;
    private javax.swing.JLabel lServicioFMadOrigen;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lTarifa;
    // End of variables declaration//GEN-END:variables



    /**
     * Función para sacar el id del proveedor de la bd
     * @return
     * @throws SQLException
     */
    private int getIdProveedores(String proveedor) throws SQLException
    {
        datos = new DbConnection();
        ResultSet rs = datos.select("SELECT pr_id FROM pr_proveedores WHERE pr_nombre_fiscal = '"+proveedor+"'");
                System.out.print("SELECT pr_id FROM pr_proveedores WHERE pr_nombre_fiscal = '"+proveedor+"'");
        int valor = 0;
        while(rs.next())
        {
            valor = rs.getInt("pr_id");
        }
        return valor;
    }

    /**
     * Función para comprobar si existe una tarifa de un proveedor en la bd
     * @return
     * @throws SQLException
     */
    private int getTarifaProveedor(int proveedor, String soporte, String servicio, String servicio_destino) throws SQLException
    {
        datos = new DbConnection();
        int tarifa = 0;
        tarifa = datos.numeroFilas("SELECT tp_id FROM tp_tarifas_proveedores " +
                                    "WHERE tp_servicio='"+servicio+"' AND tp_servicio_destino='"+servicio_destino+"' " +
                                    "AND tp_soporte='"+soporte+"' AND pr_id = '"+proveedor+"'");

        System.out.println("SELECT tp_id FROM tp_tarifas_proveedores " +
                                    "WHERE tp_servicio='"+servicio+"' AND tp_servicio_destino='"+servicio_destino+"' " +
                                    "AND tp_soporte='"+soporte+"' AND pr_id = '"+proveedor+"'");
        return tarifa;
    }
}
