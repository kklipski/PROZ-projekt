package dvdrental.rental;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

/**
 * Klasa implementująca interfejs RentalDAO dla bazy danych Oracle
 */
public class OracleRentalDAO implements RentalDAO {

    /**
     * Połączenie z bazą danych
     */
    private Connection connection;

    /**
     * Obiekt typu QueryRunner pozwala wykonywać niektóre operacje na bazie danych w prostszy sposób
     */
    private QueryRunner dbAccess = new QueryRunner();

    /**
     * Metoda służąca do umieszczania wypożyczenia w bazie danych
     * @param rental dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    @Override
    public boolean insertRental(Rental rental) {
        try {
            String query = "INSERT INTO WYPOŻYCZENIA (data_wypożyczenia, filmy_dvd_id_płyty, klienci_id_klienta) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, rental.getData_wypożyczenia());
            preparedStatement.setInt(2, rental.getFilmy_dvd_id_płyty());
            preparedStatement.setInt(3, rental.getKlienci_id_klienta());
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
     * Metoda służąca do edytowania danych wypożyczenia z bazy danych
     * @param rental nowe dane, które zostaną wstawione w miejsce aktualnych danych wypożyczenia
     * @return true jeśli się powiodło, false w p.p.
     */
    @Override
    public boolean updateRental(Rental rental) {
        try {
            String query = "UPDATE WYPOŻYCZENIA SET data_zwrotu = ? WHERE filmy_dvd_id_płyty = ? AND data_zwrotu IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, rental.getData_zwrotu());
            preparedStatement.setInt(2, rental.getFilmy_dvd_id_płyty());
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
     * Metoda służąca do szukania wypożyczeń w bazie danych po identyfikatorze płyty
     * @param dvdId identyfikator szukanej płyty
     * @return lista wypożyczeń danej płyty
     */
    @Override
    public List<Rental> findRentalByDvdId(int dvdId) {
        try {
            return dbAccess.query(connection, "SELECT * FROM WYPOŻYCZENIA WHERE filmy_dvd_id_płyty=?",
                    new BeanListHandler<Rental>(Rental.class), dvdId);
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
