package oo.hide;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class PointSetTests {

    @Test
    public void pointSetKeepsTrackOfPoints() {
        PointSet set = new PointSet();

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));
        set.add(new Point(1, 2));

        assertThat(set.size(), is(3));

        set.add(new Point(2, 1));

        assertThat(set.size(), is(3));

        assertTrue(set.contains(new Point(1, 1)));
        assertTrue(set.contains(new Point(1, 2)));
        assertFalse(set.contains(new Point(1, 3)));

        assertThat(set.toString(), is("(1, 1), (2, 1), (1, 2)"));
    }

    @Test
    public void setGrowsWhenThereIsNoMoreRoom() {
        PointSet set = new PointSet(2);

        set.add(new Point(1, 1));
        set.add(new Point(2, 1));

        assertThat(getInternalArray(set).length, is(2));

        set.add(new Point(3, 1));

        assertThat(getInternalArray(set).length, is(4));
    }

    private Point[] getInternalArray(PointSet set) {

        Field[] fields = set.getClass().getDeclaredFields();

        List<Field> integerArrayFields = Arrays.asList(fields)
                .stream()
                .filter(field -> field.getType().equals(Point[].class))
                .collect(Collectors.toList());

        if (integerArrayFields.size() == 0) {
            fail("PointSet should have a field of type Point[]");
        }

        if (integerArrayFields.size() > 1) {
            fail("PointSet should have just one field of type Point[]");
        }

        integerArrayFields.stream()
                .forEach(field -> field.setAccessible(true));

        try {
            return (Point[]) integerArrayFields.get(0).get(set);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
