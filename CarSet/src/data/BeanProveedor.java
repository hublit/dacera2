package data;

/**
 *
 * @author Raul
 *
 */
public class BeanProveedor
{
    private String pr_id;
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
    private String plazoPago;
    private String tipo;
    private String numCuenta;
    private String email;
    private String regimen;
    private String UrlTarifas;

    public String getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(String diasPlazo) {
        this.diasPlazo = diasPlazo;
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

    public String getProvincia_fiscal() {
        return provincia_fiscal;
    }

    public void setProvincia_fiscal(String provincia_fiscal) {
        this.provincia_fiscal = provincia_fiscal;
    }
    private String formaPago;
    private String diasPlazo;
    /**
     * @return cl_id
     */
    public String getPr_id()
    {
        return pr_id;
    }

    /**
     * @param p_cl_id a establecer
     */
    public void setPr_id(String pr_id) {
        this.pr_id = pr_id;
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

    /**
     * @param p_provincia provincia a establecer
     */
    public void setProvinciaFiscal(String p_provincia)
    {
            provincia_fiscal = p_provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getUrlTarifas() {
        return UrlTarifas;
    }

    public void setUrlTarifas(String UrlTarifas) {
        this.UrlTarifas = UrlTarifas;
    }
}