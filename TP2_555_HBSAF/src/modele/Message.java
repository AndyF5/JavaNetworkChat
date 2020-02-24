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
public class Message implements Serializable{

    private final String message;
    private final String user;
    private final String type;

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public Message(String user , String message) {
        this.message = message;
        this.user = user;
        this.type = "Message";
    }

    @Override
    public String toString() {
        return user + " : " + message;
    }
}
