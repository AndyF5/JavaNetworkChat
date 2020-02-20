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
    private final Collection<Message> chat;
    private final Collection<String> events;
    private final String FILESAVEPATH = "C:/TEMP/Destination";

    public ChatManager(Collection<Message> chat, Collection<String> events) {
        this.chat = chat;
        this.events = events;
    }

    public void connect(String ipDistant, int portDistant) throws IOException {
        Connection con = new Connection(ipDistant, portDistant);
        socket = con.getSenderSocket();
    }

    public void sendMessage(Message message) {
        Thread messageSender = new Thread(new MessageSender(chat, message, socket));
        messageSender.start();
    }

    public void sendFile(File file) {
        Thread fileSender = new Thread(new FileSender(events, file, socket));
        fileSender.start();
    }

    public void startServer() {
        Thread server = new Thread(new ServerThread(chat, events, 5555, FILESAVEPATH));
        server.start();
    }
    public void close(){
      //
    }

}
