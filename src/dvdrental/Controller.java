package dvdrental;

import dvdrental.client.Client;
import dvdrental.dvdmovie.DvdMovie;
import dvdrental.rent.RentController;
import dvdrental.update.UpdateController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Calendar;

public class Controller {

    /**
     * Tabela zawierająca dane płyt DVD
     */
    @FXML
    private TableView<DvdMovie> dvdTable;

    /**
     * Kolumna tabeli dvdTable zawierająca identyfikatory płyt DVD
     */
    @FXML
    private TableColumn<DvdMovie, String> dvdColId;

    /**
     * Kolumna tabeli dvdTable zawierająca polskie tytuły filmów DVD
     */
    @FXML
    private TableColumn<DvdMovie, String> dvdColPlTitle;

    /**
     * Kolumna tabeli dvdTable zawierająca oryginalne tytuły filmów DVD
     */
    @FXML
    private TableColumn<DvdMovie, String> dvdColOrigTitle;

    /**
     * Kolumna tabeli dvdTable zawierająca lata produkcji filmów DVD
     */
    @FXML
    private TableColumn<DvdMovie, String> dvdColYear;

    /**
     * Kolumna tabeli dvdTable zawierająca identyfikatory klientów, którzy wypożyczyli daną płytę (jeśli płyta nie jest wypożyczona,
     * to wartość wyświetlana w kolumnie wynosi 0
     */
    @FXML
    private TableColumn<DvdMovie, String> dvdColClientId;

    /**
     * Kolumna tabeli dvdTable, która będzie zawierać przyciski do wypożyczania/zwrotu poszczególnych płyt DVD
     */
    @FXML
    private TableColumn<DvdMovie, DvdMovie> dvdColRentRet;

    /**
     * Pole tekstowe, w które użytkownik może wpisać polski tytuł nowego filmu
     */
    @FXML
    private TextField dvdNewPlTitle;

    /**
     * Pole tekstowe, w które użytkownik może wpisać oryginalny tytuł nowego filmu
     */
    @FXML
    private TextField dvdNewOrigTitle;

    /**
     * Pole tekstowe, w które użytkownik może wpisać rok produkcji nowego filmu
     */
    @FXML
    private TextField dvdNewYear;

    /**
     * Tabela zawierająca dane klientów
     */
    @FXML
    private TableView<Client> clientTable;

    /**
     * Kolumna tabeli clientTable zawierająca identyfikatory klientów
     */
    @FXML
    private TableColumn<Client, String> clientColId;

    /**
     * Kolumna tabeli clientTable zawierająca imiona klientów
     */
    @FXML
    private TableColumn<Client, String> clientColName;

    /**
     * Kolumna tabeli clientTable zawierająca nazwiska klientów
     */
    @FXML
    private TableColumn<Client, String> clientColSurname;

    /**
     * Kolumna tabeli clientTable zawierająca adresy klientów
     */
    @FXML
    private TableColumn<Client, String> clientColAddr;

    /**
     * Kolumna tabeli clientTable zawierająca numery telefonów klientów
     */
    @FXML
    private TableColumn<Client, String> clientColPhone;

    /**
     * Kolumna tabeli clientTable zawierająca liczby wypożyczonych przez poszczególnych klientów płyt DVD
     */
    @FXML
    private TableColumn<Client, String> clientColRentals;

    /**
     * Kolumna tabeli clientTable, która będzie zawierać przyciski do edytowania danych poszczególnych klientów
     */
    @FXML
    private TableColumn<Client, Client> clientColUpdate;

    /**
     * Pole tekstowe, w które użytkownik może wpisać imię nowego klienta
     */
    @FXML
    private TextField clientNewName;

    /**
     * Pole tekstowe, w które użytkownik może wpisać nazwisko nowego klienta
     */
    @FXML
    private TextField clientNewSurname;

    /**
     * Pole tekstowe, w które użytkownik może wpisać adres nowego klienta
     */
    @FXML
    private TextField clientNewAddr;

    /**
     * Pole tekstowe, w które użytkownik może wpisać numer telefonu nowego klienta
     */
    @FXML
    private TextField clientNewPhone;

    /**
     * Moduł 'Model'
     */
    private DvdRental model;

