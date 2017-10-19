import java.awt.*;

/**
 * Created by Daniel Lim and JJ Lim on 10/17/17.
 */
public class PointPair {
    private double d;
    private Point pt1;
    private Point pt2;

    public PointPair(){

    }

    public PointPair(Point pt1, Point pt2) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.d = dist(pt1, pt2);
    }

    private double dist(Point pt1, Point pt2) {
        double xdist = pt2.getX() - pt1.getX();
        double ydist = pt2.getY() - pt1.getY();
        return Math.sqrt(xdist*xdist + ydist*ydist);
    }

    public void updatePointPair(double d, Point pt1, Point pt2) {
        this.d = d;
        this.pt1 = pt1;
        this.pt2 = pt2;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public Point getPt1() {
        return pt1;
    }

//    public void setPt1(Point pt1) {
//        this.pt1 = pt1;
//    }

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

    //    public void setPt2(Point pt2) {
//        this.pt2 = pt2;
//    }
}
