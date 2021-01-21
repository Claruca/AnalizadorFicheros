package analizadorficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

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

            /*
            // Random semilla definida
            Random rSem = new Random(123);
            for (int i = 0; i < 10; i++) {
                System.out.print(rSem.nextInt(10));
            }
            System.out.println("");
            // Al ser la semilla definida por nosotros podemos generar la misma secuencia
            Random rSem1 = new Random(123);
            for (int i = 0; i < 10; i++) {
                System.out.print(rSem1.nextInt(20));
            }

             */
            //          bw.write("Prueba");
            //        bw.newLine();
            //      bw.write(56);
            //    bw.close();
            //  fw.close();
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
