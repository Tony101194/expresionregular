package ExpresionRegular.reconocimiento;

/**
 * Esta clase funciona como reconocedor de la cadena que se ingrese, se hace el
 * uso de métodos que utilizan el reconocimiento decendente y validan la
 * entrada de datos del usuario, validando si la ER es correcta o no
 * La gramatica utilizada es la siguiente:
 *<ul>
 *<li>1.  <1>-> <2><3> </li>
 *<li>2.  <2>-> <4><5></li>
 *<li>3.  <3>-> |<2><3></li>
 *<li>4.  <3>-> NULL</li>
 *<li> 5. <3>-> <2><3></li>
 *<li>6.  <4> -> (<1>)</li>
 *<li> 7. <4>-> I (simbolo cualquiera)</li>
 *<li>8.  <5>-> NULL</li>
 *<li>9.  <5>-> *<3></li>
 *<li>10. <5>-> +<3></li>
 *</ul>
 * @version 1.0
 * @author Alexander Galvis
 *         Sebastian Ramirez
 *
 */
public class Reconocedor {
      private final String terminales = "[0-9a-zA-ZÃ±Ã‘]";
      private String simboloent, er,t;
      public  boolean confirma,continuar;
      private int recorrer;

      /**
       * Se crea un subprograma tipo void por cada N (No terminal).Se maneja una
       * variable global con la cual se procesa la hilera a reconocer , cada
       * subprogramadeja un simbolo leido
       * Tema correspondiente a : Reconocimiento Descendente Recursivo
       * Notas de Roberto Florez
       * 
       * @param c
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
	
        /**
         * para leer la cadena ingresada en el campo de texto caracter a caracter
         * @param cadena esta es la ingresada por el usuario
         * @return <code>Sring</code> 
         */
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
