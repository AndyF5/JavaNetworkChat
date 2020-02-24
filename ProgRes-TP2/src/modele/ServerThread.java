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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author 1897483
 */
public class ServerThread implements Runnable {

    private final String filepath;

    private final Collection<Message> chat;
    private final Collection<String> events;

    private boolean continuer;

    private final int port;

    public ServerThread(Collection<Message> conversation, Collection<String> events, int port, String filepath) {
        this.chat = conversation;
        this.events = events;
        this.port = port;
        this.filepath = filepath;

        continuer = true;
    }

    /**
     * Lancer le thread qui écoute les objets entrant. Le méthode execute en
     * continue tant que le boolean continuer est vrai.
     */
    @Override
    public void run() {
        try {
            ServerSocket server;
            server = new ServerSocket(port);
            

            System.out.println("Server ecoute sur socket " + server);

            Platform.runLater(() -> {
                events.add("Server ecoute sur port " + server.getLocalPort());
            });
            
            Socket socket = server.accept();
            
            

            while (continuer) {
                try {
                    ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                
                    Object obj = input.readObject();
                    
                    if (obj instanceof Message) {
                        saveMessage(obj);
                    } else if (obj instanceof FilePacket) {
                        saveFile(obj);
                    } else {
                        events.add("Object inconnu reçu!");
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Platform.runLater(() -> {
                        System.out.println(ex.getMessage());
                        events.add("Erreur avec le reception d'un objet.");
                    });
                }
            }
        }
        catch (IOException ex) {
                events.add("Erreur en établissant le socket de reception.");
        }
    }

    private void saveMessage(Object obj) {
        Message message = (Message) obj;

        System.out.println("Message received : " + message.getMessage());

        Platform.runLater(() -> {
            chat.add(message);
        });
    }

    private void saveFile(Object obj) {
        try {
            FilePacket fp = (FilePacket) obj;
            
            System.out.println("File " + fp.getFileName() + " received.");
            System.out.println("Saving file : " + filepath + fp.getFileName());
            File file = new File(filepath + fp.getFileName());
            file.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            
            Files.write(file.toPath(), fp.getContenu());
            
            Platform.runLater(() -> {
                events.add("Fichier reçu et stocké à : " + file.getPath());
            });
        } catch (IOException ex) {
            Platform.runLater(() -> {
                events.add("Erreur avec le sauvegarde du fichier.");
            });
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Arreter le thread.
     */
    public void StopThread() {
        continuer = false;
    }
}
