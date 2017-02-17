/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import files.exception.ModoAperturaIncorrectoException;
import files.Modo;
import files.texto.TextFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

            File file = new File("archivo.pdf");
            FileInputStream input = new FileInputStream(file);

            File file2 = new File("archivo2.pdf");

            FileOutputStream output = new FileOutputStream(file2);

            while (input.available() > 0) {
                int a = input.read();
                System.out.println(a);
                output.write(a);
            }

            input.close();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void ejemploTextFile() throws IOException {

        TextFile text = new TextFile("test01.txt", Modo.REWRITE);
        try {
            text.escribirLinea("La linea debe ser independiente");
            text.escribir("Esta linea debe estar acompañada de la siguiente ---> ");
            text.escribir('K');
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text.cerrar();

        TextFile text02 = new TextFile("test01.txt", Modo.APPEND);
        try {
            text02.escribirLinea("\n Se agrega una linea más ");
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text02.cerrar();

        System.out.println("**************************");

        TextFile text03 = new TextFile("test01.txt", Modo.READ);
        try {
            String linea = text03.leerLinea();
            while (linea != null) {
                System.out.println(linea);
                linea = text03.leerLinea();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text03.cerrar();

        System.out.println("**************************");

        TextFile text04 = new TextFile("test01.txt", Modo.READ);
        try {
            String linea = text04.leerPalabra();
            while (linea != null) {
                System.out.println(linea);
                linea = text04.leerPalabra();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text04.cerrar();

        System.out.println("**************************");

        TextFile text05 = new TextFile("test01.txt", Modo.READ);
        try {
            Character ch = text05.leerCaracter();
            while (ch != null) {
                System.out.println(ch);
                ch = text05.leerCaracter();
            }
        } catch (ModoAperturaIncorrectoException ex) {
            ex.printStackTrace();
        }
        text05.cerrar();
    }

}
