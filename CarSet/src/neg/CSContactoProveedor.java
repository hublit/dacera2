/*
 * ABContactosProveedor.java
 *
 * Created on 11-oct-2009, 20:53:54
 */

package neg;

import utils.Utilidades;
import data.Proveedor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author lito
 */
public class CSContactoProveedor extends javax.swing.JPanel
{
    int proveedorID=0;

    /** Creates new form ABContactosProveedor */
    public CSContactoProveedor() throws SQLException
    {
        CSDesktop.menuContactoProveedor.setEnabled(false);
        initComponents();
        getDepartamentoContacto();

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

        lTelefono2 = new javax.swing.JLabel();
        jTextTelefono2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        lNombre = new javax.swing.JLabel();
        lDepartamento = new javax.swing.JLabel();
        jComboBoxDepartamento = new javax.swing.JComboBox();
        lTelefono = new javax.swing.JLabel();
        jTextTelefono = new javax.swing.JTextField();
        lEmail = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jToggleButtonProveedor2 = new javax.swing.JToggleButton();
        jTextProveedor = new javax.swing.JTextField();
        lProveedor = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();

        lTelefono2.setForeground(new java.awt.Color(0, 0, 100));
        lTelefono2.setText("Teléfono 2");
        lTelefono2.setName("lTelefono2"); // NOI18N

        jTextTelefono2.setName("jTextTelefono2"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

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

        lNombre.setForeground(new java.awt.Color(0, 0, 100));
        lNombre.setText("Nombre");
        lNombre.setName("lNombre"); // NOI18N

        lDepartamento.setForeground(new java.awt.Color(0, 0, 100));
        lDepartamento.setText("Departamento");
        lDepartamento.setName("lDepartamento"); // NOI18N

        jComboBoxDepartamento.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxDepartamento.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxDepartamento.setName("jComboBoxDepartamento"); // NOI18N

        lTelefono.setForeground(new java.awt.Color(0, 0, 100));
        lTelefono.setText("Teléfono");
        lTelefono.setName("lTelefono"); // NOI18N

        jTextTelefono.setName("jTextTelefono"); // NOI18N

        lEmail.setForeground(new java.awt.Color(0, 0, 100));
        lEmail.setText("Email");
        lEmail.setName("lEmail"); // NOI18N

        jTextNombre.setName("jTextNombre"); // NOI18N
        jTextNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreFocusLost(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setName("jButtonGuardar"); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("*");
        jLabel6.setName("jLabel6"); // NOI18N

        jTextEmail.setName("jTextEmail"); // NOI18N
        jTextEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextEmailFocusLost(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("*");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");
        jLabel9.setName("jLabel9"); // NOI18N

        jToggleButtonProveedor2.setText("Buscar Proveedor");
        jToggleButtonProveedor2.setName("jToggleButtonProveedor2"); // NOI18N
        jToggleButtonProveedor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonProveedor2ActionPerformed(evt);
            }
        });

        jTextProveedor.setEditable(false);
        jTextProveedor.setName("jTextProveedor"); // NOI18N
        jTextProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextProveedorActionPerformed(evt);
            }
        });

        lProveedor.setForeground(new java.awt.Color(0, 0, 100));
        lProveedor.setText("Proveedor");
        lProveedor.setName("lProveedor"); // NOI18N

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        jLabel17.setName("jLabel17"); // NOI18N

        jSeparator9.setBackground(new java.awt.Color(0, 102, 51));
        jSeparator9.setForeground(new java.awt.Color(0, 102, 51));
        jSeparator9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 53), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        jSeparator9.setOpaque(true);
        jSeparator9.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lTelefono2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lNombre)
                .addGap(18, 18, 18)
                .addComponent(jTextNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lDepartamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jToggleButtonProveedor2)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 580, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(364, Short.MAX_VALUE)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonProveedor2)
                    .addComponent(jLabel17)
                    .addComponent(lProveedor))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNombre)
                    .addComponent(jLabel6)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDepartamento))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTelefono)
                    .addComponent(jLabel7)
                    .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEmail)
                    .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTelefono2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonGuardar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCancelar))
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        {
         CSDesktop.NuevoContactoProveedor.dispose();
         CSDesktop.menuContactoProveedor.setEnabled(true);
        }
}//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCancelarKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_jButtonCancelarKeyPressed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed

        System.out.println("\nBoton Guardar de Contactos Proveedor.");
        String nomProv= new String(jTextProveedor.getText());
        String nombre = new String(jTextNombre.getText());
        String telefono = new String(jTextTelefono.getText());
        String telefono2 = new String(jTextTelefono2.getText());
        String email = new String(jTextEmail.getText());
        String fDepartamento = new String(Integer.valueOf(jComboBoxDepartamento.getSelectedIndex()+1).toString());
        String departamento = new String(jComboBoxDepartamento.getSelectedItem().toString());

        if (!Utilidades.campoObligatorio(nombre,"nombre").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorio(nombre,"nombre"));
        } else if (!Utilidades.campoObligatorio(telefono,"telefono").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorio(telefono,"telefono"));
        }else if (!Utilidades.campoObligatorioCombo(departamento,"Departamento").equals("OK")) {
            ValidarFormatos(Utilidades.campoObligatorioCombo(departamento,"Departamento"));
        } else if(!Utilidades.formatoTelefono9(telefono).equals("OK")) {
            ValidarFormatos(Utilidades.formatoTelefono9(telefono));
        } else if(!Utilidades.formatoCorreo(email).equals("OK")) {
            ValidarFormatos(Utilidades.formatoCorreo(email));
        } else
        {
            Proveedor proveedor = new Proveedor();
            proveedorID = proveedor.getProveedorID(nomProv);
            String query = "INSERT INTO cp_contactos_proveedor (cp_nombre, cp_telefono, cp_telefono2, cp_email, pr_id, dp_id) "+
                              "VALUES ('"+nombre+"','"+telefono+"','"+telefono2+"','"+email+"', "+
                              "'"+proveedorID+"', '"+fDepartamento+"')";
    System.out.println(query);
            boolean rs = CSDesktop.datos.manipuladorDatos(query);
            System.out.println(query);
            if(rs)
            {
                jButtonGuardar.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                JOptionPane.showMessageDialog(null,errorFields);
                jButtonGuardar.setEnabled(true);
            }
            else
            {
                jButtonGuardar.setEnabled(false);
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                jButtonGuardar.setEnabled(true);
                CSDesktop.NuevoContactoProveedor.dispose();
                 CSDesktop.menuContactoProveedor.setEnabled(true);
            }
         }
}//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jToggleButtonProveedor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonProveedor2ActionPerformed
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

    }//GEN-LAST:event_jToggleButtonProveedor2ActionPerformed

    private void jTextProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextProveedorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextProveedorActionPerformed

    private void jTextNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreFocusLost
       String nombreM = jTextNombre.getText().toUpperCase();
       jTextNombre.setText(nombreM);
    }//GEN-LAST:event_jTextNombreFocusLost

    private void jTextEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextEmailFocusLost
       String MailM = jTextEmail.getText().toUpperCase();
       jTextEmail.setText(MailM);
    }//GEN-LAST:event_jTextEmailFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JComboBox jComboBoxDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextProveedor;
    private javax.swing.JTextField jTextTelefono;
    private javax.swing.JTextField jTextTelefono2;
    private javax.swing.JToggleButton jToggleButtonProveedor2;
    private javax.swing.JLabel lDepartamento;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lProveedor;
    private javax.swing.JLabel lTelefono;
    private javax.swing.JLabel lTelefono2;
    // End of variables declaration//GEN-END:variables

    public Dimension getPreferredSize()
    {
      return new Dimension( 850,600 );
    }
    
    public void ValidarFormatos(String accion)
    {
         jButtonGuardar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonGuardar.setEnabled(true);
    }

    private void getDepartamentoContacto() throws SQLException
    {
        ResultSet rs = CSDesktop.datos.select("SELECT dp_id, dp_nombre FROM dp_departamentos");
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