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
import javax.swing.event.InternalFrameEvent;
import utils.Utilidades;
import data.Cliente;
import data.DbConnection;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author lito
 */
public class CSAnyadirTarifaCliente extends JPanel
{
    DbConnection datos;
    
    public CSAnyadirTarifaCliente() throws ParseException
    {
        CSDesktop.menuTarifaCliente.setEnabled(false);
        this.setLayout(new GridBagLayout());
        Date hoy = new Date();
        initComponents();
        jDateDesde.setDate(hoy);
        String nueva="01-01-2050";
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        Date d=sdf.parse(nueva);
        jDateHasta.setDate(d);
        
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

   /**
    * Asignamos un tamaño por defecto0
    * @return
    */
    public Dimension getPreferredSize()
    {
      return new Dimension( 826,620 );
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
        lCliente = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        jLabelO5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateHasta = new com.toedter.calendar.JDateChooser();
        jSeparator4 = new javax.swing.JSeparator();
        lServicioFMadOrigen = new javax.swing.JLabel();
        jDateDesde = new com.toedter.calendar.JDateChooser();
        jTextTarifa = new javax.swing.JTextField();
        lTarifa = new javax.swing.JLabel();
        lServicioFMadDestino = new javax.swing.JLabel();
        jComboBoxServicioFMadDestino = new javax.swing.JComboBox();
        jLabelO3 = new javax.swing.JLabel();
        jLabelO = new javax.swing.JLabel();
        jLabelO4 = new javax.swing.JLabel();
        jLabelO6 = new javax.swing.JLabel();
        jLabelO7 = new javax.swing.JLabel();
        jComboBoxServicioFMad = new javax.swing.JComboBox();
        jComboBoxSoporte = new javax.swing.JComboBox();
        lPContacto2 = new javax.swing.JLabel();
        jCheckBoxFMadrid = new javax.swing.JCheckBox();
        lSoporte = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lFuMadrid = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lFechaDesde = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox();
        lFechaHasta = new javax.swing.JLabel();

        setForeground(new java.awt.Color(0, 0, 100));
        setPreferredSize(new java.awt.Dimension(550, 322));

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

        lCliente.setForeground(new java.awt.Color(0, 0, 100));
        lCliente.setText("Cliente");
        lCliente.setName("lCliente"); // NOI18N

        jTextCliente.setEditable(false);
        jTextCliente.setName("jTextCliente"); // NOI18N

        jToggleButtonCliente.setText("Buscar Cliente");
        jToggleButtonCliente.setName("jToggleButtonCliente"); // NOI18N
        jToggleButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonClienteActionPerformed(evt);
            }
        });

        jLabelO5.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO5.setText("*");
        jLabelO5.setName("jLabelO5"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

        jDateHasta.setDateFormatString("dd-MM-yyyy");
        jDateHasta.setName("jDateHasta"); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        lServicioFMadOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMadOrigen.setText("FM Origen");
        lServicioFMadOrigen.setName("lServicioFMadOrigen"); // NOI18N

        jDateDesde.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateDesde.setName("jDateDesde"); // NOI18N

        jTextTarifa.setName("jTextTarifa"); // NOI18N

        lTarifa.setFont(new java.awt.Font("Tahoma", 1, 11));
        lTarifa.setForeground(new java.awt.Color(0, 0, 100));
        lTarifa.setText("TARIFA");
        lTarifa.setName("lTarifa"); // NOI18N

        lServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMadDestino.setText("FM Destino");
        lServicioFMadDestino.setName("lServicioFMadDestino"); // NOI18N

        jComboBoxServicioFMadDestino.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMadDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJ.", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVED.", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxServicioFMadDestino.setName("jComboBoxServicioFMadDestino"); // NOI18N

        jLabelO3.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO3.setText("*");
        jLabelO3.setName("jLabelO3"); // NOI18N

        jLabelO.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO.setText("*");
        jLabelO.setName("jLabelO"); // NOI18N

        jLabelO4.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO4.setText("*");
        jLabelO4.setName("jLabelO4"); // NOI18N

        jLabelO6.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO6.setText("*");
        jLabelO6.setName("jLabelO6"); // NOI18N

        jLabelO7.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO7.setText("*");
        jLabelO7.setName("jLabelO7"); // NOI18N

        jComboBoxServicioFMad.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJ.", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVED.", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxServicioFMad.setName("jComboBoxServicioFMad"); // NOI18N

        jComboBoxSoporte.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxSoporte.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxSoporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grúa", "Camión completo", "Conductor", "Tren" }));
        jComboBoxSoporte.setName("jComboBoxSoporte"); // NOI18N

        lPContacto2.setFont(new java.awt.Font("Tahoma", 1, 11));
        lPContacto2.setForeground(new java.awt.Color(170, 16, 4));
        lPContacto2.setText("SERVICIOS");
        lPContacto2.setName("lPContacto2"); // NOI18N

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

        lSoporte.setForeground(new java.awt.Color(0, 0, 100));
        lSoporte.setText("Soporte");
        lSoporte.setName("lSoporte"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        lFuMadrid.setForeground(new java.awt.Color(0, 0, 100));
        lFuMadrid.setText("Fuera de Madrid");
        lFuMadrid.setName("lFuMadrid"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        lFechaDesde.setForeground(new java.awt.Color(0, 0, 100));
        lFechaDesde.setText("Fecha Vigencia Desde");
        lFechaDesde.setName("lFechaDesde"); // NOI18N

        jComboBoxServicio.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicio.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Urbano", "Interurbano", "Provincial", "Urbano ITV" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N

        lFechaHasta.setForeground(new java.awt.Color(0, 0, 100));
        lFechaHasta.setText("Fecha Vigencia Hasta");
        lFechaHasta.setName("lFechaHasta"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabelO5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lCliente)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelO6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTarifa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(330, 330, 330))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 636, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelO, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFuMadrid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lServicioFMadOrigen)
                                        .addGap(141, 141, 141)))
                                .addGap(41, 41, 41)
                                .addComponent(lServicioFMadDestino)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxServicioFMadDestino, 0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabelO3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSoporte)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabelO4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaDesde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelO7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addComponent(lPContacto2))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lPContacto2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lFuMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelO))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSoporte)
                            .addComponent(jLabelO3)
                            .addComponent(lSoporte)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelO4)))
                    .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelO7)
                        .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCliente)
                    .addComponent(jLabelO5)
                    .addComponent(jToggleButtonCliente))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelO6)
                    .addComponent(lTarifa)
                    .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCancelar))
                .addGap(46, 46, 46))
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
            String cliente = new String(jTextCliente.getText());

            Calendar fechaCalendar = jDateDesde.getCalendar();
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaDesde = formatoDeFecha.format(fecha);
            }

            fechaCalendar = jDateHasta.getCalendar();
            if (fechaCalendar != null) {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaHasta = formatoDeFecha.format(fecha);
            }
            int idCliente = 0;
            Cliente cl = new Cliente();
            idCliente = cl.getClienteID(cliente);
            try {
                idCliente = this.getIdCliente(cliente);
            } catch (SQLException ex) {
                Logger.getLogger(CSAnyadirTarifaCliente.class.getName()).log(Level.SEVERE, null, ex);
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

            if(servicio.equals("Selecciona"))
                servicio = "";

            if(servicioFMad.equals("Selecciona"))
                servicioFMad = "";

            if(servicioFMadDestino.equals("Selecciona"))
                servicioFMadDestino = "";

            //Comprobamos si la tarifa existe para ese cliente
            int ta = 0;
            ta = getTarifaCliente(idCliente, soporte, servicio, servicioFMad, servicioFMadDestino);
            if (ta != 0) {
                jButtonGuardar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Ya existe una tarifa con el Soporte y Servicio seleccionado para ese cliente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);
                jButtonGuardar.setEnabled(true);
            }
          //CAMPOS OBLIGATORIOS
            else if (!Utilidades.campoObligatorio(servicio,"Servicio").equals("OK") &&
                 !Utilidades.campoObligatorio(servicioFMad,"ServicioFMad").equals("OK"))
            {
                ValidarFormatos(Utilidades.campoObligatorio(servicio,"servicio"));
            }
            else if (!Utilidades.campoObligatorio(soporte,"Soporte").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(soporte,"Soporte"));
            }
            else if (!Utilidades.campoObligatorio(fechaDesde,"Fecha Desde").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(fechaDesde,"Fecha Desde"));
            }
            else if (!Utilidades.campoObligatorio(cliente,"Cliente").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(cliente,"Cliente"));
            }
            else if (!Utilidades.campoObligatorio(tarifa,"Tarifa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(tarifa,"Tarifa"));

            } 
            else
            {
                double incrementoN = 0;
                if (!incremento.equals("") && Utilidades.validarNumericoDecimal(incremento).equals("OK"))
                {
                    incrementoN =  Double.valueOf(incremento).doubleValue();
                }
                
                double tarifaN = 0;
                if (!tarifa.equals("") && Utilidades.validarNumericoDecimal(tarifa).equals("OK"))
                {
                    tarifaN = Double.valueOf(tarifa).doubleValue();
                }

                String query = "INSERT INTO tc_tarifas_clientes (tc_servicio, tc_servicio_origen, tc_servicio_destino, " +
                               "tc_soporte, tc_fecha_desde, tc_fecha_hasta, tc_fuera_mad, tc_incremento, tc_tarifa, cl_id) " + 
                               "VALUES ('" + servicio + "', '" + servicioFMad + "', '" + servicioFMadDestino + "', " +
                               "'" + soporte + "', '" + fechaDesde + "','" + fechaHasta + "','" + fueraM + "', " +
                               ""+ incrementoN + ", "+ tarifaN + ", "+ idCliente+")";

                System.out.println(query);
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
                    CSDesktop.TarifaCliente.dispose();
                    CSDesktop.menuTarifaCliente.setEnabled(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CSAnyadirTarifaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.TarifaCliente.dispose();
        CSDesktop.menuTarifaCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");

        String query="select cl_id,cl_nombre,cl_DNI_CIF from cl_clientes order by cl_id";

        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
        int clienteID = 0;
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(query,jTextCliente);
        CSDesktop.BuscaCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaCliente );
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible( true );
    }//GEN-LAST:event_jToggleButtonClienteActionPerformed

    private void jCheckBoxFMadridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFMadridActionPerformed
        // TODO add your handling code here:
        /**if (jCheckBoxFMadrid.isSelected()) {
            jComboBoxServicio.setEnabled(false);
            jComboBoxServicioFMad.setEnabled(true);
            jComboBoxServicioFMadDestino.setEnabled(true);
        } else {
            jComboBoxServicio.setEnabled(true);
            jComboBoxServicioFMad.setEnabled(false);
            jComboBoxServicioFMadDestino.setEnabled(false);
        }*/
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
    private javax.swing.JLabel jLabelO;
    private javax.swing.JLabel jLabelO3;
    private javax.swing.JLabel jLabelO4;
    private javax.swing.JLabel jLabelO5;
    private javax.swing.JLabel jLabelO6;
    private javax.swing.JLabel jLabelO7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextTarifa;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFechaDesde;
    private javax.swing.JLabel lFechaHasta;
    private javax.swing.JLabel lFuMadrid;
    private javax.swing.JLabel lPContacto2;
    private javax.swing.JLabel lServicioFMadDestino;
    private javax.swing.JLabel lServicioFMadOrigen;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lTarifa;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonGuardar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGuardar.setEnabled(true);
    }

     /**
     * Función para sacar el id del cliente de la bd
     * @return
     * @throws SQLException
     */
    private int getIdCliente(String cliente) throws SQLException
    {
        datos = new DbConnection();
        ResultSet rs = datos.select("SELECT cl_id FROM cl_clientes WHERE cl_nombre = '"+cliente+"'");

        int valor = 0;
        while(rs.next())
        {
            valor = rs.getInt("cl_id");
        }
        return valor;
    }

    /**
     * Función para comprobar si existe una tarifa de un cliente en la bd
     * @return
     * @throws SQLException
     */
    private int getTarifaCliente(int cliente, String soporte, String servicio, String servicio_origen, String servicio_destino) throws SQLException
    {
        datos = new DbConnection();
        int tarifa = 0;
        tarifa = datos.numeroFilas("SELECT tc_id FROM tc_tarifas_clientes WHERE " +
                                   "tc_servicio='"+servicio+"' AND tc_servicio_origen='"+servicio_origen+"' " +
                                   "AND tc_servicio_destino='"+servicio_destino+"' " +
                                   "AND tc_soporte='"+soporte+"' AND cl_id = '"+cliente+"'");

        System.out.println("SELECT tc_id FROM tc_tarifas_clientes WHERE " +
                                   "tc_servicio='"+servicio+"' AND tc_servicio_origen='"+servicio_origen+"' " +
                                   "AND tc_servicio_destino='"+servicio_destino+"' " +
                                   "AND tc_soporte='"+soporte+"' AND cl_id = '"+cliente+"'");
        return tarifa;
    }

}