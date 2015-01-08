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
public class TablaValidarArchivo extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
    {
        if (column == 0 ||column == 1 ||column == 2 ||column == 13 || column == 14|| column == 15|| column == 16 || column == 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}