/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABBuscarProveedores.java
 *
 * Created on 07-oct-2009, 11:25:56
 */

package neg;

import utils.LimitadorDeDocumento;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utils.Utilidades;
/**
 *
 * @author depr73
 */
public class CSBuscarProveedor extends javax.swing.JPanel {

    /** Creates new form ABBuscarProveedores */
    public CSBuscarProveedor()
    {
        CSDesktop.menuBuscarProveedor.setEnabled(false);
        initComponents();
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

        for (int k = 0; k < this.getComponents().length; k ++)
        {
            if (this.getComponents()[k] != jComboBoxRegimen &&
                this.getComponents()[k] != jComboBoxTipo &&
                this.getComponents()[k] != jComboBoxProvincia)
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

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        lNombreCon = new javax.swing.JLabel();
        jTextNombreCon = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jTextPoblacion = new javax.swing.JTextField();
        lProvincia = new javax.swing.JLabel();
        jComboBoxProvincia = new javax.swing.JComboBox();
        lPoblacion = new javax.swing.JLabel();
        lNumero = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        lDNI = new javax.swing.JLabel();
        jTextCodPostal = new javax.swing.JTextField();
        lNombre = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jComboBoxRegimen = new javax.swing.JComboBox();
        lRegimen = new javax.swing.JLabel();
        jTextDNI = new javax.swing.JTextField();
        jTextDireccion = new javax.swing.JTextField();
        lDireccion = new javax.swing.JLabel();
        lCodPostal = new javax.swing.JLabel();
        lTipo = new javax.swing.JLabel();
        lPContacto = new javax.swing.JLabel();

        jPanel1.setForeground(new java.awt.Color(0, 0, 100));
        jPanel1.setName("jPanel1"); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator1.setName("jSeparator1"); // NOI18N

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

        lNombreCon.setForeground(new java.awt.Color(0, 0, 100));
        lNombreCon.setText("Nombre");
        lNombreCon.setName("lNombreCon"); // NOI18N

        jTextNombreCon.setName("jTextNombreCon"); // NOI18N
        jTextNombreCon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreConFocusLost(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        jTextPoblacion.setName("jTextPoblacion"); // NOI18N
        jTextPoblacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPoblacionFocusLost(evt);
            }
        });

        lProvincia.setForeground(new java.awt.Color(0, 0, 100));
        lProvincia.setText("Provincia");
        lProvincia.setName("lProvincia"); // NOI18N

