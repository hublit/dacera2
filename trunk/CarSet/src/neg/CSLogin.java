/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neg;

import data.DbConnection;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import java.awt.event.KeyEvent;

public class CSLogin extends JFrame
{
    // Variables declaration
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1;
    private JPanel contentPane;
  
    public CSLogin() throws IOException
    {
        super();
        create();
        this.setVisible(true);
    }

    private void create() throws IOException
    {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        jButton1 = new JButton();
        contentPane = (JPanel)this.getContentPane();
   
        KeyListener l=new KeyListener()
        {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e)
            {
               if (e.getKeyCode() == KeyEvent.VK_ENTER)
               {
                    try
                    {
                        try {
                            jButton1_actionPerformed(null);
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(CSLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(CSLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            public void keyReleased(KeyEvent e) { }
        };
              
        //Para que funciona la tecla ENTER
        jTextField1.addKeyListener(l);
        jPasswordField1.addKeyListener(l);
      
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 100));
        jLabel1.setText("Usuario:");
        
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 100));
        jLabel2.setText("Contraseña:");
       
        jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel3.setForeground(new Color(0, 0, 100));
        jLabel3.setText("Por favor, introduzca un nombre de usuario y contraseña");
       
        jTextField1.setForeground(new Color(0, 0, 255));
        jTextField1.setSelectedTextColor(new Color(0, 0, 255));
        jTextField1.setToolTipText("Introduzca su usuario");
        jTextField1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            jTextField1_actionPerformed(e);
            }
        });
        
        jPasswordField1.setForeground(new Color(0, 0, 255));
        jPasswordField1.setToolTipText("Introduzca su contraseña");
        jPasswordField1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            jPasswordField1_actionPerformed(e);
        }
        });
        
        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setForeground(new Color(0, 0, 100));
        jButton1.setText("Acceder");

        jButton1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                    try {
                        jButton1_actionPerformed(e);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(CSLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CSLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });

    Icon imagen = new ImageIcon(getClass().getResource("/images/logo_carset_200.jpg"));
    jLabel5.setIcon(imagen);
    contentPane.setLayout(null);
    contentPane.setBorder(BorderFactory.createEtchedBorder());
    contentPane.setBackground(Color.white);
    
    addComponent(contentPane, jLabel5, 250,20,300,215);
    addComponent(contentPane, jLabel1, 175,260,106,18);
    addComponent(contentPane, jLabel2, 175,307,97,18);
    addComponent(contentPane, jTextField1, 280,260,183,22);
    addComponent(contentPane, jPasswordField1, 280,305,183,22);
    addComponent(contentPane, jButton1, 320,345,83,28);
    addComponent(contentPane, jLabel3, 180,395,500,25);
    addComponent(contentPane, jLabel4, 320,395,100,25);
    addComponent(contentPane, jLabel6, 390,395,100,25);
    
    this.setTitle("CarSet - Acceso para usuarios registrados");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setIconImage(new ImageIcon(getClass().getResource("/images/entire.png")).getImage());
    this.setSize(new Dimension(700, 550));
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension ventana = this.getSize();
    this.setLocation(
        (pantalla.width - ventana.width) / 2,
        (pantalla.height - ventana.height) / 2);
    this.setVisible(true);
 
    }
    /** Add Component Without a Layout Manager (Absolute Positioning) */
    private void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }
    private void jTextField1_actionPerformed(ActionEvent e)
    {
    }
    private void jPasswordField1_actionPerformed(ActionEvent e)
    {
    }
    public void jButton1_actionPerformed(ActionEvent e) throws SQLException, UnknownHostException
    {
        System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
        String username = new String(jTextField1.getText());
        String password = new String(jPasswordField1.getText());
        if(username.equals("") || password.equals("")) // If password and username is empty > Do this >>>
        {           
            jLabel3.setText("Por favor, introduzca un nombre de usuario y contraseña");
            jLabel3.setForeground(new Color(255, 0, 0));
            jButton1.setEnabled(true);
            this.setVisible(true);
        }
        else
        {
            jLabel3.setVisible(false);
            jLabel4.setText("Cargando...");
            jLabel4.setForeground(new Color(0, 0, 255));
            Icon imagen = new ImageIcon(getClass().getResource("/images/loader.gif"));
            jLabel6.setIcon(imagen);
                                                
            DbConnection datos = new DbConnection();
            ResultSet rs = datos.select("SELECT usr_id FROM usr_usuarios u WHERE usr_nombre = '"+username+"' AND usr_password = '"+password+"'");

            String usuario = "";
            while(rs.next())
            {
                    usuario = rs.getString("usr_id");
                    System.out.println(usuario);
            }
            if (usuario.equals(""))
            {              
                jButton1.setEnabled(false);
                jTextField1.setText("");
                jPasswordField1.setText("");
                jLabel4.setVisible(false);
                jLabel6.setVisible(false);
                jLabel3.setText("USUARIO INCORRECTO. Introduzca el nombre de usuario y password de nuevo");
                jLabel3.setForeground(new Color(255, 0, 0));
                jLabel3.setVisible(true);
                jButton1.setEnabled(true);
                this.setVisible(true);
            }
            else
            {
                try
                {                  
                    this.setVisible(false);
                    CSDesktop ventana = new CSDesktop(usuario);
                   ventana.addWindowListener( new WindowAdapter() {
                      public void windowClosing( WindowEvent evt ) {
                        confirmarCierre();
                      }
                    } );
                    ventana.getRootPane().setDefaultButton(jButton1);

                    ventana.setSize(1200,750);
                    ventana.setTitle("CarSet");
                    ventana.setLocationRelativeTo(null);
                    ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    ventana.setVisible( true );                   
                }
                catch(Exception er)
                {
                   System.err.println("Errorrrrr :" +er.toString());
                    er.printStackTrace();
                }
            }
        datos.cerrarConexion();
        }
    }

    public static void main(String[] args) throws IOException
    {       
        try {	
            com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
            //com.birosoft.liquid.LiquidLookAndFeel.setShowTableGrids(true);
            //UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            //com.birosoft.liquid.LiquidLookAndFeel.setShowTableGrids(true);

             } catch (Exception e) {}
        new CSLogin();

    };
     void confirmarCierre()
  {
       int confirmado = JOptionPane.showConfirmDialog(this,"¿Realmente quieres salir de la aplicación?");

                    if (JOptionPane.OK_OPTION == confirmado)
                    {
                        CSDesktop.datos.cerrarConexion();
                        System.exit(0);
                    }
  }
}