package dvdrental.dvdmovie;

import dvdrental.DAO;

import java.util.List;

/**
 * Interfejs do tworzenia DAO dla filmu DVD
 */
public interface DvdMovieDAO extends DAO {

    /**
     * Deklaracja metody służącej do umieszczania filmu DVD w bazie danych
     * @param dvdMovie dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    boolean insertDvdMovie(DvdMovie dvdMovie);

    /**
     * Deklaracja metody służącej do znajdowania wszystkich filmów DVD w bazie danych
     * @return lista filmów DVD
     */
    List<DvdMovie> findAll();
}
