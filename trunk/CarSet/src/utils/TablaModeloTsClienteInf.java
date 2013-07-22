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
public class TablaModeloTsClienteInf extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
    {
        if (column == 7 ||column == 8 || column == 9 || column == 10|| column == 11)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}