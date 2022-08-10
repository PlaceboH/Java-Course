package com.example.demo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DirController {
    final String dataFileName = "record.txt";
    final private Path path = Paths.get("/Users/placebo/Desktop/lab02/UserData");;

    WeakHashMap<String ,UserData> catalogsData = new WeakHashMap<>();
    List<Path> dirNames = new LinkedList<>();

    DirController(){
        super();
        collectFolders();
    }


    public List<Path> getDirNames() {
        return dirNames;
    }

    private void checkData(String dir){

        if(catalogsData.get(dir) != null){
            if(catalogsData.get(dir).getUserData() != null){
                return;
            }
        }
        collectData(dir);
    }

    public void showCatalog(){
        for (String element : catalogsData.keySet()) {
            System.out.println(element);
            catalogsData.get(element).showWeakHashMap();
        }
    }

    public FolderInterface getUserFileData(String usr){
        checkData(usr);
        return catalogsData.get(usr).getUserData().get(dataFileName);
    }
    public FolderInterface getUserImgData(String usr){
        checkData(usr);
        UserData concretUser = catalogsData.get(usr);
        for (String element : concretUser.getUserData().keySet()){
            if(concretUser.getUserData().get(element).getImage() != null){
                return catalogsData.get(usr).getUserData().get(element);
            }
        }
        return null;
    }

    private void collectData(String dir){
        UserData userData = new UserData();
        File files = new  File(this.path.toAbsolutePath().toString() + "/" + dir);
        for(File f : files.listFiles() ){
            if (f.getName().equals(dataFileName)) {
                userData.addFileUserData(f.getName(), Paths.get(f.getPath()));
            }
            else{
                userData.addImgUserData(f.getName(), Paths.get(f.getPath()));
            }
        }
        catalogsData.put(dir, userData);
    }

    private void collectFolders() {
        File[] directories = new File(this.path.toAbsolutePath().toString()).listFiles(File::isDirectory);
        for( File d : directories) {
            dirNames.add(Paths.get(d.getName()));
        }

    }
}
