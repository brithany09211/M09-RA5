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
        /* 
        //Desxifrats:
        System.out.println("\nDesxifrat: ");
        System.out.println("------");
        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            String resultXifrat = xifraRotX(cadena, i*2);
            String resultDesxifrat = desxifraRotX(resultXifrat, i*2);
            System.out.println("("+ (i*2) +")-" + resultXifrat + "    =>  " + resultDesxifrat);
        }
        */
    }
    //Función que convierte el char caractersMayus a list<> despues utiliza Collectios shuffle (lista) 
    //convierte en char[] y la devuelve
    public static char[] permutaAlfabet(char[] abecedari) {
        List<Character> llistaCaracters = new ArrayList<>();

        for(int i = 0; i < caractersMayus.length; i++) {
            char c = caractersMayus[i];
            llistaCaracters.add(c); //añadimos los caracteres a la lista
        }
        Collections.shuffle(llistaCaracters); //Los mezclamos con shuffle

        char[] permutacioCaracters = new char[llistaCaracters.size()];
        for(int i = 0; i < llistaCaracters.size(); i++) {
            permutacioCaracters[i] += llistaCaracters.get(i); //guarda los caracteres de la lista en un array[]
        }
        return permutacioCaracters;
    }
    public static int compararIndice (char[] abecedari, char c) {
        for(int i= 0; i < abecedari.length; i++) {
            char n = abecedari[i];
            if(c == n) {
                return i;
            } 
        }
        return -1;
    }
    public static String xifraMonoAlfa(String cadena) {
    //las minusculas se xifran en minusculas y las mayusculas en mayusculas con una booleana
        String cadenaXifrada = ""; 
        for(int i= 0; i < permutacio.length; i++) {         
            for(int j= 0; j < cadena.length(); j++) {
                char c = cadena.charAt(i);

                if(Character.isUpperCase(c)) {
                    int indice = compararIndice(caractersMayus, c);
                    if(indice != -1) {
                        if (i == indice) {
                            cadenaXifrada += permutacio[indice];
                        } else {
                            return cadenaXifrada;
                        }
                    }
                }
            }
        }
        return cadenaXifrada;
    }

    public static String desxifraMonoAlfa(String cadena) {
        return null;
    }
}
