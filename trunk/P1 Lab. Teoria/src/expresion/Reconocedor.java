package expresion;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexander Galvis
 *         Sebastian Ramirez
 */
public class Reconocedor {
      private final String terminales = "[0-9a-zA-ZÃ±Ã‘]";
      private String simboloent, er,t;
      public  boolean confirma,continuar;
      private int recorrer;
     /*Metodos utilizando el reconocimiento decendente para validar la 
     * entrada de datos del usuario, validando si la ER es correcta o no
     * La gramatica utilizada es la siguiente:
     * 1.  <1> 	-> <2><3>
     * 2.  <2>	-> <4><5>
     * 3.  <3>	-> |<2><3>
     * 4.  <3>	-> NULL
     * 5.  <3>	-> <2><3>  
     * 6.  <4>  -> (<1>)
     * 7.  <4>	-> I (simbolo cualquiera)
     * 8.  <5>	-> NULL
     * 9.  <5>	-> *<3>
     * 10. <5>	-> +<3>
     */ 
        public void reconocercadena(String c){
		confirma = true;
		er = c;
		recorrer = 0;
		simboloent = lea(c);
		NT1();
                recorrer = 0;
                    int contadorDerecho=0, contadorIzquierdo=0;
                    String caracter= lea(c);
                    while(!caracter.isEmpty()){
                        if(caracter.equalsIgnoreCase("(")){
                            contadorIzquierdo++;
                        }
                        if(caracter.equalsIgnoreCase(")")){
                            contadorDerecho++;
                        }
                        caracter=lea(c);
                    }
                    if(contadorDerecho!=contadorIzquierdo){
                        confirma=false;
                    }
		if(simboloent.isEmpty() & confirma==true){
                   continuar=true; 
                }
		else{
                 continuar=false;
		}
	}
        
        public void NT1(){
		if(simboloent.equalsIgnoreCase("(") || simboloent.matches(terminales)){
			NT2();
			NT3();
		}
		else{confirma = false;}
	}
        
        public void NT2(){
		if(simboloent.equalsIgnoreCase("(") || simboloent.matches(terminales)){
			NT4();
			NT5();
		}
		else{confirma = false;}
	}
        
        public void NT3(){
		if(simboloent.equalsIgnoreCase("(") || simboloent.matches(terminales)){
			NT2();
			NT3();
		}
		else if(simboloent.equalsIgnoreCase("|")){
			simboloent = lea(er);
			NT2();
			NT3();
		}
		else if(simboloent.equalsIgnoreCase(")") || simboloent.isEmpty()){}
		else{confirma = false;}
	}
        
        public void NT4(){
		if(simboloent.equalsIgnoreCase("(")){
			simboloent = lea(er);
			NT1();
			if (simboloent.equalsIgnoreCase(")")){
				simboloent = lea(er);
			}else{confirma = false;}
		}
		else if(simboloent.matches(terminales)){
			simboloent = lea(er);
		}
		else{confirma = false;}
	}
        
        public void NT5(){
		if(simboloent.equalsIgnoreCase("*")){
			simboloent = lea(er);
			NT3();
		}
		else if(simboloent.equalsIgnoreCase("+")){
			simboloent = lea(er);
			NT3();
		}
		else if(simboloent.equalsIgnoreCase("(") || simboloent.equalsIgnoreCase(")")
				|| simboloent.equalsIgnoreCase("|") || simboloent.matches(terminales)
				|| simboloent.isEmpty()){
		}
		else{confirma = false;}
	}
	
        //para leer la cadena ingresada en el campo de texto caracter a caracter
        public String lea(String cadena){
		char caracter;
		String g;
		int tamanoCadena = cadena.length();
		if(recorrer>=tamanoCadena){
			g = "";
		}else{
			caracter = cadena.charAt(recorrer);
			g = caracter + "";
		}
		recorrer++;
		return g;
	}
}
