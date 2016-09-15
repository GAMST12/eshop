package eshop.inject;

/*
* Синглетонное хранилище ApplicationContext-ов
* Необходимо для того, чтобы каждый из потомков DependencyInjectionServlet
* не создавал свой appCtx, а использовал разделяемый с другими сервлетами.
 * Хотя может хранить несколько контекстов - по одному для каждого xml,
 * но в нашей системе будет один на все сервлеты.*/

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextHolder {
    private static final Map<String, ApplicationContext> pathToContextRepository = new HashMap<>();
    static synchronized ApplicationContext getClassPathXmlApplicationContext(String path) {
        if (!pathToContextRepository.containsKey(path)) {
            pathToContextRepository.put(path, new ClassPathXmlApplicationContext(path));
        }
        return pathToContextRepository.get(path);
    }
}
