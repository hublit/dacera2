/*
 * ABAnyadirProveedores.java
 *
 * Created on 29-sep-2009, 21:04:18
 */

package neg;

import data.BeanCorreoCliente;
import utils.Utilidades;
import utils.LimitadorDeDocumento;
import data.Cliente;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lito
 */
public class CSAnyadirPedido extends JPanel
{
    int clienteID=0;
    int proveedorID=0;

    /** Creates new form ABAnyadirProveedores */
    public CSAnyadirPedido() throws SQLException
    {
        CSDesktop.mailCliente.clear();
        CSDesktop.nombreCliente.clear();
        CSDesktop.mailProveedor.clear();
        CSDesktop.nombreProveedor.clear();
        CSDesktop.menuNuevoPedido.setEnabled(false);
        this.setLayout(new GridBagLayout());
        Date hoy = new Date();
        initComponents();
        jDateFecha.setDate(hoy);
        getFactorCorrecion();
        limitacionesCampos();

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
                    jButtonCancelar1.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        for (int k = 0; k < this.getComponents().length; k ++)
        {
            if (this.getComponents()[k] != jComboTipoOrigen &&
                this.getComponents()[k] != jComboTipoDestino &&
                this.getComponents()[k] != jComboBoxProvinciaOrigen &&
                this.getComponents()[k] != jComboBoxProvinciaDestino &&
                this.getComponents()[k] != jComboBoxServicio &&
                this.getComponents()[k] != jComboBoxServicioFMad &&
                this.getComponents()[k] != jComboFactor &&
                this.getComponents()[k] != jComboBoxSoporte &&
                this.getComponents()[k] != jComboBoxEstado)
            {
                this.getComponents()[k].addKeyListener(l);
            }
        }
        addKeyListener(l);
    }   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lDireccionOrigen = new javax.swing.JLabel();
        jTextDireccionOrigen = new javax.swing.JTextField();
        lTelefonoOrigen = new javax.swing.JLabel();
        jTextTelefonoOrigen = new javax.swing.JTextField();
        lNombreOrigen = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        lDescripción = new javax.swing.JLabel();
        lCodPostalOrigen = new javax.swing.JLabel();
        jTextCodPostalOrigen = new javax.swing.JTextField();
        lNumero = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        lFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboFactor = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextDescripcion = new javax.swing.JTextPane();
        lCliente = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        jToggleButtonCliente = new javax.swing.JToggleButton();
        lProveedor = new javax.swing.JLabel();
        jTextProveedor = new javax.swing.JTextField();
        jToggleButtonProveedor = new javax.swing.JToggleButton();
        lMatricula = new javax.swing.JLabel();
        jTextMatricula = new javax.swing.JTextField();
        lMarca = new javax.swing.JLabel();
        jTextMarca = new javax.swing.JTextField();
        lModelo = new javax.swing.JLabel();
        jTextModelo = new javax.swing.JTextField();
        lSolred = new javax.swing.JLabel();
        jTextSolred = new javax.swing.JTextField();
        lViaje = new javax.swing.JLabel();
        jTextViaje = new javax.swing.JTextField();
        lOrigen = new javax.swing.JLabel();
        lFechaOrigen = new javax.swing.JLabel();
        lHoraOrigen = new javax.swing.JLabel();
        lTipoOrigen = new javax.swing.JLabel();
        jComboTipoOrigen = new javax.swing.JComboBox();
        lDestino = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lTarifaEspecial = new javax.swing.JLabel();
        lTarifaEsProv = new javax.swing.JLabel();
        jTextTaEsProv = new javax.swing.JTextField();
        jTextTaEsCli = new javax.swing.JTextField();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        jDateFechaOrigen = new com.toedter.calendar.JDateChooser();
        jTextNombreOrigen = new javax.swing.JTextField();
        jButtonCancelar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lServicio = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox();
        lServicioFMad = new javax.swing.JLabel();
        jComboBoxServicioFMad = new javax.swing.JComboBox();
        lSoporte = new javax.swing.JLabel();
        jComboBoxSoporte = new javax.swing.JComboBox();
        lEstado3 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        lCerrado = new javax.swing.JLabel();
        jCheckBoxCerrado = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lPoblacionOrigen = new javax.swing.JLabel();
        jTextPoblacionOrigen = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lProvinciaOrigen = new javax.swing.JLabel();
        jComboBoxProvinciaOrigen = new javax.swing.JComboBox();
        lTipoDestino = new javax.swing.JLabel();
        jTextHoraDestino = new javax.swing.JTextField();
        lHoraDestino = new javax.swing.JLabel();
        lFechaDestino = new javax.swing.JLabel();
        jTextDireccionDestino = new javax.swing.JTextField();
        lDireccionDestino = new javax.swing.JLabel();
        lNombreDestino = new javax.swing.JLabel();
        jTextTelefonoDestino = new javax.swing.JTextField();
        lTelefonoDestino = new javax.swing.JLabel();
        lCodPostalDestino = new javax.swing.JLabel();
        jComboBoxProvinciaDestino = new javax.swing.JComboBox();
        lProvinciaDestino = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextPoblacionDestino = new javax.swing.JTextField();
        lPoblacionDestino = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextCodPostalDestino = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jDateFechaDestino = new com.toedter.calendar.JDateChooser();
        jTextNombreDestino = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboTipoDestino = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lHoraRealOrigen = new javax.swing.JLabel();
        jTextHoraRealOrigen = new javax.swing.JTextField();
        jTextHoraRealDestino = new javax.swing.JTextField();
        lHoraRealDestino = new javax.swing.JLabel();
        jTextHoraOrigen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lSEspecial = new javax.swing.JLabel();
        jComboBoxServicioEspecial = new javax.swing.JComboBox();
        jCheckBoxIdaVuelta = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jTextDiasCampa = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lServicioFMad1 = new javax.swing.JLabel();
        jComboBoxServicioFMadDestino = new javax.swing.JComboBox();
        lTarifaCli = new javax.swing.JLabel();
        jTextSuplemento = new javax.swing.JTextField();
        lTarifaEsProv1 = new javax.swing.JLabel();
        lServicioFMad2 = new javax.swing.JLabel();
        lFechaRealDestino = new javax.swing.JLabel();
        jDateFechaRealDestino = new com.toedter.calendar.JDateChooser();
        jLabelNcamion = new javax.swing.JLabel();
        jTextNumCamion = new javax.swing.JTextField();
        jButtonMailCliente = new javax.swing.JButton();
        jButtonMailProveedor = new javax.swing.JButton();

        setForeground(new java.awt.Color(0, 0, 100));

        lDireccionOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lDireccionOrigen.setText("Dirección");
        lDireccionOrigen.setName("lDireccionOrigen"); // NOI18N

