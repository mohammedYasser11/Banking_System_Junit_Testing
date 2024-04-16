package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class Main extends Application {
    public Bank bank =new Bank();
    
    public Bank getBank() {
    	return bank;
    } 

    @Override
    public void start(Stage primaryStage) {
        try {
            BankSession.createInstance(bank);
            BankAccount acc1 = new BankAccount("1","123",100,bank);
            bank.addAccount(acc1);
            
            BankAccount acc2 = new BankAccount("2","321",1000,bank);
            bank.addAccount(acc2);
            
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Bank.fxml"));
            Parent Root = Loader.load();
            BankController bankController = Loader.getController();
            bankController.setBank(bank);
            
            Scene scene = new Scene(Root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

