package biamp.framework.biampworkplace.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "biamp.framework.biampworkplace.steps, biamp.framework.biampworkplace.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:src/test/java/biamp/framework/biampworkplace/artifacts/cucumber-report.html")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")


//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features",
//        glue = {"biamp.framework.biampworkplace.steps", "biamp.framework.biampworkplace.hooks"},
//        dryRun = false,
//        plugin = {"pretty", "html:src/test/java/biamp/framework/biampworkplace/artifacts/cucumber-report.html"}
//)

public class TestRunner {
}
