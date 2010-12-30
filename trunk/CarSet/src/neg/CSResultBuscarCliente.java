/*
 * ABResultC.java
 *
 * Created on 07-oct-2009, 12:38:47
 */

package neg;

import utils.TablaModelo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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

/**
 *
 * @author depr102
 */
public class CSResultBuscarCliente extends javax.swing.JPanel
{
    /** Creates new form ABResultC */
    public CSResultBuscarCliente(String query)
    {
        TablaModelo modelo = new TablaModelo();
        ResultSet rs = CSDesktop.datos.select(query);
        
        modelo.setColumnIdentifiers(new String[] {"NUM", "NOMBRE", "TIPO TARIFA", "PROVINCIA", "PLAZO DE PAGO", "FORMA DE PAGO", "COMERCIAL" });
        int numeroFila = 0;
        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 34; k++)
                {
                    if (k==0 ||k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k == 6 )
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
            Logger.getLogger(CSResultBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Clientes con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else
        {
            CSDesktop.ResultCliente = new JInternalFrame("Resultado Búsqueda Clientes", true, false, false, true );
            CSDesktop.ResultCliente.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ResultCliente.pack();
            CSDesktop.BuscarCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ResultCliente );
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ResultCliente.getSize();
            CSDesktop.ResultCliente.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ResultCliente.setVisible( true );
        }
        initComponents();
        tablaClientes.setModel(modelo);
        tablaClientes.setDefaultRenderer (Object.class, new MiRender());

        TableColumn columna = tablaClientes.getColumnModel().getColumn(0);
        columna.setPreferredWidth(40);
        TableColumn columna1 = tablaClientes.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(300);
        TableColumn columna2 = tablaClientes.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(50);
        TableColumn columna3 = tablaClientes.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(75);
        TableColumn columna4 = tablaClientes.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(70);
        TableColumn columna5 = tablaClientes.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(100);
        TableColumn columna6 = tablaClientes.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(100);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tablaClientes.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
        tablaClientes.getTableHeader().setPreferredSize(new Dimension(tablaClientes.getTableHeader().getWidth(),26));
        tablaClientes.getTableHeader().setBackground(Color.GRAY);
        tablaClientes.getTableHeader().setForeground(Color.white);

        tablaClientes.setAutoCreateRowSorter(true);

        tablaClientes.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = tablaClientes.rowAtPoint(e.getPoint());
            int columna = tablaClientes.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {               
               int cliente = Integer.parseInt((String)tablaClientes.getValueAt(fila,0).toString());
               CSDesktop.EditarCliente = new JInternalFrame("Editar Cliente", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarCliente editarC=null;
                    try {
                        editarC = new CSEditarCliente(cliente);
                    } catch (SQLException ex) {
                        Logger.getLogger(CSResultBuscarCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
               CSDesktop.EditarCliente.getContentPane().add( editarC,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarCliente.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarCliente );
               //ABDesktop.ResultCliente.dispose();
               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarCliente.getSize();
               CSDesktop.EditarCliente.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarCliente.setVisible( true );
               CSDesktop.ResultCliente.setVisible(false);


            System.out.println(tablaClientes.getValueAt(fila,columna));
         }
        }
        }    );
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 1000,600 );
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
        tablaClientes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.setGridColor(new java.awt.Color(204, 204, 255));
        tablaClientes.setName("tablaClientes"); // NOI18N
        tablaClientes.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tablaClientes);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
                .addContainerGap())
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
        CSDesktop.ResultCliente.dispose();
        CSDesktop.menuBuscarCliente.setEnabled(true);
       
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
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

            tablaClientes.setRowHeight(20);

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

            return cell;
        }
    }


}