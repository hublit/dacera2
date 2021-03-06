/*
 * AnyadirProveedores.java
 *
 * Created on 29-sep-2009, 21:04:18
 */

package neg;

import data.BeanCliente;
import data.BeanCorreoCliente;
import data.BeanPedido;
import java.text.ParseException;
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
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.FileChooser;


public class CSAnyadirPedidoNew extends JPanel
{
    int clienteID = 0;
    int proveedorID = 0;
    public String urlTarifas = "";
    public String factor = null;
    public float precioFa = 0;
    Cliente cliente = new Cliente();
    
    public CSAnyadirPedidoNew(BeanPedido peUnido) throws SQLException, ParseException
    {
        CSDesktop.mailCliente.clear();
        CSDesktop.nombreCliente.clear();
        CSDesktop.mailProveedor.clear();
        CSDesktop.nombreProveedor.clear();
        CSDesktop.menuNuevoPedido.setEnabled(false);
        this.setLayout(new GridBagLayout());
        Date hoy = new Date();
        initComponents();
        jTextUrl.setVisible(false);
        jTextUrlPr.setVisible(false);
        jTextClId.setVisible(false);
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
            if (this.getComponents()[k] != jComboBoxIncidencias &&
                this.getComponents()[k] != jComboBoxProvinciaOrigen &&
                this.getComponents()[k] != jComboBoxProvinciaDestino &&
                this.getComponents()[k] != jComboBoxServicio &&
                this.getComponents()[k] != jComboFactor &&
                this.getComponents()[k] != jComboBoxSoporte &&
                this.getComponents()[k] != jCBoxEstadoVehiculo &&
                this.getComponents()[k] != jComboBoxEstado)
            {
                this.getComponents()[k].addKeyListener(l);
            }
        }
        addKeyListener(l);

