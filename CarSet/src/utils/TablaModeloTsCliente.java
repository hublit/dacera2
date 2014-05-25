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
public class TablaModeloTsCliente extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
    {
        if (column == 9 || column == 10 || column == 11 || column == 12|| column == 13)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}