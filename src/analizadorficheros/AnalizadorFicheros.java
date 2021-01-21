package analizadorficheros;

import java.util.Random;

/**
 * @author clara
 */
public class AnalizadorFicheros {
    
     //Método principal
    public static void main(String[] args) {
        AnalizadorFicheros af = new AnalizadorFicheros();
        af.inicio();
    }

    LT lector = new LT();
    public int numCaracteres;
    public int numPalabras;
    public int semilla;   
    

    public void inicio() {
        String ficheroUsuario;
        System.out.println("Introduce un nombre de fichero para analizar (acabado en txt.): ");        
        ficheroUsuario = lector.leerLinea();
        //ficheroejemplo.txt
        //El fichero de ejemplo se llama así
        
        //Análisis inicial
        FicheroIn fic = new FicheroIn(ficheroUsuario);
        Palabra pal = fic.leerPalabra();
        while (!pal.vacia()) {            
            pal = fic.leerPalabra();
        }
        fic.cerrar();
        
        numCaracteres = fic.getChar();
        numPalabras = fic.getPalabra();
        if (numPalabras > 500) {
            System.out.println("El fichero tiene demasiadas palabras para ser procesado");
        }
        System.out.println("El número total de caracteres es: " + numCaracteres);
        System.out.println("El número total de palabras es: " + numPalabras);
        System.out.println("El número total de líneas es: " + fic.numLineas());

        
        char opcion = ' ';
        while (opcion != 's') {  // mientras no salir
            System.out.println("\n\n");
            System.out.println("**************************************");
            System.out.println("*        Menú      *");
            System.out.println("**************************************");
            System.out.println("Escoge una opción:");
            System.out.println("   1 Letra más repetida");
            System.out.println("   2 Nº apariciones de cada caracter");
            System.out.println("   3 Palabra más repetida");
            System.out.println("   4 Busca una palabra");
            System.out.println("   5 Busca un texto");
            System.out.println("   6 Busca palabras repetidas");
            System.out.println("   7 Codifica el fichero");
            System.out.println("   8 Descodifica el fichero");
            System.out.println("   s Salir");
            System.out.print("Opcion: ");
            opcion = lector.leerCaracter();
            switch (opcion) {
                case '1':
                    Letra letraMax = letraMasRepetida();
                    System.out.println("La letra más repetida es la: " + letraMax.getCaracter() + ", un total de: " + letraMax.getNumRepeticiones() + " veces");
                    break;
                case '2':
                    numCaracteres();
                    break;
                case '3':
                    numPalabras();
                    imprimirPalabra();
                    break;
                case '4':
                    System.out.println("Introduce una palabra");
                    buscaPalabra(lector.leerLinea().toCharArray());                    
                    break;
                case '5':
                    System.out.println("Introduce un texto");
                    char[] textoPasado = lector.leerLinea().toCharArray();
                    buscaTexto(aPalabras(textoPasado));
                    break;
                case '6':                    
                    buscaPalabrasRepetidas();
                    break;
                case '7':
                    System.out.println("Introduce una semilla:");
                    semilla = lector.leerEntero();
                    codificar(semilla);
                    System.out.println("El fichero se ha codificado correctamente");
                    break;
                case '8':                    
                    System.out.println("Introduce una semilla para descodificar: ");
                    int semilladec = lector.leerEntero();
                    System.out.println("Introduce un nombre de fichero para descodificar: ");
                    String decodedFichero = lector.leerLinea();
                    descodificar(semilladec, decodedFichero);
                    break;

            }
        }
        System.out.println("**************************************");
        System.out.println("*            Fin opción              *");
        System.out.println("**************************************");
        System.out.println("\n\n");
    }

    //case 1
    public Letra letraMasRepetida() {
        numAparicionesCaracter();
        int numMax = 0;
        Letra letraMax = null;
        for (int i = 0; i < arrayLetras.length; i++) {
            if ((arrayLetras[i] != null) && arrayLetras[i].getNumRepeticiones() >= numMax) {
                numMax = arrayLetras[i].getNumRepeticiones();
                letraMax = arrayLetras[i];
            }
        }
        return letraMax;
    }

    //case 2
    public void numCaracteres() {
        numAparicionesCaracter();        
        for (int i = 0; i < arrayLetras.length; i++) {
            if (arrayLetras[i] != null) {
                char letra = arrayLetras[i].getCaracter();
                int repe = arrayLetras[i].getNumRepeticiones();
                System.out.println("Letra: " + letra + " repetida: " + repe);
            }
        }       
    }

