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
public class Cliente
{

   DbConnection cn;

   /** Constructor de DbUser */
   public Cliente()
   {
      cn = new DbConnection();
   }


   /**
    * Método que trae todos los clientes
    */
   public Object[][] getClientes()
   {
      int registros = 0;
      try
      {

         ResultSet rsTotal = cn.select("SELECT count(1) as cont FROM cl_clientes ");

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
         ResultSet rs = cn.select("SELECT cl_id, cl_nombre  FROM cl_clientes ORDER BY cl_id ");

         int i = 0;
         while(rs.next())
         {
            String estCodigo = rs.getString("cl_id");
            String estNombre = rs.getString("cl_nombre");

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
    * Método que comprueba si existe un cliente por el nombre
    * @param nombre
    * @return boolean cliente
    */
   public boolean isCliente(String nombre)
   {
      boolean cliente = false;
      String estCodigo = "";

      try
      {
         ResultSet rsCl = cn.select("SELECT cl_id, cl_nombre " +
                                    "FROM cl_clientes " +
                                    "WHERE cl_nombre = '"+nombre+"'");

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
       int clienteID=0;

       ResultSet rsCl = cn.select("SELECT cl_id FROM cl_clientes WHERE cl_nombre = '"+cliente+"'");
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

       ResultSet rsCl = cn.select("SELECT cl_nombre FROM cl_clientes WHERE cl_id = "+clienteID);
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

}