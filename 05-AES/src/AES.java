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

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    public static final String CLAU = "Bril4M3j0r#09211";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Agora ïlla Ótto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                        + e.getLocalizedMessage());
            }
            
            // Mostrar els resultats
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats)); 
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String password) throws Exception {
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

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        String textDesxifrat = "";
        //Extreure l'IV
        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
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