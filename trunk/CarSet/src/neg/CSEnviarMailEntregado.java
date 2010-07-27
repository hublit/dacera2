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
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class CSEnviarMailEntregado
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(BeanCorreoCliente mail)
    {
        String tarifa="";
        String importeServicioEs="";
        String IdaVuelta="";
        double importeTotal=0;
        String nombre="";
        String email="";

        try
        {

            String query="SELECT DISTINCT"+
            " pe.pe_servicio_especial,pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id,pe.pe_ta_es_cliente," +
            " pe.pe_ta_es_proveedor, pe.pe_suplemento,pe.pe_num_en_camion,pe.pe_descripcion, tc.tc_tarifa," +
            " sc_entrada_campa, sc_campa" +
            " FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc, sc_servicios_clientes sc" +
            " WHERE pe.pe_num =" +mail.getNumero()+""+
            " AND sc.cl_id = pc.cl_id" +
            " AND tc.tc_servicio = pe.pe_servicio"+
            " AND  tc.cl_id = pc.cl_id"+
            " AND (tc.tc_servicio_origen = pe.pe_servicio_origen"+
            " OR tc.tc_servicio_origen = pe.pe_servicio_destino)"+
            " AND (tc.tc_servicio_destino = pe.pe_servicio_destino"+
            " OR tc.tc_servicio_destino = pe.pe_servicio_origen)"+
            " AND tc.tc_soporte = pe.pe_soporte"+
            " GROUP BY pe.pe_num";

            System.out.println(query);

            ResultSet rs_mail = CSDesktop.datos.select(query);

            while (rs_mail.next())  {

                mail.setServicioEspecial(rs_mail.getString("pe_servicio_especial"));
                mail.setDiasCampa(rs_mail.getString("pe_dias_campa"));
                mail.setIdaVuelta(rs_mail.getString("pe_ida_vuelta"));
                mail.setFactorCorrecccion(rs_mail.getString("fc_id"));
                mail.setTarifaEspecialCliente(rs_mail.getString("pe_ta_es_cliente"));
                mail.setTarifaEspecialProveedor(rs_mail.getString("pe_ta_es_proveedor"));
                mail.setNumeroEnCamion(rs_mail.getString("pe_num_en_camion"));
                mail.setDescripcion(rs_mail.getString("pe_descripcion"));
                mail.setTarifa(rs_mail.getString("tc_tarifa"));
                mail.setEntradaCampa(rs_mail.getString("sc_entrada_campa"));
                mail.setCampa(rs_mail.getString("sc_campa"));
                mail.setSuplemento(rs_mail.getString("pe_suplemento"));
            }

             String queryContacto="SELECT * FROM CC_CONTACTOS_CLIENTE WHERE CL_ID="+mail.getClienteID()+" LIMIT 1";
            ResultSet rsContacto = CSDesktop.datos.select(queryContacto);

             while (rsContacto.next())  {
                 nombre=rsContacto.getString("cc_nombre");
                 email=rsContacto.getString("cc_email");
             }
           
           
            // Propiedades de la conexión
            Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            //props.put("mail.smtp.host", "smtp.e.telefonica.net");
            props.put("mail.smtp.host", "10.25.11.32");
            //props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.port", "2525");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.user", "raul.cortes@grupoaldebaran.com");
            //props.put("mail.smtp.password","aldebaran");

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
                new InternetAddress("cesardecruz@gmail.com"));
            message.setSubject("Resumen Estado Pedido " + mail.getNumPedido());
            String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";

            //String htmlText ="<br><br><center><table>";
            //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
            String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
            "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
            "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
            "<br><br><table width='700'>" +
            "<tr><td width='100'><img src=\""+imagen+"\" width='100'></td>" +
            "<td><p><font face='Helvetica' size='+1'> CONFIRMACI&Oacute;N DE ENTREGA</p></font></td></tr>" +
            "<tr><td colspan='2'><br><br><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: </font></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Mediante la presente, le pasamos confirmaci&oacute;n del siguiente servicio realizado por nuestros transportistas: </font></td></tr>" +
            "<br><br>" +
            "<tr><td width='200'><font face='Helvetica'><u> Tipo de servicio : </u></font></td><td><font face='Helvetica'> Traslado servicio "+mail.getSoporte()+" </font></td></tr>" +            
            "<tr><td width='200'><font face='Helvetica'><u> Fecha aprox. de entrega : </u></font></td><td><font face='Helvetica'> "+mail.getFechaEntrega()+" </font></td></tr>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td width='100' bgcolor='#BDBDBD'><font face='Helvetica'><b>MARCA</b></font></td><td  width='300'  bgcolor='#BDBDBD'><font face='Helvetica'><b>MODELO </b></font></td><td  width='300'  bgcolor='#BDBDBD'><font face='Helvetica'><b>MATRICULA/BASTIDOR</b></font></td>" +
                                "<tr><td width='100'><font face='Helvetica'>"+mail.getMarca()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getModelo()+"</font></td><td   width='300'><font face='Helvetica'>"+mail.getMatricula()+"</font></td></table>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td width='100' bgcolor='#BDBDBD'>&nbsp;</td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Direcci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Poblaci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Provincia</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                                "<tr><td width='100'><font face='Helvetica'><b>Contacto</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Tel&eacute;fono</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>" +
            "</table></td></tr>" +
            "<br><br>";
            htmlText = htmlText +"<br><tr><td colspan='2'><br><br><font face='Helvetica'> Para cualquier consulta al respecto, no dude en ponerse en contacto con nuestro departamento de Operaciones (91.268.69.60). </font></td></tr>";
            htmlText = htmlText +"<br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><br><font face='Helvetica'>Atentamente, </font></td></tr>";
            htmlText = htmlText +"<br><br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'><b> Departamento de Operaciones<b></font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' size='+1' color='#088A08'>CarSet</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Tlf: 91 268 69 60</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Movil: 606 33 96 56</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Avda. Puente Cultural, 5 Bl.A - Pl .3 - Of. 2 </font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>www.carset.es</font></td></tr>";
            htmlText = htmlText +"</table></body>";

            message.setContent(htmlText, "text/html");


            // Lo enviamos.
             transport.connect();
            transport.sendMessage(message, message.getAllRecipients());

            // Cierre.
            transport.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

     private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "operaciones@carset.e.telefonica.net";
            String password = "912686953";
           return new PasswordAuthentication(username, password);
        }
    }
}