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
public class CSEnviarMailProveedor
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

         String query="SELECT DISTINCT pe.pe_num, pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino," +
                      " pe.pe_servicio, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial," +
                      " pe.pe_dias_campa, pe.pe_ida_vuelta, pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula," +
                      " pe.pe_ve_marca, pe.pe_ve_modelo, pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor, " +
                      " pe.pe_suplemento, pe.pe_num_en_camion, pe.pe_descripcion, tp.tp_tarifa, sp_entrada_campa," +
                      " sp_campa, sp_suplemento FROM pe_pedidos pe, pp_pedidos_proveedores pp, " +
                      " tp_tarifas_proveedores tp, sp_servicios_proveedores sp " +
                      " WHERE pe.pe_num = pp.pe_num AND sp.pr_id = pp.pr_id " +
                      " AND tp.tp_fecha_hasta > pe.pe_fecha AND tp.tp_servicio = pe.pe_servicio " +
                      " AND tp.pr_id = pp.pr_id " +
                      " AND (tp.tp_servicio_origen = pe.pe_servicio_origen OR tp.tp_servicio_origen = pe.pe_servicio_destino) " +
                      " AND (tp.tp_servicio_destino = pe.pe_servicio_destino OR tp.tp_servicio_destino = pe.pe_servicio_origen)" +
                      " AND tp.tp_soporte = pe.pe_soporte AND pe.pe_num ="+mail.getNumero();
            
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
                mail.setTarifa(rs_mail.getString("tp_tarifa"));
                mail.setEntradaCampa(rs_mail.getString("sp_entrada_campa"));
                mail.setCampa(rs_mail.getString("sp_campa"));
                mail.setSuplemento(rs_mail.getString("pe_suplemento"));
            }
            String queryContacto="SELECT * FROM CP_CONTACTOS_PROVEEDOR WHERE CP_ID="+mail.getClienteID()+" LIMIT 1";
            ResultSet rsContacto = CSDesktop.datos.select(queryContacto);

             while (rsContacto.next())  {
                 nombre=rsContacto.getString("cp_nombre");
                 email=rsContacto.getString("cp_email");
             }
           
            // Propiedades de la conexión
           Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            //props.put("mail.smtp.host", "smtp.e.telefonica.net");
            props.put("mail.smtp.host", "localhost");
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
             /*message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(email));*/
             message.addRecipient(
                Message.RecipientType.CC,
                new InternetAddress("cdecruz@yahoo.es"));
            message.setSubject("Resumen Estado Pedido " + mail.getNumPedido());
            String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";

            //String htmlText ="<br><br><center><table>";
            //htmlText = htmlText + "<tr><td><img src=\"http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg\" width='50%></td></tr></table>";
            String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
            "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
            "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
            "<br><br><table width='700'>" +
            "<tr><td width='100'><img src=\""+imagen+"\" width='100'></td>" +
            "<td><p><font face='Helvetica' size='+1'> CONFIRMACI&Oacute;N DE PEDIDO</p></font></td></tr>" +
            "<tr><td colspan='2'><br><br><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: "+nombre+"</font></td></tr>" +
            "<tr><td colspan='2'><br><br><font face='Helvetica'> A continuaci&oacute;n y en contestaci&oacute;n a su solicitud, le remitimos la confirmaci&oacute;n del servicio solicitado:  </font></td></tr>" +
            "<br><br>" +
            "<tr><td width='200'><font face='Helvetica'><u> Tipo de servicio : </u></font></td><td><font face='Helvetica'> Traslado servicio "+mail.getSoporte()+" </font></td></tr>" +
            "<tr><td width='200'><font face='Helvetica'><u> Fecha aprox. de recogida : </u></font></td><td><font face='Helvetica'> "+mail.getFechaRecogida()+" </font></td></tr>" +
            "<tr><td width='200'><font face='Helvetica'><u> Fecha aprox. de entrega : </u></font></td><td><font face='Helvetica'> "+mail.getFechaEntrega()+" </font></td></tr>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td width='100' bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MARCA</b></font></td><td width='300'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MODELO </b></font></td><td width='300'  bgcolor='#BDBDBD'><font face='Helvetica'><b>&nbsp;MATRICULA/BASTIDOR</b></font></td>" +
                                "<tr><td width='100'><font face='Helvetica'>&nbsp;"+mail.getMarca()+"</font></td><td width='300'><font face='Helvetica'>&nbsp;"+mail.getModelo()+"</font></td><td width='300'><font face='Helvetica'>&nbsp;"+mail.getMatricula()+"</font></td></table>" +
            "<br>" +
            "<tr><td colspan='2'><table border='1' width='700'>" +
                                "<tr><td  width='100' bgcolor='#BDBDBD'>&nbsp;</td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td  width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Direcci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Poblaci&oacute;n</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Provincia</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Contacto</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                                "<tr><td  width='100'><font face='Helvetica'><b>Tel&eacute;fono</b></font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>" +
            "</table></td></tr>" +
            "<br><br>" +
            "<tr><td colspan='2'><table width='400' border='1' align='right'>";
            if(mail.getDiasCampa().equals("0"))
            {
                //TARIFA
                if(mail.getTarifaEspecialProveedor().equals("0"))
                {
                    tarifa = mail.getTarifa();
                    /*if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";*/
                }
                else
                {
                    if (!mail.getServicioEspecial().equals("Otros"))
                        tarifa = mail.getTarifaEspecialCliente();
                    else
                        tarifa=mail.getTarifa();
                    /*if(soporte.equals("Camión completo") && !numCamion.equals("1"))
                        importeTraslado="0";*/
                }
                double importeTarifa = Double.parseDouble(tarifa);
                importeTarifa = Utilidades.redondear(importeTarifa, 2);
                String tarifa2=String.valueOf(importeTarifa);
                importeTotal = importeTotal + importeTarifa;
                //Escribimos la linea
                htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;TARIFA</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeTarifa+" &euro;</font></td></tr>";

                

                //SI TIENE IDA Y VUELTA
                if(mail.getIdaVuelta().equals("1"))
                {
                    String queryIv = "SELECT sc_ida_vuelta FROM sc_servicios_clientes WHERE cl_id = "+mail.getClienteID();

                    ResultSet rsIv = CSDesktop.datos.select(queryIv);
                    while (rsIv.next())
                    {
                        IdaVuelta = rsIv.getString("sc_ida_vuelta");
                    }
                    String textoIda="IDA-VUELTA ("+IdaVuelta+"%)";

                    double IdaVueltaP=Double.parseDouble(IdaVuelta);

                    double IdaVuelta2=(importeTarifa *IdaVueltaP)/100;

                    String importeIda=String.valueOf(IdaVuelta2);

                    htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;"+textoIda+"</font></td><td align='right'><font face='Helvetica'> - " +IdaVuelta2+" &euro;</font></td></tr>";

                    importeTotal = importeTotal - IdaVuelta2;
                }

                //FACTOR DE CORRECCION
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
                            double importeFc = ((importeTarifa * ft) - importeTarifa);
                            double nuevoImporteFactor=Utilidades.redondear(importeFc, 2);
                            String importeFactor = Double.toString(nuevoImporteFactor);
                            String factorTexto2=factorTarifa.get(0).toString();                            
                            htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;FACTOR DE CORRECCION </font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeFc+" &euro;</font></td></tr>";
                            importeTotal = importeTotal + nuevoImporteFactor;
                        }
                    }
                }
                
                //SERVICIO ESPECIAL
                if(!mail.getServicioEspecial().equals(""))
                {
                    double importeServicioEspecial=0;
                    importeServicioEs=Utilidades.CalcularImporteServicioEspecial(mail.getServicioEspecial(),mail.getClienteID(),mail.getFecha());                    
                    if(mail.getServicioEspecial().equals("Otros"))
                        importeServicioEspecial=Double.parseDouble(mail.getTarifaEspecialCliente());
                    else
                    {
                        importeServicioEspecial= Double.parseDouble(importeServicioEs);
                        htmlText = htmlText +  "<tr><td><font face='Helvetica'>"+mail.getServicioEspecial().toUpperCase()+"</font></td><td align='right'><font face='Helvetica'>"+importeServicioEs+" &euro;</font></td></tr>";
                    }
                    importeTotal = importeTotal + importeServicioEspecial;                   
                }

                //SUPLEMENTO
                if(!mail.getSuplemento().equals("0"))
                {
                    String ServicioSuplemento = mail.getDescripcion();
                    String importeSuplemento=mail.getSuplemento();
                    double importeSup = Double.parseDouble(importeSuplemento);
                    htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+ServicioSuplemento+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeSuplemento+" &euro;</font></td></tr>";
                    importeTotal = importeTotal + importeSup;
                }
            }
            else
            {
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

            //LINEA DE SERVICIO ESPECIAL OTROS
            if(!mail.getServicioEspecial().equals(""))
            {
                String labelOtros="";
                String importeServicioEsOtros="";
                if(mail.getServicioEspecial().equals("Otros"))
                {
                    labelOtros=mail.getDescripcion().toUpperCase();
                    double importeServicioOtros=Double.parseDouble(mail.getTarifaEspecialCliente());
                    importeServicioEsOtros=mail.getTarifaEspecialCliente();
                    htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;"+labelOtros+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeServicioEsOtros+" &euro;</font></td></tr>";
                    importeTotal = importeTotal + importeServicioOtros;
                }
                
            }          
            htmlText = htmlText +"<tr><td><font face='Helvetica'><b>&nbsp;TOTAL</b></font></td><td align='right'><font face='Helvetica'><b>&nbsp;"+importeTotal+" &euro;</b></font></td></tr>";
            htmlText = htmlText +"</table><br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><br><font face='Helvetica'> Estos precios no incluyen I.V.A </font></td></tr>";
            htmlText = htmlText +"<br><tr><td colspan='2'><br><br><font face='Helvetica'> Para cualquier consulta al respecto, no dude en ponerse en contacto con nuestro departamento de Operaciones (91.268.69.60). </font></td></tr>";
            htmlText = htmlText +"<br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><br><font face='Helvetica'>Atentamente, </font></td></tr>";
            htmlText = htmlText +"<br><br>";
            htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'><b> Departamento de Operaciones<b></font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' size='+1' color='#088A08'>CarSet</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Tlf: 91 268 69 60</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Movil: 681 022 122</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>Avda. Puente Cultural, 5 Bl.A - Pl .3 - Of. 2 </font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica'>28700 San Sebasti&aacute;n de los Reyes</font></td></tr>";
            htmlText = htmlText +"<tr><td colspan='2'><font face='Helvetica' color='#088A08'>www.carset.es</font></td></tr>";
            htmlText = htmlText +"</table></body>";

            message.setContent(htmlText, "text/html");


            // Lo enviamos.
            //Transport t = session.getTransport("smtp");
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());

            // Cierre.
            transport.close();

            JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail ha sido enviado correctamente.</FONT></HTML>");
            JOptionPane.showMessageDialog(null,mensaje);
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