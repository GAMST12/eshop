package examples.junit;

import org.junit.*;

public class _1_LifeCycleExample {
    public _1_LifeCycleExample() {
        System.out.println("constructor()");
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void test0() {
        System.out.println("test-0");
    }

    @Test
    public void test1() {
        System.out.println("test-1");
    }

    @Test
    public void test2() {
        System.out.println("test-2");
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass");
    }
}
