import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
/**
 * A simple implementation of Closest-Pair Divide-and-Conquer algorithm.
 * Created by JJ Lim and Daniel Lim on 10/14/17.
 */
public class ClosestPairDC {
    private ArrayList<Point> ptsByX;
    private ArrayList<Point> ptsByY;

    private ArrayList<Point> leftByX;
    private ArrayList<Point> rightByX;
    private ArrayList<Point> leftByY;
    private ArrayList<Point> rightByY;

    private double minDist;
    private Point cp1;
    private Point cp2;
    private double cpX1;
    private double cpX2;
    private double cpY1;
    private double cpY2;


    /**
     * Constructor takes in a list of n points, (x, y), sorted by x values, and
     * the same list but sorted by y values.
     */
    public ClosestPairDC(ArrayList<Point> ptsByX, ArrayList<Point> ptsByY) {
        this.ptsByX = ptsByX;
        this.ptsByY = ptsByY;

        this.leftByX = new ArrayList<>();
        this.rightByX = new ArrayList<>();
        this.leftByY = new ArrayList<>();
        this.rightByY = new ArrayList<>();
    }

    public void dcClosestPair() {
        //Handle the non-recursive step
        if (ptsByX.size() == 2 || ptsByX.size() == 3) {
            bfClosestPair();
        }

        //Divide points into two equal groups
        int midPos = (int)Math.floor(ptsByX.size()/2);
        Point midptX = ptsByX.get(midPos);
        for (int i = 0; i <= midPos; i++) {
            this.leftByX.add(ptsByX.get(i));
        }
        for (int i = midPos + 1; i < ptsByX.size(); i++) {
            this.rightByX.add(ptsByX.get(i));
        }

        //Now divide y-sorted in linear time (PICK UP HERE)

    }

    private void bfClosestPair() {
        double d = -1;
        double x1 = -1;
        double x2 = -1;
        double y1 = -1;
        double y2 = -1;

        for (int i = 0; i < ptsByX.size(); i++) {
            for (int j = 0; j < ptsByY.size(); j++) {
                double xi = ptsByX.get(i).getX();
                double xj = ptsByX.get(j).getX();
                double yi = ptsByY.get(i).getY();
                double yj = ptsByY.get(j).getY();
                double currPtDist = Math.sqrt(Math.pow(xi - xj, 2) + Math.pow(yi - yj, 2));
                d = Math.min(d, currPtDist);
                if (currPtDist < d) {
                    d = currPtDist;
                    x1 = xi;
                    x2 = xj;
                    y1 = yi;
                    y2 = yj;
                }
            }
        }
        this.minDist = d;
        this.cp1.setLocation(x1, y1);
        this.cp2.setLocation(x2, y2);
    }

    public double getClosestDistance() {
        return -1;
    }
    public double getPoint1X() {
        return -1;
    }
    public double getPoint2X() {
        return -1;
    }
    public double getPoint1Y() {
        return -1;
    }
    public double getPoint2Y() {
        return -1;
    }

}
