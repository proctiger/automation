package uol.admanager.consolidator.test.steps.view;

import java.util.Date;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.View;
import cucumber.api.java.pt.Quando;

public class WhenTestStep extends TestStep {

    @Quando("^for iniciado o processamento de impressões$")
    public void consolidateViews() throws Exception {
        simulateViewConsolidation();

        TestPrepare.startConsolidator();
        TestPrepare.awaitConsolidator();
        TestPrepare.stopConsolidator();
    }

    private void simulateViewConsolidation() throws Exception {
        if (dimensionForView == null) {
            expectedView = null;
            expectedDimension = null;
            return;
        }

        final Date currDate = dimensionForView.getTruncatedDate();
        final Dimension dimensionData = TestPrepare.getDimensionData(dimensionForView);
        final View viewData = TestPrepare.getViewData(dimensionData, currDate);

        if (dimensionForView.isValid()) {
            final boolean isNewView = (viewData == null);

            if (isNewView) {
                expectedView = new View();
                expectedView.setDate(currDate);
                expectedView.setQuantity(dimensionForView.getQuantity());
            } else {
                expectedView = viewData;
                expectedView.setQuantity(viewData.getQuantity() + dimensionForView.getQuantity());
            }

            expectedDimension = dimensionForView.copy();
        } else {
            expectedView = viewData;
            expectedDimension = dimensionData;
            expectedNumberOfParserErrors += dimensionForView.getQuantity();
        }
    }
}
