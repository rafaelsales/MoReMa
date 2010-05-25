package morema.model;

import java.util.Vector;

public class Answer extends AbstractModel {
	
	public Integer surveyId;
	public Integer questionId;
	public Integer questionTypeId;
	public Object answer;

	public Answer(Object answer) {
		this.answer = answer;
	}
	
	public Vector getAsVector() {
		return (Vector) answer;
	}
}
