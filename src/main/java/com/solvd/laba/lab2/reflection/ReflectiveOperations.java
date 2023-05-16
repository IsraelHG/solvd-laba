package com.solvd.laba.lab2.reflection;

import com.solvd.laba.lab2.Store;
import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectiveOperations {
    public static void main(String[] args) {
        // Get the Store class
        Class<Store> storeClass = Store.class;

        // Get information about fields
        Field[] fields = storeClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field Name: " + field.getName());
            System.out.println("Field Type: " + field.getType());
            System.out.println("Field Modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println();
        }

        // Get information about constructors
        Constructor<?>[] constructors = storeClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor Name: " + constructor.getName());
            System.out.println("Constructor Parameters: " + Arrays.toString(constructor.getParameterTypes()));
            System.out.println("Constructor Modifiers: " + Modifier.toString(constructor.getModifiers()));
            System.out.println();
        }

        // Get information about methods
        Method[] methods = storeClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method Name: " + method.getName());
            System.out.println("Method Return Type: " + method.getReturnType());
            System.out.println("Method Parameters: " + Arrays.toString(method.getParameterTypes()));
            System.out.println("Method Modifiers: " + Modifier.toString(method.getModifiers()));
            System.out.println();
        }
    }
}
