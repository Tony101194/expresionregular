package ExpresionRegular.construccion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @version 1.0
 * @author Sebastián Ramírez
 * @author Alexander Galvis
 */

import javax.swing.*;
import java.util.*;
public class Automata{
    private String ER;
    private Vector<Character> simbolos;
    private Thompson construcciones;
    private Stack miPila;
    private int ultimoIndice;
    public int alterna=0;

    //metodo constructor del Automata
    public Automata(){
        setER("");
        setSimbolos(null);
        miPila= new Stack();
        ultimoIndice=0;
    }

    //metodo constructor con parametros
    public Automata(String ER,Vector<Character> simbolos){
        setER(ER);
        setSimbolos(simbolos);
        construcciones=new Thompson();
        miPila=new Stack();
        ultimoIndice=0;
    }

    //para retornar la pila que contiene las construcciones
    public Stack miPila (){
       return miPila;
    }


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


    /*metodo que construye el automata analizando caracter a caracter usando un
     * entero como puntero para saber en que parte de la cadena de texto esta
     * parado, se analizan los posibles simbolos para determinar la construccion
     * de thompson que se debe implementar para cada caso.
     */
    public Vector<Estado> construirAutomata(int indice){
        Vector<Estado> operando1;
        Vector<Estado> operando2;
        Vector<Estado> resultado;
        

       if(indice<ER.length()){
        if(ER.charAt(indice)=='('){
            miPila.push(ER.charAt(indice));
            indice++;
            return construirAutomata(indice);
        }else
            if(Character.isLetterOrDigit(ER.charAt(indice))){
                operando1=construcciones.constSimple(ER.charAt(indice));
                if(indice+1<ER.length()){
                  if(ER.charAt(indice+1)=='*'){
                   operando1 = construcciones.constAsterisco(operando1);
                   indice++;
                  }else
                  if(ER.charAt(indice+1)=='+'){
                   operando1 = construcciones.constMas(operando1);
                   indice++;
                  }    
                }

                if((!miPila.empty()) && miPila.peek() instanceof Vector){
                    operando2=(Vector<Estado>)miPila.pop();
                    resultado=construcciones.constConcatena(operando2,operando1);
                    miPila.push(resultado);
                    indice++;
                    ultimoIndice=indice;
                    return resultado;
                }else{
                      miPila.push(operando1);
                      indice++;
                      ultimoIndice=indice;
                      return operando1;
                 }
            }else
                if(ER.charAt(indice)==')'){              
                    if(miPila.peek() instanceof Vector){
                        operando1=(Vector<Estado>)miPila.pop();
                        if(miPila.peek() instanceof Vector){
                            operando2=(Vector<Estado>)miPila.pop();
                            resultado=construcciones.constConcatena(operando2,operando1);
                            miPila.push(resultado);
                            return resultado;
                        }else
                        if((Character)miPila.peek()=='|'){
                            miPila.pop();
                            if(!miPila.empty() && miPila.peek() instanceof Vector){
                              operando2 = (Vector<Estado>)miPila.pop();
                              resultado = construcciones.constAlterna(operando1, operando2);
                              miPila.push(resultado);
                              return resultado;
                            }
                        }else{                           
                            indice++;
                            ultimoIndice=indice;
                            miPila.pop();
                            miPila.push(operando1);
                            return (operando1);
                         }
                     }
                }else
                    if(ER.charAt(indice)=='|'){
                        miPila.push(ER.charAt(indice));
                        indice++;
                        ultimoIndice=indice;
                        resultado = construirAutomata(indice);
                        return resultado;
                    }else
                        if(ER.charAt(indice)=='*'){
                            operando1=(Vector <Estado>)miPila.pop();
                            resultado = construcciones.constAsterisco(operando1);
                            indice++;
                            ultimoIndice=indice;
                            miPila.push(resultado);
                            return resultado;
                        }else
                            if(ER.charAt(indice)=='+'){
                            operando1=(Vector <Estado>)miPila.pop();
                            resultado = construcciones.constMas(operando1);
                            indice++;
                            ultimoIndice=indice;
                            miPila.push(resultado);
                            return resultado;
                            }  
             }else
             /**
              * se analiza al final de la expresion si al pila que almacena las
              * cosntrucciones aun contiene elementos, de ser asi se tienen dos
              * casos que hayan quedado en pila y deban ser concatenados o que
              * hay uno o varios simbolos | , por lo q se debe hacer la
              * construcion alternacion.
              */
               //caso alternacion
            if(indice>=(ER.length()-1) && alterna==1){
                  while(!miPila.empty() && miPila.size()>=3){
                     if(miPila.peek() instanceof Vector){
                        operando1 = (Vector<Estado>)miPila.pop();
                        if((Character)miPila.peek()=='|'){
                            miPila.pop();
                           if(!miPila.empty()&& miPila.peek() instanceof Vector){
                               operando2 =(Vector<Estado>) miPila.pop();
                               resultado = construcciones.constAlterna(operando1, operando2);
                               miPila.push(resultado);
                               return resultado;
                           }
                        }
                     }
                  }
                  //caso concatenacion
                  while(!miPila.empty()){
                    if(miPila.peek() instanceof Vector){
                        operando1 = (Vector<Estado>)miPila.pop();
                        if(!miPila.empty()&& miPila.peek() instanceof Vector){
                           operando2 =(Vector<Estado>) miPila.pop();
                           resultado = construcciones.constConcatena(operando1, operando2);
                           miPila.push(resultado);
                           return resultado;
                        }
                     }
                  }
              }
        return null;
    }

