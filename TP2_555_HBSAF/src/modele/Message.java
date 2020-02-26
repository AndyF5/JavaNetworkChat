/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;

/**
 *
 * @author 1897483
 */
public class Message implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
    private final String user;
    private transient boolean sentByThis;

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public boolean isSentByThis() {
        return sentByThis;
    }

    public Message(String user, String message, boolean sentByThis) {
        this.message = message;
        this.user = user;
        this.sentByThis = sentByThis;
    }

    public Message(String user, String message) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return user + " : " + message;
    }
}
