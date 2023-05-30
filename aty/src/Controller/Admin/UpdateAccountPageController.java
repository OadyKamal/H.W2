/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import Model.User;
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
public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;

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

        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;

        userNameTF.setText(oldAccount.getUsername());
        currencyTF.setText(oldAccount.getCurrency());
    }

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        int account_number = Integer.parseInt(accountNumberTF.getText());
        String username = userNameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        int creation_date = Integer.parseInt(creationDatelTF.getText());

        Account newAccount = new Account(account_number, username, currency, balance, creation_date);

        newAccount.setId(oldAccount.getId());

        newAccount.update();

        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        Controller.Admin.UsersManagmentController.updateStage.close();
    }

}
