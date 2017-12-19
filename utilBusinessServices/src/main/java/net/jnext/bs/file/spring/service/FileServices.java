/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.file.spring.service;

import java.io.File;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.jnext.bs.bean.UploadFile;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import static net.jnext.bs.file.spring.util.Util.getPathServerFile;
import static net.jnext.bs.file.spring.util.Util.getSeparator;

/**
 *
 * @author jcernaq
 */
@Path("/files")
public final class FileServices {
    
    /**
     * Constructor por defecto
     */
    public FileServices(){
    }
    
    private static final Logger LOGGER = LogManager.getLogger(FileServices.class);
    
    @GET
    @Path("/version")
    public String version(){
        return "1.0.0";
    }
    
    /**
     * 
     * @param uploadFile 
     * @return UploadFile
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transfer")
    public UploadFile saveFileInServer(UploadFile uploadFile){
        File dir = new File(getPathServerFile() + getSeparator() + uploadFile.getDestinyPath());
        
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        try{
            File file = new File(uploadFile.getCopyFile());        
        
            DiskFileItem fileItem = new DiskFileItem(
                    uploadFile.getFilename(), uploadFile.getType(), false, file.getName(), 
                    (int) file.length() , file.getParentFile());
            fileItem.getOutputStream();
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);       

            uploadFile.setFile(multipartFile); 
            uploadFile.setBytes(fileItem.get());
            LOGGER.debug("Archivo a grabar ========" + multipartFile.getOriginalFilename()); 

            uploadFile.getFile().transferTo(file);
            
            uploadFile.setTransfer(true);
        }catch(IOException e){
            uploadFile.setTransfer(false);
            LOGGER.debug("Error en archivo: " + e.getMessage());
        }
        
        return uploadFile;
    }
}
