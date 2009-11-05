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
    * Método que comprueba si existe un proveedor por el nombre
    * @param nombre
    * @return boolean cliente
    */
   public boolean isProveedor(String nombre)
   {
      boolean proveedor = false;
      String estCodigo = "";

      try
      {
         ResultSet rsCl = cn.select("SELECT pr_id, pr_nombre_fiscal " +
                                    "FROM pr_proveedor " +
                                    "WHERE pr_nombre_fiscal = '"+nombre+"'");

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
}