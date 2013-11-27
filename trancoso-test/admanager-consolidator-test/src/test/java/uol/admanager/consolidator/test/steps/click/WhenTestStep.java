package uol.admanager.consolidator.test.steps.click;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.consolidator.test.steps.view.TestStep;
import uol.admanager.entity.Click;
import uol.admanager.entity.Dimension;
import cucumber.api.java.pt.Quando;

public class WhenTestStep extends TestStep {

    @Quando("^for iniciado o processamento de cliques$")
    public void consolidateClicks1() throws Exception {
        simulateClickConsolidation();

        TestPrepare.startConsolidator();
        TestPrepare.awaitConsolidator();
        TestPrepare.stopConsolidator();
    }

    private void simulateClickConsolidation() throws Exception {
        if (dimensionForClick == null) {
            expectedClick = null;
            expectedDimension = null;
            return;
        }

        final Dimension dimensionData = TestPrepare.getDimensionData(dimensionForClick);
        final Click clickData = TestPrepare.getClickData(dimensionData, dimensionForClick.getTruncatedDate());
        
        if (dimensionForClick.isValid()) {
            final boolean isNewClick = (clickData == null);

            if (isNewClick) {
                expectedClick = new Click();
                expectedClick.setDate(truncateDate(dimensionForClick.getDate()));
                expectedClick.setQuantity(dimensionForClick.getQuantity());
            } else {
                expectedClick = clickData;
                expectedClick.setQuantity(clickData.getQuantity() + dimensionForClick.getQuantity());
            }

            expectedDimension = dimensionForClick.copy();
        } else {
            expectedClick = clickData;
            expectedDimension = dimensionData;
            expectedNumberOfParserErrors += dimensionForClick.getQuantity();
        }
    }

    private Date truncateDate(Date date) throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        final String dateString = dateFormat.format(date);

        return dateFormat.parse(dateString);
    }
}
