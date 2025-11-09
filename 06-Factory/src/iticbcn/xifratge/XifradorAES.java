package iticbcn.xifratge;
/*
 * Programa que té com objectiu converteix un text amb xifratge AES utilitzant una contrasenya com a clau. 
 * La funció xifraAES pren un missatge i una contrasenya, genera un IV aleatori (per fer el xifrat més segur), 
 * transforma la contrasenya en una clau AES amb un hash, i després xifra el missatge combinant l’IV i el text xifrat. 
 * La funció desxifraAES fa el procés invers
 */
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    public static final String CLAU = "Bril4M3j0r#09211";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifrat = xifraAES(msg, clau);
            return new TextXifrat(xifrat);
        } catch (Exception e) {
            System.err.println("Error xifrant amb AES: " + e.getMessage());
            System.exit(1);//termina el programa
            return null; 
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error desxifrant amb AES: " + e.getMessage());
            System.exit(1); //termina el programa
            return null; 
        }
    }

    public byte[] xifraAES(String msg, String password) throws Exception {
        //Obtenir els bytes de el String
        byte[] msgBytes = msg.getBytes("UTF-8");

        //Genera IvParameterSpec
        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);  //L'IV ens dona un valor aleatori que ajuda a fer el xifrat més segur

        //Genera HASH
        //Creem una clau AES a partir del hash de la contrasenya
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashBytes = sha.digest(password.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(hashBytes, ALGORISME_XIFRAT);

        //Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] msgXifrat = cipher.doFinal(msgBytes); // Aquí es fa el xifrat real del missatge amb AES
        
        //Combinar IV i part xifrada
        byte[] IV_MsgXifrat = new byte[MIDA_IV + msgXifrat.length]; //Guardem l'IV al principi perquè el puguem usar per desxifrar després
        System.arraycopy(iv, 0, IV_MsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, IV_MsgXifrat, MIDA_IV, msgXifrat.length); // Copia la part xifrada després de fer l'IV

        return IV_MsgXifrat;
    }

    public String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        //Extreure l'IV
        iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv); //Treiem l'IV del principi per usar-lo en el desxifrat

        //Extreure la part xifrada.
        byte[] textXifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);//Separem només la part que està xifrada

        //Fer hash de la clau
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashBytes = sha.digest(password.getBytes("UTF-8"));
        hashBytes = Arrays.copyOf(hashBytes, 32);
        //Convertim la contrasenya en clau AES igual que en el xifrat
        SecretKeySpec keySpec = new SecretKeySpec(hashBytes, ALGORISME_XIFRAT);// Crea l'especificació de la clau AES per el hash

        //Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] textBytes = cipher.doFinal(textXifrat);

        //return String desxifrat
        return new String(textBytes, "UTF-8"); //Convertim els bytes desxifrats a String i el retornem
    }
}