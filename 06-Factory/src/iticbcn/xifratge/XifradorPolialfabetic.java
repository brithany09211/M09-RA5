package iticbcn.xifratge;
/*
 * Programa que té com objectiu converteix un text amb xifratge poli-alfabètic quan per xifrar s’utilitza una
 * permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original amb Random per la
 * corresponent en la mateixa posició en l’alfabet permutat.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    //Varibales globals:
    private long clauSecreta = 123L;
    private Random rnd;
    char[] caractersMayus = "AÁÀBCÇDEÉÈFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    char[] caractersPermutats;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauConvertida;
        try {
            clauConvertida = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauConvertida);

        return new TextXifrat(XifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
    
        long clauConvertida;
        try {
            clauConvertida = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauConvertida);

        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }

    private void initRandom(long clau) {
        rnd = new Random(clauSecreta);
    }

    public void permutaAlfabet() {
        List<Character> llistaCaracters = new ArrayList<>();
        for(int i = 0; i < caractersMayus.length; i++) {
            char c = caractersMayus[i];
            llistaCaracters.add(c); 
        }
        Collections.shuffle(llistaCaracters, rnd); 

        caractersPermutats = new char[llistaCaracters.size()];
        for(int i = 0; i < llistaCaracters.size(); i++) {
            caractersPermutats[i] += llistaCaracters.get(i); //Guarda los caracteres de la lista en una variable global
        }   
    }

    public String XifraPoliAlfa(String msg) {
        initRandom(clauSecreta); //Inicia el Random amb la clau secreta
        permutaAlfabet(); //Permuta l'alfabet una vegada 
        String cadenaXifrada = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(c); // Guarda si es minúscula
            char cMayus = Character.toUpperCase(c);
            int indice = compararIndice(cMayus, caractersMayus);
            if(indice != -1) {
                char caracterPermutat = caractersPermutats[indice];
                if(esMinuscula) {
                    caracterPermutat = Character.toLowerCase(caracterPermutat);
                }
                cadenaXifrada += caracterPermutat;
                
            } else {
                cadenaXifrada += c;
            }
        }
        return cadenaXifrada;
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        String cadenaDesxifrada = "";
        for(int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(c); // Guarda si es minúscula
            char cMayus = Character.toUpperCase(c);
            int indice = compararIndice(cMayus, caractersPermutats);
            if(indice != -1) {
                char caracterDexifrat = caractersMayus[indice];
                if(esMinuscula) {
                    caracterDexifrat = Character.toLowerCase(caracterDexifrat);
                }
                cadenaDesxifrada += caracterDexifrat;
                
            } else {
                cadenaDesxifrada += c;
            }
        }
        return cadenaDesxifrada;
    }

    public int compararIndice (char c, char[] caracters) {
        for(int i = 0; i < caracters.length; i++) {
            if(caracters[i] == c) {
                return i;
            } 
        }
        return -1;
    }    
}
