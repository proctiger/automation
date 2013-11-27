package uol.test.start;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

// reponsavel por fazer a chamada do feature

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",
    features = "src/test/resources/features", 
    name = {"Feature01"}
)
public class CucumberStartTest {}
