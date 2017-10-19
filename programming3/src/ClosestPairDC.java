import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
/**
 * A simple implementation of Closest-Pair Divide-and-Conquer algorithm.
 * Created by JJ Lim and Daniel Lim on 10/14/17.
 */
public class ClosestPairDC {
    private PointPair recClosestPair;

    /**
     * Constructor takes in a list of n points, (x, y), sorted by x values, and
     * the same list but sorted by y values.
     */
    public ClosestPairDC() {}

    /**
     *
     * @param ptsByX
     * @param ptsByY
     * @return
     */
    public PointPair dcClosestPair(ArrayList<Point> ptsByX, ArrayList<Point> ptsByY) {
        //Handle the non-recursive step
        if (ptsByX.size() == 2 || ptsByX.size() == 3) {
            return bfClosestPair(ptsByX);
        }

        ArrayList<Point> leftByX = new ArrayList<>();
        ArrayList<Point> rightByX = new ArrayList<>();
        ArrayList<Point> leftByY = new ArrayList<>();
        ArrayList<Point> rightByY = new ArrayList<>();

        //Divide points into two equal groups
        int midPos = (int)Math.floor(ptsByX.size()/2);

        for (int i = 0; i < midPos; i++) {
            leftByX.add(ptsByX.get(i));
        }
        for (int i = midPos; i < ptsByX.size(); i++) {
            rightByX.add(ptsByX.get(i));
        }

        //Now divide y-sorted in linear time
        Point midpt = ptsByX.get(midPos);
        for (int i = 0; i < ptsByY.size(); i++) {
            if (ptsByY.get(i).getX() < midpt.getX()
                    || (ptsByY.get(i).getX() == midpt.getX() && ptsByY.get(i).getY() <= midpt.getY())) {
                leftByY.add(ptsByY.get(i));
            }
            else {
                rightByY.add(ptsByY.get(i));
            }
        }

        //Part2: Recurse on subproblems
        PointPair pointPair1 = dcClosestPair(leftByX, leftByY);
        PointPair pointPair2 = dcClosestPair(rightByX, rightByY);

        if (pointPair1.getD() < pointPair2.getD()) {
            recClosestPair = pointPair1;
        }
        else {
            recClosestPair = pointPair2;
        }

        //Part3: Build y-sorted sets of points close to midpoint
        ArrayList<Point> leftClose = new ArrayList<>();
        for (int i = 0; i < leftByY.size(); i++) {
            if (Math.abs(leftByY.get(i).getX() - midpt.getX()) < recClosestPair.getD()) {
                leftClose.add(leftByY.get(i));
            }
        }

        ArrayList<Point> rightClose = new ArrayList<>();
        for (int i = 0; i < rightByY.size(); i++) {
            if (Math.abs(rightByY.get(i).getX() - midpt.getX()) < recClosestPair.getD()) {
                rightClose.add(rightByY.get(i));
            }
        }

        //Part4: Do final comparisons, climbing up leftClose and rightClose
        int jLow = 0;
        int i = 0;
        int j = 0;

        while(i < leftClose.size() && j < rightClose.size()) {
            //use brute-force algorithm when there are less than 3 points
            if (leftClose.size() + rightClose.size() <= 3) {
                ArrayList<Point> leftRightClose = new ArrayList<Point>();
                leftRightClose.addAll(leftClose);
                leftRightClose.addAll(rightClose);
                return bfClosestPair(leftRightClose);
            }

            if (j > rightClose.size() || (leftClose.get(i).getY() + recClosestPair.getD() < rightClose.get(j).getY())) {
                //out of points on right, or next is too fat way, done with leftClose.get(i)
                i++;
                j = jLow;
            }
            else if (leftClose.get(i).getY() > rightClose.get(j).getY() + recClosestPair.getD()) {
                //left point too high above right point; go to next right point,
                //and don't consider points below it in future
                jLow++;
                j = jLow;
            }
            else {
                //compare current i and j points
                PointPair currPair = new PointPair(leftClose.get(i), rightClose.get(j));
                if (currPair.getD() < recClosestPair.getD()) {
                    //we've found a new closest pair
                    recClosestPair.updatePointPair(currPair.getD(), currPair.getPt1(), currPair.getPt2());
                }
                j++;
            }
        }

        return recClosestPair;
    }

    /**
     * Brute-force implementation of Closest Pair search algorithm
     * @param points a set of points to be examined
     * @return PointPair containing distance, point coordinates of the closest pair
     */
    private PointPair bfClosestPair(ArrayList<Point> points) {
        PointPair pointPair = new PointPair(points.get(0), points.get(1));

        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                PointPair currPair = new PointPair(points.get(i), points.get(j));
                if (currPair.getD() < pointPair.getD()) {
                    pointPair.updatePointPair(currPair.getD(), currPair.getPt1(), currPair.getPt2());
                }
            }
        }

        return pointPair;
    }

    @Override
    public String toString() {
        return "ClosestPairDC{" +
                "Closest Pair Point1" + recClosestPair.getPt1() +
                "Closest Pair Point2" + recClosestPair.getPt2() +
                "Closest Pair Distance" + recClosestPair.getD() +
                '}';
    }

    public double getMinDist() {
        return recClosestPair.getD();
    }
    public PointPair getClosestPair() {
        return recClosestPair;
    }
}
