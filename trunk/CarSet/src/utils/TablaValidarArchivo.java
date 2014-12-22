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
        return true;
    }
}