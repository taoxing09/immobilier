package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

 
public class PDFGenerator {
 
    public static void generate(List<Bien> biens, String filename) throws IOException, DocumentException {
 
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
 
        Font font = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
 
        Paragraph title = new Paragraph("Liste des biens", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
 
        for (Bien bien : biens) {
            Paragraph p = new Paragraph();
            p.add(new Paragraph("Adresse: " + bien.getAdresse()));
            p.add(new Paragraph("Surface: " + bien.getSuperficie() + " m²"));
            p.add(new Paragraph("Loyer: " + bien.getLoyer().getMontant() + " €"));
            p.add(new Paragraph("Locataires: " + bien.getLocataires()));
            document.add(p);
        }
 
        document.close();
    }
 
}