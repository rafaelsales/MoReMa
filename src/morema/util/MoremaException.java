package morema.util;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class MoremaException extends Exception {
	public MoremaException(String msg) {
		super(msg);
	}

	public MoremaException(String msg, Throwable exception) {
		super(msg + " - " + Constants.MSG_DETAILS + ": " + exception.toString());
	}

	public static void throwAsMoremaException(Exception e) throws MoremaException {
		if (e instanceof RecordStoreFullException) {
			throw new MoremaException(Constants.MSG_ERROR_FULL_MEMORY, e);
		} else if (e instanceof RecordStoreNotFoundException) {
			throw new MoremaException(Constants.MSG_ERROR_PERSISTENCE_NOT_FOUND, e);
		} else if (e instanceof RecordStoreNotOpenException) {
			throw new MoremaException(Constants.MSG_ERROR_PERSISTENCE_NOT_FOUND, e);
		} else if (e instanceof InvalidRecordIDException) {
			throw new MoremaException(Constants.MSG_ERROR_PERSISTENCE_ITEM_NOT_FOUND, e);
		} else if (e instanceof RecordStoreException) {
			throw new MoremaException(Constants.MSG_ERROR_PERSISTENCE, e);
		} else if (e instanceof MoremaException) {
			throw (MoremaException) e;
		} else {
			throw new MoremaException(Constants.MSG_ERROR_UNEXPECTED, e);
		}
	}
}
