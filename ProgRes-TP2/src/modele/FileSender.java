/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
public class FileSender implements Runnable{

    private final Collection<String> events;
    private final File file;
    private final Socket socket;

    public FileSender(Collection<String> events, File file, Socket socket) {
        this.events = events;
        this.file = file;
        this.socket = socket;
    }
    
    @Override
    public void run() {
         try {
            synchronized(socket){
                OutputStream out = socket.getOutputStream();
                ObjectOutputStream outFile = new ObjectOutputStream(out);
                
                byte[] content = Files.readAllBytes(file.toPath());
                
                outFile.writeObject(content);
                outFile.flush();
                Platform.runLater(()->{
                    events.add("Fichier "+ file.getPath()+ " envoyé !");
                });
                socket.shutdownOutput();
            }
           
        } catch (IOException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
