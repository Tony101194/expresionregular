package ExpresionRegular;
import ExpresionRegular.construccion.Estado;
import ExpresionRegular.construccion.Automata;
import ExpresionRegular.Graficos.Tabla;
import ExpresionRegular.construccion.CierreLambda;
import ExpresionRegular.construccion.Thompson;
import java.awt.*;
import javax.swing.*;
import  java.awt.event.*;
import java.util.Vector;
import ExpresionRegular.reconocimiento.Reconocedor;
/**
 *  En esta deplegamos los componenetes gráficos y provee la interfaz al usuario,
 * además de interactuar con las otras clases para formar el automata finito.
 *
 * @version 1.0
 * @author Sebastián Ramírez
 * @author Alexander Galvis
 */

public class Principal extends JFrame implements ActionListener{
    private JLabel labelExpresion,imagen,labelTitulo;
    private JTextField textoExpresion;
    private JButton comenzar;
    private JButton siguiente;
    private JButton cierrelambda;
    private JMenuBar mbarra;
    private JMenu mmenu;
    private Container contenedor;
    private String ER;
    private char [] caracteres; //Debemos analizar cada caracter
    private Vector <Character> simbolos; //En este vector almacenamos los simbolos de entrada del AF
    private Vector<Estado> estados;//objeto que contiene los estados del AF que se crean a partir de la ER
    private Reconocedor reconozca;
    private JTextArea areacred;//Donde se muestran los creditos
    private JFrame otra;
    private JMenuItem nuevo;
    private JMenuItem creditos;
    private JMenuItem msalir;
    private Automata automata;
    private Tabla tablaAutomata,tablaCierre;
    private CierreLambda clambda;
    private static ImageIcon fondo = new ImageIcon("fondoaf.JPG");
    private static ImageIcon out   = new ImageIcon("iconodesalir.PNG");
    private static ImageIcon cred  = new ImageIcon("iconcred.PNG");
    private static ImageIcon mas = new ImageIcon("iconofile.PNG");

    public Principal(String titulo){
        //definicion de objetos a utilizar en la interfaz
        super(titulo);
        setResizable(false);
    	contenedor = new Container();
    	contenedor.setLayout(null);
    	getContentPane().add(contenedor);
        imagen = new JLabel();
        labelTitulo=new JLabel();
    	mbarra = new JMenuBar();
    	mmenu = new JMenu("Opciones");
        nuevo=new JMenuItem("Nueva Máquina",mas);
        nuevo.setMnemonic('N');
        nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        creditos  = new JMenuItem("Creditos",cred);
        creditos.setMnemonic('C');
	creditos.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C, InputEvent.CTRL_MASK));
       	msalir = new JMenuItem("Salir",out);
    	msalir.setMnemonic('S');
	msalir.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, InputEvent.CTRL_MASK));
        imagen.setBounds(0,0,fondo.getIconWidth(),fondo.getIconHeight());
        imagen.setIcon(fondo);
        mmenu.add(nuevo);
        mmenu.addSeparator();
        mmenu.add(creditos);
        mmenu.addSeparator();
    	mmenu.add(msalir);
        mbarra.add(mmenu);
        labelExpresion=new JLabel("Ingresar Expresión:");
        textoExpresion=new JTextField();
        comenzar=new JButton("<html>comenzar</html>");
        siguiente=new JButton("<html>Siguiente</html>");
        cierrelambda=new JButton("<html>Cierre Lambda</html>");
        siguiente.addActionListener(this);
        cierrelambda.addActionListener(this);
        labelExpresion.setBounds(20, 50, 150, 30);
        textoExpresion.setBounds(170, 50, 150, 30);
        comenzar.setBounds(320, 50, 100, 30);
        labelTitulo.setBounds(20, 30, 400, 30);
        siguiente.setBounds(400,380,100,30);
        cierrelambda.setBounds(200,380,150,30);
        msalir.addActionListener(this);
        comenzar.addActionListener(this);
        creditos.addActionListener(this);
        nuevo.addActionListener(this);
        this.setJMenuBar(mbarra);
       /*validacion del campo de texto de forma que solo acepte los caracteres de una ER ,
        * no acepta el caracter r por que es una palabra reservada pero acepta R*/
        textoExpresion.addKeyListener(new KeyAdapter(){
         public void keyTyped(KeyEvent evento){
         char c = evento.getKeyChar();
         if ((!(c==KeyEvent.VK_BACK_SPACE))&&((c!='*')&&(c!='(')&&(c!=')')&&(c!='+')&&(c!='|')&&
             (!(Character.isDigit(c)))&&(!(Character.isLetter(c))))||
             (c == KeyEvent.VK_SPACE)/*||(c == KeyEvent.VK_PASTE)*/||(c=='r')){
         getToolkit().beep();
         evento.consume();
         }
         }
         });
         // se agregan los objetos al contenedor
        contenedor.add(labelExpresion);
        contenedor.add(textoExpresion);
        contenedor.add(comenzar);
        contenedor.add(imagen);
    }

    //para las acciones de los botones
    public void actionPerformed(ActionEvent e){
    Object src = e.getSource();
        if(src==creditos){
          areacred = new JTextArea();
          areacred.setText(" ER/AF - Lab.Teo.Lenguajes"+"\n"+
                             " (c)2010-20?? - Alexander Galvis Grisales" +"\n"+
                             "                      - Sebastian Ramirez Bedoya"+"\n"
                            );
           JOptionPane.showMessageDialog(null,areacred,"Practica ER to AF",
           JOptionPane.INFORMATION_MESSAGE);
        }
        if (src == msalir) {
            int z = JOptionPane.showConfirmDialog(null,"Desea salir del programa?",
                    "Desea Salir?",JOptionPane.YES_NO_OPTION);
                   if (z == JOptionPane.YES_OPTION){
                     System.exit(-1);
                   }else
                   if (z == JOptionPane.NO_OPTION){}
        }

    if(src==nuevo){
        inicio();
        //carga la pantalla inicial de la aplicacion
    }
    //accion del boton comenzar
    	if (src == comenzar) {
        //llama a la clase reconocedor para que analice la cadena ingresada
        reconozca=new Reconocedor();
        reconozca.reconocercadena(textoExpresion.getText());
          if(reconozca.continuar==false){
           JOptionPane.showMessageDialog(null,"¡Expresion Regular Incorrecta!,por favor revise");
          }else{
            //si la cadena es valida empieza con la construccion del AF
            contenedor.removeAll();
             expresionValida();
             mostrarPasos();
             contenedor.repaint();
            }
    	}

        if(src==siguiente) {
            //para una ejecucion paso a paso de la construccion
            contenedor.removeAll();
            mostrarPasos();
            contenedor.repaint();
        }

        if(src==cierrelambda) {
            /*este muestra en una nueva ventana el resultado de hacer el cierre lambda del AF
             * previamente creado toma como base la primer tabla construida
             * */
          clambda = new CierreLambda(tablaAutomata);
          clambda.cierreLambda(estados,simbolos);
          otra = new JFrame("Cierre Lambda");
          otra.setSize(520,300);
          otra.setVisible(true);
          tablaCierre=new Tabla(clambda.getCierre(), simbolos);
          tablaCierre.setBounds(80, 80, 500, 300);
          otra.getContentPane().add(tablaCierre);
        }
    }
