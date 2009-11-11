/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABEditarTarifaCliente.java
 *
 * Created on 29-sep-2009, 21:04:18
 */

package neg;

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
import java.util.GregorianCalendar;
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
public class CSEditarTarifaProveedor extends JPanel
{
    DbConnection datos;
    
    public CSEditarTarifaProveedor(int tarifa)
    {
        CSDesktop.ABResultTarifasProveedor.setVisible(false);
        CSDesktop.EditarProveedor.setVisible(false);
        datos = new DbConnection();
        this.setLayout(new GridBagLayout());
        initComponents();
        this.datosTarifa(tarifa);

        KeyListener l = new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e)
            {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    jButtonModificar.doClick();
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
                this.getComponents()[k] != jComboBoxServicioFMadDestino &&
                this.getComponents()[k] != jComboBoxServicioFMad &&
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

        jButtonModificar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTextIncremento = new javax.swing.JTextField();
        jDateHasta = new com.toedter.calendar.JDateChooser();
        lIncremento = new javax.swing.JLabel();
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
        lPorCiento = new javax.swing.JLabel();
        jLabelO5 = new javax.swing.JLabel();
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
        jTextId = new javax.swing.JTextField();
        lNumero = new javax.swing.JLabel();

        setForeground(new java.awt.Color(0, 0, 100));
        setPreferredSize(new java.awt.Dimension(550, 322));

        jButtonModificar.setText("Modificar");
        jButtonModificar.setName("jButtonModificar"); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
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

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

        jTextIncremento.setEditable(false);
        jTextIncremento.setName("jTextIncremento"); // NOI18N

        jDateHasta.setDateFormatString("dd-MM-yyyy");
        jDateHasta.setName("jDateHasta"); // NOI18N

        lIncremento.setForeground(new java.awt.Color(0, 0, 100));
        lIncremento.setText("    Incremento");
        lIncremento.setName("lIncremento"); // NOI18N

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
        jComboBoxServicioFMadDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERíA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CORDOBA", "CORUÑA, A", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPUZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MALAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PALMAS, LAS", "PONTEVEDRA", "RIOJA, LA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxServicioFMadDestino.setEnabled(false);
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

        lPorCiento.setText("%");
        lPorCiento.setName("lPorCiento"); // NOI18N

        jLabelO5.setForeground(new java.awt.Color(204, 0, 0));
        jLabelO5.setText("*");
        jLabelO5.setName("jLabelO5"); // NOI18N

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
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Urbano", "Interurbano", "Provincial", "Urbano ITV" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N

        lFechaHasta.setForeground(new java.awt.Color(0, 0, 100));
        lFechaHasta.setText("Fecha Vigencia Hasta");
        lFechaHasta.setName("lFechaHasta"); // NOI18N

        jTextId.setEditable(false);
        jTextId.setEnabled(false);
        jTextId.setFocusable(false);
        jTextId.setName("jTextId"); // NOI18N
        jTextId.setOpaque(false);

        lNumero.setForeground(new java.awt.Color(0, 0, 100));
        lNumero.setText("   Número de tarifa  TC/");
        lNumero.setName("lNumero"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(393, 393, 393)
                                .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                                .addGap(3, 3, 3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelO3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lSoporte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addComponent(jLabelO4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaDesde)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabelO5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPorCiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 533, Short.MAX_VALUE)
                                .addComponent(jLabelO6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTarifa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                                        .addGap(3, 3, 3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelO)
                                        .addGap(8, 8, 8)
                                        .addComponent(lFuMadrid)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(lServicioFMadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(219, 219, 219)
                                        .addComponent(lServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxServicioFMadDestino, 0, 124, Short.MAX_VALUE)
                                        .addGap(6, 6, 6))))
                            .addComponent(lPContacto2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 597, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNumero)
                    .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lPContacto2)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMadOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxFMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelO)
                            .addComponent(lFuMadrid, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelO3)
                            .addComponent(lFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelO4)
                            .addComponent(lSoporte)
                            .addComponent(jComboBoxSoporte)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addComponent(jDateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelO5)
                        .addComponent(lFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTarifa)
                    .addComponent(jLabelO6)
                    .addComponent(jTextIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPorCiento)
                    .addComponent(lIncremento))
                .addGap(210, 210, 210)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModificar)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        System.out.println("\njButtonGuardar_actionPerformed(ActionEvent e) called.");
            int fueraM=0;
            String fechaDesde = "";
            String fechaHasta = "";
            String id = new String(jTextId.getText());
            String servicio = new String(jComboBoxServicio.getSelectedItem().toString());
            String servicioFMad=new String(jComboBoxServicioFMad.getSelectedItem().toString());
            String servicioFMadDestino=new String(jComboBoxServicioFMadDestino.getSelectedItem().toString());
            String soporte = new String(jComboBoxSoporte.getSelectedItem().toString());
            boolean fueraMad = new Boolean(jCheckBoxFMadrid.isSelected());
            String incremento = new String(jTextIncremento.getText());
            String tarifa = new String(jTextTarifa.getText());

            Calendar fechaCalendar = jDateDesde.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaDesde = formatoDeFecha.format(fecha);
            }

            fechaCalendar = jDateHasta.getCalendar();
            if (fechaCalendar!=null)
            {
                Date fecha = fechaCalendar.getTime();
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaHasta = formatoDeFecha.format(fecha);
            }


            if(servicio.equals("") && soporte.equals(""))
            {
                jButtonModificar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debe asignar algún valor.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonModificar.setEnabled(true);
                //this.setVisible(true);
            }
            else
            {
                String servicioAux = "";
                String servicioDestinoAux = "";

                if(!fueraMad)
                {
                  fueraM = 0;
                  servicioAux =servicio;
                }
                else
                {
                  fueraM=1;
                  servicioAux = servicioFMad;
                  servicioDestinoAux = servicioFMadDestino;
                }
                if (servicioAux.equals("Selecciona"))
                {
                    
                }

                if (servicio.equals(""))
                {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe asignar valor al campo Servicio.</FONT></HTML>");
                }
                if (soporte.equals(""))
                {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe asignar valor al campo Soporte.</FONT></HTML>");
                }
                if (incremento.equals("") && !Utilidades.validarNumericoDecimal(incremento).equals("OK"))
                {
                    incremento = "0";
                }
                if (tarifa.equals("") && !Utilidades.validarNumericoDecimal(tarifa).equals("OK"))
                {
                    JLabel errorFields1 = new JLabel("<HTML><FONT COLOR = Blue>Debe rellenar el campo tarifa con un valor en €.</FONT></HTML>");
                }
                double tarifaN = Double.valueOf(tarifa).doubleValue();

                if (incremento.trim().equals("") || !Utilidades.validarNumericoDecimal(incremento).equals("OK"))
                {
                    incremento = "0";
                }

                String query = "UPDATE tp_tarifas_proveedores SET tp_servicio ='"+servicioAux+"',tp_servicio_destino ='"+servicioFMadDestino+"', "+
                               "tp_soporte='"+soporte+"', tp_fecha_desde = '"+fechaDesde+"', tp_fecha_hasta='"+fechaHasta+"', " +
                               "tp_fuera_mad='"+fueraM+"', tp_incremento='"+incremento+"', tp_tarifa='"+tarifaN+"' " +
                               "WHERE tp_id = "+id+"";

                System.out.println(query);
                datos = new DbConnection();
                boolean rs = datos.manipuladorDatos(query);
                System.out.println(rs);
                if(rs)
                {
                    jButtonModificar.setEnabled(false);
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);
                    jButtonModificar.setEnabled(true);
                    //this.setVisible(true);
                }
                else
                {
                    jButtonModificar.setEnabled(false);
                    JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,mensaje);
                    jButtonModificar.setEnabled(true);
                    CSDesktop.EditarTarifaProveedor.dispose();
                    CSDesktop.ABResultTarifasProveedor.dispose();
                    CSDesktop.EditarProveedor.setVisible(true);


                }

            }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.EditarTarifaProveedor.dispose();
        CSDesktop.ABResultTarifasProveedor.setVisible(true);
        
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
    private javax.swing.JButton jButtonModificar;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextId;
    private javax.swing.JTextField jTextIncremento;
    private javax.swing.JTextField jTextTarifa;
    private javax.swing.JLabel lFechaDesde;
    private javax.swing.JLabel lFechaHasta;
    private javax.swing.JLabel lFuMadrid;
    private javax.swing.JLabel lIncremento;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lPContacto2;
    private javax.swing.JLabel lPorCiento;
    private javax.swing.JLabel lServicioFMadDestino;
    private javax.swing.JLabel lServicioFMadOrigen;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lTarifa;
    // End of variables declaration//GEN-END:variables


    /**
     * Función para sacar todos los datos de la tarifa del cliente de la bd
     * @param cliente
     * @return
     * @throws SQLException
     */
    public void datosTarifa(int tarifa)
    {
    	String query = "SELECT * FROM tp_tarifas_proveedores " +
                       "WHERE tp_id = "+tarifa;

        ResultSet rs = datos.select(query);
        int numeroFila = 0;
        String tp_id="";

         try
        {
            while(rs.next())
            {
                tp_id=rs.getString("tp_id");
                jTextId.setText(rs.getString("tp_id"));
                if (rs.getInt("tp_fuera_mad") == 0)
                {
                    jCheckBoxFMadrid.setSelected(false);
                    jComboBoxServicio.setEnabled(true);
                    jComboBoxServicioFMad.setEnabled(false);
                    jComboBoxServicioFMadDestino.setEnabled(false);
                }
                else
                {
                    jCheckBoxFMadrid.setSelected(true);
                    jComboBoxServicio.setEnabled(false);
                    jComboBoxServicioFMad.setEnabled(true);
                    jComboBoxServicioFMadDestino.setEnabled(true);
                }
                jComboBoxServicioFMad.setSelectedItem(rs.getString("tp_servicio"));
                jComboBoxServicio.setSelectedItem(rs.getString("tp_servicio"));
                jComboBoxServicioFMadDestino.setSelectedItem(rs.getString("tp_servicio_destino"));
                jComboBoxSoporte.setSelectedItem(rs.getString("tp_soporte"));
                jDateDesde.setDate(rs.getDate("tp_fecha_desde"));
                jDateHasta.setDate(rs.getDate("tp_fecha_hasta"));
                jTextIncremento.setText(rs.getString("tp_incremento"));
                jTextTarifa.setText(rs.getString("tp_tarifa"));
                numeroFila++;
            }
            rs.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Tarifas para este proveedor, con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
    }

}