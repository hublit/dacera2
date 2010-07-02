/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neg;

/**
 *
 * @author depr102
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class EnviarMail
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(String[] args)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "correo.inem.es");
            props.setProperty("mail.smtp.starttls.enable", "false");
            props.setProperty("mail.smtp.port", "25");
            //props.setProperty("mail.smtp.user", "cesar.delacruz@sepe.es");
            props.setProperty("mail.smtp.auth", "false");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("c_de_cruz@hotmail.com"));
            message.setSubject("Hola");
            //message.setText(
                //"Mensajito con Java Mail" + "de los buenos." + "poque si");
            String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";

            //String htmlText ="<br><br><center><table>";
            //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
            String htmlText = "<br><br><center><table border='1' width='800'><tr><td><img src=\""+imagen+"\" width='100'></td><td><p align=center>Confirmaci&oacute;n de Entrega</p></td></tr>";
            htmlText = htmlText + "<tr><td><br><br>Estimado Sr/Sra</td></tr>";
            htmlText = htmlText + "<tr><td><br><br>Mediante la presente, le pasamos confirmación del siguiente servicio realizado por nuestros transportistas:</td></tr>";
            message.setContent(htmlText, "text/html");


            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("", "");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}