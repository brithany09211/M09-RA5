/*
 * Programa que implementa el xifratge ROT13.
 * El ROT13 consisteix a substituir cada lletra per la que està 13 posicions més enllà a l’alfabet.
 * Si és una majúscula → es busca dins l’array de majúscules i es canvia.
 * Si és una minúscula → es busca dins l’array de minúscules i es canvia.
 * Si no és una lletra (espais, comes, etc.) → es deixa igual.
 */
public class Rot13 {
    //Variables globals:
    static char[] abecedariMinus = "aàáäbcçdeèéëfghiìíïjklmnoòóöñpqrstuùúüvwxyz".toCharArray();
    static char[] abecedariMayus = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNOÒÓÖÑPQRSTUÙÚÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        //Proves:
        //Xifrats:
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        System.out.println("Xifrat: ");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String xifrat = xifraRot13(cadena);
            System.out.println(cadena + " => " + xifrat);
        }    
        //Desxifrats:
        System.out.println("\nDesxifrat");
        for (int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String xifrat = xifraRot13(cadena);
            String desxifrat = desxifraRot13(xifrat);
            System.out.println(xifrat + " => " + desxifrat);
        }
    }
    //Funcions:
    public static int indice (char [] abecedari, char c) {
        for (int i = 0; i < abecedari.length; i++) {
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
            //Abecedari en mayuscules:
            if(Character.isUpperCase(c)) {        
                int indice = indice(abecedariMayus, c);
                if(indice != 1) {    
                    int suma = (indice + 13) % abecedariMayus.length;    
                    textXifrat += abecedariMayus[indice];    
                } else {    
                    textXifrat += c;    
                }                    
            }   
            //Abecedari en minuscules:
            if(Character.isLowerCase(c)) {
                int indice = indice(abecedariMinus, c);
                if(indice != -1) {
                    int suma = (indice + 13) % abecedariMinus.length;
                    textXifrat += abecedariMinus[indice];
                } else {
                    textXifrat += c;
                }
            }
        }    
        return textXifrat;
    }

    public static String desxifraRot13(String cadena) {
        
        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            //Abecedari en mayuscules:
            if (Character.isUpperCase(c)) {
                int indice = indice(abecedariMayus, c);
                if (indice != -1) {
                    int resta = indice - 13;
                    if (resta < 0) resta += abecedariMayus.length;
                    textDesxifrat += abecedariMayus[resta];
                } else {
                    textDesxifrat += c;
                }
            //Abecedari en minuscules:
            } else if (Character.isLowerCase(c)) {
                int indice = indice(abecedariMinus, c);
                if (indice != -1) {
                    int resta = indice - 13;
                    if (resta < 0) resta += abecedariMinus.length;
                    textDesxifrat += abecedariMinus[resta];
                } else {
                    textDesxifrat += c;
                }
            } else {
                textDesxifrat += c;
            }
        }
        return textDesxifrat;
    }
}

