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

public class TransactionController {
	private Stage stage;
	private Scene scene;
    @FXML
    private ListView<String> transactionListView;
    
    private BankAccount account = UserSession.getInstance().getCurrentAccount();

    @FXML
    private void initialize() {
        updateTransactions();
    }

    private void updateTransactions() {
        ObservableList<String> transactions = FXCollections.observableArrayList();

        for (Transaction transaction : account.getTransactions()) {
            transactions.add(transaction.getType() + ": " + transaction.getAmount());
        }

        transactionListView.setItems(transactions);
    }
    
    public void SwitchToDashboard(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}
