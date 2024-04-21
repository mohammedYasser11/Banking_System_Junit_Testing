package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class LoanListController {
	private Stage stage;
	private Scene scene;
    @FXML
    private ListView<String> LoanListView;
    
    private BankAccount account = UserSession.getInstance().getCurrentAccount();

    @FXML
    private void initialize() {
        updateTransactions();
    }

    private void updateTransactions() {
        ObservableList<String> Loan = FXCollections.observableArrayList();

        for (Loan loan : account.getLoans()) {
        	Loan.add("Loan Amount: " + loan.getAmount());
        }

        LoanListView.setItems(Loan);
    }
    
    public void SwitchToLoan(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Loan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
