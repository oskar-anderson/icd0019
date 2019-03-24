package collections.set;

import oo.hide.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class HashCodeExample {

    @Test
    public void runExample() {

        Set<Point> set = new HashSet<>();

        System.out.println(new Point(1, 1).hashCode());

        System.out.println(new Point(1, 1).hashCode());

        set.add(new Point(1, 1));
        set.add(new Point(1, 1));

        System.out.println(set.size());

    }
}
