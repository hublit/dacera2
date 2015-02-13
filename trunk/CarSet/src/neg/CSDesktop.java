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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
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
import jxl.read.biff.BiffException;
import utils.*;
import data.DbConnection;
import java.util.HashMap;

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
  public static JInternalFrame EditarPedidoArchivo;
  public static JInternalFrame NuevoPedidoArchivo;
  public static JInternalFrame BuscarPedido;
  public static JInternalFrame ValidarPedidoArchivo;
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
  public static JInternalFrame ResultPedidoArchivo;
  public static JInternalFrame ResultRecFactura;
  public static JInternalFrame ResultSPrPedido;
  public static JInternalFrame ResultFacturaPedido;
  public static JInternalFrame ResultBuscarMail;
  public static JInternalFrame ResultProveedor;
  public static JInternalFrame ResultContactoProveedor;
  public static JInternalFrame EditarProveedor;
  public static JInternalFrame NuevoContactoProveedor;
  public static JInternalFrame EditarContactoProveedor;
  public static JInternalFrame BuscaCliente;
  public static JInternalFrame BuscaMailCliente;
  public static JInternalFrame BuscaMailProveedor;
  public static JInternalFrame BuscaProveedor;
  public static JInternalFrame ServicioCliente;
  public static JInternalFrame ServicioProveedor;
  public static JInternalFrame FacturaCliente;
  public static JInternalFrame FacturaClientePedido;
  public static JInternalFrame RecuperarFacturaCliente;
  public static JInternalFrame FacturaProveedor;
  public static JInternalFrame InformeDetallado1;
  public static JInternalFrame InformeDetallado2;
  public static JInternalFrame InformeProveedor1;
  public static JInternalFrame InformeProveedor2;
  public static JInternalFrame NuevaFactura;
  public static JInternalFrame NuevoInformeDetallado1;
  public static JInternalFrame NuevoInformeDetallado2;
  public static JInternalFrame NuevoAlbaran;
  public static JInternalFrame BuscarValidacionPedidos;
  public static JInternalFrame ResultValidacionPedidos;
  public static JInternalFrame BuscarTesoreriaProveedor;
  public static JInternalFrame ResultTesoreriaProveedor;
  public static JInternalFrame BuscarTesoreriaCliente;
  public static JInternalFrame BuscarTesoreriaClienteInf;
  public static JInternalFrame BuscarTesoreriaProveedorInf;
  public static JInternalFrame ResultTesoreriaCliente;
  public static JMenu menuClientes;
  public static JMenu menuProveedores;
  public static JMenu menuPedidos;
  public static JMenu menuTarifa;
  public static JMenu menuFactura;
  public static JMenu menuInforme;
  public static JMenu menuAyuda;
  public static JMenu menuFacturasTesoreria;
  public static JMenu menuTesoreria;
  public static JMenuBar barra;
  public static JMenuItem menuNuevoCliente;
  public static JMenuItem menuBuscarCliente;
  public static JMenuItem menuContactoCliente;
  public static JMenuItem menuNuevoProveedor;
  public static JMenuItem menuBuscarProveedor;
  public static JMenuItem menuContactoProveedor;
  public static JMenuItem menuNuevoPedido;

  //////////////// NUEVO ////////////////////////
  public static JInternalFrame NuevoPedidoPrueba;
  public static JMenuItem menuNuevoPedidoPrueba;
  public static JInternalFrame BuscarPedidoNew;
  public static JInternalFrame EditarPedidoNew;
  public static JInternalFrame ResultPedidoNew;
  public static JMenuItem menuBuscarPedidoNew;
  public static JMenuItem menuValidarPedido;
  public static JMenuItem menuNuevoClienteNew;
  public static JInternalFrame NuevoClienteNew;
  public static JInternalFrame EditarClienteNew;
  public static JInternalFrame NuevoProveedorNew;
  public static JInternalFrame EditarProveedorNew;
  public static JMenuItem menuNuevoProveedorNew;
  public static JInternalFrame InformeComercial;
  public static JMenuItem menuInformeComercial;
  public static JInternalFrame InformeClienteUnitario;
  public static JMenuItem menuInformeClienteUnitario;
  /////////////////////////////////////////////

  public static JMenuItem menuBuscarPedido;
  public static JMenuItem menuNuevoPedidoArchivo;
  public static JMenuItem menuTarifaCliente;
  public static JMenuItem menuTarifaProveedor;
  public static JMenuItem menuFacturaCliente;
  public static JMenuItem menuFacturaClientePedido;
  public static JMenuItem menuRecuperarFacturaCliente;
  public static JMenuItem menuFacturaProveedor;
  public static JMenuItem menuInformeDetallado1;
  public static JMenuItem menuInformeDetallado2;
  public static JMenuItem menuInformeProveedor2;
  public static JMenuItem menuInformeProveedor1;
  public static JMenuItem menuTesoreriaValidacion;
  public static JMenuItem menuTesoreriaCliente;
  public static JMenuItem menuTesoreriaProveedor;

  public static DbConnection datos;
  public static HashMap mailCliente = new HashMap();
  public static HashMap mailProveedor = new HashMap();
  public static HashMap nombreCliente = new HashMap();
  public static HashMap nombreProveedor = new HashMap();
  public static String user;
  
  public  CSDesktop(String usuario) throws UnknownHostException
  {
      datos = new DbConnection();
      /*setDefaultCloseOperation(CSDesktop.DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we) {

            confirmarCierre();
        }
        });*/
      user = usuario;

      elEscritorio = new DesktopConFondo("/images/fondo_desktop.jpg");
      getContentPane().add( elEscritorio );
      barra = new JMenuBar();

      //Barra con los menus
      menuClientes = new JMenu( "Clientes" );
      menuProveedores = new JMenu( "Proveedores" );
      menuPedidos = new JMenu( "Pedidos" );
      menuTarifa = new JMenu( "Tarifas" );
      menuFactura = new JMenu( "Facturas Clientes" );
      menuFacturasTesoreria = new JMenu("Relación Facturas");
      menuInforme = new JMenu("Informes Comeciales");
      menuTesoreria = new JMenu("Informes Tesorería");
      menuAyuda = new JMenu( "Ayuda" );

      //MENU DE CLIENTES
      menuClientes.setMnemonic( 'C' );

      ////////////////////////////////////////////////////////////////////////////////////////////////
      //Nuevo Cliente
      menuNuevoClienteNew = new JMenuItem( "Nuevo Cliente" );
      menuNuevoClienteNew.setMnemonic( 'g' );
      menuClientes.add( menuNuevoClienteNew );
      menuNuevoClienteNew.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               NuevoClienteNew = new JInternalFrame("Nuevo Cliente", true,false,false,true );

               CSAnyadirClienteNew panel = null;
                try {
                    panel = new CSAnyadirClienteNew();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoClienteNew.getContentPane().add( panel,BorderLayout.CENTER);
               NuevoClienteNew.pack();
               elEscritorio.add( NuevoClienteNew );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoClienteNew.getSize();
               NuevoClienteNew.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoClienteNew.setVisible( true );
            }
         });

      menuNuevoPedidoPrueba = new JMenuItem( "Nuevo Pedido" );
      menuNuevoPedidoPrueba.setMnemonic( 'u' );
      menuPedidos.add( menuNuevoPedidoPrueba );
      menuNuevoPedidoPrueba.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
                try {
                    // crear marco interno
                    NuevoPedidoPrueba = new JInternalFrame("Nuevo Pedido", true, false, false, true);
                    // adjuntar panel al panel de contenido del marco interno
                    CSAnyadirPedidoNew panel = new CSAnyadirPedidoNew(null);
                    NuevoPedidoPrueba.getContentPane().add(panel, BorderLayout.CENTER);
                    // establecer tama�o de marco interno en el tama�o de su contenido
                    NuevoPedidoPrueba.pack();
                    // adjuntar marco interno al escritorio y mostrarlo
                    elEscritorio.add(NuevoPedidoPrueba);
                    Dimension pantalla = elEscritorio.getSize();
                    Dimension ventana = NuevoPedidoPrueba.getSize();
                    NuevoPedidoPrueba.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 15);
                    NuevoPedidoPrueba.setVisible(true);

                } catch (ParseException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      menuBuscarPedidoNew = new JMenuItem( "Buscar Pedido" );
      menuBuscarPedidoNew.setMnemonic( 'b' );
      menuPedidos.add( menuBuscarPedidoNew );
      menuBuscarPedidoNew.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               BuscarPedidoNew = new JInternalFrame("Buscar Pedido", true, false, false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSBuscarPedidoNew panel = new CSBuscarPedidoNew();
               BuscarPedidoNew.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               BuscarPedidoNew.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( BuscarPedidoNew );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarPedidoNew.getSize();
               BuscarPedidoNew.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarPedidoNew.setVisible( true );

            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      menuNuevoProveedorNew = new JMenuItem( "Nuevo Proveedor" );
      menuNuevoProveedorNew.setMnemonic( 'n' );
      menuProveedores.add( menuNuevoProveedorNew );
      menuNuevoProveedorNew.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
               // crear marco interno
               NuevoProveedorNew = new JInternalFrame("Nuevo Proveedor", true,false,false, true );
               // adjuntar panel al panel de contenido del marco interno
               CSAnyadirProveedorNew panel=null;
                try {
                    panel = new CSAnyadirProveedorNew();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               NuevoProveedorNew.getContentPane().add( panel,BorderLayout.CENTER);
               // establecer tama�o de marco interno en el tama�o de su contenido
               NuevoProveedorNew.pack();
               // adjuntar marco interno al escritorio y mostrarlo
               elEscritorio.add( NuevoProveedorNew );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = NuevoProveedorNew.getSize();
               NuevoProveedorNew.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               NuevoProveedorNew.setVisible( true );

            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      ////////////////////////////////////////////////////////////////////////////////////////////////

      //Nuevo Cliente
      menuNuevoCliente = new JMenuItem( "Nuevo Cliente" );
      menuNuevoCliente.setMnemonic( 'n' );
//Revisar
//      menuClientes.add( menuNuevoCliente );
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
//Revisar
//      menuProveedores.add( menuNuevoProveedor );
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
               NuevoProveedor.setVisible( false );
              
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
//Revisar
//      menuPedidos.add( menuNuevoPedido );
      menuNuevoPedido.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
                try {
                    // crear marco interno
                    NuevoPedido = new JInternalFrame("Nuevo Pedido", true, false, false, true);
                    // adjuntar panel al panel de contenido del marco interno
                    CSAnyadirPedido panel = new CSAnyadirPedido(null);
                    NuevoPedido.getContentPane().add(panel, BorderLayout.CENTER);
                    // establecer tama�o de marco interno en el tama�o de su contenido
                    NuevoPedido.pack();
                    // adjuntar marco interno al escritorio y mostrarlo
                    elEscritorio.add(NuevoPedido);
                    Dimension pantalla = elEscritorio.getSize();
                    Dimension ventana = NuevoPedido.getSize();
                    NuevoPedido.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
                    NuevoPedido.setVisible(false);
                    
                } catch (ParseException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      menuBuscarPedido = new JMenuItem( "Buscar Pedido" );
      menuBuscarPedido.setMnemonic( 'b' );
//Revisar
//      menuPedidos.add( menuBuscarPedido );
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
               BuscarPedido.setVisible( false );
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener

      menuNuevoPedidoArchivo = new JMenuItem( "Pedidos desde Archivo" );
      menuNuevoPedidoArchivo.setMnemonic( 'a' );
      menuPedidos.add( menuNuevoPedidoArchivo );
      menuNuevoPedidoArchivo.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
                try {
                    // crear marco interno
                    NuevoPedidoArchivo = new JInternalFrame("Pedido desde Archivo", true, false, false, true);
                    // adjuntar panel al panel de contenido del marco interno
                    CSAnyadirPedidoArchivoNew panel = null;
                    try {
                        panel = new CSAnyadirPedidoArchivoNew();
                    } catch (BiffException ex) {
                        Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    NuevoPedidoArchivo.getContentPane().add(panel, BorderLayout.CENTER);
                    // establecer tama�o de marco interno en el tama�o de su contenido
                    NuevoPedidoArchivo.pack();
                    // adjuntar marco interno al escritorio y mostrarlo
                    elEscritorio.add(NuevoPedidoArchivo);
                    Dimension pantalla = elEscritorio.getSize();
                    Dimension ventana = NuevoPedidoArchivo.getSize();
                    NuevoPedidoArchivo.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
                    //NuevoPedidoArchivo.setVisible(true);
                    NuevoPedidoArchivo.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         } // fin de la clase interna an�nima
      ); // fin de la llamada a addActionListener


      menuValidarPedido = new JMenuItem( "Validar Pedidos de Archivo" );
      menuValidarPedido.setMnemonic( 'v' );
      menuPedidos.add( menuValidarPedido );
      menuValidarPedido.addActionListener(

         new ActionListener() {  // clase interna an�nima

            // mostrar nueva ventana interna
            public void actionPerformed( ActionEvent evento ) {
                    CSValidarPedidoArchivo panel = null;
                try {
                    panel = new CSValidarPedidoArchivo();

                } catch (UnknownHostException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
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

      menuFacturaClientePedido = new JMenuItem( "Factura Cliente Pedido" );
      menuFacturaClientePedido.setMnemonic( 'x' );
      menuFactura.add( menuFacturaClientePedido );
      menuFacturaClientePedido.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               FacturaClientePedido = new JInternalFrame("Factura Cliente Pedido", true,false,false,true );

               CSFacturaClientePedido panel = null;
               panel = new CSFacturaClientePedido();
               FacturaClientePedido.getContentPane().add( panel,BorderLayout.CENTER);
               FacturaClientePedido.pack();
               elEscritorio.add( FacturaClientePedido );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = FacturaClientePedido.getSize();
               FacturaClientePedido.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               FacturaClientePedido.setVisible( true );
            }
         });

      menuFacturaProveedor = new JMenuItem( "Albaran Proveedor" );
      menuFacturaProveedor.setMnemonic( 'x' );
      //menuFactura.add( menuFacturaProveedor );
      menuFacturaProveedor.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               FacturaProveedor = new JInternalFrame("Albaran Proveedor", true,false,false,true );

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

       menuRecuperarFacturaCliente = new JMenuItem( "Recuperar Factura Cliente" );
       menuRecuperarFacturaCliente.setMnemonic( 'x' );
       menuFactura.add( menuRecuperarFacturaCliente );
       menuRecuperarFacturaCliente.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               RecuperarFacturaCliente = new JInternalFrame("Modificacion Facturas", true,false,false,true );

               CSRecuperarFacturaCliente panel = null;
               panel = new CSRecuperarFacturaCliente();
               RecuperarFacturaCliente.getContentPane().add( panel,BorderLayout.CENTER);
               RecuperarFacturaCliente.pack();
               elEscritorio.add( RecuperarFacturaCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = RecuperarFacturaCliente.getSize();
               RecuperarFacturaCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               RecuperarFacturaCliente.setVisible( true );
            }
         });


      menuInforme.setMnemonic( 'I' );


      menuInformeComercial = new JMenuItem( "Informe Comercial" );
      menuInformeComercial.setMnemonic( 'w' );
      menuInforme.add( menuInformeComercial );
      menuInformeComercial.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeComercial = new JInternalFrame("Informe Comercial", true,false,false,true );
               CSInformeComercial panel=null;
                try {
                    panel = panel = new CSInformeComercial();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               InformeComercial.getContentPane().add( panel,BorderLayout.CENTER);
               InformeComercial.pack();
               elEscritorio.add( InformeComercial );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeComercial.getSize();
               InformeComercial.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeComercial.setVisible( true );
            }
         });

      menuInformeClienteUnitario = new JMenuItem( "Informe Cliente Unitario" );
      menuInformeClienteUnitario.setMnemonic( 'w' );
      menuInforme.add( menuInformeClienteUnitario );
      menuInformeClienteUnitario.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeClienteUnitario = new JInternalFrame("Informe Cliente Unitario", true,false,false,true );
               CSInformeClienteUnitario panel=null;
                try {
                    panel = panel = new CSInformeClienteUnitario();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               InformeClienteUnitario.getContentPane().add( panel,BorderLayout.CENTER);
               InformeClienteUnitario.pack();
               elEscritorio.add( InformeClienteUnitario );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeClienteUnitario.getSize();
               InformeClienteUnitario.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeClienteUnitario.setVisible( true );
            }
         });

      menuInformeDetallado1 = new JMenuItem( "Informe Cliente 1" );
      menuInformeDetallado1.setMnemonic( 'w' );
      menuInforme.add( menuInformeDetallado1 );
      menuInformeDetallado1.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeDetallado1 = new JInternalFrame("Informe Cliente 1", true,false,false,true );
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

      menuInformeDetallado2 = new JMenuItem( "Informe Cliente 2" );
      menuInformeDetallado2.setMnemonic( 'x' );
      menuInforme.add( menuInformeDetallado2 );
      menuInformeDetallado2.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeDetallado2 = new JInternalFrame("Informe Cliente 2", true,false,false,true );
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

      menuInformeProveedor1 = new JMenuItem( "Informe Proveedor 1" );
      menuInformeProveedor1.setMnemonic( 'w' );
      menuInforme.add( menuInformeProveedor1 );
      menuInformeProveedor1.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeProveedor1 = new JInternalFrame("Informe Proveedor 1", true,false,false,true );
               CSInformeDetPr1 panel=null;
                try {
                    panel = panel = new CSInformeDetPr1();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }
               InformeProveedor1.getContentPane().add( panel,BorderLayout.CENTER);
               InformeProveedor1.pack();
               elEscritorio.add( InformeProveedor1 );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeProveedor1.getSize();
               InformeProveedor1.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeProveedor1.setVisible( true );
            }
         });

      menuInformeProveedor2 = new JMenuItem( "Informe Proveedor 2" );
      menuInformeProveedor2.setMnemonic( 'x' );
      menuInforme.add( menuInformeProveedor2 );
      menuInformeProveedor2.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               InformeProveedor2 = new JInternalFrame("Informe Proveedor 2", true,false,false,true );
               CSInformeDetPr2 panel = panel = new CSInformeDetPr2();
               InformeProveedor2.getContentPane().add( panel,BorderLayout.CENTER);
               InformeProveedor2.pack();
               elEscritorio.add( InformeProveedor2 );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = InformeProveedor2.getSize();
               InformeProveedor2.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               InformeProveedor2.setVisible( true );
            }
         });
      

      //Relación Facturas Tesorería
      menuFacturasTesoreria.setMnemonic( 'N' );

      menuTesoreriaValidacion = new JMenuItem( "Validación de Pedidos" );
      menuTesoreriaValidacion.setMnemonic( 'v' );
      menuFacturasTesoreria.add( menuTesoreriaValidacion );
      menuTesoreriaValidacion.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
               BuscarValidacionPedidos = new JInternalFrame("Validación de Pedidos", true,false,false,true );
               CSValidarPedido panel = null;

               panel = new CSValidarPedido();

               BuscarValidacionPedidos.getContentPane().add( panel,BorderLayout.CENTER);
               BuscarValidacionPedidos.pack();
               elEscritorio.add( BuscarValidacionPedidos );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarValidacionPedidos.getSize();
               BuscarValidacionPedidos.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarValidacionPedidos.setVisible( true );
            }
         });

      menuTesoreriaProveedor = new JMenuItem( "Relación F. Proveedor" );
      menuTesoreriaProveedor.setMnemonic( 'r' );
      menuFacturasTesoreria.add( menuTesoreriaProveedor );
      menuTesoreriaProveedor.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
              BuscarTesoreriaProveedor = new JInternalFrame("Relación F. Proveedores", true,false,false,true );
               CSLanzarInformeTesoreriaProveedor panel = null;

               panel = new CSLanzarInformeTesoreriaProveedor();

               BuscarTesoreriaProveedor.getContentPane().add( panel,BorderLayout.CENTER);
               BuscarTesoreriaProveedor.pack();
               elEscritorio.add( BuscarTesoreriaProveedor );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarTesoreriaProveedor.getSize();
               BuscarTesoreriaProveedor.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarTesoreriaProveedor.setVisible( true );
            }
         });

      menuTesoreriaCliente = new JMenuItem( "Relación F. Cliente" );
      menuTesoreriaCliente.setMnemonic( 'l' );
      menuFacturasTesoreria.add( menuTesoreriaCliente );
      menuTesoreriaCliente.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
              BuscarTesoreriaCliente = new JInternalFrame("Relación F. Cliente", true,false,false,true );
               CSLanzarInformeTesoreriaCliente panel = null;
                try {
                    panel = new CSLanzarInformeTesoreriaCliente();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }

               BuscarTesoreriaCliente.getContentPane().add( panel,BorderLayout.CENTER);
               BuscarTesoreriaCliente.pack();
               elEscritorio.add( BuscarTesoreriaCliente );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarTesoreriaCliente.getSize();
               BuscarTesoreriaCliente.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarTesoreriaCliente.setVisible( true );
            }
         });
   
