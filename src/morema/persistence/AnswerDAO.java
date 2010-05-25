package morema.persistence;

import morema.model.AbstractModel;
import morema.model.Answer;
import morema.model.Question;
import morema.model.TrueFalseQuestion;
import morema.util.MoremaException;

public class AnswerDAO extends AbstractDAO {

	private final Integer surveyId;

	public AnswerDAO(int surveyId) throws MoremaException {
		super("Survey" + surveyId + "Answer");
		this.surveyId = new Integer(surveyId);
	}
	
	public AnswerDAO(Integer surveyId) throws MoremaException {
		this(surveyId.intValue());
	}

	protected AbstractModel deserialize(byte[] data) {
		Object[] fields = genericalDeserialize(data, new Class[] { Integer.class });
		Integer questionTypeId = ((Integer)fields[0]);
		Answer answer = null;
		if (questionTypeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
			fields = genericalDeserialize(data, new Class[] { Integer.class, Integer.class, Boolean.class });
			TrueFalseQuestion question = new TrueFalseQuestion((String) fields[1]);
			question.surveyId = surveyId;
			return question;
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_IntegerNumber)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_Open)) {
			
		}
		return answer;
	}

	protected byte[] serialize(AbstractModel model) {
		Answer answer = (Answer) model;
		Object[] objects = new Object[] { answer.questionTypeId, answer.questionId, answer.answer };
		return genericalSerialize(objects);
	}

}
