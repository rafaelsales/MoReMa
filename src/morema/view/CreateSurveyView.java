package morema.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public class CreateSurveyView extends Form implements CommandListener {

	private final Displayable parentForm;
	private final TextField tfTitle;
	private final Survey survey;
	private final Command cmdSave = new Command(Constants.COMMAND_SAVE, Command.ITEM, 0);
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 1);
	
	public CreateSurveyView(Displayable parentForm) {
		this(new Survey(), parentForm);
	}
	
	public CreateSurveyView(Survey survey, Displayable parentForm) {
		super(Constants.COMMAND_CREATE_SURVEY);
		this.parentForm = parentForm;
		this.survey = survey;
		
		tfTitle = new TextField(Constants.MSG_TITLE, survey.title, Constants.TEXTFIELD_MAX_SIZE, TextField.ANY);
		append(tfTitle);
		addCommand(cmdSave);
		addCommand(cmdBack);
		setCommandListener(this);
	}

	private void saveSurvey() {
		try {
			survey.title = tfTitle.getString();
			SurveyBS.save(survey);
			MainView.showAlert(Constants.MSG_DATA_SAVED_SUCCESSFULLY + "\n" + Constants.MSG_CREATE_QUESTION, new PrepareCreateQuestionView(survey, this), false);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdSave) {
			saveSurvey();
		} else if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
