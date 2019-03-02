package oo.hide;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;

public class EqualityTests {

    @Test
    public void testsEquality() {

        assertFalse(new Point(1, 2) == new Point(1, 2));

        assertTrue(new Point(1, 2).equals(new Point(1, 2)));

        assertThat(new Point(1, 2), is(equalTo(new Point(1, 2))));

        assertThat(new Point(1, 1), not(equalTo(new Point(1, 2))));

        assertThat(new Point(1, 1), not(equalTo(null)));

        assertThat(new Point(1, 1), not(equalTo(1)));

        assertThat(new Point(null, null), not(equalTo(new Point(1, 1))));

        assertThat(new Point(1, 1), not(equalTo(new Point(null, null))));
    }


}
