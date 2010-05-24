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
	private final Command cmdSelect = new Command("Selecionar", Command.ITEM, 0);
	private final Command cmdRemove = new Command("Remover", Command.ITEM, 1);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 2);
	
	public ListSurveyView(Displayable parentForm) throws MoremaException {
		super("Pesquisas", Choice.IMPLICIT);
		this.parentForm = parentForm;
		
		list();
		
		addCommand(cmdSelect);
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

	public void remove() {
		try {
			SurveyBS.removeSurvey((Survey) listSurvey.elementAt(getSelectedIndex()));
		} catch (MoremaException e) {
			MainView.showAlert(e.getMessage(), null);
		}
	}

	public void select() {

	}
	
	public void commandAction(Command c, Displayable d) {
		if (c.getLabel().equals(cmdSelect.getLabel())) {
			select();
		} else if (c.getLabel().equals(cmdRemove.getLabel())) {
			remove();
		} else if (c.getLabel().equals(cmdBack.getLabel())) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}