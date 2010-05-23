package morema.persistence;

import morema.model.AbstractModel;
import morema.util.MoremaException;

public class QuestionDAO extends AbstractDAO {

	public QuestionDAO(int surveyId) throws MoremaException {
		super("Survey" + surveyId + "Question");
	}
	
	public QuestionDAO(Integer surveyId) throws MoremaException {
		this(surveyId.intValue());
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
