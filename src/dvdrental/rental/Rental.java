package dvdrental.rental;

import java.sql.Date;

/**
 * Klasa będąca odpowiednikiem encji Wypożyczenie z bazy danych.
 */
public class Rental {

    /**
     * Data wypożyczenia filmu
     */
    private java.sql.Date data_wypożyczenia;

    /**
     * Data zwrotu filmu (brak daty - niezwrócony)
     */
    private java.sql.Date data_zwrotu;

    /**
     * Identyfikator wypożyczonej płyty
     */
    private int filmy_dvd_id_płyty;

    /**
     * Identyfikator klienta wypożyczającego płytę
     */
    private int klienci_id_klienta;

    /**
     * Konstruktor klasy Rental
     */
    public Rental() {
    }

    /**
     * Metoda służąca do pobrania daty wypożyczenia filmu
     * @return data wypożyczenia filmu
     */
    public Date getData_wypożyczenia() {
        return data_wypożyczenia;
    }

    /**
     * Metoda służąca do ustawienia daty wypożyczenia filmu
     * @param data_wypożyczenia nowa data wypożyczenia filmu
     */
    public void setData_wypożyczenia(Date data_wypożyczenia) {
        this.data_wypożyczenia = data_wypożyczenia;
    }

    /**
     * Metoda służąca do pobrania daty zwrotu filmu
     * @return data zwrotu filmu
     */
    public Date getData_zwrotu() {
        return data_zwrotu;
    }

    /**
     * Metoda służąca do ustawienia daty zwrotu filmu
     * @param data_zwrotu nowa data zwrotu filmu
     */
    public void setData_zwrotu(Date data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    /**
     * Metoda służąca do pobrania identyfikatora wypożyczonej płyty
     * @return identyfikator wypożyczonej płyty
     */
    public int getFilmy_dvd_id_płyty() {
        return filmy_dvd_id_płyty;
    }

    /**
     * Metoda służąca do ustawienia identyfikatora wypożyczonej płyty
     * @param filmy_dvd_id_płyty nowy identyfikator wypożyczonej płyty
     */
    public void setFilmy_dvd_id_płyty(int filmy_dvd_id_płyty) {
        this.filmy_dvd_id_płyty = filmy_dvd_id_płyty;
    }

    /**
     * Metoda służąca do pobrania identyfikatora klienta wypożyczającego płytę
     * @return identyfikator klienta wypożyczającego płytę
     */
    public int getKlienci_id_klienta() {
        return klienci_id_klienta;
    }

    /**
     * Metoda służąca do ustawienia identyfikatora klienta wypożyczającego płytę
     * @param klienci_id_klienta nowy identyfikator klienta wypożyczającego płytę
     */
    public void setKlienci_id_klienta(int klienci_id_klienta) {
        this.klienci_id_klienta = klienci_id_klienta;
    }
}
