package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import morema.util.Constants;

public class AboutView extends Form implements CommandListener {

	private final Displayable parentForm;
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 0);
	
	public AboutView(Displayable parentForm) {
		super(Constants.COMMAND_ABOUT + " " + Constants.APPLICATION_TITLE);
		this.parentForm = parentForm;
		
		StringItem strItem = new StringItem(Constants.MSG_APPLICATION_NAME, null);
		strItem.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
		append(strItem);
		
		strItem = new StringItem(Constants.MSG_ABOUT, null);
		strItem.setLayout(StringItem.LAYOUT_NEWLINE_AFTER);
		append(strItem);
		
		addCommand(cmdBack);
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable d) {
		MainView.getDisplay().setCurrent(parentForm);
	}
}
