package com.spring.ghost.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassMergeUtils {


    public static <T> void copyProperties(T source, Object target) {
        ClassMergeUtils classUtils = new ClassMergeUtils();
        classUtils._copyProperties(source, target);
    }

    public static <T> T copyProperties(Class<?> cls, Object target) {
        try {
            T source = (T) Class.forName(cls.getName()).newInstance();
            copyProperties(source, target);
            return source;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ClassMergeUtils() {

    }

    private void _copyProperties(Object source, Object target) {
        Map<String, Field> sourceFields = getAllFields(source.getClass());
        Map<String, Field> targetFields = getAllFields(target.getClass());
        for (Map.Entry<String, Field> entry : sourceFields.entrySet()) {
            String key = entry.getKey();
            Field sourceField = sourceFields.get(key);
            Field targetField = targetFields.get(key);
            if (targetField != null) {
                mergeValue(source, target, sourceField, targetField);
            }
        }
    }

    private void mergeValue(Object source, Object target, Field sourceField, Field targetField) {
        try {
            if (equalsType(sourceField, targetField)) {
                Object sourceValue = sourceField.get(source);
                Object targetValue = targetField.get(target);
                Class<?> type = sourceField.getType();
                if (type.isMemberClass() && sourceValue != null && targetValue != null) {
                    _copyProperties(sourceValue, targetValue);
                } else {
                    if (targetValue != null) {
                        sourceField.set(source, targetValue);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean equalsType(Field sourceField, Field targetField) {
        Class<?> sourceType = sourceField.getType();
        Class<?> targetType = targetField.getType();
        return sourceType.equals(targetType);
    }


    private Map<String, Field> getAllFields(Class<?> clazz) {
        List<Class<?>> allSuperClass = getExtendsClass(clazz);
        Map<String, Field> allField = new HashMap<>();
        for (Class<?> cls : allSuperClass) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String name = field.getName();
                if (!allField.containsKey(name)) {
                    allField.put(name, field);
                }
            }
        }
        return allField;
    }

    private List<Class<?>> getExtendsClass(Class<?> clazz) {
        List<Class<?>> allClass = new ArrayList<>();
        allClass.add(clazz);
        while (hasSuperClass(clazz)) {
            clazz = clazz.getSuperclass();
            allClass.add(clazz);
        }
        return allClass;
    }

    private boolean hasSuperClass(Class<?> clazz) {
        String name = clazz.getName();
        if ("java.lang.Object".equals(name)) {
            return false;
        }
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null) {
            return false;
        } else {
            name = superclass.getName();
            return !"java.lang.Object".equals(name);
        }
    }
}
