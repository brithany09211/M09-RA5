package iticbcn.xifratge;
/*
 * Programa que té com objectiu converteix un text amb xifratge mono-alfabètic quan per xifrar s’utilitza una
 * permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original per la
 * corresponent en la mateixa posició en l’alfabet permutat.
 */
import java.util.*;
public class XifradorMonoalfabetic implements Xifrador {
    //Variables globals:
    char[] caractersMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    char[] permutacio;

    public XifradorMonoalfabetic() {
        permutacio = permutaAlfabet(caractersMayus); // Inicialitza la permutació en el constructor
    }
    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        return desxifraMonoAlfa(new String(xifrat.getBytes()));
    }

    //Función que convierte el char caractersMayus a una lista después utiliza Collectios shuffle(lista) para mezclar y devuelve un char[]
    public char[] permutaAlfabet(char[] abecedari) {
        List<Character> llistaCaracters = new ArrayList<>();

        for(int i = 0; i < caractersMayus.length; i++) {
            char c = caractersMayus[i];
            llistaCaracters.add(c); //Añadimos los caracteres a la lista
        }
        Collections.shuffle(llistaCaracters); //Los mezclamos con shuffle

        char[] permutacioCaracters = new char[llistaCaracters.size()];
        for(int i = 0; i < llistaCaracters.size(); i++) {
            permutacioCaracters[i] = llistaCaracters.get(i); //Guarda los caracteres de la lista en un char[]
        }
        return permutacioCaracters;
    }

    public String xifraMonoAlfa(String cadena) {
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
    
    public String desxifraMonoAlfa(String cadenaXifrada) {
        
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
    public int compararIndice(char[] abecedari, char c) {
        for(int i = 0; i < abecedari.length; i++) { 
            if(abecedari[i] == c) {
                return i;
            }      
        }
        return -1; // Si el índex no es igual a la posición de el abecedario, devuelve -1.
    }
}
