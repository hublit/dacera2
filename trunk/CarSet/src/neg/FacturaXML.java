/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neg;



import data.DbConnection;
import data.FacturasCesar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author depr102
 */
public class FacturaXML {
  

   public FacturaXML(String query) throws SQLException
   {

       DbConnection datos=new DbConnection();
       ResultSet rs = datos.select(query);
       ArrayList lista=new ArrayList();

       

       int i=0;
       String hola="raul";

        FileWriter fw = null;
            try {
                fw = new FileWriter("c:\\prueba.jrxml");
            } catch (IOException ex) {
                Logger.getLogger(FacturaXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter salida = new PrintWriter(bw);
     
        salida.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        salida.println("<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"report1\" language=\"groovy\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\">");
        salida.println("<parameter name=\"FechaFactura\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"NombreCliente\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"DireccionFiscal\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Direccion\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"PoblacionFiscal\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Poblacion\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"CodPostalFiscal\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"CodPostal\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"ProvinciaFiscal\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Provincia\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"CIF\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"NumFactura\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Anyo\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Query\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Blanco\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Factor\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"TipoServicio\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Servicio\" class=\"java.lang.String\"/>");
        salida.println("<parameter name=\"Importe\" class=\"java.lang.String\"/>");
        salida.println("<queryString><![CDATA[$P!{Query}]]></queryString>");
        salida.println("<field name=\"pe_num\" class=\"java.lang.Long\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_fecha\" class=\"java.sql.Date\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_descripcion\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_direccion_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_poblacion_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_provincia_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_cp_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_fecha_origen\" class=\"java.sql.Date\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_hora_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_tipo_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_nombre_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_telefono_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_direccion_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_poblacion_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_provincia_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_cp_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_fecha_destino\" class=\"java.sql.Date\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_hora_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_tipo_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_nombre_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_telefono_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_fuera_mad\" class=\"java.lang.Boolean\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_servicio\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_servicio_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_servicio_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_servicio_especial\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_dias_campa\" class=\"java.lang.Integer\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ida_vuelta\" class=\"java.lang.Boolean\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_soporte\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ve_matricula\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ve_marca\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ve_modelo\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_hora_real_origen\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_hora_real_destino\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_solred\" class=\"java.lang.Double\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_viaje\" class=\"java.lang.Double\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ta_es_cliente\" class=\"java.lang.Double\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_ta_es_proveedor\" class=\"java.lang.Double\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_suplemento\" class=\"java.lang.Double\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"fc_id\" class=\"java.lang.Integer\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_estado\" class=\"java.lang.String\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<field name=\"pe_activo\" class=\"java.lang.Boolean\"><fieldDescription><![CDATA[]]></fieldDescription></field>");
        salida.println("<background><band splitType=\"Stretch\"/></background>");
        salida.println("<title>");
        salida.println("<band height=\"192\" splitType=\"Stretch\">");
        salida.println("<image><reportElement x=\"17\" y=\"-1\" width=\"139\" height=\"80\"/><imageExpression class=\"java.lang.String\"><![CDATA[\"C:/Documents and Settings/depr102/Mis documentos/NetBeansProjects/CarSet/src/images/logo_carset_200.jpg\"]]></imageExpression></image>");
        salida.println("<staticText><reportElement x=\"249\" y=\"12\" width=\"188\" height=\"52\"/><textElement><font fontName=\"Verdana\" size=\"36\" isBold=\"true\" isUnderline=\"true\"/></textElement><text><![CDATA[FACTURA]]></text></staticText>");
        salida.println("<textField><reportElement x=\"45\" y=\"148\" width=\"100\" height=\"14\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"13\" y=\"148\" width=\"32\" height=\"14\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[]]></textFieldExpression></textField>");
        salida.println("<rectangle radius=\"10\"><reportElement x=\"0\" y=\"113\" width=\"220\" height=\"64\" backcolor=\"#FFFFFF\"/></rectangle>");
        salida.println("<staticText><reportElement x=\"29\" y=\"93\" width=\"100\" height=\"20\"/><textElement/><text><![CDATA[DOMICILIO FISCAL]]></text></staticText>");
        salida.println("<textField><reportElement x=\"29\" y=\"116\" width=\"100\" height=\"15\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{NombreCliente}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"271\" y=\"96\" width=\"126\" height=\"20\"/><textElement><font fontName=\"Verdana\" size=\"10\" isBold=\"true\" isUnderline=\"true\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{NombreCliente}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"29\" y=\"131\" width=\"100\" height=\"17\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{DireccionFiscal}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"271\" y=\"116\" width=\"284\" height=\"20\"/><textElement><font fontName=\"Verdana\" size=\"10\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Direccion}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"129\" y=\"131\" width=\"90\" height=\"17\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{PoblacionFiscal}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"29\" y=\"147\" width=\"33\" height=\"14\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{CodPostalFiscal}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"271\" y=\"136\" width=\"37\" height=\"20\"/><textElement><font fontName=\"Verdana\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{CodPostal}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"146\" width=\"106\" height=\"14\"/><textElement><font size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{ProvinciaFiscal}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"136\" width=\"146\" height=\"20\"/><textElement><font fontName=\"Verdana\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Provincia}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"29\" y=\"161\" width=\"100\" height=\"15\"/><textElement><font fontName=\"Verdana\" size=\"8\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{CIF}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"271\" y=\"155\" width=\"100\" height=\"20\"/><textElement><font fontName=\"Verdana\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{CIF}]]></textFieldExpression></textField>");
        salida.println("</band></title>");        
        salida.println("<pageHeader><band height=\"43\" splitType=\"Stretch\">");
        salida.println("<staticText><reportElement x=\"0\" y=\"0\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\"/></textElement><text><![CDATA[Fecha Factura:]]></text></staticText>");
        salida.println("<staticText><reportElement x=\"0\" y=\"20\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\"/></textElement><text><![CDATA[Nº Factura]]></text></staticText>");
        salida.println("<textField><reportElement x=\"83\" y=\"0\" width=\"100\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"10\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{FechaFactura}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"83\" y=\"20\" width=\"100\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\"/></textElement><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{NumFactura}]]></textFieldExpression></textField>");
        salida.println("</band></pageHeader>");
        salida.println("<columnHeader><band height=\"44\" splitType=\"Stretch\">");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"0\" y=\"24\" width=\"62\" height=\"20\" backcolor=\"#999999\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[Nº PEDIDO]]></text></staticText>");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"62\" y=\"24\" width=\"68\" height=\"20\" backcolor=\"#999999\"/><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[FECHA PEDIDO]]></text></staticText>");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"130\" y=\"24\" width=\"178\" height=\"20\" backcolor=\"#999999\"/><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[VEHICULO / TIPO SERVICIO]]></text></staticText>");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"308\" y=\"24\" width=\"83\" height=\"20\" backcolor=\"#999999\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[SERVICIO]]></text></staticText>");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"391\" y=\"24\" width=\"83\" height=\"20\" backcolor=\"#999999\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[SOPORTE]]></text></staticText>");
        salida.println("<staticText><reportElement mode=\"Opaque\" x=\"474\" y=\"24\" width=\"81\" height=\"20\" backcolor=\"#999999\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"><font fontName=\"Verdana\" size=\"8\"/></textElement><text><![CDATA[IMPORTE]]></text></staticText>");
        salida.println("</band></columnHeader>");


        salida.println("<detail><band height=\"121\" splitType=\"Stretch\"><elementGroup/>");
        
        salida.println("<textField><reportElement x=\"0\" y=\"0\" width=\"62\" height=\"20\"/><box><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Left\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[($F{pe_num})+\"/\"+($F{pe_fecha}.toString()).substring(2,4)]]></textFieldExpression></textField>");
        salida.println("<textField pattern=\"dd/MM/yyyy\"><reportElement x=\"62\" y=\"0\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.util.Date\"><![CDATA[$F{pe_fecha}]]></textFieldExpression></textField>");

        //Tengo que hacer la comprobación del factor de correcíon
        salida.println("<textField><reportElement x=\"130\" y=\"0\" width=\"261\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[\"Vehiculo :\"+ $F{pe_ve_marca} + $F{pe_ve_modelo} + \"(\" + $F{pe_ve_matricula} + \") -- \" + $P{Factor}]]></textFieldExpression></textField>");
       
        salida.println("<textField><reportElement x=\"391\" y=\"0\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$F{pe_soporte}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"0\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"0\" y=\"0\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"0\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");

      
        salida.println("<textField><reportElement x=\"0\" y=\"20\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"20\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"130\" y=\"20\" width=\"178\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{TipoServicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"20\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Servicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"391\" y=\"20\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"20\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Importe}]]></textFieldExpression></textField>");

        salida.println("<textField><reportElement x=\"0\" y=\"40\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"40\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"130\" y=\"40\" width=\"178\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{TipoServicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"40\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Servicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"391\" y=\"40\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"40\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Importe}]]></textFieldExpression></textField>");

        salida.println("<textField><reportElement x=\"0\" y=\"60\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"60\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"130\" y=\"60\" width=\"178\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{TipoServicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"60\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Servicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"391\" y=\"60\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"60\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Importe}]]></textFieldExpression></textField>");

        salida.println("<textField><reportElement x=\"0\" y=\"80\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"80\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"130\" y=\"80\" width=\"178\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{TipoServicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"80\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Servicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"391\" y=\"80\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"80\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Importe}]]></textFieldExpression></textField>");


        salida.println("<textField><reportElement x=\"0\" y=\"100\" width=\"62\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"62\" y=\"100\" width=\"67\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"130\" y=\"100\" width=\"178\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{TipoServicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"308\" y=\"100\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Servicio}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"391\" y=\"100\" width=\"83\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Blanco}]]></textFieldExpression></textField>");
        salida.println("<textField><reportElement x=\"474\" y=\"100\" width=\"81\" height=\"20\"/><box><pen lineWidth=\"1.0\"/><topPen lineWidth=\"1.0\"/><leftPen lineWidth=\"1.0\"/><bottomPen lineWidth=\"1.0\"/><rightPen lineWidth=\"1.0\"/></box><textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/><textFieldExpression class=\"java.lang.String\"><![CDATA[$P{Importe}]]></textFieldExpression></textField>");
     

        salida.println("</band></detail>");
      
        salida.println("<columnFooter><band height=\"45\" splitType=\"Stretch\"/></columnFooter>");
        salida.println("<pageFooter><band height=\"54\" splitType=\"Stretch\">");
        salida.println("</band></pageFooter>");
        salida.println("</jasperReport>");



        salida.close();
        
       System.out.println(i);

   
    }
}
