package morema.view;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import morema.test.BusinessTest;
import morema.util.MoremaException;

public class MainView extends MIDlet {

	protected void startApp() throws MIDletStateChangeException {
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