/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExpresionRegular.construccion;


import ExpresionRegular.Graficos.Tabla;
import java.util.Vector;
/**
 * @version 1.0
 * @author Sebastián Ramírez
 * @author Alexander Galvis
 */

public class CierreLambda {
  private String [][] estados ;
  private Vector <Estado> vectorestado;
  private Vector<Character>  simbolos;
  private Vector <Estado> cierre;
  static String incluidocierre="";
  boolean estadosaceptacion=false;
  static String aux = "";
  static int col=0,vez=0;
  private Vector posiciones;
  private final static String LETRAE = "e";

  /** metodo para copiar los elementos de la tabla, haciendolos accesibles para
   * utilizar la misma con proposito de realizar el cierre lambda.
   * @param latabla
   */
  public CierreLambda( Tabla latabla ){
    estados = latabla.getDatos();
  }

/**
 * Metodo principal encargado de determinar la creacion de los estados por
 * simplificacion, observar cuales quedan de aceptacion o rechazo y determinar
 * las respectivas transicions de los nuevos estados simplificados.
 * @param vectorestado
 * @param simbolos
 */
  public void cierreLambda(Vector<Estado> vectorestado, Vector<Character> simbolos){
      this.vectorestado= vectorestado;
      this.simbolos    = simbolos;
      int j = simbolos.size()+1;
      String e = "",f="";
      col = simbolos.size()+1;
      cierre = new Vector <Estado>();
      posiciones = new Vector();

      posiciones = new Vector();
      //determina y construye los nuevos estados
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
      //determina sobre los nuevos estados cuales son de aceptacion
      for(int y=0;y<cierre.size();y++){
          e = cierre.get(y).getNombre();
          estadosaceptacion = isaceptacion(cierre,simbolos,estadosaceptacion,e);
          cierre.get(y).setAceptacion(estadosaceptacion);
      }
      vez =0;
      for(int y=0;y<cierre.size();y++){
       f = cierre.get(y).getNombre();
       transiciones(vectorestado,simbolos,f,y);
       for(int g=0;g<cierre.get(y).getLengthTrancisiones();g++){
           System.out.println("Estado incial"+cierre.get(y).getNombre());
           System.out.println("Estado final"+cierre.get(y).getTransicion(g).getEstadoFinal().getNombre());
           System.out.println("Simbolo"+cierre.get(y).getTransicion(g).getSimbolo()+"\n");
       }
      }
      vez =0;
      //determina las transiciones de los estados simplificados
      for(int y=0;y<cierre.size();y++){
       f = cierre.get(y).getNombre();
       transiciones(vectorestado,simbolos,f,y);
      }
  }


  /**
   * Explora en la tabla la columna 0 que es la que contiene el nombre de los
   * estados y en su misma fila busca la penultima columna correspondiente a las
   * que poseen las transiciones nulas, este metodo se llama asi mismo hasta q
   * no encuentre mas estados nuevos y retorna un string con los estados que
   * conforman el cierre lambda del estado n.
   * @param incluidocierre
   * @param aux
   * @return
   */
  private String iteracion(String incluidocierre,String aux){
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
             else{}
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


  /**
   * Metodo que verifica para los estados nuevos si son de aceptacion o no
   * retorna un booleano que retorna true si almenos uno de los estados q conforma
   * el nuevo estado tiene alguna transicion de acpetacion , implementa un
   * metodo similar al metodo anterior, solo que este verifica la ultima columna
   * de la tabla.
   * @param vectorestado
   * @param simbolos
   * @param s
   * @param string
   * @return
   */
  private boolean isaceptacion(Vector<Estado> vectorestado, Vector simbolos, boolean s,String string){
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


  /**
   * Este metodo reliza algo similar a los anteriores, analiza todas las trancisiones
   * posibles para los nuevos estados creados, a diferencia de los anteriores
   * este explora desde la columna 1 , hasta la columna n-2 , que son las
   * correspondientes a los simbolos de entrada, al encontrar la transicion la
   * añade al vector de transiciones asociado a dicho estado.
   * @param vectorestado
   * @param simbolos
   * @param estring
   * @param posicion
   * @return
   */
  private String transiciones(Vector<Estado> vectorestado,Vector<Character> simbolos,String estring,int posicion){
      String subcadena="";int pos=0;String buscado="";

      while(estring.indexOf(LETRAE, pos+1)!= -1){
         subcadena = estring.substring(pos,estring.indexOf(LETRAE, pos+1));
         pos = estring.indexOf(LETRAE,pos+1);
         transiciones(vectorestado,simbolos,subcadena,posicion);
         vez=0;
       }

      if((estring.indexOf(LETRAE, pos+1)== -1)&&(vez==0)){
          subcadena = estring.substring(estring.lastIndexOf(LETRAE),estring.length());
          vez++;
          return transiciones(vectorestado,simbolos,subcadena,posicion);
       }

      for ( int u=0; u<this.vectorestado.size();u++){
          for ( int v=0 ; v < simbolos.size();v++){
            if((estados[u][0].equals(estring))&&(!estados[u][v+1].isEmpty())){
               buscado = estados[u][v+1];
               for (int g=0;g<cierre.size();g++){
                 if ( cierre.get(g).getNombre().indexOf(buscado)!= -1){
                     posiciones.addElement(g);
                     cierre.get(posicion).agregarTransicion(cierre.get(g), simbolos.get(v));
                 }
               }
            }
         }
       }
      return estring;
  }

  /**
   * Metodo que retorna el resultado de la clase , es decir el vector que
   * contiene el cierre lambda, sera utilizado en la clase principal por medio
   * de una tabla para su visualizaion.
   * @return
   */
  public Vector <Estado> getCierre(){
      return cierre;
  }
}
