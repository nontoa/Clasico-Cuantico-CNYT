# De lo clásico a lo cuántico y Teoría Quántica


## Empezando

>Para clonar el archivo 

>git clone https://github.com/nontoa/Clasico-Cuantico-CNYT.git

### Prerrequisitos
* Maven
* Java
* Git


## Construido con

* [Maven](https://maven.apache.org/) - Gestión de dependencias


## Números complejos:

Un número complejo, es una entidad matemática que viene dada por un par de números reales, el primero x se denomina la parte real y al segundo y la parte imaginaria. Los números complejos se representa por un par de números entre paréntesis (x, y), como los puntos del plano, o bien, en la forma usual de x+yi, i se denomina la unidad imaginaria, la raíz cuadrada de menos uno.

## Programa simulación de lo clásico a lo cuántico:

* 1. Los experimentos de la canicas con coeficiente booleanos

```java
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
```

* 2. Experimentos de las múltiples rendijas clásico probabilístico, con más de dos rendijas.

```java
**
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
```

* 3. Experimento de las múltiples rendijas cuántico.

```java
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
```

## Sistema cuántico de partícula en una línea:

* 1. El sistema debe calcular la probabilidad de encontrarlo en una posición en particular.

```java
/**
     * Este metodo permite calcular la probabilidad de encontrarlo en una posicion en especifico.
     * @param ket Es el ket inicial.
     * @param pos La posicion.
     * @return La probabilidad.
     */
    public static Double prob (Complejo[] ket, int pos){
        double c = (ket[pos].getReal()*ket[pos].getReal())+(ket[pos].getImag()*ket[pos].getImag());
        double norma = 0;
        for(int i=0;i<ket.length;i++){
            norma+=(ket[i].getReal()*ket[i].getReal())+(ket[i].getImag()*ket[i].getImag());
        }
        return ((c/norma)*100);
    }
```

* 2. El sistema si se le da otro vector Ket debe buscar la probabilidad de transitar del primer vector al segundo.

```java
/**
     * El sistema si se le da otro vector Ket debe buscar la probabilidad de transitar del primer vector al segundo.
     * @param si Vector complejo inicial.
     * @param fi Vector complejo inicial.
     * @return 
     */
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
```

## Pruebas:

Para correr las pruebas despues de clonar el proyecto ejecutar el sigiente comando:

>mvn test


## Autor


* **Juan Nicolas Nontoa Caballero**  Clasico-Cuantico-CNYT - [nontoa] (https://github.com/nontoa)

## licencia

Este proyecto está licenciado bajo la Licencia GNU - vea el archivo [LICENSE](LICENSE) para más detalles.
