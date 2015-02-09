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
import javax.mail.PasswordAuthentication;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utils.Utilidades;

/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class CSEnviarMailProveedorNew
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(BeanCorreoCliente mail,String email,String nombre)
    {
        String tarifa="";
        double importeTotal=0;
        
        try{
         String query="SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino," +
                      " pe.pe_servicio, pe.pe_servicio_especial," +
                      " pe.pe_dias_campa, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula," +
                      " pe.pe_ve_marca, pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, " +
                      " pe.pe_num_en_camion, pe_observaciones_carset, " +
                      " pe_ve_estado, pe_kms FROM pe_pedidos pe, pp_pedidos_proveedores pp " +
                      " WHERE pe.pe_num = pp.pe_num AND pe.pe_num ="+mail.getNumero();
            
            System.out.println(query);

            ResultSet rs_mail = CSDesktop.datos.select(query);

            while (rs_mail.next())  {

                mail.setServicioEspecial(rs_mail.getString("pe_servicio_especial"));
                mail.setDiasCampa(rs_mail.getString("pe_dias_campa"));
                mail.setFactorCorrecccion(rs_mail.getString("fc_id"));
                mail.setNumeroEnCamion(rs_mail.getString("pe_num_en_camion"));
                mail.setObservaciones(rs_mail.getString("pe_observaciones_carset"));
                mail.setVeEstado(rs_mail.getString("pe_ve_estado"));
                //mail.setKms(rs_mail.getString("pe_kms"));
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

                //String htmlText ="<br><br><center><table>";
                //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
                String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
                "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
                "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
                "<table width='700'>" +
                "<tr><td width='140'><img src=\""+imagen+"\" width='190'></td></tr>" +
                "<tr><td colspan='2'><center><font face='Helvetica' size='+1'><b>CONFIRMACI&Oacute;N DE PEDIDO</b></font></td></tr>" +
                "<tr><td colspan='2'><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr>" +
                "<tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
                "<tr><td colspan='2'><br><font face='Helvetica'> Estimado Sr./Sra.: "+nombre+"</font></td></tr>" +
                "<tr><td colspan='2'><font face='Helvetica'> A continuaci&oacute;n y en contestaci&oacute;n a su solicitud, le remitimos la confirmaci&oacute;n del servicio solicitado:  </font></td></tr>" +
//              "<br>" +
                "<tr><td width='200'><font face='Helvetica'><u><b> Soporte : </b></u></font></td><td><font face='Helvetica'> "+mail.getSoporte()+" </font></td></tr>" +
                "<tr><td width='200'><font face='Helvetica'><u><b> Fecha aprox. de recogida : </b></u></font></td><td><font face='Helvetica'> "+mail.getFechaRecogida()+" </font></td></tr>" +
                "<tr><td width='200'><font face='Helvetica'><u><b> Fecha aprox. de entrega : </b></u></font></td><td><font face='Helvetica'> "+mail.getFechaEntrega()+" </font></td></tr>" +
                "<tr><td width='200'><font face='Helvetica'><u><b> Estado del vehículo : </b></u></font></td><td><font face='Helvetica'> "+mail.getVeEstado()+" </font></td></tr>";
                htmlText = htmlText + //"<br>" +
                "<tr><td colspan='2'><table border='1' width='700'>" +
                "<tr><td width='130' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MARCA</b></font></td><td width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MODELO </b></font></td><td width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MATRICULA/BASTIDOR</b></font></td>" +
                "<tr><td width='130'><font face='Helvetica'>&nbsp;"+mail.getMarca()+"</font></td><td width='285'><font face='Helvetica'>&nbsp;"+mail.getModelo()+"</font></td><td width='285'><font face='Helvetica'>&nbsp;"+mail.getMatricula()+"</font></td></table>" +
//                "<br>" +
                "<tr><td colspan='2'><table border='1' width='700'>" +
                "<tr><td  width='130' bgcolor='#BDBDBD'>&nbsp;</td><td  width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td  width='285' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                "<tr><td  width='130'><font face='Helvetica'><b>Direcci&oacute;n</b></font></td><td  width='285'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                "<tr><td  width='130'><font face='Helvetica'><b>Poblaci&oacute;n</b></font></td><td  width='285'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                "<tr><td  width='130'><font face='Helvetica'><b>Provincia</b></font></td><td  width='285'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                "<tr><td  width='130'><font face='Helvetica'><b>Contacto</b></font></td><td  width='285'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                "<tr><td  width='130'><font face='Helvetica'><b>Tel&eacute;fono</b></font></td><td  width='285'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='285'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>";
                if(mail.isObsPrInmail()){
                    htmlText = htmlText +  "<tr><td  width='130'><font face='Helvetica'><b>Observaciones</b></font></td><td colspan='2'><font face='Helvetica'>&nbsp;"+mail.getObservaciones().toUpperCase()+"</font></td></tr>";
                }
                htmlText = htmlText + "</table></td></tr>" +
//                "<br>" +
                "<tr><td colspan='2'><table width='400' border='1' align='right'>";


                if (mail.getDiasCampa()!= null || mail.getDiasCampa().equals("0")  )
                {
                    if(!mail.getTarifaEspecialProveedor().equals("-1"))
                    {
                        tarifa = mail.getTarifaEspecialProveedor();

                        // SI EL SOPORTE ES CAMION COMPLETO Y SU NUMERO EN CAMION NO ES 1
                        // EL IMPORTE ES 0 PORQUE SOLO LLEVA IMPORTE EL PRIMERO
                        if(mail.getSoporte().equals("Camión completo") && !mail.getNumeroEnCamion().equals("1"))
                            tarifa="0";
                        double importeTrasladoD = Double.parseDouble(tarifa);
                        importeTrasladoD=Utilidades.redondear(importeTrasladoD, 2);
                        importeTotal = importeTotal + importeTrasladoD;
                        //Escribimos la linea
                        htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;TARIFA</font></td><td align='right' width='80'><font face='Helvetica'><b>&nbsp;"+importeTrasladoD+" &euro;</b></font></td></tr>";
                    }
                    // SI NO TIENE TARIFA ESPECIAL
                    else
                    {
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
                                tempOrigen = mail.getFecha().split("\\/");
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
                                    String importeFactor = Double.toString(nuevoImporteFactor);
                                    String factorTexto2=factorTarifa.get(0).toString();
                                    htmlText = htmlText + "<tr><td><font face='Helvetica'> FACTOR DE CORRECCION </font></td><td align='right'><font face='Helvetica'>"+importeFc+" &euro;</font></td></tr>";
                                    importeTotal = importeTotal + nuevoImporteFactor;
                                }
                            }
                        }
                    }

                } else if (mail.getDiasCampa()!= null){
                    String queryCampa = "SELECT sc_entrada_campa,sc_campa FROM sc_servicios_clientes WHERE cl_id = "+mail.getClienteID();

                    String importeCampa2Aux="";
                    String importeCampaAux="";
                    ResultSet rsCampa = CSDesktop.datos.select(queryCampa);
                    while (rsCampa.next())
                    {
                        importeCampaAux = rsCampa.getString("sc_entrada_campa");
                        importeCampa2Aux = rsCampa.getString("sc_campa");
                    }

                    String soporte = "CAMPA";
                    String finalCampa2 = mail.getDiasCampa()+ " DIAS * " + importeCampa2Aux;
                    String importeCampa=importeCampaAux;

                    double importeCampa4=Double.parseDouble(importeCampa);
                    double importeCampa5=(Double.parseDouble(mail.getDiasCampa()))*(Double.parseDouble(importeCampa2Aux));

                    String importeCampa2=String.valueOf(importeCampa5);

                    htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;ENTRADA CAMPA</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeCampaAux+" &euro;</font></td></tr>";
                    htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;"+finalCampa2+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeCampa2+" &euro;</font></td></tr>";

                    importeTotal = importeTotal + importeCampa4 + importeCampa5;
                }
