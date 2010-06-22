package morema.view;

import java.util.Vector;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.MultipleChoiceQuestion;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;
import morema.util.Util;

public class CreateMultipleChoiceQuestionView extends AbstractCreateQuestionView {
	
	private final Command cmdAddChoice = new Command(Constants.COMMAND_QUESTION_MULTIPLE_CHOICE_ADD_CHOICE, Command.ITEM, 2);
	private final Command cmdRemoveBlankChoices = new Command(Constants.COMMAND_QUESTION_MULTIPLE_CHOICE_REMOVE_BLANK_CHOICES, Command.ITEM, 3);
	private final Vector listTextFieldsChoices = new Vector();
	private final ChoiceGroup cgMultipleAnswer;
	
	public CreateMultipleChoiceQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm, Constants.QUESTION_TYPE_MULTIPLE_CHOICE);
		
		cgMultipleAnswer = new ChoiceGroup(Constants.QUESTION_TYPE_MULTIPLE_CHOICE_MULTIPLE_ANSWER, ChoiceGroup.EXCLUSIVE);
		cgMultipleAnswer.append(Constants.MSG_NO, null);
		cgMultipleAnswer.append(Constants.MSG_YES, null);
		append(cgMultipleAnswer);
		
		//Inicialmente adiciona duas opções:
		addChoice();
		addChoice();
		addCommand(cmdAddChoice);
		addCommand(cmdRemoveBlankChoices);
	}
	
	protected void addQuestion() throws MoremaException {
		removeBlankChoices();
		
		Vector listChoices = new Vector(listTextFieldsChoices.size());
		for (int i = 0; i < listTextFieldsChoices.size(); i++) {
			listChoices.addElement(((TextField) listTextFieldsChoices.elementAt(i)).getString());
		}
		boolean multipleAnswer = (cgMultipleAnswer.getSelectedIndex() == 1);
		MultipleChoiceQuestion question = new MultipleChoiceQuestion(tfTitle.getString(), listChoices, multipleAnswer);
		question.surveyId = survey.id;
		QuestionBS.addMultipleChoiceQuestion(question);		
	}
	
	/**
	 * Adiciona um TextField para escrever o texto de uma opção 
	 */
	private void addChoice() {
		TextField tfChoice = new TextField(Constants.QUESTION_LABEL_CHOICE + " " + (listTextFieldsChoices.size() + 1) + "", null, Constants.TEXTFIELD_MAX_SIZE, TextField.ANY);
		listTextFieldsChoices.addElement(tfChoice);
		append(tfChoice);
	}
	
	/**
	 * Remove os TextFields de opões sem texto
	 */
	private void removeBlankChoices() {
		for (int i = listTextFieldsChoices.size() - 1, fieldIndex = this.size() - 1; i >= 0; i--, fieldIndex--) {
			if (Util.isEmpty(((TextField)listTextFieldsChoices.elementAt(i)).getString())) {
				delete(fieldIndex);
				listTextFieldsChoices.removeElementAt(i);
			}
		}
	}

	public void commandAction(Command c, Displayable d) {
		super.commandAction(c, d);
		if (c == cmdAddChoice) {
			addChoice();
		} else if (c == cmdRemoveBlankChoices) {
			removeBlankChoices();
		}
	}
}
