package analizadorficheros;

public class Letra {

    char caracter;
    int numRepeticiones;
    char cifrado;
    int codi;

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }
    

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

    public char getCifrado() {
        return cifrado;
    }

    public void setCifrado(char cifrado) {
        this.cifrado = cifrado;
    }
    

}
