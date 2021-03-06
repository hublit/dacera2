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
public class Proveedor
{

   /** Constructor de DbUser */
   public Proveedor()
   {

   }


   /**
    * Método que trae todos los proveedores
    */
   public Object[][] getProveedores()
   {
      int registros = 0;
      try
      {

         ResultSet rsTotal = CSDesktop.datos.select("SELECT count(1) as cont FROM pr_proveedores WHERE pr_estado = 'Activo'");

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
         ResultSet rs = CSDesktop.datos.select("SELECT pr_id, pr_nombre_fiscal, pr_DNI_CIF FROM pr_proveedores WHERE pr_estado = 'Activo' ORDER BY pr_nombre_fiscal ");

         int i = 0;
         while(rs.next())
         {
            String estCodigo = rs.getString("pr_id");
            String estNombre = rs.getString("pr_nombre_fiscal");
            String estDNI = rs.getString("pr_DNI_CIF");

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
   *
   * @param condicion
   * @return
   */
    public Object[][] getProveedoresQuery(String condicion)
    {
      int registros = 0;
      try
      {

         ResultSet rsTotal = CSDesktop.datos.select("SELECT count(1) as cont FROM pr_proveedores WHERE pr_estado = 'Activo'AND pr_nombre_fiscal like  '%"+condicion+"%' ");

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
         ResultSet rs = CSDesktop.datos.select("SELECT pr_id, pr_nombre_fiscal, pr_DNI_CIF FROM pr_proveedores WHERE pr_estado = 'Activo' AND pr_nombre_fiscal like  '%"+condicion+"%' ORDER BY pr_nombre_fiscal ");

         int i = 0;
         while(rs.next())
         {
            String estCodigo = rs.getString("pr_id");
            String estNombre = rs.getString("pr_nombre_fiscal");
            String estDNI = rs.getString("pr_DNI_CIF");

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
    * Método que comprueba si existe un proveedor por el nombre y DNI
    * @param nombre
    * @return boolean proveedor
    */
   public boolean isProveedor(String nombre, String nif)
   {
      boolean proveedor = false;
      String estCodigo = "";

      try
      {
         ResultSet rsCl = CSDesktop.datos.select("SELECT pr_id, pr_nombre_fiscal " +
                                    "FROM pr_proveedores " +
                                    "WHERE pr_nombre_fiscal = '"+nombre+"' AND pr_DNI_CIF = '"+nif+"'");

         int i = 0;
         while(rsCl.next())
         {
            estCodigo = rsCl.getString("pr_id");
            i++;
         }
         rsCl.close();

         if (!estCodigo.equals(""))
         {
             proveedor = true;
         }
      }
      catch(SQLException e)

      {
         System.out.println(e);
      }

      return proveedor;

   }

   /**
    * Metodo para consultar el id del Proveedor a partir de su nombre
    * @param proveedor
    * @return int pr_id
    */
   public int getProveedorID(String proveedor)
   {
       int proveedorID=0;

       ResultSet rsCl = CSDesktop.datos.select("SELECT pr_id FROM pr_proveedores WHERE pr_nombre_fiscal = '"+proveedor+"'");
        try {
            while (rsCl.next()) {
                proveedorID = rsCl.getInt("pr_id");
            }
            rsCl.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return proveedorID;
   }

   /**
    * Metodo para consultar el nombre del proveedor a partir de si id
    * @param proveedorID
    * @return
    */
   public String getProveedor(int proveedorID)
   {
       String proveedor="";

       ResultSet rsCl = CSDesktop.datos.select("SELECT pr_nombre_fiscal FROM pr_proveedores WHERE pr_id = "+proveedorID);
        try {
            while (rsCl.next()) {
                proveedor = rsCl.getString("pr_nombre_fiscal");
            }
            rsCl.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return proveedor;
   }

   /**
    *
    * @param proveedorID
    * @return
    */
   public BeanProveedor getDatosProveedor(int proveedorID) throws SQLException
   {
       BeanProveedor bProveedor = new BeanProveedor();

       ResultSet rsCl = CSDesktop.datos.select("SELECT * FROM pr_proveedores WHERE pr_id = "+proveedorID);
        try
        {
            while (rsCl.next())
            {
                bProveedor.setNombre(rsCl.getString("pr_nombre_fiscal"));
                bProveedor.setDNI_CIF(rsCl.getString("pr_DNI_CIF"));
                bProveedor.setDireccion(rsCl.getString("pr_direccion"));
                bProveedor.setCod_postal(rsCl.getString("pr_cod_postal"));
                bProveedor.setPoblacion(rsCl.getString("pr_poblacion"));
                bProveedor.setProvincia(rsCl.getString("pr_provincia"));
                bProveedor.setDireccion_fiscal(rsCl.getString("pr_direccion_fiscal"));
                bProveedor.setCod_postal_fiscal(rsCl.getString("pr_cod_postal_fiscal"));
                bProveedor.setPoblacion_fiscal(rsCl.getString("pr_poblacion_fiscal"));
                bProveedor.setProvinciaFiscal(rsCl.getString("pr_provincia_fiscal"));
                bProveedor.setPlazoPago(rsCl.getString("pr_plazo"));
                bProveedor.setDiasPlazo(rsCl.getString("pr_dias_plazo"));
                bProveedor.setNumCuenta(rsCl.getString("pr_num_cuenta"));
                bProveedor.setTipo(rsCl.getString("pr_tipo"));
                bProveedor.setEmail(rsCl.getString("pr_email"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        rsCl.close();

       return bProveedor;
   }

   /**
    * Metodo para consultar la url de tarifas del proveedor a partir del id
    * @param proveedorID
    * @return url
    */
   public String getUrlTarifasProveedor(int proveedorID)
   {
       String url="";

       ResultSet rsCl = CSDesktop.datos.select("SELECT pr_tarifa_url FROM pr_proveedores WHERE pr_id = "+proveedorID);
        try {
            while (rsCl.next()) {
                url = rsCl.getString("pr_tarifa_url");
            }
            rsCl.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return url;
   }
}