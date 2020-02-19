/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.BufferedInputStream;
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
                Socket socket = server.accept();
                
                ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                
                Object obj = (Object) input.readObject();
                
                Message message = (Message)obj;
                
                Platform.runLater(() -> {
                    conversation.add(message);
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void StopThread(){
        continuer = false;
    }
}
