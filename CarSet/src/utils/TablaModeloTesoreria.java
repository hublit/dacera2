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
public class TablaModeloTesoreria extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
    {
        if (column == 2 ||column == 11 ||column == 12 ||column == 13 || column == 14|| column == 15|| column == 16 || column == 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}