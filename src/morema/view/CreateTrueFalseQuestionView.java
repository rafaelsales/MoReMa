package morema.view;

import javax.microedition.lcdui.Displayable;

import morema.business.QuestionBS;
import morema.model.Survey;
import morema.model.TrueFalseQuestion;
import morema.util.MoremaException;

public class CreateTrueFalseQuestionView extends AbstractCreateQuestionView {
	
	public CreateTrueFalseQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
	}
	
	protected void addQuestion() throws MoremaException {
		TrueFalseQuestion question = new TrueFalseQuestion(tfTitle.getString());
		question.surveyId = survey.id;
		QuestionBS.addTrueFalseQuestion(question);
	}
}
