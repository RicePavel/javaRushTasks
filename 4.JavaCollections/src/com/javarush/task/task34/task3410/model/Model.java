package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Model {

    private EventListener eventListener;

    private GameObjects gameObjects;

    private int currentLevel = 1;

    private LevelLoader levelLoader = new LevelLoader(Paths.get(".\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public static final int FIELD_CELL_SIZE = 20;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void move(Direction direction) {
        Player player = null;
        for (GameObject obj: gameObjects.getAll()) {
            if (obj instanceof Player) {
                player = (Player) obj;
                break;
            }
        }
        if (player != null) {
            if (checkWallCollision(player, direction)) {
                return;
            }
            if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
                return;
            } else {
                moveObject(player, direction);
                checkCompletion();
            }
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<GameObject> set = gameObjects.getAll();
        for (GameObject obj: set) {
            if (obj instanceof Wall && gameObject.isCollision(obj, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = null;
        Set<GameObject> set = gameObjects.getAll();
        for (GameObject obj: set) {
            if (obj instanceof Player) {
                player = (Player) obj;
                break;
            }
        }
        if (player == null) {
            return true;
        }
        // ищем ящик, который находится в том направлении
        Box box = null;
        List<Box> otherBoxes = new ArrayList<>();
        for (GameObject obj: set) {
            if (obj instanceof Box) {
                if (player.isCollision(obj, direction)) {
                    box = (Box) obj;
                } else {
                    otherBoxes.add((Box) obj);
                }
            }
        }
        // если ящика нет, то игрок может быть сдвинут
        if (box == null) {
            return false;
        }
        // если за ящиком стена, не может быть сдвинут
        if (checkWallCollision(box, direction)) {
            return true;
        }
        // если за ящик4ом другой ящик, не может быть сдвинут
        for (Box otherBox: otherBoxes) {
            if (box.isCollision(otherBox, direction)) {
                return true;
            }
        }
        // если все ок, сдвигаем ящик
        moveObject(box, direction);
        return false;
    }

    private void moveObject(Movable movable, Direction direction) {
        int x = 0;
        int y = 0;
        switch (direction) {
            case LEFT:
                x = -Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                x = Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                y = Model.FIELD_CELL_SIZE;
                break;
            case UP:
                y = -Model.FIELD_CELL_SIZE;
                break;
        }
        movable.move(x, y);
    }

    public void checkCompletion() {
        List<Home> homes = new ArrayList<>();
        List<Box> boxes = new ArrayList<>();
        for (GameObject obj: gameObjects.getAll()) {
            if (obj instanceof Home) {
                homes.add((Home) obj);
            } else if (obj instanceof  Box) {
                boxes.add((Box) obj);
            }
        }
        if (homes.size() > 0 && boxes.size() > 0) {
            for (Home home: homes) {
                boolean worthBox = false;
                for (Box box: boxes) {
                    if (home.getX() == box.getX() && home.getY() == box.getY()) {
                        worthBox = true;
                        break;
                    }
                }
                if (!worthBox) {
                    return;
                }
            }
            eventListener.levelCompleted(currentLevel);
        }
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

}
