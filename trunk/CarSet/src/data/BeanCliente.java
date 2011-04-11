package data;

/**
 *
 * @author Raul
 *
 */
public class BeanCliente
{
    private String cl_id;
    private String nombre;
    private String DNI_CIF;
    private String direccion;
    private String cod_postal;
    private String poblacion;
    private String provincia;
    private String direccion_fiscal;
    private String cod_postal_fiscal;
    private String poblacion_fiscal;
    private String provincia_fiscal;
    private String estado;
    private String plazoPago;
    private String formaPago;
    private String diasPlazo;
    private int correo;

    /**
     * @return cl_id
     */
    public String getCl_id()
    {
        return cl_id;
    }

    /**
     * @param p_cl_id a establecer
     */
    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    /**
     * @return cod_postal
     */
    public String getCod_postal()
    {
            return cod_postal;
    }

    /**
     * @param p_cod_postal cod_postal a establecer
     */
    public void setCod_postal(String p_cod_postal)
    {
            cod_postal = p_cod_postal;
    }

    /**
     * @return cod_postal_fiscal
     */
    public String getCod_postal_fiscal()
    {
            return cod_postal_fiscal;
    }

    /**
     * @param p_cod_postal_fiscal cod_postal_fiscal a establecer
     */
    public void setCod_postal_fiscal(String p_cod_postal_fiscal)
    {
            cod_postal_fiscal = p_cod_postal_fiscal;
    }

    /**
     * @return direccion
     */
    public String getDireccion()
    {
            return direccion;
    }

    /**
     * @param p_direccion direccion a establecer
     */
    public void setDireccion(String p_direccion)
    {
            direccion = p_direccion;
    }

    /**
     * @return direccion_fiscal
     */
    public String getDireccion_fiscal()
    {
            return direccion_fiscal;
    }

    /**
     * @param p_direccion_fiscal direccion_fiscal a establecer
     */
    public void setDireccion_fiscal(String p_direccion_fiscal)
    {
            direccion_fiscal = p_direccion_fiscal;
    }

    /**
     * @return dNI_CIF
     */
    public String getDNI_CIF()
    {
            return DNI_CIF;
    }

    /**
     * @param p_dni_cif dNI_CIF a establecer
     */
    public void setDNI_CIF(String p_dni_cif)
    {
            DNI_CIF = p_dni_cif;
    }

    /**
     * @return nombre
     */
    public String getNombre()
    {
            return nombre;
    }

    /**
     * @param p_nombre nombre a establecer
     */
    public void setNombre(String p_nombre)
    {
            nombre = p_nombre;
    }

    /**
     * @return poblacion
     */
    public String getPoblacion()
    {
            return poblacion;
    }

    /**
     * @param p_poblacion poblacion a establecer
     */
    public void setPoblacion(String p_poblacion)
    {
            poblacion = p_poblacion;
    }

    /**
     * @return poblacion_fiscal
     */
    public String getPoblacion_fiscal()
    {
            return poblacion_fiscal;
    }

    /**
     * @param p_poblacion_fiscal poblacion_fiscal a establecer
     */
    public void setPoblacion_fiscal(String p_poblacion_fiscal)
    {
            poblacion_fiscal = p_poblacion_fiscal;
    }

    /**
     * @return provincia
     */
    public String getProvincia()
    {
            return provincia;
    }

    /**
     * @param p_provincia provincia a establecer
     */
    public void setProvincia(String p_provincia)
    {
            provincia = p_provincia;
    }

    /**
     * @return provincia fiscal
     */
    public String getProvinciaFiscal()
    {
            return provincia_fiscal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getPlazoPago() {
        return plazoPago;
    }

    public void setPlazoPago(String plazoPago) {
        this.plazoPago = plazoPago;
    }

    /**
     * @param p_provincia provincia a establecer
     */
    public void setProvinciaFiscal(String p_provincia)
    {
            provincia_fiscal = p_provincia;
    }

     public String getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(String diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public int getCorreo() {
        return correo;
    }

    public void setCorreo(int correo) {
        this.correo = correo;
    }

}