package morema.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.model.Survey;
import morema.util.Constants;

public class PrepareCreateQuestionView extends List implements CommandListener {
	
	private final Displayable parentForm;
	private final Survey survey;
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 0);
	
	public PrepareCreateQuestionView(Survey survey, Displayable parentForm) {
		super(Constants.MSG_QUESTION_TYPE, Choice.IMPLICIT);
		this.survey = survey;
		this.parentForm = parentForm;
		
		append(Constants.QUESTION_TYPE_TRUE_FALSE, null);
		append(Constants.QUESTION_TYPE_MULTIPLE_CHOICE, null);
		append(Constants.QUESTION_TYPE_FLOAT_NUMBER, null);
		append(Constants.QUESTION_TYPE_OPEN, null);
		
		addCommand(cmdBack);
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		} else {
			switch (this.getSelectedIndex()) {
			case 0:
				MainView.getDisplay().setCurrent(new CreateTrueFalseQuestionView(survey, this));
				break;
			case 1:
				MainView.getDisplay().setCurrent(new CreateMultipleChoiceQuestionView(survey, this));
				break;
			case 2:
				MainView.getDisplay().setCurrent(new CreateFloatNumberQuestionView(survey, this));
				break;
			case 3:
				MainView.getDisplay().setCurrent(new CreateOpenQuestionView(survey, this));
				break;
			}
		}
	}
}
