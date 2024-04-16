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

public class DepositController {
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField depositAmountField;

    @FXML
    private Label errorLabel;

    @FXML
    public void submitDepositAmount(ActionEvent event) throws IOException {
        String depositAmount = depositAmountField.getText();
        BankAccount account = UserSession.getInstance().getCurrentAccount();
        
        if (account != null) {
            try {
            	String amountText = depositAmountField.getText();
             	 
            	if (amountText == null || amountText.isEmpty()) {
            		errorLabel.setText("Please enter the amount.");
            		errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                    return;
                }
                double amount = Double.parseDouble(depositAmount);
                if (amount>0) {
                	account.deposit(amount);
                	errorLabel.setText("Deposit successfully");
                	errorLabel.setStyle("-fx-text-fill: green; -fx-alignment: center;");
                }
                else {
                	errorLabel.setText("Invalid Deposit Amount");
                	errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid deposit amount");
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
