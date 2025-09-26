public class RotX {
    //Variables globals:
    static char[] abecedariMinus = "aáàbcçdeèéfghiíìïjklmñnoóòpqrstuúùüvwxyz".toCharArray();
    static char[] abecedariMayus = "AÁÀBCÇDEÈÉFGHIÌÍÏJKLMÑNOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    public static void main(String[] args) {
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};

        for(int i = 0; i < proves.length; i++) {
            String cadena = proves[i];
            forcaBrutaRotX(cadena);
            String resultXifrat = xifraRotX(cadenaXifrada, i);
        }
    }
    static void forcaBrutaRotX(String cadenaXifrada ) {
        for (int i = 0; i < 40; i++) {

            String resultDesxifrat = desxifraRotX(resultXifrat, i);
            System.out.println("("+ i +")-" + cadenaXifrada + "  =>" + resultXifrat);
            //System.out.println("("+ i +")-" + cadenaXifrada + "  =>" + resultDesxifrat);
        }
    }
    static String xifraRotX(String cadena, int desplacament ) {
        String cadenaXifrada = "";

        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(Character.isUpperCase(c)) {
                int indice = compararIndice(abecedariMayus, c);
                if(indice != -1) {
                    int suma = (indice + desplacament) % abecedariMayus.length;
                    cadenaXifrada += abecedariMayus[suma];
                } else {
                    cadenaXifrada += c;
                }
            } else if(Character.isLowerCase(c)) {
                int indice = compararIndice(abecedariMinus, c);
                if(indice != -1) {
                    int suma = (indice + desplacament) % abecedariMinus.length;
                    cadenaXifrada += abecedariMinus[suma];
                } else {
                    cadenaXifrada += c;
                }
            }
        }
        return cadenaXifrada;
    }
    static String desxifraRotX(String cadena, int desplacament) {
        String cadenaXifrada = "";

        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isUpperCase(c)) {
                int indice = compararIndice(abecedariMayus, c);
                if(indice != -1) {
                    int resta = indice - desplacament;
                    cadenaXifrada += abecedariMayus[resta];
                } else {
                    cadenaXifrada += c;
                }
            } else if(Character.isLowerCase(c)) {
                int indice = compararIndice(abecedariMinus, c);
                if(indice != -1) {
                    int resta = indice - desplacament;
                    cadenaXifrada += abecedariMinus[resta];
                } else {
                    cadenaXifrada += c;
                }
            }
        }
        return cadenaXifrada;
    }
    static int compararIndice(char[] abecedari, char c) {
        for(int i = 0; i < abecedari.length; i++) {
            if(abecedari[i] == c) {
                return i;
            }      
        }
        return -1;
    }
}
