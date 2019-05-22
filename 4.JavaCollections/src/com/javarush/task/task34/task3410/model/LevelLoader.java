package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {

    private Path levelsPath;

    public LevelLoader(Path levels) {
        this.levelsPath = levels;
    }

    public GameObjects getLevel(int level) {

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(levelsPath.toFile()))) {
                String line = null;
                int countLevels = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Maze:")) {
                        String[] arr = line.split(":");
                        int n = Integer.parseInt(arr[arr.length - 1].trim());
                        if (n > countLevels) {
                            countLevels = n;
                        }
                    }
                }
                if (level != countLevels) {
                    level = level % countLevels;
                }

                Set<Wall> walls = new HashSet<>();
                Set<Box> boxes = new HashSet<>();
                Set<Home> homes = new HashSet<>();
                int column = 0;
                int row = 0;
                Player player = null;
                ReadState state = ReadState.FIND;
                String trimLine = "";
                try (BufferedReader reader1 = new BufferedReader(new FileReader(levelsPath.toFile()))) {
                    while ((line = reader1.readLine()) != null) {
                        switch (state) {
                            case FIND:
                                if (line.contains("Maze:")) {
                                    String[] arr = line.split(":");
                                    int n = Integer.parseInt(arr[arr.length - 1].trim());
                                    if (n == level) {
                                        state = ReadState.LEVEL;
                                    }
                                }
                                break;
                            case LEVEL:
                                trimLine = line.trim();
                                if (trimLine.isEmpty()) {
                                    state = ReadState.MAZE;
                                }
                                break;
                            case MAZE:
                                trimLine = line.trim();
                                if (trimLine.isEmpty()) {
                                    state = ReadState.FIND_END;
                                    break;
                                } else {
                                    column = 0;
                                    for (int i = 0; i < line.length(); i++) {
                                        char c = line.charAt(i);
                                        int x = getX(column);
                                        int y = getY(row);
                                        switch (c) {
                                            case 'X':
                                                Wall wall = new Wall(x, y);
                                                walls.add(wall);
                                                break;
                                            case '*':
                                                Box box = new Box(x, y);
                                                boxes.add(box);
                                                break;
                                            case '.':
                                                Home home = new Home(x, y);
                                                homes.add(home);
                                                break;
                                            case '&':
                                                Box box1 = new Box(x, y);
                                                Home home1 = new Home(x, y);
                                                boxes.add(box1);
                                                homes.add(home1);
                                                break;
                                            case '@':
                                                player = new Player(x, y);
                                                break;
                                        }
                                        column++;
                                    }
                                    row++;
                                }
                        }
                    }
                    GameObjects obj = new GameObjects(walls, boxes, homes, player);
                    return obj;
                }
            }
        } catch (IOException e) {
            System.out.println("levels file not found");
            throw new Error("System error");
        }

        /*
        Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE*2));
        walls.add(new Wall(Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE*3));
        walls.add(new Wall(Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE*4));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(Model.FIELD_CELL_SIZE*4, Model.FIELD_CELL_SIZE));
        boxes.add(new Box(Model.FIELD_CELL_SIZE*5, Model.FIELD_CELL_SIZE));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE));
        Player p = new Player(Model.FIELD_CELL_SIZE*2, Model.FIELD_CELL_SIZE);
        GameObjects obj = new GameObjects(walls, boxes, homes, p);
        return obj;

         */
    }

    private int getX(int column) {
        return Model.FIELD_CELL_SIZE/2 + column*Model.FIELD_CELL_SIZE;
    }

    private int getY(int row) {
        return Model.FIELD_CELL_SIZE/2 + row*Model.FIELD_CELL_SIZE;
    }

    private enum ReadState {
        FIND, LEVEL, MAZE, FIND_END
    }


}
