package morema.persistence;

import morema.model.AbstractModel;
import morema.model.Answer;

public class AnswerDAO extends AbstractDAO {

	public AnswerDAO(int surveyId) {
		super("Survey" + surveyId + "Answers");
	}

	@Override
	protected Answer deserialize(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] serialize(AbstractModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
