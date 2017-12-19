/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.file.spring.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.jnext.bs.enums.ExtensionFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author jcernaq
 */
public class Util {
    /**
     * Método para obtener una lista Multipart de un request
     * @param request 
     * @return lista de Multipart
     */
    public static  List<MultipartFile> getLstMultipartFileFromRequest(MultipartHttpServletRequest request){
        Iterator<String> itr = request.getFileNames();
        List<MultipartFile> lst = new ArrayList<>();
        while (itr.hasNext()){
                lst.add(request.getFile(itr.next()));
        }
        return lst;
    }
    
    /**
     * Método para verificar el tipo de un documento
     * @param filetype extensión del archivo
     * @param extension extensión a comparar
     * @return boolean
     */
    public static boolean checkExtension(String filetype, ExtensionFile extension) {
        return filetype.equals(extension.getValue());
    }
    
    /**
     * Método para determinar si un archivo está vacío
     * @param mpf 
     * @return boolean
     */
    public static boolean isEmptyFile(MultipartFile mpf){
        return mpf == null || mpf.getSize()==0 || mpf.getOriginalFilename().isEmpty();
    }
    
    public static String getSeparator(){
        return System.getProperty("file.separator");
    }
    
    /**
     * 
     * @return ruta servidor
     */
    public static String getPathServerFile(){
        String separador = getSeparator();
        if(separador.equals("/")){
            return System.getProperty("file.server.linux");
        } else {
            return System.getProperty("file.server.windows");
        }
    }
}
