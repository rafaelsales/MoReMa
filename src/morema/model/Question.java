package morema.model;

public abstract class Question extends AbstractModel {
	
	public static final Integer QUESTION_TYPE_TrueFalse = new Integer(0);
	public static final Integer QUESTION_TYPE_MultipleChoiceOneAnswer = new Integer(1);
	public static final Integer QUESTION_TYPE_MultipleChoiceMultipleAnswer = new Integer(2);
	public static final Integer QUESTION_TYPE_FloatNumber = new Integer(3);
	public static final Integer QUESTION_TYPE_Open = new Integer(4);
		
	
	public Question(String question, Integer questionTypeId) {
		this.question = question;
		this.typeId = questionTypeId;
	}
	
	public transient Integer surveyId;
	
	public Integer typeId;
	public String question;
}
