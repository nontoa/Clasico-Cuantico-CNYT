package edu.escuelaing.arep.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */


public class AppTest 
    extends TestCase
{
    
    public static DecimalFormat round = new DecimalFormat("#.00");
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCanicas()
    {
        int n =8;
        int m =8;
        Double[][] matriz = new Double [n][m];
        ArrayList<Double> vector = new ArrayList<Double>();
        ArrayList<Double> res = new ArrayList<Double>();        
        int clicks =5;
        
        matriz[0][0]=0.0;
        matriz[0][1]=0.0;
        matriz[0][2]=0.0;
        matriz[0][3]=0.0;
        matriz[0][4]=0.0;
        matriz[0][5]=0.0;
        matriz[0][6]=0.0;
        matriz[0][7]=0.0;
        
        matriz[1][0]=0.5;
        matriz[1][1]=0.0;
        matriz[1][2]=0.0;
        matriz[1][3]=0.0;
        matriz[1][4]=0.0;
        matriz[1][5]=0.0;
        matriz[1][6]=0.0;
        matriz[1][7]=0.0;
        
        matriz[2][0]=0.5;
        matriz[2][1]=0.0;
        matriz[2][2]=0.0;
        matriz[2][3]=0.0;
        matriz[2][4]=0.0;
        matriz[2][5]=0.0;
        matriz[2][6]=0.0;
        matriz[2][7]=0.0;
        
        matriz[3][0]=0.0;
        matriz[3][1]=0.33;
        matriz[3][2]=0.0;
        matriz[3][3]=1.0;
        matriz[3][4]=0.0;
        matriz[3][5]=0.0;
        matriz[3][6]=0.0;
        matriz[3][7]=0.0;
        
        matriz[4][0]=0.0;
        matriz[4][1]=0.33;
        matriz[4][2]=0.0;
        matriz[4][3]=0.0;
        matriz[4][4]=1.0;
        matriz[4][5]=0.0;
        matriz[4][6]=0.0;
        matriz[4][7]=0.0;
        
        matriz[5][0]=0.0;
        matriz[5][1]=0.33;
        matriz[5][2]=0.33;
        matriz[5][3]=0.0;
        matriz[5][4]=0.0;
        matriz[5][5]=1.0;
        matriz[5][6]=0.0;
        matriz[5][7]=0.0;
        
        matriz[6][0]=0.0;
        matriz[6][1]=0.0;
        matriz[6][2]=0.33;
        matriz[6][3]=0.0;
        matriz[6][4]=0.0;
        matriz[6][5]=0.0;
        matriz[6][6]=1.0;
        matriz[6][7]=0.0;
       
        matriz[7][0]=0.0;
        matriz[7][1]=0.0;
        matriz[7][2]=0.33;
        matriz[7][3]=0.0;
        matriz[7][4]=0.0;
        matriz[7][5]=0.0;
        matriz[7][6]=0.0;
        matriz[7][7]=1.0;
       
        vector.add(1.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);               
        
        res =  App.canicas(matriz,vector,n,m,clicks);                
                
        
        assertEquals(round.format(res.get(0)),",00");
        assertEquals(round.format(res.get(1)),",00");
        assertEquals(round.format(res.get(2)),",00");
        assertEquals(round.format(res.get(3)),",17");
        assertEquals(round.format(res.get(4)),",17");
        assertEquals(round.format(res.get(5)),",33");
        assertEquals(round.format(res.get(6)),",17");
        assertEquals(round.format(res.get(7)),",17");
        
    }        
        
    
    public void testRendijas(){
        int rendijas=2;
        int blancos=3;
        int clicks =2;
        ArrayList<Double> vector = new ArrayList<Double>();
        ArrayList<Double> res = new ArrayList<Double>();        
        
        vector.add(1.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);
        vector.add(0.0);                
        
        res = App.rendija(rendijas, blancos, clicks, vector);
        
        assertEquals(round.format(res.get(0)),",00");
        assertEquals(round.format(res.get(1)),",00");
        assertEquals(round.format(res.get(2)),",00");
        assertEquals(round.format(res.get(3)),",17");
        assertEquals(round.format(res.get(4)),",17");
        assertEquals(round.format(res.get(5)),",33");
        assertEquals(round.format(res.get(6)),",17");
        assertEquals(round.format(res.get(7)),",17");
                
        
    }
    
    
    public void testRendijaCompleja(){
        int n = 2;
        int x = 8;
        int y = 8;
        Complejo[][] matriz = new Complejo [x][y];
        Complejo[] vector = new Complejo[x];
        
        matriz[0][0]=new Complejo(0,0);
        matriz[0][1]=new Complejo(0,0);
        matriz[0][2]=new Complejo(0,0);
        matriz[0][3]=new Complejo(0,0);
        matriz[0][4]=new Complejo(0,0);
        matriz[0][5]=new Complejo(0,0);
        matriz[0][6]=new Complejo(0,0);
        matriz[0][7]=new Complejo(0,0);
        
        matriz[1][0]=new Complejo(0.5,0);
        matriz[1][1]=new Complejo(0,0);
        matriz[1][2]=new Complejo(0,0);
        matriz[1][3]=new Complejo(0,0);
        matriz[1][4]=new Complejo(0,0);
        matriz[1][5]=new Complejo(0,0);
        matriz[1][6]=new Complejo(0,0);
        matriz[1][7]=new Complejo(0,0);
        
        matriz[2][0]=new Complejo(0.5,0);
        matriz[2][1]=new Complejo(0,0);
        matriz[2][2]=new Complejo(0,0);
        matriz[2][3]=new Complejo(0,0);
        matriz[2][4]=new Complejo(0,0);
        matriz[2][5]=new Complejo(0,0);
        matriz[2][6]=new Complejo(0,0);
        matriz[2][7]=new Complejo(0,0);
        
        matriz[3][0]=new Complejo(0,0);
        matriz[3][1]=new Complejo(0.33,0);
        matriz[3][2]=new Complejo(0,0);
        matriz[3][3]=new Complejo(1,0);
        matriz[3][4]=new Complejo(0,0);
        matriz[3][5]=new Complejo(0,0);
        matriz[3][6]=new Complejo(0,0);
        matriz[3][7]=new Complejo(0,0);
       
        matriz[4][0]=new Complejo(0,0);
        matriz[4][1]=new Complejo(0.33,0);
        matriz[4][2]=new Complejo(0,0);
        matriz[4][3]=new Complejo(0,0);
        matriz[4][4]=new Complejo(1,0);
        matriz[4][5]=new Complejo(0,0);
        matriz[4][6]=new Complejo(0,0);
        matriz[4][7]=new Complejo(0,0);
        
        matriz[5][0]=new Complejo(0,0);
        matriz[5][1]=new Complejo(0.33,0);
        matriz[5][2]=new Complejo(0.33,0);
        matriz[5][3]=new Complejo(0,0);
        matriz[5][4]=new Complejo(0,0);
        matriz[5][5]=new Complejo(1,0);
        matriz[5][6]=new Complejo(0,0);
        matriz[5][7]=new Complejo(0,0);
        
        matriz[6][0]=new Complejo(0,0);
        matriz[6][1]=new Complejo(0,0);
        matriz[6][2]=new Complejo(0.33,0);
        matriz[6][3]=new Complejo(0,0);
        matriz[6][4]=new Complejo(0,0);
        matriz[6][5]=new Complejo(0,0);
        matriz[6][6]=new Complejo(1,0);
        matriz[6][7]=new Complejo(0,0);
        
        matriz[7][0]=new Complejo(0,0);
        matriz[7][1]=new Complejo(0,0);
        matriz[7][2]=new Complejo(0.33,0);
        matriz[7][3]=new Complejo(0,0);
        matriz[7][4]=new Complejo(0,0);
        matriz[7][5]=new Complejo(0,0);
        matriz[7][6]=new Complejo(0,0);
        matriz[7][7]=new Complejo(1,0);
       
        
        vector[0]= new Complejo(1,0);
        vector[1]= new Complejo(0,0);
        vector[2]= new Complejo(0,0);
        vector[3]= new Complejo(0,0);
        vector[4]= new Complejo(0,0);
        vector[5]= new Complejo(0,0);
        vector[6]= new Complejo(0,0);
        vector[7]= new Complejo(0,0);
        
        Complejo[] respuesta = App.rendijaCompleja(n, matriz, vector, x, y);                
        
        assertEquals(",00",round.format(respuesta[0].getReal()));
        assertEquals(",00",round.format(respuesta[0].getImag()));
        assertEquals(",00",round.format(respuesta[1].getReal()));
        assertEquals(",00",round.format(respuesta[1].getImag()));
        assertEquals(",00",round.format(respuesta[2].getReal()));
        assertEquals(",00",round.format(respuesta[2].getImag()));
        assertEquals(",17",round.format(respuesta[3].getReal()));
        assertEquals(",00",round.format(respuesta[3].getImag()));
        assertEquals(",17",round.format(respuesta[4].getReal()));
        assertEquals(",00",round.format(respuesta[4].getImag()));
        assertEquals(",33",round.format(respuesta[5].getReal()));
        assertEquals(",00",round.format(respuesta[5].getImag()));
        assertEquals(",17",round.format(respuesta[6].getReal()));
        assertEquals(",00",round.format(respuesta[6].getImag()));
        assertEquals(",17",round.format(respuesta[7].getReal()));
        assertEquals(",00",round.format(respuesta[7].getImag()));
                        
    }
    
    public void testProb(){
        Complejo[] ket = new Complejo[10];
        int pos = 7;
        Double res=0.0; 
        
        ket[0]= new Complejo(2,1);
        ket[1]= new Complejo(-1,2);
        ket[2]= new Complejo(0,1);
        ket[3]= new Complejo(1,0);
        ket[4]= new Complejo(3,-1);
        ket[5]= new Complejo(2,0);
        ket[6]= new Complejo(0,-2);
        ket[7]= new Complejo(-2,1);
        ket[8]= new Complejo(1,-3);
        ket[9]= new Complejo(0,-1);
        
        res= App.prob(ket, pos);
        
        assertEquals(round.format(res),"10,87");
        
    }
    
    public void testAmplitud(){                
        
        Complejo[] si = new Complejo[10];
        Complejo[] fi = new Complejo[10];                
        
        si[0]= new Complejo(2,1);
        si[1]= new Complejo(-1,2);
        si[2]= new Complejo(0,1);
        si[3]= new Complejo(1,0);
        si[4]= new Complejo(3,-1);
        si[5]= new Complejo(2,0);
        si[6]= new Complejo(0,-2);
        si[7]= new Complejo(-2,1);
        si[8]= new Complejo(1,-3);
        si[9]= new Complejo(0,-1);
        
        fi[0]= new Complejo(-1,-4);
        fi[1]= new Complejo(2,-3);
        fi[2]= new Complejo(-7,6);
        fi[3]= new Complejo(-1,1);
        fi[4]= new Complejo(-5,-3);
        fi[5]= new Complejo(5,0);
        fi[6]= new Complejo(5,8);
        fi[7]= new Complejo(4,-4);
        fi[8]= new Complejo(8,-7);
        fi[9]= new Complejo(2,-7);
        
        Complejo res = App.amplitud(si, fi);
        
        assertEquals(round.format(res.getReal()),"-3,00");
        assertEquals(round.format(res.getImag()),"-19,00");
        
        
    }
        

}
