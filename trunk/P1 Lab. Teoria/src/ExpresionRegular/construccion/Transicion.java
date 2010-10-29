/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExpresionRegular.construccion;

/**
 *
 * @author Sebastian & Galvis
 */
public class Transicion {
    private Estado estadoFinal;
    private char simbolo;
     /**
     * Constructor por defecto, inicializa en <code>null</code> los atributos
      *
     * de la <code>Transicion</code>.
     */
    public Transicion(){
        setEstadoFinal(null);
        setSimbolo('\0');
    }
/**
 *
 * @param estadoFinal
 * @param simbolo
 */
    public Transicion(Estado estadoFinal,char simbolo){
        setEstadoFinal(estadoFinal);
        setSimbolo(simbolo);
    }
    /**
     *
     * @param estadoFinal
     */
    
    public void setEstadoFinal(Estado estadoFinal){
        this.estadoFinal=estadoFinal;
    }
/**
 *
 * @param simbolo
 */
    public void setSimbolo(char simbolo){
        this.simbolo=simbolo;
    }
    /**
     *
     * @return
     */

    public char getSimbolo(){
        return simbolo;
    }
/**
 *
 * @return
 */

    public Estado getEstadoFinal(){
        return estadoFinal;
    }
}
