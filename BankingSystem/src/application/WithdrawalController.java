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

public class WithdrawalController {
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField withdrawalAmountField;

    @FXML
    private Label errorLabel;

    @FXML
    public void submitWithdrawalAmount(ActionEvent event) throws IOException {
        BankAccount account = UserSession.getInstance().getCurrentAccount();
        
        if (account != null) {
            try {
            	String amountText = withdrawalAmountField.getText();
              	 
            	if (amountText == null || amountText.isEmpty()) {
            		errorLabel.setText("Please enter the amount.");
            		errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                    return;
                }
                double amount = Double.parseDouble(amountText);
                if (account.getBalance() >= amount && amount>0) {
                    account.withdraw(amount);
                    errorLabel.setText("Withdrawl Successfully");
                    errorLabel.setStyle("-fx-text-fill: green; -fx-alignment: center;");
                } else if(account.getBalance() < amount) {
                	errorLabel.setText("Insufficient balance");
                	errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                } else {
                	errorLabel.setText("Invalid Amount");
                	errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid Amount");
                errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
            }
        } else {
            errorLabel.setText("No account is currently logged in");
            errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
        }
    }
    
    public void SwitchToDashboard(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
    
}