    /**
     * Konstruktor głównego kontrolera w aplikacji
     * @param model model, z którym będzie się komunikował konstruktor
     * @param stage scena główna, główny kontener aplikacji JavaFX
     */
    public Controller(DvdRental model, Stage stage) {
        this.model = model;
        stage.setOnCloseRequest(e -> model.close());
    }

    /**
     * Metoda inicjująca kontroler, wywoływana następnie po jego konstruktorze
     */
    public void initialize() {
        dvdColId.setCellValueFactory(new PropertyValueFactory<>("id_płyty"));
        dvdColPlTitle.setCellValueFactory(new PropertyValueFactory<>("polski_tytuł_filmu"));
        dvdColOrigTitle.setCellValueFactory(new PropertyValueFactory<>("oryginalny_tytuł_filmu"));
        dvdColYear.setCellValueFactory(new PropertyValueFactory<>("rok_produkcji"));
        dvdColClientId.setCellValueFactory(new PropertyValueFactory<>("id_klienta"));

        dvdColRentRet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DvdMovie, DvdMovie>, ObservableValue<DvdMovie>>() {
            /**
             * Ustawienie cell value factory dla kolumny przycisków
             * @param features dane potrzebne dla komórek kolumny przycisków
             * @return obiekt klasy opakowującej utworzony z features
             */
            @Override public ObservableValue<DvdMovie> call(TableColumn.CellDataFeatures<DvdMovie, DvdMovie> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });

        dvdColRentRet.setCellFactory(new Callback<TableColumn<DvdMovie, DvdMovie>, TableCell<DvdMovie, DvdMovie>>() {
            /**
             * Ustawienie cell factory dla kolumny przycisków
             * @param dvdColRentRet kolumna przycisków
             * @return przycisk (w komórce kolumny przycisków)
             */
            @Override public TableCell<DvdMovie, DvdMovie> call(TableColumn<DvdMovie, DvdMovie> dvdColRentRet) {
                return new TableCell<DvdMovie, DvdMovie>() {
                    final Button button = new Button();

                    /**
                     * Edycja atrybutów przycisku (elememtu związanego z daną komórką)
                     * @param dvdMovie wiersz danych odpowiadających przyciskowi
                     * @param empty mówi, czy komórka przechowuje jakąś wartość
                     */
                    @Override public void updateItem(final DvdMovie dvdMovie, boolean empty) {
                        super.updateItem(dvdMovie, empty);
                        if (dvdMovie != null) {
                            if (dvdMovie.getId_klienta() == 0) button.setText("Wypożycz");
                            else button.setText("Zwróć");
                            button.setMinWidth(70);
                            setGraphic(button);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                /**
                                 * Metoda wywoływana przy naciśnięciu przycisku "Wypożycz" / "Zwróć"
                                 * @param event
                                 */
                                @Override public void handle(ActionEvent event) {
                                    if (dvdMovie.getId_klienta() == 0) {
                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("rent/rent.fxml"));
                                            Stage stage = new Stage();
                                            stage.setScene(new Scene(loader.load()));
                                            stage.setTitle("Wypożycz");
                                            stage.initModality(Modality.WINDOW_MODAL);
                                            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                                            RentController rentController = loader.<RentController>getController();
                                            rentController.initData(dvdMovie.getId_płyty(), model);
                                            stage.showAndWait();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        model.returnDvd(dvdMovie.getId_płyty());
                                    }
                                    updateTables(2);
                                }
                            });
                        }
                        else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        updateTables(0);

        clientColId.setCellValueFactory(new PropertyValueFactory<>("id_klienta"));
        clientColName.setCellValueFactory(new PropertyValueFactory<>("imię"));
        clientColSurname.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        clientColAddr.setCellValueFactory(new PropertyValueFactory<>("adres"));
        clientColPhone.setCellValueFactory(new PropertyValueFactory<>("numer_telefonu"));
        clientColRentals.setCellValueFactory(new PropertyValueFactory<>("liczba_wypożyczeń"));

        clientColUpdate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Client, Client>, ObservableValue<Client>>() {
            /**
             * Ustawienie cell value factory dla kolumny przycisków
             * @param features dane potrzebne dla komórek kolumny przycisków
             * @return obiekt klasy opakowującej utworzony z features
             */
            @Override public ObservableValue<Client> call(TableColumn.CellDataFeatures<Client, Client> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });

        clientColUpdate.setCellFactory(new Callback<TableColumn<Client, Client>, TableCell<Client, Client>>() {
            /**
             * Ustawienie cell factory dla kolumny przycisków
             * @param clientColUpdate kolumna przycisków
             * @return przycisk (w komórce kolumny przycisków)
             */
            @Override public TableCell<Client, Client> call(TableColumn<Client, Client> clientColUpdate) {
                return new TableCell<Client, Client>() {
                    final Button button = new Button();

                    /**
                     * Edycja atrybutów przycisku (elememtu związanego z daną komórką)
                     * @param client wiersz danych odpowiadających przyciskowi
                     * @param empty mówi, czy komórka przechowuje jakąś wartość
                     */
                    @Override public void updateItem(final Client client, boolean empty) {
                        super.updateItem(client, empty);
                        if (client != null) {
                            button.setText("Edytuj");
                            setGraphic(button);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                /**
                                 * Metoda wywoływana przy naciśnięciu przycisku "Edytuj"
                                 * @param event
                                 */
                                @Override public void handle(ActionEvent event) {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("update/update.fxml"));
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(loader.load()));
                                        stage.setTitle("Edytuj");
                                        stage.initModality(Modality.WINDOW_MODAL);
                                        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                                        UpdateController updateController = loader.<UpdateController>getController();
                                        updateController.initData(client.getId_klienta(), model);
                                        stage.showAndWait();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    updateTables(2);
                                }
                            });
                        }
                        else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        updateTables(1);
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku "Dodaj klienta" - jeśli wszystkie dane są prawidłowe, do bazy zostaje dodany nowy klient
     * o podanych w poprzedzających przycisk polach tekstowych danych
     * @param event
     */
    public void onAddingNewClient(ActionEvent event) {
        try {
            String newName = clientNewName.getText();
            String newSurname = clientNewSurname.getText();
            String newAddr = clientNewAddr.getText();
            int newPhoneNum = Integer.parseInt(clientNewPhone.getText());
            if ((newPhoneNum != 0 && (newPhoneNum / 1000000000 >= 1 || newPhoneNum / 100000000 < 1)) || newName.equals("")
                    || newSurname.equals("") || newName.length() >= 15 || newSurname.length() >= 20 || newAddr.length() >= 50) return;

            model.addNewClient(newName, newSurname, newAddr, newPhoneNum);
        } catch (Exception e) { return; }

        clientNewName.setText(null);
        clientNewSurname.setText(null);
        clientNewAddr.setText(null);
        clientNewPhone.setText(null);

        updateTables(1);
    }

    /**
     * Metoda wywoływana po kliknięciu przycisku "Dodaj film" - jeśli wszystkie dane są prawidłowe, do bazy zostaje dodany nowy film o podanych
     * w poprzedzających przycisk polach tekstowych danych
     * @param event
     */
    public void onAddingNewDvd(ActionEvent event) {
        try {
            String newPlTitle = dvdNewPlTitle.getText();
            String newOrigTitle = dvdNewOrigTitle.getText();
            int newYear = Integer.parseInt(dvdNewYear.getText());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if ((newYear != 0 && (newYear % 1895 < 1 || newYear % year >= 1)) || newPlTitle.equals("") || newPlTitle.length() >= 75
                    || newOrigTitle.length() >= 75) return;

            model.addNewDvd(newPlTitle, newOrigTitle, newYear);
        } catch (Exception e) { return;}

        dvdNewPlTitle.setText(null);
        dvdNewOrigTitle.setText(null);
        dvdNewYear.setText(null);

        updateTables(0);
    }

    /**
     * Metoda służy do odświeżania widoku po dokonaniu zmian w bazie danych
     * @param whichOnes 0 - odśwież tabelę filmów DVD, 1 - odśwież tabelę klienta, 2 - odśwież obie
     */
    public void updateTables(int whichOnes) {
        switch (whichOnes) {
            case 0:
                dvdTable.setItems(FXCollections.observableArrayList(model.getAllDvds()));
                break;
            case 1:
                clientTable.setItems(FXCollections.observableArrayList(model.getAllClients()));
                break;
            case 2:
                clientTable.setItems(FXCollections.observableArrayList(model.getAllClients()));
                dvdTable.setItems(FXCollections.observableArrayList(model.getAllDvds()));
                return;
        }
    }
}
