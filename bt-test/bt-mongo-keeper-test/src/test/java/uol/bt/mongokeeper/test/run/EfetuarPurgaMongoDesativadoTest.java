package uol.bt.mongokeeper.test.run;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * @author cin_wrodrigues
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue="uol.bt.mongokeeper.test.step",
        features = {"src/test/resources/features/Efetuar purga com Mongo desativado.feature"},
        name = "Efetuar purga com Mongo desativado")
public class EfetuarPurgaMongoDesativadoTest {
}
