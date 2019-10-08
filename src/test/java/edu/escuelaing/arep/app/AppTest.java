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
        int n =4;
        int m =4;
        Integer[][] matriz = new Integer [n][m];
        ArrayList<Integer> vector = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        int clicks =5;
        matriz[0][0]=1;
        matriz[0][1]=0;
        matriz[0][2]=0;
        matriz[0][3]=1;
        matriz[1][0]=0;
        matriz[1][1]=1;
        matriz[1][2]=1;
        matriz[1][3]=0;
        matriz[2][0]=0;
        matriz[2][1]=0;
        matriz[2][2]=0;
        matriz[2][3]=1;
        matriz[3][0]=1;
        matriz[3][1]=0;
        matriz[3][2]=0;
        matriz[3][3]=0;
        vector.add(1);
        vector.add(0);
        vector.add(1);
        vector.add(1);
        res =  App.canicas(matriz,vector,n,m,clicks);
        assertEquals((Integer)13, res.get(0));
        assertEquals((Integer)8, res.get(1));
        assertEquals((Integer)5, res.get(2));
        assertEquals((Integer)8, res.get(3));
        
    }
    
    public void testCanicas2()
    {
        int n =4;
        int m =4;
        Integer[][] matriz = new Integer [n][m];
        ArrayList<Integer> vector = new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        int clicks =5;
        matriz[0][0]=0;
        matriz[0][1]=0;
        matriz[0][2]=0;
        matriz[0][3]=1;
        matriz[1][0]=0;
        matriz[1][1]=1;
        matriz[1][2]=0;
        matriz[1][3]=0;
        matriz[2][0]=1;
        matriz[2][1]=1;
        matriz[2][2]=1;
        matriz[2][3]=1;
        matriz[3][0]=0;
        matriz[3][1]=0;
        matriz[3][2]=0;
        matriz[3][3]=0;
        vector.add(0);
        vector.add(0);
        vector.add(1);
        vector.add(1);
        res =  App.canicas(matriz,vector,n,m,clicks);
        assertEquals((Integer)0, res.get(0));
        assertEquals((Integer)0, res.get(1));
        assertEquals((Integer)3, res.get(2));
        assertEquals((Integer)0, res.get(3));
        
    }
    
    /**
    public void testRendijas(){
        int rendijas=2;
        int blancos=4;
        int clicks =8;
        ArrayList<Integer> vector = new ArrayList<Integer>();
        ArrayList<Integer> respuesta = new ArrayList<Integer>();
        vector.add(1);
        vector.add(5);
        vector.add(7);
        vector.add(4);
        vector.add(9);
        vector.add(12);
        respuesta = App.rendija(rendijas, blancos, clicks, vector);
        
    }
    
    public void testRendijaCompleja(){
        int n = 5;
        int x = 4;
        int y = 4;
        Complejo[][] matriz = new Complejo [x][y];
        Complejo[] vector = new Complejo[x];
        matriz[0][0]=new Complejo(2,-2);
        matriz[0][1]=new Complejo(4,3);
        matriz[0][2]=new Complejo(6,-1);
        matriz[0][3]=new Complejo(0,4);
        matriz[1][0]=new Complejo(6,8);
        matriz[1][1]=new Complejo(1,24);
        matriz[1][2]=new Complejo(12,11);
        matriz[1][3]=new Complejo(-9,3);
        matriz[2][0]=new Complejo(-3,-4);
        matriz[2][1]=new Complejo(7,8);
        matriz[2][2]=new Complejo(-8,-2);
        matriz[2][3]=new Complejo(1,1);
        matriz[3][0]=new Complejo(4,-8);
        matriz[3][1]=new Complejo(14,3);
        matriz[3][2]=new Complejo(3,12);
        matriz[3][3]=new Complejo(20,-14);
        vector[0]= new Complejo(2,-15);
        vector[1]= new Complejo(4,8);
        vector[2]= new Complejo(3,7);
        vector[3]= new Complejo(5,-6);
        Complejo[] respuesta = App.rendijaCompleja(n, matriz, vector, x, y);
        assertEquals("36681070,00",round.format(respuesta[0].getReal()));
        assertEquals("6979769,00",round.format(respuesta[0].getImag()));
        assertEquals("80208330,00",round.format(respuesta[1].getReal()));
        assertEquals("173171343,00",round.format(respuesta[1].getImag()));
        assertEquals("35377079,00",round.format(respuesta[2].getReal()));
        assertEquals("52595275,00",round.format(respuesta[2].getImag()));
        assertEquals("100674468,00",round.format(respuesta[3].getReal()));
        assertEquals("52864453,00",round.format(respuesta[3].getImag()));
        
        
        
    }
    
    
    public void testExperimentoProbabilidadEnUnPuntoEnParticular() {
        App s = new App();
        System.out.println("Experimento 4.1.1: ");
        Complejo[][] k = new Complejo[4][1];
        k[0][0] = new Complejo (-3,-1);
        k[1][0] = new Complejo (0,-2);
        k[2][0] = new Complejo (0,1);
        k[3][0] = new Complejo (2,0);        

        Complejo[][] p = new Complejo[4][1];
        p[0][0] = new Complejo (0.5263157894736842,0);
        p[1][0] = new Complejo (0.21052631578947364,0);
        p[2][0] = new Complejo (0.05263157894736841,0);
        p[3][0] = new Complejo (0.21052631578947364,0);        

        System.out.println("Vector ket");
        System.out.println(k);
        int punto = 2;
        
        assertEquals(p[punto][0].getReal(), s.probabilidadPuntoX(punto, k));
    }
    **/

}
