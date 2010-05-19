package morema.persistence;

import morema.model.AbstractModel;

public class SurveyDAO extends AbstractDAO {
	
	public SurveyDAO() {
		super("Survey");
	}

	protected AbstractModel deserialize(byte[] data) {
		
		return null;
	}

	protected byte[] serialize(AbstractModel model) {
		
		return null;
	}
}