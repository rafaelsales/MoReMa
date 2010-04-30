package morema.persistence;

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
	protected byte[] serialize(Object object) {
		Survey survey = (Survey) object;
		
		return null;
	}
}
