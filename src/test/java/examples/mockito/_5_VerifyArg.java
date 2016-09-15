package examples.mockito;


import org.junit.Ignore;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Ignore
public class _5_VerifyArg {
    public static void main(String[] args) {
        //create+programming
        List list = mock(List.class);

        //use
        list.add("A");
        list.remove("B");
        list.add("B");

        //ask?
        verify(list).add("B");
    }
}
