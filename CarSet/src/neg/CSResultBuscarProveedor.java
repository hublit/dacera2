/*
 * ABResultBuscarProveedores.java
 *
 * Created on 07-oct-2009, 22:40:56
 */
package neg;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import neg.CSResultBuscarCliente.MiRender;

/**
 *
 * @author lito
 */
public class CSResultBuscarProveedor extends javax.swing.JPanel
{
    /** Creates new form ABResultBuscarProveedores */
    public CSResultBuscarProveedor(String query)
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
                    jButton1.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        addKeyListener(l);

        modelo.setColumnIdentifiers(new String[] {"NUM", "NOMBRE", "TIPO", "PROVINCIA", "PLAZO DE PAGO", "FORMA DE PAGO" });

        int numeroFila = 0;
        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 24; k++)
                {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4|| k == 5)
                    {
                        datosFila[j] = rs.getObject(k + 1);
                        System.out.println("Dato" + k + " " + rs.getObject(k + 1));
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
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Proveedores con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else
        {
            CSDesktop.ResultProveedor = new JInternalFrame("Resultado Búsqueda Proveedores", true, false, false, true );
            CSDesktop.ResultProveedor.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultProveedor.pack();
            CSDesktop.BuscarProveedor.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultProveedor );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultProveedor.getSize();
            CSDesktop.ResultProveedor.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultProveedor.setVisible( true );
        }
        initComponents();
        tablaProveedores.setModel(modelo);
        tablaProveedores.setDefaultRenderer (Object.class, new MiRender());
        tablaProveedores.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        //tablaProveedores.getTableHeader().setPreferredSize(new Dimension(tablaProveedores.getTableHeader().getWidth(),26));
        tablaProveedores.getTableHeader().setBackground(Color.GRAY);
        tablaProveedores.getTableHeader().setForeground(Color.white);

        TableColumn columna = tablaProveedores.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = tablaProveedores.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(300);
        TableColumn columna2 = tablaProveedores.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);
        TableColumn columna3 = tablaProveedores.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(100);
        TableColumn columna4 = tablaProveedores.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(100);
        TableColumn columna5 = tablaProveedores.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(110);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        tablaProveedores.setAutoCreateRowSorter(true);

        tablaProveedores.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = tablaProveedores.rowAtPoint(e.getPoint());
            int columna = tablaProveedores.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {
               int proveedor = Integer.parseInt((String)tablaProveedores.getValueAt(fila,0).toString());
               CSDesktop.EditarProveedor = new JInternalFrame("Editar Proveedor", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarProveedor editarC = null;
                    try {
                        editarC = new CSEditarProveedor(proveedor);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarProveedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
               CSDesktop.EditarProveedor.getContentPane().add( editarC,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarProveedor.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarProveedor );

               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarProveedor.getSize();
               CSDesktop.EditarProveedor.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarProveedor.setVisible( true );
               CSDesktop.ResultProveedor.setVisible(false);

            System.out.println(tablaProveedores.getValueAt(fila,columna));
         }
        }
        }    );
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 850,600 );
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
        tablaProveedores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaProveedores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProveedores.setGridColor(new java.awt.Color(204, 204, 255));
        tablaProveedores.setName("tablaProveedores"); // NOI18N
        jScrollPane1.setViewportView(tablaProveedores);

        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Cancelar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(366, 366, 366))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CSDesktop.ResultProveedor.dispose();
        CSDesktop.menuBuscarProveedor.setEnabled(true);
}//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProveedores;
    // End of variables declaration//GEN-END:variables

    public class MiRender extends DefaultTableCellRenderer {

    @SuppressWarnings("static-access")
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        //se centran los valores
        //se toman algunos valores especificos para mi programa
        //double cantidad = Double. parseDouble(table. getValueAt(row, 11). toString());
        //double stockMin = Double. parseDouble(table. getValueAt(row, 12). toString());
        //double stockMax = Double. parseDouble(table. getValueAt(row, 13). toString());
        //si cumplen x condicion se pintan
        if (column == 0 ||column == 2)
        {
            this. setHorizontalAlignment(SwingConstants.CENTER);
        }
        else
        {
            this. setHorizontalAlignment(SwingConstants.LEFT);
        }

        tablaProveedores.setRowHeight(20);

        if (row % 2 ==1)
        {
            Color fondo = new  Color(206, 227, 242);
            cell. setBackground(fondo);
            cell. setForeground(Color. DARK_GRAY);
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

        return cell;
    }
   }

}