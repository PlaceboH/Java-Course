package key.store;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


public class MyKeyStore {
    private KeyStore keyStore;
    private String keyStoreFileName;
    private String keyStoreType;
    private String keyStorePassword;

    public MyKeyStore(String keyStoreType, String keyStorePassword, String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
        this.keyStoreType = keyStoreType;
        this.keyStorePassword = keyStorePassword;
    }

    public void storeToKeyStore(SecretKey keyToStore, String alias/*, Certificate certificate*/) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        keyStore = KeyStore.getInstance("JCEKS");
        char[] password = keyStorePassword.toCharArray();
        keyStore.load(null, password);
        keyStore.setKeyEntry(alias, keyToStore, keyStorePassword.toCharArray(), null);
        //keyStore.setCertificateEntry("certificateAlias", certificate);
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(keyStoreFileName + "_" + alias + ".txt", true);
            keyStore.store(outputStream, keyStorePassword.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    public SecretKey loadFromKeyStore(String alias) {
        try {
            keyStore = KeyStore.getInstance("JCEKS");
            InputStream inputStream = new FileInputStream(keyStoreFileName + "_" + alias + ".txt");
            keyStore.load(inputStream, keyStorePassword.toCharArray());
            return (SecretKey) keyStore.getKey(alias, keyStorePassword.toCharArray());
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return null;
    }


}
