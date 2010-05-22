package morema.persistence;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;

import morema.model.AbstractModel;
import morema.model.Survey;
import morema.util.MoremaException;

public class SurveyDAO extends AbstractDAO {

	public SurveyDAO() throws MoremaException {
		super("Survey");
	}

	public Survey getRecord(int id) throws MoremaException {
		return (Survey) super.getRecordGeneric(id);
	}

	public Survey getByName(final String title) throws MoremaException {
		RecordFilter titleFilter = new RecordFilter() {

			public boolean matches(byte[] candidate) {
				return (((Survey) deserialize(candidate)).title.equals(title));
			}
		};
		try {
			RecordEnumeration result = recordStore.enumerateRecords(titleFilter, null, false);
			if (result.hasNextElement()) {
				return (Survey) deserialize(result.nextRecord());
			}
		} catch (Exception e) {
			MoremaException.throwAsMoremaException(e);
		}
		return null;
	}

	protected AbstractModel deserialize(byte[] data) {
		Object[] fields = genericalDeserialize(data, new Class[] { String.class });
		Survey survey = new Survey();
		survey.title = (String) fields[0];
		return survey;
	}

	protected byte[] serialize(AbstractModel model) {
		Survey survey = (Survey) model;
		return genericalSerialize(new Object[] { survey.title });
	}
}