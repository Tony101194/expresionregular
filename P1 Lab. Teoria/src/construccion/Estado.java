package construccion;

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
    private String name;
    private boolean inicial;
    private boolean aceptacion;
    private Vector<Transicion> transiciones;
    public Estado(){
        setNombre("");
        setAceptacion(false);
        setInicial(false);
        this.transiciones = new Vector<Transicion>();
    }
    public  Estado(String nombre, boolean inicial,boolean aceptacion){
        setNombre(nombre);
        setInicial(inicial);
        setAceptacion(aceptacion);
        this.transiciones = new Vector<Transicion>();
    }

    public void setNombre(String nombre){
        name=nombre;
    }

    public void setAceptacion(boolean aceptacion){
        this.aceptacion=aceptacion;
    }

    public void setInicial(boolean inicial){
        this.inicial=inicial;
    }

    public String getNombre(){
        return name;
    }

    public boolean esAceptacion(){
        return aceptacion;
    }

    public boolean esInicial(){
        return inicial;
    }

    public void setTransicion(Vector<Transicion> trancisiones){
        this.transiciones=trancisiones;
    }

    public Transicion getTransicion(int i){
        return transiciones.get(i);
    }

     public Vector <Transicion> getTransicion(){
        return transiciones;
    }

    public void agregarTransicion(Transicion transicion){
        transiciones.addElement(transicion);
    }

    public void agregarTransicion(Estado estadoFinal, char simbolo){
        transiciones.addElement(new Transicion(estadoFinal, simbolo));
    }
    public int getLengthTrancisiones(){
        return transiciones.size();
    }
    public void removerTransiciones(){
        transiciones.removeAll(transiciones);
    }
}
