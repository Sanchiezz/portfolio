package tutby.converter.utils;


public class Utils {

	public static float currencyString2Float(String currencyRateString) {
		String result;

		result = currencyRateString;

		try {
			result = currencyRateString.replaceAll("\\s", "");
			result = result.replaceAll(",", ".");
			return Float.parseFloat(result);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
