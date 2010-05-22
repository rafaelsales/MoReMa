package morema.test;

import java.util.Vector;

import morema.business.SurveyBS;
import morema.model.Survey;
import morema.util.MoremaException;

public class BusinessTest {
	public static void main(String[] args) {
	}
	
	public static void createSurvey(int quantity) throws MoremaException {		
		for (int i = 0; i < quantity; i++) {
			Survey survey = new Survey();
			survey.title = "Survey No." + i;
			SurveyBS.createSurvey(survey);
			System.out.println("Survey id: " + survey.id + " has been created");			
		}
	}
	
	public static void listSurvey() throws Exception {
		Vector listSurvey = SurveyBS.list();
		for (int i = 0; i < listSurvey.size(); i++) {
			Survey s = (Survey) listSurvey.elementAt(i);
			System.out.println(s.id + " " + s.title);
		}
	}
}
