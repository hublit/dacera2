/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABResultTarifaProveedor.java
 *
 * Created on 07-oct-2009, 12:38:47
 */

package neg;

import javax.swing.event.InternalFrameListener;
import utils.TablaModelo;
import data.DbConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author depr102
 */
public class CSResultTarifaProveedor extends javax.swing.JPanel
{
    DbConnection datos = new DbConnection();
    /** Creates new form ABResultC */
    public CSResultTarifaProveedor(String query)
    {
        CSDesktop.EditarProveedor.setVisible(false);

        TablaModelo modelo = new TablaModelo();
        ResultSet rs = datos.select(query);
        System.out.println("Tarifas: "+query);
        modelo.setColumnIdentifiers(new String[] {"NUMERO", "SERVICIO", "SERVICIO ORIGEN", "SERVICIO DESTINO", "SOPORTE", "FECHA DESDE", "TARIFA"});
        int numeroFila = 0;
        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 11; k++) {
                    if (k == 0 || k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k == 8) {
                        datosFila[j] = rs.getObject(k + 1);
                        System.out.println("Dato" + k + " " + rs.getObject(k + 1));
                        j++;
                    } else {
                        //datosFila[k] = rs.getObject(k + 1);
                        //System.out.println("No Dato"+k+" "+rs.getObject(k + 1));
                    }
                }
                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CSResultTarifaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Tarifas de este Cliente</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else
        {
            CSDesktop.ABResultTarifasProveedor = new JInternalFrame("Tarifas Proveedor", true, false, false, true );
            CSDesktop.ABResultTarifasProveedor.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ABResultTarifasProveedor.pack();
            //ABDesktop.BuscarCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ABResultTarifasProveedor);
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ABResultTarifasProveedor.getSize();
            CSDesktop.ABResultTarifasProveedor.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ABResultTarifasProveedor.setVisible( true );
        }
        initComponents();
        tablaTarifas.setModel(modelo);
        TableColumn columna = tablaTarifas.getColumnModel().getColumn(0);
        columna.setPreferredWidth(100);
        TableColumn columna1 = tablaTarifas.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(200);
        TableColumn columna2 = tablaTarifas.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(200);
        TableColumn columna3 = tablaTarifas.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(200);
        TableColumn columna4 = tablaTarifas.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(200);
        TableColumn columna5 = tablaTarifas.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(200);
        TableColumn columna6 = tablaTarifas.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(100);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tablaTarifas.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tablaTarifas.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tablaTarifas.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tablaTarifas.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tablaTarifas.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = tablaTarifas.rowAtPoint(e.getPoint());
            int columna = tablaTarifas.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {
               int tarifa = Integer.parseInt((String)tablaTarifas.getValueAt(fila,0).toString());
               CSDesktop.EditarTarifaProveedor = new JInternalFrame("Editar Tarifa Proveedor", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarTarifaProveedor editarT= new CSEditarTarifaProveedor(tarifa);
               CSDesktop.EditarTarifaProveedor.getContentPane().add( editarT,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarTarifaProveedor.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarTarifaProveedor );
               //ABDesktop.ResultCliente.dispose();
               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarTarifaProveedor.getSize();
               CSDesktop.EditarTarifaProveedor.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarTarifaProveedor.setVisible( true );

            System.out.println(tablaTarifas.getValueAt(fila,columna));
         }
        }
        }    );
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarifas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaTarifas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaTarifas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaTarifas.setCellSelectionEnabled(true);
        tablaTarifas.setGridColor(new java.awt.Color(204, 204, 255));
        tablaTarifas.setName("tablaTarifas"); // NOI18N
        jScrollPane1.setViewportView(tablaTarifas);

        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Cerrar");
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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        datos.cerrarConexion();
        CSDesktop.ABResultTarifasProveedor.dispose();
        CSDesktop.EditarProveedor.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTarifas;
    // End of variables declaration//GEN-END:variables

}