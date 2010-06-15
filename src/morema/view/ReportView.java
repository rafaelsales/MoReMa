package morema.view;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

import morema.business.AnswerBS;
import morema.business.QuestionBS;
import morema.model.FloatNumberQuestion;
import morema.model.MultipleChoiceQuestion;
import morema.model.OpenQuestion;
import morema.model.Question;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.Constants;
import morema.util.MoremaException;

public class ReportView extends Form implements CommandListener {

	private final Survey survey;
	private final Displayable parentForm;
	private final Command cmdBack = new Command(Constants.COMMAND_BACK, Command.CANCEL, 0);
	private final Command cmdOk = new Command(Constants.COMMAND_SELECT, Command.SCREEN, 1);
	private final Object[] questions;

	public ReportView(final Survey survey, Displayable parentForm) throws MoremaException {
		super(survey.title);
		this.survey = survey;
		this.parentForm = parentForm;

		questions = QuestionBS.list(this.survey);

		for (int i = 0; i < questions.length; i++) {
			Question genericQuestion = (Question) questions[i];
			Object[] answers = null;
			//Obtém as respostas da questão, caso não seja questão aberta:
			if (!genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				answers = AnswerBS.listByQuestion(genericQuestion);
			}
			
			String prefixQuestion = Constants.MSG_QUESTION + " No." + genericQuestion.id.toString() + ": ";
			StringItem questionTitleItem = new StringItem(prefixQuestion + genericQuestion.question, null);
			append(questionTitleItem);
			if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
				TrueFalseQuestion question = (TrueFalseQuestion) genericQuestion;
				Hashtable trueFalsePercentage = AnswerBS.getPercentageTrueFalseQuestion(question, answers);
				append(new StringItem(Constants.QUESTION_LABEL_FALSE + ": " + trueFalsePercentage.get(Boolean.FALSE).toString(), null));
				append(new StringItem(Constants.QUESTION_LABEL_TRUE + ": " + trueFalsePercentage.get(Boolean.TRUE).toString(), null));
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
				append(new StringItem(Constants.MSG_AVERAGE + ": " + new Float(average).toString(), null));
			} else if (genericQuestion.typeId.equals(Question.QUESTION_TYPE_Open)) {
				//Caso seja questão aberta, oferece a opção de o usuário listar as respostas:
				final OpenQuestion question = (OpenQuestion) genericQuestion;
				StringItem cmdViewOpenQuestionAnswers = new StringItem(Constants.COMMAND_VIEW_OPEN_QUESTION_ANSWERS, null, StringItem.BUTTON);
				cmdViewOpenQuestionAnswers.setDefaultCommand(cmdOk);
				cmdViewOpenQuestionAnswers.setItemCommandListener(new ItemCommandListener() {
					public void commandAction(Command c, Item item) {
						MainView.getDisplay().setCurrent(new AnswersOpenQuestionView(survey, question, ReportView.this));
					}
				});
				append(cmdViewOpenQuestionAnswers);
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