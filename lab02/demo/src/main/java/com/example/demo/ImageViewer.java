package com.example.demo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import javafx.scene.image.Image;

public class ImageViewer  implements FolderInterface{

    Image imageIcon;
    Path path;
    ImageViewer(Path path){
        this.path = path;
        this.loadFromPath();
    }

    @Override
    public String getType() {
        return "img";
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void loadFromPath() {
        try {
            FileInputStream imgStream = imgStream = new FileInputStream(path.toAbsolutePath().toString());
            imageIcon = new Image(imgStream);
        } catch (FileNotFoundException e) {
            System.out.println("Do not find img!");
            e.printStackTrace();
        }
    }

    @Override
    public String showContent() {
        return this.path.toAbsolutePath().toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(imageIcon.getHeight());
        return builder.toString();
    }

    @Override
    public Image getImage() {
        return imageIcon;
    }
}
