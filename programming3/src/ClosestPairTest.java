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
        //Test on Different Sets of Points (uncomment to see toString)
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
//        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 1.4);

        ptsByX = new ArrayList<>(Arrays.asList(pt1, pt2, pt3));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt1, pt3));
        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
//        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 2.0);

        ptsByX = new ArrayList<>(Arrays.asList(pt2, pt3, pt4));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt3, pt4));
        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
//        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 1.4);

        ptsByX = new ArrayList<>(Arrays.asList(pt2, pt3, pt8, pt4));
        ptsByY = new ArrayList<>(Arrays.asList(pt2, pt3, pt8, pt4));
        pointPair = closestPairDC.dcClosestPair(ptsByX, ptsByY);
//        System.out.println(pointPair.toString());
        Assert.assertEquals((int)(pointPair.getD()*10)/10.0, 0.0);


        //Test Time Efficiency
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            points.add(new Point(i, i));
        }
        long startTime = System.currentTimeMillis();
        closestPairDC.dcClosestPair(points,points);
        long endTime = System.currentTimeMillis();
        System.out.println("Divide-and-Conquer Cloeset Pair Search Time for 100 Points: " + (endTime-startTime));
        //100*log(100) ==  664.3856189774724

        for (int i = 100; i < 1000; i++) {
            points.add(new Point(i, i));
        }
        startTime = System.currentTimeMillis();
        closestPairDC.dcClosestPair(points,points);
        endTime = System.currentTimeMillis();
        System.out.println("Divide-and-Conquer Cloeset Pair Search Time for 1000 Points: " + (endTime-startTime));
        //1000*log(1000) == 9965.784284662088

        for (int i = 1000; i < 10000; i++) {
            points.add(new Point(i, i));
        }
        startTime = System.currentTimeMillis();
        closestPairDC.dcClosestPair(points,points);
        endTime = System.currentTimeMillis();
        System.out.println("Divide-and-Conquer Cloeset Pair Search Time for 10000 Points: " + (endTime-startTime));
        // 10000*log(10000) == 13287.712379549448

    }


}

