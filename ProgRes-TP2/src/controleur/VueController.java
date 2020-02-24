/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.Message;
import modele.Connection;
//boite de dialogue
import javafx.stage.FileChooser;
import javafx.scene.Node;
import java.io.File;
import javafx.application.Platform;
import java.net.InetAddress;
import java.util.regex.Pattern;
import javafx.scene.input.MouseEvent;
import modele.ChatManager;
import modele.InterfaceInteraction;

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

    @FXML
    private ListView<String> listEventV1;
    @FXML
    private Label alert;

    private File file;
    private Connection con;
    private ChatManager chatManager;
    public String fileName;

    FileChooser fileChooser = new FileChooser();

    private final ObservableList<Message> chat = FXCollections.observableArrayList();

    private final ObservableList<String> events = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listChatV1.setItems(chat);

        listEventV1.setItems(events);

        btnConnectV1.setDefaultButton(true);

        chatManager = new ChatManager(chat, events);
        chatManager.startServer();

        //initialiser le dossier -- default
        fileChooser.setInitialDirectory(new File("c:/temp"));
        btnEnvoyerMSGV1.setDisable(true);
        btnEnvoyerFichierV1.setDisable(true);
        txtUrlFichierV1.setDisable(true);
        txtMessageV1.setDisable(true);
        txtNomUtilisateurV1.setDisable(true);
        
        InterfaceInteraction.setInput_IP(txtIpDistntV1);
        InterfaceInteraction.setInput_Port(txtPortV1);
        InterfaceInteraction.setInput_UserName(txtNomUtilisateurV1);
        InterfaceInteraction.setInput_Message(txtMessageV1);
        InterfaceInteraction.setInput_FilePath(txtUrlFichierV1);
        
        InterfaceInteraction.setBtn_Connexion(btnConnectV1);
        InterfaceInteraction.setBtn_EnvoyerMessage(btnEnvoyerMSGV1);
        InterfaceInteraction.setBtn_EnvoyerFichier(btnEnvoyerFichierV1);
    }

    @FXML
    private void btnEnvoyerMSGV1Clicked(ActionEvent event) {
        chatManager.sendMessage(new Message(txtNomUtilisateurV1.getText(), txtMessageV1.getText()));
        txtMessageV1.setText("");
    }

    @FXML
    private void btnEnvoyerFichierV1Clicked(ActionEvent event) {
        if (file != null) {
            chatManager.sendFile(file);
        }
    }

    @FXML
    private void txtUrlFichierClicked(MouseEvent event) throws Exception {
        {
            file = fileChooser.showOpenDialog(((Node) (event.getTarget())).getScene().getWindow());
            txtUrlFichierV1.setText(file.getPath());
            fileName = file.getName();
        }
    }

    @FXML
    private void btnConnectV1Clicked(ActionEvent event) {
        alert.setText("");
        //try {
            //InetAddress inetAddress = InetAddress.getByName(txtIpDistntV1.getText());
            //System.out.println(inetAddress);
            if (Pattern.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", txtIpDistntV1.getText())) {
                chatManager.connect(txtIpDistntV1.getText(), Integer.parseInt(txtPortV1.getText()));
            } else {
                alert.setText("Address non valid");
                txtIpDistntV1.requestFocus();
            }
        //} 
        /*catch (IOException ex) {
            alert.setText("Le serveur est Injoignable : " + txtIpDistntV1.getText() + "/" + Integer.parseInt(txtPortV1.getText()));
            Logger.getLogger(VueController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void btnQuitterV1Clicked(ActionEvent event) {
        shutdown();
    }
    
    public void shutdown() {
        try {
            System.out.println("Fermeture");
            chatManager.close();
            Platform.exit();
        } catch (IOException ex) {
            Logger.getLogger(VueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void activate(){
        
    }
}
