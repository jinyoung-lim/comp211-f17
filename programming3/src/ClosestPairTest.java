import org.junit.Test;
import org.testng.Assert;

import java.lang.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by JJ Lim and Daniel Lim on 10/18/17.
 */

public class ClosestPairTest {
    @Test
    public void testClosestPair() {
        Point pt1 = new Point(0, 3);
        Point pt2 = new Point(1, 1);
        Point pt3 = new Point(2, 3);
        Point pt4 = new Point(3, 4);
        Point pt5 = new Point(4, 2);
        Point pt6 = new Point(10, 10);
        Point pt7 = new Point(7, 9);
        Point pt8 = new Point(2, 3);

        ArrayList<Point> ptsByX = new ArrayList<>(Arrays.asList(pt1, pt2, pt3, pt4, pt5, pt7, pt6));
        ArrayList<Point> ptsByY = new ArrayList<>(Arrays.asList(pt2, pt5, pt1, pt3, pt4, pt7, pt6));

        ClosestPairDC closestPairDC = new ClosestPairDC();
        PointPair pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 1.4);



        ptsByX = new ArrayList<>(Arrays.asList(pt1, pt2, pt3));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt1, pt3));

//        closestPairDC = new ClosestPairDC();

        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 2.0);



        ptsByX = new ArrayList<>(Arrays.asList(pt2, pt3, pt4));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt3, pt4));

//        closestPairDC = new ClosestPairDC();

        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 1.4);


        ptsByX = new ArrayList<>(Arrays.asList(pt2, pt3, pt8, pt4));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt3, pt8, pt4));

        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
        System.out.println(pointPair.toString());

    }

}

