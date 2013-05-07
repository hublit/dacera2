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
    
    private double pedidosValidados;
    
    private double pedidosPendientes;
    
    private double pedidosNoFacturados;
    
    private double pedidosTotales;
    
    private double importeFacturas;
    
    private double importeFacturasPagadas;
    
    private double importeFacturasPendientes;
    
    private double importeFacturasCobradasCliente;
    
    private double importeFacturasPendientesCliente;

    public double getImporteFacturas() {
        return importeFacturas;
    }

    public double getImporteFacturasCobradasCliente() {
        return importeFacturasCobradasCliente;
    }

    public void setImporteFacturasCobradasCliente(double importeFacturasCobradasCliente) {
        this.importeFacturasCobradasCliente = importeFacturasCobradasCliente;
    }

    public double getImporteFacturasPendientesCliente() {
        return importeFacturasPendientesCliente;
    }

    public void setImporteFacturasPendientesCliente(double importeFacturasPendientesCliente) {
        this.importeFacturasPendientesCliente = importeFacturasPendientesCliente;
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

    public double getPedidosNoFacturados() {
        return pedidosNoFacturados;
    }

    public void setPedidosNoFacturados(double pedidosNoFacturados) {
        this.pedidosNoFacturados = pedidosNoFacturados;
    }

    public double getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void setPedidosPendientes(double pedidosPendientes) {
        this.pedidosPendientes = pedidosPendientes;
    }

    public double getPedidosValidados() {
        return pedidosValidados;
    }

    public void setPedidosValidados(double pedidosValidados) {
        this.pedidosValidados = pedidosValidados;
    }

    public double getPedidosTotales() {
        return pedidosTotales;
    }

    public void setPedidosTotales(double pedidosTotales) {
        this.pedidosTotales = pedidosTotales;
    }
    
    
    
    
}
