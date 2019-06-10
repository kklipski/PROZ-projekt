package dvdrental;

import dvdrental.client.ClientDAO;
import dvdrental.client.OracleClientDAO;
import dvdrental.dvdmovie.DvdMovieDAO;
import dvdrental.dvdmovie.OracleDvdMovieDAO;
import dvdrental.rental.OracleRentalDAO;
import dvdrental.rental.RentalDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa inicjująca aplikację (moduły M-V-C)
 */
public class DvdRentalApp extends Application{

    /**
     * Metoda tworzy obiekt klasy implementującej interfejs ClientDAO
     * @return obiekt klasy implementującej interfejs ClientDAO
     */
    private ClientDAO buildClientDAO() {
        return new OracleClientDAO();
    }

    /**
     * Metoda tworzy obiekt klasy implementującej interfejs DvdMovieDAO
     * @return obiekt klasy implementującej interfejs DvdMovieDAO
     */
    private DvdMovieDAO buildDvdMovieDAO() {
        return new OracleDvdMovieDAO();
    }

    /**
     * Metoda tworzy obiekt klasy implementującej interfejs RentalDAO
     * @return obiekt klasy implementującej interfejs RentalDAO
     */
    private RentalDAO buildRentalDAO() {
        return new OracleRentalDAO();
    }

    /**
     * Metoda tworzy model dla aplikacji
     * @return model dla aplikacji
     */
    private DvdRental buildModel() {
        return new DvdRental(buildDvdMovieDAO(), buildClientDAO(), buildRentalDAO());
    }

    /**
     * Metoda tworzy główny kontroler w aplikacji
     * @return główny kontroler w aplikacji
     */
    private Controller buildController(Stage stage) {
        return new Controller(buildModel(), stage);
    }

    /**
     * Metoda inicjuje komponenty M-V-C aplikacji
     * @param stage scena główna, główny kontener aplikacji JavaFX
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        loader.setControllerFactory(t -> buildController(stage));
        stage.setTitle("Wypożyczalnia płyt DVD");
        stage.setScene(new Scene(loader.load(),900,600));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metoda main odpowiadająca za uruchomienie aplikacji
     * @param args argumenty przekazywane przy starcie (domyślnie - brak argumentów)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
