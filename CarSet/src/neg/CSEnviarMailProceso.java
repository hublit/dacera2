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


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

public class CSEnviarMailProceso
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */

   

    public static void main(BeanCorreoCliente mail,String email, String nombre)
    {
        String tarifa="";
        String importeServicioEs="";
        String IdaVuelta="";
        double importeTotal=0;

        try
        {

            String query="SELECT DISTINCT"+
            " pe.pe_num,pe.pe_fecha, pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio," +
            " pe.pe_servicio_origen, pe.pe_servicio_destino, pe.pe_servicio_especial, pe.pe_dias_campa,"+
            " pe.pe_ida_vuelta,pe.fc_id, pe.pe_soporte, pe.pe_ve_matricula, pe.pe_ve_marca, pe.pe_ve_modelo," +
            " pe.pe_ta_es_cliente, pe.pe_ta_es_proveedor,pe.pe_suplemento,pe.pe_num_en_camion, pe.pe_descripcion," +
            " tc.tc_tarifa, sc_entrada_campa, sc_campa" +
            " FROM pe_pedidos pe, pc_pedidos_clientes pc, tc_tarifas_clientes tc, sc_servicios_clientes sc" +
            " WHERE pe.pe_num = pc.pe_num AND sc.cl_id = pc.cl_id AND tc.tc_fecha_hasta > pe.pe_fecha" +
            " AND sc.sc_fecha_hasta > pe.pe_fecha AND tc.tc_servicio = pe.pe_servicio AND tc.cl_id = pc.cl_id" +
            " AND (tc.tc_servicio_origen = pe.pe_servicio_origen OR tc.tc_servicio_origen = pe.pe_servicio_destino)" +
            " AND (tc.tc_servicio_destino = pe.pe_servicio_destino OR tc.tc_servicio_destino = pe.pe_servicio_origen)" +
            "AND tc.tc_soporte = pe.pe_soporte AND pe.pe_num="+mail.getNumero();
            
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

            if(rs_mail != null)
            {
                //Se recoge el mail y el contacto del cliente
                /*String queryContacto="SELECT * FROM CC_CONTACTOS_CLIENTE WHERE CL_ID="+mail.getClienteID()+" LIMIT 1";
                ResultSet rsContacto = CSDesktop.datos.select(queryContacto);

                while (rsContacto.next())  {
                    nombre=rsContacto.getString("cc_nombre");
                    email=rsContacto.getString("cc_email");
                }*/

           
                // Propiedades de la conexión
                Properties props = new Properties();
                props.put("mail.transport.protocol","smtp");
                props.put("mail.smtp.host", "smtp.e.telefonica.net");
                //props.put("mail.smtp.host", "localhost");
                //props.put("mail.smtp.starttls.enable", "false");
                props.put("mail.smtp.port", "25");
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
                String imagen = "http://www.advillaverdebajo.com/CarSet/logo_carset_200.jpg";

           
                String htmlText = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>" +
                    "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='es'><head>" +
                    "<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-15' /></head><body>" +
                    "<br><br><table width='700'>" +
                    "<tr><td width='100'><img src=\""+imagen+"\" width='100'></td>" +
                    "<td><p><font face='Helvetica' size='+1'> CONFIRMACI&Oacute;N DE PEDIDO</p></font></td></tr>" +
                    "<tr><td colspan='2'><br><br><table><tr><td width='100'><font face='Helvetica'>Para:</font></td><td><font face='Helvetica'>"+mail.getCliente()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Fecha:</font></td><td><font face='Helvetica'>"+mail.getFecha()+"</font></td></tr><tr><td width='100'><font face='Helvetica'>Nº Pedido:</font></td><td><font face='Helvetica'>"+mail.getNumPedido()+"</font></td></tr></table></td></tr>" +
                    "<tr><td colspan='2'><br><br><font face='Helvetica'> Estimado Sr./Sra.: "+nombre+"</font>" +
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
                    "<tr><td width='100' bgcolor='#BDBDBD'>&nbsp;</td><td width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE ORIGEN</b></font></td><td width='300' bgcolor='#BDBDBD'><font face='Helvetica'><b>DATOS DE DESTINO</b></font></td>" +
                    "<tr><td width='100'><font face='Helvetica'><b>&nbsp;Direcci&oacute;n</b></font></td><td width='300'><font face='Helvetica'>"+mail.getDireccionOrigen()+"</font></td><td width='300'><font face='Helvetica'>"+mail.getDireccionDestino()+"</font></td>" +
                    "<tr><td width='100'><font face='Helvetica'><b>&nbsp;Poblaci&oacute;n</b></font></td><td width='300'><font face='Helvetica'>"+mail.getPoblacionOrigen()+"</font></td><td width='300'><font face='Helvetica'>"+mail.getPoblacionDestino()+"</font></td>" +
                    "<tr><td width='100'><font face='Helvetica'><b>&nbsp;Provincia</b></font></td><td width='300'><font face='Helvetica'>"+mail.getProvinciaOrigen()+"</font></td><td width='300'><font face='Helvetica'>"+mail.getProvinciaDestino()+"</font></td>" +
                    "<tr><td width='100'><font face='Helvetica'><b>&nbsp;Contacto</b></font></td><td width='300'><font face='Helvetica'>"+mail.getNombreOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getNombreDestino()+"</font></td>" +
                    "<tr><td width='100'><font face='Helvetica'><b>&nbsp;Tel&eacute;fono</b></font></td><td width='300'><font face='Helvetica'>"+mail.getTelefonoOrigen()+"</font></td><td  width='300'><font face='Helvetica'>"+mail.getTelefonoDestino()+"</font></td>" +
                    "</table></td></tr>" +
                    "<br><br>" +
                    "<tr><td colspan='2'><table width='400' border='1' align='right'>";

                /*if(mail.getDiasCampa().equals("0"))
                {
                    //TARIFA
                    if(mail.getTarifaEspecialCliente().equals("-1"))
                    {
                        tarifa = mail.getTarifa();

                    }
                    else
                    {
                        if (!mail.getServicioEspecial().equals("Otros"))
                            tarifa = mail.getTarifaEspecialCliente();
                        else
                            tarifa=mail.getTarifa();
                    
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
                                htmlText = htmlText + "<tr><td><font face='Helvetica'> FACTOR DE CORRECCION </font></td><td align='right'><font face='Helvetica'>"+importeFc+" &euro;</font></td></tr>";
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
                            htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getServicioEspecial().toUpperCase()+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeServicioEs+" &euro;</font></td></tr>";
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
                
                }*/
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
                    htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;TARIFA</font></td><td align='right' width='80'><font face='Helvetica'>&nbsp;"+importeTrasladoD+" &euro;</font></td></tr>";

                    //SERVICIO ESPECIAL
                    if(!mail.getServicioEspecial().equals(""))
                    {
                        if(!mail.getServicioEspecial().equals("Otros"))
                        {
                            String importeServicio=Utilidades.CalcularImporteServicioEspecial(mail.getServicioEspecial(),mail.getClienteID(),mail.getFecha());
                            if(!importeServicio.equals(""))
                            {
                                double importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                                //String servicioEspecial=mail.getServicioEspecial().toUpperCase();
                                //String labelServicioEspecial="SERVICIO ESPECIAL";
                                htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getServicioEspecial().toUpperCase()+"</font></td><td align='right' width='80'><font face='Helvetica'>&nbsp;"+importeServicioEs+" &euro;</font></td></tr>";
                                importeTotal = importeTotal + importeServicioD;
                            }                            
                        }
                        else
                        {
                            htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getDescripcion().toUpperCase()+"</font></td><td align='right' width='80'><font face='Helvetica'>&nbsp;</font></td></tr>";
                            //labelOtros=beanFactura.getDescripcion().toUpperCase();
                        }
                    }
                    // FACTOR DE CORRECCION
                    //ArrayList factorTarifa = Utilidades.obtenerFactor(mail.getFactorCorrecccion(), mail.getClienteID());
                    //factorTexto = factorTarifa.get(0).toString();
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
                            String importeServicio=Utilidades.CalcularImporteServicioEspecial(mail.getServicioEspecial(),mail.getClienteID(),mail.getFecha());
                            if(!importeServicio.equals(""))
                            {
                                double importeServicioD = Double.parseDouble(importeServicio);
                                importeServicioD = Utilidades.redondear(importeServicioD, 2);
                                //servicioEspecial=beanFactura.getServicioEspecial().toUpperCase();
                                //labelServicioEspecial="SERVICIO ESPECIAL";
                                htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+mail.getServicioEspecial().toUpperCase()+"</font></td><td align='right'><font face='Helvetica'>&nbsp;"+importeServicioEs+" &euro;</font></td></tr>";
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

                        double IdaVuelta2=(importeTrasladoD *IdaVueltaP)/100;

                        String importeIda=String.valueOf(IdaVuelta2);

                        htmlText = htmlText + "<tr><td><font face='Helvetica'>&nbsp;"+textoIda+"</font></td><td align='right'><font face='Helvetica'> - " +IdaVuelta2+" &euro;</font></td></tr>";

                        importeTotal = importeTotal - IdaVuelta2;
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
                    //SUPLEMENTO
                    if(!mail.getSuplemento().equals("0"))
                    {
                        String ServicioSuplemento = mail.getDescripcion();
                        String importeSuplemento=mail.getSuplemento();
                        double importeSup = Double.parseDouble(importeSuplemento);
                        htmlText = htmlText +  "<tr><td><font face='Helvetica'>&nbsp;"+ServicioSuplemento+"</font></td><td align='right' width='80'><font face='Helvetica'>&nbsp;"+importeSuplemento+" &euro;</font></td></tr>";
                        importeTotal = importeTotal + importeSup;
                    }
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
                /*if(!mail.getServicioEspecial().equals(""))
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

                }*/





                htmlText = htmlText +"<tr><td><font face='Helvetica'><b>&nbsp;TOTAL</b></font></td><td align='right'><font face='Helvetica'><b>&nbsp;"+importeTotal+" &euro;</b></font></td></tr>";
                htmlText = htmlText +"</table><br>";
                htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'> Estos precios no incluyen I.V.A </font></td></tr>";
                htmlText = htmlText +"<br><tr><td colspan='2'><br><font face='Helvetica'> Para cualquier consulta al respecto, no dude en ponerse en contacto con nuestro departamento de Operaciones (91.268.69.60). </font></td></tr>";
                htmlText = htmlText +"<br>";
                htmlText = htmlText +"<tr><td colspan='2'><br><font face='Helvetica'>Atentamente, </font></td></tr>";
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

                // PARA PRUEBAS EN SPEE, YA QUE NO SE PUEDE MANDAR UN MAIL, LO SACA A FICHERO
                /*BufferedWriter bw2 = null;
                bw2 = new BufferedWriter(new FileWriter("c://mailProcesoCliente.html", false));
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
                JLabel mensaje = new JLabel("<HTML><FONT COLOR = Blue>El e-mail no ha podido ser enviado.</FONT></HTML>");
                JOptionPane.showMessageDialog(null,mensaje);
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