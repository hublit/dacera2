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
        if (column == 3 || column == 9 || column == 14 || column == 18 || column == 19 || column == 28 || column == 29|| column == 32 || column == 33 || column == 34)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}