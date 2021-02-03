package analizadorficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class FicheroOut {

    private FileWriter fw;
    private BufferedWriter bw;

    public FicheroOut() {
        try {
            fw = new FileWriter("fichero.txt.cod.txt");
            bw = new BufferedWriter(fw);

        } catch (Exception e) {
            System.out.println("Error de apertura");
        }
    }
    
     public FicheroOut(String nombre) {
        try {
            fw = new FileWriter(nombre+".txt");
            bw = new BufferedWriter(fw);

        } catch (Exception e) {
            System.out.println("Error de apertura");
        }
    }

    public void escribirChar(char c) {
        try {
            bw.write(c);
        }
         catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void escribirCifrado(Letra l) {
        try {
            bw.write(l.getCifrado());
           
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void cerrar() {
        try {
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error de cerrado");
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                System.out.println("Error ");
            }
        }

    }

}
