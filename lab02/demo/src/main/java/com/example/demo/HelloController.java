package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HelloController implements Initializable {

    private DirController dirController;
    private FolderInterface folderData;

    @FXML
    public ImageView imgFile;

    @FXML
    public TextFlow areaData;

    @FXML
    private ListView<String> FolderList;




    @FXML
    private void createFolderListView(){
        List<Path> folders =  dirController.getDirNames();
        for (Path p : folders) {
            File f = new File(p.getFileName().toString());
            System.out.println(p.getFileName().toString());
            this.FolderList.getItems().add(f.getName());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dirController = new DirController();
        dirController.showCatalog();
        this.createFolderListView();
    }

    @FXML
    public void showContent(MouseEvent mouseEvent) {
        String selectedFolder = FolderList.getSelectionModel().getSelectedItem();

        folderData =  dirController.getUserFileData(selectedFolder);
        Text text=new Text(folderData.showContent());
        text.setStyle("-fx-font-family: Arial");
        areaData.getChildren().clear();
        areaData.getChildren().add(text);

        folderData = dirController.getUserImgData(selectedFolder);
        imgFile.setImage(folderData.getImage());

    }
}