package morema.view;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import morema.business.AnswerBS;
import morema.business.QuestionBS;
import morema.model.FloatNumberQuestion;
import morema.model.MultipleChoiceQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.Constantes;
import morema.util.MoremaException;

public class ReportView extends Form implements CommandListener {

	private final Survey survey;
	private final Displayable parentForm;
	private final Command cmdBack = new Command("Voltar", Command.CANCEL, 0);
	private final Object[] questions;
	private final Object[] answers;

	public ReportView(Survey survey, Displayable parentForm) throws MoremaException {
		super(survey.title);
		this.survey = survey;
		this.parentForm = parentForm;

		questions = QuestionBS.list(this.survey);
		answers = AnswerBS.list(this.survey);
		
		for (int i = 0; i < questions.length; i++) {
			Question genericQuestion = (Question) questions[i];
			String prefixQuestion = "No." + genericQuestion.id.toString() + ":";
			StringItem questionTitleItem = new StringItem(prefixQuestion, genericQuestion.question);
			append(questionTitleItem);
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				TrueFalseQuestion question = (TrueFalseQuestion) genericQuestion;
				Hashtable trueFalsePercentage = AnswerBS.getPercentageTrueFalseQuestion(question, answers);
				append(new StringItem(Constantes.QUESTION_LABEL_FALSE + ":", trueFalsePercentage.get(Boolean.TRUE).toString()));
				append(new StringItem(Constantes.QUESTION_LABEL_TRUE + ":", trueFalsePercentage.get(Boolean.FALSE).toString()));
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
					genericQuestion.typeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) genericQuestion;
				Hashtable multipleChoicePercentage = AnswerBS.getPercentageMultipleChoiceQuestion(question, answers);
				for (int j = 0; j < question.choices.size(); j++) {
					append(new StringItem((String) question.choices.elementAt(j) + ":", multipleChoicePercentage.get(new Integer(j)).toString()));
				}
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
				FloatNumberQuestion question = (FloatNumberQuestion) genericQuestion;
				float average = AnswerBS.getAverageFloatNumberQuestion(question, answers);
				append(new StringItem("Média de " + Constantes.QUESTION_LABEL_TRUE + ":", new Float(average).toString()));
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
			}
		}
		
		addCommand(cmdBack);
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmdBack) {
			MainView.getDisplay().setCurrent(parentForm);
		}
	}
}