public class Rot13 {
    static char[] abecedariMinus = "aàáäbcçdeèéëfghiìíïjklmnoòóöñpqrstuùúüvwxyz".toCharArray();
    static char[] abecedariMayus = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNOÒÓÖÑPQRSTUÙÚÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        String [] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String cadena = "ABC";
        /*System.out.println("Xifrat: ");
        for(int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(Character.isLetter(c)){
                if(c == abecedariMayus[i]) {
                    cadena += abecedariMayus[i];
                } 
                if(c == abecedariMinus[i]) {
                   cadena += abecedariMinus[i]; 
                }
            }
        }*/
        System.out.println("Xifrat: ");
        for(int i = 0; i < proves.length; i++) {
            if(proves[i] == abecedariMayus[i]) {
                    cadena += abecedariMayus[i];
                } 
                if(c == abecedariMinus[i]) {
                   cadena += abecedariMinus[i]; 
                }
            }
        xifraRot13(cadena);

    }
    public String xifraRot13(String cadena) {
        String textXifrat = "";
        
            for(int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                if(Character.isUpperCase(c)) {
                    int suma = i + 13;
                    c = abecedariMayus[suma];
                    textXifrat += c;
                }
                
            }
        return textXifrat;
    }

    public void desxifraRot13(String cadena) {
        if(Character.isLetter(cadena.charAt(cadena.length()))) {
            
        }
    }
}
