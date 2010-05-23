package morema.model;

public class Answer extends AbstractModel {
	
	public Integer surveyId;
	public Integer questionId;
	public Object answer;

	public Answer(Object answer) {
		this.answer = answer;
		
	}
}
