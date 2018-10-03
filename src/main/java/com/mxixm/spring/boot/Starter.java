package com.mxixm.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class Starter {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        SpringApplication.run(Starter.class);
    }

}
