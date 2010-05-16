package morema.persistence;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Vector;

import morema.model.AbstractModel;

public abstract class AbstractDAO  {
	
	public enum Entity {
		Survey("Survey");
		
		public final String RECORD_STORE_BASE_NAME;
		
		private Entity(String recordStoreBaseName) {
			RECORD_STORE_BASE_NAME = recordStoreBaseName;
		}
	}
	
	public static final char FIELD_SEPARATOR = '|';
	
	protected String recordStore;
	
	public AbstractDAO(String recordStore) {
		this.recordStore = recordStore;
	}
	
	public Object getRecord(short id) {
		return deserialize(null);
	}
	
	public Vector getRecords() {
		Vector records = new Vector();
		while (true) {
			records.add(deserialize(null));
			break;
		}
		return records;
	}
	
	public short addRecord(Object object) {
		serialize(object);
		return 0;
	}

	protected abstract byte[] serialize(Object object);
	
	protected abstract Object deserialize(byte[] data);

	public static byte[] genericalSerialize(Object... values) {
		StringBuffer string = new StringBuffer(); 
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				string.append(FIELD_SEPARATOR);
			} else {
				string.append(values[i].toString() + FIELD_SEPARATOR);
			}
		}
		System.out.println(string);
		return string.toString().getBytes();
	}
	
	public static Object[] genericalDeserialize(byte[] data, Class... types) {
		String dataString = new String(data);
		Object[] objects = new Object[types.length];
		StringBuffer auxStringBuffer = new StringBuffer();
		int currentField = 0;
		for (int i = 0; i < data.length; i++) {
			char charI = dataString.charAt(i);
			if (charI == FIELD_SEPARATOR) {
				if (auxStringBuffer.length() == 0) {
					if (types[currentField].equals(String.class)) {
						objects[currentField] = "";
					} else {
						objects[currentField] = null;
					}
				} else {
					try {
						if (types[currentField].equals(Character.class)) {
							objects[currentField] = types[currentField].getConstructor(Character.class).newInstance(auxStringBuffer.toString().charAt(0));
						} else {
							objects[currentField] = types[currentField].getConstructor(String.class).newInstance(auxStringBuffer.toString());							
						}
					} catch (Exception e) {
						e.printStackTrace();
						objects[currentField] = null;
					}			
				}
				currentField++;
				auxStringBuffer = new StringBuffer();
			} else {
				auxStringBuffer.append(charI);
			}
		}
		return objects;
	}
	
	public static void main(String[] args) {
		Float a = 3.15f;
		String b = "Hello guys!";
		Boolean c = false;
		Character d = 'k';
		Integer e = 210;
		byte[] genericalSerialized = genericalSerialize(a, b, c, d, e);
		System.out.println(Arrays.asList(genericalSerialized));
		System.out.println(Arrays.asList(genericalDeserialize(genericalSerialized, Float.class, String.class, Boolean.class, Character.class, Integer.class)));
	}
}
