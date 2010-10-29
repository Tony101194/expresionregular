package ExpresionRegular.construccion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
import java.util.*;
public final class Estado {
    /**
     * Es el nombre del <code>Estado</code> el cual comienza con la letra e y lo
     * sigue un número que se va incrementando a medida que se instancie la
     * clase.
     */
    private String name;
    /**
     * En este campo verificamos si el <code>Estado</code> es inicial.
     */
    private boolean inicial;
    /**
     * En este campo verificamos si el <code>Estado</code> es de aceptación.
     */
    private boolean aceptacion;
    /**
     * Esta colección de datos mantenemos las transiciones que tiene este
     * estado a otros.
     */
    private Vector<Transicion> transiciones;
    /**
     * Constructor 
     */
    public Estado(){
        setNombre("");
        setAceptacion(false);
        setInicial(false);
        this.transiciones = new Vector<Transicion>();
    }
    /**
     *
     * @param nombre
     * @param inicial
     * @param aceptacion
     */
    public  Estado(String nombre, boolean inicial,boolean aceptacion){
        setNombre(nombre);
        setInicial(inicial);
        setAceptacion(aceptacion);
        this.transiciones = new Vector<Transicion>();
    }
/**
 *
 * @param nombre
 */
    public void setNombre(String nombre){
        name=nombre;
    }
    /**
     *
     * @param aceptacion
     */

    public void setAceptacion(boolean aceptacion){
        this.aceptacion=aceptacion;
    }
/**
 *
 * @param inicial
 */
    public void setInicial(boolean inicial){
        this.inicial=inicial;
    }
    /**
     *
     * @return
     */

    public String getNombre(){
        return name;
    }
    /**
     *
     * @return
     */

    public boolean esAceptacion(){
        return aceptacion;
    }
    /**
     *
     * @return
     */

    public boolean esInicial(){
        return inicial;
    }
/**
 *
 * @param trancisiones
 */
    public void setTransicion(Vector<Transicion> trancisiones){
        this.transiciones=trancisiones;
    }
/**
 *
 * @param i
 * @return
 */
    public Transicion getTransicion(int i){
        return transiciones.get(i);
    }
    /**
     *
     * @return
     */

     public Vector <Transicion> getTransicion(){
        return transiciones;
    }
     /**
      *
      * @param transicion
      */

    public void agregarTransicion(Transicion transicion){
        transiciones.addElement(transicion);
    }
    /**
     *
     * @param estadoFinal
     * @param simbolo
     */

    public void agregarTransicion(Estado estadoFinal, char simbolo){
        transiciones.addElement(new Transicion(estadoFinal, simbolo));
    }
    /**
     *
     * @return
     */
    public int getLengthTrancisiones(){
        return transiciones.size();
    }
    /**
     *
     */
    public void removerTransiciones(){
        transiciones.removeAll(transiciones);
    }
}
