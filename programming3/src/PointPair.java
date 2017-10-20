import java.awt.*;

/**
 * Created by Daniel Lim and JJ Lim on 10/17/17.
 */
public class PointPair {
    private double d;
    private Point pt1;
    private Point pt2;

    /**
     * Constructor for PointPair class. Takes in two points and stores them as well as their distance.
     * @param pt1 a point
     * @param pt2 another point
     */
    public PointPair(Point pt1, Point pt2) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.d = dist(pt1, pt2);
    }

    /**
     * Calculates the distance between two points.
     * @param pt1 a point
     * @param pt2 another points
     * @return the distance between two points
     */
    private double dist(Point pt1, Point pt2) {
        double xdist = pt2.getX() - pt1.getX();
        double ydist = pt2.getY() - pt1.getY();
        return Math.sqrt(xdist*xdist + ydist*ydist);
    }

    /**
     * Takes in distance, point1, point2 inputs to update PointPair.
     * @param d distance
     * @param pt1 point1
     * @param pt2 point2
     */
    public void updatePointPair(double d, Point pt1, Point pt2) {
        this.d = d;
        this.pt1 = pt1;
        this.pt2 = pt2;
    }

    /**
     * Getter for the distance of PointPair
     * @return the distance of the PointPair
     */
    public double getD() {
        return d;
    }

    /**
     * Setter for the distance of PointPair
     * @param d the new distance of the PointPair
     */
    public void setD(double d) {
        this.d = d;
    }

    /**
     * Getter for Point1
     * @return Point1
     */
    public Point getPt1() {
        return pt1;
    }

    /**
     * Getter for Point2
     * @return Point2
     */
    public Point getPt2() {
        return pt2;
    }

    @Override
    public String toString() {
        return "PointPair{" +
                "Distance=" + d +
                ", Point1=" + pt1 +
                ", Point2=" + pt2 +
                '}';
    }
}
