/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExpresionRegular.construccion;

import java.util.Vector;

/**
 *
 * @author Sebastian
 */
public class Thompson {
    private static int cont=0;
    public static final char NULO='\0';
    public Thompson(){}
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
    public static void resetCont(){
        cont=0;
    }
}
