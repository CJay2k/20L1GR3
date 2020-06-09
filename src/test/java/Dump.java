import java.io.IOException;

public class Dump {

    public static void main(final String[] args) throws Exception {
        loadDump();
    }

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