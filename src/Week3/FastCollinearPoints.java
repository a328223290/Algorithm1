package Week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segmentList = new ArrayList<>();

    public FastCollinearPoints(Point[] points){
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
        // Before sorting the array by slope, we first sort it by the coordinates so that we can easily find
        // the tail and rear points. (As sort() method is stable.)
        Arrays.sort(points);
        Point[] other;
        for (int index = 0; index < points.length; index++){
            // Create an array excluding the previous points
            other = new Point[points.length - index - 1];
            for (int k = index + 1; k < points.length; k++){
                other[k - index - 1] = points[k];
            }
            Arrays.sort(other, points[index].slopeOrder()); //descending order

            for (int i = 0; i < other.length - 2; i++) {
                if (points[index].slopeTo(other[i]) == points[index].slopeTo(other[i + 1]) &&
                        points[index].slopeTo(other[i + 1]) == points[index].slopeTo(other[i + 2])) {
                    segmentList.add(new LineSegment(points[index], other[i + 2]));
                }
            }
        }



    }     // finds all line segments containing 4 or more points
    public int numberOfSegments(){
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println("Number of segments: " + collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
