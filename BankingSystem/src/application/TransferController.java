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

public class TransferController {
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField transferAmountField;

    @FXML
    private TextField destinationAccountNumberField;

    @FXML
    private Label errorLabel;

    Bank bank = BankSession.getInstance().getCurrentBank();

    @FXML
    public void submitTransferAmount(ActionEvent event) throws IOException {
        String transferAmount = transferAmountField.getText();
        String destinationAccountNumber = destinationAccountNumberField.getText();
        BankAccount sourceAccount = UserSession.getInstance().getCurrentAccount();
        BankAccount destinationAccount = bank.findAccount(destinationAccountNumber);
        System.out.print(bank.findAccount(destinationAccountNumber));
        
        if (transferAmount == null || transferAmount.isEmpty() || 
    			destinationAccountNumber == null || destinationAccountNumber.isEmpty())
    	{
    		errorLabel.setText("Please fill all the fields.");
    		errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
	        return;
	    }
        
        if (sourceAccount != null && destinationAccount != null) {
            if (sourceAccount!=destinationAccount) {
            	try {
            	
                double amount = Double.parseDouble(transferAmount);
                if (sourceAccount.getBalance() >= amount && amount>0) {
                	sourceAccount.transfer(destinationAccount, amount);
                    errorLabel.setText("Transfer Successfully");
                    errorLabel.setStyle("-fx-text-fill: green; -fx-alignment: center;");
                } else if (sourceAccount.getBalance() < amount) {
                    errorLabel.setText("Insufficient balance");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                } else {
                	errorLabel.setText("Invalid amount");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid transfer amount");
                errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
            }
        } 
            else {
            	errorLabel.setText("Invalid account number");
                errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
            }
            }
        	else {
            errorLabel.setText("Either source account or destination account \n doesn't exist");
            
            errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
        }
    } 
    
    public void SwitchToDashboard(ActionEvent event) throws IOException {
    	BankSession.getInstance().UpdateBank(bank);
		Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
