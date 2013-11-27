package uol.adserversap.ws.test.run.probe;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    glue = {"uol.adserversap.ws.test.steps.commons", "uol.adserversap.ws.test.steps.probe"},
    features = {"src/test/resources/features/probe"},
    name = "Validar Probe")
public class ProbeTest {
    
}
