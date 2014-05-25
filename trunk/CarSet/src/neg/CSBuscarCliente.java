/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABBuscarCliente.java
 *
 * Created on 07-oct-2009, 0:21:39
 */
package neg;

import utils.LimitadorDeDocumento;
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
public class CSBuscarCliente extends javax.swing.JPanel
{
    /** Creates new form ABBuscarCliente */
    public CSBuscarCliente() throws SQLException
    {
        CSDesktop.menuBuscarCliente.setEnabled(false);
        initComponents();
        getComercial();
        limitacionesCampos();

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
                    jButtonCancelar.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };

        for (int k = 0; k < jPanel1.getComponents().length; k ++)
        {
            if (jPanel1.getComponents()[k] != jComboBoxEstado &&
                jPanel1.getComponents()[k] != jComboBoxProvincia)
            {
                jPanel1.getComponents()[k].addKeyListener(l);
            }
        }
        jPanel1.addKeyListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lComercial1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lNombre = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        lDNI = new javax.swing.JLabel();
        jTextDNI = new javax.swing.JTextField();
        lDireccion = new javax.swing.JLabel();
        jTextDireccion = new javax.swing.JTextField();
        lCodPostal = new javax.swing.JLabel();
        jTextCodPostal = new javax.swing.JTextField();
        lPoblacion = new javax.swing.JLabel();
        jTextPoblacion = new javax.swing.JTextField();
        lProvincia = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        lNumero = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        lPContacto = new javax.swing.JLabel();
        lNombreCon = new javax.swing.JLabel();
        jComboBoxProvincia = new javax.swing.JComboBox();
        lEstado = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jTextNombreCon = new javax.swing.JTextField();
        lComercial = new javax.swing.JLabel();
        jComboBoxComercial = new javax.swing.JComboBox();
        jSeparator8 = new javax.swing.JSeparator();

