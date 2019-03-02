package oo.struct;

import org.junit.Test;

public class Runner {

    @Test
    public void coordinatesAsArrays() {

        Integer[][] trianglePoints = {{1, 1, 0}, {5, 1, 0}, {3, 7, 1}};

        for (Integer[] each : trianglePoints) {
            System.out.println(each[2]);
        }

    }

    @Test
    public void coordinatesAsObjects() {

        Point3D[] trianglePoints = {
                new Point3D(1, 1, 0),
                new Point3D(5, 1, 0),
                new Point3D(3, 7, 1)
        };

        for (Point3D point : trianglePoints) {
            System.out.println(point.z);
        }
        // System.out.println(trianglePoints[0].x);  // private- can't be accessed
        System.out.println(trianglePoints[0].z);  // public- can be accessed
    }
}