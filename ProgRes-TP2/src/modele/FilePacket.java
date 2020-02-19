/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.File;
import java.io.Serializable;

/** Object qui respresent un fichier envoyé par réseau.
 *  Ça utilise un proprieté «type» pour identifier l'objet comme un fichier quand reçu par l'application.
 * 
 * @author 1897654
 */
public class FilePacket implements Serializable{
    private final String type;
    private final File file;

    public FilePacket(File file) {
        this.type = "File";
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public File getFile() {
        return file;
    }
}
