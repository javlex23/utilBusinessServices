/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jcernaq
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadFile implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String filename; //Nombre del archivo a crear
    private String type; //Tipo de archivo a crear
    private String destinyPath; //Path destino donde copiar el archivo en el server
    private transient MultipartFile file; //Multipart capturado
    private String copyFile; //Archivo a copiar
    private boolean transfer; //Estado de la transferencia

    /**
     * Constructor por defecto
     */
    public UploadFile(){
    }
    
    /**
     * Constructor 
     * @param filename 
     */
    public UploadFile(String filename){
        this.filename = filename;
    }
    
    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        if(filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0){
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        return "";
    }

    /**
     * @return the type
     */
    public String getContentType() {
        return file.getContentType();
    }

    /**
     * 
     * @return array de bytes
     * @throws IOException 
     */
    public byte[] getBytes() throws IOException {
        return file.getBytes();
    }

    /**
     * 
     * @return tamaño del archivo
     * @throws IOException 
     */
    public int getSize() throws IOException{
        return getBytes().length;
    }
    
    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the destinyPath
     */
    public String getDestinyPath() {
        return destinyPath;
    }

    /**
     * @param destinyPath the destinyPath to set
     */
    public void setDestinyPath(String destinyPath) {
        this.destinyPath = destinyPath;
    }

    /**
     * @return the copyFile
     */
    public String getCopyFile() {
        return copyFile;
    }

    /**
     * @param copyFile the copyFile to set
     */
    public void setCopyFile(String copyFile) {
        this.copyFile = copyFile;
    }
    
    /**
     * @return the transfer
     */
    public boolean isTransfer() {
        return transfer;
    }

    /**
     * @param transfer the transfer to set
     */
    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
