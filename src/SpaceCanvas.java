import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SpaceCanvas extends JPanel {

    private Point startPt;
    private Point endPt;
    private ArrayList<SpaceMass> spaceMasses;
    private int fps;
    private Point center;
    private Point lowerRight;

    public SpaceCanvas() {
        startPt = null;
        endPt = null;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(15, 15, 75));
        this.spaceMasses = new ArrayList();
        this.setVisible(true);

        center = new Point(395, 242);
        lowerRight = new Point(25, 475);

        spaceMasses.add(new Blackhole(center));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        for(SpaceMass item: spaceMasses){
            item.draw(g);
        }

        if(startPt != null && endPt != null) {
            g.drawLine(startPt.x, startPt.y, endPt.x, endPt.y);
        }

        g.drawString("FPS: "+ fps, lowerRight.x, lowerRight.y);
    }

    public void updateFps(int newFps){
        fps = newFps;
    }

    /* Calculates the new position of each SpaceMass.
     * The Blackhole will never move.
     */
    public void calcSpaceMassPositions(){
        for(SpaceMass activeMass: spaceMasses){
            if (activeMass.isActive()) {
                for(SpaceMass otherMass: spaceMasses) {
                    if(!otherMass.isBlackhole() && otherMass.isActive()) {
                        activeMass.calcForce(otherMass);
                    }
                }
                activeMass.move(spaceMasses.get(0));
            }
        }
    }

    public void setStartPt(Point point){
        startPt = point;
    }

    public void setEndPt(Point point){
        endPt = point;
    }

    public void clearLine(){
        startPt = null;
        endPt = null;
        repaint();
    }

    public void addItem(SpaceMass item){ spaceMasses.add(item); }

    public void deleteItem(SpaceMass item){
        spaceMasses.remove(item);
    }

    public void clearGalaxy(){
        spaceMasses.clear();
        spaceMasses.add(new Blackhole(center));
    }

    public void relocateGalaxy(int width, int height){
        center.x = width/2;
        center.y = height/2;

        spaceMasses.get(0).setLocation(center);
        lowerRight.y = height - 25;

        for (SpaceMass spaceMass: spaceMasses){
            spaceMass.setRelativeLocation(spaceMasses.get(0));
        }
    }

}
