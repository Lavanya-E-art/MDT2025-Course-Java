
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MultipleExceptionHandlerTest.class,
    RethrowExceptionHandlerTest.class,
    ResourceManagementHandlerTest.class,
    ChainingExceptionHandlerTest.class
})
public class ExceptionTestSuite {}

