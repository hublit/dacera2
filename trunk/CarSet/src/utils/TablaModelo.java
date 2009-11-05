/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author depr102
 */
public class TablaModelo extends DefaultTableModel {

   public boolean isCellEditable (int row, int column)
   {
       // Aquí devolvemos true o false según queramos que una celda
       // identificada por fila,columna (row,column), sea o no editable      
       return false;
   }
}