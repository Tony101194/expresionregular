/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExpresionRegular.construccion;


import ExpresionRegular.Graficos.Tabla;
import java.util.Vector;
/**
 *
 * @author lis
 */
public class CierreLambda {
  private String [][] estados ;
  private Vector <Estado> vectorestado;
  private Vector  simbolos;
  private Vector <Estado> cierre;
  static String incluidocierre="";
  boolean estadosaceptacion=false;
  static String aux = "";
  static int col=0,vez=0,sale=0;
  private final static String LETRAE = "e";

  public CierreLambda( Tabla latabla ){
    estados = latabla.getDatos();
  }


  public void cierreLambda(Vector<Estado> vectorestado, Vector simbolos){
      this.vectorestado= vectorestado;
      this.simbolos    = simbolos;
      int j = simbolos.size()+1;
      String e = "";
      col = simbolos.size()+1;
      cierre = new Vector <Estado>();




      for(int x=0;x<vectorestado.size();x++){
         incluidocierre = estados [x][0];
         aux         = estados [x][j];
         incluidocierre = iteracion(incluidocierre,aux);
         if(incluidocierre.lastIndexOf("e")!=0){
            Estado inicial=new Estado(incluidocierre, true, false);
            cierre.addElement(inicial);
         }
      }

      //muestra vector de estados simplificados
      for(int y=0;y<cierre.size();y++){
          e = cierre.get(y).getNombre();
          isaceptacion(cierre,simbolos,estadosaceptacion,e,sale);
          cierre.get(y).setAceptacion(estadosaceptacion);
          System.out.println(cierre.get(y).getNombre());
          System.out.println(cierre.get(y).esAceptacion());
      }
  }

  public String iteracion(String incluidocierre,String aux){
      int z=0;String aux2="";
      z = aux.indexOf(",", z);
      if ( z == -1 ){
          incluidocierre = incluidocierre+aux;
          for ( int i=0; i<vectorestado.size();i++){
              if(estados[i][0].equals(aux)){
                  aux = estados [i][col];
                  incluidocierre = iteracion(incluidocierre,aux);
              }
          }
      }else{
          int r = aux.lastIndexOf(",");
          z= aux.indexOf("e");
          while((z!= -1)&& (z<r)){
             aux2=aux.substring(z,aux.indexOf(","));
             z = aux.indexOf(",",z)+1;
             incluidocierre = incluidocierre+aux2;
             for ( int i=0; i<vectorestado.size();i++){
                  if(estados[i][0].equals(aux2)){
                      aux2 = estados [i][col];
                      incluidocierre = iteracion(incluidocierre,aux2);
                      break;
                  }
              }
          }
          aux2 = aux.substring(aux.lastIndexOf("e"),aux.length());
          incluidocierre = incluidocierre+aux2;
          for ( int i=0; i<vectorestado.size();i++){
               if(estados[i][0].equals(aux2)){
                   aux2 = estados [i][col];
                   incluidocierre = iteracion(incluidocierre,aux2);
                   break;
               }
          }
      }
      return incluidocierre;
  }

  public boolean isaceptacion(Vector<Estado> vectorestado, Vector simbolos, boolean s,String string,int exit){
      int cacepta = simbolos.size()+2;
      int pos=0;String subcadena="";
      if(exit == 0){
       while(string.indexOf(LETRAE, pos+1)!= -1){
         subcadena = string.substring(pos,string.indexOf(LETRAE, pos+1));
         pos = string.indexOf(LETRAE,pos+1);
         System.out.println(subcadena);
         s =isaceptacion(vectorestado,simbolos,s,subcadena,exit);
         vez=0;
       }

       if((string.indexOf(LETRAE, pos+1)== -1)&&(vez==0)){
          subcadena = string.substring(string.lastIndexOf(LETRAE),string.length());
          vez++;
          s = isaceptacion(vectorestado,simbolos,s,subcadena,exit);
       }

       for ( int b=0; b<this.vectorestado.size();b++){
         if((estados[b][0].equals(string)&& (!estados[b][cacepta].isEmpty()))){
                s = true ;
                exit = 1;
                s = isaceptacion(vectorestado,simbolos,s,subcadena,exit);
         }
       }
      }
      
      return s;
  }


}
