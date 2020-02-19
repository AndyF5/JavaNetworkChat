/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.net.Socket;
import java.util.Collection;

/**
 *
 * @author 1897483
 */
public class FileSender implements Runnable{

    private final Collection<String> envent;
    private final FilePacket file;
    private final Socket socket;

    public FileSender(Collection<String> envent, FilePacket file, Socket socket) {
        this.envent = envent;
        this.file = file;
        this.socket = socket;
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