    //case 3
    Palabra[] arrayPalabras;
    public void numPalabras() {
        FicheroIn fic = new FicheroIn("ficheroejemplo.txt");
        arrayPalabras = new Palabra[numPalabras];
        int ultimaPosicion = 0;
        for (int i = 0; i < numPalabras; i++) {
            Palabra pal1 = fic.leerPalabra();              
            int index = existeEnArrayPalabras(pal1); // si existe, devuelve su posición
            if (index == -1) {
                arrayPalabras[ultimaPosicion] = pal1;
                ultimaPosicion++;
            } else {
                arrayPalabras[index].aumenta();
            }
        }
        fic.cerrar();
    }
    
    //imprime palabra más repetida del array de palabras del texto
    public void imprimirPalabra() {
        int max = 0;
        int j = 0;
        Palabra[] masRep = new Palabra[arrayPalabras.length]; //nuevo array para introducir palabras mas repetidas
        for (int i = 0; i < arrayPalabras.length; i++) {
            if (arrayPalabras[i] != null && arrayPalabras[i].getNumRepeticiones() >= max) {
                max = arrayPalabras[i].getNumRepeticiones();
                masRep[j] = arrayPalabras[i];
                j++;

                for (int a = 0; a < masRep.length; a++) { //comprueba la palabra introducida en el array con las que ya había
                    if ((masRep[a] != null) && masRep[a].getNumRepeticiones() < max) { //si la nueva es mayor, la pone en primera posición y borra las anteriores
                        masRep[a] = arrayPalabras[i];
                        masRep[a] = null;
                    }
                }
            }
        }

        //imprime el array con las palabras más repetidas
        for (int i = 0; i < masRep.length; i++) {
            if (masRep[i] != null) {
                System.out.println(masRep[i] + " veces: " + masRep[i].getNumRepeticiones());
            }
        }
    }

    //case 4
    public void buscaPalabra(char[] pal1) {
        FicheroIn fic = new FicheroIn("ficheroejemplo.txt");
        Palabra palLeida = fic.leerPalabra();
        int hayAlguna = 0;
        while (!palLeida.vacia()) {

            boolean iguales = true;
            if (pal1.length != palLeida.getLongitud()) {
                iguales = false;
            }
            if (pal1.length == palLeida.getLongitud()) {
                for (int i = 0; i < pal1.length; i++) {
                    if (pal1[i] != palLeida.getLetras()[i]) {
                        iguales = false;
                        break;
                    }
                }
                if (iguales) {
                    System.out.print("La fila: " + palLeida.getPosLin() + " --- ");
                    System.out.println("La columna: " + palLeida.getPosColumna());
                    hayAlguna++;
                }
            }
            palLeida = fic.leerPalabra();
        }
        if (hayAlguna == 0) {
            System.out.println("La palabra introducida no está en el texto");
        }
        fic.cerrar();
    }

    //case 5
    public void buscaTexto(Palabra[] texto) {
        FicheroIn fic = new FicheroIn("ficheroejemplo.txt");
        Palabra palLeida = fic.leerPalabra();
        Palabra primeraPalabra = null;
        int idx = 0;
        while (!palLeida.vacia()) {
            boolean iguales = palLeida.sonIguales(texto[idx]);
            if (!iguales) {
                idx = 0;
            } else {
                if (idx == 0) {
                    primeraPalabra = palLeida;
                }
                idx++;
                if (idx == texto.length) {
                    System.out.print("La fila: " + primeraPalabra.getPosLin() + " --- ");
                    System.out.println("La columna: " + primeraPalabra.getPosColumna());
                    idx = 0;
                }
            }
            palLeida = fic.leerPalabra();
        }
    }

