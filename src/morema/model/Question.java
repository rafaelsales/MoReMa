package morema.model;

public abstract class Question extends AbstractModel {
	public enum QuestionType {
		TrueFalse(0),
		MultipleChoiceOneAnswer(1),
		MultipleChoiceMultipleAnswer(2),
		IntegerNumber(3),
		FloatNumber(4),
		Open(4);
		
		public final Byte QUESTION_TYPE_ID;
		
		QuestionType(int questionTypeId) {
			QUESTION_TYPE_ID = (byte) questionTypeId;
		}
	}
	
	public Question(QuestionType questionType) {
		typeId = questionType.QUESTION_TYPE_ID;
	}
	
	public Short surveyId;
	public Byte typeId;
	public String question;
}
