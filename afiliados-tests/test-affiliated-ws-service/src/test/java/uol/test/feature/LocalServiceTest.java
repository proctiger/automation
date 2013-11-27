package uol.test.feature;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.junit.Cucumber;

/**
  Esta classe representa os arquivos com extensao feature onde os testes estao
  documentados. Nela definem-se 3 configuracoes: 
  - glue: classes onde os passos "Dado, Quando e Entao" serao documentados 
  - features: path de onde os arquivos com extensao feature estao armazenados 
  - name: nome das funcionalidades que serao executadas
  
  @author dzaniro
**/

@ContextConfiguration("classpath:beans.xml")
@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",
features = "src/test/resources/feature/",
name = {"Validar Servico de Insercao de Afiliado"}
)

public class LocalServiceTest {

	
}
