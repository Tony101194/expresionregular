/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Graficos;

import construccion.Estado;
import construccion.Thompson;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

/**
 *
 * @author lis
 */
public class Tabla extends JPanel{
    private JTable tablaAutomata;
    private JScrollPane scroll;
    private Vector<Estado> automata;
    private Vector simbolos;
    private String [][]data;
    private String [] simbol;
    public Tabla(Vector<Estado> automata, Vector<Character> simbolos){
        this.automata=automata;
        this.simbolos=simbolos;
        simbol=getSimbolos(this.simbolos);
        data=getDatos(this.automata);
        tablaAutomata=new JTable(data,simbol);
        tablaAutomata.setPreferredScrollableViewportSize(new Dimension(500, 70));
        scroll=new JScrollPane(tablaAutomata);
        scroll.setBounds(0, 0, 400, 300);
        this.add(scroll);
    }

    private String [] getSimbolos(Vector<Character> simbolos){
        String [] simbols=new String[simbolos.size()+2];
        System.out.println(simbolos.size()+2);
        simbols[0]="Estados";
        for(int i=1;i<simbols.length-1;i++){
            simbols[i]=String.valueOf(simbolos.get(i-1));
        }
        simbols[simbols.length-1]="NULA";
        return simbols;
    }
    private String [][] getDatos(Vector<Estado> automat){
        String [][] datos=new String[automat.size()][simbolos.size()+2];
       // int k=0;
        for (int i = 0; i < automat.size(); i++) {
            datos[i][0]=automat.get(i).getNombre();
            for (int j = 0; j < automat.get(i).getLengthTrancisiones(); j++) {
                for(int k=1;k<simbolos.size();k++)
                    if(automat.get(i).getTransicion(j).getSimbolo()==simbolos.get(k)||automat.get(i).getTransicion(j).getSimbolo()==Thompson.NULO){
                        datos[i][k]=automat.get(i).getTransicion(j).getEstadoFinal().getNombre();
                    }else{
                        datos[i][k]="";
                    }
            }
        }
        return datos;
    }

}
