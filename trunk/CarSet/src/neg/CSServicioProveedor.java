/*
 * CSServicioProveedor.java
 *
 * Created on 03-oct-2009, 0:25:10
 */

package neg;

import utils.Utilidades;
import utils.LimitadorDeDocumento;
import data.DbConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author lito
 */
public class CSServicioProveedor extends javax.swing.JPanel
{
    DbConnection datos = new DbConnection();
    int pr_id = 0;
    static int sp_id = 0;

    public CSServicioProveedor(String query, int proveedor) throws SQLException
    {
        ResultSet rs = datos.select(query);
        
        CSDesktop.EditarProveedor.setEnabled(false);
        initComponents();
        limitacionesCampos();
        pr_id = proveedor;
        int numeroFila = 0;
        try {
            while (rs.next()) {
                sp_id = rs.getInt("sp_id");
                jTextIndustrial.setText(rs.getString("sp_industrial"));
                jText4x4.setText(rs.getString("sp_todoterreno"));
                jTextFurgonetas.setText(rs.getString("sp_furgonetas"));
                jTextFurgones.setText(rs.getString("sp_furgones"));
                jTextItv.setText(rs.getString("sp_itv"));
                jTextPreItv.setText(rs.getString("sp_pre_itv"));
                jTextItvPreItv.setText(rs.getString("sp_itv_pre_itv"));
                jTextIdaVuelta.setText(rs.getString("sp_ida_vuelta"));
                jTextCampaEntrada.setText(rs.getString("sp_entrada_campa"));
                jTextCampa.setText(rs.getString("sp_campa"));
                jTextMObraMChapa.setText(rs.getString("sp_mo_mecanica_chapa"));
                jTextPeritacion.setText(rs.getString("sp_peritacion"));
                jTextChequeo.setText(rs.getString("sp_chequeo"));
                jTextRepostaje.setText(rs.getString("sp_repostaje"));
                jTextSuplemento.setText(rs.getString("sp_suplemento"));
                jTextUrgente.setText(rs.getString("sp_urgente"));
                jTextLavadoEx.setText(rs.getString("sp_lavado_exterior"));
                jTextLavadoINEX.setText(rs.getString("sp_lavado_exin"));
                jTextLavadoInt.setText(rs.getString("sp_lavado_integral"));
                jTextLavadoInEx4.setText(rs.getString("sp_int_ext_cuatro"));
                jTextLavadoInt4.setText(rs.getString("sp_integral_cuatro"));
                jTextLavadoInExInd.setText(rs.getString("sp_int_ext_industrial"));
                jTextLavadoIntInd.setText(rs.getString("sp_integral_industrial"));
                jTextDesrotularPegFacil.setText(rs.getString("sp_desrotular_peg_facil"));
                jTextDesrotularPegNormal.setText(rs.getString("sp_desrotular_peg_normal"));
                jTextDesrotularPegDificil.setText(rs.getString("sp_desrotular_peg_dificil"));
                jTextRotularPegFacil.setText(rs.getString("sp_rotular_peg_facil"));
                jTextRotularPegNormal.setText(rs.getString("sp_rotular_peg_normal"));
                jTextRotularPegDificil.setText(rs.getString("sp_rotular_peg_dificil"));
                jTextLavadoDEx.setText(rs.getString("sp_ldom_exterior"));
                jTextLavadoDINEX.setText(rs.getString("sp_ldom_exin"));
                jTextLavadoDIn.setText(rs.getString("sp_ldom_integral"));
                jTextLavadoDIntEx4.setText(rs.getString("sp_ldom_int_ext_cuatro"));
                jTextLavadoDIn4.setText(rs.getString("sp_ldom_integral_cuatro"));
                jTextLavadoDInExInd.setText(rs.getString("sp_ldom_int_ext_industrial"));
                jTextLavadoDIntInd.setText(rs.getString("sp_ldom_integral_industrial"));
                numeroFila++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CSServicioProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        CSDesktop.ServicioProveedor = new JInternalFrame("Sercicios Especiales de Proveedor", true, false, false, true );
        CSDesktop.ServicioProveedor.getContentPane().add( this, BorderLayout.CENTER );
        CSDesktop.ServicioProveedor.pack();
        CSDesktop.ServicioProveedor.dispose();
        CSDesktop.elEscritorio.add( CSDesktop.ServicioProveedor );
        Dimension pantalla = CSDesktop.elEscritorio.getSize();
        Dimension ventana = CSDesktop.ServicioProveedor.getSize();
        CSDesktop.ServicioProveedor.setLocation(
           (pantalla.width - ventana.width) / 2,
           (pantalla.height - ventana.height) / 2);
        CSDesktop.ServicioProveedor.setVisible( true );

         CSDesktop.EditarProveedor.addInternalFrameListener(new InternalFrameListener() {

            public void internalFrameOpened(InternalFrameEvent e) {
            }

            public void internalFrameClosing(InternalFrameEvent e) {

            }

            public void internalFrameClosed(InternalFrameEvent e) {

            }

            public void internalFrameIconified(InternalFrameEvent e) {

            }

            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            public void internalFrameActivated(InternalFrameEvent e) {
                CSDesktop.ServicioProveedor.moveToFront();
            }

            public void internalFrameDeactivated(InternalFrameEvent e) {
            }
        });

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
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonModificar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lEuro21 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTextRotularPegDificil = new javax.swing.JTextField();
        jTextItv = new javax.swing.JTextField();
        lRotularPegDificil = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lItv = new javax.swing.JLabel();
        jTextRotularPegNormal = new javax.swing.JTextField();
        lFurgonetas = new javax.swing.JLabel();
        lEuro20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lRotularPegNormal = new javax.swing.JLabel();
        jTextFurgonetas = new javax.swing.JTextField();
        lLavadoIntInd = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lEuro12 = new javax.swing.JLabel();
        jTextLavadoInExInd = new javax.swing.JTextField();
        jTextLavadoIntInd = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        l4x4 = new javax.swing.JLabel();
        jText4x4 = new javax.swing.JTextField();
        lLavadoInExInd = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lEuro11 = new javax.swing.JLabel();
        lChequeo = new javax.swing.JLabel();
        lFCorrecion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextIndustrial = new javax.swing.JTextField();
        lIndustrial = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lFurgones = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lUrgente = new javax.swing.JLabel();
        jTextUrgente = new javax.swing.JTextField();
        jTextChequeo = new javax.swing.JTextField();
        ltantoxciento1 = new javax.swing.JLabel();
        jTextLavadoInt4 = new javax.swing.JTextField();
        lLavadoInt4 = new javax.swing.JLabel();
        jTextFurgones = new javax.swing.JTextField();
        lEuro10 = new javax.swing.JLabel();
        lEuro30 = new javax.swing.JLabel();
        lEuro28 = new javax.swing.JLabel();
        lEuro29 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lEuro9 = new javax.swing.JLabel();
        jTextLavadoInEx4 = new javax.swing.JTextField();
        lLavadoInEx4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lEuro8 = new javax.swing.JLabel();
        jTextLavadoInt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lLavadoInt = new javax.swing.JLabel();
        lEuro7 = new javax.swing.JLabel();
        lEuro27 = new javax.swing.JLabel();
        lEuro22 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextSuplemento = new javax.swing.JTextField();
        lFCorrecion2 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextIdaVuelta = new javax.swing.JTextField();
        lSuplemento = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lEuro23 = new javax.swing.JLabel();
        lEuro24 = new javax.swing.JLabel();
        jTextCampa = new javax.swing.JTextField();
        lEuro25 = new javax.swing.JLabel();
        lIdaVuelta = new javax.swing.JLabel();
        lEuro26 = new javax.swing.JLabel();
        lCampa = new javax.swing.JLabel();
        jTextLavadoDIntInd = new javax.swing.JTextField();
        lLavadoDIntInd = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lFCorrecion3 = new javax.swing.JLabel();
        jTextPreItv = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextLavadoDEx = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextLavadoDIntEx4 = new javax.swing.JTextField();
        lLavadoEx = new javax.swing.JLabel();
        jTextLavadoINEX = new javax.swing.JTextField();
        lLavadoInEx = new javax.swing.JLabel();
        jTextItvPreItv = new javax.swing.JTextField();
        lItvPreItv = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lEuro6 = new javax.swing.JLabel();
        lPeritacion = new javax.swing.JLabel();
        jTextPeritacion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lPreItv1 = new javax.swing.JLabel();
        jTextLavadoEx = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lFCorrecion1 = new javax.swing.JLabel();
        jTextLavadoDInExInd = new javax.swing.JTextField();
        lLavadoDEx = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lLavadoDInEx = new javax.swing.JLabel();
        jTextLavadoDINEX = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        lLavadoDIn = new javax.swing.JLabel();
        jTextLavadoDIn = new javax.swing.JTextField();
        lLavadoDIntEx4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lEuro1 = new javax.swing.JLabel();
        lEuro2 = new javax.swing.JLabel();
        ltantoxciento = new javax.swing.JLabel();
        lEuro = new javax.swing.JLabel();
        lEuro3 = new javax.swing.JLabel();
        lEuro5 = new javax.swing.JLabel();
        lLavadoDInExInd = new javax.swing.JLabel();
        lCampaEntrada = new javax.swing.JLabel();
        jTextCampaEntrada = new javax.swing.JTextField();
        jTextDesrotularPegDificil = new javax.swing.JTextField();
        jTextRotularPegFacil = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextDesrotularPegNormal = new javax.swing.JTextField();
        lEuro17 = new javax.swing.JLabel();
        lLavadoDIn4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextLavadoDIn4 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lRepostaje = new javax.swing.JLabel();
        jTextRepostaje = new javax.swing.JTextField();
        lEuro18 = new javax.swing.JLabel();
        lEuro16 = new javax.swing.JLabel();
        lDesrotularPegDificil = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextMObraMChapa = new javax.swing.JTextField();
        lMObraMChapa = new javax.swing.JLabel();
        lEuro19 = new javax.swing.JLabel();
        lEuro14 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lDesrotularPegFacil = new javax.swing.JLabel();
        jTextDesrotularPegFacil = new javax.swing.JTextField();
        lEuro13 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lRotularPegFacil = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lDesrotularPegNormal = new javax.swing.JLabel();
        lEuro15 = new javax.swing.JLabel();

        jButtonModificar.setText("Modificar");
        jButtonModificar.setName("jButtonModificar"); // NOI18N
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
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

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("* Campos obligatorios");
        jLabel1.setName("jLabel1"); // NOI18N

        jSeparator2.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator2.setName("jSeparator2"); // NOI18N

        lEuro21.setForeground(new java.awt.Color(0, 0, 100));
        lEuro21.setText("€");
        lEuro21.setName("lEuro21"); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        jTextRotularPegDificil.setName("jTextRotularPegDificil"); // NOI18N

        jTextItv.setName("jTextItv"); // NOI18N

        lRotularPegDificil.setForeground(new java.awt.Color(0, 0, 100));
        lRotularPegDificil.setText("Rotular Pegatinas Difícil");
        lRotularPegDificil.setName("lRotularPegDificil"); // NOI18N

        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel46.setForeground(new java.awt.Color(204, 0, 0));
        jLabel46.setText("*");
        jLabel46.setName("jLabel46"); // NOI18N

        lItv.setForeground(new java.awt.Color(0, 0, 100));
        lItv.setText("ITV Conductor");
        lItv.setName("lItv"); // NOI18N

        jTextRotularPegNormal.setName("jTextRotularPegNormal"); // NOI18N

        lFurgonetas.setForeground(new java.awt.Color(0, 0, 100));
        lFurgonetas.setText("Furgonetas");
        lFurgonetas.setName("lFurgonetas"); // NOI18N

        lEuro20.setForeground(new java.awt.Color(0, 0, 100));
        lEuro20.setText("€");
        lEuro20.setName("lEuro20"); // NOI18N

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel45.setForeground(new java.awt.Color(204, 0, 0));
        jLabel45.setText("*");
        jLabel45.setName("jLabel45"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        lRotularPegNormal.setForeground(new java.awt.Color(0, 0, 100));
        lRotularPegNormal.setText("Rotular Pegatinas Normal");
        lRotularPegNormal.setName("lRotularPegNormal"); // NOI18N

        jTextFurgonetas.setName("jTextFurgonetas"); // NOI18N

        lLavadoIntInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoIntInd.setText("Integral (Industrial)");
        lLavadoIntInd.setName("lLavadoIntInd"); // NOI18N

        jLabel31.setForeground(new java.awt.Color(204, 0, 0));
        jLabel31.setText("*");
        jLabel31.setName("jLabel31"); // NOI18N

        lEuro12.setForeground(new java.awt.Color(0, 0, 100));
        lEuro12.setText("€");
        lEuro12.setName("lEuro12"); // NOI18N

        jTextLavadoInExInd.setName("jTextLavadoInExInd"); // NOI18N

        jTextLavadoIntInd.setName("jTextLavadoIntInd"); // NOI18N

        jLabel29.setForeground(new java.awt.Color(204, 0, 0));
        jLabel29.setText("*");
        jLabel29.setName("jLabel29"); // NOI18N

        l4x4.setForeground(new java.awt.Color(0, 0, 100));
        l4x4.setText("Todoterreno");
        l4x4.setName("l4x4"); // NOI18N

        jText4x4.setName("jText4x4"); // NOI18N

        lLavadoInExInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInExInd.setText("Interior y Exterior (Indus.)");
        lLavadoInExInd.setName("lLavadoInExInd"); // NOI18N

        jLabel30.setForeground(new java.awt.Color(204, 0, 0));
        jLabel30.setText("*");
        jLabel30.setName("jLabel30"); // NOI18N

        lEuro11.setForeground(new java.awt.Color(0, 0, 100));
        lEuro11.setText("€");
        lEuro11.setName("lEuro11"); // NOI18N

        lChequeo.setForeground(new java.awt.Color(0, 0, 100));
        lChequeo.setText("Chequeo");
        lChequeo.setName("lChequeo"); // NOI18N

        lFCorrecion.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion.setText("FACTOR DE CORRECIÓN");
        lFCorrecion.setName("lFCorrecion"); // NOI18N

        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jLabel8.setName("jLabel8"); // NOI18N

        jTextIndustrial.setName("jTextIndustrial"); // NOI18N

        lIndustrial.setForeground(new java.awt.Color(0, 0, 100));
        lIndustrial.setText("Industrial y Monovolumen");
        lIndustrial.setName("lIndustrial"); // NOI18N

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");
        jLabel9.setName("jLabel9"); // NOI18N

        lFurgones.setForeground(new java.awt.Color(0, 0, 100));
        lFurgones.setText("Furgones");
        lFurgones.setName("lFurgones"); // NOI18N

        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");
        jLabel13.setName("jLabel13"); // NOI18N

        lUrgente.setForeground(new java.awt.Color(0, 0, 100));
        lUrgente.setText("Urgente");
        lUrgente.setName("lUrgente"); // NOI18N

        jTextUrgente.setName("jTextUrgente"); // NOI18N

        jTextChequeo.setName("jTextChequeo"); // NOI18N

        ltantoxciento1.setForeground(new java.awt.Color(0, 0, 100));
        ltantoxciento1.setText("%");
        ltantoxciento1.setName("ltantoxciento1"); // NOI18N

        jTextLavadoInt4.setName("jTextLavadoInt4"); // NOI18N

        lLavadoInt4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInt4.setText("Integral (4x4)");
        lLavadoInt4.setName("lLavadoInt4"); // NOI18N

        jTextFurgones.setName("jTextFurgones"); // NOI18N

        lEuro10.setForeground(new java.awt.Color(0, 0, 100));
        lEuro10.setText("€");
        lEuro10.setName("lEuro10"); // NOI18N

        lEuro30.setForeground(new java.awt.Color(0, 0, 100));
        lEuro30.setText("€");
        lEuro30.setName("lEuro30"); // NOI18N

        lEuro28.setForeground(new java.awt.Color(0, 0, 100));
        lEuro28.setText("€");
        lEuro28.setName("lEuro28"); // NOI18N

        lEuro29.setForeground(new java.awt.Color(0, 0, 100));
        lEuro29.setText("€");
        lEuro29.setName("lEuro29"); // NOI18N

        jLabel27.setForeground(new java.awt.Color(204, 0, 0));
        jLabel27.setText("*");
        jLabel27.setName("jLabel27"); // NOI18N

        lEuro9.setForeground(new java.awt.Color(0, 0, 100));
        lEuro9.setText("€");
        lEuro9.setName("lEuro9"); // NOI18N

        jTextLavadoInEx4.setName("jTextLavadoInEx4"); // NOI18N

        lLavadoInEx4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInEx4.setText("Interior y Exterior (4x4)");
        lLavadoInEx4.setName("lLavadoInEx4"); // NOI18N

        jLabel26.setForeground(new java.awt.Color(204, 0, 0));
        jLabel26.setText("*");
        jLabel26.setName("jLabel26"); // NOI18N

        lEuro8.setForeground(new java.awt.Color(0, 0, 100));
        lEuro8.setText("€");
        lEuro8.setName("lEuro8"); // NOI18N

        jTextLavadoInt.setName("jTextLavadoInt"); // NOI18N

        jLabel25.setForeground(new java.awt.Color(204, 0, 0));
        jLabel25.setText("*");
        jLabel25.setName("jLabel25"); // NOI18N

        lLavadoInt.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInt.setText("Integral (Turismo)");
        lLavadoInt.setName("lLavadoInt"); // NOI18N

        lEuro7.setForeground(new java.awt.Color(0, 0, 100));
        lEuro7.setText("€");
        lEuro7.setName("lEuro7"); // NOI18N

        lEuro27.setForeground(new java.awt.Color(0, 0, 100));
        lEuro27.setText("€");
        lEuro27.setName("lEuro27"); // NOI18N

        lEuro22.setForeground(new java.awt.Color(0, 0, 100));
        lEuro22.setText("€");
        lEuro22.setName("lEuro22"); // NOI18N

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        jLabel17.setName("jLabel17"); // NOI18N

        jTextSuplemento.setName("jTextSuplemento"); // NOI18N

        lFCorrecion2.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion2.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion2.setText("OTROS");
        lFCorrecion2.setName("lFCorrecion2"); // NOI18N

        jLabel48.setForeground(new java.awt.Color(204, 0, 0));
        jLabel48.setText("*");
        jLabel48.setName("jLabel48"); // NOI18N

        jTextIdaVuelta.setName("jTextIdaVuelta"); // NOI18N

        lSuplemento.setForeground(new java.awt.Color(0, 0, 100));
        lSuplemento.setText("Suplemento");
        lSuplemento.setName("lSuplemento"); // NOI18N

        jSeparator5.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator5.setName("jSeparator5"); // NOI18N

        lEuro23.setForeground(new java.awt.Color(0, 0, 100));
        lEuro23.setText("€");
        lEuro23.setName("lEuro23"); // NOI18N

        lEuro24.setForeground(new java.awt.Color(0, 0, 100));
        lEuro24.setText("€");
        lEuro24.setName("lEuro24"); // NOI18N

        jTextCampa.setName("jTextCampa"); // NOI18N

        lEuro25.setForeground(new java.awt.Color(0, 0, 100));
        lEuro25.setText("€");
        lEuro25.setName("lEuro25"); // NOI18N

        lIdaVuelta.setForeground(new java.awt.Color(0, 0, 100));
        lIdaVuelta.setText("Ida-Vuelta");
        lIdaVuelta.setName("lIdaVuelta"); // NOI18N

        lEuro26.setForeground(new java.awt.Color(0, 0, 100));
        lEuro26.setText("€");
        lEuro26.setName("lEuro26"); // NOI18N

        lCampa.setForeground(new java.awt.Color(0, 0, 100));
        lCampa.setText("Campa Día");
        lCampa.setName("lCampa"); // NOI18N

        jTextLavadoDIntInd.setName("jTextLavadoDIntInd"); // NOI18N

        lLavadoDIntInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDIntInd.setText("Lavado Integral (industrial)");
        lLavadoDIntInd.setName("lLavadoDIntInd"); // NOI18N

        jLabel44.setForeground(new java.awt.Color(204, 0, 0));
        jLabel44.setText("*");
        jLabel44.setName("jLabel44"); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator6.setName("jSeparator6"); // NOI18N

        lFCorrecion3.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion3.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion3.setText("LAVADOS A DOMICILIO");
        lFCorrecion3.setName("lFCorrecion3"); // NOI18N

        jTextPreItv.setName("jTextPreItv"); // NOI18N

        jLabel43.setForeground(new java.awt.Color(204, 0, 0));
        jLabel43.setText("*");
        jLabel43.setName("jLabel43"); // NOI18N

        jTextLavadoDEx.setName("jTextLavadoDEx"); // NOI18N

        jLabel47.setForeground(new java.awt.Color(204, 0, 0));
        jLabel47.setText("*");
        jLabel47.setName("jLabel47"); // NOI18N

        jLabel42.setForeground(new java.awt.Color(204, 0, 0));
        jLabel42.setText("*");
        jLabel42.setName("jLabel42"); // NOI18N

        jLabel41.setForeground(new java.awt.Color(204, 0, 0));
        jLabel41.setText("*");
        jLabel41.setName("jLabel41"); // NOI18N

        jLabel24.setForeground(new java.awt.Color(204, 0, 0));
        jLabel24.setText("*");
        jLabel24.setName("jLabel24"); // NOI18N

        jTextLavadoDIntEx4.setName("jTextLavadoDIntEx4"); // NOI18N

        lLavadoEx.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoEx.setText("Exterior");
        lLavadoEx.setName("lLavadoEx"); // NOI18N

        jTextLavadoINEX.setName("jTextLavadoINEX"); // NOI18N

        lLavadoInEx.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInEx.setText("Interior y Exterior (Turismo)");
        lLavadoInEx.setName("lLavadoInEx"); // NOI18N

        jTextItvPreItv.setName("jTextItvPreItv"); // NOI18N

        lItvPreItv.setForeground(new java.awt.Color(0, 0, 100));
        lItvPreItv.setText("ITV Grúa");
        lItvPreItv.setName("lItvPreItv"); // NOI18N

        jLabel23.setForeground(new java.awt.Color(204, 0, 0));
        jLabel23.setText("*");
        jLabel23.setName("jLabel23"); // NOI18N

        lEuro6.setForeground(new java.awt.Color(0, 0, 100));
        lEuro6.setText("€");
        lEuro6.setName("lEuro6"); // NOI18N

        lPeritacion.setForeground(new java.awt.Color(0, 0, 100));
        lPeritacion.setText("Peritación");
        lPeritacion.setName("lPeritacion"); // NOI18N

        jTextPeritacion.setName("jTextPeritacion"); // NOI18N

        jLabel22.setForeground(new java.awt.Color(204, 0, 0));
        jLabel22.setText("*");
        jLabel22.setName("jLabel22"); // NOI18N

        lPreItv1.setForeground(new java.awt.Color(0, 0, 100));
        lPreItv1.setText("Pre-ITV");
        lPreItv1.setName("lPreItv1"); // NOI18N

        jTextLavadoEx.setName("jTextLavadoEx"); // NOI18N

        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");
        jLabel18.setName("jLabel18"); // NOI18N

        lFCorrecion1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion1.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion1.setText("LAVADOS CAMPA");
        lFCorrecion1.setName("lFCorrecion1"); // NOI18N

        jTextLavadoDInExInd.setName("jTextLavadoDInExInd"); // NOI18N

        lLavadoDEx.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDEx.setText("Exterior");
        lLavadoDEx.setName("lLavadoDEx"); // NOI18N

        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("*");
        jLabel39.setName("jLabel39"); // NOI18N

        lLavadoDInEx.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDInEx.setText("Interior y Exterior (Turismo)");
        lLavadoDInEx.setName("lLavadoDInEx"); // NOI18N

        jTextLavadoDINEX.setName("jTextLavadoDINEX"); // NOI18N

        jLabel40.setForeground(new java.awt.Color(204, 0, 0));
        jLabel40.setText("*");
        jLabel40.setName("jLabel40"); // NOI18N

        lLavadoDIn.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDIn.setText("Integral (Turismo)");
        lLavadoDIn.setName("lLavadoDIn"); // NOI18N

        jTextLavadoDIn.setName("jTextLavadoDIn"); // NOI18N

        lLavadoDIntEx4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDIntEx4.setText("Interior y Exterior (4x4)");
        lLavadoDIntEx4.setName("lLavadoDIntEx4"); // NOI18N

        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");
        jLabel19.setName("jLabel19"); // NOI18N

        lEuro1.setForeground(new java.awt.Color(0, 0, 100));
        lEuro1.setText("€");
        lEuro1.setName("lEuro1"); // NOI18N

        lEuro2.setForeground(new java.awt.Color(0, 0, 100));
        lEuro2.setText("€");
        lEuro2.setName("lEuro2"); // NOI18N

        ltantoxciento.setForeground(new java.awt.Color(0, 0, 100));
        ltantoxciento.setText("%");
        ltantoxciento.setName("ltantoxciento"); // NOI18N

        lEuro.setForeground(new java.awt.Color(0, 0, 100));
        lEuro.setText("€");
        lEuro.setName("lEuro"); // NOI18N

        lEuro3.setForeground(new java.awt.Color(0, 0, 100));
        lEuro3.setText("€");
        lEuro3.setName("lEuro3"); // NOI18N

        lEuro5.setForeground(new java.awt.Color(0, 0, 100));
        lEuro5.setText("€");
        lEuro5.setName("lEuro5"); // NOI18N

        lLavadoDInExInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDInExInd.setText("Interior y Exterior (indus.)");
        lLavadoDInExInd.setName("lLavadoDInExInd"); // NOI18N

        lCampaEntrada.setForeground(new java.awt.Color(0, 0, 100));
        lCampaEntrada.setText("Entrada en Campa");
        lCampaEntrada.setName("lCampaEntrada"); // NOI18N

        jTextCampaEntrada.setName("jTextCampaEntrada"); // NOI18N

        jTextDesrotularPegDificil.setName("jTextDesrotularPegDificil"); // NOI18N

        jTextRotularPegFacil.setName("jTextRotularPegFacil"); // NOI18N

        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setText("*");
        jLabel37.setName("jLabel37"); // NOI18N

        jTextDesrotularPegNormal.setName("jTextDesrotularPegNormal"); // NOI18N

        lEuro17.setForeground(new java.awt.Color(0, 0, 100));
        lEuro17.setText("€");
        lEuro17.setName("lEuro17"); // NOI18N

        lLavadoDIn4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoDIn4.setText("Integral 4x4");
        lLavadoDIn4.setName("lLavadoDIn4"); // NOI18N

        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("*");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel28.setForeground(new java.awt.Color(204, 0, 0));
        jLabel28.setText("*");
        jLabel28.setName("jLabel28"); // NOI18N

        jTextLavadoDIn4.setName("jTextLavadoDIn4"); // NOI18N

        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setText("*");
        jLabel38.setName("jLabel38"); // NOI18N

        jLabel36.setForeground(new java.awt.Color(204, 0, 0));
        jLabel36.setText("*");
        jLabel36.setName("jLabel36"); // NOI18N

        lRepostaje.setForeground(new java.awt.Color(0, 0, 100));
        lRepostaje.setText("Repostaje");
        lRepostaje.setName("lRepostaje"); // NOI18N

        jTextRepostaje.setName("jTextRepostaje"); // NOI18N

        lEuro18.setForeground(new java.awt.Color(0, 0, 100));
        lEuro18.setText("€");
        lEuro18.setName("lEuro18"); // NOI18N

        lEuro16.setForeground(new java.awt.Color(0, 0, 100));
        lEuro16.setText("€");
        lEuro16.setName("lEuro16"); // NOI18N

        lDesrotularPegDificil.setForeground(new java.awt.Color(0, 0, 100));
        lDesrotularPegDificil.setText("Desrotular Pegatinas Difícil");
        lDesrotularPegDificil.setName("lDesrotularPegDificil"); // NOI18N

        jLabel35.setForeground(new java.awt.Color(204, 0, 0));
        jLabel35.setText("*");
        jLabel35.setName("jLabel35"); // NOI18N

        jTextMObraMChapa.setName("jTextMObraMChapa"); // NOI18N

        lMObraMChapa.setForeground(new java.awt.Color(0, 0, 100));
        lMObraMChapa.setText("Mano Obra Mec. y Chapa");
        lMObraMChapa.setName("lMObraMChapa"); // NOI18N

        lEuro19.setForeground(new java.awt.Color(0, 0, 100));
        lEuro19.setText("€");
        lEuro19.setName("lEuro19"); // NOI18N

        lEuro14.setForeground(new java.awt.Color(0, 0, 100));
        lEuro14.setText("€");
        lEuro14.setName("lEuro14"); // NOI18N

        jLabel33.setForeground(new java.awt.Color(204, 0, 0));
        jLabel33.setText("*");
        jLabel33.setName("jLabel33"); // NOI18N

        lDesrotularPegFacil.setForeground(new java.awt.Color(0, 0, 100));
        lDesrotularPegFacil.setText("Desrotular Pegatinas Fácil");
        lDesrotularPegFacil.setName("lDesrotularPegFacil"); // NOI18N

        jTextDesrotularPegFacil.setName("jTextDesrotularPegFacil"); // NOI18N

        lEuro13.setForeground(new java.awt.Color(0, 0, 100));
        lEuro13.setText("€");
        lEuro13.setName("lEuro13"); // NOI18N

        jLabel32.setForeground(new java.awt.Color(204, 0, 0));
        jLabel32.setText("*");
        jLabel32.setName("jLabel32"); // NOI18N

        lRotularPegFacil.setForeground(new java.awt.Color(0, 0, 100));
        lRotularPegFacil.setText("Rotular Pegatinas Fácil");
        lRotularPegFacil.setName("lRotularPegFacil"); // NOI18N

        jLabel34.setForeground(new java.awt.Color(204, 0, 0));
        jLabel34.setText("*");
        jLabel34.setName("jLabel34"); // NOI18N

        lDesrotularPegNormal.setForeground(new java.awt.Color(0, 0, 100));
        lDesrotularPegNormal.setText("Desrotular Pegatinas Normal");
        lDesrotularPegNormal.setName("lDesrotularPegNormal"); // NOI18N

        lEuro15.setForeground(new java.awt.Color(0, 0, 100));
        lEuro15.setText("€");
        lEuro15.setName("lEuro15"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFCorrecion, javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lIndustrial))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel12))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lFurgonetas)
                                                        .addComponent(l4x4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lFurgones)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jText4x4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFurgonetas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFurgones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextIndustrial, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel43)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoDEx))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel42)
                                                    .addComponent(jLabel44)
                                                    .addComponent(jLabel38)
                                                    .addComponent(jLabel41)
                                                    .addComponent(jLabel40)
                                                    .addComponent(jLabel39))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lLavadoDIn4)
                                                    .addComponent(lLavadoDInExInd)
                                                    .addComponent(lLavadoDInEx)
                                                    .addComponent(lLavadoDIntEx4)
                                                    .addComponent(lLavadoDIn)
                                                    .addComponent(lLavadoDIntInd))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jTextLavadoDIntInd, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addComponent(jTextLavadoDInExInd, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addComponent(jTextLavadoDIntEx4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addComponent(jTextLavadoDIn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jTextLavadoDINEX, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextLavadoDEx, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextLavadoDIn4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lEuro24)
                                    .addComponent(lEuro25)
                                    .addComponent(lEuro26)
                                    .addComponent(lEuro27)
                                    .addComponent(lEuro29)
                                    .addComponent(lEuro30)
                                    .addComponent(lEuro28))
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFCorrecion3, javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLavadoInEx4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLavadoInt4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lLavadoInEx)
                                    .addComponent(lLavadoInt)
                                    .addComponent(lLavadoEx)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLavadoInExInd))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lLavadoIntInd))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lDesrotularPegFacil))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lDesrotularPegNormal))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lRotularPegFacil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lDesrotularPegDificil)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel46))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lRotularPegDificil)
                                    .addComponent(lRotularPegNormal))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextLavadoInExInd, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextRotularPegFacil, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextRotularPegNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextLavadoIntInd, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextLavadoInEx4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextLavadoINEX, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextLavadoInt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextLavadoInt4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextLavadoEx, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextDesrotularPegFacil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextDesrotularPegNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextDesrotularPegDificil, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextRotularPegDificil, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lEuro17)
                                    .addComponent(lEuro10)
                                    .addComponent(lEuro9)
                                    .addComponent(lEuro8)
                                    .addComponent(lEuro7)
                                    .addComponent(lEuro2)
                                    .addComponent(lEuro12)
                                    .addComponent(lEuro13)
                                    .addComponent(lEuro14)
                                    .addComponent(lEuro16)
                                    .addComponent(lEuro20)
                                    .addComponent(lEuro15))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(lFCorrecion2)
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel28)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lChequeo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lMObraMChapa)
                                                    .addComponent(lRepostaje, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lSuplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                    .addComponent(lUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lItv)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lPreItv1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lItvPreItv)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lIdaVuelta)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lCampa, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                                    .addComponent(lCampaEntrada)
                                                    .addComponent(lPeritacion, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextCampaEntrada)
                                                    .addComponent(jTextRepostaje, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextItv)
                                                    .addComponent(jTextCampa)
                                                    .addComponent(jTextChequeo)
                                                    .addComponent(jTextItvPreItv)
                                                    .addComponent(jTextIdaVuelta, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextMObraMChapa)
                                                    .addComponent(jTextSuplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addComponent(jTextPeritacion)
                                                    .addComponent(jTextPreItv, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(8, 8, 8)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lEuro18)
                                                                .addComponent(lEuro11))
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lEuro6)
                                                                .addComponent(lEuro5)
                                                                .addComponent(lEuro1))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lEuro3)
                                                            .addComponent(ltantoxciento)
                                                            .addComponent(lEuro)
                                                            .addComponent(lEuro22)
                                                            .addComponent(lEuro19)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lEuro23))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ltantoxciento1))))))
                            .addComponent(lEuro21)))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(lFCorrecion1)
                        .addGap(304, 304, 304)))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFCorrecion2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro1)
                            .addComponent(jTextItv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(lItv))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro5)
                            .addComponent(jLabel22)
                            .addComponent(jTextPreItv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lPreItv1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextItvPreItv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro6)
                            .addComponent(jLabel23)
                            .addComponent(lItvPreItv))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextIdaVuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltantoxciento))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro3)
                            .addComponent(jTextCampaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lCampaEntrada)
                            .addComponent(jLabel28))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCampa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro)
                            .addComponent(lCampa)
                            .addComponent(jLabel17))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro22)
                            .addComponent(lPeritacion)
                            .addComponent(jTextPeritacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextMObraMChapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro19)
                            .addComponent(lMObraMChapa)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextChequeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro11)
                            .addComponent(jLabel29)
                            .addComponent(lChequeo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro18)
                            .addComponent(jLabel36)
                            .addComponent(jTextRepostaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lRepostaje))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro23)
                            .addComponent(lSuplemento)
                            .addComponent(jLabel48)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFCorrecion1)
                                .addGap(4, 4, 4)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(lEuro2)
                                    .addComponent(jTextIndustrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lLavadoEx)
                                    .addComponent(jTextLavadoEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(lEuro7)
                                    .addComponent(lLavadoInEx)
                                    .addComponent(jTextLavadoINEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(lLavadoInt)
                                    .addComponent(lEuro8)
                                    .addComponent(jTextLavadoInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lLavadoInEx4)
                                    .addComponent(jLabel26)
                                    .addComponent(lEuro9)
                                    .addComponent(jTextLavadoInEx4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lIdaVuelta)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lLavadoInt4)
                                    .addComponent(jLabel27)
                                    .addComponent(lEuro10)
                                    .addComponent(jTextLavadoInt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lFCorrecion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(lIndustrial))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jText4x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(l4x4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFurgonetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lFurgonetas)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFurgones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lFurgones)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addComponent(lFCorrecion3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(lLavadoInExInd)
                            .addComponent(lEuro12)
                            .addComponent(lLavadoDEx)
                            .addComponent(jLabel43)
                            .addComponent(lEuro24)
                            .addComponent(jTextLavadoInExInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextLavadoDEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lLavadoIntInd)
                            .addComponent(lEuro13)
                            .addComponent(jTextLavadoDINEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(lLavadoDInEx)
                            .addComponent(lEuro25)
                            .addComponent(jTextLavadoIntInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel32)
                            .addComponent(lDesrotularPegFacil)
                            .addComponent(lEuro14)
                            .addComponent(jTextLavadoDIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(lLavadoDIn)
                            .addComponent(lEuro26)
                            .addComponent(jTextDesrotularPegFacil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextLavadoDIntEx4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLavadoDIntEx4)
                            .addComponent(jLabel41)
                            .addComponent(lEuro27)
                            .addComponent(jLabel33)
                            .addComponent(lDesrotularPegNormal)
                            .addComponent(jTextDesrotularPegNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro15))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextDesrotularPegDificil, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lEuro16))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lDesrotularPegDificil)
                                .addComponent(jTextLavadoDIn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lLavadoDIn4)
                                .addComponent(jLabel42)
                                .addComponent(lEuro28)
                                .addComponent(jLabel35)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lRotularPegFacil)
                            .addComponent(lEuro17)
                            .addComponent(jLabel34)
                            .addComponent(jTextLavadoDInExInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(lEuro29)
                            .addComponent(lLavadoDInExInd)
                            .addComponent(jTextRotularPegFacil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextLavadoDIntInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro30)
                            .addComponent(lLavadoDIntInd)
                            .addComponent(jLabel44)
                            .addComponent(lRotularPegNormal)
                            .addComponent(jTextRotularPegNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(lEuro20)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltantoxciento1)
                            .addComponent(jLabel20)
                            .addComponent(lUrgente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextRotularPegDificil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lEuro21))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lRotularPegDificil)
                        .addComponent(jLabel46)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonModificar))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
 
            System.out.println("\nBoton Modificar de Servicios Proveedor.");
            
            String todoterreno = new String(jText4x4.getText());
            String industrial = new String(jTextIndustrial.getText());
            String furgonetas = new String(jTextFurgonetas.getText());
            String furgones = new String(jTextFurgones.getText());
            String lavadoEx = new String(jTextLavadoEx.getText());
            String lavadoExIn = new String(jTextLavadoINEX.getText());
            String lavadoInt = new String(jTextLavadoInt.getText());
            String lavadoIE4 = new String(jTextLavadoInEx4.getText());
            String lavadoIntegral4 = new String(jTextLavadoInt4.getText());
            String lavadoIEIndustrial = new String(jTextLavadoInExInd.getText());
            String lavadoIntInd = new String(jTextLavadoIntInd.getText());
            String desrotularPegaFacil = new String(jTextDesrotularPegFacil.getText());
            String desrotularPegaNormal = new String(jTextDesrotularPegNormal.getText());
            String desrotularPegaDificil = new String(jTextDesrotularPegDificil.getText());
            String rotularPegaFacil = new String(jTextRotularPegFacil.getText());
            String rotularPegaNormal = new String(jTextRotularPegNormal.getText());
            String rotularPegaDificil = new String(jTextRotularPegDificil.getText());
            String lavadoDomEx = new String(jTextLavadoDEx.getText());
            String lavadoDomExIn = new String(jTextLavadoDINEX.getText());
            String lavadoDomInt = new String(jTextLavadoDIn.getText());
            String lavadoDomIE4 = new String(jTextLavadoDIntEx4.getText());
            String lavadoDomIntegral4 = new String(jTextLavadoDIn4.getText());
            String lavadoDomIEIndustrial = new String(jTextLavadoDInExInd.getText());
            String lavadoDomIntInd = new String(jTextLavadoDIntInd.getText());
            String itv = new String(jTextItv.getText());
            String preItv = new String(jTextPreItv.getText());
            String itvPreItv = new String(jTextItvPreItv.getText());
            String idaVuelta = new String(jTextIdaVuelta.getText());
            String entradaCampa = new String(jTextCampaEntrada.getText());
            String campa = new String(jTextCampa.getText());
            String peritacion = new String(jTextPeritacion.getText());
            String mOMecanicaChapa = new String(jTextMObraMChapa.getText());
            String chequeo = new String(jTextChequeo.getText());
            String repostaje = new String(jTextRepostaje.getText());
            String suplemento = new String(jTextSuplemento.getText());
            String urgente = new String(jTextUrgente.getText());


            //CAMPOS OBLIGATORIOS
            if (!Utilidades.campoObligatorio(todoterreno,"todoterreno").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(todoterreno,"todoterreno"));
            }
            else if (!Utilidades.campoObligatorio(furgones,"Furgones").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(furgones,"Furgones"));
            }
            else if (!Utilidades.campoObligatorio(furgonetas,"Furgonetas").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(furgonetas,"Furgonetas"));
            }
            else if (!Utilidades.campoObligatorio(industrial,"Industrial").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(industrial,"Industrial"));
            }
            else if (!Utilidades.campoObligatorioCombo(idaVuelta,"Ida y vuelta").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorioCombo(idaVuelta,"Ida y vuelta"));
            }
            else if (!Utilidades.campoObligatorio(itv,"ITV").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(itv,"ITV"));
            }
            else if (!Utilidades.campoObligatorio(preItv,"Pre-ITV").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(preItv,"Pre-itv"));
            }
            else if (!Utilidades.campoObligatorio(itvPreItv,"ITV + Pre-ITV").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(itvPreItv,"ITV + Pre-itv"));
            }
            else if (!Utilidades.campoObligatorio(campa,"Campa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(campa,"Campa"));
            }
            else if (!Utilidades.campoObligatorio(entradaCampa,"Entrada en Campa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(entradaCampa, "Entrada en Campa"));
            }
            else if (!Utilidades.campoObligatorio(peritacion ,"Peritación").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(peritacion,"Peritación"));
            }
            else if (!Utilidades.campoObligatorio(mOMecanicaChapa,"Mano de Obra Mecánica y Chapa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(mOMecanicaChapa,"Mano de Obra Mecánica y Chapa"));
            }
            else if (!Utilidades.campoObligatorio(repostaje,"Repostaje").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(repostaje,"Repostaje"));
            }
            else if (!Utilidades.campoObligatorio(suplemento,"Suplemento").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(suplemento,"Suplemento"));
            }
            else if (!Utilidades.campoObligatorio(urgente,"Urgente").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(urgente,"Urgente"));
            }
            else if (!Utilidades.campoObligatorio(lavadoEx, "Lavado exterior").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoEx,"Lavado exterior"));
            }
            else if (!Utilidades.campoObligatorio(lavadoExIn,"Lavado Exterior e Interior").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoExIn,"Lavado Exterior e Interior"));
            }
            else if (!Utilidades.campoObligatorio(lavadoInt,"Lavado Integral").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoInt,"Lavado Integral"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIE4,"Lavado interior exterior 4x4").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIE4,"Lavado interior exterior 4x4"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIntegral4,"Lavado Integral 4x4").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIntegral4,"Lavado Integral 4x4"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIEIndustrial,"Lavado interior y exterior Industrial").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIEIndustrial,"Lavado interior y exterior Industrial"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomEx,"Lavado exterior a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomEx,"Lavado exterior a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomExIn,"Lavado Interior y Exterior a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomExIn,"Lavado Interior y Exterior a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomInt,"Lavado Integral a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomInt,"Lavado Integral a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomIE4,"Lavado interior exterior 4x4 a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomIE4,"Lavado interior exterior 4x4 a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomIntegral4,"Limpieza Integral 4x4 a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomIntegral4,"Limpieza Integral 4x4 a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomIEIndustrial,"Lavado interior exterior Industrial a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomIEIndustrial,"Lavado interior exterior Industrial a Domicilio"));
            }
            else if (!Utilidades.campoObligatorio(lavadoDomIntInd,"Limpieza Integral Industrial a Domicilio").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoDomIntInd,"Limpieza Integral Industrial a Domicilio"));
            }
            else
            {
                String query = "UPDATE sp_servicios_proveedores SET sp_todoterreno  = '"+todoterreno+"', sp_industrial  = '"+industrial+"', " +
                               "sp_furgonetas  = '"+furgonetas+"', sp_furgones  = '"+furgones+"', sp_lavado_exterior  = '"+lavadoEx+"', " +
                               "sp_lavado_exin  = '"+lavadoExIn+"', sp_lavado_integral  = '"+lavadoInt+"', sp_int_ext_cuatro  = '"+lavadoIE4+"', " +
                               "sp_integral_cuatro  = '"+lavadoIntegral4+"', sp_int_ext_industrial  = '"+lavadoIEIndustrial+"', " +
                               "sp_integral_industrial  = '"+lavadoIntInd+"', sp_desrotular_peg_facil  = '"+desrotularPegaFacil+"', " +
                               "sp_desrotular_peg_normal  = '"+desrotularPegaNormal+"', sp_desrotular_peg_dificil  = '"+desrotularPegaDificil+"', " +
                               "sp_rotular_peg_facil  = '"+rotularPegaFacil+"', sp_rotular_peg_normal  = '"+rotularPegaNormal+"', " +
                               "sp_rotular_peg_dificil  = '"+rotularPegaDificil+"', sp_ldom_exterior  = '"+lavadoDomEx+"', " +
                               "sp_ldom_exin  = '"+lavadoDomExIn+"', sp_ldom_integral  = '"+lavadoDomInt+"', sp_ldom_int_ext_cuatro  = '"+lavadoDomIE4+"', " +
                               "sp_ldom_integral_cuatro  = '"+lavadoDomIntegral4+"', sp_ldom_int_ext_industrial  = '"+lavadoDomIEIndustrial+"', " +
                               "sp_ldom_integral_industrial  = '"+lavadoDomIntInd+"', sp_itv  = '"+itv+"', sp_pre_itv  = '"+preItv+"', " +
                               "sp_itv_pre_itv  = '"+itvPreItv+"', sp_ida_vuelta  = '"+idaVuelta+"', sp_entrada_campa  = '"+entradaCampa+"', " +
                               "sp_campa  = '"+campa+"', sp_peritacion  = '"+peritacion+"', sp_mo_mecanica_chapa  = '"+mOMecanicaChapa+"', " +
                               "sp_chequeo  = '"+chequeo+"', sp_repostaje  = '"+repostaje+"', sp_suplemento  = '"+suplemento+"', " +
                               "sp_urgente  = '"+urgente+"', pr_id = "+ pr_id+"  " +
                               "WHERE sp_id = "+ sp_id+"";

                System.out.println(query);
                //datos = new DbConnection();
                boolean rs = datos.manipuladorDatos(query);
                System.out.println(rs);
                if(rs)
                {
                    jButtonModificar.setEnabled(false);
                    JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>Se ha producido un error al guardar en la base de datos</FONT></HTML>");
                    JOptionPane.showMessageDialog(null,errorFields);
                    jButtonModificar.setEnabled(true);
                }
                else
                {
                    jButtonModificar.setEnabled(false);
                    JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>Los datos se han guardado correctamente.</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, mensaje);
                    jButtonModificar.setEnabled(true);
                    CSDesktop.ServicioProveedor.dispose();
                    CSDesktop.EditarProveedor.setVisible(true);
                }
            }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    public Dimension getPreferredSize()
    {
      return new Dimension( 850,600 );
    }
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
            {
               CSDesktop.EditarProveedor.setEnabled(true);
               CSDesktop.ServicioProveedor.dispose();
               CSDesktop.EditarProveedor.setVisible(true);
            }
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jText4x4;
    private javax.swing.JTextField jTextCampa;
    private javax.swing.JTextField jTextCampaEntrada;
    private javax.swing.JTextField jTextChequeo;
    private javax.swing.JTextField jTextDesrotularPegDificil;
    private javax.swing.JTextField jTextDesrotularPegFacil;
    private javax.swing.JTextField jTextDesrotularPegNormal;
    private javax.swing.JTextField jTextFurgones;
    private javax.swing.JTextField jTextFurgonetas;
    private javax.swing.JTextField jTextIdaVuelta;
    private javax.swing.JTextField jTextIndustrial;
    private javax.swing.JTextField jTextItv;
    private javax.swing.JTextField jTextItvPreItv;
    private javax.swing.JTextField jTextLavadoDEx;
    private javax.swing.JTextField jTextLavadoDINEX;
    private javax.swing.JTextField jTextLavadoDIn;
    private javax.swing.JTextField jTextLavadoDIn4;
    private javax.swing.JTextField jTextLavadoDInExInd;
    private javax.swing.JTextField jTextLavadoDIntEx4;
    private javax.swing.JTextField jTextLavadoDIntInd;
    private javax.swing.JTextField jTextLavadoEx;
    private javax.swing.JTextField jTextLavadoINEX;
    private javax.swing.JTextField jTextLavadoInEx4;
    private javax.swing.JTextField jTextLavadoInExInd;
    private javax.swing.JTextField jTextLavadoInt;
    private javax.swing.JTextField jTextLavadoInt4;
    private javax.swing.JTextField jTextLavadoIntInd;
    private javax.swing.JTextField jTextMObraMChapa;
    private javax.swing.JTextField jTextPeritacion;
    private javax.swing.JTextField jTextPreItv;
    private javax.swing.JTextField jTextRepostaje;
    private javax.swing.JTextField jTextRotularPegDificil;
    private javax.swing.JTextField jTextRotularPegFacil;
    private javax.swing.JTextField jTextRotularPegNormal;
    private javax.swing.JTextField jTextSuplemento;
    private javax.swing.JTextField jTextUrgente;
    private javax.swing.JLabel l4x4;
    private javax.swing.JLabel lCampa;
    private javax.swing.JLabel lCampaEntrada;
    private javax.swing.JLabel lChequeo;
    private javax.swing.JLabel lDesrotularPegDificil;
    private javax.swing.JLabel lDesrotularPegFacil;
    private javax.swing.JLabel lDesrotularPegNormal;
    private javax.swing.JLabel lEuro;
    private javax.swing.JLabel lEuro1;
    private javax.swing.JLabel lEuro10;
    private javax.swing.JLabel lEuro11;
    private javax.swing.JLabel lEuro12;
    private javax.swing.JLabel lEuro13;
    private javax.swing.JLabel lEuro14;
    private javax.swing.JLabel lEuro15;
    private javax.swing.JLabel lEuro16;
    private javax.swing.JLabel lEuro17;
    private javax.swing.JLabel lEuro18;
    private javax.swing.JLabel lEuro19;
    private javax.swing.JLabel lEuro2;
    private javax.swing.JLabel lEuro20;
    private javax.swing.JLabel lEuro21;
    private javax.swing.JLabel lEuro22;
    private javax.swing.JLabel lEuro23;
    private javax.swing.JLabel lEuro24;
    private javax.swing.JLabel lEuro25;
    private javax.swing.JLabel lEuro26;
    private javax.swing.JLabel lEuro27;
    private javax.swing.JLabel lEuro28;
    private javax.swing.JLabel lEuro29;
    private javax.swing.JLabel lEuro3;
    private javax.swing.JLabel lEuro30;
    private javax.swing.JLabel lEuro5;
    private javax.swing.JLabel lEuro6;
    private javax.swing.JLabel lEuro7;
    private javax.swing.JLabel lEuro8;
    private javax.swing.JLabel lEuro9;
    private javax.swing.JLabel lFCorrecion;
    private javax.swing.JLabel lFCorrecion1;
    private javax.swing.JLabel lFCorrecion2;
    private javax.swing.JLabel lFCorrecion3;
    private javax.swing.JLabel lFurgones;
    private javax.swing.JLabel lFurgonetas;
    private javax.swing.JLabel lIdaVuelta;
    private javax.swing.JLabel lIndustrial;
    private javax.swing.JLabel lItv;
    private javax.swing.JLabel lItvPreItv;
    private javax.swing.JLabel lLavadoDEx;
    private javax.swing.JLabel lLavadoDIn;
    private javax.swing.JLabel lLavadoDIn4;
    private javax.swing.JLabel lLavadoDInEx;
    private javax.swing.JLabel lLavadoDInExInd;
    private javax.swing.JLabel lLavadoDIntEx4;
    private javax.swing.JLabel lLavadoDIntInd;
    private javax.swing.JLabel lLavadoEx;
    private javax.swing.JLabel lLavadoInEx;
    private javax.swing.JLabel lLavadoInEx4;
    private javax.swing.JLabel lLavadoInExInd;
    private javax.swing.JLabel lLavadoInt;
    private javax.swing.JLabel lLavadoInt4;
    private javax.swing.JLabel lLavadoIntInd;
    private javax.swing.JLabel lMObraMChapa;
    private javax.swing.JLabel lPeritacion;
    private javax.swing.JLabel lPreItv1;
    private javax.swing.JLabel lRepostaje;
    private javax.swing.JLabel lRotularPegDificil;
    private javax.swing.JLabel lRotularPegFacil;
    private javax.swing.JLabel lRotularPegNormal;
    private javax.swing.JLabel lSuplemento;
    private javax.swing.JLabel lUrgente;
    private javax.swing.JLabel ltantoxciento;
    private javax.swing.JLabel ltantoxciento1;
    // End of variables declaration//GEN-END:variables

    public void ValidarFormatos(String accion)
    {
         jButtonModificar.setEnabled(false);
         JLabel errorFields = new JLabel(accion);
         JOptionPane.showMessageDialog(null,errorFields);
         jButtonModificar.setEnabled(true);
    }


     private void limitacionesCampos()
    {
        LimitadorDeDocumento limitador4= new LimitadorDeDocumento(10);
        jText4x4.setDocument(limitador4);
        LimitadorDeDocumento limitadorFurgones= new LimitadorDeDocumento(10);
        jTextFurgonetas.setDocument(limitadorFurgones);
        LimitadorDeDocumento limitadorIdaVuelta = new LimitadorDeDocumento(10);
        jTextIdaVuelta.setDocument(limitadorIdaVuelta);
        LimitadorDeDocumento limitadorIndustrial= new LimitadorDeDocumento(10);
        jTextIndustrial.setDocument(limitadorIndustrial);
        LimitadorDeDocumento limitadorMonovolumen= new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitadorCampa= new LimitadorDeDocumento(10);
        jTextCampa.setDocument(limitadorCampa);

    }

}