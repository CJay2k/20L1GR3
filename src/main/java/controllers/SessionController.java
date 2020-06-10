/**
 * Package controllers przechowuje klasy, odpowiadające za sterowanie naszą aplikacją.
 */

package controllers;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa SessionController odpowiada za zarządzanie sesja w naszej aplikacji.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-02
 */

public class SessionController {
    /**
     * Zmienna ourSessionFactory odnosi się do SessionFactory to klasa fabryczna, za pośrednictwem której uzyskujemy sesje i wykonujemy operacje na bazie danych.
     */
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

    /**
     * Metoda getSession Służy do pobrania i otworzenia naszej sesji.
     * @throws HibernateException Wyrzuca nam błąd, jeśli Hibernate ma problem z pobraniem naszej sesji.
     * @return ourSessionFactory.openSession() Służy do załadowania naszej sesji.
     */
    public static org.hibernate.Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}
