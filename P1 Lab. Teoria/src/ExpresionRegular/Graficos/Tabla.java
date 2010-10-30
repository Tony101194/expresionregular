/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExpresionRegular.Graficos;

import ExpresionRegular.construccion.Estado;
import ExpresionRegular.construccion.Thompson;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

/**
 * <p>Esta clase permite graficar una tabla de transición la cual contiene los
 * estados y los simbolos de entrada de una expresión regular que ingrese el
 * usuario.
 * </p>
 * @version 1.0
 * @author Sebastián Ramírez
 * @author Alexander Galvis
 */
public class Tabla extends JPanel{
    private JTable tablaAutomata;
    private JScrollPane scroll;
    private Vector<Estado> automata;
    private Vector simbolos;
    private String [][]data;
    private String [] simbol;
    /**
     *
     * @param automata automata generado por la expresión regular.
     * @param simbolos simbolos de entrada de la cadena.
     */
    public Tabla(Vector<Estado> automata, Vector<Character> simbolos){
        this.automata=automata;
        this.simbolos=simbolos;
        simbol=getSimbolos(this.simbolos);
        data=getDatos(this.automata);
        tablaAutomata=new JTable(data,simbol);
        tablaAutomata.setEnabled(false);
        tablaAutomata.setPreferredScrollableViewportSize(new Dimension(500, 70));
        scroll=new JScrollPane(tablaAutomata);
        scroll.setBounds(0, 0, 500, 300);
        this.add(scroll);
    }
/**
 *
 * @param simbolos simboloes de entrada de una cadena
 * @return retorna la coleccion de simbolos y se adicionan otras tres columnas
 * que están dadas en la tabla de transición, las cuales son: el nombre de los
 * estados, el simbolo nulo, y si es un estado de aceptación.
 */
    private String [] getSimbolos(Vector<Character> simbolos){
        String [] simbols=new String[simbolos.size()+3];
        simbols[0]="Estados";
        for(int i=1;i<simbols.length-2;i++){
            simbols[i]=String.valueOf(simbolos.get(i-1));
        }
        simbols[simbols.length-2]="NULO";
        simbols[simbols.length-1]="Aceptación";
        return simbols;
    }
    /**
     *
     * @param automat
     * @return String datos
     * Este metodo escribe en la tabla los valores que obtiene del vector de estados
     * que se le mande, accesando a las transiciones estado inicial, estado siguiente
     * y verificando si es de aceptacion o rechazo
     */
    private String [][] getDatos(Vector<Estado> automat){
        String [][] datos=new String[automat.size()][simbolos.size()+3];
        for (int i = 0; i < automat.size(); i++) {
            for (int j = 0; j < simbolos.size()+3; j++) {
                datos[i][j]="";

            }

        }
        for (int i = 0; i < automat.size(); i++) {
            datos[i][0]=automat.get(i).getNombre();
            if(automat.get(i).esAceptacion()){
                datos[i][simbolos.size()+2]="1";
            }
            for (int j = 0; j < automat.get(i).getLengthTrancisiones(); j++) {
                for(int k=0;k<simbolos.size();k++)
                    if(automat.get(i).getTransicion(j).getSimbolo()==simbolos.get(k)){
                        if(datos[i][k+1]=="")
                            datos[i][k+1]=automat.get(i).getTransicion(j).getEstadoFinal().getNombre();
                        else if(!(datos[i][k+1].substring(datos[i][k+1].lastIndexOf("e"),datos[i][k+1].length()).trim().equals(automat.get(i).getTransicion(j).getEstadoFinal().getNombre().trim()))){
                             datos[i][k+1]=datos[i][k+1]+","+automat.get(i).getTransicion(j).getEstadoFinal().getNombre();
                            }
                    }else{
                    if(automat.get(i).getTransicion(j).getSimbolo()==Thompson.NULO){
                        if(datos[i][simbolos.size()+1]=="")
                         datos[i][simbolos.size()+1]=automat.get(i).getTransicion(j).getEstadoFinal().getNombre();
                        else if(!(datos[i][simbolos.size()+1].substring(datos[i][simbolos.size()+1].lastIndexOf("e"),datos[i][simbolos.size()+1].length()).trim().equals(automat.get(i).getTransicion(j).getEstadoFinal().getNombre().trim()))){
                             datos[i][simbolos.size()+1]=datos[i][simbolos.size()+1]+","+automat.get(i).getTransicion(j).getEstadoFinal().getNombre();
                        }
                    }
                    }
            }
        }
        return datos;
    }


    /* metodo usado para retornar la tabla creada en el metodo anterior , esta
     * sera usada en la clase CierreLambda
     */
    public String [][] getDatos(){
        return data;
    }
}
