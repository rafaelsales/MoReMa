package morema.view;

import java.util.Vector;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.MultipleChoiceMultipleAnswerQuestion;
import morema.model.Survey;
import morema.util.Constantes;
import morema.util.MoremaException;

public class CreateMultipleChoiceQuestionView extends AbstractCreateQuestionView {
	
	private final Command cmdAddChoice = new Command("Adicionar opção", Command.ITEM, 2);
	private final Vector listTextFieldsChoices = new Vector();
	private final ChoiceGroup cgMultiplaResposta;
	
	public CreateMultipleChoiceQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
		
		cgMultiplaResposta = new ChoiceGroup("Multipla resposta", ChoiceGroup.EXCLUSIVE);
		cgMultiplaResposta.append("Não", null);
		cgMultiplaResposta.append("Sim", null);
		append(cgMultiplaResposta);
		
		addCommand(cmdAddChoice);
	}
	
	protected void addQuestion() {
		Vector listChoices = new Vector(listTextFieldsChoices.size());
		for (int i = 0; i < listTextFieldsChoices.size(); i++) {
			listChoices.addElement(((TextField) listTextFieldsChoices.elementAt(i)).getString());
		}
		try {
			if (cgMultiplaResposta.getSelectedIndex() == 0) {
			} else {
				MultipleChoiceMultipleAnswerQuestion question = new MultipleChoiceMultipleAnswerQuestion(tfTitle.getString(), listChoices);
				question.surveyId = survey.id;
				QuestionBS.addMultipleChoiceQuestion(question);
			}
			MainView.showAlert(Constantes.MSG_DADOS_CADASTRADOS_SUCESSO, parentForm);
		} catch (MoremaException e) {
			MainView.showAlert(e.getMessage(), null);
		}
	}
	
	private void addChoice() {
		TextField tfChoice = new TextField("Opção " + listTextFieldsChoices.size() + "", null, getWidth(), TextField.ANY);
		listTextFieldsChoices.addElement(tfChoice);
		append(tfChoice);
	}
	
	public void commandAction(Command c, Displayable d) {
		super.commandAction(c, d);
		if (c == cmdAddChoice) {
			addChoice();
		}
	}
}
