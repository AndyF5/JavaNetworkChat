/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1897483
 */
public class Connection  implements Runnable{

    private Socket senderSocket = null;
    private final String ip;
    private final int port;

    public Connection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    

    public Socket getSenderSocket() {
        return senderSocket;
    }

    
    public void close() throws IOException{
        senderSocket.close();
    }

    Thread thConnexion = new Thread();
    @Override
    public void run() {
        try {
            senderSocket = new Socket(ip, port);
            senderSocket.setKeepAlive(true);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
