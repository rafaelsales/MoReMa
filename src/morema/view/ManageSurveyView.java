package morema.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;

public class ManageSurveyView extends List implements CommandListener {

	private Object[] listSurvey;
	protected final Displayable parentForm;
	private final Command cmdAnswer = new Command(Constants.COMMAND_ANSWER, Command.OK, 0);
	private final Command cmdRemove = new Command(Constants.COMMAND_REMOVE, Command.ITEM, 1);
	private final Command cmdReport = new Command(Constants.COMMAND_SHOW_REPORT, Command.ITEM, 2);
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 3);
	
	public ManageSurveyView(Displayable parentForm) throws MoremaException {
		super(Constants.COMMAND_MANAGE_SURVEY, Choice.IMPLICIT);
		this.parentForm = parentForm;
		
		list();
		
		addCommand(cmdAnswer);
		addCommand(cmdRemove);
		addCommand(cmdReport);
		addCommand(cmdBack);
		setCommandListener(this);
	}
	

	public void list() throws MoremaException {
		listSurvey = SurveyBS.list();
		for (int i = 0; i < listSurvey.length; i++) {
			append(((Survey) listSurvey[i]).title, null);
		}
	}

	public void remove(Survey survey) {
		if (survey != null) {
			MainView.getDisplay().setCurrent(new RemoveSurveyView(survey, this));
		}
	}
	
	public void answer(Survey survey) {
		if (survey != null) {
			try {
				MainView.getDisplay().setCurrent(new AnswerSurveyView(survey, this));
			} catch (MoremaException e) {
				MainView.showAlert(e, null);
			}
		}
	}
	
	public void viewReport(Survey survey) {
		if (survey != null) {
			try {
				MainView.getDisplay().setCurrent(new ReportView(survey, this));
			} catch (MoremaException e) {
				MainView.showAlert(e, null);
			}
		}
	}
	
	public void commandAction(Command c, Displayable d) {
		Survey survey = null;
		if (this.getSelectedIndex() != -1) {
			survey = (Survey) listSurvey[this.getSelectedIndex()];
		}
		if (c == cmdAnswer) {
			answer(survey);
		} else if (c == cmdRemove) {
			remove(survey);
		} else if (c == cmdReport) {
			viewReport(survey);
		} else if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		} else {
			answer(survey);
		}
	}
}