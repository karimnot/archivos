/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.serializa;

import files.Modo;
import files.binarios.AbstractBinaryFile;
import files.exception.ModoAperturaIncorrectoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author karimnot
 */
public class SerializeFile extends AbstractBinaryFile {

    private ObjectInputStream reader;
    private ObjectOutputStream writer; 
    
    public SerializeFile(String filename, Modo modo) throws FileNotFoundException, IOException, ModoAperturaIncorrectoException{
        super(filename, modo);
        switch (super.getMode()) {
            case OUTPUT:
                writer = new ObjectOutputStream(getOutput());
                break;
            case INPUT:
                reader = new ObjectInputStream(getInput());
                break;
            default:
                throw new ModoAperturaIncorrectoException();
        }
        
    }
    
    public void writeObject(Object obj) throws IOException, ModoAperturaIncorrectoException{
        validateOperation(Modo.OUTPUT);
        writer.writeObject(obj);
    }
    
    public Object readObject() throws IOException, ClassNotFoundException, ModoAperturaIncorrectoException{
        validateOperation(Modo.INPUT);
        return reader.readObject();
    }  
    
    @Override
    public void close() throws IOException{
        super.close();
        if (getMode() == Modo.INPUT){
            reader.close();
        }else{
            writer.close();
        }
    }      
    
}
