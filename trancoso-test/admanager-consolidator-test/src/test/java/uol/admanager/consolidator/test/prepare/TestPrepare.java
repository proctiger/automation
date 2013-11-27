package uol.admanager.consolidator.test.prepare;

import java.io.File;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.math.NumberUtils;

import uol.admanager.consolidator.test.http.RemoteTestHttp;
import uol.admanager.consolidator.test.reconf.RemoteConfig;
import uol.admanager.dao.AdvertisementDao;
import uol.admanager.dao.ClickDao;
import uol.admanager.dao.ClickLogDao;
import uol.admanager.dao.CustomerDao;
import uol.admanager.dao.DimensionDao;
import uol.admanager.dao.PackSaleDao;
import uol.admanager.dao.SlotDao;
import uol.admanager.dao.TypeAdvertisementDao;
import uol.admanager.dao.ViewDao;
import uol.admanager.dao.ViewLogDao;
import uol.admanager.entity.Advertisement;
import uol.admanager.entity.Click;
import uol.admanager.entity.ClickLog;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.PackSale;
import uol.admanager.entity.Slot;
import uol.admanager.entity.View;
import uol.admanager.entity.ViewLog;
import uol.admanager.utils.CollectionUtils;

import com.google.gson.Gson;

public class TestPrepare {

    private static final String LEGACY_VIEWS_LINE_FORMAT = "[%s:%s:00:00 -0300] 200.217.210.133 /ads.imguol.com/x.gif?ch=%s&s=%s&c=%s&ad=%s&sa=%s&ty=%s&r=88986625364065 - 204";
    private static final String VIEWS_LINE_FORMAT = "[%s:%s:00:00 -0300] 200.217.210.133 /imp.ads.imguol.com/view?req=88986625364065%s - 204 0.000 -";
    private static final String CUSTOMER_NAME_PREFIX = "Teste Automatizado - C";
    private static final String ADVERTISEMENT_NAME_PREFIX = "Teste Automatizado - Ad";
    private static final Pattern JOB_PID_START_PATTERN = Pattern.compile("^Started with pid: \\[([0-9]+)\\]$");
    private static final Pattern JOB_PID_STOP_PATTER_PATTERN = Pattern.compile("Killing \\[(.+)\\]|(Error: no process to kill)");;

    public static String createViewsLegacyFile(Collection<Dimension> dims) throws Exception {
        return new LegacyViewsFileCreator() {
            @Override
            String buildLine(Dimension dim, Slot slot, SimpleDateFormat dateFormat, DecimalFormat hourFormat) {
                return String.format(LEGACY_VIEWS_LINE_FORMAT,
                        dateFormat.format(dim.getDate()),
                        hourFormat.format(dim.getHour()),
                        dim.getChannelId(),
                        slot.getDescription(),
                        dim.getCustomerId(),
                        dim.getAdvertisementId(),
                        dim.getPackSaleId(),
                        dim.getTypeAdvertisementId());
            }
        }.create(dims);
    }

    public static String createViewsLegacyFileWithInvalidParameter(Collection<Dimension> dims, final String invalidParameterName) throws Exception {
        return new LegacyViewsFileCreator() {
            @Override
            String buildLine(Dimension dim, Slot slot, SimpleDateFormat dateFormat, DecimalFormat hourFormat) {
                return String.format(LEGACY_VIEWS_LINE_FORMAT,
                        dateFormat.format(dim.getDate()),
                        hourFormat.format(dim.getHour()),
                        ("ch".equals(invalidParameterName)) ? "\"invalid param\"" :dim.getChannelId(),
                        ("s".equals(invalidParameterName)) ? slot.getChannelId() : slot.getDescription(),
                        ("c".equals(invalidParameterName)) ? "\"invalid param\"" : dim.getCustomerId(),
                        ("ad".equals(invalidParameterName)) ? "\"invalid param\"" : dim.getAdvertisementId(),
                        ("sa".equals(invalidParameterName)) ? "\"invalid param\"" : dim.getPackSaleId(),
                        ("ty".equals(invalidParameterName)) ? "\"invalid param\"" : dim.getTypeAdvertisementId());
            }
        }.create(dims);
    }

