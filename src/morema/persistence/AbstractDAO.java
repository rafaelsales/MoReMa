package morema.persistence;

import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

import morema.model.AbstractModel;
import morema.util.MoremaException;

public abstract class AbstractDAO {

	public static final char FIELD_SEPARATOR = '|';

	protected String recordStoreName;
	protected static RecordStore recordStore = null; //Current opened RecordStore

	public AbstractDAO(String recordStoreName) throws MoremaException {
		this.recordStoreName = recordStoreName;
		try {
			openRecordStore(recordStoreName);
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
	}

	public AbstractModel getRecordGeneric(int id) throws MoremaException {
		try {
			return deserialize(recordStore.getRecord(id));
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
			return null;
		}
	}

	public Vector getRecords() throws MoremaException {
		Vector records = null;
		try {
			RecordEnumeration result = recordStore.enumerateRecords(null, null, false);
			records = new Vector(result.numRecords());
			while (result.hasNextElement()) {
				int id = result.nextRecordId();
				AbstractModel model = deserialize(recordStore.getRecord(id));
				model.id = new Integer(id);
//				model.id = new Integer(result.previousRecordId());
				
				records.addElement(model);
			}
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
		return records;
	}

	public AbstractModel addRecord(AbstractModel model) throws MoremaException {
		byte[] data = serialize(model);
		try {
			model.id = new Integer(recordStore.addRecord(data, 0, data.length));
			return model;
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
			return null;
		}
	}

	protected abstract byte[] serialize(AbstractModel model);

	protected abstract AbstractModel deserialize(byte[] data);

	public static byte[] genericalSerialize(Object[] values) {
		StringBuffer string = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				string.append(FIELD_SEPARATOR);
			} else {
				string.append(values[i].toString() + FIELD_SEPARATOR);
			}
		}
		return string.toString().getBytes();
	}

	public static Object[] genericalDeserialize(byte[] data, Class[] types) {
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
						String stringValue = auxStringBuffer.toString();
						// objects[currentField] = types[currentField].getConstructor(String.class).newInstance(auxStringBuffer.toString());
						if (types[currentField].equals(String.class)) {
							objects[currentField] = stringValue;
						} else if (types[currentField].equals(Character.class)) {
							objects[currentField] = new Character(stringValue.charAt(0));
						} else if (types[currentField].equals(Float.class)) {
							objects[currentField] = Float.valueOf(stringValue);
						} else if (types[currentField].equals(Double.class)) {
							objects[currentField] = Double.valueOf(stringValue);
						} else if (types[currentField].equals(Integer.class)) {
							objects[currentField] = Integer.valueOf(stringValue);
						} else if (types[currentField].equals(Long.class)) {
							objects[currentField] = new Long(Long.parseLong(stringValue));
						} else if (types[currentField].equals(Boolean.class)) {
							objects[currentField] = stringValue.equals("true") ? Boolean.TRUE : Boolean.FALSE;
						} else {
							throw new RuntimeException("Unexpected type: " + types[currentField].getName());
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
	
	public void close() {
		try {
			recordStore.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
		} catch (RecordStoreException e) {
		}
	}

//	public static void test() {
//		Float a = new Float(3.15f);
//		String b = "Hello guys!";
//		Boolean c = Boolean.FALSE;
//		Character d = new Character('k');
//		Integer e = new Integer(210);
//		byte[] genericalSerialized = genericalSerialize(new Object[] { a, b, c, d, e });
//		System.out.println(new String(genericalSerialized));
//		System.out.println(genericalDeserialize(genericalSerialized, new Class[] { Float.class, String.class, Boolean.class, Character.class, Integer.class }));
//	}
	
	private static void openRecordStore(String recordStoreName) throws MoremaException {
		//Caso haja outro RecordStore aberto, fecha para abrir o RecordStore solicitado:
		boolean recordStoreOpened = false;
		if (recordStore != null) {
			try {
				if (recordStore.getName().equals(recordStoreName)) {
					recordStoreOpened = true;
				} else {
					recordStore.closeRecordStore();					
				}
			} catch (RecordStoreException e) {
			}
		}
		
		if (!recordStoreOpened) {
			try {
				recordStore = RecordStore.openRecordStore(recordStoreName, true, RecordStore.AUTHMODE_ANY, true);		
			} catch (Exception e) {
				MoremaException.throwAsMoremaException(e);
			}
		}
	}
}
