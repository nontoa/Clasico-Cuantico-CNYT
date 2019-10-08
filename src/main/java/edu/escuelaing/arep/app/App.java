package edu.escuelaing.arep.app;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    **/
    public static DecimalFormat round = new DecimalFormat("#.00");
    
    
    /**
     * Este metodo permite simular los experimentos de la canicas con coeficiente booleanos.
     * @param matriz Matriz inicial.
     * @param vector Vector inicial.
     * @param n Dimension de la matriz.
     * @param m Dimension de la matriz.
     * @param clicks Numero de clicks.
     * @return  Retorna un ArrayList con el vector resultante.
     */
    public static ArrayList<Integer> canicas (Integer[][] matriz,ArrayList<Integer> vector , int n , int m, int clicks){
        for(int x=0;x<clicks;x++){
            ArrayList<Integer> respuesta = new ArrayList<Integer>();
            for(int i=0;i<n;i++){
                int c =0;
                for(int j=0;j<m;j++){
                    c+= matriz[i][j]*vector.get(j);
                }
                respuesta.add(c);
            }
            vector=respuesta;
        }
        
        return vector;
    }
    
    
    /**
     * Este metodo permite simular experimentos de las múltiples rendijas clásico probabilístico, con más de dos rendijas.
     * @param rendijas Numero de rendijas.
     * @param blancos Numero de blancos.
     * @param clicks Numero de clicks.
     * @param vector Vector inicial.
     * @return  Retorna un ArrayList con el vector resultante.
     */
    public static ArrayList<Integer> rendija(int rendijas, int blancos, int clicks ,ArrayList<Integer> vector){
        int n= vector.size();
        Integer[][] matriz = new Integer[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) matriz[i][j]=0;        
        }
        int bT= blancos * rendijas - (rendijas - 1);
        int cont = n -1;

        for(int x=1;x<(rendijas+1);x++){
            matriz[x][0]=1/rendijas;        
        }
        for (int x=0;x<bT;x++){
            matriz[cont][cont]=1;
            cont -= 1;
        }
        int ini = rendijas+1;

        for(int x=1;x<(rendijas+1);x++){
            for(int y=0;y<blancos;y++){
                matriz[ini+y][x]=1/blancos;
            }
            ini+=blancos-1;
        }
        ArrayList<Integer> res = canicas(matriz,vector,n,n,clicks);
        for(Integer i : res){
            System.out.println(i);
        }
        return canicas(matriz,vector,n,n,clicks);
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
    public static Complejo[] rendijaCompleja(int n, Complejo[][] matriz, Complejo[] vector,int x, int y){
        for(int i=0;i<n;i++){
            vector = accionMatrizVector(matriz,vector,x,y);
        }        
        return vector;
    }
    
    /**
     * Este metodo se encarga de la multiplicacion de una matriz por un vector.
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
     * Este metodo calcula la probabilidad de que una particula resulte en un punto x de un vector ket
     * @param punto
     * @param ket
     * @return la probabilidad de que una particula resulte en un punto x de un vector ket
     */
    public double probabilidadPuntoX (int punto, Complejo[][] ket){
        double probabilidad =  Math.pow(getModulo(ket[punto][0]),2)/Math.pow(normaMatriz(ket,ket[0].length,ket.length),2);
        return probabilidad;
    }
    
    public double getModulo(Complejo i){
        double modulo = Math.sqrt((Math.pow(i.getReal(),2) + (Math.pow(i.getImag(),2))));
        return modulo;
    }
    
    /**
     * Este metodo se encarga de calcular la norma de una matriz.
     * @param mat Matriz inicial.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     * @return Retorna la norma de la matriz.
     */
    public static double normaMatriz(Complejo[][] mat, int x, int y) {
        double respuesta = 0.0;
        Complejo[][] adj = new Complejo[x][y];
        Complejo[][] res= new Complejo[x][y];
        adj=matrizAdjunta(mat, x, y);
        res=multiplicacionEscalarMatrices(adj, mat, x);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(i == j) respuesta+=res[i][j].getReal();
            }
        }
        System.out.println("La norma de la matriz es: "+ Math.sqrt(respuesta));
        return Math.sqrt(respuesta);
    }
    
    /**
     * Este metodo se encarga de calcular la matriz adjunta.
     * @param mat Matriz inicial.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     * @return Retorna la matriz adjunta.
     */
    public static Complejo[][] matrizAdjunta(Complejo[][] mat, int x, int y) {
        Complejo[][] Trasp = matrizTraspuesta(mat, x, y);
        Complejo[][] res = matrizConjugada(Trasp, x, y);
        System.out.println("El resultado de la matriz adjunta es: ");
        String s;
        for (int f = 0; f < x; f++) {
            s = "";
            for (int t = 0; t < y; t++) {
                if (res[f][t].getImag() > 0) {
                    s += round.format(res[f][t].getReal()) + "+" + round.format(res[f][t].getImag()) + "i ";
                } else {
                    s += round.format(res[f][t].getReal()) + "" + round.format(res[f][t].getImag()) + "i ";
                }
            }
            System.out.println(s);
        }
        return res;
    }
    
    /**
     * Este metodo se encarga de calcular la conjugada de una matriz.
     *
     * @param mat Matriz inicial.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     */
    public static Complejo[][] matrizConjugada(Complejo[][] mat, int x, int y) {
        Complejo[][] res = new Complejo[x][y];
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                res[a][b] = new Complejo(mat[a][b].getReal(), mat[a][b].getImag() * -1);
            }
        }
       
        return res;

    }
    
    /**
     * Este metodo se encarga de calcular la traspuesta de una matriz.
     *
     * @param mat Matriz inicial.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     */
    public static Complejo[][] matrizTraspuesta(Complejo[][] mat, int x, int y) {
        Complejo[][] res = new Complejo[y][x];
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                res[b][a] = mat[a][b];
            }
        }
        System.err.println("El resultado de la matriz traspuesta es: ");
        for (int a = 0; a < y; a++) {
            String s = "";
            for (int b = 0; b < x; b++) {
                s += round.format(res[a][b].getReal());
                if (res[a][b].getImag() > 0) {
                    s += "+" + round.format(res[a][b].getImag());
                } else {
                    s += round.format(res[a][b].getImag());
                }
                s += "i ";
            }
            System.out.println(s);
        }
        return res;
    }
    
    public static Complejo[][] multiplicacionEscalarMatrices(Complejo[][] mat1, Complejo[][] mat2, int de) {
        Complejo[][] res = new Complejo[de][de];
        for (int x = 0; x < de; x++) {
            for (int y = 0; y < de; y++) {
                Complejo suma = new Complejo(0, 0);
                for (int z = 0; z < de; z++) {
                    suma = sumarComplejos(suma, multiplicacionComplejos(mat1[x][z], mat2[z][y]));
                }
                res[x][y] = suma;
            }
        }        
        return res;
    }
    
    /**
     * Este metodo normaliza un vector ket
     * @param ket
     * @return el vector ket normalizado
     */
    public Complejo[][] normalizar (Complejo[][] ket){
        Complejo[][] normalizada = multiplicacionEscalar(new Complejo ((double) 1/normaMatriz(ket,ket[0].length,ket.length),0),ket);
        return normalizada;
    }
    
    /**
     * Este metodo multiplica un escalar por los elementos de una matriz de numeros complejos
     *
     * @param c un Numero Complejo
     * @param a una Matriz de numeros complejos
     * @return una Matriz de numeros complejos resultante de la multiplicacion escalar
     */
    public Complejo[][] multiplicacionEscalar(Complejo c, Complejo[][] a) {
        Complejo[][] escalar = new Complejo[ a[0].length][ a.length];
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length; j++) {
                escalar[i][j] = multiplicacionComplejos(c, a[i][j]);
            }
        }
        return escalar;
    }
    
    /**
     * Este metodo Calcula el bra de un vector ket
     * @param ket
     * @return
     */
    public Complejo[][] calcularElBra(Complejo[][] ket){
        return matrizAdjunta(ket,ket[0].length,ket.length);
    }
    
    /**
     * Este metodo calcula la amplitud de Transicion entre dos vectores normalizados ket
     * @param ket1
     * @param ket2
     * @return
     * @throws ComplexException
     */
    public Complejo amplitudDeTransicion (Complejo[][] ket1, Complejo[][] ket2)  {
        return productoInterno(ket2,ket1);
    }
    
     /**
     * Calcula el producto interno entre dos Matrices de numeros complejos
     *
     * @param a Matriz de numeros complejos
     * @param b Matriz de numeros complejos
     * @return un Numero Complejo resultante
     * @throws ComplexException
     */
    public Complejo productoInterno(Complejo[][] a, Complejo[][] b) {
        if ((a[0].length != b[0].length)) {
            System.out.println("No se puede realizar el producto interno");
        }
        Complejo x = new Complejo(0, 0);
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length; j++) {
                x = sumarComplejos(x, multiplicacionComplejos(a[i][j], b[i][j]));
            }
        }
        return x;
    }
    
    
     /**
     * Este metodo calcula el valor medio
     * @param observable
     * @param ket
     * @return el valor medio
     * @throws ComplexException
     */
    public Complejo calcularElValorMedio( Complejo[][] observable, Complejo[][] ket) {        
        Complejo[][] phi = multiplicacionEscalarMatrices(observable,ket,observable.length);
        this.calcularElBra(phi);
        return productoInterno(phi, ket);

    }
    
    /**
     * Este metodo calcula la varianza
     * @param observable
     * @param ket
     * @return la varianza
     * @throws ComplexException
     */
    public Complejo calcularLaVarianza (Complejo[][] observable, Complejo[][] ket) {
        
        Complejo[][] unitaria = hacerLaUnitariaConUnValor(this.calcularElValorMedio(observable,ket),observable[0].length,observable.length);
        Complejo[][] resultante = restaMatrices(observable,unitaria,observable[0].length,observable.length);
        Complejo[][] resultante2 = multiplicacionEscalarMatrices(resultante,resultante,resultante.length);
        Complejo[][] r1 = multiplicacionEscalarMatrices(matrizAdjunta(ket,ket[0].length,ket.length),resultante2,resultante2.length);
        Complejo r2 = productoInterno(r1,matrizAdjunta(ket,ket[0].length,ket.length));
        return r2;

    }
    
     /**
     * Este metodo se encarga de la resta de dos matrices.
     *
     * @param matA Primera matriz.
     * @param matB Segunda matriz.
     * @param x Numero de filas.
     * @param y Numero de columnas.
     */
    public static Complejo[][] restaMatrices(Complejo[][] matA, Complejo[][] matB, int x, int y) {
        Complejo[][] respuesta = new Complejo[x][y];        
        for (int a = 0; a < x; a++) {            
            for (int b = 0; b < y; b++) {
                respuesta[a][b] = new Complejo(matA[a][b].getReal() - matB[a][b].getReal(), matA[a][b].getImag() - matB[a][b].getImag());                
            }            
        }
        return respuesta;

    }
    
    /**
     * Este metodo genera una matriz unitaria con un valor ingresado
     * @param valor
     * @param M
     * @param N
     * @return la matriz unitaria con un valor
     */
    public Complejo[][] hacerLaUnitariaConUnValor(Complejo valor, int M, int N){
        Complejo[][] unitaria = new Complejo[M][N];
        for (int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(i==j){
                    unitaria[i][j] = valor;
                }else{
                    unitaria[i][j] = new Complejo(0,0);
                }
            }
        }
        return unitaria;

    }
    
    
    /**
     * Este metodo calcula la dinamica del sistema dado unos tiempo, un estado inicial y una secuencia de matrices
     * @param tiempos
     * @param ket
     * @param secuencia
     * @return el estado final despues de t tiempos
     * @throws ComplexException
     */
    public Complejo[][] dinamicaDelSistema (int tiempos, Complejo[][] ket, ArrayList<Complejo[][]> secuencia) {
        Complejo[][] estadoFinal = ket;
        for(int i = 0; i< tiempos; i++){
            estadoFinal = multiplicacionEscalarMatrices(secuencia.get(i), estadoFinal, estadoFinal.length);
        }
        return estadoFinal;
    }

    
    
}






