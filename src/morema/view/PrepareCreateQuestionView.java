package morema.view;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import morema.model.Question;
import morema.model.Survey;

public class PrepareCreateQuestionView extends List implements CommandListener {
	
	private final Displayable parentForm;
	private final Survey survey;
	private final Command cmdChoose = new Command("Escolher", Command.ITEM, 0);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 1);
	
	public PrepareCreateQuestionView(Survey survey, Displayable parentForm) {
		super("Tipo de pergunta", Choice.IMPLICIT);
		this.survey = survey;
		this.parentForm = parentForm;
		
		append("Verdadeiro/Falso", null);
		append("Múltipla escolha - Múltipla resposta", null);
		append("Múltipla escolha - Uma resposta", null);
		append("Número inteiro", null);
		append("Número decimal", null);
		append("Aberta", null);
		
		addCommand(cmdChoose);
		addCommand(cmdBack);
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d) {
		if (c.getLabel().equals(cmdChoose.getLabel())) {
			switch (this.getSelectedIndex()) {
			case 0:
				MainView.getDisplay().setCurrent(new CreateTrueFalseQuestionView(survey, this));
				break;
			case 1:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			}
		} else if (c.getLabel().equals(cmdBack.getLabel())) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}
