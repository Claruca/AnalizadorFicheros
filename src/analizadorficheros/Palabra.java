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
        if (longitud <= 19) {
            letras[longitud++] = c;
        } else {
            System.out.println("Palabra demasiado larga");

        }

    }

    public boolean vacia() {
        return (longitud == 0);
    }

    public int getLong() {
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

    public void aumenta() {
        numRepeticiones++;
    }

    public boolean sonIguales(Palabra pal2) {
        if (this.longitud != pal2.getLongitud()) {
            return false;
        }
        for (int i = 0; i < this.longitud; i++) {
            if (this.letras[i] != pal2.getLetras()[i]) {
                return false;
            }
        }
        return true;
    }
    
    //comprueba si una palabra ya está en el array de palabras y devuelve su posición
    public static int existeEnArrayPalabras(Palabra pal, Palabra[] arrayPalabras) {
        int index = -1;
        boolean iguales = true;
        for (int i = 0; i < arrayPalabras.length; i++) {
            iguales = true; //inicializa variable en cada iteración
            if (arrayPalabras[i] == null) {
                return index;
            } else {
                if (arrayPalabras[i].getLongitud() != pal.getLongitud()) {
                    iguales = false;
                }
                if (arrayPalabras[i].getLongitud() == pal.getLongitud()) {
                    for (int j = 0; j < arrayPalabras[i].getLongitud(); j++) {
                        if (arrayPalabras[i].getLetras()[j] != pal.getLetras()[j]) {
                            iguales = false;
                            break;
                        }
                    }
                }
                if (iguales) {
                    index = i;
                    return index;
                }
            }
        }
        return index;
    }    
    
    //convierte el texto, char array, pasado por parámetro a palabras para poder buscarlas
    public static Palabra[] aPalabras(char[] texto) {
        int espacios = 1;
        Palabra aux = new Palabra();
        for (int i = 0; i < texto.length; i++) {
            if (texto[i] == 32) {
                espacios++;
            }
        }
        Palabra[] textoPal = new Palabra[espacios];
        int idx = 0;
        for (int i = 0; i < texto.length; i++) {
            if (texto[i] != 32) {
                aux.addCaracter(texto[i]);
            } else {
                textoPal[idx] = aux;
                aux = new Palabra();
                idx++;
            }
        }
        textoPal[idx] = aux;
        return textoPal;
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
