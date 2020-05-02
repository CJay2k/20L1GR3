import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.io.IOException;

public class Dump {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }

//        loadDump();
    }

    public static void loadDump(){
        try (Session session = getSession()) {

            System.out.println("Czyszczenie bazy danych...");
            session.beginTransaction();

            try {
                session.createNativeQuery("ALTER TABLE uczen DROP FOREIGN KEY FKhhbcs24yp2d32ecuphf8sit2b").executeUpdate();

                session.createNativeQuery("delete from autoryzacja").executeUpdate();
                session.createNativeQuery("delete from klasa").executeUpdate();
                session.createNativeQuery("delete from nauczyciel").executeUpdate();
                session.createNativeQuery("delete from obecnosc").executeUpdate();
                session.createNativeQuery("delete from ocena").executeUpdate();
                session.createNativeQuery("delete from przedmiot").executeUpdate();
                session.createNativeQuery("delete from rodzaj_oceny").executeUpdate();
                session.createNativeQuery("delete from rodzic").executeUpdate();
                session.createNativeQuery("delete from sklad_klasy").executeUpdate();
                session.createNativeQuery("delete from uczen").executeUpdate();
                session.createNativeQuery("delete from zajecia").executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }

            session.getTransaction().commit();

            System.out.println("Uzupełnianie bazy danych...");

            ProcessBuilder pb;
            if(System.getProperty("os.name").toLowerCase().contains("win")){
                pb = new ProcessBuilder("mysql --host=54.38.143.86 --user=ediary --password='ZAQ!2wsx' dziennik_elektroniczny < dump.sql");
            }else{
                pb = new ProcessBuilder("bash", "-c", "mysql --host=54.38.143.86 --user=ediary --password='ZAQ!2wsx' dziennik_elektroniczny < dump.sql");
            }
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();

            System.out.println("Proces przebiegł pomyślnie!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}