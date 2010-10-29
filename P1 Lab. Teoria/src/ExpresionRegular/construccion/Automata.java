package ExpresionRegular.construccion;

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
    public int alterna=0;

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

    public Stack miPila (){
       return miPila;
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

    public int getUltimoIndice(){
        return this.ultimoIndice;
    }
}
