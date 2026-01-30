package biamp.framework.biampworkplace.hooks;

import biamp.framework.biampworkplace.base.baseTest;
import io.cucumber.java.*;
import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

public class hooks extends baseTest {

    // It is like BeforeAll in JUnit
    @Before(order = 0) // Cucumber BeforeAll equivalent
    public void setUp() throws InterruptedException {
        setup();
    }

    // It is like BeforeEach in JUnit
    @Before // Cucumber BeforeEach equivalent
    public void beforeScenario() {
        beforeEach();
    }

    // It is like AfterEach in JUnit

   /* @After // Cucumber AfterEach equivalent
    public void afterScenario(Scenario scenario,TestInfo testInfo) {
        afterEach(testInfo);
    }*/
    @After
    public void afterScenario(Scenario scenario) {
        // Add your logic here
        TestInfo testInfo = new TestInfo() {
            @Override
            public String getDisplayName() {
                return "";
            }

            @Override
            public Set<String> getTags() {
                return Set.of();
            }

            @Override
            public Optional<Class<?>> getTestClass() {
                return Optional.empty();
            }

            @Override
            public Optional<Method> getTestMethod() {
                return Optional.empty();
            }
        };
        afterEach(testInfo);
        System.out.println("After scenario: " + scenario.getName());

    }

}