    public static String createViewsLegacyFileWithEmptyParameter(Collection<Dimension> dims, final String emptyParameterName) throws Exception {
        return new LegacyViewsFileCreator() {
            @Override
            String buildLine(Dimension dim, Slot slot, SimpleDateFormat dateFormat, DecimalFormat hourFormat) {
                return String.format(LEGACY_VIEWS_LINE_FORMAT,
                        dateFormat.format(dim.getDate()),
                        hourFormat.format(dim.getHour()),
                        ("ch".equals(emptyParameterName)) ? "\"\"" :dim.getChannelId(),
                        ("s".equals(emptyParameterName)) ? "\"\"" : slot.getDescription(),
                        ("c".equals(emptyParameterName)) ? "\"\"" : dim.getCustomerId(),
                        ("ad".equals(emptyParameterName)) ? "\"\"" : dim.getAdvertisementId(),
                        ("sa".equals(emptyParameterName)) ? "\"\"" : dim.getPackSaleId(),
                        ("ty".equals(emptyParameterName)) ? "\"\"" : dim.getTypeAdvertisementId());
            }
        }.create(dims);
    }

    public static String createViewsLegacyFileWithoutParameter(Collection<Dimension> dims, final String missingParameterName) throws Exception {
        return new LegacyViewsFileCreator() {
            @Override
            String buildLine(Dimension dim, Slot slot, SimpleDateFormat dateFormat, DecimalFormat hourFormat) {
                return String.format(LEGACY_VIEWS_LINE_FORMAT,
                        dateFormat.format(dim.getDate()),
                        hourFormat.format(dim.getHour()),
                        ("ch".equals(missingParameterName)) ? "" :dim.getChannelId(),
                        ("s".equals(missingParameterName)) ? "" : slot.getDescription(),
                        ("c".equals(missingParameterName)) ? "" : dim.getCustomerId(),
                        ("ad".equals(missingParameterName)) ? "" : dim.getAdvertisementId(),
                        ("sa".equals(missingParameterName)) ? "" : dim.getPackSaleId(),
                        ("ty".equals(missingParameterName)) ? "" : dim.getTypeAdvertisementId());
            }
        }.create(dims);
    }

    public static String createViewsFile(Collection<Dimension> dims) throws Exception {
        return new ViewsFileCreator() {
            @Override
            void buildAds(StringBuilder builder, Dimension dim) {
                builder.append("&ads=")
                .append("ad:").append(dim.getAdvertisementId()).append(";")
                .append("sa:").append(dim.getPackSaleId()).append(";")
                .append("s:").append(dim.getSlotId()).append(";");
            }
        }.create(dims);
    }

    public static String createViewsFileWithInvalidParameter(Collection<Dimension> dims, final String invalidParameterName) throws Exception {
        return new ViewsFileCreator() {
            @Override
            void buildAds(StringBuilder builder, Dimension dim) {
                if (dim.isValid()) {
                    builder.append("&ads=")
                    .append("ad:").append(dim.getAdvertisementId()).append(";")
                    .append("sa:").append(dim.getPackSaleId()).append(";")
                    .append("s:").append(dim.getSlotId()).append(";");
                } else {
                    builder.append("&ads=")
                    .append("ad:").append(("ad".equals(invalidParameterName)) ? "invalid value" : dim.getAdvertisementId()).append(";")
                    .append("sa:").append(("sa".equals(invalidParameterName)) ? "invalid value" : dim.getPackSaleId()).append(";")
                    .append("s:").append(("s".equals(invalidParameterName)) ? "invalid value" : dim.getSlotId()).append(";");
                }
            }
        }.create(dims);
    }

