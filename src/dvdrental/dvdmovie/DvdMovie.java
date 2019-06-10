package dvdrental.dvdmovie;

/**
 * Klasa będąca odpowiednikiem encji Film DVD z bazy danych
 */
public class DvdMovie {

    /**
     * Unikalny identyfikator płyty
     */
    private int id_płyty;

    /**
     * Polski tytuł filmu
     */
    private String polski_tytuł_filmu;

    /**
     * Oryginalny tytuł filmu
     */
    private String oryginalny_tytuł_filmu;

    /**
     * Rok produkcji
     */
    private int rok_produkcji;

    /**
     * Identyfikator klienta wypożyczającego aktualnie daną płytę (wartość równa 0 - płyta nie jest aktualnie wypożyczona)
     */
    private int id_klienta;

    /**
     * Konstruktor klasy DvdMovie
     */
    public DvdMovie() {
    }

    /**
     * Metoda służąca do pobrania identyfikatora płyty
     * @return identyfikator płyty
     */
    public int getId_płyty() {
        return id_płyty;
    }

    /**
     * Metoda służąca do ustawienia identyfikatora płyty
     * @param id_płyty nowy identyfikator płyty
     */
    public void setId_płyty(int id_płyty) {
        this.id_płyty = id_płyty;
    }

    /**
     * Metoda służąca do pobrania polskiego tytułu filmu
     * @return polski tytuł filmu
     */
    public String getPolski_tytuł_filmu() {
        return polski_tytuł_filmu;
    }

    /**
     * Metoda służąca do ustawienia polskiego tytułu filmu
     * @param polski_tytuł_filmu nowy polski tytuł filmu
     */
    public void setPolski_tytuł_filmu(String polski_tytuł_filmu) {
        this.polski_tytuł_filmu = polski_tytuł_filmu;
    }

    /**
     * Metoda służąca do pobrania oryginalnego tytułu filmu
     * @return oryginalny tytuł filmu
     */
    public String getOryginalny_tytuł_filmu() {
        return oryginalny_tytuł_filmu;
    }

    /**
     * Metoda służąca do ustawienia oryginalnego tytułu filmu
     * @param oryginalny_tytuł_filmu nowy oryginalny tytuł filmu
     */
    public void setOryginalny_tytuł_filmu(String oryginalny_tytuł_filmu) {
        this.oryginalny_tytuł_filmu = oryginalny_tytuł_filmu;
    }

    /**
     * Metoda służąca do pobrania roku produkcji filmu
     * @return rok produkcji filmu
     */
    public int getRok_produkcji() {
        return rok_produkcji;
    }

    /**
     * Metoda służąca do ustawienia roku produkcji filmu
     * @param rok_produkcji nowy rok produkcji filmu
     */
    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    /**
     * Metoda służąca do pobrania identyfikatora klienta aktualnia wypożyczającego daną płytę
     * @return identyfikator klienta aktualnie wypożyczającego daną płytę
     */
    public int getId_klienta() {
        return id_klienta;
    }

    /**
     * Metoda służąca do ustawienia identyfikatora klienta aktualnia wypożyczającego daną płytę
     * @param id_klienta nowy identyfikator klienta aktualnie wypożyczającego daną płytę
     */
    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }
}
