package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import mapping.Oceny;

import java.io.FileOutputStream;
import java.io.IOException;


public class PdfGenerator {
    private static PdfPTable createTable(ObservableList<Oceny> listaOcen) {
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Data"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Przedmiot"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Ocena"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Opis"));
        table.addCell(cell);

        for (int i = 0; i < listaOcen.size(); i++) {
            table.addCell(listaOcen.get(i).getData().toString());
            table.addCell(listaOcen.get(i).getPrzedmiotyByPrzedmiotId().toString());
            table.addCell(listaOcen.get(i).getWartosc());
            table.addCell(listaOcen.get(i).getOpis());
        }
        return table;
    }

    public void PDFGenerateGrades(ObservableList<Oceny> listaOcen) throws IOException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("raport.pdf"));
        doc.open();


        doc.add(new Paragraph("Wykaz ocen ucznia"));
        doc.add(new Paragraph("\n"));
        doc.add(createTable(listaOcen));
        doc.close();
    }
}