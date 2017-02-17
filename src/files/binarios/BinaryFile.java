/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.binarios;

import files.GenericFile;
import files.Modo;
import files.exception.ModoAperturaIncorrectoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author karimnot
 */
public class BinaryFile extends GenericFile {

    private FileInputStream input;
    private FileOutputStream output;
    private Modo modo;

    public BinaryFile(String file, Modo modo) throws FileNotFoundException, ModoAperturaIncorrectoException {
        super(file);
        this.modo = modo;
        if (null != modo) switch (modo) {
            case INPUT:
                input = new FileInputStream(file);
                break;
            case OUTPUT:
                output = new FileOutputStream(file);
                break;
            default:
                throw new ModoAperturaIncorrectoException();
        }
    }

    private void validateOperation(Modo modo) throws ModoAperturaIncorrectoException {
        if (this.modo != modo) {
            throw new ModoAperturaIncorrectoException();
        }
    }

    public int readByte() throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);
        return input.read();
    }

    public byte[] readByte(int n) throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);
        byte[] retorno = new byte[n];
        input.read(retorno);
        return retorno;
    }

    public byte[] readByteLeft() throws IOException, ModoAperturaIncorrectoException {
        validateOperation(Modo.INPUT);        
        byte[] retorno = new byte[input.available()];
        input.read(retorno);
        return retorno;
    }
    
    public void writeByte(int byt) throws ModoAperturaIncorrectoException, IOException{
        validateOperation(Modo.OUTPUT);
        output.write(byt);
    }
    
    public void writeByte(byte[] byts) throws ModoAperturaIncorrectoException, IOException{
        validateOperation(Modo.OUTPUT);
        output.write(byts);
    }   
    
    public void close() throws IOException{
        if (modo == Modo.INPUT){
            input.close();
        }else{
            output.close();
        }
    }

}
