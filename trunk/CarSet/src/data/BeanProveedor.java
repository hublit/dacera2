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
}