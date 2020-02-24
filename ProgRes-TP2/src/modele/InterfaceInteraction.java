/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author 1897654
 */
public final class InterfaceInteraction {

    private static TextInputControl input_IP;
    private static TextInputControl input_Port;
    private static TextInputControl input_Message;
    private static TextInputControl input_FilePath;
    private static TextInputControl input_UserName;
    private static Button btn_Connexion;
    private static Button btn_EnvoyerMessage;
    private static Button btn_EnvoyerFichier;

    public static TextInputControl getInput_Message() {
        return input_Message;
    }

    public static void setInput_Message(TextInputControl input_Message) {
        InterfaceInteraction.input_Message = input_Message;
    }

    public static TextInputControl getInput_FilePath() {
        return input_FilePath;
    }

    public static void setInput_FilePath(TextInputControl input_FilePath) {
        InterfaceInteraction.input_FilePath = input_FilePath;
    }

    public static Button getBtn_Connexion() {
        return btn_Connexion;
    }

    public static void setBtn_Connexion(Button btn_Connexion) {
        InterfaceInteraction.btn_Connexion = btn_Connexion;
    }

    public static Button getBtn_EnvoyerMessage() {
        return btn_EnvoyerMessage;
    }

    public static void setBtn_EnvoyerMessage(Button btn_EnvoyerMessage) {
        InterfaceInteraction.btn_EnvoyerMessage = btn_EnvoyerMessage;
    }

    public static Button getBtn_EnvoyerFichier() {
        return btn_EnvoyerFichier;
    }

    public static void setBtn_EnvoyerFichier(Button btn_EnvoyerFichier) {
        InterfaceInteraction.btn_EnvoyerFichier = btn_EnvoyerFichier;
    }

    public static TextInputControl getInput_IP() {
        return input_IP;
    }

    public static void setInput_IP(TextInputControl input_IP) {
        InterfaceInteraction.input_IP = input_IP;
    }

    public static TextInputControl getInput_Port() {
        return input_Port;
    }

    public static void setInput_Port(TextInputControl input_Port) {
        InterfaceInteraction.input_Port = input_Port;
    }

    public static TextInputControl getInput_UserName() {
        return input_UserName;
    }

    public static void setInput_UserName(TextInputControl input_UserName) {
        InterfaceInteraction.input_UserName = input_UserName;
    }
    
    

    public static void connexionReussi() {
        btn_EnvoyerMessage.setDisable(false);
        btn_EnvoyerFichier.setDisable(false);

        btn_Connexion.setDisable(true);
        input_IP.setDisable(true);
        input_Port.setDisable(true);

        input_FilePath.setDisable(false);
        input_Message.setDisable(false);
        input_UserName.setDisable(false);
    }
}
