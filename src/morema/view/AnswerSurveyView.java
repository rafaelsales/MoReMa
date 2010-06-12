package morema.view;

import java.util.Vector;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

import morema.business.AnswerBS;
import morema.business.QuestionBS;
import morema.model.Answer;
import morema.model.MultipleChoice;
import morema.model.Question;
import morema.model.Survey;
import morema.util.MoremaException;

public class AnswerSurveyView extends Form implements CommandListener {

	private final Survey survey;
	private final Displayable parentForm;
	private final Command cmdSave = new Command("Salvar", Command.OK, 0);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 1);
	private final Object[] questions;
	private final Object[] fields;

	public AnswerSurveyView(Survey survey, Displayable parentForm) throws MoremaException {
		super(survey.title);
		this.survey = survey;
		this.parentForm = parentForm;
		
		questions = QuestionBS.getQuestions(survey);
		fields = new Object[questions.length];
		for (int i = 0; i < questions.length; i++) {
			Question genericQuestion = (Question) questions[i];
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				ChoiceGroup choiceGroup = new ChoiceGroup(genericQuestion.question, ChoiceGroup.EXCLUSIVE);
				choiceGroup.append("Não", null);
				choiceGroup.append("Sim", null);
				fields[i] = choiceGroup;
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
					genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
				MultipleChoice question = (MultipleChoice) genericQuestion;
				int choiceType = question.multipleAnswer ? ChoiceGroup.MULTIPLE : ChoiceGroup.EXCLUSIVE;
				ChoiceGroup choiceGroup = new ChoiceGroup(genericQuestion.question, choiceType);
				for (int j = 0; j < question.choices.size(); j++) {
					choiceGroup.append((String) question.choices.elementAt(j), null);
				}
				fields[i] = choiceGroup;
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_IntegerNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				fields[i] = new TextField(genericQuestion.question, null, getWidth(), TextField.ANY);
			}
			append((Item) fields[i]);
		}
		
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	private void showNextQuestion() {

	}

	private void answerQuestion() {

	}
	
	private void save() {
		for (int i = 0; i < questions.length; i++) {
			Question genericQuestion = (Question) questions[i];
			Answer answer = null;
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				ChoiceGroup choiceGroup = (ChoiceGroup) fields[i];
				Boolean booleanAnswer;
				if (choiceGroup.getSelectedIndex() == 0) {
					booleanAnswer = Boolean.FALSE;
				} else {
					booleanAnswer = Boolean.TRUE;
				}
				answer = new Answer(booleanAnswer);
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
					genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
				ChoiceGroup choiceGroup = (ChoiceGroup) fields[i];
				boolean[] choices = new boolean[choiceGroup.size()];
				int numberSelectedAnswers = choiceGroup.getSelectedFlags(choices);
				Vector idsSelectedChoices = new Vector(numberSelectedAnswers);
				for (int j = 0; j < choices.length; j++) {
					if (choices[j] == true) {
						idsSelectedChoices.addElement(new Integer(j));
					}
				}
				answer = new Answer(idsSelectedChoices);
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_IntegerNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				TextField textField = (TextField) fields[i];
				answer = new Answer(textField.getString());
			}
			answer.surveyId = survey.id;
			answer.questionId = genericQuestion.id;
			answer.questionTypeId = genericQuestion.typeId;
			try {
				AnswerBS.answer(answer);
			} catch (MoremaException e) {
				MainView.showAlert(e, null);
			}
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		} else if (c == cmdSave) {
			save();
		}
	}
}