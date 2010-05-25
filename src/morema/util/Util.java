package morema.util;

import java.util.Vector;

public class Util {
	
	private Util() {
	}
	
	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
	
	public static Object[] vectorToArray(Vector vector, boolean reverse) {
		Object[] objects = new Object[vector.size()];
		if (reverse) {
			for (int i = 0; i < objects.length; i++) {
				objects[i] = vector.elementAt(objects.length - i - 1);
			}
		} else {
			vector.copyInto(objects);
		}
		return objects;
	}
}
