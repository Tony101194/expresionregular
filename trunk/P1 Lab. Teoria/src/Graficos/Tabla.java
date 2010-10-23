/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Graficos;

import construccion.Estado;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author lis
 */
public class Tabla extends JPanel{
    private JTable tablaAutomata;
    private JScrollPane scroll;
    private Vector<Estado> automata;
    private Vector simbolos;
    public Tabla(Vector<Estado> automata, Vector<Character> simbolos){
        this.automata=automata;
        this.simbolos=simbolos;
    }

    private String [] getSimbolos(Vector<Character> simbolos){
        String [] simbols=new String[simbolos.size()+2];
        simbols[0]="Estados";
        for(int i=1;i<simbols.length-1;i++){
            simbols[i]=String.valueOf(simbolos.get(i));
        }
        simbols[simbols.length]="NULA";
        return simbols;
    }
    private String [][] getDatos(Vector<Estado> automat){
        String [][] datos=new String[automat.size()][simbolos.size()];
        for (int i = 0; i < datos.length; i++) {
            datos[i][0]=automat.get(i).getNombre();
            for (int j = 1; j < automat.size(); j++) {
                datos[i][j]=String.valueOf(automat.get(i).getTransicion(j).getSimbolo());
            }

        }
        return datos;
    }

}
