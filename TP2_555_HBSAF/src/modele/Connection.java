/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
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
            senderSocket = new Socket();
            senderSocket.connect(new InetSocketAddress(ip, port), 1000);
            senderSocket.setKeepAlive(true);
            
            Platform.runLater(() -> {
                events.add("Connection Établie avec le serveur : [" + senderSocket.getRemoteSocketAddress() + "]");
                InterfaceInteraction.connexionReussi();
            });
        } catch (IOException ex) {
            Platform.runLater(() -> {
                events.add("Serveur non joinable : [" + ip + " : " + port + "]");
            });
        }
    }
}
