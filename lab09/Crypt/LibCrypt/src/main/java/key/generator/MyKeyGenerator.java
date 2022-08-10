package key.generator;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MyKeyGenerator {
    private Algorithms algorithm;
    private KeyGenerator keyGenerator;
    private KeyPairGenerator keyPairGenerator;
    private int keyBitSize = 256;

    public SecretKey generateNewKey(Algorithms algorithm, SecureRandom secureRandom) {
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm.name());
        } catch(NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "error creating key generator", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if(algorithm == Algorithms.DES) {
            keyBitSize = 56;
        }
        else if(algorithm == Algorithms.RSA){
            keyBitSize = 1024;
        }
        else {
            keyBitSize = 256;
        }

        keyGenerator.init(keyBitSize, secureRandom);
        return keyGenerator.generateKey();
    }

    public KeyPair generateNewKeyPair(Algorithms algorithm) {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(algorithm.name());
        } catch(NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "error creating key pair generator", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return keyPairGenerator.generateKeyPair();
    }
}
