/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.selectedUserToUpdate;
import static Controller.Admin.UsersManagmentController.updateStage;
import Model.Account;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AccountsManagmentController implements Initializable {

    public static Account selectedAccountToUpdate;
    public static Stage updateStage;

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private TableView<Account> AccountTableView;
    @FXML
    private TableColumn<Account, Integer> id;
    @FXML
    private TableColumn<Account, Integer> accountNumber;
    @FXML
    private TableColumn<Account, String> userName;
    @FXML
    private TableColumn<Account, String> currency;
    @FXML
    private TableColumn<Account, Double> balancy;
    @FXML
    private TableColumn<Account, Integer> creationDate;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private Button searchAccountBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        accountNumber.setCellValueFactory(new PropertyValueFactory("account_number"));
        userName.setCellValueFactory(new PropertyValueFactory("user_name"));
        currency.setCellValueFactory(new PropertyValueFactory("currency"));
        balancy.setCellValueFactory(new PropertyValueFactory("balancy"));
        creationDate.setCellValueFactory(new PropertyValueFactory("creation_date"));
        // TODO
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccountsManagment();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Account> accountList
                = FXCollections.observableArrayList(Account.getAllAccount());

        AccountTableView.setItems(accountList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {

        selectedAccountToUpdate = AccountTableView.getSelectionModel().getSelectedItem();

        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccountPage.fxml"));
        Parent rootUpdate = loaderUpdate.load();
        Scene updateUserScene = new Scene(rootUpdate);
        updateStage = new Stage();
        updateStage.setScene(updateUserScene);
        updateStage.setTitle("Update Account " + selectedAccountToUpdate.getUsername());
        updateStage.show();
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {

        if (AccountTableView.getSelectionModel().getSelectedItem() != null) {
            Account selectedAccount = AccountTableView.getSelectionModel().getSelectedItem();

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedAccount.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an account");
            warnAlert.setContentText("Please select an account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
