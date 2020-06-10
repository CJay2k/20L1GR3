/**
 * Package utils, specjalna paczka stworzona do przechowywania plików, odnoszących się do generowania raportów.
 */

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

/**
 * Klasa PdfGenerator jest to klasa obsługująca generowanie pdfów.
 *
 * @author Paweł Kolano
 * @version 1.0
 * @since   2020-05-18 *
 *
 */




public class PdfGenerator {


    /**
     * Metoda PdfPTable ma za zadanie stworzenie tabeli przechowującej dane o ocenach ucznia.
     * @param listaOcen Jest to lista, która przechowuje informacje o ocenach.
     * @return table Zwraca nam tabele, w której przechowuje informacje z listy.
     */

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

        for (Oceny oceny : listaOcen) {
            table.addCell(oceny.getData().toString());
            table.addCell(oceny.getPrzedmiotyByPrzedmiotId().toString());
            table.addCell(oceny.getWartosc());
            table.addCell(oceny.getOpis());
        }
        return table;
    }

    /**
     * Metoda PDFGenerateGrades ma za zadanie stworzenie tabeli, która będzie w raporcie zapisanym w odpowiednim pliku pdf.
     * @param listaOcen Jest to lista, która przechowuje informacje trafiające do tabeli.
     * @throws IOException Występuje w przypadku braku uprawnień.
     * @throws DocumentException Występuje w przypadku błędów w dokumencie.
     */

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