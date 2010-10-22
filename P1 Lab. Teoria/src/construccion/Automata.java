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
        ultimoIndice=0;
    }

    public Automata(String ER,Vector<Character> simbolos){
        setER(ER);
        setSimbolos(simbolos);
        construcciones=new Thompson();
        miPila=new Stack();
        ultimoIndice=0;
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

    public Vector<Estado> construirAutomata(int indice){
        Vector<Estado> operando1;
        Vector<Estado> operando2;
        Vector<Estado> resultado;
/*        if(indice == 0 && ER.charAt(indice)=='('){
            miPila.push(ER.charAt(indice));
            indice++;
            construirAutomata(indice);
        }else{
            if(indice==0 && ER.charAt(indice)!='('){
                operando1=construcciones.constSimple(ER.charAt(indice));
                miPila.push(operando1);
                indice++;
                return operando1;
            }
        }*/
        if(indice<ER.length()){
        if(ER.charAt(indice)=='('){
            miPila.push(ER.charAt(indice));
            indice++;
            return construirAutomata(indice);
        }else
            if(Character.isLetterOrDigit(ER.charAt(indice))){
                operando1=construcciones.constSimple(ER.charAt(indice));
                miPila.push(operando1);
                indice++;
                ultimoIndice=indice;
                return operando1;
            }else
                if(ER.charAt(indice)==')'){
                    
                    if(miPila.peek() instanceof Vector){
                        operando1=(Vector<Estado>)miPila.pop();
                        if(miPila.peek()instanceof Vector){
                            operando2=(Vector<Estado>)miPila.pop();
                            resultado=construcciones.constConcatena(operando2,operando1);
                            miPila.push(resultado);
                            return resultado;
                        }else
                        if((Character)miPila.peek()=='|'){

                        }else{
                            indice++;
                            ultimoIndice=indice;
                            miPila.pop();
                            return construirAutomata(indice);
                        }
                    }else{
                        if((Character)miPila.peek()=='|'){
                            
                        }else{
                            indice++;
                            ultimoIndice=indice;
                            miPila.pop();
                            return construirAutomata(indice);
                        }
                    }

                }
                else
                if(ER.charAt(indice)=='*'){
                    operando1=(Vector<Estado>)miPila.pop();
                    resultado=construcciones.constAsterisco(operando1);
                    miPila.push(resultado);
                    indice++;
                    ultimoIndice=indice;
                    return resultado;
                }else
                    if(ER.charAt(indice)=='+'){
                        operando1=(Vector<Estado>)miPila.pop();
                        resultado=construcciones.constMas(operando1);
                        miPila.push(resultado);
                        indice++;
                        ultimoIndice=indice;
                        return resultado;
                    }else
                        if(ER.charAt(indice)=='|'){
                            miPila.push(ER.charAt(indice));
                            indice++;
                            ultimoIndice=indice;
                            return construirAutomata(indice);
                        }
        }

        return operando1;
    }


    public void construirAutomata(String ER, int ultimoIndice){

    }

    public int getUltimoIndice(){
        return this.ultimoIndice;
    }
}
