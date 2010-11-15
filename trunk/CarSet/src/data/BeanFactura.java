/*/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author depr102
 */
public class BeanFactura {

    private Long numPedido;
     private String fecha;
    private String provinciaOrigen;
    private String provinciaDestino;
    private String servicio;
    private String servicioOrigen;
    private String servicioDestino;
    private String servicioEspecial;
    private String diasCampa;
    private String idaVuelta;
    private String factor;
    private String soporte;
    private String matricula;
    private String marca;
    private String modelo;
    private String tarifaEsCliente;
    private String tarifaEsProveedor;
    private String servicioSuplemento;
    private String numCamion;
    private String suplemento;
    private String descripcion;
    private String tarifa;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_prevista_entrega() {
        return fecha_prevista_entrega;
    }

    public void setFecha_prevista_entrega(String fecha_prevista_entrega) {
        this.fecha_prevista_entrega = fecha_prevista_entrega;
    }

    public String getFecha_prevista_recogida() {
        return fecha_prevista_recogida;
    }

    public void setFecha_prevista_recogida(String fecha_prevista_recogida) {
        this.fecha_prevista_recogida = fecha_prevista_recogida;
    }

    public String getFecha_real_entrega() {
        return fecha_real_entrega;
    }

    public void setFecha_real_entrega(String fecha_real_entrega) {
        this.fecha_real_entrega = fecha_real_entrega;
    }
    private String vehiculo;
    private String estado;
    private String fecha_prevista_entrega;
    private String fecha_prevista_recogida;
    private String fecha_real_entrega;

    public String getTarifa() {
        return tarifa;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDiasCampa(String diasCampa) {
        this.diasCampa = diasCampa;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdaVuelta(String idaVuelta) {
        this.idaVuelta = idaVuelta;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNumPedido(Long numPedido) {
        this.numPedido = numPedido;
    }

    public void setProvinciaDestino(String provinciaDestino) {
        this.provinciaDestino = provinciaDestino;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setServicioDestino(String servicioDestino) {
        this.servicioDestino = servicioDestino;
    }

    public void setServicioEspecial(String servicioEspecial) {
        this.servicioEspecial = servicioEspecial;
    }

    public void setServicioOrigen(String servicioOrigen) {
        this.servicioOrigen = servicioOrigen;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public void setSuplemento(String suplemento) {
        this.suplemento = suplemento;
    }

    public void setTarifaEsCliente(String tarifaEsCliente) {
        this.tarifaEsCliente = tarifaEsCliente;
    }

    public void setTarifaEsProveedor(String tarifaEsProveedor) {
        this.tarifaEsProveedor = tarifaEsProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDiasCampa() {
        return diasCampa;
    }

    public String getFactor() {
        return factor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getIdaVuelta() {
        return idaVuelta;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public Long getNumPedido() {
        return numPedido;
    }

    public String getProvinciaDestino() {
        return provinciaDestino;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public String getServicio() {
        return servicio;
    }

    public String getServicioDestino() {
        return servicioDestino;
    }

    public String getServicioEspecial() {
        return servicioEspecial;
    }

    public String getServicioOrigen() {
        return servicioOrigen;
    }

    public String getSoporte() {
        return soporte;
    }

    public String getServicioSuplemento() {
        return servicioSuplemento;
    }

    public void setServicioSuplemento(String servicioSuplemento) {
        this.servicioSuplemento = servicioSuplemento;
    }

    public String getSuplemento() {
        return suplemento;
    }

    public String getTarifaEsCliente() {
        return tarifaEsCliente;
    }

     public String getTarifaEsProveedor() {
        return tarifaEsProveedor;
    }

     public String getNumCamion() {
        return numCamion;
    }

    public void setNumCamion(String numCamion) {
        this.numCamion = numCamion;
    }


}
