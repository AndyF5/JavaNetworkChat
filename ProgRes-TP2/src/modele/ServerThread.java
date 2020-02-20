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
import java.nio.file.Files;
import java.util.Collection;
import javafx.application.Platform;

/**
 *
 * @author 1897483
 */
public class ServerThread implements Runnable {
    private String filepath;
    
    private Collection<Message> chat;
    private Collection<String> events;
    
    private boolean continuer;
    
    private int port;

    public ServerThread(Collection<Message> conversation, Collection<String> events, int port, String filepath) {
        this.chat = conversation;
        this.events = events;
        this.port = port;
        this.filepath = filepath;
        
        continuer = true;
    }
        
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            
            System.out.println("Server ecoute sur socket " + server);
                
            Socket socket = server.accept();
            
            while(continuer) {
                ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                
                Object obj = input.readObject();
                
                if (obj instanceof Message){
                    Message message = (Message)obj;
                
                    System.out.println("Message received : " + message.getMessage());

                    Platform.runLater(() -> {
                        chat.add(message);
                    });
                }
                else if (obj instanceof byte[]){
                    byte[] fileBytes = (byte[])obj;
                    
                    System.out.println("File received.");
                    
                    File file = new File(filepath + "/test.txt");
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    
                    Files.write(file.toPath(), fileBytes);
                    
                    Platform.runLater(() -> {
                        events.add("Fichier reçu et stocké à : " + file.getPath());
                    });
                }
                else {
                    events.add("Object inconnu reçu!");
                }
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
