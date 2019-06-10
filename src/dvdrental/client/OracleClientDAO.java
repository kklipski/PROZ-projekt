package dvdrental.client;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

/**
 * Klasa implementująca interfejs ClientDAO dla bazy danych Oracle
 */
public class OracleClientDAO implements ClientDAO {

    /**
     * Połączenie z bazą danych
     */
    private Connection connection;

    /**
     * Obiekt typu QueryRunner pozwala wykonywać niektóre operacje na bazie danych w prostszy sposób
     */
    private QueryRunner dbAccess = new QueryRunner();

    /**
     * Metody służąca do umieszczania klienta w bazie danych
     * @param client dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    @Override
    public boolean insertClient(Client client) {
        try {
            String query = "INSERT INTO KLIENCI (imię, nazwisko, adres, numer_telefonu) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getImię());
            preparedStatement.setString(2, client.getNazwisko());
            preparedStatement.setString(3, client.getAdres());
            preparedStatement.setInt(4, client.getNumer_telefonu());
            preparedStatement.executeQuery();
            preparedStatement.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metoda służąca do edytowania danych klienta z bazy danych
     * @param client nowe dane, które zostaną wstawione w miejsce aktualnych danych klienta
     * @return true jeśli się powiodło, false w p.p.
     */
    @Override
    public boolean updateClient(Client client) {
        try {
            String query = "UPDATE KLIENCI SET adres=?, numer_telefonu=? WHERE id_klienta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getAdres());
            preparedStatement.setInt(2, client.getNumer_telefonu());
            preparedStatement.setInt(3, client.getId_klienta());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metoda służąca do szukania klientów w bazie danych po unikalnym identyfikatorze
     * @param clientId identyfikator szukanego klienta
     * @return szukany klient (jednoelementowa lista)
     */
    @Override
    public List<Client> findClientById(int clientId) {
        try {
            return dbAccess.query(connection, "SELECT * FROM KLIENCI WHERE id_klienta=?", new BeanListHandler<Client>(Client.class),
                    clientId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Metody służąca do znajdowania wszystkich klientów w bazie danych
     * @return lista klientów
     */
    @Override
    public List<Client> findAll() {
        try {
            return dbAccess.query(connection, "SELECT k.id_klienta, k.imię, k.nazwisko, k.adres, k.numer_telefonu, n.liczba_wypożyczeń " +
                    "FROM KLIENCI k LEFT JOIN (SELECT w.klienci_id_klienta, COUNT(w.klienci_id_klienta) AS liczba_wypożyczeń " +
                    "FROM WYPOŻYCZENIA w WHERE w.data_zwrotu IS NULL GROUP BY w.klienci_id_klienta) n ON k.id_klienta = n.klienci_id_klienta",
                    new BeanListHandler<Client>(Client.class));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Metoda służąca do nawiązywania połączenia z bazą danych
     * @throws Exception
     */
    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","student","student");
    }

    /**
     * Metoda służąca do zamykania połączenia z bazą danych
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        connection.close();
        try {
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl;shutdown=true","student","student");
        }
        catch (Exception e) {}
    }
}
