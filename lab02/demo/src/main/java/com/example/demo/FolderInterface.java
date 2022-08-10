package com.example.demo;

import java.nio.file.Path;
import javafx.scene.image.Image;

public interface FolderInterface {
    public String getType();
    public Path getPath();
    public void loadFromPath();
    public String showContent();
    public String toString();
    public Image getImage();
}