    public static String createViewsFileWithEmptyParameter(Collection<Dimension> dims, final String emptyParameterName) throws Exception {
        return new ViewsFileCreator() {
            @Override
            void buildAds(StringBuilder builder, Dimension dim) {
                if (dim.isValid()) {
                    builder.append("&ads=")
                    .append("ad:").append(dim.getAdvertisementId()).append(";")
                    .append("sa:").append(dim.getPackSaleId()).append(";")
                    .append("s:").append(dim.getSlotId()).append(";");
                } else {
                    builder.append("&ads=")
                    .append("ad:").append(("ad".equals(emptyParameterName)) ? "" : dim.getAdvertisementId()).append(";")
                    .append("sa:").append(("sa".equals(emptyParameterName)) ? "" : dim.getPackSaleId()).append(";")
                    .append("s:").append(("s".equals(emptyParameterName)) ? "" : dim.getSlotId()).append(";");
                }
            }
        }.create(dims);
    }

    public static String createViewsFileWithoutParameter(Collection<Dimension> dims, final String missingParameterName) throws Exception {
        return new ViewsFileCreator() {
            @Override
            void buildAds(StringBuilder builder, Dimension dim) {
                if (dim.isValid()) {
                    builder.append("&ads=")
                    .append("ad:").append(dim.getAdvertisementId()).append(";")
                    .append("sa:").append(dim.getPackSaleId()).append(";")
                    .append("s:").append(dim.getSlotId()).append(";");
                } else {
                    builder.append("&ads=");
                    if (!"ad".equals(missingParameterName)) {
                        builder.append("ad:").append(dim.getAdvertisementId()).append(";");
                    }
                    if (!"sa".equals(missingParameterName)) {
                        builder.append("sa:").append(dim.getPackSaleId()).append(";");
                    }
                    if (!"s".equals(missingParameterName)) {
                        builder.append("s:").append(dim.getSlotId()).append(";");
                    }
                }
            }
        }.create(dims);
    }

    public static String createClicksFile(Collection<Dimension> dims) throws Exception {
        return new ClicksFileCreator() {
            @Override
            void transformLine(Map<String, Object> line) {
                return;
            }
        }.create(dims);
    }

    public static String createClicksFileWithInvalidParameter(Collection<Dimension> dims, final String invalidParameterName) throws Exception {
        return new ClicksFileCreator() {
            @Override
            void transformLine(Map<String, Object> line) {
                switch (invalidParameterName) {
                case "s":
                    line.put("s", new Random().nextLong());
                    break;
                default:
                    line.put(invalidParameterName, "invalid parameter");
                    break;
                }
            }
        }.create(dims);
    }

    public static String createClicksFileWithEmptyParameter(Collection<Dimension> dims, final String emptyParameterName) throws Exception {
        return new ClicksFileCreator() {
            @Override
            void transformLine(Map<String, Object> line) {
                line.put(emptyParameterName, "");
            }
        }.create(dims);
    }

    public static String createClicksFileWithoutParameter(Collection<Dimension> dims, final String missingParameterName) throws Exception {
        return new ClicksFileCreator() {
            @Override
            void transformLine(Map<String, Object> line) {
                line.remove(missingParameterName);
            }
        }.create(dims);
    }

    public static String createGzipFile(String content, String type) throws Exception {
        final String repo = CollectionUtils.randomElement(RemoteConfig.getInstance().getConsolidatorRepositories().get(type));
        final URI repoUri = URI.create(repo);
        final String repoPath = repoUri.getPath();
        final String rootPath = RemoteConfig.getInstance().getApacheAdmPath().get(type);
        final String filename = UUID.randomUUID().toString();
        final String filepath = String.format("%s/%s/%s", rootPath, repoPath, filename);

        final String echoThanGzipCmd = String.format("echo '%1$s' > %2$s; gzip %2$s", content, filepath);
        try {
            RemoteTestHttp.shell(repoUri.getHost(), echoThanGzipCmd).assertOk();
        } catch (Exception e) {
            System.err.println("erro ao invocar o comando: " + echoThanGzipCmd);
            throw e;
        }

        return new File(filepath).getName();
    }

