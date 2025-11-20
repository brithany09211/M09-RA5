package iticbcn.xifratge;

import java.security.KeyPair;
import javax.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String args[]) throws Exception {
        ClauPublica cp = new ClauPublica();
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";
        KeyPair parellClaus = cp.generaParellClausRSA();
        byte[] msgxifrat = cp.xifratRSA(msg, parellClaus.getPublic());
        
        System.out.println("==============================");
        System.out.print("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgxifrat));
        
        String msgDesxifrat = cp.desxifratRSA(msgxifrat, parellClaus.getPrivate());
        System.out.println("==============================");
        System.out.println("Text desxifrat: " + msgDesxifrat);
        
        String strClauPub = DatatypeConverter.printHexBinary(
        parellClaus.getPublic().getEncoded());
        String strClauPriv = DatatypeConverter.printHexBinary(
        parellClaus.getPrivate().getEncoded());
            
        System.out.println("==============================");
        System.out.println("Clau publica: " + strClauPub);
        System.out.println("==============================");
        System.out.println("Clau privada: " + strClauPriv);
    }
}