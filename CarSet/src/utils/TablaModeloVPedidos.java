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
public class TablaModeloVPedidos extends DefaultTableModel {

    public boolean isCellEditable (int row, int column)
    {
        if (column == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}