package com.solvd.laba.lab2.reflection;

import com.solvd.laba.lab2.Store;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectiveOperations {
    public static final Logger LOGGER = LogManager.getLogger(ReflectiveOperations.class.getName());

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Store> storeClass = Store.class;

        Field[] fields = storeClass.getDeclaredFields();
        for (Field field : fields) {
            LOGGER.info("Field Name: " + field.getName());
            LOGGER.info("Field Type: " + field.getType());
            LOGGER.info("Field Modifiers: " + Modifier.toString(field.getModifiers()));
        }

        Constructor<?>[] constructors = storeClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            LOGGER.info("Constructor Name: " + constructor.getName());
            LOGGER.info("Constructor Parameters: " + Arrays.toString(constructor.getParameterTypes()));
            LOGGER.info("Constructor Modifiers: " + Modifier.toString(constructor.getModifiers()));
        }

        Method[] methods = storeClass.getDeclaredMethods();
        for (Method method : methods) {
            LOGGER.info("Method Name: " + method.getName());
            LOGGER.info("Method Return Type: " + method.getReturnType());
            LOGGER.info("Method Parameters: " + Arrays.toString(method.getParameterTypes()));
            LOGGER.info("Method Modifiers: " + Modifier.toString(method.getModifiers()));
        }

        // Creating a Store object using reflection
        Constructor<?> constructor = storeClass.getDeclaredConstructor(String.class, String.class);
        constructor.setAccessible(true); // In case the constructor is not public
        Store store = (Store) constructor.newInstance("Store (Created by Reflection", "123 Reflection Street");

        // Calling a method on the Store object using reflection
        Method getNameMethod = storeClass.getDeclaredMethod("getName");
        getNameMethod.setAccessible(true); // In case the method is not public
        LOGGER.info(getNameMethod.invoke(store));
    }
}
