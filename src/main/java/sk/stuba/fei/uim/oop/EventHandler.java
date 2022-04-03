package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class EventHandler extends UniversalAdapter {
    private final JLabel label;
    private int mouseOriginX;
    private int MouseOriginY;

    public EventHandler(JLabel label) {
        super();
        this.label = label;
        this.mouseOriginX = 0;
        this.MouseOriginY = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (this.commandIs(e, "Tree")) {
            this.label.setText("DRAWING");
        }
        if (this.commandIs(e, "Move")) {
            this.label.setText("MOVING");
        }
        if (this.commandIs(e, "Next color")) {
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
            if (this.modeIs("DRAWING")) {
                canvas.setTree(new Tree(e.getX(), e.getY(), canvas.getColor()));
            }
            if (this.modeIs("MOVING")) {
                this.mouseOriginX = e.getX();
                this.MouseOriginY = e.getY();
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
            if (this.modeIs("DRAWING")) {
                canvas.finishTree(e.getX(), e.getY());
            }
            if (this.modeIs("MOVING")) {
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
            if (this.modeIs("DRAWING")) {
                canvas.getTree().resize(e.getX(), e.getY());
            }
            if (this.modeIs("MOVING")) {
                Tree tree = canvas.getTree();
                if (tree != null) {
                    tree.move(e.getX() - this.mouseOriginX, e.getY() - this.MouseOriginY);
                    this.mouseOriginX = e.getX();
                    this.MouseOriginY = e.getY();
                }
            }
            canvas.repaint();
        }
    }

    private boolean modeIs(String mode) {
        return Objects.equals(this.label.getText(), mode);
    }

    private boolean commandIs(ActionEvent e, String command) {
        return Objects.equals(e.getActionCommand(), command);
    }
}
