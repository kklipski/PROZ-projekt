package dvdrental.rent;

import dvdrental.DvdRental;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Klasa kontrolera dla okna wyświetlającego się po kliknięciu przycisku "Wypożycz" / "Zwróć" w tabeli płyt DVD
 */
public class RentController {

    /**
     * Pole tekstowe, w które użytkownik może wpisać identyfikator klienta wypożyczającego wybrany film
     */
    @FXML
    private TextField clientId;

    /**
     * Identyfikator wypożyczanego filmu DVD
     */
    private int dvdId;

    /**
     * Moduł 'Model'
     */
    private DvdRental model;

    /**
     * Metoda wywoływana w głównym kontrolerze mająca na celu inicjalizację kontrolera przypisanego do nowego okna poprzez przydzielenie
     * mu dostępu do modelu i podanie identyfikatora wypożyczanego filmu DVD (pozyskanego z wiersza, w którym został naciśnięty przycisk)
     * @param dvdId identyfikator wypożyczanego filmu DVD
     * @param model model, z którym będzie się komunikował konstruktor
     */
    public void initData(int dvdId, DvdRental model) {
        this.dvdId = dvdId;
        this.model = model;
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku "Zatwierdź" w nowym oknie - jeśli wszystkie dane są prawidłowe,
     * zmiany są wprowadzane do bazy danych, a okno zostaje zamknięte
     * @param event
     */
    public void onConfirm(ActionEvent event) {
        try {
            int clientIdent = Integer.parseInt(clientId.getText());
            if ((model.getClientById(clientIdent)).isEmpty()) return;
            model.rentDvd(dvdId, clientIdent);
        } catch (Exception e) { return; }
        closeWindow(event);
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku "Anuluj" w nowym oknie - powoduje zamnknięcie okna bez wprowadzenia zmian w bazie danych
     * @param event
     */
    public void onCancel(ActionEvent event) {
        closeWindow(event);
    }

    /**
     * Metoda służąca do zamknięcia okna
     * @param event
     */
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
