package morema.persistence;

import morema.model.AbstractModel;
import morema.model.FloatNumberQuestion;
import morema.model.IntegerNumberQuestion;
import morema.model.MultipleChoiceMultipleAnswerQuestion;
import morema.model.MultipleChoiceOneAnswerQuestion;
import morema.model.OpenQuestion;
import morema.model.Question;
import morema.model.TrueFalseQuestion;
import morema.util.MoremaException;

public class QuestionDAO extends AbstractDAO {

	private final Integer surveyId;

	public QuestionDAO(int surveyId) throws MoremaException {
		super("Survey" + surveyId + "Question");
		this.surveyId = new Integer(surveyId);
	}
	
	public QuestionDAO(Integer surveyId) throws MoremaException {
		this(surveyId.intValue());
	}

	protected AbstractModel deserialize(byte[] data) {
		Object[] fields = genericalDeserialize(data, new Class[] { Integer.class });
		Integer questionTypeId = ((Integer)fields[0]);
		if (questionTypeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
			fields = genericalDeserialize(data, new Class[] { Integer.class, String.class });
			TrueFalseQuestion question = new TrueFalseQuestion((String) fields[1]);
			question.surveyId = surveyId;
			return question;
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_IntegerNumber)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
			
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_Open)) {
			
		}
		return null;
	}

	protected byte[] serialize(AbstractModel model) {
		Object[] objects = null;
		if (model instanceof TrueFalseQuestion) {
			TrueFalseQuestion question = (TrueFalseQuestion) model;
			objects = new Object[] { question.typeId, question.question };
		} else if (model instanceof MultipleChoiceOneAnswerQuestion) {
			
		} else if (model instanceof MultipleChoiceMultipleAnswerQuestion) {
			
		} else if (model instanceof IntegerNumberQuestion) {
			
		} else if (model instanceof FloatNumberQuestion) {
			
		} else if (model instanceof OpenQuestion) {
			
		}
		return genericalSerialize(objects);
	}

}