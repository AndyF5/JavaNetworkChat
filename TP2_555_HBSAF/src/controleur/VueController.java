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
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import modele.ChatManager;
import modele.InterfaceInteraction;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class VueController implements Initializable {

    @FXML
    private TextField txtIpDistnt;
    @FXML
    private TextField txtNomUtilisateur;
    @FXML
    private TextField txtPort;
    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtUrlFichier;
    @FXML
    private Button btnEnvoyerMSG;
    @FXML
    private Button btnEnvoyerFichier;
    @FXML
    private Button btnQuitter;
    @FXML
    private ListView<Message> listChat;
    @FXML
    private Button btnConnect;

    @FXML
    private ListView<String> listEvent;
    @FXML
    private Label alert;

    private File file;
    private ChatManager chatManager;
    public String fileName;

    FileChooser fileChooser = new FileChooser();

    private final ObservableList<Message> chat = FXCollections.observableArrayList();

    private final ObservableList<String> events = FXCollections.observableArrayList();
    @FXML
    private ImageView imageView;

    Image image;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listChat.setItems(chat);

        listEvent.setItems(events);

        btnConnect.setDefaultButton(true);

        chatManager = new ChatManager(chat, events);
        chatManager.startServer();

        //initialiser le dossier -- default
        fileChooser.setInitialDirectory(new File("c:/temp"));
        btnEnvoyerMSG.setDisable(true);
        btnEnvoyerFichier.setDisable(true);
        txtUrlFichier.setDisable(true);
        txtMessage.setDisable(true);
        txtNomUtilisateur.setDisable(true);

        InterfaceInteraction.setInput_IP(txtIpDistnt);
        InterfaceInteraction.setInput_Port(txtPort);
        InterfaceInteraction.setInput_UserName(txtNomUtilisateur);
        InterfaceInteraction.setInput_Message(txtMessage);
        InterfaceInteraction.setInput_FilePath(txtUrlFichier);

        InterfaceInteraction.setBtn_Connexion(btnConnect);
        InterfaceInteraction.setBtn_EnvoyerMessage(btnEnvoyerMSG);
        InterfaceInteraction.setBtn_EnvoyerFichier(btnEnvoyerFichier);

        listChat.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            BackgroundFill backgroundUser = new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY);

            @Override
            public ListCell<Message> call(ListView<Message> msg) {
                return new ListCell<Message>() {
                    @Override
                    public void updateItem(Message item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {

                            setText(null);
                        } else {

                            if (item.isSentByThis()) {
                                setBackground(new Background(backgroundUser));
                                setText(item.toString());

                            } else {
                                setText("--> " + item.toString());
                            }
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void btnEnvoyerMSGClicked(ActionEvent event) {
        chatManager.sendMessage(new Message(txtNomUtilisateur.getText(), txtMessage.getText(), true));
        txtMessage.setText("");

        image = new Image("/vue/img.png");
        imageView.setImage(image);
    }

    @FXML
    private void btnEnvoyerFichierClicked(ActionEvent event) {
        if (file != null) {
            chatManager.sendFile(file);
            image = new Image("/vue/img2.png");
            imageView.setImage(image);
        }
    }

    @FXML
    private void txtUrlFichierClicked(MouseEvent event) throws Exception {
        {
            file = fileChooser.showOpenDialog(((Node) (event.getTarget())).getScene().getWindow());
            txtUrlFichier.setText(file.getPath());
            fileName = file.getName();
        }
    }

    @FXML
    private void btnConnectClicked(ActionEvent event) {
        alert.setText("");

        if (Pattern.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", txtIpDistnt.getText())) {
            if (txtPort.getText().matches("\\d+")) {
                chatManager.connect(txtIpDistnt.getText(), Integer.parseInt(txtPort.getText()));
                image = new Image("/vue/img4.png");
                imageView.setImage(image);
            } else {
                alert.setText("Port non valid");
                txtPort.requestFocus();
            }

        } else {
            alert.setText("Address non valid");
            txtIpDistnt.requestFocus();
        }
    }

    @FXML
    private void btnQuitterClicked(ActionEvent event) {
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
}
