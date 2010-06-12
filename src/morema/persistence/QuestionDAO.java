package morema.persistence;

import java.util.Vector;

import morema.model.AbstractModel;
import morema.model.FloatNumberQuestion;
import morema.model.IntegerNumberQuestion;
import morema.model.MultipleChoice;
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
		} else if (questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ||
				questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceOneAnswer)) {
			fields = genericalDeserialize(data, new Class[] { Integer.class, String.class, Vector.class });
			boolean multipleAnswer = questionTypeId.equals(Question.QUESTION_TYPE_MultipleChoiceMultipleAnswer) ? true : false; 
			MultipleChoice question = new MultipleChoice((String) fields[1], (Vector) fields[2], multipleAnswer);
			question.surveyId = surveyId;
			return question;
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
		} else if (model instanceof MultipleChoice) {
			MultipleChoice question = (MultipleChoice) model;
			objects = new Object[] { question.typeId, question.question, question.choices };
		} else if (model instanceof IntegerNumberQuestion) {
			
		} else if (model instanceof FloatNumberQuestion) {
			
		} else if (model instanceof OpenQuestion) {
			
		}
		return genericalSerialize(objects);
	}

}