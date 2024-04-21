package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BankLoans {
	private Stage stage;
	private Scene scene;
    @FXML
    private ListView<String> LoanListView;
	Bank bank = BankSession.getInstance().getCurrentBank();

    @FXML
    private void initialize() {
        updateTransactions();
    }

    private void updateTransactions() {
        ObservableList<String> Loan = FXCollections.observableArrayList();
        for (String x : bank.getAllLoans().keySet()) {
        	for (Loan loan : bank.getAllLoans().get(x)) {
        	Loan.add("Account Number: "+loan.getAccountNumber() + ", "+"Loan Amount: " + loan.getAmount());
             }
        LoanListView.setItems(Loan);
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
