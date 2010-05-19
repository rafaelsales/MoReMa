package morema.persistence;

import morema.model.AbstractModel;
import morema.model.Survey;

public class SurveyDAO extends AbstractDAO {
	
	public SurveyDAO() {
		super("Survey");
	}

	@Override
	protected Survey deserialize(byte[] data) {
		
		return null;
	}

	@Override
	protected byte[] serialize(AbstractModel model) {
		
		return null;
	}
}