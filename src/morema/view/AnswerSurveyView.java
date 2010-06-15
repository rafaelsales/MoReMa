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
import morema.model.MultipleChoiceQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.util.Constants;
import morema.util.MoremaException;
import morema.util.Util;

public class AnswerSurveyView extends Form implements CommandListener {

	private final Survey survey;
	private final Displayable parentForm;
	private final Command cmdSave = new Command(Constants.COMMAND_SAVE, Command.OK, 0);
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 1);
	private final Object[] questions;
	private final Object[] fields;

	public AnswerSurveyView(Survey survey, Displayable parentForm) throws MoremaException {
		super(Constants.COMMAND_ANSWER + " - " + survey.title);
		this.survey = survey;
		this.parentForm = parentForm;
		
		questions = QuestionBS.list(survey);
		fields = new Object[questions.length];
		for (int i = 0; i < questions.length; i++) {
			Question genericQuestion = (Question) questions[i];
			String prefixQuestion = "No." + genericQuestion.id.toString() + ": ";
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				ChoiceGroup choiceGroup = new ChoiceGroup(prefixQuestion + genericQuestion.question, ChoiceGroup.EXCLUSIVE);
				choiceGroup.append(Constants.QUESTION_LABEL_FALSE, null);
				choiceGroup.append(Constants.QUESTION_LABEL_TRUE, null);
				fields[i] = choiceGroup;
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
					genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) genericQuestion;
				int choiceType = question.multipleAnswer ? ChoiceGroup.MULTIPLE : ChoiceGroup.EXCLUSIVE;
				ChoiceGroup choiceGroup = new ChoiceGroup(prefixQuestion + genericQuestion.question, choiceType);
				for (int j = 0; j < question.choices.size(); j++) {
					choiceGroup.append((String) question.choices.elementAt(j), null);
				}
				fields[i] = choiceGroup;
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
				fields[i] = new TextField(prefixQuestion + genericQuestion.question, null, Constants.TEXTFIELD_MAX_SIZE, TextField.DECIMAL);
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				fields[i] = new TextField(prefixQuestion + genericQuestion.question, null, Constants.TEXTFIELD_MAX_SIZE, TextField.ANY);
			}
			append((Item) fields[i]);
		}
		
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	private void save() {
		try {
			for (int i = 0; i < questions.length; i++) {
				Question question = (Question) questions[i];
				Answer answer = null;
				if (question.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
					ChoiceGroup choiceGroup = (ChoiceGroup) fields[i];
					Boolean booleanAnswer;
					if (choiceGroup.getSelectedIndex() == 0) {
						booleanAnswer = Boolean.FALSE;
					} else {
						booleanAnswer = Boolean.TRUE;
					}
					answer = new Answer(booleanAnswer);
				} else if (question.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
						question.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
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
				} else if (question.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
					TextField textField = (TextField) fields[i];
					Float floatValue = null;
					if (!Util.isEmpty(textField.getString())) {
						floatValue = Float.valueOf(textField.getString());
					}
					answer = new Answer(floatValue);
				} else if (question.typeId.equals(Question.QUESTION_TYPE_Open)) {
					TextField textField = (TextField) fields[i];
					answer = new Answer(textField.getString());
				}
				answer.surveyId = survey.id;
				answer.questionId = question.id;
				answer.questionTypeId = question.typeId;
				AnswerBS.save(answer, question);
			}
			MainView.showAlert(Constants.MSG_DATA_SAVED_SUCCESSFULLY, parentForm, false);
		} catch (MoremaException e) {
			MainView.showAlert(e, null);
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