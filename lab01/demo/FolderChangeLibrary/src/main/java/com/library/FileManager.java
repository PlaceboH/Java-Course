package com.library;
import java.nio.file.Path;
import java.util.Objects;

public class FileManager {

    private String fileHash;
    private String fileName;
    private Path filePath;

    public FileManager(String fileHash, String fileName, Path filePath) {
        this.fileHash = fileHash;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    public String getHash() {
        return fileHash;
    }
    public Path getPath(){
        return filePath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileHash,fileName,filePath);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass()!=obj.getClass())return false;
        FileManager otherObject=(FileManager) obj;
        boolean isSameFileManager= fileHash.equals(otherObject.fileHash);
        return isSameFileManager;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n ===File Info==");
        builder.append("\nFile Name: " + fileName);
        builder.append("\nFile Hash: " + fileHash);
        builder.append("\nFile Path: " +filePath);
        builder.append("\n ===End===");
        return builder.toString();
    }

}
