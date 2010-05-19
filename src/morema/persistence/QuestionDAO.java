package morema.persistence;

import morema.model.AbstractModel;

public class QuestionDAO extends AbstractDAO {

	public QuestionDAO(int surveyId) {
		super("Survey" + surveyId + "Questions");
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
