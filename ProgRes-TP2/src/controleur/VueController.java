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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.Connection;
import modele.Message;
import modele.ServerThread;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class VueController implements Initializable {
    private final String FILESAVEPATH = "/TEMP";
    private Connection connection;
    
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
    private ListView<Message> listChatV1;
    @FXML
    private Button btnConnectV1;

    @FXML
    private ListView<String> listEventV1;
    
    private final ObservableList<Message> conversation = FXCollections.observableArrayList();
    
    private final ObservableList<String> events = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listChatV1.setItems(conversation);
        
        listEventV1.setItems(events);
        
        btnConnectV1.setDefaultButton(true);
        
        Thread server = new Thread(new ServerThread(new ArrayList<Message>(), new ArrayList<String>(), 5555, FILESAVEPATH));
        
        server.start();
    }

    @FXML
    private void btnEnvoyerMSGV1Clicked(ActionEvent event) {
    }

    @FXML
    private void btnEnvoyerFichierV1Clicked(ActionEvent event) {
    }

    @FXML
    private void btnConnectV1Clicked(ActionEvent event) {
        try {
            connection = new Connection("127.0.0.1", 5556);
        } catch (IOException ex) {
            // TODO Add to events
        }
    }
    
    @FXML
    private void btnQuitterV1Clicked(ActionEvent event) {
      //  try {
     //       ServerConnection.close();
            System.exit(0);
    /*   } catch (IOException ex) {
            Logger.getLogger(VueController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    private void testSend(){
        
    }
}
