package examples.mockito;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class _6_JUnitHamcrestMockitoExample {
    private ArrayList<String> list;


    @Before
    public void setUp() {
       this.list = new ArrayList<>();
    }

    @Test
    public void test_addAll () {
        Collection<String> coll = mock(Collection.class);
        when(coll.toArray()).thenReturn(new String[] {"A", "B", "C"});
        when(coll.iterator()).thenReturn(Arrays.asList("A", "B", "C").iterator());

        coll.size();
        list.addAll(coll);
        list.addAll(coll);
        list.addAll(coll);

        assertThat(list, equalTo(Arrays.asList("A", "B", "C", "A", "B", "C", "A", "B", "C")));

        //verify(coll).iterator());
        verify(coll,times(1)).size();
        verify(coll,times(3)).toArray();
        verifyNoMoreInteractions(coll);
    }
}
