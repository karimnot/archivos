/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.binarios;

import files.Modo;
import files.exception.ModoAperturaIncorrectoException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author karimnot
 */
public class BinaryFile extends AbstractBinaryFile {

    public BinaryFile(String filename, Modo modo) throws FileNotFoundException, ModoAperturaIncorrectoException {
        super(filename, modo);
    }

    public int readByte() throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);
        return getInput().read();
    }

    public byte[] readByte(int n) throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);
        byte[] retorno = new byte[n];
        getInput().read(retorno);
        return retorno;
    }

    public byte[] readByteLeft() throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);        
        byte[] retorno = new byte[getInput().available()];
        getInput().read(retorno);
        return retorno;
    }
    
    public void writeByte(int byt) throws ModoAperturaIncorrectoException, IOException{
        validateOperation(Modo.OUTPUT);
        getOutput().write(byt);
    }
    
    public void writeByte(byte[] byts) throws ModoAperturaIncorrectoException, IOException{
        validateOperation(Modo.OUTPUT);
        getOutput().write(byts);
    }   
       
    
}
