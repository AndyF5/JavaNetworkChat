/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

/**
 *
 * @author 1897483
 */
public class ChatManager {

    private Socket socket;
    private Connection con;
    private final Collection<Message> chat;
    private final Collection<String> events;
    private final String FILESAVEPATH = "C:/TEMP/Destination";
    Thread server;

    public ChatManager(Collection<Message> chat, Collection<String> events) {
        this.chat = chat;
        this.events = events;
    }

    public void connect(String ipDistant, int portDistant) throws IOException {
        con = new Connection(ipDistant, portDistant);
        socket = con.getSenderSocket();
        
        events.add("Connection établie avec le serveur : [" + socket.getRemoteSocketAddress() + "]");  
    }

    public void sendMessage(Message message) {
        if (message != null && socket.isConnected()){
            Thread messageSender = new Thread(new MessageSender(chat, message, socket));
            messageSender.start();
        }
    }

    public void sendFile(File file) {
        if (file != null && socket.isConnected()){
            Thread fileSender = new Thread(new FileSender(events, file, socket));
            fileSender.start();
        }
    }

    public void startServer() {
        server = new Thread(new ServerThread(chat, events, 5555, FILESAVEPATH));
        
        server.start();
    }
    
    public void close() throws IOException{
        if(con!=null){
            con.close();
        }
      
      server.interrupt();
    }

}
