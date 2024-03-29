package morema.persistence;

import java.util.Vector;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import morema.model.AbstractModel;
import morema.util.MoremaException;

public abstract class AbstractDAO {

	public static final char FIELD_SEPARATOR = '|';
	public static final char FIELD_ARRAY_START_SEPARATOR = '[';
	public static final char FIELD_ARRAY_END_SEPARATOR = ']';

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

	/**
	 * Carrega um registro a partir de seu id único
	 * @param id
	 * @return
	 * @throws MoremaException
	 */
	public AbstractModel getRecordGeneric(int id) throws MoremaException {
		try {
			openThisRecordStore();
			AbstractModel model = deserialize(recordStore.getRecord(id));
			model.id = new Integer(id);
			return model;
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
			return null;
		}
	}

	/**
	 * Retorna todos os registros do RecordStore
	 * @return
	 * @throws MoremaException
	 */
	public Object[] getRecords() throws MoremaException {
		return getRecords(null, null);
	}
	
	protected Object[] getRecords(RecordFilter recordFilter, RecordComparator recordComparator) throws MoremaException {
		Object[] records = null;
		try {
			openThisRecordStore();
			RecordEnumeration result = recordStore.enumerateRecords(recordFilter, recordComparator, false);
			records = new Object[result.numRecords()];
			for (int i = result.numRecords() - 1; (i >= 0 && result.hasNextElement()); i--) {
				int id = result.nextRecordId();
				AbstractModel model = deserialize(recordStore.getRecord(id));
				model.id = new Integer(id);
				records[i] = model;
			}
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
		return records;
	}

	public AbstractModel saveRecord(AbstractModel model) throws MoremaException {
		byte[] data = serialize(model);
		try {
			openThisRecordStore();
			if (model.id == null) {
				model.id = new Integer(recordStore.addRecord(data, 0, data.length));
			} else {
				recordStore.setRecord(model.id.intValue(), data, 0, data.length);
			}
			System.out.println("Persist " + model.getClass().getName() + ": " + new String(data));
			return model;
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
			return null;
		}
	}
	
	public void removeRecord(int id) throws MoremaException {
		try {
			openThisRecordStore();
			recordStore.deleteRecord(id);
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
	}
	
	public void removeRecordStore() throws MoremaException {
		try {
			openThisRecordStore();
			recordStore.closeRecordStore();
			RecordStore.deleteRecordStore(recordStoreName);
			recordStore = null;
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
	}

	protected abstract byte[] serialize(AbstractModel model);

	protected abstract AbstractModel deserialize(byte[] data);

	/**
	 * Serializa os objetos do array values em bytes
	 * @param values
	 * @return
	 */
	public static byte[] genericalSerialize(Object[] values) {
		StringBuffer string = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null) {
				string.append(FIELD_SEPARATOR);
			} else if (values[i] instanceof Vector) {
				string.append(vectorToString((Vector) values[i]) + FIELD_SEPARATOR);
			} else {
				//Substitui os caracteres de controle por caracteres similares:
				if (values[i] instanceof String) {
					String valueAsString = ((String) values[i]);
					valueAsString.replace(FIELD_ARRAY_START_SEPARATOR, '(');
					valueAsString.replace(FIELD_ARRAY_END_SEPARATOR, ')');
					valueAsString.replace(FIELD_SEPARATOR, ';');
				}
				string.append(values[i].toString() + FIELD_SEPARATOR);
			}
		}
		return string.toString().getBytes();
	}

	/**
	 * Desserializa os bytes de data em objetos seguindo os tipos de objetos esperados no array types
	 * @param data
	 * @param types
	 * @return
	 */
	public static Object[] genericalDeserialize(byte[] data, Class[] types) {
		String dataString = new String(data);
		Object[] objects = new Object[types.length];
		StringBuffer auxStringBuffer = new StringBuffer();
		int currentField = 0;
		boolean readingVector = false;
		for (int i = 0; (i < data.length) && (currentField < types.length); i++) {
			char charI = dataString.charAt(i);
			if (charI == FIELD_SEPARATOR && !readingVector) {
				if (auxStringBuffer.length() == 0) {
					if (types[currentField].equals(String.class)) {
						objects[currentField] = "";
					} else {
						objects[currentField] = null;
					}
				} else {
					try {
						String stringValue = auxStringBuffer.toString();
						if (types[currentField].equals(String.class)) {
							objects[currentField] = stringValue;
						} else if (types[currentField].equals(Boolean.class)) {
							if (stringValue.equals(Boolean.TRUE.toString())) {
								objects[currentField] = Boolean.TRUE;
							} else {
								objects[currentField] = Boolean.FALSE;
							}
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
							objects[currentField] = stringValue.equals(Boolean.TRUE.toString()) ? Boolean.TRUE : Boolean.FALSE;
						} else if (types[currentField].equals(Vector.class)) {
							objects[currentField] = stringToVector(stringValue);
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
				if (charI == FIELD_ARRAY_START_SEPARATOR) {
					readingVector = true;
				} else if (charI == FIELD_ARRAY_END_SEPARATOR) {
					readingVector = false;
				}
				auxStringBuffer.append(charI);
			}
		}
		return objects;
	}
	
	/**
	 * Serializa um objeto Vector
	 * @param vector
	 * @return
	 */
	private static String vectorToString(Vector vector) {
		StringBuffer auxStringBuffer = new StringBuffer();
		auxStringBuffer.append(FIELD_ARRAY_START_SEPARATOR);
		for (int i = 0; i < vector.size(); i++) {
			auxStringBuffer.append(vector.elementAt(i).toString() + FIELD_SEPARATOR);
		}
		auxStringBuffer.append(FIELD_ARRAY_END_SEPARATOR);
		return auxStringBuffer.toString();
	}
	
	/**
	 * Desserializa um objeto Vector a partir de sua representação serializada
	 * @param vectorString
	 * @return
	 */
	private static Vector stringToVector(String vectorString) {
		Vector vector = new Vector();
		StringBuffer auxStringBuffer = new StringBuffer();
		int currentField = 0;
		for (int i = 1; i < vectorString.length() - 1; i++) {
			char charI = vectorString.charAt(i);
			if (charI == FIELD_SEPARATOR) {
				vector.addElement(auxStringBuffer.toString());
				currentField++;
				auxStringBuffer = new StringBuffer();
			} else {				
				auxStringBuffer.append(charI);
			}
		}
		return vector;
	}
	
	private void openThisRecordStore() throws Exception {
		openRecordStore(recordStoreName);
	}
	
	/**
	 * Abre um RecordStore no dispositivo. Caso não exista, o RecordStore é criado.
	 * Este método gerencia os RecordStores de forma que somente um esteja aberto por vez. 
	 * @param recordStoreName
	 * @throws MoremaException
	 */
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
