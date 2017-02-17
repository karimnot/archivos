/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.binarios;

import files.GenericFile;
import files.Modo;
import files.exception.ModoAperturaIncorrectoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author karimnot
 */
public abstract class AbstractBinaryFile extends GenericFile {
    
    private FileInputStream input;
    private FileOutputStream output;    
    
    public AbstractBinaryFile(String filename, Modo modo) throws FileNotFoundException, ModoAperturaIncorrectoException{
        super(filename);
        super.setMode(modo);
        switch (modo) {
            case INPUT:
                input = new FileInputStream(super.getFile());
                break;
            case OUTPUT:
                output = new FileOutputStream(super.getFile());
                break;
            default:
                throw new ModoAperturaIncorrectoException();
        }
    }
    
    public void close() throws IOException{
        if (getMode() == Modo.INPUT){
            input.close();
        }else{
            output.close();
        }
    }
   
    public boolean eof() throws ModoAperturaIncorrectoException, IOException{
        validateOperation(Modo.INPUT);
        return input.available() <= 0;
    }  
    

    protected FileInputStream getInput() {
        return input;
    }

    protected void setInput(FileInputStream input) {
        this.input = input;
    }

    protected FileOutputStream getOutput() {
        return output;
    }

    protected void setOutput(FileOutputStream output) {
        this.output = output;
    }
    
    
    
    
}
