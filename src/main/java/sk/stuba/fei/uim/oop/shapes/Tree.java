package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tree {
    @Getter
    private int x;
    @Getter
    private int y;
    private final Color color;
    private int width;
    private int height;
    //-private final int originX;
    //private final int originY;

    public Tree(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = 0;
        this.height = 0;
        //this-.originX = x;
        //this.originY = y;
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

    public void resize(int x0, int y0, int x2, int y2) {
        this.x = Math.min(x0/*this.originX*/, x2);
        this.y = Math.min(y0/*this.originY*/, y2);
        this.width = Math.abs(x2 - x0/*this.originX*/);
        this.height = Math.abs(y2 - y0/*this.originY*/);
    }

    public boolean isInCoordinates(int x, int y) {
        /*more accurate below -
        boolean inBoundsX = (this.x <= x) && (this.x + this.width >= x);
        boolean inBoundsY = (this.y <= y) && (this.y + this.height >= y);
        return inBoundsX && inBoundsY;*/
        Rectangle rect = new Rectangle(
                this.x + this.width / 3,
                this.y + this.height / 2,
                this.width / 3,
                this.height / 2);
        Ellipse2D ellipse = new Ellipse2D.Double(this.x, this.y, this.width, this.height * 2 / 3.);
        return rect.contains(x, y) || ellipse.contains(x, y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
