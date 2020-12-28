package analizadorficheros;

public class Letra {

    char caracter;
    int numRepeticiones;

    public Letra(char caracter) {
        this.caracter = caracter;
        numRepeticiones = 1;
    }

    public void aumenta() {
        numRepeticiones++;
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

}
