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
  static String aux = "";
  static int col=0;

  public CierreLambda( Tabla latabla ){
    estados = latabla.getDatos();
  }


  public void cierreLambda(Vector<Estado> vectorestado, Vector simbolos){
      this.vectorestado= vectorestado;
      this.simbolos    = simbolos;
      int j = simbolos.size()+1;
      col = simbolos.size()+1;
      cierre = new Vector <Estado>();

      for(int x=0;x<vectorestado.size();x++){
         incluidocierre = estados [x][0];
         aux         = estados [x][j];
         incluidocierre = iteracion(incluidocierre,aux);
         System.out.println(incluidocierre);
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


}
