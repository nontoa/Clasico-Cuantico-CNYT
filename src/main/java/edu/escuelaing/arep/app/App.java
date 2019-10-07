package edu.escuelaing.arep.app;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    
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
}






