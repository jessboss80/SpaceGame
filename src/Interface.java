
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class Interface extends JFrame {
    private JSplitPane splitPaneH;
    private JPanel menu;
    private SpaceCanvas canvas;
    private ButtonGroup types;
    private Point startPt;
    private Point endPt;
    private SpaceMass activeMass;

    public Interface() {
        setTitle("Space Simulation");
        setBackground(Color.gray);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        menu = new JPanel();
        menu.setLayout(new FlowLayout());
        JRadioButton starButton = new JRadioButton("Star");
        starButton.setBackground(new Color(15, 15, 75));
        starButton.setForeground(Color.WHITE);
        JRadioButton planetButton = new JRadioButton("Planet");
        planetButton.setBackground(new Color(15, 15, 75));
        planetButton.setForeground(Color.WHITE);
        JRadioButton moonButton = new JRadioButton("Moon");
        moonButton.setBackground(new Color(15, 15, 75));
        moonButton.setForeground(Color.WHITE);

        JLabel star = new JLabel("", new ImageIcon("res/sun.png"), JLabel.CENTER);
        JLabel planet = new JLabel("", new ImageIcon("res/greenearth.png"), JLabel.CENTER);
        JLabel moon = new JLabel("", new ImageIcon("res/redmoon.png"), JLabel.CENTER);

        JButton resetButton = new JButton("Reset Galaxy");
        resetButton.setBackground(new Color(50, 15, 75));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> canvas.clearGalaxy());

        menu.add(starButton);
        menu.add(star);
        menu.add(Box.createHorizontalStrut(100));
        menu.add(planetButton);
        menu.add(planet);
        menu.add(Box.createHorizontalStrut(100));
        menu.add(moonButton);
        menu.add(moon);
        menu.setBackground(new Color(15, 15, 75));
        menu.add(Box.createHorizontalStrut(50));
        menu.add(resetButton);

        types = new ButtonGroup();
        types.add(starButton);
        types.add(planetButton);
        types.add(moonButton);
        starButton.doClick();

        Input in = new Input();
        canvas = new SpaceCanvas();
        canvas.addMouseListener(in);
        canvas.addMouseMotionListener(in);
        canvas.addComponentListener(in);

        splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        topPanel.add(splitPaneH, BorderLayout.CENTER);
        splitPaneH.setEnabled(false);
        splitPaneH.setDividerSize(0);

        splitPaneH.setLeftComponent(menu);
        splitPaneH.setRightComponent(canvas);

        this.pack();
        this.setVisible(true);

    }

    private String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = types.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public void render() {
        canvas.repaint();
    }

    public void updateFps(int fps) {
        canvas.updateFps(fps);
    }

    public void update() {
        canvas.calcSpaceMassPositions();
    }

    private int[] calcSpeed(Point from, Point to) {
        int[] speed = new int[2];
        speed[0] = (int) (from.getX() - to.getX()) * 100;
        speed[1] = (int) (from.getY() - to.getY()) * 100;

        return speed;
    }


    private class Input implements MouseListener, MouseMotionListener, ComponentListener {

        public void mouseClicked(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            String type = getSelectedButtonText();
            startPt = e.getPoint();

            if (type == null) {
                return;
            }
            if (type.equals("Planet")) {
                Planet planet = new Planet(e.getPoint());
                activeMass = planet;
                canvas.addItem(planet);
            }
            if (type.equals("Star")) {
                Star star = new Star(e.getPoint());
                activeMass = star;
                canvas.addItem(star);
            }

            if (type.equals("Moon")) {
                Moon moon = new Moon(e.getPoint());
                activeMass = moon;
                canvas.addItem(moon);
            }

            canvas.setStartPt(e.getPoint());
        }

        public void mouseReleased(MouseEvent e) {
            endPt = e.getPoint();

            int[] speed = calcSpeed(startPt, endPt);

            if (Math.abs(speed[0]) < 1 && Math.abs(speed[1]) < 1) {
                canvas.deleteItem(activeMass);
            } else {
                activeMass.setSpeed(calcSpeed(startPt, endPt));
                activeMass.activate();
            }

            canvas.clearLine();
            activeMass = null;
            endPt = null;
            startPt = null;
        }

        public void mouseDragged(MouseEvent e) {
            canvas.setEndPt(e.getPoint());
        }

        public void mouseMoved(MouseEvent e) {}

        public void componentHidden(ComponentEvent e) {}

        public void componentMoved(ComponentEvent e) {}

        public void componentResized(ComponentEvent e) {
            canvas.relocateGalaxy(canvas.getWidth(), canvas.getHeight());
        }

        public void componentShown(ComponentEvent e) {}
    }

}

