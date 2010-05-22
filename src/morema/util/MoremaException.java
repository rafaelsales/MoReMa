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
		super(msg + " - Detalhes: " + exception.toString());
	}

	public static void throwAsMoremaException(Exception e) throws MoremaException {
		if (e instanceof RecordStoreFullException) {
			throw new MoremaException(Constantes.MSG_ERRO_MEMORIA_CHEIA, e);
		} else if (e instanceof RecordStoreNotFoundException) {
			throw new MoremaException(Constantes.MSG_ERRO_PERSISTENCIA_NAO_ENCONTRADA, e);
		} else if (e instanceof RecordStoreNotOpenException) {
			throw new MoremaException(Constantes.MSG_ERRO_PERSISTENCIA_NAO_ENCONTRADA, e);
		} else if (e instanceof InvalidRecordIDException) {
			throw new MoremaException(Constantes.MSG_ERRO_PERSISTENCIA_ITEM_NAO_ENCONTRADO, e);
		} else if (e instanceof RecordStoreException) {
			throw new MoremaException(Constantes.MSG_ERRO_PERSISTENCIA, e);
		} else {
			throw new MoremaException(Constantes.MSG_ERRO_INESPERADO, e);
		}
	}
}
