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
import javafx.stage.StageStyle;

/**
 *
 * @author hamdi
 */
public class App extends Application{
 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource("/vue/Vue.fxml"));
        Scene scene1 = new Scene(root1);

        stage.setTitle("Logiciel de messagerie et envoie de fichier");
        stage.setScene(scene1);
        stage.show();
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
}
