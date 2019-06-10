package dvdrental.client;

import dvdrental.DAO;

import java.util.List;

/**
 * Interfejs do tworzenia DAO dla klienta
 */
public interface ClientDAO extends DAO {

    /**
     * Deklaracja metody służącej do umieszczania klienta w bazie danych
     * @param client dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    boolean insertClient(Client client);

    /**
     * Deklaracja metody służącej do edytowania danych klienta z bazy danych
     * @param client nowe dane, które zostaną wstawione w miejsce aktualnych danych klienta
     * @return true jeśli się powiodło, false w p.p.
     */
    boolean updateClient (Client client);

    /**
     * Deklaracja metody służącej do szukania klientów w bazie danych po unikalnym identyfikatorze
     * @param clientId identyfikator szukanego klienta
     * @return szukany klient (jednoelementowa lista)
     */
    List<Client> findClientById(int clientId);

    /**
     * Deklaracja metody służącej do znajdowania wszystkich klientów w bazie danych
     * @return lista klientów
     */
    List<Client> findAll();
}
