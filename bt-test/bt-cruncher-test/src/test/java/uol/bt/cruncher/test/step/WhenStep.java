package uol.bt.cruncher.test.step;

import uol.bt.cruncher.test.helper.TestPrepare;
import cucumber.api.java.pt.Quando;

public class WhenStep extends BaseStep {

    @Quando("o sistema for iniciado")
    public void startCruncher() throws Exception {
        TestPrepare.runCruncher();
    }

}
