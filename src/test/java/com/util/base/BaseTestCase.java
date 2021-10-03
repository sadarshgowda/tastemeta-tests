package com.util.base;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTestCase {

    @BeforeMethod(alwaysRun = true)
    protected void setUp(final Method begin){
        System.out.println("Test execution started");
    }

    @BeforeMethod(alwaysRun = true)
    protected void tearDown(final Method end){
        System.out.println("Test execution completed");
    }





}
