package uol.admanager.consolidator.test.steps.view;

import java.util.ArrayList;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.entity.Click;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.View;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 *
 */
public class TestStep {

    protected static SimpleHttpResponse probeResponse;
    protected static Dimension dimensionForView;
    protected static Dimension dimensionForClick;
    protected static Dimension expectedDimension;
    protected static View expectedView;
    protected static Click expectedClick;
    protected static String createdViewsFileName;
    protected static ArrayList<String> createdViewsFileNameList = new ArrayList<String>();
    protected static String createdClickFileName;
    protected static ArrayList<String> createdClickFileNameList = new ArrayList<String>();
    protected static Long expectedNumberOfParserErrors;
    protected static boolean self = false;

    public void createViewData() throws Exception {
        Dimension dimensionData = TestPrepare.getDimensionData(dimensionForView);
        if (dimensionData == null) {
            dimensionData = dimensionForView.copy();
            dimensionData.setQuantity(TestPrepare.getRandomQuantity());
            TestPrepare.insertDimensionData(dimensionData);
            dimensionData.setId(TestPrepare.getDimensionData(dimensionForView).getId());
        }

        final View viewData = TestPrepare.getViewData(dimensionData, dimensionForView.getTruncatedDate());
        if (viewData == null) {
            if (dimensionData.getQuantity() == null) {
                dimensionData.setQuantity(TestPrepare.getRandomQuantity());
            }
            if (dimensionData.getDate() == null) {
                dimensionData.setDate(dimensionForView.getDate());
            }
            TestPrepare.insertViewData(dimensionData);
        }
    }

    public void createClickData() throws Exception {
        Dimension dimensionData = TestPrepare.getDimensionData(dimensionForClick);
        if (dimensionData == null) {
            dimensionData = dimensionForClick.copy();
            dimensionData.setQuantity(TestPrepare.getRandomQuantity());
            TestPrepare.insertDimensionData(dimensionData);
            dimensionData.setId(TestPrepare.getDimensionData(dimensionForClick).getId());
        }

        final Click clickData = TestPrepare.getClickData(dimensionData, dimensionForClick.getTruncatedDate());
        if (clickData == null) {
            if (dimensionData.getQuantity() == null) {
                dimensionData.setQuantity(TestPrepare.getRandomQuantity());
            }
            if (dimensionData.getDate() == null) {
                dimensionData.setDate(dimensionForClick.getDate());
            }
            TestPrepare.insertClickData(dimensionData);
        }
    }
    
    public void deleteViewData() throws Exception {
        final Dimension dimensionData = TestPrepare.getDimensionData(dimensionForView);
        if (dimensionData != null) {
            final View viewData = TestPrepare.getViewData(dimensionData, dimensionForView.getTruncatedDate());
            TestPrepare.deleteViewData(viewData);
        }
    }

    public void deleteClickData() throws Exception {
        final Dimension dimensionData = TestPrepare.getDimensionData(dimensionForClick);
        if (dimensionData != null) {
            final Click clickData = TestPrepare.getClickData(dimensionData, dimensionForClick.getTruncatedDate());
            TestPrepare.deleteClickData(clickData);
        }
    }

    public void deleteDimension(Dimension dimension) throws Exception {
        final Dimension dimensionData = TestPrepare.getDimensionData(dimension);
        try {
            TestPrepare.deleteDimensionData(dimensionData);
        } catch (Exception e) {
            // --ignore se a dimensao da view e click eh a mesma
        }
    }
}
