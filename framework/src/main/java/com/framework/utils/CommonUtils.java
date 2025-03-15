package com.framework.utils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static String createFolder(){
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));

        Path folder = Paths.get(timestamp);
        try {
            Files.createDirectory(folder);
            System.out.println("Folder created: " + folder.getFileName());
        } catch (FileAlreadyExistsException e) {
            System.out.println("Folder already exists: " + folder.getFileName());
        } catch (IOException e) {
            System.out.println("Failed to create folder: " + e.getMessage());
        }
        return timestamp;
    }
}
