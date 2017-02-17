/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import files.texto.Modo;
import files.texto.TextFile;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author redoaxaca
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

//        File file = new File("archivo3.txt");
//        FileWriter writer = new FileWriter(file, true);
//        FileReader reader = new FileReader(file);
//
//        int valor = reader.read();
//        while ((valor != -1)&&(valor != 10)) {
//            System.out.print((char) valor);
//            valor = reader.read();
//        }
//
//        writer.close();
//        reader.close();
        
        TextFile text = new TextFile("archivo3.txt", Modo.READ);
        
        
       
//        
//        String a = text.leerLinea();
//        while( a != null){
//            System.out.println(a);
//            a = text.leerLinea();
//                
//        }
        
        
        
        String a = text.leerPalabra();
        while ( a!= null){
            System.out.println(a);
            a = text.leerPalabra();            
        }
        
        //text.cambiarModo(Modo.APPEND);
        
        //text.escribirLinea("esta debe ser una nueva linea");
        
        System.out.println("tan ta");
        text.cerrar();
        

    }

}
