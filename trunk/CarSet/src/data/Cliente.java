/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import neg.CSDesktop;

/**
 *
 * @author lito
 */
public class Cliente{
   /** Constructor de DbUser */

   public Cliente(){
   }

   /**
    * Método que trae todos los clientes
    */
   public Object[][] getClientes()
   {
      int registros = 0;

      try{

         ResultSet rsTotal = CSDesktop.datos.select("SELECT count(1) as cont FROM cl_clientes WHERE cl_estado = 'Activo'");

         rsTotal.next();
         registros = rsTotal.getInt("cont");
         rsTotal.close();
      }
      catch(SQLException e)
      {
         System.out.println(e);
      }

      Object [][] data = new Object[registros][5];
      try
      {
         ResultSet rsCl = CSDesktop.datos.select("SELECT cl_id, cl_nombre,cl_DNI_CIF  FROM cl_clientes WHERE cl_estado = 'Activo' ORDER BY cl_nombre");

         int i = 0;
         while(rsCl.next())
         {
            String estCodigo = rsCl.getString("cl_id");
            String estNombre = rsCl.getString("cl_nombre");
            String estDNI = rsCl.getString("cl_DNi_CIF");
            data[i][0] = estCodigo;
            data[i][1] = estNombre;
            data[i][2] = estDNI;
            i++;
         }
         rsCl.close();
      }
      catch(SQLException e)
      {
         System.out.println(e);
      }

      return data;

   }


   public Object[][] getClientesQuery(String condicion)
   {
      int registros = 0;
      try
      {
         ResultSet rsTotal = CSDesktop.datos.select("SELECT count(1) as cont FROM cl_clientes WHERE cl_estado = 'Activo' AND cl_nombre like  '%"+condicion+"%' ");

         rsTotal.next();
         registros = rsTotal.getInt("cont");
         rsTotal.close();
      }
      catch(SQLException e)
      {
         System.out.println(e);
      }

      Object [][] data = new Object[registros][5];
      try
      {
         ResultSet rs = CSDesktop.datos.select("SELECT cl_id, cl_nombre, cl_DNI_CIF  FROM cl_clientes WHERE cl_estado = 'Activo' AND cl_nombre like  '%"+condicion+"%' ORDER BY cl_nombre");

         int i = 0;
         while(rs.next())
         {
            String estCodigo = rs.getString("cl_id");
            String estNombre = rs.getString("cl_nombre");
            String estDNI = rs.getString("cl_DNI_CIF");
            data[i][0] = estCodigo;
            data[i][1] = estNombre;
            data[i][2] = estDNI;
            i++;
         }
         rs.close();
      }
      catch(SQLException e)
      {
         System.out.println(e);
      }

      return data;

   }


   /**
    * Método que comprueba si existe un cliente por el nombre y CIF
    * @param nombre
    * @param nif
    * @return boolean cliente
    */
   public boolean isCliente(String nombre, String nif)
   {
      boolean cliente = false;
      String estCodigo = "";

      try
      {
         ResultSet rsCl = CSDesktop.datos.select("SELECT cl_id, cl_nombre " +
                                    "FROM cl_clientes " +
                                    "WHERE cl_nombre = '"+nombre+"' AND cl_DNI_CIF = '"+nif+"'");

         int i = 0;
         while(rsCl.next())
         {
            estCodigo = rsCl.getString("cl_id");
            i++;
         }
         rsCl.close();
         
         if (!estCodigo.equals(""))
         {
             cliente = true;
         }
      }
      catch(SQLException e)

      {
         System.out.println(e);
      }

      return cliente;

   }

