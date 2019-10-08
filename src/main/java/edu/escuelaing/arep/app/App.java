package edu.escuelaing.arep.app;

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
    
    public static ArrayList<Integer> rendija(int rendijas, int blancos, int clicks,ArrayList<Integer> vector){
        int n= vector.size();
        Integer[][] matriz = new Integer[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) matriz[i][j]=0;        
        }
        int bT= blancos * rendijas - (rendijas - 1);
        int cont = -1;

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
        return canicas(matriz,vector,n,n,clicks);
    }
    
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

    
    
}






