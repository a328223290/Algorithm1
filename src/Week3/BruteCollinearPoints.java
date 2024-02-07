package Week3;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>() ;

    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new IllegalArgumentException();
        else{
            for (int i = 0; i < points.length - 1; i++){
                if (points[i] == null) throw new IllegalArgumentException();
                for (int j = i + 1; j < points.length; j++){
                    if (points[j] == null) throw new IllegalArgumentException();
                    if (points[i].equals(points[j])) throw new IllegalArgumentException();
                }
            }
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length - 3; i++){
            Point point1 = points[i];
            for (int j = i + 1; j < points.length - 2; j++){
                Point point2 = points[j];
                double slope12 = point1.slopeTo(point2);
                for (int m = j + 1; m < points.length - 1; m++){
                    Point point3 = points[m];
                    double slope23 = point2.slopeTo(point3);
                    if (slope12 == slope23){
                        for (int n = m + 1; n < points.length; n++){
                            Point point4 = points[n];
                            double slope34 = point3.slopeTo(point4);
                            if (slope23 == slope34)  {
                                // Need to find the tail and rear of the segment
                                LineSegment segment = new LineSegment(point1, point4);
                                segmentList.add(segment);
                            }
                        }
                    }
                }
            }
        }
    }    // finds all line segments containing 4 points
    public           int numberOfSegments(){
        return segmentList.size();
    }        // the number of line segments
    public LineSegment[] segments(){
        LineSegment[] segments = new LineSegment[segmentList.size()];
        return segmentList.toArray(segments);
    }                // the line segments

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println("Number of segments: " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}