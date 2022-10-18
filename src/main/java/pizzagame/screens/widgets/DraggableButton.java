package pizzagame.screens.widgets;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class DraggableButton extends JButton {

    public DraggableButton() {
        DragListener drag = new DragListener();
        addMouseListener(drag);
        addMouseMotionListener(drag);
    }

    private static class DragListener extends MouseInputAdapter {
        private Point location;
        private MouseEvent pressed;

        public void mousePressed(MouseEvent me)
        {
            pressed = me;
        }

        public void mouseDragged(MouseEvent me)
        {
            Component component = me.getComponent();
            location = component.getLocation(location);
            int x = location.x - pressed.getX() + me.getX();
            int y = location.y - pressed.getY() + me.getY();
            component.setLocation(x, y);
        }
    }

}
