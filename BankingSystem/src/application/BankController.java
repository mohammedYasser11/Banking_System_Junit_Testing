package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.Node;



public class BankController {
	private Stage stage;
	private Scene scene;
    @FXML
    private TextField accountNumberField,passwordField;

    @FXML
    private Label errorLabel;

    private Bank bank = BankSession.getInstance().getCurrentBank();
    
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @FXML
    public void submitAccountNumber(ActionEvent event) throws IOException {
        String accountNumber = accountNumberField.getText();
        String password = passwordField.getText();

        // Check if all fields are filled
        if (accountNumber == null || accountNumber.isEmpty() || 
            password == null || password.isEmpty()) {
            errorLabel.setText("Please fill all the fields.");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        String type = bank.LoginCheacker(accountNumber, password);
        if(type == "U") {
        	BankAccount account = bank.findAccount(accountNumber);
        	if (account != null) {
        	UserSession.createInstance(account);
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            errorLabel.setText("Account doesn't exist");
            errorLabel.setStyle("-fx-text-fill: red;");
           }  
        }
        else if(type == "A") {
        	Parent root = FXMLLoader.load(getClass().getResource("DashboardTeller.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
        	errorLabel.setText("Account doesn't exist");
        	errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