    //convierte el texto, char array, pasado por parámetro a palabras para poder buscarlas
    public Palabra[] aPalabras(char[] texto) {
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

    //case 6
    public void buscaPalabrasRepetidas() {
        FicheroIn fic = new FicheroIn("ficheroejemplo.txt");
        Palabra pal = fic.leerPalabra();
        Palabra pal2 = fic.leerPalabra();
        while (!pal2.vacia()) {
            if (pal.sonIguales(pal2)) {
                System.out.println(pal);
                System.out.println("Línea: " + pal.getPosLin());
                System.out.println("Columna: " + pal.getPosColumna());
            }
            pal = pal2;
            pal2 = fic.leerPalabra();
        }
        fic.cerrar();
    }

    //case 7
    Letra[] codificadas;
    char[] alfa;
    public void codificar(int semilla) {
        //Esta primera parte crea una tabla de codificación que usaremos posteriormente
        FicheroIn fic = new FicheroIn("ficheroejemplo.txt");
        FicheroOut fo = new FicheroOut();
        alfa = "abcdefghijklmnopqrstuvwxyz .,:@?!\"()<>".toCharArray();
        char[] cifrado = new char[alfa.length];
        boolean[] usado = new boolean[alfa.length];
        Random r = new Random(semilla);        
        for (int i = 0; i < alfa.length; i++) {
            usado[i] = false;
        }
        int count = 0;
        while (count < alfa.length) {
            int num = r.nextInt(alfa.length);
            if (!usado[num]) {
                cifrado[count] = alfa[num];
                usado[num] = true;
                count++;
            }

        }
        //crea un array de las letras del texto codificadas
        codificadas = new Letra[alfa.length];
        int codidx = 0;
        int index = fic.leerChar();
        boolean encontrado = false;
        while (index != -1) {
            int idxExists = existeLetra((char) index, codificadas);
            if (idxExists == -1) {
                codificadas[codidx] = new Letra((char) index);
                for (int i = 0; i < alfa.length; i++) {
                    if (alfa[i] == index) {
                        codificadas[codidx].setCifrado(cifrado[i]);
                        fo.escribirCifrado(codificadas[codidx]);
                        codidx++;
                        i = alfa.length;
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    fo.escribirChar((char) index);
                }
                encontrado = false;
            } else {
                codificadas[idxExists].getCifrado();
                fo.escribirCifrado(codificadas[idxExists]);
            }
            index = fic.leerChar();
        }
        for (int i = 0; i < alfa.length; i++) {
            System.out.println(alfa[i] + " es " + cifrado[i]);
        }
        fo.cerrar();

    }

//case 7
    public void descodificar(int semilladec, String decodedFichero) {        
        FicheroIn fic = new FicheroIn("fichero.txt.cod.txt");
        FicheroOut fo = new FicheroOut(decodedFichero);
        int caracter = fic.leerChar();
        if (semilladec == semilla) {
            while (caracter != -1) {
                for (int i = 0; i < codificadas.length; i++) {
                    if (codificadas[i] == null) {
                        fo.escribirChar((char) caracter);
                        break;
                    }
                    if (caracter == codificadas[i].getCifrado()) {
                        fo.escribirChar(codificadas[i].getCaracter());
                        i = 0;
                        break;
                    }
                }
                caracter = fic.leerChar();
            }
            System.out.println("El fichero se ha descodificado correctamente");
            fo.cerrar();
        } else {
            System.out.println("La semilla no es correcta o el fichero aún no ha sido codificado");
        }
    }

    //comprueba si una palabra ya está en el array de palabras y devuelve su posición
    public int existeEnArrayPalabras(Palabra pal) {
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

//Recorre todo el fichero añadiendo a una array de letras, la letra y el número de veces que está repetida
    Letra[] arrayLetras;
    public void numAparicionesCaracter() {
        FicheroIn fichero = new FicheroIn("ficheroejemplo.txt");
        arrayLetras = new Letra[numCaracteres];
        int ultimaPosicion = 0;
        for (int i = 0; i < numCaracteres; i++) {
            char caracter = (char) fichero.leerChar();
            int index = existeLetra(caracter, arrayLetras);
            if (index == -1) {
                //fichero.saltarBlancosYOtros();
                if (esLetra(caracter)) {
                    //if (caracter != 32 && caracter != 10 && caracter != 13) {
                    arrayLetras[ultimaPosicion] = new Letra(caracter);
                    ultimaPosicion++;
                }
            } else {
                arrayLetras[index].aumenta();
            }
        }
        fichero.cerrar();
    }

    //mira si el caracter introducido es un caracter apto para crear un objeto letra
    public boolean esLetra(char car) {
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

    

    //submétodo para mirar si existe una letra en la array de letras
    public int existeLetra(char caracter, Letra[] arrayL) {
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

}
