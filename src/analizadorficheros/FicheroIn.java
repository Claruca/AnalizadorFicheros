package analizadorficheros;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author clara
 */
public class FicheroIn {

    private FileReader f;
    private BufferedReader br;
    private int car;
    private int linea;
    private int columna;
    private int numChar;
    private int lineasTotal = 1;
    private int numPal;

    public FicheroIn(String nom) {
        try {
            f = new FileReader(nom);
            br = new BufferedReader(f);
            linea = 1;
            columna = 0;
        } catch (Exception e) {
            System.out.println("Error de apertura");
        }
    }

    //lee char desde fichero
    public int leerChar() {
        try {
            car = br.read();
            if (car == 10) {
                linea++;
                lineasTotal++;
                columna = 0;
            } //10 es line feed. 13 es carridge return
            else {
                if (car != 13) {
                    columna++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error ");
        }
        if(car != -1)numChar++;
        return car;
    }

    public Palabra leerPalabra() {
        Palabra aux = new Palabra();
        leerChar();
        saltarBlancosYOtros();
        aux.setPosLin(linea);
        aux.setPosColumna(columna);
        while (car != -1 && car >= 33) {
            aux.addCaracter((char) car);
            leerChar();
        }
        if(!aux.vacia())numPal++;
        return aux;
    }

    public void saltarBlancosYOtros() {
        while (car != -1
                && car <= 32
                || (car > 34 && car <= 39)
                || (car > 41 && car <44)
                || car == 45
                || car == 47
                || (car >90 && car < 96)
                || car >122) {
            leerChar();
        }
    }

    public int getPalabra() {
        return numPal;
    }

    public int getChar() {
        return numChar;
    }

    public int numLineas() {
        return lineasTotal;
    }

    public void cerrar() {
        try {
            br.close();
            f.close();
        } catch (Exception e) {
            System.out.println("Error de cerrado");
        } finally {
            try {
                f.close();
            } catch (Exception e) {
                System.out.println("Error ");
            }
        }

    }

}