        jTextDireccionOrigen.setName("jTextDireccionOrigen"); // NOI18N
        jTextDireccionOrigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDireccionOrigenFocusLost(evt);
            }
        });

        lTelefonoOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lTelefonoOrigen.setText("Móvil");
        lTelefonoOrigen.setName("lTelefonoOrigen"); // NOI18N

        jTextTelefonoOrigen.setName("jTextTelefonoOrigen"); // NOI18N

        lNombreOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lNombreOrigen.setText("Nombre");
        lNombreOrigen.setName("lNombreOrigen"); // NOI18N

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setName("jButtonGuardar"); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        lDescripción.setForeground(new java.awt.Color(0, 0, 100));
        lDescripción.setText("Descripción");
        lDescripción.setName("lDescripción"); // NOI18N

        lCodPostalOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lCodPostalOrigen.setText("Cód. Postal");
        lCodPostalOrigen.setName("lCodPostalOrigen"); // NOI18N

        jTextCodPostalOrigen.setName("jTextCodPostalOrigen"); // NOI18N
        jTextCodPostalOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCodPostalOrigenActionPerformed(evt);
            }
        });

        lNumero.setForeground(new java.awt.Color(0, 0, 100));
        lNumero.setText("Número de pedido PE/");
        lNumero.setName("lNumero"); // NOI18N

        jTextNumero.setEditable(false);
        jTextNumero.setName("jTextNumero"); // NOI18N

        lFecha.setForeground(new java.awt.Color(0, 0, 100));
        lFecha.setText("Fecha");
        lFecha.setName("lFecha"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 0, 100));
        jLabel1.setText("Factor de corrección");
        jLabel1.setName("jLabel1"); // NOI18N

        jComboFactor.setBackground(new java.awt.Color(228, 229, 255));
        jComboFactor.setForeground(new java.awt.Color(0, 0, 100));
        jComboFactor.setName("jComboFactor"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextDescripcion.setName("jTextDescripcion"); // NOI18N
        jTextDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDescripcionFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTextDescripcion);

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

        lProveedor.setForeground(new java.awt.Color(0, 0, 100));
        lProveedor.setText("Proveedor");
        lProveedor.setName("lProveedor"); // NOI18N

        jTextProveedor.setEditable(false);
        jTextProveedor.setName("jTextProveedor"); // NOI18N
        jTextProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextProveedorActionPerformed(evt);
            }
        });

        jToggleButtonProveedor.setText("Buscar Proveedor");
        jToggleButtonProveedor.setName("jToggleButtonProveedor"); // NOI18N
        jToggleButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonProveedorActionPerformed(evt);
            }
        });

        lMatricula.setForeground(new java.awt.Color(0, 0, 100));
        lMatricula.setText("Matrícula");
        lMatricula.setName("lMatricula"); // NOI18N

        jTextMatricula.setName("jTextMatricula"); // NOI18N
        jTextMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextMatriculaFocusLost(evt);
            }
        });

        lMarca.setForeground(new java.awt.Color(0, 0, 100));
        lMarca.setText("Marca");
        lMarca.setName("lMarca"); // NOI18N

        jTextMarca.setName("jTextMarca"); // NOI18N
        jTextMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextMarcaFocusLost(evt);
            }
        });

        lModelo.setForeground(new java.awt.Color(0, 0, 100));
        lModelo.setText("Modelo");
        lModelo.setName("lModelo"); // NOI18N

        jTextModelo.setName("jTextModelo"); // NOI18N
        jTextModelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextModeloFocusLost(evt);
            }
        });

        lSolred.setForeground(new java.awt.Color(0, 0, 100));
        lSolred.setText("Solred");
        lSolred.setName("lSolred"); // NOI18N

        jTextSolred.setName("jTextSolred"); // NOI18N

        lViaje.setForeground(new java.awt.Color(0, 0, 100));
        lViaje.setText("Viaje");
        lViaje.setName("lViaje"); // NOI18N

        jTextViaje.setName("jTextViaje"); // NOI18N

        lOrigen.setFont(new java.awt.Font("Tahoma", 1, 11));
        lOrigen.setForeground(new java.awt.Color(170, 16, 4));
        lOrigen.setText("DATOS DEL ORIGEN");
        lOrigen.setName("lOrigen"); // NOI18N

        lFechaOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lFechaOrigen.setText("Fecha");
        lFechaOrigen.setName("lFechaOrigen"); // NOI18N

        lHoraOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lHoraOrigen.setText("Hora Prevista");
        lHoraOrigen.setName("lHoraOrigen"); // NOI18N

        lTipoOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lTipoOrigen.setText("    Tipo");
        lTipoOrigen.setName("lTipoOrigen"); // NOI18N

        jComboTipoOrigen.setBackground(new java.awt.Color(228, 229, 255));
        jComboTipoOrigen.setForeground(new java.awt.Color(0, 0, 100));
        jComboTipoOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Directivo", "Empleado", "Taller", "Particular", "Otros", " " }));
        jComboTipoOrigen.setName("jComboTipoOrigen"); // NOI18N

        lDestino.setFont(new java.awt.Font("Tahoma", 1, 11));
        lDestino.setForeground(new java.awt.Color(170, 16, 4));
        lDestino.setText("DATOS DEL DESTINO");
        lDestino.setName("lDestino"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        lTarifaEspecial.setFont(new java.awt.Font("Tahoma", 1, 11));
        lTarifaEspecial.setForeground(new java.awt.Color(170, 16, 4));
        lTarifaEspecial.setText("  TARIFA ESPECIAL ");
        lTarifaEspecial.setName("lTarifaEspecial"); // NOI18N

        lTarifaEsProv.setForeground(new java.awt.Color(0, 0, 100));
        lTarifaEsProv.setText("Proveedor");
        lTarifaEsProv.setName("lTarifaEsProv"); // NOI18N

        jTextTaEsProv.setName("jTextTaEsProv"); // NOI18N

        jTextTaEsCli.setName("jTextTaEsCli"); // NOI18N
        jTextTaEsCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTaEsCliActionPerformed(evt);
            }
        });

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        jDateFechaOrigen.setDateFormatString("dd-MM-yyyy");
        jDateFechaOrigen.setName("jDateFechaOrigen"); // NOI18N

        jTextNombreOrigen.setName("jTextNombreOrigen"); // NOI18N
        jTextNombreOrigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreOrigenFocusLost(evt);
            }
        });

        jButtonCancelar1.setForeground(new java.awt.Color(204, 0, 0));
        jButtonCancelar1.setText("Cancelar");
        jButtonCancelar1.setName("jButtonCancelar1"); // NOI18N
        jButtonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar1ActionPerformed(evt);
            }
        });
        jButtonCancelar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonCancelar1KeyPressed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("   * Campos obligatorios");
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("*");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");
        jLabel19.setName("jLabel19"); // NOI18N

        lServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        lServicio.setForeground(new java.awt.Color(170, 16, 4));
        lServicio.setText("SERVICIOS");
        lServicio.setName("lServicio"); // NOI18N

        jComboBoxServicio.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicio.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Urbano", "Interurbano", "Provincial", "Urbano ITV", "Especial" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N

        lServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad.setText("Origen");
        lServicioFMad.setName("lServicioFMad"); // NOI18N

        jComboBoxServicioFMad.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMad.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxServicioFMad.setName("jComboBoxServicioFMad"); // NOI18N

        lSoporte.setForeground(new java.awt.Color(0, 0, 100));
        lSoporte.setText("Soporte");
        lSoporte.setName("lSoporte"); // NOI18N

        jComboBoxSoporte.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxSoporte.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxSoporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Grúa", "Camión completo", "Conductor", "Tren", "Custodia", "Traslado pesado" }));
        jComboBoxSoporte.setName("jComboBoxSoporte"); // NOI18N
        jComboBoxSoporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSoporteActionPerformed(evt);
            }
        });

        lEstado3.setForeground(new java.awt.Color(0, 0, 100));
        lEstado3.setText("   Estado");
        lEstado3.setName("lEstado3"); // NOI18N

        jComboBoxEstado.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxEstado.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "En Proceso", "Entregado", "Facturado", "Cobrado", "Pagado", "Anulado", "Fallido" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N
        jComboBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstadoActionPerformed(evt);
            }
        });

        lCerrado.setForeground(new java.awt.Color(0, 0, 100));
        lCerrado.setText("   Cerrado");
        lCerrado.setName("lCerrado"); // NOI18N

        jCheckBoxCerrado.setName("jCheckBoxCerrado"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");
        jLabel6.setName("jLabel6"); // NOI18N

        lPoblacionOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lPoblacionOrigen.setText("Población");
        lPoblacionOrigen.setName("lPoblacionOrigen"); // NOI18N

        jTextPoblacionOrigen.setName("jTextPoblacionOrigen"); // NOI18N
        jTextPoblacionOrigen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPoblacionOrigenFocusLost(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");
        jLabel12.setName("jLabel12"); // NOI18N

        lProvinciaOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lProvinciaOrigen.setText("Provincia");
        lProvinciaOrigen.setName("lProvinciaOrigen"); // NOI18N

        jComboBoxProvinciaOrigen.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxProvinciaOrigen.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxProvinciaOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxProvinciaOrigen.setName("jComboBoxProvinciaOrigen"); // NOI18N

        lTipoDestino.setForeground(new java.awt.Color(0, 0, 100));
        lTipoDestino.setText("    Tipo");
        lTipoDestino.setName("lTipoDestino"); // NOI18N

        jTextHoraDestino.setName("jTextHoraDestino"); // NOI18N

        lHoraDestino.setForeground(new java.awt.Color(0, 0, 100));
        lHoraDestino.setText("Hora Prevista");
        lHoraDestino.setName("lHoraDestino"); // NOI18N

        lFechaDestino.setForeground(new java.awt.Color(0, 0, 100));
        lFechaDestino.setText("Fecha");
        lFechaDestino.setName("lFechaDestino"); // NOI18N

        jTextDireccionDestino.setName("jTextDireccionDestino"); // NOI18N
        jTextDireccionDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDireccionDestinoFocusLost(evt);
            }
        });

        lDireccionDestino.setForeground(new java.awt.Color(0, 0, 100));
        lDireccionDestino.setText("Dirección");
        lDireccionDestino.setName("lDireccionDestino"); // NOI18N

        lNombreDestino.setForeground(new java.awt.Color(0, 0, 100));
        lNombreDestino.setText("Nombre");
        lNombreDestino.setName("lNombreDestino"); // NOI18N

        jTextTelefonoDestino.setName("jTextTelefonoDestino"); // NOI18N

        lTelefonoDestino.setForeground(new java.awt.Color(0, 0, 100));
        lTelefonoDestino.setText("Móvil");
        lTelefonoDestino.setName("lTelefonoDestino"); // NOI18N

        lCodPostalDestino.setForeground(new java.awt.Color(0, 0, 100));
        lCodPostalDestino.setText("Cód. Postal");
        lCodPostalDestino.setName("lCodPostalDestino"); // NOI18N

        jComboBoxProvinciaDestino.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxProvinciaDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxProvinciaDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxProvinciaDestino.setName("jComboBoxProvinciaDestino"); // NOI18N

        lProvinciaDestino.setForeground(new java.awt.Color(0, 0, 100));
        lProvinciaDestino.setText("Provincia");
        lProvinciaDestino.setName("lProvinciaDestino"); // NOI18N

        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");
        jLabel13.setName("jLabel13"); // NOI18N

        jTextPoblacionDestino.setName("jTextPoblacionDestino"); // NOI18N
        jTextPoblacionDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPoblacionDestinoFocusLost(evt);
            }
        });

        lPoblacionDestino.setForeground(new java.awt.Color(0, 0, 100));
        lPoblacionDestino.setText("Población");
        lPoblacionDestino.setName("lPoblacionDestino"); // NOI18N

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");
        jLabel7.setName("jLabel7"); // NOI18N

        jTextCodPostalDestino.setName("jTextCodPostalDestino"); // NOI18N
        jTextCodPostalDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCodPostalDestinoActionPerformed(evt);
            }
        });

        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("*");
        jLabel20.setName("jLabel20"); // NOI18N

        jDateFechaDestino.setDateFormatString("dd-MM-yyyy");
        jDateFechaDestino.setName("jDateFechaDestino"); // NOI18N

        jTextNombreDestino.setName("jTextNombreDestino"); // NOI18N
        jTextNombreDestino.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreDestinoFocusLost(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("*");
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("*");
        jLabel11.setName("jLabel11"); // NOI18N

        jComboTipoDestino.setBackground(new java.awt.Color(228, 229, 255));
        jComboTipoDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboTipoDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Directivo", "Empleado", "Taller", "Particular", "Otros", " " }));
        jComboTipoDestino.setName("jComboTipoDestino"); // NOI18N

        jLabel23.setForeground(new java.awt.Color(204, 0, 0));
        jLabel23.setText("*");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setForeground(new java.awt.Color(204, 0, 0));
        jLabel24.setText("*");
        jLabel24.setName("jLabel24"); // NOI18N

        lHoraRealOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lHoraRealOrigen.setText("Hora Real Origen");
        lHoraRealOrigen.setName("lHoraRealOrigen"); // NOI18N

        jTextHoraRealOrigen.setName("jTextHoraRealOrigen"); // NOI18N
        jTextHoraRealOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHoraRealOrigenActionPerformed(evt);
            }
        });

        jTextHoraRealDestino.setName("jTextHoraRealDestino"); // NOI18N
        jTextHoraRealDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextHoraRealDestinoActionPerformed(evt);
            }
        });

        lHoraRealDestino.setForeground(new java.awt.Color(0, 0, 100));
        lHoraRealDestino.setText("Hora Real Destino");
        lHoraRealDestino.setName("lHoraRealDestino"); // NOI18N

        jTextHoraOrigen.setName("jTextHoraOrigen"); // NOI18N

        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("*");
        jLabel14.setName("jLabel14"); // NOI18N

        lSEspecial.setForeground(new java.awt.Color(0, 0, 100));
        lSEspecial.setText("Especial");
        lSEspecial.setName("lSEspecial"); // NOI18N

        jComboBoxServicioEspecial.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioEspecial.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioEspecial.setMaximumRowCount(20);
        jComboBoxServicioEspecial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Urgente", "ITV Conductor", "Pre_ITV", "ITV Grúa", "Peritación", "Mano obra Mecánica/Chapa", "Chequeo", "Repostaje", "", "LAVADO DOMICILIO", "LD Exterior", "LD Interior y Exterior", "LD Integral", "LD Interior/Exterior 4x4", "LD Integral 4x4", "LD Interior/Exterior Industrial", "LD Integral Industrial", "", "LAVADO CAMPA", "LC Exterior", "LC Interior y Exterior", "LC Integral", "LC Interior/Exterior 4x4", "LC Integral 4x4", "LC Interior/Exterior Industrial", "LC Integral Industrial", "", "Desrotular pegatinas fácil", "Desrotular pegatinas normal", "Desrotular pegatinas difícil", "Rotular pegatinas fácil", "Rotular pegatinas normal", "Rotular pegatinas difícil", "Otros" }));
        jComboBoxServicioEspecial.setName("jComboBoxServicioEspecial"); // NOI18N
        jComboBoxServicioEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxServicioEspecialActionPerformed(evt);
            }
        });

        jCheckBoxIdaVuelta.setForeground(new java.awt.Color(0, 0, 100));
        jCheckBoxIdaVuelta.setText(" Ida y Vuelta");
        jCheckBoxIdaVuelta.setName("jCheckBoxIdaVuelta"); // NOI18N

        jLabel15.setForeground(new java.awt.Color(0, 0, 100));
        jLabel15.setText("Días campa");
        jLabel15.setName("jLabel15"); // NOI18N

        jTextDiasCampa.setName("jTextDiasCampa"); // NOI18N

        jSeparator5.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator5.setName("jSeparator5"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        jSeparator8.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator8.setName("jSeparator8"); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        lServicioFMad1.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad1.setText("Destino");
        lServicioFMad1.setName("lServicioFMad1"); // NOI18N

        jComboBoxServicioFMadDestino.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicioFMadDestino.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicioFMadDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxServicioFMadDestino.setName("jComboBoxServicioFMadDestino"); // NOI18N

        lTarifaCli.setForeground(new java.awt.Color(0, 0, 100));
        lTarifaCli.setText("Cliente");
        lTarifaCli.setName("lTarifaCli"); // NOI18N

        jTextSuplemento.setName("jTextSuplemento"); // NOI18N

        lTarifaEsProv1.setForeground(new java.awt.Color(0, 0, 100));
        lTarifaEsProv1.setText("Suplemento");
        lTarifaEsProv1.setName("lTarifaEsProv1"); // NOI18N

        lServicioFMad2.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad2.setText("Provincial");
        lServicioFMad2.setName("lServicioFMad2"); // NOI18N

        lFechaRealDestino.setForeground(new java.awt.Color(0, 0, 100));
        lFechaRealDestino.setText("Fecha Real Destino");
        lFechaRealDestino.setName("lFechaRealDestino"); // NOI18N

        jDateFechaRealDestino.setDateFormatString("dd-MM-yyyy");
        jDateFechaRealDestino.setName("jDateFechaRealDestino"); // NOI18N

        jLabelNcamion.setForeground(new java.awt.Color(0, 0, 100));
        jLabelNcamion.setText("N.º en Camión");
        jLabelNcamion.setName("jLabelNcamion"); // NOI18N

        jTextNumCamion.setEnabled(false);
        jTextNumCamion.setName("jTextNumCamion"); // NOI18N

        jButtonMailCliente.setText("Mail Cliente");
        jButtonMailCliente.setName("jButtonMailCliente"); // NOI18N
        jButtonMailCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMailClienteActionPerformed(evt);
            }
        });

        jButtonMailProveedor.setText("Mail Proveedor");
        jButtonMailProveedor.setName("jButtonMailProveedor"); // NOI18N
        jButtonMailProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMailProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lEstado3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lHoraRealOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextHoraRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lFechaRealDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFechaRealDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(lHoraRealDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextHoraRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lSolred)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextSolred, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lViaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lOrigen)
                .addContainerGap(882, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lDireccionOrigen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextDireccionOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPoblacionOrigen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextPoblacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lCodPostalOrigen))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jComboTipoOrigen, 0, 93, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lNombreOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNombreOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
                                .addGap(2, 2, 2)
                                .addComponent(lTelefonoOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addGap(3, 3, 3)
                                .addComponent(jTextTelefonoOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
                                .addGap(4, 4, 4)
                                .addComponent(lFechaOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lHoraOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextCodPostalOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(jTextHoraOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lTipoOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addGap(944, 944, 944)))
                .addGap(7, 7, 7))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDestino)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lDireccionDestino)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lPoblacionDestino)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextPoblacionDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel13)
                                                .addGap(2, 2, 2)
                                                .addComponent(lProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBoxProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(jComboTipoDestino, 0, 94, Short.MAX_VALUE)
                                                .addGap(19, 19, 19)
                                                .addComponent(lNombreDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextNombreDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lTelefonoDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextTelefonoDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lFechaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateFechaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                                .addGap(14, 14, 14))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lTipoDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                        .addGap(821, 821, 821)))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lHoraDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lCodPostalDestino)
                                        .addGap(4, 4, 4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextCodPostalDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                    .addComponent(jTextHoraDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lCerrado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBoxCerrado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonMailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonMailProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lServicio)
                                .addGap(18, 18, 18)
                                .addComponent(lServicioFMad2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lServicioFMad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lServicioFMad1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lSEspecial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxServicioEspecial, 0, 206, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextDiasCampa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxIdaVuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lMatricula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lModelo)
                                .addGap(10, 10, 10)
                                .addComponent(jTextModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lSoporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelNcamion, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNumCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(lProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jToggleButtonProveedor)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lTarifaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lTarifaCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTaEsCli, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lTarifaEsProv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTaEsProv, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(lTarifaEsProv1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lDescripción)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lFecha)
                            .addComponent(jLabel3)
                            .addComponent(lNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lCliente)
                        .addComponent(jLabel2)
                        .addComponent(jToggleButtonCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextDireccionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lDireccionOrigen)
                        .addComponent(jTextCodPostalOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(lCodPostalOrigen)
                        .addComponent(jComboBoxProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(lProvinciaOrigen)
                        .addComponent(jLabel6)
                        .addComponent(lPoblacionOrigen)
                        .addComponent(jTextPoblacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateFechaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lTipoOrigen)
                                .addComponent(jComboTipoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lNombreOrigen)
                                .addComponent(jTextNombreOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lTelefonoOrigen)
                                .addComponent(jLabel8)
                                .addComponent(jTextTelefonoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lFechaOrigen)
                                .addComponent(jLabel19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextHoraOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lHoraOrigen)))
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lDireccionDestino)
                                .addComponent(jTextCodPostalDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextPoblacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(lCodPostalDestino)
                                .addComponent(lPoblacionDestino)
                                .addComponent(jLabel7)
                                .addComponent(lProvinciaDestino)
                                .addComponent(jLabel13)
                                .addComponent(jComboBoxProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lTipoDestino)
                                .addComponent(lNombreDestino)
                                .addComponent(jTextNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lTelefonoDestino)
                                .addComponent(jTextTelefonoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(lFechaDestino)
                                .addComponent(jLabel14))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextHoraDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lHoraDestino))
                            .addComponent(jDateFechaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextDiasCampa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(lServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMad2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jComboBoxServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxServicioFMadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lServicioFMad1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lSEspecial))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBoxIdaVuelta)
                                .addComponent(lMarca)
                                .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(lMatricula))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lModelo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jToggleButtonProveedor))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lSoporte)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lProveedor)
                                .addComponent(jTextNumCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelNcamion))
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTarifaEspecial)
                                    .addComponent(jTextTaEsCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lTarifaEsProv)
                                    .addComponent(jTextTaEsProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lDescripción)
                                    .addComponent(jTextSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lTarifaEsProv1)
                                    .addComponent(lTarifaCli)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lEstado3)
                                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lViaje)
                                    .addComponent(jTextViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextSolred, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lSolred)
                                    .addComponent(jTextHoraRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lHoraRealOrigen)
                                    .addComponent(lFechaRealDestino)
                                    .addComponent(jTextHoraRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lHoraRealDestino)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateFechaRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonCancelar1)
                                    .addComponent(jButtonGuardar)
                                    .addComponent(jButtonMailCliente)
                                    .addComponent(jButtonMailProveedor)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lCerrado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBoxCerrado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        System.out.println("\njButtonGuardar_actionPerformed(ActionEvent e) called.");

        //VARIABLES A UTILIZAR
        int cerradoN=0;
        int idaVueltaN=0;
        int diasCampaN=0;
        String fecha2="";
        String fechaOrigen="";
        String fechaDestino="";
        String fechaRealDestino="";
        double solredN=0;
        double viajeN=0;
        double taescliN=-1;
        double taesproN=0;
        double suplementoN = 0;
        int comparacion1 = -1;
        int comparacion2 = -1;
        int comparacion3 = -1;
        
        // CONVERSION DE LA FECHA
        Calendar fechaCalendarFecha = jDateFecha.getCalendar();
        if (fechaCalendarFecha!=null)
        {
            Date fecha = fechaCalendarFecha.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fecha2=formatoDeFecha.format(fecha);
        }

        // CONVERSION DE LA FECHA ORIGEN
        Calendar fechaCalendarOrigen = jDateFechaOrigen.getCalendar();
        if (fechaCalendarOrigen!=null)
        {
            Date fecha = fechaCalendarOrigen.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaOrigen=formatoDeFecha.format(fecha);
        }
        
        // CONVERSION DE LA FECHA DESTINO
        Calendar fechaCalendarDestino = jDateFechaDestino.getCalendar();
        if (fechaCalendarDestino!=null)
        {
            Date fecha = fechaCalendarDestino.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaDestino=formatoDeFecha.format(fecha);
        }

        // CONVERSION DE LA FECHA REAL DESTINO
        Calendar fechaCalendarRealDestino = jDateFechaRealDestino.getCalendar();
        if (fechaCalendarRealDestino!=null)
        {
            Date fecha = fechaCalendarRealDestino.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaRealDestino=formatoDeFecha.format(fecha);
        }
        // SINO LE PONEMOS NOSOTROS UNA FECHA REAL DESTINO
        else
        {
            fechaRealDestino="2050-01-01";
        }

        // SE RECOGEN LOS VALORES DE LOS CAMPOS
        String descripcion = new String(jTextDescripcion.getText());
        int factor = new Integer(jComboFactor.getSelectedIndex());
        String cliente= new String(jTextCliente.getText());
        String proveedor= new String(jTextProveedor.getText());
        String matricula=new String(jTextMatricula.getText());
        String marca=new String(jTextMarca.getText());
        String modelo=new String(jTextModelo.getText());
        String solred=new String(jTextSolred.getText());
        if(!solred.equals(""))
        {
            solredN=Double.valueOf(solred).doubleValue();
        }
        String viaje=new String(jTextViaje.getText());
        if(!viaje.equals(""))
        {
            viajeN=Double.valueOf(viaje).doubleValue();
        }
        String tarifaCliente=new String(jTextTaEsCli.getText());
        if(!tarifaCliente.equals(""))
        {
            taescliN=Double.valueOf(tarifaCliente).doubleValue();
        }
        String tarifaProveedor=new String(jTextTaEsProv.getText());
        if(!tarifaProveedor.equals(""))
        {
            taesproN=Double.valueOf(tarifaProveedor).doubleValue();
        }
        String direccionOrigen=new String(jTextDireccionOrigen.getText());
        String poblacionOrigen=new String(jTextPoblacionOrigen.getText());
        String provinciaOrigen=new String(jComboBoxProvinciaOrigen.getSelectedItem().toString());
        String codigoPOrigen=new String(jTextCodPostalOrigen.getText());
        String horaOrigen = new String(jTextHoraOrigen.getText());
        String tipoOrigen=new String(jComboTipoOrigen.getSelectedItem().toString());
        String nombreOrigen=new String(jTextNombreOrigen.getText());
        String telefonoOrigen=new String(jTextTelefonoOrigen.getText());
        String direccionDestino=new String(jTextDireccionDestino.getText());
        String poblacionDestino=new String(jTextPoblacionDestino.getText());
        String provinciaDestino=new String(jComboBoxProvinciaDestino.getSelectedItem().toString());
        String codigoPDestino=new String(jTextCodPostalDestino.getText());
        String horaDestino = new String(jTextHoraDestino.getText());
        String tipoDestino=new String(jComboTipoDestino.getSelectedItem().toString());
        String nombreDestino=new String(jTextNombreDestino.getText());
        String telefonoDestino=new String(jTextTelefonoDestino.getText());
        String servicio=new String(jComboBoxServicio.getSelectedItem().toString());
        String servicioFMad=new String(jComboBoxServicioFMad.getSelectedItem().toString());
        String servicioFMadDestino=new String(jComboBoxServicioFMadDestino.getSelectedItem().toString());
        String servicioEspecial=new String(jComboBoxServicioEspecial.getSelectedItem().toString());
        String soporte=new String(jComboBoxSoporte.getSelectedItem().toString());
        String suplemento=new String(jTextSuplemento.getText().toString());
        String numEnCamion = new String(jTextNumCamion.getText());

        if (numEnCamion.equals(""))
        {
            numEnCamion = "0";
        }

        if(!suplemento.equals(""))
        {
            suplementoN=Double.valueOf(suplemento).doubleValue();
        }
        String diasCampa = new String(jTextDiasCampa.getText());
        if(!diasCampa.equals(""))
        {
            diasCampaN=Integer.valueOf(diasCampa).intValue();
        }
        else
            diasCampaN=0;
        boolean idaVuelta = new Boolean(jCheckBoxIdaVuelta.isSelected());
        String horaRealOrigen = new String(jTextHoraRealOrigen.getText());
        String horaRealDestino = new String(jTextHoraRealDestino.getText());
        String estado=new String(jComboBoxEstado.getSelectedItem().toString());
        boolean cerrado = new Boolean(jCheckBoxCerrado.isSelected());

        if(tipoOrigen.equals("Selecciona"))
            tipoOrigen="";

        if(tipoDestino.equals("Selecciona"))
            tipoDestino="";

        if(servicio.equals("Selecciona"))
            servicio="";

        if(servicioFMad.equals("Selecciona"))
            servicioFMad="";
        
        if(servicioFMadDestino.equals("Selecciona"))
            servicioFMadDestino="";
        
        if(soporte.equals("Selecciona"))
            soporte="";

        if(servicioEspecial.equals("Selecciona"))
            servicioEspecial="";

        if(soporte.equals("Selecciona"))
            soporte="";

        if((fechaCalendarOrigen != null) && (fechaCalendarDestino != null))
        {
            comparacion1=fechaCalendarFecha.compareTo(fechaCalendarOrigen);
            comparacion2=fechaCalendarOrigen.compareTo(fechaCalendarDestino);           
        }
        if (fechaCalendarRealDestino != null)
        {
             comparacion3=fechaCalendarRealDestino.compareTo(fechaCalendarOrigen);
        }
        
        if (!Utilidades.campoObligatorio(fecha2,"Fecha").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fecha2,"Fecha"));
        }
        else if (!Utilidades.campoObligatorio(servicio,"Servicio").equals("OK") && 
                 !Utilidades.campoObligatorio(servicioFMad,"ServicioFMad").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(servicio,"servicio"));
        }
        else if (!Utilidades.campoObligatorio(soporte,"Soporte").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(soporte,"Soporte"));
        }
        else if (!Utilidades.campoObligatorio(cliente,"Cliente").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(cliente,"Cliente"));
        }
        else if (!Utilidades.campoObligatorio(direccionOrigen,"Direccion de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(direccionOrigen,"Direccion de Origen"));
        }
        else if (!Utilidades.campoObligatorio(poblacionOrigen,"Poblacion de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(poblacionOrigen,"Poblacion de Origen"));
        }
        else if (!Utilidades.campoObligatorioCombo(provinciaOrigen,"Provincia de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorioCombo(provinciaOrigen,"Provincia de Origen"));
        }
        else if (!Utilidades.campoObligatorio(codigoPOrigen,"Código Postal de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(codigoPOrigen,"Código Postal de Origen"));
        }       
         else if (!Utilidades.campoObligatorio(telefonoOrigen,"Teléfono de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(telefonoOrigen,"Teléfono de Origen"));
        }
         else if (!Utilidades.campoObligatorio(fechaOrigen,"Fecha de Origen").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fechaOrigen,"Fecha de Origen"));
        }                        
         else if (!Utilidades.campoObligatorio(direccionDestino,"Direccion de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(direccionDestino,"Direccion de Destino"));
        }
          else if (!Utilidades.campoObligatorio(poblacionDestino,"Poblacion de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(poblacionDestino,"Poblacion de Destino"));
        }
          else if (!Utilidades.campoObligatorioCombo(provinciaDestino,"Provincia de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorioCombo(provinciaDestino,"Provincia de Destino"));
        }
        else if (!Utilidades.campoObligatorio(codigoPDestino,"Código Postal de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(codigoPDestino,"Código Postal de Destino"));
        }        
         else if (!Utilidades.campoObligatorio(telefonoDestino,"Teléfono de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(telefonoDestino,"Teléfono de Destino"));
        }
         else if (!Utilidades.campoObligatorio(fechaDestino,"Fecha de Destino").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fechaDestino,"Fecha de Destino"));
        }                      
        else if (!Utilidades.campoObligatorio(matricula,"Matricula").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(matricula,"Matricula"));
        }       
        else if(!Utilidades.formatoCodPostal(codigoPOrigen).equals("OK"))
        {
             ValidarFormatos(Utilidades.formatoCodPostal(codigoPOrigen));
        }
        else if(!Utilidades.formatoTelefono9(telefonoOrigen).equals("OK"))
        {
             ValidarFormatos(Utilidades.formatoTelefono9(telefonoOrigen));
        }
        else if(!Utilidades.formatoCodPostal(codigoPDestino).equals("OK"))
        {
             ValidarFormatos(Utilidades.formatoCodPostal(codigoPDestino));
        }
        else if(!Utilidades.formatoTelefono9(telefonoDestino).equals("OK"))
        {
             ValidarFormatos(Utilidades.formatoTelefono9(telefonoDestino));
        }
        else if(comparacion1 > 0)
        {
            ValidarFormatos("Fecha Pedido tiene que ser <= Fecha Prevista Recogida");
        }
        else if(comparacion2 > 0)
        {
            ValidarFormatos("Fecha Prevista Recogida tiene que ser <= Fecha Prevista Entrega");
        }
        else if(comparacion3 < 0)
        {
            ValidarFormatos("Fecha Real Entrega tiene que ser >= Fecha Prevista Recogida");
        }
        else
        {
              if(!cerrado)
                  cerradoN=0;
              else
                  cerradoN=1;
               if(!idaVuelta)
                  idaVueltaN=0;
              else
                  idaVueltaN=1;

              String query = "INSERT INTO pe_pedidos (pe_fecha, pe_descripcion, pe_direccion_origen, pe_poblacion_origen, " +
                                                         "pe_provincia_origen, pe_cp_origen, pe_fecha_origen, " +
                                                         "pe_hora_origen,pe_tipo_origen,pe_nombre_origen, pe_telefono_origen, " +
                                                         "pe_direccion_destino, pe_poblacion_destino, pe_provincia_destino, " +
                                                         "pe_cp_destino, pe_fecha_destino, pe_hora_destino, pe_tipo_destino, " +
                                                         "pe_nombre_destino,pe_telefono_destino, pe_servicio, " +
                                                         "pe_servicio_origen, pe_servicio_destino, pe_servicio_especial, " +
                                                         "pe_dias_campa,pe_ida_vuelta,pe_soporte, pe_ve_matricula,pe_ve_marca, " +
                                                         "pe_ve_modelo,pe_hora_real_origen,pe_fecha_real_destino,pe_hora_real_destino, " +
                                                         "pe_solred, pe_viaje,pe_ta_es_cliente,pe_ta_es_proveedor,pe_suplemento, " +
                                                         "fc_id, pe_estado,pe_activo,pe_num_en_camion) VALUES (";

              query = query + "'"+fecha2+"','"+descripcion+"','"+direccionOrigen+"','"+poblacionOrigen+"','"+provinciaOrigen+"','"+codigoPOrigen+"','"+fechaOrigen+"','"+horaOrigen+"'";
              query = query + " ,'"+tipoOrigen+"','"+nombreOrigen+"','"+telefonoOrigen+"','"+direccionDestino+"','"+poblacionDestino+"','"+provinciaDestino+"','"+codigoPDestino+"'";
              query = query + ", '"+fechaDestino+"','"+horaDestino+"','"+tipoDestino+"','"+nombreDestino+"','"+telefonoDestino+"'";
              query = query + " ,'"+servicio+"','"+servicioFMad+"','"+servicioFMadDestino+"','"+servicioEspecial+"','"+diasCampaN+"','"+idaVueltaN+"','"+soporte+"','"+matricula+"'";
              query = query + ",'"+marca+"','"+modelo+"','"+horaRealOrigen+"','"+fechaRealDestino+"','"+horaRealDestino+"','"+solredN+"','"+viajeN+"'";
              query = query + " ,'"+taescliN+"','"+taesproN+"','"+suplementoN+"','"+factor+"','"+estado+"','"+cerradoN+"','"+numEnCamion+"')";


               System.out.println(query);
                boolean rs3 = CSDesktop.datos.manipuladorDatos(query);
                System.out.println(rs3);
                if(rs3)
                {
                    jButtonGuardar.setEnabled(false);
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);
                    jButtonGuardar.setEnabled(true);

                }
                else
                {
                    Cliente cliente2=new Cliente();
                    clienteID=cliente2.getClienteID(cliente);
                    Proveedor proveedor2=new Proveedor();
                    proveedorID=proveedor2.getProveedorID(proveedor);
                    query = "select distinct last_insert_id() from pe_pedidos";
                    String pe_num="";
                    ResultSet rs2 = CSDesktop.datos.select(query);
                    try {
                           if (rs2.first())
                            {
                                pe_num=Integer.valueOf(rs2.getInt("last_insert_id()")).toString();
                                //System.out.println(rs2.getInt("last_insert_id()"));
                                String queryCon = "INSERT INTO pc_pedidos_clientes (pe_num,cl_id) "+
                                                  "VALUES ('"+pe_num+"','"+clienteID+"')";
                                boolean rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                                if(rsCon)
                                {
                                    jButtonGuardar.setEnabled(false);
                                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                    JOptionPane.showMessageDialog(null,errorFields);
                                    jButtonGuardar.setEnabled(true);
                                }
                                queryCon = "INSERT INTO pp_pedidos_proveedores (pe_num,pr_id) "+
                                                  "VALUES ('"+pe_num+"','"+proveedorID+"')";
                                rsCon = CSDesktop.datos.manipuladorDatos(queryCon);
                                if(rsCon)
                                {
                                    jButtonGuardar.setEnabled(false);
                                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                                    JOptionPane.showMessageDialog(null,errorFields);
                                    jButtonGuardar.setEnabled(true);
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(CSAnyadirCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    // MANDAR MAIL AL CLIENTE
                    if (estado.equals("En Proceso") || (estado.equals("Entregado")))
                    {
                        String mails="\n";
                        for(int i=0;i<CSDesktop.mailCliente.size();i++)
                        {
                            mails=mails + CSDesktop.mailCliente.get(i);
                            if(i!=CSDesktop.mailCliente.size()-1)
                            {
                                mails=mails + "\n";
                            }
                        }

                        int seleccion = JOptionPane.showOptionDialog(
                        CSAnyadirPedido.this,
                        "¿Quieres mandar un mail al cliente " + mails + "?",
                        "Atención",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,    // null para icono por defecto.
                        new Object[] { "SI", "NO"},   // null para YES, NO y CANCEL
                        "SI");

                        if(seleccion == 0)
                        {
                            BeanCorreoCliente mail= new BeanCorreoCliente();

                            //Para calcular la fecha
                            Date fechaHoy = new Date(System.currentTimeMillis());
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                            String fechaHoy2=formatoDeFecha.format(fechaHoy);

                            //Para el numero de pedido
                            String numPedido=Utilidades.rellenarCeros(pe_num,5);
                            String pedido=numPedido+"/"+fecha2.substring(2, 4);


                            mail.setCliente(cliente);
                            mail.setFecha(fechaHoy2);
                            mail.setNumPedido(pedido);
                            mail.setSoporte(soporte);
                            mail.setFechaEntrega(fechaDestino);
                            mail.setFechaRecogida(fechaOrigen);
                            mail.setMarca(marca);
                            mail.setModelo(modelo);
                            mail.setMatricula(matricula);
                            mail.setDireccionOrigen(direccionOrigen);
                            mail.setPoblacionOrigen(poblacionOrigen);
                            mail.setProvinciaOrigen(provinciaOrigen);
                            mail.setNombreOrigen(nombreOrigen);
                            mail.setTelefonoOrigen(telefonoOrigen);
                            mail.setDireccionDestino(direccionDestino);
                            mail.setPoblacionDestino(poblacionDestino);
                            mail.setProvinciaDestino(provinciaDestino);
                            mail.setNombreDestino(nombreDestino);
                            mail.setTelefonoDestino(telefonoDestino);
                            if (diasCampa.equals(""))
                                diasCampa="0";
                            mail.setDiasCampa(diasCampa);
                            mail.setNumero(pe_num);
                            Cliente client = new Cliente();
                            mail.setClienteID(String.valueOf(client.getClienteID(cliente)));

                            if(estado.equals("En Proceso"))
                            {
                                 for(int i=0;i<CSDesktop.mailCliente.size();i++)
                                 {
                                    CSEnviarMailProceso.main(mail,CSDesktop.mailCliente.get(i).toString(),CSDesktop.nombreCliente.get(i).toString());
                                 }
                            }
                            else if (estado.equals("Entregado"))
                            {
                                 for(int i=0;i<CSDesktop.mailCliente.size();i++)
                                 {
                                    CSEnviarMailEntregado.main(mail,CSDesktop.mailCliente.get(i).toString(),CSDesktop.nombreCliente.get(i).toString());
                                 }
                            }
                        }
                        if(estado.equals("En Proceso"))
                        {
                            String mailsP="\n";
                            for(int i=0;i<CSDesktop.mailProveedor.size();i++)
                            {
                                mailsP=mailsP + CSDesktop.mailProveedor.get(i);
                                if(i!=CSDesktop.mailProveedor.size()-1)
                                {
                                    mailsP=mailsP + "\n";
                                }
                            }
                           int seleccion2 = JOptionPane.showOptionDialog(
                                CSAnyadirPedido.this,
                        "¿Quieres mandar un mail al proveedor " + mailsP + "?",
                        "Atención",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,    // null para icono por defecto.
                        new Object[] { "SI", "NO"},   // null para YES, NO y CANCEL
                        "SI");

                        if(seleccion2 == 0)
                        {
                            BeanCorreoCliente mail= new BeanCorreoCliente();

                            //Para calcular la fecha
                            Date fechaHoy = new Date(System.currentTimeMillis());
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                            String fechaHoy2=formatoDeFecha.format(fechaHoy);

                            //Para el numero de pedido
                            String numPedido=Utilidades.rellenarCeros(pe_num,5);
                            String pedido=numPedido+"/"+fecha2.substring(2, 4);


                            mail.setCliente(proveedor);
                            mail.setFecha(fechaHoy2);
                            mail.setNumPedido(pedido);
                            mail.setSoporte(soporte);
                            mail.setFechaEntrega(fechaOrigen);
                            mail.setFechaRecogida(fechaDestino);
                            mail.setMarca(marca);
                            mail.setModelo(modelo);
                            mail.setMatricula(matricula);
                            mail.setDireccionOrigen(direccionOrigen);
                            mail.setPoblacionOrigen(poblacionOrigen);
                            mail.setProvinciaOrigen(provinciaOrigen);
                            mail.setNombreOrigen(nombreOrigen);
                            mail.setTelefonoOrigen(telefonoOrigen);
                            mail.setDireccionDestino(direccionDestino);
                            mail.setPoblacionDestino(poblacionDestino);
                            mail.setProvinciaDestino(provinciaDestino);
                            mail.setNombreDestino(nombreDestino);
                            mail.setTelefonoDestino(telefonoDestino);
                            mail.setNumero(pe_num);
                             if (diasCampa.equals(""))
                                diasCampa="0";
                            mail.setDiasCampa(diasCampa);
                            Proveedor proveed = new Proveedor();
                            mail.setClienteID(String.valueOf(proveed.getProveedorID(proveedor)));

                            for(int i=0;i<CSDesktop.mailProveedor.size();i++)
                            {
                                CSEnviarMailProveedor.main(mail,CSDesktop.mailProveedor.get(i).toString(),CSDesktop.nombreProveedor.get(i).toString());
                            }
                             }

                        }
                    }

                    int confirmado = JOptionPane.showConfirmDialog(this,"Los datos se han guardado correctamente.¿Quieres introducir más pedidos para este cliente?");

                    if (JOptionPane.OK_OPTION == confirmado)
                    {
                        jTextMatricula.setText("");
                        jTextMarca.setText("");
                        jTextModelo.setText("");
                        jComboFactor.setSelectedItem("Ninguno");
                    }
                    else
                    {
                        CSDesktop.NuevoPedido.dispose();
                        CSDesktop.menuNuevoPedido.setEnabled(true);
                    }
                }
        }

    }//GEN-LAST:event_jButtonGuardarActionPerformed

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

    private void jToggleButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonProveedorActionPerformed
        System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");
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

    private void jTextTaEsCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTaEsCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTaEsCliActionPerformed

    private void jButtonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar1ActionPerformed
        {
            CSDesktop.NuevoPedido.dispose();
            CSDesktop.menuNuevoPedido.setEnabled(true);      
        }
}//GEN-LAST:event_jButtonCancelar1ActionPerformed

    private void jButtonCancelar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCancelar1KeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_jButtonCancelar1KeyPressed

    private void jComboBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEstadoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jComboBoxEstadoActionPerformed

    private void jTextCodPostalOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCodPostalOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCodPostalOrigenActionPerformed

    private void jTextProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextProveedorActionPerformed

    private void jTextCodPostalDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCodPostalDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCodPostalDestinoActionPerformed

    private void jTextHoraRealOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHoraRealOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHoraRealOrigenActionPerformed

    private void jTextHoraRealDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextHoraRealDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextHoraRealDestinoActionPerformed

    private void jTextDireccionOrigenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDireccionOrigenFocusLost
         String DireccionOrigenM = jTextDireccionOrigen.getText().toUpperCase();
       jTextDireccionOrigen.setText(DireccionOrigenM);
    }//GEN-LAST:event_jTextDireccionOrigenFocusLost

    private void jTextPoblacionOrigenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPoblacionOrigenFocusLost
        String PoblacionOrigenM = jTextPoblacionOrigen.getText().toUpperCase();
       jTextPoblacionOrigen.setText(PoblacionOrigenM);
    }//GEN-LAST:event_jTextPoblacionOrigenFocusLost

    private void jTextNombreOrigenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreOrigenFocusLost
         String NombreOrigenM = jTextNombreOrigen.getText().toUpperCase();
       jTextNombreOrigen.setText(NombreOrigenM);
    }//GEN-LAST:event_jTextNombreOrigenFocusLost

    private void jTextDireccionDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDireccionDestinoFocusLost
        String DireccionDestinoM = jTextDireccionDestino.getText().toUpperCase();
       jTextDireccionDestino.setText(DireccionDestinoM);
    }//GEN-LAST:event_jTextDireccionDestinoFocusLost

    private void jTextPoblacionDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPoblacionDestinoFocusLost
        String PoblacionDestinoM = jTextPoblacionDestino.getText().toUpperCase();
       jTextPoblacionDestino.setText(PoblacionDestinoM);
    }//GEN-LAST:event_jTextPoblacionDestinoFocusLost

    private void jTextNombreDestinoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreDestinoFocusLost
        String NombreDestinoM = jTextNombreDestino.getText().toUpperCase();
       jTextNombreDestino.setText(NombreDestinoM);
    }//GEN-LAST:event_jTextNombreDestinoFocusLost

    private void jTextMatriculaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMatriculaFocusLost
         String MatriculaM = jTextMatricula.getText().toUpperCase();
       jTextMatricula.setText(MatriculaM);
    }//GEN-LAST:event_jTextMatriculaFocusLost

    private void jTextMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMarcaFocusLost
        String MarcaM = jTextMarca.getText().toUpperCase();
       jTextMarca.setText(MarcaM);
    }//GEN-LAST:event_jTextMarcaFocusLost

    private void jTextModeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextModeloFocusLost
       String ModeloM = jTextModelo.getText().toUpperCase();
       jTextModelo.setText(ModeloM);
    }//GEN-LAST:event_jTextModeloFocusLost

    private void jTextDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDescripcionFocusLost
       String DescripcionM = jTextDescripcion.getText().toUpperCase();
       jTextDescripcion.setText(DescripcionM);
    }//GEN-LAST:event_jTextDescripcionFocusLost

    private void jComboBoxSoporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSoporteActionPerformed
        String soporte = jComboBoxSoporte.getSelectedItem().toString();
        if (soporte.equals("Camión completo"))
        {
            jTextNumCamion.setEnabled(true);
        }
        else
        {
            jTextNumCamion.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxSoporteActionPerformed

    private void jComboBoxServicioEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxServicioEspecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxServicioEspecialActionPerformed

    private void jButtonMailClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMailClienteActionPerformed

        Cliente finalCliente=new Cliente();
        String cliente = jTextCliente.getText();
        String query = "SELECT cc_nombre,cc_email FROM cc_contactos_cliente where cl_id ="+ finalCliente.getClienteID(cliente);


        CSDesktop.BuscaMailCliente = new JInternalFrame("Seleccionar Mail Cliente", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectMailCliente panel = new CSSelectMailCliente(query);
        CSDesktop.BuscaMailCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaMailCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaMailCliente );
        CSDesktop.BuscaMailCliente.setLocation(150, 50);
        CSDesktop.BuscaMailCliente.setVisible( true );
}//GEN-LAST:event_jButtonMailClienteActionPerformed

    private void jButtonMailProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMailProveedorActionPerformed

        Proveedor finalProveedor=new Proveedor();
        String proveedor = jTextProveedor.getText();
        String query = "SELECT cp_nombre,cp_email FROM cp_contactos_proveedor where pr_id ="+ finalProveedor.getProveedorID(proveedor);

        CSDesktop.BuscaMailProveedor = new JInternalFrame("Seleccionar Mail Proveedor", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectMailProveedor panel = new CSSelectMailProveedor(query);
        CSDesktop.BuscaMailProveedor.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaMailProveedor.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaMailProveedor );
        CSDesktop.BuscaMailProveedor.setLocation(150, 50);
        CSDesktop.BuscaMailProveedor.setVisible( true );
}//GEN-LAST:event_jButtonMailProveedorActionPerformed
 public Dimension getPreferredSize()
   {
      return new Dimension( 1100,650 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonMailCliente;
    private javax.swing.JButton jButtonMailProveedor;
    private javax.swing.JCheckBox jCheckBoxCerrado;
    private javax.swing.JCheckBox jCheckBoxIdaVuelta;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxProvinciaDestino;
    private javax.swing.JComboBox jComboBoxProvinciaOrigen;
    private javax.swing.JComboBox jComboBoxServicio;
    private javax.swing.JComboBox jComboBoxServicioEspecial;
    private javax.swing.JComboBox jComboBoxServicioFMad;
    private javax.swing.JComboBox jComboBoxServicioFMadDestino;
    private javax.swing.JComboBox jComboBoxSoporte;
    private javax.swing.JComboBox jComboFactor;
    private javax.swing.JComboBox jComboTipoDestino;
    private javax.swing.JComboBox jComboTipoOrigen;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaDestino;
    private com.toedter.calendar.JDateChooser jDateFechaOrigen;
    private com.toedter.calendar.JDateChooser jDateFechaRealDestino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNcamion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextCodPostalDestino;
    private javax.swing.JTextField jTextCodPostalOrigen;
    private javax.swing.JTextPane jTextDescripcion;
    private javax.swing.JTextField jTextDiasCampa;
    private javax.swing.JTextField jTextDireccionDestino;
    private javax.swing.JTextField jTextDireccionOrigen;
    private javax.swing.JTextField jTextHoraDestino;
    private javax.swing.JTextField jTextHoraOrigen;
    private javax.swing.JTextField jTextHoraRealDestino;
    private javax.swing.JTextField jTextHoraRealOrigen;
    private javax.swing.JTextField jTextMarca;
    private javax.swing.JTextField jTextMatricula;
    private javax.swing.JTextField jTextModelo;
    private javax.swing.JTextField jTextNombreDestino;
    private javax.swing.JTextField jTextNombreOrigen;
    private javax.swing.JTextField jTextNumCamion;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextField jTextPoblacionDestino;
    private javax.swing.JTextField jTextPoblacionOrigen;
    private javax.swing.JTextField jTextProveedor;
    private javax.swing.JTextField jTextSolred;
    private javax.swing.JTextField jTextSuplemento;
    private javax.swing.JTextField jTextTaEsCli;
    private javax.swing.JTextField jTextTaEsProv;
    private javax.swing.JTextField jTextTelefonoDestino;
    private javax.swing.JTextField jTextTelefonoOrigen;
    private javax.swing.JTextField jTextViaje;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JToggleButton jToggleButtonProveedor;
    private javax.swing.JLabel lCerrado;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lCodPostalDestino;
    private javax.swing.JLabel lCodPostalOrigen;
    private javax.swing.JLabel lDescripción;
    private javax.swing.JLabel lDestino;
    private javax.swing.JLabel lDireccionDestino;
    private javax.swing.JLabel lDireccionOrigen;
    private javax.swing.JLabel lEstado3;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lFechaDestino;
    private javax.swing.JLabel lFechaOrigen;
    private javax.swing.JLabel lFechaRealDestino;
    private javax.swing.JLabel lHoraDestino;
    private javax.swing.JLabel lHoraOrigen;
    private javax.swing.JLabel lHoraRealDestino;
    private javax.swing.JLabel lHoraRealOrigen;
    private javax.swing.JLabel lMarca;
    private javax.swing.JLabel lMatricula;
    private javax.swing.JLabel lModelo;
    private javax.swing.JLabel lNombreDestino;
    private javax.swing.JLabel lNombreOrigen;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lOrigen;
    private javax.swing.JLabel lPoblacionDestino;
    private javax.swing.JLabel lPoblacionOrigen;
    private javax.swing.JLabel lProveedor;
    private javax.swing.JLabel lProvinciaDestino;
    private javax.swing.JLabel lProvinciaOrigen;
    private javax.swing.JLabel lSEspecial;
    private javax.swing.JLabel lServicio;
    private javax.swing.JLabel lServicioFMad;
    private javax.swing.JLabel lServicioFMad1;
    private javax.swing.JLabel lServicioFMad2;
    private javax.swing.JLabel lSolred;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lTarifaCli;
    private javax.swing.JLabel lTarifaEsProv;
    private javax.swing.JLabel lTarifaEsProv1;
    private javax.swing.JLabel lTarifaEspecial;
    private javax.swing.JLabel lTelefonoDestino;
    private javax.swing.JLabel lTelefonoOrigen;
    private javax.swing.JLabel lTipoDestino;
    private javax.swing.JLabel lTipoOrigen;
    private javax.swing.JLabel lViaje;
    // End of variables declaration//GEN-END:variables

 public void ValidarFormatos(String accion)
    {
         jButtonGuardar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGuardar.setEnabled(true);
    }

  public String ConvertirFechaString(Calendar FechaCalendar) {
            String fechaString;
            String mes;
            String dia;

            if(FechaCalendar==null)
            {
                fechaString="";
                return fechaString;
            }

            String anio = Integer.toString(FechaCalendar.get(Calendar.YEAR));
            if((FechaCalendar.get(Calendar.MONTH)+1 < 10))
            {
                mes = "0" + Integer.toString(FechaCalendar.get(Calendar.MONTH)+1);
                
            }
            else
            {
                mes = Integer.toString(FechaCalendar.get(Calendar.MONTH)+1);
            }
            if((FechaCalendar.get(Calendar.DAY_OF_MONTH)+1 < 10))
            {
                dia = "0" + Integer.toString(FechaCalendar.get(Calendar.DAY_OF_MONTH));
            }
            else
            {
                dia = Integer.toString(FechaCalendar.get(Calendar.DAY_OF_MONTH));
            }
            fechaString = anio + "-" + mes + "-" + dia;

            return fechaString;
        }

  private void getFactorCorrecion() throws SQLException
        {

        ResultSet rs = CSDesktop.datos.select("SELECT fc_id, fc_nombre FROM fc_factores_correccion");
        int j = 0;
        String valor = "";
        while(rs.next())
        {
            valor = rs.getString("fc_nombre");

            jComboFactor.addItem(valor);
            jComboFactor.setSelectedIndex(0);
            j++;
        }
     }

    private void limitacionesCampos()
    {
       LimitadorDeDocumento limitadorDescripcion= new LimitadorDeDocumento(255);
       jTextDescripcion.setDocument(limitadorDescripcion);
       LimitadorDeDocumento limitadorDireccionOrigen= new LimitadorDeDocumento(255);
       jTextDireccionOrigen.setDocument(limitadorDireccionOrigen);
       LimitadorDeDocumento limitadorPoblacionOrigen= new LimitadorDeDocumento(50);
       jTextPoblacionOrigen.setDocument(limitadorPoblacionOrigen);
       LimitadorDeDocumento limitadorCPOrigen= new LimitadorDeDocumento(5);
       jTextCodPostalOrigen.setDocument(limitadorCPOrigen);
       LimitadorDeDocumento limitadorHoraOrigen= new LimitadorDeDocumento(5);
       jTextHoraOrigen.setDocument(limitadorHoraOrigen);
       LimitadorDeDocumento limitadorNombreOrigen= new LimitadorDeDocumento(50);
       jTextNombreOrigen.setDocument(limitadorNombreOrigen);
       LimitadorDeDocumento limitadorTelefonoOrigen= new LimitadorDeDocumento(15);
       jTextTelefonoOrigen.setDocument(limitadorTelefonoOrigen);

       LimitadorDeDocumento limitadorDireccionDestino= new LimitadorDeDocumento(255);
       jTextDireccionDestino.setDocument(limitadorDireccionDestino);
       LimitadorDeDocumento limitadorPoblacionDestino= new LimitadorDeDocumento(50);
       jTextPoblacionDestino.setDocument(limitadorPoblacionDestino);
       LimitadorDeDocumento limitadorCPDestino= new LimitadorDeDocumento(5);
       jTextCodPostalDestino.setDocument(limitadorCPDestino);
       LimitadorDeDocumento limitadorHoraDestino= new LimitadorDeDocumento(5);
       jTextHoraDestino.setDocument(limitadorHoraDestino);
       LimitadorDeDocumento limitadorNombreDestino= new LimitadorDeDocumento(50);
       jTextNombreDestino.setDocument(limitadorNombreDestino);
       LimitadorDeDocumento limitadorTelefonoDestino= new LimitadorDeDocumento(15);
       jTextTelefonoDestino.setDocument(limitadorTelefonoDestino);

       LimitadorDeDocumento limitadorMatricula= new LimitadorDeDocumento(15);
       jTextMatricula.setDocument(limitadorMatricula);
       LimitadorDeDocumento limitadorMarca= new LimitadorDeDocumento(30);
       jTextMarca.setDocument(limitadorMarca);
       LimitadorDeDocumento limitadorModelo= new LimitadorDeDocumento(30);
       jTextModelo.setDocument(limitadorModelo);
       LimitadorDeDocumento limitadorHoraROrigen= new LimitadorDeDocumento(30);
       jTextHoraRealOrigen.setDocument(limitadorHoraROrigen);
       LimitadorDeDocumento limitadorHoraRDestino= new LimitadorDeDocumento(30);
       jTextHoraRealDestino.setDocument(limitadorHoraRDestino);
    }
}