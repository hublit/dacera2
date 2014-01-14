/*
 * BuscaClientesPanel.java
 *
 * Created on 06-oct-2009, 11:09:27
 */

package neg;

import java.awt.Font;
import utils.TablaModelo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import data.Cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author depr102
 */
public class CSSelectCliente extends javax.swing.JPanel
{
    private JTextField jTextCli;
    private int cliID = 0;
   
    /** Creates new form BuscaClientesPanel */
    public CSSelectCliente(JTextField jTextC,String busqueda,boolean inicial)
    {
        TablaModelo modelo = new TablaModelo();
        Cliente client = new Cliente();

        this.jTextCli = jTextC;

        modelo.setColumnIdentifiers(new String[] { "NUMERO", "NOMBRE", "DNI",});

        int numeroFila = 0;

        String query = "SELECT cl_id, cl_nombre, cl_DNI_CIF  FROM cl_clientes WHERE cl_estado = 'Activo' ORDER BY cl_nombre";

        if(!inicial)
        {

    /////////////////////

            query = "SELECT cl_id, cl_nombre, cl_DNI_CIF  FROM cl_clientes WHERE cl_estado = 'Activo' AND cl_nombre like  '%"+busqueda+"%' ORDER BY cl_nombre";
        }
        try
        {
         ResultSet rs = CSDesktop.datos.select(query);

         int i = 0;
         while(rs.next())
         {
            Object[] datosFila = new Object[modelo.getColumnCount()];
            int j = 0;
            for (int k = 0; k < 4; k++) {
                if (k == 0 || k == 1 || k == 2) {
                    datosFila[j] = rs.getObject(k + 1);
                    j++;
                }
            }

            modelo.addRow(datosFila);
            numeroFila++;
            i++;
         }
         System.out.println(i);
         rs.close();
      }
      catch(SQLException e)
      {
         System.out.println(e);
      }
/////////////////////
/*                for (int i = 0; i < client.getClientes().length; i ++)
                {
                    Object[] datosFila2 = new Object[modelo.getColumnCount()];
                    int j = 0;
                    for (int k = 0; k < 4; k++) {
                        if (k == 0 || k == 1 || k == 2) {
                            datosFila2[j] = client.getClientes()[i][k];
                            //datosFila[j] = rs.getObject(k + 1);
                            j++;
                        }
                    }
                    modelo.addRow(datosFila2);
                    numeroFila++;
                 }
            }
            else if (inicial==false)
            {
                 for (int i = 0; i < client.getClientesQuery(busqueda).length; i ++)
                {
                    Object[] datosFila2 = new Object[modelo.getColumnCount()];
                    int j = 0;
                    for (int k = 0; k < 4; k++) {
                        if (k == 0 || k == 1 || k == 2) {
                            datosFila2[j] = client.getClientesQuery(busqueda)[i][k];
                            //datosFila[j] = rs.getObject(k + 1);
                            j++;
                        }
                    }
                    modelo.addRow(datosFila2);
                    numeroFila++;
                 }
            }
      
        */
        initComponents();

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
                    jButton1.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

         for (int k = 0; k < this.getComponents().length; k ++)
        {

                this.getComponents()[k].addKeyListener(l);
            
        }

        addKeyListener(l);
        
        jTable1.setDefaultRenderer (Object.class, new MiRender());
        jTable1.setModel(modelo);
        TableColumn columna = jTable1.getColumnModel().getColumn(0);
        columna.setPreferredWidth(50);
        TableColumn columna1 = jTable1.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(300);
        TableColumn columna2 = jTable1.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(100);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        jTable1.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
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
               String cliente = ((String)jTable1.getValueAt(fila,1).toString());
               jTextCli.setText(cliente);              
               CSDesktop.BuscaCliente.dispose();
              }                  
         }
        });
        
        jTextField1.grabFocus();
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
        jButton1 = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEditingRow(0);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cerrar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.setName("jButtonLimpiar"); // NOI18N
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jTextField1.setName("jTextField1"); // NOI18N

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setName("jButtonBuscar"); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(71, 71, 71)
                        .addComponent(jButtonLimpiar)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButton1))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CSDesktop.BuscaCliente.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
         jTextCli.setText("");
         CSDesktop.BuscaCliente.dispose();
}//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        String letras=new String(jTextField1.getText());

        CSDesktop.BuscaCliente.dispose();
        CSDesktop.BuscaCliente = new JInternalFrame("Seleccionar Cliente", true, false, false, true );
        // adjuntar panel al panel de contenido del marco interno
        CSSelectCliente panel = new CSSelectCliente(jTextCli,letras,false);
        CSDesktop.BuscaCliente.getContentPane().add( panel,BorderLayout.CENTER);
        // establecer tama�o de marco interno en el tama�o de su contenido
        CSDesktop.BuscaCliente.pack();
        // adjuntar marco interno al escritorio y mostrarlo
        CSDesktop.elEscritorio.add( CSDesktop.BuscaCliente );
        CSDesktop.BuscaCliente.setLocation(150, 50);
        CSDesktop.BuscaCliente.setVisible( true );

    }//GEN-LAST:event_jButtonBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


    public class MiRender extends DefaultTableCellRenderer {

        @SuppressWarnings("static-access")
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component cell = super. getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
            //se centran los valores
            //se toman algunos valores especificos para mi programa

            //si cumplen x condicion se pintan
            if (column == 0 ||column == 2)
            {
                this. setHorizontalAlignment(SwingConstants.CENTER);
            }
            else
            {
                this. setHorizontalAlignment(SwingConstants.LEFT);
            }

            jTable1.setRowHeight(20);

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