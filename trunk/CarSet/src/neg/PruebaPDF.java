/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neg;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PruebaPDF {
  public static void main(String[] args) {
    System.out.println("http://www.seraphinux.com/");
    Document documento = new Document();
    try {
        PdfWriter.getInstance(documento,
            new FileOutputStream("carset.pdf"));
        documento.open();
        Image gif = Image.getInstance("src\\images\\logo_carset_200.jpg");
        gif.scalePercent(50);
        gif.setAlignment(Image.RIGHT);
        documento.add(gif);

        Paragraph membrete=new Paragraph("FACTURA",new Font(Font.HELVETICA, 18, Font.UNDERLINE));
        membrete.setAlignment("CENTER");
        documento.add(membrete);
    } catch (DocumentException de) {
        System.err.println(de.getMessage());
    } catch (IOException ioe) {
        System.err.println(ioe.getMessage());
    }
    documento.close();
  }
}