/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author Raul
 */
public class BeanAuxInformeTesoreriaCliente {
    
    private String nombreCliente;
    
    private double importeFechaVencida;
    
    private double importePendienteVencer;
    
    private double importeTotalPendientesCobro;
    
    private double importeFacturasCobradas;
    
    private double importeFacturasIncobrables;
    
    private double importeFacturasAplazadas;

    private Date fecha;

    private String email;

    private String contacto;

    private String observaciones;
    
    private String formaPago;


    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getImporteFacturasAplazadas() {
        return importeFacturasAplazadas;
    }

    public void setImporteFacturasAplazadas(double importeFacturasAplazadas) {
        this.importeFacturasAplazadas = importeFacturasAplazadas;
    }

    public double getImporteFacturasCobradas() {
        return importeFacturasCobradas;
    }

    public void setImporteFacturasCobradas(double importeFacturasCobradas) {
        this.importeFacturasCobradas = importeFacturasCobradas;
    }

    public double getImporteFacturasIncobrables() {
        return importeFacturasIncobrables;
    }

    public void setImporteFacturasIncobrables(double importeFacturasIncobrables) {
        this.importeFacturasIncobrables = importeFacturasIncobrables;
    }

    public double getImporteFechaVencida() {
        return importeFechaVencida;
    }

    public void setImporteFechaVencida(double importeFechaVencida) {
        this.importeFechaVencida = importeFechaVencida;
    }

    public double getImportePendienteVencer() {
        return importePendienteVencer;
    }

    public void setImportePendienteVencer(double importePendienteVencer) {
        this.importePendienteVencer = importePendienteVencer;
    }

    public double getImporteTotalPendientesCobro() {
        return importeTotalPendientesCobro;
    }

    public void setImporteTotalPendientesCobro(double importeTotalPendientesCobro) {
        this.importeTotalPendientesCobro = importeTotalPendientesCobro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}