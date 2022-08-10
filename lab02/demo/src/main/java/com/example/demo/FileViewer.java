package com.example.demo;

import javafx.scene.image.Image;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileViewer implements FolderInterface {

    String content;
    Path path;

    public FileViewer(Path path) {
        this.path = path;
        this.loadFromPath();
    }

    @Override
    public String getType() {
        return path.getFileName().toString().split(".")[1];
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void loadFromPath() {
        try {
            Scanner in = new Scanner(new FileReader(this.path.toString()));
            StringBuilder sb = new StringBuilder();
            while(in.hasNext()) {
                sb.append(in.next() + "\n");
            }
            in.close();
            this.content = sb.toString();
        } catch (IOException e) {
            System.out.println("bad path");
            e.printStackTrace();
        }
    }

    @Override
    public String showContent() {
        return content;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public Image getImage() {
        return null;
    }

}