/////////////////////////////
      //Tesorería
      menuTesoreria.setMnemonic( 'E' );

      menuTesoreriaProveedor = new JMenuItem( "Tesoreria Proveedor" );
      menuTesoreriaProveedor.setMnemonic( 'c' );
      menuTesoreria.add( menuTesoreriaProveedor );
      menuTesoreriaProveedor.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
              BuscarTesoreriaProveedorInf = new JInternalFrame("Tesorería Proveedor", true,false,false,true );
               CSLanzarTesoreriaProveedorInf panel = null;
                try {
                    panel = new CSLanzarTesoreriaProveedorInf();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }

               BuscarTesoreriaProveedorInf.getContentPane().add( panel,BorderLayout.CENTER);
               BuscarTesoreriaProveedorInf.pack();
               elEscritorio.add( BuscarTesoreriaProveedorInf );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarTesoreriaProveedorInf.getSize();
               BuscarTesoreriaProveedorInf.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarTesoreriaProveedorInf.setVisible( true );
            }
         });

       menuTesoreriaCliente = new JMenuItem( "Tesoreria Cliente" );
       menuTesoreriaCliente.setMnemonic( 'c' );
       menuTesoreria.add( menuTesoreriaCliente );
       menuTesoreriaCliente.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent evento ) {
              BuscarTesoreriaClienteInf = new JInternalFrame("Tesorería Cliente", true,false,false,true );
                CSLanzarTesoreriaClienteInf panel = null;
                try {
                    panel = new CSLanzarTesoreriaClienteInf();
                } catch (SQLException ex) {
                    Logger.getLogger(CSDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }

               BuscarTesoreriaClienteInf.getContentPane().add( panel,BorderLayout.CENTER);
               BuscarTesoreriaClienteInf.pack();
               elEscritorio.add( BuscarTesoreriaClienteInf );
               Dimension pantalla = elEscritorio.getSize();
               Dimension ventana = BuscarTesoreriaClienteInf.getSize();
               BuscarTesoreriaClienteInf.setLocation(
                     (pantalla.width - ventana.width) / 2,
                     (pantalla.height - ventana.height) / 2);
               BuscarTesoreriaClienteInf.setVisible( true );
            }
         });

