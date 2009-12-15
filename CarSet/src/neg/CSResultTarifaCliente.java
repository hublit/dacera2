/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABResultC.java
 *
 * Created on 07-oct-2009, 12:38:47
 */

package neg;

import javax.swing.event.InternalFrameEvent;
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
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author depr102
 */
public class CSResultTarifaCliente extends javax.swing.JPanel {

    /** Creates new form ABResultC */
    public CSResultTarifaCliente(String query) {


        CSDesktop.EditarCliente.setVisible(false);

        DbConnection datos = new DbConnection();
        TablaModelo modelo = new TablaModelo();
        ResultSet rs = datos.select(query);
        System.out.println("Tarifas: "+query);
        modelo.setColumnIdentifiers(new String[] {"NUM", "SERVICIO", "SERVICIO ORIGEN", "SERVICIO DESTINO", "SOPORTE", "FECHA DESDE", "TARIFA"});

        int numeroFila = 0;
        try {
            while (rs.next()) {
                Object[] datosFila = new Object[modelo.getColumnCount()];
                int j = 0;
                for (int k = 0; k < 11; k++) {
                    if (k == 0 || k == 1 || k == 2 || k == 3 || k == 4 || k == 5 || k == 8) {
                         if(k==5)
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
            Logger.getLogger(CSResultTarifaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado Tarifas de este Cliente</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else
        {
            CSDesktop.ABResultTarifasCliente = new JInternalFrame("Tarifas Cliente", true, false, false, true );
            CSDesktop.ABResultTarifasCliente.getContentPane().add( this, BorderLayout.CENTER );
            CSDesktop.ABResultTarifasCliente.pack();
            //ABDesktop.BuscarCliente.dispose();
            CSDesktop.elEscritorio.add( CSDesktop.ABResultTarifasCliente);
            Dimension pantalla = CSDesktop.elEscritorio.getSize();
            Dimension ventana = CSDesktop.ABResultTarifasCliente.getSize();
            CSDesktop.ABResultTarifasCliente.setLocation(
               (pantalla.width - ventana.width) / 2,
               (pantalla.height - ventana.height) / 2);
            CSDesktop.ABResultTarifasCliente.setVisible( true );
        }
        initComponents();
        tablaClientes.setModel(modelo);
        TableColumn columna = tablaClientes.getColumnModel().getColumn(0);
        columna.setPreferredWidth(100);
        TableColumn columna1 = tablaClientes.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(200);
        TableColumn columna2 = tablaClientes.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(200);
        TableColumn columna3 = tablaClientes.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(200);
        TableColumn columna4 = tablaClientes.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(200);
        TableColumn columna5 = tablaClientes.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(200);
        TableColumn columna6 = tablaClientes.getColumnModel().getColumn(6);
        columna6.setPreferredWidth(100);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tablaClientes.getColumnModel().getColumn(0).setCellRenderer(tcr);
        //tablaClientes.getColumnModel().getColumn(2).setCellRenderer(tcr);
        //tablaClientes.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tablaClientes.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tablaClientes.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tablaClientes.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tablaClientes.addMouseListener(new MouseAdapter()
        {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Estamos en el result");
            int fila = tablaClientes.rowAtPoint(e.getPoint());
            int columna = tablaClientes.columnAtPoint(e.getPoint());

            if ((fila > -1) && (columna > -1))
            {
               int tarifa = Integer.parseInt((String)tablaClientes.getValueAt(fila,0).toString());
               CSDesktop.EditarTarifaCliente = new JInternalFrame("Editar Tarifa Cliente", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSEditarTarifaCliente editarT= new CSEditarTarifaCliente(tarifa);
               CSDesktop.EditarTarifaCliente.getContentPane().add( editarT,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               CSDesktop.EditarTarifaCliente.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               CSDesktop.elEscritorio.add( CSDesktop.EditarTarifaCliente );
               //ABDesktop.ResultCliente.dispose();
               Dimension pantalla = CSDesktop.elEscritorio.getSize();
               Dimension ventana = CSDesktop.EditarTarifaCliente.getSize();
               CSDesktop.EditarTarifaCliente.setLocation(
                    (pantalla.width - ventana.width) / 2,
                    (pantalla.height - ventana.height) / 2);
               CSDesktop.EditarTarifaCliente.setVisible( true );

            System.out.println(tablaClientes.getValueAt(fila,columna));
         }
        }
        }    );
    }

     public Dimension getPreferredSize()
   {
      return new Dimension( 826,600 );
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
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
        CSDesktop.ABResultTarifasCliente.dispose();
        CSDesktop.EditarCliente.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables

}
