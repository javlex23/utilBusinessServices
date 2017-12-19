/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnext.bs.enums;

/**
 *
 * @author jcernaq
 */
public enum ContentTypeFile {
    /**
     * Tipos MIME
     */
    CSS("text/css"), CSV("text/csv"), GIF("image/gif"), ICON("image/x-icon"),
    JPEG("image/jpeg"), PDF("application/pdf"), MICROSOFT_POWER_POINT("application/vnd.ms-powerpoint"), 
    MICROSOFT_WORD("application/msword"), RAR("application/x-rar-compressed"), RTF("application/rtf"), 
    SVG("image/svg+xml"), TIFF("image/tiff"), MICROSOFT_VISIO("application/vnd.visio"), 
    MICROSOFT_EXCEL("application/vnd.ms-excel"), XML("application/xml"), ZIP("application/zip"), 
    TEXT_DEFAULT("text/plain"), OTHER_DEFAULT("application/octet-stream"), HTML("text/html");
    
    private final String value;

    ContentTypeFile(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    
}