    public static void setPermissionDeniedGzipFile(String fileName, String fileType) throws Exception {
    	final String repo = CollectionUtils.randomElement(RemoteConfig.getInstance().getConsolidatorRepositories().get(fileType));
    	final URI repoUri = URI.create(repo);
        final String repoPath = repoUri.getPath();
        final String rootPath = RemoteConfig.getInstance().getApacheAdmPath().get(fileType);
        final String file = String.format("%s/%s/%s.gz", rootPath, repoPath, fileName);

        final String chmodCmd = String.format("chmod 000 %s", file);
        try {
            RemoteTestHttp.shell(repoUri.getHost(), chmodCmd).assertOk();
        } catch (Exception e) {
            System.err.println("erro ao invocar o comando: " + chmodCmd);
            throw e;
        }
    }

    public static void deleteGzipFiles() throws Exception {
        final Map<String, String> rootPaths = RemoteConfig.getInstance().getApacheAdmPath();

        for (Entry<String, List<String>> repo : RemoteConfig.getInstance().getConsolidatorRepositories().entrySet()) {
            final String fileType = repo.getKey();

            for (String repoUrl : repo.getValue()) {
                final URI repoUri = URI.create(repoUrl);
                final String rootPath = rootPaths.get(fileType);
                final String repoPath = repoUri.getPath();

                final String rmCmd = String.format("rm -f %s/%s/*.gz", rootPath, repoPath);
                RemoteTestHttp.shell(repoUri.getHost(), rmCmd).assertOk();
            }
        }
    }

    public static Dimension newBasicDimension() throws Exception {
        Long customerId = CollectionUtils.randomElement(CustomerDao.selectCustomerIdsLike(CUSTOMER_NAME_PREFIX));
        if (customerId == null) {
            CustomerDao.insertCustomer(CUSTOMER_NAME_PREFIX);
            customerId = CollectionUtils.randomElement(CustomerDao.selectCustomerIdsLike(CUSTOMER_NAME_PREFIX));
        }

        Advertisement ad = CollectionUtils.randomElement(AdvertisementDao.selectAdvertisementsLike(ADVERTISEMENT_NAME_PREFIX));
        if (ad == null) {
            final Long typeAdId = CollectionUtils.randomElement(TypeAdvertisementDao.selectTypeAdvertisementIds());
            final String url = randomUrl();
            final String adName = ADVERTISEMENT_NAME_PREFIX + "01";

            AdvertisementDao.insertAdvertisement(adName, url, customerId, typeAdId);
            ad = CollectionUtils.randomElement(AdvertisementDao.selectAdvertisementsLike(adName));
        }

        final Slot s = CollectionUtils.randomElement(SlotDao.selectSlots(ad.getTypeAdvertisementId()));

        PackSale sa = CollectionUtils.randomElement(PackSaleDao.selectPackSale(customerId, s.getChannelId()));
        if (sa == null) {
            PackSaleDao.insertPackSale(customerId, s.getChannelId());
            sa = CollectionUtils.randomElement(PackSaleDao.selectPackSale(customerId, s.getChannelId()));
        }

        final Date today = new Date();
        final Integer hour = Integer.valueOf(new SimpleDateFormat("HH").format(today));
        final Dimension dim = new Dimension();

        dim.setAdvertisementId(ad.getId());
        dim.setChannelId(s.getChannelId());
        dim.setCustomerId(ad.getCustomerId());
        dim.setDate(today);
        dim.setHour(hour);
        dim.setPackSaleId(sa.getId());
        dim.setSlotId(s.getId());
        dim.setTypeAdvertisementId(ad.getTypeAdvertisementId());
        dim.setValid(true);

        return dim;
    }

    public static String randomUrl() {
        return "http://" + UUID.randomUUID().toString().replace('-', '/');
    }

    public static Dimension getDimensionData(Dimension dim) throws Exception {
        return DimensionDao.selectDimension(
                dim.getAdvertisementId(),
                dim.getChannelId(),
                dim.getCustomerId(),
                dim.getPackSaleId(),
                dim.getSlotId(),
                dim.getTypeAdvertisementId(),
                dim.getHour());
    }

