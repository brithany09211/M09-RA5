import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0; 

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest mssgd = MessageDigest.getInstance("SHA-512");
        String combinat = pw + salt;
        byte[] bytes = mssgd.digest(combinat.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iteracions = 10000;
        int midaClau = 128; // 128 bits

        KeySpec espec = new PBEKeySpec(pw.toCharArray(),salt.getBytes(),iteracions,midaClau);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] bytes = factory.generateSecret(espec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    private String pwTrobat(String alg,char[] arrayPw,int pos,char ch,String hash,String salt) throws Exception {
        arrayPw[pos] = ch;
        String actualPw = new String(arrayPw, 0, pos + 1);
        npass++;

        String hashCalculat;
        if (alg.equals("SHA-512")) {
            hashCalculat = getSHA512AmbSalt(actualPw, salt);
        } else {
            hashCalculat = getPBKDF2AmbSalt(actualPw, salt);
        }

        if (hashCalculat.equals(hash)) {
            return actualPw;
        }

        return null;
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String charset = "abcdefABCDEF1234567890!";

        // Máximo de 6 caracteres
        char[] aPw = new char[6];

        npass = 0;

        return forcaBrutaRecursiva(alg, hash, salt, charset, aPw, 0);
    }

    private String forcaBrutaRecursiva(String alg, String hash, String salt, String charset, char[] aPw, int pos) throws Exception {
        if (pos >= aPw.length)
            return null;

        for (int i = 0; i < charset.length(); i++) {
            String trobat = pwTrobat(alg,aPw,pos,charset.charAt(i),hash,salt);

            if (trobat != null)
                return trobat;

            String resultatRecursiu = forcaBrutaRecursiva(alg, hash, salt, charset, aPw, pos + 1);

            if (resultatRecursiu != null)
                return resultatRecursiu;
        }

        return null;
    }

    // Formatea el tiempo transcurrido
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (diff % (1000 * 60)) / 1000;
        long millis = diff % 1000;

        return String.format(
                "%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis
        );
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañs1kdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), 
            h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long tl = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(tl, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
