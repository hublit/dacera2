/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neg;

/**
 *
 * @author depr102
 */
import data.BeanCorreoCliente;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utils.Utilidades;

public class CSEnviarMailProcesoNew
{
    /**
     * main de prueba
     * @param mail
     * @param email
     * @param nombre
     */
    public static void main(BeanCorreoCliente mail, String email, String nombre)
    {
        String tarifa = "";
        double importeTotal = 0;

        try{
            String query = "SELECT DISTINCT"+
            " pe.pe_num,pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, " +
            " pe.pe_servicio, pe.pe_servicio_especial, pe.pe_dias_campa, pe.fc_id, " +
            " pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_modelo, " +
            " pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, pe.pe_num_en_camion, " +
            " pe.pe_descripcion, pe_ve_estado, pe_kms, pe_ta_km_cliente " +
            " FROM pe_pedidos pe, pc_pedidos_clientes pc" +
            " WHERE pe.pe_num = pc.pe_num" +
            " AND pe.pe_num="+mail.getNumero();
            
            System.out.println(query);

            ResultSet rs_mail = CSDesktop.datos.select(query);

            while (rs_mail.next()){
                mail.setServicioEspecial(rs_mail.getString("pe_servicio_especial"));
                mail.setDiasCampa(rs_mail.getString("pe_dias_campa"));
                mail.setFactorCorrecccion(rs_mail.getString("fc_id"));
                //mail.setTarifaEspecialCliente(rs_mail.getString("pe_ta_es_cliente"));
                mail.setTarifaEspecialProveedor(rs_mail.getString("pe_ta_es_proveedor"));
                mail.setNumeroEnCamion(rs_mail.getString("pe_num_en_camion"));
                //mail.setDescripcion(rs_mail.getString("pe_descripcion"));
                mail.setVeEstado(rs_mail.getString("pe_ve_estado"));
                //mail.setKms(rs_mail.getString("pe_kms"));
                //mail.setTarifakmCliente(rs_mail.getString("pe_ta_km_cliente"));
            }

            if(rs_mail != null)
            {
                // Propiedades de la conexión
                Properties props = new Properties();
                props.put("mail.transport.protocol","smtp");
                props.put("mail.smtp.host", "smtp.office365.com");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");

                SMTPAuthenticator auth = new SMTPAuthenticator();
                Session mailSession = Session.getDefaultInstance(props, auth);
                Transport transport = mailSession.getTransport();

                // Construimos el mensaje
                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress("Operaciones CarSet <operaciones@carset.es>"));
                message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(email));
                message.addRecipient(
                    Message.RecipientType.CC,
                    new InternetAddress("carset@carset.es"));
                message.setSubject("Resumen Estado Pedido " + mail.getNumPedido());
                String imagen ="http://www.amarcos.es/carset/img/logo.png";

                String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
                    "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
                    "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
                    "<table width='700'>" +
                    "<tr><td width='140'><img src=\""+imagen+"\" width='190'></td></tr>" +
                    "<tr><td colspan='2'><center><font face='Helvetica' size='+1'><b>CONFIRMACI&Oacute;N DE PEDIDO</b></font></td></tr>" +
                    "<tr><td colspan='2'><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
                    "<tr><td colspan='2'><font face='Helvetica'> Estimado Sr./Sra.: "+nombre+"</font>" +
                    "<br>" +
                    "<tr><td colspan='2'><font face='Helvetica'> A continuaci&oacute;n y en contestaci&oacute;n a su solicitud, le remitimos la confirmaci&oacute;n del servicio solicitado:  </font></td></tr>";
                    if(!mail.isPeUnido()){
                        htmlText = htmlText + "<tr><td width='200'><font face='Helvetica'><u><b> Soporte: </b></u></font></td><td><font face='Helvetica'> "+mail.getSoporte()+" </font></td></tr>";
                    }
                    htmlText = htmlText + "<tr><td width='200'><font face='Helvetica'><u><b> Fecha aprox. de recogida : </b></u></font></td><td><font face='Helvetica'> "+mail.getFechaRecogida()+" </font></td></tr>" +
                    "<tr><td width='200'><font face='Helvetica'><u><b> Fecha aprox. de entrega : </b></u></font></td><td><font face='Helvetica'> "+mail.getFechaEntrega()+" </font></td></tr>" +
                    "<tr><td width='200'><font face='Helvetica'><u><b> Estado del vehículo : </b></u></font></td><td><font face='Helvetica'> "+mail.getVeEstado()+" </font></td></tr>";
                    if(!mail.getKms().equals("0")){
                        htmlText = htmlText + "<tr><td width='200'><font face='Helvetica'><u><b> Kilómetros : </b></u></font></td><td><font face='Helvetica'> "+mail.getKms()+" </font></td></tr>";
                    }
                    htmlText = htmlText + //"<br>" +
                    "<tr><td colspan='2'><table border='1' width='700'>" +
                    "<tr><td width='130' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MARCA</b></font></td><td width='285'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MODELO </b></font></td><td width='285'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MATRICULA/BASTIDOR</b></font></td>" +
                    "<tr><td width='100'><font face='Helvetica'>&nbsp;"+mail.getMarca()+"</font></td><td width='285'><font face='Helvetica'>&nbsp;"+mail.getModelo()+"</font></td><td width='285'><font face='Helvetica'>&nbsp;"+mail.getMatricula()+"</font></td></table>" +

                    "<tr><td colspan='2'><table border='1' width='700'>" +
                    "<tr><td width='130' bgcolor='#BDBDBD'>&nbsp;</td><td width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                    "<tr><td width='130'><font face='Helvetica'><b>&nbsp;Direcci&oacute;n</b></font></td><td width='285'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td width='285'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                    "<tr><td width='130'><font face='Helvetica'><b>&nbsp;Poblaci&oacute;n</b></font></td><td width='285'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td width='285'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                    "<tr><td width='130'><font face='Helvetica'><b>&nbsp;Provincia</b></font></td><td width='285'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td width='285'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                    "<tr><td width='130'><font face='Helvetica'><b>&nbsp;Contacto</b></font></td><td width='285'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                    "<tr><td width='130'><font face='Helvetica'><b>&nbsp;Tel&eacute;fono</b></font></td><td width='285'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>";
                    if(mail.isObsClInmail()){
                        htmlText = htmlText +  "<tr><td  width='100'><font face='Helvetica'><b>Observaciones</b></font></td><td colspan='2'><font face='Helvetica'>&nbsp;"+mail.getDescripcion().toUpperCase()+"</font></td></tr>";
                    }

                    htmlText = htmlText + "</table></td></tr>" +
                    "<tr><td colspan='2'><table width='400' border='1' align='right'>";
  
                if (mail.getDiasCampa().equals("0"))
                {
                    if(!mail.getTarifaEspecialCliente().equals("-1"))
                    {
                        tarifa = mail.getTarifaEspecialCliente();

                        // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                        // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                        if(mail.getSoporte().equals("Camión completo") && !mail.getNumeroEnCamion().equals("1"))
                            tarifa="0";
                        double importeTrasladoD = Double.parseDouble(tarifa);
                        importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                        importeTotal = importeTotal + importeTrasladoD;
                        //Escribimos la linea
                        htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;TARIFA</font></td><td align='right' width='80'><font face='Helvetica'><b>&nbsp;"+importeTrasladoD+" &euro;</b></font></td></tr>";

                        // FACTOR DE CORRECCION
                        //ArrayList factorTarifa = Utilidades.obtenerFactor(mail.getFactorCorrecccion(), mail.getClienteID());
                        //factorTexto = factorTarifa.get(0).toString();
                    // SI NO TIENE TARIFA ESPECIAL
                    }else{
                        tarifa = mail.getTarifa();
                        // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                        // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                        if(mail.getSoporte().equals("Camión completo") && !mail.getNumeroEnCamion().equals("1"))
                            tarifa="0";
                        double importeTrasladoD = Double.parseDouble(tarifa);
                        importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                        importeTotal = importeTotal + importeTrasladoD;
                         //Escribimos la linea
                        htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;TARIFA</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeTrasladoD+" &euro;</font></td></tr>";

                        if(!mail.getServicioEspecial().equals(""))
                        {
                            if(!mail.getServicioEspecial().equals("Otros"))
                            {
                                String [] tempOrigen = null;
                                tempOrigen = mail.getFecha().split("\\-");
                                String nuevaFecha=tempOrigen[2]+"-"+tempOrigen[1]+"-"+tempOrigen[0];

                                String importeServicio=Utilidades.CalcularImporteServicioEspecial(mail.getServicioEspecial(),mail.getClienteID(),nuevaFecha);
                                if(!importeServicio.equals(""))
                                {
                                    double importeServicioD = Double.parseDouble(importeServicio);
                                    importeServicioD = Utilidades.redondear(importeServicioD, 2);
                                    //servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                                    //labelServicioEspecial="SERVICIO ESPECIAL";
                                    htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getServicioEspecial().toUpperCase()+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeServicioD+" &euro;</font></td></tr>";
                                    importeTotal = importeTotal + importeServicioD;
                                }
                            }
                            // NO PUEDE TENER VALOR OTROS
                            else
                            {
                                if(mail.getSoporte().equals("Camión completo"))
                                {
                                    //labelOtros=beanFactura.getDescripcion().toUpperCase();
                                     htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getDescripcion().toUpperCase()+"</font></td><td align='right'><font face='Helvetica'>&nbsp;</font></td></tr>";
                                }
                            }
                        }
                        
                        //LINEA DE FACTOR DE CORRECCION
                        if(!mail.getFactorCorrecccion().equals("0"))
                        {
                            ArrayList factorTarifa = Utilidades.obtenerFactor(mail.getFactorCorrecccion(),mail.getClienteID());
                            String factorTexto = factorTarifa.get(0).toString();

                            if(mail.getSoporte().equals("Grúa"))
                            {
                                if((!factorTexto.equals("Sin factor") && (!factorTexto.equals("TURISMO")) )&& !tarifa.equals(""))
                                {
                                    DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
                                    double ft = Double.parseDouble(factorTarifa.get(1).toString());
                                    double importeFc = ((importeTrasladoD * ft) - importeTrasladoD);
                                    double nuevoImporteFactor=Utilidades.redondear(importeFc, 2);
                                    htmlText = htmlText + "<tr><td><font face='Helvetica'> FACTOR DE CORRECCION </font></td><td align='right'><font face='Helvetica'>"+importeFc+" &euro;</font></td></tr>";
                                    importeTotal = importeTotal + nuevoImporteFactor;
                                }
                            }
                        }
                    }
                }

                //htmlText = htmlText +"<tr><td><font face='Helvetica'><b>&nbsp;TOTAL</b></font></td><td align='right'><font face='Helvetica'><b>&nbsp;"+importeTotal+" &euro;</b></font></td></tr>";
                htmlText = htmlText +"</table>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'> Estos precios no incluyen I.V.A </font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'> Para cualquier consulta, no dude en ponerse en contacto con nuestro dpto. de Operaciones. </font></td></tr>";
                //htmlText = htmlText +"<br>";
                htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'>Atentamente, </font></td></tr>";
//                htmlText = htmlText +"<br>";
                htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'><b> Departamento de Operaciones<b></font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>Tlf: 91 268 69 60 - Fax: 91 268 69 64 - www.carset.es</font></td>";
//                "<td><font face='Helvetica'>Tlf: 91 268 69 60 - Fax: 91 268 69 64</font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='3'><font face='Helvetica'>Avda. Puente Cultural, 10 Bl.B - Pl .4 - Of. 4 - " +
                "<font face='Helvetica'>28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
                htmlText = htmlText +"</table></body>";

                message.setContent(htmlText, "text/html");

                transport.connect();
                transport.sendMessage(message, message.getAllRecipients());

                transport.close();

                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail ha sido enviado correctamente.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                }
            else
            {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>No existen datos para enviar el mail.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
            }
        }
        catch (Exception e)
            {
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = red>El e-mail no ha podido ser enviado.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
                e.printStackTrace();
            }
        }

    /**
     *
     */
    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "operaciones@carset.e.telefonica.net";
            String password = "CAR11set";
           return new PasswordAuthentication(username, password);
        }
    }
}