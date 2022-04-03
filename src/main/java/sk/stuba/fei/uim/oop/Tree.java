package sk.stuba.fei.uim.oop;

import lombok.Getter;

import java.awt.*;

public class Tree {
    @Getter
    private int x;
    @Getter
    private int y;
    private final Color color;
    private int width;
    private int height;
    private final int originX;
    private final int originY;

    public Tree(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = 0;
        this.height = 0;
        this.originX = x;
        this.originY = y;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(this.color);
        g.fillRect(
                this.x + this.width / 3,
                this.y + this.height / 3,
                this.width / 3,
                this.height * 2 / 3
        );
        g.fillOval(this.x, this.y, this.width, this.height * 2 / 3);
        g.setColor(c);
    }

    public void resize(int x2, int y2) {
        this.x = Math.min(this.originX, x2);
        this.y = Math.min(this.originY, y2);
        this.width = Math.abs(x2 - this.originX);
        this.height = Math.abs(y2 - this.originY);
    }

    public boolean isInCoordinates(int x, int y) {
        boolean inBoundsX = (this.x <= x) && (this.x + this.width >= x);
        boolean inBoundsY = (this.y <= y) && (this.y + this.height >= y);
        return inBoundsX && inBoundsY;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
