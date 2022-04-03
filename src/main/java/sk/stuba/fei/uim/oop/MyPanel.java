package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {
    private final Color[] colors;
    private final List<Tree> trees;
    @Getter @Setter
    private Tree tree;
    @Getter
    private Color color;
    private int colorIndex;

    public MyPanel(UniversalAdapter listener) {
        super();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.colors = new Color[] {Color.RED, Color.BLUE, Color.GREEN};
        this.trees = new ArrayList<>();
        this.colorIndex = 0;
        this.color = this.colors[this.colorIndex];
    }

    public void finishTree(int x2, int y2) {
        this.tree.resize(x2, y2);
        this.trees.add(this.tree);
        this.tree = null;
    }

    public Color nextColor() {
        this.colorIndex = (this.colorIndex + 1) % this.colors.length;
        this.color = this.colors[this.colorIndex];
        return this.color;
    }

    public Tree findMostFrontTreeAt(int x, int y) {
        Tree foundTree = null;
        for (Tree tree : this.trees) {
            if (tree.isInCoordinates(x, y)) {
                foundTree = tree;
            }
        }
        return foundTree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.trees.forEach(tree -> tree.draw(g));
        if (this.tree != null) {
            this.tree.draw(g);
        }
    }
}
