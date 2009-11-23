/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABEditarContactosProveedor.java
 *
 * Created on 12-oct-2009, 12:35:08
 */

package neg;

import utils.Utilidades;
import data.DbConnection;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lito
 */
public class CSEditarContactoProveedor extends javax.swing.JPanel
{

    DbConnection datos = new DbConnection();
    /** Creates new form ABEditarContactosProveedor */
    public CSEditarContactoProveedor(int contacto) throws SQLException
    {
        datos = new DbConnection();
        initComponents();
        getDepartamentoContacto();
        datosContactoProveedor(contacto);

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
            if (this.getComponents()[k] != jComboBoxDepartamento)
            {
                this.getComponents()[k].addKeyListener(l);
            }
        }
        addKeyListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextTelefonoCon = new javax.swing.JTextField();
        lEmailCon = new javax.swing.JLabel();
        jComboBoxDepartamento = new javax.swing.JComboBox();
        lTelefonoCon = new javax.swing.JLabel();
        lNombreCon = new javax.swing.JLabel();
        lDepartamento = new javax.swing.JLabel();
        jTextEmailCon = new javax.swing.JTextField();
        jTextTelefonoCon2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lTelefonoCon1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonCancelar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jTextNombreCon = new javax.swing.JTextField();
        lNumContacto = new javax.swing.JLabel();
        jTextNumContacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButtonEliminar = new javax.swing.JButton();

        jTextTelefonoCon.setName("jTextTelefonoCon"); // NOI18N

        lEmailCon.setForeground(new java.awt.Color(0, 0, 100));
        lEmailCon.setText("Email");
        lEmailCon.setName("lEmailCon"); // NOI18N

        jComboBoxDepartamento.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxDepartamento.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxDepartamento.setName("jComboBoxDepartamento"); // NOI18N

        lTelefonoCon.setForeground(new java.awt.Color(0, 0, 100));
        lTelefonoCon.setText("Teléfono");
        lTelefonoCon.setName("lTelefonoCon"); // NOI18N

        lNombreCon.setForeground(new java.awt.Color(0, 0, 100));
        lNombreCon.setText("Nombre");
        lNombreCon.setName("lNombreCon"); // NOI18N

        lDepartamento.setForeground(new java.awt.Color(0, 0, 100));
        lDepartamento.setText("Departamento");
        lDepartamento.setName("lDepartamento"); // NOI18N

