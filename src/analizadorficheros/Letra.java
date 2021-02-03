package analizadorficheros;

public class Letra {

    char caracter;
    int numRepeticiones;
    char cifrado;
    int codi;
    

    public Letra(char caracter) {
        this.caracter = caracter;
        numRepeticiones = 1;
    }

    //aumenta el contador
    public void aumenta() {
        numRepeticiones++;
    }
    
    //comprueba si el caracter pasado es adecuado para crear un objeto Letra
    public static boolean esLetra(char car) {
        if (!(car <= 32
                || (car > 34 && car <= 39)
                || (car > 41 && car < 44)
                || car == 45
                || car == 47
                || (car > 90 && car < 96)
                || car > 122)) {
            return true;
        }
        return false;
    }
    
    //si existe la letra (o caracter que la representa) en el array, devuelve su posición
      public static int existeLetra(char caracter, Letra[] arrayL) {
        int index = -1;
        for (int i = 0; i < arrayL.length; i++) {
            if (arrayL[i] == null) {
                return index;
            } else {
                if (arrayL[i].getCaracter() == caracter) {
                    index = i;
                }
            }
        }
        return index;
    }
    

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(int numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }

    public char getCifrado() {
        return cifrado;
    }

    public void setCifrado(char cifrado) {
        this.cifrado = cifrado;
    }

    @Override
    public String toString() {
        return " "+ caracter + "cifrado: " + cifrado;
    }
    
    

}
