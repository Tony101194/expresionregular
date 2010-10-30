package ExpresionRegular.construccion;

import java.util.Vector;

/**
 * En esta clase se definen las contrucciones especificas para cada uno de
 * los operadores.
 *
 * @author Sebastian & Galvis
 */
public class Thompson {
    /**
     * Esta variable de clase se incrementa cada vez que se crea un estado
     * nuevo y lo que complementa el nombremiento de los estados.
     */
    private static int cont=0;
    /**
     * Esta constante representa el simbolo de las transiciones lambda.
     */
    public static final char NULO='\0';
    /**
     * Constructor por defecto de la clase.
     */
    public Thompson(){}

    /**
     * Esta funcion realiza la construcción cuando la cadena ingresada es vacia.
     * @return un <code>Vector<Estado></code> el cual no contiene trancisiones.
     */
    public Vector<Estado> vacia(){
        cont++;
        Vector<Estado> constru=new Vector<Estado>();
        Estado inicial=new Estado("e"+String.valueOf(cont), true, false);
        cont++;
        Estado eFinal=new Estado("e"+String.valueOf(cont), false, true);
        Transicion trans=new Transicion(eFinal, NULO);
        inicial.agregarTransicion(trans);
        return constru;
    }

    /**
     * Esta funcion rezaliza la construcción simple, es decir, cuando existe un
     * carácter o digito en la expresión.
     * @param simbolo Carácter con el cual se cambia de un estado a otro.
     * @return un <code>Vector<Estado></code> el cual contiene la construcción
     * del simbolo ingresado.
     */
    public Vector<Estado> constSimple(char simbolo){
        Vector<Estado> constru=new Vector<Estado>();
        cont++;
        Estado inicial=new Estado("e"+String.valueOf(cont), true, false);
        cont++;
        Estado eFinal=new Estado("e"+String.valueOf(cont), false, true);
        Transicion trans=new Transicion(eFinal, simbolo);
        inicial.agregarTransicion(trans);
        constru.addElement(inicial);
        constru.addElement(eFinal);
        return constru;
    }
/**
 * Esta función contruye la concatenacion entre dos construcciones anteriormente
 * dadas.
 * @param constru1 <code>Vector<Estado></code> que contiene la construcción que
 * segun las reglas del Automata para cocatenarlo la otra parte de la expresión.
 * @param constru2 <code>Vector<Estado></code> que contiene la construcción que
 * complementa la primera construcción.
 * @return Un <code>Vector<Estado></code> que contiene la concatenación entre
 * constru1 y contru2.
 */
    public Vector<Estado> constConcatena(Vector<Estado> constru1, Vector<Estado> constru2){
        Vector <Estado> constru;
        Estado finalConstru1= constru1.lastElement();
        Estado inicialConstru2=constru2.firstElement();
        Transicion tra= new Transicion();
        finalConstru1.agregarTransicion(inicialConstru2,NULO);
        finalConstru1.setAceptacion(false);
        inicialConstru2.setInicial(false);
        constru1.setElementAt(finalConstru1, constru1.indexOf(constru1.lastElement()));//al modifcarse el estado se vuelve a insertar en el vector
        constru2.setElementAt(inicialConstru2,constru2.indexOf(constru2.firstElement()));

        constru=constru1;
        for(int i=0;i<constru2.size();i++){
            constru.addElement(constru2.elementAt(i));
        }

        return constru;
    }
    /**
     * Esta función contruye la la alternación entre dos construcciones
     * predefinidas, esta operacion da a la ocurrencia de que se de este caso o
     * el otro.
     * @param constru1  <code>Vector<Estado></code> que contiene la construcción que
     * segun las reglas del Automata para cocatenarlo la otra parte de la expresión.
     * @param constru2  <code>Vector<Estado></code> que contiene la construcción que
     * complementa la primera construcción.
     * @return Un <code>Vector<Estado></code> que contiene la alternación entre
     * constru1 y contru2.
     */
    public Vector<Estado> constAlterna(Vector<Estado> constru1, Vector<Estado> constru2){
        Vector<Estado> constru=new Vector<Estado>();
        cont++;
        Estado nuevoInicio= new Estado("e"+String.valueOf(cont), true, false);
        cont++;
        Estado nuevoFinal= new Estado("e"+String.valueOf(cont), false, true);
        Estado inicialConstru1=constru1.firstElement();
        Estado finalConstru1=constru1.lastElement();
        inicialConstru1.setInicial(false);
        finalConstru1.setAceptacion(false);

        Estado inicialConstru2=constru2.firstElement();
        Estado finalConstru2=constru2.lastElement();
        inicialConstru1.setInicial(false);
        finalConstru2.setAceptacion(false);

        nuevoInicio.agregarTransicion(inicialConstru1, NULO);
        nuevoInicio.agregarTransicion(inicialConstru2, NULO);

        finalConstru1.agregarTransicion(nuevoFinal, NULO);
        finalConstru2.agregarTransicion(nuevoFinal, NULO);

        constru1.setElementAt(inicialConstru1, constru1.indexOf(constru1.firstElement()));
        constru1.setElementAt(finalConstru1, constru1.indexOf(constru1.lastElement()));

        constru2.setElementAt(inicialConstru2, constru2.indexOf(constru2.firstElement()));
        constru2.setElementAt(finalConstru2, constru2.indexOf(constru2.lastElement()));

        constru.addElement(nuevoInicio);

        for(int i = 0; i < constru1.size(); i++){
            constru.addElement(constru1.elementAt(i));
        }
        for(int i = 0; i < constru2.size(); i++){
            constru.addElement(constru2.elementAt(i));
        }

        constru.addElement(nuevoFinal);
        return constru;
    }

