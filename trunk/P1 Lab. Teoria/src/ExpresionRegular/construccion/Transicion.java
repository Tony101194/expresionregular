package ExpresionRegular.construccion;

/**
 * Esta clase permite parametrizar las transiciones que llevan de un estado a
 * otro.
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
 * Define una trancisión con un estado final y un simbolo con el cual traciende.
 * @param estadoFinal <code>Estado</code> al cual se desea que trascienda.
 * @param simbolo Carácter o digito con el que se va a ese <code>Estado</code>.
 */
    public Transicion(Estado estadoFinal,char simbolo){
        setEstadoFinal(estadoFinal);
        setSimbolo(simbolo);
    }
    /**
     * Esta función asigna el estado final.
     * @param estadoFinal <code>Estado</code> al que se trasciende.
     */
    
    public void setEstadoFinal(Estado estadoFinal){
        this.estadoFinal=estadoFinal;
    }
/**
 * Esta función asigna el simbolo con el cual traciende a un <code>Estado</code>.
 * @param simbolo Carácter o digito el cual se define la transición a el
 * otro <code>Estado</code>.
 */
    public void setSimbolo(char simbolo){
        this.simbolo=simbolo;
    }
    /**
     *
     * @return el simbolo de la transición definida.
     */

    public char getSimbolo(){
        return simbolo;
    }
/**
 * 
 * @return El <code>Estado</code> al cual trasciende.
 */

    public Estado getEstadoFinal(){
        return estadoFinal;
    }
}
