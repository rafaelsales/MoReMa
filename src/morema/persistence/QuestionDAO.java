package morema.persistence;

public class QuestionDAO extends AbstractDAO {

	public QuestionDAO(short surveyId) {
		super("Survey" + surveyId + "Questions");
	}

	@Override
	protected Object deserialize(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] serialize(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
