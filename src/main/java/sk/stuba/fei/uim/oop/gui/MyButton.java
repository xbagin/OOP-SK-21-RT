package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    @Getter
    private final MyPanel canvas;

    public MyButton(String text, ActionListener listener) {
        super(text);
        this.addActionListener(listener);
        this.canvas = null;
    }

    public MyButton(String text, ActionListener listener, MyPanel canvas) {
        super(text);
        this.addActionListener(listener);
        this.canvas = canvas;
    }
}