        lComercial1.setForeground(new java.awt.Color(0, 0, 100));
        lComercial1.setText("Comercial");
        lComercial1.setName("lComercial"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        lNombre.setForeground(new java.awt.Color(0, 0, 100));
        lNombre.setText("Nombre");
        lNombre.setName("lNombre"); // NOI18N

        jTextNombre.setName("jTextNombre"); // NOI18N
        jTextNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreFocusLost(evt);
            }
        });

        lDNI.setForeground(new java.awt.Color(0, 0, 100));
        lDNI.setText("DNI/CIF");
        lDNI.setName("lDNI"); // NOI18N

        jTextDNI.setName("jTextDNI"); // NOI18N
        jTextDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDNIFocusLost(evt);
            }
        });

        lDireccion.setForeground(new java.awt.Color(0, 0, 100));
        lDireccion.setText("Dirección");
        lDireccion.setName("lDireccion"); // NOI18N

        jTextDireccion.setName("jTextDireccion"); // NOI18N
        jTextDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDireccionFocusLost(evt);
            }
        });

        lCodPostal.setForeground(new java.awt.Color(0, 0, 100));
        lCodPostal.setText("Código Postal");
        lCodPostal.setName("lCodPostal"); // NOI18N

        jTextCodPostal.setName("jTextCodPostal"); // NOI18N

        lPoblacion.setForeground(new java.awt.Color(0, 0, 100));
        lPoblacion.setText("Población");
        lPoblacion.setName("lPoblacion"); // NOI18N

        jTextPoblacion.setName("jTextPoblacion"); // NOI18N
        jTextPoblacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPoblacionFocusLost(evt);
            }
        });

        lProvincia.setForeground(new java.awt.Color(0, 0, 100));
        lProvincia.setText("Provincia");
        lProvincia.setName("lProvincia"); // NOI18N

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setName("jButtonBuscar"); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonCancelar.setForeground(new java.awt.Color(204, 0, 0));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setName("jButtonCancelar"); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        lNumero.setForeground(new java.awt.Color(0, 0, 100));
        lNumero.setText("Nº. Cliente  CL/");
        lNumero.setName("lNumero"); // NOI18N

        jTextNumero.setName("jTextNumero"); // NOI18N

        lPContacto.setFont(new java.awt.Font("Tahoma", 1, 11));
        lPContacto.setForeground(new java.awt.Color(170, 16, 4));
        lPContacto.setText("PERSONA DE CONTACTO");
        lPContacto.setName("lPContacto"); // NOI18N

        lNombreCon.setForeground(new java.awt.Color(0, 0, 100));
        lNombreCon.setText("Nombre");
        lNombreCon.setName("lNombreCon"); // NOI18N

        jComboBoxProvincia.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxProvincia.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJARA", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVEDRA", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxProvincia.setName("jComboBoxProvincia"); // NOI18N

        lEstado.setForeground(new java.awt.Color(0, 0, 100));
        lEstado.setText("Estado");
        lEstado.setName("lEstado"); // NOI18N

        jComboBoxEstado.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxEstado.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        jComboBoxEstado.setName("jComboBoxEstado"); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator1.setName("jSeparator1"); // NOI18N

        jTextNombreCon.setName("jTextNombreCon"); // NOI18N
        jTextNombreCon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreConFocusLost(evt);
            }
        });

        lComercial.setForeground(new java.awt.Color(0, 0, 100));
        lComercial.setText("Comercial");
        lComercial.setName("lComercial"); // NOI18N

        jComboBoxComercial.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxComercial.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxComercial.setName("jComboBoxComercial"); // NOI18N

        jSeparator8.setBackground(new java.awt.Color(0, 102, 51));
        jSeparator8.setForeground(new java.awt.Color(0, 102, 51));
        jSeparator8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 53), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51), new java.awt.Color(0, 102, 51)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        jSeparator8.setOpaque(true);
        jSeparator8.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(lNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lDireccion)
                                    .addComponent(lPoblacion)
                                    .addComponent(lEstado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextPoblacion, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lProvincia))
                                    .addComponent(jTextDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
                                        .addComponent(lComercial)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lDNI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jComboBoxProvincia, 0, 141, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lCodPostal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jTextCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxComercial, 0, 141, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lNombreCon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lPContacto)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNumero)
                    .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDNI)
                    .addComponent(jTextDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNombre))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDireccion)
                    .addComponent(jTextCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCodPostal))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lProvincia)
                    .addComponent(lPoblacion))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEstado)
                    .addComponent(jComboBoxComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComercial))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lPContacto)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNombreCon))
                .addGap(47, 47, 47)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        
        System.out.println("\nBoton buscar Clientes.");

        int numero = 0;
        String textNumero = new String((jTextNumero.getText()));

        if (textNumero.equals(""))
        {
            numero=-1;
        }
        else
        {
            numero = Integer.parseInt((String)jTextNumero.getText().toString());
        }

        String nombre = new String(jTextNombre.getText());
        String dni = new String(jTextDNI.getText());
        String codPostal = new String(jTextCodPostal.getText());
        String direccion = new String(jTextDireccion.getText());
        String poblacion = new String(jTextPoblacion.getText());
        String provincia = new String(jComboBoxProvincia.getSelectedItem().toString());
        //Esto hay que quitarlo o no dependiendo de la persona de contacto
        String nombreContacto = new String(jTextNombreCon.getText());
        int comercial = (jComboBoxComercial.getSelectedIndex()+1);

        if (!nombreContacto.equals("") && ((numero != -1) ||
           (!nombre.equals("")) || (!dni.equals("")) || (!poblacion.equals("")) ||
           (!codPostal.equals("")) || (!provincia.equals("Selecciona"))))
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>El campo Nombre de Contacto es un criterio único de búsqueda</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else if(!nombreContacto.equals(""))
        {
            String query = "SELECT * FROM cc_contactos_clientes where cc_nombre like '%"+nombreContacto+"%'";
        }
        else
        {
            String query = "SELECT cl.cl_id, cl.cl_nombre, cl.cl_tipo_tarifa, cl.cl_tipo, cl.cl_provincia, cl.cl_plazo, fp.fp_tipo, co.co_nombre" +
                           "  FROM cl_clientes cl, co_comerciales co, fp_forma_pago fp" +
                           " WHERE cl.co_id = co.co_id AND cl.fp_id = fp.fp_id";

            if (numero!=-1)
            {
                query = query + " AND cl.cl_id = "+numero;
            }
            if (!nombre.equals(""))
            {
                query = query + " AND cl.cl_nombre like '%"+nombre+"%'";
            }
            if (!dni.equals(""))
            {
                query = query + " AND cl.cl_DNI_CIF = '"+dni+"'";
            }
            if (!codPostal.equals(""))
            {
                     query = query + " AND cl.cl_cod_postal = '"+codPostal+"'";
            }
            if (!direccion.equals(""))
            {
                query = query + " AND cl.cl_direccion = '"+direccion+"'";
            }
            if (!codPostal.equals(""))
            {
                query = query + " AND cl.cl_cod_postal = '"+codPostal+"'";
            }

            if (!poblacion.equals(""))
            {
                query = query + " AND cl.cl_poblacion like '%"+poblacion+"%'";
            }
            if (!provincia.equals("Selecciona"))
            {
                query = query + " AND cl.cl_provincia like '%"+provincia+"%'";
            }
            if (comercial>1)
            {
                query = query + " AND co.co_id= '"+comercial+"'";
            }
            query = query + " ORDER BY cl.cl_nombre ASC";
          System.out.println(query);
          CSResultBuscarCliente resultBuscarCliente = new CSResultBuscarCliente(query);
          
       }
}//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        {
            CSDesktop.BuscarCliente.dispose();
            CSDesktop.menuBuscarCliente.setEnabled(true);
        }
}//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreFocusLost
         String NombreM = jTextNombre.getText().toUpperCase();
       jTextNombre.setText(NombreM);
    }//GEN-LAST:event_jTextNombreFocusLost

    private void jTextDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDNIFocusLost
        String DNIM = jTextDNI.getText().toUpperCase();
       jTextDNI.setText(DNIM);
    }//GEN-LAST:event_jTextDNIFocusLost

    private void jTextDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextDireccionFocusLost
        String DireccionM = jTextDireccion.getText().toUpperCase();
       jTextDireccion.setText(DireccionM);
    }//GEN-LAST:event_jTextDireccionFocusLost

    private void jTextPoblacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPoblacionFocusLost
        String PoblacionM = jTextPoblacion.getText().toUpperCase();
       jTextPoblacion.setText(PoblacionM);
    }//GEN-LAST:event_jTextPoblacionFocusLost

    private void jTextNombreConFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreConFocusLost
         String NombreConM = jTextNombreCon.getText().toUpperCase();
       jTextNombreCon.setText(NombreConM);
    }//GEN-LAST:event_jTextNombreConFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox jComboBoxComercial;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxProvincia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextCodPostal;
    private javax.swing.JTextField jTextDNI;
    private javax.swing.JTextField jTextDireccion;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNombreCon;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextField jTextPoblacion;
    private javax.swing.JLabel lCodPostal;
    private javax.swing.JLabel lComercial;
    private javax.swing.JLabel lComercial1;
    private javax.swing.JLabel lDNI;
    private javax.swing.JLabel lDireccion;
    private javax.swing.JLabel lEstado;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lNombreCon;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lPContacto;
    private javax.swing.JLabel lPoblacion;
    private javax.swing.JLabel lProvincia;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }

     private void getComercial() throws SQLException
    {
        ResultSet rs = CSDesktop.datos.select("SELECT co_id, co_nombre FROM co_comerciales");
        int j = 0;
        String valor = "";
        while(rs.next())
        {
            valor = rs.getString("co_nombre");

            jComboBoxComercial.addItem(valor);
            jComboBoxComercial.setSelectedIndex(0);

            j++;
        }
     }

      private void limitacionesCampos()
    {
        LimitadorDeDocumento limitadorNombre= new LimitadorDeDocumento(50);
        jTextNombre.setDocument(limitadorNombre);
        LimitadorDeDocumento limitadorDNI= new LimitadorDeDocumento(10);
        jTextDNI.setDocument(limitadorDNI);
        LimitadorDeDocumento limitadorDireccion = new LimitadorDeDocumento(255);
        jTextDireccion.setDocument(limitadorDireccion);
        LimitadorDeDocumento limitador5= new LimitadorDeDocumento(5);
        jTextCodPostal.setDocument(limitador5);
        LimitadorDeDocumento limitadorPoblacion= new LimitadorDeDocumento(80);
        jTextPoblacion.setDocument(limitadorPoblacion);
        LimitadorDeDocumento limitadorNombreContacto= new LimitadorDeDocumento(50);
        jTextNombreCon.setDocument(limitadorNombreContacto);
    }
}