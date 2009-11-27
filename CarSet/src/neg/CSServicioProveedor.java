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
                jTextChequeo.setText(rs.getString("sp_chequeo"));
                jTextReacondicionamiento.setText(rs.getString("sp_reacondicionamiento"));
                jTextCampa.setText(rs.getString("sp_campa"));
                jTextCampaEntrada.setText(rs.getString("sp_entrada_campa"));
                jTextLavadoEx.setText(rs.getString("sp_lavado"));
                jTextLavadoINEX.setText(rs.getString("sp_lavado_exin"));
                jTextLavadoXtr.setText(rs.getString("sp_lavado_extra"));
                jTextLavadoCo.setText(rs.getString("sp_completo"));
                jTextLavadoHi.setText(rs.getString("sp_higienizado"));
                jTextLavadoIE4.setText(rs.getString("sp_int_ext_cuatro"));
                jTextLavadoIntegral4x4.setText(rs.getString("sp_integral_cuatro"));
                jTextLavadoIEInd.setText(rs.getString("sp_int_ext_industrial"));
                jTextLavadoIntInd.setText(rs.getString("sp_integral_industrial"));
                jTextLimpiezaPega.setText(rs.getString("sp_limpieza_pegatinas"));
                jTextLimpIntPega.setText(rs.getString("sp_interior_pegatinas"));
                jTextIdaVuelta.setText(rs.getString("sp_ida_vuelta"));
                jTextUrgente.setText(rs.getString("sp_urgente"));
                jTextSuplemento.setText(rs.getString("sp_suplemento"));
                jTextRepostaje.setText(rs.getString("sp_repostaje"));
                jTextMObraMChapa.setText(rs.getString("sp_mo_mecanica_chapa"));
                
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
        lEuro2 = new javax.swing.JLabel();
        lCampaEntrada = new javax.swing.JLabel();
        lEuro = new javax.swing.JLabel();
        lEuro1 = new javax.swing.JLabel();
        lEuro4 = new javax.swing.JLabel();
        jTextCampaEntrada = new javax.swing.JTextField();
        lEuro3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lPreItv1 = new javax.swing.JLabel();
        jTextPreItv = new javax.swing.JTextField();
        lEuro5 = new javax.swing.JLabel();
        jTextLavadoEx = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTextIdaVuelta = new javax.swing.JTextField();
        lFCorrecion2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lCampa = new javax.swing.JLabel();
        lIdaVuelta = new javax.swing.JLabel();
        jTextIndustrial = new javax.swing.JTextField();
        lIndustrial = new javax.swing.JLabel();
        jTextCampa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ltantoxciento = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lFCorrecion = new javax.swing.JLabel();
        lItv = new javax.swing.JLabel();
        jTextItv = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jTextFurgonetas = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        lFurgonetas = new javax.swing.JLabel();
        l4x4 = new javax.swing.JLabel();
        jText4x4 = new javax.swing.JTextField();
        lFCorrecion1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextLimpIntPega = new javax.swing.JTextField();
        jTextLimpiezaPega = new javax.swing.JTextField();
        jTextLavadoIntInd = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        lEuro19 = new javax.swing.JLabel();
        lMObraMChapa = new javax.swing.JLabel();
        jTextMObraMChapa = new javax.swing.JTextField();
        lRepostaje = new javax.swing.JLabel();
        lEuro18 = new javax.swing.JLabel();
        jTextRepostaje = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lLimpiezaPegatinas = new javax.swing.JLabel();
        lEuro16 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lLimpIntPega = new javax.swing.JLabel();
        lEuro15 = new javax.swing.JLabel();
        lLavadoIntInd = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextLavadoIEInd = new javax.swing.JTextField();
        lEuro14 = new javax.swing.JLabel();
        lLavadoIE4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lEuro11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lEuro17 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lLavadoInt4x4 = new javax.swing.JLabel();
        jTextLavadoIE4 = new javax.swing.JTextField();
        lEuro12 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lLavadoIEInd = new javax.swing.JLabel();
        jTextLavadoIntegral4x4 = new javax.swing.JTextField();
        lEuro13 = new javax.swing.JLabel();
        lEuro10 = new javax.swing.JLabel();
        jTextLavadoHi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFurgones = new javax.swing.JTextField();
        lFurgones = new javax.swing.JLabel();
        jTextUrgente = new javax.swing.JTextField();
        lUrgente = new javax.swing.JLabel();
        ltantoxciento1 = new javax.swing.JLabel();
        jTextSuplemento = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lSuplemento = new javax.swing.JLabel();
        lEuro8 = new javax.swing.JLabel();
        jTextLavadoCo = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        lLavadoCo = new javax.swing.JLabel();
        lEuro7 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lLavadoHi = new javax.swing.JLabel();
        jTextLavadoXtr = new javax.swing.JTextField();
        lEuro9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lLavadoXtr = new javax.swing.JLabel();
        lEuro6 = new javax.swing.JLabel();
        jTextChequeo = new javax.swing.JTextField();
        jTextReacondicionamiento = new javax.swing.JTextField();
        lLavado1 = new javax.swing.JLabel();
        lChequeo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lLavadoEx = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lLavadoEx1 = new javax.swing.JLabel();
        jTextLavadoINEX = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

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

        lEuro2.setForeground(new java.awt.Color(0, 0, 100));
        lEuro2.setText("€");
        lEuro2.setName("lEuro2"); // NOI18N

        lCampaEntrada.setForeground(new java.awt.Color(0, 0, 100));
        lCampaEntrada.setText("Coste Entrada Campa");
        lCampaEntrada.setName("lCampaEntrada"); // NOI18N

        lEuro.setForeground(new java.awt.Color(0, 0, 100));
        lEuro.setText("€");
        lEuro.setName("lEuro"); // NOI18N

        lEuro1.setForeground(new java.awt.Color(0, 0, 100));
        lEuro1.setText("€");
        lEuro1.setName("lEuro1"); // NOI18N

        lEuro4.setForeground(new java.awt.Color(0, 0, 100));
        lEuro4.setText("€");
        lEuro4.setName("lEuro4"); // NOI18N

        jTextCampaEntrada.setName("jTextCampaEntrada"); // NOI18N

        lEuro3.setForeground(new java.awt.Color(0, 0, 100));
        lEuro3.setText("€");
        lEuro3.setName("lEuro3"); // NOI18N

        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("*");
        jLabel21.setName("jLabel21"); // NOI18N

        lPreItv1.setForeground(new java.awt.Color(0, 0, 100));
        lPreItv1.setText("Pre-ITV");
        lPreItv1.setName("lPreItv1"); // NOI18N

        jTextPreItv.setName("jTextPreItv"); // NOI18N

        lEuro5.setForeground(new java.awt.Color(0, 0, 100));
        lEuro5.setText("€");
        lEuro5.setName("lEuro5"); // NOI18N

        jTextLavadoEx.setName("jTextLavadoEx"); // NOI18N

        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");
        jLabel18.setName("jLabel18"); // NOI18N

        jSeparator5.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator5.setName("jSeparator5"); // NOI18N

        jTextIdaVuelta.setName("jTextIdaVuelta"); // NOI18N

        lFCorrecion2.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion2.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion2.setText("OTROS");
        lFCorrecion2.setName("lFCorrecion2"); // NOI18N

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");
        jLabel17.setName("jLabel17"); // NOI18N

        lCampa.setForeground(new java.awt.Color(0, 0, 100));
        lCampa.setText("Campa");
        lCampa.setName("lCampa"); // NOI18N

        lIdaVuelta.setForeground(new java.awt.Color(0, 0, 100));
        lIdaVuelta.setText("Ida-Vuelta");
        lIdaVuelta.setName("lIdaVuelta"); // NOI18N

        jTextIndustrial.setName("jTextIndustrial"); // NOI18N

        lIndustrial.setForeground(new java.awt.Color(0, 0, 100));
        lIndustrial.setText("Industrial y Monovolumen");
        lIndustrial.setName("lIndustrial"); // NOI18N

        jTextCampa.setName("jTextCampa"); // NOI18N

        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("*");
        jLabel8.setName("jLabel8"); // NOI18N

        ltantoxciento.setForeground(new java.awt.Color(0, 0, 100));
        ltantoxciento.setText("%");
        ltantoxciento.setName("ltantoxciento"); // NOI18N

        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");
        jLabel19.setName("jLabel19"); // NOI18N

        lFCorrecion.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion.setText("FACTOR DE CORRECIÓN");
        lFCorrecion.setName("lFCorrecion"); // NOI18N

        lItv.setForeground(new java.awt.Color(0, 0, 100));
        lItv.setText("ITV");
        lItv.setName("lItv"); // NOI18N

        jTextItv.setName("jTextItv"); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator4.setName("jSeparator4"); // NOI18N

        jTextFurgonetas.setName("jTextFurgonetas"); // NOI18N

        jSeparator3.setForeground(new java.awt.Color(170, 16, 4));
        jSeparator3.setName("jSeparator3"); // NOI18N

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");
        jLabel12.setName("jLabel12"); // NOI18N

        lFurgonetas.setForeground(new java.awt.Color(0, 0, 100));
        lFurgonetas.setText("Furgonetas");
        lFurgonetas.setName("lFurgonetas"); // NOI18N

        l4x4.setForeground(new java.awt.Color(0, 0, 100));
        l4x4.setText("Todoterreno");
        l4x4.setName("l4x4"); // NOI18N

        jText4x4.setName("jText4x4"); // NOI18N

        lFCorrecion1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lFCorrecion1.setForeground(new java.awt.Color(170, 16, 4));
        lFCorrecion1.setText("SERVICIOS ESPECIALES");
        lFCorrecion1.setName("lFCorrecion1"); // NOI18N

        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("*");
        jLabel20.setName("jLabel20"); // NOI18N

        jTextLimpIntPega.setName("jTextLimpIntPega"); // NOI18N

        jTextLimpiezaPega.setName("jTextLimpiezaPega"); // NOI18N

        jTextLavadoIntInd.setName("jTextLavadoIntInd"); // NOI18N

        jLabel37.setForeground(new java.awt.Color(204, 0, 0));
        jLabel37.setText("*");
        jLabel37.setName("jLabel37"); // NOI18N

        lEuro19.setForeground(new java.awt.Color(0, 0, 100));
        lEuro19.setText("€");
        lEuro19.setName("lEuro19"); // NOI18N

        lMObraMChapa.setForeground(new java.awt.Color(0, 0, 100));
        lMObraMChapa.setText("Mano Obra Mec. y Chapa");
        lMObraMChapa.setName("lMObraMChapa"); // NOI18N

        jTextMObraMChapa.setName("jTextMObraMChapa"); // NOI18N

        lRepostaje.setForeground(new java.awt.Color(0, 0, 100));
        lRepostaje.setText("Repostaje");
        lRepostaje.setName("lRepostaje"); // NOI18N

        lEuro18.setForeground(new java.awt.Color(0, 0, 100));
        lEuro18.setText("€");
        lEuro18.setName("lEuro18"); // NOI18N

        jTextRepostaje.setName("jTextRepostaje"); // NOI18N

        jLabel36.setForeground(new java.awt.Color(204, 0, 0));
        jLabel36.setText("*");
        jLabel36.setName("jLabel36"); // NOI18N

        jLabel35.setForeground(new java.awt.Color(204, 0, 0));
        jLabel35.setText("*");
        jLabel35.setName("jLabel35"); // NOI18N

        lLimpiezaPegatinas.setForeground(new java.awt.Color(0, 0, 100));
        lLimpiezaPegatinas.setText("Limpieza + Pegatinas");
        lLimpiezaPegatinas.setName("lLimpiezaPegatinas"); // NOI18N

        lEuro16.setForeground(new java.awt.Color(0, 0, 100));
        lEuro16.setText("€");
        lEuro16.setName("lEuro16"); // NOI18N

        jLabel34.setForeground(new java.awt.Color(204, 0, 0));
        jLabel34.setText("*");
        jLabel34.setName("jLabel34"); // NOI18N

        lLimpIntPega.setForeground(new java.awt.Color(0, 0, 100));
        lLimpIntPega.setText("Limpieza Integral + Pegatinas");
        lLimpIntPega.setName("lLimpIntPega"); // NOI18N

        lEuro15.setForeground(new java.awt.Color(0, 0, 100));
        lEuro15.setText("€");
        lEuro15.setName("lEuro15"); // NOI18N

        lLavadoIntInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoIntInd.setText("Lavado Integral Industrial");
        lLavadoIntInd.setName("lLavadoIntInd"); // NOI18N

        jLabel33.setForeground(new java.awt.Color(204, 0, 0));
        jLabel33.setText("*");
        jLabel33.setName("jLabel33"); // NOI18N

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");
        jLabel9.setName("jLabel9"); // NOI18N

        jTextLavadoIEInd.setName("jTextLavadoIEInd"); // NOI18N

        lEuro14.setForeground(new java.awt.Color(0, 0, 100));
        lEuro14.setText("€");
        lEuro14.setName("lEuro14"); // NOI18N

        lLavadoIE4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoIE4.setText("Lavado Int. y Ext (4x4)");
        lLavadoIE4.setName("lLavadoIE4"); // NOI18N

        jLabel30.setForeground(new java.awt.Color(204, 0, 0));
        jLabel30.setText("*");
        jLabel30.setName("jLabel30"); // NOI18N

        lEuro11.setForeground(new java.awt.Color(0, 0, 100));
        lEuro11.setText("€");
        lEuro11.setName("lEuro11"); // NOI18N

        jLabel28.setForeground(new java.awt.Color(204, 0, 0));
        jLabel28.setText("*");
        jLabel28.setName("jLabel28"); // NOI18N

        lEuro17.setForeground(new java.awt.Color(0, 0, 100));
        lEuro17.setText("€");
        lEuro17.setName("lEuro17"); // NOI18N

        jLabel31.setForeground(new java.awt.Color(204, 0, 0));
        jLabel31.setText("*");
        jLabel31.setName("jLabel31"); // NOI18N

        lLavadoInt4x4.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoInt4x4.setText("Lavado Integral (4x4)");
        lLavadoInt4x4.setName("lLavadoInt4x4"); // NOI18N

        jTextLavadoIE4.setName("jTextLavadoIE4"); // NOI18N

        lEuro12.setForeground(new java.awt.Color(0, 0, 100));
        lEuro12.setText("€");
        lEuro12.setName("lEuro12"); // NOI18N

        jLabel32.setForeground(new java.awt.Color(204, 0, 0));
        jLabel32.setText("*");
        jLabel32.setName("jLabel32"); // NOI18N

        lLavadoIEInd.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoIEInd.setText("Lavado Int. y Ext. Industrial");
        lLavadoIEInd.setName("lLavadoIEInd"); // NOI18N

        jTextLavadoIntegral4x4.setName("jTextLavadoIntegral4x4"); // NOI18N

        lEuro13.setForeground(new java.awt.Color(0, 0, 100));
        lEuro13.setText("€");
        lEuro13.setName("lEuro13"); // NOI18N

        lEuro10.setForeground(new java.awt.Color(0, 0, 100));
        lEuro10.setText("€");
        lEuro10.setName("lEuro10"); // NOI18N

        jTextLavadoHi.setName("jTextLavadoHi"); // NOI18N

        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");
        jLabel13.setName("jLabel13"); // NOI18N

        jTextFurgones.setName("jTextFurgones"); // NOI18N

        lFurgones.setForeground(new java.awt.Color(0, 0, 100));
        lFurgones.setText("Furgones");
        lFurgones.setName("lFurgones"); // NOI18N

        jTextUrgente.setName("jTextUrgente"); // NOI18N

        lUrgente.setForeground(new java.awt.Color(0, 0, 100));
        lUrgente.setText("Urgente");
        lUrgente.setName("lUrgente"); // NOI18N

        ltantoxciento1.setForeground(new java.awt.Color(0, 0, 100));
        ltantoxciento1.setText("%");
        ltantoxciento1.setName("ltantoxciento1"); // NOI18N

        jTextSuplemento.setName("jTextSuplemento"); // NOI18N

        jLabel29.setForeground(new java.awt.Color(204, 0, 0));
        jLabel29.setText("*");
        jLabel29.setName("jLabel29"); // NOI18N

        lSuplemento.setForeground(new java.awt.Color(0, 0, 100));
        lSuplemento.setText("Suplemento");
        lSuplemento.setName("lSuplemento"); // NOI18N

        lEuro8.setForeground(new java.awt.Color(0, 0, 100));
        lEuro8.setText("€");
        lEuro8.setName("lEuro8"); // NOI18N

        jTextLavadoCo.setName("jTextLavadoCo"); // NOI18N

        jLabel25.setForeground(new java.awt.Color(204, 0, 0));
        jLabel25.setText("*");
        jLabel25.setName("jLabel25"); // NOI18N

        lLavadoCo.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoCo.setText("Lavado Integral");
        lLavadoCo.setName("lLavadoCo"); // NOI18N

        lEuro7.setForeground(new java.awt.Color(0, 0, 100));
        lEuro7.setText("€");
        lEuro7.setName("lEuro7"); // NOI18N

        jLabel27.setForeground(new java.awt.Color(204, 0, 0));
        jLabel27.setText("*");
        jLabel27.setName("jLabel27"); // NOI18N

        lLavadoHi.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoHi.setText("Lavado Higienizado");
        lLavadoHi.setName("lLavadoHi"); // NOI18N

        jTextLavadoXtr.setName("jTextLavadoXtr"); // NOI18N

        lEuro9.setForeground(new java.awt.Color(0, 0, 100));
        lEuro9.setText("€");
        lEuro9.setName("lEuro9"); // NOI18N

        jLabel26.setForeground(new java.awt.Color(204, 0, 0));
        jLabel26.setText("*");
        jLabel26.setName("jLabel26"); // NOI18N

        lLavadoXtr.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoXtr.setText("Lavado Extra");
        lLavadoXtr.setName("lLavadoXtr"); // NOI18N

        lEuro6.setForeground(new java.awt.Color(0, 0, 100));
        lEuro6.setText("€");
        lEuro6.setName("lEuro6"); // NOI18N

        jTextChequeo.setName("jTextChequeo"); // NOI18N

        jTextReacondicionamiento.setName("jTextReacondicionamiento"); // NOI18N

        lLavado1.setForeground(new java.awt.Color(0, 0, 100));
        lLavado1.setText("Reacondicionamiento");
        lLavado1.setName("lLavado1"); // NOI18N

        lChequeo.setForeground(new java.awt.Color(0, 0, 100));
        lChequeo.setText("Chequeo");
        lChequeo.setName("lChequeo"); // NOI18N

        jLabel22.setForeground(new java.awt.Color(204, 0, 0));
        jLabel22.setText("*");
        jLabel22.setName("jLabel22"); // NOI18N

        lLavadoEx.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoEx.setText("Lavado Exterior");
        lLavadoEx.setName("lLavadoEx"); // NOI18N

        jLabel24.setForeground(new java.awt.Color(204, 0, 0));
        jLabel24.setText("*");
        jLabel24.setName("jLabel24"); // NOI18N

        lLavadoEx1.setForeground(new java.awt.Color(0, 0, 100));
        lLavadoEx1.setText("Lavado Interior y Exterior");
        lLavadoEx1.setName("lLavadoEx1"); // NOI18N

        jTextLavadoINEX.setName("jTextLavadoINEX"); // NOI18N

        jLabel23.setForeground(new java.awt.Color(204, 0, 0));
        jLabel23.setText("*");
        jLabel23.setName("jLabel23"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(303, 303, 303))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(lFCorrecion)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jText4x4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextIndustrial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFurgonetas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFurgones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFCorrecion1, javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoXtr))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoHi))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel24)
                                                    .addComponent(jLabel25))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lLavadoCo)
                                                    .addComponent(lLavadoEx1)
                                                    .addComponent(lLavadoEx)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoIE4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoInt4x4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lLavadoIEInd))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel33)
                                                .addGap(4, 4, 4)
                                                .addComponent(lLavadoIntInd))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel35)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(3, 3, 3)
                                                        .addComponent(jLabel34)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lLimpIntPega, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lLimpiezaPegatinas))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextLimpIntPega)
                                            .addComponent(jTextLavadoEx)
                                            .addComponent(jTextLavadoINEX)
                                            .addComponent(jTextLavadoCo)
                                            .addComponent(jTextLavadoXtr)
                                            .addComponent(jTextLavadoHi)
                                            .addComponent(jTextLavadoIE4)
                                            .addComponent(jTextLavadoIntegral4x4)
                                            .addComponent(jTextLavadoIEInd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                            .addComponent(jTextLavadoIntInd, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextLimpiezaPega, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lEuro17)
                                            .addComponent(lEuro16)
                                            .addComponent(lEuro15)
                                            .addComponent(lEuro10)
                                            .addComponent(lEuro9)
                                            .addComponent(lEuro8)
                                            .addComponent(lEuro7)
                                            .addComponent(lEuro2)
                                            .addComponent(lEuro12)
                                            .addComponent(lEuro13)
                                            .addComponent(lEuro14))))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel18))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(lItv))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lIdaVuelta)
                                                    .addComponent(lCampa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lLavado1)
                                                    .addComponent(lChequeo)
                                                    .addComponent(lPreItv1)
                                                    .addComponent(lCampaEntrada)
                                                    .addComponent(lUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lRepostaje, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lMObraMChapa))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextRepostaje)
                                            .addComponent(jTextMObraMChapa)
                                            .addComponent(jTextItv, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextCampa, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextCampaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                            .addComponent(jTextUrgente, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextSuplemento, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextIdaVuelta, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextReacondicionamiento, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextChequeo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextPreItv, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lEuro19)
                                                    .addComponent(lEuro18)
                                                    .addComponent(lEuro11))
                                                .addComponent(lEuro3)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lEuro4)
                                                    .addComponent(lEuro)
                                                    .addComponent(lEuro6)
                                                    .addComponent(lEuro5)
                                                    .addComponent(lEuro1)))
                                            .addComponent(ltantoxciento1)
                                            .addComponent(ltantoxciento))
                                        .addGap(8, 8, 8))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(lFCorrecion2)
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFCorrecion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextIndustrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFCorrecion2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro1)
                            .addComponent(jTextItv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lItv)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextPreItv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro5)
                            .addComponent(lPreItv1)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextChequeo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro6)
                            .addComponent(lChequeo)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextReacondicionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro4)
                            .addComponent(lLavado1)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextIdaVuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltantoxciento)
                            .addComponent(lIdaVuelta)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCampa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro)
                            .addComponent(lCampa)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextCampaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro3)
                            .addComponent(jLabel28)
                            .addComponent(lCampaEntrada))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltantoxciento1)
                            .addComponent(jLabel20)
                            .addComponent(lUrgente))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro11)
                            .addComponent(jLabel29)
                            .addComponent(lSuplemento))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro18)
                            .addComponent(jLabel36)
                            .addComponent(lRepostaje)
                            .addComponent(jTextRepostaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEuro19)
                            .addComponent(jLabel37)
                            .addComponent(lMObraMChapa)
                            .addComponent(jTextMObraMChapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFCorrecion1)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextLavadoEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(lLavadoEx)
                            .addComponent(lEuro2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jTextLavadoINEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLavadoEx1)
                            .addComponent(lEuro7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextLavadoCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLavadoCo)
                            .addComponent(lEuro8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lLavadoXtr)
                            .addComponent(jLabel26)
                            .addComponent(jTextLavadoXtr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lLavadoHi)
                            .addComponent(jLabel27)
                            .addComponent(jTextLavadoHi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(lLavadoIE4)
                            .addComponent(lEuro12)
                            .addComponent(jTextLavadoIE4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lLavadoInt4x4)
                            .addComponent(lEuro13)
                            .addComponent(jTextLavadoIntegral4x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(lLavadoIEInd)
                            .addComponent(lEuro14)
                            .addComponent(jTextLavadoIEInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(lLimpiezaPegatinas)
                            .addComponent(lEuro16)
                            .addComponent(jTextLimpiezaPega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lLimpIntPega)
                            .addComponent(jTextLimpIntPega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro17)
                            .addComponent(jLabel34)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextLavadoIntInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEuro15)
                            .addComponent(lLavadoIntInd)
                            .addComponent(jLabel33))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModificar)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
 
            System.out.println("\nBoton Modificar de Servicios Proveedor.");
            
            String campa = new String(jTextCampa.getText());
            String entradaCampa = new String(jTextCampaEntrada.getText());
            String todoterreno = new String(jText4x4.getText());
            String furgonetas = new String(jTextFurgonetas.getText());
            String furgones = new String(jTextFurgones.getText());
            String industrial = new String(jTextIndustrial.getText());
            String idaVuelta = new String(jTextIdaVuelta.getText());
            String chequeo = new String(jTextChequeo.getText());
            String reacondicionamiento = new String(jTextReacondicionamiento.getText());
            String lavado = new String(jTextLavadoEx.getText());
            String lavadoExIn = new String(jTextLavadoINEX.getText());
            String lavadoCo = new String(jTextLavadoCo.getText());
            String lavadoEx = new String(jTextLavadoXtr.getText());
            String lavadoHi = new String(jTextLavadoHi.getText());
            String lavadoIE4 = new String(jTextLavadoIE4.getText());
            String lavadoIntegral4 = new String(jTextLavadoIntegral4x4.getText());
            String lavadoIEIndustrial = new String(jTextLavadoIEInd.getText());
            String lavadoIntInd = new String(jTextLavadoIntInd.getText());
            String lavadoLimpiezaPega = new String(jTextLimpiezaPega.getText());
            String lavadoLimpIntPega = new String(jTextLimpIntPega.getText());
            String itv = new String(jTextItv.getText());
            String preItv = new String(jTextPreItv.getText());
            String urgente = new String(jTextUrgente.getText());
            String suplemento = new String(jTextSuplemento.getText());
            String repostaje = new String(jTextRepostaje.getText());
            String mOMecanicaChapa = new String(jTextMObraMChapa.getText());
            
            
             //CAMPOS OBLIGATORIOS
            if (!Utilidades.campoObligatorio(campa,"Campa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(campa,"Campa"));
            }
            else if (!Utilidades.campoObligatorio(todoterreno,"todoterreno").equals("OK"))
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
            else if (!Utilidades.campoObligatorio(lavado,"Lavado").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavado,"Lavado"));
            }
            else if (!Utilidades.campoObligatorio(lavadoExIn,"Lavado Exterior e Interior").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoExIn,"Lavado Exterior e Interior"));
            }
            else if (!Utilidades.campoObligatorio(lavadoCo,"Lavado Integral").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoCo,"Lavado Integral"));
            }
            else if (!Utilidades.campoObligatorio(lavadoEx,"Lavado Extra").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoEx,"Lavado Extra"));
            }
            else if (!Utilidades.campoObligatorio(lavadoHi,"Lavado Higienizado").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoHi,"Lavado Higienizado"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIE4,"Lavado Interior y Exterior 4x4").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIE4,"Lavado Interior y Exterior 4x4"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIntegral4,"Lavado Integral 4x4").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIntegral4,"Lavado Integral 4x4"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIEIndustrial,"Lavado Interior y Exterior Industrial").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIEIndustrial,"Lavado Interior y Exterior Industrial"));
            }
            else if (!Utilidades.campoObligatorio(lavadoIntInd,"Lavado Integral Industrial").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoIntInd,"Lavado Integral Industrial"));
            }
            else if (!Utilidades.campoObligatorio(lavadoLimpiezaPega,"Limpieza + Pegatinas").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoLimpiezaPega,"Limpieza + Pegatinas"));
            }
            else if (!Utilidades.campoObligatorio(lavadoLimpIntPega,"Limpieza Integral + Pegatinas").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(lavadoLimpIntPega,"Limpieza Integral + Pegatinas"));
            }
            else if (!Utilidades.campoObligatorio(preItv,"Pre-ITV").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(preItv,"Pre-itv"));
            }
            else if (!Utilidades.campoObligatorio(urgente,"Urgente").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(urgente,"Urgente"));
            }
            else if (!Utilidades.campoObligatorio(suplemento,"Suplemento").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(suplemento,"Suplemento"));
            }
            else if (!Utilidades.campoObligatorio(repostaje,"Repostaje").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(repostaje,"Repostaje"));
            }
            else if (!Utilidades.campoObligatorio(mOMecanicaChapa,"Mano de Obra Mecánica y Chapa").equals("OK"))
            {
                 ValidarFormatos(Utilidades.campoObligatorio(mOMecanicaChapa,"Mano de Obra Mecánica y Chapa"));
            }
            else
            {
                String query = "UPDATE sp_servicios_proveedores SET sp_industrial = '" + industrial + "', sp_todoterreno = '" + todoterreno + "', " +
                               "sp_furgonetas = '" + furgonetas + "', sp_furgones = '" + furgones + "', sp_itv = '" + itv + "', sp_pre_itv = '" + preItv + "', " +
                               "sp_chequeo = '" + chequeo + "',sp_reacondicionamiento = '"+ reacondicionamiento + "', sp_campa= '" + campa + "', " +
                               "sp_entrada_campa ='" + entradaCampa+"',sp_lavado = '"+ lavado + "', sp_lavado_exin = '"+ lavadoExIn + "', " +
                               "sp_lavado_extra ='" + lavadoEx+"', sp_completo = '"+ lavadoCo + "', sp_higienizado = '"+ lavadoHi + "', " +
                               "sp_int_ext_cuatro = '" + lavadoIE4+"', sp_integral_cuatro = '" + lavadoIntegral4+"', sp_int_ext_industrial = '" + lavadoIEIndustrial+"', " +
                               "sp_integral_industrial = '" + lavadoIntInd+"', sp_limpieza_pegatinas= '" + lavadoLimpiezaPega+"', " +
                               "sp_interior_pegatinas = '" + lavadoLimpIntPega+"', sp_ida_vuelta = '"+ idaVuelta + "', " +
                               "sp_urgente = '"+ urgente + "', sp_suplemento = '"+ suplemento + "', sp_repostaje = '"+ repostaje + "', " +
                               "sp_mo_mecanica_chapa = '"+ mOMecanicaChapa + "', pr_id = "+ pr_id+"  " +
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
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jText4x4;
    private javax.swing.JTextField jTextCampa;
    private javax.swing.JTextField jTextCampaEntrada;
    private javax.swing.JTextField jTextChequeo;
    private javax.swing.JTextField jTextFurgones;
    private javax.swing.JTextField jTextFurgonetas;
    private javax.swing.JTextField jTextIdaVuelta;
    private javax.swing.JTextField jTextIndustrial;
    private javax.swing.JTextField jTextItv;
    private javax.swing.JTextField jTextLavadoCo;
    private javax.swing.JTextField jTextLavadoEx;
    private javax.swing.JTextField jTextLavadoHi;
    private javax.swing.JTextField jTextLavadoIE4;
    private javax.swing.JTextField jTextLavadoIEInd;
    private javax.swing.JTextField jTextLavadoINEX;
    private javax.swing.JTextField jTextLavadoIntInd;
    private javax.swing.JTextField jTextLavadoIntegral4x4;
    private javax.swing.JTextField jTextLavadoXtr;
    private javax.swing.JTextField jTextLimpIntPega;
    private javax.swing.JTextField jTextLimpiezaPega;
    private javax.swing.JTextField jTextMObraMChapa;
    private javax.swing.JTextField jTextPreItv;
    private javax.swing.JTextField jTextReacondicionamiento;
    private javax.swing.JTextField jTextRepostaje;
    private javax.swing.JTextField jTextSuplemento;
    private javax.swing.JTextField jTextUrgente;
    private javax.swing.JLabel l4x4;
    private javax.swing.JLabel lCampa;
    private javax.swing.JLabel lCampaEntrada;
    private javax.swing.JLabel lChequeo;
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
    private javax.swing.JLabel lEuro3;
    private javax.swing.JLabel lEuro4;
    private javax.swing.JLabel lEuro5;
    private javax.swing.JLabel lEuro6;
    private javax.swing.JLabel lEuro7;
    private javax.swing.JLabel lEuro8;
    private javax.swing.JLabel lEuro9;
    private javax.swing.JLabel lFCorrecion;
    private javax.swing.JLabel lFCorrecion1;
    private javax.swing.JLabel lFCorrecion2;
    private javax.swing.JLabel lFurgones;
    private javax.swing.JLabel lFurgonetas;
    private javax.swing.JLabel lIdaVuelta;
    private javax.swing.JLabel lIndustrial;
    private javax.swing.JLabel lItv;
    private javax.swing.JLabel lLavado1;
    private javax.swing.JLabel lLavadoCo;
    private javax.swing.JLabel lLavadoEx;
    private javax.swing.JLabel lLavadoEx1;
    private javax.swing.JLabel lLavadoHi;
    private javax.swing.JLabel lLavadoIE4;
    private javax.swing.JLabel lLavadoIEInd;
    private javax.swing.JLabel lLavadoInt4x4;
    private javax.swing.JLabel lLavadoIntInd;
    private javax.swing.JLabel lLavadoXtr;
    private javax.swing.JLabel lLimpIntPega;
    private javax.swing.JLabel lLimpiezaPegatinas;
    private javax.swing.JLabel lMObraMChapa;
    private javax.swing.JLabel lPreItv1;
    private javax.swing.JLabel lRepostaje;
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