/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author CESAR
 */
public class BeanAuxInformeTesoreria {
    
    private String nombreProveedor;
    
    private int pedidosValidados;
    
    private int pedidosPendientes;
    
    private int pedidosNoFacturados;
    
    private int pedidosTotales;
    
    private double importeFacturas;
    
    private double importeFacturasPagadas;
    
    private double importeFacturasPendientes;

    public double getImporteFacturas() {
        return importeFacturas;
    }

    public void setImporteFacturas(double importeFacturas) {
        this.importeFacturas = importeFacturas;
    }

    public double getImporteFacturasPagadas() {
        return importeFacturasPagadas;
    }

    public void setImporteFacturasPagadas(double importeFacturasPagadas) {
        this.importeFacturasPagadas = importeFacturasPagadas;
    }

    public double getImporteFacturasPendientes() {
        return importeFacturasPendientes;
    }

    public void setImporteFacturasPendientes(double importeFacturasPendientes) {
        this.importeFacturasPendientes = importeFacturasPendientes;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getPedidosNoFacturados() {
        return pedidosNoFacturados;
    }

    public void setPedidosNoFacturados(int pedidosNoFacturados) {
        this.pedidosNoFacturados = pedidosNoFacturados;
    }

    public int getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void setPedidosPendientes(int pedidosPendientes) {
        this.pedidosPendientes = pedidosPendientes;
    }

    public int getPedidosValidados() {
        return pedidosValidados;
    }

    public void setPedidosValidados(int pedidosValidados) {
        this.pedidosValidados = pedidosValidados;
    }

    public int getPedidosTotales() {
        return pedidosTotales;
    }

    public void setPedidosTotales(int pedidosTotales) {
        this.pedidosTotales = pedidosTotales;
    }
    
    
    
    
}
