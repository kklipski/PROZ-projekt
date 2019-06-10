package dvdrental;

import dvdrental.client.Client;
import dvdrental.client.ClientDAO;
import dvdrental.dvdmovie.DvdMovie;
import dvdrental.dvdmovie.DvdMovieDAO;
import dvdrental.rental.Rental;
import dvdrental.rental.RentalDAO;

import java.util.Calendar;
import java.util.List;

/**
 * Klasa reprezentująca moduł 'Model' w aplikacji
 */
public class DvdRental {

    /**
     * Dostęp do DAO płyty DVD
     */
    private DvdMovieDAO dvdMovieDAO;

    /**
     * Dostęp do DAO klienta
     */
    private ClientDAO clientDAO;

    /**
     * Dostęp do DAO wypożyczenia
     */
    private RentalDAO rentalDAO;

    /**
     * Konstruktor klasy DvdRental (modelu)
     * @param dvdMovieDAO DAO płyty DVD
     * @param clientDAO DAO klienta
     * @param rentalDAO DAO wypożyczenia
     */
    public DvdRental(DvdMovieDAO dvdMovieDAO, ClientDAO clientDAO, RentalDAO rentalDAO) {
        this.dvdMovieDAO = dvdMovieDAO;
        this.clientDAO = clientDAO;
        this.rentalDAO = rentalDAO;
        try {
            dvdMovieDAO.connect();
            clientDAO.connect();
            rentalDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda modelu służąca do dodawania do bazy danych nowego filmu DVD
     * @param polski_tytuł_filmu polski tytuł dodawanego filmu
     * @param oryginalny_tytuł_filmu oryginalny tytuł dodawanegi filmu
     * @param rok_produkcji rok produkcji dodawanego filmu
     */
    public void addNewDvd(String polski_tytuł_filmu, String oryginalny_tytuł_filmu, int rok_produkcji) {
        DvdMovie dvdMovie = new DvdMovie();
        dvdMovie.setPolski_tytuł_filmu(polski_tytuł_filmu);
        dvdMovie.setOryginalny_tytuł_filmu(oryginalny_tytuł_filmu);
        dvdMovie.setRok_produkcji(rok_produkcji);

        dvdMovieDAO.insertDvdMovie(dvdMovie);
    }

    /**
     * Metoda modelu służąca do dodawania do bazy danych nowego klienta
     * @param imię imię dodawanego klienta
     * @param nazwisko nazwisko dodawanego klienta
     * @param adres adres dodawanego klienta
     * @param numer_telefonu numer telefonu dodawanego klienta
     */
    public void addNewClient(String imię, String nazwisko, String adres, int numer_telefonu) {
        Client client = new Client();
        client.setImię(imię);
        client.setNazwisko(nazwisko);
        client.setAdres(adres);
        client.setNumer_telefonu(numer_telefonu);

        clientDAO.insertClient(client);
    }

    /**
     * Metoda modelu służąca do edytowania danych klienta znajdującego się w bazie danych
     * @param id_klienta identyfikator edytowanego klienta
     * @param adres nowy adres edytowanego klienta
     * @param numer_telefonu nowy numer telefonu edytowanego klienta
     */
    public void updateClient(int id_klienta, String adres, int numer_telefonu) {
        List<Client> clients = clientDAO.findClientById(id_klienta);
        clients.get(0).setAdres(adres);
        clients.get(0).setNumer_telefonu(numer_telefonu);

        clientDAO.updateClient(clients.get(0));
    }

    /**
     * Metoda modelu służąca do dodawania do bazy danych nowego wypożyczania, czyli wypożyczania filmu
     * @param filmy_dvd_id_płyty identyfikator wypożyczanego filmu
     * @param klienci_id_klienta identyfikator klienta wypożyczającego płytę
     */
    public void rentDvd(int filmy_dvd_id_płyty, int klienci_id_klienta) {
        Rental rental = new Rental();
        java.sql.Date data_wypożyczenia = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        rental.setData_wypożyczenia(data_wypożyczenia);
        rental.setFilmy_dvd_id_płyty(filmy_dvd_id_płyty);
        rental.setKlienci_id_klienta(klienci_id_klienta);

        rentalDAO.insertRental(rental);
    }

    /**
     * Metoda modelu służąca do edytowania danych wypożyczenia znajdującego się w bazie danych, czyli zwrotu płyty
     * @param filmy_dvd_id_płyty identyfikator zwracanej płyty
     */
    public void returnDvd(int filmy_dvd_id_płyty) {
        List<Rental> rentals = rentalDAO.findRentalByDvdId(filmy_dvd_id_płyty);
        java.sql.Date data_zwrotu = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        rentals.get(0).setData_zwrotu(data_zwrotu);

        rentalDAO.updateRental(rentals.get(0));
    }

    /**
     * Metoda modelu służąca do pobierania wszystkich filmów DVD z bazy danych
     * @return lista filmów DVD
     */
    public List<DvdMovie> getAllDvds() {
        return dvdMovieDAO.findAll();
    }

    /**
     * Metoda modelu służąca do pobierania wszystkich klientów z bazy danych
     * @return lista klientów
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    /**
     * Metoda modelu służąca do pobrania z bazy danych klienta o wskazanym identyfikatorze
     * @return (jednoelementowa) lista klientów o wskazanym identyfikatorze
     */
    public List<Client> getClientById(int clientId) {
        return clientDAO.findClientById(clientId);
    }

    /**
     * Metoda modelu służąca do zamknięcia wszystkich połączeń z bazą danych
     */
    public void close() {
        try {
            dvdMovieDAO.close();
            clientDAO.close();
            rentalDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
