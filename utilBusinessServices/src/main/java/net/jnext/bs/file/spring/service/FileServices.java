/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.file.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.jnext.bs.bean.UploadFile;
import net.jnext.bs.enums.ExtensionFile;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jcernaq
 */
public final class FileServices {
    
    /**
     * Constructor por defecto
     */
    private FileServices(){
    }
    
    private static final Logger LOGGER = LogManager.getLogger(FileServices.class);
    
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
    
    private static String getSeparator(){
        return System.getProperty("file.separator");
    }
    
    /**
     * 
     * @return ruta servidor
     */
    private static String getPathServerFile(){
        String separador = getSeparator();
        if(separador.equals("/")){
            return System.getProperty("file.server.linux");
        } else {
            return System.getProperty("file.server.windows");
        }
    }
    
    /**
     * 
     * @param uploadFile 
     * @param path 
     * @param copyFile 
     * @return
     * @throws IOException 
     */
    public String saveFileInServer(UploadFile uploadFile, String path, String copyFile) throws IOException{
        File dir = new File(path);
        
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(path + getSeparator() + copyFile);        
        
        DiskFileItem fileItem = new DiskFileItem(
                uploadFile.getFilename(), uploadFile.getType(), false, file.getName(), 
                (int) file.length() , file.getParentFile());
        fileItem.getOutputStream();
        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);       
       
        uploadFile.setFile(multipartFile); 
        
        LOGGER.debug("Nombre del archivo de sustento a grabar ===================" 
                + multipartFile.getOriginalFilename()); 
        
        uploadFile.getFile().transferTo(file);        
        
        return multipartFile.getOriginalFilename();
    }
}
