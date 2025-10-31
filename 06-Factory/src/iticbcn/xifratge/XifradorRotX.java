package iticbcn.xifratge;
/*
 * Programa que implementa el xifratge ROTX.
 * El ROTX consisteix a substituir cada lletra per les posicions 0,2,4 i 6 més enllà a l’alfabet.
 * Si és una majúscula → es busca dins l’array de majúscules i es canvia.
 * Si és una minúscula → es busca dins l’array de minúscules i es canvia.
 * Si no és una lletra (espais, comes, etc.) → es deixa igual.
 * Força bruta desxifra totes les opcions.
 */
public class XifradorRotX implements Xifrador {
    //Variables globals:
    char[] abecedariMinus = "aáàbcçdeéèfghiíìïjklmñnoóòpqrstuúùüvwxyz".toCharArray();
    char[] abecedariMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    public void forcaBrutaRotX(String cadenaXifrada ) {
        System.out.println("\nMissatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");

        for (int i = 0; i < abecedariMayus.length; i++) { 
            String resultDesxifrat = desxifraRotX(cadenaXifrada, i);
            System.out.println("("+ i +") -> " + resultDesxifrat); //surt del 0 fins al 39 de possibilitats. 
        }
    }

    public String xifraRotX(String cadena, int desplacament) {
        
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

    public String desxifraRotX(String cadena, int desplacament) {

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

    public int compararIndice(char[] abecedari, char c) {
        for(int i = 0; i < abecedari.length; i++) { 
            if(abecedari[i] == c) {
                return i;
            }      
        }
        return -1; // Si l’índex no és igual a la posició de l’abecedari, retorna -1.
    }
}
