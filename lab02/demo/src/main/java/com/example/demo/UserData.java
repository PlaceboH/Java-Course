package com.example.demo;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.WeakHashMap;

public class UserData{
    private WeakHashMap<String, FolderInterface > userData;


    public UserData(){
        super();
        this.userData = new WeakHashMap<>();
    }

    public void showWeakHashMap() {
        for (String element : userData.keySet()) {
            System.out.println( userData.get(element).toString());
        }
    }

    public WeakHashMap<String, FolderInterface> getUserData() {
        return userData;
    }

    public void addFileUserData(String name, Path path) {

        if (Files.exists(path)) {
            FileViewer textFile = new FileViewer(path);
            userData.put(name, textFile);
        } else {
            System.out.println("plik nie istnieje!");
        }

    }

    public void addImgUserData(String name, Path path) {
        if (Files.exists(path)) {
            ImageViewer imageView = new ImageViewer(path);
            userData.put(name, imageView);
        } else {
            System.out.println("plik nie istnieje!");
        }
    }



}
