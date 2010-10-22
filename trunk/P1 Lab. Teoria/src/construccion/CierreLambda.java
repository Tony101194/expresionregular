/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package construccion;

import java.util.Vector;

/**
 *
 * @author lis
 */
public class CierreLambda {
  private Vector  <Vector <Estado>> simplificar;
  private Vector <Estado> estado;
  private Vector <String> aux;
  private Estado posicion;
  private Vector <Transicion> trans;
  private String cestado;
   
  CierreLambda( Vector <Estado> estado){
      simplificar = new Vector<Vector<Estado>>();
      posicion = estado.firstElement();
      trans = posicion.getTransicion();
      for(int z=0;z<trans.size();z++){
          buscandolambdas(z);
      }

  }

  public void buscandolambdas(int numestado){
      int conversor=0;
      aux.addElement(posicion.getNombre());
      for(int y=0;y<trans.size();y++){
        if(trans.get(y).getSimbolo()== Thompson.NULO){
            aux.addElement(estado.get(y).getNombre());
            cestado = estado.get(y).getNombre();
            cestado = cestado.substring(1, cestado.length());
            conversor = Integer.parseInt(cestado);
            buscandolambdas(conversor);
        }
      }
  }

}
