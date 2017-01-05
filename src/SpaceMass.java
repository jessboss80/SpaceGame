import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class SpaceMass {
    protected Point location;
    protected BufferedImage image;
    protected int mass;
    protected int[] speed;
    protected boolean active;
    protected boolean isBlackhole;
    protected int maxDistance;
    protected int[] relativeDistance;

    public void draw(Graphics g) {
        g.drawImage(image, location.x - (image.getWidth() / 2), location.y - (image.getHeight() / 2), null);
    }

    public void move(SpaceMass other) {
        location.x += speed[0] / 2000;
        location.y += speed[1] / 2000;

        relativeDistance= calcXYdistance(other);
    }

    public void activate() {
        active = true;
    }

    public void setSpeed(int[] newSpeed) {
        speed = newSpeed;
    }

    public boolean isActive() {
        return active;
    }

    public Point getLocation() {
        return location;
    }

    public void setRelativeLocation(SpaceMass other){
        location.x = other.getLocation().x + relativeDistance[0];
        location.y = other.getLocation().y + relativeDistance[1];
    }


    public void setLocation(Point newLoc){
        location.x = newLoc.x;
        location.y = newLoc.y;
    }

    public void calcForce(SpaceMass other) {
        int[] xyForce = calcXYdistance(other);
        int distance = distance(other);

        if (distance == 0 || distance > other.getMaxDistance()) {
            return;
        }

        if (mass > other.getMass()) {
            other.speed[0] += mass * xyForce[0];
            other.speed[1] += mass * xyForce[1];
        }
    }

    private int[] calcXYdistance(SpaceMass other){
        int[] xyDistance = new int[2];

        xyDistance[0] = (int) (location.getX() - other.getLocation().getX());
        xyDistance[1] = (int) (location.getY() - other.getLocation().getY());

        return xyDistance;
    }

    private int distance(SpaceMass other) {
        return (int) Math.sqrt(Math.pow(location.getX() - other.getLocation().getX(), 2) +
                Math.pow(location.getY() - other.getLocation().getY(), 2));
    }

    public boolean isBlackhole() {
        return isBlackhole;
    }

    public int getMass() {
        return mass;
    }

    public int getMaxDistance() {
        return maxDistance;
    }


}
