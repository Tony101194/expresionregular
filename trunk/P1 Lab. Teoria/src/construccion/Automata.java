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
    public Automata(){
        setER("");
        setSimbolos(null);
    }

    public Automata(String ER,Vector<Character> simbolos){
        setER(ER);
        setSimbolos(simbolos);
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

  public Vector<Estado> recorrerExpresion(Vector<Estado> operando, String ER, int indice){
        if(ER.isEmpty()){
            return construcciones.vacia();
        }
        Vector operando1 = operando;
        Vector operando2 = null;
        Vector resultado = operando;

        char simboloActual;
        char simboloSiguiente;
        //validar que el indice no supere el tamaño del string
        if((indice == 0) & ER.charAt(indice) != '('){
            return construcciones.constSimple(ER.charAt(indice));
        }
        else if((indice == 0) & ER.charAt(indice) == '('){
            //operando1 = construcciones.r(simboloActual);
            return simboloActualParentesis(operando1, ER, indice);
        }

        simboloActual = ER.charAt(indice - 1);
        simboloSiguiente = ER.charAt(indice);

        if(Character.isLetterOrDigit(simboloActual) || simboloActual == '*' ||
                simboloActual == '+' ||
                simboloActual == ')'){
            if(Character.isLetterOrDigit(simboloSiguiente)){
                if(ER.length() > (indice + 1)){
                    char simboloPosSig = ER.charAt(indice + 1);
                    if(simboloPosSig != '*'){
                        operando2 = construcciones.constSimple(simboloSiguiente);
                        if(simboloActual == '+')
                            resultado = construcciones.constAlterna(operando1, operando2);
                        else
                            resultado = construcciones.constConcatena(operando1, operando2);
                        return resultado;
                    }
                    else{
                        operando2 = construcciones.constSimple(simboloSiguiente);
                        operando2 = construcciones.constAsterisco(operando2);
                        if(simboloActual == '+')
                            resultado = construcciones.constAlterna(operando1, operando2);
                        else
                            resultado = construcciones.constConcatena(operando1, operando2);
                        return resultado;
                    }
                }
                else{
                    operando2 = construcciones.constSimple(simboloSiguiente);
                    if(simboloActual == '+')
                        resultado = construcciones.constConcatena(operando1, operando2);
                    else
                         resultado = construcciones.constAlterna(operando1, operando2);
                    return resultado;
                }
            }
            else if(simboloSiguiente == '('){
                resultado = simboloActualParentesis(operando1, ER, indice);
            }
        }
        return resultado;
    }

    private Vector simboloActualParentesis(Vector operando, String ER, int indice){
        Vector resultado;
        Vector operando1 = operando;
        Vector operando2 = null;

        char simboloActual;

        int controlParentesis = 1;
        String subER = null;
        int i;
        for(i = indice + 1; i < ER.length(); i++){
            if(ER.charAt(i) == '(')
                controlParentesis++;
            else if(ER.charAt(i) == ')'){
                controlParentesis--;
                if(controlParentesis == 0){
                    subER = ER.substring(indice+1, i-1);
                    break;
                }
            }
        }

        for(int j = 0; j < subER.length(); j++){
            System.out.println(j);
            operando2 = recorrerExpresion(operando2, subER, j);
        }

        if(indice > 0)
            simboloActual= ER.charAt(indice - 1);
        else{
            simboloActual = ' ';
            if(Character.isLetterOrDigit(ER.charAt(indice + 1))){
                char simboloPosSiguiente = ER.charAt(indice + 2);
                if(simboloPosSiguiente != '*'){
                    operando1 = construcciones.constSimple(ER.charAt(indice + 1));
                    indice = indice + 1;
                }
                else{
                    operando1= construcciones.constSimple(ER.charAt(indice + 1));
                    operando1 = construcciones.constAsterisco(operando1);
                    indice = indice + 2;
                }
            }
        }

        if((i + 1) < ER.length()){
            char simboloPosSig = ER.charAt(i + 1);
            if(simboloPosSig != '*'){
                if(simboloActual == '+')
                    resultado = construcciones.constMas(operando1);
                else
                    resultado = construcciones.constConcatena(operando1, operando2);
            }
            else{
                operando2 = construcciones.constAsterisco(operando2);
                if(simboloActual == '+')
                    resultado = construcciones.constMas(operando1);
                else
                    resultado = construcciones.constConcatena(operando1, operando2);
            }
        }
        else{
            if(simboloActual == '+')
                resultado = construcciones.constMas(operando1);
            else
                resultado = construcciones.constConcatena(operando1, operando2);
        }

        if(indice > 0)
            return resultado;
        else
            return operando2;
    }
//    public Vector<String> subCadena(String expre){
//        if(expre.isEmpty()){
//            return null;
//        }
//        Vector<String> subCadenas=new Vector<String>();
//        int cuentaParentesis=0;
//        int comienzo =0;
//        char simboloActual;
//        char simboloSiguiente;
//        for(int i=0; i<expre.length();i++){
//            simboloActual=expre.charAt(i);
//            if(i<expre.length()-1)
//                simboloSiguiente=expre.charAt(i+1);
//            else
//                simboloSiguiente=Thompson.NULO;
//
//            if(simboloActual=='('){
//                cuentaParentesis++;
//
//            }else if(simboloActual==')'){
//                cuentaParentesis--;
//            }
//            if(cuentaParentesis==0 && simboloSiguiente!='\0'){
//                if(simboloSiguiente=='+' || simboloSiguiente=='*'||simboloSiguiente=='|'
//                        ||Character.isLetterOrDigit(simboloSiguiente)){
//                    subCadenas.addElement(expre.substring(comienzo, i+1));
//                    comienzo=i+1;
//                  //  subCadenas.addElement(String.valueOf(simboloSiguiente));
//                }
//            }
//            if(cuentaParentesis==0 && simboloSiguiente=='\0'){
//                subCadenas.addElement(expre.substring(comienzo, i));
//                break;
//            }
//        }
//
//        return subCadenas;
//    }
//
//    public void extraer(String expre, int condicion,int indice){
//        switch(condicion){
//            case 0:
//            case 1:
//            case 3:
//            case 4:
//        }
//    }
//    public void toDeterministico(){
//
//    }
//
//    public String toString(Vector<String> expr){
//        StringBuffer str=new StringBuffer();
//        String str2;
//        for(int i=0; i<expr.size();i++){
//            str.insert(i, expr.get(i));
//        }
//        return str.toString();
//    }
//
//    public Vector<String> toVectorString(String str){
//        Vector<String> vecstr=new Vector<String>();
//        vecstr.addElement(str);
//        return vecstr;
//    }

}
