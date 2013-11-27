package selenium.infra;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageUtils {
	
	private static final String view_mask = "dd-MM-yyyy";
	
	public static String toString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(view_mask);
		return simpleDateFormat.format(date);
	}
}
