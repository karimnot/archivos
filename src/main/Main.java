/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import files.exception.ModoAperturaIncorrectoException;
import files.Modo;
import files.binarios.BinaryFile;
import files.texto.TextFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karimnot
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            ejemploBinaryFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ModoAperturaIncorrectoException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void ejemploBinaryFile() throws FileNotFoundException, ModoAperturaIncorrectoException, IOException{
        BinaryFile origen = new BinaryFile("archivo.pdf", Modo.INPUT);
        BinaryFile destino = new BinaryFile("archivo3.pdf", Modo.OUTPUT);
        destino.writeByte(origen.readByteLeft());
        origen.close();
        destino.close();
    }

    public static void ejemploTextFile() throws IOException, ModoAperturaIncorrectoException {

        TextFile text = new TextFile("test01.txt", Modo.REWRITE);
        try {
            text.writeLn("La linea debe ser independiente");
            text.write("Esta linea debe estar acompañada de la siguiente ---> ");
            text.write('K');
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text.close();

        TextFile text02 = new TextFile("test01.txt", Modo.APPEND);
        try {
            text02.writeLn("\n Se agrega una linea más ");
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text02.close();

        System.out.println("**************************");

        TextFile text03 = new TextFile("test01.txt", Modo.READ);
        try {
            String linea = text03.ReadLn();
            while (linea != null) {
                System.out.println(linea);
                linea = text03.ReadLn();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text03.close();

        System.out.println("**************************");

        TextFile text04 = new TextFile("test01.txt", Modo.READ);
        try {
            String linea = text04.readWord();
            while (linea != null) {
                System.out.println(linea);
                linea = text04.readWord();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text04.close();

        System.out.println("**************************");

        TextFile text05 = new TextFile("test01.txt", Modo.READ);
        try {
            Character ch = text05.readChar();
            while (ch != null) {
                System.out.println(ch);
                ch = text05.readChar();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text05.close();
    }

}
