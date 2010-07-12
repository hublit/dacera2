/*
*
*/
package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import neg.CSDesktop;

/**
 * @author lito 
 * Funciones comunes para la validación de campos
 */
public class Utilidades
{
    private static String mensaje;

    /**
     * Función para validar el tamaño de un campo
     * @param campo
     * @param tamano
     * @return String mensaje
     */
    public static String tamanoConcreto(String campo,String tamano)
    {
        if (tamano.equals("" + campo.length()))
        {
            mensaje = "OK";
        }
        mensaje = "Error en la longitud del campo "+campo;

	return mensaje;
    }

    
    /**
     * Función para validar el formato del año
     * @param campo
     * @return String mensaje
     */
    public static String formatoAnno(String campo)
    {
        String validando = formatoNumerico(campo);

        if (validando.equals("OK"))
        {
            int anyo = Integer.parseInt(campo.trim());
            if (anyo > 1900 && anyo < 2099)
            {
                mensaje = "OK";
            }
            mensaje = "Error rendo año";
        }
        else
        {
            mensaje = "Error debe ser un número";
        }
        return mensaje;
    }


    /**
     * @param numero - cadena de caracteres numericos a validar
     * @return Mensaje de error
     * @since Comprueba que en la cadena solo hay numeros
     */
    public static String formatoNumerico(String numero)
    {
        if ("".equals(numero) || numero == null)
        {
            mensaje = "OK";
        }
        else
        {
            // Declaracion de la constante
            final String NUMEROS = "0123456789";
            // Declaramos un Array de carcteres y lo rellenamos con el numero a validar
            char[] arrayNumerico = numero.toCharArray();
            int contador = 0;

            // Se recorre el array y comprovamos que sus elementos sean numeros
            for (int i = 0; i < arrayNumerico.length; i++)
            {
                // System.out.println(arrayNumerico[i]);
                if (NUMEROS.indexOf(arrayNumerico[i]) >= 0)
                {
                    contador++;
                }
                else
                {
                    mensaje = "Error debe ser un número";
                }
            }
            if (contador == arrayNumerico.length)
            {
                mensaje = "OK";
            }
            else
            {
                mensaje = "Error debe ser un número";
            }
        }
        return mensaje;
    }


