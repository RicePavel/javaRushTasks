package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = 0;
    private int maxSize = 0;

    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (partOfName != null || partOfContent != null || minSize != 0 || maxSize != 0) {
            byte[] content = Files.readAllBytes(file); // размер файла: content.length
            boolean conditionPartOfName =  partOfName == null || (partOfName != null && file.getFileName().toString().indexOf(partOfName) != -1);
            boolean conditionPartOfContent = partOfContent == null || (partOfContent != null && new String(content).indexOf(partOfContent) != -1);
            boolean conditionMinSize = minSize == 0 || (minSize != 0 && content.length > minSize);
            boolean conditionSize = maxSize == 0 || (maxSize != 0 && content.length < maxSize);
            if (conditionPartOfName && conditionPartOfContent && conditionMinSize && conditionSize) {
                foundFiles.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
        /*
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean containsPartOfName =  (partOfName != null && file.getFileName().toString().indexOf(partOfName) != -1);
        boolean containsPartOfContent = (partOfContent != null && new String(content).indexOf(partOfContent) != -1);
        boolean largerMinSize = (minSize != 0 && content.length > minSize);
        boolean lessmaxSize = (maxSize != 0 && content.length < maxSize);
        if (containsPartOfName || containsPartOfContent || largerMinSize || lessmaxSize) {
            foundFiles.add(file);
        }
        return FileVisitResult.CONTINUE;
         */
        //return super.visitFile(file, attrs);
    }
}
