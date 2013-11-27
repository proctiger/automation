package uol.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * Esta classe representa os arquivos com extenção feature onde os testes estão documentados
 * Nela você define 3 configurações:
 * - glue: classes onde os passos "Dado, Quando e Então" serão documentados
 * - features: path de onde os arquivos com extenção feature estão armazenados
 * - name: nome das funcionalidades que serão executadas
 * 
 * @author romero
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    glue = "uol.test.step",
    features = "src/test/resources/feature",
    name = {"Efetuar envio de registro para fila"})
public class LocalServiceTest {

}
