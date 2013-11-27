package uol.bt.mongokeeper.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.bt.mongokeeper.test.run.EfetuarPurgaCollectionCookiesTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaCollectionImpressoesTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaCollectionProfileTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaCollectionSegmentosTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaMongoDesativadoTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaMongoForadoHorarioManutencaoTest;
import uol.bt.mongokeeper.test.run.EfetuarPurgaMongoNoHorarioManutencaoTest;

/**
 * 
 * @author wrodrigues
 *
 */
@RunWith(Suite.class)
@SuiteClasses({	EfetuarPurgaCollectionSegmentosTest.class, 
			   	EfetuarPurgaCollectionImpressoesTest.class,
			   	EfetuarPurgaCollectionCookiesTest.class,
			   	EfetuarPurgaCollectionProfileTest.class,
			   	EfetuarPurgaMongoDesativadoTest.class,
			   	EfetuarPurgaMongoForadoHorarioManutencaoTest.class,
			   	EfetuarPurgaMongoNoHorarioManutencaoTest.class
			  })
public class TestSuite {

}
