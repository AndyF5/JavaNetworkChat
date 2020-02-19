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

/**
 *
 * @author 1897483
 */
public class MessageSender implements Runnable {

    private final Collection<Message> conversation;
    private final Message message;
    private final Socket socket;

    public MessageSender(Collection<Message> conversation, Message message, Socket socket) {
        this.conversation = conversation;
        this.message = message;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            synchronized(socket){
                OutputStream out = socket.getOutputStream();
                ObjectOutputStream outMessage = new ObjectOutputStream(out);
                outMessage.writeObject(message);
                outMessage.flush();
                socket.shutdownOutput();
            }
           
        } catch (IOException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
