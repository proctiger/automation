package uol.bt.cruncher.test.step;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import uol.bt.commons.test.profile.ProfileParameter;
import uol.bt.commons.test.profile.TraitMapper;
import uol.bt.cruncher.test.domain.BtEvent;
import uol.bt.cruncher.test.domain.cookies.CookiesDocument;
import uol.bt.cruncher.test.domain.profiles.ProfileDocument;
import uol.bt.cruncher.test.domain.profiles.ProfileEvent;
import uol.bt.cruncher.test.domain.segments.SegmentsDocument;
import uol.bt.cruncher.test.domain.views.ViewsDocument;
import uol.bt.cruncher.test.domain.views.ViewsEvent;
import uol.bt.cruncher.test.helper.TestPrepare;
import uol.bt.cruncher.test.util.CollectionUtils;
import uol.bt.cruncher.test.util.Constants;

import com.mongodb.DBObject;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

/**
 *
 * @author dvrocha
 *
 */
public class GivenStep extends BaseStep {

    @Before
    public void beforeEachTest() throws Exception {
        final String uuid = TestPrepare.generateUuid("");

        final DBObject mongoViewsDocument = TestPrepare.findOneInCollection(uuid, TestPrepare.getCurrViewsCollection());
        viewsDocument = ViewsDocument.fromMongoObject(mongoViewsDocument);
        viewsDocument.setId(uuid);

        final DBObject mongoProfileDocument = TestPrepare.findOneInCollection(uuid, TestPrepare.getProfileCollection());
        profileDocument = ProfileDocument.fromMongoObject(mongoProfileDocument);
        profileDocument.setId(uuid);

        final DBObject mongoSegmentsDocument = TestPrepare.findOneInCollection(uuid, TestPrepare.getCurrSegmentsCollection());
        segmentsDocument = SegmentsDocument.fromMongoObject(mongoSegmentsDocument);
        segmentsDocument.setId(uuid);

        final DBObject mongoCookiesDocument = TestPrepare.findOneInCollection(uuid, TestPrepare.getCookiesCollection());
        cookiesDocument = CookiesDocument.fromMongoObject(mongoCookiesDocument);
        cookiesDocument.setId(uuid);

        events = new ArrayList<>();

        TestPrepare.deleteArchivedFiles();
        TestPrepare.deleteCruncherLogFiles();
    }

    @Dado("^que não existe a collection de impressões para o mês atual$")
    public void removeCurrViewsCollection() throws Exception {
        final String currViewsCollection = TestPrepare.getCurrViewsCollection();
        if (TestPrepare.collectionExists(currViewsCollection)) {
            TestPrepare.deleteCollection(currViewsCollection);
        }
    }

    @Dado("^que já existe a collection de impressões para o mês atual$")
    public void createIfNotExistsCurrViewsCollection() throws Exception {
        final String currViewsCollection = TestPrepare.getCurrViewsCollection();
        if (!TestPrepare.collectionExists(currViewsCollection)) {
            TestPrepare.createCollection(currViewsCollection);
        }
    }

    @Dado("^que não existe nenhum registro de cookie para um usuário expecífico$")
    public void removeCookiesDataFromMongo() throws Exception {
        TestPrepare.removeFromCollection(cookiesDocument.getId(), TestPrepare.getCookiesCollection());
    }

    @Dado("^que não existe nenhum registro de impressão para um usuário expecífico$")
    public void removePageViewsDataFromMongo() throws Exception {
        TestPrepare.removeFromCollection(viewsDocument.getId(), TestPrepare.getCurrViewsCollection());
    }

    @Dado("^que já existe algum registro de impressão para um usuário expecífico$")
    public void createPageViewsDataOnMongo() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String todayAsString = new DecimalFormat("00").format(today);
        final String randomGroup = CollectionUtils.randomElement(viewsDocument.getGroupsPerDayCounter().get(todayAsString).keySet());

        final ViewsDocument processedViews = new ViewsDocument();
        processedViews.setId(viewsDocument.getId());

        TestPrepare.simulatePageViews(processedViews, cookiesDocument, today, randomGroup);
        TestPrepare.saveInCollection(processedViews, TestPrepare.getCurrViewsCollection());