   public int getClienteID(String cliente)
   {
       int clienteID = 0;

       ResultSet rsCl = CSDesktop.datos.select("SELECT cl_id FROM cl_clientes WHERE cl_nombre = '"+cliente+"'");
        try {
            while (rsCl.next()) {
                clienteID = rsCl.getInt("cl_id");
            }
            rsCl.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return clienteID;
   }

   public String getCliente(int clienteID)
   {
       String cliente="";

       ResultSet rsCl = CSDesktop.datos.select("SELECT cl_nombre FROM cl_clientes WHERE cl_id = "+clienteID);
        try {
            while (rsCl.next()) {
                cliente = rsCl.getString("cl_nombre");
            }
            rsCl.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return cliente;
   }

   /**
    * Método para sacar los datos necesarios del cliente en la factura
    * @param clienteID
    * @return
    */
   public BeanCliente getDatosFacturaCliente(int clienteID)
   {
       BeanCliente bCliente = new BeanCliente();
       int formaPago=0;

       ResultSet rsCl = CSDesktop.datos.select("SELECT cl_id, cl_nombre, cl_DNI_CIF, cl_direccion, cl_cod_postal, " +
                                  "cl_poblacion, cl_provincia, cl_direccion_fiscal, cl_cod_postal_fiscal, cl_poblacion_fiscal, " +
                                  "cl_provincia_fiscal, cl_plazo, cl_dias_plazo, cl_estado, fp_id,cl_correo " +
                                  "FROM cl_clientes WHERE cl_id = "+clienteID);
        try
        {
            while (rsCl.next())
            {
                bCliente.setNombre(rsCl.getString("cl_nombre"));
                bCliente.setDNI_CIF(rsCl.getString("cl_DNI_CIF"));
                bCliente.setDireccion(rsCl.getString("cl_direccion"));
                bCliente.setCod_postal(rsCl.getString("cl_cod_postal"));
                bCliente.setPoblacion(rsCl.getString("cl_poblacion"));
                bCliente.setProvincia(rsCl.getString("cl_provincia"));
                bCliente.setDireccion_fiscal(rsCl.getString("cl_direccion_fiscal"));
                bCliente.setCod_postal_fiscal(rsCl.getString("cl_cod_postal_fiscal"));
                bCliente.setPoblacion_fiscal(rsCl.getString("cl_poblacion_fiscal"));
                bCliente.setProvinciaFiscal(rsCl.getString("cl_provincia_fiscal"));
                bCliente.setPlazoPago(rsCl.getString("cl_plazo"));
                bCliente.setEstado(rsCl.getString("cl_estado"));
                bCliente.setDiasPlazo(rsCl.getString("cl_dias_plazo"));
                formaPago=rsCl.getInt("fp_id");
                bCliente.setCorreo(rsCl.getInt("cl_correo"));
            }
            rsCl.close();
            String fPago=getSFormaPago(formaPago);
            bCliente.setFormaPago(fPago);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

       return bCliente;
   }

   public String getSFormaPago (int formaPago)
   {
       String fPago="";

       ResultSet rsCl2 = CSDesktop.datos.select("SELECT fp_tipo FROM FP_FORMA_PAGO WHERE FP_ID="+ formaPago);
        try {
            while (rsCl2.next())
            {
                fPago=rsCl2.getString("fp_tipo");
            }
            rsCl2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return fPago;
   }

      /**
    * Método para sacar los datos necesarios del cliente en el Pedido
    * @param clienteID
    * @return BeanCliente
    */
   public BeanCliente getDatosPedidoCliente(int clienteID)
   {
       BeanCliente bCliente = new BeanCliente();
       int formaPago=0;

       ResultSet rsCl = CSDesktop.datos.select("SELECT cl_id, cl_tipo_tarifa, cl_tarifa_url " +
                                               "FROM cl_clientes WHERE cl_id = "+clienteID);
        try
        {
            while (rsCl.next())
            {
                bCliente.setTipoTarifa(rsCl.getInt("cl_tipo_tarifa"));
                bCliente.setUrlTarifas(rsCl.getString("cl_tarifa_url"));
            }
            rsCl.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

       return bCliente;
   }

         /**
    * Método para sacar los datos necesarios del cliente en el Pedido
    * @param clienteID
    * @return BeanCliente
    */
   public float getDatosServicioCliente(int clienteID, String servicio)
   {
       float precio = 0;

       ResultSet rsCl = CSDesktop.datos.select("SELECT "+servicio+" FROM sv_servicios WHERE cl_id = "+clienteID);
//System.out.println("SELECT "+servicio+" FROM sv_servicios WHERE cl_id = "+clienteID);
        try
        {
            while (rsCl.next())
            {
                if(servicio.equals("sv_turismo")){
                    precio = rsCl.getFloat("sv_turismo");
                }else if(servicio.equals("sv_flm")){
                    precio = rsCl.getFloat("sv_flm");
                }else if(servicio.equals("sv_furgoneta")){
                    precio = rsCl.getFloat("sv_furgoneta");
                }else if(servicio.equals("sv_todoterreno")){
                    precio = rsCl.getFloat("sv_todoterreno");
                }else if(servicio.equals("sv_furgon")){
                    precio = rsCl.getFloat("sv_furgon");
                }else if(servicio.equals("sv_suv")){
                    precio = rsCl.getFloat("sv_suv");
                }else if(servicio.equals("sv_especial")){
                    precio = rsCl.getFloat("sv_especial");
                }else if(servicio.equals("sv_dias")){
                    precio = rsCl.getFloat("sv_dias");
                }else if(servicio.equals("sv_moto")){
                    precio = rsCl.getFloat("sv_moto");
                }else if(servicio.equals("sv_carro_sobredim")){
                    precio = rsCl.getFloat("sv_carro_sobredim");
                }
        }
            rsCl.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return precio;
   }

   /**
    * Método para sacar los datos necesarios del cliente en el Pedido
    * @param clienteID
    * @return BeanCliente
    */
   public String getObservacionesCliente(int clienteID)
   {
       String observaciones = "";

       ResultSet rsClObs = CSDesktop.datos.select("SELECT sv_text_asistencia FROM sv_servicios WHERE cl_id = "+clienteID);

        try
        {
            while (rsClObs.next())
            {
                observaciones = rsClObs.getString("sv_text_asistencia");
            }

            rsClObs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

       return observaciones;
   }

      /**
    * Método para sacar los datos necesarios del cliente en el Pedido
    * @param clienteID
    * @return BeanCliente
    */
   public String getObsGeneralesCliente(int clienteID)
   {
       String obsGeneral = "";

       ResultSet rsClObs = CSDesktop.datos.select("SELECT sv_text_general FROM sv_servicios WHERE cl_id = "+clienteID);

        try
        {
            while (rsClObs.next())
            {
                obsGeneral = rsClObs.getString("sv_text_general");
            }

            rsClObs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

       return obsGeneral;
   }

}