package uol.bt.cruncher.test.helper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.Transformer;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.commons.test.helper.DroolsFactory;
import uol.bt.commons.test.helper.MongoHelper;
import uol.bt.commons.test.helper.RemoteTestHelper;
import uol.bt.commons.test.profile.ProfileParameter;
import uol.bt.commons.test.profile.TraitMapper;
import uol.bt.cruncher.behavior.Behavior;
import uol.bt.cruncher.behavior.BtCollectionFactory;
import uol.bt.cruncher.behavior.RemoteConfiguration;
import uol.bt.cruncher.test.classifier.URLClassifier;
import uol.bt.cruncher.test.domain.BtEvent;
import uol.bt.cruncher.test.domain.MongoDocument;
import uol.bt.cruncher.test.domain.cookies.CookiesDocument;
import uol.bt.cruncher.test.domain.profiles.ProfileDocument;
import uol.bt.cruncher.test.domain.profiles.ProfileEvent;
import uol.bt.cruncher.test.domain.segments.SegmentsDocument;
import uol.bt.cruncher.test.domain.views.ViewsDocument;
import uol.bt.cruncher.test.domain.views.ViewsEvent;
import uol.bt.cruncher.test.reconf.Config;
import uol.bt.cruncher.test.util.CollectionUtils;
import uol.bt.cruncher.test.util.Constants;
import uol.ecommerce.commons.util.zip.CodecUtils;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class TestPrepare {

    private static final Pattern CRUNCHER_PID_START_PATTERN = Pattern.compile("^Started with pid: \\[([0-9]+)\\]$");

    private static final Config config = Constants.config;
    private static final BtCollectionFactory collectionFactory = new BtCollectionFactory(ConfigurationRepositoryFactory.get(RemoteConfiguration.class));

    private static final Map<String, KnowledgeBase> partnerRules = new HashMap<>();
    static {
        for (Entry<String, Map<String, String>> entry : config.getCruncherCookieParameters().entrySet()) {
            final String partner = entry.getKey();
            final String drools = entry.getValue().get("drools");
            partnerRules.put(partner, DroolsFactory.newKnowledgeBaseFromString(drools));
        }
    }


    public static void createArchivedFile(BtEvent...events) throws Exception {
        final String name = CollectionUtils.randomElement(config.getCruncherFilenamePatterns()).replaceAll("\\*", "test");
        final String countFileCmd = String.format("find %s -name \"%s*\" |wc -l", Constants.CRUNCHER_ARQUIVED_EVENTS_DIR, name);
        final String countFileResult = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, countFileCmd).assertOk().getBodyAsString();
        final String fileName = String.format("%s%s%s.json", Constants.CRUNCHER_ARQUIVED_EVENTS_DIR, name, countFileResult);

        final StringBuilder contentBuilder = new StringBuilder();
        final Gson gson = new Gson();
        for (BtEvent event : events) {
            final String eventAsJson = (event.isValid()) ? gson.toJson(event) : generateUuid("") + gson.toJson(event);
            contentBuilder.append(eventAsJson).append('\n');
        }
        if (contentBuilder.length() > 0) {
            contentBuilder.deleteCharAt(contentBuilder.length() - 1);
        }
        final String fileContent = contentBuilder.toString();

        final String echoCmd = String.format("echo '%1$s' > %2$s; chown bt-cruncher.bt %2$s; chmod 777 %2$s", fileContent, fileName);

        RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, echoCmd).assertOk();
    }

    public static void createArchivedFileNotProcessable(List<? extends BtEvent> events) throws Exception {
        final Pattern asteriskPattern = Pattern.compile("\\*");
        final StringBuilder patternBuilder = new StringBuilder();
        for (String filePattern : config.getCruncherFilenamePatterns()) {
            patternBuilder.append(asteriskPattern.matcher(filePattern).replaceAll(".*")).append("|");
        }
        if (patternBuilder.length() > 0) {
            patternBuilder.deleteCharAt(patternBuilder.length() - 1);
        }

        final Pattern whitelistPattern = Pattern.compile(patternBuilder.toString());
        String name = null;

        do {
            name = generateUuid(".");
        } while (whitelistPattern.matcher(name).matches());

        final String fileName = String.format("%s%s.json", Constants.CRUNCHER_ARQUIVED_EVENTS_DIR, name);

        final StringBuilder contentBuilder = new StringBuilder();
        final Gson gson = new Gson();
        for (BtEvent event : events) {
            final String eventAsJson = (event.isValid()) ? gson.toJson(event) : generateUuid("") + gson.toJson(event);
            contentBuilder.append(eventAsJson).append('\n');
        }
        if (contentBuilder.length() > 0) {
            contentBuilder.deleteCharAt(contentBuilder.length() - 1);
        }
        final String fileContent = contentBuilder.toString();

        final String echoCmd = String.format("echo '%1$s' > %2$s; chown bt-cruncher.bt %2$s; chmod 777 %2$s", fileContent, fileName);

        RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, echoCmd).assertOk().getBodyAsString();
    }

    public static void deleteArchivedFiles() throws Exception {
        final StringBuilder deleteCmd = new StringBuilder("find ").append(Constants.CRUNCHER_ARQUIVED_EVENTS_DIR)
                .append(" \\( -name \"*.json\"");
        for (String filePattern : config.getCruncherFilenamePatterns()) {
            deleteCmd.append(" -o -name \"").append(filePattern).append("\"");
        }
        deleteCmd.append(" \\) -exec rm -f {} \\;");

        RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, deleteCmd.toString()).assertOk();
    }

    public static int countArchivedFiles() throws Exception {
        final StringBuilder countCmd = new StringBuilder("find ").append(Constants.CRUNCHER_ARQUIVED_EVENTS_DIR).append(" \\(");
        int i = 0;
        for (String filePattern : config.getCruncherFilenamePatterns()) {
            if (i++ > 0) {
                countCmd.append(" -o");
            }
            countCmd.append(" -name \"").append(filePattern).append("\"");
        }
        countCmd.append(" \\) | wc -l");

        final String result = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, countCmd.toString())
                .assertOk()
                .getBodyAsString();

        return Integer.valueOf(result);
    }

    public static void deleteCruncherLogFiles() throws Exception {
        final String deleteCmd = "rm -f /export/logs/bt-cruncher/bt-cruncher*.log";
        RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, deleteCmd).assertOk();
    }

    public static int countMissedEvents(String userId) throws Exception {
        final String countCmd = String.format("grep -c '%s' /export/logs/bt-cruncher/bt-cruncher-missed.log", userId);
        final String result = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, countCmd).assertOk().getBodyAsString();

        return Integer.valueOf(result);
    }

    public static int countProcessedFiles() throws Exception {
        if (notExistsArchivedFiles()) {
            return 0;
        }

        final String countCmd = "grep -oE \"foram processados \\[([0-9]+)\\] arquivos\" /export/logs/bt-cruncher/bt-cruncher.log |" +
                "grep -oE \"[0-9]+\" |" +
                "tail -n 1";
        final String countResult = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, countCmd)
                .assertOk()
                .getBodyAsString();

        return Integer.valueOf(countResult);
    }

    public static boolean notExistsArchivedFiles() throws Exception {
        final String grepCmd = "grep -c \"nao foram encontrados arquivos de eventos para processamento\" /export/logs/bt-cruncher/bt-cruncher.log";
        final String grepResult = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, grepCmd)
                .assertOk()
                .getBodyAsString();

        return Integer.valueOf(grepResult) > 0;
    }

    public static boolean existsArchivedFilesNotWhitelist() throws Exception {
        final String grepCmd = "grep -c \"arquivo invalido \\[nao esta na whitelist\\]\" /export/logs/bt-cruncher/bt-cruncher.log";
        final String grepResult = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, grepCmd)
                .assertOk()
                .getBodyAsString();

        return Integer.valueOf(grepResult) > 0;
    }

    public static String getCookiesCollection() {
        return "cookies";
    }

    public static String getProfileCollection() {
        return "profile";
    }

    public static String getCurrSegmentsCollection() {
        return getSegmentsCollection(new Date());
    }

    public static String getSegmentsCollection(Date date) {
        return String.format("segments_%s", getDateCollection(date));
    }

    public static String getCurrViewsCollection() {
        return getViewsCollection(new Date());
    }

    public static String getViewsCollection(Date date) {
        return String.format("views_%s", getDateCollection(date));
    }

    public static String getDateCollection(Date date) {
        return new SimpleDateFormat("MMyyyy").format(date);
    }

    public static void deleteCollection(String collectionName) throws Exception {
        MongoHelper.dropCollection(collectionName);
    }

    public static void createCollection(String collectionName) throws Exception {
        MongoHelper.createCollection(collectionName);
    }

    public static boolean collectionExists(String collectionName) throws Exception {
        return MongoHelper.collectionExists(collectionName);
    }

    public static DBObject findOneInCollection(String id, String collection) throws Exception {
        return MongoHelper.findOneInCollection(id, collection);
    }

    public static WriteResult removeFromCollection(String id, String collection) throws Exception {
        return MongoHelper.removeFromCollection(id, collection);
    }

    public static WriteResult saveInCollection(MongoDocument document, String collection) throws Exception {
        return MongoHelper.saveInCollection(document.toMongoObject(), collection);
    }

    public static void runCruncher() throws Exception {
        final String startMsg = RemoteTestHelper
                .shell(config.getRemoteAdminTestDomain(), "/opt/bt-cruncher/scripts/bt-cruncher.sh start")
                .assertOk()
                .getBodyAsString();

        final Matcher matcher = CRUNCHER_PID_START_PATTERN.matcher(startMsg);

        if (matcher.matches()) {
            boolean cruncherIsRunning = true;

            while (cruncherIsRunning) {
                Thread.sleep(1000);
                final String pidCount = RemoteTestHelper
                        .shell(config.getRemoteAdminTestDomain(), String.format("ps %s|grep -v PID|wc -l", matcher.group(1)))
                        .assertOk()
                        .getBodyAsString();
                cruncherIsRunning = Integer.valueOf(pidCount) > 0;
            }
        } else {
            throw new IllegalStateException(String.format("cruncher nao inicializado: %s", startMsg));
        }
    }

    public static String generateUuid(CharSequence separator) {
        return UUID.randomUUID().toString().replace("-", separator);
    }

    public static ViewsEvent simulatePageViews(ViewsDocument viewsDoc, CookiesDocument cookiesDoc, int day, String group) throws Exception {
        final Map<String, Set<String>> urlGroups = config.getCruncherUrlGroups();
        final URLClassifier urlClassifier = new URLClassifier(urlGroups);
        final Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, day);

        final Set<String> urls = urlGroups.get(group);
        final String url = (CollectionUtils.isNotEmpty(urls))
                ? "http://" + CollectionUtils.randomElement(urls).replaceAll("\\*", generateUuid("/"))
                : "http://" + generateUuid("/");

        final ViewsEvent event = newBasicEvent(viewsDoc.getId(), ViewsEvent.class);
        event.setTimestamp(date.getTime());
        event.setPageId(url);

        for (String otherGroup : urlClassifier.classificationFor(url)) {
            viewsDoc.addGroupViews(day, otherGroup, 1);
        }

        if (MapUtils.isNotEmpty(viewsDoc.getGroupsPerDayCounter())) {
            updateCookiesDocumentFromPageViews(viewsDoc, cookiesDoc);
        }

        cookiesDoc.setUpdated(getCruncherServerDate());

        return event;
    }

    private static void updateCookiesDocumentFromPageViews(ViewsDocument viewsDocument, CookiesDocument cookiesDocument) {
        final Behavior behavior = new Behavior(collectionFactory);
        final String viewsCollection = getCurrViewsCollection();

        behavior.summarizeViews(viewsCollection, viewsDocument.toMongoObject());

        updateBehavior(behavior);

        cookiesDocument.addPartnerSegments(behavior.getAllCurrentAndStickySegments());
    }

    private static void updateBehavior(Behavior behavior) {
        for (Entry<String, KnowledgeBase> entry : partnerRules.entrySet()) {
            final StatefulKnowledgeSession session = entry.getValue().newStatefulKnowledgeSession();
            try {
                session.setGlobal("partner", entry.getKey());
                session.setGlobal("behaviorals", Collections.singletonList(behavior));
                session.fireAllRules();
            } finally {
                session.dispose();
            }
        }
    }

    public static ProfileEvent simulateProfile(ProfileDocument profileDoc, CookiesDocument cookiesDoc, String...profiles) throws Exception {
        final Map<String, Object> cookieMap = new HashMap<>();
        final Set<String> traits = new TreeSet<>();

        for (String profileName : profiles) {
            if (cookieMap.containsKey(profileName)) {
                continue;
            }

            try {
                final ProfileParameter profile = ProfileParameter.valueOf(profileName);
                final String profileValue = CollectionUtils.randomElement(TraitMapper.profileTraits.get(profile).keySet());
                Objects.requireNonNull(profileValue);
                cookieMap.put(profileName, profileValue);
                traits.add(TraitMapper.profileTraits.get(profile).get(profileValue).getBtCode());
            } catch (Exception e) {
                final String profileValue = generateUuid("");
                cookieMap.put(profileName, profileValue);
            }
        }

        final String cookieJson = new Gson().toJson(cookieMap);
        final String cookieValue = Hex.encodeHexString(CodecUtils.zip(cookieJson));

        final ProfileEvent event = newBasicEvent(profileDoc.getId(), ProfileEvent.class);
        event.setCookieValue(cookieValue);

        if (CollectionUtils.isNotEmpty(traits)) {
            profileDoc.setTraits(traits);
            cookiesDoc.addPartnerSegments("dfp", CollectionUtils.collect(traits, new Transformer() {
                @Override
                public Object transform(Object input) {
                    int code = Integer.parseInt(input.toString());

                    if (code >= 113 && code <= 117) {
                        return "103";
                    } else if (code >= 118 && code <= 124) {
                        return "104";
                    } else if (code >= 125 && code <= 134) {
                        return "105";
                    } else if (code >= 135 && code <= 144) {
                        return "106";
                    } else if (code >= 145 && code <= 154) {
                        return "107";
                    } else if (code >= 155 && code <= 164) {
                        return "108";
                    } else if (code >= 165 && code <= 199) {
                        return "109";
                    } else {
                        return input;
                    }
                }
            }));
        }

        final Date updatedDate = getCruncherServerDate();
        profileDoc.fromCookieValue(cookieValue);
        profileDoc.setUpdated(updatedDate);
        cookiesDoc.setUpdated(updatedDate);

        return event;
    }

    public static List<ViewsEvent> simulateSegments(ViewsDocument viewsDoc, SegmentsDocument segmentsDoc, CookiesDocument cookiesDoc, int day, String partner) throws Exception {
        final Set<String> urlGroups = config.getCruncherUrlGroups().keySet();
        final List<ViewsEvent> viewsEvents = new ArrayList<>();

        while (CollectionUtils.isEmpty(cookiesDoc.getPartnerSegments(partner)) ||
                !CollectionUtils.containsAny(cookiesDoc.getPartnerSegments(partner), urlGroups)) {
            final String randomGroup = CollectionUtils.randomElement(urlGroups);
            final ViewsEvent event = simulatePageViews(viewsDoc, cookiesDoc, day, randomGroup);
            viewsEvents.add(event);
        }

        for (Entry<String, Set<String>> entry : cookiesDoc.getPartnerSegments().entrySet()) {
            final String partnerEntry = entry.getKey();
            final Set<String> segmentEntries = entry.getValue();

            for (String segmentEntry : segmentEntries) {
                if (urlGroups.contains(segmentEntry)) {
                    segmentsDoc.addPartnerSegment(day, partnerEntry, segmentEntry);
                }
            }
        }

        return viewsEvents;
    }

    public static ViewsEvent simulateStickySegments(ViewsDocument viewsDoc, SegmentsDocument segmentsDoc, CookiesDocument cookiesDoc, String partner) throws Exception {
        final Calendar today = Calendar.getInstance();
        final Calendar before = Calendar.getInstance();
        final Set<String> groups = config.getCruncherUrlGroups().keySet();

        while (TimeUnit.DAYS.convert(today.getTimeInMillis() - before.getTimeInMillis(), TimeUnit.MILLISECONDS) <= 60) {
            for (String group : groups) {
                final Behavior behavior = new Behavior(collectionFactory);
                final int dayBefore = before.get(Calendar.DAY_OF_MONTH);
                final DBObject simulatedSegments =
                        new BasicDBObject(new DecimalFormat("00").format(dayBefore), new BasicDBObject(partner, Collections.singleton(group)));

                behavior.summarizeSegments(getSegmentsCollection(before.getTime()), simulatedSegments);

                updateBehavior(behavior);

                if (CollectionUtils.isNotEmpty(behavior.getStickySegments(partner))) {
                    final ViewsEvent event = simulatePageViews(viewsDoc, cookiesDoc, dayBefore, group);

                    for (String segment : cookiesDoc.getPartnerSegments(partner)) {
                        if (groups.contains(segment)) {
                            segmentsDoc.addPartnerSegment(dayBefore, partner, segment);
                        }
                    }

                    return event;
                }
            }

            before.add(Calendar.DAY_OF_MONTH, -1);
        }

        throw new RuntimeException("não há regra que define algum segmento grudento para o parceiro [" + partner + "] nos últimos 60 dias");
    }

    public static int getAnotherDay(Collection<String> days) {
        final DecimalFormat dayFormater = new DecimalFormat("00");
        final int lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int anotherDay = 1;

        while (days.contains(dayFormater.format(anotherDay)) && anotherDay <= lastDay) {
            anotherDay++;
        }

        if (anotherDay > lastDay) {
            throw new RuntimeException("not available days in " + days.toString());
        }

        return anotherDay;
    }

    public static <T extends BtEvent> T newBasicEvent(String userId, Class<T> classOfEvent) throws Exception {
        final T event = classOfEvent.newInstance();
        event.setSourceId("UOL");
        event.setTimestamp(new Date());
        event.setPageId("http://" + generateUuid("/"));
        event.setUserId(userId);
        event.setValid(true);

        return event;
    }

    public static <T extends BtEvent> T newInvalidEvent(String userId, Class<T> classOfEvent) throws Exception {
        final T event = newBasicEvent(userId, classOfEvent);
        event.setValid(false);

        return event;
    }

    public static Date getCruncherServerDate() throws Exception {
        final String dateCmd = "date +%s";
        final String dateResponse = RemoteTestHelper.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, dateCmd)
                .assertOk()
                .getBodyAsString();

        final long serverMillis = Long.valueOf(dateResponse) * 1000;

        return new Date(serverMillis);
    }
}
