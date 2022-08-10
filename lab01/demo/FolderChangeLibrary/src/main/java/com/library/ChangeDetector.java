package com.library;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;


public class ChangeDetector {
    private Path filePath;
    private Set<FileManager> prevSnapshot;

    public ChangeDetector(Path filePath) throws IOException {
        this.filePath = filePath;
        prevSnapshot = makeSnapshot();
    }
    public void changeFolderPath(Path filePath) throws IOException {
        this.filePath = filePath;
        prevSnapshot = makeSnapshot();
    }
    public Set<FileManager> getSnapshot() {
         return prevSnapshot;
    }

    public Map<ChangeType, List<FileManager>> detectChanges() throws IOException {

        Set<FileManager> newSnapshot = makeSnapshot();
        Map<ChangeType, List<FileManager>> stateOfFolderMap = new HashMap<>();
        stateOfFolderMap.put(ChangeType.NoChanged, new ArrayList<>());
        stateOfFolderMap.put(ChangeType.Changed, new ArrayList<>());
        stateOfFolderMap.put(ChangeType.Created, new ArrayList<>());
        stateOfFolderMap.put(ChangeType.Deleted, new ArrayList<>());

        for (FileManager newSnapshotFile : newSnapshot) {
//            System.out.println(prevSnapshot.contains(newSnapshotFile) );
            if (!prevSnapshot.contains(newSnapshotFile)){
                boolean fileChanged = prevSnapshot.stream().map(FileManager::getPath).anyMatch(p -> p.equals(newSnapshotFile.getPath()));
                if (fileChanged) {
                    stateOfFolderMap.get(ChangeType.Changed).add(newSnapshotFile);
                } else {
                    stateOfFolderMap.get(ChangeType.Created).add(newSnapshotFile);
                }
            }
            else{
                stateOfFolderMap.get(ChangeType.NoChanged).add(newSnapshotFile);
            }
        }
        for (FileManager prevSnapshotFile : prevSnapshot) {
            if( ! newSnapshot.contains(prevSnapshotFile) ){
                if (newSnapshot.stream().map(FileManager::getPath).noneMatch(p -> p.equals(prevSnapshotFile.getPath())))
                    stateOfFolderMap.get(ChangeType.Deleted).add(prevSnapshotFile);
            }
        }


        prevSnapshot = newSnapshot;
        return stateOfFolderMap;
    }


    private Set<FileManager> makeSnapshot() throws IOException {
        Set<FileManager> newSnapshot = new HashSet<>();
        Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    FileManager fileManager = new FileManager(createMD5HashString(file), file.getFileName().toString(), file);
                    newSnapshot.add(fileManager);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return newSnapshot;
    }



    private static String createMD5HashString(Path path) throws NoSuchAlgorithmException, IOException {
        MessageDigest MD5 = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(path); DigestInputStream dis = new DigestInputStream(is, MD5)) {
            int counterNum = 0;
            // Tu wczytujemy z plików dane po strofie o długości stringSize i updateujemy MD5. W końcu pobieram Z MD5 tablice bajtów
            final int stringSize = 1024;
            byte[] buffer = new byte[stringSize];

            do {
                counterNum = dis.read(buffer);
                if (counterNum > 0) {
                    MD5.update(buffer, 0, counterNum);
                }
            } while (counterNum != -1);
        }
        byte[] bytes = MD5.digest();

        // Tu zapisujemy końcowe dane w stringu, w formie 2 chara to liczba hex
        StringBuilder md5HashString = new StringBuilder();
        for (byte bajtek : bytes) {
            int dec = (int) bajtek & 0xff;

            String hex = Integer.toHexString(dec);
            if (hex.length() % 2 == 1) {
                hex = "0" + hex;
            }
            md5HashString.append(hex);
        }
        return md5HashString.toString();
    }

}
