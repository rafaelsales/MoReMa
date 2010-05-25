package morema.view;

import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.MoremaException;

public class ListSurveyView extends List implements CommandListener {

	private Vector listSurvey;
	private final Displayable parentForm;
	private final Command cmdAnswer = new Command("Responder", Command.OK, 0);
	private final Command cmdUpdate = new Command("Alterar", Command.ITEM, 1);
	private final Command cmdRemove = new Command("Remover", Command.ITEM, 2);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 3);
	
	public ListSurveyView(Displayable parentForm) throws MoremaException {
		super("Pesquisas", Choice.IMPLICIT);
		this.parentForm = parentForm;
		
		list();
		
		addCommand(cmdAnswer);
		addCommand(cmdUpdate);
		addCommand(cmdRemove);
		addCommand(cmdBack);
		setCommandListener(this);
	}

	public void list() throws MoremaException {
		listSurvey = SurveyBS.list();
		for (int i = 0; i < listSurvey.size(); i++) {
			append(((Survey) listSurvey.elementAt(i)).title, null);
		}
	}

	public void remove(Survey survey) {
		if (survey != null) {
			try {
				SurveyBS.removeSurvey(survey);
			} catch (MoremaException e) {
				MainView.showAlert(e.getMessage(), null);
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
				MainView.showAlert(e.getMessage(), null);
			}
		}
	}
	
	public void commandAction(Command c, Displayable d) {
		Survey survey = null;
		if (this.getSelectedIndex() != -1) {
			survey = (Survey) listSurvey.elementAt(this.getSelectedIndex());
		}
		if (c.getLabel().equals(cmdAnswer.getLabel())) {
			answer(survey);
		} else if (c.getLabel().equals(cmdUpdate.getLabel())) {
			select(survey);
		} else if (c.getLabel().equals(cmdRemove.getLabel())) {
			remove(survey);
		} else if (c.getLabel().equals(cmdBack.getLabel())) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}