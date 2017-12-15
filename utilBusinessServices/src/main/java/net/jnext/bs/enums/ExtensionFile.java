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
public enum ExtensionFile {
    /**
     * Tipos de extensión de archivos
     */
    PDF("pdf"), JPG("jpg"), PNG("png"), DOC("doc"), DOCX("docx"), XLS("xls"), XLSX("xlsx"), PPT("ppt"), PPTX("pptx");
    
    private final String value;
    
    ExtensionFile(String value){
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