    /**
     * sobrecarga del metodo anterior , en caso de ser necesaria por alguna
     * circunstancia.
     */
    public Vector<Estado> construirAutomata(String ER, int indice){
        Vector<Estado> operando1;
        Vector<Estado> operando2;
        Vector<Estado> resultado;


       if(indice<ER.length()){
        if(ER.charAt(indice)=='('){
            miPila.push(ER.charAt(indice));
            indice++;
            return construirAutomata(ER,indice);
        }else
            if(Character.isLetterOrDigit(ER.charAt(indice))){
                operando1=construcciones.constSimple(ER.charAt(indice));
                if(indice+1<ER.length()){
                  if(ER.charAt(indice+1)=='*'){
                   operando1 = construcciones.constAsterisco(operando1);
                   indice++;
                  }
                  if(ER.charAt(indice+1)=='+'){
                   operando1 = construcciones.constMas(operando1);
                   indice++;
                  }
                }

                if((!miPila.empty()) && miPila.peek() instanceof Vector){
                    operando2=(Vector<Estado>)miPila.pop();
                    resultado=construcciones.constConcatena(operando2,operando1);
                    miPila.push(resultado);
                    indice++;
                    ultimoIndice=indice;
                    return resultado;
                }else{
                      miPila.push(operando1);
                      indice++;
                      ultimoIndice=indice;
                      return operando1;
                 }
            }else
                if(ER.charAt(indice)==')'){
                    if(miPila.peek() instanceof Vector){
                        operando1=(Vector<Estado>)miPila.pop();
                        if(miPila.peek() instanceof Vector){
                            operando2=(Vector<Estado>)miPila.pop();
                            resultado=construcciones.constConcatena(operando2,operando1);
                            miPila.push(resultado);
                            return resultado;
                        }else
                        if((Character)miPila.peek()=='|'){
                            miPila.pop();
                            if(!miPila.empty() && miPila.peek() instanceof Vector){
                              operando2 = (Vector<Estado>)miPila.pop();
                              resultado = construcciones.constAlterna(operando1, operando2);
                              miPila.push(resultado);
                              return resultado;
                            }
                        }else{
                            indice++;
                            ultimoIndice=indice;
                            miPila.pop();
                            miPila.push(operando1);
                            return (operando1);
                         }
                     }
                }else
                    if(ER.charAt(indice)=='|'){
                        miPila.push(ER.charAt(indice));
                        indice++;
                        ultimoIndice=indice;
                        resultado = construirAutomata(indice);
                        return resultado;
                    }else
                        if(ER.charAt(indice)=='*'){
                            operando1=(Vector <Estado>)miPila.pop();
                            resultado = construcciones.constAsterisco(operando1);
                            indice++;
                            ultimoIndice=indice;
                            miPila.push(resultado);
                            return resultado;
                        }else
                            if(ER.charAt(indice)=='+'){
                            operando1=(Vector <Estado>)miPila.pop();
                            resultado = construcciones.constMas(operando1);
                            indice++;
                            ultimoIndice=indice;
                            miPila.push(resultado);
                            return resultado;
                            }
             }else
            if(indice>=(ER.length()-1) && alterna==1){
                  while(!miPila.empty() && miPila.size()>=3){
                     if(miPila.peek() instanceof Vector){
                        operando1 = (Vector<Estado>)miPila.pop();
                        if((Character)miPila.peek()=='|'){
                            miPila.pop();
                           if(!miPila.empty()&& miPila.peek() instanceof Vector){
                               operando2 =(Vector<Estado>) miPila.pop();
                               resultado = construcciones.constAlterna(operando1, operando2);
                               miPila.push(resultado);
                               return resultado;
                           }
                        }
                     }
                  }
            }

        return null;
    }

    //metodo que permite obtener el ultimo indice de la cadena.
    public int getUltimoIndice(){
        return this.ultimoIndice;
    }
}
