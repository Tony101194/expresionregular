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
  static int col=0,vez=0;
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
          estadosaceptacion = isaceptacion(cierre,simbolos,estadosaceptacion,e);
          cierre.get(y).setAceptacion(estadosaceptacion);
          System.out.println(cierre.get(y).getNombre());
          System.out.println(cierre.get(y).esAceptacion());
      }
  }

  public String iteracion(String incluidocierre,String aux){
      int z=0;String aux2="";
      z = aux.indexOf(",", z);
      if ( z == -1 ){
         if (incluidocierre.indexOf(aux)== -1){
          incluidocierre = incluidocierre+aux;
              for ( int i=0; i<vectorestado.size();i++){
                  if(estados[i][0].equals(aux)){
                      aux = estados [i][col];
                      incluidocierre = iteracion(incluidocierre,aux);
                  }
              }
          }

      }else{
          int r = aux.lastIndexOf(",");
          z= aux.indexOf("e");
          while((z!= -1)&& (z<r)){
             aux2=aux.substring(z,aux.indexOf(","));
             z = aux.indexOf(",",z)+1;

             if (incluidocierre.indexOf(aux2)== -1){
              incluidocierre = incluidocierre+aux2;

                  for ( int i=0; i<vectorestado.size();i++){
                      if(estados[i][0].equals(aux2)){
                          aux2 = estados [i][col];
                          incluidocierre = iteracion(incluidocierre,aux2);
                          break;
                      }
                  }
              }
             else{System.out.println("else"+aux2);}
          }
          aux2 = aux.substring(aux.lastIndexOf("e"),aux.length());

          if (incluidocierre.indexOf(aux2)== -1){
          incluidocierre = incluidocierre+aux2;
              for ( int i=0; i<vectorestado.size();i++){
                   if(estados[i][0].equals(aux2)){
                       aux2 = estados [i][col];
                       incluidocierre = iteracion(incluidocierre,aux2);
                       break;
                   }
              }
          }
      }
      return incluidocierre;
  }

  public boolean isaceptacion(Vector<Estado> vectorestado, Vector simbolos, boolean s,String string){
      int cacepta = simbolos.size()+2;
      int pos=0;String subcadena="";

       while(string.indexOf(LETRAE, pos+1)!= -1){
         subcadena = string.substring(pos,string.indexOf(LETRAE, pos+1));
         pos = string.indexOf(LETRAE,pos+1);
         s = isaceptacion(vectorestado,simbolos,s,subcadena);
         vez=0;
       }

       if((string.indexOf(LETRAE, pos+1)== -1)&&(vez==0)){
          subcadena = string.substring(string.lastIndexOf(LETRAE),string.length());
          vez++;
          return isaceptacion(vectorestado,simbolos,s,subcadena);
       }

       for ( int b=0; b<this.vectorestado.size();b++){
         if((estados[b][0].equals(string)&& (!estados[b][cacepta].isEmpty()))){
                s = true ;
                isaceptacion(vectorestado,simbolos,s,subcadena);
         }
       }
      return s;
  }

  public String transiciones(Vector<Estado> vectorestado,Vector simbolos,String string){
      String subcadena="";int pos=0;

      while(string.indexOf(LETRAE, pos+1)!= -1){
         subcadena = string.substring(pos,string.indexOf(LETRAE, pos+1));
         pos = string.indexOf(LETRAE,pos+1);
         transiciones(vectorestado,simbolos,subcadena);
         vez=0;
       }

      if((string.indexOf(LETRAE, pos+1)== -1)&&(vez==0)){
          subcadena = string.substring(string.lastIndexOf(LETRAE),string.length());
          vez++;
          return transiciones(vectorestado,simbolos,subcadena);
       }

      for ( int u=0; u<simbolos.size();u++){
          for ( int v=0 ; v < this.vectorestado.size();v++){
            if((estados[u][0].equals(string)&& (!estados[u][v+1].isEmpty()))){
                
            }
         }
       }
      return string;
  }
}