    /**
    * @param numero - cadena de caracteres numericos a validar
    * @return Mensaje de error
    * @since Comprueba que en la cadena solo haya un valor numerico mayor o igual que cero
    */
    public static String formatoNumericoMayorCero(String numero)
    {
        if ("".equals(numero) || numero == null)
        {
            mensaje = "OK";
        }
        else
        {
            try {
                if (Integer.parseInt(numero) >= 0)
                {
                    mensaje = "OK";
                }
                else
                {
                    mensaje = "Error debe ser un número";
                }
            } catch (NumberFormatException nfe) {
                mensaje = "Error debe ser un número";
            }
        }
        return mensaje;
    }
	
	
    /**
    * @param telefono
    * @return Mensaje de error
    * @since Valida que un numero de telefono sea solo numeros y admite que tenga un simbolo '+'
    */
    public static String formatoTelefonoConUnMas(String telefono)
    {
        if ("".equals(telefono) || telefono == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (telefono.indexOf("+") == 0)
            {
                if (formatoNumerico(telefono.substring(1)).equals("OK"))
                {
                    mensaje = "OK";
                }
                else
                {
                    mensaje = "Error en el formato del campo teléfono con un +";
                }
            }
            else if (telefono.indexOf("+") < 0 && telefono.length() < 12)
            {
                if (formatoNumerico(telefono).equals("OK"))
                {
                    mensaje = "OK";
                }
                else
                {
                    mensaje = "Error en el formato del campo teléfono con un +";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo teléfono con un +";
            }
        }
        return mensaje;
    }


    /**
     * @since Valida que un numero de telefono tenga 9 dígitos
     * @param telefono
     * @return String mensaje
     */
    public static String formatoTelefono9(String telefono)
    {
        if ("".equals(telefono) || telefono == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (telefono.length() == 9)
            {
                if (formatoNumerico(telefono).equals("OK"))
                {
                    if (telefono.substring(0, 1).equals("9") ||
                        telefono.substring(0, 1).equals("8") ||
                        telefono.substring(0, 1).equals("6"))
                    {
                        mensaje = "OK";
                    }
                    else
                    {
                    mensaje = "Error en el formato del campo teléfono";
                    }
                }
                else
                {
                    mensaje = "Error en el formato del campo teléfono";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo teléfono";
            }
        }
        return mensaje;
    }


    /**
     * @since Valida el campo del teléfono fijo
     * @param telefono
     * @return String mensaje
     */
    public static String formatoTelefonoFijo(String telefono)
    {
        if ("".equals(telefono) || telefono == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (telefono.length() == 9)
            {
                if (formatoNumerico(telefono).equals("OK"))
                {
                    if (telefono.substring(0, 1).equals("9") || telefono.substring(0, 1).equals("8"))
                    {
                        mensaje = "OK";
                    }
                    else
                    {
                        mensaje = "Error en el formato del campo teléfono";
                    }
                }
                else
                {
                    mensaje = "Error en el formato del campo teléfono";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo teléfono";
            }
        }
        return mensaje;
    }

     public static String formatoFax(String fax)
    {
        if ("".equals(fax) || fax == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (fax.length() == 9)
            {
                if (formatoNumerico(fax).equals("OK"))
                {
                    if (fax.substring(0, 1).equals("9") || fax.substring(0, 1).equals("8"))
                    {
                        mensaje = "OK";
                    }
                    else
                    {
                        mensaje = "Error en el formato del campo fax";
                    }
                }
                else
                {
                    mensaje = "Error en el formato del campo fax";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo fax";
            }
        }
        return mensaje;
    }


    /**
     * @since Valida el campo del teléfono móvil
     * @param telefono
     * @return String mensaje
     */
    public static String formatoTelefonoMovil(String telefono)
    {
        if ("".equals(telefono) || telefono == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (telefono.length() == 9)
            {
                if (formatoNumerico(telefono).equals("OK"))
                {
                    if (telefono.substring(0, 1).equals("6"))
                    {
                        mensaje = "OK";
                    }
                    else
                    {
                        mensaje = "Error en el formato del campo teléfono móvil";
                    }
                }
                else
                {
                    mensaje = "Error en el formato del campo teléfono móvil";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo teléfono móvil";
            }
        }
        return mensaje;
    }


    /**
     * @since Valida el campo del código postal
     * @param CodPostal
     * @return String mensaje
     */
    public static String formatoCodPostal(String CodPostal)
    {
        if ("".equals(CodPostal) || CodPostal == null)
        {
            mensaje = "OK";
        }
        else
        {
            if (CodPostal.length() == 5)
            {
                if (formatoNumerico(CodPostal).equals("OK"))
                {
                    mensaje = "OK";
                }
                else
                {
                    mensaje = "Error en el formato del campo código postal";
                }
            }
            else
            {
                mensaje = "Error en el formato del campo código postal";
            }
        }
        return mensaje;
    }


    /**
     * @since Rellena una cadena con ceros
     * @param cadena
     * @param longitud
     * @return String cadena
     */
    public static String rellenarCeros(String cadena, int longitud)
    {
        String ceros = "";
        if (cadena != null)
        {
            if (cadena.length() == longitud)
            {
                return cadena;
            }
            else
            {
                for (int i = cadena.length(); i < longitud; i++)
                {
                    ceros = "0" + ceros;
                }
                cadena = ceros + cadena;
            }
        }
        else
        {
            cadena = "";
        }
        return cadena;
    }


    /**
    * Valida una dirección de correo electronico
    *
    * @param correo String con la direccion de correo electronico a validar
    * @return bcorrecto booleano que indica si la direccion es correcta o no.
    * @since Comprobamos que en la direccion introducida se cumplen las normas de validacion establecidas:
    *        1. Longitud del array mayor que 5
    *        2. Que no tenga espacios en blanco
    *        3. Que tenga @ y punto
    *        4. Que tenga @ antes que . final
    *        5. Que empiece por letra
    *        6. Que despues de . no lleve numeros
    *        7. Que todos sean caracter valido Dividimos la dirección en: parte1@parte2.parte3
    */
    public static String formatoCorreo(String correo)
    {
        boolean bcorrecto = false;
        char caux;
        String sDire = null;
        String sparte1 = null;
        String sparte2 = null;
        String sparte3 = null;
        String sinicio = null;
        String saux = null;
        int itam = 0;
        int inumArrobas = 0;
        int inumPuntos = 0;
        int iposArroba = -1;
        int iposPunto = -1;
        int inumEspacios = 0;
        String[] saCorreo = null;
        
        try
        {
            if ("".equals(correo) || correo == null)
            {
                return mensaje = "OK";
            }
            sDire = correo.toLowerCase();
            itam = sDire.length();
            if (itam < 5)
            {
                return mensaje = "ERROR el campo email debe tener más de 5 letras o dígitos";
            }

            // guardamos el correo en un array de string
            saCorreo = new String[itam];
            for (int i = 0; i < itam; i++)
            {
                caux = sDire.charAt(i);
                saux = "" + caux;
                saCorreo[i] = saux;
            }

            // Vemos el numero de espacios, arrobas y puntos
            for (int i = 0; i < itam; i++)
            {
                if (saCorreo[i].equals(" "))
                {
                    inumEspacios = inumEspacios + 1;
                }
                if (saCorreo[i].equals("@"))
                {
                    inumArrobas = inumArrobas + 1;
                    iposArroba = sDire.lastIndexOf("@");
                }
                if (saCorreo[i].equals("."))
                {
                    inumPuntos = inumPuntos + 1;
                    iposPunto = sDire.lastIndexOf(".");
                }
            }

            if (inumEspacios > 0 || inumArrobas <= 0 || inumArrobas > 1 || inumPuntos <= 0)
            {
                return mensaje = "ERROR en el formato del campo email";
            }
            // Recorremos todos los caracteres del correo
            if (iposArroba >= iposPunto)
            {
                return mensaje = "ERROR en el formato del campo email";
            }

            /*sinicio = saCorreo[0];
            // Compruebo que no empiece por numero ni por simbolo --> no letras
            for (int a = 0; a < ITFConstantesEre.saNoLetras.length; a++)
            {
                if (sinicio.equals(ITFConstantesEre.saNoLetras[a]))
                {
                    return ITFConstantesEre.ERROR_ERROR_VALIDACION_FORMATO_CORREO;
                }

                // Que depues de . no lleve numeros ni simbolos
                for (int e = iposPunto + 1; e < sDire.length(); e++)
                {
                    saux = saCorreo[e];
                    if (saux.equals(ITFConstantesEre.saNoLetras[a]))
                    {
                        return ITFConstantesEre.ERROR_ERROR_VALIDACION_FORMATO_CORREO;
                    }
                }
            }

            for (int u = 0; u < sDire.length(); u++)
            {
                bcorrecto = false;
                // verifico que toda la dire no lleve caracteres ilegales
                for (int o = 0; o < ITFConstantesEre.saTodosCaracteres.length; o++)
                {
                    saux = saCorreo[u];
                    if (saux.equals(ITFConstantesEre.saTodosCaracteres[o]))
                    {
                        bcorrecto = true;
                    }
                }
                if (!bcorrecto)
                {
                    return ITFConstantesEre.ERROR_ERROR_VALIDACION_FORMATO_CORREO;
                }
            }*/
        return mensaje;
        } 
        catch (Exception e)
        {
            return mensaje = "ERROR en el formato del campo email";
        }
    }


    /**
    * @param fecha
    * @return String mensaje
    * @since Valida que el formato fecha sea dd/mm/aaaa
    */
    public static  String formatoFecha(Date fecha)
    {
        // System.err.println("----------------- FECHA 1: " + fecha.toString());
        if ("".equals(fecha) || fecha == null)
        {
            mensaje = "OK";
        }
        else
        {
            try
            {
                Date date = fecha;
                mensaje = "OK";
            }
            catch (Exception e)
            {
                // System.err.println(" ------------------FECHA ERROR: " );
                mensaje = "ERROR en el formato fecha";
            }
        }
        return mensaje;
    }


    /**
     * @since Valida que el formato fecha sea dd/mm/aaaa
     * @param fecha
     * @return
     */
    public static  String formatoFechaString(String fecha)
    {
        // System.err.println(" ------------------FECHA STRING : " + fecha);
        if ("".equals(fecha) || fecha == null)
        {
            mensaje = "OK";
        }
        else
        {
            String sAnno = fecha.substring(fecha.lastIndexOf("/") + 1);
        // System.err.println(" ------------------A�O STRING : " + sAnno);
            try
            {
                Integer.parseInt(sAnno);
            }
            catch (NumberFormatException nfe)
            {
                mensaje = "ERROR en el formato fecha";
            }
            String formato = "dd/MM/yyyy";
            SimpleDateFormat SDFformato = new SimpleDateFormat(formato);
            SDFformato.setLenient(false);
            try
            {
                Date date = SDFformato.parse(fecha.toString());
                // System.err.println(" ------------------FECHA STRING : " +

                mensaje = "OK";
            }
            catch (Exception e)
            {
                mensaje = "ERROR en el formato fecha";
            }
        }
        return mensaje;
    }


    /**
     * @since Genera un String a partir de una fecha
     * @param oFecha
     * @return String fechaGeneracionNuevo
     */
    public static String generarStringFecha(Date oFecha)
    {
        if ("".equals(oFecha) || oFecha == null)
        {
            return "OK";
        }
        else
        {
            int diaAuxNuevo = 0;
            int mesAuxNuevo = 0;
            int anioAuxNuevo = 0;
            String diaNuevo = "";
            String mesNuevo = "";
            String anioNuevo = "";
            String fechaGeneracionNuevo = "";
            Calendar calendarFechaNuevo = null;
            if (oFecha == null)
            {
                return "";
            }
            calendarFechaNuevo = Calendar.getInstance();
            calendarFechaNuevo.setTime(oFecha);
            diaAuxNuevo = calendarFechaNuevo.get(Calendar.DAY_OF_MONTH);
            if (calendarFechaNuevo.get(Calendar.DAY_OF_MONTH) < 10)
            {
                diaNuevo = "0" + Integer.toString(diaAuxNuevo);
            }
            else
            {
                diaNuevo = Integer.toString(diaAuxNuevo);
            }
            mesAuxNuevo = calendarFechaNuevo.get(Calendar.MONTH) + 1;
            if (calendarFechaNuevo.get(Calendar.MONTH) + 1 < 10)
            {
                mesNuevo = "0" + Integer.toString(mesAuxNuevo);
            }
            else
            {
                mesNuevo = Integer.toString(mesAuxNuevo);
            }
            anioAuxNuevo = calendarFechaNuevo.get(Calendar.YEAR);
            anioNuevo = Integer.toString(anioAuxNuevo);
            fechaGeneracionNuevo = diaNuevo + "/" + mesNuevo + "/" + anioNuevo;

            return fechaGeneracionNuevo;
        }
    }




    /**
     * @since Modificamos el decimal cuando es coma por punto
     * @param valor
     * @return String valor
     */
    public static String validarDecimal(String valor)
    {
        if (null != valor && !(valor.trim().equals("0.00")) && !(valor.trim().equals("")))
        {
            int pos = valor.indexOf(',');
            if (pos != -1)
            {
                valor = valor.replaceAll(",", ".");
            }
        }
        return valor;
    }

     public static String formatoHora(String valor)
    {
        if(valor.length()!=5)
        {
            mensaje="ERROR en el formato hora";
        }
        else
        {

        }

        return mensaje;
    }


    /**
     * @since Validamos si hay algún elemento no numérico
     * @param valor
     * @return String mensaje
     */
    public static String validarNumericoDecimal(String valor)
    {
        // Declaracion de la constante
        final String NUMEROS = "0123456789.";
        // Declaramos un Array de carcteres y lo rellenamos con el numero a validar
        char[] arrayNumerico = valor.toCharArray();
        int contador = 0;
        int decimal = 0;
        // Se recorre el array y comprovamos que sus elementos sean numeros
        for (int i = 0; i < arrayNumerico.length; i++)
        {
            //System.out.println(arrayNumerico[i]);
            if (NUMEROS.indexOf(arrayNumerico[i]) >= 0)
            {
                contador++;
            }
            else
            {
                mensaje = "ERROR en el formato numerico decimal";
            }
            if (arrayNumerico[i] == '.')
            {
                decimal++;
            }
        }
        if (decimal > 1)
        {
            mensaje = "ERROR en el formato numerico decimal";
        }
        if (contador == arrayNumerico.length)
        {
            mensaje = "OK";
        }
        else
        {
            mensaje = "ERROR en el formato numerico decimal";
        }
        return mensaje;
    }

    public static String campoObligatorio(String campo,String nombre)
    {
        if (campo.equals("") || campo.equals(null))
        {
            mensaje="Debe introducir el campo " + nombre ;
        }
        else
            mensaje="OK";

        return mensaje;
    }

    public static String campoObligatorioCombo(String campo,String nombre)
    {
        if (campo.equals("Selecciona") || campo.equals(null))
        {
            mensaje="Debe introducir el campo " + nombre ;
        }
        else
            mensaje="OK";

        return mensaje;
    }

    public static String LiteralMes(int mes)
    {
        String mesLetras="";

        switch(mes)
        {
            case 1:
                mesLetras="ENERO";
                break;
            case 2:
                mesLetras="FEBRERO";
                break;
            case 3:
                mesLetras="MARZO";
                break;
            case 4:
                mesLetras="ABRIL";
                break;
            case 5:
                mesLetras="MAYO";
                break;
            case 6:
                mesLetras="JUNIO";
                break;
            case 7:
                mesLetras="JULIO";
                break;
            case 8:
                mesLetras="AGOSTO";
                break;
            case 9:
                mesLetras="SEPTIEMBRE";
                break;
            case 10:
                mesLetras="OCTUBRE";
                break;
            case 11:
                mesLetras="NOVIEMBRE";
                break;
            case 12:
                mesLetras="DICIEMBRE";
                break;
        }

        return mesLetras;

    }

     public static void ActivarMenus(boolean flag){

         CSDesktop.menuClientes.setEnabled(flag);
         CSDesktop.menuProveedores.setEnabled(flag);
         CSDesktop.menuPedidos.setEnabled(flag);
         CSDesktop.menuTarifa.setEnabled(flag);
         CSDesktop.menuAyuda.setEnabled(flag);
     }

      public static String CalcularImporteServicioEspecial(String servicioEspecial, String cl_id, String fecha)
        throws SQLException
    {
        String campoServicio = "";
        String importeServicioEs = "";
        if(servicioEspecial.equals("Urgente"))
            campoServicio = "sc_urgente";
        else
        if(servicioEspecial.equals("ITV Conductor"))
            campoServicio = "sc_itv";
        else
        if(servicioEspecial.equals("Pre_ITV"))
            campoServicio = "sc_pre_itv";
        else
        if(servicioEspecial.equals("ITV Gr\372a"))
            campoServicio = "sc_itv_pre_itv";
        else
        if(servicioEspecial.equals("Peritaci\363n"))
            campoServicio = "sc_peritacion";
        else
        if(servicioEspecial.equals("Mano obra Mec\341nica/Chapa"))
            campoServicio = "sc_mo_mecanica_chapa";
        else
        if(servicioEspecial.equals("Chequeo"))
            campoServicio = "sc_chequeo";
        else
        if(servicioEspecial.equals("Repostaje"))
            campoServicio = "sc_repostaje";
        else
        if(servicioEspecial.equals("LD Exterior"))
            campoServicio = "sc_ldom_exterior";
        else
        if(servicioEspecial.equals("LD Interior y Exterior"))
            campoServicio = "sc_ldom_exin";
        else
        if(servicioEspecial.equals("LD Integral"))
            campoServicio = "sc_ldom_integral";
        else
        if(servicioEspecial.equals("LD Interior/Exterior 4x4"))
            campoServicio = "sc_ldom_int_ext_cuatro";
        else
        if(servicioEspecial.equals("LD Integral 4x4"))
            campoServicio = "sc_ldom_integral_cuatro";
        else
        if(servicioEspecial.equals("LD Interior/Exterior Industrial"))
            campoServicio = "sc_ldom_int_ext_industrial";
        else
        if(servicioEspecial.equals("LD Integral Industrial"))
            campoServicio = "sc_ldom_integral_industrial";
        else
        if(servicioEspecial.equals("LC Exterior"))
            campoServicio = "sc_lavado_exterior";
        else
        if(servicioEspecial.equals("LC Interior y Exterior"))
            campoServicio = "sc_lavado_exin";
        else
        if(servicioEspecial.equals("LC Integral"))
            campoServicio = "sc_lavado_integral";
        else
        if(servicioEspecial.equals("LC Interior/Exterior 4x4"))
            campoServicio = "sc_int_ext_cuatro";
        else
        if(servicioEspecial.equals("LC Integral 4x4"))
            campoServicio = "sc_integral_cuatro";
        else
        if(servicioEspecial.equals("LC Interior/Exterior Industrial"))
            campoServicio = "sc_int_ext_industrial";
        else
        if(servicioEspecial.equals("LC Integral Industrial"))
            campoServicio = "sc_integral_industrial";
        else
        if(servicioEspecial.equals("Desrotular pegatinas f\341cil"))
            campoServicio = "sc_desrotular_peg_facil";
        else
        if(servicioEspecial.equals("Desrotular pegatinas normal"))
            campoServicio = "sc_desrotular_peg_normal";
        else
        if(servicioEspecial.equals("Desrotular pegatinas dif\355cil"))
            campoServicio = "sc_desrotular_peg_dificil";
        else
        if(servicioEspecial.equals("Rotular pegatinas f\341cil"))
            campoServicio = "sc_rotular_peg_facil";
        else
        if(servicioEspecial.equals("Rotular pegatinas normal"))
            campoServicio = "sc_rotular_peg_normal";
        else
        if(servicioEspecial.equals("Rotular pegatinas dif\355cil"))
            campoServicio = "sc_rotular_peg_dificil";
        if(!campoServicio.equals(""))
        {
            String querySe = (new StringBuilder()).append("SELECT ").append(campoServicio).append(" FROM sc_servicios_clientes WHERE cl_id = ").append(cl_id).append(" AND sc_fecha_hasta > '").append(fecha).append("' LIMIT 1").toString();
            for(ResultSet rsSe = CSDesktop.datos.select(querySe); rsSe.next();)
                importeServicioEs = rsSe.getString(campoServicio);

        }
        return importeServicioEs;
    }

    /**
     * Convierta a Hexa
     * @param data
     * @return
     */
    private static String convertToHex(byte[] data)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++)
        {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     * Ciframos una cadena
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String text)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }


     /* Cifrar una cadena */
 /*   public String getMD5(String passwd)
    {
        byte[] textBytes = passwd.getBytes();
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        md.update(textBytes);
        byte[] codigo = md.digest();
        
        String md5 = "";
        //String md5 = this.convertToHex(md5sum);
        return md5;
    }*/

    /* Convierta a Hexa */
/*    private String convertToHex(byte[] data)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i &lt; data.length; i++)
        {
            int halfbyte = (data[i] &gt;&gt;&gt; 4) &amp; 0x0F;
            int two_halfs = 0;
            do
            {
                if ((0 &lt;= halfbyte) &amp;&amp; (halfbyte &lt;= 9))
                    buf.append((char) (’0′ + halfbyte));
                else
                    buf.append((char) (‘a’ + (halfbyte - 10)));
                    halfbyte = data[i] &amp; 0x0F;
                } while(two_halfs++ &lt; 1);
            }
        return buf.toString();
    }*/
    
/* Calcular el MD5 de un archivo*/

//}

/*public String getMD5(File archivo) {
byte[] textBytes = new byte[10000];
MessageDigest md = null;
int read = 0;
String md5 = null;
try {
InputStream is = new FileInputStream(archivo);
md = MessageDigest.getInstance("MD5");
while ((read = is.read(textBytes)) &gt; 0) {
md.update(textBytes, 0, read);
}
is.close();
byte[] md5sum = md.digest();
md5 = this.convertToHex(md5sum);
} catch (FileNotFoundException e1) {
e1.printStackTrace();
} catch (NoSuchAlgorithmException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}

return md5;

}*/

public static ArrayList obtenerFactor(String factor, String cliente) throws SQLException
  {
      ArrayList factorSel = new ArrayList();
      int factorInt = Integer.parseInt(factor);
      String campo = "";
      String descripcion = "";

      switch(factorInt)
      {
          case 0 :
              descripcion = "Sin factor";
          break;
         case 1 :
              descripcion = "TURISMO";
          break;
          case 2 :
              campo = "sc_industrial";
              descripcion = "INDUSTRIAL";
          break;
          case 3 :
              campo = "sc_todoterreno";
              descripcion = "TODOTERRENO";
          break;
          case 4 :
              campo = "sc_furgonetas";
              descripcion = "FURGONETAS";
          break;
          case 5 :
              campo = "sc_furgones";
              descripcion = "FURGONES";
          break;
      }
       factorSel.add(descripcion);
      if (!campo.equals(""))
      {
          String queryFactor = "SELECT "+campo+" FROM sc_servicios_clientes WHERE cl_id="+cliente;
          ResultSet rs3 = CSDesktop.datos.select(queryFactor);

          while(rs3.next())
          {
              factorSel.add(rs3.getDouble(campo));

          }
      }

      return factorSel;
  }
public static double redondear( double numero, int decimales ) {
    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
  }


}