package morema.view;

import javax.microedition.lcdui.Displayable;

import morema.business.QuestionBS;
import morema.model.OpenQuestion;
import morema.model.Survey;
import morema.util.MoremaException;

public class CreateOpenQuestionView extends AbstractCreateQuestionView {
	
	public CreateOpenQuestionView(Survey survey, Displayable parentForm) {
		super(survey, parentForm);
	}
	
	protected void addQuestion() throws MoremaException {
		OpenQuestion question = new OpenQuestion(tfTitle.getString());
		question.surveyId = survey.id;
		QuestionBS.addOpenQuestion(question);
	}
}
