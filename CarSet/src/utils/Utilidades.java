/*
*
*/
package utils;

import java.text.SimpleDateFormat;
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

     public static void ActivarMenus(boolean flag){

         CSDesktop.menuClientes.setEnabled(flag);
         CSDesktop.menuProveedores.setEnabled(flag);
         CSDesktop.menuPedidos.setEnabled(flag);
         CSDesktop.menuTarifa.setEnabled(flag);
         CSDesktop.menuAyuda.setEnabled(flag);
     }
}