package analizadorficheros;

import java.io.FileWriter;
import java.util.Random;

public class FicheroOut {

    private FileWriter fw;

    public void escribir() {
        try {
            fw = new FileWriter("fichero.txt.cod.txt");
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
            fw.write("Prueba");
            fw.write(56);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
