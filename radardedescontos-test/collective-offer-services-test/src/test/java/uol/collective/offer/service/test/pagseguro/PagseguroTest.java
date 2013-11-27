package uol.collective.offer.service.test.pagseguro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ClickConsolidationTestModel;
import uol.collective.offer.commons.test.model.impl.ClickEventTestModel;
import uol.collective.offer.commons.test.model.impl.ClickImageRoimeterTestResponse;
import uol.collective.offer.commons.test.model.impl.TransactionEventTestModel;
import uol.collective.offer.commons.test.model.impl.TransactionTestResponse;
import uol.collective.offer.commons.test.util.GenericValidator;

public class PagseguroTest extends PagseguroBase
{

    @Before
    public void initialize()
    {
        super.initialize();
    }

    @Test
    public void saveOneTransactionStatusPago() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "3";
        String status = "3";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }

        ClickConsolidationTestModel clickConsolidationDB = clickConsolidationDAO.getLastStatistic();

        TransactionEventTestModel expectedTransaction = new TransactionEventTestModel( paymentValue, commissionAmount, clickEvent.getId(), "2", null, paymentMethod );

        clickConsolidation.setNumTransactions( "1" );
        clickConsolidation.setTransactionValueAmount( paymentValue );

        Assert.assertTrue( GenericValidator.equalsByFields( transactionDB, expectedTransaction, TransactionEventTestModel.class, new String[] {
                "clickEvent",
                "date",
                "id" } ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );
    }

    @Test
    public void saveTwoTransactionsStatusPago() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "3";
        String status = "3";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        // transacao 1
        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }

        ClickConsolidationTestModel clickConsolidationDB = clickConsolidationDAO.getLastStatistic();

        TransactionEventTestModel expectedTransaction = new TransactionEventTestModel( paymentValue, commissionAmount, clickEvent.getId(), "2", null, paymentMethod );

        clickConsolidation.setNumTransactions( "1" );
        clickConsolidation.setTransactionValueAmount( paymentValue );

        Assert.assertTrue( GenericValidator.equalsByFields( transactionDB, expectedTransaction, TransactionEventTestModel.class, new String[] {
                "clickEvent",
                "date",
                "id" } ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );

        // transacao 2
        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, "outro-transaction-id", paymentValue );

        executeNotification();

        try
        {
            Thread.sleep( 400 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            clickConsolidationDB = clickConsolidationDAO.getLastStatistic();
        }

        clickConsolidation.setNumTransactions( "2" );
        clickConsolidation.setTransactionValueAmount( String.valueOf( Integer.valueOf( paymentValue ) * 2 ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );
    }
    
    @Test
    public void saveTransactionWithStatusDisponivel() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "3";
        String status = "4";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }

        ClickConsolidationTestModel clickConsolidationDB = clickConsolidationDAO.getLastStatistic();

        TransactionEventTestModel expectedTransaction = new TransactionEventTestModel( paymentValue, commissionAmount, clickEvent.getId(), "2", null, paymentMethod );

        clickConsolidation.setNumTransactions( "1" );
        clickConsolidation.setTransactionValueAmount( paymentValue );

        Assert.assertTrue( GenericValidator.equalsByFields( transactionDB, expectedTransaction, TransactionEventTestModel.class, new String[] {
                "clickEvent",
                "date",
                "id" } ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );
    }    
    
    @Test
    public void saveTransactionWithStatusEmDisputa() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "5";
        String status = "5";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }

        ClickConsolidationTestModel clickConsolidationDB = clickConsolidationDAO.getLastStatistic();

        TransactionEventTestModel expectedTransaction = new TransactionEventTestModel( paymentValue, commissionAmount, clickEvent.getId(), "2", null, paymentMethod );

        clickConsolidation.setNumTransactions( "1" );
        clickConsolidation.setTransactionValueAmount( paymentValue );

        Assert.assertTrue( GenericValidator.equalsByFields( transactionDB, expectedTransaction, TransactionEventTestModel.class, new String[] {
                "clickEvent",
                "date",
                "id" } ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );
    }        
    
    @Test
    public void saveTransactionWithStatusDevolvida() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "5";
        String status = "6";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }

        ClickConsolidationTestModel clickConsolidationDB = clickConsolidationDAO.getLastStatistic();

        TransactionEventTestModel expectedTransaction = new TransactionEventTestModel( paymentValue, commissionAmount, clickEvent.getId(), "2", null, paymentMethod );

        clickConsolidation.setNumTransactions( "1" );
        clickConsolidation.setTransactionValueAmount( paymentValue );

        Assert.assertTrue( GenericValidator.equalsByFields( transactionDB, expectedTransaction, TransactionEventTestModel.class, new String[] {
                "clickEvent",
                "date",
                "id" } ) );

        Assert.assertTrue( GenericValidator.equalsByFields( clickConsolidationDB, clickConsolidation, ClickConsolidationTestModel.class, new String[] {
                "offer",
                "consolidationDate",
                "site",
                "clickCount" } ) );
    }        
    
    @Test
    public void saveTransactionWithStatusCancelado() throws InterruptedException
    {

        String clickId = "unique-click";
        String commissionAmount = "5";
        String paymentMethod = "5";
        String status = "7";
        String transactionId = "12345";
        String paymentValue = "15";

        ClickEventTestModel clickEvent = new ClickEventTestModel( offerDAO.getLastActiveOfferInserted(), cityDAO.getLastActiveCityInserted(), clickId );

        clickEvent = clickEventDAO.saveOrUpdate( clickEvent );

        ClickConsolidationTestModel clickConsolidation = new ClickConsolidationTestModel( clickEvent.getOffer(), clickEvent.getOffer()
            .getSite(), "1" );
        clickConsolidationDAO.saveOrUpdate( clickConsolidation );

        clickEventRoimeterDAO.saveOrUpdate( new ClickImageRoimeterTestResponse( clickEvent.getId(), "<measurement><datExpires>2013-05-29T04:11:05-03:00</datExpires><datPersistence>2013-05-28T05:11:05.810-03:00</datPersistence><groupingsMap><entry><key>Tipo</key><value>sem%20botao</value></entry><entry><key>thm</key><value>home-radar</value></entry><entry><key>src</key><value>75</value></entry><entry><key>chn</key><value>198</value></entry><entry><key>size</key><value>87</value></entry></groupingsMap><idtProduct>56</idtProduct><measuresMap><entry><key>Visualizacao</key><value>2.0</value></entry></measuresMap></measurement>" ) );

        changeMockAttributes( clickId, commissionAmount, paymentMethod, status, transactionId, paymentValue );

        executeNotification();

        TransactionEventTestModel transactionDB = null;
        try
        {
            Thread.sleep( 400 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        } catch ( Exception e )
        {
            Thread.sleep( 1000 );
            transactionDB = transactionEventDAO.getLastTransactionByClickEvent( clickEvent.getId() );
        }
        Assert.assertNull( transactionDB );
    }          

    @Test
    @Ignore
    public void successSelectMockTransaction()
    {
        TransactionTestResponse transactionExpected = new TransactionTestResponse( DEFAULT_COD_UNIQUE_CLICK, DEFAULT_DATE, DEFAULT_CODE, DEFAULT_STATUS, DEFAULT_GROSS_AMOUNT );
        TransactionTestResponse transactionToBeTested = new TransactionTestResponse( DEFAULT_COD_UNIQUE_CLICK, DEFAULT_DATE, DEFAULT_CODE, DEFAULT_STATUS, DEFAULT_GROSS_AMOUNT );

        Assert.assertTrue( executeSelectAndValidate( transactionToBeTested, transactionExpected ) );

    }

    @Test
    @Ignore
    public void successSelectMockInvalidTransaction()
    {
        TransactionTestResponse transactionExpected = new TransactionTestResponse( DEFAULT_COD_UNIQUE_CLICK, DEFAULT_DATE, DEFAULT_CODE, DEFAULT_STATUS, DEFAULT_GROSS_AMOUNT );
        TransactionTestResponse transactionToBeTested = new TransactionTestResponse( "Invalid Code", DEFAULT_DATE, DEFAULT_CODE, DEFAULT_STATUS, DEFAULT_GROSS_AMOUNT );

        Assert.assertFalse( executeSelectAndValidate( transactionToBeTested, transactionExpected ) );

    }
}
