package morema.view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

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
	}

	protected void startApp() throws MIDletStateChangeException {
		getDisplay().setCurrent(new MainViewForm());
	}
	
	public static Display getDisplay() {
		return Display.getDisplay(midlet);
	}
	
	public static void showAlert(String msg, Displayable nextForm) {
		if (nextForm == null) {
			getDisplay().setCurrent(new Alert(msg));
		} else {
			getDisplay().setCurrent(new Alert(msg), nextForm);
		}
	}

	private class MainViewForm extends List implements CommandListener {

		private final Command cmdSair = new Command("Sair", Command.EXIT, 0);
		
		public MainViewForm() {
			super("MoReMa", Choice.IMPLICIT);

			append("Criar pesquisa", null);
			append("Listar pesquisas", null);
			addCommand(cmdSair);
			setCommandListener(this);
		}

		public void commandAction(Command c, Displayable d) {
			if (c.getLabel().equals(cmdSair.getLabel())) {
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
						getDisplay().setCurrent(new ListSurveyView(this));
						break;
					}
				} catch (MoremaException e) {
					MainView.showAlert(e.getMessage(), null);
				}
			}
		}
	}

}
