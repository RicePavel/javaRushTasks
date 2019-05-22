package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    Path path;

    public FileBucket()  {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {

        }
    }

    public long getFileSize() throws IOException {
        return Files.size(path);
    }

    public void putEntry(Entry entry) throws IOException  {
        ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
        out.writeObject(entry);
    }

    public Entry getEntry() throws IOException, ClassNotFoundException {
            if (getFileSize() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
                    return (Entry) in.readObject();
                }
            } else {
                return null;
            }
    }

    public void remove() throws IOException {
        Files.delete(path);
    }

}
