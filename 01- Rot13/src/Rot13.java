/*
 * Programa que implementa el xifratge ROT13.
 * El ROT13 consisteix a substituir cada lletra per la que està 13 posicions més enllà a l’alfabet.
 * Si és una majúscula → es busca dins l’array de majúscules i es canvia.
 * Si és una minúscula → es busca dins l’array de minúscules i es canvia.
 * Si no és una lletra (espais, comes, etc.) → es deixa igual.
 */
public class Rot13 {
    //Variables globals:
    static char[] abecedariMinus = "aáàbcçdeèéfghiíìïjklmñnoóòpqrstuúùüvwxyz".toCharArray();
    static char[] abecedariMayus = "AÁÀBCÇDEÈÉFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public static void main(String[] args) {
    //Proves:
        //Xifrats:
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        System.out.println("Xifrat: ");
        for(int i = 0; i < proves.length; i++) { // recorre l'array i el guarda en un String
            String cadena = proves[i];
            String xifrat = xifraRot13(cadena); 
            System.out.println(cadena + " => " + xifrat);
        }    
        //Desxifrats:
        System.out.println("\nDesxifrat: ");
        for (int i = 0; i < proves.length; i++) { // recorre l'array i el guarda en un String
            String cadena = proves[i];
            String xifrat = xifraRot13(cadena);
            String desxifrat = desxifraRot13(xifrat); //Per desxifrar passa la cadena xifrada de la funció
            System.out.println(xifrat + " => " + desxifrat);
        }
    }
    //Funcions:
    public static int CompararIndice (char [] abecedari, char c) {
        for (int i = 0; i < abecedari.length; i++) { // recorre l'array i el compara amb el caràcter
            if(abecedari[i] == c) {
                //Suma 13 l'index
                return i;
            }
        }
        //si no retorna -1
        return -1;
    }

    public static String xifraRot13(String cadena) {
        
        String textXifrat = "";
        
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            //Abecedari en mayúscules
            if(Character.isUpperCase(c)) {        
                int indice = CompararIndice(abecedariMayus, c); 
                if(indice != -1) {    
                    int suma = (indice + 13) % abecedariMayus.length; // fa una suma de l'índex més 13 i després fa el mòdul de la longitud de les lletres
                    textXifrat += abecedariMayus[suma];   
                } else {    
                    textXifrat += c;    
                }  
                //Abecedari en minúscules:                  
            } else if(Character.isLowerCase(c)) {
                int indice = CompararIndice(abecedariMinus, c);
                if(indice != -1) {
                    int suma = (indice + 13)  % abecedariMinus.length; // fa una suma de l'índex més 13 i després fa el mòdul de la longitud de les lletres
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

    public static String desxifraRot13(String cadena) {

        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            //Abecedari en mayúscules:
            if (Character.isUpperCase(c)) {
                int indice = CompararIndice(abecedariMayus, c);
                if (indice != -1) {
                    int resta = indice - 13; //resta l'indice 
                    if (resta < 0) {
                        resta += abecedariMayus.length; //Si l'índex és menor que 0, l'índex serà la mida de les lletres
                    }
                    textDesxifrat += abecedariMayus[resta];
                } else {
                    textDesxifrat += c; // Perquè afegeixi els punts, espais, etc.
                }
            //Abecedari en minúscules:
            } else if (Character.isLowerCase(c)) {
                int indice = CompararIndice(abecedariMinus, c);
                if (indice != -1) {
                    int resta = indice - 13;
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
}

