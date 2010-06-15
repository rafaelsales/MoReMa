package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public class RemoveSurveyView extends Form implements CommandListener {

	private Command cmdYes;
	private Command cmdNo;
	private StringItem strMessage;
	private final Displayable parentForm;
	private final Survey survey;
	
	public RemoveSurveyView(Survey survey, Displayable parentForm) {
		super(Constants.COMMAND_REMOVE + " - " + survey.title);
		this.survey = survey;
		this.parentForm = parentForm;
		
		strMessage = new StringItem(Constants.MSG_CONFIRM_REMOVE_SURVEY, null);
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
			try {
				SurveyBS.remove(survey);
				MainView.showAlert(Constants.MSG_DATA_REMOVED_SUCCESSFULLY, new ManageSurveyView(MainView.getMainView()), false);
			} catch (MoremaException e) {
				MainView.showAlert(e, parentForm);
			}
		} else if (c == cmdNo) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}