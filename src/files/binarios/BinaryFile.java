/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.binarios;

import files.Modo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author karimnot
 */
public class BinaryFile {
    
    private File file;
    private FileInputStream input;
    private FileOutputStream output;
    
    public BinaryFile(String file, Modo modo) throws FileNotFoundException{
        this.file = new File(file);
        if (modo == Modo.INPUT){
            input = new FileInputStream(file);
        }
        output = new FileOutputStream(file);
    }
    
    
    
    
    
    
}
