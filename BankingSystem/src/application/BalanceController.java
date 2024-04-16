package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BalanceController {
	private Stage stage;
	private Scene scene;
    @FXML
    private Label balanceLabel;

    public void initialize() {
        // Get the current account from the UserSession
        BankAccount currentAccount = UserSession.getInstance().getCurrentAccount();

        if (currentAccount != null) {
            double balance = currentAccount.getBalance();
            // Update the balance label with the retrieved balance
            balanceLabel.setText(String.format("%.2f", balance)); // Format the balance as needed
        } else {
            balanceLabel.setText("No account selected");
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
