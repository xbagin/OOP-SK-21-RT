package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.EventHandler;

import javax.swing.*;
import java.awt.*;

public class App {
    public static final String TREE_CMD = "Tree";
    public static final String MOVE_CMD = "Move";
    public static final String NEXT_COLOR_CMD = "Next color";

    public App() {
        JFrame frame = new JFrame("RT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);

        EventHandler handler = new EventHandler(label);

        MyPanel canvas = new MyPanel(handler);
        label.setBackground(canvas.getColor());

        MyButton treeButton = new MyButton(App.TREE_CMD, handler);
        MyButton moveButton = new MyButton(App.MOVE_CMD, handler);
        MyButton colorButton = new MyButton(App.NEXT_COLOR_CMD, handler, canvas);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
        bottomPanel.add(treeButton);
        bottomPanel.add(moveButton);
        bottomPanel.add(colorButton);
        bottomPanel.add(label);

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
