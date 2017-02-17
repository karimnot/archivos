/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files.texto;

import files.GenericFile;
import files.Modo;
import files.exception.ModoAperturaIncorrectoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author karimnot
 */
public class TextFile extends GenericFile {

    public static final Integer EOL = 10;
    public static final Integer SPACE = 32;

    private FileWriter writer;
    private FileReader reader;
    private Modo modo;

    public TextFile(String archivo, Modo modo) throws IOException, IOException, FileNotFoundException, ModoAperturaIncorrectoException {
        super(archivo);
        this.modo = modo;
        openFile(modo);
    }

    public void changeMode(Modo modo) throws IOException, IOException, ModoAperturaIncorrectoException {
        if (this.modo != modo) {
            switch (this.modo) {
                case READ:
                    reader.close();
                    break;
                case REWRITE:
                case APPEND:
                    writer.close();
                default:
                    throw new ModoAperturaIncorrectoException();
            }
            openFile(modo);
            this.modo = modo;
        }
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
        if (reader != null) {
            reader.close();
        }
    }

    private void openFile(Modo modo) throws FileNotFoundException, IOException, ModoAperturaIncorrectoException {
        switch (modo) {
            case READ:
                reader = new FileReader(getFile());
                break;
            case REWRITE:
                writer = new FileWriter(getFile());
                break;
            case APPEND:
                writer = new FileWriter(getFile(), true);
                break;
            default:
                throw new ModoAperturaIncorrectoException();
        }
    }

    public String ReadLn() throws IOException, ModoAperturaIncorrectoException {
        if (modo == Modo.READ) {
            StringBuilder salida = new StringBuilder();
            int ascii = reader.read();
            while ((ascii != -1) && (ascii != EOL)) {
                salida.append((char) ascii);
                ascii = reader.read();
            }
            if (ascii == -1) {
                return null;
            }
            return salida.toString();
        }
        throw new ModoAperturaIncorrectoException();
    }

    public String readWord() throws IOException, ModoAperturaIncorrectoException {
        if (modo == Modo.READ) {
            StringBuilder salida = new StringBuilder();
            int ascii = 0;
            do {
                ascii = reader.read();
                while ((ascii != -1) && (ascii != EOL) && (ascii != SPACE)) {
                    salida.append((char) ascii);
                    ascii = reader.read();
                }
            } while ((salida.length() == 0) && ((ascii != -1)));
            if (ascii == -1) {
                return null;
            }
            return salida.toString();
        }
        throw new ModoAperturaIncorrectoException();
    }

    public Character readChar() throws IOException, ModoAperturaIncorrectoException {
        if (modo == Modo.READ) {
            int ascii = reader.read();
            return ascii == -1 ? null : new Character((char) ascii);
        }
        throw new ModoAperturaIncorrectoException();
    }

    private void write(String text, String formato) throws IOException, ModoAperturaIncorrectoException {
        if (modo == Modo.APPEND) {
            writer.append(String.format(formato, text));
        } else if (modo == Modo.REWRITE) {
            writer.write(String.format(formato, text));
        } else {
            throw new ModoAperturaIncorrectoException();
        }
    }

    public void writeLn(String text) throws IOException, ModoAperturaIncorrectoException {
        write(text, "%s\n");
    }

    public void write(String text) throws IOException, ModoAperturaIncorrectoException {
        write(text, "%s");
    }

    public void write(char character) throws IOException, ModoAperturaIncorrectoException {
        write(String.valueOf(character), "%s");
    }

    @Override
    public void finalize() throws IOException {
        close();
    }

}
