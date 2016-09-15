package examples.junit;

import org.junit.*;

public class _3_IgnoreExample {

    @Test
    public void testOk() {
        System.out.println("This test is Ok and done");
    }

    @Ignore
    @Test
    public void testFailButIgnored() {
        System.out.println("This test is Fail but Ignored");
        throw new RuntimeException();
    }
}