/*En este metodo buscamos todos los componentes del AF, tales como simbolos de entrada,
 * asteriscos y otros componentes asociados para la construcción de el.
 */
    public void inicio(){
        contenedor.removeAll();
        textoExpresion.setText("");
        contenedor.add(labelExpresion);
        contenedor.add(textoExpresion);
        contenedor.add(comenzar);
        contenedor.add(imagen);
        Thompson.resetCont();
        siguiente.setEnabled(true);
        contenedor.repaint();
    }
    public void expresionValida(){
        ER= textoExpresion.getText();
        simbolos = new Vector();
        caracteres=ER.toCharArray();//Enviamos todos los caracteres al arreglo.
        contenedor.add(siguiente);
        simbolos();
        automata=new Automata(ER, simbolos);
        labelTitulo.setText("<html>Expresión Ingresada r: "+ER+"</html");
    }

    public void mostrarPasos(){
        contenedor.add(labelTitulo);
        //analiza la pila para construir el AF paso a paso
        if(automata.getUltimoIndice() < ER.length() || automata.miPila().size()>1){
                    estados = automata.construirAutomata(automata.getUltimoIndice());
                    if(automata.getUltimoIndice() == ER.length()){
                        if(automata.miPila().size()>1){
                           automata.alterna =1;
                           estados = automata.construirAutomata(automata.getUltimoIndice());
                           if(automata.miPila().size()==1){
                             siguiente.setEnabled(false);
                           }
                        }else{siguiente.setEnabled(false);}
                    }
                }
                else if(ER.isEmpty()){
                    estados = automata.construirAutomata(automata.getUltimoIndice());
                }
        contenedor.add(siguiente);
        //cuando siguiente se deshabilita el AF esta construido a totalidad y se habilita cierrelambda
        if (siguiente.isEnabled()==false){
          contenedor.add(cierrelambda);
        }
        tablaAutomata=new Tabla(estados, simbolos);
        tablaAutomata.setBounds(80, 80, 500, 300);
        contenedor.add(tablaAutomata);
    }

    //metodo que almacena los simbolos de entrada en un vector
 public void simbolos(){
        for(int i=0;i<ER.length();i++){//Recorremos el vector para clasificar los caracteres.
            if(i==0 && Character.isLetterOrDigit(caracteres[i])){
                simbolos.addElement(caracteres[i]);
            }
            else{
                if(Character.isLetterOrDigit(caracteres[i])){//Si el caracter introducido
                    if(simbolos.size()==0){
                        simbolos.addElement(caracteres[i]);
                    }else{
                        for(int j=0;j<simbolos.size();j++){//Verificamos que no hayan simbolos repetidos.
                            if(!(caracteres[i]==simbolos.get(j))){
                                if(j==simbolos.size()-1)
                                 simbolos.addElement(caracteres[i]);
                           }else
                               break;
                        }
                    }
                }
             }
        }
    }

 //metodo constructor de la aplicacion , crea la ventana
    public static void main(String args[]){
        Principal ventana=new Principal("Convertir ER a AF");
        ventana.setSize(700,550);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
