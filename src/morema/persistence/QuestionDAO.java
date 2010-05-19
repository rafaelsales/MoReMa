package morema.persistence;

import morema.model.AbstractModel;
import morema.model.Question;

public class QuestionDAO extends AbstractDAO {

	public QuestionDAO(int surveyId) {
		super("Survey" + surveyId + "Questions");
	}

	@Override
	protected Question deserialize(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] serialize(AbstractModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
