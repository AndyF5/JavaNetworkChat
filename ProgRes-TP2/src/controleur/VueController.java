/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.Message;
import modele.ServerThread;
import modele.Connection;
import modele.FileSender;
//boite de dialogue
import javafx.stage.FileChooser;
import javafx.scene.Node;

import java.io.File;

import javafx.scene.input.MouseEvent;
import modele.FilePacket;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class VueController implements Initializable {

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

    Socket ServerConnection = null;

    @FXML
    private ListView<String> listEventV1;
    @FXML
    private Label alert;
    
    private File file;
    private Connection con;
    
    FileChooser fileChooser = new FileChooser();
   

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mettre le btnConnect par défaut
        btnConnectV1.setDefaultButton(true);

        //listChat = new Vector<Message>();
        Thread server = new Thread(new ServerThread(new ArrayList<Message>(), new ArrayList<String>(), 5555));
        server.start();
        //initialiser le dossier -- default
        fileChooser.setInitialDirectory(new File("c:/temp"));
    }

    @FXML
    private void btnEnvoyerMSGV1Clicked(ActionEvent event) {

        listChatV1.getItems().add(new Message(txtNomUtilisateurV1.getText(), txtMessageV1.getText()));
    }

    @FXML
    private void btnEnvoyerFichierV1Clicked(ActionEvent event) {
        FileSender fSend=new FileSender(listEventV1.getItems(), new FilePacket(file), con.getSenderSocket()); //******************************
    }
    @FXML
    private void txtUrlFichierClicked(MouseEvent event) throws Exception { {
            file = fileChooser.showOpenDialog(((Node)(event.getTarget())).getScene().getWindow());
            txtUrlFichierV1.setText(file.getPath());
        }
    }

    @FXML
    private void btnConnectV1Clicked(ActionEvent event) {
        alert.setText("");
        try {
            con = new Connection(txtIpDistntV1.getText(), Integer.parseInt(txtPortV1.getText()));

        } catch (IOException ex) {
            alert.setText("Le serveur est Injoignable : " + txtIpDistntV1.getText() + "/" + Integer.parseInt(txtPortV1.getText()));
            Logger.getLogger(VueController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void testSend() {

    }

    
}
