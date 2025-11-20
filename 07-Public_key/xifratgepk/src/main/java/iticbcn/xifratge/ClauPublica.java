package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public class ClauPublica {
        
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        keyGen.initialize(2048, random);
        return keyGen.generateKeyPair();
    }

    public byte[] xifratRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        //Convertim la clau en bytes i el cifrem amb el RSA 
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifratRSA(byte[] missatgeXifrat, PrivateKey ClauPublica) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, ClauPublica);
        //Convertim en bytes i el xifrem amb RSA 
        byte[] bytesDesxifrats = cipher.doFinal(missatgeXifrat);

        return new String(bytesDesxifrats, "UTF-8");
    }
}