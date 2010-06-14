package morema.view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import morema.util.Constantes;
import morema.util.MoremaException;

public class MainView extends MIDlet {

	private static MIDlet midlet = null;

	public MainView() {
		midlet = this;
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		notifyDestroyed();
	}

	protected void pauseApp() {
		notifyPaused();
	}

	protected void startApp() throws MIDletStateChangeException {
		getDisplay().setCurrent(new MainViewForm());
	}
	
	public static Display getDisplay() {
		return Display.getDisplay(midlet);
	}
	
	public static void showAlert(Exception e, Displayable nextForm) {
		e.printStackTrace();
		showAlert(e.getMessage(), nextForm, true);
	}
	
	public static void showAlert(String msg, Displayable nextForm, boolean isError) {
		Alert alert = new Alert(Constantes.TITULO_APLICACAO, msg, null, (isError ? AlertType.ERROR : AlertType.INFO));
		if (nextForm == null) {
			getDisplay().setCurrent(alert);
		} else {
			getDisplay().setCurrent(alert, nextForm);
		}
	}

	private class MainViewForm extends List implements CommandListener {

		private final Command cmdExit = new Command("Sair", Command.EXIT, 0);
		
		public MainViewForm() {
			super("MoReMa", Choice.IMPLICIT);

			append("Criar pesquisa", null);
			append("Gerenciar pesquisas", null);
			addCommand(cmdExit);
			setCommandListener(this);
		}

		public void commandAction(Command c, Displayable d) {
			if (c == cmdExit) {
				try {
					destroyApp(true);
				} catch (MIDletStateChangeException e) {
				}
			} else {
				try {
					switch (this.getSelectedIndex()) {
					case 0:
						getDisplay().setCurrent(new CreateSurveyView(this));
						break;
					case 1:
						getDisplay().setCurrent(new ManageSurveyView(this));
						break;
					}
				} catch (MoremaException e) {
					MainView.showAlert(e, null);
				}
			}
		}
	}

}
