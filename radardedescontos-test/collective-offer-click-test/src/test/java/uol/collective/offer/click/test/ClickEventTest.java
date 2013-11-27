package uol.collective.offer.click.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ClickConsolidationTestModel;
import uol.collective.offer.commons.test.model.impl.ClickEventTestModel;
import uol.collective.offer.commons.test.model.impl.ClickRequest;
import uol.collective.offer.commons.test.model.impl.OfferTestResponse;
import uol.collective.offer.commons.test.util.GenericValidator;

public class ClickEventTest extends ClickBase
{

    Date now = new Date();
    
    @Before
    public void initialize()
    {
        super.initialize();
    }

    @Test
    public void checkClickProcessed() throws InterruptedException
    {
        ClickRequest click = new ClickRequest( offerDAO.getLastActiveOfferInserted()
            .getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true", "0.69", "web", "666", "pagseguro-chave-privada", "pagseguro-chave-publica", Long.toString( now.getTime() ) );
                
        //caso o teste falhe pelo ip, usar o ip real ao inves de 127.0.0.1 
        ClickEventTestModel expectedClickDB = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), click.getOfferPosition(), cityDAO.getLastActiveCityInserted(), "P", "172.22.37.17", click.getCpc(), "0", "0", "0", "W", "666", "0" );
        //ClickEventTestModel expectedClickDB = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), click.getOfferPosition(), cityDAO.getLastActiveCityInserted(), "P", "127.0.0.1", click.getCpc(), "0", "0", "0", "W", "666", "0" );

        executeClick( click );

        ClickEventTestModel clickDB = null;
        try
        {
            Thread.sleep( 400 );
            clickDB = clickEventDAO.getLastClick();
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            clickDB = clickEventDAO.getLastClick();
        }
        
        expectedClickDB.setUniqueClick( clickDB.getUniqueClick() ); //a geracao no id unico para o banco eh randomica, nao deterministica

        Assert.assertTrue( GenericValidator.equalsByFields( clickDB, expectedClickDB, ClickEventTestModel.class, new String[] {
                "clickDate",
                "updateDate",
                "id" } ) );
    }

    @Test 
    public void checkStatisticsOneClick() throws InterruptedException
    {
        ClickRequest click = new ClickRequest( offerDAO.getLastActiveOfferInserted()
            .getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true", "0.69", "web", "666", "pagseguro-chave-privada", "pagseguro-chave-publica", Long.toString( now.getTime() )  );
        ClickConsolidationTestModel expectedClickConsolidationDB = new ClickConsolidationTestModel( offerDAO.getLastActiveOfferInserted(), siteDAO.getLastActiveSiteInserted(), "1" );

        executeClick( click );

        ClickConsolidationTestModel clickConsolidationDB = null;
        try
        {
            Thread.sleep( 400 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        }

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, expectedClickConsolidationDB, ClickConsolidationTestModel.class, new String[] {
                "consolidationDate",
                "id" } ) );
    }

    @Test
    public void checkStatisticsTwosClicksSameOffer() throws InterruptedException
    {
        ClickRequest clickOne = new ClickRequest( offerDAO.getLastActiveOfferInserted()
            .getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true", "0.69", "web", "666", "pagseguro-chave-privada", "pagseguro-chave-publica", Long.toString( now.getTime() )   );

        ClickRequest clickTwo = new ClickRequest( offerDAO.getLastActiveOfferInserted()
            .getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true", "0.69", "mobile", "1331", "pagseguro-chave-privada", "pagseguro-chave-publica", Long.toString( now.getTime() )   );

        ClickConsolidationTestModel expectedClickConsolidationDB = new ClickConsolidationTestModel( offerDAO.getLastActiveOfferInserted(), siteDAO.getLastActiveSiteInserted(), "2" );

        executeClick( clickOne );

        executeClick( clickTwo );

        ClickConsolidationTestModel clickConsolidationDB = null;
        try
        {
            Thread.sleep( 500 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        }

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, expectedClickConsolidationDB, ClickConsolidationTestModel.class, new String[] {
                "consolidationDate",
                "id" } ) );
    }

    @Test
    public void checkStatisticsTwosClicksDifferentOffers() throws InterruptedException
    {

        OfferTestResponse offerOne = offerDAO.getLastActiveOfferInserted();
        ClickRequest clickOne = new ClickRequest( offerOne.getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true","0.69", "web", "666", "pagseguro-chave-privada1", "pagseguro-chave-publica1", Long.toString( now.getTime() )   );

        OfferTestResponse offerTwo = offerDAO.insertRandomOffer();
        ClickRequest clickTwo = new ClickRequest( offerTwo.getId(), "4", "http://www.uol.com.br", siteDAO.getLastActiveSiteInserted(), cityDAO.getLastActiveCityInserted(), "Eletrônicos", "true", "0.69", "mobile", "1331", "pagseguro-chave-privada2", "pagseguro-chave-publica2", Long.toString( now.getTime() )   );

        ClickConsolidationTestModel expectedClickConsolidationOfferOne = new ClickConsolidationTestModel( offerOne, siteDAO.getLastActiveSiteInserted(), "1" );

        ClickConsolidationTestModel expectedClickConsolidationOfferTwo = new ClickConsolidationTestModel( offerTwo, siteDAO.getLastActiveSiteInserted(), "1" );

        executeClick( clickOne );

        executeClick( clickTwo );

        ClickConsolidationTestModel clickConsolidationOfferOne = null;
        ClickConsolidationTestModel clickConsolidationOfferTwo = null;
        try
        {
            Thread.sleep( 500 );
            clickConsolidationOfferOne = clickConsolidationDAO.getStatisticByOffer( offerOne );
            clickConsolidationOfferTwo = clickConsolidationDAO.getStatisticByOffer( offerTwo );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            clickConsolidationOfferOne = clickConsolidationDAO.getStatisticByOffer( offerOne );
            clickConsolidationOfferTwo = clickConsolidationDAO.getStatisticByOffer( offerTwo );
        }

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationOfferOne, expectedClickConsolidationOfferOne, ClickConsolidationTestModel.class, new String[] {
                "consolidationDate",
                "id" } ) );
        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationOfferTwo, expectedClickConsolidationOfferTwo, ClickConsolidationTestModel.class, new String[] {
                "consolidationDate",
                "id" } ) );
    }
}
