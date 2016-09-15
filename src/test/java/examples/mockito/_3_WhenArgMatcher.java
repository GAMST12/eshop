package examples.mockito;


import org.junit.Ignore;
import org.mockito.ArgumentMatcher;

import java.util.*;

import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class _3_WhenArgMatcher {
    public static void main(String[] args) {
        List<String> list = mock(List.class);
        ArgumentMatcher<Integer> matcher = new ArgumentMatcher<Integer>() {
            @Override
            public boolean matches(Object arg) {
                return (arg instanceof Integer) && ((Integer) arg) % 3 ==0;
            }
        };
        when(list.get(intThat(matcher))).thenReturn("Hello!");
        //when(list.get(intThat(matcher))).thenThrow(new RuntimeException("Booo!!!"));

        for (int i = 0; i < 10; i++) {
            System.out.println("list.get(" + i + "): " + list.get(i));
        }
    }
}
