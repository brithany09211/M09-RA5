/*
 * Programa que implementa el xifratge ROTX.
 * El ROTX consisteix a substituir cada lletra per les posicions 0,2,4 i 6 més enllà a l’alfabet.
 * Si és una majúscula → es busca dins l’array de majúscules i es canvia.
 * Si és una minúscula → es busca dins l’array de minúscules i es canvia.
 * Si no és una lletra (espais, comes, etc.) → es deixa igual.
 * Força bruta desxifra totes les opcions.
 */
public class RotX {
    //Variables globals:
    static char[] abecedariMinus = "aáàbcçdeéèfghiíìïjklmñnoóòpqrstuúùüvwxyz".toCharArray();
    static char[] abecedariMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    public static void main(String[] args) {
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        //Proves:
        //Xifrats:
        System.out.println("Xifrat: ");
        System.out.println("------");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String resultXifrat = xifraRotX(cadena, i*2);
            System.out.println("("+ (i*2) +")-" + cadena + "    =>  " + resultXifrat);
        }
        //Desxifrats:
        System.out.println("\nDesxifrat: ");
        System.out.println("------");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String resultXifrat = xifraRotX(cadena, i*2);
            String resultDesxifrat = desxifraRotX(resultXifrat, i*2);
            System.out.println("("+ (i*2) +")-" + resultXifrat + "    =>  " + resultDesxifrat);

            //Força bruta:
            if(i == proves.length -1) { //Agafa l'última frase de proves.
                forcaBrutaRotX(resultXifrat);
            }
        }
    }
    static void forcaBrutaRotX(String cadenaXifrada ) {
        System.out.println("\nMissatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");

        for (int i = 0; i < abecedariMayus.length; i++) { 
            String resultDesxifrat = desxifraRotX(cadenaXifrada, i);
            System.out.println("("+ i +") -> " + resultDesxifrat); //surt del 0 fins al 39 de possibilitats. 
        }
    }

    public static String xifraRotX(String cadena, int desplacament) {
        
        String textXifrat = "";
        
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            //Abecedari en mayúscules
            if(Character.isUpperCase(c)) {        
                int indice = compararIndice(abecedariMayus, c); 
                if(indice != -1) {    
                    int suma = (indice + desplacament) % abecedariMayus.length; // fa una suma de l'índex més el desplacament i després fa el mòdul de la longitud de les lletres
                    textXifrat += abecedariMayus[suma];   
                } else {    
                    textXifrat += c;    
                }  
                //Abecedari en minúscules:                  
            } else if(Character.isLowerCase(c)) {
                int indice = compararIndice(abecedariMinus, c);
                if(indice != -1) {
                    int suma = (indice + desplacament)  % abecedariMinus.length; // fa una suma de l'índex més més el desplacament i després fa el mòdul de la longitud de les lletres
                    textXifrat += abecedariMinus[suma];
                } else {
                    textXifrat += c;
                }
            } else {
                textXifrat += c; // Perquè afegeixi els punts, espais, etc.
            }
        }    
        return textXifrat;
    }

    public static String desxifraRotX(String cadena, int desplacament) {

        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            //Abecedari en mayúscules:
            if (Character.isUpperCase(c)) {
                int indice = compararIndice(abecedariMayus, c);
                if (indice != -1) {
                    int resta = indice - desplacament; //resta l'indice 
                    if (resta < 0) {
                        resta += abecedariMayus.length; //Si l'índex és menor que 0, l'índex serà la mida de les lletres
                    }
                    textDesxifrat += abecedariMayus[resta];
                } else {
                    textDesxifrat += c; // Perquè afegeixi els punts, espais, etc.
                }
            //Abecedari en minúscules:
            } else if (Character.isLowerCase(c)) {
                int indice = compararIndice(abecedariMinus, c);
                if (indice != -1) {
                    int resta = indice - desplacament;
                    if (resta < 0) {
                    resta += abecedariMinus.length; //Si l'índex és menor que 0, l'índex serà la mida de les lletres
                    }
                    textDesxifrat += abecedariMinus[resta];
                } else {
                    textDesxifrat += c;
                }
            } else {
                textDesxifrat += c; // Perquè afegeixi els punts, espais, etc.
            }
        }
        return textDesxifrat;
    }

    static int compararIndice(char[] abecedari, char c) {
        for(int i = 0; i < abecedari.length; i++) { 
            if(abecedari[i] == c) {
                return i;
            }      
        }
        return -1; // Si l’índex no és igual a la posició de l’abecedari, retorna -1.
    }
}
