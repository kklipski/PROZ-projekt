package dvdrental;

/**
 * Interfejs do tworzenia DAO
 */
public interface DAO {

    /**
     * Deklaracja metody służącej do nawiązywania połączenia z bazą danych
     * @throws Exception
     */
    void connect() throws Exception;

    /**
     * Deklaracja metody służącej do zamykania połączenia z bazą danych
     * @throws Exception
     */
    void close() throws Exception;
}
