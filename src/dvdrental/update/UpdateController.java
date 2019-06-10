package dvdrental.update;

import dvdrental.DvdRental;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Klasa kontrolera dla okna wyświetlającego się po kliknięciu przycisku "Edytuj" w tabeli klientów
 */
public class UpdateController {

    /**
     * Pole tekstowe, w które użytkownik może wpisać nowy adres danego klienta
     */
    @FXML
    private TextField newAddr;

    /**
     * Pole tekstowe, w które użytkownik może wpisać nowy numer telefonu danego klienta
     */
    @FXML
    private TextField newPhoneNum;

    /**
     * Identyfikator klienta, którego dane są edytowane
     */
    private int clientId;

    /**
     * Moduł 'Model'
     */
    private DvdRental model;

    /**
     * Metoda wywoływana w głównym kontrolerze mająca na celu inicjalizację kontrolera przypisanego do nowego okna poprzez przydzielenie
     * mu dostępu do modelu i podanie identyfikatora edytowanego klienta (pozyskanego z wiersza, w którym został naciśnięty przycisk)
     * @param clientId identyfikator klienta, którego dane są edytowane
     * @param model model, z którym będzie się komunikował konstruktor
     */
    public void initData(int clientId, DvdRental model) {
        this.clientId = clientId;
        this.model = model;
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku "Zatwierdź" w nowym oknie - jeśli wszystkie dane są prawidłowe,
     * zmiany są wprowadzane do bazy danych, a okno zostaje zamknięte
     * @param event
     */
    public void onConfirm(ActionEvent event) {
        try {
            String addr = newAddr.getText();
            int phoneNum = Integer.parseInt(newPhoneNum.getText());
            if ((phoneNum != 0 && (phoneNum / 1000000000 >= 1 || phoneNum / 100000000 < 1)) || addr.length() >= 50) return;
            model.updateClient(clientId, addr, phoneNum);
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
