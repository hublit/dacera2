package data;

/**
 *
 * @author Raul
 *
 */
public class BeanPedidoAux
{
    private String num;
    private String fecha;
    private String direccionOrigen;
    private String poblacionOrigen;
    private String provinciaOrigen;
    private String cpOrigen;
    private String nombreOrigen;
    private String telefonoOrigen;
    private String fechaOrigen;
    private String direccionDestino;
    private String poblacionDestino;
    private String provinciaDestino;
    private String cpDestino;
    private String nombreDestino;
    private String telefonoDestino;
    private String fechaDestino;    
    private Integer factor;
    private String estado_vehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private String soporte;
    private String servicio;
    private String estado;
    private String kms;
    private Integer numEnCamion;
    private Integer diasCampa;
    private String descripcion;
    private String obsCarset;
    private String obsGeneral;
    private String numUnido;
    private Double tarifaCl;
    private Double tarifaPr;
    private Boolean obClMail;
    private Boolean obPrMail;
    private Integer peNumUnido;
    private Boolean peFinUnido;
    private Integer cliente;
    private Integer proveedor;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return cliente
     */
    public Integer getCliente()
    {
        return cliente;
    }

    /**
     * @param cliente a establecer
     */
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    /**
     * @return proveedor
     */
    public Integer getProveedor()
    {
        return proveedor;
    }

    /**
     * @param proveedor a establecer
     */
    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiasCampa() {
        return diasCampa;
    }

    public void setDiasCampa(Integer diasCampa) {
        this.diasCampa = diasCampa;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_vehiculo() {
        return estado_vehiculo;
    }

    public void setEstado_vehiculo(String estado_vehiculo) {
        this.estado_vehiculo = estado_vehiculo;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaDestino() {
        return fechaDestino;
    }

    public void setFechaDestino(String fechaDestino) {
        this.fechaDestino = fechaDestino;
    }

    public String getFechaOrigen() {
        return fechaOrigen;
    }

    public void setFechaOrigen(String fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
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

    public Integer getNumEnCamion() {
        return numEnCamion;
    }

    public void setNumEnCamion(Integer numEnCamion) {
        this.numEnCamion = numEnCamion;
    }

    public String getNumUnido() {
        return numUnido;
    }

    public void setNumUnido(String numUnido) {
        this.numUnido = numUnido;
    }

    public Boolean isObClMail() {
        return obClMail;
    }

    public void setObClMail(Boolean obClMail) {
        this.obClMail = obClMail;
    }

    public Boolean isObPrMail() {
        return obPrMail;
    }

    public void setObPrMail(Boolean obPrMail) {
        this.obPrMail = obPrMail;
    }

    public String getObsCarset() {
        return obsCarset;
    }

    public void setObsCarset(String obsCarset) {
        this.obsCarset = obsCarset;
    }

    public String getObsGeneral() {
        return obsGeneral;
    }

    public void setObsGeneral(String obsGeneral) {
        this.obsGeneral = obsGeneral;
    }

    public Boolean isPeFinUnido() {
        return peFinUnido;
    }

    public void setPeFinUnido(Boolean peFinUnido) {
        this.peFinUnido = peFinUnido;
    }

    public Integer getPeNumUnido() {
        return peNumUnido;
    }

    public void setPeNumUnido(Integer peNumUnido) {
        this.peNumUnido = peNumUnido;
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

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public Double getTarifaCl() {
        return tarifaCl;
    }

    public void setTarifaCl(Double tarifaCl) {
        this.tarifaCl = tarifaCl;
    }

    public Double getTarifaPr() {
        return tarifaPr;
    }

    public void setTarifaPr(Double tarifaPr) {
        this.tarifaPr = tarifaPr;
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

    public String getCpDestino() {
        return cpDestino;
    }

    public void setCpDestino(String cpDestino) {
        this.cpDestino = cpDestino;
    }

    public String getCpOrigen() {
        return cpOrigen;
    }

    public void setCpOrigen(String cpOrigen) {
        this.cpOrigen = cpOrigen;
    }
    
}