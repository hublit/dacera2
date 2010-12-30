/*
 * ABResultBuscarPedido.java
 *
 * Created on 19-oct-2009, 15:58:57
 */

package neg;

//import utils.TablaModeloPedidos;
import com.lowagie.text.pdf.ColumnText;
import groovy.inspect.swingui.TableSorter;
import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import utils.Utilidades;

/**
 *
 * @author depr102
 */
public class CSResultBuscarSinPrPedido extends javax.swing.JPanel
{
    /** Creates new form ABResultBuscarPedido */
    public CSResultBuscarSinPrPedido(String query)
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

        modelo.setColumnIdentifiers(new String[] {"NUM", "FECHA", "CLIENTE" , "SERVICIO" , "ORIGEN", "DESTINO", "F.CORRECCION", "MATRICULA","MARCA","MODELO","ESTADO","OBSERVACIONES"});

        int numeroFila = 0;
        double totalCliente = 0;
        double totalProveedor = 0;
        double totalSuplemento = 0;
        double totalMargen = 0;

        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                 double ta_es_cl=0;
                        double ta_es_pr=0;
                        double suple=0;
                        double ganancia=0;
                for (int k = 0; k < 18; k++) {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k==6 || k == 7 || k == 8 || k==9 || k==16 || k==17) {
                        if((k==1))
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
                            System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                        }
                        j++;

                        
                    }
                   
                }

                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();


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
            CSDesktop.ResultPedido = new JInternalFrame("Resultado Búsqueda Pedidos", true, false, false, true );
            CSDesktop.ResultPedido.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultPedido.pack();
            CSDesktop.BuscarPedido.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultPedido );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultPedido.getSize();
            CSDesktop.ResultPedido.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultPedido.setVisible( true );
        }
        initComponents();
        jTable1.setModel(modelo);
        jTable1.setDefaultRenderer (Object.class, new MiRender());

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(80);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(200);
        TableColumn columna3 = jTable1.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(80);
        TableColumn columna4 = jTable1.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(120);
        TableColumn columna5 = jTable1.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(120);
        TableColumn columna6 = jTable1.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(80);
        TableColumn columna7 = jTable1.getColumnModel().getColumn(7);
        columna7.setPreferredWidth(100);
        TableColumn columna8 = jTable1.getColumnModel().getColumn(8);
        columna8.setPreferredWidth(100);
        TableColumn columna9 = jTable1.getColumnModel().getColumn(9);
        columna9.setPreferredWidth(120);
        TableColumn columna10 = jTable1.getColumnModel().getColumn(10);
        columna10.setPreferredWidth(80);
        TableColumn columna11 = jTable1.getColumnModel().getColumn(11);
        columna11.setPreferredWidth(500);

        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
        tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        //jTable1.getTableHeader().setPreferredSize(new Dimension(jTable1.getTableHeader().getWidth(),26));
        jTable1.getTableHeader().setBackground(Color.GRAY);
        jTable1.getTableHeader().setForeground(Color.white);        

        jTable1.setAutoCreateRowSorter(true);

        jTable1.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = jTable1.rowAtPoint(e.getPoint());
            int columna = jTable1.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {
               int proveedor = Integer.parseInt((String)jTable1.getValueAt(fila,0).toString());
               CSDesktop.EditarPedido = new JInternalFrame("Editar Pedido", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarPedido editarC = null;
                    try {
                        editarC = new CSEditarPedido(proveedor);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarSinPrPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
               CSDesktop.EditarPedido.getContentPane().add( editarC,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarPedido.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarPedido );

               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarPedido.getSize();
               CSDesktop.EditarPedido.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarPedido.setVisible( true );
               CSDesktop.ResultPedido.setVisible(false);

            System.out.println(jTable1.getValueAt(fila,columna));
         }
        }
        }    );
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonCerrar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCerrar)
                .addContainerGap())
        );

        jButtonCerrar.getAccessibleContext().setAccessibleParent(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        CSDesktop.ResultPedido.dispose();
        CSDesktop.menuBuscarPedido.setEnabled(true);
    }//GEN-LAST:event_jButtonCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores

            jTable1.setRowHeight(20);

            if (column == 0 ||column == 1)
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
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
            return cell;
        }
    }
}