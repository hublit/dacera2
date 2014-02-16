package neg;

//import utils.TablaModeloPedidos;
import data.BeanProveedor;
import data.BeanTesoreriaProveedor;
import data.Proveedor;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.table.TableCellRenderer;
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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import utils.TablaModeloVPedidos;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSValidarPedidosProveedor extends javax.swing.JPanel
{
    Date hoy = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    String anyo = sdf.format(hoy);
    int anyoAnt = (Integer.parseInt(anyo) - 1);
    private  String consulta = "";
    ArrayList pedidos = new ArrayList();
    ArrayList importe = new ArrayList();
    String regimen = "";
    String pr_id = "";
    double totalProveedor = 0;

    private DefaultListSelectionModel selectionModel;

    public CSValidarPedidosProveedor(String query) throws FileNotFoundException, IOException, SQLException
    {
        initComponents();
        inicializarTrimestres();
        consulta = query;

        TablaModeloVPedidos modelo = new TablaModeloVPedidos();
        ArrayList lista = new ArrayList();
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

        modelo.setColumnIdentifiers(new String[] {"","NUM", "FECHA", "CLIENTE" , "SERVICIO" , "ORIGEN", "DESTINO", "F.CORRECCION", "MATRICULA","MARCA","MODELO","PROVEEDOR","TAR.CL","TAR.PR", "SE" ,"SUPLE","MG","F.RECOGIDA","F.ENTREGA","F.REAL","ESTADO","OBSERVACIONES"});

        int numeroFila = 0;
        double totalCliente = 0;
        double totalSuplemento = 0;
        double totalMargen = 0;

        try
        {
            while (rs.next()) 
            {
                //Asignamos regimen para el irpf, Cambiar
                regimen = rs.getString("pr_regimen");

                pedidos.add(rs.getLong("pe_num"));
                Object[] datosFila = new Object[modelo.getColumnCount()];

                 int j = 1;
                 double ta_es_cl=0;
                 double ta_es_pr=0;
                 double s_especial=0;
                 double importeServicioD = 0;
                 double suple=0;
                 double ganancia=0;
                 pr_id = rs.getString("pr_id");
                 String fechaPe = rs.getString("pe_fecha");
                for (int k = 0; k < 21; k++)
                {
                    if((k==1) || (k==16)|| (k==17) || (k==18))
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
                    else if(k==11)
                    {
                         ta_es_cl=rs.getDouble(k+1);
                         datosFila[j] = rs.getDouble(k + 1);
                    }
                    else if(k==12)
                    {
                        ta_es_pr=rs.getDouble(k+1);
                        datosFila[j] = rs.getDouble(k + 1);
                        importe.add(rs.getDouble(k + 1));
                        totalProveedor = totalProveedor + ta_es_pr;
                        totalProveedor = Utilidades.redondear(totalProveedor, 2);
                    }
                    else if (k==14)
                    {
                        suple = rs.getDouble(k+1);
                        datosFila[j] = suple;
                        totalSuplemento = totalSuplemento + suple;
                        totalSuplemento = Utilidades.redondear(totalSuplemento, 2);
                    }
                    else if (k==15)
                    {
                        ganancia = ((ta_es_cl + s_especial) + suple) - ta_es_pr;
                        double gananciaF=Utilidades.redondear(ganancia, 2);
                        datosFila[j] = gananciaF;

                        totalMargen = totalMargen + gananciaF;
                        totalMargen = Utilidades.redondear((totalMargen), 2);
                    }
                    else
                    {
                        datosFila[j] = rs.getObject(k + 1);
                    }
                    j++;
                }
                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
            Object[] datosFilaTotal = new Object[modelo.getColumnCount()];
            int i = 0;
            for (int k = 0; k < 19; k++)
            {
                if(k==11)
                {
                    datosFilaTotal[i] = "TOTALES";
                }
                if(k==12)
                {
                    datosFilaTotal[i] = totalCliente;
                }
                if(k==13)
                {
                    datosFilaTotal[i] = totalProveedor;
                }
                if(k==15)
                {
                    datosFilaTotal[i] = totalSuplemento;
                }
                if(k==16)
                {
                    datosFilaTotal[i] = totalMargen;
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
            CSDesktop.ResultValidacionPedidos = new JInternalFrame("Resultado Búsqueda Pedidos", true, false, true, true );
            CSDesktop.ResultValidacionPedidos.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultValidacionPedidos.pack();
            CSDesktop.elEscritorio.add( CSDesktop.ResultValidacionPedidos );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultValidacionPedidos.getSize();
            CSDesktop.ResultValidacionPedidos.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultValidacionPedidos.setVisible( true );
        }
        //initComponents();
        Date hoy = new Date();
        jDateChooserFechaCont.setDate(hoy);
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());
        //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(50);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(80);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(200);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(80);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(120);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(120);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(80);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(100);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(100);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(120);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(200);
        TableColumn columna12 = jTable1.getColumnModel().getColumn(12);
        columna12.setPreferredWidth(60);
        TableColumn columna13 = jTable1.getColumnModel().getColumn(13);
        columna13.setPreferredWidth(60);
        TableColumn columna14 = jTable1.getColumnModel().getColumn(14);
        columna14.setPreferredWidth(60);
        TableColumn columna15 = jTable1.getColumnModel().getColumn(15);
        columna15.setPreferredWidth(60);
        TableColumn columna16 = jTable1.getColumnModel().getColumn(16);
        columna16.setPreferredWidth(60);
        TableColumn columna17 = jTable1.getColumnModel().getColumn(17);
        columna17.setPreferredWidth(80);
        TableColumn columna18 = jTable1.getColumnModel().getColumn(18);
        columna18.setPreferredWidth(80);
        TableColumn columna19 = jTable1.getColumnModel().getColumn(19);
        columna19.setPreferredWidth(80);
         TableColumn columna20 = jTable1.getColumnModel().getColumn(20);
        columna20.setPreferredWidth(100);
        TableColumn columna21 = jTable1.getColumnModel().getColumn(21);
        columna21.setPreferredWidth(500);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);

        selectionModel = (DefaultListSelectionModel) jTable1.getSelectionModel();
        jTable1.setRowSelectionAllowed(true);
        jTable1.setSelectionMode(selectionModel.MULTIPLE_INTERVAL_SELECTION);

        jTable1.setAutoCreateRowSorter(true);

    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1400,700 );
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
  
    /**
     *
     */
    public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer
    {
        public MyComboBoxRenderer(String[] items)
        {
            super(items);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                if(value.equals("SELECC.")){
                    Color fondo = new  Color(247, 174, 40);
                    setForeground(Color.BLACK);
                    super.setBackground(fondo);
                }else{
                    setForeground(table.getSelectionForeground());
                    super.setBackground(table.getSelectionBackground());
                }
            }
            else
            {
               setForeground(table.getForeground());
               setBackground(table.getBackground());
            }

            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }
     
     /**
     *
     */
    public class MyComboBoxEditor extends DefaultCellEditor
    {
        public MyComboBoxEditor(String[] items)
        {
            super(new JComboBox(items));
        }
    }

    /**
     *
     */
    public class MyCheckBoxRenderer extends JCheckBox implements TableCellRenderer
    {
        public MyCheckBoxRenderer()
        {
            super();
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                setSelected(true);
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            }
            else
            {
                setSelected(false);
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            // Select the current value
            return this;
        }
    }

    /**
     *
     */
    public class MyCheckBoxEditor extends DefaultCellEditor
    {
        public MyCheckBoxEditor()
        {
            super(new JCheckBox(pr_id, true));
        }
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
        jButtonValidar = new javax.swing.JButton();
        jLabelNumFa = new javax.swing.JLabel();
        jTextFieldNFa = new javax.swing.JTextField();
        jDateChooserFechaFa = new com.toedter.calendar.JDateChooser();
        jLabelFechaFa = new javax.swing.JLabel();
        jLabelAnyoIva = new javax.swing.JLabel();
        jComboBoxAnyoIva = new javax.swing.JComboBox();
        jLabelObservaciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservaciones = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelIvaTrimestre = new javax.swing.JLabel();
        jComboBoxIvaTrimestre = new javax.swing.JComboBox();
        jLabelIvaTipo = new javax.swing.JLabel();
        jComboBoxIvaTipo = new javax.swing.JComboBox();
        jLabelFechaCont = new javax.swing.JLabel();
        jDateChooserFechaCont = new com.toedter.calendar.JDateChooser();
        jButtonTriUno = new javax.swing.JButton();
        jButtonTriDos = new javax.swing.JButton();
        jButtonTriTres = new javax.swing.JButton();
        jButtonTriCuatro = new javax.swing.JButton();
        ltrimestre = new javax.swing.JLabel();

        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(1400, 670));

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

        jButtonValidar.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButtonValidar.setForeground(new java.awt.Color(0, 0, 100));
        jButtonValidar.setText("Validar Pedidos");
        jButtonValidar.setName("jButtonValidar"); // NOI18N
        jButtonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidarActionPerformed(evt);
            }
        });

        jLabelNumFa.setForeground(new java.awt.Color(0, 0, 100));
        jLabelNumFa.setText("Núm. Factura");
        jLabelNumFa.setName("jLabelNumFa"); // NOI18N

        jTextFieldNFa.setName("jTextFieldNFa"); // NOI18N
        jTextFieldNFa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNFaActionPerformed(evt);
            }
        });

        jDateChooserFechaFa.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateChooserFechaFa.setName("jDateChooserFechaFa"); // NOI18N

        jLabelFechaFa.setForeground(new java.awt.Color(0, 0, 100));
        jLabelFechaFa.setText("Fecha factura");
        jLabelFechaFa.setName("jLabelFechaFa"); // NOI18N

        jLabelAnyoIva.setForeground(new java.awt.Color(0, 0, 100));
        jLabelAnyoIva.setText("IVA año");
        jLabelAnyoIva.setName("jLabelAnyoIva"); // NOI18N

        jComboBoxAnyoIva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016", "2015", "2014", "2013", "2012", "2011", "2010" }));
        jComboBoxAnyoIva.setSelectedIndex(2);
        jComboBoxAnyoIva.setName("jComboBoxAnyoIva"); // NOI18N
        jComboBoxAnyoIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnyoIvaActionPerformed(evt);
            }
        });

        jLabelObservaciones.setForeground(new java.awt.Color(0, 0, 100));
        jLabelObservaciones.setText("Observaciones");
        jLabelObservaciones.setName("jLabelObservaciones"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextAreaObservaciones.setColumns(20);
        jTextAreaObservaciones.setRows(5);
        jTextAreaObservaciones.setName("jTextAreaObservaciones"); // NOI18N
        jScrollPane2.setViewportView(jTextAreaObservaciones);

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        jLabelIvaTrimestre.setForeground(new java.awt.Color(0, 0, 100));
        jLabelIvaTrimestre.setText("IVA Trimestre");
        jLabelIvaTrimestre.setName("jLabelIvaTrimestre"); // NOI18N

        jComboBoxIvaTrimestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1T", "2T", "3T", "4T" }));
        jComboBoxIvaTrimestre.setName("jComboBoxIvaTrimestre"); // NOI18N
        jComboBoxIvaTrimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIvaTrimestreActionPerformed(evt);
            }
        });

        jLabelIvaTipo.setForeground(new java.awt.Color(0, 0, 100));
        jLabelIvaTipo.setText("IVA Tipo");
        jLabelIvaTipo.setName("jLabelIvaTipo"); // NOI18N

        jComboBoxIvaTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21%", "18%", "8%", "4%", "5%", "2%", "0%", " " }));
        jComboBoxIvaTipo.setName("jComboBoxIvaTipo"); // NOI18N

        jLabelFechaCont.setForeground(new java.awt.Color(0, 0, 100));
        jLabelFechaCont.setText("Fecha contabilización");
        jLabelFechaCont.setName("jLabelFechaCont"); // NOI18N

        jDateChooserFechaCont.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateChooserFechaCont.setName("jDateChooserFechaCont"); // NOI18N

        jButtonTriUno.setText("Trimestre 1");
        jButtonTriUno.setName("jButtonTriUno"); // NOI18N
        jButtonTriUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTriUnoActionPerformed(evt);
            }
        });

        jButtonTriDos.setText("Trimestre 2");
        jButtonTriDos.setName("jButtonTriDos"); // NOI18N
        jButtonTriDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTriDosActionPerformed(evt);
            }
        });

        jButtonTriTres.setText("Trimestre 3");
        jButtonTriTres.setName("jButtonTriTres"); // NOI18N
        jButtonTriTres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTriTresActionPerformed(evt);
            }
        });

        jButtonTriCuatro.setText("Trimestre 4");
        jButtonTriCuatro.setName("jButtonTriCuatro"); // NOI18N
        jButtonTriCuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTriCuatroActionPerformed(evt);
            }
        });

        ltrimestre.setFont(new java.awt.Font("Tahoma", 1, 11));
        ltrimestre.setForeground(new java.awt.Color(170, 16, 4));
        ltrimestre.setText("Deshabilitar trimestres");
        ltrimestre.setName("ltrimestre"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1380, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                        .addComponent(jButtonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1374, Short.MAX_VALUE))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonTriUno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonTriDos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTriTres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTriCuatro))
                    .addComponent(ltrimestre))
                .addContainerGap(1020, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelFechaCont, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIvaTrimestre, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxIvaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabelAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabelIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(872, 872, 872))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooserFechaCont, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabelFechaFa, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jDateChooserFechaFa, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabelNumFa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNFa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabelObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldNFa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelNumFa)
                                .addComponent(jLabelObservaciones))
                            .addComponent(jLabelFechaCont)
                            .addComponent(jDateChooserFechaFa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFechaFa)
                            .addComponent(jDateChooserFechaCont, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIvaTrimestre)
                    .addComponent(jComboBoxAnyoIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIvaTipo)
                    .addComponent(jLabelAnyoIva)
                    .addComponent(jComboBoxIvaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxIvaTrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ltrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTriUno)
                    .addComponent(jButtonTriDos)
                    .addComponent(jButtonTriTres)
                    .addComponent(jButtonTriCuatro))
                .addGap(30, 30, 30)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerrar))
                .addGap(23, 23, 23))
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultValidacionPedidos.dispose();
        CSDesktop.menuBuscarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidarActionPerformed
        boolean informe = false;
        int longitud = jTable1.getSelectedRowCount();
        int[] celdas = jTable1.getSelectedRows();
        String fechaCo = "";
        String fechaFac = "";
        int mes = 0;

        String numFa = jTextFieldNFa.getText();
        Calendar fechaFa = jDateChooserFechaFa.getCalendar();

        if (fechaFa != null) {
            Date fecha = fechaFa.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFac = formatoDeFecha.format(fecha);
        }

        Calendar fechaContabilizacion = jDateChooserFechaCont.getCalendar();
        String mesCo = "";
        if (fechaContabilizacion != null) {
            Date fecha = fechaContabilizacion.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaCo = formatoDeFecha.format(fecha);
            String[] arrayFaCo = fechaCo.split("-");
            mesCo = arrayFaCo[1];
        }

        mes = (Integer.parseInt(mesCo));

        //comprobamos los trimestres deshabilitados
        boolean primerTimestre = false;
        boolean segundoTimestre = false;
        boolean tercerTimestre = false;
        boolean cuartoTimestre = false;

        try {
            //Estado trimestres
            if (getTrimestreIva(anyo, "primero") && mes < 4)
            {
                primerTimestre = true;
            }
            if (getTrimestreIva(anyo, "segundo") && mes < 7)
            {
                segundoTimestre = true;
            }
            if (getTrimestreIva(anyo, "tercero") && mes < 10)
            {
                tercerTimestre = true;
            }
            //if (getTrimestreIva(String.valueOf(anyoAnt), "cuarto") && mes > 10)
            if (getTrimestreIva(anyo, "cuarto") && mes > 10)
            {
                cuartoTimestre = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(longitud == 0)
        {
            jButtonValidar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Debes seleccionar algún pedido.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonValidar.setEnabled(true);
        }
        else if(primerTimestre || segundoTimestre || tercerTimestre || cuartoTimestre)
        {
            jButtonValidar.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>La fecha de contabilización no puede ser menor a un trimestre deshabilitado.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
            jButtonValidar.setEnabled(true);
        }
        else if (!Utilidades.campoObligatorio(fechaFac,"Fecha Factura").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fechaFac,"Fecha Factura"));
        }
        else if (!Utilidades.campoObligatorio(fechaCo,"Fecha Contabilización").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fechaCo,"Fecha Contabilización"));
        }
        else if (!Utilidades.campoObligatorio(numFa,"Número Factura").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(numFa,"Número Factura"));
        }
        else
        {

            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres validar los pedidos seleccionados?");

            if (JOptionPane.OK_OPTION == confirmado)
            {
                String num_carset = "";
                String fechaCont = "";
                boolean rsUp = false;
                String query = "SELECT tr_num_carset FROM tr_tesoreria_proveedor ORDER BY tr_id DESC LIMIT 1";
                ResultSet rs = CSDesktop.datos.select(query);
                try
                {
                    while (rs.next())
                    {
                        num_carset = rs.getString("tr_num_carset");
                    }
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CSFacturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                    String ivaTrimestre = jComboBoxIvaTrimestre.getSelectedItem().toString();
                    String ivaAnyo = jComboBoxAnyoIva.getSelectedItem().toString();

                    if (num_carset != null && !num_carset.equals(""))
                    {
                        String[] arrayNumCarset = num_carset.split("/");
                        int numCarset = (Integer.parseInt(arrayNumCarset[2])) + 1;
                        String numero = Utilidades.rellenarCeros(String.valueOf(numCarset), 4);
                        String[] fecha = fechaCo.split("-");
                        String anyo=fecha[0];
                        num_carset = ivaTrimestre+ "/" +anyo + "/" +numero;

                        //num_carset = ivaTrimestre+"/"+ivaAnyo+"/"+numero;
                    }
                    else
                    {
                        num_carset = anyo + "/0001";
                    }

                    String observaciones = jTextAreaObservaciones.getText();
                    BeanTesoreriaProveedor tsProveedor = new BeanTesoreriaProveedor();
                    tsProveedor.setTr_fecha(fechaFa);
                    tsProveedor.setTr_fh_vencimiento(fechaContabilizacion);
                    tsProveedor.setTr_num(numFa);
                    tsProveedor.setTr_num_carset(num_carset);
                    tsProveedor.setPr_num(pr_id);
                    String[] textoIva =  (jComboBoxIvaTipo.getSelectedItem().toString()).split("%");
                    Double iva = Double.valueOf(textoIva[0]);
                    tsProveedor.setTr_iva(iva);
                    tsProveedor.setTr_irpf(0.0);
                    tsProveedor.setTr_regimen(regimen);
                    tsProveedor.setTr_importe_neto(0.0);
                    tsProveedor.setTr_observaciones(observaciones);

                    //importe para la nueva linea del informe
                    double importe_pr = 0;

                    for(int i = 0; i < longitud; i++)
                    {
                        importe_pr = importe_pr + Double.parseDouble(importe.get(celdas[i]).toString());
                    }

                    tsProveedor.setTr_importe(importe_pr);

                    try
                    {
                        //guardamos los datos de la tesoreria
                        informe = guardarTesoreria(tsProveedor);

                        if (informe)
                        {
                            for(int i = 0; i < longitud; i++)
                            {
                                String queryUpdate = "UPDATE pe_pedidos SET pe_estado = 'Facturado y Validado', pe_num_fa_pr = '"+numFa+"' WHERE pe_num = '"+pedidos.get(celdas[i])+"'";
                                rsUp = CSDesktop.datos.manipuladorDatos(queryUpdate);
                            }
                            if(rsUp)
                            {
                                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al cambiar el estado de los pedidos</FONT></HTML>");
                                JOptionPane.showMessageDialog(null,errorFields);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
             }
        }
    }//GEN-LAST:event_jButtonValidarActionPerformed

        private void jTextFieldNFaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNFaActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jTextFieldNFaActionPerformed

        private void jComboBoxIvaTrimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIvaTrimestreActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jComboBoxIvaTrimestreActionPerformed

        private void jButtonTriUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTriUnoActionPerformed

            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres deshabilitar el primer trimestre?");
            if (JOptionPane.OK_OPTION == confirmado)
            {
                try {
                    setTrimestreIva(anyo, "primero");
                } catch (SQLException ex) {
                    Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                jButtonTriUno.setEnabled(false);
            }
        }//GEN-LAST:event_jButtonTriUnoActionPerformed

        private void jButtonTriDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTriDosActionPerformed
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres deshabilitar el segundo trimestre?");
            if (JOptionPane.OK_OPTION == confirmado)
            {
                try {
                    setTrimestreIva(anyo, "segundo");
                } catch (SQLException ex) {
                    Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                jButtonTriDos.setEnabled(false);
            }
        }//GEN-LAST:event_jButtonTriDosActionPerformed

        private void jButtonTriTresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTriTresActionPerformed
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres deshabilitar el tercer trimestre?");
            if (JOptionPane.OK_OPTION == confirmado)
            {
                try {
                    setTrimestreIva(anyo, "tercero");
                } catch (SQLException ex) {
                    Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                jButtonTriTres.setEnabled(false);
            }
        }//GEN-LAST:event_jButtonTriTresActionPerformed

        private void jButtonTriCuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTriCuatroActionPerformed
            int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres deshabilitar el cuarto trimestre?");
            if (JOptionPane.OK_OPTION == confirmado)
            {
                try {
                    setTrimestreIva(anyo, "cuarto");
                } catch (SQLException ex) {
                    Logger.getLogger(CSValidarPedidosProveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                jButtonTriCuatro.setEnabled(false);
            }
        }//GEN-LAST:event_jButtonTriCuatroActionPerformed

        private void jComboBoxAnyoIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnyoIvaActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_jComboBoxAnyoIvaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonTriCuatro;
    private javax.swing.JButton jButtonTriDos;
    private javax.swing.JButton jButtonTriTres;
    private javax.swing.JButton jButtonTriUno;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JComboBox jComboBoxAnyoIva;
    private javax.swing.JComboBox jComboBoxIvaTipo;
    private javax.swing.JComboBox jComboBoxIvaTrimestre;
    private com.toedter.calendar.JDateChooser jDateChooserFechaCont;
    private com.toedter.calendar.JDateChooser jDateChooserFechaFa;
    private javax.swing.JLabel jLabelAnyoIva;
    private javax.swing.JLabel jLabelFechaCont;
    private javax.swing.JLabel jLabelFechaFa;
    private javax.swing.JLabel jLabelIvaTipo;
    private javax.swing.JLabel jLabelIvaTrimestre;
    private javax.swing.JLabel jLabelNumFa;
    private javax.swing.JLabel jLabelObservaciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaObservaciones;
    private javax.swing.JTextField jTextFieldNFa;
    private javax.swing.JLabel ltrimestre;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public class MiRender extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);

            //se centran los valores
            jTable1.setRowHeight(20);

            // These are the combobox values
/*            String[] values = new String[]{"","SELECC."};
          
            TableColumn col = table.getColumnModel().getColumn(column);
            
            if (column == 0)
            {
                col.setCellEditor(new MyComboBoxEditor(values));
                col.setCellRenderer(new MyComboBoxRenderer(values));
                jTable1.setValueAt(value, row, column);
            }
*/
            TableColumn col = table.getColumnModel().getColumn(column);
            if (column == 0)
            {
                col.setCellEditor(new MyCheckBoxEditor());
                col.setCellRenderer(new MyCheckBoxRenderer());

                this. setHorizontalAlignment(SwingConstants.CENTER);

                jTable1.setValueAt(value, row, column);
            }

            if (column == 2 || column == 17 || column == 18 || column == 19 )
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if (column == 12 ||column == 13 || column == 14 || column == 15 || column == 16 )
            {
                this. setHorizontalAlignment(SwingConstants.RIGHT);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }

            if (row % 2 ==1)
            {
                Color fondo = new  Color(206, 227, 242);
                cell.setBackground(fondo);
                cell.setForeground(Color.DARK_GRAY);
            }
            else
            {
                cell.setBackground(Color.white);
                cell.setForeground(Color. BLACK);
            }

            if(isSelected==true)
            {
                Color fondo = new  Color(247, 174, 40);
                cell. setBackground(fondo);
                cell. setForeground(Color.BLACK);
            }

            //si no cumplen esa condicion pongo las celdas en color blanco
            if (table. getValueAt(row, 11).toString().equals("TOTALES"))
            {
                Color fondo = new  Color(244, 144, 144);
                cell. setBackground(fondo);
                cell. setForeground(Color. BLACK);
                cell.setFont(new Font(null, Font.BOLD, 12));
            }

            return cell;
        }
    }


    /**
     * Guardamos en la base de datos los datos seleccionados de tesorería
     * @param ts
     * @param fechaFac
     * @param fechaCont
     * @param numFa
     * @param numCarset
     * @param observaciones
     */
    public boolean guardarTesoreria(BeanTesoreriaProveedor ts) throws SQLException
    {
        Proveedor pr = new Proveedor();
        BeanProveedor beanPr = new BeanProveedor();
        int pr_num = Integer.parseInt(ts.getPr_num());
        beanPr = pr.getDatosProveedor(pr_num);
        String diasPlazo = beanPr.getPlazoPago().substring(0, 2);
        String banco = "";
        String prTipo = beanPr.getTipo();
        Double importeProveedor = ts.getTr_importe();
        String regPr = ts.getTr_regimen();
        Double iva = ts.getTr_iva();
        Double totalIva  = (importeProveedor * iva) /100;
        totalIva = Utilidades.redondear(totalIva, 2);
        Double irpf = 0.0;

        if(regPr.equalsIgnoreCase("Autonomo") ||regPr.equalsIgnoreCase("Autónomo"))
        {
            irpf = (importeProveedor * 1) /100;
            irpf = Utilidades.redondear(irpf, 2);
        }

        Double totalImporte = importeProveedor + totalIva - irpf;
        totalImporte = Utilidades.redondear(totalImporte, 2);
        //Fecha de factura
        String fechaFactura = "";
        if(ts.getTr_fecha() != null)
        {
            Date fecha = ts.getTr_fecha().getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaFactura=formatoDeFecha.format(fecha);
        }
        String fechaPago = "";
        if (ts.getTr_fecha()!=null)
        {
            if (beanPr.getPlazoPago().equals("Especial"))
            {
                diasPlazo = beanPr.getDiasPlazo();
            }
            int plazo = Integer.parseInt(diasPlazo);
            //sumamos a la fecha de la factura el plazo de pago del proveedor
            ts.getTr_fecha().add(Calendar.DATE, plazo);

            Date fecha = ts.getTr_fecha().getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaPago=formatoDeFecha.format(fecha);

            fechaPago="2050-01-01"; //Fecha de pago por defecto
        }

        Calendar fechaCont = jDateChooserFechaCont.getCalendar();
        String fechaContabilizacion = "";
        if (fechaCont!=null)
        {
            Date fecha = fechaCont.getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaContabilizacion = formatoDeFecha.format(fecha);
        }
        if (!Utilidades.campoObligatorio(fechaContabilizacion,"Fecha").equals("OK"))
        {
            ValidarFormatos(Utilidades.campoObligatorio(fechaContabilizacion,"Fecha"));
        }

            int confirmado = JOptionPane.showConfirmDialog(this, " Importe = '"+importeProveedor+"' \n Iva = '"+totalIva+"' \n Irpf = '"+irpf+"' \n Total = '"+totalImporte+"'\n Factura = '"+ts.getTr_num_carset()+"'");

            if (JOptionPane.OK_OPTION == confirmado)
            {

                String query = "INSERT INTO tr_tesoreria_proveedor (tr_fecha, tr_num, tr_num_carset, pr_num, tr_importe_neto, " +
                                                            "tr_iva, tr_irpf, tr_importe, tr_estado, tr_fecha_pago, " +
                                                            "tr_banco, tr_observaciones, tr_fecha_cont) " +
                                                            "VALUES ('"+fechaFactura+"', '"+ts.getTr_num()+"', " +
                                                            "'"+ts.getTr_num_carset()+"', '"+pr_num+"', '"+importeProveedor+"', " +
                                                            "'"+totalIva+"', '"+irpf+"', '"+totalImporte+"', 'PTE', '"+fechaPago+"', " +
                                                            "'"+banco+"', '"+ts.getTr_observaciones()+"', '"+fechaContabilizacion+"')";
                System.out.println(query);
                boolean rsIn = CSDesktop.datos.manipuladorDatos(query);

                if(rsIn)
                {
                    jButtonValidar.setEnabled(false);
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);
                    jButtonValidar.setEnabled(true);

                    return false;
                }
                else
                {
                    CSDesktop.ResultValidacionPedidos.dispose();
                   //CSDesktop.BuscarValidacionPedidos.dispose();
                    CSDesktop.menuTesoreriaValidacion.setEnabled(true);

                    return true;
                }
            }
            else
            {
                return false;
            }
    }

     public void ValidarFormatos(String accion)
    {
         jButtonValidar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonValidar.setEnabled(true);
    }

    /**
     * Comprobar el estado del trimestre para Iva
     * @param anyo
     * @param trimestre
     * @throws SQLException
     */
      private void inicializarTrimestres() throws SQLException
      {
        ResultSet rs = CSDesktop.datos.select("SELECT ti_id, ti_anyo, ti_trimestre, ti_estado FROM ti_trimestre_iva WHERE ti_anyo = "+anyo);
        while(rs.next())
        {
            if (rs.getString("ti_trimestre").equals("primero") && rs.getBoolean("ti_estado"))
            {
                jButtonTriUno.setEnabled(false);
                jComboBoxIvaTrimestre.setSelectedIndex(0);
            }
            else if(rs.getString("ti_trimestre").equals("segundo") && rs.getBoolean("ti_estado"))
            {
                jButtonTriDos.setEnabled(false);
                jComboBoxIvaTrimestre.setSelectedIndex(1);
            }
            else if(rs.getString("ti_trimestre").equals("tercero") && rs.getBoolean("ti_estado"))
            {
                jButtonTriTres.setEnabled(false);
                jComboBoxIvaTrimestre.setSelectedIndex(2);
            }else{
                jComboBoxIvaTrimestre.setSelectedIndex(3);
            }
            
        }
        rs.close();
        String query = "SELECT ti_id, ti_anyo, ti_trimestre, ti_estado FROM ti_trimestre_iva WHERE ti_trimestre = 'cuarto' AND ti_anyo = "+anyoAnt;
        System.out.println(query);
        ResultSet rsCuatro = CSDesktop.datos.select("SELECT ti_id, ti_anyo, ti_trimestre, ti_estado FROM ti_trimestre_iva WHERE ti_trimestre = 'cuarto' AND ti_trimestre = ti_anyo = "+anyoAnt);
        while(rsCuatro.next())
        {
            if (rs.getBoolean("ti_estado"))
            {
                jButtonTriCuatro.setEnabled(false);
            }
        }
        rsCuatro.close();
     }

    /**
     * Comprobar el estado del trimestre para Iva
     * @param anyo
     * @param trimestre
     * @throws SQLException
     */
      private boolean getTrimestreIva(String anyo, String trimestre) throws SQLException
      {
        ResultSet rs = CSDesktop.datos.select("SELECT ti_id, ti_estado FROM ti_trimestre_iva WHERE ti_anyo = '"+anyo+"' AND ti_trimestre = '"+trimestre+"'");

        Boolean valor = false;
        while(rs.next())
        {
            valor = rs.getBoolean("ti_estado");
        }

        return valor;
     }

       /**
     * Deshabilitamos el trimestre para Iva
     * @param anyo
     * @param trimestre
     * @throws SQLException
     */
    private void setTrimestreIva(String anyo, String trimestre) throws SQLException
    {
        String query = "INSERT INTO ti_trimestre_iva (ti_anyo, ti_trimestre, ti_estado)" +
                                             " VALUES ('"+anyo+"', '"+trimestre+"', 1)";
        System.out.println(query);
        boolean rsIn = CSDesktop.datos.manipuladorDatos(query);
     }
}