        viewsDocument.addGroupViews(processedViews.getGroupsPerDayCounter());
    }

    @Dado("^que já existe algum registro de impressão para um usuário expecífico para um dia diferente$")
    public void createPageViewsDataOnMongoForAnotherDay() throws Exception {
        final String today = new DecimalFormat("00").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        final int anotherDay = TestPrepare.getAnotherDay(viewsDocument.getGroupsPerDayCounter().keySet());
        final String randomGroup = CollectionUtils.randomElement(viewsDocument.getGroupsPerDayCounter().get(today).keySet());

        final ViewsDocument processedViews = new ViewsDocument();
        processedViews.setId(viewsDocument.getId());

        TestPrepare.simulatePageViews(processedViews, cookiesDocument, anotherDay, randomGroup);
        TestPrepare.saveInCollection(processedViews, TestPrepare.getCurrViewsCollection());

        viewsDocument.addGroupViews(processedViews.getGroupsPerDayCounter());
    }

    @Dado("^que já existe algum registro de impressão para um usuário expecífico para um sinal diferente$")
    public void createPageViewsDataOnMongoForAnotherGroup() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String todayAsString = new DecimalFormat("00").format(today);
        final Set<String> allGroups = Constants.config.getCruncherUrlGroups().keySet();
        final Set<String> chosenGroups = viewsDocument.getGroupsPerDayCounter().get(todayAsString).keySet();
        final Collection<String> freeGroups = CollectionUtils.subtract(allGroups, chosenGroups);
        final String newGroup = CollectionUtils.randomElement(freeGroups);

        final ViewsDocument processedViews = new ViewsDocument();
        processedViews.setId(viewsDocument.getId());

        TestPrepare.simulatePageViews(processedViews, cookiesDocument, today, newGroup);
        TestPrepare.saveInCollection(processedViews, TestPrepare.getCurrViewsCollection());

        viewsDocument.addGroupViews(processedViews.getGroupsPerDayCounter());
    }

    @Dado(  "^que exite um arquivo com eventos de impressão para um usuário expecífico" +
            "|que existe algum arquivo a ser processado que está na whitelist$")
    public void createPageViewsDefaultFile() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());

        final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
        TestPrepare.createArchivedFile(event);

        events.add(event);
    }

    @Dado("^que exite um arquivo com eventos de impressão para um usuário expecífico com URLs não classificadas$")
    public void createPageViewsFileWithUnclassifiedUrls() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String invalidGroup = TestPrepare.generateUuid("");

        final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, invalidGroup);
        TestPrepare.createArchivedFile(event);

        events.add(event);
    }

    @Dado(  "^que exite um arquivo com eventos de perfil para um usuário expecífico$")
    public void createProfileDefaultFile() throws Exception {
        final String randomProfile = CollectionUtils.randomElement(TraitMapper.profileTraits.keySet()).toString();

        final ProfileEvent event = TestPrepare.simulateProfile(profileDocument, cookiesDocument, randomProfile);
        TestPrepare.createArchivedFile(event);
    }

    @Dado("^que exite um arquivo com eventos de perfil para um usuário expecífico com dados não configurados$")
    public void createUselessProfileFile() throws Exception {
        final String uselessProfile = TestPrepare.generateUuid("");
        final ProfileEvent event = TestPrepare.simulateProfile(profileDocument, cookiesDocument, uselessProfile);
        TestPrepare.createArchivedFile(event);
    }

    @Dado("^que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <(.*)>$")
    public void createProfileDefinedFile(String profile) throws Exception {
        final ProfileParameter profileParameter;

        switch (profile.toLowerCase(new Locale("pt", "BR"))) {

        case "estado":
            profileParameter = ProfileParameter.state;
            break;

        case "cidade":
            profileParameter = ProfileParameter.city;
            break;

        case "distrito":
            profileParameter = ProfileParameter.area;
            break;

        case "profissão":
            profileParameter = ProfileParameter.jobFunction;
            break;

        case "ramo de atuação":
            profileParameter = ProfileParameter.jobIndustry;
            break;

        case "sexo":
            profileParameter = ProfileParameter.gender;
            break;

        case "cep":
            profileParameter = ProfileParameter.zipcode;
            break;

        case "estado civil":
            profileParameter = ProfileParameter.maritalStatus;
            break;

        case "grau de escolaridade":
            profileParameter = ProfileParameter.education;
            break;

        case "renda familiar":
            profileParameter = ProfileParameter.householdIncome;
            break;

        case "interesse":
            profileParameter = ProfileParameter.interest;
            break;

        case "data de nascimento":
            profileParameter = ProfileParameter.birth;
            break;

        case "idade":
            profileParameter = ProfileParameter.age;
            break;

        default:
            profileParameter = ProfileParameter.valueOf(profile);
            break;
        }

        final String profileName = profileParameter.toString();
        final ProfileEvent event = TestPrepare.simulateProfile(profileDocument, cookiesDocument, profileName);
        TestPrepare.createArchivedFile(event);
    }

    @Dado("^que não existe nenhum registro de perfil para um usuário expecífico$")
    public void removeProfileDataFromMongo() throws Exception {
        TestPrepare.removeFromCollection(profileDocument.getId(), TestPrepare.getProfileCollection());
    }

    @Dado("^que já existe algum registro de perfil para um usuário expecífico$")
    public void createProfileDataOnMongo() throws Exception {
        final String randomProfile = CollectionUtils.randomElement(TraitMapper.profileTraits.keySet()).toString();
        final ProfileDocument processedProfile = (profileDocument.getUpdated() == null) ? profileDocument : new ProfileDocument();
        processedProfile.setId(profileDocument.getId());

        TestPrepare.simulateProfile(processedProfile, cookiesDocument, randomProfile);
        TestPrepare.saveInCollection(processedProfile, TestPrepare.getProfileCollection());
    }

    @Dado("^que não existe a collection de segmentos para o mês atual$")
    public void removeCurrSegmentsCollection() throws Exception {
        final String currSegmentsCollection = TestPrepare.getCurrSegmentsCollection();
        if (TestPrepare.collectionExists(currSegmentsCollection)) {
            TestPrepare.deleteCollection(currSegmentsCollection);
        }
    }

    @Dado("^que já existe a collection de segmentos para o mês atual$")
    public void createIfNotExistsCurrSegmentsCollection() throws Exception {
        final String currSegmentsCollection = TestPrepare.getCurrSegmentsCollection();
        if (!TestPrepare.collectionExists(currSegmentsCollection)) {
            TestPrepare.createCollection(currSegmentsCollection);
        }
    }

    @Dado("^que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico$")
    public void createSegmentsDefaultFile() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomPartner = CollectionUtils.randomElement(Constants.config.getCruncherCookieParameters().keySet());

        final List<ViewsEvent> viewsEvents = TestPrepare.simulateSegments(viewsDocument, segmentsDocument, cookiesDocument, today, randomPartner);
        TestPrepare.createArchivedFile(viewsEvents.toArray(new BtEvent[0]));
    }

    @Dado("^que não existe nenhum registro de segmento para um usuário expecífico$")
    public void removeSegmentsDataFromMongo() throws Exception {
        TestPrepare.removeFromCollection(segmentsDocument.getId(), TestPrepare.getCurrSegmentsCollection());
    }

    @Dado("^que já existe algum registro de segmento para um usuário expecífico para um dia diferente$")
    public void createSegmentsDataOnMongoForAnotherDay() throws Exception {
        final Map<String, Map<String, Set<String>>> partnerSegmentsPerDay = segmentsDocument.getPartnerSegmentsPerDay();
        final int anotherDay = TestPrepare.getAnotherDay(partnerSegmentsPerDay.keySet());
        final String randomPartner = CollectionUtils.randomElement(Constants.config.getCruncherCookieParameters().keySet());

        final ViewsDocument processedViewsDoc = new ViewsDocument();
        final SegmentsDocument processedSegmentsDoc = new SegmentsDocument();
        final CookiesDocument processedCookiesDoc = (cookiesDocument.getUpdated() == null) ? cookiesDocument : new CookiesDocument();
        processedViewsDoc.setId(viewsDocument.getId());
        processedSegmentsDoc.setId(segmentsDocument.getId());
        processedCookiesDoc.setId(cookiesDocument.getId());

        TestPrepare.simulateSegments(processedViewsDoc, processedSegmentsDoc, processedCookiesDoc, anotherDay, randomPartner);
        TestPrepare.saveInCollection(processedSegmentsDoc, TestPrepare.getCurrSegmentsCollection());

        segmentsDocument.addPartnerSegmentsPerDay(processedSegmentsDoc.getPartnerSegmentsPerDay());
    }

    @Dado("^que já existe algum registro de segmento para um usuário expecífico com mesmo dia e para um cliente diferente$")
    public void createSegmentsDataOnMongoForAnotherPartner() throws Exception {
        final int sameDay = Integer.valueOf(CollectionUtils.randomElement(segmentsDocument.getPartnerSegmentsPerDay().keySet()));
        final Collection<String> eligiblePartners = CollectionUtils.subtract(Constants.config.getCruncherCookieParameters().keySet(), segmentsDocument.getPartnerSegmentsPerDay().keySet());
        final String anotherPartner = CollectionUtils.randomElement(eligiblePartners);
        Objects.requireNonNull(anotherPartner, "não há parceiros disponiveis");

        final ViewsDocument processedViewsDoc = new ViewsDocument();
        final SegmentsDocument processedSegmentsDoc = new SegmentsDocument();
        final CookiesDocument processedCookiesDoc = (cookiesDocument.getUpdated() == null) ? cookiesDocument : new CookiesDocument();
        processedViewsDoc.setId(viewsDocument.getId());
        processedSegmentsDoc.setId(segmentsDocument.getId());
        processedCookiesDoc.setId(cookiesDocument.getId());

        TestPrepare.simulateSegments(processedViewsDoc, processedSegmentsDoc, processedCookiesDoc, sameDay, anotherPartner);
        TestPrepare.saveInCollection(processedSegmentsDoc, TestPrepare.getCurrSegmentsCollection());

        segmentsDocument.addPartnerSegmentsPerDay(processedSegmentsDoc.getPartnerSegmentsPerDay());
    }

    @Dado("^que já existe algum registro de segmento para um usuário expecífico com mesmo dia e mesmo cliente$")
    public void createSegmentsDataOnMongo() throws Exception {
        final Map<String, Map<String, Set<String>>> partnerSegmentsPerDay = segmentsDocument.getPartnerSegmentsPerDay();
        final String sameDay = CollectionUtils.randomElement(partnerSegmentsPerDay.keySet());
        final String samePartner = CollectionUtils.randomElement(partnerSegmentsPerDay.get(sameDay).keySet());

        final ViewsDocument processedViewsDoc = new ViewsDocument();
        final SegmentsDocument processedSegmentsDoc = new SegmentsDocument();
        final CookiesDocument processedCookiesDoc = (cookiesDocument.getUpdated() == null) ? cookiesDocument : new CookiesDocument();
        processedViewsDoc.setId(viewsDocument.getId());
        processedSegmentsDoc.setId(segmentsDocument.getId());
        processedCookiesDoc.setId(cookiesDocument.getId());

        TestPrepare.simulateSegments(processedViewsDoc, processedSegmentsDoc, processedCookiesDoc, Integer.valueOf(sameDay), samePartner);
        TestPrepare.saveInCollection(processedSegmentsDoc, TestPrepare.getCurrSegmentsCollection());

        segmentsDocument.addPartnerSegmentsPerDay(processedSegmentsDoc.getPartnerSegmentsPerDay());
    }

    @Dado("^que o arquivo a ser processado possui um erro na primeira linha$")
    public void createPageViewsFileWithErrorIn1stLine() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());

        final ViewsEvent validEvent = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
        final ViewsEvent invalidEvent = TestPrepare.newInvalidEvent(viewsDocument.getId(), ViewsEvent.class);

        TestPrepare.createArchivedFile(invalidEvent, validEvent);

        events.add(validEvent);
        events.add(invalidEvent);
    }

    @Dado("^que o arquivo a ser processado possui um erro na ultima linha$")
    public void createPageViewsFileWithErrorInLastLine() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());

        final ViewsEvent validEvent = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
        final ViewsEvent invalidEvent = TestPrepare.newInvalidEvent(viewsDocument.getId(), ViewsEvent.class);

        TestPrepare.createArchivedFile(validEvent, invalidEvent);

        events.add(validEvent);
        events.add(invalidEvent);
    }

    @Dado("^que o arquivo a ser processado possui um erro em todas as linhas$")
    public void createFileWithErrorInAllLines() throws Exception {
        int numOfEvents = new Random().nextInt(5) + 1;
        while (numOfEvents > 0) {
            final ViewsEvent event = TestPrepare.newInvalidEvent(viewsDocument.getId(), ViewsEvent.class);
            events.add(event);
            numOfEvents--;
        }
        TestPrepare.createArchivedFile(events.toArray(new BtEvent[0]));
    }

    @Dado("^que o arquivo a ser processado está vazio$")
    public void createEmptyFile() throws Exception {
        events.clear();
        TestPrepare.createArchivedFile(events.toArray(new BtEvent[0]));
    }

    @Dado("^que não há nenhum arquivo a ser processado$")
    public void deleteArchivedFiles() throws Exception {
        TestPrepare.deleteArchivedFiles();
    }

    @Dado("^que a quantidade de arquivos a processar é menor que a quantidade máxima permitida$")
    public void createBatchSizeMinusOnePageViewsFiles() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());
        final int batchSizeMinusOne = Constants.config.getCruncherBatchSize() - 1;

        while (TestPrepare.countArchivedFiles() < batchSizeMinusOne) {
            final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
            TestPrepare.createArchivedFile(event);

            events.add(event);
        }
    }

    @Dado("^que a quantidade de arquivos a processar é maior que a quantidade máxima permitida$")
    public void createBatchSizePlusOnePageViewsFiles() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());
        final int batchSizePlusOne = Constants.config.getCruncherBatchSize() + 1;

        while (TestPrepare.countArchivedFiles() < batchSizePlusOne) {
            final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
            TestPrepare.createArchivedFile(event);

            events.add(event);
        }
    }

    @Dado("^que a quantidade de arquivos a processar é igual a quantidade máxima permitida$")
    public void createBatchSizePageViewsFiles() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());
        final int batchSize = Constants.config.getCruncherBatchSize();

        while (TestPrepare.countArchivedFiles() < batchSize) {
            final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
            TestPrepare.createArchivedFile(event);

            events.add(event);
        }
    }

    @Dado("^que existe algum arquivo a ser processado que não está na whitelist$")
    public void createPageViewsFileNotProcessable() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomGroup = CollectionUtils.randomElement(Constants.config.getCruncherUrlGroups().keySet());

        final ViewsEvent event = TestPrepare.simulatePageViews(viewsDocument, cookiesDocument, today, randomGroup);
        TestPrepare.createArchivedFileNotProcessable(Arrays.asList(event));

        events.add(event);
    }

    @Dado("^que já existe algum registro de cookie para um usuário expecífico$")
    public void createCookieDataOnMongo() throws Exception {
        final int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final String randomPartner = CollectionUtils.randomElement(Constants.config.getCruncherCookieParameters().keySet());

        final ViewsDocument processedViewsDoc = new ViewsDocument();
        final SegmentsDocument processedSegmentsDoc = new SegmentsDocument();
        final CookiesDocument processedCookiesDoc = (cookiesDocument.getUpdated() == null) ? cookiesDocument : new CookiesDocument();
        processedViewsDoc.setId(viewsDocument.getId());
        processedSegmentsDoc.setId(viewsDocument.getId());
        processedCookiesDoc.setId(viewsDocument.getId());

        TestPrepare.simulateSegments(processedViewsDoc, processedSegmentsDoc, processedCookiesDoc, today, randomPartner);
        TestPrepare.saveInCollection(processedCookiesDoc, TestPrepare.getCookiesCollection());
    }

    @Dado("^que exite um arquivo com eventos de impressão que configuram em segmentos grudentos para um usuário expecífico$")
    public void createStickySegmentsDefaultFile() throws Exception {
        final String partner = "dfp";
        final ViewsEvent event = TestPrepare.simulateStickySegments(viewsDocument, segmentsDocument, cookiesDocument, partner);
        TestPrepare.createArchivedFile(event);
    }

    @Dado("^que já existe algum registro de segmento grudento para um usuário expecífico$")
    public void createStickySegmentsDataOnMongo() throws Exception {
        final String partner = "dfp";

        final ViewsDocument processedViewsDoc = new ViewsDocument();
        final CookiesDocument processedCookiesDoc = new CookiesDocument();
        processedViewsDoc.setId(viewsDocument.getId());
        processedCookiesDoc.setId(viewsDocument.getId());

        TestPrepare.simulateStickySegments(processedViewsDoc, segmentsDocument, processedCookiesDoc, partner);
        TestPrepare.saveInCollection(segmentsDocument, TestPrepare.getCurrSegmentsCollection());

        cookiesDocument.addPartnerSegments(partner, processedCookiesDoc.getPartnerSegments(partner));
        cookiesDocument.setUpdated(processedCookiesDoc.getUpdated());
    }
    
    
}
