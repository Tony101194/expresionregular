/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package construccion;

/**
 *
 * @author Sebastian & Galvis
 */
public class Transicion {
    private Estado estadoFinal;
    private char simbolo;
     /**
     * Constructor por defecto, inicializa en <code>null</code> los atributos
     * de la <code>Transicion</code>.
     */
    public Transicion(){
        setEstadoFinal(null);
        setSimbolo('\0');
    }

    public Transicion(Estado estadoFinal,char simbolo){
        setEstadoFinal(estadoFinal);
        setSimbolo(simbolo);
    }
    
    public void setEstadoFinal(Estado estadoFinal){
        this.estadoFinal=estadoFinal;
    }

    public void setSimbolo(char simbolo){
        this.simbolo=simbolo;
    }

    public char getSimbolo(){
        return simbolo;
    }


    public Estado getEstadoFinal(){
        return estadoFinal;
    }
}
