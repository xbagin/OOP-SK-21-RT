package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.shapes.Colors;
import sk.stuba.fei.uim.oop.shapes.Tree;
import sk.stuba.fei.uim.oop.logic.UniversalAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {
    private final List<Tree> trees;
    @Getter @Setter
    private Tree tree;
    private Colors rotateColor;

    public MyPanel(UniversalAdapter listener) {
        super();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.setBackground(Color.LIGHT_GRAY);
        this.trees = new ArrayList<>();
        this.tree = null;
        this.rotateColor = Colors.FIRST;
    }

    public void finishTree(/*int x2, int y2*/) {
        //this.tree.resize(x2, y2);
        this.trees.add(this.tree);
        this.tree = null;
    }

    public Color nextColor() {
        this.rotateColor = this.rotateColor.next();
        return this.rotateColor.getColor();
    }

    public Color getColor() {
        return this.rotateColor.getColor();
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
        if (this.tree != null && !this.trees.contains(this.tree)) {
            this.tree.draw(g);
        }
    }
}
