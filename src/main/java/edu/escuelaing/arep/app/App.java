package edu.escuelaing.arep.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * public static void main( String[] args ) { System.out.println( "Hello
     * World!" ); }
     *
     */
    public static DecimalFormat round = new DecimalFormat("#.00");

    /**
     * Este metodo permite simular los experimentos de la canicas con
     * coeficiente booleanos.
     *
     * @param matriz Matriz inicial.
     * @param vector Vector inicial.
     * @param n Dimension de la matriz.
     * @param m Dimension de la matriz.
     * @param clicks Numero de clicks.
     * @return Retorna un ArrayList con el vector resultante.
     */
    public static ArrayList<Double> canicas(Double[][] matriz, ArrayList<Double> vector, int n, int m, int clicks) {
        for (int x = 0; x < clicks; x++) {
            ArrayList<Double> respuesta = new ArrayList<Double>();
            for (int i = 0; i < n; i++) {
                double c = 0;
                for (int j = 0; j < m; j++) {
                    c += matriz[i][j] * vector.get(j);
                }
                respuesta.add(c);
            }
            vector = respuesta;
        }

        return vector;
    }

    /**
     * Este metodo permite simular experimentos de las múltiples rendijas
     * clásico probabilístico, con más de dos rendijas.
     *
     * @param rendijas Numero de rendijas.
     * @param blancos Numero de blancos.
     * @param clicks Numero de clicks.
     * @param vector Vector inicial.
     * @return Retorna un ArrayList con el vector resultante.
     */
    public static ArrayList<Double> rendija(int rendijas, int blancos, int clicks, ArrayList<Double> vector) {
        int n = vector.size();
        Double[][] matriz = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = 0.0;
            }
        }
        int bT = blancos * rendijas - (rendijas - 1);
        int cont = n - 1;

        for (int x = 1; x < (rendijas + 1); x++) {
            matriz[x][0] = (double) 1 / rendijas;
        }
        for (int x = 0; x < bT; x++) {
            matriz[cont][cont] = 1.0;
            cont -= 1;
        }
        int ini = rendijas + 1;

        for (int x = 1; x < (rendijas + 1); x++) {
            for (int y = 0; y < blancos; y++) {
                matriz[ini + y][x] = (double) 1 / blancos;
            }
            ini += blancos - 1;
        }

        return canicas(matriz, vector, n, n, clicks);
    }

    /**
     *
     * @param n Numero de clicks
     * @param matriz Matriz inicial.
     * @param vector Vector inicial.
     * @param x Dimension de la matriz.
     * @param y Dimension de la matriz.
     * @return Retorna un ArrayList con el vector resultante.
     */
    public static Complejo[] rendijaCompleja(int n, Complejo[][] matriz, Complejo[] vector, int x, int y) {
        for (int i = 0; i < n; i++) {
            vector = accionMatrizVector(matriz, vector, x, y);
        }
        return vector;
    }

    /**
     * Este metodo se encarga de la multiplicacion de una matriz por un vector.
     *
     * @param mat Matriz inicial.
     * @param vec Vector inicial.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     * @return Retorna el vector resultante de la multiplicacion.
     */
    public static Complejo[] accionMatrizVector(Complejo[][] mat, Complejo[] vec, int x, int y) {
        Complejo[] res = new Complejo[x];
        Complejo suma;
        for (int i = 0; i < x; i++) {
            suma = new Complejo(0, 0);
            for (int j = 0; j < y; j++) {
                suma = sumarComplejos(suma, multiplicacionComplejos(mat[i][j], vec[j]));
            }
            res[i] = suma;

        }
        return res;

    }

    /**
     * Este metodo se encarga de la multiplicacion de dos complejos.
     *
     * @param c1 Primer complejo.
     * @param c2 Segundo complejo.
     * @return Retorna la multiplicacion de los complejos.
     */
    public static Complejo multiplicacionComplejos(Complejo c1, Complejo c2) {
        double x = c1.real * c2.real - c1.imag * c2.imag;
        double y = c1.real * c2.imag + c1.imag * c2.real;
        return (new Complejo(x, y));

    }

    /**
     * Este metodo se encarga de la suma de complejos.
     *
     * @param c1 Primer complejo.
     * @param c2 Segundo comolejo.
     * @return Retorna la suma de los complejos.
     */
    public static Complejo sumarComplejos(Complejo c1, Complejo c2) {
        return new Complejo(c1.getReal() + c2.getReal(), c1.getImag() + c2.getImag());
    }

    /**
     * Este metodo permite calcular la probabilidad de encontrarlo en una
     * posicion en especifico.
     *
     * @param ket Es el ket inicial.
     * @param pos La posicion.
     * @return La probabilidad.
     */
    public static Double prob(Complejo[] ket, int pos) {
        double c = (ket[pos].getReal() * ket[pos].getReal()) + (ket[pos].getImag() * ket[pos].getImag());
        double norma = 0;
        for (int i = 0; i < ket.length; i++) {
            norma += (ket[i].getReal() * ket[i].getReal()) + (ket[i].getImag() * ket[i].getImag());
        }
        return ((c / norma) * 100);
    }

    /**
     * El sistema si se le da otro vector Ket debe buscar la probabilidad de
     * transitar del primer vector al segundo.
     *
     * @param si Vector complejo inicial.
     * @param fi Vector complejo inicial.
     * @return
     */
    public static Complejo amplitud(Complejo[] si, Complejo[] fi) {
        Complejo[] bra = new Complejo[si.length];
        Complejo res = new Complejo(0, 0);
        for (int x = 0; x < fi.length; x++) {
            bra[x] = new Complejo(fi[x].getReal(), fi[x].getImag() * -1);
        }
        for (int a = 0; a < si.length; a++) {
            Complejo p = multiplicacionComplejos(bra[a], si[a]);
            res = sumarComplejos(res, p);
        }

        return res;

    }

    /**
     * Este metodo se encarga de hallar la probabilidad de que una particula
     * este en una posicion especifica.
     *
     * @param posPosibles Son las posiciones a ocupar.
     * @param ket Es el ket inicial.
     * @param x
     * @return Retorna la probabilidad.
     */
    public double probabilidadPosPart(int posPosibles, Complejo[] ket, int x) {
        double normaKet = normaKet(ket);
        double probabilidad = Math.pow(modulo(ket[x]), 2) / Math.pow(normaKet, 2);
        return probabilidad;
    }

    /**
     * Este metodo se encarga de hallar la norma de un ket
     *
     * @param k Ket inicial.
     * @return Retorna la norma del ket.
     */
    public static double normaKet(Complejo[] k) {
        double norma = 0;
        for (int i = 0; i < k.length; i++) {
            norma += Math.pow(modulo(k[i]), 2);
        }
        norma = Math.sqrt(norma);
        return norma;
    }

    /**
     * Este metodo se encarga de hallar el modulo de un numero complejo.
     *
     * @param c Numero complejo.
     * @return Retorna el modulo del numero complejo.
     */
    public static double modulo(Complejo c) {
        return Math.sqrt(Math.pow(c.getReal(), 2) + Math.pow(c.getImag(), 2));
    }

    /**
     * Este metodo se encarga de calcular la amplitud transicional.
     *
     * @param ket1 Ket 1 inicial.
     * @param ket2 Ket 2 inicial.
     * @return
     */
    public Complejo calcularAmplitudTransicional(Complejo[] ket1, Complejo[] ket2) {
        return productoInterno(ket2, ket1);
    }

    /**
     * Este metodo se encarga de calcular el producto interno entre dos
     * vectores.
     *
     * @param v1 Vector 1 inicial.
     * @param v2 Vector 2 inicial.
     * @return Retorna el producto interno.
     */
    public Complejo productoInterno(Complejo[] v1, Complejo[] v2) {
        Complejo ans = new Complejo(0, 0);
        Complejo[] V = conjugado(v1);
        for (int i = 0; i < v1.length; i++) {
            ans = sumarComplejos(ans, multiplicacionComplejos(V[i], v2[i]));
        }
        return ans;
    }

    /**
     * Este metodo se encarga de hallar el conjugado de un vector.
     *
     * @param v1 Vector inicial.
     * @return Retorna el conjugado del vector.
     */
    public Complejo[] conjugado(Complejo[] v1) {
        Complejo[] ansV = new Complejo[v1.length];
        for (int i = 0; i < v1.length; i++) {
            ansV[i] = conjugadoV(v1[i]);
        }
        return ansV;
    }

    /**
     * Este metodo retorna el conjugado de un numero complejo.
     *
     * @param c Numero complejo inicial.
     * @return retorna el conjugado del numero.
     */
    public Complejo conjugadoV(Complejo c) {
        return new Complejo(c.getReal(), -c.getImag());
    }

    /**
     * Este metodo se encarga de hallar la media de un observable.
     *
     * @param m Matriz inicial.
     * @param ket Vector inicial.
     * @return Retorna la media del observable.
     */
    public Complejo mediaDeUnObservableVector(Complejo[][] m, Complejo[] ket) {
        Complejo[] accion = accion(m, ket);
        Complejo media = productoInterno(accion, ket);
        return media;
    }

    /**
     * Este metodo se encarga de la multiplicacion de una matriz por un vector.
     *
     * @param m1 Matriz inicial.
     * @param v1 Vector inicial.
     * @return Retorna el vector resultante de la multiplicacion.
     */
    public Complejo[] accion(Complejo[][] m1, Complejo[] v1) {
        Complejo[] ansV = new Complejo[v1.length];
        for (int i = 0; i < m1.length; i++) {
            ansV[i] = new Complejo(0, 0);
            for (int j = 0; j < m1.length; j++) {
                ansV[i] = sumarComplejos(ansV[i], multiplicacionComplejos(m1[i][j], v1[j]));
            }
        }
        return ansV;
    }

    public Complejo varianzaDeUnOperador(Complejo[][] m, Complejo[] ket) {

        Complejo[][] mediaObservable = MultiplicacionEscalar(mediaDeUnObservableVector(m, ket), identidadMat(m.length));
        Complejo[][] Observable = sumaMatrices(m, inversaMatriz(mediaObservable));
        Complejo[][] Observable2 = multM(Observable, Observable);
        Complejo varianza = productoInterno(accion(Observable2, ket), ket);
        return varianza;
    }

    /**
     * Este metodo se encarga de realizar la multiplicacion de dos matrices.
     *
     * @param m1 Matriz 1 inicial.
     * @param m2 Matriz 2 inicial.
     * @return Retorna la multiplicacion de las dos matrices.
     */
    public Complejo[][] multM(Complejo[][] m1, Complejo[][] m2) {
        Complejo[][] ansM = new Complejo[m1.length][m2[0].length];
        Complejo r;
        int i, j;
        for (int a = 0; a < m1.length; a++) {
            for (int b = 0; b < m2[0].length; b++) {
                r = new Complejo(0, 0);
                i = 0;
                j = 0;
                while (j < m1[0].length && i < m2.length) {
                    r = sumarComplejos(r, multiplicacionComplejos(m1[a][j], m2[i][b]));
                    j++;
                    i++;
                }
                ansM[a][b] = r;
            }
        }
        return ansM;
    }

    /**
     * Este metodo se encarga de hallar la inversa de una matriz.
     *
     * @param m1 Matriz inicial.
     * @return Retorna la inversa de la matriz.
     */
    public Complejo[][] inversaMatriz(Complejo[][] m1) {
        Complejo[][] ansM = m1;
        for (int i = 0; i < m1.length; i++) {
            ansM[i] = inversaVector(m1[i]);
        }
        return ansM;
    }

    /**
     * Este metodo se encarga de hallar la inversa de un vector.
     * @param v1 Vector inicial.
     * @return Retorna la inversa del vector.
     */
    public Complejo[] inversaVector(Complejo[] v1) {
        Complejo[] ansV = new Complejo[v1.length];
        for (int i = 0; i < v1.length; i++) {
            ansV[i] = inverse(v1[i]);
        }
        return ansV;
    }

    /**
     * Este metodo se encarga de hallar el inverso de un numero complejo.
     * @param c Numero complejo inicial.
     * @return Retorna el inverso del numero complejo.
     */
    public Complejo inverse(Complejo c) {
        return new Complejo(-c.getReal(), -c.getImag());
    }

    /**
     * Este metodo se encarga de multiplicar una matriz por un complejo.
     * @param x Numero complejo inicial.
     * @param M Matriz inicial.
     * @return 
     */
    public Complejo[][] MultiplicacionEscalar(Complejo x, Complejo[][] M) {
        Complejo[][] ansM = M;
        for (int i = 0; i < ansM.length; i++) {
            for (int j = 0; j < ansM[0].length; j++) {
                ansM[i][j] = this.multiplicacionComplejos(x, M[i][j]);
            }
        }
        return ansM;
    }

    /**
     * Este metodo se encarga de hallar una matriz identidad partiendo de una dimension.
     * @param length Dimension.
     * @return Retorna la matriz identidad.
     */
    public Complejo[][] identidadMat(int length) {
        Complejo[][] ansM = new Complejo[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                ansM[i][j] = i == j ? new Complejo(1, 0) : new Complejo(0, 0);
            }
        }
        return ansM;
    }

    /**
     * Este metodo se encarga de realizar la suma de dos matrices.
     *
     * @param m1 Matriz 1 inicial.
     * @param m2 Matriz 2 inicial.
     * @return Retorna la suma de las dos matrices.
     */
    public Complejo[][] sumaMatrices(Complejo[][] m1, Complejo[][] m2) {
        Complejo[][] ansM = new Complejo[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            ansM[i] = sumaVectores(m1[i], m2[i]);
        }
        return ansM;
    }

    /**
     * Este metodo se encarga de realizar la suma de dos vectores.
     *
     * @param v1 Vector 1 inicial.
     * @param v2 Vector 2 inicial.
     * @return Retorna la suma de las dos vectores.
     */
    public Complejo[] sumaVectores(Complejo[] v1, Complejo[] v2) {
        Complejo[] ansV = new Complejo[v1.length];
        for (int i = 0; i < v1.length; i++) {
            ansV[i] = sumarComplejos(v1[i], v2[i]);
        }
        return ansV;

    }
        

}
