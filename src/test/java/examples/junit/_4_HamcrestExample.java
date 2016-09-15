package examples.junit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static java.util.Arrays.*;

public class _4_HamcrestExample {
    private List<String> list;

    @Before
    public void setUp() {
        this.list = new ArrayList<>();
    }

    @Test
    public void test_core_java() {
        list.add("A");
        if (!list.get(0).equals("A")) {
            throw new AssertionError();
        }
    }

    @Test
    public void test_junit() {
        list.add("A");
        Assert.assertTrue(list.get(0).equals("A"));
    }

    @Test
    public void test_hamcrest() {
        list.add("A");
        Assert.assertThat(list.get(0), Matchers.is("A"));
    }

    @Test
    public void test_hamcrest_internal_dsl() {
        list.add("A");
        assertThat(list.get(0), is("A")); //internal DSL - внутренний предметно-ориентированный язык (domen specific language)
    }

    @Test
    public void test_twoX_by_custom_matcher() {
        List <String> list= asList("A","X","*","X","+");
        assertThat(list, hasStrictCount("X", 3));
    }

    public static <T> Matcher<Collection<T>> hasStrictCount(final T expectedElem, final int expectedCount) {
        return new BaseMatcher<Collection<T>>() {
            private int actualCount;
            private Object arg;
            @Override
            public boolean matches(Object item) {
                this.arg = item;
                actualCount = 0;
                for (T value : ((Iterable<T>) item)) {
                    actualCount += value.equals(expectedElem) ? 1 : 0;
                }
                return actualCount == expectedCount;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(expectedCount + " of '" + expectedElem + "', but found " + actualCount + " in " + arg);
            }
        };
    }

}
