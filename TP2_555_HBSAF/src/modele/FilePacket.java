/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.io.Serializable;

/** Object qui represent un fichier envoyé par réseau.
 *  Ça utilise un propriété «type» pour identifier l'objet comme un fichier quand reçu par l'application.
 * 
 * @author 1897654
 */
public class FilePacket implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fileName;
    
    private byte[] contenu;

    public FilePacket(String fileName, byte[] contenu) {
        this.fileName = fileName;
        this.contenu = contenu;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContenu() {
        return contenu;
    }

    public void setContenu(byte[] contenu) {
        this.contenu = contenu;
    }
}
