/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import files.exception.ModoAperturaIncorrectoException;
import files.Modo;
import files.binarios.BinaryFile;
import files.serializa.SerializeFile;
import files.texto.TextFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

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

            ejemploSerializar();
        } catch (IOException | ModoAperturaIncorrectoException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
    
    public static void ejemploSerializar() throws IOException, FileNotFoundException, ModoAperturaIncorrectoException, ClassNotFoundException{
        SerializeFile objetos = new SerializeFile("objetos.dat", Modo.OUTPUT);
        Objetitos obj1 = new Objetitos(1, "AA");
        Objetitos obj2 = new Objetitos(2, "BB");
        Objetitos obj3 = new Objetitos(3, "CC");
        Objetitos obj4 = new Objetitos(4, "DD");
        
        objetos.writeObject(obj1);
        objetos.writeObject(obj2);
        objetos.writeObject(obj3);
        objetos.writeObject(obj4);
        
        objetos.close();
        
        SerializeFile lectura = new SerializeFile("objetos.dat", Modo.INPUT);
        while (!lectura.eof()){
            Objetitos obj = (Objetitos)lectura.readObject();
            System.out.println(obj.getNumero() + obj.getString());
        }
        lectura.close();
    }

    public static void ejemploBinaryFile() throws FileNotFoundException, ModoAperturaIncorrectoException, IOException {
        BinaryFile origen = new BinaryFile("archivo.pdf", Modo.INPUT);
        BinaryFile destino = new BinaryFile("archivo3.pdf", Modo.OUTPUT);
        destino.writeByte(origen.readByteLeft());
        origen.close();
        destino.close();

        BinaryFile lecturaxBytes = new BinaryFile("archivo3.pdf", Modo.INPUT);
        while (!lecturaxBytes.eof()) {
            System.out.println(lecturaxBytes.readByte());
        }
    }

    public static void ejemploTextFile() throws IOException, ModoAperturaIncorrectoException {

        TextFile text = new TextFile("test01.txt", Modo.REWRITE);
        text.writeLn("La linea debe ser independiente");
        text.write("Esta linea debe estar acompañada de la siguiente ---> ");
        text.write('K');
        text.close();

        TextFile text02 = new TextFile("test01.txt", Modo.APPEND);
        text02.writeLn("\n Se agrega una linea más ");
        text02.close();

        System.out.println("**************************");

        TextFile text03 = new TextFile("test01.txt", Modo.READ);
        String linea = text03.ReadLn();
        while (linea != null) {
            System.out.println(linea);
            linea = text03.ReadLn();
        }
        text03.close();

        System.out.println("**************************");

        TextFile text04 = new TextFile("test01.txt", Modo.READ);
        String palabra = text04.readWord();
        while (palabra != null) {
            System.out.println(palabra);
            palabra = text04.readWord();
        }
        text04.close();

        System.out.println("**************************");

        TextFile text05 = new TextFile("test01.txt", Modo.READ);
        Character ch = text05.readChar();
        while (ch != null) {
            System.out.println(ch);
            ch = text05.readChar();
        }
        text05.close();
    }

}





class Objetitos implements Serializable {
    
    private Integer numero;
    private String string;
    
    public Objetitos(Integer n, String s){
        numero = n;
        string = s;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }  
    
}