package com.javarush.task.task34.task3410.model;

import java.awt.*;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        Rectangle current = new Rectangle();
        Rectangle other = new Rectangle();
        current.x1 = x - width/2;
        current.x2 = x + width/2;
        current.y1 = y - height/2;
        current.y2 = y + height/2;

        other.x1 = gameObject.getX() - gameObject.getWidth()/2;
        other.x2 = gameObject.getX() + gameObject.getWidth()/2;
        other.y1 = gameObject.getY() - gameObject.getHeight()/2;
        other.y2 = gameObject.getY() + gameObject.getHeight()/2;

        switch (direction) {
            case RIGHT:
                current.x1 += Model.FIELD_CELL_SIZE;
                current.x2 += Model.FIELD_CELL_SIZE;
                break;
            case LEFT:
                current.x1 -= Model.FIELD_CELL_SIZE;
                current.x2 -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                current.y1 += Model.FIELD_CELL_SIZE;
                current.y2 += Model.FIELD_CELL_SIZE;
                break;
            case UP:
                current.y1 -= Model.FIELD_CELL_SIZE;
                current.y2 -= Model.FIELD_CELL_SIZE;
                break;
        }
        return !notIntersect(current, other);
    }

    private boolean notIntersect(Rectangle a, Rectangle b) {
        return ( a.y2 <= b.y1 || a.y1 >= b.y2 ||  a.x2 <= b.x1 || a.x1 >= b.x2 );
    }

    private static class Rectangle {
        int x1;
        int x2;
        int y1;
        int y2;
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
