/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.jafc.components.excelbuilder;

import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author usuario
 */
public class ZipBuilder {
    
    private String directory;
    private String zipFolder;
    private String zipName;
    private FileOutputStream file;
    private ZipOutputStream zipFile;

    public ZipBuilder(String directory, String zipFolder, String zipName) {
        this.directory = directory;
        this.zipFolder = zipFolder;
        this.zipName = zipName;
    }
    
    //public 
    
    
    
}
