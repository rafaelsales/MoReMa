package morema.view;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;

import morema.test.BusinessTest;
import morema.util.MoremaException;

public class MainView extends MIDlet {

	protected void startApp() throws MIDletStateChangeException {
		try {
			//TODO Remover
			String[] rsNames = RecordStore.listRecordStores();
			for (int i = 0; i < rsNames.length; i++) {
				System.out.println("RS Existente: " + rsNames[i]);
				RecordStore.deleteRecordStore(rsNames[i]);
			}
		} catch (Exception e) {
		}
		createSurvey();
		listSurveys();
	}

	protected void pauseApp() {		
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {	
	}
	
	private void createSurvey() {
		 try {
			BusinessTest.createSurvey(3);
		} catch (MoremaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void listSurveys() {
		try {
			BusinessTest.listSurvey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}