/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lito
 */
public class Proveedor
{

   DbConnection cn;

   /** Constructor de DbUser */
   public Proveedor()
   {
      cn = new DbConnection();
   }


   /**
    * Método que trae todos los proveedores
    */
   public Object[][] getProveedores()
   {
      int registros = 0;
      try
      {

         ResultSet rsTotal = cn.select("SELECT count(1) as cont FROM pr_proveedores ");

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
         ResultSet rs = cn.select("SELECT pr_id, pr_nombre_fiscal FROM pr_proveedores ORDER BY pr_id ");

         int i = 0;
         while(rs.next())
         {
            String estCodigo = rs.getString("pr_id");
            String estNombre = rs.getString("pr_nombre_fiscal");

            data[i][0] = estCodigo;
            data[i][1] = estNombre;
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
         ResultSet rsCl = cn.select("SELECT pr_id, pr_nombre_fiscal " +
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

   public int getProveedorID(String proveedor)
   {
       int proveedorID=0;

       ResultSet rsCl = cn.select("SELECT pr_id FROM pr_proveedores WHERE pr_nombre_fiscal = '"+proveedor+"'");
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

   public String getProveedor(int proveedorID)
   {
       String proveedor="";

       ResultSet rsCl = cn.select("SELECT pr_nombre_fiscal FROM pr_proveedores WHERE pr_id = "+proveedorID);
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

   public BeanProveedor getDatosFacturaProveedor(int proveedorID)
   {
       BeanProveedor bProveedor = new BeanProveedor();

       ResultSet rsCl = cn.select("SELECT pr_id, pr_nombre_fiscal, pr_DNI_CIF, pr_direccion, pr_cod_postal, " +
                                  "pr_poblacion, pr_provincia, pr_direccion_fiscal, pr_cod_postal_fiscal, " +
                                  "pr_poblacion_fiscal, pr_provincia_fiscal " +
                                  "FROM pr_proveedores WHERE pr_id = "+proveedorID);
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
            }
            rsCl.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return bProveedor;
   }
}