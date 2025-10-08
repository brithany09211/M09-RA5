/*
 * Programa que té com objectiu converteix un text amb xifratge mono-alfabètic quan per xifrar s’utilitza una
 * permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original per la
 * corresponent en la mateixa posició en l’alfabet permutat.
 */
import java.util.*;
public class Monoalfabetic {
    //Variables globals:
    static char[] caractersMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static char[] permutacio = permutaAlfabet(caractersMayus);
    public static void main(String[] args) {
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        //Proves:
        //Xifrats:
        System.out.println("Xifrat: ");
        System.out.println("------");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String resultXifrat = xifraMonoAlfa(cadena);
            System.out.println(cadena + "    =>  " + resultXifrat);
        }

        //Desxifrats:
        System.out.println("\nDesxifrat: ");
        System.out.println("------");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String resultXifrat = xifraMonoAlfa(cadena);
            String resultDesxifrat = desxifraMonoAlfa(resultXifrat);
            System.out.println(resultXifrat + "    =>  " + resultDesxifrat);
        }
    }

    //Función que convierte el char caractersMayus a una lista después utiliza Collectios shuffle(lista) para mezclar y devuelve un char[]
    public static char[] permutaAlfabet(char[] abecedari) {
        List<Character> llistaCaracters = new ArrayList<>();

        for(int i = 0; i < caractersMayus.length; i++) {
            char c = caractersMayus[i];
            llistaCaracters.add(c); //Añadimos los caracteres a la lista
        }
        Collections.shuffle(llistaCaracters); //Los mezclamos con shuffle

        char[] permutacioCaracters = new char[llistaCaracters.size()];
        for(int i = 0; i < llistaCaracters.size(); i++) {
            permutacioCaracters[i] += llistaCaracters.get(i); //Guarda los caracteres de la lista en un char[]
        }
        return permutacioCaracters;
    }

    public static String xifraMonoAlfa(String cadena) {
        String cadenaXifrada = "";       
        for(int j= 0; j < cadena.length(); j++) {
            char c = cadena.charAt(j);
            boolean esMinuscula = Character.isLowerCase(c); // Guarda si es minúscula
            char cMayus = Character.toUpperCase(c);          

            int index = compararIndice(caractersMayus, cMayus);

            if (index != -1) {
                char caracterPermutat = permutacio[index];
                if (esMinuscula) {
                    caracterPermutat = Character.toLowerCase(caracterPermutat); //Lo pone en minúscula si era minúscula
                }
                cadenaXifrada += caracterPermutat;
            } else {
                cadenaXifrada += c; // Deja igual si no está en el abecedario
            }
        }
        return cadenaXifrada;
    }
    
    public static String desxifraMonoAlfa(String cadenaXifrada) {
        
        String cadenaDesxifrada = ""; 

        for(int j= 0; j < cadenaXifrada.length(); j++) {
            char c = cadenaXifrada.charAt(j);
            boolean esMinuscula = Character.isLowerCase(c); // Guarda si es minúscula
            char cMayus = Character.toUpperCase(c);           

            int index = compararIndice(permutacio, cMayus);

            if (index != -1) {
                char caracterXifrat = caractersMayus[index];
                if (esMinuscula) {
                    caracterXifrat = Character.toLowerCase(caracterXifrat); //Lo pone en minúscula si era minúscula
                }
                cadenaDesxifrada += caracterXifrat;
            } else {
               cadenaDesxifrada += c; // Deja igual si no está en el abecedario
            }
        }
        return cadenaDesxifrada;
    }
    public static int compararIndice(char[] abecedari, char c) {
        for(int i = 0; i < abecedari.length; i++) { 
            if(abecedari[i] == c) {
                return i;
            }      
        }
        return -1; // Si l’índex no és igual a la posició de l’abecedari, retorna -1.
    }
}
