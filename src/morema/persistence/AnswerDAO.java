package morema.persistence;

import morema.model.Answer;

public class AnswerDAO extends AbstractDAO {

	public AnswerDAO(short surveyId) {
		super("Survey" + surveyId + "Answers");
	}

	@Override
	protected Answer deserialize(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] serialize(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
