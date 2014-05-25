package data;

/**
 *
 * @author Raul
 *
 */
public class BeanPedido
{
    private String num;
    private String cliente;
    private String fecha;
    private String soporte;
    private String matricula;
    private String marca;
    private String modelo;
    private String observacionesCl;
    private String numUnido;
    private String fechaOrigen;
    private String direccionOrigen;
    private String poblacionOrigen;
    private String provinciaOrigen;
    private String nombreOrigen;
    private String telefonoOrigen;
    private String tarifa;
    private String kms;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return cliente
     */
    public String getCliente()
    {
        return cliente;
    }

    /**
     * @param cliente a establecer
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getNumUnido() {
        return numUnido;
    }

    public void setNumUnido(String numUnido) {
        this.numUnido = numUnido;
    }

    public String getObservacionesCl() {
        return observacionesCl;
    }

    public void setObservacionesCl(String observacionesCl) {
        this.observacionesCl = observacionesCl;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public String getFechaOrigen() {
        return fechaOrigen;
    }

    public void setFechaOrigen(String fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getPoblacionOrigen() {
        return poblacionOrigen;
    }

    public void setPoblacionOrigen(String poblacionOrigen) {
        this.poblacionOrigen = poblacionOrigen;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
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

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

}