/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author depr102
 */
public class TablaModeloPedidos extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
   {
       // Aquí devolvemos true o false según queramos que una celda
       // identificada por fila,columna (row,column), sea o no editable
       return false;
   }

   public Class getColumnClass(int columnIndex){

          if(columnIndex == 4){
             return Boolean.class;
          }
             return Object.class;
                }



}
