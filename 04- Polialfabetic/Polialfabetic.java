/*
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static long clauSecreta = 123L;
    private Random rnd;
    static char[] caractersMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static char[] caractersPermutats = null;
    //guardar lo permutado bro
    public static void permutaAlfabet() {
        //random y shelf
        List<Character> llistaCaracters = new ArrayList<>();
        for(int i = 0; i < caractersMayus.length; i++) {
            char c = caractersMayus[i];
            llistaCaracters.add(c); //Añadimos los caracteres a la lista
        }
        Collections.shuffle(llistaCaracters); //Los mezclamos con shuffle

        char[] permutacioCaracters = new char[llistaCaracters.size()];
        for(int i = 0; i < llistaCaracters.size(); i++) {
            permutacioCaracters[initRandom(i)] += llistaCaracters.get(i); //Guarda los caracteres de la lista en un char[]
        }   
        return permutacioCaracters;
    }
        

    public static String xifraPoliAlfa(String msg) {
        String caracterXifrat = "";
        //me pasa solo una frase que ya descomprimio de msg[]
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            permutaAlfabet();
            for (int j = 0; j <  caractersPermutats.length; j++) {
                caracterXifrat += caractersPermutats[j];
            }
        }
        return caracterXifrat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        for(int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            int index = compararIndice(c, caractersMayus);
            return null;
        }
    }

    private static void initRandom(long clau) {
        Random rnd = new Random(clau);
    }

    public static int compararIndice (char c, char[] caracters) {
        for(int i = 0; i < caracters.length; i++) {
            if(caracters[i] == c) {
                return i;
            } 
        }
        return -1;
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                         "Test 02 Taull, DÍA, año", 
                         "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = XifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }    
}
