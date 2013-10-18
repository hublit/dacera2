package data;

/**
 *
 * @author Raul
 *
 */
public class BeanPedido
{
    private String cliente;
    private String fecha;
    private String soporte;
    private String matricula;
    private String marca;
    private String modelo;
    private String observacionesCl;
    private String numUnido;


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

}