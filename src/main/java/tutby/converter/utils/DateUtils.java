package tutby.converter.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtils {

	public static List<String> getLastWeekEndDates(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> result = new ArrayList<String>(2);
		Calendar today = Calendar.getInstance();

		today.add(Calendar.DAY_OF_WEEK, -(today.get(Calendar.DAY_OF_WEEK) - 1));
		String formatted = sdf.format(today.getTime());
		result.add(formatted);
		today.add(Calendar.DAY_OF_MONTH, -1);
		formatted = sdf.format(today.getTime());
		result.add(formatted);

		return result;
	}
}