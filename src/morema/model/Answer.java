package morema.model;

public class Answer extends AbstractModel {
	
	public Short surveyId;
	public Short questionId;
	public Object answer;

	public Answer(Object answer) {
		this.answer = answer;
		
	}
}
