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
    public static void main(BeanCorreoCliente mail)
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
            message.setFrom(new InternetAddress("operaciones@carset.es"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("cesardecruz@gmail.com"));
            message.setSubject("Resumen Estado Pedido " + mail.getNumPedido());
            //message.setText(
                //"Mensajito con Java Mail" + "de los buenos." + "poque si");
            String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";

            //String htmlText ="<br><br><center><table>";
            //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
            String htmlText = "<br><br><center><table width='700'>" +
            "<tr><td width='100'><img src=\""+imagen+"\" width='100'></td>" +
            "<td><p align=center><font face='Helvetica' size='+1'> CONFIRMACI&Oacute;N DE PEDIDO</p></font></td></tr>" +
            "<tr><td colspan='2'><br><br><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: </font></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> A continuaci&oacute;n y en contestaci&oacute;n a su solicitud, le remitimos la confirmaci&oacute;n del servicio solicitado:  </font></td></tr>" +
            "<br><br>" +
            "<tr><td width='200'><font face='Helvetica'><u> Tipo de servicio : </u></font></td><td><font face='Helvetica'> Traslado servicio "+mail.getSoporte()+" </font></td></tr>" +
            "<tr><td width='200'><font face='Helvetica'><u> Fecha aprox. de recogida : </u></font></td><td><font face='Helvetica'> 28/06/2010 </font></td></tr>" +
            "<tr><td width='200'><font face='Helvetica'><u> Fecha aprox. de entrega : </u></font></td><td><font face='Helvetica'> 03/07/2010 </font></td></tr>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td align='center' width='100'>MARCA</td><td align='center' width='300'>MODELO</td><td align='center' width='300'>MATRICULA/BASTIDOR</td>" +
                                "<tr><td align='center' width='100'>AUDI</td><td align='center' width='300'>A3</td><td align='center'  width='300'>3065 GMP</td></table>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td align='center' width='100'>&nsbp;</td><td align='center' width='300'>DATOS DE ORIGEN</td><td align='center' width='300'>DATOS DE DESTINO</td>" +
                                "<tr><td align='center' width='100'>DIRECCION</td><td align='center' width='300'>A3</td><td align='center' width='300'>3065 GMP</td>" +
                                "<tr><td align='center' width='100'>POBLACION</td><td align='center' width='300'>A3</td><td align='center' width='300'>3065 GMP</td>" +
                                "<tr><td align='center' width='100'>PROVINCIA</td><td align='center' width='300'>A3</td><td align='center' width='300'>3065 GMP</td>" +
                                "<tr><td align='center' width='100'>CONTACTO</td><td align='center' width='300'>A3</td><td align='center' width='300'>3065 GMP</td>" +
                                "<tr><td align='center' width='100'>TELEFONO</td><td align='center' width='300'>A3</td><td align='center' width='300'>3065 GMP</td>" +
            "</table></td></tr>" +
            "<br><br>" +
            "<tr><td colspan='2'><table width='400' border='1'><tr><td> TARIFA :</td><td align='right'> 100 €</td></tr>" +
            "<tr><td> FACTOR DE CORRECCION :</td><td align='right'> 25 €</td></tr>" +
            "<tr><td> ENTRADA A CASCO URBANO</td><td align='right'> 45 €</td></tr>" +
            "<tr><td> TOTAL :</td><td align='right'> 170 € (+ IVA)</td></tr></table>" +
            "<br>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Para cualquier consulta al respecto, no dude en ponerse en contacto con nuestro departamento de Operaciones (91.268.69.60). </font></td></tr>" +
            "<br>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'>Atentamente, </font></td></tr>" +
            "<br><br>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Departamento de Operaciones</font></td></tr>";

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