package examples.mockito;

import org.junit.Ignore;

import java.util.*;
import static org.mockito.Mockito.*;

@Ignore
public class _2_WhenEqExample {
    public static void main(String[] args) {
        List<String> list = mock(List.class);
        //when(list.get(eq(5))).thenReturn("A");
        when(list.size()).thenReturn(Integer.MAX_VALUE);
        when(list.get(anyInt())).thenReturn("Hello!");

        System.out.println(list.size());
        for (int i = 0; i < 10; i++) {
            System.out.println("list.get(" + i + "): " +list.get(i));
        }
    }
}
