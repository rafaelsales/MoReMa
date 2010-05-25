package morema.view;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import morema.business.QuestionBS;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.MoremaException;

public class AnswerSurveyView extends Form implements CommandListener {

	private final Survey survey;
	private final Displayable parentForm;
	private final Command cmdSave = new Command("Salvar", Command.ITEM, 0);
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 1);
	private final Vector questions;

	public AnswerSurveyView(Survey survey, Displayable parentForm) throws MoremaException {
		super(survey.title);
		this.survey = survey;
		this.parentForm = parentForm;
		
		questions = QuestionBS.getQuestions(survey);
		for (int i = 0; i < questions.size(); i++) {
			Question genericQuestion = (Question) questions.elementAt(i);
			StringItem title = new StringItem(genericQuestion.question, null);
			append(title);
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				TrueFalseQuestion question = (TrueFalseQuestion) genericQuestion;
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_IntegerNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
				
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				
			}
		}
		
		addCommand(cmdBack);
		addCommand(cmdSave);
		setCommandListener(this);
	}
	
	private void showNextQuestion() {

	}

	private void answerQuestion() {

	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		
	}
}