    public static void deleteDimensionData(Dimension dim) throws Exception {
        if (dim == null || dim.getId() == null) {
            return;
        }

        DimensionDao.deleteDimension(dim.getAdvertisementId());
    }

    public static void deleteAllDimensionData() throws Exception {
        DimensionDao.deleteAllDimension();
    }

    public static void insertDimensionData(Dimension dim) throws Exception {
        if (dim == null) {
            return;
        }

        DimensionDao.insertDimension(dim.getAdvertisementId(),
                dim.getChannelId(),
                dim.getCustomerId(),
                dim.getPackSaleId(),
                dim.getSlotId(),
                dim.getTypeAdvertisementId(),
                dim.getHour());
    }

    public static View getViewData(Dimension dim, Date date) throws Exception {
        if (dim == null || dim.getId() == null) {
            return null;
        }

        return ViewDao.selectViewByDimensionAndDate(dim.getId(), date);
    }

    public static void deleteViewData(View view) throws Exception {
        if (view == null || view.getId() == null) {
            return;
        }

        ViewDao.deleteView(view.getId());
    }

    public static void deleteAllViewData() throws Exception {
        ViewDao.deleteAllView();
    }

    public static void insertViewData(Dimension dim) throws Exception {
        if (dim == null) {
            return;
        }

        ViewDao.insertView(dim.getId(), dim.getDate(), dim.getQuantity());
    }

    public static Click getClickData(Dimension dim, Date date) throws Exception {
        if (dim == null || dim.getId() == null) {
            return null;
        }

        return ClickDao.selectClickByDimensionAndDate(dim.getId(), date);
    }

    public static void deleteClickData(Click click) throws Exception {
        if (click == null || click.getId() == null) {
            return;
        }

        ClickDao.deleteClick(click.getId());
    }

    public static void deleteAllClickData() throws Exception {
        ClickDao.deleteAllClick();
    }

    public static void insertClickData(Dimension dim) throws Exception {
        if (dim == null) {
            return;
        }

        ClickDao.insertClick(dim.getId(), dim.getDate(), dim.getQuantity());
    }

    public static Date getAnotherDay(Date date) {
        final Calendar dateTomorrow = Calendar.getInstance();
        dateTomorrow.setTime(date);
        dateTomorrow.add(Calendar.DAY_OF_MONTH, -1);

        return dateTomorrow.getTime();
    }

    public static Long getAnotherSlotId(Long slotId) throws Exception {
        final Slot currSlot = SlotDao.selectSlot(slotId);
        final Collection<Slot> slots = SlotDao.selectSlots(currSlot.getTypeAdvertisementId());

        if (CollectionUtils.isEmpty(slots) ||
                (slots.size() == 1 && Objects.equals(slotId, slots.iterator().next().getId()))) {
            throw new RuntimeException("nao exitem slots disponiveis no banco com idt_type_advertisement=" + currSlot.getTypeAdvertisementId());
        }

        Long anotherSlotId;
        do {
            anotherSlotId = CollectionUtils.randomElement(slots).getId();
        } while (Objects.equals(anotherSlotId, slotId));

        return anotherSlotId;
    }

    public static Long getAnotherPackSaleId(Long packSaleId) throws Exception {
        final PackSale sale = PackSaleDao.selectPackSale(packSaleId);
        Collection<PackSale> currSales = PackSaleDao.selectPackSale(sale.getCustomerId(), sale.getChannelId());

        if (CollectionUtils.isEmpty(currSales) ||
                (currSales.size() == 1 && Objects.equals(packSaleId, currSales.iterator().next().getId()))) {
            PackSaleDao.insertPackSale(sale.getCustomerId(), sale.getChannelId());
            currSales = PackSaleDao.selectPackSale(sale.getCustomerId(), sale.getChannelId());
        }

        Long anotherPackSaleId;
        do {
            anotherPackSaleId = CollectionUtils.randomElement(currSales).getId();
        } while (Objects.equals(anotherPackSaleId, packSaleId));

        return anotherPackSaleId;
    }

