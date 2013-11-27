package uol.admanager.consolidator.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.admanager.consolidator.test.run.click.ConsolidarCliquesCenariosNegativos;
import uol.admanager.consolidator.test.run.click.ConsolidarCliquesCenariosPositivos;
import uol.admanager.consolidator.test.run.click.ControlarProcessamentoCliquesCenariosNegativos;
import uol.admanager.consolidator.test.run.click.ControlarProcessamentoCliquesCenariosPositivos;
import uol.admanager.consolidator.test.run.view.ConsolidarImpressoesCenariosNegativos;
import uol.admanager.consolidator.test.run.view.ConsolidarImpressoesCenariosPositivos;
import uol.admanager.consolidator.test.run.view.ControlarProcessamentoImpressoesCenariosNegativos;
import uol.admanager.consolidator.test.run.view.ControlarProcessamentoImpressoesCenariosPositivos;
import uol.admanager.consolidator.test.run.view.legacy.ConsolidarImpressoesLegacyCenariosNegativos;
import uol.admanager.consolidator.test.run.view.legacy.ConsolidarImpressoesLegacyCenariosPositivos;
import uol.admanager.consolidator.test.run.view.legacy.ControlarProcessamentoImpressoesLegacyCenariosNegativos;
import uol.admanager.consolidator.test.run.view.legacy.ControlarProcessamentoImpressoesLegacyCenariosPositivos;

/**
 *
 * @author cin_wrodrigues
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    ConsolidarImpressoesLegacyCenariosNegativos.class,
    ConsolidarImpressoesLegacyCenariosPositivos.class,
    ControlarProcessamentoImpressoesLegacyCenariosNegativos.class,
    ControlarProcessamentoImpressoesLegacyCenariosPositivos.class,

    ConsolidarImpressoesCenariosNegativos.class,
    ConsolidarImpressoesCenariosPositivos.class,
    ControlarProcessamentoImpressoesCenariosNegativos.class,
    ControlarProcessamentoImpressoesCenariosPositivos.class,

    ConsolidarCliquesCenariosPositivos.class,
    ConsolidarCliquesCenariosNegativos.class,
    ControlarProcessamentoCliquesCenariosPositivos.class,
    ControlarProcessamentoCliquesCenariosNegativos.class
})
public class TestSuite {
}
