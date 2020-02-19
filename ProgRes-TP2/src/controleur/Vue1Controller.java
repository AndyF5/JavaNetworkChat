/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class Vue1Controller implements Initializable {

    @FXML
    private TextField txtIpDistntV1;
    @FXML
    private TextField txtNomUtilisateurV1;
    @FXML
    private TextField txtPortV1;
    @FXML
    private TextField txtMessageV1;
    @FXML
    private TextField txtUrlFichierV1;
    @FXML
    private Button btnEnvoyerMSGV1;
    @FXML
    private Button btnEnvoyerFichierV1;
    @FXML
    private Button btnQuitterV1;
    @FXML
    private ListView<?> listChatV1;
    @FXML
    private ListView<Object> listStateV1 = new ListView<Object>();
    @FXML
    private Button btnConnectV1;

    Socket ServerConnection=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mettre le btnConnect par défaut
        btnConnectV1.setDefaultButton(true);
        
        Socket ServerConnection=null;
        int Port = 55555;
        try {
            ServerSocket serverSocket = new ServerSocket(Port);
            ServerConnection=serverSocket.accept();
            InputStream inV1=this.ServerConnection.getInputStream();
            ObjectInputStream objV1= new ObjectInputStream(inV1);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Vue1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void btnEnvoyerMSGV1Clicked(ActionEvent event) {
    }

    @FXML
    private void btnEnvoyerFichierV1Clicked(ActionEvent event) {
    }

    @FXML
    private void btnConnectV1Clicked(ActionEvent event) {
    }
    
    @FXML
    private void btnQuitterV1Clicked(ActionEvent event) {
        try {
            ServerConnection.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Vue1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
