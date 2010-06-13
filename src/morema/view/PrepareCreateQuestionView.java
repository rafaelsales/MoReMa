package morema.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.model.Survey;

public class PrepareCreateQuestionView extends List implements CommandListener {
	
	private final Displayable parentForm;
	private final Survey survey;
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 0);
	
	public PrepareCreateQuestionView(Survey survey, Displayable parentForm) {
		super("Tipo de pergunta", Choice.IMPLICIT);
		this.survey = survey;
		this.parentForm = parentForm;
		
		append("Sim/Não", null);
		append("Múltipla escolha", null);
		append("Número decimal", null);
		append("Aberta", null);
		
		addCommand(cmdBack);
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		} else {
			switch (this.getSelectedIndex()) {
			case 0:
				MainView.getDisplay().setCurrent(new CreateTrueFalseQuestionView(survey, parentForm));
				break;
			case 1:
				MainView.getDisplay().setCurrent(new CreateMultipleChoiceQuestionView(survey, parentForm));
				break;
			case 2:
				MainView.getDisplay().setCurrent(new CreateFloatNumberQuestionView(survey, parentForm));
				break;
			case 3:
				MainView.getDisplay().setCurrent(new CreateOpenQuestionView(survey, parentForm));
				break;
			}
		}
	}
}
