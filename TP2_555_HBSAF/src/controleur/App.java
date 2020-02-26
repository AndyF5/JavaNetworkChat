/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hamdi
 */
public class App extends Application {

    String pathIMG ="/vue/img.png";
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/Vue.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);

        stage.setTitle("Logiciel de messagerie et d'envoie de fichier");
        stage.setScene(scene1);
        
        VueController controller = loader.getController();
        
        stage.setOnHidden(e -> controller.shutdown());
               
        stage.resizableProperty().setValue(Boolean.FALSE);
        
        stage.show();

    }

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
