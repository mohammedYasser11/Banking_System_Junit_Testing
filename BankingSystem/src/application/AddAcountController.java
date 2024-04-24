package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.io.IOException;

public class AddAcountController {
	private Stage stage;
	private Scene scene;
	
		Bank bank = BankSession.getInstance().getCurrentBank();
		
	    @FXML
	    private TextField accountNumberField;

	    @FXML
	    private TextField passwordField;

	    @FXML
	    private TextField balanceField;

	    @FXML
	    private Button enterButton;
	    
	    @FXML
	    private Label errorlabel;
	    
	    @FXML
	    public void handleEnterButtonAction(ActionEvent event) throws IOException {
	        String accountNumber = accountNumberField.getText();
	        String password = passwordField.getText();
	        String balanceText = balanceField.getText();

	        // Check if all fields are filled
	        if (accountNumber == null || accountNumber.isEmpty() || 
	            password == null || password.isEmpty() || 
	            balanceText == null || balanceText.isEmpty()) {
	            errorlabel.setText("Please fill all the fields");
	            errorlabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
	            return;
	        }
	        
	        try {
	            double balance = Double.parseDouble(balanceText);

	            if (balance >= 0) {
	                if (!bank.isAccountNumberTaken(accountNumber, password)) {
	                    bank.addAccount(new BankAccount(accountNumber, password, balance, bank));
	                    errorlabel.setText("Account added successfully");
	                    errorlabel.setStyle("-fx-text-fill: green; -fx-alignment: center;");
	                } else {
	                    errorlabel.setText("Account Already Taken");
	                    errorlabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
	                }
	            } else {
	                errorlabel.setText("Please enter a valid balance");
	                errorlabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
	            }
	        } catch (NumberFormatException e) {
	            errorlabel.setText("Please enter a valid amount");
	            errorlabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
	        }
	    }

	    public void SwitchToDashboardTeller(ActionEvent event) throws IOException {
	    	BankSession.getInstance().UpdateBank(bank);
	        Parent root = FXMLLoader.load(getClass().getResource("DashboardTeller.fxml"));
	        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
	}


