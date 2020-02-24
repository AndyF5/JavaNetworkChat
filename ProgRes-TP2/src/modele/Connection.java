/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author 1897483
 */
public class Connection  implements Runnable{

    private Socket senderSocket = null;
    private final String ip;
    private final int port;
    private final Collection<String> events;
    
    public Connection(String ip, int port, Collection<String> events) {
        this.ip = ip;
        this.port = port;
        this.events = events;
    }
    

    public Socket getSenderSocket() {
        return senderSocket;
    }

    
    public void close() throws IOException{
        senderSocket.close();
    }
    
    @Override
    public void run() {
        try {
            senderSocket = new Socket(ip, port);
            senderSocket.setKeepAlive(true);
            
            Platform.runLater(() -> {
                events.add("Connection établie avec le serveur : [" + senderSocket.getRemoteSocketAddress() + "]");
            });
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
