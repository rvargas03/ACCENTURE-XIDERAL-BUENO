package SEMANA1;

public class ManipuladorStrings {
	 public static String invertir(String s) {
	        // TODO: usar StringBuilder.reverse()
	        return new StringBuilder(s).reverse().toString();
	    }

	    public static boolean esPalindromo(String s) {
	        // TODO: limpiar (toLowerCase, replaceAll espacios)
	    	 String limpio = s.toLowerCase().replaceAll("\\s+", "");
	        // TODO: comparar con su version invertida
	    	 String invertido = new StringBuilder(limpio).reverse().toString();
	        return  limpio.equals(invertido);
	    }

	    public static int contarVocales(String s) {
	        int count = 0;
	        String vocales = "aeiouAEIOU";
	        // TODO: recorrer cada caracter, verificar si es vocal
	        for (char c : s.toCharArray()) {
	            if (vocales.indexOf(c) != -1) {
	                count++;
	            }
	        }
	        return count;
	    }

	    public static String construirPiramide(int niveles) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 1; i <= niveles; i++) {
	                  // espacios
	                  sb.append(" ".repeat(niveles - i));

	                  // asteriscos
	                  sb.append("*".repeat(2 * i - 1));

	                  // salto de línea
	                  sb.append("\n");
	              }
	        
	        return sb.toString();
	    }

	    public static void main(String[] args) {
	        System.out.println("Invertir 'Hola Mundo': "
	                         + invertir("Hola Mundo"));
	        System.out.println("'Anita lava la tina' es palindromo: "
	                         + esPalindromo("Anita lava la tina"));
	        System.out.println("Vocales en 'Murcielago': "
	                         + contarVocales("Murcielago"));
	        System.out.println("Piramide de 5 niveles:");
	        System.out.println(construirPiramide(5));
	    }

}
