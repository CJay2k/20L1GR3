import java.io.IOException;

/**
 * Klasa Dump odpowiada za wyczyszczenie i ponowne załadowanie danych do bazy.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-02
 *
 */
public class Dump {

    /**
     * Metoda main jest to główna metoda naszej klasy.
     * @param args Parametr przechowujący liste argumentów.
     * @throws Exception Wyrzuci nam kiedy będą błędne dane do logowania w bazie.
     */
    public static void main(final String[] args) throws Exception {
        loadDump();
    }

    /**
     * Metoda loadDump służy do czyszczenia i uzupełniania bazy danych.
     * @throws IOException Wystąpi wówczas, gdy nie znajdzie pliku, bądź nie będzie miało dostępu.
     * @throws InterruptedException Wystąpi wówczas, gdy proces ładowania zostanie przerwany.
     */
    public static void loadDump() throws IOException, InterruptedException {

        System.out.println("Czyszczenie bazy danych...");
        System.out.println("Uzupełnianie bazy danych...");

        ProcessBuilder pb;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            pb = new ProcessBuilder("mysql --host=54.38.143.86 --user=ediary --password='ZAQ!2wsx' edziennik < dump.sql");
        } else {
            pb = new ProcessBuilder("bash", "-c", "mysql --host=54.38.143.86 --user=ediary --password='ZAQ!2wsx' edziennik < dump.sql");
        }
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();

        System.out.println("Proces przebiegł pomyślnie!");
    }
}