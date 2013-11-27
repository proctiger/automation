package uol.test.feature;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

// reponsavel por fazer a chamada do feature

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",
    features = "src/test/resources/feature", 
    name = {"Validar envio de evento de comissao para pagadoria"}
)
public class LocalServiceTest {}