    public static Integer getAnotherHour(Integer hour) {
        final Random random = new Random();
        Integer anotherHour;

        do {
            anotherHour = random.nextInt(24);
        } while (Objects.equals(anotherHour, hour));

        return anotherHour;
    }

    public static Long getRandomQuantity() {
        return (long) Math.max(2, new Random().nextInt(11));
    }

    public static void stopConsolidator() throws Exception {
        final String stopMsg = RemoteTestHttp
                .shell("/opt/admanager-consolidator/scripts/admanager-consolidator.sh stop")
                .assertOk()
                .getBodyAsString();

        final Matcher matcher = JOB_PID_STOP_PATTER_PATTERN.matcher(stopMsg);

        if (matcher.matches()) {
            boolean consolidatorIsRunning = true;

            while (consolidatorIsRunning) {
                Thread.sleep(100);

                final String pidCount = RemoteTestHttp
                        .shell("/opt/admanager-consolidator/scripts/admanager-consolidator.sh pid")
                        .assertOk()
                        .getBodyAsString();

                consolidatorIsRunning = NumberUtils.isNumber(pidCount);
            }
        } else {
            throw new IllegalStateException(String.format("nao foi possivel parar o consolidador: %s", stopMsg));
        }
    }

    public static int startConsolidator() throws Exception {
        final String startMsg = RemoteTestHttp
                .shell("/opt/admanager-consolidator/scripts/admanager-consolidator.sh start")
                .assertOk()
                .getBodyAsString();

        final Matcher matcher = JOB_PID_START_PATTERN.matcher(startMsg);
        final String pid;

        if (matcher.matches()) {
            pid = matcher.group(1);
        } else {
            throw new IllegalStateException(String.format("nao foi possivel iniciar o consolidador: %s", startMsg));
        }

        return Integer.parseInt(pid);
    }

    public static void awaitConsolidator() throws Exception {
        boolean consolidatorIsRunning = true;

        do {
            Thread.sleep(100);

            final String pid = RemoteTestHttp
                    .shell("/opt/admanager-consolidator/scripts/admanager-consolidator.sh pid")
                    .assertOk()
                    .getBodyAsString();

            final String grepCount = RemoteTestHttp
                    .shell("grep -cE 'fim da consolidacao em \\[[0-9]+\\] ms' /export/logs/admanager-consolidator/admanager-consolidator.log")
                    .assertOk()
                    .getBodyAsString();

            consolidatorIsRunning = (NumberUtils.isNumber(pid)) && (Integer.valueOf(grepCount) <= 0);
        } while (consolidatorIsRunning);
    }

    public static void deleteConsolidatorLogs() throws Exception {
        RemoteTestHttp.shell("rm -f /export/logs/admanager-consolidator/admanager-consolidator*.log").assertOk();
    }

    public static Long countParseErrors() throws Exception {
    	
        final String countString = RemoteTestHttp
                .shell("cat /export/logs/admanager-consolidator/admanager-consolidator-parse-error.log | wc -l")
                .assertOk()
                .getBodyAsString();

        return Long.valueOf(countString);
    }

 public static Long countParseErrors(String error) throws Exception {
        final String countString = RemoteTestHttp
                .shell("grep -c '"+ error +"' /export/logs/admanager-consolidator/admanager-consolidator-parse-error.log")
                .assertOk()
                .getBodyAsString();

        return Long.valueOf(countString);
    }
    
    private abstract static class LegacyViewsFileCreator {
        abstract String buildLine(Dimension dim, Slot slot, SimpleDateFormat dateFormat, DecimalFormat hourFormat);

        public String create(Collection<Dimension> dims) throws Exception {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
            final DecimalFormat hourFormat = new DecimalFormat("00");
            final StringBuilder content = new StringBuilder();

            for (Dimension dim : dims) {
                final Slot slot = SlotDao.selectSlot(dim.getSlotId());

                for (int i = 0; i < dim.getQuantity(); i++) {
                    final String line = buildLine(dim, slot, dateFormat, hourFormat);

                    content.append(line).append('\n');
                }
            }

            if (content.length() > 0) {
                content.deleteCharAt(content.length() - 1);
            }

            return createGzipFile(content.toString(), "legacy-views");
        }
    }

