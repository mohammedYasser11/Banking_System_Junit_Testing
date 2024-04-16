package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class PayLoanController {
	private Stage stage;
	private Scene scene;
	
    @FXML
    private TextField loanPaymentField;

    @FXML
    private Label errorLabel;
    
    @FXML
    private Label totalLoanLabel;

    @FXML
    public void submitLoanPayment() {
        BankAccount account = UserSession.getInstance().getCurrentAccount();
        
        if (account != null) {
            try {
            	String amountText = loanPaymentField.getText();
           	 
            	if (amountText == null || amountText.isEmpty()) {
            		errorLabel.setText("Please enter the amount.");
            		errorLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
            	
                double amount = Double.parseDouble(loanPaymentField.getText());
                if (amount>0 && account.getTotalLoanAmount()!=0) {
                	account.payLoan(amount);
                    errorLabel.setText("Loan payment successfully");
                    errorLabel.setStyle("-fx-text-fill: green;");
                }
                else {
                	errorLabel.setText("Inavalid Payment Amount");
                	errorLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid payment amount");
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            errorLabel.setText("Account not found");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    public void initialize() {
        // Get the current account from the UserSession
        BankAccount currentAccount = UserSession.getInstance().getCurrentAccount();

        if (currentAccount != null) {
        	double loan = currentAccount.getTotalLoanAmount();
            totalLoanLabel.setText(String.format("%.2f", loan));
        } else {
        	totalLoanLabel.setText("No account selected");
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
