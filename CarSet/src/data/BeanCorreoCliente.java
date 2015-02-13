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
    private String fechaRecogida;
    private String fechaEntrega;
    private String fechaRealEntrega;
    private String marca;
    private String modelo;
    private String matricula;
    private String direccionOrigen;
    private String poblacionOrigen;
    private String provinciaOrigen;
    private String nombreOrigen;
    private String telefonoOrigen;
    private String direccionDestino;
    private String poblacionDestino;
    private String provinciaDestino;
    private String nombreDestino;
    private String telefonoDestino;
    private String tarifa;
    private String servicioEspecial;
    private String diasCampa="";
    private String idaVuelta;
    private String factorCorrecccion;
    private String tarifaEspecialCliente;
    private String tarifaEspecialProveedor;
    private String cifProveedor;
    private String numeroEnCamion;
    private String entradaCampa="";
    private String campa="";
    private String descripcion;
    private String numero;
    private String clienteID;
    private String suplemento;
    private String observaciones;
    private String veEstado;
    private String kms;
    private String tarifakmCliente;
    private boolean obsClInmail;
    private boolean obsPrInmail;
    private boolean peUnido;

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

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(String fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getPoblacionDestino() {
        return poblacionDestino;
    }

    public void setPoblacionDestino(String poblacionDestino) {
        this.poblacionDestino = poblacionDestino;
    }

    public String getPoblacionOrigen() {
        return poblacionOrigen;
    }

    public void setPoblacionOrigen(String poblacionOrigen) {
        this.poblacionOrigen = poblacionOrigen;
    }

    public String getProvinciaDestino() {
        return provinciaDestino;
    }

    public void setProvinciaDestino(String provinciaDestino) {
        this.provinciaDestino = provinciaDestino;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    public String getTelefonoDestino() {
        return telefonoDestino;
    }

    public void setTelefonoDestino(String telefonoDestino) {
        this.telefonoDestino = telefonoDestino;
    }

    public String getTelefonoOrigen() {
        return telefonoOrigen;
    }

    public void setTelefonoOrigen(String telefonoOrigen) {
        this.telefonoOrigen = telefonoOrigen;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getCampa() {
        return campa;
    }

    public void setCampa(String campa) {
        this.campa = campa;
    }

    public String getDiasCampa() {
        return diasCampa;
    }

    public void setDiasCampa(String diasCampa) {
        this.diasCampa = diasCampa;
    }

    public String getEntradaCampa() {
        return entradaCampa;
    }

    public void setEntradaCampa(String entradaCampa) {
        this.entradaCampa = entradaCampa;
    }

    public String getFactorCorrecccion() {
        return factorCorrecccion;
    }

    public void setFactorCorrecccion(String factorCorrecccion) {
        this.factorCorrecccion = factorCorrecccion;
    }

    public String getIdaVuelta() {
        return idaVuelta;
    }

    public void setIdaVuelta(String idaVuelta) {
        this.idaVuelta = idaVuelta;
    }

    public String getNumeroEnCamion() {
        return numeroEnCamion;
    }

    public void setNumeroEnCamion(String numeroEnCamion) {
        this.numeroEnCamion = numeroEnCamion;
    }

    public String getServicioEspecial() {
        return servicioEspecial;
    }

    public void setServicioEspecial(String servicioEspecial) {
        this.servicioEspecial = servicioEspecial;
    }

    public String getTarifaEspecialCliente() {
        return tarifaEspecialCliente;
    }

    public void setTarifaEspecialCliente(String tarifaEspecialCliente) {
        this.tarifaEspecialCliente = tarifaEspecialCliente;
    }

    public String getTarifaEspecialProveedor() {
        return tarifaEspecialProveedor;
    }

    public void setTarifaEspecialProveedor(String tarifaEspecialProveedor) {
        this.tarifaEspecialProveedor = tarifaEspecialProveedor;
    }

    public String getCifProveedor() {
        return cifProveedor;
    }

    public void setCifProveedor(String cifProveedor) {
        this.cifProveedor = cifProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getSuplemento() {
        return suplemento;
    }

    public void setSuplemento(String suplemento) {
        this.suplemento = suplemento;
    }
      public String getFechaRealEntrega() {
        return fechaRealEntrega;
    }

    public void setFechaRealEntrega(String fechaRealEntrega) {
        this.fechaRealEntrega = fechaRealEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getVeEstado() {
        return veEstado;
    }

    public void setVeEstado(String ve_estado) {
        this.veEstado = ve_estado;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public boolean isObsClInmail() {
        return obsClInmail;
    }

    public void setObsClInmail(boolean obsClInmail) {
        this.obsClInmail = obsClInmail;
    }

    public boolean isObsPrInmail() {
        return obsPrInmail;
    }

    public void setObsPrInmail(boolean obsPrInmail) {
        this.obsPrInmail = obsPrInmail;
    }

    public String getTarifakmCliente() {
        return tarifakmCliente;
    }

    public void setTarifakmCliente(String tarifakmCliente) {
        this.tarifakmCliente = tarifakmCliente;
    }

    public boolean isPeUnido() {
        return peUnido;
    }

    public void setPeUnido(boolean peUnido) {
        this.peUnido = peUnido;
    }
}