//                htmlText = htmlText +"<tr><td><font face='Helvetica'><b>&nbsp;TOTAL</b></font></td><td align='right'><font face='Helvetica'><b>&nbsp;"+importeTotal+" &euro;</b></font></td></tr>";
                htmlText = htmlText +"</table>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'> Estos precios no incluyen I.V.A </font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'> Para cualquier consulta, no dude en ponerse en contacto con nuestro dpto. de Operaciones. </font></td></tr>";
                htmlText = htmlText +"<br>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Atentamente, </font></td></tr>";
//                htmlText = htmlText +"<br>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'><b> Carset Servivios Integrales, S. L.<b> </font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>C.I.F.: 85750727 - Tlf: 91 268 69 60 - Fax: 91 268 69 64 - www.carset.es</font></td></tr>";
                htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Avda. Puente Cultural, 10 Bl.B - Pl .4 - Of. 4 - 28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
                htmlText = htmlText +"</table></body>";

                message.setContent(htmlText, "text/html");

                 /*BufferedWriter bw2 = null;
                    bw2 = new BufferedWriter(new FileWriter("c://mailProcesoProveedor.html", false));
                    bw2.write(htmlText);
                    bw2.close();*/


                // Lo enviamos.
                //Transport t = session.getTransport("smtp");
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