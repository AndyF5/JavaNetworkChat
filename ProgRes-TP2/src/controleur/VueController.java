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
import javafx.scene.input.MouseEvent;
import modele.ChatManager;

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
    }

    @FXML
    private void btnEnvoyerMSGV1Clicked(ActionEvent event) {

        chatManager.sendMessage(new Message(txtNomUtilisateurV1.getText(), txtMessageV1.getText()));
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
        }
    }

    @FXML
    private void btnConnectV1Clicked(ActionEvent event) {
        alert.setText("");
        try {
            chatManager.connect(txtIpDistntV1.getText(), Integer.parseInt(txtPortV1.getText()));
            btnEnvoyerMSGV1.setDisable(false);
            btnEnvoyerFichierV1.setDisable(false);
            btnConnectV1.setDisable(true);
        } catch (IOException ex) {
            alert.setText("Le serveur est Injoignable : " + txtIpDistntV1.getText() + "/" + Integer.parseInt(txtPortV1.getText()));
            Logger.getLogger(VueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnQuitterV1Clicked(ActionEvent event) throws IOException {
        chatManager.close();
        System.exit(0);
    }

}
