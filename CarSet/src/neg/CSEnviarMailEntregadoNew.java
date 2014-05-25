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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class CSEnviarMailEntregadoNew
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(BeanCorreoCliente mail,String email,String nombre)
    {
        String tarifa="";
        String importeServicioEs="";
        double importeTotal=0;
        
        try
        {
            String query = "SELECT DISTINCT"+
            " pe.pe_num , pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio," +
            " pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, pe.pe_dias_campa,"+
            " pe.pe_ida_vuelta,pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_modelo," +
            " pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor,pe.pe_suplemento,pe.pe_num_en_camion, pe.pe_descripcion," +
            " sc_entrada_campa, sc_campa, pe_ve_estado, pe_kms " +
            " FROM pe_pedidos pe, pc_pedidos_clientes pc, sc_servicios_clientes sc" +
            " WHERE pe.pe_num = pc.pe_num AND sc.cl_id = pc.cl_id" +
            " AND sc.sc_fecha_hasta > pe.pe_fecha" +
            " AND pe.pe_num="+mail.getNumero();

            System.out.println(query);

            ResultSet rs_mail = CSDesktop.datos.select(query);

            while (rs_mail.next())
            {
                mail.setServicioEspecial(rs_mail.getString("pe_servicio_especial"));
                mail.setDiasCampa(rs_mail.getString("pe_dias_campa"));
                mail.setFactorCorrecccion(rs_mail.getString("fc_id"));
                //mail.setTarifaEspecialCliente(rs_mail.getString("pe_ta_es_cliente"));
                mail.setTarifaEspecialProveedor(rs_mail.getString("pe_ta_es_proveedor"));
                mail.setNumeroEnCamion(rs_mail.getString("pe_num_en_camion"));
                //mail.setDescripcion(rs_mail.getString("pe_descripcion"));
                //mail.setTarifa(rs_mail.getString("tc_tarifa"));
                mail.setCampa(rs_mail.getString("sc_campa"));
                mail.setVeEstado(rs_mail.getString("pe_ve_estado"));
                //mail.setKms(rs_mail.getString("pe_kms"));
            }
             if(rs_mail != null)
            {
            
            String fechaRealEntrega=mail.getFechaRealEntrega();
                String [] tempDestino = null;
                    tempDestino = fechaRealEntrega.split("\\-");
                    String anyoD=tempDestino[0];
                    String mesD=tempDestino[1];
                    String diaD=tempDestino[2];
                    String nuevaD=diaD+"-"+mesD+"-"+anyoD;
           
            // Propiedades de la conexión
            Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            //props.put("mail.smtp.host", "smtp.e.telefonica.net");
            props.put("mail.smtp.host", "smtp.office365.com");
            //props.put("mail.smtp.host", "localhost");
            //props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.starttls.enable", "true");
            //props.put("mail.smtp.port", "25");
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
            String imagen ="http://carset.e.telefonica.net/images/logo_carset_trans.gif";
            
            //String htmlText ="<br><br><center><table>";
            //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
            String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
            "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
            "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
            "<table width='750'>" +
            "<tr><td width='150'><img src=\""+imagen+"\" width='100'></td>" +
            "<td><b><font face='Helvetica' size='+1'> CONFIRMACI&Oacute;N DE ENTREGA</b></font></td></tr>" +
            "<tr><td colspan='2'><table><tr><td width='150'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
            "<tr><td colspan='2'><br><font face='Helvetica'> Estimado Sr./Sra.: "+nombre+"</font></td></tr>" +
            "<tr><td colspan='2'><font face='Helvetica'> Mediante la presente, le pasamos confirmaci&oacute;n del siguiente servicio realizado por nuestros transportistas: </font></td></tr>";
            //"<br>" +
            if(mail.isPeUnido()){
                htmlText = "<tr><td width='200'><font face='Helvetica'><u><b> Soporte: </b></u></font></td><td><font face='Helvetica'> "+mail.getSoporte()+" </font></td></tr>";
            }
            htmlText = "<tr><td width='200'><font face='Helvetica'><u><b> Fecha de entrega: </b></u></font></td><td><font face='Helvetica'> "+nuevaD+" </font></td></tr>" +
            //"<br>" +
            "<tr><td colspan='2'><table border='1' width='750'>" +
                                "<tr><td width='150' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MARCA</b></font></td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MODELO </b></font></td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MATRICULA/BASTIDOR</b></font></td>" +
                                "<tr><td width='150'><font face='Helvetica'>&nbsp;"+mail.getMarca()+"</font></td><td  width='300'><font face='Helvetica'>&nbsp;"+mail.getModelo()+"</font></td><td   width='300'><font face='Helvetica'>&nbsp;"+mail.getMatricula()+"</font></td></table>" +
            //"<br>" +
            "<tr><td colspan='2'><table border='1' width='750'>" +
                                "<tr><td width='150' bgcolor='#BDBDBD'>&nbsp;</td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                                "<tr><td  width='150'><font face='Helvetica'><b>Direcci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                                "<tr><td  width='150'><font face='Helvetica'><b>Poblaci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                                "<tr><td  width='150'><font face='Helvetica'><b>Provincia</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                                "<tr><td width='150'><font face='Helvetica'><b>Contacto</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                                "<tr><td  width='150'><font face='Helvetica'><b>Tel&eacute;fono</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>";
            if(mail.isObsClInmail()){

                htmlText = htmlText +  "<tr><td  width='150'><font face='Helvetica'><b>Observaciones</b></font></td><td colspan='2'><font face='Helvetica'>&nbsp;"+mail.getDescripcion().toUpperCase()+"</font></td></tr>";
            }
            htmlText = htmlText + "</table></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'> Para cualquier consulta, no dude en ponerse en contacto con nuestro dpto. de Operaciones. </font></td></tr>";
//            htmlText = htmlText +"<br>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Atentamente, </font></td></tr>";
            htmlText = htmlText +"<br>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'><b> Departamento de Operaciones<b></font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' size='+1' color='#088A08'>CarSet</font> - <font face='Helvetica' color='#088A08'>www.carset.es - Tlf: 91 268 69 60 - Fax: 91 268 69 64</font></td>";
//            "<tr><td><font face='Helvetica'></font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Avda. Puente Cultural, 10 Bl.B - Pl .4 - Of. 4  - 28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
//            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>www.carset.es</font></td></tr>";

            htmlText = htmlText +"</table></body>";

            message.setContent(htmlText, "text/html");

            /* BufferedWriter bw2 = null;
                bw2 = new BufferedWriter(new FileWriter("c://mailEntregadoCliente.html", false));
                bw2.write(htmlText);
                bw2.close();*/

            // Lo enviamos.
             transport.connect();
            transport.sendMessage(message, message.getAllRecipients());

            // Cierre.
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

     private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "operaciones@carset.e.telefonica.net";
            //String password = "912686953";
            String password = "CAR11set";
           return new PasswordAuthentication(username, password);
        }
    }
}