        jComboBoxProvincia.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxProvincia.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "A CORUÑA", "ÁLAVA", "ALBACETE", "ALICANTE", "ALMERÍA", "ASTURIAS", "ÁVILA", "BADAJOZ", "BARCELONA", "BURGOS", "CÁCERES", "CÁDIZ", "CANTABRIA", "CASTELLÓN", "CEUTA", "CIUDAD REAL", "CÓRDOBA", "CUENCA", "GIRONA", "GRANADA", "GUADALAJ.", "GUIPÚZCOA", "HUELVA", "HUESCA", "ILLES BALEARS", "JAÉN", "LA RIOJA", "LAS PALMAS", "LEÓN", "LLEIDA", "LUGO", "MADRID", "MÁLAGA", "MELILLA", "MURCIA", "NAVARRA", "OURENSE", "PALENCIA", "PONTEVED.", "SALAMANCA", "SANTA CRUZ DE TENERIFE", "SEGOVIA", "SEVILLA", "SORIA", "TARRAGONA", "TERUEL", "TOLEDO", "VALENCIA", "VALLADOLID", "VIZCAYA", "ZAMORA", "ZARAGOZA" }));
        jComboBoxProvincia.setName("jComboBoxProvincia"); // NOI18N

        lPoblacion.setForeground(new java.awt.Color(0, 0, 100));
        lPoblacion.setText("Población");
        lPoblacion.setName("lPoblacion"); // NOI18N

        lNumero.setForeground(new java.awt.Color(0, 0, 100));
        lNumero.setText("Núm. Proveedor PR/");
        lNumero.setName("lNumero"); // NOI18N

        jTextNumero.setEditable(false);
        jTextNumero.setEnabled(false);
        jTextNumero.setName("jTextNumero"); // NOI18N

        jTextNombre.setName("jTextNombre"); // NOI18N
        jTextNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextNombreFocusLost(evt);
            }
        });

        lDNI.setForeground(new java.awt.Color(0, 0, 100));
        lDNI.setText("DNI/NIF");
        lDNI.setName("lDNI"); // NOI18N

        jTextCodPostal.setName("jTextCodPostal"); // NOI18N

        lNombre.setForeground(new java.awt.Color(0, 0, 100));
        lNombre.setText("Nombre");
        lNombre.setName("lNombre"); // NOI18N

        jComboBoxTipo.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxTipo.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gruero", "Conductor", "Tren", "Custodia", "Global" }));
        jComboBoxTipo.setName("jComboBoxTipo"); // NOI18N

        jComboBoxRegimen.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxRegimen.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empresa", "Régimen General", "Autónomo" }));
        jComboBoxRegimen.setName("jComboBoxRegimen"); // NOI18N

        lRegimen.setForeground(new java.awt.Color(0, 0, 100));
        lRegimen.setText("Régimen");
        lRegimen.setName("lRegimen"); // NOI18N

        jTextDNI.setName("jTextDNI"); // NOI18N
        jTextDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDNIFocusLost(evt);
            }
        });

        jTextDireccion.setName("jTextDireccion"); // NOI18N
        jTextDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextDireccionFocusLost(evt);
            }
        });

        lDireccion.setForeground(new java.awt.Color(0, 0, 100));
        lDireccion.setText("Dirección");
        lDireccion.setName("lDireccion"); // NOI18N

        lCodPostal.setForeground(new java.awt.Color(0, 0, 100));
        lCodPostal.setText("Cód. Postal");
        lCodPostal.setName("lCodPostal"); // NOI18N

        lTipo.setForeground(new java.awt.Color(0, 0, 100));
        lTipo.setText("Tipo");
        lTipo.setName("lTipo"); // NOI18N

        lPContacto.setBackground(new java.awt.Color(170, 16, 4));
        lPContacto.setFont(new java.awt.Font("Tahoma", 1, 11));
        lPContacto.setForeground(new java.awt.Color(170, 16, 4));
        lPContacto.setText("PERSONA DE CONTACTO");
        lPContacto.setName("lPContacto"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lNombreCon)
                        .addGap(13, 13, 13)
                        .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lPContacto)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPoblacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(lProvincia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxProvincia, 0, 154, Short.MAX_VALUE)
                                .addGap(16, 16, 16)
                                .addComponent(lCodPostal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lRegimen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(lTipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(lDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(lDNI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                            .addComponent(jButtonCancelar))
                        .addGap(19, 19, 19))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(342, Short.MAX_VALUE)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNumero)
                    .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDNI))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lRegimen)
                    .addComponent(lTipo)
                    .addComponent(lDireccion))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPoblacion)
                    .addComponent(lProvincia)
                    .addComponent(lCodPostal))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lPContacto)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNombreCon)
                    .addComponent(jTextNombreCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 793, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        System.out.println("\njButtonBuscar_actionPerformed(ActionEvent e) called.");
      
        int numero=0;
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
        String regimen = new String(jComboBoxRegimen.getSelectedItem().toString());
        String tipo = new String(jComboBoxTipo.getSelectedItem().toString());
        String direccion = new String(jTextDireccion.getText());
        String codPostal = new String(jTextCodPostal.getText());
        String poblacion = new String(jTextPoblacion.getText());
        String provincia = new String(jComboBoxProvincia.getSelectedItem().toString());
        String nombreContacto = new String(jTextNombreCon.getText());

        if (!nombreContacto.equals("") && ((numero == -1) ||
           (!nombre.equals("")) || (!dni.equals("")) || (!poblacion.equals("")) ||
           (!codPostal.equals("")) || (!provincia.equals("Selecciona"))))
        {
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>El campo Nombre de Contacto es un criterio único de búsqueda</FONT></HTML>");
            JOptionPane.showMessageDialog(null,errorFields);
        }
        else if(!nombreContacto.equals(""))
        {
            String query = "SELECT * FROM cp_contactos_proveedores where cp_nombre like '%"+nombreContacto+"%'";
        }
        else
        {
            String query = "SELECT * FROM pr_proveedores c WHERE";
            boolean and = false;

            if (numero!=-1)
            {
                query = query + " pr_num = "+numero;
                and = true;
            }
             if (!nombre.equals(""))
            {
                if (and)
                {
                    query = query + " AND pr_nombre_fiscal like '%"+nombre+"%'";
                }
                else
                {
                    query = query + " pr_nombre_fiscal like '%"+nombre+"%'";
                    and = true;
                }
            }
            if (!dni.equals(""))
            {
                if (and)
                {
                    query = query + " AND pr_DNI_CIF = '"+dni+"'";
                }
                else
                {
                    query = query + " pr_DNI_CIF = '"+dni+"'";
                    and = true;
                }
            }
            if (!regimen.equals(""))
            {
                if (and)
                {
                    query = query + " AND pr_regimen = '"+regimen+"'";
                }
                else
                {
                    query = query + " pr_regimen = '"+regimen+"'";
                    and = true;
                }
            }
            if (!tipo.equals(""))
            {
                if (and)
                {
                    query = query + " AND pr_tipo = '"+tipo+"'";
                }
                else
                {
                    query = query + " pr_tipo = '"+tipo+"'";
                    and = true;
                }
            }
            if (!codPostal.equals(""))
            {
                if (and)
                {
                    query = query + " AND pr_cod_postal = '"+codPostal+"'";
                }
                else
                {
                    query = query + " pr_cod_postal = '"+codPostal+"'";
                    and = true;
                }
            }
            if (!poblacion.equals(""))
            {
                if (and)
                {
                    query = query + " AND cl_poblacion like '%"+poblacion+"%'";
                }
                else
                {
                    query = query + " wher cl_poblacion like '%"+poblacion+"%'";
                    and = true;
                }
            }
            if (!provincia.equals("Selecciona"))
            {
                if (and)
                {
                    query = query + " AND pr_provincia like '%"+provincia+"%'";
                }
                else
                {
                    query = query + " pr_provincia like '%"+provincia+"%'";
                    and = true;
                }
            }

            System.out.println(query);
            CSResultBuscarProveedor resultBuscarProveedor = new CSResultBuscarProveedor(query);

            }
}//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        CSDesktop.BuscarProveedor.dispose();
        CSDesktop.menuBuscarProveedor.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextNombreFocusLost
       String nombreM = jTextNombre.getText().toUpperCase();
       jTextNombre.setText(nombreM);
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
    private javax.swing.JComboBox jComboBoxProvincia;
    private javax.swing.JComboBox jComboBoxRegimen;
    private javax.swing.JComboBox jComboBoxTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextCodPostal;
    private javax.swing.JTextField jTextDNI;
    private javax.swing.JTextField jTextDireccion;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNombreCon;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JTextField jTextPoblacion;
    private javax.swing.JLabel lCodPostal;
    private javax.swing.JLabel lDNI;
    private javax.swing.JLabel lDireccion;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lNombreCon;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lPContacto;
    private javax.swing.JLabel lPoblacion;
    private javax.swing.JLabel lProvincia;
    private javax.swing.JLabel lRegimen;
    private javax.swing.JLabel lTipo;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonBuscar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonBuscar.setEnabled(true);
    }

    /**
     * Función para la limitación de los campos
     */
    private void limitacionesCampos()
    {
        LimitadorDeDocumento limitadorDNI= new LimitadorDeDocumento(10);
        jTextDNI.setDocument(limitadorDNI);
        LimitadorDeDocumento limitador5= new LimitadorDeDocumento(5);
        jTextCodPostal.setDocument(limitador5);
        LimitadorDeDocumento limitadorNombre= new LimitadorDeDocumento(50);
        jTextNombre.setDocument(limitadorNombre);
        LimitadorDeDocumento limitadorEmail= new LimitadorDeDocumento(50);
        LimitadorDeDocumento limitadorNomC= new LimitadorDeDocumento(50);
        jTextNombreCon.setDocument(limitadorNomC);
     }
}