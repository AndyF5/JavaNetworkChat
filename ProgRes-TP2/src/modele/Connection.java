/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 1897483
 */
public class Connection {

    private Socket senderSocket = null;

    public Socket getSenderSocket() {
        return senderSocket;
    }

    public Connection(String ip, int port) throws IOException {
        senderSocket = new Socket(ip, port);
        System.out.println("connection établie avec le serveur");  
        System.out.println(ip+"/"+port);
    }
}
