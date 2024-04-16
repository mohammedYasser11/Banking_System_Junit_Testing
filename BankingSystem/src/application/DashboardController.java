package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {
	private Stage stage;
	private Scene scene;
		
	Bank bank = BankSession.getInstance().getCurrentBank();
	    
	
	public void SwitchToDeposit(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Deposit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToWithsrawl(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Withdrawl.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToLoan(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Loan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToPayLoan(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("PayLoan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToBalance(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Balance.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToTransfer(ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Transfer.fxml"));
	    Parent root = loader.load();
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

	public void SwitchToMain(ActionEvent event) throws IOException {
		bank = BankSession.getInstance().getCurrentBank();
		UserSession.getInstance().cleanUserSession();
		Parent root = FXMLLoader.load(getClass().getResource("Bank.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToTransaction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToWaitingLoan(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("WaitingLoan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToBankLoans(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("BankLoans.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToAccount(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToAddAcount(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AddAcount.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	public void SwitchToAddMaint(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Bank.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	
}
