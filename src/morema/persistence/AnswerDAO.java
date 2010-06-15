package morema.persistence;

import java.util.Vector;

import javax.microedition.rms.RecordFilter;

import morema.model.AbstractModel;
import morema.model.Answer;
import morema.model.Question;
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
	
	public Object[] listByQuestion(final Question question) throws MoremaException {
		return getRecords(new RecordFilter() {
			public boolean matches(byte[] candidate) {
				Object[] fields = genericalDeserialize(candidate, new Class[] { Integer.class, Integer.class });
				Integer questionId = (Integer) fields[1];
				return question.id.equals(questionId);
			}
		}, null);
	}

	protected AbstractModel deserialize(byte[] data) {
		Object[] fields = genericalDeserialize(data, new Class[] { Integer.class });
		Integer questionTypeId = ((Integer)fields[0]);
		Class answerType = null;
		if (questionTypeId.equals(Question.QUESTION_TYPE_TrueFalse)) {
			answerType = Boolean.class;
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
				questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
			answerType = Vector.class;
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_FloatNumber)) {
			answerType = Float.class;
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_Open)) {
			answerType = String.class;
		}
		fields = genericalDeserialize(data, new Class[] { Integer.class, Integer.class, answerType });
		Answer answer = new Answer(fields[2]);
		answer.questionTypeId = (Integer) fields[0];
		answer.questionId = (Integer) fields[1];
		answer.surveyId = surveyId;
		return answer;
	}

	protected byte[] serialize(AbstractModel model) {
		Answer answer = (Answer) model;
		Object[] objects = new Object[] { answer.questionTypeId, answer.questionId, answer.answer };
		return genericalSerialize(objects);
	}

}
