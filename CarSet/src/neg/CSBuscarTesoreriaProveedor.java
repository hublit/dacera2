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

/**
 *
 * @author depr73
 */
public class CSBuscarTesoreriaProveedor extends javax.swing.JPanel
{

    /** Creates new form ABBuscarProveedores */
    public CSBuscarTesoreriaProveedor()
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
                this.getComponents()[k] != jComboBoxTipo);
            {
                this.getComponents()[k].addKeyListener(l);
            }
        }
        jTextNumero.addKeyListener(l);
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
        jButtonBuscar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lPoblacion = new javax.swing.JLabel();
        lNumero = new javax.swing.JLabel();
        jTextNumero = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        lDNI = new javax.swing.JLabel();
        lNombre = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jComboBoxRegimen = new javax.swing.JComboBox();
        lRegimen = new javax.swing.JLabel();
        jTextDNI = new javax.swing.JTextField();
        lCodPostal = new javax.swing.JLabel();
        lTipo = new javax.swing.JLabel();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        numFc = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setForeground(new java.awt.Color(0, 0, 100));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 379));

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

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        lPoblacion.setForeground(new java.awt.Color(0, 0, 100));
        lPoblacion.setText("Fecha Factura");
        lPoblacion.setName("lPoblacion"); // NOI18N

        lNumero.setForeground(new java.awt.Color(0, 0, 100));
        lNumero.setText("Núm. Proveedor PR/");
        lNumero.setName("lNumero"); // NOI18N

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

        lNombre.setForeground(new java.awt.Color(0, 0, 100));
        lNombre.setText("Nombre");
        lNombre.setName("lNombre"); // NOI18N

        jComboBoxTipo.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxTipo.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Gruero", "Conductor", "Tren", "Custodia", "Global" }));
        jComboBoxTipo.setName("jComboBoxTipo"); // NOI18N

        jComboBoxRegimen.setBackground(new java.awt.Color(228, 229, 255));
        jComboBoxRegimen.setForeground(new java.awt.Color(0, 0, 100));
        jComboBoxRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona", "Empresa", "Régimen General", "Autónomo" }));
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

        lCodPostal.setForeground(new java.awt.Color(0, 0, 100));
        lCodPostal.setName("lCodPostal"); // NOI18N

        lTipo.setForeground(new java.awt.Color(0, 0, 100));
        lTipo.setText("Tipo");
        lTipo.setName("lTipo"); // NOI18N

        jDateFecha.setDateFormatString("dd-MM-yyyy"); // NOI18N
        jDateFecha.setName("jDateFecha"); // NOI18N

        numFc.setName("numFc"); // NOI18N
        numFc.setText("Núm. factura");

        jTextField1.setName("jTextField1"); // NOI18N

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Búsqueda de pedidos facturados a clientes");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(numFc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(lPoblacion)
                                .addGap(10, 10, 10)
                                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lRegimen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(lTipo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxTipo, 0, 292, Short.MAX_VALUE)
                                .addGap(207, 207, 207))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(lNumero)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lCodPostal)
                .addGap(7, 7, 7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(lCodPostal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numFc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lPoblacion)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lDNI)
                            .addComponent(lNumero))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lRegimen)
                            .addComponent(jComboBoxRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lTipo)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCancelar)
                            .addComponent(jButtonBuscar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jLabel1.setText("jLabel1");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(714, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(382, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            String query = "SELECT pr.pr_id, pr_nombre_fiscal, pr.pr_tipo, pr.pr_provincia, pr.pr_plazo, fp.fp_tipo" +
                           " FROM pr_proveedores pr, fp_forma_pago fp" +
                           " WHERE pr.fp_id = fp.fp_id";

            if (numero!=-1)
            {
                query = query + " AND pr.pr_id = "+numero;
            }
             if (!nombre.equals(""))
            {
                query = query + " AND pr.pr_nombre_fiscal like '%"+nombre+"%'";
            }
            if (!dni.equals(""))
            {
                query = query + " AND pr.pr_DNI_CIF = '"+dni+"'";
            }
            if (!regimen.equals("Selecciona"))
            {
                query = query + " AND pr.pr_regimen = '"+regimen+"'";
            }
            if (!tipo.equals("Selecciona"))
            {
                query = query + " AND pr.pr_tipo = '"+tipo+"'";
            }
            if (!codPostal.equals(""))
            {
                query = query + " AND pr.pr_cod_postal = '"+codPostal+"'";
            }
            if (!poblacion.equals(""))
            {
                query = query + " AND pr.pr_poblacion like '%"+poblacion+"%'";
            }
            if (!provincia.equals("Selecciona"))
            {
                query = query + " AND pr.pr_provincia like '%"+provincia+"%'";
            }
            query = query + " ORDER BY pr.pr_nombre_fiscal ASC";
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox jComboBoxRegimen;
    private javax.swing.JComboBox jComboBoxTipo;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextDNI;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNumero;
    private javax.swing.JLabel lCodPostal;
    private javax.swing.JLabel lDNI;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lPoblacion;
    private javax.swing.JLabel lRegimen;
    private javax.swing.JLabel lTipo;
    private java.awt.Label numFc;
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