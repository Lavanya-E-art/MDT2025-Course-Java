package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ExceptionDemo_KeyaTest.class,
    ExceptionDemo_LavanyaTest.class,
    ExceptionDemo_NayanaTest.class,
    ExceptionDemo_ToobaTest.class
})
public class TeamExceptionSuiteTest {}
