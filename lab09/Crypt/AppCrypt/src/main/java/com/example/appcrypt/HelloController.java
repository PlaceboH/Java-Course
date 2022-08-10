package com.example.appcrypt;


import encryption.EncryptAndDecryptFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import key.generator.Algorithms;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;



public class HelloController implements Initializable {

    private FileChooser fil_chooser;
    @FXML
    private Stage stage;
    @FXML
    private Label lableFile;
    @FXML
    private TextField aliasInput;
    @FXML
    private MenuButton menuButton;

    private File fileInput;
    private File fileOutput;
    private String fileContent;
    private List<String> aliases = new ArrayList<>();
    private SecureRandom secureRandom;
    private EncryptAndDecryptFile encryptAndDecryptFile;
    private Algorithms algorithm;

    public void openFile(ActionEvent actionEvent) {
        fileInput = fil_chooser.showOpenDialog(stage);
        if (fileInput != null) {
            lableFile.setText(fileInput.getAbsolutePath()
                    + "  selected");
        }
    }

    public void saveFile(ActionEvent actionEvent) throws IOException {
        fil_chooser.setInitialFileName(fileInput.getName());
        File newFile = fil_chooser.showSaveDialog(stage);
        if (fileInput != null) {
            writeFile(newFile);
            lableFile.setText(fileInput.getAbsolutePath()
                    + "  selected");
        }
    }

    public void encryptFile(ActionEvent actionEvent) {
        String alias = aliasInput.getText();
        if(alias.isEmpty() || this.aliases.contains(alias))
            return;

        if(menuButton.getText() == "AES")
            algorithm = Algorithms.AES;
        else if(menuButton.getText() == "DES")
            algorithm = Algorithms.DES;
        else  if(menuButton.getText() == "RSA")
            algorithm = Algorithms.RSA;
        else
            return;

        encryptAndDecryptFile = new EncryptAndDecryptFile(algorithm, secureRandom);

        String fileName = fileInput.getAbsolutePath();
        int dotExt = fileName.lastIndexOf(".");
        if (dotExt > 0) {
            fileName = fileName.substring(0, dotExt);
        }
        encryptAndDecryptFile.encrypt(fileInput.getAbsolutePath(), fileName + "enc.txt", alias);
    }

    public void decryptFile(ActionEvent actionEvent) {
        String alias = aliasInput.getText();
        if(alias.isEmpty())
            return;

        if(menuButton.getText() == "AES")
            algorithm = Algorithms.AES;
        else if(menuButton.getText() == "DES")
            algorithm = Algorithms.DES;
        else  if(menuButton.getText() == "RSA")
            algorithm = Algorithms.RSA;
        else
            return;

        encryptAndDecryptFile = new EncryptAndDecryptFile(algorithm, secureRandom);
        String fileName = fileInput.getAbsolutePath();
        int dotExt = fileName.lastIndexOf(".");
        if (dotExt > 0) {
            fileName = fileName.substring(0, dotExt);
        }
        encryptAndDecryptFile.decrypt(fileInput.getAbsolutePath(), fileName + "dec.txt", alias);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new Stage();
        fil_chooser = new FileChooser();
        fil_chooser.setInitialFileName("filename");
        secureRandom = new SecureRandom();
        fileContent = new String();
        for (Algorithms item : Algorithms.values() ) {
            menuButton.getItems().add(new MenuItem(item.toString()));
        }
        menuButton.getItems().stream().forEach( menuItem -> {
            menuItem.setOnAction(e -> {
                menuButton.setText(menuItem.getText());
            });
        });
    }


    private String getFileContent() throws FileNotFoundException {
        Scanner sc = new Scanner(this.fileInput);
        String content = new String();
        while(sc.hasNextLine()){
            content = sc.nextLine();
        }
        return content;
    }

    private void writeFile(File newFile) throws IOException{
        if (this.fileInput.canRead()) {
            getFileContent();
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(newFile);
            fileWriter.write(this.fileContent);
            fileWriter.close();
        }

    }

}