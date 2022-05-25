package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.gui.App;
import sk.stuba.fei.uim.oop.gui.MyButton;
import sk.stuba.fei.uim.oop.gui.MyPanel;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Objects;

public class EventHandler extends UniversalAdapter {
    private final JLabel label;
    private int mouseOriginX;
    private int mouseOriginY;

    public EventHandler(JLabel label) {
        super();
        this.label = label;
        this.mouseOriginX = 0;
        this.mouseOriginY = 0;
        this.label.setText(Modes.DRAWING.name());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (this.commandIs(e, App.TREE_CMD)) {
            this.label.setText(Modes.DRAWING.name());
        }
        if (this.commandIs(e, App.MOVE_CMD)) {
            this.label.setText(Modes.MOVING.name());
        }
        if (this.commandIs(e, App.NEXT_COLOR_CMD)) {
            if(e.getSource() instanceof MyButton) {
                MyButton button = (MyButton) e.getSource();
                if (button.getCanvas() != null) {
                    this.label.setBackground(button.getCanvas().nextColor());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            this.mouseOriginX = e.getX();
            this.mouseOriginY = e.getY();
            if (this.modeIs(Modes.DRAWING)) {
                canvas.setTree(new Tree(e.getX(), e.getY(), canvas.getColor()));
            }
            if (this.modeIs(Modes.MOVING)) {
                //this-.mouseOriginX = e.getX();
                //this.MouseOriginY = e.getY();
                canvas.setTree(canvas.findMostFrontTreeAt(e.getX(), e.getY()));
            }
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.DRAWING)) {
                canvas.finishTree(/*e.getX(), e.getY()*/);
            }
            if (this.modeIs(Modes.MOVING)) {
                canvas.setTree(null);
            }
            canvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.DRAWING)) {
                canvas.getTree().resize(this.mouseOriginX, this.mouseOriginY, e.getX(), e.getY());
            }
            if (this.modeIs(Modes.MOVING)) {
                Tree tree = canvas.getTree();
                if (tree != null) {
                    tree.move(e.getX() - this.mouseOriginX, e.getY() - this.mouseOriginY);
                    this.mouseOriginX = e.getX();
                    this.mouseOriginY = e.getY();
                }
            }
            canvas.repaint();
        }
    }

    private boolean modeIs(Modes mode) {
        //return Objects.equals(this.label.getText(), mode);
        try {
            return Objects.equals(Modes.valueOf(this.label.getText().toUpperCase(Locale.ROOT)), mode);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean commandIs(ActionEvent e, String command) {
        return Objects.equals(e.getActionCommand(), command);
    }
}
