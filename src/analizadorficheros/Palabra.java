
package analizadorficheros;


public class Palabra {
    
    private final int max = 20;
    private char[] letras;
    private int longitud;
    private int posLin;
    private int posColumna;
    private int numRepeticiones;


    public Palabra() {
        letras = new char[max];
        longitud = 0;
        numRepeticiones = 1;
        
    }

    public void addCaracter(char c) {
        letras[longitud++] = c;
    }

    public boolean vacia() {
        return (longitud == 0);
    }
    public int getLong(){
        return longitud;
    }

    public char[] getLetras() {
        return letras;
    }

    public void setLetras(char[] letras) {
        this.letras = letras;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getPosLin() {
        return posLin;
    }

    public void setPosLin(int posLin) {
        this.posLin = posLin;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }

    public int getNumRepeticiones() {
        return numRepeticiones;
    }

    public void setNumRepeticiones(int numRepeticiones) {
        this.numRepeticiones = numRepeticiones;
    }
    public void aumenta(){
        numRepeticiones++;
    }
    
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < longitud; i++) {
            res = res + letras[i];
        }
        return res;
    }
}
