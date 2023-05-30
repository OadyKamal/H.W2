/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class CreateAccountController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    @FXML
    private TextField creationDatelTF;
    @FXML
    private TextField userNameTF;
    @FXML
    private TextField accountNumberTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {

        int account_number = Integer.parseInt(accountNumberTF.getText());
        String username = userNameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        int creation_date = Integer.parseInt(creationDatelTF.getText());

        Account account = new Account(account_number, username, currency, balance, creation_date);
        account.save();

        ViewManager.adminPage.changeSceneToUsersManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

}
