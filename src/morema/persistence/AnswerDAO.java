package morema.persistence;

import morema.model.AbstractModel;

public class AnswerDAO extends AbstractDAO {

	public AnswerDAO(int surveyId) {
		super("Survey" + surveyId + "Answers");
	}

	protected AbstractModel deserialize(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	protected byte[] serialize(AbstractModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
