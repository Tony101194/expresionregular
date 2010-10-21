package construccion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
import javax.swing.*;
import java.util.*;
public class Automata{
    private String ER;
    private Vector<Character> simbolos;
    private Thompson construcciones;
    private Stack miPila;
    private int ultimoIndice;
    public Automata(){
        setER("");
        setSimbolos(null);
        miPila= new Stack();
    }

    public Automata(String ER,Vector<Character> simbolos){
        setER(ER);
        setSimbolos(simbolos);
        construcciones=new Thompson();
        miPila=new Stack();
    }

    /*private void cadenaPartida(String cadena){
        for(int i=0; i<cadena.length();i++){
            if(cadena.charAt(i)=='('){

            }
        }
    }*/

    public void setER(String ER){
        this.ER=ER;
    }

    public void setSimbolos(Vector<Character> simbolos){
        this.simbolos=simbolos;
    }

    public String getER(){
        return ER;
    }

    public Vector<Character> getSimbolos(){
        return simbolos;
    }

    public Vector<Estado> construir(){
        if(ER.isEmpty()){
            return construcciones.vacia();
        }
        
        return null;
    }

    public void construirAutomata(int indice){
        Vector<Estado> operando1;
        Vector<Estado> operando2;
        Vector<Estado> resultado;
        if(indice == 0 && ER.charAt(indice)=='('){
            miPila.push(ER.charAt(indice));
            indice++;
            construirAutomata(indice);
        }else{
            if(indice==0 && ER.charAt(indice)!='('){
                
            }
        }
    }


    public void construirAutomata(String ER, int ultimoIndice){

    }

    public int getUltimoIndice(){
        return this.ultimoIndice;
    }
}
