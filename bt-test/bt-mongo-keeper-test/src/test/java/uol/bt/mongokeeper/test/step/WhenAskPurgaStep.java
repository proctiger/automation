package uol.bt.mongokeeper.test.step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uol.bt.commons.test.helper.RemoteTestHelper;
import uol.bt.mongokeeper.test.util.Constants;
import cucumber.api.java.it.Quando;

/**
 * 
 * @author wrodrigues
 * 
 */
public class WhenAskPurgaStep extends BaseStep {

	private static final Pattern PID_START_PATTERN = Pattern.compile("^Started with pid: \\[([0-9]+)\\]$");
	
	@Quando("^solicitar purga$")
	public void checkCollectionPurged() {
		try {
			runKeeper();
			
		} catch (Exception e) {

		}
	}
	
	 public static void runKeeper() throws Exception {
		 	
			final String startMsg = RemoteTestHelper
	                .shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, "/opt/bt-mongo-keeper/scripts/bt-mongo-keeper.sh start")
	                .assertOk()
	                .getBodyAsString();

	        final Matcher matcher = PID_START_PATTERN.matcher(startMsg);

	        if (matcher.matches()) {
	            boolean keeperIsRunning = true;

	            while (keeperIsRunning) {
	                Thread.sleep(1000);
	                final String pidCount = RemoteTestHelper
	                		.shell(Constants.REMOTE_ADMIN_TEST_DOMAIN, String.format("ps %s|grep -v PID|wc -l", matcher.group(1)))
	                        .assertOk()
	                        .getBodyAsString();
	                keeperIsRunning = Integer.valueOf(pidCount) > 0;
	            }
	        } else {
	            throw new IllegalStateException(String.format("cruncher nao inicializado: %s", startMsg));
	        }
	    }
}
