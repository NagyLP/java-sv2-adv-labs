package Nesting;

import javax.persistence.Entity;

@Entity
public class NestingBox extends Nest{

    private int width;

    private int height;

    public NestingBox() {
    }

    public NestingBox(int height) {
        this.height = height;
    }

    public NestingBox(int numberOfEggs, int width, int height) {
        super(numberOfEggs);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
