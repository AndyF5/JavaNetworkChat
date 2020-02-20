/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author 1897483
 */
public class FileSender implements Runnable{

    private final Collection<String> event;
    private final FilePacket file;
    private final Socket socket;

    public FileSender(Collection<String> event, FilePacket file, Socket socket) {
        this.event = event;
        this.file = file;
        this.socket = socket;
    }
    
    @Override
    public void run() {
         try {
            synchronized(socket){
                OutputStream out = socket.getOutputStream();
                ObjectOutputStream outFile = new ObjectOutputStream(out);
                outFile.writeObject(file);
                outFile.flush();
                Platform.runLater(()->{
                    event.add("Fichier "+ file.getFile().getPath()+ " envoyé !");
                });
                socket.shutdownOutput();
            }
           
        } catch (IOException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
