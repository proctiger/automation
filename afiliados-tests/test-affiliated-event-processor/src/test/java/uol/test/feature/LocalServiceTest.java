package uol.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",
features = "src/test/resources/feature" ,
name = {"A Validar pagamento de indicacao 2ndTier para o afiliado pai","Validar pagamento","Stressando o Processor","Validar agrupadores e status","Validar pagamento de indicacao"}
)
/*@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",features={"src/test/resources/feature/Validar_pagamento_de_indicacao_2ndTier.feature:35"})*/
public class LocalServiceTest {

}
