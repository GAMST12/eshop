package examples.mockito;

import org.junit.Ignore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

@Ignore
public class _1_JdkProxyDemo {
    public static void main(String[] args) {
        List<String> list = (List<String>) Proxy.newProxyInstance(null,
                new Class[]{List.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        switch (method.getName()) {
                            case "add":
                                return true;
                            case "get":
                                return "Hello from proxy";
                            case "size":
                                return -42;
                            case "iterator":
                                return null;
                            default:
                                throw new UnsupportedOperationException();
                        }
                    }
                });
        System.out.println(list.add("A"));
        System.out.println(list.get(-1));
        System.out.println(list.size());
        System.out.println(list.iterator());
    }
}
