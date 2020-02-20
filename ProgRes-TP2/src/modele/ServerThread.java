/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;

/**
 *
 * @author 1897483
 */
public class ServerThread implements Runnable {
    private final String FILEPATH = "/TEMP";
    
    private Collection<Message> conversation;
    private Collection<String> events;
    
    private boolean continuer;
    
    private int port;

    public ServerThread(Collection<Message> conversation, Collection<String> events, int port) {
        this.conversation = conversation;
        this.events = events;
        this.port = port;
        
        continuer = true;
    }
        
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            
            while(continuer) {
                System.out.println("Server ecoute sur socket " + server);
                
                Socket socket = server.accept();
                
                ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                
                Object obj = input.readObject();
                
                if (obj instanceof Message){
                    Message message = (Message)obj;
                
                    System.out.println("Message received : " + message.getMessage());

                    Platform.runLater(() -> {
                        conversation.add(message);
                    });
                }
                else if (obj instanceof File){
                    
                }
                /*
                System.out.println("Is Message? " + (obj instanceof Message));
                
                System.out.println("Is Message? " + (obj instanceof FilePacket));
                */
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void StopThread(){
        continuer = false;
    }
}
