/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.file.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.jnext.bs.bean.UploadFile;
import static net.jnext.bs.file.spring.util.Util.writeSeparator;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
    
    /**
     * Método para guardar un archivo en el server
     * @param uploadFile 
     * @return UploadFile
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transfer")
    public UploadFile saveFileInServer(UploadFile uploadFile){
        File dir = new File(uploadFile.getDestinyPath() + writeSeparator());
        LOGGER.debug(dir.getAbsolutePath());
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        try{
            File file = new File(uploadFile.getCopyFile());        

            LOGGER.debug("Archivo seleccionado ======== " + file.getName()); 
            LOGGER.debug("Size archivo seleccionado ======== " + (int) file.length()); 

            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(uploadFile.getFilename(),
            file.getName(), uploadFile.getType(), IOUtils.toByteArray(input));

            uploadFile.setFile(multipartFile); 
            LOGGER.debug("Archivo a grabar ======== " + multipartFile.getOriginalFilename()); 
            LOGGER.debug("Size archivo a grabar ======== " + multipartFile.getBytes().length); 
            uploadFile.getFile().transferTo(
                    new File(uploadFile.getDestinyPath() + writeSeparator() + uploadFile.getFilename()));
            LOGGER.debug("Bytes transferidos a ======== " + uploadFile.getDestinyPath() 
                    + writeSeparator() + uploadFile.getFilename()); 
            
            uploadFile.setTransfer(true);
        }catch(IOException e){
            uploadFile.setTransfer(false);
            LOGGER.debug("Error en archivo: " + e.getMessage());
        }
        
        return uploadFile;
    }
}
