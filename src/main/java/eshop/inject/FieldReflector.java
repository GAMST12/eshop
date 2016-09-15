package eshop.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FieldReflector {
    public static List<Field> collectUpTo (Class <?> clazz, Class <?> upperBound) {
        ArrayList<Field> result = new ArrayList<>();
        Class<?> current = clazz;
        while (clazz != upperBound) {
            result.addAll(asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return result;
    }

    public static  List<Field> filterInject (List<Field> allFields) {
        ArrayList<Field> result = new ArrayList<>();
        for (Field field : allFields) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                result.add(field);
            }
        }
        return result;
    }
}
