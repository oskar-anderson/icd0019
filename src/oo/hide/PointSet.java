package oo.hide;

import java.util.Arrays;
import java.util.Objects;

public class PointSet {

    private Point[] points;
    private Integer indexToInsertNext = 0;

    public PointSet(Integer initialCapacity) {
        points = new Point[initialCapacity];
    }

    public PointSet() {
        points = new Point[100];
    }

    public void add(Point point) {
        if (!(contains(point))) {
            if (points.length == size()) {
                points = Arrays.copyOf(points, size() * 2);
            }
            // System.out.println(point + " inserted to array at index: " + indexToInsertNext);
            points[indexToInsertNext] = point;
            indexToInsertNext++;
        }
    }

    public Integer size() {
        // return size of array without nulls.
        return indexToInsertNext;
    }

    public boolean contains(Point p) {
        for (Point oldPoint : points) {
            if (Objects.equals(p, oldPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Point[] finalArr = Arrays.copyOfRange(points, 0, size());

        StringBuilder builder = new StringBuilder();
        String sep = "";
        for (Point value : finalArr) {
            builder.append(sep);
            builder.append(value);
            sep = ", ";
        }

        return builder.toString();
    }
}