        jTextEmailCon.setName("jTextEmailCon"); // NOI18N
        jTextEmailCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextEmailConActionPerformed(evt);
            }
        });
        jTextEmailCon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextEmailConFocusLost(evt);
            }
        });

        jTextTelefonoCon2.setName("jTextTelefonoCon2"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

        lTelefonoCon1.setForeground(new java.awt.Color(0, 0, 100));
        lTelefonoCon1.setText("Teléfono 2");
        lTelefonoCon1.setName("lTelefonoCon1"); // NOI18N

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");
        jLabel6.setName("jLabel6"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        jButtonCancelar.setForeground(new java.awt.Color(204, 0, 0));
        jButtonCancelar.setText("Cerrar");
        jButtonCancelar.setName("jButtonCancelar"); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jButtonCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonCancelarKeyPressed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.setName("jButtonModificar"); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jTextNombreCon.setName("jTextNombreCon"); // NOI18N
        jTextNombreCon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreConFocusLost(evt);
            }
        });

        lNumContacto.setForeground(new java.awt.Color(0, 0, 100));
        lNumContacto.setText("Número Contacto CP/");
        lNumContacto.setName("lNumContacto"); // NOI18N

        jTextNumContacto.setEditable(false);
        jTextNumContacto.setName("jTextNumContacto"); // NOI18N

        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jLabel8.setName("jLabel8"); // NOI18N

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setName("jButtonEliminar"); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNombreCon)
                        .addGap(18, 18, 18)
                        .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lDepartamento)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTelefonoCon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextTelefonoCon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lTelefonoCon1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextTelefonoCon2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(lEmailCon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextEmailCon, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lNumContacto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextNumContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNumContacto)
                    .addComponent(jTextNumContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNombreCon)
                    .addComponent(jLabel6)
                    .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDepartamento))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTelefonoCon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTelefonoCon1)
                    .addComponent(jTextTelefonoCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTelefonoCon)
                    .addComponent(jLabel7)
                    .addComponent(jTextEmailCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEmailCon)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModificar)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        {
            CSDesktop.EditarContactoProveedor.dispose();
        }
}//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCancelarKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_jButtonCancelarKeyPressed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed

        System.out.println("\nBoton Modificar contactos Proveedor.");

        String numero = new String(jTextNumContacto.getText());
        String nombre = new String(jTextNombreCon.getText());
        String telefono = new String(jTextTelefonoCon.getText());
        String telefono2 = new String(jTextTelefonoCon2.getText());
        String email = new String(jTextEmailCon.getText());
        String fDepartamento = new String(Integer.valueOf(jComboBoxDepartamento.getSelectedIndex()+1).toString());
        String departamento = new String(jComboBoxDepartamento.getSelectedItem().toString());

        if (!Utilidades.campoObligatorio(nombre,"nombre").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorio(nombre,"nombre"));
        } else if (!Utilidades.campoObligatorio(telefono,"telefono").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorio(telefono,"telefono"));
        }else if (!Utilidades.campoObligatorioCombo(departamento,"Departamento").equals("OK")){
            ValidarFormatos(Utilidades.campoObligatorioCombo(departamento,"Departamento"));
        } else if(!Utilidades.formatoCorreo(email).equals("OK")) {
            ValidarFormatos(Utilidades.formatoCorreo(email));
        }
        else
        {
            //System.out.println(rs2.getInt("last_insert_id()"));
            String query = "UPDATE cp_contactos_proveedor SET cp_nombre = '"+nombre+"', cp_telefono = '"+telefono+"', " +
                              "cp_telefono2 = '"+telefono2+"', cp_email = '"+email+"', dp_id = '"+fDepartamento+"'  where cp_id=" + numero ;

            System.out.println(query);
            boolean rsCon = datos.manipuladorDatos(query);
            System.out.println(query);
            if(rsCon)
            {
                jButtonModificar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonModificar.setEnabled(true);
            }


            // query="INSERT INTO cc_contactos_clientes (cc_nombre,cc_telefono,cc_email,cl_id) VALUES (";

            jButtonModificar.setEnabled(false);
            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,mensaje);
            jButtonModificar.setEnabled(true);
            CSDesktop.EditarContactoProveedor.dispose();
        }
}//GEN-LAST:event_jButtonModificarActionPerformed

    private void jTextNombreConFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreConFocusLost
       String nombreM = jTextNombreCon.getText().toUpperCase();
       jTextNombreCon.setText(nombreM);
    }//GEN-LAST:event_jTextNombreConFocusLost

    private void jTextEmailConFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEmailConFocusLost
       String MailM = jTextEmailCon.getText().toUpperCase();
       jTextEmailCon.setText(MailM);
    }//GEN-LAST:event_jTextEmailConFocusLost

    private void jTextEmailConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextEmailConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextEmailConActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(this,"¿Estas seguro que quieres eliminar el contacto?");

        if (JOptionPane.OK_OPTION == confirmado) {
            int cp_id = Integer.parseInt(jTextNumContacto.getText());
            String query="DELETE from cp_contactos_proveedor where cp_id="+cp_id;

            boolean rs=datos.manipuladorDatos(query);
            if(rs) {
                jButtonModificar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al eleiminar el contacto en la base de datos</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonModificar.setEnabled(true);
            } else {
                jButtonModificar.setEnabled(false);
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han borrado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                jButtonModificar.setEnabled(true);
                CSDesktop.EditarContactoProveedor.dispose();
                CSDesktop.menuBuscarProveedor.setEnabled(true);
            }
        }
}//GEN-LAST:event_jButtonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox jComboBoxDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextEmailCon;
    private javax.swing.JTextField jTextNombreCon;
    private javax.swing.JTextField jTextNumContacto;
    private javax.swing.JTextField jTextTelefonoCon;
    private javax.swing.JTextField jTextTelefonoCon2;
    private javax.swing.JLabel lDepartamento;
    private javax.swing.JLabel lEmailCon;
    private javax.swing.JLabel lNombreCon;
    private javax.swing.JLabel lNumContacto;
    private javax.swing.JLabel lTelefonoCon;
    private javax.swing.JLabel lTelefonoCon1;
    // End of variables declaration//GEN-END:variables

    public Dimension getPreferredSize()
    {
      return new Dimension( 850,600 );
    }

    /**
     * Función para sacar todos los datos del proveedor de la bd
     * @param cliente
     * @return
     * @throws SQLException
     */
    public void datosContactoProveedor(int contacto)
    {
    	String query = "SELECT * FROM cp_contactos_proveedor " +
                       "WHERE cp_id = "+contacto;
        System.out.println("Query con:"+query);
        ResultSet rs = datos.select(query);
        int numeroFila = 0;
        int cp_id = 0;

        try
        {
            while(rs.next())
            {
                cp_id = rs.getInt("cp_id");
                jTextNumContacto.setText(Integer.toString(cp_id));
                jTextNombreCon.setText(rs.getString("cp_nombre"));
                jTextTelefonoCon.setText(rs.getString("cp_telefono"));
                jTextTelefonoCon2.setText(rs.getString("cp_telefono2"));
                jTextEmailCon.setText(rs.getString("cp_email"));
                jComboBoxDepartamento.setSelectedIndex(rs.getInt("dp_id")-1);
                numeroFila++;
            }
            rs.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if (numeroFila == 0)
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>No se han encontrado contactos de Proveedores, con los parámetros de búsqueda seleccionados.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }

    }

    public void ValidarFormatos(String accion)
    {
         jButtonModificar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonModificar.setEnabled(true);
    }

    private void getDepartamentoContacto() throws SQLException
    {
        ResultSet rs = datos.select("SELECT dp_id, dp_nombre FROM dp_departamentos");
        int j = 0;
        String valor = "";
        while(rs.next())
        {
            valor = rs.getString("dp_nombre");

                jComboBoxDepartamento.addItem(valor);
                jComboBoxDepartamento.setSelectedIndex(0);
                j++;
        }
    }
}