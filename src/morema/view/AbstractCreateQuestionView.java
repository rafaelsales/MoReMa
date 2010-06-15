package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public abstract class AbstractCreateQuestionView extends Form implements CommandListener {
	
	protected final Survey survey;
	protected final Displayable parentForm;
	protected final TextField tfTitle;
	protected final Command cmdSave = new Command(Constants.COMMAND_SAVE, Command.ITEM, 0);
	protected final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 1);
	
	public AbstractCreateQuestionView(Survey survey, Displayable parentForm) {
		super(Constants.COMMAND_ADD_QUESTION);
		this.survey = survey;
		this.parentForm = parentForm;
		
		tfTitle = new TextField(Constants.MSG_QUESTION_TEXT, null, Constants.TEXTFIELD_MAX_SIZE, TextField.ANY);
		
		append(tfTitle);
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	protected abstract void addQuestion() throws MoremaException;
	
	public void commandAction(Command c, Displayable d) {
		if (c == cmdSave) {
			try {
				addQuestion();
				MainView.showConfirmation(Constants.MSG_DATA_SAVED_SUCCESSFULLY + "\n" + Constants.MSG_CREATE_ANOTHER_QUESTION, new PrepareCreateQuestionView(survey, new CreateSurveyView(survey, MainView.getMainView())), MainView.getMainView());
			} catch (MoremaException e) {
				MainView.showAlert(e, this);
			}
		} else if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
