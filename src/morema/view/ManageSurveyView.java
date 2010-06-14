package morema.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.MoremaException;

public class ManageSurveyView extends List implements CommandListener {

	private Object[] listSurvey;
	private final Displayable parentForm;
	private final Command cmdAnswer = new Command("Responder", Command.OK, 0);
	private final Command cmdUpdate = new Command("Alterar", Command.ITEM, 1);
	private final Command cmdRemove = new Command("Remover", Command.ITEM, 2);
	private final Command cmdReport = new Command("Exibir Relatório", Command.ITEM, 3);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 3);
	
	public ManageSurveyView(Displayable parentForm) throws MoremaException {
		super("Pesquisas", Choice.IMPLICIT);
		this.parentForm = parentForm;
		
		list();
		
		addCommand(cmdAnswer);
		addCommand(cmdUpdate);
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
			try {
				SurveyBS.remove(survey);
			} catch (MoremaException e) {
				MainView.showAlert(e, null);
			}
		}
	}

	public void select(Survey survey) {
		if (survey != null) {
			MainView.getDisplay().setCurrent(new CreateSurveyView(survey, this));
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
		} else if (c == cmdUpdate) {
			select(survey);
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