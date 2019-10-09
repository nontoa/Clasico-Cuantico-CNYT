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
    public static ArrayList<Double> canicas (Double[][] matriz,ArrayList<Double> vector , int n , int m, int clicks){
        for(int x=0;x<clicks;x++){
            ArrayList<Double> respuesta = new ArrayList<Double>();
            for(int i=0;i<n;i++){
                double c =0;
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
    public static ArrayList<Double> rendija(int rendijas, int blancos, int clicks ,ArrayList<Double> vector){
        int n= vector.size();
        Double[][] matriz = new Double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) matriz[i][j]=0.0;        
        }
        int bT= blancos * rendijas - (rendijas - 1);
        int cont = n -1;

        for(int x=1;x<(rendijas+1);x++){
            matriz[x][0]= (double) 1/rendijas;        
        }
        for (int x=0;x<bT;x++){
            matriz[cont][cont]=1.0;
            cont -= 1;
        }
        int ini = rendijas+1;

        for(int x=1;x<(rendijas+1);x++){
            for(int y=0;y<blancos;y++){
                matriz[ini+y][x]=(double )1/blancos;
            }
            ini+=blancos-1;
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
    
    
    public static Double prob (Complejo[] ket, int pos){
        double c = (ket[pos].getReal()*ket[pos].getReal())+(ket[pos].getImag()*ket[pos].getImag());
        double norma = 0;
        for(int i=0;i<ket.length;i++){
            norma+=(ket[i].getReal()*ket[i].getReal())+(ket[i].getImag()*ket[i].getImag());
        }
        return ((c/norma)*100);
    }
    
    public static Complejo amplitud(Complejo[] si, Complejo[] fi){
        Complejo[] bra = new Complejo[si.length];
        Complejo res = new Complejo(0,0);
        for(int x=0;x<fi.length;x++){
            bra[x]=new Complejo(fi[x].getReal(),fi[x].getImag()*-1);
        }
        for(int a=0;a<si.length;a++){
            Complejo p = multiplicacionComplejos(bra[a], si[a]);
            res = sumarComplejos(res, p);
        }
        
        return res;
        
    }
    
    public static Complejo mediaVarianza (Complejo[][] matriz , Complejo[] k){
        int var=0;
        int medi=0;
        
        
        return null;
    }
    
}