    private abstract static class ViewsFileCreator {
        abstract void buildAds(StringBuilder builder, Dimension dim);

        public String create(Collection<Dimension> dims) throws Exception {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
            final DecimalFormat hourFormat = new DecimalFormat("00");
            final StringBuilder content = new StringBuilder();

            if (CollectionUtils.isNotEmpty(dims)) {
                final Collection<Dimension> dimCopies = CollectionUtils.collect(dims, new Transformer() {
                    @Override
                    public Object transform(Object input) {
                        return ((Dimension) input).copy();
                    }
                });

                final Dimension firstDim = dimCopies.iterator().next();
                final String date = dateFormat.format(firstDim.getDate());
                final String hour = hourFormat.format(firstDim.getHour());

                boolean existsDimensionToWrite = true;

                do {
                    final StringBuilder ads = new StringBuilder();
                    long totalQtd = 0;

                    for (Dimension dimCopy : dimCopies) {
                        if (dimCopy.getQuantity() <= 0) {
                            continue;
                        }

                        dimCopy.setQuantity(dimCopy.getQuantity() - 1);
                        totalQtd += dimCopy.getQuantity();

                        buildAds(ads, dimCopy);
                    }

                    content.append(String.format(VIEWS_LINE_FORMAT, date, hour, ads.toString())).append('\n');

                    existsDimensionToWrite = (totalQtd > 0);
                } while (existsDimensionToWrite);

                if (content.length() > 0) {
                    content.deleteCharAt(content.length() - 1);
                }
            }
            
            return createGzipFile(content.toString(), "views");
        }
    }

    private abstract static class ClicksFileCreator {
        abstract void transformLine(Map<String, Object> line);

        public String create(Collection<Dimension> dims) throws Exception {
            final StringBuilder content = new StringBuilder();
            final Gson jsonBuilder = new Gson();

            for (Dimension dim : dims) {
                final Slot slot = SlotDao.selectSlot(dim.getSlotId());
                Map<String, Object> line = new HashMap<>();

                ArrayList<Object> lineList = new ArrayList<>();
                long time = dim.getDate().getTime();
                for (int i = 0; i < dim.getQuantity(); i++) {
                	line = new HashMap<>();
                	time = time + 1000;	
	                line.put("ad", dim.getAdvertisementId());
	                line.put("sa", dim.getPackSaleId());
	                line.put("s", slot.getDescription());
	                line.put("c", dim.getCustomerId());
	                line.put("ch", dim.getCustomerId());
	                line.put("ty", dim.getTypeAdvertisementId());
	                line.put("t", time);
	                transformLine(line);
	                lineList.add(line);

                }
                for (Object object : lineList) {
                	final String lineAsJson = jsonBuilder.toJson(object);
                    content.append(lineAsJson).append('\n');
                }
            }

            if (content.length() > 0) {
                content.deleteCharAt(content.length() - 1);
            }
            return createGzipFile(content.toString(), "clicks");
        }
    }

    public static List<ViewLog> getViewLogList(List<String> viewFileNameList) throws Exception {
        return(ViewLogDao.getViewLogList(viewFileNameList));
    }

    public static boolean deleteViewLogList(List<String> viewFileNameList) throws Exception {
        return(ViewLogDao.deleteViewLog(viewFileNameList));
    }

    public static List<ClickLog> getClickLogList(List<String> clickFileNameList) throws Exception{
        return(ClickLogDao.getClickLogList(clickFileNameList));
    }

    public static boolean deleteClickLogList(List<String> clickFileNameList) throws Exception{
        return(ClickLogDao.deleteClickLog(clickFileNameList));
    }

	public static void insertViewLog(String urlLog) throws Exception {
	    ViewLogDao.insertLogView(urlLog);
	}

	public static void insertClickLog(String urlLog, int lines) throws Exception {
		ClickLogDao.insertLogClick(urlLog, lines );
	}
}
