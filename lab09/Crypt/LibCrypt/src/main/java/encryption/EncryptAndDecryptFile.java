package encryption;

import key.generator.MyKeyGenerator;
import key.generator.Algorithms;
import key.store.MyKeyStore;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.SecureRandom;

public class EncryptAndDecryptFile {

    private MyKeyGenerator myKeyGenerator = new MyKeyGenerator();
    private Algorithms algorithm;
    private SecureRandom secureRandom;
    private MyKeyStore myKeyStore;


    public EncryptAndDecryptFile(Algorithms algorithm, SecureRandom secureRandom){
        this.algorithm = algorithm;
        this.secureRandom = secureRandom;
        this.myKeyStore = new MyKeyStore("private", "12345", "keyStores");
    }

    public SecretKey encrypt(String input, String output, String alias) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm.name());
            SecretKey secretKey = myKeyGenerator.generateNewKey(algorithm, secureRandom);
            System.out.println(algorithm);

            myKeyStore.storeToKeyStore(secretKey, alias);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            File fileInput = new File(input);
            FileInputStream fileInputStream = new FileInputStream(fileInput);
            FileOutputStream fileOutputStream = new FileOutputStream(output);
            byte[] inputBytes = new byte[(int) fileInput.length()];
            fileInputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            fileOutputStream.write(outputBytes);
            fileOutputStream.close();
            fileInputStream.close();

            return secretKey;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String input, String output, String alias) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm.name());
            SecretKey secretKey = myKeyStore.loadFromKeyStore(alias);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            System.out.println(algorithm);

            File fileInput = new File(input);
            FileInputStream fileInputStream = new FileInputStream(fileInput);
            FileOutputStream fileOutputStream = new FileOutputStream(output);
            byte[] inputBytes = new byte[(int) fileInput.length()];
            fileInputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            fileOutputStream.write(outputBytes);
            fileOutputStream.close();
            fileInputStream.close();
            System.out.println(outputBytes.toString());
            return outputBytes.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
