package dvdrental.rental;

import dvdrental.DAO;

import java.util.List;

/**
 * Interfejs do tworzenia DAO dla wypożyczenia
 */
public interface RentalDAO extends DAO {

    /**
     * Deklaracja metody służącej do umieszczania wypożyczenia w bazie danych
     * @param rental dane do umieszczenia w bazie danych
     * @return true jeśli się powiodło, false w p.p.
     */
    boolean insertRental(Rental rental);

    /**
     * Deklaracja metody służącej do edytowania danych wypożyczenia z bazy danych
     * @param rental nowe dane, które zostaną wstawione w miejsce aktualnych danych wypożyczenia
     * @return true jeśli się powiodło, false w p.p.
     */
    boolean updateRental (Rental rental);

    /**
     * Deklaracja metody służącej do szukania wypożyczeń w bazie danych po identyfikatorze płyty
     * @param dvdId identyfikator szukanej płyty
     * @return lista wypożyczeń danej płyty
     */
    List<Rental> findRentalByDvdId(int dvdId);
}
