package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RequestLoanController {
	private Stage stage;
	private Scene scene;
	@FXML
    private TextField amountField;
    @FXML
    private Button enterButton, backButton;
    @FXML
    private Label statusLabel,totalLoanLabel;
    
    private Bank bank = BankSession.getInstance().getCurrentBank();
    
    public void initialize() {
        BankAccount currentAccount = UserSession.getInstance().getCurrentAccount();

        if (currentAccount != null) {
        	double loan = currentAccount.getTotalLoanAmount();
            totalLoanLabel.setText(String.format("%.2f", loan));
        } else {
        	totalLoanLabel.setText("No account selected");
        }
    }
    @FXML
    public void requestLoan(ActionEvent event)throws IOException {
    	 String amountText = amountField.getText();
    	 
    	if (amountText == null || amountText.isEmpty()) {
            statusLabel.setText("Please fill all the fields.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
            return;
        }
    	
        double amount = Double.parseDouble(amountField.getText());
        BankAccount account = UserSession.getInstance().getCurrentAccount();
        String accountNumber = account.getAccountNumber();

        if (amount > 0) {
            Loan existingLoan = bank.findLoan(accountNumber);
            if (existingLoan != null) {
                statusLabel.setText("You have already requested a loan");
                statusLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
            } else {
                bank.issueLoan(accountNumber, amount); 
                statusLabel.setText("Loan request submitted.");
                statusLabel.setStyle("-fx-text-fill: green; -fx-alignment: center;");
            }
        } else {
            statusLabel.setText("Invalid amount.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
        }
    }
    
    public void SwitchToLoan(ActionEvent event) throws IOException {
    	BankSession.getInstance().UpdateBank(bank);
		Parent root = FXMLLoader.load(getClass().getResource("Loan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
    
}
