package dvdrental.dvdmovie;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

/**
 * Klasa implementująca interfejs DvdMovieDAO dla bazy danych Oracle
 */
public class OracleDvdMovieDAO implements DvdMovieDAO {

    /**
     * Połączenie z bazą danych
     */
    private Connection connection;

    /**
     * Obiekt typu QueryRunner pozwala wykonywać niektóre operacje na bazie danych w prostszy sposób
     */
    private QueryRunner dbAccess = new QueryRunner();

    /**
     * Metody służąca do umieszczania filmu DVD w bazie danych
     * @param dvdMovie dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    @Override
    public boolean insertDvdMovie(DvdMovie dvdMovie) {
        try {
            String query = "INSERT INTO FILMY_DVD (polski_tytuł_filmu, oryginalny_tytuł_filmu, rok_produkcji) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dvdMovie.getPolski_tytuł_filmu());
            preparedStatement.setString(2, dvdMovie.getOryginalny_tytuł_filmu());
            preparedStatement.setInt(3, dvdMovie.getRok_produkcji());
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
     * Metoda służąca do znajdowania wszystkich filmów DVD w bazie danych
     * @return lista filmów DVD
     */
    @Override
    public List<DvdMovie> findAll() {
        try {
            return dbAccess.query(connection, "SELECT f.id_płyty, f.polski_tytuł_filmu, f.oryginalny_tytuł_filmu, f.rok_produkcji, " +
                    "n.id_klienta FROM FILMY_DVD f LEFT JOIN (SELECT w.klienci_id_klienta AS id_klienta, w.filmy_dvd_id_płyty " +
                    "FROM WYPOŻYCZENIA w WHERE w.data_zwrotu IS NULL) n ON f.id_płyty = n.filmy_dvd_id_płyty",
                    new BeanListHandler<DvdMovie>(DvdMovie.class));
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
