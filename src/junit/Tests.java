package junit;

        import org.junit.Test;

        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.*;
        import static org.junit.Assert.*;

public class Tests {

    @Test
    public void equalityExamples()
    {
        assertTrue(1 == 1);
        assertFalse(1 == 2);

        Integer x2 = 1;
        Integer y2 = 1;
        assertTrue(x2 == y2);

        Integer x3 = 128;
        Integer y3 = 128;
        assertFalse(x3 == y3);
        assertTrue(x3.equals(y3));

        assertTrue("abc" == "abc");
        assertTrue("abc" == "a" + "bc");

        String a = "a";
        assertFalse("abc" == a + "bc");
        assertTrue("abc".equals(a + "bc"));

    }

    @Test
    public void assertThatAndAssertEqualsExample() {

        assertEquals(3, 1 + 2);
        assertThat(1 + 3 , is(4));
        assertThat(1 + 3, is(equalTo(4)));
        assertThat(1 + 3, is(not(2)));

        assertThat(new Integer[] {1, 2, 3}, is(new Integer[] {1, 2, 3}));
        assertThat(new Integer[] {1, 2, 3}, is(not(new Integer[] {1, 2})));
    }


    @Test
    public void findsSpecialNumbers() {
        assertFalse(Code.isSpecial(2));

        assertTrue(Code.isSpecial(11));

        assertTrue(Code.isSpecial(12));

        assertFalse(Code.isSpecial(13));

        assertTrue(Code.isSpecial(33));
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak(""), is(0));

        assertThat(Code.longestStreak("a"), is(1));

        assertThat(Code.longestStreak("abc"), is(1));

        assertThat(Code.longestStreak("abbb"), is(3));

        assertThat(Code.longestStreak("abbcccaaaad"), is(4));
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 2)), is(arrayOf(1, 2)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 2)), is(3));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private Integer[] arrayOf(Integer... numbers) {
        return numbers;
    }

}
