/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author depr102
 */
public class BeanCorreoCliente {

    private String cliente;
    private String fecha;
    private String numPedido;
    private String soporte;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }


}
