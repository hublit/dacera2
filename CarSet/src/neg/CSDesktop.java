package neg; 

/**
 * Este ejempolo presenta un completo sistema de men�s, partiendo de los
 * items que est�n situados en la barra superior y depleg�ndose las
 * opciones que se pueden seleccionar. Tambi�n se muestra c�mo se
 * colocan iconos sobre etiquetas y la inserci�n de items a la
 * barra de men�
 * Se utilizan botones del tipo creados en el ejemplo java1410, los 
 * cuales indican con im�genes su estado
 */
import java.text.ParseException;
import utils.DesktopConFondo;
import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import neg.CSDesktop;
import utils.*;

//import pruebas.FrameInterno;

public class CSDesktop extends JFrame
{
  public static JDesktopPane elEscritorio;
  public static JInternalFrame NuevoCliente;
  public static JInternalFrame BuscarCliente;
  public static JInternalFrame ResultCliente;
  public static JInternalFrame ResultContactosCliente;
  public static JInternalFrame NuevoContactoCliente;
  public static JInternalFrame EditarContactoCliente;
  public static JInternalFrame NuevoPedido;
  public static JInternalFrame EditarPedido;
  public static JInternalFrame BuscarPedido;
  public static JInternalFrame NuevoProveedor;
  public static JInternalFrame BuscarProveedor;
  public static JInternalFrame TarifaCliente;
  public static JInternalFrame EditarTarifaCliente;
  public static JInternalFrame ABResultTarifasCliente;
  public static JInternalFrame TarifaProveedor;
  public static JInternalFrame EditarTarifaProveedor;
  public static JInternalFrame ABResultTarifasProveedor;
  public static JInternalFrame EditarCliente;
  public static JInternalFrame ResultPedido;
  public static JInternalFrame ResultProveedor;
  public static JInternalFrame ResultContactoProveedor;
  public static JInternalFrame EditarProveedor;
  public static JInternalFrame NuevoContactoProveedor;
  public static JInternalFrame EditarContactoProveedor;
  public static JInternalFrame BuscaCliente;
  public static JInternalFrame BuscaProveedor;
  public static JInternalFrame ServicioCliente;
  public static JInternalFrame ServicioProveedor;
  public static JMenu menuClientes;
  public static JMenu menuProveedores;
  public static JMenu menuPedidos;
  public static JMenu menuTarifa;
  public static JMenu menuAyuda;
  public static JMenuBar barra;
  public static JDialog prueba;
  public CSDesktop()
  {
      elEscritorio = new DesktopConFondo("/images/fondo_desktop.jpg");
      getContentPane().add( elEscritorio );
      barra = new JMenuBar();
      

      // establecer elemento de men� Salir
      /*JMenuItem elementoSalir = new JMenuItem( "Salir" );
      elementoSalir.setMnemonic( 'S' );
      menuArchivo.add( elementoSalir );
      elementoSalir.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // terminar la aplicaci�n cuando el usuario haga clic en elementoSalir
            public void actionPerformed( ActionEvent evento )
            {
               System.exit( 0 );
            }*/

         //}  // fin de la clase interna an�nima

     // ); // fin de la llamada a addActionListener

      menuClientes = new JMenu( "Clientes" );
      menuProveedores = new JMenu( "Proveedores" );
      menuPedidos = new JMenu( "Pedidos" );
      menuTarifa = new JMenu( "Tarifas" );
      menuAyuda = new JMenu( "Ayuda" );

      menuClientes.setMnemonic( 'C' );

      JMenuItem elementoNCliente = new JMenuItem( "Nuevo Cliente" );
      elementoNCliente.setMnemonic( 'n' );
      menuClientes.add( elementoNCliente );
      elementoNCliente.addActionListener(
         new ActionListener() {          
            public void actionPerformed( ActionEvent evento ) {
               NuevoCliente = new JInternalFrame("Nuevo Cliente", true,false,false,true );
               
               CSAnyadirCliente panel = null;
                try {
                    panel = new CSAnyadirCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoCliente.getContentPane().add( panel,BorderLayout.CENTER);
               NuevoCliente.pack();
               elEscritorio.add( NuevoCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoCliente.getSize();
               NuevoCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoCliente.setVisible( true );
               
               
            }
         }); 

      JMenuItem elementoBCliente = new JMenuItem( "Buscar Cliente" );
      elementoBCliente.setMnemonic( 'b' );
      menuClientes.add( elementoBCliente );
      elementoBCliente.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               BuscarCliente = new JInternalFrame("Búsqueda de Clientes", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSBuscarCliente panel=null;
                try {
                    panel = new CSBuscarCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               BuscarCliente.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               BuscarCliente.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( BuscarCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarCliente.getSize();
               BuscarCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarCliente.setVisible( true );
               
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      JMenuItem elementoNContactoCliente = new JMenuItem( "Nuevo Contacto de Cliente" );
      elementoNContactoCliente.setMnemonic( 'n' );
      menuClientes.add( elementoNContactoCliente );
      elementoNContactoCliente.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               NuevoContactoCliente= new JInternalFrame("Nuevo Contacto Cliente", true,false,false,true );
               CSContactoCliente panel = null;
                try {
                    panel = new CSContactoCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoContactoCliente.getContentPane().add( panel,BorderLayout.CENTER);
               NuevoContactoCliente.pack();
               elEscritorio.add( NuevoContactoCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoContactoCliente.getSize();
               NuevoContactoCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoContactoCliente.setVisible( true );
               
            }
         });

      
      menuProveedores.setMnemonic( 'r' );

      JMenuItem elementoNProveedores = new JMenuItem( "Nuevo Proveedor" );
      elementoNProveedores.setMnemonic( 'n' );
      menuProveedores.add( elementoNProveedores );
       elementoNProveedores.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               NuevoProveedor = new JInternalFrame("Nuevo Proveedor", true,false,false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSAnyadirProveedor panel=null;
                try {
                    panel = new CSAnyadirProveedor();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               NuevoProveedor.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( NuevoProveedor );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoProveedor.getSize();
               NuevoProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoProveedor.setVisible( true );
              
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

       JMenuItem elementoBProveedores = new JMenuItem( "Buscar Proveedor" );
      elementoBProveedores.setMnemonic( 'n' );
      menuProveedores.add( elementoBProveedores );
       elementoBProveedores.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               BuscarProveedor = new JInternalFrame("Buscar Proveedor", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSBuscarProveedor panel = new CSBuscarProveedor();
               BuscarProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               BuscarProveedor.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( BuscarProveedor );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarProveedor.getSize();
               BuscarProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarProveedor.setVisible( true );
               
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener
      JMenuItem elementoNContactoProveedor = new JMenuItem( "Nuevo Contacto de Proveedor" );
      elementoNContactoProveedor.setMnemonic( 'c' );
      menuProveedores.add( elementoNContactoProveedor );
      elementoNContactoProveedor.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               NuevoContactoProveedor= new JInternalFrame("Nuevo Contacto Proveedor", true,false,false,true );
               CSContactoProveedor panel = null;
                try {
                    panel = new CSContactoProveedor();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoContactoProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               NuevoContactoProveedor.pack();
               elEscritorio.add( NuevoContactoProveedor );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoContactoProveedor.getSize();
               NuevoContactoProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoContactoProveedor.setVisible( true );
               
            }
         });

       
      menuPedidos.setMnemonic( 'P' );

      JMenuItem elementoNPedido = new JMenuItem( "Nuevo Pedido" );
      elementoNCliente.setMnemonic( 'p' );
      menuPedidos.add( elementoNPedido );
      elementoNPedido.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
                try {
                    // crear marco interno
                    NuevoPedido = new JInternalFrame("Nuevo Pedido", true, false, false, true);
                    // adjuntar panel al panel de contenido del marco interno
                    CSAnyadirPedido panel = new CSAnyadirPedido();
                    NuevoPedido.getContentPane().add(panel, BorderLayout.CENTER);
                    // establecer tama�o de marco interno en el tama�o de su contenido
                    NuevoPedido.pack();
                    // adjuntar marco interno al escritorio y mostrarlo
                    elEscritorio.add(NuevoPedido);
                    Dimension pantalla = elEscritorio.getSize();
                    Dimension ventana = NuevoPedido.getSize();
                    NuevoPedido.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
                    NuevoPedido.setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      JMenuItem elementoBPedido = new JMenuItem( "Buscar Pedido" );
      elementoNCliente.setMnemonic( 'b' );
      menuPedidos.add( elementoBPedido );
      elementoBPedido.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               BuscarPedido = new JInternalFrame("Buscar Pedido", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSBuscarPedido panel = new CSBuscarPedido();
               BuscarPedido.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               BuscarPedido.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( BuscarPedido );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarPedido.getSize();
               BuscarPedido.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarPedido.setVisible( true );
               
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

     
      menuTarifa.setMnemonic( 'T' );

      JMenuItem elementoTCliente = new JMenuItem( "Tarifa Cliente" );
      elementoTCliente.setMnemonic( 'p' );
      menuTarifa.add( elementoTCliente );
      elementoTCliente.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               TarifaCliente = new JInternalFrame("Tarifa Cliente", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSAnyadirTarifaCliente panel=null;
                try {
                    panel = new CSAnyadirTarifaCliente();
                } catch (ParseException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               TarifaCliente.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               TarifaCliente.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = TarifaCliente.getSize();
               elEscritorio.add( TarifaCliente );
               TarifaCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               TarifaCliente.setVisible( true );
              
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      JMenuItem elementoTProveedor = new JMenuItem( "Tarifa Proveedor" );
      elementoTProveedor.setMnemonic( 'b' );
      menuTarifa.add( elementoTProveedor );
      elementoTProveedor.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               TarifaProveedor = new JInternalFrame("Tarifa Proveedor", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSAnyadirTarifaProveedor panel=null;
                try {
                    panel = new CSAnyadirTarifaProveedor();
                } catch (ParseException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               TarifaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               TarifaProveedor.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = TarifaProveedor.getSize();
               elEscritorio.add( TarifaProveedor );
               TarifaProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               TarifaProveedor.setVisible( true );
              
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      
      menuAyuda.setMnemonic( 'A' );

      //establecer elemento de men� Acerca de...
      JMenuItem elementoAcerca = new JMenuItem( "Versión" );
      elementoAcerca.setMnemonic( 'A' );
      menuAyuda.add( elementoAcerca );
      elementoAcerca.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar cuadro de di�logo de mensaje cuando el usuario seleccione Acerca de...
            public void actionPerformed( ActionEvent evento )
            {
               JOptionPane.showMessageDialog(CSDesktop.this,
                  "                         CarSet version 1.0",
                  "Versión", JOptionPane.PLAIN_MESSAGE );
            }

          }  // fin de la clase interna an�nima

       ); // fin de la llamada a addActionListener


      
      setJMenuBar( barra );
      //barra.add( menuArchivo );
      barra.add( menuClientes );
      barra.add( menuProveedores );
      barra.add( menuPedidos );
      barra.add( menuTarifa );
      barra.add( menuAyuda );
  
      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension ventana = this.getSize();
      this.setLocation(
        (pantalla.width - ventana.width) / 2,
        (pantalla.height - ventana.height) / 2);
      
  }

}