/////////////////////////////

      //Ayuda
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
                  "CarSet version 4.0.5 (13-02-2015)",
                  "Versión", JOptionPane.PLAIN_MESSAGE );
            }
          }  // fin de la clase interna an�nima

       ); // fin de la llamada a addActionListener

      
      setJMenuBar( barra );
      barra.add( menuClientes );
      barra.add( menuProveedores );
      barra.add( menuPedidos );
      //barra.add( menuTarifa );
      if (usuario.equals("2") ||
          usuario.equals("3") ||
          usuario.equals("4") ||
          usuario.equals("5") ||
          usuario.equals("7") ||
          usuario.equals("8"))
      {
        barra.add( menuFactura );
      }
      if (usuario.equals("2") || 
          usuario.equals("4") ||
          usuario.equals("8"))
      {
        barra.add( menuFacturasTesoreria );
        barra.add( menuTesoreria );
      }

     if (usuario.equals("2") ||
          usuario.equals("3") ||
          usuario.equals("4") ||
          usuario.equals("5") ||
          usuario.equals("7") ||
          usuario.equals("8"))
      {
        barra.add( menuInforme );
      }

      barra.add( menuAyuda );
  
      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension ventana = this.getSize();
      this.setLocation(
        (pantalla.width - ventana.width) / 2,
        (pantalla.height - ventana.height) / 2);
      
  }

}