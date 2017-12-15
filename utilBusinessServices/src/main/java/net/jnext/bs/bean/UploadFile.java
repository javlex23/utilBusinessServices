/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.bean;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jcernaq
 */
public class UploadFile implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String filename;
    private String type;
    private byte[] bytes;
    private MultipartFile file;

    /**
     * Constructor por defecto
     */
    public UploadFile(){
    }
    
    /**
     * Constructor 
     * @param filename 
     * @param type 
     * @param bytes 
     */
    public UploadFile(String filename, String type, byte[] bytes){
        this.filename = filename;
        this.type = type;
        this.bytes = bytes;
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
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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
    
}
