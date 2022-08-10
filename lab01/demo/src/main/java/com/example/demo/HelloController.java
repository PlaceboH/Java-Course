package com.example.demo;

import com.library.ChangeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.library.ChangeDetector;
import com.library.FileManager;
import com.library.ChangeType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class HelloController {

    private ChangeDetector changeDetector;
    private Path path;

    public void setChangeDetector(Path path) throws IOException {
        this.changeDetector = new ChangeDetector(path);
    }

    @FXML
    private TextArea outputArea;

    @FXML
    private TextField folderPath;

    @FXML
    protected void onAllFilesButtonClick(ActionEvent event) throws IOException {
        StringBuilder allContent = new StringBuilder("");
        allContent.append("All Files in Folder: \n");
        changeDetector.getSnapshot().stream().forEach(x -> allContent.append(x));
        allContent.append("\n+++Done!+++ \n");
        outputArea.clear();
        outputArea.setText(allContent.toString());

    }
    @FXML
    protected void onSetPathButtonClick(ActionEvent event) throws IOException {
        if (!folderPath.getText().isEmpty()) {
            this.path = Paths.get(folderPath.getText());
            setChangeDetector(this.path);
        }
    }
        @FXML
    protected void onChangesButtonClick(ActionEvent event) throws IOException {
        if (folderPath.getText().isEmpty()) {
            return;
        }
        Map<ChangeType, List<FileManager>> changes = changeDetector.detectChanges();
        StringBuilder allContent = new StringBuilder("");

        allContent.append("Changes: ");

        if (!changes.get(ChangeType.Created).isEmpty()) {
            allContent.append("\n New Files: \n");
            changes.get(ChangeType.Created).stream()
                    .forEach(x -> allContent.append(x));
        }
        if (!changes.get(ChangeType.Changed).isEmpty()) {
            allContent.append("\n Changed Files: \n");
            changes.get(ChangeType.Changed).stream()
                    .forEach(x ->allContent.append(x));
        }

        if(!changes.get(ChangeType.Deleted).isEmpty()) {
            allContent.append("\n Deleted Files: \n");
            changes.get(ChangeType.Deleted).stream()
                    .forEach(x -> allContent.append(x));
        }
        allContent.append("\n+++Done!+++\n");
        outputArea.clear();
        outputArea.setText(allContent.toString());

    }

}