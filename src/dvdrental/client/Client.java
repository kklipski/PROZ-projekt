package dvdrental.client;

/**
 * Klasa będąca odpowiednikiem encji Klient z bazy danych.
 */
public class Client {

    /**
     * Unikalny identyfikator klienta
     */
    private int id_klienta;

    /**
     * Imię klienta
     */
    private String imię;

    /**
     * Nazwisko klienta
     */
    private String nazwisko;

    /**
     * Adres klienta
     */
    private String adres;

    /**
     * Numer telefonu klienta
     */
    private int numer_telefonu;

    /**
     * Liczba niezwróconych przez klienta płyt
     */
    private int liczba_wypożyczeń;

    /**
     * Konstruktor klasy Client
     */
    public Client() {
    }

    /**
     * Metoda służąca do pobrania identyfikatora klienta
     * @return identyfikator klienta
     */
    public int getId_klienta() {
        return id_klienta;
    }

    /**
     * Metoda służąca do ustawienia identyfikatora klienta
     * @param id_klienta nowy identyfikator klienta
     */
    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    /**
     * Metoda służąca do pobrania imienia klienta
     * @return imię klienta
     */
    public String getImię() {
        return imię;
    }

    /**
     * Metoda służąca do ustawienia imienia klienta
     * @param imię nowe imię klienta
     */
    public void setImię(String imię) {
        this.imię = imię;
    }

    /**
     * Metoda służąca do pobrania nazwiska klienta
     * @return nazwisko klienta
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Metoda służąca do ustawienia nazwiska klienta
     * @param nazwisko nowe nazwisko klienta
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Metoda służąca do pobrania adresu klienta
     * @return adres klienta
     */
    public String getAdres() {
        return adres;
    }

    /**
     * Metoda służąca do ustawienia adresu klienta
     * @param adres nowy adres
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * Metoda służąca do pobrania numeru telefonu klienta
     * @return numer telefonu klienta
     */
    public int getNumer_telefonu() {
        return numer_telefonu;
    }

    /**
     * Metoda służąca do ustawienia numeru telefonu klienta
     * @param numer_telefonu nowy numer telefonu
     */
    public void setNumer_telefonu(int numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    /**
     * Metoda służąca do pobrania liczby niezwróconych przez klienta płyt
     * @return liczba niezwróconych przez klienta płyt
     */
    public int getLiczba_wypożyczeń() {
        return liczba_wypożyczeń;
    }

    /**
     * Metoda służąca do ustawienia liczby niezwróconych przez klienta płyt
     * @param liczba_wypożyczeń nowa liczba niezwróconych przez klienta płyt
     */
    public void setLiczba_wypożyczeń(int liczba_wypożyczeń) {
        this.liczba_wypożyczeń = liczba_wypożyczeń;
    }
}
