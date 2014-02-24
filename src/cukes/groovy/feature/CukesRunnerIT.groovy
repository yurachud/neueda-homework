package feature

import cucumber.api.junit.Cucumber
import cucumber.api.junit.Cucumber.Options
import org.junit.runner.RunWith

@RunWith(Cucumber)
@Options(features = "src/cukes/resources", strict = true, format = ["html:target/cucumber"])
class CukesRunnerIT {
}
