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
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import utils.*;
import data.DbConnection;

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
  public static JInternalFrame FacturaCliente;
  public static JInternalFrame FacturaProveedor;
  public static JInternalFrame InformeDetallado1;
  public static JInternalFrame InformeDetallado2;
  public static JInternalFrame NuevaFactura;
  public static JInternalFrame NuevoInformeDetallado1;
  public static JInternalFrame NuevoInformeDetallado2;
  public static JInternalFrame NuevoAlbaran;
  public static JMenu menuClientes;
  public static JMenu menuProveedores;
  public static JMenu menuPedidos;
  public static JMenu menuTarifa;
  public static JMenu menuFactura;
  public static JMenu menuInforme;
  public static JMenu menuAyuda;
  public static JMenuBar barra;
  public static JMenuItem menuNuevoCliente;
  public static JMenuItem menuBuscarCliente;
  public static JMenuItem menuContactoCliente;
  public static JMenuItem menuNuevoProveedor;
  public static JMenuItem menuBuscarProveedor;
  public static JMenuItem menuContactoProveedor;
  public static JMenuItem menuNuevoPedido;
  public static JMenuItem menuBuscarPedido;
  public static JMenuItem menuTarifaCliente;
  public static JMenuItem menuTarifaProveedor;
  public static JMenuItem menuFacturaCliente;
  public static JMenuItem menuFacturaProveedor;
  public static JMenuItem menuInformeDetallado1;
  public static JMenuItem menuInformeDetallado2;

  public static DbConnection datos = new DbConnection();
  
  public  CSDesktop()
  {

      /*setDefaultCloseOperation(CSDesktop.DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we) {

            confirmarCierre();
        }
        });*/

      elEscritorio = new DesktopConFondo("/images/fondo_desktop.jpg");
      getContentPane().add( elEscritorio );
      barra = new JMenuBar();

      //Barra con los menus
      menuClientes = new JMenu( "Clientes" );
      menuProveedores = new JMenu( "Proveedores" );
      menuPedidos = new JMenu( "Pedidos" );
      menuTarifa = new JMenu( "Tarifas" );
      menuFactura = new JMenu( "Facturas" );
      menuInforme = new JMenu("Informes");
      menuAyuda = new JMenu( "Ayuda" );

      //MENU DE CLIENTES
      menuClientes.setMnemonic( 'C' );

      //Nuevo Cliente
      menuNuevoCliente = new JMenuItem( "Nuevo Cliente" );
      menuNuevoCliente.setMnemonic( 'n' );
      menuClientes.add( menuNuevoCliente );
      menuNuevoCliente.addActionListener(
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

      //Buscar Cliente
      menuBuscarCliente = new JMenuItem( "Buscar Cliente" );
      menuBuscarCliente.setMnemonic( 'b' );
      menuClientes.add( menuBuscarCliente );
      menuBuscarCliente.addActionListener(

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

      menuContactoCliente = new JMenuItem( "Nuevo Contacto de Cliente" );
      menuContactoCliente.setMnemonic( 'n' );
      menuClientes.add( menuContactoCliente );
      menuContactoCliente.addActionListener(
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

      menuNuevoProveedor = new JMenuItem( "Nuevo Proveedor" );
      menuNuevoProveedor.setMnemonic( 'n' );
      menuProveedores.add( menuNuevoProveedor );
      menuNuevoProveedor.addActionListener(

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

      menuBuscarProveedor = new JMenuItem( "Buscar Proveedor" );
      menuBuscarProveedor.setMnemonic( 'n' );
      menuProveedores.add( menuBuscarProveedor );
      menuBuscarProveedor.addActionListener(

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


      menuContactoProveedor = new JMenuItem( "Nuevo Contacto de Proveedor" );
      menuContactoProveedor.setMnemonic( 'c' );
      menuProveedores.add( menuContactoProveedor );
      menuContactoProveedor.addActionListener(
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

      menuNuevoPedido = new JMenuItem( "Nuevo Pedido" );
      menuNuevoPedido.setMnemonic( 'p' );
      menuPedidos.add( menuNuevoPedido );
      menuNuevoPedido.addActionListener(

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

      menuBuscarPedido = new JMenuItem( "Buscar Pedido" );
      menuBuscarPedido.setMnemonic( 'b' );
      menuPedidos.add( menuBuscarPedido );
      menuBuscarPedido.addActionListener(

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

      menuTarifaCliente = new JMenuItem( "Tarifa Cliente" );
      menuTarifaCliente.setMnemonic( 'p' );
      menuTarifa.add( menuTarifaCliente );
      menuTarifaCliente.addActionListener(

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

      menuTarifaProveedor = new JMenuItem( "Tarifa Proveedor" );
      menuTarifaProveedor.setMnemonic( 'b' );
      menuTarifa.add( menuTarifaProveedor );
      menuTarifaProveedor.addActionListener(

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

      menuFactura.setMnemonic( 'F' );

      menuFacturaCliente = new JMenuItem( "Factura Cliente" );
      menuFacturaCliente.setMnemonic( 'f' );
      menuFactura.add( menuFacturaCliente );
      menuFacturaCliente.addActionListener(
         new ActionListener() { 
            public void actionPerformed( ActionEvent evento ) {
               FacturaCliente = new JInternalFrame("Factura Cliente", true,false,false,true );

               CSFacturaCliente panel = null;
               panel = new CSFacturaCliente();
               FacturaCliente.getContentPane().add( panel,BorderLayout.CENTER);
               FacturaCliente.pack();
               elEscritorio.add( FacturaCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = FacturaCliente.getSize();
               FacturaCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               FacturaCliente.setVisible( true );
            }
         });

      menuFacturaProveedor = new JMenuItem( "Factura Proveedor" );
      menuFacturaProveedor.setMnemonic( 'x' );
      menuFactura.add( menuFacturaProveedor );
      menuFacturaProveedor.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               FacturaProveedor = new JInternalFrame("Factura Proveedor", true,false,false,true );

               CSFacturaProveedor panel = null;
               panel = new CSFacturaProveedor();
               FacturaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               FacturaProveedor.pack();
               elEscritorio.add( FacturaProveedor );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = FacturaProveedor.getSize();
               FacturaProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               FacturaProveedor.setVisible( true );
            }
         });

      menuInforme.setMnemonic( 'I' );

      menuInformeDetallado1 = new JMenuItem( "Informe Detallado 1" );
      menuInformeDetallado1.setMnemonic( 'w' );
      menuInforme.add( menuInformeDetallado1 );
      menuInformeDetallado1.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeDetallado1 = new JInternalFrame("Informe Detallado 1", true,false,false,true );
               CSInformeDet1 panel=null;
                try {
                    panel = panel = new CSInformeDet1();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               InformeDetallado1.getContentPane().add( panel,BorderLayout.CENTER);
               InformeDetallado1.pack();
               elEscritorio.add( InformeDetallado1 );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeDetallado1.getSize();
               InformeDetallado1.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeDetallado1.setVisible( true );
            }
         });

      menuInformeDetallado2 = new JMenuItem( "Informe Detallado 2" );
      menuInformeDetallado2.setMnemonic( 'x' );
      menuInforme.add( menuInformeDetallado2 );
      menuInformeDetallado2.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeDetallado2 = new JInternalFrame("Informe Detallado 2", true,false,false,true );
               CSInformeDet2 panel = panel = new CSInformeDet2();
               InformeDetallado2.getContentPane().add( panel,BorderLayout.CENTER);
               InformeDetallado2.pack();
               elEscritorio.add( InformeDetallado2 );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeDetallado2.getSize();
               InformeDetallado2.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeDetallado2.setVisible( true );
            }
         });

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
                  "                         CarSet version 1.2",
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
      barra.add( menuFactura );
      barra.add( menuInforme );
      barra.add( menuAyuda );
  
      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension ventana = this.getSize();
      this.setLocation(
        (pantalla.width - ventana.width) / 2,
        (pantalla.height - ventana.height) / 2);
      
  }

  /*void confirmarCierre()
  {
       int confirmado = JOptionPane.showConfirmDialog(this,"¿Realmente quieres salir de la aplicación?");

                    if (JOptionPane.OK_OPTION == confirmado)
                    {
                        CSDesktop.datos.cerrarConexion();
                    }
  }*/
}