        //Datos para unir pedido desde la opción editar
        if (peUnido != null)
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal  = Calendar.getInstance();
            cal.setTime(df.parse(peUnido.getFecha()));
            jDateFecha.setCalendar(cal);
            jTextCliente.setText(peUnido.getCliente());
            jComboBoxSoporte.setSelectedItem(peUnido.getSoporte());
            jTextMatricula.setText(peUnido.getMatricula());
            jTextMarca.setText(peUnido.getMarca());
            jTextModelo.setText(peUnido.getModelo());
            jTextDescripcion.setText(peUnido.getObservacionesCl());
            jTextPeUnido.setText(peUnido.getNumUnido());
        }
    }   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        lDireccionOrigen = new javax.swing.JLabel();
        jTextDireccionOrigen = new javax.swing.JTextField();
        lTelefonoOrigen = new javax.swing.JLabel();
        jTextTelefonoOrigen = new javax.swing.JTextField();
        lNombreOrigen = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
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
        lOrigen = new javax.swing.JLabel();
        lFechaOrigen = new javax.swing.JLabel();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lServicio = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox();
        lSoporte = new javax.swing.JLabel();
        jComboBoxSoporte = new javax.swing.JComboBox();
        lEstado3 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lPoblacionOrigen = new javax.swing.JLabel();
        jTextPoblacionOrigen = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lProvinciaOrigen = new javax.swing.JLabel();
        jComboBoxProvinciaOrigen = new javax.swing.JComboBox();
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
        jLabel24 = new javax.swing.JLabel();
        lHoraRealOrigen = new javax.swing.JLabel();
        jTextHoraRealOrigen = new javax.swing.JTextField();
        jTextHoraRealDestino = new javax.swing.JTextField();
        lHoraRealDestino = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextDiasCampa = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lTarifaCli = new javax.swing.JLabel();
        lServicioFMad2 = new javax.swing.JLabel();
        lFechaRealDestino = new javax.swing.JLabel();
        jDateFechaRealDestino = new com.toedter.calendar.JDateChooser();
        jLabelNcamion = new javax.swing.JLabel();
        jTextNumCamion = new javax.swing.JTextField();
        jButtonMailCliente = new javax.swing.JButton();
        jButtonMailProveedor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextObservaciones = new javax.swing.JTextPane();
        lOGeneralText = new javax.swing.JLabel();
        lOProveedor = new javax.swing.JLabel();
        lOCliente = new javax.swing.JLabel();
        jCheckBoxUnidos = new javax.swing.JCheckBox();
        jTextPeUnido = new javax.swing.JTextField();
        jCheckFinUnido = new javax.swing.JCheckBox();
        jSeparator9 = new javax.swing.JSeparator();
        lVehiculo = new javax.swing.JLabel();
        jTextKm = new javax.swing.JTextField();
        jLKm = new javax.swing.JLabel();
        jCBoxEstadoVehiculo = new javax.swing.JComboBox();
        lTarifaEspecial1 = new javax.swing.JLabel();
        lFechaRealOrigen = new javax.swing.JLabel();
        jDateFechaRealOrigen = new com.toedter.calendar.JDateChooser();
        jSeparator10 = new javax.swing.JSeparator();
        jLTarifaCKm = new javax.swing.JLabel();
        jTextTaEsClKm = new javax.swing.JTextField();
        jLTarifaCKm1 = new javax.swing.JLabel();
        lServicio1 = new javax.swing.JLabel();
        lincidencias = new javax.swing.JLabel();
        jComboBoxIncidencias = new javax.swing.JComboBox();
        jTextFMas = new javax.swing.JTextField();
        jLFMas = new javax.swing.JLabel();
        jLFMenos = new javax.swing.JLabel();
        jTextFMenos = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextObsGeneral = new javax.swing.JTextPane();
        lPeUnidos = new javax.swing.JLabel();
        jCheckBoxObsCl = new javax.swing.JCheckBox();
        jCheckBoxObsPr = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        lEstado4 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lTCliente1 = new javax.swing.JLabel();
        jTextTarifa = new javax.swing.JTextField();
        jTextUrl = new javax.swing.JTextField();
        jTextUrlPr = new javax.swing.JTextField();
        jTextClId = new javax.swing.JTextField();

        textField1.setName("textField1"); // NOI18N
        textField1.setText("textField1");

        textField2.setName("textField2"); // NOI18N
        textField2.setText("textField2");

        setForeground(new java.awt.Color(0, 0, 100));
        setPreferredSize(new java.awt.Dimension(1150, 820));

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

        jButtonGuardar.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButtonGuardar.setText("GUARDAR");
        jButtonGuardar.setName("jButtonGuardar"); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

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
        jLabel1.setText("Tipo");
        jLabel1.setName("jLabel1"); // NOI18N

        jComboFactor.setBackground(new java.awt.Color(228, 229, 255));
        jComboFactor.setForeground(new java.awt.Color(0, 0, 100));
        jComboFactor.setName("jComboFactor"); // NOI18N
        jComboFactor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboFactorActionPerformed(evt);
            }
        });

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
        jTextCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextClienteActionPerformed(evt);
            }
        });

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
        lMatricula.setText("Matrícula / Bastidor");
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
        jTextModelo.setNextFocusableComponent(jComboBoxSoporte);
        jTextModelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextModeloFocusLost(evt);
            }
        });

        lOrigen.setFont(new java.awt.Font("Tahoma", 1, 11));
        lOrigen.setForeground(new java.awt.Color(170, 16, 4));
        lOrigen.setText("DATOS DEL ORIGEN");
        lOrigen.setName("lOrigen"); // NOI18N

        lFechaOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lFechaOrigen.setText("Fecha Prevista");
        lFechaOrigen.setName("lFechaOrigen"); // NOI18N

        lDestino.setFont(new java.awt.Font("Tahoma", 1, 11));
        lDestino.setForeground(new java.awt.Color(170, 16, 4));
        lDestino.setText("DATOS DEL DESTINO");
        lDestino.setName("lDestino"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        lTarifaEspecial.setFont(new java.awt.Font("Tahoma", 1, 11));
        lTarifaEspecial.setForeground(new java.awt.Color(170, 16, 4));
        lTarifaEspecial.setText(" TARIFA");
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
        lServicio.setText("OBSERVACIONES");
        lServicio.setName("lServicio"); // NOI18N

        jComboBoxServicio.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxServicio.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Kilómetros", "Urbano", "Interurbano", "Provincial", "ITV" }));
        jComboBoxServicio.setName("jComboBoxServicio"); // NOI18N
        jComboBoxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxServicioActionPerformed(evt);
            }
        });

        lSoporte.setForeground(new java.awt.Color(0, 0, 100));
        lSoporte.setText("Soporte");
        lSoporte.setName("lSoporte"); // NOI18N

        jComboBoxSoporte.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxSoporte.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxSoporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Portavehículos", "Camión completo", "Conductor", "Grúa Unitaria", "Custodia", "Vehículo Pesado", "Transporte Marítimo" }));
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
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "En Proceso", "Entregado", "Facturado", "Anulado", "Pedido Libre", "Fallido" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N
        jComboBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstadoActionPerformed(evt);
            }
        });

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
        jComboBoxProvinciaOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxProvinciaOrigen.setName("jComboBoxProvinciaOrigen"); // NOI18N
        jComboBoxProvinciaOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProvinciaOrigenActionPerformed(evt);
            }
        });

        lFechaDestino.setForeground(new java.awt.Color(0, 0, 100));
        lFechaDestino.setText("Fecha Prevista");
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
        jComboBoxProvinciaDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA", "OTROS" }));
        jComboBoxProvinciaDestino.setName("jComboBoxProvinciaDestino"); // NOI18N
        jComboBoxProvinciaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProvinciaDestinoActionPerformed(evt);
            }
        });

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

        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("*");
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setForeground(new java.awt.Color(0, 0, 100));
        jLabel15.setText("Días campa");
        jLabel15.setName("jLabel15"); // NOI18N

        jTextDiasCampa.setEnabled(false);
        jTextDiasCampa.setName("jTextDiasCampa"); // NOI18N
        jTextDiasCampa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextDiasCampaActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        jSeparator7.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator7.setName("jSeparator7"); // NOI18N

        jSeparator8.setBackground(new java.awt.Color(0, 102, 51));
        jSeparator8.setForeground(new java.awt.Color(0, 102, 51));
        jSeparator8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 53), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        jSeparator8.setOpaque(true);
        jSeparator8.setPreferredSize(new java.awt.Dimension(5, 5));

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        lTarifaCli.setForeground(new java.awt.Color(0, 0, 100));
        lTarifaCli.setText("Cliente");
        lTarifaCli.setName("lTarifaCli"); // NOI18N

        lServicioFMad2.setForeground(new java.awt.Color(0, 0, 100));
        lServicioFMad2.setText("Radios");
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
        jTextNumCamion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNumCamionActionPerformed(evt);
            }
        });

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

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextObservaciones.setAutoscrolls(false);
        jTextObservaciones.setName("jTextObservaciones"); // NOI18N
        jTextObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextObservacionesFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTextObservaciones);

        lOGeneralText.setForeground(new java.awt.Color(0, 0, 100));
        lOGeneralText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lOGeneralText.setText("Generales");
        lOGeneralText.setName("lOGeneralText"); // NOI18N

        lOProveedor.setForeground(new java.awt.Color(0, 0, 100));
        lOProveedor.setText("Proveedor");
        lOProveedor.setName("lOProveedor"); // NOI18N

        lOCliente.setForeground(new java.awt.Color(0, 0, 100));
        lOCliente.setText("Cliente");
        lOCliente.setName("lOCliente"); // NOI18N

        jCheckBoxUnidos.setForeground(new java.awt.Color(0, 0, 100));
        jCheckBoxUnidos.setText(" Unir Pedido");
        jCheckBoxUnidos.setName("jCheckBoxUnidos"); // NOI18N
        jCheckBoxUnidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxUnidosActionPerformed(evt);
            }
        });

        jTextPeUnido.setEditable(false);
        jTextPeUnido.setName("jTextPeUnido"); // NOI18N
        jTextPeUnido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPeUnidoActionPerformed(evt);
            }
        });

        jCheckFinUnido.setForeground(new java.awt.Color(0, 0, 100));
        jCheckFinUnido.setText("Final");
        jCheckFinUnido.setName("jCheckFinUnido"); // NOI18N
        jCheckFinUnido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckFinUnidoActionPerformed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator9.setName("jSeparator9"); // NOI18N

        lVehiculo.setFont(new java.awt.Font("Tahoma", 1, 11));
        lVehiculo.setForeground(new java.awt.Color(170, 16, 4));
        lVehiculo.setText("DATOS DEL VEHÍCULO");
        lVehiculo.setName("lVehiculo"); // NOI18N

        jTextKm.setEnabled(false);
        jTextKm.setName("jTextKm"); // NOI18N
        jTextKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextKmActionPerformed(evt);
            }
        });
        jTextKm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextKmFocusLost(evt);
            }
        });

        jLKm.setForeground(new java.awt.Color(0, 0, 100));
        jLKm.setText("Kms.");
        jLKm.setName("jLKm"); // NOI18N

        jCBoxEstadoVehiculo.setBackground(new java.awt.Color(228, 229, 255));
        jCBoxEstadoVehiculo.setForeground(new java.awt.Color(0, 0, 100));
        jCBoxEstadoVehiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Funciona", "Avería Mecánica", "Accidente Leve", "Accidente Grave" }));
        jCBoxEstadoVehiculo.setName("jCBoxEstadoVehiculo"); // NOI18N
        jCBoxEstadoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxEstadoVehiculoActionPerformed(evt);
            }
        });

        lTarifaEspecial1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lTarifaEspecial1.setForeground(new java.awt.Color(170, 16, 4));
        lTarifaEspecial1.setText("ENTREGA REAL");
        lTarifaEspecial1.setName("lTarifaEspecial1"); // NOI18N

        lFechaRealOrigen.setForeground(new java.awt.Color(0, 0, 100));
        lFechaRealOrigen.setText("Fecha Real Origen");
        lFechaRealOrigen.setName("lFechaRealOrigen"); // NOI18N

        jDateFechaRealOrigen.setDateFormatString("dd-MM-yyyy");
        jDateFechaRealOrigen.setName("jDateFechaRealOrigen"); // NOI18N

        jSeparator10.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator10.setName("jSeparator10"); // NOI18N

        jLTarifaCKm.setForeground(new java.awt.Color(0, 0, 100));
        jLTarifaCKm.setText("Tarifa Cliente (€/km)");
        jLTarifaCKm.setName("jLTarifaCKm"); // NOI18N

        jTextTaEsClKm.setName("jTextTaEsClKm"); // NOI18N
        jTextTaEsClKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTaEsClKmActionPerformed(evt);
            }
        });

        jLTarifaCKm1.setForeground(new java.awt.Color(0, 0, 100));
        jLTarifaCKm1.setText("€/km");
        jLTarifaCKm1.setName("jLTarifaCKm1"); // NOI18N

        lServicio1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lServicio1.setForeground(new java.awt.Color(170, 16, 4));
        lServicio1.setText("PROVEEDOR");
        lServicio1.setName("lServicio1"); // NOI18N

        lincidencias.setForeground(new java.awt.Color(0, 0, 100));
        lincidencias.setText("Incidencias");
        lincidencias.setName("lincidencias"); // NOI18N

        jComboBoxIncidencias.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxIncidencias.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxIncidencias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "I", "IM", "IRD", "RFL", "RP", "RC", "ADMINISTRATIVA" }));
        jComboBoxIncidencias.setName("jComboBoxIncidencias"); // NOI18N
        jComboBoxIncidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIncidenciasActionPerformed(evt);
            }
        });

        jTextFMas.setName("jTextFMas"); // NOI18N

        jLFMas.setForeground(new java.awt.Color(0, 0, 100));
        jLFMas.setText("F+");
        jLFMas.setName("jLFMas"); // NOI18N

        jLFMenos.setForeground(new java.awt.Color(0, 0, 100));
        jLFMenos.setText("F-");
        jLFMenos.setName("jLFMenos"); // NOI18N

        jTextFMenos.setName("jTextFMenos"); // NOI18N
        jTextFMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFMenosActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextObsGeneral.setName("jTextObsGeneral"); // NOI18N
        jTextObsGeneral.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextObsGeneralFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(jTextObsGeneral);

        lPeUnidos.setFont(new java.awt.Font("Tahoma", 1, 11));
        lPeUnidos.setForeground(new java.awt.Color(170, 16, 4));
        lPeUnidos.setText("PEDIDOS UNIDOS");
        lPeUnidos.setName("lPeUnidos"); // NOI18N

        jCheckBoxObsCl.setForeground(new java.awt.Color(0, 0, 100));
        jCheckBoxObsCl.setText("Observaciones en Mail, factura y Web");
        jCheckBoxObsCl.setName("jCheckBoxObsCl"); // NOI18N
        jCheckBoxObsCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxObsClActionPerformed(evt);
            }
        });

        jCheckBoxObsPr.setForeground(new java.awt.Color(0, 0, 100));
        jCheckBoxObsPr.setText("Observaciones en Mail");
        jCheckBoxObsPr.setName("jCheckBoxObsPr"); // NOI18N
        jCheckBoxObsPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxObsPrActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Tarifas Cliente");
        jToggleButton1.setName("jToggleButton1"); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        lEstado4.setForeground(new java.awt.Color(0, 0, 100));
        lEstado4.setText("Estado");
        lEstado4.setName("lEstado4"); // NOI18N

        jToggleButton2.setText("Tarifas Proveedor");
        jToggleButton2.setName("jToggleButton2"); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("*");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("*");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("*");
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setForeground(new java.awt.Color(204, 0, 0));
        jLabel22.setText("*");
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setForeground(new java.awt.Color(204, 0, 0));
        jLabel23.setText("*");
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel25.setForeground(new java.awt.Color(204, 0, 0));
        jLabel25.setText("*");
        jLabel25.setName("jLabel25"); // NOI18N

        jLabel26.setForeground(new java.awt.Color(204, 0, 0));
        jLabel26.setText("*");
        jLabel26.setName("jLabel26"); // NOI18N

        lTCliente1.setForeground(new java.awt.Color(0, 0, 100));
        lTCliente1.setText("T.");
        lTCliente1.setName("lTCliente1"); // NOI18N

        jTextTarifa.setEnabled(false);
        jTextTarifa.setName("jTextTarifa"); // NOI18N

        jTextUrl.setEditable(false);
        jTextUrl.setAutoscrolls(false);
        jTextUrl.setFocusable(false);
        jTextUrl.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextUrl.setName("jTextUrl"); // NOI18N
        jTextUrl.setOpaque(false);
        jTextUrl.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTextUrlComponentHidden(evt);
            }
        });

        jTextUrlPr.setEditable(false);
        jTextUrlPr.setAutoscrolls(false);
        jTextUrlPr.setFocusable(false);
        jTextUrlPr.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextUrlPr.setName("jTextUrlPr"); // NOI18N
        jTextUrlPr.setOpaque(false);
        jTextUrlPr.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTextUrlPrComponentHidden(evt);
            }
        });

        jTextClId.setEditable(false);
        jTextClId.setAutoscrolls(false);
        jTextClId.setFocusable(false);
        jTextClId.setMinimumSize(new java.awt.Dimension(0, 0));
        jTextClId.setName("jTextClId"); // NOI18N
        jTextClId.setOpaque(false);
        jTextClId.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTextClIdComponentHidden(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxIncidencias, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lincidencias)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLFMas)
                            .addComponent(jTextFMas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLFMenos)
                            .addComponent(jTextFMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lServicio))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lTarifaEspecial1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1028, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lTarifaEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLTarifaCKm)
                .addGap(18, 18, 18)
                .addComponent(jTextTaEsClKm, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLTarifaCKm1)
                .addGap(37, 37, 37)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lTarifaCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTaEsCli, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lTarifaEsProv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTaEsProv, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(lPeUnidos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxUnidos)
                .addGap(14, 14, 14)
                .addComponent(jCheckFinUnido)
                .addGap(16, 16, 16)
                .addComponent(jTextPeUnido, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lDireccionDestino))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lDireccionOrigen))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNombreOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNombreOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextDireccionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPoblacionDestino))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPoblacionOrigen)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jTextPoblacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPoblacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jComboBoxProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCodPostalOrigen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCodPostalOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCodPostalDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextCodPostalDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTelefonoOrigen))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTelefonoDestino)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jTextTelefonoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTelefonoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lFechaOrigen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(lFechaDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(88, 88, 88))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lOrigen)
                .addContainerGap(1028, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lNumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTextClId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lTCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jCheckBoxObsCl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(lOCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(lOProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(lOGeneralText)
                        .addGap(197, 197, 197))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jCheckBoxObsPr)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lSoporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lServicio1))
                .addGap(18, 18, 18)
                .addComponent(lServicioFMad2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelNcamion)
                        .addComponent(jLKm))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextDiasCampa)
                    .addComponent(jTextNumCamion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextKm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addGap(3, 3, 3)
                        .addComponent(lProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextUrlPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jToggleButtonProveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lEstado4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBoxEstadoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lMatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(4, 4, 4)
                .addComponent(lMarca)
                .addGap(2, 2, 2)
                .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel22)
                .addGap(4, 4, 4)
                .addComponent(lModelo)
                .addGap(2, 2, 2)
                .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lVehiculo)
                .addContainerGap(1015, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lDestino)
                .addContainerGap(1022, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(47, 47, 47)
                        .addComponent(jButtonMailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonMailProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lEstado3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lFechaRealOrigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateFechaRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(lHoraRealOrigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextHoraRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lFechaRealDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateFechaRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lHoraRealDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextHoraRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFMas, jTextFMenos});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextDireccionDestino, jTextNombreDestino});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDateFechaRealDestino, jDateFechaRealOrigen});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lCliente)
                        .addComponent(jLabel2)
                        .addComponent(lTCliente1)
                        .addComponent(jTextTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButtonCliente)
                        .addComponent(jToggleButton1)
                        .addComponent(jTextUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextClId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lFecha)
                                .addComponent(jLabel3)
                                .addComponent(lNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextDireccionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lDireccionOrigen)
                        .addComponent(jTextCodPostalOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(lPoblacionOrigen)
                        .addComponent(jTextPoblacionOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lCodPostalOrigen)
                        .addComponent(lProvinciaOrigen)
                        .addComponent(jLabel12)
                        .addComponent(jComboBoxProvinciaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateFechaOrigen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombreOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lFechaOrigen)
                            .addComponent(jLabel19)
                            .addComponent(lNombreOrigen)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextTelefonoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lTelefonoOrigen)
                            .addComponent(jLabel8))))
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateFechaDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextDireccionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lDireccionDestino)
                                .addComponent(jTextPoblacionDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lPoblacionDestino)
                                .addComponent(jLabel7)
                                .addComponent(jTextCodPostalDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lCodPostalDestino)
                                .addComponent(lProvinciaDestino)
                                .addComponent(jLabel13)
                                .addComponent(jComboBoxProvinciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNombreDestino)
                            .addComponent(jLabel11)
                            .addComponent(jLabel20)
                            .addComponent(lFechaDestino)
                            .addComponent(jTextTelefonoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(lTelefonoDestino))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lVehiculo)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel17))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBoxEstadoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lEstado4)
                        .addComponent(jLabel18)
                        .addComponent(lMarca)
                        .addComponent(jLabel21)
                        .addComponent(lModelo)
                        .addComponent(jLabel22)
                        .addComponent(jTextMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(lMatricula)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lServicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lSoporte)
                                .addComponent(jComboBoxSoporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lServicioFMad2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButtonProveedor)
                            .addComponent(lProveedor)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton2)
                            .addComponent(jTextUrlPr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLKm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNumCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNcamion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextDiasCampa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLFMas)
                            .addComponent(jLFMenos)
                            .addComponent(lincidencias))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFMas)
                                .addComponent(jComboBoxIncidencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lOCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lOProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lOGeneralText, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxObsCl)
                    .addComponent(jCheckBoxObsPr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTarifaCKm)
                            .addComponent(jTextTaEsClKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLTarifaCKm1)
                            .addComponent(lTarifaEspecial)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lTarifaCli)
                            .addComponent(jTextTaEsCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lTarifaEsProv)
                        .addComponent(jTextTaEsProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextPeUnido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckFinUnido)
                        .addComponent(jCheckBoxUnidos)
                        .addComponent(lPeUnidos)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lTarifaEspecial1)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEstado3)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lFechaRealOrigen)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jDateFechaRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lHoraRealDestino)
                                    .addComponent(jTextHoraRealDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lFechaRealDestino)
                                    .addComponent(jTextHoraRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lHoraRealOrigen)))
                            .addComponent(jDateFechaRealOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonMailCliente)
                    .addComponent(jButtonMailProveedor)
                    .addComponent(jLabel9)
                    .addComponent(jButtonCancelar1))
                .addGap(45, 45, 45))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextFMas, jTextFMenos});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3});

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        System.out.println("\njButtonGuardar_actionPerformed(ActionEvent e) called.");

        //VARIABLES A UTILIZAR
        int cerradoN=0;
        int finUnidoN=0;
        int pedidoUnidoN=0;
        int diasCampaN=0;
        int inMailCl = 0;
        int inMailPr = 0;
        double kms = 0;
        String fecha2="";
        String fechaOrigen="";
        String fechaDestino="";
        String fechaRealOrigen = "";
        String fechaRealDestino = "";
        String fechaEntrega = "";
        String fechaRecogida = "";
        double tarifaKmClN = 0;
        double taescliN=-1;
        double taesproN=0;
        int comparacion1 = -1;
        int comparacion2 = -1;
        int comparacion3 = -1;
        String comparaFecha = "ko";
        String comparaFechaOrig = "ko";
        String comparaFechaDes = "ko";

        String estadoCliente = "Activo";

        // SE RECOGEN LOS VALORES DE LOS CAMPOS
        // CONVERSION DE LA FECHA
        Calendar fechaCalendarFecha = jDateFecha.getCalendar();
        if (fechaCalendarFecha!=null)
        {
            Date fecha = fechaCalendarFecha.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fecha2=formatoDeFecha.format(fecha);
        }
        String clName= new String(jTextCliente.getText());

        if (Utilidades.campoObligatorio(clName,"Cliente").equals("OK"))
        {
             clienteID = cliente.getClienteID(clName);
             BeanCliente datosCl = new BeanCliente();
             datosCl = cliente.getDatosFacturaCliente(clienteID);
             estadoCliente = datosCl.getEstado();
        }

        //DATOS ORIGEN
        String direccionOrigen=new String(jTextDireccionOrigen.getText());
        String poblacionOrigen=new String(jTextPoblacionOrigen.getText());
        String provinciaOrigen=new String(jComboBoxProvinciaOrigen.getSelectedItem().toString());
        String codigoPOrigen=new String(jTextCodPostalOrigen.getText());
        String nombreOrigen=new String(jTextNombreOrigen.getText());
        String telefonoOrigen=new String(jTextTelefonoOrigen.getText());
        // CONVERSION DE LA FECHA ORIGEN
        Calendar fechaCalendarOrigen = jDateFechaOrigen.getCalendar();
        if (fechaCalendarOrigen!=null)
        {
            Date fecha = fechaCalendarOrigen.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaOrigen=formatoDeFecha.format(fecha);
            SimpleDateFormat formatoDeFecha2 = new SimpleDateFormat("dd-MM-yyyy");
            fechaRecogida=formatoDeFecha2.format(fecha);
        }
        
        //DATOS DESTINO
        String direccionDestino=new String(jTextDireccionDestino.getText());
        String poblacionDestino=new String(jTextPoblacionDestino.getText());
        String provinciaDestino=new String(jComboBoxProvinciaDestino.getSelectedItem().toString());
        String codigoPDestino=new String(jTextCodPostalDestino.getText());
        String nombreDestino=new String(jTextNombreDestino.getText());
        String telefonoDestino=new String(jTextTelefonoDestino.getText());
        // CONVERSION DE LA FECHA DESTINO
        Calendar fechaCalendarDestino = jDateFechaDestino.getCalendar();
        if (fechaCalendarDestino!=null)
        {
            Date fecha = fechaCalendarDestino.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaDestino=formatoDeFecha.format(fecha);
            SimpleDateFormat formatoDeFecha2 = new SimpleDateFormat("dd-MM-yyyy");
            fechaEntrega=formatoDeFecha2.format(fecha);
        }

        //DATOS DEL VEHICULO
        int factor = new Integer(jComboFactor.getSelectedIndex());
        String estado_ve=new String(jCBoxEstadoVehiculo.getSelectedItem().toString());
        String matricula=new String(jTextMatricula.getText());
        String marca=new String(jTextMarca.getText());
        String modelo=new String(jTextModelo.getText());

        //PROVEEDOR
        String soporte=new String(jComboBoxSoporte.getSelectedItem().toString());
        String servicio=new String(jComboBoxServicio.getSelectedItem().toString());
        String kilometros=new String(jTextKm.getText());
        if(!kilometros.equals(""))
        {
            kms=Double.valueOf(kilometros).doubleValue();
        }

        String numEnCamion = new String(jTextNumCamion.getText());
        if (numEnCamion.equals(""))
        {
            numEnCamion = "0";
        }
        String diasCampa = new String(jTextDiasCampa.getText());
        if(!diasCampa.equals(""))
        {
            diasCampaN=Integer.valueOf(diasCampa).intValue();
        }
        else
        {
            diasCampaN=0;
        }
        String proveedor= new String(jTextProveedor.getText());

        //OBSERVACIONES
        String incidencia = new String(jComboBoxIncidencias.getSelectedItem().toString());
        incidencia = (incidencia.equals("Selecciona")) ? "" : incidencia;
        String in_f_mas = new String(jTextFMas.getText());
        int fMas = (in_f_mas.equals("")) ? 0 : Integer.parseInt(in_f_mas);
        String in_f_menos = new String(jTextFMenos.getText());
        int fMenos = (in_f_menos.equals("")) ? 0 : Integer.parseInt(in_f_menos);
        String descripcion = new String(jTextDescripcion.getText());
        String observaciones = new String(jTextObservaciones.getText());
        String obs_general = new String(jTextObsGeneral.getText());
        boolean ob_cl_mail = new Boolean(jCheckBoxObsCl.isSelected());
        boolean ob_pr_mail = new Boolean(jCheckBoxObsPr.isSelected());

        //TARIFA
        String tarifaKmCl=new String(jTextTaEsClKm.getText());
        if(!tarifaKmCl.equals(""))
        {
            tarifaKmClN=Double.valueOf(tarifaKmCl).doubleValue();
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
        boolean unirPedidos = new Boolean(jCheckBoxUnidos.isSelected());

        if(!jTextPeUnido.getText().equals("")){
            pedidoUnidoN = Integer.parseInt(jTextPeUnido.getText());
        }
        boolean finUnido = new Boolean(jCheckFinUnido.isSelected());

        //ENTREGA REAL
        String estado=new String(jComboBoxEstado.getSelectedItem().toString());
        String horaRealOrigen = new String(jTextHoraRealOrigen.getText());
        String horaRealDestino = new String(jTextHoraRealDestino.getText());
        // CONVERSION DE LA FECHA REAL ORIGEN
        Calendar fechaCalendarRealOrigen = jDateFechaRealOrigen.getCalendar();
        if (fechaCalendarRealOrigen!=null)
        {
            Date fecha = fechaCalendarRealOrigen.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaRealOrigen=formatoDeFecha.format(fecha);
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
       /* else
        {
            fechaRealDestino="2050-01-01";
        }*/


        ///////// VALIDACION DE LOS DATOS //////////////////////
        if((fechaCalendarOrigen != null) && (fechaCalendarDestino != null))
        {
            comparacion1=fechaCalendarFecha.compareTo(fechaCalendarOrigen);
            comparacion2=fechaCalendarOrigen.compareTo(fechaCalendarDestino);
            comparaFecha = Utilidades.comparaFechaString(fecha2, fechaOrigen);
            comparaFechaOrig = Utilidades.comparaFechaString(fechaOrigen, fechaDestino);
        }
        if (fechaCalendarRealDestino!=null)
        {
             comparacion3=fechaCalendarRealDestino.compareTo(fechaCalendarOrigen);
             comparaFechaDes = Utilidades.comparaFechaString(fechaOrigen, fechaRealDestino);
        }
        
        if (!Utilidades.campoObligatorio(fecha2,"Fecha").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fecha2,"Fecha"));
        }
        else if (!Utilidades.campoObligatorio(servicio,"Servicio").equals("OK") )
        {
            ValidarFormatos(Utilidades.campoObligatorio(servicio,"servicio"));
        }
        else if (!Utilidades.campoObligatorio(soporte,"Soporte").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(soporte,"Soporte"));
        }
        else if (!Utilidades.campoObligatorio(clName,"Cliente").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(clName,"Cliente"));
        }
        else if (estadoCliente.equals("Inactivo"))
        {
            ValidarFormatos("El Cliente seleccionado está Inactivo");
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
        //else if(comparacion1 > 0)
        else if(comparaFecha.equals("ko"))
        {
            ValidarFormatos("Fecha Pedido tiene que ser <= Fecha Prevista Recogida");
        }
        //else if(comparacion2 > 0)
        else if(comparaFechaOrig.equals("ko"))
        {
            ValidarFormatos("Fecha Prevista Recogida tiene que ser <= Fecha Prevista Entrega");
        }
        //else if(comparacion3 < 0)
        else if(comparaFechaDes.equals("ko") && !fechaRealDestino.equals(""))
        {
            ValidarFormatos("Fecha Real Entrega tiene que ser >= Fecha Prevista Recogida");
        }
        else if(estadoCliente.equals("Inactivo"))
        {
            jButtonGuardar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>El cliente está inactivo</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonGuardar.setEnabled(true);
        } else if((jComboFactor.getSelectedItem().toString()).equals("Ninguno")){
            ValidarFormatos("Seleccione Tipo de Vehículo");
        }
        else if(estado_ve.equals("Selecciona")){
            ValidarFormatos("Seleccione Estado del Vehículo");
        }
        else if(soporte.equals("Selecciona")){
            ValidarFormatos("Seleccione Soporte del Proveedor");
        }
        else
        {
            finUnidoN = (!finUnido) ? 0 : 1;
            inMailCl = (!ob_cl_mail) ? 0 : 1;
            inMailPr = (!ob_pr_mail) ? 0 : 1;

            String query = "INSERT INTO pe_pedidos (pe_fecha, pe_descripcion, pe_direccion_origen, pe_poblacion_origen, " +
                                                     "pe_provincia_origen, pe_cp_origen, pe_fecha_origen, pe_nombre_origen, " +
                                                     "pe_telefono_origen, pe_direccion_destino, pe_poblacion_destino, pe_provincia_destino, " +
                                                     "pe_cp_destino, pe_fecha_destino, pe_nombre_destino, pe_telefono_destino, pe_servicio, " +
                                                     "pe_dias_campa, pe_soporte, pe_ve_matricula, pe_ve_marca, pe_ve_modelo, " +
                                                     "pe_hora_real_origen, pe_hora_real_destino, pe_ta_km_cliente, pe_ta_es_cliente, " +
                                                     "pe_ta_es_proveedor, fc_id, pe_estado, pe_activo, pe_kms, pe_num_en_camion, " +
                                                     "pe_observaciones_carset, pe_num_unido, pe_fin_unido, pe_ve_estado, pe_incidencia," +
                                                     " pe_in_f_mas, pe_in_f_menos, pe_ob_general, pe_ob_cl_mail, pe_ob_pr_mail";

           query = (!fechaRealOrigen.equals("")) ? query + ", pe_fecha_real_origen" : query;
           query = (!fechaRealDestino.equals("")) ? query + ", pe_fecha_real_destino" : query;
           query = query + ") VALUES (";

            query = query + "'"+fecha2+"', '"+descripcion+"', '"+direccionOrigen+"', '"+poblacionOrigen+"', '"+provinciaOrigen+"', '"+codigoPOrigen+"'";
            query = query + ", '"+fechaOrigen+"', '"+nombreOrigen+"', '"+telefonoOrigen+"', '"+direccionDestino+"', '"+poblacionDestino+"'";
            query = query + ", '"+provinciaDestino+"', '"+codigoPDestino+"', '"+fechaDestino+"', '"+nombreDestino+"', '"+telefonoDestino+"'";
            query = query + ", '"+servicio+"', '"+diasCampaN+"', '"+soporte+"', '"+matricula+"', '"+marca+"', '"+modelo+"'";
            query = query + ", '"+horaRealOrigen+"', '"+horaRealDestino+"', '"+tarifaKmClN+"', '"+taescliN+"', '"+taesproN+"', '"+factor+"'";
            query = query + ", '"+estado+"', '"+cerradoN+"', '"+kms+"', '"+numEnCamion+"', '"+observaciones+"', '"+pedidoUnidoN+"', '"+finUnidoN+"'";
            query = query + ", '"+estado_ve+"', '"+incidencia+"', '"+fMas+"', '"+fMenos+"', '"+obs_general+"', '"+inMailCl+"', '"+inMailPr+"'";
            query = (!fechaRealOrigen.equals("")) ? query + ", '"+fechaRealOrigen+"'" : query;
            query = (!fechaRealDestino.equals("")) ? query + ", '"+fechaRealDestino+"'" : query;
            query = query + ")";
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
                Proveedor proveedor2 = new Proveedor();
                proveedorID = proveedor2.getProveedorID(proveedor);
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
                    if(!unirPedidos)
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
                        CSAnyadirPedidoNew.this,
                        "¿Quieres mandar un mail al cliente " + mails + "?",
                        "Atención",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,    // null para icono por defecto.
                        new Object[] { "SI", "NO"},   // null para YES, NO y CANCEL
                        "SI");

                        if(seleccion == 0)
                        {
                            BeanCorreoCliente mail = new BeanCorreoCliente();

                            //Para calcular la fecha
                            Date fechaHoy = new Date(System.currentTimeMillis());
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
                            String fechaHoy2=formatoDeFecha.format(fechaHoy);

                            //Para el numero de pedido
                            String numPedido=Utilidades.rellenarCeros(pe_num,5);
                            String pedido=numPedido+"/"+fecha2.substring(2, 4);

                            mail.setCliente(clName);
                            mail.setFecha(fechaHoy2);
                            mail.setNumPedido(pedido);
                            mail.setSoporte(soporte);
                            mail.setFechaEntrega(fechaEntrega);
                            mail.setMarca(marca);
                            mail.setModelo(modelo);
                            mail.setMatricula(matricula);
                            if(finUnidoN == 1){
                                try {
                                    BeanPedido pedidoOrigen = getPedidoUnido(jTextPeUnido.getText());
                                    numPedido = Utilidades.rellenarCeros(pedidoOrigen.getNum(), 5);
                                    pedido = numPedido + "/" + fecha2.substring(2, 4);
                                    mail.setNumPedido(pedido);
                                    String fechaOrigenUnido = "";
                                    if (pedidoOrigen!=null)
                                    {
                                        SimpleDateFormat formatoOrigenFecha = new SimpleDateFormat("yyyy-MM-dd");
                                        Date fechaODestino=formatoOrigenFecha.parse(pedidoOrigen.getFechaOrigen());
                                        SimpleDateFormat formatoOrigenFecha2 = new SimpleDateFormat("dd-MM-yyyy");
                                        fechaOrigenUnido = formatoOrigenFecha2.format(fechaODestino);
                                    }
                                    mail.setFechaRecogida(fechaOrigenUnido);
                                    mail.setDireccionOrigen(pedidoOrigen.getDireccionOrigen());
                                    mail.setPoblacionOrigen(pedidoOrigen.getPoblacionOrigen());
                                    mail.setProvinciaOrigen(pedidoOrigen.getProvinciaOrigen());
                                    mail.setNombreOrigen(pedidoOrigen.getNombreOrigen());
                                    mail.setTelefonoOrigen(pedidoOrigen.getTelefonoOrigen());
                                    mail.setDescripcion(pedidoOrigen.getObservacionesCl());
                                    mail.setTarifaEspecialCliente(pedidoOrigen.getTarifa());
                                    mail.setKms(pedidoOrigen.getKms());
                                    mail.setSoporte(pedidoOrigen.getSoporte());
                                    mail.setPeUnido(true);
                                } catch (ParseException ex) {
                                    Logger.getLogger(CSAnyadirPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(CSAnyadirPedidoNew.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                mail.setDireccionOrigen(direccionOrigen);
                                mail.setPoblacionOrigen(poblacionOrigen);
                                mail.setProvinciaOrigen(provinciaOrigen);
                                mail.setNombreOrigen(nombreOrigen);
                                mail.setTelefonoOrigen(telefonoOrigen);
                                mail.setFechaRecogida(fechaRecogida);
                                mail.setDescripcion(descripcion);
                                mail.setTarifaEspecialCliente(tarifaCliente);
                                mail.setKms(kilometros);
                                mail.setSoporte(soporte);
                                mail.setPeUnido(false);
                            }
                            mail.setDireccionDestino(direccionDestino);
                            mail.setPoblacionDestino(poblacionDestino);
                            mail.setProvinciaDestino(provinciaDestino);
                            mail.setNombreDestino(nombreDestino);
                            mail.setTelefonoDestino(telefonoDestino);
                            mail.setObsClInmail(ob_cl_mail);
                            if (diasCampa.equals(""))
                                diasCampa="0";
                            mail.setDiasCampa(diasCampa);
                            mail.setNumero(pe_num);
                            mail.setObsClInmail(ob_cl_mail);
                            mail.setTarifakmCliente(tarifaKmCl);
                            Cliente client = new Cliente();
                            mail.setClienteID(String.valueOf(client.getClienteID(clName)));

                            if(estado.equals("En Proceso"))
                            {
                                 for(int i=0;i<CSDesktop.mailCliente.size();i++)
                                 {
                                    CSEnviarMailProcesoNew.main(mail,CSDesktop.mailCliente.get(i).toString(),CSDesktop.nombreCliente.get(i).toString());
                                 }
                            }
                            else if (estado.equals("Entregado"))
                            {
                                 for(int i=0;i<CSDesktop.mailCliente.size();i++)
                                 {
                                    CSEnviarMailEntregadoNew.main(mail,CSDesktop.mailCliente.get(i).toString(),CSDesktop.nombreCliente.get(i).toString());
                                 }
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
                            CSAnyadirPedidoNew.this,
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
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaHoy2=formatoDeFecha.format(fechaHoy);

                        //String fechaOrigen2=formatoDeFecha.format(fechaOrigen);
                        //String fechaDestino2=formatoDeFecha.format(fechaDestino);
                        String [] tempOrigen = null;
                        tempOrigen = fechaOrigen.split("\\-");
                        String fechaOrigen2=tempOrigen[2]+"/"+tempOrigen[1]+"/"+tempOrigen[0];
                        String [] tempDestino = null;
                        tempDestino = fechaDestino.split("\\-");
                        String fechaDestino2=tempDestino[2]+"/"+tempDestino[1]+"/"+tempDestino[0];
                        //Para el numero de pedido
                        String numPedido=Utilidades.rellenarCeros(pe_num,5);
                        String pedido=numPedido+"/"+fecha2.substring(2, 4);

                        mail.setCliente(proveedor);
                        mail.setFecha(fechaHoy2);
                        mail.setNumPedido(pedido);
                        mail.setSoporte(soporte);
                        mail.setFechaEntrega(fechaEntrega);
                        mail.setFechaRecogida(fechaRecogida);
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
                        mail.setTarifaEspecialProveedor(tarifaProveedor);
                        mail.setObservaciones(observaciones);
                        mail.setObsPrInmail(ob_pr_mail);
                        mail.setNumero(pe_num);
                         if (diasCampa.equals(""))
                            diasCampa="0";
                        mail.setDiasCampa(diasCampa);
                        Proveedor proveed = new Proveedor();
                        mail.setClienteID(String.valueOf(proveed.getProveedorID(proveedor)));

                        for(int i=0;i<CSDesktop.mailProveedor.size();i++)
                        {
                            CSEnviarMailProveedorNew.main(mail,CSDesktop.mailProveedor.get(i).toString(),CSDesktop.nombreProveedor.get(i).toString());
                        }
                     }
                   }
                }

                if(unirPedidos){
                    int confirmado = JOptionPane.showConfirmDialog(this,"Los datos se han guardado correctamente, se ha marcado la opción de unir pedidos, rellene los datos del pedido");

                    if (JOptionPane.OK_OPTION == confirmado)
                    {
                        pedidoUnidoN = (pedidoUnidoN != 0) ? pedidoUnidoN : Integer.parseInt(pe_num);
                        jTextPeUnido.setText(String.valueOf(pedidoUnidoN));
                        jTextDireccionOrigen.setText("");
                        jTextPoblacionOrigen.setText("");
                        jComboBoxProvinciaOrigen.setSelectedItem("Selecciona");
                        jTextCodPostalOrigen.setText("");
                        jTextNombreOrigen.setText("");
                        jTextTelefonoOrigen.setText("");
                        jDateFechaOrigen.setDate(null);

                        jTextDireccionDestino.setText("");
                        jTextPoblacionDestino.setText("");
                        jComboBoxProvinciaDestino.setSelectedItem("Selecciona");
                        jTextCodPostalDestino.setText("");
                        jTextNombreDestino.setText("");
                        jTextTelefonoDestino.setText("");
                        jDateFechaDestino.setDate(null);

                        jComboBoxServicio.setSelectedItem("Selecciona");
                        jTextDiasCampa.setText("");
                        jComboBoxSoporte.setSelectedItem("Selecciona");
                        jTextNumCamion.setText("");
                        jTextProveedor.setText("");
                        jTextTaEsCli.setText("");
                        jTextTaEsProv.setText("");
                        jTextObservaciones.setText("");
                        jTextKm.setText("");
                        jComboBoxIncidencias.setSelectedItem("Selecciona");
                        jTextFMas.setText("");
                        jTextFMenos.setText("");
                        jComboBoxEstado.setSelectedItem("Selecciona");
                        jTextHoraRealOrigen.setText("");
                        jCheckBoxUnidos.setSelected(false);
                    }else{
                        CSDesktop.NuevoPedido.dispose();
                        CSDesktop.menuNuevoPedido.setEnabled(true);
                    }
                }else{
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
                        CSDesktop.NuevoPedidoPrueba.dispose();
                        CSDesktop.menuNuevoPedidoPrueba.setEnabled(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jToggleButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonClienteActionPerformed

         System.out.println("\nBotón Buscar Cliente en Añadir Pedido.");
               CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSSelectClientePedido panel = new CSSelectClientePedido(jTextClId, jTextCliente,jTextTarifa,jTextUrl,jTextObservaciones,jTextObsGeneral,"",true);
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
               CSSelectProveedorPedido panel = new CSSelectProveedorPedido(jTextProveedor,jTextUrlPr,"",true);
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
            CSDesktop.NuevoPedidoPrueba.dispose();
            CSDesktop.menuNuevoPedidoPrueba.setEnabled(true);
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

    private void jComboBoxSoporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSoporteActionPerformed
        String soporte = jComboBoxSoporte.getSelectedItem().toString();
        if (soporte.equals("Camión completo"))
        {
            jTextNumCamion.setEnabled(true);
        }else{
            jTextNumCamion.setEnabled(false);
        }
        if (soporte.equals("Custodia"))
        {
            jTextDiasCampa.setEnabled(true);
        } else {
            jTextDiasCampa.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxSoporteActionPerformed

    private void jButtonMailClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMailClienteActionPerformed

        Cliente finalCliente=new Cliente();
        String cliente = jTextCliente.getText();
       
        String query = "SELECT cc_nombre,cc_email FROM cc_contactos_cliente where cl_id ="+ finalCliente.getClienteID(cliente);

        System.out.println(query);
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

    private void jTextObservacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextObservacionesFocusLost
        String ObservacionesM = jTextObservaciones.getText().toUpperCase();
        jTextObservaciones.setText(ObservacionesM);
}//GEN-LAST:event_jTextObservacionesFocusLost

    private void jTextDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDescripcionFocusLost
        String DescripcionM = jTextDescripcion.getText().toUpperCase();
        jTextDescripcion.setText(DescripcionM);
}//GEN-LAST:event_jTextDescripcionFocusLost

    private void jCheckBoxUnidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxUnidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxUnidosActionPerformed

    private void jTextPeUnidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPeUnidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPeUnidoActionPerformed

    private void jCheckFinUnidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckFinUnidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckFinUnidoActionPerformed

    private void jComboBoxProvinciaOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProvinciaOrigenActionPerformed

    }//GEN-LAST:event_jComboBoxProvinciaOrigenActionPerformed

    private void jComboBoxProvinciaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProvinciaDestinoActionPerformed

    }//GEN-LAST:event_jComboBoxProvinciaDestinoActionPerformed

    private void jCBoxEstadoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxEstadoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBoxEstadoVehiculoActionPerformed

    private void jTextTaEsClKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTaEsClKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTaEsClKmActionPerformed

    private void jComboBoxIncidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIncidenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIncidenciasActionPerformed

    private void jTextObsGeneralFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextObsGeneralFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextObsGeneralFocusLost

    private void jCheckBoxObsClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxObsClActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxObsClActionPerformed

    private void jCheckBoxObsPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxObsPrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxObsPrActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        FileChooser utilFc = new FileChooser();
        System.out.println("UrlTarifasProveedor: "+jTextUrl.getText());
        utilFc.openFile(jTextUrl.getText());
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextNumCamionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNumCamionActionPerformed

    }//GEN-LAST:event_jTextNumCamionActionPerformed

    private void jTextFMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFMenosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFMenosActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        FileChooser utilFc = new FileChooser();
        System.out.println("UrlTarifasProveedor: "+jTextUrl.getText());
        utilFc.openFile(jTextUrlPr.getText());
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jComboBoxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxServicioActionPerformed

        if (jComboBoxServicio.getSelectedIndex() == 1)
        {
            jTextKm.setEnabled(true);
        } else {
            jTextKm.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxServicioActionPerformed

    private void jTextKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextKmActionPerformed

    }//GEN-LAST:event_jTextKmActionPerformed

    private void jTextClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextClienteActionPerformed

    }//GEN-LAST:event_jTextClienteActionPerformed

    private void jTextUrlComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTextUrlComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextUrlComponentHidden

    private void jComboFactorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboFactorActionPerformed

        String sFactor = jComboFactor.getSelectedItem().toString();
        if(sFactor.equals("Turismo") && !jTextClId.getText().equals("")){
            factor = "sv_turismo";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Turismo") && !jTextClId.getText().equals("")){
            factor = "sv_turismo";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Furgoneta Ligera o Monovolumen") && !jTextClId.getText().equals("")){
            factor = "sv_flm";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Todoterreno") && !jTextClId.getText().equals("")){
            factor = "sv_todoterreno";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Furgonetas") && !jTextClId.getText().equals("")){
            factor = "sv_furgoneta";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Furgones") && !jTextClId.getText().equals("")){
            factor = "sv_furgon";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("SUV") && !jTextClId.getText().equals("")){
            factor = "sv_suv";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Especiales") && !jTextClId.getText().equals("")){
            factor = "sv_especial";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Moto") && !jTextClId.getText().equals("")){
            factor = "sv_moto";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }else if(sFactor.equals("Carrozados y Sobredimensionados") && !jTextClId.getText().equals("")){
            factor = "sv_carro_sobredim";
            precioFa = cliente.getDatosServicioCliente(Integer.parseInt(jTextClId.getText()), factor);
        }
        jTextTaEsClKm.setText(String.valueOf(Utilidades.redondear(precioFa, 2)));
    }//GEN-LAST:event_jComboFactorActionPerformed

    private void jTextDiasCampaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextDiasCampaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDiasCampaActionPerformed

    private void jTextUrlPrComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTextUrlPrComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextUrlPrComponentHidden

    private void jTextClIdComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTextClIdComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextClIdComponentHidden

    private void jTextKmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextKmFocusLost
        if(!jTextKm.getText().equals("")){
            Double kms = Utilidades.redondear((Double.parseDouble(jTextKm.getText()) * precioFa), 2);
            jTextTaEsCli.setText(kms.toString());
        }
    }//GEN-LAST:event_jTextKmFocusLost
 public Dimension getPreferredSize()
   {
      return new Dimension( 1170,825 );
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonMailCliente;
    private javax.swing.JButton jButtonMailProveedor;
    private javax.swing.JComboBox jCBoxEstadoVehiculo;
    private javax.swing.JCheckBox jCheckBoxObsCl;
    private javax.swing.JCheckBox jCheckBoxObsPr;
    private javax.swing.JCheckBox jCheckBoxUnidos;
    private javax.swing.JCheckBox jCheckFinUnido;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxIncidencias;
    private javax.swing.JComboBox jComboBoxProvinciaDestino;
    private javax.swing.JComboBox jComboBoxProvinciaOrigen;
    private javax.swing.JComboBox jComboBoxServicio;
    private javax.swing.JComboBox jComboBoxSoporte;
    private javax.swing.JComboBox jComboFactor;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaDestino;
    private com.toedter.calendar.JDateChooser jDateFechaOrigen;
    private com.toedter.calendar.JDateChooser jDateFechaRealDestino;
    private com.toedter.calendar.JDateChooser jDateFechaRealOrigen;
    private javax.swing.JLabel jLFMas;
    private javax.swing.JLabel jLFMenos;
    private javax.swing.JLabel jLKm;
    private javax.swing.JLabel jLTarifaCKm;
    private javax.swing.JLabel jLTarifaCKm1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNcamion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextClId;
    public javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextCodPostalDestino;
    private javax.swing.JTextField jTextCodPostalOrigen;
    private javax.swing.JTextPane jTextDescripcion;
    private javax.swing.JTextField jTextDiasCampa;
    private javax.swing.JTextField jTextDireccionDestino;
    private javax.swing.JTextField jTextDireccionOrigen;
    private javax.swing.JTextField jTextFMas;
    private javax.swing.JTextField jTextFMenos;
    private javax.swing.JTextField jTextHoraRealDestino;
    private javax.swing.JTextField jTextHoraRealOrigen;
    private javax.swing.JTextField jTextKm;
    private javax.swing.JTextField jTextMarca;
    private javax.swing.JTextField jTextMatricula;
    private javax.swing.JTextField jTextModelo;
    private javax.swing.JTextField jTextNombreDestino;
    private javax.swing.JTextField jTextNombreOrigen;
    private javax.swing.JTextField jTextNumCamion;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextPane jTextObsGeneral;
    private javax.swing.JTextPane jTextObservaciones;
    private javax.swing.JTextField jTextPeUnido;
    private javax.swing.JTextField jTextPoblacionDestino;
    private javax.swing.JTextField jTextPoblacionOrigen;
    private javax.swing.JTextField jTextProveedor;
    private javax.swing.JTextField jTextTaEsClKm;
    private javax.swing.JTextField jTextTaEsCli;
    private javax.swing.JTextField jTextTaEsProv;
    private javax.swing.JTextField jTextTarifa;
    private javax.swing.JTextField jTextTelefonoDestino;
    private javax.swing.JTextField jTextTelefonoOrigen;
    private javax.swing.JTextField jTextUrl;
    private javax.swing.JTextField jTextUrlPr;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButtonCliente;
    private javax.swing.JToggleButton jToggleButtonProveedor;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lCodPostalDestino;
    private javax.swing.JLabel lCodPostalOrigen;
    private javax.swing.JLabel lDestino;
    private javax.swing.JLabel lDireccionDestino;
    private javax.swing.JLabel lDireccionOrigen;
    private javax.swing.JLabel lEstado3;
    private javax.swing.JLabel lEstado4;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lFechaDestino;
    private javax.swing.JLabel lFechaOrigen;
    private javax.swing.JLabel lFechaRealDestino;
    private javax.swing.JLabel lFechaRealOrigen;
    private javax.swing.JLabel lHoraRealDestino;
    private javax.swing.JLabel lHoraRealOrigen;
    private javax.swing.JLabel lMarca;
    private javax.swing.JLabel lMatricula;
    private javax.swing.JLabel lModelo;
    private javax.swing.JLabel lNombreDestino;
    private javax.swing.JLabel lNombreOrigen;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lOCliente;
    private javax.swing.JLabel lOGeneralText;
    private javax.swing.JLabel lOProveedor;
    private javax.swing.JLabel lOrigen;
    private javax.swing.JLabel lPeUnidos;
    private javax.swing.JLabel lPoblacionDestino;
    private javax.swing.JLabel lPoblacionOrigen;
    private javax.swing.JLabel lProveedor;
    private javax.swing.JLabel lProvinciaDestino;
    private javax.swing.JLabel lProvinciaOrigen;
    private javax.swing.JLabel lServicio;
    private javax.swing.JLabel lServicio1;
    private javax.swing.JLabel lServicioFMad2;
    private javax.swing.JLabel lSoporte;
    private javax.swing.JLabel lTCliente1;
    private javax.swing.JLabel lTarifaCli;
    private javax.swing.JLabel lTarifaEsProv;
    private javax.swing.JLabel lTarifaEspecial;
    private javax.swing.JLabel lTarifaEspecial1;
    private javax.swing.JLabel lTelefonoDestino;
    private javax.swing.JLabel lTelefonoOrigen;
    private javax.swing.JLabel lVehiculo;
    private javax.swing.JLabel lincidencias;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
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
            jComboFactor.addItem(rs.getString("fc_nombre"));
            jComboFactor.setSelectedIndex(0);
            j++;
        }
     }

    /**
     * Buscamos la provincia de origen del pedido unido original
     * @throws SQLException
     */
    private BeanPedido getPedidoUnido(String pe_num) throws SQLException
    {
        ResultSet rs = CSDesktop.datos.select("SELECT pe_num, pe_fecha_origen, pe_direccion_origen, pe_poblacion_origen, pe_provincia_origen, " +
                                              "pe_nombre_origen, pe_telefono_origen, pe_ta_es_cliente, pe_descripcion, pe_kms, pe_soporte " +
                                              "FROM pe_pedidos WHERE pe_num = '"+pe_num+"'");
        BeanPedido pedidoUnido = new BeanPedido();
        while(rs.next())
        {
            pedidoUnido.setNum(rs.getString("pe_num"));
            pedidoUnido.setFechaOrigen(rs.getString("pe_fecha_origen"));
            pedidoUnido.setDireccionOrigen(rs.getString("pe_direccion_origen"));
            pedidoUnido.setPoblacionOrigen(rs.getString("pe_poblacion_origen"));
            pedidoUnido.setProvinciaOrigen(rs.getString("pe_provincia_origen"));
            pedidoUnido.setNombreOrigen(rs.getString("pe_nombre_origen"));
            pedidoUnido.setTelefonoOrigen(rs.getString("pe_telefono_origen"));
            pedidoUnido.setObservacionesCl(rs.getString("pe_descripcion"));
            pedidoUnido.setTarifa(rs.getString("pe_ta_es_cliente"));
            pedidoUnido.setKms(rs.getString("pe_kms"));
            pedidoUnido.setSoporte(rs.getString("pe_soporte"));
        }
        return pedidoUnido;
     }

    private void limitacionesCampos()
    {
       LimitadorDeDocumento limitadorDescripcion= new LimitadorDeDocumento(255);
       jTextDescripcion.setDocument(limitadorDescripcion);
       LimitadorDeDocumento limitadorDescProveedor= new LimitadorDeDocumento(255);
       jTextObservaciones.setDocument(limitadorDescProveedor);
       LimitadorDeDocumento limitadorDescGeneral= new LimitadorDeDocumento(255);
       jTextObsGeneral.setDocument(limitadorDescGeneral);
       LimitadorDeDocumento limitadorDireccionOrigen= new LimitadorDeDocumento(255);
       jTextDireccionOrigen.setDocument(limitadorDireccionOrigen);
       LimitadorDeDocumento limitadorPoblacionOrigen= new LimitadorDeDocumento(50);
       jTextPoblacionOrigen.setDocument(limitadorPoblacionOrigen);
       LimitadorDeDocumento limitadorCPOrigen= new LimitadorDeDocumento(5);
       jTextCodPostalOrigen.setDocument(limitadorCPOrigen);
       LimitadorDeDocumento limitadorHoraOrigen= new LimitadorDeDocumento(5);
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