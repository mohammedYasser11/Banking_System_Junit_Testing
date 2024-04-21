package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class WaitingLoanController {

    @FXML
    private ListView<String> loanRequestListView;

    @FXML
    private Button acceptButton;

    @FXML
    private Button rejectButton;
    
    @FXML
    private Label errorLabel;

    private Stage stage;
    private Scene scene;
    private List<Loan> waitingLoans;
    
	Bank bank = BankSession.getInstance().getCurrentBank();

    @FXML
    private void initialize() {
        updateLoanRequests();
    }

    private void updateLoanRequests() {
        ObservableList<String> loanRequests = FXCollections.observableArrayList();
        if (bank.waitingLoansGetter()!=null) {
        	for (Loan loan : bank.waitingLoansGetter()) {
            loanRequests.add("Account Number: "+loan.getAccountNumber() + ", " +"Loan Amount: "+ loan.getAmount());
        }
        	loanRequestListView.setItems(loanRequests);
        }
    }

    public List<Loan> waitingLoansGetter(){
        return this.waitingLoans;
    }

    @FXML
    public void acceptLoanRequest(ActionEvent event) throws IOException {
        String selectedLoanRequest = loanRequestListView.getSelectionModel().getSelectedItem();
        
        if (selectedLoanRequest != null) {
        	String[] parts = selectedLoanRequest.split(":");
        	String number = parts[1].trim().split(",")[0].trim();
        	bank.LoanConfirmation(true, number);
            loanRequestListView.getItems().remove(selectedLoanRequest);
        }
        else {
        	System.out.println("No loan request selected");
            errorLabel.setText("No loan request selected");
    		errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
        }
    }

    @FXML
    public void rejectLoanRequest(ActionEvent event) throws IOException {
    	String selectedLoanRequest = loanRequestListView.getSelectionModel().getSelectedItem();
        
        if (selectedLoanRequest != null) {
        	String[] parts = selectedLoanRequest.split(":");
        	String number = parts[1].trim().split(",")[0].trim();
        	bank.LoanConfirmation(false, number);
            loanRequestListView.getItems().remove(selectedLoanRequest);
        }
        else {
            System.out.println("No loan request selected");
            errorLabel.setText("No loan request selected");
    		errorLabel.setStyle("-fx-text-fill: red; -fx-alignment: center;");
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