    /**
     * Esta función construye la clausura de una construcción predefinida.
     * @param constru <code>Vector<Estado></code> que contiene una construcción
     * predefinida.
     * @return Un <code>Vector<Estado></code> que contiene la clausura entre
     * constru
     */
    public Vector<Estado> constAsterisco(Vector<Estado> constru){
        cont++;
        Estado nuevoInicio=new Estado("e"+String.valueOf(cont), true, false);
        cont++;
        Estado nuevoFinal= new Estado("e"+String.valueOf(cont), false, true);
        Estado inicioActual=constru.firstElement();
        Estado finalActual=constru.lastElement();
        finalActual.agregarTransicion(inicioActual, NULO);
        inicioActual.setInicial(false);
        finalActual.setAceptacion(false);
        finalActual.agregarTransicion(nuevoFinal, NULO);
        nuevoInicio.agregarTransicion(inicioActual, NULO);
        nuevoInicio.agregarTransicion(nuevoFinal, NULO);

        constru.setElementAt(inicioActual, constru.indexOf(constru.firstElement()));
        constru.insertElementAt(nuevoInicio, 0);

        constru.setElementAt(finalActual, constru.indexOf(constru.lastElement()));
        constru.addElement(nuevoFinal);

        return constru;
    }
    /**
     * Esta función construye la opreacion mas (+) de una construcción predefinida.
     * Esta operación permite tener por lo menos una ocurrencia de una expresión.
     * @param constru <code>Vector<Estado></code> que contiene una construcción
     * predefinida.
     * @return Un <code>Vector<Estado></code> que contiene la operación (+) entre
     * constru
     */
    public Vector<Estado> constMas(Vector<Estado> constru){
        cont++;
        Estado nuevoInicio=new Estado("e"+String.valueOf(cont), true, false);
        cont++;
        Estado nuevoFinal= new Estado("e"+String.valueOf(cont), false, true);
        Estado inicioActual=constru.firstElement();
        Estado finalActual=constru.lastElement();
        finalActual.agregarTransicion(inicioActual, NULO);
        inicioActual.setInicial(false);
        finalActual.setAceptacion(false);
        finalActual.agregarTransicion(nuevoFinal, NULO);
        nuevoInicio.agregarTransicion(inicioActual, NULO);

        constru.setElementAt(inicioActual, constru.indexOf(constru.firstElement()));
        constru.insertElementAt(nuevoInicio, 0);

        constru.setElementAt(finalActual, constru.indexOf(constru.lastElement()));
        constru.addElement(nuevoFinal);

        return constru;
    }
    /**
     * Esta funcion reinicia el valor del contador con el cual se le
     * complementa el nombre del estado.
     */
    public static void resetCont(){
        cont=0;
    }
}
