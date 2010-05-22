package morema.util;

public class Util {
	
	private Util() {
	}
	
	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
}
