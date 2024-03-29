package morema.view;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import morema.util.Constants;
import morema.util.MoremaException;

public class MainView extends MIDlet {

	private static MIDlet midlet = null;
	private static MainViewForm mainViewForm;

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
		mainViewForm = new MainViewForm();
		getDisplay().setCurrent(mainViewForm);
	}
	
	public static MainViewForm getMainView() {
		return mainViewForm;
	}
	
	public static Display getDisplay() {
		return Display.getDisplay(midlet);
	}
	
	public static void showAlert(Exception e, Displayable nextForm) {
		e.printStackTrace();
		showAlert(e.getMessage(), nextForm, true);
	}
	
	public static void showAlert(String msg, Displayable nextForm, boolean isError) {
		Alert alert = new Alert(Constants.APPLICATION_TITLE, msg, null, (isError ? AlertType.ERROR : AlertType.CONFIRMATION));
		if (nextForm == null) {
			getDisplay().setCurrent(alert);
		} else {
			getDisplay().setCurrent(alert, nextForm);
		}
	}
	
	public static void showConfirmation(String msg, Displayable nextFormYesChoice, Displayable nextFormNoChoice) {
		getDisplay().setCurrent(new ConfirmationDialog(msg, nextFormYesChoice, nextFormNoChoice));
	}

	private class MainViewForm extends List implements CommandListener {

		private final Command cmdExit = new Command(Constants.COMMAND_EXIT, Command.EXIT, 0);
		
		public MainViewForm() {
			super(Constants.APPLICATION_TITLE, Choice.IMPLICIT);

			append(Constants.COMMAND_CREATE_SURVEY, null);
			append(Constants.COMMAND_MANAGE_SURVEY, null);
			append(Constants.COMMAND_ABOUT, null);
			
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
					case 2:
						getDisplay().setCurrent(new AboutView(this));
					}
				} catch (MoremaException e) {
					MainView.showAlert(e, null);
				}
			}
		}
	}

	private static class ConfirmationDialog extends Form implements CommandListener {

		private Command cmdYes;
		private Command cmdNo;
		private StringItem strMessage;
		private final Displayable formYesChoice;
		private final Displayable formNoChoice;
		
		public ConfirmationDialog(String msg, Displayable nextFormYesChoice, Displayable nextFormNoChoice) {
			super(Constants.APPLICATION_TITLE);
			this.formYesChoice = nextFormYesChoice;
			this.formNoChoice = nextFormNoChoice;
			
			strMessage = new StringItem(msg, null);
			strMessage.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
			cmdYes = new Command(Constants.MSG_YES, Command.OK, 0);
			cmdNo = new Command(Constants.MSG_NO, Command.CANCEL, 1);
			
			append(strMessage);
			addCommand(cmdYes);
			addCommand(cmdNo);
			setCommandListener(this);
		}

		public void commandAction(Command c, Displayable d) {
			if (c == cmdYes) {
				MainView.getDisplay().setCurrent(formYesChoice);
			} else if (c == cmdNo) {
				MainView.getDisplay().setCurrent(formNoChoice);
			}
		}
	}
}
