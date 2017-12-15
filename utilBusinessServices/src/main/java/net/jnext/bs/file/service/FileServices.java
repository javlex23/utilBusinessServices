/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.file.service;

import javax.servlet.http.Part;

/**
 *
 * @author jcernaq
 */
public class FileServices {
    /**
     * 
     * @param part 
     * @return nombre de archivo
     */
    public String getFileName(Part part){
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for(String token : tokens){
            if(token.trim().startsWith("filename")){
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
